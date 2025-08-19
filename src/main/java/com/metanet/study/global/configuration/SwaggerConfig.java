package com.metanet.study.global.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
  @Bean
  OpenAPI openAPI() {
    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new Components().addSecuritySchemes("bearerAuth",
            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                .bearerFormat("JWT")))
        .info(new Info().title("Auth API")
            .description("해당 인증 애플리케이션은 spring security oauth2 를 활용하여 keycloak과 연동합니다.")
            .version("v1.0.0"));
  }
}
