
package persistencia.builder;

import dominio.Libro;
import dominio.Prestamo;
import persistencia.entitad.LibroEntity;
import persistencia.entitad.PrestamoEntity;

public class PrestamoBuilder {

	public PrestamoBuilder() {
	}

	public static Prestamo convertirADominio(PrestamoEntity prestamoEntity) {

		Prestamo prestamo = null;

		if (prestamoEntity != null) {

			Libro libro = LibroBuilder.convertirADominio(prestamoEntity.getLibro());
			prestamo = new Prestamo(prestamoEntity.getFechaSolicitud(), libro, prestamoEntity.getFechaEntregaMaxima(),
					prestamoEntity.getNombreUsuario());

		}

		return prestamo;
	}

	public static PrestamoEntity convertirAEntity(Prestamo prestamo) {

		LibroEntity libro = LibroBuilder.convertirAEntity(prestamo.getLibro());
		PrestamoEntity prestamoEntity = new PrestamoEntity();
		prestamoEntity.setFechaSolicitud(prestamo.getFechaSolicitud());
		prestamoEntity.setLibro(libro);
		prestamoEntity.setFechaEntregaMaxima(prestamo.getFechaEntregaMaxima());
		prestamoEntity.setNombreUsuario(prestamo.getNombreUsuario());
		return prestamoEntity;

	}

}