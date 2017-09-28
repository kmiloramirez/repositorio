package dominio;

import java.util.List;

import dominio.reglas.ReglasPrestamo;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;

public class BibliotecarioUco extends Bibliotecario {

	public BibliotecarioUco(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo,
			List<ReglasPrestamo> reglas) {
		super(repositorioLibro, repositorioPrestamo, reglas);
	}

	@Override
	public Prestamo prestar(String isbn, String usuarioUco) {
		System.out.println("Hola vamos a prestar");
		return super.prestar(isbn, usuarioUco);
	}

}
