import java.util.Scanner;

// Main class - menu-driven console application
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        library.loadFromFile(); // load saved books at startup

        int choice;
        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Display All Members");
            System.out.println("7. Save & Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    int memberId = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    library.addMember(new Member(memberId, name));
                    break;

                case 3:
                    try {
                        System.out.print("Enter Book ID to issue: ");
                        int bId = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Member ID: ");
                        int mId = Integer.parseInt(sc.nextLine());
                        library.issueBook(bId, mId);
                    } catch (BookNotAvailableException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Book ID to return: ");
                        int bId = Integer.parseInt(sc.nextLine());
                        library.returnBook(bId);
                    } catch (BookNotAvailableException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    library.displayAllBooks();
                    break;

                case 6:
                    library.displayAllMembers();
                    break;

                case 7:
                    library.saveToFile();
                    System.out.println("Thank you! Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 7);

        sc.close();
    }
}
