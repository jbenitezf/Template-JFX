package negocio.impl;

import java.util.ArrayList;
import java.util.List;

import negocio.BibliotecaService;
import negocio.model.Libro;

public class BibliotecaImpl implements BibliotecaService {

	private static BibliotecaImpl program = null;
	private static List<Libro> catalogo;

	private BibliotecaImpl() {
		catalogo = new ArrayList<Libro>();

	}

	public static BibliotecaImpl getInstance() {
		if (program == null)
			program = new BibliotecaImpl();
		return program;

	}

	// Metodo que devuelve el catalogo para pasarlo al MainController
	public List<Libro> getCatalogo() {
		return catalogo;
	}

	public boolean nuevo(Libro libro) {

		return catalogo.add(libro);

	}

	public boolean editar(Libro libro) {

		boolean devolver = false;
		for (Libro l : catalogo) {
			if (l.getIsbn().equals(libro.getIsbn())) {
                int pos = catalogo.indexOf(l);
                catalogo.set(pos, libro);
                devolver = true;
                break;
            }
		}
		return devolver;

	}

	public boolean eliminar(Libro libro) {

		return catalogo.remove(libro);

	}

	@Override
	public boolean salvar(String fichero) {
		boolean retorno = false;

		XMLService.getXML(fichero);

		return retorno;
	}

	@Override
	public boolean cargar(String fichero) {
		boolean retorno = false;

		XMLService.cargarXML(fichero);

		return retorno;
	}

}
