package utiles.laminas;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utiles.elem.AreaTexto;

@SuppressWarnings("serial")
public class LaminaPruebas extends Lamina {
	final int widthArea = 50, hightArea = 50;
	// Paneles
	private JPanel pgrl1 = new JPanel(), pgrl11 = new JPanel(), pgrl111 = new JPanel(), pgrl2 = new JPanel(),
			pgrl12 = new JPanel();
	//
	private JButton aceptarB = new JButton("Aceptar"), abrirB = new JButton("Abrir"), anadirEl = new JButton("Añadir");
	private AreaTexto textArea = new AreaTexto(50, 50);

	public LaminaPruebas() {

		this.setLayout(new GridLayout(1, 2));
		this.add(pgrl1); //
		this.add(pgrl2);

		pgrl1.setLayout(new GridLayout(2, 1));
		pgrl1.add(pgrl11);
		pgrl1.add(pgrl12);

		pgrl2.setLayout(new FlowLayout());

		pgrl11.add(new JLabel("Ruta: "));
		pgrl11.add(textArea);
		pgrl11.add(abrirB);
		pgrl11.add(anadirEl);

		pgrl2.add(aceptarB);

		this.textArea.reemplazarTextArea("HolaMudno linea 5", 5);

	}
}
