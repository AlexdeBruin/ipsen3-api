package io.dropwizard.services;

import io.dropwizard.models.Customer;
import io.dropwizard.models.Project;
import io.dropwizard.models.Subject;
import io.dropwizard.persistence.DAO.CustomerDAO;
import io.dropwizard.persistence.DAO.ProjectDAO;
import io.dropwizard.persistence.DAO.SubjectDAO;

import java.util.List;

public class SubjectService {
    ProjectDAO projectDao;
    CustomerDAO customerDao;
    SubjectDAO dao;
    public SubjectService(SubjectDAO subjectDao, ProjectDAO projectDao, CustomerDAO customerDao) {
        this.dao = subjectDao;
        this.projectDao = projectDao;
        this.customerDao = customerDao;
    }

    public Subject getSubjectByPIDSName(int projectID, String subjectName) {
        return this.dao.getSubjectByPIDSName(projectID, subjectName);
    }

    public List<Subject> getSubjects(String projectName, String customerName) {

        Customer customer = customerDao.getCustomerByName(customerName);
        Project project = projectDao.getProjectByCIdAndPName(projectName, customer.getCustomerId());

        return this.dao.getSubjects(project.getProjectID());
    }

    public void addSubject(String klantnaam, String projectnaam, String onderwerpnaam){

        System.out.println("subjectservice addSubject()");
        Customer customer = this.customerDao.getCustomerByName(klantnaam);

        this.projectDao.addProject(projectnaam, customer.getCustomerId());
        Project project = projectDao.getProjectByCIdAndPName(projectnaam, customer.getCustomerId());

        this.dao.addSubject(onderwerpnaam, project.getProjectID());
    }

//    public void addSubject(int projectID, String onderwerpnaam) {
//        dao.addSubject(onderwerpnaam, projectID);
//    }


    }
