package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 07-01-2017.
 */

public class MaritalStatus {

    private int maritalStatusId;
    private String maritalStatusName;
    private String gMaritalStatusCode;

    public int getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(int maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    public String getgMaritalStatusCode() {
        return gMaritalStatusCode;
    }

    public void setgMaritalStatusCode(String gMaritalStatusCode) {
        this.gMaritalStatusCode = gMaritalStatusCode;
    }

    public MaritalStatus() {
    }

    public MaritalStatus(int maritalStatusId, String maritalStatusName, String gMaritalStatusCode) {
        this.maritalStatusId = maritalStatusId;
        this.maritalStatusName = maritalStatusName;
        this.gMaritalStatusCode = gMaritalStatusCode;
    }
}
