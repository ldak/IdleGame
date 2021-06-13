package components.moveable;

import java.awt.Color;
import java.awt.Graphics2D;

import GPU.Game3D;
import components.Components;
import components.MapConstructor;

public abstract class Creaters extends Components{

	
	public long costValue;
	
	public Creaters(int i, int j) {
		super(i, j);
	}
	
	public abstract void moveUP() ;

	public abstract void moveDown() ;

	public abstract void moveLeft() ;

	public abstract void moveRight() ;
	
	public abstract void create();
	
	public void fillRedSqrt(Graphics2D g,int x,int y){
		g.setColor(new Color(255,75,75));
		int [] xM={
				x,
				x+MapConstructor.sqrtSize,
				x,
				x-MapConstructor.sqrtSize,
		};
		int[] yM={
				y,
				y-MapConstructor.sqrtSize/2,
				y-MapConstructor.sqrtSize,
				y-MapConstructor.sqrtSize/2
		};
		g.fillPolygon(xM,yM,4);
	}
	
	public void paintCost(Graphics2D g,int x,int y){
		g.setColor(new Color(255,100,100));
		g.drawString("- "+MapConstructor.convertMoney(getCostValue())+"$" , x, y);
	}
	
	public long getCostValue() {
		return costValue;
	}
	
	public void setCostValue(long costValue) {
		this.costValue = costValue;
	}
	
	
}
