package com.example.test01.controller.ex;

/**
 * @author Keifer
 * @creat 2022-02-12-16:13
 */
/*上传文件时读写异常*/
public class FileUploadIOException extends FileUploadException{
    public FileUploadIOException() {
    }

    public FileUploadIOException(String message) {
        super(message);
    }

    public FileUploadIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIOException(Throwable cause) {
        super(cause);
    }

    public FileUploadIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
