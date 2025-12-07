
package com.example.weather.request;

import java.util.List;
import java.util.Map;

public class WeatherRequestBody {

    public static Map<String, Object> build(String location) {

        return Map.of(
            "location", location,
            "fields", List.of(
                "temperature", "temperatureApparent", "temperatureMin", "temperatureMax",
                "humidity", "pressureSeaLevel", "windSpeed", "windGust",
                "cloudCover", "precipitationIntensity", "precipitationType",
                "thunderstormProbability", "visibility", "uvIndex", "weatherCode",
                "particulateMatter25", "particulateMatter10", "pollutantO3", "epaIndex"
            ),
            "units", "metric",
            "timesteps", List.of("1h"),
            "startTime", "now",
            "endTime", "nowPlus6h",
            "dailyStartHour", 6
        );
    }
}
