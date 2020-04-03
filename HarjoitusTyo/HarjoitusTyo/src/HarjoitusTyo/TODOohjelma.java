package HarjoitusTyo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODOohjelma, jonka tarkoituksena on kommunikoida ja koordinoita muiden luokkien toimintaa ja käsitellä käyttäjän antamaan syötettä.
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
	 * Jäsenen postamisen välittäjämetodi.
	 */
	public void poistaKayttaja(int id) {
		this.kayttajat.poistaKayttaja(id);
	}
	
	
	/**
	 * Jäsenen lisäämisen välittäjämetodi.
	 */
	public void lisaaKayttaja(Kayttaja kayttaja) {
		this.kayttajat.lisaaKayttaja(kayttaja);
	}
	
	
	/**
	 * Todo:n poistamisen välittäjämetodi.
	 * Pitäisi tietää, mille käyttäjälle antaa viiteindeksin.
	 */
	public void poistaTODO(int id) {
		this.todot.poistaTODO(id);
	}
	
	
	/**
	 * Lisää todo:n tietylle Kayttaja:lle. Kayttajalle menee indeksi todot taulukkoon
	 * @param kayttaja Kayttaja, jolle todo asetetaan
	 * @param todo Todo, joka asetetaan
	 */
	public void lisaaTODO(Kayttaja kayttaja, TODO todo) {
		this.todot.lisaaTODO(todo);
		kayttaja.lisaaTODO(todo.getId());		
	}
	
	
	/**
	 * Palauttaa Kayttajien määrän
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
	public String kayttajanTODOt(int id) {
		int[] lista = this.kayttajat.anna(id).getTODOt();
		String tulostettava = "";
		for (int i = 0; i < 10; i++) if (lista[i] > -1) tulostettava += this.todot.anna(lista[i]) + "\n";
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
	 * Hakee tiedoston viimesimmän ei käytetyn indeksin
	 * @param tiedosto Tiedosto, josta indeksi heataa.
	 * @return viimesin käyttämätön indeksi.
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
			
			for (int j = 0; j < this.annaMaxKayttajaMaara(); j++) {
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
	 * Palauttaa maksimin kayttaja maaran
	 */
	public int annaMaxTODOMaara() {
		return this.todot.getMaxTODOt();
	}
}
