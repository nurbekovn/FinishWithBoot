package com.service;

import com.model.Instructor;

import java.util.List;

public interface InstructorService {
    void saveInstructor(Long id,Instructor instructor);

    void updateInstructor(Long id, Instructor instructor);

    void deleteInstructor(Long id);

    List<Instructor> getAllInstructors(Long id);

    Instructor getInstructorById(Long id);

//    void getStudentsCount();

    void assignedInstructorToCourse(Long instructorId, Long courseId);

}
