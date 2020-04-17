package HarjoitusTyo;

/**
 * @author Mikko Turunen
 * @version 17.4.2020
 *
 */

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class TODODialogController implements ModalControllerInterface<TODO>,Initializable{
    @FXML private BorderPane UusiTODOPanel;
    @FXML private TextField otsikkoText;
    @FXML private TextField paikkaText;
    @FXML private TextField aikaText;
    @FXML private TextArea lisaTietoaText;
    
    @FXML private void handleOK() {
    	if (!tarkistaTiedot()) {
    		return;
    	} else {
    		ModalController.closeStage(UusiTODOPanel);
    	}
    }


	@FXML private void handleCancel() {
    	tamanHetkinenTODO = null;
    	ModalController.closeStage(UusiTODOPanel);
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Ei käytössä
	}
	
	
	@Override
	public TODO getResult() {
		try {
			tamanHetkinenTODO.muokkaaKaikki(otsikkoText.getText(), paikkaText.getText(), parseAika(aikaText.getText()), lisaTietoaText.getText());
			return tamanHetkinenTODO;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@Override
	public void handleShown() {
		// Ei käytössä
		
	}
	
	
	@Override
	public void setDefault(TODO oletus) {
		tamanHetkinenTODO = oletus;
		naytaTODO(tamanHetkinenTODO);
	}
	
	
	/* Ei käyttöliittymään liittyvät metodit*/
	
	private TODO tamanHetkinenTODO;
	
	public static TODO luoUusiTODO(Stage ModalityStage, TODO oletus) {
		TODO todo = ModalController.<TODO, TODODialogController>showModal(TODODialogController.class.getResource("UusiTODOGUIView.fxml"), 
																															 "TODO", 
																															  ModalityStage, 
																															  oletus, 
																															  ctrl -> ctrl.setFocus());
		return todo;
	}
	
	
	private void setFocus() {
		otsikkoText.requestFocus();
	}
	
	
	public void naytaTODO(TODO todo) {
		if (todo == null) return;
		otsikkoText.setText(todo.getOtsikko());
	    paikkaText.setText(todo.getPaikka());
	    aikaText.setText(todo.getAika().toString());
	    lisaTietoaText.setText(todo.getLisaTietoa());
	}
	

    private boolean tarkistaTiedot() {
		String virhe = "";
		
		if (!tarkistaRegex(".*", otsikkoText.getText())) virhe += "Otsikko ei saa olla tyhjä\n";
		
		if (!tarkistaRegex(".*", paikkaText.getText())) virhe += "Paikka ei saa olla tyhjä\n";
		
		if (aikaText.getText() == "") virhe += "Aika ei saa olla tyhjä\n";
		if (aikaText.getText() != "" && !tarkistaRegex("[0-2][0-9]:[0-5][0-9]:[0-5][0-9]", aikaText.getText())) virhe += "Ajan pitää olla muodossa hh:mm:ss";
		
		
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
    
	
	/**
	 * Jäsentää merkkijonosta ajan ja luo uuden aika olion
	 * @param jono Jono, josta aika jäsennetään
	 * @return Aika-olio
	 */
	@SuppressWarnings("deprecation")
	public Time parseAika(String jono) {
		String[] osat = jono.split(":");
		int tunnit = Integer.parseInt(osat[0]);
		int minuutit = Integer.parseInt(osat[1]);
		int sekunnit = Integer.parseInt(osat[2]);
		Time aika = new Time(tunnit, minuutit, sekunnit);
		return aika;
	}
}
