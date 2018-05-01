package utiles.laminas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.farng.mp3.TagException;

import utiles.Cf;
import utiles.Metodos;
import utiles.elem.AudioFile;

@SuppressWarnings("serial")
public class LaminaTag extends Lamina {

	// Paneles
	JPanel pgrl1 = new JPanel(), pgrl11 = new JPanel(), pgrl111 = new JPanel(), pgrl2 = new JPanel(),
			pgrl12 = new JPanel();
	//
	JCheckBox albumBlanco = new JCheckBox("Album en blanco");
	JButton aceptarB = new JButton("Aceptar"), abrirB = new JButton("Abrir"), anadirEl = new JButton("Añadir");
	JTextArea pathField = new JTextArea(50, 50);

	List<AudioFile> audioList = new ArrayList<AudioFile>();

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
		pgrl11.add(anadirEl);

		pgrl12.add(albumBlanco);

		pgrl2.add(aceptarB);

		// AcctionListeners
		albumBlanco.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				Cf.blankAlbum = albumBlanco.isSelected();
			}
		});

		aceptarB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (AudioFile af : audioList) {
					af.setPathTag();
					if (Cf.blankAlbum)
						af.setAlbum("Music");
					try {
						af.sync();
					} catch (IOException | TagException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					af.readTag();
				}
			}
		});

		abrirB.addActionListener(new ActionListener() { // Limpia la lista existente y carga los MP3

			@Override
			public void actionPerformed(ActionEvent e) {
				audioList.clear();
				anadirAudioList();
				actualizarJTextAreaMP3();
			}
		});

		anadirEl.addActionListener(new ActionListener() { // Añade a la lista existente los nuevos MP3

			@Override
			public void actionPerformed(ActionEvent arg0) {
				anadirAudioList();
				actualizarJTextAreaMP3();
			}
		});
	}

	private void anadirAudioList() {
		List<File> f = Metodos.abrirArchivo(pgrl11);

		for (File fp : f) {
			audioList.add(new AudioFile(fp.getAbsolutePath()));
			pathField.setText(fp.getAbsolutePath());
		}
	}

	private void actualizarJTextAreaMP3() {

		for (int i = 0; i < this.audioList.size(); i++) {

		}
	}
}
