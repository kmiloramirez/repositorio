package dominio;

import java.util.Calendar;

public class Prestamo {

	private Calendar fechaSolicitud;
	private Libro libro;
	private Calendar fechaEntregaMaxima;
	private String nombreUsuario;

	public Prestamo(Libro libro) {
		this.fechaSolicitud = Calendar.getInstance();
		this.libro = libro;
	}

	public Prestamo(Calendar fechaSolicitud, Libro libro, Calendar fechaEntregaMaxima, String nombreUsuario) {
		this.fechaSolicitud = fechaSolicitud;
		this.libro = libro;
		this.fechaEntregaMaxima = fechaEntregaMaxima;
		this.nombreUsuario = nombreUsuario;
	}

	public Calendar getFechaSolicitud() {
		return fechaSolicitud;
	}

	public Libro getLibro() {
		return libro;
	}

	public Calendar getFechaEntregaMaxima() {
		return fechaEntregaMaxima;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

}
