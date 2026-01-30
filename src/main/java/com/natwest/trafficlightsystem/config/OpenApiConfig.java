package com.natwest.trafficlightsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI trafficLightApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Traffic Light Controller API")
                        .description("API to control traffic lights at intersections")
                        .version("1.0.0"));
    }
}
