package HarjoitusTyo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.ListView;

/**
 * TODOohjelma, jonka tarkoituksena on kommunikoida ja koordinoita muiden luokkien toimintaa ja k‰sitell‰ k‰ytt‰j‰n antamaan syˆtett‰.
 */
public class TODOohjelma {
	private Kayttajat kayttajat = new Kayttajat();
	private TODOt todot = new TODOt();
	String kayttajatTiedosto = "kayttajat.dat";
	String todotTiedosto = "todo.dat";
	
	public static void main(String[] args) {
		TODOohjelma ohjelma = new TODOohjelma();
		
		int indeksi = ohjelma.viimeisinEiKaytettyIndeksi("kayttajat.dat");
		
		ohjelma.lueTiedosto();
		
		Kayttaja k0 = new Kayttaja();
		k0.luoValmis(indeksi);
		
		ohjelma.lisaaKayttaja(k0);
		
		
		TODO t0 = new TODO(); t0.luoValmis(0);
		
		ohjelma.lisaaTODO(ohjelma.annaKayttaja(1), t0);
		
		ohjelma.tallenna();
	}
	
	
	/**
	 * TODOohjelman konstruktori. Ei tarvitse tehd‰ mit‰‰n, koska arvot ollaan m‰‰ritelty aikaisemmin.
	 * @example
	 * </pre name="test">
	 * TODOohjelma ohjelma = new TODOohjelma();
	 * TODO t0 = new TODO(0); t0.luoValmis(0);
	 * TODO t1 = new TODO(1); t1.luoValmis(1);
	 * TODO t2 = new TODO(2); t2.luoValmis(2);
	 * 
	 * Kayttaja k0 = new Kayttaja(0); k0.luoValmis(0);
	 * Kayttaja k1 = new Kayttaja(1); k0.luoValmis(1);
	 * ohjelma.lisaaKayttaja(k0); ohjelma.lisaaKayttaja(k1);
	 * 
	 * ohjelma.lisaaTODO(k0, t0); ohjelma.lisaaTODO(k0, t1); ohjelma.lisaaTODO(k1, t2);
	 * ohjelma.getKayttajia() === 2;
	 * ohjelma.annaKayttaja(0) === k0;
	 * ohjelma.annaTODO(0) === t0;
	 * ohjelma.poistaKayttaja(1);
	 * ohjelma.getKayttajia() === 1;
	 * </pre>
	 */
	public TODOohjelma() {
		
	}
	
	
	/**
	 * J‰senen postamisen v‰litt‰j‰metodi.
	 */
	public void poistaKayttaja(int id) {
		this.kayttajat.poistaKayttaja(id);
	}
	
	
	/**
	 * J‰senen lis‰‰misen v‰litt‰j‰metodi.
	 */
	public void lisaaKayttaja(Kayttaja kayttaja) {
		this.kayttajat.lisaaKayttaja(kayttaja);
	}
	
	
	/**
	 * Todo:n poistamisen v‰litt‰j‰metodi.
	 * Pit‰isi tiet‰‰, mille k‰ytt‰j‰lle antaa viiteindeksin.
	 */
	public void poistaTODO(int id) {
		this.todot.poistaTODO(id);
	}
	
	
	/**
	 * Lis‰‰ todo:n tietylle Kayttaja:lle. Kayttajalle menee indeksi todot taulukkoon
	 * @param kayttaja Kayttaja, jolle todo asetetaan
	 * @param todo Todo, joka asetetaan
	 */
	public void lisaaTODO(Kayttaja kayttaja, TODO todo) {
		this.todot.lisaaTODO(todo);
		kayttaja.lisaaTODO(todo.getId());		
	}
	
	
	/**
	 * Palauttaa Kayttajien m‰‰r‰n
	 */
	public void getKayttajia() {
		this.kayttajat.getKayttajaMaara();
	}
	
	
	/**
	 * Palauttaa viitteen tiettyyn Kayttajaan
	 * @param id Kayttajan id
	 */
	public Kayttaja annaKayttaja(int id) {
		return kayttajat.anna(id);
	}
	
	
	/**
	 * Palauttaa todo:n ID:n
	 * @param id Todo:n ID
	 */
	public TODO annaTODO(int id) {
		return todot.anna(id);
	}
	
	/**
	 * Tekee kayttajan todo:ista yhden merkkijonon
	 * @param id Kayttajan ID.
	 */
	public String kayttajanTODOt(int id, ListView<String> todoLista) {
		int[] lista = this.kayttajat.anna(id).getTODOt();
		String tulostettava = "";
		for (int i = 0; i < this.annaMaxTODOMaara(); i++) {
			if (lista[i] > -1) {
				todoLista.getItems().add(this.todot.anna(lista[i]).toStringLuettava());
			}
		}
		return tulostettava;
	}
		
	
	/**
	 * Tallentaa Kayttajien ja todo:iden tiedot omiin tiedostoihin.
	 */
	public void tallenna() {
		try {
			kayttajat.tallenna(kayttajatTiedosto);
		} catch (Exception e) {}
		
		try {
			todot.tallenna(todotTiedosto);
		} catch (Exception f) {}
	}
	
	
	/**
	 * Lukee kayttajien ja todo:iden tiedostot. Jos tiedostoa ei voida lukea, tulostetaan konsoliin virheet.
	 */
	public void lueTiedosto() {
		kayttajat = new Kayttajat();
		todot = new TODOt();
		
		try {
			kayttajat.lueTiedostosta(kayttajatTiedosto);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("TODOohjelma ongelma: " + e);
		}
		
		try {
			todot.lueTiedostosta(todotTiedosto);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("TODOohjelma ongelma: " + e);
		}
	}
		
	
	/**
	 * Hakee tiedoston viimesimm‰n ei k‰ytetyn indeksin
	 * @param tiedosto Tiedosto, josta indeksi heataa.
	 * @return viimesin k‰ytt‰m‰tˆn indeksi.
	 */
	public int viimeisinEiKaytettyIndeksi(String tiedosto) {		
		int[] indeksit = new int[this.annaMaxTODOMaara()];
		
		try (BufferedReader lukija = new BufferedReader(new FileReader(tiedosto))) {
			String rivi; 
			while ((rivi = lukija.readLine()) != null) {
				String[] osat = rivi.split("#");
				int i = Integer.parseInt(osat[0]);
				indeksit[i] = 1;
			}
			
			for (int j = 0; j < this.annaMaxTODOMaara(); j++) {
				if (indeksit[j] == 0) return j;
			}
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	
	/**
	 * Palauttaa maksimin kayttaja maaran
	 */
	public int annaMaxKayttajaMaara() {
		return this.kayttajat.getMaxKayttajat();
	}
	
	
	/**
	 * Palauttaa maksimin todo maaran taulukossa
	 */
	public int annaMaxTODOMaara() {
		return this.todot.getMaxTODOt();
	}
	
	
	/**
	 * Palauttaa maksimin m‰‰r‰n todo:ita per k‰ytt‰j‰
	 * @return maksimi todo m‰‰r‰ per k‰ytt‰j‰
	 */
	public int annaMaxTODOperKayttaja() {
		return this.kayttajat.getMaxTODOtPerKayttaja();
	}
}
