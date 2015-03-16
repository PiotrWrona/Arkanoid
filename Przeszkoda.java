/**
 * Klasa tworzy przeszkody, które pojawiaj¹ siê na ka¿dym poziomie gry
 * 
 * @author Piotr Wrona
 * @version 1.0
 *
 */
public class Przeszkoda {
	
	public int xPrzeszkoda, yPrzeszkoda;
	public boolean czyNietykalnaPrzeszkoda;
	public int punktyZaPrzeszkode;
	
	/**
	 * Konstruktot tworzy obiekt Przeszkoda, w ktora nalezy udezyc pileczka, aby uzyskac punkty
	 * @param xPrzeszkoda miejsce pojawienia sie przeszkody - os OX
	 * @param yPrzeszkoda miejsce pojawienia sie przeszkody - os OY
	 * @param czyNietykalnaPrzeszkoda informacja czy przeszkode da sie zniszczyc i dostac za nia punkty
	 * @param punktyZaPrzeszkode liczba punktow za dana przeszkode
	 */
	public Przeszkoda(int xPrzeszkoda, int yPrzeszkoda, boolean czyNietykalnaPrzeszkoda, int punktyZaPrzeszkode){
		this.xPrzeszkoda = xPrzeszkoda;
		this.yPrzeszkoda = yPrzeszkoda;
		this.czyNietykalnaPrzeszkoda = czyNietykalnaPrzeszkoda;
		this.punktyZaPrzeszkode = punktyZaPrzeszkode;
	}
	
}
