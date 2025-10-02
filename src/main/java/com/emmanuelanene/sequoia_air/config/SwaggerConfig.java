package com.emmanuelanene.sequoia_air.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Sequoia Airlines API's")
                        .description("Sequoia Airlines - Connecting you with the world and the world with you.")
                        .version("1.0")
                        .contact(new Contact().name("Sequoia Airlines"))
                );
    }
}
