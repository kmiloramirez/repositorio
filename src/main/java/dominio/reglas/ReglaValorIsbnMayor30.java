package dominio.reglas;

import java.util.Calendar;

import dominio.Prestamo;

public class ReglaValorIsbnMayor30 implements ReglasPrestamo {

	@Override
	public boolean validar(String isbn) {
		char[] ch = isbn.toCharArray();
		int valorIsbn = 0;
		for (int i = 0; i < isbn.length(); ++i) {
			int numero = ch[i] - '0';
			if (0 <= numero && numero <= 9) {
				valorIsbn += ch[i] - '0';
			}
		}
		return valorIsbn > 30;
	}

	public void diasDePrestamo15(Prestamo prestamo) {
		quitarHora(prestamo.getFechaEntregaMaxima());
		int diasHabiles = 15;
		int diasContados = 1;
		while (diasContados < diasHabiles) {
			if (prestamo.getFechaEntregaMaxima().get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				diasContados++;
			}
			prestamo.getFechaEntregaMaxima().add(Calendar.DAY_OF_YEAR, 1);
		}
		if (prestamo.getFechaEntregaMaxima().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			prestamo.getFechaEntregaMaxima().add(Calendar.DAY_OF_YEAR, 1);
		}

	}

	private void quitarHora(Calendar calendario) {
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
	}
}
