package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 14-12-2016.
 */

public class Occupation {

    private int occupationId;
    private String occupationCode;
    private String occupationName;

    public int getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public Occupation() {
    }

    public Occupation(int occupationId, String occupationName, String occupationCode) {
        this.occupationId = occupationId;
        this.occupationName = occupationName;
        this.occupationCode = occupationCode;
    }

}
