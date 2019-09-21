/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import Exceptions.PersonNotFoundException;
import entities.Person;
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

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

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

    public Person addPerson(Person p) {
        EntityManager em = emf.createEntityManager();
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
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Person.deleteById", Person.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new PersonNotFoundException("Could not delete, provided id does not exist");
        }
    }

    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Person.class, id);
        } catch (Exception e) {
            throw new PersonNotFoundException("No person with provided id found");
        }
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Person.getAll", Person.class).getResultList();
    }

    @Override
    public Person editPerson(Person p) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            return em.merge(p);
        } catch (Exception e) {
            throw new PersonNotFoundException("No Person with provided id found.");
        }
    }

}
