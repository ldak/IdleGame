package components.goldDiggers;

import java.awt.Color;
import java.awt.Graphics2D;

import GPU.Game3D;
import components.Components;
import components.CoolDown;
import components.MapConstructor;

public abstract class GoldDiger extends Components{
	
	public CoolDown animating=new CoolDown();
	
	private long profit;
	
	public GoldDiger(int i, int j) {
		super(i, j);
		
	}
	
	public void updateOnPlayerMove() {
		Game3D.player.addMoney(getProfit());
		animating.start();
	}
	
	public long getProfit() {
		return profit;
	}
	
	public void setProfit(long profit) {
		this.profit = profit;
	}
	
	public void paintProfit(Graphics2D g,int x1,int y1) {
		int x=x1;
		int y=y1;
		g.setColor(Color.GREEN);
		if (animating.isStart()) {
			int startX=x+MapConstructor.sqrtSize/2;
			int startY=y-MapConstructor.sqrtSize/4;
			x=startX ;
			y=startY -(50*animating.percentsDone())/100;
			g.drawString(MapConstructor.convertMoney(getProfit())+"$", x, y);
		}
	}
	public static int compare(GoldDiger g1, GoldDiger g2) {
				return Integer.compare(MapConstructor.sumIndex(g1),MapConstructor.sumIndex(g2));
		
	}
}
