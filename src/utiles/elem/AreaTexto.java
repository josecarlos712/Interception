package utiles.elem;

import java.awt.HeadlessException;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

public class AreaTexto extends TextArea {

	private static final long serialVersionUID = 1L;
	private List<String> t = new ArrayList<String>();

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

	public void reemplazarTextArea(String str, int pos) {

		String[] t = this.getText().split("\n");

		for (String st : t) {
			this.t.add(st);
		}

		if (pos >= 0) {
			if (pos <= this.t.size()) {
				this.t.set(pos, str + "\n");
			} else {
				for (int i = this.t.size(); i < pos; i++) {
					this.t.add("\n");
				}
				this.t.add(str + "\n");
			}
		} else {
			System.out.println("Has introducido un numero negativo en reemplazarTextArea(" + pos + ")");
		}
		refrescarTextArea();
	}

	public void refrescarTextArea() {
		for (String line : t) {
			this.append(line);
		}
	}
}
