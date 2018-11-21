/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.impl;

import com.hilman.test.etiqa.sql_module.service.AuthService;
import com.hilman.test.etiqa.sql_module.service.SignService;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.LoginRequest;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author hilmananwarsah
 */
@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    SessionFactory mSessionFactory;

    @Autowired
    SignService mSignService;

    

    @Override
    public Response<String> login(LoginRequest mLoginRequest) {
        return auth(mLoginRequest, "fun_auth_user","student");
    }

    

    @Override
    public Response<String> loginTeacher(LoginRequest mLoginRequest) {
        return auth(mLoginRequest, "fun_auth_user_teacher","teacher");
    }

    @Override
    public Response<String> loginAdmin(LoginRequest mLoginRequest) {
        return auth(mLoginRequest, "fun_auth_user_admin","admin");
    }
    
    
    
    
    
    
    
    
    
    private Response<String> auth(LoginRequest mLoginRequest, String funName,String role) {
        Session mLoginSession = mSessionFactory.openSession();
        StringBuilder sb = new StringBuilder().append("call ").append(funName).append("( :email , :password );");
        Response<String> resut = (Response<String>) mLoginSession
                .createSQLQuery(sb.toString())
                .setParameter("email", mLoginRequest.getEmail())
                .setParameter("password", mLoginRequest.getPassword())
                .setResultTransformer(Transformers.aliasToBean(new Response<String>().getClass())).uniqueResult();
        mLoginSession.close();
        if (resut.getData() != null) {
            String d = mSignService.sign(mLoginRequest.getEmail(), "authentication", "email", mLoginRequest.getEmail(), "password", mLoginRequest.getPassword(),"id",resut.getStatus().longValue()+"","role",role);
            resut.setData(d);
            return resut;
        }
        if (resut == null) {
            return new Response<String>(-1, "No User Found", "");
        }
        return resut;
    }
}
