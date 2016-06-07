package com.tkreativApps.couponplus.ui.coupons;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

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
import com.tkreativApps.couponplus.ui.dialogs.DeleteDialog;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends BaseActivity {
    public static final String TAG = "CouponActivity";
    public static final String EXTRA_COUPON_KEY = "coupon_key";

    @BindView(R.id.companyName)
    TextView mCompanyName;

    @BindView(R.id.amount)
    TextView mAmount;

    @BindView(R.id.code)
    TextView mCode;

    @BindView(R.id.switchPublic)
    Switch mPublic;

    boolean nCoupon = true;
    boolean newCoupon = false;
    DatabaseReference couponRef;
    private String couponKey = null;
    private ActivityCouponBinding binding;
    private Coupons mCoupon = null;
    private User mUser;
    private String mUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coupon);
        ButterKnife.bind(this);
        mUser = getUser();
        mUserId = getUid();
        binding.setUser(mUser);
        binding.setHandler(new CouponHandler());
        binding.setActivity(this);

        couponRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            nCoupon = false;
            couponKey = extras.getString(EXTRA_COUPON_KEY);
            loadData(couponKey);
        } else {
            mCoupon = new Coupons();
            mCoupon.setOwnerID(mUserId);
            newCoupon = true;
            binding.setCoupon(mCoupon);
        }
    }

    private void loadData(String couponKey) {
        couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(mUserId).child(couponKey).addListenerForSingleValueEvent(new ValueEventListener() {
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
        DatabaseReference updateRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(mUserId).child(couponKey);
        updateRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Intent returnIntent = new Intent();
                setResult(Constants.COUPON_DELETED, returnIntent);
                finish();
            }
        });

    }

    @OnClick(R.id.deleteButton)
    public void sureDelete() {
        FragmentManager fm = getSupportFragmentManager();
        DeleteDialog deleteDialog = DeleteDialog.newInstance(R.string.coupon_delete_verify);
        deleteDialog.show(fm, "dialog");
    }
}
