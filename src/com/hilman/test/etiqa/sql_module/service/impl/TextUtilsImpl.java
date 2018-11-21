/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.impl;

import com.hilman.test.etiqa.sql_module.service.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hilmananwarsah
 */
public class TextUtilsImpl implements TextUtils{

    @Override
    public Date formatToDate(String text, String format) {
        try {
            return new SimpleDateFormat(format).parse(text);
        } catch (ParseException ex) {
            Logger.getLogger(TextUtilsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
    
}
