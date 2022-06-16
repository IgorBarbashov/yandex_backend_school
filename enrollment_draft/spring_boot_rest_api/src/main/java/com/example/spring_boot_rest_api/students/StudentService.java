package com.example.spring_boot_rest_api.students;

import com.example.spring_boot_rest_api.response.RestApiException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public void add(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new RestApiException("Email is busy");
        }

        studentRepository.save(student);
    }

    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void update(Student student) {
        Optional<Student> row = studentRepository.findById(student.getId());

        if (row.isPresent()) {
            Student item = row.get();
            if (!student.getName().isEmpty()) {
                item.setName(student.getName());
            }
            if (student.getDateOfBirth() != null) {
                item.setDateOfBirth(student.getDateOfBirth());
            }
            studentRepository.save(item);
        }
    }
}
