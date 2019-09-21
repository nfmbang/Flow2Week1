package rest;

import DTO.MsgDTO;
import Exceptions.PersonNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/F2W1W",
            "dev",
            "ax2",
            EMF_Creator.Strategy.DROP_AND_CREATE);
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") int id) {
        try {
            return GSON.toJson(FACADE.getPerson(id));
        } catch (PersonNotFoundException e) {
            return GSON.toJson(new MsgDTO(e.getMessage(), 404));
        }
    }

    @GET
    @Path("all")
    public String getAll() {
        return GSON.toJson(FACADE.getAllPersons());
    }

    @POST
    @Path("add/{person}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addPerson(@PathParam("person") String person) {
        FACADE.addPerson(GSON.fromJson(person, Person.class));
    }

    @POST
    @Path("edit/{person}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String editPerson(@PathParam("person") String p) {
        try {
            FACADE.editPerson(GSON.fromJson(p, Person.class));
        } catch (PersonNotFoundException ex) {
            return GSON.toJson(new MsgDTO(ex.getMessage(), 404));
        }
        return GSON.toJson(new MsgDTO("Success!", 200));
    }

    @POST
    @Path("delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String deletePerson(@PathParam("id") int id) {
        try {
            FACADE.deletePerson(id);
        } catch (PersonNotFoundException ex) {
            return GSON.toJson(new MsgDTO(ex.getMessage(), 404));
        } catch (Exception e) {
            return GSON.toJson(new MsgDTO("Internal Server Problem. We are sorry for the inconvienience", 500));
        }
        return GSON.toJson(new MsgDTO("Success!", 200));
    }

}
