package com.serviceImp;

import com.model.Course;
import com.model.Student;
import com.repository.CompanyRepository;
import com.repository.CourseRepository;
import com.repository.StudentRepository;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CompanyRepository companyRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.companyRepository = companyRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public void saveStudent(Student student, Long id) {
        student.setCompany(companyRepository.findById(id).get());
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Long id ,Student student) {
        Student student1 = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found student"));
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        studentRepository.save(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.delete(studentRepository.findById(id).get());
    }

    @Override
    public List<Student> getStudents(Long id) {
        return studentRepository.getStudentByCompanyId(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }


    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        student.setCourse(course);
        course.addStudent(student);
        courseRepository.save(course);
        studentRepository.save(student);

    }

//    @Override
//    public List<Student> countOfStudents(Long id) {
//        return studentRepository.countOfStudents(id);
//    }
}
