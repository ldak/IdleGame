package components;

import java.awt.Color;
import java.awt.Graphics2D;

import GPU.Game3D;

public abstract class Components {

	private int i;
	private int j;
	
	public Components(int i, int j) {
		this.i = i;
		this.j = j;

	}

	public void paint(Graphics2D g) {
		this.paint(g,this.getXByIJ(i, j),this.getYByIJ(i, j));
	}
	
	public int getXByIJ(int i,int j){
		
		return  MapConstructor.centerX - i * MapConstructor.sqrtSize + j * MapConstructor.sqrtSize;
	}
	
	public int getYByIJ(int i,int j){
		return MapConstructor.centerY - (i + j) * MapConstructor.sqrtSize / 2;
	}

	public abstract void paint(Graphics2D g, int x, int y);
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	
	
}
