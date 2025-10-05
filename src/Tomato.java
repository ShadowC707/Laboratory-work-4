// Оголошення публічного класу Tomato (Томат), який наслідує клас Nightshade (Пасльонові)
public class Tomato extends Nightshade {
    // Приватне поле для зберігання кольору томата
    private String color;

    // Конструктор класу, який приймає колір та калорійність
    public Tomato(String color, int calories) {
        // Виклик конструктора батьківського класу (Nightshade)
        // Передаємо калорійність та встановлюємо фіксований тип росту - кущ (BUSH)
        super(calories, GrowthType.BUSH);

        // Ініціалізація поля 'color' поточного об'єкта
        // значенням, переданим у конструктор
        this.color = color;
    }

    // --- Гетери та сетери для доступу до полів класу ---

    // Метод (гетер) для отримання кольору томата
    public String getColor() {
        // Повертаємо значення поля 'color' поточного об'єкта
        return this.color;
    }

    // Метод (сетер) для встановлення нового кольору томата
    public void setColor(String color) {
        // Присвоюємо полю 'color' нове значення, передане в метод
        this.color = color;
    }

}