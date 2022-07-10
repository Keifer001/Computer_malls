package com.example.test01.service.ex;

/**
 * @author Keifer
 * @creat 2022-03-02-8:37
 */
public class DeleteException extends ServiceException{
    /*删除数据失败的异常*/
    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
