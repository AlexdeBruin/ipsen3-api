package io.dropwizard.models;

public class Categories {
    private String customerName;
    private String projectName;
    private String subjectName;

    public Categories(String customerName, String projectName, String subjectName) {
        this.customerName = customerName;
        this.projectName = projectName;
        this.subjectName = subjectName;
    }

    public Categories(){

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
