package utiles.elem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoTXT extends Object {

	private final String path;
	private List<String> texto = new ArrayList<>();
	private File archivo;
	private FileReader fr;
	private BufferedReader br;
	private FileWriter writer;
	private PrintWriter pw;
	private final AreaTexto areaTexto;
	private boolean existe = true;
	public static boolean crearNuevoArchivo = false;

	public ArchivoTXT(final String path) {
		this(path, null);
	}

	public ArchivoTXT(final String path, final AreaTexto areaTexto) {
		this.path = path;
		this.areaTexto = areaTexto;

		leerArchivo(path);
		if (crearNuevoArchivo || existe)
			crearArchivo(path);
	}

	private void crearArchivo(final String path) {
		System.out.println("creando archivo...");
		existe = true;
		try {
			writer = new FileWriter(path);
			pw = new PrintWriter(writer);
		} catch (final Exception e) {
			System.out.println("No se ha podido crear el archivo en " + path);
			existe = false;
		}
		if (existe)
			leerArchivo(path);
	}

	private void leerArchivo(final String path) {
		existe = true;
		archivo = new File(path);
		try {
			fr = new FileReader(archivo);
		} catch (final FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("No se ha podido encontrar el archivo en la ruta " + path);
			existe = false;
		}
		br = new BufferedReader(fr);
		try {
			String linea;
			areaTexto.clean();
			while ((linea = br.readLine()) != null) {
				texto.add(linea);
				areaTexto.añadirLinea(linea);
			}
			areaTexto.actualizar();
		} catch (final Exception e) {
			System.out.println("No se ha podido leer el archivo " + archivo.getAbsolutePath());
			existe = false;
		}
	}

	public void añadirLineaEjemplo(final String linea) {
		//pw.println(linea);
		System.out.println("Escrita linea " + linea);
		pw.println(linea);
		//pw.flush();
	}

	public void añadirLinea(final String linea) {
		if (areaTexto != null && existe) {
			areaTexto.añadirLinea(linea);
			texto.add(linea);
			actualizarArchivo();
		}
	}

	public void añadirLinea(final int i, final String linea) {
		try {
			texto.set(i, linea);
			if (areaTexto != null && existe)
				areaTexto.añadirLinea(i, linea);
		} catch (final IndexOutOfBoundsException e) {
			System.out.println("OutOfBounds");
			for (int j = texto.size(); j < i; j++)
				texto.add("");
			texto.add(linea);
			if (areaTexto != null && existe)
				areaTexto.añadirLinea(i, linea);
		}
	}

	public void close() {
		if (existe)
			try {
				writer.close();
				if (fr != null)
					fr.close();
			} catch (final Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar el archivo " + path);
				e.printStackTrace();
			}
	}

	private void actualizarAreaTexto() {
		if (areaTexto != null) {
			areaTexto.clean();
			for (final String l : texto)
				areaTexto.añadirLinea(l);
		}
		System.out.println("Nº de lineas: " + texto.size());
	}

	public void actualizarArchivo() {
		String text = "";
		System.out.println("Texto escrito en archivo " + archivo.getName() + ":");
		for (final String line : texto)
			text += line + "\n";
		System.out.println("======================\n" + text);
		pw.println(texto);
	}

	public List<String> getTexto() {
		texto = areaTexto.getTexto();
		actualizarAreaTexto();
		return texto;
	}

	public void setTexto(final List<String> texto) {
		this.texto = texto;
	}

	public void limpiarTexto() {
		texto.clear();
		areaTexto.clean();
		areaTexto.actualizar();
	}

	public void limpiarArchivo() {
		pw.write("Limpio");
	}

	public int length() {
		return texto.size();
	}
}
