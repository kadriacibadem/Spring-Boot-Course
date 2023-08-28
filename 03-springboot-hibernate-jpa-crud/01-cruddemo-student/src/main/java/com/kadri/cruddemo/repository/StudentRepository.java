package com.kadri.cruddemo.repository;

import com.kadri.cruddemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudentRepository {
     List<Student> findAll();
    //public List<Student> findStudentByFirstNameAndLastName(String name,String lastName);

     Student findById(int id);

     List<Student> findByLastName(String lastName);

     void updateStudent(Student student);

     void deleteStudentById(int id);

     int deleteAll();
}
