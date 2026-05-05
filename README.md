The Monthly Bill Reminder System is a console-based Java application designed to help users manage recurring monthly bills efficiently. It keeps track of due dates, categorizes expenses, and provides reminders for upcoming or overdue payments to avoid late fees and financial stress.

This project demonstrates strong object-oriented programming (OOP) principles along with practical use of Java features such as file handling, collections, and the modern date/time API.

🚀 Features
➕ Add new bills (name, amount, due date, category)
📋 View all saved bills
✏️ Update bill details
❌ Delete bills
🔍 Search for specific bills
✅ Mark bills as paid
⏰ Automatic reminders for:
Upcoming bills
Overdue bills
📊 Sort bills by due date or category
💰 Calculate total upcoming expenses
📈 Category-wise expense summary
💾 Persistent storage (save/load from file)
📧 Simulated email alerts for overdue bills
🛠 Technologies Used
Java (JDK 11+)
Object-Oriented Programming (OOP)
Java Collections Framework
File I/O (Serialization)
java.time API (LocalDate)

🧠 Object-Oriented Programming Concepts Used
🔹 Encapsulation
The Bill class encapsulates bill data such as name, amount, due date, category, and payment status.
Fields are declared private and accessed via getter and setter methods, ensuring controlled data access.
🔹 Abstraction
BillManager handles business logic like adding, removing, searching, and sorting bills.
FileService abstracts file operations (saving/loading data).
ReminderService abstracts reminder logic for overdue and upcoming bills.
🔹 Inheritance (Extendable Design)
The design allows future extensions such as:
RecurringBill
OneTimeBill
Promotes code reuse and scalability.
🔹 Polymorphism
Bills are handled using List<Bill>, allowing flexibility for future subclasses.
Enables dynamic behavior if extended classes override methods.
🔹 Composition
BillManager contains and manages a collection of Bill objects.
Models real-world relationships (a manager “has” bills).
🔹 Separation of Concerns

Each class has a single responsibility:
Bill → Data model
BillManager → Core logic
ReminderService → Reminder handling
FileService → Data persistence
Main → User interface
🔹 Enums for Type Safety
Category enum ensures predefined categories and prevents invalid inputs.
🔹 Modern Java Features
LocalDate for accurate date handling
Streams API for filtering, sorting, and aggregation
Serialization for persistent storage

📂 Project Structure
MonthlyBillReminder/
│
├── Category.java
├── Bill.java
├── BillManager.java
├── ReminderService.java
├── FileService.java
└── Main.java


▶️ How to Run
1. Compile all files
javac *.java
2. Run the program
java Main


🧠 How It Works
Each bill is stored as an object with attributes like name, amount, due date, and category.
The system checks the current date to identify overdue and upcoming bills.
Data is saved locally using serialization, allowing persistence between runs.
Users interact with the system through a simple console-based menu.


📸 Sample Output
==== BILL REMINDER SYSTEM ====
1. Add Bill
2. View Bills
3. Mark as Paid
4. Remove Bill
5. Show Reminders
6. Summary
7. Save & Exit

👨‍💻 Author
NFN Rokayah
Computer Science Student @ UNC Charlotte

📄 License
This project is for educational purposes.
