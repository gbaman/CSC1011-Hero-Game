package csc1011;

public class Clothing extends Items implements
java.io.Serializable {
	double fightSuccess;
	

	public Clothing(String name, String code, int price, double fightSuccess) {
		super(name, code);
		this.fightSuccess = fightSuccess;
		this.setPrice(price); 
		this.setModifierText("Increases chance of success by ");
	}

	@Override
	public double getModifier() {
		return this.fightSuccess;
	}

	@Override
	public void PurchaseAction() {
		// TODO Auto-generated method stub
		
	}

}
