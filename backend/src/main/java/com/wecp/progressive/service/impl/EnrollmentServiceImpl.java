package com.wecp.progressive.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Enrollment;
import com.wecp.progressive.repository.EnrollmentRepository;
import com.wecp.progressive.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService  {

    EnrollmentRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAllEnrollments()
    {
        return enrollmentRepository.findAll();
    }

    public int createEnrollment(Enrollment enrollment)
    {
        return enrollmentRepository.save(enrollment).getEnrollmentId();
    }

    public void updateEnrollment(Enrollment updatedEnrollment)
    {
        Enrollment enrollment=enrollmentRepository.findById(updatedEnrollment.getEnrollmentId()).orElseThrow(()-> new RuntimeException());
        enrollment.setStudent(updatedEnrollment.getStudent());
        enrollment.setCourse(updatedEnrollment.getCourse());
        enrollment.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());

        enrollmentRepository.save(enrollment);
    }

    public Enrollment getEnrollmentById(int enrollmentId)
    {
        return enrollmentRepository.findById(enrollmentId).orElseThrow(()-> new RuntimeException());
    }
    
    public List<Enrollment> getAllEnrollmentsByStudent(int studentId)
    {
        return enrollmentRepository.findAllByStudent_StudentId(studentId);
    }

    public List<Enrollment> getAllEnrollmentsByCourse(int courseId)
    {
        return enrollmentRepository.findAllByCourse_CourseId(courseId);
    }

    


}