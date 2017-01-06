package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class State {

    int stateId;
    String stateCode;
    String stateName;

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public State() {
    }

    public State(int stateId, String stateCode, String stateName) {
        this.stateId = stateId;
        this.stateCode = stateCode;
        this.stateName = stateName;
    }
    @Override
    public String toString() {
        return stateName;
    }
}
