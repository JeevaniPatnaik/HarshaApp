package com.harsha.harshaapp.bean;

/**
 * Created by GUPTA on 11-Feb-17.
 */

public class BaselineHeadInfo {

    private BaselineInfo baselineInfo;
    private MemberInfo memberInfo;

    public BaselineHeadInfo() {
    }

    public BaselineHeadInfo(BaselineInfo baselineInfo, MemberInfo memberInfo) {
        this.baselineInfo = baselineInfo;
        this.memberInfo = memberInfo;
    }

    public BaselineInfo getBaselineInfo() {
        return baselineInfo;
    }

    public void setBaselineInfo(BaselineInfo baselineInfo) {
        this.baselineInfo = baselineInfo;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }
}
