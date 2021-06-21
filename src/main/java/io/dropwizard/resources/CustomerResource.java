package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Categories;
import io.dropwizard.models.Customer;
import io.dropwizard.services.CustomerService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Singleton
@Path("/klanten")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"1", "0"}) // 1 = admin, 0 = personeel;
public class CustomerResource {
    CustomerService service;

    public CustomerResource(CustomerService service){
        this.service = service;
    }

    @GET
    @Path("/all")
    @JsonView(View.Public.class)
    @RolesAllowed("1, 0")
    public List<Customer> getCustomer() {
        return service.getCustomer();
    }

    @POST
    @Path("/add")
    @JsonView(View.Public.class)
    @RolesAllowed({"1", "0"})
    public void addProject(Categories categorie) {
        this.service.addCustomer(categorie.getCustomerName(), categorie.getProjectName(), categorie.getSubjectName());
    }

}
