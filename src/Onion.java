// Оголошення публічного класу Onion (Цибуля), який розширює клас RootVegetable
public class Onion extends RootVegetable {
    // Оголошення приватного поля для зберігання сорту цибулі
    private String variety;

    // Конструктор класу, який приймає сорт та калорійність
    public Onion(String variety, int calories) {
        // Виклик конструктора батьківського класу (RootVegetable)
        // Встановлюємо калорійність та вказуємо, що овоч не є їстівним у сирому вигляді (false)
        super(calories, false);

        // Ініціалізація поля 'variety' поточного об'єкта
        // значенням, переданим у конструктор
        this.variety = variety;
    }

    // --- Гетери та сетери для доступу до полів класу ---

    // Метод (сетер) для встановлення нового сорту цибулі
    public void setVariety(String variety) {
        // Присвоюємо полю 'variety' нове значення, передане в метод
        this.variety = variety;
    }

    // Метод (гетер) для отримання сорту цибулі
    public String getVariety() {
        // Повертаємо значення поля 'variety' поточного об'єкта
        return this.variety;
    }
}