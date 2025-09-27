package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
