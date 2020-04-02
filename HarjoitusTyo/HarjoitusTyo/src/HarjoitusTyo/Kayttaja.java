package HarjoitusTyo;

import java.util.Random;

/**
 * Käyttätä-olio, joka voi tallentaa itseensä ID:n, nimen, puh-numeron, osoitteen, s-postin ja itseleen kuuluvat todo:t.
 * Osaa muokata näitä tietoja, paitsi ID:tä 
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
		int[] todot = new int[]{0,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		k1.todot = todot;
		System.out.println(k1.parse(k1.toString()));
	}
	
	
	/**
	 * Luo uuden Kayttaja-olion oletusarvoilla
	 * Jos taulukon indeksissä on -1, se tarkoittaa, että siihen voidaan lisätä uusi todo
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
	 * Luo uuden Kayttaja-olion oletusarvoilla
	 * Jos taulukon indeksissä on -1, se tarkoittaa, että siihen voidaan lisätä uusi todo
	 */
	public Kayttaja() {
		this.id = -1;
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
	 * Luo Kayttaja-olion ja varmistaa, että tietoja on. Todo:iden indeksit lisätään myöhemmin.
	 * Jos taulukon indeksissä on -1, se tarkoittaa, että siihen voidaan lisätä uusi todo
	 * @param id Kayttajan ID. Ei voida muokata.
	 * @param nimi Kayttajan nimi. Voidaan muokata.
	 * @param puh Kayttajan puhelinnumero. Voidaan muokata.
	 * @param osoite Kayttajan osoite. Voidaan muokata.
	 * @param sPosti Kayttajan sähköpostiosoite. Voidaan muokata.
	 */
	public Kayttaja(int id, String nimi, String puh, String osoite, String sPosti) {
		int[] todot = new int[MAX_TODOT];
		
		this.id = id;
		
		if (nimi == "") this.nimi = "Syötä nimi tähän";
		else this.nimi = nimi;
		
		if (puh == "") this.puhNum = "Syötä puhelinnumero tähän";
		else this.puhNum = puh;
		
		if (osoite == "") this.osoite = "Syötä osoite tähän";
		else this.osoite = osoite;
		
		if (sPosti == "") this.sPosti = "Syötä sähköpostiosoite tähän";
		else this.sPosti = sPosti;
		
		for (int i = 0; i < todot.length; i++) {
			todot[i] = -1;
		}
		this.todot = todot;
	}
	
	
	/**
	 * Luo Kayttaja-olion ja varmistaa, että tietoja on. Todo:iden indeksit lisätään myöhemmin.
	 * Jos taulukon indeksissä on -1, se tarkoittaa, että siihen voidaan lisätä uusi todo
	 * @param id Kayttajan ID. Ei voida muokata.
	 * @param nimi Kayttajan nimi. Voidaan muokata.
	 * @param puh Kayttajan puhelinnumero. Voidaan muokata.
	 * @param osoite Kayttajan osoite. Voidaan muokata.
	 * @param sPosti Kayttajan sähköpostiosoite. Voidaan muokata.
	 */
	public Kayttaja(int id, String nimi, String puh, String osoite, String sPosti, int[] todot) {		
		this.id = id;
		
		if (nimi == "") this.nimi = "Syötä nimi tähän";
		else this.nimi = nimi;
		
		if (puh == "") this.puhNum = "Syötä puhelinnumero tähän";
		else this.puhNum = puh;
		
		if (osoite == "") this.osoite = "Syötä osoite tähän";
		else this.osoite = osoite;
		
		if (sPosti == "") this.sPosti = "Syötä sähköpostiosoite tähän";
		else this.sPosti = sPosti;
		
		
		this.todot = todot;
	}
	
	
	/**
	 * Testi käyttäjä
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
	 * Tulostaa Kayttasja-olion tiedot. Pitäisi tulla nullPointer, jos oliota ei ole tietyssä ID:ssä. 
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
			
			System.out.println("Käyttäjää ei ole tässä ID:ssä!");
			
			System.out.println("");
			tulostaErottimet();
		}
		
	}
	
	
	/**
	 * Tekee oliosta luettavan.
	 * @return Olion tiedot.
	 */
	public String toStringTulostus() {
		String palautus = "ID: " + this.id + "\nNimi: " + this.nimi + "\nPuh.#: " + this.puhNum + "\nOsoite: " + this.osoite + "\nS-posti: " + this.sPosti + "\n";
		return palautus;
	}
	
	
	/**
	 * Muuttaa kayttaja-olion tiedostosta luettavaan muotoon.
	 * @return kayttaja-olio luettavassa muodossa.
	 */
	public String toString() {
		StringBuilder todot = new StringBuilder();
		
		for (int i = 0; i < this.todot.length; i++) {
			if (this.todot[i] != -1) {
				todot.append(this.todot[i] + " ");
			}
		}
		try {
			todot.deleteCharAt(todot.length() - 1);			
		} catch (Exception e) {}
		
		return this.id + "#" + this.nimi + "#" + this.puhNum + "#" + this.osoite + "#" + this.sPosti + "#" +  todot.toString();
	}
	
	
	/**
	 * Jäsentää merkkijonon tiedostosta ja luo uuden kayttaja-olion.
	 * Taulukossa olevat osat menevät seuraavasti:
	 * 0.: ID.
	 * 1.: Nimi.
	 * 2.: Puhelinnumero.
	 * 3.: Osoite.
	 * 4.: Todo:iden indeksit
	 * @param rivi Rivi, joka jäsennetään
	 * @return Kayttaja-olio
	 */
	public Kayttaja parse(String rivi) {
		String[] arr = rivi.split("#");
		int id = Integer.parseInt(arr[0]);
		int[] indeksit = new int[MAX_TODOT];
		
		for (int i = 0; i < MAX_TODOT; i++) {
			indeksit[i] = -1;
		}
			
		try {
			indeksit = parseTodoIndeksit(arr[5]);
		} catch (Exception e) {
			
		}
		
		Kayttaja kayttaja = new Kayttaja(id, arr[1], arr[2], arr[3], arr[4], indeksit);
		//kayttaja.todot = parseTodoIndeksit(arr[5]);
		return kayttaja;
	}
	
	public int[] parseTodoIndeksit(String jono) {
		int[] todoIndeksit = new int[MAX_TODOT];
		
		for (int i = 0; i < MAX_TODOT; i++) {
			todoIndeksit[i] = -1;
		}
		
		String[] todotString = jono.split(" ");
		
		for (int i = 0; i < todotString.length; i++) {
			todoIndeksit[i] = Integer.parseInt(todotString[i]);
		}
		
		return todoIndeksit;
	}
	
	/**
	 * Tulostaa tietojen tulostukseen erottimet, vain esteettinen
	 */
	public void tulostaErottimet() {
		System.out.println("=======================================");
	}
	
	
	/**
	 * Lisää Kayttaja:an todo:n indeksin
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
	 * Palauttaa Kayttajan sähköpostiosoitteen
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
	
	/**
	 * Laskee, monta todo:ta on käyttäjällä.
	 * @return Todo:iden määrä.
	 */
	public int getTODOmaara() {
		int maara = 0;
		
		for (int i = 0; i < todot.length; i++) {
			if (todot[i] != -1) maara++;
		}
		return maara;
	}
	
	
	/*Kayttajan muokkausmetodit*/
	
	//HUOM! ID:tä ei voida muokata, koska se viittaa taulukon alkion paikkaan
	
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
	 * Muokkaa Kayttajan sähköpostiosoitteen
	 */
	public void muokkaaSPosti(String sPosti) {
		this.sPosti = sPosti;
	}


	/**
	 * Muokkaa kaikki muokattavat kohdat Kayttajassa. Voitaisiin käyttää luomisen jälkeen.
	 * @param nimi Kayttajan uusi nimi.
	 * @param puhNum Kayttajan uusi puhelinnumero.
	 * @param osoite Kayttajan uusi osoite.
	 * @param sPosti Kayttajan uusi sähköpostiosoite.
	 */
	public void muokkaaKaikki(String nimi, String puhNum, String osoite, String sPosti) {
		this.nimi = nimi;
		this.puhNum = puhNum;
		this.osoite = osoite;
		this.sPosti = sPosti;
	}
}
