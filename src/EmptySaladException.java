public class EmptySaladException extends RuntimeException {
    // Конструктор класу, що приймає повідомлення про помилку
    public EmptySaladException(String message) {
        // Виклик конструктора батьківського класу (RuntimeException)
        // для збереження та передачі повідомлення про помилку
        super(message);
    }
}