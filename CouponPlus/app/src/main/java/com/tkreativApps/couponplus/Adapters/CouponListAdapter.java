package com.tkreativApps.couponplus.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerViewAdapter;
import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.model.Coupons;


public class CouponListAdapter extends FirebaseRecyclerViewAdapter<Coupons, CouponListAdapter.CouponViewHolder> {


    public CouponListAdapter(Class<Coupons> modelClass, int modelLayout, Class viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(CouponViewHolder viewHolder, Coupons model, int position) {
        super.populateViewHolder(viewHolder, model, position);
        viewHolder.couponName.setText(model.getCompany());
    }

    public static class CouponViewHolder extends RecyclerView.ViewHolder {
        TextView couponName;

        public CouponViewHolder(View v) {
            super(v);
            couponName = (TextView) v.findViewById(R.id.couponName);
        }
    }
}
