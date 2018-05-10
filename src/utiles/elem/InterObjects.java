package utiles.elem;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utiles.Cf;

public class InterObjects extends JPanel implements MouseMotionListener, MouseListener {
	private List<InterObject> io = new ArrayList<InterObject>();
	private List<Object> objs = new ArrayList<Object>();
	private GridBagConstraints cons = new GridBagConstraints();
	private JPanel container;

	public InterObjects(JPanel container, List<Object> io, GridBagConstraints cons) {
		super(new GridBagLayout());

		this.cons = cons;
		this.container = container;
		this.objs = io;
		this.io = new ArrayList<InterObject>();

		this.cons.gridx = 0;
		this.cons.gridwidth = 1;
		this.cons.gridheight = 1;
		this.cons.weighty = 1.0;
		this.cons.weightx = 1.0;

		for (int i = 0; i < this.objs.size(); i++) {
			InterObject o = new InterObject((Component) this.objs.get(i));
			this.io.add(o);
			this.cons.gridy = i;
			add(o, this.cons);
			o.addMouseMotionListener(this);
			o.addMouseListener(this);
		}
	}

	public InterObjects(JPanel container, List<InterObject> io, GridBagConstraints cons, boolean iosb) {
		super(new GridBagLayout());

		this.cons = cons;
		this.container = container;
		this.io = new ArrayList<InterObject>();

		cons.gridx = 0;
		cons.gridwidth = 1;
		cons.gridheight = 1;
		cons.fill = GridBagConstraints.BOTH;
		cons.weighty = 1.0;
		cons.weightx = 1.0;

		for (int i = 0; i < io.size(); i++) {
			InterObject o = (InterObject) io.get(i);
			this.io.add(o);
			this.cons.gridy = i;
			o.addMouseMotionListener(this);
			o.addMouseListener(this);
			add(o, this.cons);
		}
	}

	public void actualizar() {
		nuevaInterObjects(this.container, this.io, this.cons, true);
		Cf.ven.actualizarVentana();
	}

	void intercambiarPos(InterObject io, InterObject inter) {
		// TODO Auto-generated method stub
		int f = -1, o = -1;
		for (int i = 0; i < this.io.size(); i++) {
			if (this.io.get(i) == io) {
				o = i;
			} else if (this.io.get(i) == inter) {
				f = i;
			}
		}
		if (f != -1 && o != -1) {
			InterObject aux = (InterObject) this.io.get(f);
			this.io.set(f, this.io.get(o));
			this.io.set(o, aux);
		}
		actualizar();
	}

	public void nuevaInterObjects(JPanel container, List<Object> intObjs, GridBagConstraints cons) {
		removeAll();
		//container.repaint();
		new InterObjects(container, intObjs, cons);
		//container.repaint();
		//jsp.repaint();

	}

	private void nuevaInterObjects(JPanel container2, List<InterObject> io, GridBagConstraints cons, boolean b) {
		removeAll();
		//container.repaint();
		new InterObjects(container, io, cons, true);
	}

	public void mouseDragged(MouseEvent e) {
		InterObject o = ((InterObject) e.getSource()).getContenedor();
		o.setLocation(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
	public void mouseReleased(MouseEvent e) {
		InterObject o = (InterObject) e.getSource();
		InterObject inter = intersects(o);
		if (inter != null)
			intercambiarPos(o, inter);
		else if (io == null)
			System.out.println("io == null");
		else
			actualizar();

	}

	public InterObject intersects(InterObject o2) {
		for (InterObject o : io)
			if (o.intersects(o2))
				return o;
		return null;
	}

}

class InterObject extends JPanel {

	private Component obj;

	public InterObject(Component o) {
		this.add(o);
		this.obj = o;
	}

	public InterObject getContenedor() {
		return this;
	}

	public boolean intersects(InterObject b) {
		return (b.getBounds().intersects(this.getBounds())) ? true : false;
	}

	public String getText() {
		try {
			return ((JLabel) this.obj).getText();
		} catch (Exception e) {
			return "No es JLabel";
		}
	}

	public Component getObject() {
		return this.obj;
	}
}