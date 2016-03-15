package com.tkreativApps.couponplus.model;

import java.util.HashMap;

public class Coupons {
    private String cid;
    private String company;
    private String amount;
    private String code;
    private boolean oPublic;

    private HashMap<String, Object> timeStampCreated;
    private HashMap<String, Object> timeStampLastEdit;

    public Coupons() {
    }

    public Coupons(String cid, String company, String amount, String code, boolean oPublic) {
        this.cid = cid;
        this.company = company;
        this.amount = amount;
        this.code = code;
        this.oPublic = oPublic;
    }

    public Coupons(String cid, String company, String amount, String code, boolean oPublic, HashMap<String, Object> timeStampCreated) {
        this.cid = cid;
        this.company = company;
        this.amount = amount;
        this.code = code;
        this.oPublic = oPublic;
        this.timeStampCreated = timeStampCreated;
        this.timeStampLastEdit = timeStampCreated;
    }

    public String getCid() {
        return cid;
    }

    public String getCompany() {
        return company;
    }

    public String getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }

    public boolean isoPublic() {
        return oPublic;
    }

    public HashMap<String, Object> getTimeStampCreated() {
        return timeStampCreated;
    }

    public HashMap<String, Object> getTimeStampLastEdit() {
        return timeStampLastEdit;
    }
}
