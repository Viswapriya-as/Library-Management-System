// Book.java - demonstrates OOP: encapsulation (private fields + getters/setters)
public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }

    @Override
    public String toString() {
        return "ID: " + bookId + " | Title: " + title + " | Author: " + author
                + " | Status: " + (isIssued ? "Issued" : "Available");
    }
}