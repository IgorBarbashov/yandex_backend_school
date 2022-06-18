package com.example.spring_boot_rest_api.student_updates;

import com.example.spring_boot_rest_api.students.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class StudentUpdate {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "student_update_sequence", sequenceName = "student_update_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_update_sequence")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    // OneToOne
    @OneToOne(mappedBy = "update", // field name in the Student Entity with @OneToOne annotation
        cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JsonIgnore
    private Student student;

    public StudentUpdate() {
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    @JsonProperty("student_id")
    public Long getStudentId() {
        if (student == null) {
            return null;
        }
        return student.getId();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentUpdate{" +
                "id=" + id +
                ", date=" + date +
                ", student=" + student +
                '}';
    }
}
