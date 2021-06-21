package io.dropwizard.services;

import java.util.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.models.Employee;
import io.dropwizard.persistence.DAO.EmployeeDAO;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;


public class AuthService implements Authenticator<BasicCredentials, Employee>, Authorizer<Employee> {
    private EmployeeDAO dao;

    @Inject
    public AuthService(EmployeeDAO dao){
        this.dao = dao;
    }

    @Override
    public Optional<Employee> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        Employee persoon = dao.getByEmailaddress(basicCredentials.getUsername());
        if (persoon != null && persoon.getWachtwoord() != null){
            if(BCrypt.checkpw(basicCredentials.getPassword(), persoon.getWachtwoord())){
                return Optional.of(persoon);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean authorize(Employee user, String roleName)
    {
        return user.getRechten().equals(roleName);
    }
}
