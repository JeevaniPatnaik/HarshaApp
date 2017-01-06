package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class District {

    int districtId;
    String districtName;
    String districtCode;
    int stateId;

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public District() {
    }

    public District(int districtId, String districtName, String districtCode, int stateId) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.districtCode = districtCode;
        this.stateId = stateId;
    }
}
