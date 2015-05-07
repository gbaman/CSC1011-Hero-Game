package csc1011;

public class Clothing extends Items {
	int fightSuccess;
	

	public Clothing(String name, String code, int price, int fightSuccess) {
		super(name, code);
		this.fightSuccess = fightSuccess;
		this.setPrice(price); 
		this.setModifierText("Increases chance of fight success by ");
	}

	@Override
	public int getModifier() {
		return this.fightSuccess;
	}

}
