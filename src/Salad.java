import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Salad {
    private String name;
    private List<Vegetable> ingredients;

    public Salad(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Vegetable> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Vegetable> ingredients) {
        this.ingredients = ingredients;
    }

    // methods
    public int getTotalCalories() throws EmptySaladException {
        if(this.ingredients.isEmpty()){
            throw new EmptySaladException("Cannot find total calories, because salad is empty!");
        }
        int total = 0;
        for (Vegetable v : ingredients) {
            total += v.getCalories();
        }
        return total;
    }

    public void addIngredient(Vegetable vegetable) {
        this.ingredients.add(vegetable);
    }

    public void sortIngredientsByCalories() throws  EmptySaladException{
        if(this.ingredients.isEmpty()){
            throw new EmptySaladException("Cannot sort ingredients, because salad is empty!");
        }
        Collections.sort(ingredients);
    }
    
    public List<Vegetable> findIngredientsByCalorieRange(int min, int max) throws EmptySaladException {
        if(this.ingredients.isEmpty()) {
            throw new EmptySaladException("Cannot find ingredient by calorie range, because salad is empty!");
        }

        List<Vegetable> result = new ArrayList<>();
        // iterating through ingredients, finding ones that fit [min, max] range
        for (Vegetable v : ingredients) {
            if (v.getCalories() >= min && v.getCalories() <= max) {
                result.add(v);
            }
        }
        return result;
    }
    
    public void displayIngredients() throws EmptySaladException {
        if(this.ingredients.isEmpty()) {
            throw new EmptySaladException("Cannot display ingredients, because salad is empty!");
        }
        System.out.println("--- Ingredients of '" + this.name + "' salad ---");
        
        // displaying each element of collection
        for (Vegetable v : ingredients) {
            System.out.println(v.getClass().getSimpleName() + ": " + v.getCalories() + " kcal");
        }
    }
}