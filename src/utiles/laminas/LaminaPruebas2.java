package utiles.laminas;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import utiles.elem.InterCuadricula;

public class LaminaPruebas2 extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton b = new JButton("Boton"), up = new JButton("Up"), down = new JButton("Down"),
			right = new JButton("Right"), left = new JButton("Left");
	int x, y;
	List<JButton> botones = new ArrayList<JButton>();
	List<JPanel> pbotones = new ArrayList<JPanel>();
	InterCuadricula cons = new InterCuadricula();

	public LaminaPruebas2() {
		this.setLayout(new FlowLayout());

		cons.pasox = 0;
		cons.pasoy = 100;
		cons.x = 100;
		cons.y = 100;
		botones.add(b);
		botones.add(down);
		botones.add(up);
		botones.add(left);
		botones.add(right);

		for (int i = 0; i < botones.size(); i++) {
			JButton b = botones.get(i);
			pbotones.add(new InterO(pbotones, b, i, cons));
			this.add(pbotones.get(i));
		}
	}
}

class InterO extends JPanel implements MouseMotionListener, MouseListener {

	//private Component obj;
	private List<JPanel> intObjs;
	private Component inter;
	private int xo, yo, xf, yf;

	public InterO(List<JPanel> intObjs, Component obj, int pos) {
		//this.obj = obj;
		this.intObjs = intObjs;
		this.add(obj);
		obj.addMouseMotionListener(this);
		obj.addMouseListener(this);
		this.xo = pos * 100;
		this.yo = pos * 100;
		setLocation(xo, yo);
	}

	public InterO(List<JPanel> intObjs, Component obj, int pos, InterCuadricula cons) {
		this(intObjs, obj, pos);

		xo = cons.x + pos * cons.pasox;
		yo = cons.y + pos * cons.pasoy;
		setLocation(xo, yo);
	}

	public void mouseDragged(MouseEvent e) {
		xf = this.getX() + e.getX() - this.getWidth() / 2;
		yf = this.getY() + e.getY() - this.getHeight() / 2;
		setLocation(xf, yf);
		//intersects(intObjs);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean intersects(List<Component> intObjs2) {
		// TODO Auto-generated method stub
		for (Component b : intObjs) {
			if (b != this) {
				if (b.getBounds().intersects(this.getBounds())) {
					this.setInter(b);
					return true;
				}
			}
		}
		this.setInter(null);
		return false;
	}

	public void setInter(Component b) {
		this.inter = b;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setLocation(xo, yo);
	}
}