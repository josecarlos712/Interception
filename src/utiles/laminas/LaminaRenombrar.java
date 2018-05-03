package utiles.laminas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import utiles.elem.AreaTexto;

public class LaminaRenombrar extends Lamina {

	private static final long serialVersionUID = 3412494987256176885L;
	private ButtonGroup groupRadio = new ButtonGroup(), groupB = new ButtonGroup();
	private JScrollPane jsp = new JScrollPane();
	private GridBagConstraints cons = new GridBagConstraints();
	private AreaTexto textArea = new AreaTexto();
	private JButton BVis = new JButton("Visualizar"), BApl = new JButton("Aplicar"), BAbrir = new JButton("Abrir"),
			BSalir = new JButton("Salir");
	private JRadioButton[] radioButtons = new JRadioButton[100];

	public LaminaRenombrar() {
		this.setLayout(new GridBagLayout());

		for (int i = 0; i < 10; i++) {
			radioButtons[i] = new JRadioButton("Valor " + i);
		}

		groupB.add(BVis);
		groupB.add(BApl);
		groupB.add(BAbrir);
		groupB.add(BSalir);

		for (JRadioButton comp : radioButtons) {
			if (comp != null) {
				jsp.add(comp);
				groupRadio.add(comp);
			}
		}

		textArea.setEditable(false);

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 3;
		cons.gridwidth = 1;
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		cons.fill = GridBagConstraints.BOTH;
		this.add(textArea, cons);

		cons.gridx = 1;
		cons.gridwidth = 2;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.BOTH;
		this.add(jsp, cons);

		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.weightx = 0.5;
		cons.weighty = 0.0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.CENTER;
		this.add(BVis, cons);

		cons.gridx = 2;
		this.add(BApl, cons);

		cons.gridx = 1;
		cons.gridy = 2;
		this.add(BAbrir, cons);

		cons.gridx = 2;
		this.add(BSalir, cons);

		BAbrir.addActionListener(new ActionListener() { // Limpia la lista existente y carga los MP3		
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
