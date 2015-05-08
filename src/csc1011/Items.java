package csc1011;

public abstract class Items implements
java.io.Serializable {
	
    private String name;
    private String code;
    private String ModifierText;
    private int price;

    public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Items(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
    abstract public double getModifier();
    
    abstract public void PurchaseAction();

    public String getModifierText(){
    	return this.ModifierText;
    }
    public void setModifierText(String value){
    	this.ModifierText = value;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() { 
        return name; 
    }    
	
	
}
