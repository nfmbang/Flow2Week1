/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Semester;
import Entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nille
 */
public class Facade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("duhr_F2W1T_jar_1PU");

    public List<Student> findAllStudents() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public List<Student> findAllStudentsByFirstName(String firstName) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Student.findByFirstname", Student.class).setParameter("firstname", firstName).getResultList();
    }

    public List<Student> findAllStudentsByLastName(String lastName) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Student.findByLastname", Student.class).setParameter("lastname", lastName).getResultList();
    }

    public Student addStudent(Student s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return s;
    }

    public Student assignSemesterById(Long studentID, Long semesterID) {
        EntityManager em = emf.createEntityManager();
        try {
            Student s = em.find(Student.class, studentID);
            s.setCurrentsemesterId(em.find(Semester.class, semesterID));
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }

    public int studentCount() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Student.count", Integer.class).getSingleResult();
    }

    public int studentCountForSemester(String semName) {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("SELECT COUNT(s) FROM Student s INNER JOIN s.Semester c ON s.currentsemesterId = c.id WHERE c.name = :sname", Integer.class).setParameter("sname", semName).getSingleResult();
    }
}
