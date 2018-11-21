/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service;

import org.springframework.stereotype.Component;

/**
 *
 * @author hilmananwarsah
 */
@Component
public class Config {

    public int getPage_list_adm_teacher() {
        return page_list_adm_teacher;
    }

    public void setPage_list_adm_teacher(int page_list_adm_teacher) {
        this.page_list_adm_teacher = page_list_adm_teacher;
    }

    public int getPage_list_adm_student() {
        return page_list_adm_student;
    }

    public void setPage_list_adm_student(int page_list_adm_student) {
        this.page_list_adm_student = page_list_adm_student;
    }

    public int getPage_list_adm_class() {
        return page_list_adm_class;
    }

    public void setPage_list_adm_class(int page_list_adm_class) {
        this.page_list_adm_class = page_list_adm_class;
    }

    String signingKey;
    int page_list_adm_teacher,page_list_adm_student,page_list_adm_class;

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

}
