package Exceptions;

public class ConcurrentModificationException extends Exception{
    public ConcurrentModificationException(){
        super("Concurrent Modification Exception");
    }
}
