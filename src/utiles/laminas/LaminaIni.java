package utiles.laminas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;

import utiles.elem.BotonLamina;

public class LaminaIni extends Lamina {

	private static final long serialVersionUID = -1144013840434754187L;
	GridBagConstraints cons = new GridBagConstraints();

	BotonLamina BTag = new BotonLamina(BotonLamina.TAGS, "Tags", new ImageIcon("images\\tag.png")),
			BRen = new BotonLamina(BotonLamina.INTERCEPCION, "Renombrar", new ImageIcon("images\\rename.png")),
			B3 = new BotonLamina(BotonLamina.PRUEBAS, "Pruebas 1", new ImageIcon("images\\pruebas.png")),
			B4 = new BotonLamina(BotonLamina.PRUEBAS, "Pruebas 2", new ImageIcon("images\\pruebas.png"));

	public LaminaIni() {

		this.setLayout(new GridBagLayout());

		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		this.add(BTag, cons);

		cons.gridx = 1;
		this.add(BRen, cons);

		cons.gridx = 0;
		cons.gridy = 1;
		this.add(B3, cons);

		cons.gridx = 1;
		this.add(B4, cons);

	}
}
