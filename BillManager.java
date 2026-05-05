import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// BillManager handles all business logic
// This class demonstrates abstraction (hides internal operations from Main)

public class BillManager {

    // List stores all bill objects in memory
    private List<Bill> bills = new ArrayList<>();

    // Adds a new bill
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    // Removes a bill by name (case-insensitive match)
    public void removeBill(String name) {
        boolean removed = bills.removeIf(b -> b.getName().equalsIgnoreCase(name));

        if (!removed) {
            System.out.println("Bill not found.");
        } else {
            System.out.println("Bill removed.");
        }
    }

    // Finds a bill by name
    public Bill findBill(String name) {
        return bills.stream()
                .filter(b -> b.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // Marks a bill as paid
    public void markAsPaid(String name) {
        Bill bill = findBill(name);

        if (bill != null) {
            bill.markPaid();
            System.out.println("Marked as paid.");
        } else {
            System.out.println("Bill not found.");
        }
    }

    // Returns all bills
    public List<Bill> getAllBills() {
        return bills;
    }

    // Returns bills due soon (within given days)
    public List<Bill> getUpcomingBills(int days) {
        LocalDate now = LocalDate.now();

        return bills.stream()
                .filter(b -> !b.isPaid())
                .filter(b -> !b.getDueDate().isBefore(now))
                .filter(b -> b.getDueDate().isBefore(now.plusDays(days)))
                .collect(Collectors.toList());
    }

    // Returns overdue bills
    public List<Bill> getOverdueBills() {
        LocalDate now = LocalDate.now();

        return bills.stream()
                .filter(b -> !b.isPaid())
                .filter(b -> b.getDueDate().isBefore(now))
                .collect(Collectors.toList());
    }

    // Calculates total amount of unpaid bills
    public double getTotalUpcomingAmount() {
        return bills.stream()
                .filter(b -> !b.isPaid())
                .mapToDouble(Bill::getAmount)
                .sum();
    }

    // Groups bills by category and sums amounts
    public Map<Category, Double> getCategorySummary() {
        return bills.stream()
                .collect(Collectors.groupingBy(
                        Bill::getCategory,
                        Collectors.summingDouble(Bill::getAmount)
                ));
    }
}