package com.wecp.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Attendance;
import com.wecp.progressive.repository.AttendanceRepository;
import com.wecp.progressive.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService  {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance createAttendance(Attendance attendance) {   
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deleteAttendance(int attendanceId) {
        Attendance attendance= attendanceRepository.findById(attendanceId).orElse(null);
        if(attendance!=null)
        attendanceRepository.deleteById(attendanceId);
        
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttendanceByCourse(int courseId) {
        return attendanceRepository.findByCourse_CourseId(courseId);
    }

    @Override
    public List<Attendance> getAttendanceByStudent(int studentId) {
        return attendanceRepository.findByStudent_StudentId(studentId);
    }

    

}