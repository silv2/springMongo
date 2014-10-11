package sk.mongodb.example.exceptions;

/**
 * Created by Silvia on 11. 10. 2014.
 */
public class ShopEmptyResultException extends Exception {
    public ShopEmptyResultException() {
        super();
    }

    public ShopEmptyResultException(String message) {
        super(message);
    }

    public ShopEmptyResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopEmptyResultException(Throwable cause) {
        super(cause);
    }

}
