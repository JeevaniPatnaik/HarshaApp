package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 14-12-2016.
 */

public class Religion {

    private int religionId;
    private String religionCode;
    private String religionName;

    public int getReligionId() {
        return religionId;
    }

    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }

    public String getReligionCode() {
        return religionCode;
    }

    public void setReligionCode(String religionCode) {
        this.religionCode = religionCode;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public Religion() {
    }

    public Religion(int religionId, String religionName, String religionCode) {
        this.religionId = religionId;
        this.religionName = religionName;
        this.religionCode = religionCode;
    }

}
