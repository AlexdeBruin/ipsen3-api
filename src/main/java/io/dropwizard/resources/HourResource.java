package io.dropwizard.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.View;
import io.dropwizard.models.RegisteredHour;
import io.dropwizard.services.RegisteredHourService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Singleton
@Path("/uren")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"1", "0"}) // 1 = admin, 0 = personeel;
public class HourResource {
    private RegisteredHourService service;

    public HourResource(RegisteredHourService service){
        this.service = service;
    }

    //  Voorbeelden voor de URL:
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1&klant=WebEdu
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1&klant=WebEdu&project=UrenRegistratieApplicatie
    //  localhost:8080/uren/getby?begindatum=2017-1-1&einddatum=2018-1-1&klant=WebEdu&project=UrenRegistratieApplicatie&onderwerp=Applicatie


    /**
     * Ontvangt een personeelID en geeft alle gewerkte uren voor dit personeelID terug.
     * @param id
     * @return
     */
    @GET
    @Path("/getbyid")
    @JsonView(View.Public.class)
    public List<RegisteredHour> getUren(
            @QueryParam("id") int id){

        return service.getUrenByPersoneelId(id);
    }

    @GET
    @Path("/getall")
    @JsonView(View.OnlyAdmins.class)
    @RolesAllowed("1")
    public List<RegisteredHour> getAllUren(){
        return service.getAllUren();
    }

    @POST
    @Path("/setHour")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    public void setHours( RegisteredHour registeredHour) {
        this.service.setHours(registeredHour);
    }

    @POST
    @Path("/confirm")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.OnlyAdmins.class)
    @RolesAllowed("1")
    public void setConfirmed(RegisteredHour uur){
        this.service.setConfirmed(uur);
    }

    @POST
    @Path("/updateHour")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void updateHour(RegisteredHour hour){
        System.out.println(hour.getHourID());
        this.service.updateHour(hour);}

}
