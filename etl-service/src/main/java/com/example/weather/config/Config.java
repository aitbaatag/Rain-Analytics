
package com.example.weather.config;
import io.github.cdimascio.dotenv.Dotenv; 

public class Config {

    // ---------- API Information ---------- 
    private static final Dotenv dotenv = Dotenv.load();
    public static final String API_KEY = dotenv.get("OPENWEATHER_API_KEY");
    public static final String BASE_URL = dotenv.get("BASE_URL");
    public static final String COUNTRY = System.getenv("COUNTRY");

    // ---------- AWS S3 Configuration ----------
    public static final String AWS_REGION = dotenv.get("AWS_REGION");
    public static final String S3_BUCKET_NAME = dotenv.get("S3_BUCKET_NAME");

    // ---------- AWS Credentials ----------
    // public static final String ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
    // public static final String SECRET_KEY = System.getenv("AWS_SECRET_KEY");

    // ---------- Default Location ----------
    public static final double DEFAULT_LAT = 33.5731;
    public static final double DEFAULT_LON = -7.5898;

    // ---------- Build Weather API URL ----------
    public static String getUrl() {
        return String.format("%s?apikey=%s", BASE_URL, API_KEY);
    }

}
