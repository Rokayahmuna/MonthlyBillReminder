import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

// Main class handles only user interaction (UI layer)
// Business logic is separated into other classes (OOP design)

public class Main {

    // Validates and parses amount safely
    public static double getValidAmount(Scanner sc) {
        while (true) {
            System.out.print("Enter amount: ");
            String input = sc.nextLine().replace("$", "").trim();

            try {
                return Double.parseDouble(input);
            } catch (Exception e) {
                System.out.println("Invalid amount. Try again.");
            }
        }
    }

    // FIXED: Supports multiple valid date formats safely
    public static LocalDate getValidDate(Scanner sc) {

        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy")
        };

        while (true) {
            System.out.print("Enter due date (yyyy-MM-dd or MM-dd-yyyy): ");
            String input = sc.nextLine().trim();

            for (DateTimeFormatter formatter : formats) {
                try {
                    return LocalDate.parse(input, formatter);
                } catch (DateTimeParseException ignored) {
                    // try next format
                }
            }

            System.out.println("Invalid date format. Please use yyyy-MM-dd or MM-dd-yyyy.");
        }
    }

    // Category selection using numeric menu (prevents invalid enum input)
    public static Category getValidCategory(Scanner sc) {

        while (true) {
            System.out.println("\n==============================");
            System.out.println("       SELECT CATEGORY");
            System.out.println("==============================");
            System.out.println("1. RENT");
            System.out.println("2. ELECTRICITY");
            System.out.println("3. INTERNET");
            System.out.println("4. PHONE");
            System.out.println("5. CREDIT CARD");
            System.out.println("6. SUBSCRIPTION");
            System.out.println("7. OTHER");
            System.out.println("==============================");
            System.out.print("Enter choice (1-7): ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1: return Category.RENT;
                    case 2: return Category.ELECTRICITY;
                    case 3: return Category.INTERNET;
                    case 4: return Category.PHONE;
                    case 5: return Category.CREDIT_CARD;
                    case 6: return Category.SUBSCRIPTION;
                    case 7: return Category.OTHER;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Enter number between 1-7.");
            }
        }
    }

    // Menu input validation
    public static int getValidMenuChoice(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Enter valid number: ");
            }
        }
    }

    // Displays clean menu UI
    public static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("     BILL REMINDER SYSTEM");
        System.out.println("==============================");
        System.out.println("1. Add Bill");
        System.out.println("2. View Bills");
        System.out.println("3. Mark as Paid");
        System.out.println("4. Remove Bill");
        System.out.println("5. Show Reminders");
        System.out.println("6. Summary");
        System.out.println("7. Save & Exit");
        System.out.println("==============================");
        System.out.print("Select option: ");
    }

    // Clean structured bill display
    public static void printBills(List<Bill> bills) {

        System.out.println("\n==============================");
        System.out.println("           ALL BILLS");
        System.out.println("==============================\n");

        if (bills.isEmpty()) {
            System.out.println("No bills found.\n");
            return;
        }

        for (Bill b : bills) {
            System.out.println("Bill Name: " + b.getName());
            System.out.println("Monthly Cost: $" + String.format("%.2f", b.getAmount()));
            System.out.println("Due Date: " + b.getDueDate());
            System.out.println("Category: " + b.getCategory());
            System.out.println("Status: " + (b.isPaid() ? "PAID" : "UNPAID"));
            System.out.println("------------------------------");
        }

        System.out.println();
    }

    // Main program loop
    public static void main(String[] args) {

        BillManager manager = new BillManager();
        Scanner sc = new Scanner(System.in);

        // Load saved data if available
        List<Bill> loaded = FileService.load();
        if (loaded != null) {
            manager.getAllBills().addAll(loaded);
        }

        while (true) {

            printMenu();
            int choice = getValidMenuChoice(sc);

            switch (choice) {

                case 1:
                    System.out.println("\n==============================");
                    System.out.println("        ADD NEW BILL");
                    System.out.println("==============================");

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    double amount = getValidAmount(sc);
                    LocalDate date = getValidDate(sc);
                    Category category = getValidCategory(sc);

                    manager.addBill(new Bill(name, amount, date, category));

                    System.out.println("Bill added successfully.\n");
                    break;

                case 2:
                    printBills(manager.getAllBills());
                    break;

                case 3:
                    System.out.println("\nEnter bill name to mark as paid:");
                    manager.markAsPaid(sc.nextLine());
                    break;

                case 4:
                    System.out.println("\nEnter bill name to remove:");
                    manager.removeBill(sc.nextLine());
                    break;

                case 5:
                    ReminderService.showReminders(manager);
                    break;

                case 6:
                    System.out.println("\n==============================");
                    System.out.println("          SUMMARY");
                    System.out.println("==============================");

                    System.out.println("Total Upcoming: $" + manager.getTotalUpcomingAmount());

                    manager.getCategorySummary()
                            .forEach((k, v) ->
                                    System.out.println(k + ": $" + v));

                    System.out.println("==============================\n");
                    break;

                case 7:
                    FileService.save(manager.getAllBills());
                    System.out.println("Data saved. Exiting...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}