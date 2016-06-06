package com.tkreativApps.couponplus.ui.coupons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.ui.dialogs.DeleteDialog;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends AppCompatActivity {
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

    @BindView(R.id.deleteButton)
    Button mDelete;

    boolean nCoupon = true;
    DatabaseReference couponRef;
    private String userId;
    private String couponKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);

        userId = getUser();
        couponRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            nCoupon = false;
            couponKey = extras.getString(EXTRA_COUPON_KEY);
            loadData(couponKey);
            mDelete.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.saveButton)
    public void onSave() {
        String companyName = mCompanyName.getText().toString();
        String amount = mAmount.getText().toString();
        String code = mCode.getText().toString();
        boolean isPublic = mPublic.isChecked();

        if(userId == "") { return; }

        if(nCoupon) {
            DatabaseReference newCopRef = null;
            if(isPublic) {
                newCopRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PUBLIC).push();
            } else {
                newCopRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(userId).push();
            }

            Coupons newCoupons = new Coupons(newCopRef.getKey(), companyName, amount, code, isPublic);
            newCopRef.setValue(newCoupons, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                    Intent returnIntent = new Intent();
                    setResult(Constants.COUPON_CREATED, returnIntent);
                    finish();
                }
            });
        } else {
            if(couponKey != null) {
                Coupons updateCoupon = new Coupons(couponKey, companyName, amount, code, isPublic);
                DatabaseReference updateRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(userId).child(couponKey);
                updateRef.setValue(updateCoupon);
                finish();
            }
        }
    }

    private void loadData(String couponKey) {

        couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(userId).child(couponKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Coupons editCoupon = dataSnapshot.getValue(Coupons.class);
                mCompanyName.setText(editCoupon.getCompany());
                mAmount.setText(editCoupon.getAmount());
                mCode.setText(editCoupon.getCode());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "getCoupon: Error", databaseError.toException());
            }
        });


    }

    private String getUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user.getUid();
    }

    public void deleteCoupon() {
        DatabaseReference updateRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(userId).child(couponKey);
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
