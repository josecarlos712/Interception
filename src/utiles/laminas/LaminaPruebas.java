package utiles.laminas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utiles.elem.AreaTexto;

@SuppressWarnings("serial")
public class LaminaPruebas extends Lamina {
	//final int widthArea = 50, heightArea = 50;
	int cont = 0;
	// Paneles
	private JPanel pgrl1 = new JPanel(), pgrl11 = new JPanel(), pgrl2 = new JPanel(), pgrl12 = new JPanel();
	//
	private JButton aceptarB = new JButton("Aceptar");
	private AreaTexto textArea = new AreaTexto();

	public LaminaPruebas() {

		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(100, 500));

		this.setLayout(new GridLayout(1, 2));
		this.add(pgrl1); //
		this.add(pgrl2);

		pgrl1.setLayout(new GridLayout(2, 1));
		pgrl1.add(pgrl11);
		pgrl1.add(pgrl12);

		pgrl2.setLayout(new FlowLayout());

		pgrl11.setLayout(new BorderLayout());
		pgrl11.add(new JLabel("Ruta: "), BorderLayout.CENTER);
		pgrl11.add(textArea, BorderLayout.CENTER);

		pgrl2.add(aceptarB);

		aceptarB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.reemplazarLinea(cont + ". Linea " + (cont + 1) + "\n", cont);
				cont += 20;
			}

		});
	}
}
