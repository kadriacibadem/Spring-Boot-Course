package com.kadri.cruddemo;

import com.kadri.cruddemo.entity.Student;
import com.kadri.cruddemo.repository.StudentRepoImpl;
import com.kadri.cruddemo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepoImpl StudentRepoImpl) {
		return runner -> {
			//createUser(studentRepository);
			//getAllUser(StudentRepoImpl);
			//getByLastName(StudentRepoImpl);
			//updateStudent(StudentRepoImpl);
			//deleteStudent(StudentRepoImpl);
			deleteAllStudent(StudentRepoImpl);
		};
	}

	private void deleteAllStudent(StudentRepoImpl studentRepoImpl) {
		int count = studentRepoImpl.deleteAll();
		System.out.println("Total number of deleted students "+count);
	}


	private void deleteStudent(StudentRepoImpl studentRepoImpl) {
		int studentId = 1;
		studentRepoImpl.deleteStudentById(studentId);
		System.out.println("Student deleted successfully for id "+studentId);
	}

	private void updateStudent(StudentRepoImpl studentRepoImpl) {
		int studentId = 1;
		Student student = studentRepoImpl.findById(studentId);
		student.setFirstName("Kadri 3");
		studentRepoImpl.updateStudent(student);
		System.out.println("Student updated successfully for "+student);
	}

	private void getByLastName(StudentRepoImpl studentRepoImpl) {
		List<Student> students = studentRepoImpl.findByLastName("acibadem");
		students.forEach(System.out::println);
	}

	private void getAllUser(StudentRepository studentRepository) {
		List<Student> students = studentRepository.findAll();
		students.forEach(System.out::println);
	}


}
