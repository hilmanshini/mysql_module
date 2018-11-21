/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.pojo.teacher;

/**
 *
 * @author hilmananwarsah
 */
public class TeacherClassDetail {
    long id,id_student_class;

    public long getId_student_class() {
        return id_student_class;
    }

    public void setId_student_class(long id_student_class) {
        this.id_student_class = id_student_class;
    }
    String profile_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public void setProfile_name(String profile_name) {
        this.profile_name = profile_name;
    }
    
}
