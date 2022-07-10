package com.example.test01.controller.ex;

/**
 * @author Keifer
 * @creat 2022-02-12-15:57
 */
/*上传的文件为空的异常，例如没有选择上传的文件就提交了表单，或者是选择的文件是0字节的空文件*/
public class FileEmptyException extends FileUploadException{
    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
