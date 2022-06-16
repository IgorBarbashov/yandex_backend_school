package com.example.spring_boot_rest_api.student_updates;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/updates")
public class StudentUpdateController {

    private final StudentUpdateService studentUpdateService;

    public StudentUpdateController(StudentUpdateService studentUpdateService) {
        this.studentUpdateService = studentUpdateService;
    }

    @GetMapping(path = "list")
    public List<StudentUpdate> list() {
        return studentUpdateService.list();
    }

    @DeleteMapping(path = "item/{updateId}")
    public void delete(@PathVariable Long updateId) {
        studentUpdateService.delete(updateId);
    }
}
