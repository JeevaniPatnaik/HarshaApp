package com.harsha.harshaapp.bean;

/**
 * Created by Jeevani on 1/11/2017.
 */

public class ImpactInfo {

    private int impactId;
    private int baselineId;
    private int familyHeadId;
    private String land;
    private int noOfPlants;
    private int yearOfPlanting;
    private int herr;
    private int projectId;
    private String beneficiaryName;
    private String husbandWifeName;
    private int stateId;
    private int districtId;
    private int villageId;
    private String impactIncome;

    public int getImpactId() {
        return impactId;
    }

    public void setImpactId(int impactId) {
        this.impactId = impactId;
    }

    public int getBaselineId() {
        return baselineId;
    }

    public void setBaselineId(int baselineId) {
        this.baselineId = baselineId;
    }

    public int getFamilyHeadId() {
        return familyHeadId;
    }

    public void setFamilyHeadId(int familyHeadId) {
        this.familyHeadId = familyHeadId;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public int getNoOfPlants() {
        return noOfPlants;
    }

    public void setNoOfPlants(int noOfPlants) {
        this.noOfPlants = noOfPlants;
    }

    public int getYearOfPlanting() {
        return yearOfPlanting;
    }

    public void setYearOfPlanting(int yearOfPlanting) {
        this.yearOfPlanting = yearOfPlanting;
    }

    public int getHerr() {
        return herr;
    }

    public void setHerr(int herr) {
        this.herr = herr;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getHusbandWifeName() {
        return husbandWifeName;
    }

    public void setHusbandWifeName(String husbandWifeName) {
        this.husbandWifeName = husbandWifeName;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public String getImpactIncome() {
        return impactIncome;
    }

    public void setImpactIncome(String impactIncome) {
        this.impactIncome = impactIncome;
    }

    public ImpactInfo() {

    }

    public ImpactInfo(int impactId, int baselineId, int familyHeadId, String land, int noOfPlants, int yearOfPlanting, int herr, int projectId, String beneficiaryName, String husbandWifeName, int stateId, int districtId, int villageId, String impactIncome) {
        this.impactId = impactId;
        this.baselineId = baselineId;
        this.familyHeadId = familyHeadId;
        this.land = land;
        this.noOfPlants = noOfPlants;
        this.yearOfPlanting = yearOfPlanting;
        this.herr = herr;
        this.projectId = projectId;
        this.beneficiaryName = beneficiaryName;
        this.husbandWifeName = husbandWifeName;
        this.stateId = stateId;
        this.districtId = districtId;
        this.villageId = villageId;
        this.impactIncome = impactIncome;
    }
}
