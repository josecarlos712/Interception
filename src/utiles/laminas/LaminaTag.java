package utiles.laminas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utiles.Cf;

@SuppressWarnings("serial")
public class LaminaTag extends Lamina {

	// Paneles
	JPanel pgrl1 = new JPanel(), pgrl11 = new JPanel(), pgrl111 = new JPanel(), pgrl2 = new JPanel(),
			pgrl12 = new JPanel();
	//
	JCheckBox albumBlanco = new JCheckBox("Album en blanco");
	JButton aceptarB = new JButton("Aceptar"), abrirB = new JButton("Abrir");
	JTextField pathField = new JTextField();

	public LaminaTag() {

		this.setLayout(new GridLayout(1, 2));
		this.add(pgrl1); //
		this.add(pgrl2);

		pgrl1.setLayout(new GridLayout(2, 1));
		pgrl1.add(pgrl11);
		pgrl1.add(pgrl12);

		pgrl2.setLayout(new FlowLayout());

		pgrl11.add(new JLabel("Ruta: "));
		pgrl11.add(pathField);
		pgrl11.add(abrirB);

		pgrl12.add(albumBlanco);

		pgrl2.add(aceptarB);

		// AcctionListeners
		albumBlanco.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				Cf.blankAlbum = albumBlanco.isSelected();
			}
		});

		aceptarB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		abrirB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
