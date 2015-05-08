package csc1011;

public class Gadget extends Items implements
java.io.Serializable {
	double modifier;
	
	public static enum ModifierType {
		Travel, FightEnergy, Action
	}
	
	public Gadget(String name, String code, int price, double modifier, ModifierType mod) {
		super(name, code);
		this.setPrice(price); 
		switch (mod){
		case Travel : this.setModifierText("Travel costs x"); break;
		case FightEnergy : this.setModifierText("Crime fight energy cost x"); break;
		case Action : this.setModifierText("Action generated x"); break;
		}
		this.modifier = modifier;
	}

	@Override
	public double getModifier() {
		// TODO Auto-generated method stub
		return this.modifier;
	}

	@Override
	public void PurchaseAction() {
		// TODO Auto-generated method stub
		
	}
	
	

}
