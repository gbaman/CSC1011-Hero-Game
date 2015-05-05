package csc1011;

public class EnergyCountDown implements Runnable {
	
	MainMenu m;
	Character c;
	
	public EnergyCountDown(MainMenu m){
    	this.m = m;
    }
	
    public void run() {
    	//System.out.println(m.getEnergy());
    	while (m.getEnergy() > 0 ){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (m.deductEnergy){
				m.setEnergy(m.getEnergy() - 1);
			}
			
		}
    	m.displayEnd();
    }
 
}
