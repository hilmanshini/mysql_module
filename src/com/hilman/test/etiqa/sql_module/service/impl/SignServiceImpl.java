/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.impl;

import com.hilman.test.etiqa.sql_module.service.Config;
import com.hilman.test.etiqa.sql_module.service.SignService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author hilmananwarsah
 */
@Component
public class SignServiceImpl implements SignService {

    @Autowired
    Config mConfig;

    @Override
    public String sign(String issuer, String subjects, String... keyPairs) {
        JwtBuilder jws = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subjects);
        for (int i = 0; i < keyPairs.length; i += 2) {
            String keyPair = keyPairs[i];
            String val = keyPairs[i + 1];
            jws = jws.claim(keyPair, val);
        }
        Calendar cal = Calendar.getInstance();
        jws = jws.setIssuedAt(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 30);
        return jws.setExpiration(cal.getTime())
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(mConfig.getSigningKey())
                )
                .compact();

    }

    @Override
    public Map<String, Object> parse(String data) {
        Jws<Claims> d = Jwts.parser().setSigningKey(mConfig.getSigningKey()).parseClaimsJws(data);
        return d.getBody();
    }
    @Override
    public Map<String, Object> parse(HttpServletRequest data) {
        String head = data.getHeader("Authentication");
        if(head == null){
            return new HashMap<>();
        }
        Jws<Claims> d = Jwts.parser().setSigningKey(mConfig.getSigningKey()).parseClaimsJws(head);
        return d.getBody();
    }

}
