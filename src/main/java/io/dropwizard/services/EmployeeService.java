package io.dropwizard.services;

import io.dropwizard.models.Employee;
import io.dropwizard.persistence.DAO.EmployeeDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class EmployeeService {
    EmployeeDAO dao;
    private final String STANDAARDWACHTWOORD = "Welkom1";

    public EmployeeService(EmployeeDAO dao){
        this.dao = dao;
    }

    public void setWerkzaam(int id, String werkzaam){
        int werkzaamBool = werkzaam.equals("1") ? 0 : 1;
        dao.setWerkzaam(werkzaamBool, id);
    }

    public Employee getPersoon(String email){
        return dao.getByEmailaddress(email);
    }

    public List<Employee> getAll() {
        return dao.getAll();
    }

    public void addUser(Employee employee){
        employee.setWachtwoord(BCrypt.hashpw(STANDAARDWACHTWOORD, BCrypt.gensalt()));
        dao.add(employee);
    }

    public void changePassword(String newPassword, int ID, String oldPassword) {

        String passwordCheck = dao.getPasswordById(ID);

        if(BCrypt.checkpw(oldPassword, passwordCheck)){
        String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        dao.setWachtwoord(hash, ID);
            System.out.println("Hetzelfde:");
        }
    }
}
