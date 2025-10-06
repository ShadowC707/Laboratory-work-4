public class InvalidInputException extends RuntimeException {
    // Конструктор класу, що приймає повідомлення про помилку
    public InvalidInputException(String message) {
        // Виклик конструктора батьківського класу (RuntimeException)
        // для ініціалізації повідомлення про помилку
        super(message);
    }
}