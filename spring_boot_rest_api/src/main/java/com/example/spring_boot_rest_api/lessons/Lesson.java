package com.example.spring_boot_rest_api.lessons;

import javax.persistence.*;

// OneToMany
// Uni-directional
// Relation owning side - STUDENT
@Entity
public class Lesson {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "lesson_sequence", sequenceName = "lesson_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_sequence")
    private Long id;

    @Column(name = "name")
    private String name;

    public Lesson() {
    }

    public Lesson(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
