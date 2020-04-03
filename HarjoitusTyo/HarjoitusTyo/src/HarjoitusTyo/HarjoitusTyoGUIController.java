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
import fi.jyu.mit.fxgui.ModalController;

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
    
    /*
    
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
    	lueTiedosto();
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
	
	*/
	
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
	@FXML void lisaaKayttaja(ActionEvent event) {ModalController.showModal(HarjoitusTyoGUIController.class.getResource("UusiKayttajaGUIView.fxml"), "Uusi Kayttaja", null, "");
		uusiKayttaja();
	}
	
	
	/**
	 * K‰sittelee Kayttajan valinnan liittym‰ss‰
	 */	
	@FXML void handleValinta(MouseEvent event) throws InvocationTargetException {
		valitseKayttaja();
	}
	
	
	/**
	 * K‰sittelee todo:n lis‰‰misen, kun painetaan nappia
	 */
	@FXML void lisaaTODO(ActionEvent event) {
		uusiTODO();
	}
	
	
	/**
	 * K‰sittelee erillisen tallenatimisen 
	 */
	@FXML void handleTallenna(ActionEvent event) {
		//ohjelma.tallenna();
    	//tulostaKayttajat();
    	lueTiedosto();
	}
	

	/*Ei k‰yttˆliittym‰‰n liittyv‰t funktiot ja metodit*/
	
	private TODOohjelma ohjelma = new TODOohjelma();
	private int ind = ohjelma.viimeisinEiKaytettyIndeksi("kayttajat.dat");
	private int TODOind = ohjelma.viimeisinEiKaytettyIndeksi("todo.dat");
	private int tamanHetkinen = ind;
		
	
	private void uusiKayttaja() {
		if (ind != -1) {
			Kayttaja uusi = new Kayttaja(ind);
			uusi.luoValmis(ind);
			try {
				ohjelma.lisaaKayttaja(uusi);
				kayttajaLista.getItems().add(uusi.getNimi());
				tulostusAlue.setText(ohjelma.annaKayttaja(ind).toString());
				ind++;
				ohjelma.tallenna();
			} catch (Exception e) {
				Dialogs.showMessageDialog("Ongelma k‰ytt‰j‰n luomisessa");
				return;
			}
		} else {
			Dialogs.showMessageDialog("Ongelma k‰ytt‰j‰n luomisessa");
			return;
		}
	}
	
	/**
	 * Uuden todo:n luomismetodi. 
	 */
	private void uusiTODO() { // TODO BUGI: todo:ita lis‰tess‰ ei aseteta k‰ytt‰j‰‰, jos k‰ytt‰j‰ on luotu sill‰v‰lin
		TODO uusi = new TODO(TODOind);
		uusi.luoValmis(TODOind);
		try {
			ohjelma.lisaaTODO(ohjelma.annaKayttaja(tamanHetkinen), uusi);
			TODOind++;
			valitseKayttaja();
			ohjelma.tallenna();
		} catch (Exception e) {
			Dialogs.showMessageDialog("Ongelma TODO:n lis‰‰misess‰");
			return;
		}
	}
	
	
	/**
	 * Kayttajan valinnan metodi.
	 */
	private void valitseKayttaja() {
		tamanHetkinen = kayttajaLista.getSelectionModel().getSelectedIndex();
		
		String tulostettava = ohjelma.annaKayttaja(tamanHetkinen).toString();
		
		try {
			tulostettava += "\n" + ohjelma.kayttajanTODOt(tamanHetkinen);
		} catch (Exception e) {}
		
		tulostusAlue.setText(tulostettava);
	}

	
	/**
	 * Tallentaa tiedot
	 */
	public void tallenna() {
		try {
			ohjelma.tallenna();
		} catch (Exception e) {
			System.out.println("Ongelma: " + e);
		}
	}
	
	/**
	 * Yritt‰‰ lukea tiedostosta.
	 */
	public void lueTiedosto() {
		try {
			ohjelma.lueTiedosto();
			for (int i = 0; i < ohjelma.annaMaxKayttajaMaara(); i++) {
				try {
					Kayttaja kayttaja = ohjelma.annaKayttaja(i);
					if (kayttaja != null) {
						String nimi = kayttaja.getNimi();
						kayttajaLista.getItems().add(nimi);
					}
				} catch (Exception e) {
					e.printStackTrace();
					e.getCause();
					System.out.println("Ongelma HarjoitusTyoGUIController.java : " + e);
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
			System.out.println("Ongelma HarjoitusTyoGUIController.java : " + e);
		}
	}
	

	
	/**
	 * Tulostaa kayttaja-listan sis‰llˆn
	 */
	public void tulostaKayttajat() {
		//kayttajaLista = new ListView<String>();
		for (int i = 0; i < ohjelma.annaMaxKayttajaMaara(); i++) {
			if (ohjelma.annaKayttaja(i) != null) {
				kayttajaLista.getItems().addAll(ohjelma.annaKayttaja(i).toString());
			}
		}	
	}
}