// File: Main.java
import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LINE = "-----------------------------------";

    public static void main(String[] args) {
        System.out.println("Welcome to the Vegetable Calculator");
        System.out.println("Please enter the salad name:");
        String saladName = scanner.nextLine();
        Salad salad = new Salad(saladName);

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View Vegetables and calories");
            System.out.println("2. Add Vegetable");
            System.out.println("3. Remove Vegetable");
            System.out.println("4. Save salad to a file");
            System.out.println("5. Load salad from a file");
            System.out.println("0. Exit");

            try {
                int choice = getIntInput(); // method for better input
                scanner.nextLine(); // clear the buffer

                switch (choice) {
                    case 1:
                        viewIngredients(salad);
                        break;
                    case 2:
                        insertIngredient(salad);
                        break;
                    case 3:
                        removeIngredient(salad);
                        break;
                    case 4:
                        saveToFile(salad);
                        break;
                    case 5:
                        salad = loadFromFile(saladName); // rewrite current salad
                        System.out.println("Salad loaded successfully!");
                        break;
                    case 0:
                        System.out.println("Exiting the program. Goodbye!");
                        return; // exit from the program
                    default:
                        System.out.println("Wrong choice. Please enter a number between 0 and 5.");
                        break;
                }
            } catch (InvalidInputException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("File operation error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static int getIntInput() throws InvalidInputException {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Очищення невірного введення з буфера
            throw new InvalidInputException("Invalid input. Please enter a whole number.");
        }
    }

    public static void viewIngredients(Salad salad) {
        System.out.println(LINE);
        System.out.println("Ingredients of " + salad.getName() + " salad:");
        try {
            salad.displayIngredients();
        } catch (EmptySaladException e) {
            System.out.println("The salad is empty!");
        }
        System.out.println(LINE);
    }

    public static void insertIngredient(Salad salad) {
        try {
            System.out.println("Please enter ingredient name. Available options: Onion, Tomato, Carrot");
            String name = scanner.nextLine();

            if (!(name.equalsIgnoreCase("Onion") || name.equalsIgnoreCase("Tomato") || name.equalsIgnoreCase("Carrot"))) {
                throw new EmptySaladException("Invalid vegetable name. Please choose from the available options.");
            }

            System.out.println("Please enter the calories of the vegetable:");
            int calories = getIntInput();
            scanner.nextLine(); // Очищуємо буфер

            Vegetable vegetable;
            switch (name.toLowerCase()) {
                case "onion":
                    System.out.println("Enter onion variety:");
                    String variety = scanner.nextLine();
                    vegetable = new Onion(variety, calories);
                    break;
                case "tomato":
                    System.out.println("Enter tomato colour:");
                    String colour = scanner.nextLine();
                    vegetable = new Tomato(colour, calories);
                    break;
                case "carrot":
                    System.out.println("Enter carrot length:");
                    int length = getIntInput();
                    scanner.nextLine(); // Очищуємо буфер
                    vegetable = new Carrot(length, calories);
                    break;
                default:
                    // Ця гілка не має виконуватися через перевірку вище, але є гарною практикою
                    return;
            }
            salad.addIngredient(vegetable);
            System.out.println(name + " has been added to the salad.");

        } catch (NoSuchIngredientException | InvalidInputException e) {
            System.err.println("Error adding ingredient: " + e.getMessage());
        }
    }

    public static void removeIngredient(Salad salad) {
        System.out.println(LINE);
        if (salad.getIngredients().isEmpty()) {
            System.out.println("The salad is already empty. Nothing to remove.");
            return;
        }

        for (int i = 0; i < salad.getIngredients().size(); i++) {
            Vegetable vegetable = salad.getIngredients().get(i);
            System.out.println("no." + (i + 1) + " " + vegetable.getClass().getSimpleName() + " " + vegetable.getCalories());
        }

        try {
            System.out.println("Please enter the number of the vegetable you want to remove:");
            int no = getIntInput();
            scanner.nextLine(); // Очищуємо буфер

            if (no > salad.getIngredients().size() || no <= 0) {
                throw new InvalidInputException("Invalid number. Please choose a number from the list.");
            }
            salad.getIngredients().remove(no - 1); // Видалення за індексом (користувач вводить 1, а індекс 0)
            System.out.println("Vegetable removed successfully.");

        } catch (InvalidInputException e) {
            System.err.println("Error removing ingredient: " + e.getMessage());
        }
        System.out.println(LINE);
    }

    public static void saveToFile(Salad salad) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream("salad.ser");
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            System.out.println("Saving salad to salad.ser...");
            objectOutput.writeObject(salad);
            System.out.println("Salad saved successfully.");
        }
    }

    public static Salad loadFromFile(String currentSaladName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInput = new FileInputStream("salad.ser");
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            Salad loadedSalad = (Salad) objectInput.readObject();
            System.out.println("Successfully loaded '" + loadedSalad.getName() + "' from salad.ser");
            return loadedSalad;
        }
    }
}