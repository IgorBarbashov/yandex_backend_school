package com.example.spring_boot_rest_api.adresses;

import com.example.spring_boot_rest_api.students.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

// OneToMany
// Bi-directional
// Relation owning side - ADDRESS
// Relation inverse side - STUDENT
// mappedBy - used in the annotation of the relation inverse side and set linking-field-name in the owner-side-entity
// In the case of bi-directional OneToMany relation, owning side may be only MANY-side
@Entity
public class Address {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    private Long id;

    private String address;

    // OneToMany
    // Bi-directional
    // Relation owning side - ADDRESS
    // Relation inverse side - STUDENT
    // mappedBy - used in the annotation of the relation inverse side and set linking-field-name in the owner-side-entity
    // In the case of bi-directional OneToMany relation, owning side may be only MANY-side
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(
            name = "student_id" // how new column in the ADDRESS table will be called
    )
    @JsonIgnore
    private Student student;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Student getStudent() {
        return student;
    }

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
        return "\nAddress{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", student=" + student +
                '}';
    }
}
