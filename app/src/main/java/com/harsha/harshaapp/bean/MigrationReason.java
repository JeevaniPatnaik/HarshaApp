package com.harsha.harshaapp.bean;

/**
 * Created by Jeevani on 1/9/2017.
 */

public class MigrationReason {

    private int migrationReasonId;
    private String migrationReasonCode;
    private String migrationReasonName;

    public int getMigrationReasonId() {
        return migrationReasonId;
    }

    public void setMigrationReasonId(int migrationReasonId) {
        this.migrationReasonId = migrationReasonId;
    }

    public String getMigrationReasonCode() {
        return migrationReasonCode;
    }

    public void setMigrationReasonCode(String migrationReasonCode) {
        this.migrationReasonCode = migrationReasonCode;
    }

    public String getMigrationReasonName() {
        return migrationReasonName;
    }

    public void setMigrationReasonName(String migrationReasonName) {
        this.migrationReasonName = migrationReasonName;
    }

    public MigrationReason() {
    }

    public MigrationReason(int migrationReasonId, String migrationReasonName, String migrationReasonCode) {
        this.migrationReasonId = migrationReasonId;
        this.migrationReasonName = migrationReasonName;
        this.migrationReasonCode = migrationReasonCode;
    }

}
