/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service;

import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;
import com.hilman.test.etiqa.sql_module.service.pojo.student.StudentClass;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hilmananwarsah
 */
public interface StudentService {
    public Response<List<StudentClass>> getAll(long idStudent,Date startTime,Date endTime);
    
}
