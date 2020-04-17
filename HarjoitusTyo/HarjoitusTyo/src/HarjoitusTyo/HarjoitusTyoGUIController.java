package HarjoitusTyo;

/**
 * @author Mikko Turunen
 * @version 17.4.2020
 *
 */

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import fi.jyu.mit.fxgui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class HarjoitusTyoGUIController {

    @FXML private BorderPane KirjautuminenPanel;    
    @FXML private BorderPane UusiKayttajaPanel;    
    @FXML private BorderPane KayttajatHakuPanel;   
    @FXML private BorderPane KayttajaNakymaPanel; 
    @FXML private BorderPane UusiTODOPanel;
    
    @FXML private TextField hakuKentta;
    
    @FXML private Button muokkaaKayttajaButton;
    @FXML private Button poistaKayttajaButton;
    @FXML private Button lisaaTODOButton;
    @FXML private Button muokkaaTODONappi;
    @FXML private Button poistaTODOButton;
    
    @FXML private TextField nimiField;
    @FXML private TextField puhNumField;
    @FXML private TextField osoiteField;
    @FXML private TextField sPostiField;
         
    @FXML private ListView<String> kayttajaLista;
    @FXML private ListView<String> TODOLista;
    
    @FXML private TextArea tulostusAlue = new TextArea();
    	
	
	/**
	 * Lis�� valmiin Kayttajan valittavalle listalle.
	 */
	@FXML void lisaaKayttaja(ActionEvent event) {
		uusiKayttaja();
	}
	
	
	/**
	 * K�sittelee Kayttajan valinnan liittym�ss�
	 */	
	@FXML void handleValinta(MouseEvent event) throws InvocationTargetException {
		valitseKayttaja();
	}
	
	
	/**
	 * K�sittelee todo:n valinnan k�ytt�liittym�ss�
	 */
	@FXML void handleValintaTODO(MouseEvent event) throws InvocationTargetException {
		valitseTODO();
	}
	
	/**
	 * K�sittelee todo:n lis��misen, kun painetaan nappia
	 */
	@FXML void lisaaTODO(ActionEvent event) {	
		uusiTODO();
	}
	

	/**
	 * K�sittelee tiettyjen k�ytt�jien hakemisen
	 */
	@FXML void handleHakeminen(ActionEvent event) {
		String hakuEhto = hakuKentta.getText();
		haeKayttajat(hakuEhto);
	}
	
	
	/**
	 * K�sittelee kayttaja:n poistamisen. Ei viel� kysele, onko varma.
	 */
	@FXML void handlePoistaKayttaja(ActionEvent event) {
		poistaKayttaja();
		tulostaKayttajat();
		tallenna();
	}
	
	
	/**
	 * K�sittelee k�ytt�j�n muokkaamisen k�ytt�liittym�ss�
	 */
	@FXML void handleMuutaKayttajaa() {
		muokkaaKayttaja();
	}
	
	
	/**
	 * K�sittelee todo:n muokkaamisen k�ytt�liittym�ss�
	 */
	@FXML void handleMuutaTODO() {
		muokkaaTODO();
	}
	
	
	@FXML void handlePoistaTODO() {
		poistaTODO();
		
	}
	
	/*Ei k�ytt�liittym��n liittyv�t funktiot ja metodit*/
	
	private TODOohjelma ohjelma = new TODOohjelma();
	private int ind = ohjelma.viimeisinEiKaytettyIndeksi("kayttajat.dat");
	private int TODOind = ohjelma.viimeisinEiKaytettyIndeksi("todo.dat");
	private int tamanHetkinenKayttaja = ind;
	private int tamanHetkinenTODO = TODOind;
	List<Integer> kayttajaIndeksit = new ArrayList<Integer>();
	List<Integer> TODOIndeksit = new ArrayList<Integer>();
		
	
	/**
	 * Luo uuden kayttaja:n ja lis�� sen k�ytt�j� listaan. Sammalla hekee ensimm�isen vapaan ideksin seuraavalle uudelle k�ytt�j�lle.
	 */
	private void uusiKayttaja() {
		if (ind != -1) {
			Kayttaja uusi = new Kayttaja(ind);
			uusi = KayttajaDialogController.luoUusiKayttaja(null, new Kayttaja());						
			if (uusi == null) { 
				return;
			}
			uusi.muokkaaID(ind);
			try {
				ohjelma.lisaaKayttaja(uusi);
				kayttajaLista.getItems().add(uusi.getNimi());
				tulostusAlue.setText(ohjelma.annaKayttaja(ind).toString());
				kayttajaIndeksit.add(ind);
				ohjelma.tallenna();
				ind = ohjelma.viimeisinEiKaytettyIndeksi("kayttajat.dat");
			} catch (Exception e) {
				Dialogs.showMessageDialog("Ongelma k�ytt�j�n luomisessa");
				return;
			}
		} else {
			Dialogs.showMessageDialog("Ongelma k�ytt�j�n luomisessa");
			return;
		}
	}
	
	
	/**
	 * Muokkaa t�ll� hetkell� valitun k�ytt�j�n tiedot
	 */
	private void muokkaaKayttaja() {
		Kayttaja alkuperainen = ohjelma.annaKayttaja(tamanHetkinenKayttaja);
		Kayttaja muokattu = KayttajaDialogController.luoUusiKayttaja(null, alkuperainen);
		if (muokattu != null) {
			alkuperainen = muokattu;
			tulostaKayttajat();
			tallenna();
			asetaKayttajaTiedot(ohjelma.annaKayttaja(tamanHetkinenKayttaja).toString());
		} else {
			return;
		}
	}
	
	
	/**
	 * Uuden todo:n luomismetodi. Heitt�� dialogin, jos sen tekeminen ei onnistu syyst� taikka toisesta
	 */
	private void uusiTODO() {
		if (TODOind != -1) {
			TODO uusi = new TODO(TODOind);
			uusi = TODODialogController.luoUusiTODO(null, new TODO());
			if (uusi == null) {
				return;
			}
			uusi.muokkaaID(TODOind);
			try {		
				if (ohjelma.annaKayttaja(tamanHetkinenKayttaja).getTODOmaara() >= ohjelma.annaMaxTODOperKayttaja()) {
					Dialogs.showMessageDialog("Ongelma TODO:n lis��misess�: Liikaa TODO:ita");
					return;
				} else {				
					ohjelma.lisaaTODO(ohjelma.annaKayttaja(tamanHetkinenKayttaja), uusi);
					valitseKayttaja();
					ohjelma.tallenna();
					TODOind = ohjelma.viimeisinEiKaytettyIndeksi("todo.dat");
				}
			} catch (Exception e) {
				Dialogs.showMessageDialog("Ongelma TODO:n lis��misess�");
				return;
			}
		} else {
			Dialogs.showMessageDialog("Ongelma TODOn luomisessa");
			return;
		}
	}
	
	
	/**
	 * Muokkaa valitun todo:n tietoja. T�m� voidaan peruuttaa, jos k�ytt�j�n painaa "Peruuta"-nappia k�ytt�liittym�ss�.
	 */
	public void muokkaaTODO() {
		TODO alkuperainen = ohjelma.annaTODO(tamanHetkinenTODO);
		TODO muokattu = TODODialogController.luoUusiTODO(null, alkuperainen);
		if (muokattu != null) {
			alkuperainen = muokattu;
			try {
				TODOLista.getItems().clear();
				int todoMaara = ohjelma.annaMaxTODOperKayttaja();
				int[] KayttajanTODOt = ohjelma.annaKayttaja(tamanHetkinenKayttaja).getTODOt();
				
				for (int i = 0; i < todoMaara; i++) {
					if (KayttajanTODOt[i] != -1) {
						TODOLista.getItems().add(asetaTODOTiedot(ohjelma.kayttajanTODOt(tamanHetkinenKayttaja, TODOLista)));
					}
				}				
			} catch (Exception e) { /**/ }
			tallenna();
			asetaKayttajaTiedot(ohjelma.annaKayttaja(tamanHetkinenKayttaja).toString());
		} else {
			return;
		}
	}
	
	
	/**
	 * Kayttajan valinnan metodi. Luodaan uusi lista, jossa on k�ytt�joen ideksit suhteessa n�kyvill� olevaa listView:i�.
	 * Osaa my�s laittaa oikeat napit pois p��lt�, jos ei ole k�ytt�j�� valittuna.
	 */
	private void valitseKayttaja() {
		try {
			tyhjennaTiedot();
			muokkaaKayttajaButton.setDisable(false);
			poistaKayttajaButton.setDisable(false);
			lisaaTODOButton.setDisable(false);
			muokkaaTODONappi.setDisable(false);
			poistaTODOButton.setDisable(false);
			int apu = kayttajaLista.getSelectionModel().getSelectedIndex();
			tamanHetkinenKayttaja = kayttajaIndeksit.get(apu);
			TODOIndeksit = new ArrayList<Integer>();
			
			try {
				asetaKayttajaTiedot(ohjelma.annaKayttaja(tamanHetkinenKayttaja).toString());
				if (ohjelma.annaKayttaja(tamanHetkinenKayttaja).getTODOmaara() > 0) {
					muokkaaTODONappi.setDisable(false);
				} else {
					muokkaaTODONappi.setDisable(true);
				}
			} catch (Exception e) { /**/ }
				
			try {
				TODOLista.getItems().clear();
				int todoMaara = ohjelma.annaMaxTODOperKayttaja();
				int[] KayttajanTODOt = ohjelma.annaKayttaja(tamanHetkinenKayttaja).getTODOt();
						
				for (int i = 0; i < todoMaara; i++) {
					if (KayttajanTODOt[i] != -1) {
						TODOIndeksit.add(KayttajanTODOt[i]);
					}
				}
				
				for (int i = 0; i < todoMaara; i++) {
					if (KayttajanTODOt[i] != -1) {
						TODOLista.getItems().add(asetaTODOTiedot(ohjelma.kayttajanTODOt(tamanHetkinenKayttaja, TODOLista)));
					}
				}
				
			} catch (Exception e) { /**/ }
		} catch (Exception e) {
			muokkaaKayttajaButton.setDisable(true);
			poistaKayttajaButton.setDisable(true);
			lisaaTODOButton.setDisable(true);
			muokkaaTODONappi.setDisable(true);
			poistaTODOButton.setDisable(true);
		}

	}

	
	/**
	 * Valitsee todo:n listalta.
	 * T�m� tapahtuu listView:in ja listan suhteellisten indeksien avulla.
	 */
	private void valitseTODO() {
		try {
			int apu = TODOLista.getSelectionModel().getSelectedIndex();
			tamanHetkinenTODO = TODOIndeksit.get(apu);			
		} catch (Exception e) {
			
		}
	}
	
	
	/**
	 * Tulostaa kayttaja:n tiedot sopiviin textField-kenttiin
	 * @param kayttajanTiedot Kayttaja:n tiedot tiedostosta
	 */
	public void asetaKayttajaTiedot(String kayttajanTiedot) {
		String[] osat = kayttajanTiedot.split("#");	
		nimiField.setText(osat[1]);
	    puhNumField.setText(osat[2]);
	    osoiteField.setText(osat[3]);
	    sPostiField.setText(osat[4]);		
	}
	
	
	/**
	 * Tyhjent�� tiedot textField-laatikoista
	 */
	public void tyhjennaTiedot() {
		TODOLista.getItems().clear();
		nimiField.setText("");
	    puhNumField.setText("");
	    osoiteField.setText("");
	    sPostiField.setText("");	
	}
	
	
	/**
	 * Tulostaa todo:n tiedot sopivaan kohtaan
	 * @param TODOnTiedot Todo:n tiedot tiedostosta
	 * @return Todo luettavassa ja esteettisess� muodossa.
	 */
	public String asetaTODOTiedot(String TODOnTiedot) {
		String[] osat = TODOnTiedot.split("#");
		String tulos = "|  " + osat[0] + "  |  " + osat[1] + "  |  " + osat[2] + "  |  " + osat[3] + "  |";
		return tulos;
	}
	
	
	/**
	 * Tallentaa tiedot ohjelma.java-luokassa
	 */
	public void tallenna() {
		try {
			ohjelma.tallenna();
		} catch (Exception e) {
			System.out.println("Ongelma: " + e);
		}
	}
	
	
	/**
	 * Yritt�� lukea tiedostosta ja asettaa k�ytt�j�t listaan.
	 */
	public void lueTiedosto() {
		try {
			muokkaaKayttajaButton.setDisable(true);
			poistaKayttajaButton.setDisable(true);
			lisaaTODOButton.setDisable(true);
			muokkaaTODONappi.setDisable(true);
			poistaTODOButton.setDisable(true);
			ohjelma.lueTiedosto();
			kayttajaIndeksit = new ArrayList<Integer>();
			for (int i = 0; i < ohjelma.annaMaxKayttajaMaara(); i++) {
				try {
					Kayttaja kayttaja = ohjelma.annaKayttaja(i);
					if (kayttaja != null) {
						String nimi = kayttaja.getNimi();
						kayttajaLista.getItems().add(nimi);
						kayttajaIndeksit.add(kayttaja.getId());
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
	 * Tulostaa kayttaja-listan sis�ll�n listView-kentt��n
	 */
	public void tulostaKayttajat() {
		kayttajaLista.getItems().clear();
		kayttajaIndeksit = new ArrayList<Integer>();
		for (int i = 0; i < ohjelma.annaMaxKayttajaMaara(); i++) {
			Kayttaja tulostettava = ohjelma.annaKayttaja(i);
			if (tulostettava != null) {
				kayttajaIndeksit.add(tulostettava.getId());
				kayttajaLista.getItems().addAll(tulostettava.getNimi());
			}
		}	
	}
	
	
	/**
	 * Hakkee k�ytt�ji� hakukentt��n kirjoitetun parametrin avulla. Osaa laittaa napit pois p��lt�.
	 * K�ytet��n regex:i�
	 */
	public void haeKayttajat(String hakuehto) {
		
		kayttajaIndeksit = new ArrayList<Integer>();
		
		if (hakuehto != "") {
			kayttajaLista.getItems().clear();
			String RegexEhto = ".*" + hakuehto.toLowerCase() + ".*";
			try {
				for (int i = 0; i < ohjelma.annaMaxKayttajaMaara(); i++) {
					Kayttaja kayttaja = ohjelma.annaKayttaja(i);
					String kayttajanNimi = kayttaja.getNimi();
					String kayttajaNimiHaku = ohjelma.annaKayttaja(i).getNimi().toLowerCase();
					if (kayttajaNimiHaku.matches(RegexEhto)) {
						kayttajaLista.getItems().addAll(kayttajanNimi);
						kayttajaIndeksit.add(kayttaja.getId());
					}
				}
			} catch (Exception e) { /**/ }
			if (kayttajaIndeksit.isEmpty()) {
				tyhjennaTiedot();
				muokkaaKayttajaButton.setDisable(true);
				poistaKayttajaButton.setDisable(true);
				lisaaTODOButton.setDisable(true);
				muokkaaTODONappi.setDisable(true);
				poistaTODOButton.setDisable(true);
			}
		} else {
			tulostaKayttajat();
		}
	}
	
	
	/**
	 * Poistaa kayttajan
	 */
	public void poistaKayttaja() {
		ohjelma.poistaKayttaja(tamanHetkinenKayttaja);
		tyhjennaTiedot();
		hakuKentta.clear();
	}
	
	
	/**
	 * Poistaa todo:n kayttaja:lta
	 */
	public void poistaTODO() {
		int indeksi = tamanHetkinenTODO;
		ohjelma.poistaTODO(tamanHetkinenTODO);
		
		Kayttaja kayttaja = ohjelma.annaKayttaja(tamanHetkinenKayttaja);
		int [] todot = kayttaja.getTODOt();
		
		for (int i = 0; i < ohjelma.annaMaxTODOperKayttaja(); i++) {
			if (indeksi == todot[i]) { 
				todot[i] = -1;
				tallenna();
				TODOLista.getItems().clear();
				try {
					TODOIndeksit.remove(tamanHetkinenTODO);	
				} catch (Exception e) {}
				
				tulostaTODOt(ohjelma.annaKayttaja(tamanHetkinenKayttaja));
				return;
			}
		}
	}
	
	
	/**
	 * Tulostaa kayttaja:n todot
	 */
	public void tulostaTODOt(Kayttaja kayttaja) {
		int [] todot = kayttaja.getTODOt();
		for (int i = 0; i < kayttaja.getTODOmaara(); i++) {
			if (todot[i] != -1) {
				try {
					TODOLista.getItems().add(asetaTODOTiedot(ohjelma.kayttajanTODOt(tamanHetkinenKayttaja, TODOLista)));
				} catch (Exception e) {
					
				}			
			}
		}	
	}
}












