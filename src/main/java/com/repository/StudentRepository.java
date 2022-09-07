package com.repository;


import com.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long > {

//    @Query("select s from Student s where s.company=?1")
    List<Student> getStudentByCompanyId(Long courseId);



//    void assignStudentToCourse(Long studentId,Long courseId);

//    List<Student> countOfStudents(Long id);

}
