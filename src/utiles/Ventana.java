package utiles;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import utiles.laminas.Lamina;
import utiles.laminas.LaminaIni;
import utiles.laminas.LaminaInter;
import utiles.laminas.LaminaPruebas;
import utiles.laminas.LaminaPruebas2;
import utiles.laminas.LaminaRenombrar;
import utiles.laminas.LaminaTag;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;

	private String title = "Song Interception";

	public Ventana(int x, int y, int width, int height) {
		// Instanciacion de laminas
		Cf.mapLam.put(Lamina.INICIAL, new LaminaIni());
		Cf.mapLam.put(Lamina.INTERCEPCION, null);
		Cf.mapLam.put(Lamina.TAG, null);
		Cf.mapLam.put(Lamina.PRUEBAS, null);
		Cf.mapLam.put(Lamina.PRUEBAS2, null);
		Cf.mapLam.put(Lamina.RENOMBRAR, null);
		crearMenu();
		Cf.lamAct = Cf.mapLam.get(Lamina.INICIAL);
		// Creacion de la ventana
		this.setBounds(x, y, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.title);
		this.add(Cf.lamAct);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}

	public Ventana(Rectangle bounds) {
		this(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public Ventana() {
		this(0, 200, 1200, 500);
	}

	public void crearMenu() {
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Programas");
		menu.setMnemonic(KeyEvent.VK_M);
		menu.getAccessibleContext().setAccessibleDescription("Menu para elegir entre los programas");
		menuBar.add(menu);

		// Añadir menu del programa de Tags
		menuItem = new JMenuItem("Tags", KeyEvent.VK_T); // Crea una entrada en el menu desplegable
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK)); // Crea un acceso rapido
																								// de teclado
		menuItem.getAccessibleContext().setAccessibleDescription("Programa para cambiar las tags");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		menu.add(menuItem);
		// Añadir menu del programa de Pruebas
		menuItem = new JMenuItem("Pruebas", KeyEvent.VK_P); // Crea una entrada en el menu desplegable
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK)); // Crea un acceso rapido
																								// de teclado
		menuItem.getAccessibleContext().setAccessibleDescription("Programa para hacer pruebas");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Cf.ven.setLamAct(Lamina.PRUEBAS);
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Inicio", KeyEvent.VK_I); // Crea una entrada en el menu desplegable
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK)); // Crea un acceso rapido
																								// de teclado
		menuItem.getAccessibleContext().setAccessibleDescription("Volver al inicio");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Cf.ven.setLamAct(Lamina.INICIAL);
			}
		});
		menu.add(menuItem);
	}

	public void actualizarVentana() {
		this.repaint();
		Rectangle b = this.getBounds();
		this.setBounds(b.x, b.y, b.height - 1, b.width - 1);
		this.setBounds(b);
	}

	public void setLamAct(int lam) {
		this.getContentPane().remove(Cf.lamAct);
		if (Cf.mapLam.get(lam) == null)
			Cf.mapLam.put(lam, Ventana.crearLamina(lam));
		Cf.lamAct = Cf.mapLam.get(lam);
		this.add(Cf.lamAct);
		this.actualizarVentana();
	}

	public static JPanel crearLamina(int lam2) {
		// TODO Auto-generated method stub
		switch (lam2) {
		case Lamina.INICIAL:
			return new LaminaIni();
		case Lamina.TAG:
			return new LaminaTag();
		case Lamina.INTERCEPCION:
			return new LaminaInter();
		case Lamina.PRUEBAS:
			return new LaminaPruebas();
		case Lamina.PRUEBAS2:
			return new LaminaPruebas2();
		case Lamina.RENOMBRAR:
			return new LaminaRenombrar();
		default:
			return null;
		}
	}
}
