import java.io.*;
import java.util.HashMap;

public class InventoryManager implements Serializable {
    private static final long serialVersionUID = 1L; // Best practice for Serializable classes
    private HashMap<String, InventoryItem> inventory = new HashMap<>();

    // Add an item to the inventory
    public void addItem(String name, int quantity, String expirationDate) {
        if (inventory.containsKey(name)) {
            InventoryItem item = inventory.get(name);
            item.setQuantity(item.getQuantity() + quantity); // Update the quantity
        } else {
            InventoryItem newItem = new InventoryItem(name, quantity, expirationDate);
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
            for (InventoryItem item : inventory.values()) {
                System.out.println(item.getName() + " - Quantity: " + item.getQuantity() +
                        ", Expiration Date: " + item.getExpirationDate());
            }
        }
    }

    // Save the inventory to a file
    public void saveToFile(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this); // Serialize the current InventoryManager object
            System.out.println("Inventory saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving inventory to file: " + e.getMessage());
        }
    }
    

   public static InventoryManager loadFromFile(String filename) {
    try (FileInputStream fileIn = new FileInputStream(filename);
         ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
        System.out.println("Inventory loaded successfully from " + filename);
        return (InventoryManager) objectIn.readObject();
    } catch (IOException | ClassNotFoundException e) {
        System.err.println("Error loading inventory: " + e.getMessage() + ". Starting with a new inventory.");
        return new InventoryManager();
    }
}

    
}
