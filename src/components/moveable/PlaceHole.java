package components.moveable;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import GPU.Game3D;
import components.Components;
import components.MapConstructor;
import components.goldDiggers.Hole;

public class PlaceHole extends Creaters {

	private final static long COSTVALUE = 600;
	
	public PlaceHole() throws FullBoardException {
		this(getEmptyI(), getEmptyJ());
	
	}
	
	private static int getEmptyJ() throws FullBoardException {
		for (int i = 0; i < MapConstructor.poleSize; i++) {
			for (int j = 0; j < MapConstructor.poleSize; j++) {
				if (!hasElWhit(i,j)) {
					return j;
				}
			}
		}
		throw new FullBoardException();
	}
	
	private static int getEmptyI() throws FullBoardException {
		// TODO Auto-generated method stub
		for (int i = 0; i < MapConstructor.poleSize; i++) {
			for (int j = 0; j < MapConstructor.poleSize; j++) {
				if (!hasElWhit(i,j)) {
					
					return i;
				}
			}
		}
		throw new FullBoardException();
	}
	
	private static boolean hasElWhit(int i, int j) {
		for (Components el : Game3D.getHoles()) {
			if (el.getI()==i&&el.getJ()==j) {
				return true;
			}
		}
		if (Game3D.player.getI()==i&&Game3D.player.getJ()==j) {
			return true;
		}
		return false;
	}
	
	public PlaceHole(int i, int j) {
		super(i, j);
		super.costValue = (long) Math.pow(PlaceHole.COSTVALUE ,( Game3D.getHoles().size()-4));
	}

	@Override
	public void moveUP() {
		if (canGo(getI(), getJ() + 1)) {
			moveTo(getI(), getJ() + 1);
		}
	}

	@Override
	public void moveDown() {

		if (canGo(getI(), getJ() - 1)) {
			moveTo(getI(), getJ() - 1);
		}
	}

	@Override
	public void moveLeft() {
		if (canGo(getI() + 1, getJ())) {
			moveTo(getI() + 1, getJ());
		}
	}

	@Override
	public void moveRight() {
		if (canGo(getI() - 1, getJ())) {
			moveTo(getI() - 1, getJ());
		}

	}

	private boolean canGo(int i, int j) {
		for (Components component : Game3D.getHoles()) {
			if (component.getI() == i && component.getJ() == j) {
				return false;
			}
		}
		if (Game3D.player.getI()==i&&Game3D.player.getJ()==j) {
			return false;
		}
		if (i < 0) {
			return false;
		}
		if (j < 0) {
			return false;
		}
		if (j >= MapConstructor.poleSize) {
			return false;
		}
		if (i >= MapConstructor.poleSize) {
			return false;
		}

		return true;
	}

	private void moveTo(int i, int j) {
		setI(i);
		setJ(j);
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		if (Game3D.player.getMoney()-this.getCostValue()<0) {
			JOptionPane.showMessageDialog(null, "You don't have enough money!");
			Game3D.creater=null;
			return;
		}
		Game3D.holes.add(new Hole(this.getI(), this.getJ()));
		Game3D.player.minusMoney(this.getCostValue());
		Game3D.creater=null;
	}
	
	@Override
	public void paint(Graphics2D g, int x, int y) {

		fillRedSqrt(g, x, y);
		new Hole(getI(), getJ()).paint(g,x,y);
	}
}
