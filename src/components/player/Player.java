package components.player;

import java.awt.Color;
import java.awt.Graphics2D;

import GPU.Game3D;
import GPU.MapType;
import components.Components;
import components.CoolDown;
import components.MapConstructor;
import components.goldDiggers.GoldDiger;

public class Player extends Components {

	private long money = 0;
	private int oldI;
	private int oldJ;
	public CoolDown animating = new CoolDown();

	public Player(int i, int j) {
		super(i, j);
	}

	public void paint(Graphics2D g, int x1, int y1) {

		int x = x1;
		int y = y1;
		if (animating.percentsDone() == 100) {
			animating.stop();
		}
		if (animating.isStart()) {
			int oldX = getXByIJ(oldI, oldJ);
			int oldY = getYByIJ(oldI, oldJ);
			x = oldX + ((x - oldX) * animating.percentsDone()) / 100;
			y = oldY + ((y - oldY) * animating.percentsDone()) / 100;
			// light jump
			double hD = Math.abs(y1 - oldY) / 2; // half of the whole distance
			double mD = Math.abs(oldY - y); // what distance have moved
			double percents = (mD / (hD * 2)) * 100;
			double jumpHigh = 4;
			y -= (hD - Math.abs(hD - mD)) * ((Math.abs(50 - percents) * 0.02) * jumpHigh);
		}
		g.setColor(new Color(0, 200, 0));
		int s = MapConstructor.sqrtSize;
		g.fillPolygon(new int[] { x, x + s - s / 2, x + s - s / 2, x, x - s + s / 2, x - s + s / 2 },
				new int[] { y - s / 4, y - s / 2, y - s * 3 / 2, y - s * 2 + s / 4, y - s * 3 / 2, y - s / 2, y }, 6);
		g.setColor(new Color(0, 255, 0));
		g.drawLine(x, y - s / 4, x, y - s * 5 / 4);
		g.drawLine(x - s / 2, y - s * 3 / 2, x, y - s * 5 / 4);
		g.drawLine(x + s / 2, y - s * 3 / 2, x, y - s * 5 / 4);
		g.drawLine(x - s / 2, y - s * 3 / 2, x, y - s * 7 / 4);
		g.drawLine(x + s / 2, y - s * 3 / 2, x, y - s * 7 / 4);

	}

	public void moveUP() {
		if (canGo(getI(), getJ() + 1)) {
			moveTo(getI(), getJ() + 1);
		}
	}

	public void moveDown() {

		if (canGo(getI(), getJ() - 1)) {
			moveTo(getI(), getJ() - 1);
		}
	}

	public void moveLeft() {
		if (canGo(getI() + 1, getJ())) {
			moveTo(getI() + 1, getJ());
		}
	}

	public void moveRight() {
		if (canGo(getI() - 1, getJ())) {
			moveTo(getI() - 1, getJ());
		}

	}

	public boolean canGo(int i, int j) {
		// TODO Auto-generated method stub
		if (animating.isStart()) {
			return false;
		}
		for (Components component : Game3D.getHoles()) {
			if (component.getI() == i && component.getJ() == j) {
				return false;
			}
		}
		for (Components component : Game3D.getMinors()) {
			if (component.getI() == i && component.getJ() == j) {
				return false;
			}
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

		animating.start();
		oldI = this.getI();
		oldJ = this.getJ();
		updateAll();
		return true;
	}

	public void moveTo(int i, int j) {
		setI(i);
		setJ(j);
	}

	private void updateAll() {
		for (GoldDiger component : Game3D.getHoles()) {
			component.updateOnPlayerMove();
		}
		for (GoldDiger component : Game3D.getMinors()) {
			component.updateOnPlayerMove();
		}
		if (Game3D.mapType==MapType.VOLCAN) {
			addMoney(1*1024*1024);
		}
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public long getMoney() {
		return money;
	}

	public void addMoney(long a) {
		this.money += a;
	}

	public void minusMoney(long a) {
		this.money -= a;
	}
}
