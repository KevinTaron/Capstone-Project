package com.tkreativApps.couponplus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.SignInActivity;
import com.tkreativApps.couponplus.ui.coupons.CouponActivity;
import com.tkreativApps.couponplus.ui.fragments.CouponPrivate;
import com.tkreativApps.couponplus.ui.fragments.CouponPublic;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity  {
    static final String TAG = "MainActivity";
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private FragmentStatePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new CouponPrivate(),
                    new CouponPublic(),
            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.section_title_coupons_private),
                    getString(R.string.section_title_coupons_public)
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * Create an new Coupon
     */
    @OnClick(R.id.fab)
    public void addCoupon(View view) {
        Intent addCoupon = new Intent(MainActivity.this, CouponActivity.class);
        startActivityForResult(addCoupon, Constants.CREATE_COUPON);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }
        if (id == R.id.action_sign_out) {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                public void onComplete(@NonNull Task<Void> task) {
                    // user is now signed out
                    Intent mainAct = new Intent(MainActivity.this, SignInActivity.class);
                    mainAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainAct);
                    finish();
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Constants.COUPON_CREATED) { showSnackbar(R.string.coupon_created_successful); }
        if(resultCode == Constants.COUPON_DELETED) { showSnackbar(R.string.coupon_deleted_successful); }
    }
}
