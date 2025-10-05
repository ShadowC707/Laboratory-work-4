import java.io.Serializable;

public abstract class Vegetable implements Comparable<Vegetable>, Serializable {
    private int calories;

    // Конструктор класу, який приймає калорійність
    public Vegetable (int calories) {
        // Ініціалізація поля 'calories' поточного об'єкта
        // значенням, переданим у конструктор
        this.calories = calories;
    }

    // --- Гетери та сетери для доступу до полів класу ---

    // Метод (гетер) для отримання калорійності
    public int getCalories() {
        // Повертаємо значення поля 'calories' поточного об'єкта
        return this.calories;
    }

    // Метод (сетер) для встановлення нового значення калорійності
    public void setCalories(int calories){
        // Присвоюємо полю 'calories' нове значення, передане в метод
        this.calories = calories;
    }

    // Перевизначення методу compareTo з інтерфейсу Comparable
    // Цей метод визначає логіку порівняння двох об'єктів Vegetable
    @Override
    public int compareTo(Vegetable other) {
        // Використовуємо статичний метод Integer.compare для порівняння
        // калорійності поточного овоча (this) з іншим овочем (other)
        // Це стандартний спосіб порівняння примітивів int
        return Integer.compare(this.getCalories(), other.getCalories());
    }
}