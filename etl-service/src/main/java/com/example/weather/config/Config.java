
package com.example.weather.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public static final String API_KEY = dotenv.get("OPENWEATHER_API_KEY");
    public static final String BASE_URL = dotenv.get("BASE_URL");
    public static final String DAY_SUMMARY_URL = dotenv.get("DAY_SUMMARY_URL");

    // Default example coordinates (Casablanca)
    public static final double DEFAULT_LAT = 33.5731;
    public static final double DEFAULT_LON = -7.5898;

    public static String getSummaryUrl(double lat, double lon) {
        return String.format("%s?lat=%f&lon=%f&appid=%s&units=metric",
                BASE_URL, lat, lon, API_KEY);
    }
}
