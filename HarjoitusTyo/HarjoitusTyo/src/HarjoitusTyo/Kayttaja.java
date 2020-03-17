package HarjoitusTyo;

import java.util.Random;

/**
 * K‰ytt‰t‰-olio, joka voi tallentaa itseens‰ ID:n, nimen, puh-numeron, osoitteen, s-postin ja itseleen kuuluvat todo:t.
 * Osaa muokata n‰it‰ tietoja, paitsi ID:t‰ 
 */
public class Kayttaja {
	private final int MAX_TODOT = 10;
	private int id;
	private String nimi;
	private String puhNum;
	private String osoite;
	private String sPosti;
	private int[] todot; // Viittaa erilliseen taulukkoon, jossa todo-oliot ovat
	
	public static void main(String[] args) {
		Kayttaja k1 = new Kayttaja(0);		
		k1.luoValmis(0);
		k1.tulostaTiedot();
		
		Kayttaja k2 = new Kayttaja(1);
		k2.luoValmis(1);
		k2.tulostaTiedot();
		
		System.out.println("\nLis‰t‰‰n TODOt\n");
		
		k1.lisaaTODO(1); k1.lisaaTODO(5); k1.lisaaTODO(2);
		k1.tulostaTiedot();
		k2.tulostaTiedot();
		
		System.out.println("\nPoistetaan TODOt\n");
		
		k1.poistaTODO(5);
		k1.tulostaTiedot();
		k2.poistaTODO(2);
		k2.tulostaTiedot();
	}
	
	
	/**
	 * Luo uuden Kayttaja-olion oletusarvoilla
	 * Jos taulukon indeksiss‰ on -1, se tarkoittaa, ett‰ siihen voidaan lis‰t‰ uusi todo
	 */
	public Kayttaja(int id) {
		this.id = id;
		this.nimi = "";
		this.puhNum = "";
		this.osoite = "";
		this.sPosti = "";
		this.todot = new int[MAX_TODOT];
		
		for (int i = 0; i < todot.length; i++) {
			todot[i] = -1;
		}
	}
	
	
	/**
	 * Luo Kayttaja-olion ja varmistaa, ett‰ tietoja on. Todo:iden indeksit lis‰t‰‰n myˆhemmin.
	 * Jos taulukon indeksiss‰ on -1, se tarkoittaa, ett‰ siihen voidaan lis‰t‰ uusi todo
	 * @param id Kayttajan ID. Ei voida muokata.
	 * @param nimi Kayttajan nimi. Voidaan muokata.
	 * @param puh Kayttajan puhelinnumero. Voidaan muokata.
	 * @param osoite Kayttajan osoite. Voidaan muokata.
	 * @param sPosti Kayttajan s‰hkˆpostiosoite. Voidaan muokata.
	 */
	public Kayttaja(int id, String nimi, String puh, String osoite, String sPosti) {
		this.id = id;
		
		if (nimi == "") this.nimi = "Syˆt‰ nimi t‰h‰n";
		else this.nimi = nimi;
		
		if (puh == "") this.puhNum = "Syˆt‰ puhelinnumero t‰h‰n";
		else this.puhNum = puh;
		
		if (osoite == "") this.osoite = "Syˆt‰ osoite t‰h‰n";
		else this.osoite = osoite;
		
		if (sPosti == "") this.sPosti = "Syˆt‰ s‰hkˆpostiosoite t‰h‰n";
		else this.sPosti = sPosti;
		
		for (int i = 0; i < todot.length; i++) {
			todot[i] = -1;
		}
	}
	
	
	/**
	 * Testi k‰ytt‰j‰
	 */
	public void luoValmis(int id) {
		this.id = id;
		Random r = new Random();
		this.nimi = "Mikko Mallikas " + r.nextInt(255);
		this.puhNum = "0501234567";
		this.osoite = "Testikatu 1A";
		this.sPosti = "mikko.mallikas" + r.nextInt(255) +"@email.fi";
	}
	
	
	/**
	 * Tulostaa Kayttasja-olion tiedot. Pit‰isi tulla nullPointer, jos oliota ei ole tietyss‰ ID:ss‰. 
	 */
	public void tulostaTiedot() {
		try {
			tulostaErottimet();	
			
			System.out.println("ID: " + this.id);
			System.out.println("Nimi: " + this.nimi);
			System.out.println("Puh.#: " + this.puhNum);
			System.out.println("Osoite: " + this.osoite);
			System.out.println("S-posti: " + this.sPosti);
			
			for (int i = 0; i < this.todot.length; i++) {
				System.out.print(todot[i] + ", ");
			}
			
			System.out.println("");
			tulostaErottimet();
		} catch (NullPointerException e) {
			tulostaErottimet();
			System.out.println("");		
			
			System.out.println("K‰ytt‰j‰‰ ei ole t‰ss‰ ID:ss‰!");
			
			System.out.println("");
			tulostaErottimet();
		}
		
	}
	
	
	
