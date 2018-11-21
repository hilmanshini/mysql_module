/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.impl;

import com.hilman.test.etiqa.sql_module.service.TeacherService;
import com.hilman.test.etiqa.sql_module.service.pojo.teacher.AddClassRequest;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.ValueOnlyRequest;
import com.hilman.test.etiqa.sql_module.service.pojo.teacher.TeacherClass;
import com.hilman.test.etiqa.sql_module.service.pojo.teacher.TeacherClassDetail;
import java.util.Date;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.hibernate.SQLQuery;
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
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    SessionFactory mSessionFactory;

    @Override
    public Response<String> addClass(AddClassRequest mAddClassRequest, long id) {
        Session ses = mSessionFactory.openSession();
        Response<String> data = (Response<String>) ses.createSQLQuery("call fun_teacher_add_class(:id_student,:id_class,:id_teacher,:start_time,:end_time,:room)")
                .setParameter("id_student", mAddClassRequest.getId_student())
                .setParameter("id_class", mAddClassRequest.getId_class())
                .setParameter("id_teacher", id)
                .setParameter("room", mAddClassRequest.getRoom())
                .setParameter("start_time", mAddClassRequest.getStart_time())
                .setParameter("end_time", mAddClassRequest.getEnd_time())
                .setResultTransformer(Transformers.aliasToBean(Response.class)).uniqueResult();
        ses.close();
        return data;
    }

    public Response<String> removeStudentFromClass(ValueOnlyRequest<Long> mValueOnlyRequest) {
        Session ses = mSessionFactory.openSession();
        SQLQuery q = ses.createSQLQuery("delete from student_class where id = :id");
        q.setParameter("id", mValueOnlyRequest.getValue());
        q.executeUpdate();
        ses.close();
        return new Response<>(200, "Ok", "");
    }

    public Response<List<TeacherClass>> getAllClassByTime(long id_teacher, Date time_start, Date time_end) {
        Session ses = mSessionFactory.openSession();
        List<TeacherClass> data = ses.createSQLQuery("call fun_teacher_view_class_by_time(:id_teacher,:start_time,:end_time)")
                .setParameter("id_teacher", id_teacher)
                .setParameter("start_time", time_start)
                .setParameter("end_time", time_end)
                .setResultTransformer(Transformers.aliasToBean(TeacherClass.class)).list();
        ses.close();
        return new Response<>(200, "Ok", data);
    }

    public Response<List<TeacherClassDetail>> getClassDetailByTime(long id_teacher, Date time_start, Date time_end,int room,long id_class) {
        Session ses = mSessionFactory.openSession();
        List<TeacherClassDetail> data = ses.createSQLQuery("call fun_teacher_view_student_by_room_and_subject(:id_teacher,:id_class,:start_time,:end_time,:room)")
                .setParameter("id_teacher", id_teacher)
                .setParameter("id_class", id_class)
                .setParameter("start_time", time_start)
                .setParameter("end_time", time_end)
                .setParameter("room", room)
                .setResultTransformer(Transformers.aliasToBean(TeacherClassDetail.class)).list();
        ses.close();
        return new Response<List<TeacherClassDetail>>(200, "Ok", data);
    }
}
