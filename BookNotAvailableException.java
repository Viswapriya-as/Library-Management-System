// Custom exception - shows you understand exception handling beyond try/catch
public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}
