package csc1011;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SuperheroGameController {
	
	MainMenu m;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
					frame.frame = frame;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		//MainMenu.main1();
		//MainMenu frame = new MainMenu();
		//frame.setVisible(true);
		
		//MainMenu frame;
		//frame = LoadGame();
		//System.out.println(frame.getAction());
		//System.out.println(frame.frame.getAction());
		//frame.setVisible(true);
		//frame.c.
		
	}

	
	public static MainMenu LoadGame(){
		FileInputStream f_in;
		try {
			f_in = new FileInputStream("myobject.data");
			// Read object using ObjectInputStream
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
			// Read an object
			Object obj = obj_in.readObject();

			if (obj instanceof MainMenu)
			{
				// Cast object to a Vector
				MainMenu frame = (MainMenu) obj;
				obj_in.close();
				return frame;

				// Do something with vector....
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	
	public void main1() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
