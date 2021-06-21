package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Employee;
import io.dropwizard.persistence.ConnectionPool;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends DAO{

    @Inject
    public EmployeeDAO(ConnectionPool pool) {
        super(pool);
    }

    public Employee getByEmailaddress(String email) {
        Employee model = new Employee();
        try {
            Connection con = pool.checkout();
            PreparedStatement getGebruiker = con.prepareStatement("SELECT * FROM personeel WHERE email =?");
            getGebruiker.setString(1, email);

            ResultSet results = getGebruiker.executeQuery();
            pool.checkIn(con);

            if (results.next()) {
                if (results.getString("tussenvoegsel") != null) {
                    model = new Employee(results.getInt("persoonID"), results.getString("achternaam"), results.getString("tussenvoegsel"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                } else {
                    model = new Employee(results.getInt("persoonID"), results.getString("achternaam"), results.getString("voornaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }

    public void setWerkzaam(int werkzaam, int id){
        try{
            Connection con = pool.checkout();
            PreparedStatement statement = con.prepareStatement("update personeel set werkzaam = ? where persoonID = ?");
            statement.setInt(1, werkzaam);
            statement.setInt(2, id);
            statement.executeUpdate();
            pool.checkIn(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Employee> getAll(){
        ResultSet results;
        List<Employee> alHetPersoneel = new ArrayList<>();
        Connection con = pool.checkout();

        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM personeel");
            results = statement.executeQuery();
            pool.checkIn(con);
            while (results.next()){
                Employee model;
                if (results.getString("tussenvoegsel") != null) {
                    model = new Employee(results.getInt("persoonID"), results.getString("voornaam"), results.getString("tussenvoegsel"), results.getString("achternaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                } else {
                    model = new Employee(results.getInt("persoonID"), results.getString("voornaam"), results.getString("achternaam"), results.getString("email"), results.getString("wachtwoord"), results.getString("rechten"), results.getString("werkzaam"));
                }
                alHetPersoneel.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            pool.checkIn(con);
        }
        return alHetPersoneel;
    }

    public void add(Employee employee){
        try{
            Connection con = pool.checkout();
            PreparedStatement statement = con.prepareStatement("INSERT INTO personeel (achternaam, tussenvoegsel, voornaam, email, wachtwoord, rechten, werkzaam) VALUES (?, ?, ?, ?, ?, ?, ?)");

            System.out.println(employee.getVoornaam());
            statement.setString(1, employee.getAchternaam());

            if (employee.getTussenvoegsel() == null) {
                statement.setString(2, null);
            } else {
                statement.setString(2, employee.getTussenvoegsel());
            }
            statement.setString(3, employee.getVoornaam());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getWachtwoord());
            if(employee.getRechten().equals("Employee")){
                statement.setString(6, "0");
            } else {
                statement.setString(6, "1");
            }

            statement.setString(7, "1");

            statement.executeQuery();
        } catch(SQLException e){
            e.printStackTrace();

        }
    }

    public void setWachtwoord(String newPassword, int ID) {
        Connection con = pool.checkout();
        String id = Integer.toString(ID);
        try {

            PreparedStatement changePassword = con.prepareStatement("UPDATE personeel SET wachtwoord = (?) WHERE persoonID = (?);");
            changePassword.setString(1,newPassword);
            changePassword.setString(2,id);
            changePassword.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }

    public String getPasswordById(int ID) {
        Connection con = pool.checkout();
        String pass = null;
        try{
            PreparedStatement getPassword = con.prepareStatement("SELECT wachtwoord FROM personeel WHERE persoonID = (?)");
            getPassword.setInt(1, ID);
            ResultSet results = getPassword.executeQuery();
            if (results.next()) {
                pass = results.getString("wachtwoord");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return pass;
    }

}
