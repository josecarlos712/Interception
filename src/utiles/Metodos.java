package utiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Metodos {

	public static List<File> abrirArchivo(JPanel v) {
		JFileChooser jf = new JFileChooser();
		List<File> f = new ArrayList<File>();
		jf.setMultiSelectionEnabled(true);

		if (jf.showOpenDialog(v) == JFileChooser.APPROVE_OPTION) {

			jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (!jf.isMultiSelectionEnabled()) {
				jf.setMultiSelectionEnabled(true);
			}

			f = Arrays.asList(jf.getSelectedFiles());
		}

		return f;
	}

	public static String selectSaveDirectory(JPanel v) {
		JFileChooser jf = new JFileChooser();

		jf.setDialogTitle("Guardar en");
		jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (jf.showSaveDialog(v) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Save directory: " + jf.getSelectedFile().getAbsolutePath());
			return jf.getSelectedFile().getAbsolutePath();
		} else {
			return "";
		}
	}

	public static void intercepcion(List<File> f1, List<File> f2, boolean out, String saveDir) {
		// TODO Auto-generated method stub
		List<File> f = f1;

		for (File fs : f2) {
			if (f1.contains(fs)) {
				f.remove(fs);
			}
			// Cf.mes.setText("Comparando..." + (f1.indexOf(fs) + 1) * 100 / f1.size());
		}

		if (out) {
			for (File fout : f) {
				Metodos.consola("copy \"" + fout.getAbsolutePath() + "\" \"" + saveDir + "\\" + fout.getName() + "\"");
				// Cf.mes.setText("Copiando archivos..." + (f.indexOf(fout) + 1) * 100 /
				// f.size() + "%");
			}
		} else {

		}
	}

	public static void consola(String c) {
		try {
			Runtime.getRuntime().exec("CMD /C " + c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR en el comando " + c);
		}
	}
}
