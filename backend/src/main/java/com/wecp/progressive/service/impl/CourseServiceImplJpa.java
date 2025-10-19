package com.wecp.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Course;
import com.wecp.progressive.exception.CourseNotFoundException;
import com.wecp.progressive.repository.CourseRepository;
import com.wecp.progressive.repository.TeacherRepository;
import com.wecp.progressive.service.CourseService;

@Service
public class CourseServiceImplJpa implements CourseService  {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    
    
    public CourseServiceImplJpa() {
    }

    public CourseServiceImplJpa(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    public CourseServiceImplJpa(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Course> getAllCourses()
    {
        return courseRepository.findAll();
    }
    @Override
    public Integer addCourse(Course course) throws Exception {
        Course c1=courseRepository.save(course);
        return c1.getCourseId();
    }
    @Override
    public void deleteCourse(int courseId) throws Exception {
        Course cDel= courseRepository.findById(courseId).orElse(null);
        if(cDel !=null)
        courseRepository.deleteById(courseId);
    }
    @Override
    public Course getCourseById(int courseId) throws Exception {
        Course course=courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course not found"));
        return course;
    }
    @Override
    public void updateCourse(Course course) throws Exception {
        Course c1=courseRepository.findById(course.getCourseId()).orElseThrow(()-> new IllegalArgumentException());
        
        c1.setCourseName(course.getCourseName());
        c1.setDescription(course.getDescription());
        c1.setTeacher(course.getTeacher());

        courseRepository.save(c1);
    }
    public List<Course> getAllCourseByTeacherId(int teacherId)
        {
            return courseRepository.findAll();
        }
    


    
}