/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author LENOVO
 */
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Course;
import util.JPAUtil;

import java.util.List;

public class CourseRepository {

    public void addCourse(Course course) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(course);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }

    public List<Course> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }

    public Course findById(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Course.class, id);
    }

    public long countStudents(int courseId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("SELECT COUNT(s) FROM Course c JOIN c.students s WHERE c.id = :id", Long.class).setParameter("id", courseId).getSingleResult();
    }

    public List<Course> findCoursesNotEnrolledByStudent(int studentId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("""
                SELECT c FROM Course c
                WHERE :student NOT MEMBER OF c.students
                """, Course.class)
                .setParameter("student", em.find(model.Student.class, studentId))
                .getResultList();
    }
}
