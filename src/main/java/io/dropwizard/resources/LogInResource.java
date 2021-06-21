package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.auth.Auth;
import io.dropwizard.models.Employee;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
public class LogInResource {

    public LogInResource(){

    }

    @GET
    @Path("/login")
    @JsonView(View.Public.class)
    public Employee logIn(@Auth Employee employee){
        return employee;
    }
}
