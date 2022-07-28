package com.example.spring_boot_rest_api.config;

import com.example.spring_boot_rest_api.adresses.Address;
import com.example.spring_boot_rest_api.grades.Grade;
import com.example.spring_boot_rest_api.grades.GradeRepository;
import com.example.spring_boot_rest_api.lessons.Lesson;
import com.example.spring_boot_rest_api.mapper.GoodMapper;
import com.example.spring_boot_rest_api.student_updates.StudentUpdate;
import com.example.spring_boot_rest_api.students.Student;
import com.example.spring_boot_rest_api.students.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
public class AppConfig extends WebMvcConfigurationSupport {

    @Bean
    @Override
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
        dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ISO_LOCAL_DATE);
        dateTimeRegistrar.registerFormatters(conversionService);

        return conversionService;
    }

    @Bean
    public GoodMapper goodMapper() {
        return new GoodMapper();
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            GradeRepository gradeRepository
    ) {
//        Student igor = new Student(123L, "Igor", "igor@gmail.com", LocalDate.of(1979, Month.SEPTEMBER, 3));
//        Student roma = new Student(456L, "Roma", "roma@gmail.com", LocalDate.of(2001, Month.OCTOBER, 12), 123L);
//        Student ula = new Student(789L, "Ula", "ula@gmail.com", LocalDate.of(2006, Month.JULY, 13), 123L);
//
//        StudentUpdate lastUpdate = new StudentUpdate();
//        StudentUpdate beforeLastUpdate = new StudentUpdate();
//
//        Lesson englishLesson = new Lesson("English");
//        Lesson franceLesson = new Lesson("France");
//        Lesson chineseLesson = new Lesson("Chinese");
//
//        Address address1 = new Address("Address 1");
//        Address address2 = new Address("Address 2");
//        Address address3 = new Address("Address 3");
//        Address address4 = new Address("Address 4");
//
//        Grade grade1 = new Grade(5);
//        Grade grade2 = new Grade(3);
//        Grade grade3 = new Grade(4);
//
//        igor.setUpdate(lastUpdate);
//        ula.setUpdate(beforeLastUpdate);
//
//        igor.setLessons(Arrays.asList(englishLesson, franceLesson));
//        roma.setLessons(Arrays.asList(chineseLesson));
//
//        igor.addChild(roma);
//        igor.addChild(ula);
//
//        roma.addAddress(address1);
//        roma.addAddress(address3);
//        ula.addAddress(address2);
//        ula.addAddress(address4);

//        Commented cause FK constraint reject to delete STUDENT entity
//        grade1.setStudent(roma);
//        grade2.setStudent(roma);
//        grade3.setStudent(ula);

        return args -> {
//            studentRepository.saveAll(List.of(igor));
//            gradeRepository.saveAll(List.of(grade1, grade2, grade3));
        };
    }
}
