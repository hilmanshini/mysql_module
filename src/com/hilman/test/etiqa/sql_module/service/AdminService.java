/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service;

import com.hilman.test.etiqa.sql_module.service.pojo.admin.AddUserRequest;
import com.hilman.test.etiqa.sql_module.service.pojo.admin.AdmData;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.Response;
import com.hilman.test.etiqa.sql_module.service.pojo.auth.ValueOnlyRequest;
import java.util.List;

/**
 *
 * @author hilmananwarsah
 */
public interface AdminService {
    public Response<String> addTeacher(AddUserRequest mAddUserRequest);
    public Response<String> addStudent(AddUserRequest mAddUserRequest);
    public Response<String> addClass(ValueOnlyRequest mAddUserRequest);
    public Response<List<AdmData>> listTeacher(int page);
    public Response<List<AdmData>> listStudent(int page);
    public Response<List<AdmData>> listClass(int page);
    public Response<String> deleteTeacher(ValueOnlyRequest<Long> mAddUserRequest);
    public Response<String> deleteStudent(ValueOnlyRequest<Long> mAddUserRequest);
    public Response<String> deleteClass(ValueOnlyRequest<Long> mAddUserRequest);

}
