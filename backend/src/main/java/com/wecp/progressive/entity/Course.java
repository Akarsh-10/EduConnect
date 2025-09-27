package com.wecp.progressive.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;
    private String description;
    //private Integer teacherId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Course() {
    }


    // public Course(Integer courseId, String courseName, String description, Integer teacherId) {
    //     this.courseId = courseId;
    //     this.courseName = courseName;
    //     this.description = description;
    //     this.teacherId = teacherId;
    // }


    
    public Course(Integer courseId, String courseName, String description , Integer teacherId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.teacher.setTeacherId(teacherId);
    }
    

    public Course(Integer courseId, String courseName, String description, Teacher teacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.teacher = teacher;
    }


    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Teacher getTeacher() {
        return teacher;
    }


    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // public Integer getTeacherId() {
    //     return teacherId;
    // }

    // public void setTeacherId(Integer teacherId) {
    //     this.teacherId = teacherId;
    // }

    
}