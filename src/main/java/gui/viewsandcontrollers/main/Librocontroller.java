package gui.viewsandcontrollers.main;

import java.io.IOException;

import javafx.scene.Node;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

	public class Librocontroller {
		public ChoiceBox<String> choiceBox;
		
		public void initialize() {
			mostrar();
		}
		@FXML
		public void guardar(ActionEvent event){
			Node source = (Node) event.getSource();
	        Stage stage = (Stage) source.getScene().getWindow();
	        stage.close();
		}
		public void mostrar() { 
			choiceBox.setItems(FXCollections.observableArrayList("NOVELA", "20-30", "30-40"));
			
			
		}

	
	
}
