package com.tkreativApps.couponplus.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tkreativApps.couponplus.model.User;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.BindView;

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(android.R.id.content)
    View mRootView;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showSnackbar(@StringRes int errorMessageRes) {
        Snackbar.make(mRootView, errorMessageRes, Snackbar.LENGTH_LONG)
                .show();
    }

    public void setUser() {
        User mUser = getUser();
        String mUserId = getUid();

        DatabaseReference updateRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL).child(Constants.FIREBASE_LOCATION_USERS).child(mUserId);
        updateRef.setValue(mUser);
    }
    public User getUser() {
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        User mUser = new User(fUser.getUid(), fUser.getDisplayName(), fUser.getEmail());
        return mUser;
    }
    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
