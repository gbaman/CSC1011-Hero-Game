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
			try {
				while (x > 0  && !Thread.currentThread().isInterrupted()){
					String message = this.baseMessage +"<br>\n<br>\n  " + x + " seconds remaining. </html>";
					this.d.lblACrimeIs.setText(message);
					Thread.sleep(1000);
					x = x - 1;
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		d.dispose();	
		return;
		
	}
	

}
