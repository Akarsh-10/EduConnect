package com.wecp.progressive.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    Optional<Enrollment> findByStudent_StudentIdAndCourse_CourseId(int studentId, int courseId);

    List<Enrollment> findAllByStudent_StudentId(int student);

    List<Enrollment> findAllByCourse_CourseId(int courseId);

    //void deleteByCourseId(int courseId);

    //void deleteByStudentId(int studentId);

    //void deleteByTeacherId(int teacherId);
    
}
