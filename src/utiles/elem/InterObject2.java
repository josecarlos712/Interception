package utiles.elem;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/*public class InterObject2 extends JPanel implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private InterObject2 inter;
	private int x, y, pos;
	private JPanel pcomp;
	private Component comp;
	private List<InterObject2> intObjs;

	public InterObject2(Container con, List<InterObject2> intObjs, int pos, Component comp, InterCuadricula rej) {
		this.pos = pos;
		this.intObjs = intObjs;
		this.comp = comp;
		//	this.pcomp = this.getComponente();
		con.add(comp);
		comp.addMouseMotionListener(this);
		//comp.addMouseListener(this);
		/*this.x = rej.x + rej.pasox * pos;
		this.y = rej.y + rej.pasoy * pos;
}

public void mouseDragged(MouseEvent e){

setLocation(this.getX()+e.getX()-this.getWidth()/2,this.getY()+e.getY()-this.getHeight()/2);
//intersects(intObjs);

}

public void mouseMoved(MouseEvent mme){}

/*@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	intercambiarPos();
	inter = null;
}

private void intercambiarPos(){
// TODO Auto-generated method stub
int f=-1,o=-1;if(inter!=null){for(int i=0;i<intObjs.size();i++){if(intObjs.get(i)==this)o=i;else if(intObjs.get(i)==inter)f=i;}if(f!=-1&&o!=-1){InterObject2 aux=intObjs.get(f);setInterObj(f,intObjs.get(o));setInterObj(o,aux);
//System.out.println("Se han intercambiado los botones " + this.getText() + " y " + inter.getText());
}else setInterObj(o,intObjs.get(o));}else setLocation(x,y);

}

public void setInter(InterObject2 b){this.inter=b;}

public boolean intersects(List<InterObject2>objs){
// TODO Auto-generated method stub
for(InterObject2 b:intObjs){if(b!=this){if(b.getBoudns().intersects(this.getBounds())){this.setInter(b);return true;}}}this.setInter(null);return false;}

private Rectangle getBoudns(){
// TODO Auto-generated method stub
return this.comp.getBounds();}

public void setInterObj(int i,InterObject2 b){intObjs.set(i,b);setLocation(40,i*40);}

public void setLocation(int x,int y){
//pcomp.setLocation(x, y);
comp.setLocation(x,y);}

public Component getComponente(){return comp;}}

class PanelTemporal extends JPanel {
	public PanelTemporal(Component comp) {
		this.add(comp);
	}

}*/

public class InterObject2 extends JPanel implements MouseMotionListener {

	public InterObject2(Component b) {
		this.add(b);
		b.addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		setLocation(this.getX() + e.getX() - this.getWidth() / 2, this.getY() + e.getY() - this.getHeight() / 2);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}