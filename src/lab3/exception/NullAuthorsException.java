package lab3.exception;

public class NullAuthorsException extends RuntimeException{
    public NullAuthorsException(String message){
        super(message);
    }
}
