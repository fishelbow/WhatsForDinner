    import java.util.Scanner;

    public class Main{ 

    public static void main(String[] args){

                // loading in the KitchenManager kitchenManager form  the serilaized file.
                // this object stores the ingredients, recipes, and groceryList.
                KitchenManager kitchenManager = KitchenManager.loadFromFile("inventory.ser");

                 // opening the scanner in main and passing it around.
                Scanner scanner = new Scanner(System.in);
                
                // Pass the same instance to all menus:
                MainMenu.topMenu(scanner, kitchenManager);
            
                scanner.close();
            }
        }
         

