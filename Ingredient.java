import java.io.Serializable;

public class Ingredient implements Serializable {
    private static final long serialVersionUID = 1L; // Best practice for Serializable classes
    private String name;
    private int quantity;
    private String expirationDate;
    

    // Constructor
    public Ingredient(String name, int quantity, String expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Custom Methods
    public void reduceQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            System.out.println("Not enough inventory to reduce.");
        }
    }
}
