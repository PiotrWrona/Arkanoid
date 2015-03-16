import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Tworzy graficzny interfejs gry
 * 
 * @author Piotr Wrona
 * @version 1.1
 */


public class GUI extends Gra{

	
	private Image zdjecieObrazu;
	private Graphics grafikaGry;
	
	public final static int szerokoscPlanszy = 560;
	public final static int wysokoscPlanszy = 500;	
	public final static int szerokoscPaletki = 120;
	
	/**
	 * Konstruuje ekran gry
	 */	
	public GUI(){
		
		JFrame okno = new JFrame("Pingiel");
		setBounds(0,0,szerokoscPlanszy,wysokoscPlanszy);
		addKeyListener(this);
		
		JPanel panel = (JPanel) okno.getContentPane();
		panel.setPreferredSize(new Dimension(szerokoscPlanszy,wysokoscPlanszy));
		panel.setLayout(null);
		panel.add(this);
				
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setResizable(false);
		okno.setBounds(100,20,szerokoscPlanszy+6,wysokoscPlanszy+25);
		okno.setVisible(true);	
		
		zdjecieObrazu = createImage (szerokoscPlanszy,wysokoscPlanszy);
		grafikaGry = zdjecieObrazu.getGraphics();
	}	

	/**
	 * Rysyje elementy na ekranie
	 */
	public void paint(Graphics grafika){		
		
		grafikaGry.setColor(Color.white);
		grafikaGry.fillRect(0,0,szerokoscPlanszy,wysokoscPlanszy);
		
		grafikaGry.setColor(Color.black);
		grafikaGry.fillOval(xPileczka,yPileczka,20,20);
		
		grafikaGry.fillRect(PlayerPaletkaX,PlayerPaletkaY,szerokoscPaletki,20);
		grafikaGry.setColor(Color.DARK_GRAY);
		grafikaGry.fillRect(PlayerPaletkaX,PlayerPaletkaY,30,20);
		grafikaGry.fillRect(PlayerPaletkaX+90,PlayerPaletkaY,szerokoscPaletki-90,20);

		grafikaGry.setColor(Color.black);
		grafikaGry.drawString("Punkty: " + liczbaPunktow, 5, 15);
		if (liczbaZyc==0) grafikaGry.setColor(Color.red);
		grafikaGry.drawString("Zycia: " + liczbaZyc, 5, 27);
		
		grafikaGry.setColor(Color.yellow);
		
		if (poziom==1) rysujPlansze(PoziomPlansza.poziom1);
		if (poziom==2) rysujPlansze(PoziomPlansza.poziom2);
		if (poziom==3) rysujPlansze(PoziomPlansza.poziom3);
		if (poziom==0) rysujPlansze(PoziomPlansza.poziom0);
		grafika.drawImage(zdjecieObrazu,0,0,this);
		
	}
	
	/**
	 * Rysuje przeszkody poziomu X
	 * @param poziomX poziom gry. w wersji 1.2 mo¿liwe poziomy 1-3 oraz 0 (testowy)
	 */
	private void rysujPlansze(ArrayList<Przeszkoda> poziomX) {
		for (int i=0; i < poziomX.size(); i++){
		grafikaGry.setColor(Color.yellow);
		if(poziomX.get(i).punktyZaPrzeszkode == 15){
			grafikaGry.setColor(Color.green);
		}
		if (poziomX.get(i).czyNietykalnaPrzeszkoda){
			grafikaGry.setColor(Color.BLACK);
		}			
		int x = poziomX.get(i).xPrzeszkoda;
		int y = poziomX.get(i).yPrzeszkoda;
		grafikaGry.fillOval(x,y,40,20);
		}	
	}


	public void update(Graphics grafika){
		paint(grafika);
	}
	
	
	public static void main(String[] args) {
		GUI plansza = new GUI();	
		plansza.run();
	}
	
	
}