package utiles.elem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import utiles.Cf;
import utiles.Ventana;

public class BotonLamina extends JButton implements ActionListener {

	private static final long serialVersionUID = -2038536197794101371L;
	private String lam;

	public BotonLamina(String lamina, String name, ImageIcon img) {
		super(name, img);
		this.lam = lamina;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Cf.mapLam.get(lam) == null)
			Cf.mapLam.put(lam, Ventana.crearLamina(lam));
		Cf.ven.setLamAct(lam);
	}
}
