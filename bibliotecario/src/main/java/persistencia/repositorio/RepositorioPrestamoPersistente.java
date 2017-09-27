package persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dominio.Libro;
import dominio.Prestamo;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import persistencia.builder.LibroBuilder;
import persistencia.builder.PrestamoBuilder;
import persistencia.entitad.LibroEntity;
import persistencia.entitad.PrestamoEntity;
import persistencia.repositorio.jpa.RepositorioLibroJPA;

@Repository
public class RepositorioPrestamoPersistente implements RepositorioPrestamo {

	private static final String ISBN = "isbn";
	private static final String PRESTAMO_FIND_BY_ISBN = "Prestamo.findByIsbn";
	private static final String PRESTAMOS_FIND = "Prestamo.findAll";

	private EntityManager entityManager;

	private RepositorioLibroJPA repositorioLibroJPA;

	public RepositorioPrestamoPersistente(EntityManager entityManager, RepositorioLibro repositorioLibro) {

		this.entityManager = entityManager;
		this.repositorioLibroJPA = (RepositorioLibroJPA) repositorioLibro;
	}

	@Override
	public void agregar(Prestamo prestamo) {

		PrestamoEntity prestamoEntity = buildPrestamoEntity(prestamo);

		entityManager.persist(prestamoEntity);
	}

	@Override
	public Libro obtenerLibroPrestadoPorIsbn(String isbn) {

		PrestamoEntity prestamoEntity = obtenerPrestamoEntityPorIsbn(isbn);

		return LibroBuilder.convertirADominio(prestamoEntity != null ? prestamoEntity.getLibro() : null);
	}

	@SuppressWarnings("rawtypes")
	private PrestamoEntity obtenerPrestamoEntityPorIsbn(String isbn) {

		Query query = entityManager.createNamedQuery(PRESTAMO_FIND_BY_ISBN);
		query.setParameter(ISBN, isbn);

		List resultList = query.getResultList();

		return !resultList.isEmpty() ? (PrestamoEntity) resultList.get(0) : null;
	}

	private PrestamoEntity buildPrestamoEntity(Prestamo prestamo) {

		LibroEntity libroEntity = repositorioLibroJPA.obtenerLibroEntityPorIsbn(prestamo.getLibro().getIsbn());

		PrestamoEntity prestamoEntity = new PrestamoEntity();
		prestamoEntity.setLibro(libroEntity);
		prestamoEntity.setFechaSolicitud(prestamo.getFechaSolicitud());
		prestamoEntity.setNombreUsuario(prestamo.getNombreUsuario());
		prestamoEntity.setFechaEntregaMaxima(prestamo.getFechaEntregaMaxima());
		return prestamoEntity;
	}

	@Override
	public Prestamo obtener(String isbn) {

		PrestamoEntity prestamoEntity = obtenerPrestamoEntityPorIsbn(isbn);
		return new Prestamo(prestamoEntity.getFechaSolicitud(),
				LibroBuilder.convertirADominio(prestamoEntity.getLibro()), prestamoEntity.getFechaEntregaMaxima(),
				prestamoEntity.getNombreUsuario());
	}@Override
	public List<Prestamo> obtenerListaPrestamos() {

		List<PrestamoEntity> listaEntity = listarPrestamos();
		List<Prestamo> listaPrestamos = new ArrayList<>();

		for (int i = 0; i < listaEntity.size(); ++i) {
			Prestamo prestamo = PrestamoBuilder.convertirADominio(listaEntity.get(i));
			listaPrestamos.add(prestamo);
		}
		return listaPrestamos;
	}

	private List<PrestamoEntity> listarPrestamos() {
		Query query = entityManager.createNamedQuery(PRESTAMOS_FIND);
		List<PrestamoEntity> resultList = query.getResultList();

		return !resultList.isEmpty() ? resultList : null;
	}
}
