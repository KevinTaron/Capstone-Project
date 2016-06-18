package com.tkreativApps.couponplus.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;

/**
 * Defines the data structure for User objects.
 */

@IgnoreExtraProperties
public class User {
    private String uid;
    private String name;
    private String email;
    private String orderBy = "latest";
    private String sortBy = "ASC";
    private HashMap<String, Object> timestampJoined;

    /**
     * Required public constructor
     */
    public User() {
    }

    /**
     * Use this constructor to create new User.
     * Takes user name, email and timestampJoined as params
     *
     * @param name
     * @param email
     * @param timestampJoined
     */

    public User(String uid, String name, String email, HashMap<String, Object> timestampJoined) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.timestampJoined = timestampJoined;
    }

    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTimestampJoined(HashMap<String, Object> timestampJoined) {
        this.timestampJoined = timestampJoined;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
