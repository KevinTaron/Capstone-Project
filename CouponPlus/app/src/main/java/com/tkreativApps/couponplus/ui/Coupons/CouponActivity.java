package com.tkreativApps.couponplus.ui.Coupons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends AppCompatActivity {
    @Bind(R.id.companyName) TextView mCompanyName;
    @Bind(R.id.amount) TextView mAmount;
    @Bind(R.id.code) TextView mCode;
    @Bind(R.id.switchPublic) Switch mPublic;

    boolean nCoupon = true;
    Firebase couponRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);

        couponRef = new Firebase(Constants.FIREBASE_URL);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            nCoupon = false;
        }
    }

    @OnClick(R.id.saveButton)
    public void onSave() {
        String companyName = mCompanyName.getText().toString();
        String amount = mAmount.getText().toString();
        String code = mCode.getText().toString();
        boolean isPublic = mPublic.isChecked();

        if(nCoupon) {
            Firebase newCopRef = null;
            if(isPublic) {
                newCopRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PUBLIC).push();
            } else {
                newCopRef = couponRef.child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).push();
            }

            Coupons newCoupons = new Coupons(newCopRef.getKey(), companyName, amount, code, isPublic);
            newCopRef.setValue(newCoupons);
        }

        Toast testToast = Toast.makeText(this, "public: " + isPublic, Toast.LENGTH_LONG);
        testToast.show();

        // finish();
    }
}
