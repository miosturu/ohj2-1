package HarjoitusTyo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;

/**
 * todotTaulukko-luokka, jonka tarkoituksena on tiet‰‰, miss‰ mik‰kin todo on, osata tehd‰ ja poistaa todon.
 * Ei itse tied‰, kenelle mik‰kin todo kuuluu.
 */
public class TODOt {
	private int MAX_TODOITA = 50;
	private TODO[] todotTaulukko = new TODO[MAX_TODOITA];
	private int todoMaara = 0;
	
	
	public static void main(String[] args) {
		TODOt todot = new TODOt();
		try {
			todot.lueTiedostosta("todo.dat");
		} catch (IOException e) { /**/ }
		
		TODO t1 = new TODO();
		t1.luoValmis(5);
		
		todot.lisaaTODO(t1);
		
		try {
			todot.tallenna("todo.dat");
		} catch (IOException e) { /**/ }
	}
	
	
	/**
	 * Todo:t luokan alustusmetodi. Ei tarvitse tehd‰ mit‰‰n, koska arvot asetetaan aikaisemmin.
	 * @example
	 * <pre name="test">
	 * TODOt todot = new TODOt();
	 * TODO t0 = new TODO();
	 * t0.luoValmis(0);
	 * todot.lisaaTODO(t0);
	 * todot.anna(0) === t0;
	 * todot.getTODOMaara() === 1;
	 * todot.poistaTODO(0);
	 * todot.getTODOMaara() === 0;
	 * todot.anna(0) === null;
	 * </pre>
	 */
	public TODOt() {
		// Ei tarvitse tehd‰ mit‰‰n, muuttujat m‰‰ritelty aikaisemmin.
	}
	
	
	/**
	 * Lis‰‰ uuden todo:n ensimm‰iseen vapaaseen indeksiin 
	 */
	@SuppressWarnings("deprecation")
	public void lisaaGenerinenTODO() {
		if (this.todoMaara > this.MAX_TODOITA) {
			for (int i = 0; i < this.todotTaulukko.length-1; i++) {
				if (this.todotTaulukko[i] == null) {
					this.todotTaulukko[i] = new TODO(i, "", "", new Time(12, 0, 0), "");
				}
			}
			this.todoMaara++;
		} else {
			System.out.println("LIKAA TODO:ITA LISƒƒMISTƒ VARTEN");
		}
	}
	
	
	/**
	 * Lukee tiedostosta todo:iden tiedot
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void lueTiedostosta(String tiedosto) throws FileNotFoundException, IOException {
		try (BufferedReader lukija = new BufferedReader(new FileReader(tiedosto))) {
			String rivi;
			while ((rivi = lukija.readLine()) != null) {
				//System.out.println(rivi);
				TODO todo = new TODO();
				todo= new TODO().parse(rivi);
				lisaaTODO(todo);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("Ongelma TODOt.java:" + ex);
		} catch (IOException ex) {
			System.out.println("Ongelma TODOt.java:" + ex);
		}
	}
	
	
	/**
	 * Tallentaa todo:n tiedot tiedostoon
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
			for (TODO todo : todotTaulukko) {
				if (todo != null) {
					kirjuri.println(todo.toString());	
				}
			}
		}  catch (IOException e) {
			System.out.println("Ongelma: " + e);
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
	 * Palauttaa todo:iden m‰‰r‰n.
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
	
	
	/**
	 * Palauttaa max todo m‰‰r‰n
	 */
	public int getMaxTODOt() {
		return this.MAX_TODOITA;
	}
}
