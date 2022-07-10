package com.example.test01.util;

import java.io.Serializable;

/**
 * @author Keifer
 * @creat 2022-01-27-11:28
 */
public class JsonResult<E> implements Serializable {
    /*状态码*/
    private Integer state;
    /*描述信息*/
    private String message;
    /*数据*/
    private E data;
    //1.无参构造方法
    public JsonResult() {
    }
    //2.涉及到状态码的构造方法
    public JsonResult(Integer state) {
        this.state = state;
    }
    //3.状态码+对应的数据的构造方法
    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }
    //涉及到异常的状态码的构造方法 状态信息用e来接收
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }
    //get和set方法
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
