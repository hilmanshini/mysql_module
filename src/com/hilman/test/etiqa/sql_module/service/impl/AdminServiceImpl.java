/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.impl;

import com.hilman.test.etiqa.sql_module.service.AdminService;
import com.hilman.test.etiqa.sql_module.service.Config;
import com.hilman.test.etiqa.sql_module.service.pojo.admin.AddUserRequest;
import com.hilman.test.etiqa.sql_module.service.pojo.admin.AdmData;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.ValueOnlyRequest;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    SessionFactory mSessionFactory;

    @Autowired
    Config mConfig;

    @Override
    public Response<String> addTeacher(AddUserRequest mAddUserRequest) {
        Session ses = mSessionFactory.openSession();
        Response<String> ret = (Response<String>) ses.createSQLQuery("call fun_admin_add_teacher(:email,:password,:profile_name)")
                .setParameter("email", mAddUserRequest.getEmail())
                .setParameter("password", mAddUserRequest.getPassword())
                .setParameter("profile_name", mAddUserRequest.getProfile_name()).setResultTransformer(Transformers.aliasToBean(Response.class)).uniqueResult();
        ses.close();
        return ret;
    }

    @Override
    public Response<String> addStudent(AddUserRequest mAddUserRequest) {
        Session ses = mSessionFactory.openSession();
        Response<String> ret = (Response<String>) ses.createSQLQuery("call fun_admin_add_student(:email,:password,:profile_name)")
                .setParameter("email", mAddUserRequest.getEmail())
                .setParameter("password", mAddUserRequest.getPassword())
                .setParameter("profile_name", mAddUserRequest.getProfile_name()).setResultTransformer(Transformers.aliasToBean(Response.class)).uniqueResult();
        ses.close();
        return ret;
    }

    @Override
    public Response<String> addClass(ValueOnlyRequest mValueOnlyRequest) {
        Session ses = mSessionFactory.openSession();
        Response<String> ret = (Response<String>) ses.createSQLQuery("call fun_admin_add_class(:val)")
                .setParameter("val", mValueOnlyRequest.getValue())
                .setResultTransformer(Transformers.aliasToBean(Response.class)).uniqueResult();
        ses.close();
        return ret;
    }

    public List<AdmData> list(String tableName, int page, int limit) {
        Session ses = mSessionFactory.openSession();
        int start = limit * page;
        int end = (page + 1) * limit;

        List<AdmData> ret = ses.createSQLQuery(new StringBuilder("select * from ").append(tableName).append(" limit :start,:end ").toString())
                .setParameter("start", start)
                .setParameter("end", end)
                .setResultTransformer(Transformers.aliasToBean(AdmData.class)).list();
        ses.close();
        return ret;
    }

    @Override
    public Response<List<AdmData>> listTeacher(int page) {
        List<AdmData> data = list("teacher", page, mConfig.getPage_list_adm_teacher());
        return new Response<>(200, "Ok", data);

    }

    @Override
    public Response<List<AdmData>> listStudent(int page) {
        List<AdmData> data = list("student", page, mConfig.getPage_list_adm_teacher());
        return new Response<>(200, "Ok", data);
    }

    @Override
    public Response<List<AdmData>> listClass(int page) {
        List<AdmData> data = list("class", page, mConfig.getPage_list_adm_teacher());
        return new Response<>(200, "Ok", data);
    }

    private Response<String> delete(String tableName, long id) {
        Session ses = mSessionFactory.openSession();
        ses.createSQLQuery(new StringBuilder("delete from ").append(tableName).append(" where id = ").append(id).toString()).executeUpdate();
        ses.close();
        return new Response<>(200, "Ok", "");
    }

    @Override
    public Response<String> deleteTeacher(ValueOnlyRequest<Long> mAddUserRequest) {
        return delete("teacher", 0);
    }

    @Override
    public Response<String> deleteStudent(ValueOnlyRequest<Long> mAddUserRequest) {
        return delete("student", 0);
    }

    @Override
    public Response<String> deleteClass(ValueOnlyRequest<Long> mAddUserRequest) {
        return delete("class", 0);
    }

    
}
