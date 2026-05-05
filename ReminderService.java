import java.util.List;

// Handles reminder logic (overdue and upcoming bills)
// Demonstrates separation of concerns

public class ReminderService {

    public static void showReminders(BillManager manager) {

        List<Bill> overdue = manager.getOverdueBills();
        List<Bill> upcoming = manager.getUpcomingBills(5);

        System.out.println("\nOVERDUE BILLS:");

        if (overdue.isEmpty()) {
            System.out.println("None");
        } else {
            overdue.forEach(System.out::println);
        }

        System.out.println("\nUPCOMING BILLS (Next 5 Days):");

        if (upcoming.isEmpty()) {
            System.out.println("None");
        } else {
            upcoming.forEach(System.out::println);
        }

        if (!overdue.isEmpty()) {
            System.out.println("\nYou have overdue bills.");
        }
    }
}