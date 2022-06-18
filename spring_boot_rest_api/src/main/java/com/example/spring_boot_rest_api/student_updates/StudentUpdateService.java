package com.example.spring_boot_rest_api.student_updates;

import com.example.spring_boot_rest_api.students.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentUpdateService {

    private final StudentUpdateRepository studentUpdateRepository;

    public StudentUpdateService(StudentUpdateRepository studentUpdateRepository) {
        this.studentUpdateRepository = studentUpdateRepository;
    }

    public List<StudentUpdate> list() {
        return studentUpdateRepository.findAll();
    }

    public void delete(Long updateId) {
        Optional<StudentUpdate> studentUpdate = studentUpdateRepository.findById(updateId);
        Student student = studentUpdate.get().getStudent();

        if (student != null) {
            student.setUpdate(null);
        }

        studentUpdateRepository.deleteById(updateId);
    }

}
