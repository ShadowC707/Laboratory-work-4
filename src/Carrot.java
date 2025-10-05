// Оголошення публічного класу Carrot, який наслідує (розширює) клас RootVegetable
public class Carrot extends RootVegetable {
    // Оголошення приватного поля для зберігання довжини моркви
    private int length;

    // Конструктор класу, який приймає довжину та калорійність
    public Carrot(int length, int calories) {
        // Виклик конструктора батьківського класу (RootVegetable)
        // для ініціалізації калорійності та їстівності
        super(calories, true);

        // Ініціалізація поля 'length' поточного об'єкта
        // значенням, переданим у конструктор
        this.length = length;
    }

    // --- Гетери та сетери для доступу до полів класу ---

    // Метод (гетер) для отримання значення довжини
    public int getLength() {
        // Повертаємо значення поля 'length'
        return length;
    }

    // Метод (сетер) для встановлення нового значення довжини
    public void setLength(int length) {
        // Присвоюємо полю 'length' нове значення, передане в метод
        this.length = length;
    }
}