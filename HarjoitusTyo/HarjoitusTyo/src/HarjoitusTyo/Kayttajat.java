package HarjoitusTyo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Kayttajat-luokka, jonka tarkoituksena on pit‰‰ k‰ytt‰jien paikoista taulukossa ja luoda, ett‰ poistaa k‰ytt‰ji‰
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
	
	
	/**
	 * Kayttajat-luokan alustus metodi. Ei tarvitse tehd‰ mit‰‰n, koska ollaan m‰‰ritelty tarvittavat arvot aikaisemmin
	 * @example
	 * <pre name="test">
	 * Kayttajat kayttajat = new Kayttajat();
	 * Kayttaja k0 = new Kayttaja();
	 * k0.luoValmis();
	 * kayttajat.lisaaKayttaja(k0);
	 * kayttajat.getKayttajaMaara() === 1;
	 * kayttajat.poistaKayttaja(0);
	 * kayttajat.getKayttajaMaara() === 0;
	 * </pre>
	 */
	public Kayttajat() {
		// Ei tarvitse tehd‰ mit‰‰n.
	}
	
	
	/**
	 * Lis‰‰ uuden k‰ytt‰j‰n ensimm‰iseen tyhj‰‰n indeksiin
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
			System.out.println("LIKAA KAYTTAJIA LISƒƒMISTƒ VARTEN");
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
		File tied = new File(tiedosto);
		if (!tied.exists()) {
			tied = new File("C:\\todoOhjelmaTiedot\\" + tiedosto);
			tied.createNewFile();
		}
		System.out.println(tied.getCanonicalFile());
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
	 * Lis‰‰ Kayttaja:n taulukkoon
	 */
	public void lisaaKayttaja(Kayttaja kayttaja) {
		kayttajatTaulukko[kayttaja.getId()] = kayttaja;
		this.kayttajienMaara++;
	}
	
	
	/**
	 * Poistaa Kayttajan tietyst‰ indeksist‰.
	 */
	public void poistaKayttaja(int id) {
		this.kayttajatTaulukko[id] = null;
		this.kayttajienMaara--;
	}
	
	
	/**
	 * Palauttaa Kayttajien m‰‰r‰n.
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
	
	
	/**
	 * Palauttaa max kayttaja m‰‰r‰n
	 */
	public int getMaxKayttajat() {
		return this.MAX_KAYTTAJIA;
	}
	
	
	/**
	 * Palauttaa maksimi todo m‰‰r‰n per yksi kaytt‰ja
	 * @return Maksimi todo per kayttaaja.
	 */
	public int getMaxTODOtPerKayttaja() {
		return this.kayttajatTaulukko[0].getMaxTODOtPerKayttaja();
	}
}