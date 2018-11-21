/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.pojo.auth;

/**
 *
 * @author hilmananwarsah
 */
public class Response<T> {
    Number status;
    String message;
    T data;

    public Response() {
    }

    public Response(Number status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    

    public Number getStatus() {
        return status;
    }

    public void setStatus(Number status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    
    
}
