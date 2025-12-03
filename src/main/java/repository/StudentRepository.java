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
import model.Student;
import util.JPAUtil;
import java.util.List;

public class StudentRepository {
    public void addStudent(Student student){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(student);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
    }
    public List<Student> findAll(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }
    public Student findById(int id){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Student.class, id);
    }
    public void enrollStudent(int studentId, int courseId){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            var student = em.find(Student.class, studentId);
            var course = em.find(model.Course.class, courseId);
            if(!student.getCourses().contains(course)){
                student.getCourses().add(course);
                em.merge(student);
            }
            tx.commit();
        } catch (Exception e){
            if (tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
    }
}
