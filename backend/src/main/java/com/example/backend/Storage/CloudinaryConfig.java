package com.example.backend.Storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;


@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(@Value("${cloudinary.cloud_name}") String cloudName,
                                @Value("${cloudinary.api_key}") String apiKey,
                                @Value("${cloudinary.api_secret}") String apiSecret){

                                    HashMap<String, String> config = new HashMap<>();
                                    config.put("cloud_name", cloudName);
                                    config.put("api_key", apiKey);
                                    config.put("api_secret", apiSecret);

                                    return new Cloudinary(config);
                                }
                                
    
}
