package com.bibliotecario.bibliotecario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"persistencia.repositorio","com.bibliotecario.bibliotecario","dominio.reglas"})
@EntityScan("persistencia.entitad")

public class BibliotecarioApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotecarioApplication.class, args);
	}
}
