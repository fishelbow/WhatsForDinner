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
