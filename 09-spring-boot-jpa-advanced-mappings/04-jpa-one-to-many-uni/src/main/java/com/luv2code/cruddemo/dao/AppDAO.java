package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    public void save(Instructor instructor);
    public Instructor findInstructorById(int id);
    public Instructor findInstructorByJoinFetch(int id);
    public void deleteInstructorById(int id);
    public InstructorDetail findInstructorDetailById(int id);
    public void deleteInstructorDetailById(int id);
    public List<Course> findCoursesByInstructorId(int id);
    public void update(Instructor instructor);
    public void deleteInstructorByIdWithNotCourses(int id);
    public void deleteCourseById(int id);
    public void save(Course course);
    public Course findCourseAndReviewsById(int id);
}
