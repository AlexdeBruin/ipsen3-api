package io.dropwizard.persistence.DAO;

import io.dropwizard.models.Customer;
import io.dropwizard.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DAO{
    public CustomerDAO(ConnectionPool pool){
        super(pool);
    }

    public List<Customer> getCustomer() {
        List<Customer> klanten = null;

        Connection con = this.pool.checkout();
        try {
            PreparedStatement getCustomers = con.prepareStatement("SELECT klant_ID, klant_naam FROM klant");
            ResultSet results = getCustomers.executeQuery();
            klanten = new ArrayList<>();

            while(results.next()) {
                klanten.add(new Customer(results.getInt("klant_ID"), results.getString("klant_naam")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return klanten;
    }

    public void add(String customerName){
        Connection con = pool.checkout();
        try{
            PreparedStatement statement = con.prepareStatement("INSERT INTO klant (klant_naam) VALUES (?)");
            statement.setString(1, customerName);

        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
    }

    public Customer getCustomerByName(String customerName) {
        Connection con = pool.checkout();
        Customer customer = null;
        try{
            PreparedStatement getCustomer = con.prepareStatement("SELECT klant_ID, klant_naam FROM klant WHERE klant_naam = (?)");
            getCustomer.setString(1, customerName);
            ResultSet results = getCustomer.executeQuery();
            if (results.next()) {
                customer = new Customer(results.getInt("klant_ID"), results.getString("klant_naam"));
            }



        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }
        return customer;
    }

    public void addCustomer(String klantnaam){
        Connection con = pool.checkout();
        Customer customer = null;
        try {
            PreparedStatement addCustomer = con.prepareStatement("INSERT INTO klant (klant_naam) VALUE (?)");
            addCustomer.setString(1, klantnaam);
            addCustomer.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            pool.checkIn(con);
        }

    }
}
