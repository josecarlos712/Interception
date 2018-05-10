package utiles.laminas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utiles.elem.InterObjects;

public class LaminaPruebas2 extends Lamina {

	JFrame f = new JFrame();
	GridBagConstraints cons = new GridBagConstraints();
	JPanel panel = new JPanel(new GridBagLayout());
	InterObjects ios;
	//	JButton removeButton = new JButton("Remove Field");
	private List<Object> io = new ArrayList<Object>();

	public LaminaPruebas2() {
		setLayout(new GridBagLayout());

		for (int i = 0; i < 25; i++) {
			io.add(new JLabel("Field " + i));
		}

		cons.gridx = 0;
		cons.gridy = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.BOTH;
		cons.weighty = 1.0;
		cons.weightx = 1.0;

		panel.add(ios = new InterObjects(panel, io, cons), cons);

		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		add(scrollPane, cons);

	}
}