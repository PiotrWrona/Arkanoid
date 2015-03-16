import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za mechanik� gry
 * 
 * @author Piotr Wrona
 * @version 1.3
 */
public class Gra extends Canvas implements Runnable, KeyListener{

	/**
	 * @param korekta korekta wielko�ci planszy
	 */
	private final static int korekta = 16;
	
	/**
	 * @param xPileczka warto�� na osi OX, gdzie znajduje si� pi�eczka
	 * @param yPileczka warto�� na osi OY, gdzie znajduje si� pi�eczka
	 * @param pileczkaPionowo kierunek ruchu pi�eczki (true=g�ra; false=d�)
	 * @param pileczkaPoziomo kierunek ruchu pi�eczki (true=prawo; false=lewo)
	 * @param ruchPileczkiX ruch (liczba px) na osi OX na jeden cykl gry
	 * @param ruchPileczkiY ruch (liczba px) na osi OY na jeden cykl gry
	 */
	public int xPileczka, yPileczka;
	private boolean pileczkaPionowo, pileczkaPoziomo;
	private int ruchPileczkiX, ruchPileczkiY;
	/**
	 * @param PlayerPaletkaX okresla warto�� na osi OX, gdzie znajduje si� paletka gracza (OX: d� planszy)
	 * @param PlayerPaletkaY okresla warto�� na osi OY, gdzie znajduje si� paletka gracza (OY: �rodek planszy)
	 * @param liczbaPunktow liczba punkt�w zdobyta przez gracza w ca�ej grze (sumowana na ka�dym poziomie gry)
	 */
	public int PlayerPaletkaX;
	public final int PlayerPaletkaY = GUI.wysokoscPlanszy-30;
	public int liczbaPunktow;
	
	/**
	 * @param koniecGry zmienna ko�cz�ca gr�
	 * @param poziom poziom, od kt�rego zaczyna si� gra
	 * @param poziomTrwa okre�la, czy dany poziom gry dobieg� ko�ca
	 */
	private boolean koniecGry;
	public int poziom = 1;
	private boolean poziomTrwa;
	/**
	 * @param liczbaZyc liczba �y� (mo�liwych b��d�w) zanim zako�czy si� gra
	 * @param start warto�c okre�laj�ca start gry (start ka�dego z poziom�w dzi�ki naci�ni�ciu spacji)
	 * @param szybkoscGryNaStarcie szybko�� gry na starcie (warto�c pocz�tkowa)
	 * @param szybkoscGry szybko�� gry na starcie, kt�ra ro�nie z biegiem gry
	 * @param licznikPredkosci warto�� okreslaj�ca cykle gry i pomagaj�ca przelicza� szybko�c gry
	 */
	public int liczbaZyc = 3;
	private boolean start;
	private int szybkoscGryNaStarcie = 15;
	private int szybkoscGry;
	private int licznikPredkosci = 1;
	
	
		

	public void run() {
		
		try {
			click(110,80);
		} catch (AWTException e1) {
			System.out.println("Autoklik nie zadzialal");
			e1.printStackTrace();
		}
		
		while(!koniecGry){
			
			if(!poziomTrwa)poziomGryiStart();		
	
			if (poziom==3) { 
        		odbicieOdPrzeszkody(PoziomPlansza.poziom3);
        		sprawdzenieKoncaPlanszy(PoziomPlansza.poziom3);       		
			}
			if (poziom==2) { 
        		odbicieOdPrzeszkody(PoziomPlansza.poziom2);
        		sprawdzenieKoncaPlanszy(PoziomPlansza.poziom2);
			}
			if (poziom==1) {
        		odbicieOdPrzeszkody(PoziomPlansza.poziom1);
        		sprawdzenieKoncaPlanszy(PoziomPlansza.poziom1);
			}
			if (poziom==0) { 
        		odbicieOdPrzeszkody(PoziomPlansza.poziom0);
        		sprawdzenieKoncaPlanszy(PoziomPlansza.poziom0);       		
			}		
							
			odbicieOdPaletki();
			
			ruchPileczki();
			
			nieObroniono();
			
			licznikCzasuiPredkosciGry();
			
			repaint();
			
		}		
	}
	
		
	/**
	 * okre�lenie poziomu gry i start
	 */
	private void poziomGryiStart() {
		if (poziom==0) PoziomPlansza.stworzPoziom0(80,100,false,10);
		if (poziom==1) PoziomPlansza.stworzPoziom1(80,100,false,10);
		if (poziom==2) PoziomPlansza.stworzPoziom2(80,60,false,10);	
		if (poziom==3) PoziomPlansza.stworzPoziom3(80,100,false,10);	
		
		
		PlayerPaletkaX = (GUI.szerokoscPlanszy/2)-(GUI.szerokoscPaletki/2);
		xPileczka = PlayerPaletkaX+(GUI.szerokoscPaletki/2)-10;
		yPileczka = PlayerPaletkaY-20;
		ruchPileczkiX=2;
		ruchPileczkiY=2;
		pileczkaPionowo = true;
		pileczkaPoziomo = true;
		szybkoscGry = szybkoscGryNaStarcie;
		repaint();
		while(!start){} //---- start = "spacja"
		poziomTrwa = true;
	}

