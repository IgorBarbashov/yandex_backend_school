package com.example.spring_boot_rest_api.grades;

import com.example.spring_boot_rest_api.students.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

// OneToMany
// Uni-directional
// Relation owning side - GRADE
@Entity
public class Grade {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "grade_sequence", sequenceName = "grade_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_sequence")
    private Long id;

    private int grade;

    @ManyToOne(cascade = {
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinColumn(
            name = "student_id" // how new column in the GRADE table will be called
    )
    @JsonIgnore
    private Student student;

    public Grade() {
    }

    public Grade(int grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getStudentId() {
        if (student == null) {
            return null;
        }
        return student.getId();
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", student=" + student +
                '}';
    }
}
