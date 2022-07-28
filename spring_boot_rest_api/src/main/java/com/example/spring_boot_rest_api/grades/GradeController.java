package com.example.spring_boot_rest_api.grades;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping(path = "list")
    public List<Grade> list() {
        return gradeService.list();
    }
}
