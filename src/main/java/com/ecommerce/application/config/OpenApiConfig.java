package com.ecommerce.application.config;


import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Ecommerce API", description = "Ecommerce API Documentation", version = "1.0"))

public class OpenApiConfig {

    // Configura el grupo de endpoints de la API
    
}
