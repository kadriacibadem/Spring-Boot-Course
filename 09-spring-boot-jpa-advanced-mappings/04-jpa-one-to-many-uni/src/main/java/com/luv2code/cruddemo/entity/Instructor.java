package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instructor")
@Entity
// bi-directional ilişki için ikinci tabloyu toString'den çıkar
//@ToString(exclude = {"instructorDetail"})
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(mappedBy = "instructor"
            , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
            , fetch = FetchType.LAZY)
    // fetch = FetchType.LAZY: instructor'ı çağırdığımızda courses'u çağırmayacak
    // fetch = FetchType.EAGER: instructor'ı çağırdığımızda courses'u da çağıracak
    // Lazy olduğunda program exception fırlatıyor, Eager olduğunda çalışıyor
    // Lazy olarak çalışması için DAOImpl'de instructorid ile kursları bulan metodu yazmamız gerek
    // daha sonra da InstructorDAO'da findInstructorById metodunu çağırırken
    // instructor'ı bulduktan sonra courses listesi oluşturup instructorid ile bulduğumuz kursları
    // courses listesine eklememiz gerek
    private List<Course> courses;

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // bi-directional ilişki için
    // add convenience methods for bi-directional relationship
    public void add(Course tempCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);
        tempCourse.setInstructor(this);
    }


}
