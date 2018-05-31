package utiles.laminas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class LaminaPruebas extends JPanel implements ActionListener {

	private static String[] fontOptions = { "Serif", "Agency FB", "Arial", "Calibri", "Cambrian", "Century Gothic",
			"Comic Sans MS", "Courier New", "Forte", "Garamond", "Monospaced", "Segoe UI", "Times New Roman",
			"Trebuchet MS", "Serif" };
	private static String[] sizeOptions = { "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28" };
	private final String pathFont = "fonts/digital-7.ttf";

	ImageIcon newIcon = new ImageIcon("images/icons/NewIcon.png");
	ImageIcon saveIcon = new ImageIcon("images/icons/SaveIcon.png");
	ImageIcon openIcon = new ImageIcon("images/icons/OpenIcon.png");
	ImageIcon fontIcon = new ImageIcon("images/icons/FontIcon.png");
	ImageIcon changeFontIcon = new ImageIcon("images/icons/ChangeFontIcon.png");

	JButton New = new JButton(newIcon);
	JButton Save = new JButton(saveIcon);
	JButton Open = new JButton(openIcon);
	JButton changeFont = new JButton(changeFontIcon);

	JLabel fontLabel = new JLabel(fontIcon);
	JLabel fontLabelText = new JLabel("Font: ");
	JLabel fontSizeLabel = new JLabel("Size: ");

	JComboBox<String> fontName = new JComboBox<>(fontOptions);
	JComboBox<String> fontSize = new JComboBox<>(sizeOptions);

	JToolBar tool = new JToolBar();

	JTextArea texty = new JTextArea();
	JScrollPane scroll = new JScrollPane(texty);

	Font fuente;

	public LaminaPruebas() {
		Display();
	}

	public void Display() {
		texty.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		New.addActionListener(this);
		New.setToolTipText("Creates a new File");
		Save.addActionListener(this);
		Save.setToolTipText("Saves the current File");
		Open.addActionListener(this);
		Open.setToolTipText("Opens a file");
		changeFont.addActionListener(e -> {
			texty.setFont(fuente.deriveFont((float) texty.getFont().getSize() + 5));
		});
		changeFont.setToolTipText("Change the Font");

		fontLabel.setToolTipText("Font");

		fontLabelText.setToolTipText("Set the kind of Font");
		fontSizeLabel.setToolTipText("Set the size of the Font");

		tool.add(New);
		tool.addSeparator();
		tool.add(Save);
		tool.addSeparator();
		tool.add(Open);
		tool.addSeparator();
		tool.addSeparator();
		tool.addSeparator();
		tool.add(fontLabel);
		tool.addSeparator();
		tool.add(fontLabelText);
		tool.add(fontName);
		tool.addSeparator();
		tool.add(fontSizeLabel);
		tool.add(fontSize);
		tool.addSeparator();
		tool.add(changeFont);

		setLayout(new BorderLayout());
		this.add(tool, "North");
		this.add(scroll, "Center");

		try {
			fuente = Font.createFont(Font.PLAIN, new BufferedInputStream(new FileInputStream(pathFont)));
		} catch (FontFormatException | IOException e1) {
			fuente = new Font("Serif", Font.PLAIN, 50);
			e1.printStackTrace();
		}
		texty.setFont(fuente);
		texty.setFont(fuente.deriveFont(50f));
	}

	@Override
	public void actionPerformed(final ActionEvent evt) {
		String fontNameSet;
		String fontSizeSetTemp;
		int fontSizeSet;
		final Object source = evt.getSource();
		if (source == New)
			texty.setText("");
		else if (source == changeFont) {
			fontNameSet = (String) fontName.getSelectedItem();
			fontSizeSetTemp = (String) fontSize.getSelectedItem();
			fontSizeSet = Integer.parseInt(fontSizeSetTemp);
			System.out.println(fontNameSet + fontSizeSet);
			scroll.setFont(new Font(fontNameSet, fontSizeSet, Font.PLAIN));
		}

	}
}