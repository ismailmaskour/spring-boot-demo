package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	

	// @Bean
	// CommandLineRunner commandLineRunner(StudentRepository studentRepository){
	// 	return args->{
	// 		Student maria= new Student(null, "Maria", "Jones", "maria.jones@gmail.com", 21);
	// 		studentRepository.save(maria);
	// 	};
	// }

}
