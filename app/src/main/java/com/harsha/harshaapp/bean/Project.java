package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 09-01-2017.
 */

public class Project {

    int projectId;
    String projectName;
    String donorName;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public Project() {
    }

    public Project(int projectId, String projectName, String donorName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.donorName = donorName;
    }
}
