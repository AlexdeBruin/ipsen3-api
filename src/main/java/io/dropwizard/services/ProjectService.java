package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.persistence.DAO.CustomerDAO;
import io.dropwizard.persistence.DAO.ProjectDAO;
import io.dropwizard.persistence.DAO.SubjectDAO;

import java.util.List;

public class ProjectService {
    private SubjectDAO subjectDao;
    private CustomerDAO customerDao;
    private ProjectDAO dao;
    public ProjectService(ProjectDAO dao, CustomerDAO customerDao, SubjectDAO subjectDao) {
        this.subjectDao = subjectDao;
        this.dao = dao;
        this.customerDao = customerDao;
    }

    public List<Project> getProjects(String customerName) {
        Customer customer = customerDao.getCustomerByName(customerName);
        return this.dao.getProjectByID(customer.getCustomerId());
    }

    public void addProject(String klantNaam, String projectNaam, String onderwerpNaam) {
        System.out.println("projectService addProject()");
        Project project;
        Customer customer = customerDao.getCustomerByName(klantNaam);

        dao.addProject(projectNaam, customer.getCustomerId());

        project = dao.getProjectByCIdAndPName(projectNaam, customer.getCustomerId());
        subjectDao.addSubject(onderwerpNaam, project.getProjectID());
    }

//    public void addProject(int klant_ID, String projectnaam, String onderwerpnaam) {
//
//        Project project;
//        dao.addProject(projectnaam, klant_ID);
//
//        project = dao.getProjectByCIdAndPName(projectnaam, klant_ID);
//        subjectDao.addSubject(onderwerpnaam, project.getProjectID());
//    }
}
