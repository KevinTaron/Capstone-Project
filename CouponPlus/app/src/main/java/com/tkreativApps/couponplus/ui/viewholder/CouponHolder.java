package com.tkreativApps.couponplus.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tkreativApps.couponplus.R;

/**
 * Created by Kevin on 29.05.2016.
 */

public class CouponHolder extends RecyclerView.ViewHolder {
    public TextView couponName;
    public TextView couponAmount;
    public TextView couponInfo;
    public TextView couponDate;
    public TextView couponCode;

    public LinearLayout couponDateRow;
    public LinearLayout couponCodeRow;
    public LinearLayout couponInfoRow;

    public CouponHolder(View v) {
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

    

    public void setCouponName(String couponName) {
        TextView field = (TextView) itemView.findViewById(R.id.couponName);
        field.setText(couponName);
    }
}
