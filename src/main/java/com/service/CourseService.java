package com.service;

import com.model.Course;

import java.util.List;

public interface CourseService {
    void saveCourse(Course course,Long id);
    void updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    List<Course> getAllCourses(Long companyId);
    Course getCourseById(Long id);
    List<Course> getCourses();

}
