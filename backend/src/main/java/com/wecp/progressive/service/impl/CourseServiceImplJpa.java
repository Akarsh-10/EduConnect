package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.Course;
import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.exception.CourseAlreadyExistsException;
import com.wecp.progressive.exception.CourseNotFoundException;
import com.wecp.progressive.repository.AttendanceRepository;
import com.wecp.progressive.repository.CourseRepository;
import com.wecp.progressive.repository.EnrollmentRepository;
import com.wecp.progressive.repository.TeacherRepository;
import com.wecp.progressive.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplJpa implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public CourseServiceImplJpa(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() throws Exception {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int courseId) throws Exception {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " not found for deletion"));
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        Course existingCourse = courseRepository.findByCourseName(course.getCourseName());
        if (existingCourse != null) {
            throw new CourseAlreadyExistsException("Course with this name already exists, Course Name: " + course.getCourseName());
        }

        if(course.getTeacher()!=null && course.getTeacher().getTeacherId()!=0)
        {
            Teacher teacher= teacherRepository.findById((course.getTeacher().getTeacherId())).orElseThrow(()-> new RuntimeException("Teacher does not exist"));
            course.setTeacher(teacher);
        }

        return courseRepository.save(course).getCourseId();
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        Course existingCourse = courseRepository.findByCourseName(course.getCourseName());
        if (existingCourse != null && existingCourse.getCourseId() != course.getCourseId()) {
            throw new CourseAlreadyExistsException("Course with this name already exists, Course Name: " + course.getCourseName());
        }
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(int courseId) throws Exception {
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException("Course with ID " + courseId + " not found for deletion");
        }
        attendanceRepository.deleteByCourseId(courseId);
        enrollmentRepository.deleteByCourseId(courseId);
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> getAllCourseByTeacherId(int teacherId) {
        return courseRepository.findAllByTeacherId(teacherId);
    }
}