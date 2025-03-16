import java.util.Scanner;
import java.util.InputMismatchException;

public class RecipeMenu {

    // Ensure that inventoryManager is properly initialized;
    // It should be a static field available to all methods in this class.
   

    // This method displays the recipe menu
    public static void recipeMenu(Scanner scanner, InventoryManager inventoryManager) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Recipe Manager ---");
            System.out.println("1. Create a Recipe");
            System.out.println("2. View Recipes");
            System.out.println("3. Delete a Recipe");
            System.out.println("4. Generate Grocery List for a Recipe");
            System.out.println("5. Back to Inventory Menu");
            System.out.print("Choose an option: ");

            int choice = getValidChoice(scanner, 1, 5);

            switch (choice) {
                case 1:
                    createRecipe(scanner, inventoryManager);

                    break;
                case 2:
                    inventoryManager.viewRecipes();
                    break;
                case 3:
                    deleteRecipe(scanner, inventoryManager);
                    break;
                case 4:
                    generateGroceryList(scanner, inventoryManager);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

private static void createRecipe(Scanner scanner, InventoryManager inventoryManager) {
    System.out.print("Enter recipe name: ");
    String recipeName = scanner.nextLine();
    Recipe recipe = new Recipe(recipeName);
    boolean addingIngredients = true;

    while (addingIngredients) {
        System.out.print("Enter ingredient name (or type 'done' to finish): ");
        String ingredientName = scanner.nextLine().trim();
        if (ingredientName.equalsIgnoreCase("done")) {
            addingIngredients = false;
        } else {
            int quantity = -1;
            boolean validQuantity = false;
            while (!validQuantity) {
                System.out.print("Enter quantity needed for " + ingredientName + ": ");
                try {
                    quantity = scanner.nextInt();
                    scanner.nextLine();  // Consume leftover newline
                    validQuantity = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number for quantity. Please enter a valid integer.");
                    scanner.nextLine();  // Clear the invalid input from the scanner
                }
            }
            recipe.addIngredient(ingredientName, quantity);
            System.out.println("Ingredient added: " + ingredientName + " (" + quantity + ")");
        }
    }

    inventoryManager.addRecipe(recipe);
    System.out.println("Recipe created successfully: " + recipeName);
    
    inventoryManager.saveToFile("inventory.ser");

    System.out.println("Recipe Saved!!");
}


    private static void deleteRecipe(Scanner scanner, InventoryManager inventoryManager) {
        System.out.print("Enter the name of the recipe to delete: ");
        String recipeName = scanner.nextLine();
        inventoryManager.deleteRecipe(recipeName);
    }

    private static void generateGroceryList(Scanner scanner, InventoryManager inventoryManager) {
        System.out.print("Enter the name of the recipe: ");
        String recipeName = scanner.nextLine();
        inventoryManager.generateGroceryList(recipeName);
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
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
}
