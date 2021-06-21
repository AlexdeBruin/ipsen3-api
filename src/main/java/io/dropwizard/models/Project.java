package io.dropwizard.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Project {

    @NotEmpty
    @Length(max = 40)
    private String projectName;
    private int customerID;
    private int projectID;

    public Project(int projectID, String projectName, int customerID){

        this.projectID = projectID;
        this.customerID = customerID;
        this.projectName = projectName;
    }

    public int getProjectID(){return projectID; }

    public void setProjectID(int projectID){this.projectID = projectID;}

    public String getProjectName(){return projectName;}

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

}
