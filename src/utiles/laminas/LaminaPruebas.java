package utiles.laminas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utiles.elem.AreaTexto;

@SuppressWarnings("serial")
public class LaminaPruebas extends JPanel {

	private AreaTexto text = new AreaTexto();
	private List<PanelInter> plabels = new ArrayList<PanelInter>();
	private Box groupPanel = Box.createVerticalBox();
	private GridBagConstraints cons = new GridBagConstraints();

	public LaminaPruebas() {

		//this.setLayout(new GridBagLayout());
		this.setLayout(null);

		//this.add(new LabelInter(this, "PRUEBA"));

		for (int i = 0; i < 10; i++) {
			plabels.add(new PanelInter(this, "Linea " + i));
			/*cons.gridx = 0;
			cons.gridy = i;
			cons.gridheight = 1;
			cons.gridwidth = 1;
			cons.weightx = 0.0;
			cons.weighty = 0.0;
			cons.fill = GridBagConstraints.NONE;*/
			this.add(plabels.get(i));
			plabels.get(i).addMouseListener(plabels.get(i));
			plabels.get(i).addMouseMotionListener(plabels.get(i));
		}

		//this.add(groupPanel);

		/*text.setEditable(false);
		this.add(text);*/

	}

	public List<PanelInter> getPanels() {
		return plabels;
	}
}

class PanelInter extends JPanel implements MouseMotionListener, MouseListener {

	private String name;
	private LaminaPruebas con;
	private int xmouse, ymouse, x, y, width, height;
	private JLabel label;

	public PanelInter(JPanel con) {
		this(con, "Null");

	}

	public PanelInter(JPanel con, String name) {
		super();
		this.name = name;
		this.con = (LaminaPruebas) con;
		this.label = new JLabel(name);
		this.add(label);
		this.width = this.getBounds().width;
		this.height = this.getBounds().height;
		//System.out.println(name + ": " + width + "x" + height);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Dragging Panel \"" + this.name + "\"");

		setLocation(

				this.getX() + e.getX() - this.getWidth() / 2,

				this.getY() + e.getY() - this.getHeight() / 2

		);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Clicked " + this.label.getText());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.intercepta();
	}

	public void intercepta() {

		List<PanelInter> coni = con.getPanels();

		for (int i = 0; i < coni.size(); i++) {
			PanelInter p = coni.get(i);
			if (new Rectangle(this.x, this.y, 1, 1).intersects(p.getBounds())) {
				PanelInter auxi = p;
				p = this;
				coni.set(i, auxi);
			}

		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
	}
}
