
public abstract class Vegetable implements Comparable<Vegetable> {
	private int calories;
	
	public Vegetable (int calories) {
		this.calories = calories;
	}
	
	// getter and setter
	public int getCalories() {
		return this.calories;
	}
	public void setCalories(int calories){
        this.calories = calories;
	}
	
	@Override
    public int compareTo(Vegetable other) {
        return Integer.compare(this.getCalories(), other.getCalories());
    }
}
