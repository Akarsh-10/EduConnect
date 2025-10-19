package com.wecp.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.exception.TeacherAlreadyExistsException;
import com.wecp.progressive.repository.TeacherRepository;
import com.wecp.progressive.service.TeacherService;

@Service
public class TeacherServiceImplJpa implements TeacherService {
    
    @Autowired
    TeacherRepository teacherRepository;
    
    public TeacherServiceImplJpa() {
    }

    public TeacherServiceImplJpa(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Integer addTeacher(Teacher teacher) throws Exception {

        Integer i= teacher.getTeacherId();
        if(i!=null)
        {
            if(teacherRepository.findById(teacher.getTeacherId()).isPresent())
            {
                throw new TeacherAlreadyExistsException("null");
            }
        } 
        Teacher t1= teacherRepository.save(teacher);
        return t1.getTeacherId();
    }

    @Override
    public List<Teacher> getAllTeachers() throws Exception {

        return teacherRepository.findAll();
    }
    public Teacher getTeacherById(int teacherId) throws Exception
    {
        
        return teacherRepository.findById(teacherId).orElse(null);
    }
    public void deleteTeacher(int teacherId)throws Exception
    {
        teacherRepository.findById(teacherId).orElseThrow();
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() throws Exception {
        List<Teacher> sortedList= teacherRepository.findAll();
        Collections.sort(sortedList);
        return sortedList;
    }
    public void updateTeacher(Teacher teacher) throws Exception
    {
        Teacher updatedTeacher= teacherRepository.findById(teacher.getTeacherId()).orElseThrow();
        
        updatedTeacher.setFullName(teacher.getFullName());
        updatedTeacher.setEmail(teacher.getEmail());
        updatedTeacher.setContactNumber(teacher.getContactNumber());
        updatedTeacher.setSubject(teacher.getSubject());
        updatedTeacher.setYearsOfExperience(teacher.getYearsOfExperience());

        teacherRepository.save(updatedTeacher);
    }
}