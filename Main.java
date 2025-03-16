
    public class Main{ 

    public static void main(String[] args){


                InventoryManager inventoryManager = InventoryManager.loadFromFile("inventory.ser");
                
                // Pass the same instance to all menus:
                InventoryMenu.displayMenu(inventoryManager);
            }
        }
         

