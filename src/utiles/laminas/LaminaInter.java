package utiles.laminas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utiles.Cf;
import utiles.Metodos;

public class LaminaInter extends Lamina {

	private static final long serialVersionUID = 1L;
	private JTextField u1 = new JTextField("Entrada 1", 30), u2 = new JTextField("Entrada 2", 30),
			out = new JTextField("Capeta Salida", 30);
	private JButton bu1 = JButton("Abrir"), bu2 = JButton("Abrir"), bout = JButton("Abrir"),
			bint = JButton("Intercepcion");
	private JLabel lu1 = new JLabel("Entrada 1"), lu2 = new JLabel("Entrada 2"), luout = new JLabel("     Salida"),
			lmes = new JLabel("     Informacion");
	private JCheckBox cb = new JCheckBox("Salida     ");

	private List<File> f1 = new ArrayList<File>(), f2 = new ArrayList<File>();

	public LaminaInter() {
		Cf.mes = new JTextField("Mensajes", 30);
		// Layouts
		GridLayout grl1 = new GridLayout(2, 1);
		GridLayout grl2 = new GridLayout(1, 2);
		// Panels
		JPanel grl1p = new JPanel();
		JPanel grl2p = new JPanel();
		JPanel grl11p = new JPanel();
		JPanel grl12p = new JPanel();
		// Asignacion de panels
		this.setLayout(grl1);
		this.add(grl1p); //
		this.add(grl2p); // Lamina 3
		grl1p.setLayout(grl2);
		grl1p.add(grl11p); // Lamina 1
		grl1p.add(grl12p); // Lamina 2
		// Añadir
		grl2p.add(cb); // Checkbox de salida
		grl2p.add(bint); // Boton intercepcion
		grl2p.add(luout); // Label "Salida"
		grl2p.add(out); // Carpeta de salida Texto
		grl2p.add(bout); // Boton para elegir carpeta de salida
		grl2p.add(lmes); // Label "Mensaje"
		grl2p.add(Cf.mes); // Mensaje de informacion Texto
		grl11p.add(lu1); // Label "Entrada 1"
		grl11p.add(u1); // Carpeta de entrada 1 Texto
		grl11p.add(bu1); // Boton para elegir carpeta de entrada 1
		grl12p.add(lu2); // Label "Entrada 2"
		grl12p.add(u2); // Carpeta de entrada 2
		grl12p.add(bu2); // Boton para elegir la carpeta de entrada 2

		Cf.mes.setText("Seleccionar archivos de entrada 1");

		bu1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f1 = Metodos.abrirArchivo(grl11p);
				if (f1.size() != 0) {
					Cf.ent1 = f1.get(0).getParentFile().getPath();
					u1.setText(Cf.ent1);
					Cf.mes.setText("Seleccionar archivos de entrada 2");
				}
			}
		});

		bu2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f2 = Metodos.abrirArchivo(grl12p);
				if (f2.size() != 0) {
					Cf.ent2 = f2.get(0).getParentFile().getPath();
					u2.setText(Cf.ent2);
					Cf.mes.setText("Presionar boton de intercepcion");
				}
			}
		});

		bout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Cf.saveDirectory = Metodos.selectSaveDirectory(grl2p);
				out.setText(Cf.saveDirectory);
			}
		});

		bint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Cf.mes.setText("Copiando archivos...0%");
				Metodos.intercepcion(f1, f2, cb.isSelected(), Cf.saveDirectory);
				Cf.mes.setText("Archivos copiados");
			}
		});

	}

	private JButton JButton(String text) {
		// TODO Auto-generated method stub
		return new JButton(text);
	}
}
