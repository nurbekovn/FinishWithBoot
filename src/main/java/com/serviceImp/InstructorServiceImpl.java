package com.serviceImp;

import com.model.Course;
import com.model.Instructor;
import com.repository.CompanyRepository;
import com.repository.CourseRepository;
import com.repository.InstructorRepository;
import com.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, CompanyRepository companyRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.companyRepository = companyRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveInstructor(Long id,Instructor instructor) {
        instructor.setCompany(companyRepository.findById(id).get());
        instructorRepository.save(instructor);

    }

    @Override
    public void updateInstructor(Long id,Instructor instructor) {
        Instructor instructor1 = instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found "));
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructorRepository.save(instructor1);

    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.delete(instructorRepository.findById(id).get());

    }

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return instructorRepository.findInstructorsByCompanyId(id);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).get();
    }

    @Override
    public void assignedInstructorToCourse(Long instructorId, Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        Instructor instructor = instructorRepository.findById(instructorId).get();
        course.addInstructor(instructor);
        instructor.addCourse(course);
        instructorRepository.save(instructor);
        courseRepository.save(course);
    }


//    public void getCountOfStudents(String firstName) {
//        instructorRepository.getCountOfStudents(firstName);
//    }

//    @Override
//    public void getStudentsCount() {
//        instructorRepository.getStudentsCount();
//    }





}
