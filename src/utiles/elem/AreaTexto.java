package utiles.elem;

import java.awt.TextArea;

public class AreaTexto extends TextArea {

	public int MAXLINEAS = 500;
	public static boolean DELIMITADOR = false;
	public static boolean enumeracion = true;
	private static final long serialVersionUID = 1L;
	protected String[] to = new String[MAXLINEAS];

	public AreaTexto() {
	}

	public AreaTexto(int MAXLINEAS) {
		this.MAXLINEAS = MAXLINEAS;
	}

	@SuppressWarnings("static-access")
	public void reemplazarLinea(String str, int pos) {
		if (pos < 0) {
			throw new IllegalArgumentException("Has introducido un numero negativo en reemplazarLinea(" + pos + ")");
		} else if (pos < MAXLINEAS - 1) {
			if (this.to[pos] == null) {
				for (int i = getUltimaLinea(); i < pos; i++)
					this.to[i] = "\n";
				String in = this.enumeracion ? pos + ". " : "";
				this.to[pos] = in + str + "\n";
			}
			if (DELIMITADOR)
				this.to[MAXLINEAS - 1] = "===============================================";
		} else if (pos >= MAXLINEAS)
			throw new IllegalArgumentException("El texto no puede exceder las " + MAXLINEAS + " lineas");
		refrescarTextArea();
	}

	public void añadirLinea(String str) {
		reemplazarLinea(str, this.getUltimaLinea());
	}

	public void refrescarTextArea() {
		this.setText("");

		for (String line : this.to) {
			if (line != null) {
				//System.out.println(line);
				this.append(line);
			}
		}
	}

	public String getLine(int pos) {
		return this.to[pos];
	}

	public int getUltimaLinea() {
		for (int i = 0; i < this.to.length; i++) {
			if (this.to[i] == null)
				return i;
		}
		return MAXLINEAS;
	}

	public void limpiarTextArea() {
		this.to = new String[MAXLINEAS];
		this.refrescarTextArea();
	}

	public void borrarLinea(int linea) {
		if (linea > 0 && linea < MAXLINEAS)
			this.to[linea] = null;
		else
			throw new IllegalArgumentException("La linea a borrar debe estar en el rango [0," + (MAXLINEAS - 1) + "]");
	}
}
