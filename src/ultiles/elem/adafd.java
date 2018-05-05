package ultiles.elem;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JPanel;

class InterObj extends Component implements MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static InterCuadricula rej;
	private JPanel con;
	private InterObj inter;
	private int pos;
	private List<InterObj> intObjs;

	public InterObj(List<InterObj> intObjs, int pos, JPanel con) {
		this(intObjs, pos, con, rej);
		this.pos = pos;
		this.con = con;
		this.intObjs = intObjs;
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public InterObj(List<InterObj> intObjs, int pos, JPanel con, InterCuadricula rej) {
		super();
	}

	public void mouseDragged(MouseEvent e) {

		setLocation(this.getX() + e.getX() - this.getWidth() / 2, this.getY() + e.getY() - this.getHeight() / 2);

		intersects(intObjs, this);

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
				InterObj aux = intObjs.get(f);
				setInterObj(f, intObjs.get(o));
				setInterObj(o, aux);
				//System.out.println("Se han intercambiado los botones " + this.getText() + " y " + inter.getText());
			} else
				setInterObj(o, intObjs.get(o));
		} else
			this.setBounds(40, pos * 40, 100, 30);

	}

	public void setInter(InterObj b) {
		this.inter = b;
	}

	public boolean intersects(List<InterObj> objs, InterObj myButton) {
		// TODO Auto-generated method stub
		for (InterObj b : intObjs) {
			if (b != myButton) {
				if (b.getBounds().intersects(myButton.getBounds())) {
					myButton.setInter(b);
					return true;
				}
			}
		}
		myButton.setInter(null);
		return false;
	}

	public void setInterObj(int i, InterObj b) {
		intObjs.set(i, b);
		b.setBounds(40, i * 40, 100, 30);
	}
}