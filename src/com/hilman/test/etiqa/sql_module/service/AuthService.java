/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service;

import com.hilman.test.etiqa.sql_module.service.pojo.auth.LoginRequest;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;

/**
 *
 * @author hilmananwarsah
 */
public interface AuthService {
    public Response<String> login(LoginRequest mLoginRequest);
    public Response<String> loginTeacher(LoginRequest mLoginRequest);
    public Response<String> loginAdmin(LoginRequest mLoginRequest);
    
}
