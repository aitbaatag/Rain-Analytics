package com.example.weather.model;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherData {
    private JsonNode data;
    private String city;
    private String country;
    private double lat;
    private double lon;
    private LocalDateTime fetchedAt;

    public WeatherData(JsonNode data, String city, String country, double lat, double lon) {
        this.data = data;
        this.city = city;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.fetchedAt = LocalDateTime.now();
    }

    public JsonNode getData() {
        return data;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fetchedAt.format(formatter);
    }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return fetchedAt.format(formatter);
    }
}
