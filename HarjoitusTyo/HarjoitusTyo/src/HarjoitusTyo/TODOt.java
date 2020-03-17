package HarjoitusTyo;

import java.sql.Time;

/**
 * todotTaulukko-luokka, jonka tarkoituksena on tiet��, miss� mik�kin todo on, osata tehd� ja poistaa todon.
 * Ei itse tied�, kenelle mik�kin todo kuuluu.
 */
public class TODOt {
	private int MAX_TODOITA = 50;
	private TODO[] todotTaulukko = new TODO[MAX_TODOITA];
	private int todoMaara = 0;
	
	
	public static void main(String[] args) {
		TODOt todot = new TODOt();
		TODO t1 = new TODO(0); TODO t2 = new TODO(1);
		t1.luoValmis(0); t2.luoValmis(1);
		t1.tulostaTiedot(); t2.tulostaTiedot();
	}
	
	
	public TODOt() {

	}
	
	
	/**
	 * Lis�� uuden todo:n ensimm�iseen vapaaseen indeksiin 
	 */
	public void lisaaGenerinenTODO() {
		if (this.todoMaara > this.MAX_TODOITA) {
			for (int i = 0; i < this.todotTaulukko.length-1; i++) {
				if (this.todotTaulukko[i] == null) {
					this.todotTaulukko[i] = new TODO(i, "", "", new Time(12, 0, 0), "");
				}
			}
			this.todoMaara++;
		} else {
			System.out.println("LIKAA TODO:ITA LIS��MIST� VARTEN");
		}
	}
	
	
	/**
	 * Lisaa uuden todo:n taulukkoon
	 * @param todo Lisattava todo
	 */
	public void lisaaTODO(TODO todo) {
		todotTaulukko[todo.getId()] = todo;
		this.todoMaara++;
	}
	
	
	/**
	 * Poistaa tietyn todo:n listalta
	 * @param id Poistetavan todo:n ID
	 */
	public void poistaTODO(int id) {
		this.todotTaulukko[id] = null;
		this.todoMaara--;
	}


	/**
	 * Palauttaa todo:iden m��r�n.
	 */
	public int getTODOMaara() {
		return this.todoMaara;
	}
	
	/**
	 * Palauttaa taulukosta alkion
	 * @param id Alkion indeksi
	 */
	public TODO anna(int id) {
		return todotTaulukko[id];
	}
}
