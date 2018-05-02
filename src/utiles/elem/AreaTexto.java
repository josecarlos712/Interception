package utiles.elem;

import java.awt.HeadlessException;
import java.awt.TextArea;

public class AreaTexto extends TextArea {

	public static final int MAXLINEAS = 300;
	private static final long serialVersionUID = 1L;
	protected String[] to = new String[MAXLINEAS];

	public AreaTexto() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	public AreaTexto(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AreaTexto(int arg0, int arg1) throws HeadlessException {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public AreaTexto(String arg0, int arg1, int arg2) throws HeadlessException {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	public AreaTexto(String arg0, int arg1, int arg2, int arg3) throws HeadlessException {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public void reemplazarLinea(String str, int pos) {

		separarEnLineas();

		if (pos < 0) {
			throw new IllegalArgumentException("Has introducido un numero negativo en reemplazarLinea(" + pos + ")");
		} else if (pos < 300) {
			this.to[pos] = str + "\n";
		}

		refrescarTextArea();
	}

	public void refrescarTextArea() {
		this.setText("");

		for (String line : this.to) {
			if (line != null)
				this.append(line);
		}
	}

	public String getLine(int pos) {
		return this.to[pos];
	}

	private void separarEnLineas() {

		String texto = this.getText();
		String[] lineas = texto.split("\n");

		if (lineas.length < 300) {
			for (int i = 0; i < lineas.length; i++) {
				this.to[i] = lineas[i] + "\n";
			}
		} else {
			for (int i = 0; i < MAXLINEAS; i++) {
				this.to[i] = lineas[i] + "\n";
			}
			throw new IllegalArgumentException("El texto no puede exceder la 300 lineas");
		}
	}
}
