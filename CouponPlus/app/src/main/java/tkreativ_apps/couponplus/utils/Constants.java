package tkreativ_apps.couponplus.utils;

/**
 * Constants class store most important strings and paths of the app
 */
public final class Constants {

    /**
     * Constants related to locations in Firebase, such as the name of the node
     * where user lists are stored (ie "userLists")
     */
    public static final String FIREBASE_LOCATION_USERS = "users";

    /**
     * Constants for Firebase URL
     */
    public static final String FIREBASE_URL = "https://couponplus.firebaseio.com/";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;


    /**
     * Constants for bundles, extras and shared preferences keys
     */
    public static final String KEY_PROVIDER = "PROVIDER";
    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";

    /**
     * Constants for Firebase login
     */
    public static final String GOOGLE_PROVIDER = "google";
}
