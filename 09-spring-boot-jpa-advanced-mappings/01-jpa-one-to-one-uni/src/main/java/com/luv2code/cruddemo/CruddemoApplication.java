package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			deleteInstructorDetailById(appDAO);
		};
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		appDAO.deleteInstructorDetailById(6);
		System.out.println("Deleted instructor detail with id: 6");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(6);
		System.out.println("Found instructor detail: " + instructorDetail);
	}


	private void createInstructor(AppDAO appDAO) {
		/*
		Instructor instructor = new Instructor(
				"Kadri", "Acibadem", "kadriacibadem@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.kadriacibadem.com", "Coding");

		// associate the objects
		instructorDetail.setInstructor(instructor);
		*/
		Instructor instructor = new Instructor(
				"Kadri2", "Acibadem", "kadriacibadem@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.kadriacibadem.com", "Running");

		// associate the objects
		instructorDetail.setInstructor(instructor);

		// save the instructor
		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);

		System.out.println("Done!");
	}
	private void findInstructor(AppDAO appDAO) {
		Instructor instructor = appDAO.findInstructorById(1);
		System.out.println("Found instructor: " + instructor);
	}

	private void deleteInstructor(AppDAO appDAO) {
		appDAO.deleteInstructorById(2);
		System.out.println("Deleted instructor with id: 1");
	}
}
