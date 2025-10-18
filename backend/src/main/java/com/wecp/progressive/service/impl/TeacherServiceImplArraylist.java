package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.service.TeacherService;

public class TeacherServiceImplArraylist implements TeacherService  {

    private static List<Teacher> teacherList = new ArrayList<>();

    @Override
    public Integer addTeacher(Teacher teacher) {
        teacherList.add(teacher);
        return teacherList.size();
    }

    @Override
    public void emptyArrayList() {
        teacherList=new ArrayList<>();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        Collections.sort(teacherList);
        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() {
        Collections.sort(teacherList);
        return teacherList;
    }
    

}