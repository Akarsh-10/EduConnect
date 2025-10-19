package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wecp.progressive.dao.TeacherDAO;
import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.service.TeacherService;

public class TeacherServiceImplJdbc implements TeacherService {
    private TeacherDAO teacherDAO;
    
    public TeacherServiceImplJdbc(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Integer addTeacher(Teacher teacher) throws SQLException {
        return teacherDAO.addTeacher(teacher);
    }

    @Override
    public void deleteTeacher(int teacherId) throws SQLException {
        teacherDAO.deleteTeacher(teacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() throws SQLException {
        return teacherDAO.getAllTeachers();
        
    }
    public Teacher getTeacherById(int teacherId)throws SQLException
    {
        return teacherDAO.getTeacherById(teacherId);
    }


    @Override
    public List<Teacher> getTeacherSortedByExperience() throws SQLException {
        List<Teacher> sortedList= new ArrayList<>();
        sortedList= teacherDAO.getAllTeachers();
            Collections.sort(sortedList);
        return sortedList; 
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        teacherDAO.updateTeacher(teacher);
    }
    

}