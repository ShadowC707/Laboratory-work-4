/*
 * Лабораторна робота №4
 * Виконав: Добринін Олексій КІ-43
 * Виконати наступне завдання шляхом модифікації класів минулої лабораторної роботи:
 * 1. Визначити свою систему класів Exception (мінімум 2) та здійснити обробку помилок в програмі.
 * 2. Створити консольне меню для роботи з класами з можливістю збереження інформації у файл та зчитування з нього (використовуючи механізм сериалізації).
 * 3. При роботі консольного меню для збереження множини об'єктів використовувати одну з реалізації колекції (LIST в цьому варіанті)
 * Логіку меню підлаштувати під колекцію, що використовується.
 */

import java.io.*; // Класи для роботи з потоками вводу/виводу (для файлів)
import java.util.InputMismatchException; // Клас для обробки помилок невідповідності типів при вводі
import java.util.List;
import java.util.Scanner;

// --- Головний клас програми ---
public class Main {
    // 'final' - що посилання на нього не можна змінити
    private static final Scanner scanner = new Scanner(System.in);

    // Стала для візуального розділення блоків у консолі для кращої читабельності
    private static final String LINE = "-----------------------------------";

    // --- Точка входу в програму ---
    public static void main(String[] args) {
        // Вітальне повідомлення для користувача
        System.out.println("Welcome to the Vegetable Calculator");
        System.out.println("Please enter the salad name:");

        // Зчитування назви салату, введеної користувачем
        String saladName = scanner.nextLine();
        // Створення нового об'єкта Salad з введеною назвою
        Salad salad = new Salad(saladName);

        // Головний нескінченний цикл програми, який працює, доки користувач не вибере опцію виходу
        while (true) {
            // Виведення меню доступних опцій
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View Vegetables and calories");
            System.out.println("2. Add Vegetable");
            System.out.println("3. Remove Vegetable");
            System.out.println("4. Save salad to a file");
            System.out.println("5. Load salad from a file");
            System.out.println("6. Sort salad by Calories");
            System.out.println("0. Exit");

            // try-catch для обробки можливих винятків під час виконання операцій
            try {
                // Виклик методу для отримання цілочисельного вводу від користувача
                int choice = getIntInput();
                // Очищення буфера сканера після зчитування числа, щоб уникнути проблем з наступним nextLine()
                scanner.nextLine();

                // Оператор вибору для обробки введеної користувачем опції
                switch (choice) {
                    case 1:
                        viewIngredients(salad); // Опція 1: Перегляд інгредієнтів
                        break;
                    case 2:
                        insertIngredient(salad); // Опція 2: Додавання інгредієнта
                        break;
                    case 3:
                        removeIngredient(salad); // Опція 3: Видалення інгредієнта
                        break;
                    case 4:
                        saveToFile(salad); // Опція 4: Збереження салату у файл
                        break;
                    case 5:
                        // Опція 5: Завантаження салату з файлу
                        salad = loadFromFile(saladName); // Перезаписуємо поточний об'єкт салату завантаженим з файлу
                        System.out.println("Salad loaded successfully!");
                        break;
                    case 6:
                        // Опція 6: Сортувати салат за калоріями
                        System.out.println("Sorting salad by Calories:");
                        salad.sortIngredientsByCalories(); // Сортуємо
                        viewIngredients(salad); // Виводимо на консоль
                        break;
                    case 0:
                        // Опція 0: Вихід з програми
                        System.out.println("Exiting the program. Goodbye!");
                        return; // Завершує виконання методу main і, відповідно, програми
                    default:
                        // Обробка випадку, коли введено число, що не відповідає жодній опції
                        System.out.println("Wrong choice. Please enter a number between 0 and 5.");
                        break;
                }
            } catch (InvalidInputException e) {
                // Обробка помилки некоректного вводу (наприклад, якщо введено текст замість числа)
                System.err.println("Error: " + e.getMessage());
            } catch (EmptySaladException e) {
                // Обробка помилки порожнього салату
                System.err.println("Error: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                // Обробка помилок, пов'язаних з роботою з файлами (збереження/завантаження)
                System.err.println("File operation error: " + e.getMessage());
            } catch (Exception e) {
                // Обробка будь-яких інших непередбачуваних помилок
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // Допоміжний метод для безпечного отримання цілого числа з консолі
    private static int getIntInput() throws InvalidInputException {
        try {
            // Намагаємося зчитати наступне ціле число
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            // Якщо введені дані не є цілим числом, виникає InputMismatchException
            scanner.nextLine(); // Очищуємо невірне введення з буфера
            // Генеруємо власний виняток для кращої обробки на вищому рівні
            throw new InvalidInputException("Invalid input. Please enter a whole number.");
        }
    }

    // Метод для відображення інгредієнтів салату
    public static void viewIngredients(Salad salad) {
        System.out.println(LINE); // Візуальний розділювач
        System.out.println("Ingredients of " + salad.getName() + " salad:");
        try {
            // Намагаємося викликати метод для відображення інгредієнтів
            salad.displayIngredients();
        } catch (EmptySaladException e) {
            // Якщо салат порожній, метод displayIngredients кине виняток, який ми тут обробляємо
            System.out.println("The salad is empty!");
        }
        System.out.println(LINE); // Візуальний розділювач
    }

    // Метод для додавання нового інгредієнта до салату
    public static void insertIngredient(Salad salad) {
        try {
            // Запит на введення назви інгредієнта
            System.out.println("Please enter ingredient name. Available options: Onion, Tomato, Carrot");
            String name = scanner.nextLine();

            // Перевірка, чи введена назва є однією з доступних опцій
            if (!(name.equalsIgnoreCase("Onion") || name.equalsIgnoreCase("Tomato") || name.equalsIgnoreCase("Carrot"))) {
                // Якщо назва некоректна, генеруємо виняток
                throw new NoSuchIngredientException("Invalid vegetable name. Please choose from the available options.");
            }

            // Запит на введення калорійності
            System.out.println("Please enter the calories of the vegetable:");
            int calories = getIntInput();
            scanner.nextLine(); // Очищуємо буфер

            Vegetable vegetable; // Оголошення змінної для зберігання створеного овоча
            // Оператор switch для створення об'єкта відповідного класу залежно від введеної назви
            switch (name.toLowerCase()) {
                case "onion":
                    System.out.println("Enter onion variety:");
                    String variety = scanner.nextLine(); // Введення сорту цибулі
                    vegetable = new Onion(variety, calories); // Створення об'єкта Onion
                    break;
                case "tomato":
                    System.out.println("Enter tomato colour:");
                    String colour = scanner.nextLine(); // Введення кольору томата
                    vegetable = new Tomato(colour, calories); // Створення об'єкта Tomato
                    break;
                case "carrot":
                    System.out.println("Enter carrot length:");
                    int length = getIntInput(); // Введення довжини моркви
                    scanner.nextLine(); // Очищуємо буфер
                    vegetable = new Carrot(length, calories); // Створення об'єкта Carrot
                    break;
                default:
                    // Ця гілка не має виконуватися через перевірку вище, але є гарною практикою для повноти
                    return;
            }
            // Додавання створеного овоча до салату
            salad.addIngredient(vegetable);
            System.out.println(name + " has been added to the salad.");

        } catch (NoSuchIngredientException | InvalidInputException e) {
            // Обробка помилок, пов'язаних із додаванням інгредієнта
            System.err.println("Error adding ingredient: " + e.getMessage());
        }
    }

    // Метод для видалення інгредієнта з салату
    public static void removeIngredient(Salad salad) {
        System.out.println(LINE);
        // Перевірка, чи салат не порожній
        if (salad.getIngredients().isEmpty()) {
            System.out.println("The salad is already empty. Nothing to remove.");
            return; // Вихід з методу, якщо видаляти нічого
        }

        // Цикл для виведення пронумерованого списку інгредієнтів
        for (int i = 0; i < salad.getIngredients().size(); i++) {
            Vegetable vegetable = salad.getIngredients().get(i);
            // Виводимо номер (i + 1), назву класу та калорійність
            System.out.println("no." + (i + 1) + " " + vegetable.getClass().getSimpleName() + " " + vegetable.getCalories());
        }

        try {
            // Запит на введення номера інгредієнта для видалення
            System.out.println("Please enter the number of the vegetable you want to remove:");
            int no = getIntInput();
            scanner.nextLine(); // Очищуємо буфер

            // Перевірка, чи введений номер є коректним (в межах розміру списку)
            if (no > salad.getIngredients().size() || no <= 0) {
                throw new InvalidInputException("Invalid number. Please choose a number from the list.");
            }
            // Видалення за індексом (користувач вводить 1, а індекс у списку 0)
            salad.getIngredients().remove(no - 1);
            System.out.println("Vegetable removed successfully.");

        } catch (InvalidInputException e) {
            // Обробка помилок, пов'язаних із видаленням
            System.err.println("Error removing ingredient: " + e.getMessage());
        }
        System.out.println(LINE);
    }

    // Метод для збереження об'єкта Salad у файл за допомогою серіалізації
    public static void saveToFile(Salad salad) throws IOException {
        // Використання try-with-resources, що автоматично закриває потоки після завершення
        try (FileOutputStream fileOutput = new FileOutputStream("salad.ser");
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            System.out.println("Saving salad to salad.ser...");
            // Запис (серіалізація) об'єкта salad у файл
            objectOutput.writeObject(salad);
            System.out.println("Salad saved successfully.");
        }
    }

    // Метод для завантаження (десеріалізації) об'єкта Salad з файлу
    public static Salad loadFromFile(String currentSaladName) throws IOException, ClassNotFoundException {
        // Використання try-with-resources для автоматичного закриття потоків
        try (FileInputStream fileInput = new FileInputStream("salad.ser");
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            // Зчитування об'єкта з файлу та приведення його до типу Salad
            Salad loadedSalad = (Salad) objectInput.readObject();
            System.out.println("Successfully loaded '" + loadedSalad.getName() + "' from salad.ser");
            // Повернення завантаженого об'єкта
            return loadedSalad;
        }
    }
}