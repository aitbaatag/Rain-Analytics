package com.example.cloudstorage;

import com.example.cloudstorage.config.Config;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

class S3Uploader {
  private static final String bucketName = Config.S3_BUCKET_NAME;
  private static final String region = Config.AWS_REGION;
  private final S3Client s3Client;
  private final AwsCredentials credentials;

  public S3Uploader() {
    this.credentials = AwsCredentials.create(Config.accessKey, Config.secretKey);
    this.s3Client = S3Client.builder()
          .region(Region.of(region))
          .credentialsProvider(StaticCredentialsProvider.create(credentials))
          .build()
  }

  private String generateUploadPath(String keyName) {
    return "s3://" + bucketName + "/" + keyName;
  }

  public static Bucket createBucket(String bucket_name) 
  {
  }

  public static void toS3(String keyName, String jsonContent) {
    String uploadPath = generateUploadPath(keyName);
  if (s3.doesBucketExistV2(bucketName)) { 
      System.out.format("Bucket %s already exists.\n", bucketName);
      b = getBucket(bucketName);
    }
  }
}

