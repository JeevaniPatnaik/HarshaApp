package com.harsha.harshaapp.bean;

/**
 * Created by Jeevani on 12/14/2016.
 */
public class MemberInfo {
    private int memberId;
    private int uniqueId;
    private String memberName;
    private String dob;
    private String gender;
    private String socialCategory;
    private int aadhaarCardId;
    private int voterId;
    private String familyHead;
    private String personalSalary;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSocialCategory() {
        return socialCategory;
    }

    public void setSocialCategory(String socialCategory) {
        this.socialCategory = socialCategory;
    }

    public int getAadhaarCardId() {
        return aadhaarCardId;
    }

    public void setAadhaarCardId(int aadhaarCardId) {
        this.aadhaarCardId = aadhaarCardId;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public String getFamilyHead() {
        return familyHead;
    }

    public void setFamilyHead(String familyHead) {
        this.familyHead = familyHead;
    }

    public String getPersonalSalary() {
        return personalSalary;
    }

    public void setPersonalSalary(String personalSalary) {
        this.personalSalary = personalSalary;
    }
}
