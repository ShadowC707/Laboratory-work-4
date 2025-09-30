
public abstract class Nightshade extends Vegetable {
	public enum GrowthType { BUSH, VINE } // enum to show what growth type vegetable has

    private GrowthType growthHabit;

    public Nightshade(int calories, GrowthType growthHabit) {
        super(calories);
        this.growthHabit = growthHabit;
    }

    // only getter, because all nightshades have growth habit
    public GrowthType getGrowthHabit() {
        return growthHabit;
    }
}
