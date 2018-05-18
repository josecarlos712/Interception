package utiles.laminas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utiles.Cf;
import utiles.Metodos;
import utiles.elem.AreaTexto;
import utiles.elem.AudioFile;

@SuppressWarnings("serial")
public class LaminaTag extends JPanel {
	// Paneles
	JPanel pgrl1 = new JPanel(), pgrl11 = new JPanel(), pgrl111 = new JPanel(), pgrl2 = new JPanel(),
			pgrl12 = new JPanel(), pgrl2box = new JPanel();
	//
	JCheckBox albumBlanco = new JCheckBox("Album en blanco"), tagNominal = new JCheckBox("Tag nominal");
	JButton aceptarB = new JButton("Aceptar"), abrirB = new JButton("Abrir"), añadirEl = new JButton("Añadir");
	private AreaTexto textArea = new AreaTexto();
	GridBagConstraints cons = new GridBagConstraints();

	List<AudioFile> audioList = new ArrayList<AudioFile>();
	private JMenuItem menuItem;

	public LaminaTag() {

		textArea.setEditable(false);
		albumBlanco.setSelected(Cf.albumBlanco);
		tagNominal.setSelected(Cf.tagNominal);

		this.setLayout(new GridBagLayout());

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 5;
		cons.gridwidth = 1;
		cons.weightx = 1.0;
		cons.weighty = 1.0;
		cons.fill = GridBagConstraints.BOTH;
		this.add(textArea, cons); //textArea

		cons.gridx = 1;
		cons.gridheight = 1;
		cons.weightx = 0.0;
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.anchor = GridBagConstraints.NORTH;
		this.add(abrirB, cons); //Boton Abrir

		cons.gridy = 1;
		this.add(aceptarB, cons); //Boton Aceptar

		cons.gridy = 2;
		this.add(añadirEl, cons); //Boton Añadir Elemento

		cons.gridy = 3;
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.SOUTHWEST;
		this.add(albumBlanco, cons); //CheckBox para dejar el album con el valor predefinido

		cons.gridy = 4;
		this.add(tagNominal, cons); //CheckBox para modificar los tags y poner los pathTags

		// AcctionListeners
		albumBlanco.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Cf.albumBlanco = albumBlanco.isSelected();
			}
		});

		tagNominal.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				Cf.tagNominal = tagNominal.isSelected();
			}

		});

		aceptarB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < audioList.size(); i++) {
					if (Cf.tagNominal)
						audioList.get(i).setPathTag();
				}

			}
		});

		abrirB.addActionListener(new ActionListener() { // Limpia la lista existente y carga los MP3		
			@Override
			public void actionPerformed(ActionEvent e) {
				audioList.clear();
				textArea.clean();
				anadirAudioList();
			}
		});

		añadirEl.addActionListener(new ActionListener() { // Añade a la lista existente los nuevos MP3

			@Override
			public void actionPerformed(ActionEvent arg0) {
				anadirAudioList();
			}
		});

		abrirB.setMnemonic(KeyEvent.VK_O);
	}

	private void anadirAudioList() {
		List<File> f = Metodos.abrirArchivo(pgrl11);

		for (File fp : f) {
			audioList.add(new AudioFile(fp.getAbsolutePath()));
			textArea.añadirLinea(fp.getName());
		}
	}
}
