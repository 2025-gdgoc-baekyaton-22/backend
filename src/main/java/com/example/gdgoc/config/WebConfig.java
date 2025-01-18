package com.example.gdgoc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://dollbomzigi.store", "http://localhost:3000", "http://react:3000")
                .allowedMethods("GET", "POST")
                .allowCredentials(true);
    }
}
