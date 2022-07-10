package com.example.test01.controller.ex;

/**
 * @author Keifer
 * @creat 2022-02-12-16:11
 */
/*上传的文件大小超出了限制值*/
public class FileSizeException extends FileUploadException{
    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
