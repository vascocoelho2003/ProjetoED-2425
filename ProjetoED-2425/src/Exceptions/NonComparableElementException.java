package Exceptions;

public class NonComparableElementException extends Exception{
    public NonComparableElementException(String message) {
        super(message);
    }
    public NonComparableElementException() {
        super("Elemento não é comparável");
    }
}
