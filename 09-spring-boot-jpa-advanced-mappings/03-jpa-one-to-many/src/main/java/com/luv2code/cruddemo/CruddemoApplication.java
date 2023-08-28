package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetailById(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorByJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//deleteInstructorByIdWithNotCourse(appDAO);
			deleteCourseById(appDAO);
		};
	}

	private void deleteCourseById(AppDAO appDAO) {
		appDAO.deleteCourseById(10);
		System.out.println("Deleted course with id: 1");
	}

	private void deleteInstructorByIdWithNotCourse(AppDAO appDAO) {
		appDAO.deleteInstructorByIdWithNotCourses(1);
		System.out.println("Deleted instructor with id: 1");
	}

	private void updateInstructor(AppDAO appDAO) {
		Instructor instructor = appDAO.findInstructorById(1);
		instructor.setFirstName("Kadri4");
		instructor.setLastName("Acibadem4");
		appDAO.update(instructor);
	}

	private void findInstructorByJoinFetch(AppDAO appDAO) {
		Instructor instructor = appDAO.findInstructorByJoinFetch(1);
		System.out.println("Found instructor: " + instructor);
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor(
				"Kadri3", "Acibadem", "kadriacibadem@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.kadriacibadem.com", "Running");

		instructorDetail.setInstructor(instructor);

		instructor.add(new Course("Java"));
		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);
		System.out.println("Done!");
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
		// Fetch type lazy olduğu için courses listesini elle oluşturup
		// instructorid ile bulduğumuz kursları courses listesine eklememiz gerek
		List<Course> courses = appDAO.findCoursesByInstructorId(1);
		instructor.setCourses(courses);


		System.out.println("Found instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourses());
		System.out.println("Instructor detail: " + instructor.getInstructorDetail());
	}

	private void deleteInstructor(AppDAO appDAO) {
		appDAO.deleteInstructorById(2);
		System.out.println("Deleted instructor with id: 1");
	}
}
