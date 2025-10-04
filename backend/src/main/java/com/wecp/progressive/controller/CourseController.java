package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Course;
import com.wecp.progressive.service.CourseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        try
        {
            return new ResponseEntity<>(courseService.getAllCourses(),HttpStatus.OK);
        }
        catch(Exception e)
        {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
        try
        {
            Course course=courseService.getCourseById(courseId);
            return new ResponseEntity<>(course,HttpStatus.OK);            
        }
        catch(RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping
    public ResponseEntity<Integer> addCourse(@RequestBody Course course) {
        try
        {
            return new ResponseEntity<>(courseService.addCourse(course),HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Void> updateCourse(@PathVariable int courseId, @RequestBody Course course) {
        course.setCourseId(courseId);
        try {
            courseService.updateCourse(course);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteCourse(int courseId) {
        try {
            courseService.deleteCourse(courseId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Course>> getAllCourseByTeacherId(int teacherId) {
        return new ResponseEntity<>(courseService.getAllCourseByTeacherId(teacherId),HttpStatus.OK);
    }
}
