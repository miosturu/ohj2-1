package HarjoitusTyo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Kayttajat-luokka, jonka tarkoituksena on pit�� k�ytt�jien paikoista taulukossa ja luoda, ett� poistaa k�ytt�ji�
 */
public class Kayttajat {
	private static final int MAX_KAYTTAJIA = 10;
	private final Kayttaja[] kayttajatTaulukko = new Kayttaja[MAX_KAYTTAJIA];
	private int kayttajienMaara = 0;
	
	public static void main(String[] args) {
		Kayttajat kayttajat = new Kayttajat();
		
		try {
			kayttajat.lueTiedostosta("kayttajat.dat");
		} catch (IOException e) { /**/ }
		
		Kayttaja k1 = new Kayttaja();
		k1.luoValmis(4);
		
		kayttajat.lisaaKayttaja(k1);
		
		try {
			kayttajat.tallenna("kayttajat.dat");
		} catch (IOException e) { /**/ }
	}
	
	
	public Kayttajat() {
		// Ei tarvitse tehd� mit��n.
	}
	
	
	/**
	 * Lis�� uuden k�ytt�j�n ensimm�iseen tyhj��n indeksiin
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
			System.out.println("LIKAA KAYTTAJIA LIS��MIST� VARTEN");
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
			kirjuri.print("");
			for (Kayttaja kayttaja : kayttajatTaulukko) {
				if (kayttaja != null) {
					kirjuri.println(kayttaja.toString());
				}
			}
		} catch (IOException e) {
			System.out.println("Ongelma: " + e);
		}
	}
	
			
	/**
	 * Lis�� Kayttaja:n taulukkoon
	 */
	public void lisaaKayttaja(Kayttaja kayttaja) {
		kayttajatTaulukko[kayttaja.getId()] = kayttaja;
		this.kayttajienMaara++;
	}
	
	
	/**
	 * Poistaa Kayttajan tietyst� indeksist�.
	 */
	public void poistaKayttaja(int id) {
		this.kayttajatTaulukko[id] = null;
		this.kayttajienMaara--;
	}
	
	
	/**
	 * Palauttaa Kayttajien m��r�n.
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