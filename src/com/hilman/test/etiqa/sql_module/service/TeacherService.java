/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service;

import com.hilman.test.etiqa.sql_module.service.pojo.teacher.AddClassRequest;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;

/**
 *
 * @author hilmananwarsah
 */
public interface TeacherService {
    public Response<String> addClass(AddClassRequest mAddClassRequest,long id);
    
}
