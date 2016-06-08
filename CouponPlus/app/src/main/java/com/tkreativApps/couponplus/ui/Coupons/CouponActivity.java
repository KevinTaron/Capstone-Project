package com.tkreativApps.couponplus.ui.coupons;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.databinding.ActivityCouponBinding;
import com.tkreativApps.couponplus.handlers.CouponHandler;
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.model.User;
import com.tkreativApps.couponplus.ui.BaseActivity;
import com.tkreativApps.couponplus.utils.Constants;

public class CouponActivity extends BaseActivity {
    public static final String TAG = "CouponActivity";
    public static final String EXTRA_COUPON_KEY = "coupon_key";
    public static final String EXTRA_COUPON_SHARED = "coupon_shared";

    DatabaseReference couponRef;
    private String couponKey = null;
    private ActivityCouponBinding binding;
    private Coupons mCoupon = null;
    private User mUser;
    private String mUserId;
    private boolean isShared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coupon);
        mUser = getUser();
        mUserId = getUid();
        binding.setUser(mUser);
        binding.setHandler(new CouponHandler(this));
        binding.setActivity(this);

        couponRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            isShared = extras.getBoolean(EXTRA_COUPON_SHARED);
            couponKey = extras.getString(EXTRA_COUPON_KEY);
            loadData(couponKey);
        } else {
            mCoupon = new Coupons();
            mCoupon.setOwnerID(mUserId);
            binding.setCoupon(mCoupon);
        }
    }

    private void loadData(String couponKey) {
        DatabaseReference updateRef = getRefUrl().child(couponKey);
        updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCoupon = dataSnapshot.getValue(Coupons.class);
                binding.setCoupon(mCoupon);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getCoupon: Error", databaseError.toException());
            }
        });
    }

    public void deleteCoupon() {
        DatabaseReference updateRef = getRefUrl().child(couponKey);
        updateRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Intent returnIntent = new Intent();
                setResult(Constants.COUPON_DELETED, returnIntent);
                finish();
            }
        });

    }

    private DatabaseReference getRefUrl() {
        DatabaseReference couponLoad = couponRef;
        if(isShared) {
            couponLoad = couponLoad.child(Constants.FIREBASE_LOCATION_COUPONS_PUBLIC);
        } else {
            couponLoad = couponLoad.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(mUserId);
        }

        return couponLoad;
    }
}
