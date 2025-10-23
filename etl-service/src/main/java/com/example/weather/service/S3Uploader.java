// package com.example.cloudstorage;
//
// import com.example.cloudstorage.config.Config;
// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.s3.S3Client;
// import software.amazon.awssdk.auth.credentials.AwsCredentials;
//
// class S3Uploader {
//   private static final String bucketName = Config.S3_BUCKET_NAME;
//   private static final String region = Config.AWS_REGION;
//   private static final AwsCredentials credentials = AwsCredentials.create(Config.accessKey, Config.secretKey);
//   private static final S3client s3Client = S3Client.builder()
//           .region(Region.of(region))
//           .credentialsProvider(StaticCredentialsProvider.create(credentials))
//           .build();
//
//     public S3Uploader() {
//     }
//
//     public void uploadFile(String filePath, String keyName) {
//         // Logic to upload file to S3
//         System.out.println("Uploading " + filePath + " to bucket " + bucketName + " with key " + keyName);
//         // Actual implementation would use AWS SDK to perform the upload
//     }
// }
