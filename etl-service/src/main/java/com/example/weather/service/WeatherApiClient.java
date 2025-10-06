
package com.example.weather.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.weather.config.Config;

public class WeatherApiClient {

    public void fetchWeatherData(double lat, double lon) {
        // Build URL using Config class
        String apiUrl = Config.getSummaryUrl(lat, lon);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .build();

        // Declare rootNode outside try so it can be used after
        JsonNode rootNode = null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            rootNode = mapper.readTree(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // Now we can safely print rootNode
        if (rootNode != null) {
            System.out.println(rootNode.toPrettyString());
        } else {
            System.out.println("Failed to fetch weather data.");
        }
    }
}
