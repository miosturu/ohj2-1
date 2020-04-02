package HarjoitusTyo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author m1kk0
 * @version 3.2.2020
 *
 */
public class HarjoitusTyoMain extends Application {
	@Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("KirjautuminenGUIView.fxml"));
            
            final Pane root = ldr.load();
            //final HarjoitusTyoGUIController harjoitustyoCtrl = (HarjoitusTyoGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("harjoitustyo.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("TODO");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}