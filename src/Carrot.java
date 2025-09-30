
public class Carrot extends RootVegetable {
	private int length;

    public Carrot(int length, int calories) {
        super(calories, true);
        this.length = length;
    }
    
    // getters and setters
    public int getLength() {
    	return length;
    }
    public void setLength(int length) {
    	this.length = length;
    }
}
