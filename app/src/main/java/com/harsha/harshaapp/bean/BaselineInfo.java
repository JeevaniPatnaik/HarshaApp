package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 14-12-2016.
 */

public class BaselineInfo {

    private int baselineId;
    private int familyHeadId;
    private int stateId;
    private int districtId;
    private int blockId;
    private int villageId;
    private int surveyUserId;
    private int socialCategoryId;
    private int religionId;
    private int occupationId;
    private int contactNo;
    private int familyMemberNumber;
    private String income;

    public int getBaselineId() {
        return baselineId;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
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

    public int getFamilyMemberNumber() {
        return familyMemberNumber;
    }

    public void setFamilyMemberNumber(int familyMemberNumber) {
        this.familyMemberNumber = familyMemberNumber;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
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

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public int getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(int surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public int getSocialCategoryId() {
        return socialCategoryId;
    }

    public void setSocialCategoryId(int socialCategoryId) {
        this.socialCategoryId = socialCategoryId;
    }

    public int getReligionId() {
        return religionId;
    }

    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }

    public int getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }
}
