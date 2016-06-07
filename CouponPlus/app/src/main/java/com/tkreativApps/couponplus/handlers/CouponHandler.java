package com.tkreativApps.couponplus.handlers;

import android.content.Intent;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.model.User;
import com.tkreativApps.couponplus.ui.coupons.CouponActivity;
import com.tkreativApps.couponplus.utils.Constants;

/**
 * Created by Kevin on 07.06.2016.
 */

public class CouponHandler {

    public CouponHandler() {}

    public void saveCoupon(Coupons coupon, User user, final CouponActivity activity) {
        DatabaseReference couponRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL);
        if(coupon.isShared()) {
            couponRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PUBLIC);
        } else {
            couponRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(user.getUid());
        }

        if(coupon.getCid() == "" || coupon.getCid() == null) {
            couponRef = couponRef.push();
            coupon.setCid(couponRef.getKey());
        } else {
            couponRef = couponRef.child(coupon.getCid());
        }

        couponRef.setValue(coupon, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Intent returnIntent = new Intent();
                activity.setResult(Constants.COUPON_CREATED, returnIntent);
                activity.finish();
            }
        });

    }
}
