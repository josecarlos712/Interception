package utiles.elem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	private final boolean CONSOLA = false;
	private String textoej;
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

		if (existe && areaTexto != null)
			texto.stream().forEach(x -> areaTexto.añadirLinea(x));

	}

	private void crearArchivo(final String path) {
		//System.out.println("creando archivo...");
		existe = true;
		try {
			writer = new FileWriter(path);
			pw = new PrintWriter(writer);
		} catch (final Exception e) {
			if (CONSOLA)
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
			if (CONSOLA)
				System.out.println("No se ha podido encontrar el archivo en la ruta " + path);
			existe = false;
		}
		if (existe) {
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
				if (CONSOLA)
					System.out.println("No se ha podido leer el archivo " + archivo.getAbsolutePath());
				existe = false;
			}
		}
	}

	public void añadirLinea(final String linea) {
		if (areaTexto != null && existe) {
			areaTexto.añadirLinea(linea);
			texto.add(linea);
		}
	}

	public void añadirLinea(final int i, final String linea) {
		if (areaTexto != null && existe)
			try {
				areaTexto.añadirLinea(i, linea);
				texto.set(i, linea);
			} catch (final IndexOutOfBoundsException e) {
				System.out.println("OutOfBounds");
				for (int j = texto.size(); j < i; j++)
					texto.add("");
				texto.add(linea);
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

	public void actualizarArchivo() {
		try {
			writer = new FileWriter(path);
		} catch (final IOException e) {
			System.out.println("Error al crear FileWriter en " + path);
			e.printStackTrace();
		}
		pw = new PrintWriter(writer);
		pw.print(textoej);
		pw.flush();
		pw.close();
		//System.out.println("Numero de lineas: " + size());
		//	System.out.println("Numero de caracteres: " + length());
	}

	public List<String> getTexto() {
		textoej = areaTexto.getTextoString();
		//areaTexto.setTexto(texto);
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
		limpiarTexto();
		actualizarArchivo();
	}

	public int size() {//Numero de lineas del texto
		return texto.size();
	}

	public int length() {//Numero de caracteres del texto
		return texto.stream().mapToInt(x -> x.length()).sum();
	}
}
