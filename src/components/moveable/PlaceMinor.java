package components.moveable;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.swing.JOptionPane;

import GPU.Game3D;
import GPU.helpMenus.MessageBox;
import components.Components;
import components.MapConstructor;
import components.goldDiggers.GoldDiger;
import components.goldDiggers.Hole;
import components.goldDiggers.Minor;

public class PlaceMinor extends Creaters {

	public PlaceMinor() throws FullBoardException {
		// TODO Auto-generated constructor stub
		this(getEmptyHoleI(), getEmptyHoleJ());
	}

	private static int getEmptyHoleJ() throws FullBoardException {
		for (GoldDiger el : Game3D.getHoles()) {

			if (!hasMinorWith(el.getI(), el.getJ())) {
				return el.getJ();
			}
		}

		throw new FullBoardException();
	}

	private static int getEmptyHoleI() throws FullBoardException {
		// TODO Auto-generated method stub
		for (GoldDiger el : Game3D.getHoles()) {

			if (!hasMinorWith(el.getI(), el.getJ())) {
				return el.getI();
			}
		}

		throw new FullBoardException();
	}

	private static boolean hasMinorWith(int i, int j) {
		for (Components el : Game3D.getMinors()) {
			if (el.getI() == i && el.getJ() == j) {

				return true;
			}
		}
		return false;
	}

	public PlaceMinor(int i, int j) {
		super(i, j);
		super.costValue = Game3D.getMinors().size() * 10 * (15 + Game3D.getMinors().size()) + 15 * 5;

	}

	@Override
	public void moveUP() {
		nextHole();
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		previousHole();
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		previousHole();
	}

	@Override
	public void moveRight() {
		nextHole();

	}

	private void nextHole() {
		List<Hole> list = Game3D.getHoles();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getI() == this.getI() && list.get(i).getJ() == this.getJ()) {
					this.setI(list.get(i + 1).getI());
					this.setJ(list.get(i + 1).getJ());
					while (hasMinorWith(this.getI(), this.getJ())) {
						i++;
						this.setI(list.get(i).getI());
						this.setJ(list.get(i).getJ());
					}
					break;
				}
			}

		} catch (IndexOutOfBoundsException e) {
			int i = 0;
			do {
				this.setI(list.get(i).getI());
				this.setJ(list.get(i).getJ());
				i++;
			} while (hasMinorWith(this.getI(), this.getJ()));
		}

	}

	private void previousHole() {
		// TODO Auto-generated method stub
		List<Hole> list = Game3D.getHoles();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getI() == this.getI() && list.get(i).getJ() == this.getJ()) {
					this.setI(list.get(i - 1).getI());
					this.setJ(list.get(i - 1).getJ());
					while (hasMinorWith(this.getI(), this.getJ())) {
						i--;
						this.setI(list.get(i).getI());
						this.setJ(list.get(i).getJ());
					}
					break;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			int i = list.size() - 1;
			do {
				this.setI(list.get(i).getI());
				this.setJ(list.get(i).getJ());
				i--;
			} while (hasMinorWith(this.getI(), this.getJ()));

		}
	}

	@Override
	public void create() {
		if (Game3D.player.getMoney() - this.getCostValue() < 0) {
			MessageBox frame = new MessageBox(Game3D.getInstance().getLocationOnScreen().x / 2 - MessageBox.width / 2,
					Game3D.getInstance().getLocationOnScreen().y / 2 - MessageBox.height / 2, "Error",
					"You don't have enough money.");
			frame.setVisible(true);
			Game3D.creater = null;
			return;
		}
		Game3D.minors.add(new Minor(this.getI(), this.getJ(), 1));
		Game3D.minors.sort(GoldDiger::compare);
		Game3D.player.minusMoney(this.getCostValue());
		Game3D.creater = null;
	}

	@Override
	public void paint(Graphics2D g, int x, int y) {
		fillRedSqrt(g, x, y);
		new Minor(getI(), getJ(), 1).paint(g, x, y);
	}

}
