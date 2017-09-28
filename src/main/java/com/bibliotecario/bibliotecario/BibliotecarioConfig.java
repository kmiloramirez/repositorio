package com.bibliotecario.bibliotecario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dominio.Bibliotecario;
import dominio.BibliotecarioUco;
import dominio.reglas.ReglaDiaDomingo;
import dominio.reglas.ReglaPalindromo;
import dominio.reglas.ReglasPrestamo;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;

@Configuration
public class BibliotecarioConfig {

	@Bean
	public Bibliotecario createBibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo,
			List<ReglasPrestamo> reglas) {
		
		return new BibliotecarioUco(repositorioLibro, repositorioPrestamo, crearRegla());
	}
	
	@Bean
	public List<ReglasPrestamo> crearRegla(){
		List<ReglasPrestamo> reglas =new ArrayList<>();
		reglas.add(new ReglaPalindromo());
		reglas.add(new ReglaDiaDomingo());
		return reglas;
		
	}
	
	@Bean
	public ReglasPrestamo crearReglas(){
		List<ReglasPrestamo> reglas =new ArrayList<>();
		reglas.add(new ReglaPalindromo());
		reglas.add(new ReglaDiaDomingo());
		return null;
		
	}
	

}
