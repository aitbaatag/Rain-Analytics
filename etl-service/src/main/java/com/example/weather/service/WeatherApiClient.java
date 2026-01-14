package com.example.weather.service;

import com.example.weather.config.Config;
import com.example.weather.model.WeatherData;
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
  private final S3Uploader s3Uploader;

  public WeatherApiClient() {
    this.s3Uploader = new S3Uploader();
  }

  public void fetchWeatherData(String country, String city, double lat, double lon) {
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
        .uri(URI.create(apiUrl))
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

    // Now we can safely print and upload to S3
    if (rootNode != null) {
      System.out.println("✓ Fetched weather data for " + city + ", " + country);
      // System.out.println(rootNode.toPrettyString());
      
      // Create WeatherData object and upload to S3
      WeatherData weatherData = new WeatherData(rootNode, city, country, lat, lon);
      s3Uploader.uploadWeatherData(weatherData);
    } else {
      System.out.println("✗ Failed to fetch weather data for " + city + ", " + country);
    }
  }

  public void close() {
    if (s3Uploader != null) {
      s3Uploader.close();
    }
  }
}
