package components.goldDiggers;

import java.awt.Color;
import java.awt.Graphics2D;

import GPU.Game3D;
import components.Components;
import components.MapConstructor;

public class Hole extends GoldDiger{
	
	public Hole(int i, int j) {
		super(i, j);
		setProfit(1);
	}
	
	@Override
	public void updateOnPlayerMove() {
		// TODO Auto-generated method stub
		if (!isThereMinor(getI(),getJ())) {
			super.updateOnPlayerMove();
		}
		
	}
	
	private boolean isThereMinor(int i, int j) {
		// TODO Auto-generated method stub
		for (Components el : Game3D.getMinors()) {
			if (el.getI()==i&&el.getJ()==j) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void paint(Graphics2D g, int x1, int y1) {
		// TODO Auto-generated method stub
		int x=x1;
		int y=y1;
		int s=MapConstructor.sqrtSize;
		g.setColor(Color.WHITE);
		g.fillPolygon(new int[]{x,x-s/2,x,x+s/2}, new int []{y-s/4,y-s/2,y-s*3/4,y-s/2}, 4);
		
		if (animating.percentsDone()==100) {
			animating.stop();
		}
		paintProfit(g, x1, y1);
		

	}
	
	
	
	
}
