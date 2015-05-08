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
			m.UpdateMap();
			System.out.println("Looping background. Modifier - "+ modifier );
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (m.getEnergy() > 20){
				if (m.getBackgroundCrime() == true){
					int rand = m.getRandom(20,70) + modifier;
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
