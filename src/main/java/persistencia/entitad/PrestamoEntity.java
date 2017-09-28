package persistencia.entitad;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "Prestamo")
@NamedQueries({
		@NamedQuery(name = "Prestamo.findByIsbn", query = "SELECT prestamo from Prestamo prestamo where prestamo.libro.isbn = :isbn"),
		@NamedQuery(name = "Prestamo.findAll", query = "SELECT prestamo from Prestamo prestamo") })

public class PrestamoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_LIBRO", referencedColumnName = "id")
	private LibroEntity libro;
	@Column(name = "fechaSolicitud")
	private Calendar fechaSolicitud;
	@Column(name = "fechaEntregaMaxima")
	private Calendar fechaEntregaMaxima;
	@Column(name = "nombreUsuario")
	private String nombreUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFechaEntregaMaxima(Calendar calendar) {
		this.fechaEntregaMaxima = calendar;
	}

	public LibroEntity getLibro() {
		return libro;
	}

	public void setLibro(LibroEntity libroEntity) {
		this.libro = libroEntity;
	}

	public Calendar getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Calendar calendar) {
		this.fechaSolicitud = calendar;
	}

	public Calendar getFechaEntregaMaxima() {
		return fechaEntregaMaxima;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
