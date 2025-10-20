package com.example.weather.service;

import com.example.weather.config.Config;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// For reading json files 
import java.nio.file.Files;
import java.nio.file.Paths;

public class WeatherApiClient {

  public void fetchWeatherData(double lat, double lon) {
    // Build URL using Config class
    String apiUrl = Config.getUrl();
    String jsonRequestBody = "";

    try {
      jsonRequestBody = Files.readString(Paths.get("src/main/resources/request.json"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(
            URI.create(apiUrl))
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
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
      System.out.println("Failed to fetch weather data ");
    }
  }
}
