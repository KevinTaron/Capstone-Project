package com.tkreativApps.couponplus.ui;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tkreativApps.couponplus.data.CouponLoader;
import com.tkreativApps.couponplus.model.User;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.BindView;

public abstract class BaseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
    @BindView(android.R.id.content)
    View mRootView;
    User mUser;

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
        UserMeta();
    }

    public User getUser() {
        if(mUser == null) {
            UserMeta();
            return mUser;
        }
        return mUser;
    }


    public void UserMeta() {
        DatabaseReference userMeta = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL).child(Constants.FIREBASE_LOCATION_USERS).child(getUid());
        userMeta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User metaUser = dataSnapshot.getValue(User.class);
                if(metaUser != null) {
                    setMyUser(metaUser);
                } else {
                    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
                    metaUser = new User(fUser.getUid(), fUser.getDisplayName(), fUser.getEmail());
                    DatabaseReference updateRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constants.FIREBASE_URL).child(Constants.FIREBASE_LOCATION_USERS).child(fUser.getUid());
                    updateRef.setValue(metaUser);
                    setMyUser(metaUser);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setMyUser(User user) {
        mUser = user;
    }


    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CouponLoader(this);
    }
}