	public String toString() {
		String palautus = "ID: " + this.id + "\nNimi: " + this.nimi + "\nPuh.#: " + this.puhNum + "\nOsoite: " + this.osoite + "\nS-posti: " + this.sPosti + "\n";
		
		//for (int i = 0; i < this.MAX_TODOT; i++) palautus += this.todot[i] + ", ";
		
		return palautus;
	}
	
	/**
	 * Tulostaa tietojen tulostukseen erottimet, vain esteettinen
	 */
	public void tulostaErottimet() {
		System.out.println("=======================================");
	}
	
	
	/**
	 * Lis‰‰ Kayttaja:an todo:n indeksin
	 * @param id Todo:n ID
	 */
	public void lisaaTODO(int id) {
		for (int i = 0; i < this.MAX_TODOT; i++) {
			if (this.todot[i] == -1) {
				this.todot[i] = id;
				break;
			}
		}
	}
	
	
	/**
	 * Poistaa Kayttaja:lta tietyn todo:n
	 * @param id Todo:n ID
	 */
	public void poistaTODO(int id) {
		for (int i = 0; i < this.MAX_TODOT; i++) {
			if (this.todot[i] == id) {
				this.todot[i] = -1;
				break;
			}
		}
	}
	
	
	/*Kayttajan saantimetodit*/
	
	
	/**
	 * Palauttaa Kayttajan ID:n
	 */
	public int getId() {
		return this.id;
	}
	
	
	/**
	 * Palauttaa Kayttajan nimen
	 */
	public String getNimi() {
		return this.nimi;
	}
	
	
	/**
	 * Palauttaa Kayttajan puhelinnumeron
	 */
	public String getPuhNum() {
		return this.puhNum;
	}
	
	
	/**
	 * Palauttaa Kayttajan osoitteen 
	 */
	public String getOsoite() {
		return this.osoite;
	}
	
	
	/**
	 * Palauttaa Kayttajan s‰hkˆpostiosoitteen
	 */
	public String getSPosti() {
		return this.sPosti;
	}
	
	
	/**
	 * Palauttaa Kayttaja:n todo-taulukon
	 */
	public int[] getTODOt() {
		return this.todot;
	}
	
	
	/*Kayttajan muokkausmetodit*/
	
	//HUOM! ID:t‰ ei voida muokata, koska se viittaa taulukon alkion paikkaan
	
	/**
	 * Muokkaa Kayttajan nimen
	 */
	public void muokkaaNimi(String nimi) {
		this.nimi = nimi;
	}


	/**
	 * Muokkaa Kayttajan puhelinnumeron
	 */
	public void muokkaaPuhNum(String puhNum) {
		this.puhNum = puhNum;
	}


	/**
	 * Muokkaa Kayttajan osoitteen
	 */
	public void muokkaaOsoite(String osoite) {
		this.osoite = osoite;
	}
	
	
	/**
	 * Muokkaa Kayttajan s‰hkˆpostiosoitteen
	 */
	public void muokkaaSPosti(String sPosti) {
		this.sPosti = sPosti;
	}


	/**
	 * Muokkaa kaikki muokattavat kohdat Kayttajassa. Voitaisiin k‰ytt‰‰ luomisen j‰lkeen.
	 * @param nimi Kayttajan uusi nimi.
	 * @param puhNum Kayttajan uusi puhelinnumero.
	 * @param osoite Kayttajan uusi osoite.
	 * @param sPosti Kayttajan uusi s‰hkˆpostiosoite.
	 */
	public void muokkaaKaikki(String nimi, String puhNum, String osoite, String sPosti) {
		this.nimi = nimi;
		this.puhNum = puhNum;
		this.osoite = osoite;
		this.sPosti = sPosti;
	}
}
