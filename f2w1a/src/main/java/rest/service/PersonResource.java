package rest.service;

import DTO.PersonDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import facades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("person")
public class PersonResource {

    static Gson gson = new Gson();
    PersonFacade facade = new PersonFacade();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        throw new UnsupportedOperationException();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") int id) {
        // try {
        return gson.toJson(facade.getPerson(id));
        // } catch (Exception e) {
        //return Gson.toJson(new dto.Error(e.getMessage(), 404));
        //  }
    }

    @GET
    @Path("all")
    public String getAll() {
        return gson.toJson(facade.getAllPersons());
    }

    @POST
    @Path("add/{person}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addPerson(Person p, @PathParam("person") String person) {
        facade.addPerson(gson.fromJson(person, Person.class));
    }

    @POST
    @Path("edit/{person}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void editPerson(@PathParam("person") String p) {
        facade.editPerson(gson.fromJson(p, Person.class));
    }

    @POST
    @Path("delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void deletePerson(@PathParam("id") int id) {
        facade.deletePerson(id);
    }
}
