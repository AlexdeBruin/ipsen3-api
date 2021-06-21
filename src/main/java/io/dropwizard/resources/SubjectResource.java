package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.Categories;
import io.dropwizard.models.Subject;
import io.dropwizard.services.SubjectService;


import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/subjects")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"1", "0"}) // 1 = admin, 0 = personeel;
public class SubjectResource {
    SubjectService service;
    public  SubjectResource(SubjectService service){
        this.service = service;
    }

    @GET
    @Path("/allByName")
    @JsonView(View.Public.class)
    @RolesAllowed("1, 0")
    public List<Subject> getSubjects(@QueryParam("project") String projectName, @QueryParam("klant") String customerName ) {
        return service.getSubjects(projectName, customerName);
    }

    @POST
    @Path("/add")
    @JsonView(View.Public.class)
    @RolesAllowed("1")
    public void addProject(Categories categorie) {
        this.service.addSubject(categorie.getCustomerName(), categorie.getProjectName(), categorie.getSubjectName());
    }
}
