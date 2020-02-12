package HarjoitusTyo;

/**
 * @author m1kk0
 * @version 3.2.2020
 *
 */

import java.io.IOException;
import fi.jyu.mit.fxgui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

// Miten ladataan uusi ikkuna: https://www.youtube.com/watch?v=RJOza3XQk34

public class HarjoitusTyoGUIController {

    @FXML private BorderPane KirjautuminenPanel;
    
    @FXML private BorderPane UusiKayttajaPanel;
    
    @FXML private BorderPane KayttajatHakuPanel;
    
    @FXML private BorderPane KayttajaNakymaPanel;
    
    @FXML private BorderPane UusiTODOPanel;
    
    
    @FXML void fromKirjautuminenToUusiKayttja (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("UusiKayttajaGUIView.fxml"));
    	KirjautuminenPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromUusiKayttajaToKirjautuminen (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KirjautuminenGUIView.fxml"));
    	UusiKayttajaPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromKirjautuminenToKayttajatHaku (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KayttajatHakuGUIView.fxml"));
    	KirjautuminenPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromKayttajatHakuToKirjautuminen (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KirjautuminenGUIView.fxml"));
    	KayttajatHakuPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromKayttajatHakuToKayttajaNakyma (ActionEvent event) throws IOException{
		
		Dialogs.showMessageDialog("Ei osata viel‰ hakea k‰ytt‰j‰‰, mutta t‰ss‰ olisi sen n‰kym‰");
		
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KayttajaNakymaGUIView.fxml"));
    	KayttajatHakuPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromKayttajaNakymaToKayttajaHaku (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KayttajatHakuGUIView.fxml"));
    	KayttajaNakymaPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromUusiTODOToKayttajaNakyma (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KayttajaNakymaGUIView.fxml"));
    	UusiTODOPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromKayttajaNakymaToUusiTODO (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("UusiTODOGUIView.fxml"));
    	KayttajaNakymaPanel.getChildren().setAll(pane);
    }
	
	@FXML void fromKayttajaNakymaToKirjautuminen (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KirjautuminenGUIView.fxml"));
    	KayttajaNakymaPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromUusiKayttajaToKayttajaNakyma (ActionEvent event) throws IOException {
		
		Dialogs.showMessageDialog("Ei osata viel‰ luoda uutta k‰ytt‰j‰‰, mutta t‰ss‰ olisi sen n‰kym‰");
		
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KayttajaNakymaGUIView.fxml"));
    	UusiKayttajaPanel.getChildren().setAll(pane);
    }
	
	@FXML void luoTODO (ActionEvent event) throws IOException {
		Dialogs.showMessageDialog("Ei osata viel‰ luoda TODO:ta");
	}
	
	@FXML void MuokkaaTODO (ActionEvent event) throws IOException {
		Dialogs.showMessageDialog("Ei osata viel‰ muokata TODO:ta");
	}
}