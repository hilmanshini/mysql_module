/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.impl;

import com.hilman.test.etiqa.sql_module.service.StudentService;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;
import com.hilman.test.etiqa.sql_module.service.pojo.student.StudentClass;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author hilmananwarsah
 */
@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    SessionFactory mSessionFactory;

    @Override
    public Response<List<StudentClass>> getAll(long idStudent, Date startTime, Date endTime) {
        Session ses = mSessionFactory.openSession();

        List<StudentClass> data = ses.createSQLQuery("call fun_student_view_class(:id_student,:start_time,:end_time)")
                .setParameter("id_student", idStudent)
                .setParameter("start_time", startTime)
                .setParameter("end_time", endTime)
                .setResultTransformer(Transformers.aliasToBean(StudentClass.class)).list();
        ses.close();
        Response<List<StudentClass>> res = new Response<>(200, "Ok", data);
        return res;
    }

}
