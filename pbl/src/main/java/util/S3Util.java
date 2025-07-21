package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import javax.management.RuntimeErrorException;
import javax.servlet.http.Part;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Util {
	private static final Properties props = PropsLoaderUtil.getProperties("secret/aws_s3.properties");
	
	private static final S3Client s3 = S3Client.builder()
			.region(Region.of(props.getProperty("region-name")))
			.credentialsProvider(
					StaticCredentialsProvider.create(
							AwsBasicCredentials.create(
									props.getProperty("access-key"), 
									props.getProperty("secret-key")
									)
							)
					)
			.build();
	
	
	public static void main(String[] args) {
		System.out.println(s3);
		
		PutObjectRequest por = PutObjectRequest.builder()
				.bucket(props.getProperty("bucket-name"))
				.key("pom.xml")
				.contentType("text/xml")
				.build();
//		s3.putObject(por, RequestBody.fromString("upload test"));
		s3.putObject(por, RequestBody.fromFile(new File("C:\\Users\\tj\\workspaces_phj\\workspace_jsp\\pbl\\pom.xml")));
		
		
	}


	public static void upload(Part part, String key) {
		try {
			uploadInternal(part.getInputStream(), key, part.getSize(), part.getContentType());
		} catch (Exception e) {
			throw new RuntimeException("S3 업로드 실패", e);
		}
	}
	
	public static void upload(File file, String key) {
		try {
			uploadInternal(new FileInputStream(file), key, file.length(), Files.probeContentType(file.toPath()));
		} catch (Exception e) {
			throw new RuntimeException("S3 업로드 실패", e);
		}
	}
	
	private static void uploadInternal(InputStream is, String key, long size, String contentType) {
		PutObjectRequest putReq = PutObjectRequest.builder()
				.bucket(props.getProperty("bucket-name"))
				.key(key)
				.contentType(contentType != null ? contentType : "application/octet-stream")
				.build();
		try(is){
			s3.putObject(putReq, RequestBody.fromInputStream(is, size));
		} catch (Exception e){
			throw new RuntimeException("S3 업로드 중 오류", e);
		}
	}
	
}
