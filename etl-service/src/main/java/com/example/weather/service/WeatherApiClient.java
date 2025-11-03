
package com.example.weather.service;

import com.example.weather.config.Config;
import com.example.cloudstorage.S3Uploader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherApiClient {

    private final S3Uploader s3Uploader;
    private final String countryName;

    public WeatherApiClient() {
        this.s3Uploader = new S3Uploader();
        this.countryName = Config.getCountry();
    }

    public void fetchWeatherData() {
        String apiUrl = Config.getUrl();
        String jsonRequestBody = "";

        try {
            jsonRequestBody = Files.readString(Paths.get("src/main/resources/request.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.err.println("Error: Received status code " + response.statusCode());
                return;
            }

            String jsonResponse = response.body();
            JsonNode rootNode = mapper.readTree(jsonResponse);

            // Build structured S3 key
            String key = String.format(
                    "raw/weather/%s/%s/%s.json",
                    countryName.toLowerCase(),
                    cityName.toLowerCase(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            );

            // Upload to S3
            s3Uploader.toS3(key, jsonResponse);
            System.out.println("✅ Successfully uploaded weather data to S3: " + key);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
