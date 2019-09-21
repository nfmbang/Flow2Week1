/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author nille
 */
public class PersonFacade implements IPersonFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Override
    public Person addPerson(String fName, String lName, String phone) {
        EntityManager em = emf.createEntityManager();
        Person p = new Person(fName, lName, phone);
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    @Override
    public Person deletePerson(int id) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Person.deleteById", Person.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Person.getAll", Person.class).getResultList();
    }

    @Override
    public Person editPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        return em.merge(p);
    }

}
