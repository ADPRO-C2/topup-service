package com.example.secondtreasurebe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://34.143.169.241")
                .allowedMethods("GET", "POST", "DELETE","PUT", "OPTIONS","PATCH")
                .allowedHeaders("Origin", "Content-Type", "Accept")
                .allowCredentials(true);
    }
}

