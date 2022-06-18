package com.example.spring_boot_rest_api;

import com.example.spring_boot_rest_api.adresses.Address;
import com.example.spring_boot_rest_api.lessons.Lesson;
import com.example.spring_boot_rest_api.lessons.LessonRepository;
import com.example.spring_boot_rest_api.student_updates.StudentUpdate;
import com.example.spring_boot_rest_api.student_updates.StudentUpdateRepository;
import com.example.spring_boot_rest_api.students.Student;
import com.example.spring_boot_rest_api.students.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SpringBootRestApiApplicationConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentUpdateRepository studentUpdateRepository,
            LessonRepository lessonRepository
    ) {
        Student igor = new Student("Igor", "igor@gmail.com", LocalDate.of(1979, Month.SEPTEMBER, 3));
        Student roma = new Student("Roma", "roma@gmail.com", LocalDate.of(2001, Month.OCTOBER, 12), 1L);
        Student ula = new Student("Ula", "ula@gmail.com", LocalDate.of(2006, Month.JULY, 13), 1L);

        StudentUpdate lastUpdate = new StudentUpdate();
        StudentUpdate beforeLastUpdate = new StudentUpdate();

        Lesson englishLesson = new Lesson("English");
        Lesson franceLesson = new Lesson("France");
        Lesson chineseLesson = new Lesson("Chinese");

        Address address1 = new Address("Address 1");
        Address address2 = new Address("Address 2");
        Address address3 = new Address("Address 3");
        Address address4 = new Address("Address 4");

        igor.setUpdate(lastUpdate);
        ula.setUpdate(beforeLastUpdate);

        igor.setLessons(Arrays.asList(englishLesson, franceLesson));
        roma.setLessons(Arrays.asList(chineseLesson));

        igor.setChildren(Arrays.asList(roma, ula));

        roma.setAddresses(Arrays.asList(address1, address3));
        ula.setAddresses(Arrays.asList(address2, address4));

        return args -> {
            studentRepository.saveAll(List.of(igor));
        };
    }
}
