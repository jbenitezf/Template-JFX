package gui.viewsandcontrollers.main;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import gui.Notifications;
import gui.viewsandcontrollers.form.Librocontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocio.BibliotecaService;
import negocio.impl.BibliotecaImpl;
import negocio.model.Genero;
import negocio.model.Libro;

	

public class MainStageController implements Initializable {

	@FXML
	public void initialize() {
    
	}
	
	@FXML
	private TableView<Libro> tableView;

	@FXML
	private TableColumn<Libro, String> Título;

	@FXML
	private TableColumn<Libro, String> ISBN;

	@FXML
	private TableColumn<Libro, Genero> Género;

	@FXML
	private TableColumn<Libro, String> Autor;

	@FXML
	private TableColumn<Libro, Integer> Páginas;

	@FXML
	private Button nuevo;

	@FXML
	private Button editar;

	@FXML
	private Button borrar;

	@FXML
	private Button guardar;

	@FXML
	private Button cargar;

	private static BibliotecaService programa = BibliotecaImpl.getInstance();

	private static ObservableList<Libro> lista = FXCollections.observableArrayList(programa.getCatalogo());
	
			
	public void initialize(URL location, ResourceBundle resources) {

		Título.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
		ISBN.setCellValueFactory(new PropertyValueFactory<Libro, String>("isbn"));
		Género.setCellValueFactory(new PropertyValueFactory<Libro, Genero>("genero"));
		Autor.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
		Páginas.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("paginas"));

		editar.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
		borrar.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());

		Notifications.subscribe(Notifications.CATALOGO_UPDATED, this, this::update);

		tableView.setItems(lista);
	}
	
	@FXML
	private void buttonNuevo(ActionEvent event) throws IOException {

		nuevo(event, null);

	}

   public void nuevo(ActionEvent event, Libro libro) throws IOException {

        
        Stage stage = new Stage();
        FXMLLoader root = new FXMLLoader(getClass().getResource("../form/libroform.fxml"));
        
        Librocontroller librocontroler;

		if (libro != null) {
			librocontroler = new Librocontroller(libro);
		} else {
			librocontroler = new Librocontroller();
		}

		root.setController(librocontroler);
		Parent root1 = root.load();
		stage.setScene(new Scene(root1));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(((Node) event.getSource()).getScene().getWindow());
		stage.showAndWait();
   }
    
    @FXML
	private void Editar(ActionEvent event) throws IOException {
    	if(tableView.getSelectionModel().selectedIndexProperty().get()!=-1)
    			nuevo(event, tableView.getSelectionModel().getSelectedItem());

	}

	@FXML
	private void Borrar(ActionEvent event) throws IOException {

		Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
		warning.setTitle("Borrar los datos de forma permanente");
		warning.setContentText("¿Quiere eliminar los datos?");

		Optional<ButtonType> botonRes = warning.showAndWait();

		if (botonRes.get() == ButtonType.OK) {
			programa.eliminar(tableView.getSelectionModel().getSelectedItem());
			Notifications.publish(Notifications.CATALOGO_UPDATED);
		}

	}

	@FXML
	private void Guardar(ActionEvent event) throws IOException {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Fichero XML");
		dialog.setContentText("Escribe el nombre del archivo:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			programa.salvar(result.get());
		}

	}

	@FXML
	private void Cargar(ActionEvent event) throws IOException {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Fichero XML");
		dialog.setContentText("Escribe el nombre del archivo:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			programa.cargar(result.get());

			Notifications.publish(Notifications.CATALOGO_UPDATED);
		}

	}

	private void update(String event) {
		lista = FXCollections.observableArrayList(programa.getCatalogo());
		tableView.setItems(lista);
		tableView.refresh();
	}

	public static ObservableList<Libro> getCatalogo() {
		return lista;
	}
   
}