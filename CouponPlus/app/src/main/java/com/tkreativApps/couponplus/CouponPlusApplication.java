package com.tkreativApps.couponplus;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Coupon+ Application
 * Author: Kevin Taron
 * Version: 0.1
 */
public class CouponPlusApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
