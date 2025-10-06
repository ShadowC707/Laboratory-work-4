// Імпорт необхідних класів з бібліотеки Java
import java.io.Serializable;
import java.util.ArrayList; // Для створення динамічного списку інгредієнтів
import java.util.List;      // Інтерфейс для роботи зі списками
import java.util.Collections; // Клас з допоміжними методами для роботи з колекціями, зокрема для сортування

// Оголошення публічного класу Salad (Салат)
public class Salad implements Serializable {
    // Приватне поле для зберігання назви салату
    private String name;
    // Список може містити будь-які об'єкти, що є нащадками класу Vegetable
    private List<Vegetable> ingredients;

    // Конструктор класу, який приймає назву салату
    public Salad(String name) {
        this.name = name;
        // Створення нового порожнього списку (ArrayList) для зберігання інгредієнтів
        this.ingredients = new ArrayList<>();
    }

    // --- Гетери та сетери для доступу до полів класу ---

    // Метод (гетер) для отримання назви салату
    public String getName() {
        return name;
    }
    // Метод (сетер) для встановлення нової назви салату
    public void setName(String name) {
        this.name = name;
    }
    // Метод (гетер) для отримання списку інгредієнтів
    public List<Vegetable> getIngredients() {
        return ingredients;
    }
    // Метод (сетер) для заміни поточного списку інгредієнтів новим
    public void setIngredients(List<Vegetable> ingredients) {
        this.ingredients = ingredients;
    }

    // --- Основні методи для роботи з салатом ---

    // Метод для обчислення загальної калорійності салату
    public int getTotalCalories() throws EmptySaladException {

        // Перевіряємо, чи список інгредієнтів не порожній
        if(this.ingredients.isEmpty()){
            // Якщо список порожній, генеруємо та кидаємо виняток з відповідним повідомленням
            throw new EmptySaladException("Cannot find total calories, because salad is empty!");
        }
        // Ініціалізація акумулятора (змінної для підсумовування) для загальної калорійності
        int total = 0;

        // Цикл 'for-each' для перебору кожного овоча (v) у списку ingredients
        for (Vegetable v : ingredients) {
            // Додаємо калорійність поточного овоча до загальної суми
            total += v.getCalories();
        }
        // Повертаємо обчислену загальну калорійність
        return total;
    }

    // Метод для додавання нового інгредієнта (овоча) до салату
    public void addIngredient(Vegetable vegetable) {
        // Додаємо переданий об'єкт 'vegetable' до списку інгредієнтів
        this.ingredients.add(vegetable);
    }

    // Метод для сортування інгредієнтів за калорійністю (від меншої до більшої)
    public void sortIngredientsByCalories() throws  EmptySaladException{

        // Перевіряємо, чи список інгредієнтів не порожній
        if(this.ingredients.isEmpty()){
            // Якщо так, генеруємо виняток
            throw new EmptySaladException("Cannot sort ingredients, because salad is empty!");
        }

        // Виклик статичного методу sort з класу Collections для сортування списку 'ingredients'
        Collections.sort(ingredients);
    }

    // Метод для пошуку інгредієнтів, калорійність яких знаходиться у заданому діапазоні
    public List<Vegetable> findIngredientsByCalorieRange(int min, int max) throws EmptySaladException {

        // Перевірка на наявність інгредієнтів у салаті
        if(this.ingredients.isEmpty()) {
            // Якщо інгредієнтів немає, кидаємо виняток
            throw new EmptySaladException("Cannot find ingredient by calorie range, because salad is empty!");
        }

        // Створення нового порожнього списку для зберігання результатів пошуку
        List<Vegetable> result = new ArrayList<>();

        // Ітерація по кожному овочу в списку інгредієнтів
        for (Vegetable v : ingredients) {
            // Перевіряємо, чи калорійність поточного овоча (v) входить у заданий діапазон [min, max]
            if (v.getCalories() >= min && v.getCalories() <= max) {
                // Якщо умова виконується, додаємо цей овоч до списку результатів
                result.add(v);
            }
        }

        // Повертаємо список знайдених інгредієнтів
        return result;
    }

    // Метод для виведення списку інгредієнтів салату на консоль
    public void displayIngredients() throws EmptySaladException {
        // Перевіряємо, чи є в салаті інгредієнти
        if(this.ingredients.isEmpty()) {
            // Якщо немає, кидаємо виняток
            throw new EmptySaladException("Cannot display ingredients, because salad is empty!");
        }
        // Виведення заголовка з назвою салату
        System.out.println("--- Ingredients of '" + this.name + "' salad ---");

        // Цикл для перебору та виведення кожного інгредієнта
        for (Vegetable v : ingredients) {
            // Для кожного овоча виводимо його назву класу (напр., "Tomato"),
            // калорійність та одиниці виміру ("kcal")
            System.out.println(v.getClass().getSimpleName() + ": " + v.getCalories() + " kcal");
        }
    }
}