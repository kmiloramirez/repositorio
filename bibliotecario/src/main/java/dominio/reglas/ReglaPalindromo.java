package dominio.reglas;

import dominio.excepcion.PrestamoException;

public class ReglaPalindromo implements ReglasPrestamo {

	public static final String EL_LIBRO_ES_PALINDROMO = "los libros palindromos solo se pueden utilizar en la biblioteca";

	@Override
	public boolean validar(String isbn) {
		int tamanio = isbn.length();
		for (int i = 0; i < tamanio; ++i) {
			if (isbn.charAt(i) != isbn.charAt((tamanio - 1) - i)) {
				return false;
			}
		}
		throw new PrestamoException(EL_LIBRO_ES_PALINDROMO);
	}

}
