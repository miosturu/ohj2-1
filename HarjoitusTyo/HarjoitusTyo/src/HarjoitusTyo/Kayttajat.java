package HarjoitusTyo;

/**
 * Kayttajat-luokka, jonka tarkoituksena on pitää käyttäjien paikoista taulukossa ja luoda, että poistaa käyttäjiä
 */
public class Kayttajat {
	private static final int MAX_KAYTTAJIA = 10;
	private final Kayttaja[] kayttajatTaulukko = new Kayttaja[MAX_KAYTTAJIA];
	private int kayttajienMaara = 0;
	
	public static void main(String[] args) {
		Kayttajat kayttajat = new Kayttajat();
		Kayttaja a1 = new Kayttaja(0); Kayttaja a2 = new Kayttaja(1);
		a1.luoValmis(0); a2.luoValmis(1);
		a1.tulostaTiedot(); a2.tulostaTiedot();
	}
	
	
	public Kayttajat() {
		
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