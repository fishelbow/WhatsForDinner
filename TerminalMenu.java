import java.util.Scanner;
import java.util.InputMismatchException;

public class TerminalMenu {
    // Load the inventory from the file during initialization
    private static InventoryManager inventoryManager = InventoryManager.loadFromFile("inventory.ser");

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Inventory Manager ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Remove Item");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = -1; // Initialize choice with a default invalid value
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume leftover newline

                    // Validate the input range
                    if (choice < 1 || choice > 4) {
                        System.out.println("Invalid choice. Please choose a number between 1 and 4.");
                    } else {
                        validInput = true; // Input is valid
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input from the scanner
                }
            }

            // Proceed to handle the valid choice
            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    inventoryManager.viewItems();
                    break;
                case 3:
                    removeItem(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    inventoryManager.saveToFile("inventory.ser"); // Save the inventory to a file
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addItem(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Enter expiration date: ");
        String expirationDate = scanner.nextLine(); // Consume leftover newline

        inventoryManager.addItem(name, quantity, expirationDate);
        System.out.println("Item added successfully: " + name + " (" + quantity + ")");
    }

    private static void removeItem(Scanner scanner) {
        System.out.print("Enter the name of the item to remove: ");
        String name = scanner.nextLine();
        inventoryManager.removeItem(name);
        System.out.println("Item removed successfully: " + name);
    }
}
