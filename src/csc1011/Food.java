package csc1011;

public class Food extends Items implements
java.io.Serializable {
	double energyRestore;
	
	public Food(String name, String code, int price, double energyRestore) {
		super(name, code);
		this.energyRestore = energyRestore;
		this.setPrice(price); 
		this.setModifierText("Increases energy by ");
	}

	@Override
	public double getModifier() {
		return energyRestore;
	}

	@Override
	public void PurchaseAction() {
		// TODO Auto-generated method stub
		
	}

}
