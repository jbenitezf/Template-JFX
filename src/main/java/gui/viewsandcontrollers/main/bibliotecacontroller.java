package gui.viewsandcontrollers.main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

	

public class bibliotecacontroller {

	@FXML
	public void initialize() {
    
	}
//	private TextField titulo;
//	
//	private TextField ISBN;
//	
//	private TextField Autor;
//	
//	private TextField Género;
//	
//	private TextField Páginas;
//    @FXML
   public void nuevo(ActionEvent event) throws IOException {

        
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("libroform.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
   }
   
}