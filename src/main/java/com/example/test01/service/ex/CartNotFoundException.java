package com.example.test01.service.ex;

/**
 * @author Keifer
 * @creat 2022-03-05-14:55
 */
public class CartNotFoundException extends SecurityException{
    public CartNotFoundException() {
        super();
    }

    public CartNotFoundException(String s) {
        super(s);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }
}
