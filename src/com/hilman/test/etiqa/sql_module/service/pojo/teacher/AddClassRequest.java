/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hilman.test.etiqa.sql_module.service.pojo.teacher;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author hilmananwarsah
 */
public class AddClassRequest {

    long room;

    public long getRoom() {
        return room;
    }

    public void setRoom(long room) {
        this.room = room;
    }

    int id_class;
    int id_student;
    @DateTimeFormat(pattern = "yyyyMMdd hh:mm")
    Date start_time;
    @DateTimeFormat(pattern = "yyyyMMdd hh:mm")
    Date end_time;

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

}
