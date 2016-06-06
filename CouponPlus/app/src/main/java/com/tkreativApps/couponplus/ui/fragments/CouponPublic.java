package com.tkreativApps.couponplus.ui.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.tkreativApps.couponplus.utils.Constants;

/**
 * Created by Kevin on 06.06.2016.
 */

public class CouponPublic extends CouponFragment {
    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child(Constants.FIREBASE_LOCATION_COUPONS_PUBLIC);
    }
}
