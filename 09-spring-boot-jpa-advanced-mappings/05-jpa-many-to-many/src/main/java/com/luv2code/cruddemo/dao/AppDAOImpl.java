package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{


    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    // Lazy Fetch Type'da tek sorguda instructor'ı bulup
    // courses listesini de bulmak için join fetch kullanıyoruz
    public Instructor findInstructorByJoinFetch(int id) {
        return entityManager.createQuery(
                "from Instructor i join fetch i.courses join fetch i.instructorDetail where i.id=:id", Instructor.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // cascade olduğu için instructor_detail tablosunda
        // bağlantılı olduğu satırı da siler
        Instructor instructor = findInstructorById(id);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);

        // instructorDetail tablosunu silip instructor silmek istemiyorsak
        // aşağıdaki satırı kaldırıyoruz
        //instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // Course tablosunda instructor_id ile ilişkili satırları bulup
        // Course listesi döndürüyoruz
        return entityManager.createQuery("from Course c where c.instructor.id=:id", Course.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorByIdWithNotCourses(int id) {
        Instructor instructor = findInstructorById(id);
        List<Course> courses = instructor.getCourses();

        // Kurslar tablosundan instructorın bağlantısını koparmak için
        for(Course tempCourses : courses){
            tempCourses.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsById(int id) {
        return entityManager.createQuery(
                "from Course c join fetch c.reviews where c.id=:id", Course.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Course findCoursesAndStudentsByCourseId(int id) {
        return entityManager.createQuery(
                "from Course c join fetch c.students where c.id=:id", Course.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Student findStudentsAndCoursesByStudentId(int id) {
        return entityManager.createQuery(
                "from Student s join fetch s.courses where s.id=:id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteCourseByIdWithNotStudents(int id) {
        Course course = entityManager.find(Course.class, id);
        List<Student> students = course.getStudents();

        for(Student tempStudent : students){
            tempStudent.getCourses().remove(course);
        }

        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void deleteStudentByIdWithNotCourses(int id) {
        Student student = entityManager.find(Student.class, id);
        List<Course> courses = student.getCourses();

        for(Course tempCourse : courses){
            tempCourse.getStudents().remove(student);
        }

        entityManager.remove(student);
    }


}
