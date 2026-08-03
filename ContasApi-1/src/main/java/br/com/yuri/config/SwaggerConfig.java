package br.com.yuri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

// REMOVA esta linha:
// import com.sun.tools.classfile.InnerClasses_attribute.Info;

// ADICIONE esta linha:
import io.swagger.v3.oas.models.info.Info;  // ✅ IMPORTAÇÃO CORRETA

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("API para controle de contas")
                        .description("Projeto desenvolvido com Spring Boot e Spring Data JPA")
                        .version("v1"));
    }
}