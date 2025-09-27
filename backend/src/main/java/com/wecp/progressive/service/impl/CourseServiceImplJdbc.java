package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.dao.CourseDAO;
import com.wecp.progressive.dao.CourseDAOImpl;
import com.wecp.progressive.entity.Course;
import com.wecp.progressive.service.CourseService;

public class CourseServiceImplJdbc implements CourseService {

    private CourseDAO courseDAO;
    
    public CourseServiceImplJdbc(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public Integer addCourse(Course course) {
        try
        {
            return courseDAO.addCourse(course);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
        
    }

    @Override
    public void deleteCourse(int courseId) {
        try
        {
            courseDAO.deleteCourse(courseId);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> li=new ArrayList<>();
        try
        {
            return courseDAO.getAllCourses();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
        
    }

    @Override
    public Course getCourseById(int courseId) {
        try
        {
            return courseDAO.getCourseById(courseId);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
       
    }

    @Override
    public void updateCourse(Course course) {
        try
        {
            courseDAO.updateCourse(course);   
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    

}