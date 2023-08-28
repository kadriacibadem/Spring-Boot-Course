package com.kadri.demo.rest;

import com.kadri.demo.entity.Student;
import com.kadri.demo.errors.StudentNotFoundException;
import com.kadri.demo.response.StudentErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Kadri", "Kadri"));
        theStudents.add(new Student("Kadri2", "Kadri2"));
        theStudents.add(new Student("Kadri3", "Kadri3"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) throws StudentNotFoundException {
        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found for - " + studentId);
        }
        return theStudents.get(studentId);
    }

}
