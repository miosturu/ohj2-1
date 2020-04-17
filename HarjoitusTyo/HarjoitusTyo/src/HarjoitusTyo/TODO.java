package HarjoitusTyo;

import java.sql.Time;
import java.util.Random;

/**
 * Todo-olio, joka tallentaa oman ID:n, otsikon, paikan, ajan ja lisätiedot.
 * Näitä voidaan muokata vapaasti, paitisi ID:tä.
 */

@SuppressWarnings("deprecation")
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
	 * Luo uuden todo-olion, jossa on olettamus arvot 
	 */
	public TODO() {
		this.id = -1;
		this.otsikko = "";
		this.paikka = "";
		this.aika = new Time(12,00,00);
		this.lisaTietoa = "";
	}
	
	
	/**
	 * Todo-olio, jossa on kaikki tiedot. Käytetään varsinaisessa ohjelmassa, kun luodaan todo:ita.
	 * @param id Todo-olion ID. Ei voida muokata.
	 * @param otsikko Todo-olion otsikko. Voidaan muokata.
	 * @param paikka Paikka, missä todo suoritetaan oikeassa elämässä. Voidaan muokata.
	 * @param aika Aika, milloin todo on aikataulutettu. Voidaan muokata.
	 * @param tietoa Lisätietoa todo:Sta, voidaan kitjoittaa vapaasti. Voidaan muokata.
	 */
	public TODO(int id, String otsikko, String paikka, Time aika, String tietoa) {
		this.id = id;
		
		if (otsikko == "") this.otsikko = "Syötä otsikko tähän";
		else this.otsikko = otsikko;
		
		if (paikka == "") this.paikka = "Syötä paikka tähän";
		else this.paikka = paikka;
		
		if (aika != null) this.aika = aika;
		else this.aika = new Time(12,00,00);
		
		if (tietoa == "") this.lisaTietoa = "Syötä lisätidot tähän";
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
		this.lisaTietoa = "Muista tehdä dokumentaatiota";
	}
	
	
	public void tulostaTiedot() {
		
		tulostaErottimet();		
		System.out.println();
		
		System.out.println("ID: " + this.getId());
		System.out.println("Otsikko: " + this.getOtsikko());
		System.out.println("Paikka: " + this.getPaikka());
		System.out.println("Aika: " + this.getAika());
		System.out.println("Lisätietoa: " + this.getLisaTietoa());
	
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
	public String toStringTulostettava() {
		return this.id + " | " + this.otsikko + " | " + this.paikka + " | " + this.aika + " | " + this.lisaTietoa;
	}
	
	
	/**
	 * Muuttaa todo-olion tiedostosta luettavaan muotoon.
	 * @return todo-olio luettavassa muodossa.
	 */
	public String toString() {
		return this.id + "#" + this.otsikko + "#" + this.paikka + "#" + this.aika + "#" + this.lisaTietoa;
	}
	
	
	/**
	 * Jäsentää merkkijonon tiedostosta ja luo uuden todo-olion.
	 * Taulukossa olevat osat menevät seuraavasti:
	 * 	0.: ID.
	 * 	1.: Otsikko.
	 * 	2.: Paikka.
	 * 	3.: Aika.
	 * 	4.: Lisätietoa.
	 * @param rivi Rivi, joka jäsennetään
	 * @return Todo-olio.
	 */
	public TODO parse(String rivi) {
		String[] arr = rivi.split("#");
		int id = Integer.parseInt(arr[0]);
		
		String[] aikaOsatString = arr[3].split(":");
		int[] aikaOsatInt = new int[3];
		
		for (int i = 0; i < 3; i++) {
			aikaOsatInt[i] = Integer.parseInt(aikaOsatString[i]);
		}

		Time aika = new Time(aikaOsatInt[0], aikaOsatInt[1], aikaOsatInt[2]);
		
		TODO todo = new TODO(id, arr[1], arr[2], aika, arr[4]);
		return todo;
	}
	
	
	/**
	 * Tulostaa todo:n tiedot sopivaan kohtaan
	 * @param TODOnTiedot Todo:n tiedot tiedostosta
	 */
	public String toStringLuettava() {
		return this.id + "  |  " + this.otsikko + "  |  " + this.paikka + "  |  " + this.aika + "  |  " + this.lisaTietoa + "  |";
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
	
	/**
	 * Muokkaa todo:n ID:n 
	 */
	public void muokkaaID(int id) {
		this.id = id;
	}
	
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
	 * Muokkaa todo:n lisätietoa 
	 */
	public void muokkaaLisaTietoa(String tietoa) {
		this.lisaTietoa = tietoa;
	}

	
	/**
	 * Muokkaa kaikki muokattavat kohdat todo:ssa. Voitaisiin käyttää uuden todo:n luomisen jälkeen.
	 * @param otsikko Todo:n uusi otsikko.
	 * @param paikka Todo:n uusi paikka.
	 * @param aika Todo:n uusi aika.
	 * @param tietoa Todo:n uusi lisätieto.
	 */
	public void muokkaaKaikki(String otsikko, String paikka, Time aika, String tietoa) {
		this.otsikko = otsikko;
		this.paikka = paikka;
		this.aika = aika;
		this.lisaTietoa = tietoa;
	}
}
