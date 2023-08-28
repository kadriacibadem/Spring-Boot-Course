package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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


}
