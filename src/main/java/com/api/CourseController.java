package com.api;

import com.model.Course;
import com.model.Instructor;
import com.model.Student;
import com.service.CompanyService;
import com.service.CourseService;
import com.service.InstructorService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService,
                            InstructorService instructorService, StudentService studentService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }


    @GetMapping("/courses/{id}")
    private String getAllCourses(@PathVariable Long id, Model model,
                                 @ModelAttribute("instructor") Instructor instructor,
                                 @ModelAttribute("student") Student student) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("companyId", id);
        List<Instructor> instructors = instructorService.getAllInstructors(id);
        model.addAttribute("instructors", instructors);
        model.addAttribute("students", studentService.getStudents(id));

        System.out.println("~~~~~~~~~~~  METHOD  all courses done  ~~~~~~~~~~~");
        return "course/courses";
    }


    @GetMapping("/{id}/addCourse")
    private String addCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "course/addCourse";
    }


    @PostMapping("/{id}/saveCourse")
    private String saveCourse(@ModelAttribute("course") Course course,
                              @PathVariable Long id) {
        courseService.saveCourse(course, id);
        System.out.println(course);
        return "redirect:/courses/courses/ " + id;
    }


    @GetMapping("/edit/{id}")
    private String updateCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/course/updateCourse";
    }


    @PostMapping("{id}/{courseId}/update")
    private String saveUpdateCourse(@PathVariable("courseId") Long courseId,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute("course") Course course) {
        courseService.updateCourse(courseId, course);
        return "redirect:/courses/courses/ " + id;
    }


    @PostMapping("/{id}/{compId}")
    private String deleteCourse(@PathVariable("id") Long id,
                                @PathVariable("compId") Long compId) {
        courseService.deleteCourse(id);
        return "redirect:/courses/courses/ " + compId;
    }

    @PostMapping("{companyId}/{courseId}/assignInstructor")
    private String assignInstructor(@PathVariable("companyId") Long comId,
                                    @PathVariable("courseId") Long courseId,
                                    @ModelAttribute("instructor") Instructor instructor) {
        instructorService.assignedInstructorToCourse(instructor.getId(), courseId);
        return "redirect:/courses/courses/ " + comId;
    }
}
