package gui.viewsandcontrollers.form;

import java.util.ArrayList;
import java.util.List;

import gui.Notifications;
import gui.viewsandcontrollers.form.viewmodel.LibroConverter;
import gui.viewsandcontrollers.form.viewmodel.LibroViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import negocio.model.Genero;
import negocio.model.Libro;

public class Librocontroller {

	private LibroViewModel viewModel = new LibroViewModel();

	@FXML
	private TextField titulo;

	@FXML
	private TextField isbn;
	
	@FXML
	private ChoiceBox<Pair<String, String>> genero = new ChoiceBox<>();

	@FXML
	private TextField autor;

	@FXML
	private TextField paginas;

	@FXML
	private Button guardar;

	@FXML
	private Button cancelar;

	boolean action;

	public Librocontroller(Libro libro) {
		action = false;
		viewModel = LibroConverter.toLibroVM(libro);
	}

	public Librocontroller() {
		action = true;
	}

	public void initialize() {
		mostrar();
		binViewModel();

	}

	private void mostrar() {

		List<Pair<String, String>> opciones = new ArrayList<>();
		opciones.add(new Pair<String, String>(Genero.FICCION.toString(), Genero.FICCION.toString()));
		opciones.add(new Pair<String, String>(Genero.NOVELA.toString(), Genero.NOVELA.toString()));
		opciones.add(new Pair<String, String>(Genero.POESIA.toString(), Genero.POESIA.toString()));

		genero.setConverter(new StringConverter<Pair<String, String>>() {

			@Override
			public String toString(Pair<String, String> pair) {
				return pair.getKey();
			}

			@Override
			public Pair<String, String> fromString(String string) {
				return null;
			}
		});

		genero.getItems().addAll(opciones);

	}

	private void binViewModel() {
		titulo.textProperty().bindBidirectional(viewModel.tituloProperty());
		isbn.textProperty().bindBidirectional(viewModel.isbnProperty());
		autor.textProperty().bindBidirectional(viewModel.autorProperty());
		genero.valueProperty().bindBidirectional(viewModel.generoProperty());
		Bindings.bindBidirectional(paginas.textProperty(), viewModel.paginasProperty(), new NumberStringConverter());

	}

	@FXML
	private void guardar(ActionEvent event) throws Exception {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();

		boolean exito = false;
		if (action) {
			exito = viewModel.create();
		} else {
			exito = viewModel.update();

		}
		stage.close();
		if (exito) {
			((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
			Notifications.publish(Notifications.CATALOGO_UPDATED);
		}

	}

//	@FXML
//	private void cancelar(ActionEvent event) throws Exception {
//
//		Node source = (Node) event.getSource();
//		Stage stage = (Stage) source.getScene().getWindow();
//		stage.close();
//	}

}
