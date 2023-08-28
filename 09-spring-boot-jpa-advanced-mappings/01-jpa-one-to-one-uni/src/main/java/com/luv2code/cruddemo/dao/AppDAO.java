package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    public void save(Instructor instructor);
    public Instructor findInstructorById(int id);
    public void deleteInstructorById(int id);
    public InstructorDetail findInstructorDetailById(int id);
    public void deleteInstructorDetailById(int id);
}
