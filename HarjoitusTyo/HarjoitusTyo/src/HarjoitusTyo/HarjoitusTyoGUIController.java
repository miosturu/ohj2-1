package HarjoitusTyo;

/**
 * @author m1kk0
 * @version 3.2.2020
 *
 */

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import fi.jyu.mit.fxgui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

// Miten ladataan uusi ikkuna: https://www.youtube.com/watch?v=RJOza3XQk34

public class HarjoitusTyoGUIController {

    @FXML private BorderPane KirjautuminenPanel;    
    @FXML private BorderPane UusiKayttajaPanel;    
    @FXML private BorderPane KayttajatHakuPanel;   
    @FXML private BorderPane KayttajaNakymaPanel; 
    @FXML private BorderPane UusiTODOPanel;
    
    @FXML private ListView<String> kayttajaLista;
    
    @FXML private TextArea tulostusAlue = new TextArea();
    

    /*Paneelien v‰linen navigointi alkaa*/
    
    
    @FXML void fromKirjautuminenToUusiKayttja (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("UusiKayttajaGUIView.fxml"));
    	KirjautuminenPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromUusiKayttajaToKirjautuminen (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KirjautuminenGUIView.fxml"));
    	UusiKayttajaPanel.getChildren().setAll(pane);
    }
	
	
	@FXML void fromKirjautuminenToKayttajatHaku (ActionEvent event) throws IOException{
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KayttajatHakuGUIView2.fxml")); // Vie v‰liaikaiseen ikkunaan 
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
    	BorderPane pane = FXMLLoader.load(getClass().getResource("KayttajatHakuGUIView2.fxml")); // Vie v‰liaikaiseen ikkunaan 
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
	
	
	/*Paneelien v‰linen navigointi loppuu*/
	
	
	@FXML void luoTODO (ActionEvent event) throws IOException {
		Dialogs.showMessageDialog("Ei osata viel‰ luoda TODO:ta");
	}
	
	
	@FXML void MuokkaaTODO (ActionEvent event) throws IOException {
		Dialogs.showMessageDialog("Ei osata viel‰ muokata TODO:ta");
	}

	
	@FXML void asetaTekstit(ActionEvent event) {
		tulostusAlue.setText(ohjelma.annaKayttaja(ind).toString());
	}
	
	
	/**
	 * Lis‰‰ valmiin Kayttajan valittavalle listalle.
	 */
	@FXML void lisaaKayttaja(ActionEvent event) {
		uusiKayttaja();
	}
	
	
	/**
	 * K‰sittelee Kayttajan valinnan liittym‰ss‰
	 */	
	@FXML void handleValinta(MouseEvent event) throws InvocationTargetException {
		valitseKayttaja();
	}
	
	
	
	@FXML void lisaaTODO(ActionEvent event) {
		uusiTODO();
	}
	

	/*Ei k‰yttˆliittym‰‰n liittyv‰t funktiot ja metodit*/
	
	private TODOohjelma ohjelma = new TODOohjelma();
	private int ind = 0;
	private int TODOind = 0;
	private int tamanHetkinen = 0;
		
	
	private void uusiKayttaja() {
		Kayttaja uusi = new Kayttaja(ind);
		uusi.luoValmis(ind);
		try {
			ohjelma.lisaaKayttaja(uusi);
			kayttajaLista.getItems().add(uusi.getNimi());
			tulostusAlue.setText(ohjelma.annaKayttaja(ind).toString());
			ind++;
		} catch (Exception e) {
			Dialogs.showMessageDialog("Ongelma k‰ytt‰j‰n luomisessa");
			return;
		}
	}
	
	
	private void uusiTODO() {
		TODO uusi = new TODO(TODOind);
		uusi.luoValmis(TODOind);
		try {
			ohjelma.lisaaTODO(ohjelma.annaKayttaja(tamanHetkinen), uusi);
			TODOind++;
		} catch (Exception e) {
			Dialogs.showMessageDialog("Ongelma TODO:n lis‰‰misess‰");
			return;
		}
	}
	
	
	void valitseKayttaja() {
		tamanHetkinen = kayttajaLista.getSelectionModel().getSelectedIndex();
		
		String tulostettava = ohjelma.annaKayttaja(tamanHetkinen).toString();
		
		try {
			tulostettava += ohjelma.kayttajanTODOt(tamanHetkinen);
		} catch (Exception e) {
			
		}

		
		tulostusAlue.setText(tulostettava);
	}

}