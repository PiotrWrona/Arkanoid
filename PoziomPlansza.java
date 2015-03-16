import java.util.ArrayList;

/**
 * Klasa tworzy ró¿ne poziomy w grze (1-3)
 * 
 * @author Piotr Wrona
 * @version 1.2
 */
public class PoziomPlansza {


	static ArrayList<Przeszkoda> poziom1 = new ArrayList<Przeszkoda>();
	static ArrayList<Przeszkoda> poziom2 = new ArrayList<Przeszkoda>();
	static ArrayList<Przeszkoda> poziom3 = new ArrayList<Przeszkoda>();
	static ArrayList<Przeszkoda> poziom0 = new ArrayList<Przeszkoda>();
	
	/**
	 * Tworzy poziom 1 gry
	 * @see Przeszkoda
	 */
	static void stworzPoziom1(int xPrzeszkoda, int yPrzeszkoda, boolean czyNietykalnaPrzeszkoda, int punktyZaPrzeszkode) {		
				
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){			
			poziom1.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
		}	
		xPrzeszkoda=80;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){		
			poziom1.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode+5));
			xPrzeszkoda+=40;
		}
		xPrzeszkoda=80;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){			
			poziom1.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
		}
	}
	
	/**
	 * Tworzy poziom 2 gry
	 * @see Przeszkoda
	 */
	static void stworzPoziom2(int xPrzeszkoda, int yPrzeszkoda, boolean czyNietykalnaPrzeszkoda, int punktyZaPrzeszkode) {		
		
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){			
			poziom2.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
		}	
		xPrzeszkoda=100;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-5; liczbaPrzeszkod>0; liczbaPrzeszkod--){		
			poziom2.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
		}
		xPrzeszkoda=80;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){			
			poziom2.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, !czyNietykalnaPrzeszkoda, 0));
			xPrzeszkoda+=40;
		}
		xPrzeszkoda=100;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-5; liczbaPrzeszkod>0; liczbaPrzeszkod--){		
			poziom2.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode+5));
			xPrzeszkoda+=40;
		}
		xPrzeszkoda=80;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){		
			poziom2.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
		}
	}
	
	/**
	 * Tworzy poziom 3 gry
	 * @see Przeszkoda
	 */
	static void stworzPoziom3(int xPrzeszkoda, int yPrzeszkoda, boolean czyNietykalnaPrzeszkoda, int punktyZaPrzeszkode) {		
		
		poziom3.add(new Przeszkoda(xPrzeszkoda-40, yPrzeszkoda, !czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));	
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){			
			poziom3.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
			if (liczbaPrzeszkod % 2==0) punktyZaPrzeszkode=15;
			else punktyZaPrzeszkode=10;
		}	
		poziom3.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, !czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));	
		xPrzeszkoda=80;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){		
			poziom3.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
		}
		xPrzeszkoda=80;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/40)-4; liczbaPrzeszkod>0; liczbaPrzeszkod--){		
			poziom3.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode+5));
			xPrzeszkoda+=40;
		}
		xPrzeszkoda=120;
		yPrzeszkoda+=20;
		for(int liczbaPrzeszkod = (GUI.szerokoscPlanszy/80+1); liczbaPrzeszkod>0; liczbaPrzeszkod--){			
			poziom3.add(new Przeszkoda(xPrzeszkoda, yPrzeszkoda, !czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
			xPrzeszkoda+=40;
		}
	}
	
	/**
	 * Tworzy poziom 0 gry (testowy - nie wykorzystywany w grze)
	 * @see Przeszkoda
	 */
	static void stworzPoziom0(int xPrzeszkoda, int yPrzeszkoda, boolean czyNietykalnaPrzeszkoda, int punktyZaPrzeszkode) {		
		poziom0.add(new Przeszkoda(GUI.szerokoscPlanszy-120, yPrzeszkoda, !czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
		poziom0.add(new Przeszkoda(GUI.szerokoscPlanszy-80, yPrzeszkoda, czyNietykalnaPrzeszkoda, punktyZaPrzeszkode));
	}
	
}