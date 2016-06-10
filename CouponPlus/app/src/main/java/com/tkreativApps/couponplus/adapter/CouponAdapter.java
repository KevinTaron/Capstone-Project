package com.tkreativApps.couponplus.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.ui.coupons.CouponActivity;
import com.tkreativApps.couponplus.ui.viewholder.CouponHolder;
import com.tkreativApps.couponplus.utils.Constants;

/**
 * Created by Kevin on 10.06.2016.
 */

public class CouponAdapter extends FirebaseRecyclerAdapter<Coupons, CouponHolder> {
    Activity mActivity;

    public CouponAdapter(Class<Coupons> modelClass, int modelLayout, Class<CouponHolder> viewHolderClass, Query ref, Activity activity) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mActivity = activity;
    }

    @Override
    protected void populateViewHolder(CouponHolder viewHolder, Coupons model, int position) {
        final DatabaseReference postRef = getRef(position);

        viewHolder.couponName.setText(model.getCompany());
        viewHolder.couponAmount.setText(model.getAmount());

        if(model.getCode() != "") viewHolder.couponCode.setText(model.getCode()); else viewHolder.couponCodeRow.setVisibility(View.GONE);
        if(false) viewHolder.couponDate.setText(""); else viewHolder.couponDateRow.setVisibility(View.GONE);
        if(false) viewHolder.couponInfo.setText(""); else viewHolder.couponInfoRow.setVisibility(View.GONE);

        // Set click listener for the whole post view
        final String couponKey = postRef.getKey();
        final boolean couponShared = model.isShared();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, CouponActivity.class);
                intent.putExtra(CouponActivity.EXTRA_COUPON_KEY, couponKey);
                intent.putExtra(CouponActivity.EXTRA_COUPON_SHARED, couponShared);
                mActivity.startActivityForResult(intent, Constants.EDIT_COUPON);
            }
        });
    }

    @Override
    public void onBindViewHolder(CouponHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
    }
}
