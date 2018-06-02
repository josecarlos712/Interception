package utiles.laminas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class LaminaPruebas extends JPanel {

	private static String[] fontOptions = { "Serif", "Agency FB", "Arial", "Calibri", "Cambrian", "Century Gothic",
			"Comic Sans MS", "Courier New", "Forte", "Garamond", "Monospaced", "Segoe UI", "Times New Roman",
			"Trebuchet MS", "Serif" };
	private static String[] sizeOptions = { "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28" };
	private final String pathFont = "fonts/njnaruto.ttf";

	JComboBox<String> fontName = new JComboBox<>(fontOptions);
	JComboBox<String> fontSize = new JComboBox<>(sizeOptions);

	JToolBar tool = new JToolBar();

	JTextArea texty = new JTextArea();
	JScrollPane scroll = new JScrollPane(texty);

	Font fuente;
	FontMetrics fontM;
	private final JButton bdown = new JButton("Disminuir"), bup = new JButton("Aumentar");

	public LaminaPruebas() {
		Display();
	}

	public void Display() {
		try {
			fuente = Font.createFont(Font.PLAIN, new BufferedInputStream(new FileInputStream(pathFont)));
		} catch (FontFormatException | IOException e1) {
			fuente = new Font(Font.MONOSPACED, Font.PLAIN, 30);
			e1.printStackTrace();
		}
		//fuente = new Font(Font.MONOSPACED, Font.PLAIN, 30);
		fuente = fuente.deriveFont(Font.PLAIN, 40f);
		texty.setFont(fuente);
		texty.setLineWrap(true);

		setLayout(new BorderLayout());

		tool.add(bdown);
		tool.add(bup);

		this.add(tool, "North");
		this.add(scroll, "Center");

		bdown.addActionListener(e -> {
			fuente = texty.getFont().deriveFont((float) (texty.getFont().getSize() - 2));
			texty.setFont(fuente);
			System.out.println("fontSize: " + fuente.getSize());
		});
		bup.addActionListener(e -> {
			fuente = texty.getFont().deriveFont((float) (texty.getFont().getSize() + 2));
			texty.setFont(fuente);
			System.out.println("fontSize: " + fuente.getSize());
		});
	}
}