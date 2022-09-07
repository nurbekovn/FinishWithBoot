package com.serviceImp;

import com.model.Course;
import com.repository.CompanyRepository;
import com.repository.CourseRepository;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseServiceImpl  implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }



    public void saveCourse(Course course, Long id) {
        course.setCompany(companyRepository.findById(id).get());
        courseRepository.save(course);
    }


    public void updateCourse(Long id,Course course) {
        Course course1 = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found course"));
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setImage(course.getImage());
        course1.setDescription(course.getDescription());
        course1.setDateOfStart(course.getDateOfStart());
        courseRepository.save(course1);
    }


    public void deleteCourse(Long id) {
        courseRepository.delete(courseRepository.findById(id).get());
    }


    public List<Course> getAllCourses(Long companyId) {
        return courseRepository.findCoursesByCompanyId(companyId);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }
}

