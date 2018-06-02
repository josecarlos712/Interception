package utiles.elem;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.TextArea;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AreaTexto extends TextArea {

	public int MAXLINEAS = 500; //Numero maximo de lineas permitidas en el documento
	private boolean DELIMITADOR = false, //Si esta a true aparece un delimitador al final del texto
			ENUMERACION = true, //Si esta a true aprarece una enumeracion de lineas ("n. Texto")
			CONSOLA = false; //Para modo depuracion, aparece el texto del AreaTexto en la consola cada vez que se actualiza este
	private final boolean EDITABLE = false;
	private static final long serialVersionUID = 1L;
	protected List<String> to = new ArrayList<>(); //ArrayList de lineas de texto
	private String texto = ""; //Texto del ArrayList /to/ en un solo String
	private Font fuente;

	public AreaTexto() {
		//this.setPreferredSize(new Dimension(500, 400));
		setEditable(EDITABLE);
	}

	public AreaTexto(final int MAXLINEAS) { //Contructor que establece un numero de lineas maximo distinto al predefinido
		this();
		this.MAXLINEAS = MAXLINEAS;

	}

	public void añadirLinea(final int pos, final String str) { //Reemplaza una linea en la posicion /pos/ por /str/
																//si la posicion no existe rellena con "" hasta llegar a la linea /pos/
		if (pos < 0)
			throw new IllegalArgumentException("Has introducido un numero negativo en reemplazarLinea(" + pos + ")");
		else if (pos < MAXLINEAS - 1) {
			if (to.size() <= pos) {
				for (int i = 0; i < pos - to.size(); i++)
					to.add(pos, "");
				final String in = ENUMERACION ? pos + ". " : "";
				to.add(in + str);
			} else
				to.add(pos, str);
			if (DELIMITADOR)
				to.add("===============================================");
			actualizar();
		} else if (pos >= MAXLINEAS)
			throw new IllegalArgumentException("El texto no puede exceder las " + MAXLINEAS + " lineas; pos: " + pos);
	}

	public void añadirLinea(final String str) { //Añade una linea al final del documento
		to.add(str);
		actualizar();
	}

	public void setTexto(final List<String> texto) { //Borra el AreaTexto y establece el texto /texto/
		clean();
		for (final String line : texto)
			this.añadirLinea(line);
	}

	public void setTexto(final String texto) { //Crea un nuevo ArrayList segun el split "\n" de /texto/ y lo establece en AreaTexto
		clean();
		final List<String> text = new LinkedList<>(Arrays.asList(texto.split("\n")));
		text.stream().forEach(x -> this.añadirLinea(x));
	}

	public void actualizar() {
		texto = "";
		to.stream().forEach(x -> texto += x + "\n");
		if (CONSOLA)
			for (final String line : to) {
				if (line != null)
					System.out.println(line);
			}
		else
			setText(texto);

	}

	public String getLine(final int pos) {
		return to.get(pos);
	}

	public void clean() {
		to = new ArrayList<>();

		actualizar();
	}

	public void borrarLinea(final int linea) {
		if (linea > 0 && linea < MAXLINEAS)
			to.set(linea, null);
		else
			throw new IllegalArgumentException("La linea a borrar debe estar en el rango [0," + (MAXLINEAS - 1) + "]");
	}

	public void escribirTexto(final List<String> texto, final boolean limpiar) {
		if (limpiar)
			clean();

		for (final String linea : texto)
			añadirLinea(linea);
	}

	public void setEnumeracion(final boolean enumeracion) {
		ENUMERACION = enumeracion;
	}

	public void setDelimitador(final boolean delimitador) {
		DELIMITADOR = delimitador;
	}

	public void setConsola(final boolean consola) {
		CONSOLA = consola;
	}

	public List<String> getTexto() {
		to = new ArrayList<>();
		Arrays.asList(getText().split("\n")).stream().forEach(x -> to.add(x));
		if (to.size() > 0)
			if (to.get(0) == "")
				return to = new ArrayList<>();
		return to;
	}

	public String getTextoString() {
		getTexto(); //Actualiza /to/  
		return getText();
	}

	public void setFuente(final Font fuente) {
		setFont(fuente.deriveFont(12f));
	}

	public void setFuente(final String pathFont, final float size) {
		try {
			fuente = Font.createFont(Font.PLAIN, new BufferedInputStream(new FileInputStream(pathFont)));
		} catch (FontFormatException | IOException e) {
			System.out.println("No se ha podio leer el archivo " + pathFont);
			e.printStackTrace();
		}
		fuente = fuente.deriveFont(size);
		setFont(fuente);
	}

	public void setTamañoFuente(final float size) {
		fuente = fuente.deriveFont(size);
		setFont(fuente);
	}
}
