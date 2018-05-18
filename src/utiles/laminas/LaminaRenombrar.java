package utiles.laminas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import utiles.Metodos;
import utiles.elem.AreaTexto;

public class LaminaRenombrar extends JPanel {

	private static final long serialVersionUID = 3412494987256176885L;
	private Box boxRadio = Box.createVerticalBox();
	private ButtonGroup groupRadio = new ButtonGroup(), groupB = new ButtonGroup();
	private JPanel radioPanel = new JPanel();
	private JScrollPane jsp = new JScrollPane(radioPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private GridBagConstraints cons = new GridBagConstraints();
	private AreaTexto textArea = new AreaTexto(500);
	private JButton BVis = new JButton("Visualizar"), BApl = new JButton("Aplicar"), BAbrir = new JButton("Abrir"),
			BAñadir = new JButton("Salir");
	private List<File> files = new ArrayList<File>();
	private List<JRadioButton> radioButtons = new ArrayList<JRadioButton>();

	public LaminaRenombrar() {
		this.setLayout(new GridBagLayout());

		/*for (int i = 0; i < 50; i++) { 
			radioButtons.add(new JRadioButton("Valor " + i));
		}*/
		radioButtons.add(new JRadioButton("Nombre(i)"));
		radioButtons.add(new JRadioButton("Nombre (i)"));
		radioButtons.add(new JRadioButton("Nombre i"));
		radioButtons.add(new JRadioButton("Nombrei"));

		groupB.add(BVis);
		groupB.add(BApl);
		groupB.add(BAbrir);
		groupB.add(BAñadir);

		for (JRadioButton comp : radioButtons) {
			radioPanel.add(comp);
			groupRadio.add(comp);
			boxRadio.add(comp);
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
		this.add(BAñadir, cons);

		BAbrir.addActionListener(new ActionListener() { // Limpia la lista existente y carga los MP3		
			@Override
			public void actionPerformed(ActionEvent e) {
				List<File> f = Metodos.abrirArchivo(radioPanel);
				files = f;
				textArea.clean();
				for (File fp : f) {
					textArea.añadirLinea(fp.getName());
				}
			}
		});

		BAñadir.addActionListener(new ActionListener() { // Limpia la lista existente y carga los MP3		
			@Override
			public void actionPerformed(ActionEvent e) {
				List<File> f = Metodos.abrirArchivo(radioPanel);

				for (File fp : f) {
					files.add(fp);
					textArea.añadirLinea(fp.getName());
				}
			}
		});
	}
}
