package com.example.weather.model;

public class RuntimeParams {
    public final String country;
    public final String city;
    public final double lat;
    public final double lon;

    private RuntimeParams(String country, String city, double lat, double lon) {
        this.country = country;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
    }

    public static RuntimeParams fromEnv() {
        String country = System.getenv("COUNTRY");
        String city = System.getenv("CITY");
        String latStr = System.getenv("LATITUDE");
        String lonStr = System.getenv("LONGITUDE");

        if (country == null || city == null || latStr == null || lonStr == null) {
            System.err.println("ERROR: Missing required environment variables!");
            System.err.println("Required: COUNTRY, CITY, LATITUDE, LONGITUDE");
            System.err.println("Example:");
            System.err.println("  export COUNTRY=MA");
            System.err.println("  export CITY=Casablanca");
            System.err.println("  export LATITUDE=33.5731");
            System.err.println("  export LONGITUDE=-7.5898");
            System.exit(1);
        }

        double lat = Double.parseDouble(latStr);
        double lon = Double.parseDouble(lonStr);

        return new RuntimeParams(country, city, lat, lon);
    }
}