	/**
	 * odbicie od przeszkody i sprawdzenie konca planszy
	 * @param poziomX poziom planszy, kt�r� nale�y sprawdzi�
	 */
	private void odbicieOdPrzeszkody(ArrayList<Przeszkoda> poziomX){
		for (int i=0; i < poziomX.size(); i++){
			int x = poziomX.get(i).xPrzeszkoda;
			int y = poziomX.get(i).yPrzeszkoda;
					
			if(pileczkaPionowo &&  ( xPileczka+10 >= x && xPileczka+10 <= x+40 ) && ( yPileczka <= y+22 && yPileczka >= y) ){
				pileczkaPionowo=false;	
				liczbaPunktow += poziomX.get(i).punktyZaPrzeszkode;
				if (!poziomX.get(i).czyNietykalnaPrzeszkoda) poziomX.remove(i);
			}
			else if(!pileczkaPionowo &&  ( xPileczka+10 >= x && xPileczka+10 <= x+40 ) && ( yPileczka+18 >= y && yPileczka <= y+20) ){
				pileczkaPionowo=true;					
				liczbaPunktow += poziomX.get(i).punktyZaPrzeszkode;
				if (!poziomX.get(i).czyNietykalnaPrzeszkoda) poziomX.remove(i);
			}
			else if(pileczkaPoziomo && ( xPileczka+18 >= x && xPileczka+20 <= x+40 ) && ( yPileczka+10 >= y && yPileczka+10 <= y+20) ){
				pileczkaPoziomo=false;					
				liczbaPunktow += poziomX.get(i).punktyZaPrzeszkode;
				if (!poziomX.get(i).czyNietykalnaPrzeszkoda) poziomX.remove(i);
			}
			else if(!pileczkaPoziomo && ( xPileczka <= x+40 && xPileczka >= x ) && ( yPileczka+10 >= y && yPileczka+10 <= y+20) ){
				pileczkaPoziomo=true;					
				liczbaPunktow += poziomX.get(i).punktyZaPrzeszkode;
				if (!poziomX.get(i).czyNietykalnaPrzeszkoda) poziomX.remove(i);
			}			
		}	
	}
	
