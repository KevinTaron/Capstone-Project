package com.tkreativApps.couponplus;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tkreativApps.couponplus.ui.BaseActivity;
import com.tkreativApps.couponplus.ui.MainActivity;
import com.tkreativApps.couponplus.ui.coupons.CouponActivity;
import com.tkreativApps.couponplus.utils.Constants;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class SignInActivity extends BaseActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startMain();
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(
                                    AuthUI.GOOGLE_PROVIDER,
                                    AuthUI.FACEBOOK_PROVIDER
                            )
                            .setTheme(R.style.AppTheme)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null) {
                    startMain();
                }
            } else {
                // user is not signed in. Maybe just wait for the user to press
                // "sign in" again, or show a message
                hideProgressDialog();
                showSnackbar(R.string.login_failed);
            }
        }
    }

    private void startMain() {
        setUser();
        Intent mainAct = new Intent(SignInActivity.this, getStartActivity());
        mainAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainAct);
        this.finish();
    }

    private Class getStartActivity() {
        if(getIntent().hasExtra(Constants.REQUEST_CODE)) {
            int mRequestCode = getIntent().getIntExtra(Constants.REQUEST_CODE, 0);
            if(mRequestCode == Constants.CREATE_COUPON) {
                return CouponActivity.class;
            }
        }
        return MainActivity.class;
    }
}
