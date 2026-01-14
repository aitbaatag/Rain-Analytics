package com.example.weather;

import com.example.weather.model.RuntimeParams;
import com.example.weather.service.WeatherApiClient;

public class App {
  public static void main(String[] args) {
    RuntimeParams params = RuntimeParams.fromEnv();

    System.out.println("Starting ETL for: " + params.city + ", " + params.country);

    WeatherApiClient client = new WeatherApiClient();
    try {
      client.fetchWeatherData(params.country, params.city, params.lat, params.lon);
    } finally {
      client.close();
    }
  }
}