	/**
	 * sprawdzenie, czy plansza zosta�a uko�czona
	 * @param poziomX poziom planszy, kt�r� nale�y sprawdzi�
	 */
	private void sprawdzenieKoncaPlanszy(ArrayList<Przeszkoda> poziomX) {
		if (poziomX.size()==0 || czyKoniec(poziomX)){
			poziomTrwa = false;
			poziom++;
			if (poziom==4) koniecGry = true;
		}		
	}
	/**
	 * sprawdzenie, czy plansza zosta�a uko�czona
	 * @param poziomX poziom planszy, kt�r� nale�y sprawdzi�
	 */
	private boolean czyKoniec(ArrayList<Przeszkoda> poziomX) {
		for (int i=0; i < poziomX.size(); i++){
			if (!poziomX.get(i).czyNietykalnaPrzeszkoda){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * odbicie pi�eczki od paletki
	 */
	private void odbicieOdPaletki() {	
		if (yPileczka >= PlayerPaletkaY-20 && xPileczka >= PlayerPaletkaX && xPileczka <= PlayerPaletkaX+GUI.szerokoscPaletki){				
			pileczkaPionowo=true;
			
		}
		if ( (pileczkaPoziomo && (yPileczka >= PlayerPaletkaY-20 && xPileczka >= PlayerPaletkaX && xPileczka <= PlayerPaletkaX+30))  ||
		     (!pileczkaPoziomo && (yPileczka >= PlayerPaletkaY-20 && xPileczka >= PlayerPaletkaX+GUI.szerokoscPaletki-30 && xPileczka <= PlayerPaletkaX+GUI.szerokoscPaletki) )  ){	
			//System.out.println("SKRAJNE A");
			
			if(ruchPileczkiX==3 && ruchPileczkiY==1){
				//System.out.println(ruchPileczkiX+ "..a1.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
				ruchPileczkiX=2;
				ruchPileczkiY=2;
			}
			else if(ruchPileczkiX==2 && ruchPileczkiY==2){
				//System.out.println(ruchPileczkiX+ "..a2.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
				ruchPileczkiX=1;
				ruchPileczkiY=3;
			}
			else if(ruchPileczkiX==1 && ruchPileczkiY==3){
				//System.out.println(ruchPileczkiX+ "..a3.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
				ruchPileczkiX=0;
				ruchPileczkiY=3;
			}
			else if(ruchPileczkiX==0 && ruchPileczkiY==3){
				//System.out.println(ruchPileczkiX+ "..a4.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
				ruchPileczkiX=1;
				ruchPileczkiY=3;
				if (yPileczka >= PlayerPaletkaY-20 && xPileczka >= PlayerPaletkaX && xPileczka <= PlayerPaletkaX+40) pileczkaPoziomo = false;
				else pileczkaPoziomo = true;
			}
		}	
		
		if ( (!pileczkaPoziomo && (yPileczka >= PlayerPaletkaY-20 && xPileczka >= PlayerPaletkaX && xPileczka <= PlayerPaletkaX+30))  ||
			 (pileczkaPoziomo && (yPileczka >= PlayerPaletkaY-20 && xPileczka >= PlayerPaletkaX+GUI.szerokoscPaletki-30 && xPileczka <= PlayerPaletkaX+GUI.szerokoscPaletki) )  ){	
				System.out.println("SKRAJNE B");
				
				if(ruchPileczkiX==3 && ruchPileczkiY==1){
					//System.out.println(ruchPileczkiX+ "..b1.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
					ruchPileczkiX=3;
					ruchPileczkiY=1;
				}
				else if(ruchPileczkiX==2 && ruchPileczkiY==2){
					//System.out.println(ruchPileczkiX+ "..b2.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
					ruchPileczkiX=3;
					ruchPileczkiY=1;
				}
				else if(ruchPileczkiX==1 && ruchPileczkiY==3){
					//System.out.println(ruchPileczkiX+ "..b3.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
					ruchPileczkiX=2;
					ruchPileczkiY=2;
				}
				else if(ruchPileczkiX==0 && ruchPileczkiY==3){
					//System.out.println(ruchPileczkiX+ "..b4.."+ ruchPileczkiY + " _" + pileczkaPoziomo);
					ruchPileczkiX=1;
					ruchPileczkiY=3;
				}
		}
		
		
	}
	
	/**
	 * ruch pileczki po planszy
	 */
	private void ruchPileczki() {
		if (pileczkaPionowo) yPileczka -= ruchPileczkiY;
		if (!pileczkaPionowo) yPileczka += ruchPileczkiY;
		if (pileczkaPoziomo) xPileczka += ruchPileczkiX;
		if (!pileczkaPoziomo) xPileczka -= ruchPileczkiX;
		
		if (xPileczka <= 0) pileczkaPoziomo=true;
		if (xPileczka >= GUI.szerokoscPlanszy-korekta) pileczkaPoziomo=false;
		if (yPileczka <= 0) pileczkaPionowo=false;
	}
	
	/**
	 * brak odbicia pileczki (pileczka przeszla dolna linie)
	 */
	private void nieObroniono() {
		if (yPileczka >= GUI.wysokoscPlanszy-korekta){
			
			liczbaZyc--;
			szybkoscGry = szybkoscGryNaStarcie;
			xPileczka = PlayerPaletkaX+(GUI.szerokoscPaletki/2)-10;
			yPileczka = PlayerPaletkaY-20;
			pileczkaPionowo = true;
			pileczkaPoziomo = true;
			ruchPileczkiX=2;
			ruchPileczkiY=2;
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (liczbaZyc<1) koniecGry=true;
			while(!start){}
		}
	}
	
	/**
	 * liczniki czasu gry
	 */
	private void licznikCzasuiPredkosciGry() {
		licznikPredkosci++;
		if(licznikPredkosci % 5000 == 0){
			szybkoscGry--;
			if (szybkoscGry < 7) szybkoscGry=7;
		}
		try {
			Thread.sleep(szybkoscGry);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	
	
	/**
	 * Obs�uga Windows'owych klawiszy w grze (strza�ek oraz spacji)
	 */
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == 37){
			PlayerPaletkaX -= 10;
			if (PlayerPaletkaX <= 0) PlayerPaletkaX=0;
		}
		if (key.getKeyCode() == 39){
			PlayerPaletkaX += 10;
			if (PlayerPaletkaX >= GUI.szerokoscPlanszy-GUI.szerokoscPaletki) PlayerPaletkaX=GUI.szerokoscPlanszy-GUI.szerokoscPaletki;
		}
		if (key.getKeyCode() == 32) start=true;		
	}
	
	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == 32) start=false;
	}
	
	public void keyTyped(KeyEvent key) {
	}
	
	/**
	 * Klikni�cie na plansz� gry
	 * @param x miejsce klikni�cia na osi OX
	 * @param y miejsce klikni�cia na osi OY
	 */
	public static void click(int x, int y) throws AWTException{
	    Robot bot = new Robot();
	    bot.mouseMove(x, y);    
	    bot.mousePress(InputEvent.BUTTON1_MASK);
	    bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
