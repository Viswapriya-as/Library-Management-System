import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

// Library.java - core business logic
// Demonstrates: Collections (ArrayList, HashMap), Exception handling, File I/O
public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private HashMap<Integer, Integer> issuedBooks = new HashMap<>(); // bookId -> memberId

    private static final String FILE_NAME = "library_data.txt";

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added: " + member.getName());
    }

    // Issue a book - throws custom exception if not available
    public void issueBook(int bookId, int memberId) throws BookNotAvailableException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookNotAvailableException("Book with ID " + bookId + " does not exist.");
        }
        if (book.isIssued()) {
            throw new BookNotAvailableException("Book \"" + book.getTitle() + "\" is already issued.");
        }
        book.setIssued(true);
        issuedBooks.put(bookId, memberId);
        System.out.println("Book \"" + book.getTitle() + "\" issued successfully.");
    }

    // Return a book
    public void returnBook(int bookId) throws BookNotAvailableException {
        Book book = findBookById(bookId);
        if (book == null || !book.isIssued()) {
            throw new BookNotAvailableException("This book was not issued, cannot return.");
        }
        book.setIssued(false);
        issuedBooks.remove(bookId);
        System.out.println("Book \"" + book.getTitle() + "\" returned successfully.");
    }

    public Book findBookById(int bookId) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                return b;
            }
        }
        return null;
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member m : members) {
            System.out.println(m);
        }
    }

    // Save book data to a text file - demonstrates File I/O
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                writer.println(b.getBookId() + "," + b.getTitle() + "," + b.getAuthor() + "," + b.isIssued());
            }
            System.out.println("Library data saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load book data from file
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Book b = new Book(Integer.parseInt(parts[0]), parts[1], parts[2]);
                    b.setIssued(Boolean.parseBoolean(parts[3]));
                    books.add(b);
                }
            }
            System.out.println("Library data loaded from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
