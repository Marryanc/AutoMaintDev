package io.automaintdev.automaintdev.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    
    // This allows use of the rest template
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
