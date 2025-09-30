
public class Tomato extends Nightshade {
    private String color;

    public Tomato(String color, int calories) {
        super(calories, GrowthType.BUSH);
        this.color = color;
    }

    // getters and setter
    public String getColor() {
    	return this.color;
    }
    public void setColor(String color) {
    	this.color = color;
    }
    
}
