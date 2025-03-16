import java.util.Scanner;

public class MainMenu {
 
    public static void topMenu(Scanner scanner, KitchenManager kitchenManager){
    
    boolean running = true;

    while (running) {
    System.out.println("###################");
    System.out.println("What's for Dinner?");
    System.out.println("###################");
    System.out.println(" ");
    System.out.println("--- Main Menu ---");
    System.out.println("1. Ingreident Menu");
    System.out.println("2. Recipe Menu");
    System.out.println("3. Exit");
  

    int choice = KitchenManager.getValidChoice(scanner, 1, 3);
    switch (choice) {
        case 1:
            IngredientMenu.ingredientMenu(scanner, kitchenManager);
            break;
        case 2:
            RecipeMenu.recipeMenu(scanner, kitchenManager);
            break;
        case 3:
            running = false;
            System.out.println("exiting program...");
            break;
        default:
            System.out.println("Invalid choice. Please try again.");

            }
        }
    }
}
