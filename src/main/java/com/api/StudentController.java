package com.api;

import com.model.Company;
import com.model.Course;
import com.model.Student;
import com.service.CompanyService;
import com.service.CourseService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final CompanyService companyService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, CompanyService companyService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping("/students/{id}")
    private String getAllStudents(@PathVariable Long id, Model model,
                                  @ModelAttribute("course") Course course) {
        model.addAttribute("students", studentService.getStudents(id));
        model.addAttribute("companyId", id);
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        model.addAttribute("courses", id);
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("allCourses", courseService.getAllCourses(id));
        return "student/students";
    }


    @GetMapping("/{id}/addStudent")
    private String addStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("companyId", id);
        return "student/addStudent";
    }

    @PostMapping("/{id}/saveStudent")
    private String saveStudent(@ModelAttribute("student") Student student,
                               @PathVariable Long id) {
        studentService.saveStudent(student, id);
        return "redirect:/students/students/" + id;
    }

    @GetMapping("/edit/{id}")
    private String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("companyId", student.getCompany().getId());
        return "/student/updateStudent";
    }

    @PostMapping("/{companyId}/{studentId}/updateStudent")
    private String saveUpdateStudent(@PathVariable("studentId") Long studentId,
                                     @PathVariable("companyId") Long id,
                                     @ModelAttribute("student") Student student) {
        studentService.updateStudent(studentId, student);
        return "redirect:/students/students/ " + id;
    }


    @PostMapping("/{id}/{companyId}")
    private String deleteStudent(@PathVariable("id") Long id,
                                 @PathVariable("companyId") Long companyId) {
        studentService.deleteStudent(id);
        return "redirect:/students/students/ " + companyId;
    }


    @PostMapping("/{companyId}/{studentId}/assign")
    private String assign(@PathVariable("studentId") Long id,
                          @PathVariable("companyId") Long companyId,
                          @ModelAttribute("course") Course course) {
        studentService.assignStudentToCourse(id, course.getId());
        return "redirect:/students/students/ " + companyId;
    }

//    @GetMapping("/countOfStudents/{companyId}")
//    private String countOfStudents(@PathVariable("companyId")Long id,Model model) {
////        List<Student> students = studentService.countOfStudents(id);
//        model.addAttribute("students",students.size());
//        return "student/students";
//    }
}
