import java.util.Scanner;
import java.util.InputMismatchException;

public class RecipeMenu {

    // This method displays the recipe menu
    public static void recipeMenu(Scanner scanner, KitchenManager kitchenManager) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Recipe Manager ---");
            System.out.println("1. Create a Recipe");
            System.out.println("2. View Recipes");
            System.out.println("3. Delete a Recipe");
            System.out.println("4. Back to Main Menu");


            int choice = KitchenManager.getValidChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    createRecipe(scanner, kitchenManager);

                    break;
                case 2:
                    kitchenManager.viewRecipes();
                    break;
                case 3:
                    deleteRecipe(scanner, kitchenManager);
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

    private static void createRecipe(Scanner scanner, KitchenManager kitchenManager) {
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

    kitchenManager.addRecipe(recipe);
    System.out.println("Recipe created successfully: " + recipeName);
    
    kitchenManager.saveToFile("inventory.ser");

    System.out.println("Recipe Saved!!");
}

    private static void deleteRecipe(Scanner scanner, KitchenManager kitchenManager) {
        System.out.print("Enter the name of the recipe to delete: ");
        String recipeName = scanner.nextLine();
        kitchenManager.deleteRecipe(recipeName);
    }

}
