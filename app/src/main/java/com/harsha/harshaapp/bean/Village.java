package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class Village {

    int villageId;
    String villageName;
    String villageCode;
    int blockId;

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public Village() {
    }

    public Village(int villageId, String villageName, String villageCode, int blockId) {
        this.villageId = villageId;
        this.villageName = villageName;
        this.villageCode = villageCode;
        this.blockId = blockId;
    }
}
