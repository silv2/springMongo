package sk.mongodb.example.exceptions;

/**
 * Created by Silvia on 11. 10. 2014.
 */
public class CustomerEmptyResultException extends Exception {
    public CustomerEmptyResultException() { super(); }
    public CustomerEmptyResultException(String message) { super(message); }
    public CustomerEmptyResultException(String message, Throwable cause) { super(message, cause); }
    public CustomerEmptyResultException(Throwable cause) { super(cause); }
}
