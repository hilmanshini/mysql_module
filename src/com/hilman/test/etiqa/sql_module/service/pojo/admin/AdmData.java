/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.pojo.admin;

import javax.persistence.Column;

/**
 *
 * @author hilmananwarsah
 */
public class AdmData {
    long id;
    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "profile_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
