package components.goldDiggers;

import java.awt.Color;
import java.awt.Graphics2D;

import GPU.Game3D;
import GPU.MapType;
import components.MapConstructor;

public class Minor extends GoldDiger{

	private int level = 1;
	public static int PROFIT=10;
	public Minor(int i, int j,int level) {
		super(i, j);
		this.level=level;
		
	}

	@Override
	public void paint(Graphics2D g, int x1, int y1) {
		int x=x1;
		int y=y1;
		int s = MapConstructor.sqrtSize;
		if (animating.percentsDone() == 100) {
			animating.stop();
		}
		int secondUp=0,firstUp=150;
		for (int i = 0; i < level; i++,y-=s*3/8,firstUp+=45) {
			if (animating.isStart()&&i==level-1) {
				int jumpHight=20;
				y -= jumpHight*(1-Math.abs(50-animating.percentsDone())*0.02);
				
			}
			
			if (firstUp>255) {
				secondUp+=50;
				if (secondUp>=255) {
					secondUp=255;
				}
				firstUp=255;
			}
				
			switch (Game3D.mapType) {
			case ICE:
					g.setColor(new Color(secondUp,firstUp,255));
					break;
			case VOLCAN:
					g.setColor(new Color(255,firstUp,secondUp));
					break;
			case MONEY:
					
					g.setColor(new Color(secondUp, 	firstUp, secondUp));
					break;
			case CRISTAL:
					g.setColor(new Color(firstUp, secondUp, firstUp));
					break;
			}
			g.fillPolygon(new int[] { x, (int) (x + s /4), (int) (x + s  / 4), x, (int) (x - s / 4), (int) (x - s /4) },
					new int[] { (int) (y - s*3/8) , y - s / 2, y - s*3/4 , y - s*7/8 , y - s * 3 / 4, y - s / 2, y }, 6);
			g.setColor(new Color(60, 60, 60));
			g.drawLine(x, y - s *3/8, x, y - s * 5/8);
			g.drawLine(x - s / 4, y - s * 6 / 8, x, y - s * 5/8);
			g.drawLine(x + s / 4, y - s * 6 / 8, x, y - s * 5/8);
			
			g.drawLine(x - s / 4, y - s * 4 / 8, x, y - s * 3/8);
			g.drawLine(x + s / 4, y - s * 4 / 8, x, y - s * 3/8);
			
			g.drawLine(x - s / 4, y - s * 6 / 8, x, y - s * 7 / 8);
			g.drawLine(x + s / 4, y - s * 6 / 8, x, y - s * 7 / 8);
			
		}
		
		paintProfit(g, x1, y1);
	}
	
	@Override
	public long getProfit() {
		// TODO Auto-generated method stub
		return (long) (PROFIT*(Math.pow(4, level-1))*Game3D.mapType.getMoneyMultiplier());
	}
	
	public int getLevel() {
		return level;
	}
	
	public void levelUp(){
		level++;
		
	}
	
	public void setLevel(int level) {
		this.level = level;
		
	}
}
