package com.tkreativApps.couponplus.ui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.ui.coupons.CouponActivity;

public class DeleteDialog extends DialogFragment {

    public static DeleteDialog newInstance(int title) {
        DeleteDialog frag = new DeleteDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getArguments().getInt("title"))
                .setPositiveButton(R.string.coupon_delete_verify_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CouponActivity myAct = (CouponActivity) getActivity();
                        myAct.deleteCoupon();
                    }
                })
                .setNegativeButton(R.string.coupon_delete_verify_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }
}
