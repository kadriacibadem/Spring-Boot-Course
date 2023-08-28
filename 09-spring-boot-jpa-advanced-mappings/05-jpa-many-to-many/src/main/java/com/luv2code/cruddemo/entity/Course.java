package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "course")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"instructor", "reviews", "students"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

     @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        @JoinTable(
                name = "course_student",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name="student_id")
        )
        private List<Student> students;

    public void add(Review review) {
        if(reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void add(Student student) {
        if(students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public Course(String title) {
        this.title = title;
    }

}
