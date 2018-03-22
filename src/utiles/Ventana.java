package utiles;

import java.awt.Rectangle;

import javax.swing.JFrame;

import utiles.laminas.Lamina;
import utiles.laminas.LaminaIni;
import utiles.laminas.LaminaInter;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private String title = "Song Interception";

	public Ventana(int x, int y, int width, int height) {
		// Instanciacion de laminas
		Cf.mapLam.put("inicial", new LaminaIni());
		Cf.mapLam.put("intercepcion", new LaminaInter());
		// Creacion de la ventana
		this.setBounds(x, y, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.title);
		Cf.lamAct = Cf.mapLam.get("inicial");
		this.add(Cf.lamAct);
		this.setVisible(true);
	}

	public Ventana(Rectangle bounds) {
		this(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public Ventana() {
		this(0, 0, 1200, 500);
	}

	public static void changeActLam(Lamina lam) {
		Cf.ven.getContentPane().remove(Cf.lamAct);
		Cf.lamAct = lam;
		Cf.ven.getContentPane().add(Cf.lamAct);
		Cf.ven.repaint();
	}
}
