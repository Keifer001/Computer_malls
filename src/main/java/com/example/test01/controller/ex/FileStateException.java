package com.example.test01.controller.ex;

/**
 * @author Keifer
 * @creat 2022-02-12-16:00
 */
/*上传的文件状态异常 例如要进行上传的时候文件是在打开的状态*/
public class FileStateException extends FileUploadException{
    public FileStateException() {
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
