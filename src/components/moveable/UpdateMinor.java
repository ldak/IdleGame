package components.moveable;

import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JOptionPane;

import GPU.Game3D;
import components.Components;
import components.goldDiggers.GoldDiger;
import components.goldDiggers.Hole;
import components.goldDiggers.Minor;

public class UpdateMinor extends Creaters {

	public static final int COST=30*5*10;
	public UpdateMinor() throws NoMinorsException {
		// TODO Auto-generated constructor stub
		this(getLowestLevelMinor().getI(),getLowestLevelMinor().getJ());
	}

	private static Minor getLowestLevelMinor()throws NoMinorsException {
		if (Game3D.getMinors().size()==0) {
			throw new NoMinorsException();
		}
		Minor lowest=Game3D.getMinors().get(0);
		for (Minor el : Game3D.getMinors()) {
			if (lowest.getLevel()>el.getLevel()) {
				lowest=el;
			}
		}
		return lowest;
	}

	public UpdateMinor(int i, int j) {
		super(i, j);
		
	}

	@Override
	public void moveUP() {
		nextMinor();
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		previousMinor();
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		previousMinor();
	}

	@Override
	public void moveRight() {
		nextMinor();

	}

	private void nextMinor() {
		List<Minor> list = Game3D.getMinors();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getI() == this.getI() && list.get(i).getJ() == this.getJ()) {
					this.setI(list.get(i + 1).getI());
					this.setJ(list.get(i + 1).getJ());
					break;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			int i = 0;
			this.setI(list.get(i).getI());
			this.setJ(list.get(i).getJ());

		}

	}

	private void previousMinor() {
		// TODO Auto-generated method stub
		List<Minor> list = Game3D.getMinors();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getI() == this.getI() && list.get(i).getJ() == this.getJ()) {
					this.setI(list.get(i - 1).getI());
					this.setJ(list.get(i - 1).getJ());
					break;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			int i = list.size() - 1;
			this.setI(list.get(i).getI());
			this.setJ(list.get(i).getJ());

		}
	}

	@Override
	public void create() {
		if (Game3D.player.getMoney() - this.getCostValue() < 0) {
			JOptionPane.showMessageDialog(null, "You don't have enough money!");
			Game3D.creater = null;
			return;
		}
		Game3D.player.minusMoney(this.getCostValue());
		this.getMinor().levelUp();
		Game3D.creater = null;
		
	}

	@Override
	public void paint(Graphics2D g, int x, int y) {
		fillRedSqrt(g, x, y);
		new Minor(getI(), getJ(), this.getMinor().getLevel()+1).paint(g, x, y);
	}
	
	@Override
	public long getCostValue() {
	// TODO Auto-generated method stub
		return (long) (Math.pow(4,getMinor().getLevel()-1)*COST+(1*1024));
	}
	
	private int getNumbberUpdates() {
		// TODO Auto-generated method stub
		int br=0;
		for (Minor el : Game3D.getMinors()) {
			if (el.getLevel()>this.getMinor().getLevel()) {
				br++;
			}
		}
		return br;
	}

	public Minor getMinor(){
		for (Minor el : Game3D.getMinors()) {
			if (el.getI()==this.getI()&&el.getJ()==this.getJ()) {
				return el;
			}
		}
		return null;
	}
}
