package HarjoitusTyo;

import java.io.IOException;

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
		
		ohjelma.lueTiedosto();
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
	public void lisaaKayttaja(Kayttaja kayttja) {
		this.kayttajat.lisaaKayttaja(kayttja);
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
			kayttajat.tallenna(kayttajatTiedosto); // TODO Pit‰isi keksi‰, miten tiedostot k‰sitell‰‰n
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
}
