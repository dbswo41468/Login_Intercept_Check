package com.dw.member.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

//WebConfig : 웹 설정
//Config : 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${aws.s3.access-id}")
    private String accessKey;
    @Value("${aws.s3.access-pw}")
    private String secretKey;

    @Autowired
    Interceptor interceptor; // 우리가 만든 인터셉터 불러옴

    // addInterceptors : 인터셉터를 추가하는 함수 재정의
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] patterns = { "/login", "/join", "/error", "/resources/static/*", "/api/v1/login",
                "/api/v1/login-test","/api/v1/image","/upload" };
        registry.addInterceptor(interceptor).excludePathPatterns(patterns);
    }


    @Bean
	public BasicAWSCredentials AwsCredentials() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		return awsCreds;
	}

	@Bean
	public AmazonS3 AwsS3Client() {

		AmazonS3 s3Builder = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials(new AWSStaticCredentialsProvider(this.AwsCredentials())).build();
		return s3Builder;
	}

}

// package com.dw.member.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import com.amazonaws.auth.AWSStaticCredentialsProvider;
// import com.amazonaws.auth.BasicAWSCredentials;
// import com.amazonaws.regions.Regions;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.AmazonS3ClientBuilder;

// import lombok.Value;

// // WebConfig : 웹 설정
// // Config : 설정
// // 포스트맨에서 확인 하려면 @Configuration 주석 처리
// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Autowired
//     Interceptor interceptor; // 우리가 만든 인터셉터 불러옴

//     // addInterceptors : 인터셉터를 추가하는 함수 재정의
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         // 이하 URL 은 인터셉터에서 제외
//         String[] patterns = { "/login", "/error", "/resources/static/*", "/api/v1/login",
//                 "/api/v1/image", "/upload" }; // 인터셉터
//         // 제외
//         // URL
//         registry.addInterceptor(interceptor).excludePathPatterns(patterns);
//     }

//     // AWS에서 발급한 key가 맞는지 검사
//     @Bean
//     public BasicAWSCredentials AwsCredentials() {
//         BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
//         return awsCreds;
//     }

//     // AWS 위치 및 인증 체크
//     @Bean
//     public AmazonS3 AwsS3Client() {

//         AmazonS3 s3Builder = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2)
//                 .withCredentials(new AWSStaticCredentialsProvider(this.AwsCredentials())).build();
//         return s3Builder;
//     }

// }
