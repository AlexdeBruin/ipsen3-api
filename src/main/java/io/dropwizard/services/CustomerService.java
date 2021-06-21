package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.CustomerDAO;
import io.dropwizard.persistence.DAO.ProjectDAO;
import io.dropwizard.persistence.DAO.SubjectDAO;

import java.util.List;

public class CustomerService {
    private CustomerDAO dao;
    private SubjectDAO subjectDao;
    private Customer customer;
    private ProjectDAO projectDao;

    public CustomerService(CustomerDAO dao, ProjectDAO projectDao, SubjectDAO subjectDao) {
        this.dao = dao;
        this.projectDao = projectDao;
        this.subjectDao = subjectDao;
    }


    public List<Customer> getCustomer() {
        return dao.getCustomer();
    }

    public Customer getCustomerByName(String customerName) {
        return dao.getCustomerByName(customerName);
    }


    public void addCustomer(String klantNaam, String projectNaam, String onderwerpNaam) {
        System.out.println("customerService addCustomer()");
        this.dao.addCustomer(klantNaam);
        Customer customer = this.dao.getCustomerByName(klantNaam);

        this.projectDao.addProject(projectNaam, customer.getCustomerId());
        Project project = projectDao.getProjectByCIdAndPName(projectNaam, customer.getCustomerId());

        this.subjectDao.addSubject(onderwerpNaam, project.getProjectID());
    }
}

