package com.tkreativApps.couponplus.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
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
        viewHolder.couponAmount.setText(model.getAmount());

        if(model.getCode() != "") viewHolder.couponCode.setText(model.getCode()); else viewHolder.couponCodeRow.setVisibility(View.GONE);
        if(false) viewHolder.couponDate.setText(""); else viewHolder.couponDateRow.setVisibility(View.GONE);
        if(false) viewHolder.couponInfo.setText(""); else viewHolder.couponInfoRow.setVisibility(View.GONE);
    }

    public static class CouponViewHolder extends RecyclerView.ViewHolder {
        TextView couponName;
        TextView couponAmount;
        TextView couponInfo;
        TextView couponDate;
        TextView couponCode;

        LinearLayout couponDateRow;
        LinearLayout couponCodeRow;
        LinearLayout couponInfoRow;

        public CouponViewHolder(View v) {
            super(v);
            couponName = (TextView) v.findViewById(R.id.couponName);
            couponAmount = (TextView) v.findViewById(R.id.couponAmount);
            couponInfo = (TextView) v.findViewById(R.id.couponInfo);
            couponDate = (TextView) v.findViewById(R.id.couponDate);
            couponCode = (TextView) v.findViewById(R.id.couponCode);

            couponCodeRow = (LinearLayout) v.findViewById(R.id.couponCodeRow);
            couponDateRow = (LinearLayout) v.findViewById(R.id.couponDateRow);
            couponInfoRow = (LinearLayout) v.findViewById(R.id.couponInfoRow);
        }
    }
}
