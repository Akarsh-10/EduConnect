package com.wecp.progressive.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    public List<Attendance> findByStudent_StudentId(int studentId);

    public List<Attendance> findByCourse_CourseId(int courseId);

    public Optional<Attendance> findByCourse_CourseIdAndStudent_StudentIdAndAttendanceDate(int courseId, int studentId, Date attendanceDate);

    // public void deleteByCourseId(int courseId);

    // public void deleteByStudentId(int studentId);

}
