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
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import utiles.laminas.Lamina;
import utiles.laminas.LaminaIni;
import utiles.laminas.LaminaPruebas;
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
		Cf.mapLam.put("inicial", new LaminaIni());
		Cf.mapLam.put("intercepcion", null);
		Cf.mapLam.put("tag", null);
		Cf.mapLam.put("pruebas", null);
		crearMenu();
		// Creacion de la ventana
		this.setBounds(x, y, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.title);
		Cf.lamAct = Cf.mapLam.get("inicial");
		this.add(Cf.lamAct);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}

	public Ventana(Rectangle bounds) {
		this(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	public Ventana() {
		this(0, 0, 1200, 500);
	}

	public void setLamAct(Lamina lam) {
		this.getContentPane().remove(Cf.lamAct);
		this.getContentPane().add(lam);
		Cf.lamAct = lam;
		this.actualizarVentana();
	}

	public void crearMenu() {
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Programas");
		menu.setMnemonic(KeyEvent.VK_P);
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
				// TODO Auto-generated method stub
				if (Cf.mapLam.get("tag") == null)
					Cf.mapLam.put("tag", new LaminaTag());
				Cf.ven.setLamAct(Cf.mapLam.get("tag"));

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
				// TODO Auto-generated method stub
				if (Cf.mapLam.get("pruebas") == null)
					Cf.mapLam.put("pruebas", new LaminaPruebas());
				Cf.ven.setLamAct(Cf.mapLam.get("pruebas"));
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
}
