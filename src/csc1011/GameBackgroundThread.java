package csc1011;

public class GameBackgroundThread implements Runnable {
	
	MainMenu m;
	
	public GameBackgroundThread(MainMenu m){
		this.m = m;
	}
	
	@Override
	public void run() {
		while (m.GameRunning == true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (m.getEnergy() > 20){
				if (m.getBackgroundCrime() == true){
					int rand = m.getRandom(1,100);
					if (rand > 85){
						System.out.println(rand);
						m.GenerateCrime();
					}
				}
			}
		}
		
	}

}
