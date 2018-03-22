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
import utiles.laminas.LaminaInter;
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
		Cf.mapLam.put("intercepcion", new LaminaInter());
		Cf.mapLam.put("tag", new LaminaTag());
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
		this.getContentPane().add(lam);
		this.getContentPane().remove(Cf.lamAct);
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

		// a group of JMenuItems
		menuItem = new JMenuItem("Tags", KeyEvent.VK_T); // Crea una entrada en el menu desplegable
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK)); // Crea un acceso rapido
																								// de teclado
		menuItem.getAccessibleContext().setAccessibleDescription("Programa para cambiar las tags");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (Cf.mapLam.get("tag") != null)
					Cf.ven.setLamAct(Cf.mapLam.get("tag"));
			}
		});
		menu.add(menuItem);

		/*
		 * menuItem = new JMenuItem("Both text and icon", new
		 * ImageIcon("images/middle.gif")); menuItem.setMnemonic(KeyEvent.VK_B);
		 * menu.add(menuItem);
		 * 
		 * menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
		 * menuItem.setMnemonic(KeyEvent.VK_D); menu.add(menuItem);
		 * 
		 * // a group of radio button menu items menu.addSeparator(); ButtonGroup group
		 * = new ButtonGroup(); rbMenuItem = new
		 * JRadioButtonMenuItem("A radio button menu item");
		 * rbMenuItem.setSelected(true); rbMenuItem.setMnemonic(KeyEvent.VK_R);
		 * group.add(rbMenuItem); menu.add(rbMenuItem);
		 * 
		 * rbMenuItem = new JRadioButtonMenuItem("Another one");
		 * rbMenuItem.setMnemonic(KeyEvent.VK_O); group.add(rbMenuItem);
		 * menu.add(rbMenuItem);
		 * 
		 * // a group of check box menu items menu.addSeparator(); cbMenuItem = new
		 * JCheckBoxMenuItem("A check box menu item");
		 * cbMenuItem.setMnemonic(KeyEvent.VK_C); menu.add(cbMenuItem);
		 * 
		 * cbMenuItem = new JCheckBoxMenuItem("Another one");
		 * cbMenuItem.setMnemonic(KeyEvent.VK_H); menu.add(cbMenuItem);
		 * 
		 * // a submenu menu.addSeparator(); submenu = new JMenu("A submenu");
		 * submenu.setMnemonic(KeyEvent.VK_S);
		 * 
		 * menuItem = new JMenuItem("An item in the submenu");
		 * menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
		 * ActionEvent.ALT_MASK)); submenu.add(menuItem);
		 * 
		 * menuItem = new JMenuItem("Another item"); submenu.add(menuItem);
		 * menu.add(submenu);
		 * 
		 * // Build second menu in the menu bar. menu = new JMenu("Another Menu");
		 * menu.setMnemonic(KeyEvent.VK_N);
		 * menu.getAccessibleContext().setAccessibleDescription("This menu does nothing"
		 * ); menuBar.add(menu);
		 */
	}

	public void actualizarVentana() {
		this.repaint();
		Rectangle b = this.getBounds();
		this.setBounds(b.x, b.y, b.height - 1, b.width - 1);
		this.setBounds(b);
	}
}
