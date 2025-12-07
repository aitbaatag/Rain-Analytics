package com.example.weather.service;

import com.example.weather.config.Config;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse; 
import com.example.weather.request.WeatherRequestBody; 

import java.util.Map;

// For reading json files 
import java.nio.file.Files;
import java.nio.file.Paths;

public class WeatherApiClient {

  public void fetchWeatherData(double lat, double lon) {
    // Build URL using Config class
    String apiUrl = Config.getUrl();  
    String location = lat + "," + lon; 

    Map<String, Object> body = WeatherRequestBody.build(location); 
    String json = "";
    try {
    json = new ObjectMapper().writeValueAsString(body);
    } catch (IOException e) {
        e.printStackTrace();
        return; 
    }

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(
            URI.create(apiUrl))
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();

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
