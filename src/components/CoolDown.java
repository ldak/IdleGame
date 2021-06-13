package components;

public class CoolDown {
	
	public static long time=850;
	private long priv;
	private boolean start=false;
	
	public boolean isStart() {
		return start;
	}
	
	public void start(){
		start=true;
		priv=System.currentTimeMillis();
	}
	
	public void stop(){
		start=false;
	}
	
	public int percentsDone(){
		double a=System.currentTimeMillis()-priv;
		int percents=(int)((a/time)*100);
		if (percents>100) {
			return 100;
		}
		return (int)((a/time)*100);
	}
}
