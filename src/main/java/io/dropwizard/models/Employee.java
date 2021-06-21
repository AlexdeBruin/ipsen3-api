package io.dropwizard.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.security.Principal;

public class Employee implements Principal{

    private int personeelID;

    @NotEmpty
    @Length(min = 3, max = 40)
    private String voornaam;

    private String tussenvoegsel;

    @NotEmpty
    @Length(min = 3, max = 40)
    private String achternaam;

    @Length(min = 8)
    private String wachtwoord;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String rechten;

    @NotEmpty
    private String werkzaam;

    public Employee(){

    }

    public int getPersoneelID() {
        return personeelID;
    }

    public void setPersoneelID(int personeelID) {
        this.personeelID = personeelID;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getRechten() {
        return rechten;
    }

    public void setRechten(String rechten) {
        this.rechten = rechten;
    }

    public String getWerkzaam() {
        return werkzaam;
    }

    public void setWerkzaam(String werkzaam) {
        this.werkzaam = werkzaam;
    }


    public Employee(int personeelID, String voornaam, String tussenvoegsel, String achternaam, String email, String wachtwoord, String rechten, String werkzaam){
        this.personeelID = personeelID;
        this.voornaam = voornaam;

        if(tussenvoegsel != null && tussenvoegsel.equals("")) {
            this.tussenvoegsel = null;
        } else {
            this.tussenvoegsel = tussenvoegsel;
        }

        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;
        this.werkzaam = werkzaam;
    }

    public String getEmail() {
        return email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public Employee(int personeelID, String voornaam, String achternaam, String email, String wachtwoord, String rechten, String werkzaam){
        this.personeelID = personeelID;
        this.voornaam = voornaam;
        this.tussenvoegsel = null;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.rechten = rechten;

        this.werkzaam = werkzaam;

    }

    @Override
    public String getName() {
        return email;
    }


}
