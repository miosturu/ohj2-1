package HarjoitusTyo;

/**
 * TODOohjelma, jonka tarkoituksena on kommunikoida ja koordinoita muiden luokkien toimintaa ja k‰sitell‰ k‰ytt‰j‰n antamaan syˆtett‰.
 */
public class TODOohjelma {
	private final Kayttajat kayttajat = new Kayttajat();
	private final TODOt todot = new TODOt();
	
	public static void main(String[] args) {
		TODOohjelma ohjelma = new TODOohjelma();
		
		Kayttaja k0 = new Kayttaja(0);
		k0.luoValmis(0);
		ohjelma.lisaaKayttaja(k0);
		k0.tulostaTiedot();
		
		
		TODO t1 = new TODO(0);
		t1.luoValmis(0);
		TODO t2 = new TODO(1);
		t2.luoValmis(1);

		
		ohjelma.lisaaTODO(k0,t1);
		ohjelma.lisaaTODO(k0,t2);
		
		k0.tulostaTiedot();
		
		String s1 = ohjelma.kayttajanTODOt(0);
		System.out.println(s1);
		
		System.out.println(t1.toString() + "\n" + t2.toString());
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
}
