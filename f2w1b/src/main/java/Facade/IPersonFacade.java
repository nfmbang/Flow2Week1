/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Person;
import java.util.List;

/**
 *
 * @author nille
 */
public interface IPersonFacade {

    public Person addPerson(String fName, String lName, String phone);

    public Person deletePerson(int id);

    public Person getPerson(int id);

    public List<Person> getAllPersons();

    public Person editPerson(Person p);
}
