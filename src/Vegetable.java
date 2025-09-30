
public abstract class Vegetable implements Comparable<Vegetable> {
	private int calories;
	
	public Vegetable (int calories) {
		this.calories = calories;
	}
	
	// getter and setter
	public int getCalories() {
		return this.calories;
	}
	public void setCalories(int calories) throws NegativeCaloriesException {
		if(calories < 0){ // ADDED EXCEPTION
            throw new NegativeCaloriesException("Cannot assign negative value '" + calories + "' to Vegetable.");
        }
        this.calories = calories;
	}
	
	@Override
    public int compareTo(Vegetable other) {
        return Integer.compare(this.getCalories(), other.getCalories());
    }
}
