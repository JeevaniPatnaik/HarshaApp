package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 14-12-2016.
 */

public class SocialCategory {

    private int socialCategoryId;
    private String socialCategoryCode;
    private String socialCategoryName;

    public int getSocialCategoryId() {
        return socialCategoryId;
    }

    public void setSocialCategoryId(int socialCategoryId) {
        this.socialCategoryId = socialCategoryId;
    }

    public String getSocialCategoryCode() {
        return socialCategoryCode;
    }

    public void setSocialCategoryCode(String socialCategoryCode) {
        this.socialCategoryCode = socialCategoryCode;
    }

    public String getSocialCategoryName() {
        return socialCategoryName;
    }

    public void setSocialCategoryName(String socialCategoryName) {
        this.socialCategoryName = socialCategoryName;
    }

    public SocialCategory(int socialCategoryId, String socialCategoryName, String socialCategoryCode) {
        this.socialCategoryId = socialCategoryId;
        this.socialCategoryName = socialCategoryName;
        this.socialCategoryCode = socialCategoryCode;
    }
}
