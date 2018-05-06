package utiles.elem;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JPanel;

public class InterObject extends Component implements MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private InterObject inter;
	private int x, y, pos;
	private JPanel pcomp;
	private Component comp;
	private List<InterObject> intObjs;

	public InterObject(Container con, List<InterObject> intObjs, int pos, Component comp, InterCuadricula rej) {
		this.pos = pos;
		this.intObjs = intObjs;
		this.comp = comp;
		//	this.pcomp = this.getComponente();
		con.add(comp);
		comp.addMouseMotionListener(this);
		comp.addMouseListener(this);
		this.x = rej.x + rej.pasox * pos;
		this.y = rej.y + rej.pasoy * pos;
	}

	public void mouseDragged(MouseEvent e) {

		setLocation(e.getX(), e.getY());
		intersects(intObjs);

	}

	public void mouseMoved(MouseEvent mme) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
		intercambiarPos();
		inter = null;
	}

	private void intercambiarPos() {
		// TODO Auto-generated method stub
		int f = -1, o = -1;
		if (inter != null) {
			for (int i = 0; i < intObjs.size(); i++) {
				if (intObjs.get(i) == this)
					o = i;
				else if (intObjs.get(i) == inter)
					f = i;
			}
			if (f != -1 && o != -1) {
				InterObject aux = intObjs.get(f);
				setInterObj(f, intObjs.get(o));
				setInterObj(o, aux);
				//System.out.println("Se han intercambiado los botones " + this.getText() + " y " + inter.getText());
			} else
				setInterObj(o, intObjs.get(o));
		} else
			setLocation(x, y);

	}

	public void setInter(InterObject b) {
		this.inter = b;
	}

	public boolean intersects(List<InterObject> objs) {
		// TODO Auto-generated method stub
		for (InterObject b : intObjs) {
			if (b != this) {
				if (b.getBoudns().intersects(this.getBounds())) {
					this.setInter(b);
					return true;
				}
			}
		}
		this.setInter(null);
		return false;
	}

	private Rectangle getBoudns() {
		// TODO Auto-generated method stub
		return this.comp.getBounds();
	}

	public void setInterObj(int i, InterObject b) {
		intObjs.set(i, b);
		setLocation(40, i * 40);
	}

	public void setLocation(int x, int y) {
		//pcomp.setLocation(x, y);
		comp.setLocation(x, y);
	}

	public Component getComponente() {
		return comp;
	}
}

class PanelTemporal extends JPanel {
	public PanelTemporal(Component comp) {
		this.add(comp);
	}
}