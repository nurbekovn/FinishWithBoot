package com.repository;

import com.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    List<Instructor> findInstructorsByCompanyId(Long companyId);

//    void assignedInstructorToCourse(Long instructorId, Long courseId);


//    void getStudentsCount();


//    @Query("select count(s) from Instructor s where s.firstName =?1")
//    Long getCountOfStudents(@Param("firstName") String  firstName);

}
