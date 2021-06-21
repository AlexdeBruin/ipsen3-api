package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Employee;
import io.dropwizard.services.EmployeeService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/personeel")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"1", "0"}) // 1 = admin, 0 = personeel;
public class EmployeeResource {
    private EmployeeService service;

    public EmployeeResource(EmployeeService service){
        this.service = service;
    }

    @GET
    @Path("/getall")
    @JsonView(View.Public.class)
    @RolesAllowed("1")
    public List<Employee> getAll(){
        return service.getAll();
    }

    @POST
    @Path("/werkzaam")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("1")
    public void updateWerkzaam(@QueryParam("id") int id, @QueryParam("werkzaam") String werkzaam){
        System.out.println(id + " " + werkzaam);
        service.setWerkzaam(id, werkzaam);
    }

    @POST
    @Path("/add")
    @Consumes (MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    @RolesAllowed("1")
    public void createAccount(@Valid Employee employee) {
        //TODO @Valid toevoegen, maar werkt niet... ?
        service.addUser(employee);
    }

    @POST
    @Path("/wachtwoord")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"1", "0"})
    public void veranderWachtwoord(@QueryParam("wachtwoord") String newPassword, @QueryParam("id") int ID, @QueryParam("oldPassword") String oldPassword ) {
        this.service.changePassword(newPassword, ID, oldPassword);
    }
}
