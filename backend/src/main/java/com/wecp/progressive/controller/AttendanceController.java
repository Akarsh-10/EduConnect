package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Attendance;
import com.wecp.progressive.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        return new ResponseEntity<>(attendanceService.getAllAttendance(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        return new ResponseEntity<>(attendanceService.createAttendance(attendance),HttpStatus.CREATED);
    }

    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<Integer> deleteAttendance(@PathVariable int attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(("/student/{studentId}"))
    public ResponseEntity<List<Attendance>> getAllAttendanceByStudent(@PathVariable int studentId) {
        return new ResponseEntity<>(attendanceService.getAttendanceByStudent(studentId), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Attendance>> getAllAttendanceByCourse(@PathVariable  int courseId) {
        return new ResponseEntity<>(attendanceService.getAttendanceByCourse(courseId), HttpStatus.OK);
    }
}
