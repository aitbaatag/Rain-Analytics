
package com.example.weather.config;

public class Config {

    // ---------- API Information ----------
    public static final String API_KEY = System.getenv("OPENWEATHER_API_KEY");
    public static final String BASE_URL = System.getenv("BASE_URL");
    public static final String COUNTRY = System.getenv("COUNTRY");

    // ---------- AWS S3 Configuration ----------
    public static final String AWS_REGION = System.getenv("AWS_REGION");
    public static final String S3_BUCKET_NAME = System.getenv("S3_BUCKET_NAME");

    // ---------- AWS Credentials ----------
    public static final String ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
    public static final String SECRET_KEY = System.getenv("AWS_SECRET_KEY");

    // ---------- Default Location ----------
    public static final double DEFAULT_LAT = 33.5731;
    public static final double DEFAULT_LON = -7.5898;

    // ---------- Build Weather API URL ----------
    public static String getUrl() {
        return String.format("%s?apikey=%s", BASE_URL, API_KEY);
    }
}
