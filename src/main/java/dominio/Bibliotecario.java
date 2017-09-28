package dominio;

import java.util.Calendar;
import java.util.List;
import dominio.excepcion.PrestamoException;
import dominio.reglas.ReglaValorIsbnMayor30;
import dominio.reglas.ReglasPrestamo;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;

public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;
	private List<ReglasPrestamo> reglas;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo,
			List<ReglasPrestamo> reglas) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;
		this.reglas = reglas;

	}

	public Prestamo prestar(String isbn, String nombreUsuario) {

		Libro libro = repositorioLibro.obtenerPorIsbn(isbn);
		if (libro != null && !esPrestado(isbn)) {
			for (ReglasPrestamo regla : reglas) {
				regla.validar(isbn);
			}
			ReglaValorIsbnMayor30 esMAyor30 = new ReglaValorIsbnMayor30();
			if (esMAyor30.validar(isbn)) {
				return crearprestamo(nombreUsuario, libro, esMAyor30, Calendar.getInstance());
			} else {
				return crearprestamo(nombreUsuario, libro, esMAyor30, null);
			}
		} else
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);

	}

	private Prestamo crearprestamo(String nombreUsuario, Libro libro, ReglaValorIsbnMayor30 esMAyor30,
			Calendar fechaMaximaEntrega) {
		Prestamo prestamo = new Prestamo(Calendar.getInstance(), libro, fechaMaximaEntrega, nombreUsuario);
		if (fechaMaximaEntrega != null) {
			esMAyor30.diasDePrestamo15(prestamo);
		}
		repositorioPrestamo.agregar(prestamo);
		return prestamo;
	}

	public boolean esPrestado(String isbn) {
		return repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn) != null;
	}

}
