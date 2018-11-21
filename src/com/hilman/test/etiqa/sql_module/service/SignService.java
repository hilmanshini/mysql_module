/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author hilmananwarsah
 */
public interface SignService {

    public static final String ISSUER = "iss";
    public static final String SUBJECT = "sub";
    public static final String AUDIENCE = "aud";
    public static final String EXPIRATION = "exp";
    public static final String NOT_BEFORE = "nbf";
    public static final String ISSUED_AT = "iat";
    public static final String ID = "jti";

    public String sign(String issuer, String subjects, String... keyPairs);

    public Map<String, Object> parse(String data);
    
    public Map<String, Object> parse(HttpServletRequest data) ;

}
