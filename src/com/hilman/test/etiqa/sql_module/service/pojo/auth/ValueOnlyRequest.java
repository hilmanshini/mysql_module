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
public class ValueOnlyRequest<T> {
    T value;

    public ValueOnlyRequest() {
    }

    public ValueOnlyRequest(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
}
