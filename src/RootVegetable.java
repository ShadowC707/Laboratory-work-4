
public abstract class RootVegetable extends Vegetable {
	private boolean hasSoilOnSkin;

    public RootVegetable(int calories, boolean hasSoilOnSkin) {
        super(calories);
        this.hasSoilOnSkin = hasSoilOnSkin;
    }
    
    
    public boolean requiresWashing() {
        return hasSoilOnSkin;
    }
}
