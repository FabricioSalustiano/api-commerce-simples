package com.xpeducacao.desafio_final_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Desafio Final XP Educação",
                version = "1",
                description = "API desenvolvida para projeto final dos módulos de arquitetura de software da pós-graduação da XP Educação"
        )
)
public class DesafioFinalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioFinalAppApplication.class, args);
    }
}
