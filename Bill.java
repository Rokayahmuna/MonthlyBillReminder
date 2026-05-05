import java.io.Serializable;
import java.time.LocalDate;

// Bill class represents a single monthly bill
// Implements Serializable so we can save/load objects from a file

public class Bill implements Serializable {

    // Encapsulated fields (data hiding principle)
    private String name;
    private double amount;
    private LocalDate dueDate;
    private Category category;
    private boolean isPaid;

    // Constructor initializes a new bill object
    public Bill(String name, double amount, LocalDate dueDate, Category category) {
        this.name = name;
        this.amount = amount;
        this.dueDate = dueDate;
        this.category = category;
        this.isPaid = false; // default state
    }

    // Getter methods (controlled access to private data)
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public LocalDate getDueDate() { return dueDate; }
    public Category getCategory() { return category; }
    public boolean isPaid() { return isPaid; }

    // Marks bill as paid
    public void markPaid() {
        this.isPaid = true;
    }

    // Custom string representation used when printing bills
    @Override
    public String toString() {
        return String.format(
                "%s | $%.2f | Due: %s | %s | %s",
                name, amount, dueDate, category,
                isPaid ? "PAID" : "UNPAID"
        );
    }
}