import java.io.Serializable;
import java.util.HashMap;

public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private HashMap<String, Integer> ingredients; // Ingredient name -> Quantity

    // Constructor
    public Recipe(String name) {
        this.name = name;
        this.ingredients = new HashMap<>();
    }

    // Add an ingredient to the recipe
    public void addIngredient(String ingredientName, int quantity) {
        ingredients.put(ingredientName, quantity);
    }

    // Getters
    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }
    
    
    /*Can't override javas println() method itself, but can control how object is passed to System.out.println()
    by overriding toString() in the objects own class. I am doing this to format the the recipe object: with some headers
    and loop through all ingredients and add them to the ouput for the one item, instead of just getting a hashcode if 
    I hadent @Override the method
    Recipe: Pancakes
    Ingredients:
    - Flour: 2 cups
    - Milk: 1 cup
    - Eggs: 2 large
      */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe: ").append(name).append("\nIngredients:\n");
        for (var entry : ingredients.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
