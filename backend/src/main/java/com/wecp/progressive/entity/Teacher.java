package com.wecp.progressive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "teacher")
public class Teacher implements Comparable<Teacher> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="teacher_id")
    private Integer teacherId;

    private String fullName;
    private String subject;
    private String contactNumber;
    private String email;
    private Integer yearsOfExperience;

    public Teacher() {
    }

    
    public Teacher(Integer teacherId, String fullName, String subject, String contactNumber, String email,
            Integer yearsOfExperience) {
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.subject = subject;
        this.contactNumber = contactNumber;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
    }


    public Integer getTeacherId() {
        return this.teacherId;
    }


    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getContactNumber() {
        return contactNumber;
    }


    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }


    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int compareTo(Teacher otherTeacher)
    {
        return Integer.compare(this.getYearsOfExperience(), otherTeacher.getYearsOfExperience());
    }
}