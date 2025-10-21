package com.example.weather.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

  private static final Dotenv dotenv = Dotenv.load();

  // API Information
  public static final String API_KEY = dotenv.get("OPENWEATHER_API_KEY");
  public static final String BASE_URL = dotenv.get("BASE_URL");

  // ------------ Cloud Storage Configuration ------------ 
  // AWS S3 Information
  public static final String AWS_REGION = dotenv.get("AWS_REGION");
  public static final String S3_BUCKET_NAME = dotenv.get("S3_BUCKET_NAME");
  // AWS Credentials
  public static String accessKey = dotenv.get("AWS_ACCESS_KEY");
  public static String secretKey = dotenv.get("AWS_SECRET_KEY");
  // -----------------------------------------------------

  // Default Location (Casablanca, Morocco)
  public static final double DEFAULT_LAT = 33.5731;
  public static final double DEFAULT_LON = -7.5898;

  public static String getUrl() {
    return String.format("%s?apikey=%s", BASE_URL, API_KEY);
  }
}
