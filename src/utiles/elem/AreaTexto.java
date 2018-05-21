package utiles.elem;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AreaTexto extends TextArea {

	public int MAXLINEAS = 100;
	private boolean DELIMITADOR = false, ENUMERACION = true, CONSOLA = false;
	private final boolean EDITABLE = false;
	private static final long serialVersionUID = 1L;

	protected List<String> to = new ArrayList<>();

	public AreaTexto() {
		//this.setPreferredSize(new Dimension(500, 400));
		setEditable(EDITABLE);
	}

	public AreaTexto(final int MAXLINEAS) {
		this();
		this.MAXLINEAS = MAXLINEAS;

	}

	@SuppressWarnings("static-access")
	public void añadirLinea(final int pos, final String str) {
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

	public void añadirLinea(final String str) {
		to.add(str);
		actualizar();
	}

	public void setTexto(final List<String> texto) {
		clean();
		for (final String line : texto)
			this.añadirLinea(line);
	}

	public void actualizar() {
		setText("");
		for (final String line : to)
			if (line != null)
				if (CONSOLA)
					System.out.println(line);
				else
					append(line + "\n");
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
		to = Arrays.asList(getText().split("\n"));
		return to;
	}

	public String getTextoString() {
		return getText();
	}
}
