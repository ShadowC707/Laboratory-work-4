// Оголошення публічного абстрактного класу RootVegetable (Коренеплід)
// Цей клас наслідує (розширює) клас Vegetable
public abstract class RootVegetable extends Vegetable {

    // Приватне поле для зберігання інформації, чи є на шкірці овоча земля
    private boolean hasSoilOnSkin;

    // Конструктор класу, який приймає калорійність та наявність землі на шкірці
    public RootVegetable(int calories, boolean hasSoilOnSkin) {
        // Виклик конструктора батьківського класу (Vegetable)
        // для ініціалізації поля калорійності
        super(calories);

        // Ініціалізація поля 'hasSoilOnSkin' поточного об'єкта
        // значенням, переданим у конструктор
        this.hasSoilOnSkin = hasSoilOnSkin;
    }

    // Метод для перевірки, чи потребує овоч миття
    public boolean requiresWashing() {
        // Повертаємо значення поля 'hasSoilOnSkin'
        // Якщо на шкірці є земля (true), то овоч потребує миття
        return hasSoilOnSkin;
    }
}