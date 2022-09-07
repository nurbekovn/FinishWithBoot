package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @Column(name = "company_name")
    private String companyName;

    //I -  Бир компанияда бир канча курстар жана студенттер болушу керек

    @Column(name = "located_country")
    private String locatedCountry;

    public void addCourse(Course newCourse) {
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(newCourse);
    }

    @OneToMany(cascade = ALL, mappedBy ="company")
    private List<Course> courses;

    @OneToMany(cascade = ALL,mappedBy = "company")
    private List<Student> students;

    @OneToMany(cascade = ALL,mappedBy = "company")
    private List<Instructor> instructors;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void addInstructor(Instructor instructor) {
        if (instructors == null) {
            instructors=new ArrayList<>();
        }
        instructors.add(instructor);
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        this.students.add(student);
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", locatedCountry='" + locatedCountry + '\'' +
                '}';
    }
}
