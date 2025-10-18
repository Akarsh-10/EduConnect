package com.wecp.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wecp.progressive.dto.StudentDTO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.exception.StudentAlreadyExistsException;
import com.wecp.progressive.repository.StudentRepository;
import com.wecp.progressive.service.StudentService;

@Service
public class StudentServiceImplJpa implements StudentService  {

    @Autowired
    StudentRepository studentRepository;
       
    public StudentServiceImplJpa(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Integer addStudent(Student student) throws Exception {
        if(studentRepository.existsById(student.getStudentId()))
        {
            throw new StudentAlreadyExistsException("null");
        }
        Student s1=studentRepository.save(student);
        return s1.getStudentId();
    }

    @Override
    public List<Student> getAllStudentSortedByName() throws Exception {
        List<Student> sortedOne=studentRepository.findAll();
        Collections.sort(sortedOne);
        return sortedOne;
    }

    public void updateStudent(Student student)throws Exception
    {
        Student sUp=studentRepository.findById(student.getStudentId()).orElse(null);
        sUp.setFullName(student.getFullName());
        sUp.setEmail(student.getEmail());
        sUp.setContactNumber(student.getContactNumber());
        sUp.setDateOfBirth(student.getDateOfBirth());
        sUp.setAddress(student.getAddress());

        studentRepository.save(sUp);
    }
    public void deleteStudent(int studentId)
    {
       // Student sDel=studentRepository.findById(studentId).orElseThrow(()-> new IllegalArgumentException("Student not found"));
        Student st=studentRepository.findByStudentId(studentId);
        studentRepository.delete(st);
    }
    public Student getStudentById(int studentId) throws Exception
    {
        return studentRepository.findById(studentId).orElse(null);
    }
    public void modifyStudentDetails(StudentDTO studentDTO)
    {
        Student upStudent=studentRepository.findById(studentDTO.getStudentId()).orElseThrow(()-> new IllegalArgumentException());

        upStudent.setFullName(studentDTO.getFullName());
        upStudent.setEmail(studentDTO.getEmail());
        upStudent.setContactNumber(studentDTO.getContactNumber());
        upStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        upStudent.setAddress(studentDTO.getAddress());
        
        studentRepository.save(upStudent);
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll();
    }
    
    

}