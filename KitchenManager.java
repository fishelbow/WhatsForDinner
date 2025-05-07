import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class KitchenManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String, Ingredient> inventory = new HashMap<>(); //hash map for Ingredients
    private List<Recipe> recipes = new ArrayList<>(); // List to manage recipes

    // Add an item to the inventory
    public void addItem(String name, int quantity, String expirationDate) {
        if (inventory.containsKey(name)) {
            Ingredient item = inventory.get(name);
            item.setQuantity(item.getQuantity() + quantity); // Update the quantity
        } else {
            Ingredient newItem = new Ingredient(name, quantity, expirationDate);
            inventory.put(name, newItem);
        }
        System.out.println("Item added or updated successfully.");
    }

    // Remove an item from the inventory
    public void removeItem(String name) {
        if (inventory.containsKey(name)) {
            inventory.remove(name);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    // View all items in the inventory
    public void viewItems() {
        if (inventory.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            System.out.println("\n--- Inventory List ---");
            for (Ingredient item : inventory.values()) {
                System.out.println(item.getName() + " - Quantity: " + item.getQuantity() +
                        ", Expiration Date: " + item.getExpirationDate());
            }
        }
    }

    // Add a recipe
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveToFile("inventory.ser");
        System.out.println("Recipe added: " + recipe.getName());
    }

    // View all recipes
    public void viewRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("No recipes found.");
        } else {
            System.out.println("\n--- Recipe List ---");
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
            }
        }
    }

    // Delete a recipe by name
    public void deleteRecipe(String recipeName) {
        recipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(recipeName));
        System.out.println("Recipe removed: " + recipeName);
    }

    // possibly redundent code trying to find my way around.
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        if (recipes == null) {
            recipes = new ArrayList<>();
            System.out.println("DEBUG: recipes list was null; reinitialized.");
        }
    }
    

    // such a busy object, I had used this code in multiple places. 
    // and seeing as how KitchenManger is the catch all utility I put it here. felt cute might delete later.

    public static int getValidChoice(Scanner scanner, int min, int max) {
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


    // Save the inventory to a file
    public void saveToFile(String filename) { 
      //  System.out.println("DEBUG: Saving inventory with " + (recipes != null ? recipes.size() : "null") + " recipes.");
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this); // Serialize the current InventoryManager object
            System.out.println("Inventory saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving inventory to file: " + e.getMessage());
        }
    }
    
    // load the inventory from a file
   public static KitchenManager loadFromFile(String filename) {
    try (FileInputStream fileIn = new FileInputStream(filename);
         ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
        System.out.println("Inventory loaded successfully from " + filename);
        return (KitchenManager) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading inventory: " + e.getMessage() + ". Starting with a new inventory.");
        return new KitchenManager();
    }
}

    
}
