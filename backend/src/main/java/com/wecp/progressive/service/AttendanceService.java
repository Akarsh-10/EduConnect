package com.wecp.progressive.service;

import com.wecp.progressive.entity.Attendance;

import java.util.List;


public interface AttendanceService {

    public List<Attendance> getAllAttendance();

    public Attendance createAttendance(Attendance attendance);

    public void deleteAttendance(int attendanceId);

    public List<Attendance> getAttendanceByStudent(int studentId);

    public List<Attendance> getAttendanceByCourse(int courseId);
}
