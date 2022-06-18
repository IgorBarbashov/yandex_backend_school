package com.example.spring_boot_rest_api.student_updates;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentUpdateRepository extends JpaRepository<StudentUpdate, Long> {
}
