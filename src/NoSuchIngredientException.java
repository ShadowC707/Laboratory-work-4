public class NoSuchIngredientException extends RuntimeException {
    // Конструктор класу, що приймає повідомлення про помилку
    public NoSuchIngredientException(String message) {
        // Виклик конструктора батьківського класу (RuntimeException)
        // для ініціалізації повідомлення про помилку
        super(message);
    }
}