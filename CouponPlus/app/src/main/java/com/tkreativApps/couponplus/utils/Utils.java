package com.tkreativApps.couponplus.utils;

/**
 * Utility class
 */
public class Utils {

    /**
     * Encode user email to use it as a Firebase key (Firebase does not allow "." in the key name)
     * Encoded email is also used as "userEmail", list and item "owner" value
     */
    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }


    /**
     * Email is being decoded just once to display real email in AutocompleteFriendAdapter
     *
     */
    public static String decodeEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }

}
