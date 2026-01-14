package com.example.weather.service;

import com.example.weather.config.Config;
import com.example.weather.model.WeatherData;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Uploader {
    private final S3Client s3Client;
    private final String bucketName;

    public S3Uploader() {
        this.bucketName = Config.S3_BUCKET_NAME;
        
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(
            Config.AWS_ACCESS_KEY_ID,
            Config.AWS_SECRET_ACCESS_KEY
        );
        
        this.s3Client = S3Client.builder()
            .region(Region.of(Config.AWS_REGION))
            .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
            .build();
    }

    /**
     * Upload weather data to S3 with structured path:
     * s3://bucket/date=YYYY-MM-DD/country=XX/city=CityName/weather_YYYY-MM-DD_HH-mm-ss.json
     */
    public void uploadWeatherData(WeatherData weatherData) {
        String s3Key = buildS3Key(weatherData);
        String jsonContent = weatherData.getData().toPrettyString();

        try {
            PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(s3Key)
                .contentType("application/json")
                .build();

            s3Client.putObject(putRequest, RequestBody.fromString(jsonContent));
            
            System.out.println("✓ Successfully uploaded to S3: s3://" + bucketName + "/" + s3Key);
            
        } catch (S3Exception e) {
            System.err.println("✗ Failed to upload to S3: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Build S3 key with structure: date=YYYY-MM-DD/country=XX/city=CityName/weather_timestamp.json
     */
    private String buildS3Key(WeatherData weatherData) {
        return String.format("date=%s/country=%s/city=%s/weather_%s.json",
            weatherData.getFormattedDate(),
            weatherData.getCountry(),
            weatherData.getCity(),
            weatherData.getFormattedTimestamp()
        );
    }

    public void close() {
        if (s3Client != null) {
            s3Client.close();
        }
    }
}
