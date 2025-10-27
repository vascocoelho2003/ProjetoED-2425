package Exceptions;

public class ElementNotFoundException extends  Exception{
    public ElementNotFoundException(String message){
        super(message);
    }
    public ElementNotFoundException(){
        super("Element not found in the collection");
    }
}
