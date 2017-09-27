package persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import persistencia.builder.LibroBuilder;
import persistencia.entitad.LibroEntity;
import persistencia.repositorio.jpa.RepositorioLibroJPA;

@Repository
public class RepositorioLibroPersistente implements RepositorioLibro, RepositorioLibroJPA {

	private static final String ISBN = "isbn";
	private static final String LIBRO_FIND_BY_ISBN = "Libro.findByIsbn";
	private static final String LIBROS_FIND = "Libro.findAll";
	private EntityManager entityManager;

	public RepositorioLibroPersistente(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@Override
	public Libro obtenerPorIsbn(String isbn) {

		LibroEntity libroEntity = obtenerLibroEntityPorIsbn(isbn);

		return LibroBuilder.convertirADominio(libroEntity);
	}

	@Override
	public void agregar(Libro libro) {

		entityManager.persist(LibroBuilder.convertirAEntity(libro));
	}

	@Override
	public LibroEntity obtenerLibroEntityPorIsbn(String isbn) {

		Query query = entityManager.createNamedQuery(LIBRO_FIND_BY_ISBN);
		query.setParameter(ISBN, isbn);

		return (LibroEntity) query.getSingleResult();
	}

	public List<LibroEntity> listarLibros() {
		Query query = entityManager.createNamedQuery(LIBROS_FIND);
		List<LibroEntity> resultList = query.getResultList();
		return !resultList.isEmpty() ? resultList : null;
	}

	@Override
	public List<Libro> obtenerListaLibros() {
		List<LibroEntity> listaEntity = listarLibros();
		List<Libro> listaLibros = new ArrayList<>();
		for (int i = 0; i < listaEntity.size(); ++i) {
			Libro libro = LibroBuilder.convertirADominio(listaEntity.get(i));
			listaLibros.add(libro);
		}
		return listaLibros;
	}

}
