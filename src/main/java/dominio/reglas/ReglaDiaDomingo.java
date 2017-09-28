package dominio.reglas;

import java.util.Calendar;

import dominio.excepcion.PrestamoException;

public class ReglaDiaDomingo implements ReglasPrestamo {

	@Override
	public boolean validar(String isbn) {
		if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			throw new PrestamoException("los domingos no abre la biblioteca ");
		return false;
	}

}
