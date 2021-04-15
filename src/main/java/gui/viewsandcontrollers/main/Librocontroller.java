package gui.viewsandcontrollers.main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Librocontroller {

public void guardar(ActionEvent event) throws IOException {

        
	Stage stage = new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("BasicFxml.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
   }
}
