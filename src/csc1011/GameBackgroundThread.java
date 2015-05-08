package csc1011;

public class GameBackgroundThread implements Runnable {
	
	MainMenu m;
	
	public GameBackgroundThread(MainMenu m){
		this.m = m;
	}
	
	@Override
	public void run() {
		int modifier = 0;
		System.out.println("Action " + m.getAction());
		while (m.GameRunning == true){
			if (m.getBackgroundCrime() == true){
				m.UpdateMap();
				System.out.println("Looping background. Modifier - "+ modifier );
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (m.btnSleep.getText() == "Awake"){
				m.progressBarEnergy.setValue(m.progressBarEnergy.getValue()+8);
				if (m.progressBarEnergy.getValue() == 100){
					m.endNewSleep();
				}
			}
			if (m.getEnergy() > 20){
				int rand;
				if (m.getBackgroundCrime() == true){
					if (m.isEasyMode() == true){
						rand = m.getRandom(20,70) + modifier;
					}else{
						rand = m.getRandom(50,80) + modifier;
					}
					System.out.println("Running random gen " + rand);
					if (rand > 85){
						System.out.println(rand);
						//m.GenerateCrime();
						m.MapCrime();
						modifier = 0;
					}else{
						modifier = modifier + 5;
					}
				}
			}
		}
		
	}

}
