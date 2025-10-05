// Оголошення абстрактного класу Nightshade (Пасльонові), який наслідує клас Vegetable
public abstract class Nightshade extends Vegetable {

    // Оголошення публічного переліку (enum) GrowthType
    // Він визначає можливі типи росту для рослин цього класу
    public enum GrowthType {
        BUSH, // Тип росту: кущ
        VINE  // Тип росту: ліана/в'юнка рослина
    }

    // Оголошення приватного поля для зберігання типу росту конкретного овоча
    private GrowthType growthHabit;

    // Конструктор класу, приймає калорійність та тип росту
    public Nightshade(int calories, GrowthType growthHabit) {
        // Виклик конструктора батьківського класу (Vegetable) для ініціалізації калорійності
        super(calories);

        // Ініціалізація поля growthHabit значенням, переданим у конструктор
        this.growthHabit = growthHabit;
    }

    // --- Гетер для доступу до поля класу ---
    // (Сетер відсутній, оскільки передбачається, що тип росту є незмінною характеристикою)

    // Метод (гетер) для отримання типу росту
    public GrowthType getGrowthHabit() {
        // Повертаємо значення поля growthHabit
        return growthHabit;
    }
}