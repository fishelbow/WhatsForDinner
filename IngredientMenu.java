import java.util.Scanner;
import java.util.InputMismatchException;

public class IngredientMenu {
    // Load the inventory from the file during initialization
    

    public static void displayMenu(KitchenManager inventoryManager) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Inventory Menu ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Remove Item");
            System.out.println("4. Recipe Manager");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidChoice(scanner, 1, 5);

            switch (choice) {
                case 1:
                    addItem(scanner, inventoryManager);
                    break;
                case 2:
                    inventoryManager.viewItems();
                    break;
                case 3:
                    removeItem(scanner, inventoryManager);
                    break;
                case 4:
                    // Call the recipe menu. Ensure that RecipeMenu is defined and its recipeMenu method is public and static.
                    RecipeMenu.recipeMenu(scanner, inventoryManager);
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    inventoryManager.saveToFile("inventory.ser");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume leftover newline
                if (choice >= min && choice <= max) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please choose a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        return choice;
    }

    private static void addItem(Scanner scanner,KitchenManager inventoryManager) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Enter expiration date: ");
        String expirationDate = scanner.nextLine();

        inventoryManager.addItem(name, quantity, expirationDate);
        System.out.println("Item added successfully: " + name + " (" + quantity + ")");
    }

    private static void removeItem(Scanner scanner,KitchenManager inventoryManager) {
        System.out.print("Enter the name of the item to remove: ");
        String name = scanner.nextLine();
        inventoryManager.removeItem(name);
        System.out.println("Item removed successfully: " + name);
    }

}
