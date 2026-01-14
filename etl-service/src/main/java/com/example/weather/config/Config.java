
package com.example.weather.config;
import io.github.cdimascio.dotenv.Dotenv; 

public class Config {

    // ---------- API Information ---------- 
    private static final Dotenv dotenv = Dotenv.load();
    public static final String API_KEY = dotenv.get("OPENWEATHER_API_KEY");
    public static final String BASE_URL = dotenv.get("BASE_URL");

    // ---------- AWS S3 Configuration ----------
    public static final String AWS_REGION = dotenv.get("AWS_REGION");
    public static final String S3_BUCKET_NAME = dotenv.get("S3_BUCKET_NAME");

    // ---------- AWS Credentials ----------
    public static final String AWS_ACCESS_KEY_ID = dotenv.get("AWS_ACCESS_KEY_ID");
    public static final String AWS_SECRET_ACCESS_KEY = dotenv.get("AWS_SECRET_ACCESS_KEY");

    // ---------- Build Weather API URL ----------
    public static String getUrl() {
        return String.format("%s?apikey=%s", BASE_URL, API_KEY);
    }

}
