package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 14-12-2016.
 */

public class Relationship {

    private int relationshipId;
    private String relationshipCode;
    private String getRelationshipName;

    public int getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(int relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getRelationshipCode() {
        return relationshipCode;
    }

    public void setRelationshipCode(String relationshipCode) {
        this.relationshipCode = relationshipCode;
    }

    public String getGetRelationshipName() {
        return getRelationshipName;
    }

    public void setGetRelationshipName(String getRelationshipName) {
        this.getRelationshipName = getRelationshipName;
    }
}
