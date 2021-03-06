package com.tkreativApps.couponplus.model;

import java.util.HashMap;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;

@SimpleSQLTable(table = "coupons", provider = "CouponProvider")
public class Coupons {

    @SimpleSQLColumn(value = "col_cid", primary = true)
    public String cid;

    @SimpleSQLColumn("col_column")
    public String company;

    @SimpleSQLColumn("col_amount")
    public String amount;

    @SimpleSQLColumn("col_code")
    public String code;

    @SimpleSQLColumn("col_shared")
    public boolean shared;

    @SimpleSQLColumn("col_ownerid")
    public String ownerID;

    private HashMap<String, Object> timeStampCreated;

    private HashMap<String, Object> timeStampLastEdit;

    public Coupons() {
    }

    public Coupons(String cid, String company, String amount, String code, String ownerID) {
        this.cid = cid;
        this.company = company;
        this.amount = amount;
        this.code = code;
        this.ownerID = ownerID;
    }

    public Coupons(String cid, String company, String amount, String code, String ownerID, HashMap<String, Object> timeStampCreated) {
        this.cid = cid;
        this.company = company;
        this.amount = amount;
        this.code = code;
        this.ownerID = ownerID;
        this.timeStampCreated = timeStampCreated;
        this.timeStampLastEdit = timeStampCreated;
    }

    public String getCid() {
        return cid;
    }

    public String getOwnerID() { return ownerID; }

    public String getCompany() {
        return company;
    }

    public String getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }

    public HashMap<String, Object> getTimeStampCreated() {
        return timeStampCreated;
    }

    public HashMap<String, Object> getTimeStampLastEdit() {
        return timeStampLastEdit;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void setTimeStampCreated(HashMap<String, Object> timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }

    public void setTimeStampLastEdit(HashMap<String, Object> timeStampLastEdit) {
        this.timeStampLastEdit = timeStampLastEdit;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }
}
