package HarjoitusTyo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Kayttajat-luokka, jonka tarkoituksena on pitää käyttäjien paikoista taulukossa ja luoda, että poistaa käyttäjiä
 */
public class Kayttajat {
	private static final int MAX_KAYTTAJIA = 10;
	private final Kayttaja[] kayttajatTaulukko = new Kayttaja[MAX_KAYTTAJIA];
	private int kayttajienMaara = 0;
	private boolean muutettu = false;
	
	public static void main(String[] args) {
		Kayttajat kayttajat = new Kayttajat();
		Kayttaja a1 = new Kayttaja(0); Kayttaja a2 = new Kayttaja(1);
		a1.luoValmis(0); a2.luoValmis(1);
		a1.tulostaTiedot(); a2.tulostaTiedot();
	}
	
	
	public Kayttajat() {
		// Ei tarvitse tehdä mitään.
	}
	
	
	/**
	 * Lisää uuden käyttäjän ensimmäiseen tyhjään indeksiin
	 */
	public void lisaaGenerinenKayttaja() {
		if (this.kayttajienMaara > this.MAX_KAYTTAJIA) {
			for (int i = 0; i < this.kayttajatTaulukko.length-1; i++) {
				if (this.kayttajatTaulukko[i] == null) {
					this.kayttajatTaulukko[i] = new Kayttaja(i, "", "", "", "");
				}
			}
			this.kayttajienMaara++;		
		} else {
			System.out.println("LIKAA KAYTTAJIA LISÄÄMISTÄ VARTEN");
		}
	}
	
	
	/**
	 * Lukee tiedostosta Kayttajien tiedot
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void lueTiedostosta(String tiedosto) throws FileNotFoundException, IOException {
		try (BufferedReader lukija = new BufferedReader(new FileReader(tiedosto))) {
			String rivi;
			while ((rivi = lukija.readLine()) != null) {
				//System.out.println(rivi);
				Kayttaja kayttaja = new Kayttaja();
				kayttaja = new Kayttaja().parse(rivi);
				lisaaKayttaja(kayttaja);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Ongelma Kayttajat.java: " + ex);
		} catch (IOException ex) {
			System.out.println("Ongelma Kayttajat.java: " + ex);
		}		
	}
	
	
	/**
	 * Tallentaa Kayttajan tiedot tiedostoon
	 * @throws IOException 
	 */
	public void tallenna(String tiedosto) throws IOException {		
		try (PrintWriter kirjuri = new PrintWriter(new FileWriter(tiedosto))) {
			for (Kayttaja kayttaja : kayttajatTaulukko) {
				kirjuri.println(kayttaja.toString());
			}
		} catch (IOException e) {
			System.out.println("Ongelma: " + e);
		}
	}
	
			
	/**
	 * Lisää Kayttaja:n taulukkoon
	 */
	public void lisaaKayttaja(Kayttaja kayttaja) {
		kayttajatTaulukko[kayttaja.getId()] = kayttaja;
		this.kayttajienMaara++;
	}
	
	
	/**
	 * Poistaa Kayttajan tietystä indeksistä.
	 */
	public void poistaKayttaja(int id) {
		this.kayttajatTaulukko[id] = null;
		this.kayttajienMaara--;
	}
	
	
	/**
	 * Palauttaa Kayttajien määrän.
	 */
	public int getKayttajaMaara() {
		return this.kayttajienMaara;
	}
	
	
	/**
	 * Palauttaa Kayttajan taulukosta.
	 */
	public Kayttaja anna(int id) {
		return kayttajatTaulukko[id];
	}
}