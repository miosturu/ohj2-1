package HarjoitusTyo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;

/**
 * @author Mikko Turunen
 * @version 17.4.2020
 *
 */
public class KayttajaDialogController implements ModalControllerInterface<Kayttaja>,Initializable {
	@FXML private BorderPane UusiKayttajaPanel;
    @FXML private TextField nimiText;
    @FXML private TextField puhNumText;
    @FXML private TextField osoiteText;
    @FXML private TextField sPostiText;
    @FXML private Label labelVirhe;
    @FXML private Button luomisNappi;
    
    @FXML private void handleOK() {
    	if (!tarkistaTiedot()) {
    		return;
    	} else {
    		ModalController.closeStage(UusiKayttajaPanel);
    	}
    }


    @FXML private void handleCancel() {
    	tamanHetkinenKayttaja = null;
    	ModalController.closeStage(UusiKayttajaPanel);
    }
    
    
    @FXML private void handleTarkista() {
    	//tarkistaTiedot();
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Ei käytössä	
	}

	
	@Override
	public Kayttaja getResult() {
		try {
			tamanHetkinenKayttaja.muokkaaKaikki(nimiText.getText(), puhNumText.getText(), osoiteText.getText(), sPostiText.getText());
			return tamanHetkinenKayttaja;
		} catch (Exception e) {
			return null;
		}
	}

	
	@Override
	public void handleShown() {
		// Ei käytössä		
	}

	
	@Override
	public void setDefault(Kayttaja oletus) {
		tamanHetkinenKayttaja = oletus;
		naytaKayttaja(tamanHetkinenKayttaja);
	}
	
    
    /* Ei käyttöliittymään liittyvät metodit*/
    
	private Kayttaja tamanHetkinenKayttaja;
	
	/**
	 * Luo uuden kayttaja:n dialogissa
	 * @param ModalityStage Mille ollaan tekemässä
	 * @param oletus Oletus kayttaja
	 * @return Uusi kayttaja annettujen tietojen mukaan
	 */
    public static Kayttaja luoUusiKayttaja(Stage ModalityStage, Kayttaja oletus) {
    	Kayttaja kayttaja = ModalController.<Kayttaja, KayttajaDialogController>showModal(KayttajaDialogController.class.getResource("UusiKayttajaGUIView.fxml"), 
    																														"Käyttäjä", 
    																														 ModalityStage, 
    																														 oletus, 
    																														 ctrl -> ctrl.setFocus());
    	
    	return kayttaja;
    	   	
    }
    
    
    /**
     * Näyttää kayttajan tiedot dialogiin
     * @param kayttaja Kayttaja, jonka tiedot halutaan nayttaa
     */
    public void naytaKayttaja(Kayttaja kayttaja) {
    	if (kayttaja == null) return;
    	nimiText.setText(kayttaja.getNimi());
    	puhNumText.setText(kayttaja.getPuhNum());
    	osoiteText.setText(kayttaja.getOsoite());
    	sPostiText.setText(kayttaja.getSPosti());
    }
    
    
    private void setFocus() {
    	nimiText.requestFocus();
    }
    
    
    public boolean tarkistaTiedot() {
    	String virhe = "";
    	//System.out.println("Nimi: " + nimiText.getText() + "\nPu#: " + puhNumText.getText() + "\nOsoite: " + osoiteText.getText() + "\nSposti: " + sPostiText.getText());
    	if (!tarkistaRegex("[^0-9]+", nimiText.getText())) virhe += "Nimi ei saa olla tyhjä\n";
    	
    	if (puhNumText.getText() == "") virhe += "Puhelinnumero ei saa olla tyhjä";
    	if (puhNumText.getText() != "" && !tarkistaRegex("[0-9]+", puhNumText.getText())) virhe += "Puhelinnumero ei saa sisältää kirjaimia\n";
    	
    	
    	if (!tarkistaRegex(".+", osoiteText.getText())) virhe += "Osoite ei saa olla tyhjä\n";
    	
    	if (sPostiText.getText() == "") virhe += "Sähköposti ei saa olla tyhjä\n";
    	if (sPostiText.getText() != "" && !tarkistaRegex(".*@.*\\..*", sPostiText.getText())) virhe += "Sähköposti pitää olla oikeassa muodossa (esim. nimi@domain.fi)";
    	
    	System.out.println(virhe);
    	
    	if (virhe != "") {
    		Dialogs.showMessageDialog("Ongelma: " + virhe);
    		return false;
    	} else {
    		return true;	
    	}
    }
    
    
    /**
     * Tarkistaa, onko merkkijono oikeassa muodossa regex:än avulla
     * @param regex Käytettävä regex
     * @param tarkisttettava Tarkistettava merkkijono
     * @return onko oikeassa muodossa vai ei
     */
    public static boolean tarkistaRegex(String regex, String tarkistettava) {
		return tarkistettava.matches(regex);
	}
}
