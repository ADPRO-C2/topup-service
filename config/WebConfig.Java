package com.example.secondtreasurebe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // CORS mapping for payment methods
        registry.addMapping("/payment-methods/**")
                .allowedOriginPatterns("http://localhost:3000")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH")
                .allowedHeaders("Origin", "Content-Type", "Accept")
                .allowCredentials(true);

        registry.addMapping("/payment-methods/**")
                .allowedOriginPatterns("https://frontend-adpro-c2.vercel.app")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH")
                .allowedHeaders("Origin", "Content-Type", "Accept")
                .allowCredentials(true);

        // CORS mapping for topups
        registry.addMapping("/topups/**")
                .allowedOriginPatterns("http://localhost:3000")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH")
                .allowedHeaders("Origin", "Content-Type", "Accept")
                .allowCredentials(true);

        registry.addMapping("/topups/**")
                .allowedOriginPatterns("https://frontend-adpro-c2.vercel.app/")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH")
                .allowedHeaders("Origin", "Content-Type", "Accept")
                .allowCredentials(true);
    }

}
