package utiles.laminas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;

import utiles.elem.ArchivoTXT;
import utiles.elem.AreaTexto;

public class LaminaPruebas2 extends Lamina {

	private static final long serialVersionUID = 1L;
	private final AreaTexto areaTexto = new AreaTexto();
	private final ArchivoTXT archivo;
	private final String pathRoute = "text/archivoPrueba.txt";
	private final GridBagConstraints cons = new GridBagConstraints();
	private final JButton a�adir = new JButton("A�adir linea"), limpiar = new JButton("Limpiar"),
			guardar = new JButton("Guardar");

	public LaminaPruebas2() {

		setLayout(new GridBagLayout());

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridheight = 3;
		cons.gridwidth = 1;
		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 1.0;
		cons.weighty = 1.0;

		add(areaTexto, cons);
		areaTexto.setEnumeracion(false);
		areaTexto.setConsola(false);
		areaTexto.setEditable(true);

		cons.gridheight = 1;
		cons.gridx = 1;
		cons.gridy = 0;
		cons.weightx = 0.0;
		add(a�adir, cons);

		cons.gridy = 1;
		add(limpiar, cons);

		cons.gridy = 2;
		add(guardar, cons);

		ArchivoTXT.crearNuevoArchivo = true;
		archivo = new ArchivoTXT(pathRoute, areaTexto);

		a�adir.addActionListener(e -> {
			archivo.a�adirLinea("Linea " + archivo.size());
		});
		limpiar.addActionListener(e -> {
			archivo.limpiarTexto();
			archivo.actualizarArchivo();
		});
		guardar.addActionListener(e -> {
			archivo.getTexto();
			System.out.println("Actualizando el archivo");
			archivo.actualizarArchivo();
		});

	}
}