package negocio;

import java.util.List;

import negocio.model.Libro;

public interface BibliotecaService {

	boolean nuevo(Libro libro);

	boolean editar(Libro libro);

	boolean eliminar(Libro libro);

	boolean salvar(String fichero);

	boolean cargar(String fichero);

	List<Libro> getCatalogo();
}

