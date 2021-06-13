package GPU;

public enum MapType {
	ICE(1), VOLCAN(0), MONEY(10), CRISTAL(5);
	
	private int moneyMultiplier=0;
	
	MapType(int a){
		this.moneyMultiplier=a;
	}
	
	public int getMoneyMultiplier() {
	
		return moneyMultiplier;
	}
	
}
