package csc1011;

public class DialogClose implements Runnable {

	CustomDialog d;
	int countdown;
	String baseMessage;
	
	public DialogClose(CustomDialog d, int countdown, String baseMessage){
		this.d = d;
		this.countdown = countdown;
		this.baseMessage = baseMessage;
	}
	
	@Override
	public void run() {
		int x = countdown;
		while (x > 0){
			try {
				String message = this.baseMessage +"<br>\n<br>\n  " + x + " seconds remaining. </html>";
				System.out.println(message);
				this.d.lblACrimeIs.setText(message);
				Thread.sleep(1000);
				x = x - 1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.d.dispose();
		
	}

}
