package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Student;
import com.wecp.progressive.service.StudentService;

@Service
public class StudentServiceImplArraylist implements StudentService {

    private static List<Student> studentList;

    

    @Override
    public Integer addStudent(Student student) {
        studentList.add(student);
        return studentList.size();
    }

    @Override
    public List<Student> getAllStudentSortedByName() {
        Collections.sort(studentList);
        return studentList;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }
    
    @Override
    public void emptyArrayList() {
        studentList = new ArrayList<>();
    }
}