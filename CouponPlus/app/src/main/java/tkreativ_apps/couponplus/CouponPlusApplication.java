package tkreativ_apps.couponplus;

import com.firebase.client.Firebase;

/**
 * Coupon+ Application
 * Author: Kevin Taron
 * Version: 0.1
 */
public class CouponPlusApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /* Initialize Firebase */
        Firebase.setAndroidContext(this);
        /* Enable disk persistence  */
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
