package Exceptions;

public class NoSuchElementException extends Exception {
    public NoSuchElementException(String message) {
        super(message);
    }
    public NoSuchElementException() {
        super("Element not found");
    }
}
