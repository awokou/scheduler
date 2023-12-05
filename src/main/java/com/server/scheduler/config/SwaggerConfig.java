package com.server.scheduler.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configuration of swagger.
 * Swagger config will be used both for exporting Swagger UI and for OpenAPI specification generation.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(
                        new Info().title("Backend").description("Backend APIs")
                                .version("v1.0.0")
                                .contact(
                                        new Contact().name("Komivi Awokou").url("https://github.com/awokou/").email("kawokou122@gmail.com")
                                )
                                .license(
                                        new License().name("License").url("/"))
                )
                .externalDocs(
                        new ExternalDocumentation().description("Documentation").url("http://localhost:8080/swagger-ui/index.html")
                );
    }
}
