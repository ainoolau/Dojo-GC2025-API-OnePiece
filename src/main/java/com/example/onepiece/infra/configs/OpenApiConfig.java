package com.example.onepiece.infra.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🏴‍☠️ One Piece API")
                        .description("API REST para gerenciar Piratas e Missões nos mares do Novo Mundo!")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Hazel Montezuma")
                                .email("laurinha.pbernardo@gmail.com")
                                .url("https://github.com/ainoolau"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}