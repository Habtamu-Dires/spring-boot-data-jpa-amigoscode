package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.github.javafaker.Faker;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            generateRandomStudents(studentRepository);

            PageRequest pageRequest = PageRequest.of(
                0, 
                5, 
                Sort.by("firstName").ascending());
            Page<Student> page =  studentRepository.findAll(pageRequest);
            
            for (Student student : page.getContent()) {
                System.out.println(student.getFirstName());
            }

            page = studentRepository.findAll(page.nextOrLastPageable());
            for (Student student : page.getContent()) {
                System.out.println(student.getFirstName());
            }
            
        };
    }

    private void sorting(StudentRepository studentRepository){
        Sort sort = Sort.by("firstName").ascending()
                .and(Sort.by("age").descending());
            
        studentRepository.findAll(sort)
        .forEach(student -> System.out.println(student.getFirstName() + " " + student.getAge()));
    }

    private void generateRandomStudents(StudentRepository studentRepository){
            Faker faker = new Faker();
           for(int i=0; i<20; i++){
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
                
               Student student = new Student(
                    firstName, 
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55)
                );
                studentRepository.save(student);
           }
        }
    
}
