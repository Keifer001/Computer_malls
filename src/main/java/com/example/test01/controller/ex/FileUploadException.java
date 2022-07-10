package com.example.test01.controller.ex;

/**
 * @author Keifer
 * @creat 2022-02-12-15:54
 */
/*文件上传相关异常的基类*/
public class FileUploadException extends RuntimeException{
    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
