package com.enset.studentcrud;

import com.enset.studentcrud.Entities.Genre;
import com.enset.studentcrud.Entities.Student;
import com.enset.studentcrud.Repositories.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication()
public class StudentCrudApplication {

    @Autowired
    studentRepository repository ;
    public static void main(String[] args) {
        SpringApplication.run(StudentCrudApplication.class, args);
    }

    @Bean
    CommandLineRunner start() {
        return args -> {
            repository.save(new Student(1L,"Lamkirich", "Lahcen","lahcen.lamkirich@gmail.com",new Date(), Genre.MASCULIN,true));
        };
    }

}
