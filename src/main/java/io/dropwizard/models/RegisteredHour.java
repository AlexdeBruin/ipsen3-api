package io.dropwizard.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class RegisteredHour {


    @NotEmpty
    private int hourID;

    @NotNull
    private int employeeId;

    @NotNull
    private String startingDate;

    @NotNull
    private String startingTime;

    @NotNull
    private String endingTime;

    @NotNull
    private String customerName;

    @NotNull
    private String projectName;

    @NotNull
    private String subjectName;

    @NotNull
    private int customerId;

    @NotNull
    private int projectId;

    @NotNull
    private int subjectId;
    
    private String comment;

    @NotNull
    private boolean confirmed;

    @NotNull
    private String employeeName;

    @NotNull
    private boolean isChanged = false;

    public RegisteredHour(int uurId, int employeeId, String startingDate, String startingTime, String endingTime, String customerName, String projectName, String subjectName, int customerId, int projectId, int subjectId, String comment, boolean confirmed, String employeeName, boolean isChanged) {
        this.hourID = uurId;
        this.employeeId = employeeId;
        this.startingDate = startingDate;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.customerName = customerName;
        this.projectName = projectName;
        this.subjectName = subjectName;
        this.comment = comment;
        this.confirmed = confirmed;
        this.employeeName = employeeName;
        this.isChanged = isChanged;
        this.projectId = projectId;
        this.customerId = customerId;
        this.subjectId = subjectId;
    }

    public RegisteredHour(int hourID, String startingDate, String startingTime, String endingTime, String comment, boolean confirmed, int employeeId, String customerName, String projectName, String subjectName, String employeeName) {
        this.hourID = hourID;
        this.employeeId = employeeId;
        this.startingDate = startingDate;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.customerName = customerName;
        this.projectName = projectName;
        this.subjectName = subjectName;
        this.comment = comment;
        this.confirmed = confirmed;
        this.employeeName = employeeName;
    }

    public RegisteredHour(String startingDate, String startingTime, String endingTime, String customerName, String projectName, String subjectName, String comment, int employeeID) {
        this.startingDate = startingDate;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.customerName = customerName;
        this.projectName = projectName;
        this.subjectName = subjectName;
        this.comment = comment;
        this.employeeId = employeeID;
    }

    public RegisteredHour(){
    }

    public int getUurId() {
        return hourID;
    }

    public void setUurId(int uurId) {
        this.hourID = uurId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public int getHourID() {
        return hourID;
    }

    public void setHourID(int hourID) {
        this.hourID = hourID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }




}


