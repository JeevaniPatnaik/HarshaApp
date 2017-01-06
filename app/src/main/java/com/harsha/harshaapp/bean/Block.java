package com.harsha.harshaapp.bean;

/**
 * Created by IRFAN on 29-12-2016.
 */

public class Block {

    int blockId;
    String blockName;
    String blockCode;
    int districtId;

    public Block() {
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public Block(int blockId, String blockName, String blockCode, int districtId) {
        this.blockId = blockId;
        this.blockName = blockName;
        this.blockCode = blockCode;
        this.districtId = districtId;
    }
}
