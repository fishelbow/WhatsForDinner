import java.util.Scanner;

public class IngredientMenu {

    public static void ingredientMenu(Scanner scanner, KitchenManager kitchenManager) {
        
        boolean running = true;

        while (running) {
            System.out.println("\n--- Inventory Menu ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Remove Item");
            System.out.println("4. Back to Main Menu");
           

            int choice = KitchenManager.getValidChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    addItem(scanner, kitchenManager);
                    break;
                case 2:
                    kitchenManager.viewItems();
                    break;
                case 3:
                    removeItem(scanner, kitchenManager);
                    break;
                case 4:
                    System.out.println("Back to Main Menu");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addItem(Scanner scanner,KitchenManager kitchenManager) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        System.out.print("Enter expiration date: ");
        String expirationDate = scanner.nextLine();

        kitchenManager.addItem(name, quantity, expirationDate);
        System.out.println("Item added successfully: " + name + " (" + quantity + ")");
    }

    private static void removeItem(Scanner scanner,KitchenManager kitchenManager) {
        System.out.print("Enter the name of the item to remove: ");
        String name = scanner.nextLine();
        kitchenManager.removeItem(name);
        System.out.println("Item removed successfully: " + name);
    }
}
