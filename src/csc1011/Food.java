package csc1011;

public class Food extends Items {
	int energyRestore;
	
	public Food(String name, String code, int price, int energyRestore) {
		super(name, code);
		this.energyRestore = energyRestore;
		this.setPrice(price); 
		this.setModifierText("Increases energy by ");
	}

	@Override
	public int getModifier() {
		return energyRestore;
	}

	@Override
	public void PurchaseAction() {
		// TODO Auto-generated method stub
		
	}

}
