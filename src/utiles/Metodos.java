package utiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Metodos {

	public static List<File> abrirArchivo(final JPanel v) {
		final JFileChooser jf = new JFileChooser();
		List<File> f = new ArrayList<>();
		jf.setMultiSelectionEnabled(true);

		if (jf.showOpenDialog(v) == JFileChooser.APPROVE_OPTION) {

			jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (!jf.isMultiSelectionEnabled())
				jf.setMultiSelectionEnabled(true);

			f = Arrays.asList(jf.getSelectedFiles());
		}

		return f;
	}

	public static String selectSaveDirectory(final JPanel v) {
		final JFileChooser jf = new JFileChooser();

		jf.setDialogTitle("Guardar en");
		jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (jf.showSaveDialog(v) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Save directory: " + jf.getSelectedFile().getAbsolutePath());
			return jf.getSelectedFile().getAbsolutePath();
		} else
			return "";
	}

	public static void intercepcion(final List<File> f1, final List<File> f2, final boolean out, final String saveDir) {
		// TODO Auto-generated method stub
		final List<File> f = f1;

		for (final File fs : f2)
			if (f1.contains(fs))
				f.remove(fs);

		if (out)
			for (final File fout : f)
				Metodos.consola("copy \"" + fout.getAbsolutePath() + "\" \"" + saveDir + "\\" + fout.getName() + "\"");
		// Cf.mes.setText("Copiando archivos..." + (f.indexOf(fout) + 1) * 100 /
		// f.size() + "%");
		else {

		}
	}

	public static void consola(final String c) {
		try {
			Runtime.getRuntime().exec("CMD /C " + c);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR en el comando " + c);
		}
	}
}
