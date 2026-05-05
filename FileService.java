import java.io.*;
import java.util.List;

// Handles saving and loading bill data from disk
// Demonstrates file I/O and persistence

public class FileService {

    private static final String FILE_NAME = "bills.dat";

    // Saves list of bills to file
    public static void save(List<Bill> bills) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(bills);
            System.out.println("Data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Loads bills from file (if exists)
    public static List<Bill> load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (List<Bill>) ois.readObject();

        } catch (Exception e) {
            return null; // If file doesn't exist or fails
        }
    }
}