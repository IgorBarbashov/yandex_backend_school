package com.example.spring_boot_rest_api.students;

import com.example.spring_boot_rest_api.adresses.Address;
import com.example.spring_boot_rest_api.lessons.Lesson;
import com.example.spring_boot_rest_api.student_updates.StudentUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "date_of_birth")
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    // Simple field without relations
    @Column(name = "raw_parent_id")
    @JsonProperty("parent_id")
    private Long parentId;

    // OneToOne
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "last_update" // how new column in the STUDENT table will be called
    )
    private StudentUpdate update;

    // OneToMany
    // Uni-directional
    // Relation owning side - STUDENT
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    @JoinColumn( // without this annotation, Hibernate will create unnecessary connection table to link Student and Lesson
            name = "student_id" // how new column in the LESSON table will be called
    )
    private List<Lesson> lessons = new ArrayList<>();

    // OneToMany
    // Uni-directional
    // Self Join Annotations
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "parent_id" // how new column with self-link in the STUDENT table will be called
    )
    private List<Student> children = new ArrayList<>();

    // OneToMany
    // Bi-directional
    // Relation owning side - ???
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Student() {
    }

    public Student(String name, String email, LocalDate dateOfBirth, Long parentId) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.parentId = parentId;
    }

    public Student(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.parentId = null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public StudentUpdate getUpdate() {
        return update;
    }

    public void setUpdate(StudentUpdate update) {
        this.update = update;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Student> getChildren() {
        return children;
    }

    public void setChildren(List<Student> children) {
        this.children = children;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        addresses.forEach(a -> {
            a.setStudent(this);
        });
        this.addresses = addresses;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", parentId=" + parentId +
                ", update=" + update +
                ", lessons=" + lessons +
                ", children=" + children +
                '}';
    }
}
