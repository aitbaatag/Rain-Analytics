package com.example.weather;

import com.example.weather.config.Config;
import com.example.weather.service.WeatherApiClient;

public class App {
  public static void main(String[] args) {
    WeatherApiClient client = new WeatherApiClient();

    client.fetchWeatherData(Config.DEFAULT_LAT, Config.DEFAULT_LON);
    // System.out.println("Url " + Config.getUrl());
  }
}
