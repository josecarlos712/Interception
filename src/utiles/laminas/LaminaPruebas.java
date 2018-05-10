package utiles.laminas;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import utiles.elem.InterObjects;

@SuppressWarnings("serial")
public class LaminaPruebas extends JPanel {

	List<Component> io = new ArrayList<Component>();
	GridBagConstraints cons = new GridBagConstraints();
	public InterObjects ios;
	public JPanel pa = new JPanel();

	public LaminaPruebas() {

	}
}