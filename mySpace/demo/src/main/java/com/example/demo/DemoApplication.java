package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Employee;
import com.example.demo.model.Note;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.NoteRepository;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	NoteRepository noteRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public CommandLineRunner run(ApplicationContext appContext) {
		return args -> {
			String[] beans = appContext.getBeanDefinitionNames();
			Arrays.stream(beans).sorted().forEach(System.out::print);
		};
	}

//	@Bean
	public Employee getEmployee() {
			return employeeRepository.save(new Employee(1L, "Dev", "Noida", 1000, 20, "IT"));
	}
	/*@Bean
	public Employee getNote() {
		noteRepository.save(new Note(1L, "yes", "My note", "I am happy", new Timestamp(new Date().getTime())));
}*/

}
