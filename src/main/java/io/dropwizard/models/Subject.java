package io.dropwizard.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Subject {

    private int subjectId;

    @NotEmpty
    @Length(max = 40)
    private String subjectName;

    private int projectId;

    public Subject(int subjectId, String subjectName, int projectId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.projectId = projectId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

}
