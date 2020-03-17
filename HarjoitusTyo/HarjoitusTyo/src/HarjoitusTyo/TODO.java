package HarjoitusTyo;

import java.sql.Time;
import java.util.Random;

/**
 * Todo-olio, joka tallentaa oman ID:n, otsikon, paikan, ajan ja lis�tiedot.
 * N�it� voidaan muokata vapaasti, paitisi ID:t�.
 */
public class TODO {
	private int id;
	private String otsikko;
	private String paikka;
	private Time aika;
	private String lisaTietoa;
	
	public static void main(String[] args) {
		TODO t1 = new TODO(0);
		t1.luoValmis(0);
		System.out.println(t1);
	}
	
	
	/**
	 * Luo uuden todo-olion, jossa on olettamus arvot 
	 */
	public TODO(int id) {
		this.id = id;
		this.otsikko = "";
		this.paikka = "";
		this.aika = new Time(12,00,00);
		this.lisaTietoa = "";
	}
	
	/**
	 * Todo-olio, jossa on kaikki tiedot. K�ytet��n varsinaisessa ohjelmassa, kun luodaan todo:ita.
	 * @param id Todo-olion ID. Ei voida muokata.
	 * @param otsikko Todo-olion otsikko. Voidaan muokata.
	 * @param paikka Paikka, miss� todo suoritetaan oikeassa el�m�ss�. Voidaan muokata.
	 * @param aika Aika, milloin todo on aikataulutettu. Voidaan muokata.
	 * @param tietoa Lis�tietoa todo:Sta, voidaan kitjoittaa vapaasti. Voidaan muokata.
	 */
	public TODO(int id, String otsikko, String paikka, Time aika, String tietoa) {
		this.id = id;
		
		if (otsikko == "") this.otsikko = "Sy�t� otsikko t�h�n";
		else this.otsikko = otsikko;
		
		if (paikka == "") this.paikka = "Sy�t� paikka t�h�n";
		else this.paikka = paikka;
		
		if (aika != null) this.aika = aika;
		else this.aika = new Time(12,00,00);
		
		if (tietoa == "") this.lisaTietoa = "Sy�t� lis�tidot t�h�n";
		else this.lisaTietoa = tietoa;
	}
	
	
	/**
	 * Asettaa valmiit arvot todo:lle.
	 * @param id Todo:n ID.
	 */
	public void luoValmis(int id) {
		this.id = id;
		Random r = new Random();
		this.otsikko = "Tee harkan seuraava osa " + r.nextInt(255);
		this.paikka = "Oma kotikone";
		this.aika = new Time(r.nextInt(24),r.nextInt(59),r.nextInt(59));
		this.lisaTietoa = "Muista tehd� dokumentaatiota";
	}
	
	
	public void tulostaTiedot() {
		
		tulostaErottimet();		
		System.out.println();
		
		System.out.println("ID: " + this.getId());
		System.out.println("Otsikko: " + this.getOtsikko());
		System.out.println("Paikka: " + this.getPaikka());
		System.out.println("Aika: " + this.getAika());
		System.out.println("Lis�tietoa: " + this.getLisaTietoa());
	
		tulostaErottimet();
	}
	
	
	/**
	 * Tulostaa erottimet
	 */
	public void tulostaErottimet() {
		System.out.println("=======================================");
	}
	
	/**
	 * Muuttaa olion luettavaan muotoon
	 */
	public String toString() {
		return this.id + " | " + this.otsikko + " | " + this.paikka + " | " + this.aika + " | " + this.lisaTietoa;
	}
	
	
	/*todo:n saantimetodit*/
	
	
	/**
	 * Palauttaa todo:n ID:N 
	 */
	public int getId() {
		return this.id;
	}
	
	
	/**
	 * Palauttaa todo:n otsikon 
	 */
	public String getOtsikko() {
		return this.otsikko;
	}
	
	
	/**
	 * Palauttaa todo:n paikan  
	 */
	public String getPaikka() {
		return this.paikka;
	}
	
	
	/**
	 * Palauttaa todo:n ajan
	 */
	public Time getAika() {
		return this.aika;
	}
	
	
	/**
	 * Palauttaa todo:n lisatiedot
	 */
	public String getLisaTietoa() {
		return this.lisaTietoa;
	}


	/*todo:n muokkausmetodit*/
	
	//HUOM! ID:t� ei voida muokata, koska se viittaa taulukon alkion paikkaan
	
	/**
	 * Muokkaa todo:n otsikkoa 
	 */
	public void muokkaaOtsikko(String otsikko) {
		this.otsikko = otsikko;
	}
	
	
	/**
	 * Muokkaa todo:n paikkaa 
	 */
	public void muokkaaPaikka(String paikka) {
		this.paikka = paikka;
	}
	
	
	/**
	 * Muokkaa todo:n aikaa 
	 */
	public void muokkaaAika(Time aika) {
		this.aika = aika;
	}
	
	
	/**
	 * Muokkaa todo:n lis�tietoa 
	 */
	public void muokkaaLisaTietoa(String tietoa) {
		this.lisaTietoa = tietoa;
	}

	
	/**
	 * Muokkaa kaikki muokattavat kohdat todo:ssa. Voitaisiin k�ytt�� uuden todo:n luomisen j�lkeen.
	 * @param otsikko Todo:n uusi otsikko.
	 * @param paikka Todo:n uusi paikka.
	 * @param aika Todo:n uusi aika.
	 * @param tietoa Todo:n uusi lis�tieto.
	 */
	public void muokkaaKaikki(String otsikko, String paikka, Time aika, String tietoa) {
		this.otsikko = otsikko;
		this.paikka = paikka;
		this.aika = aika;
		this.lisaTietoa = tietoa;
	}
}
