/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.pojo.admin;

/**
 *
 * @author hilmananwarsah
 */
public class AddUserRequest {
    String email;
    String password;
    String profile_name;

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }
    

    public AddUserRequest() {
    }
    

    public AddUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
