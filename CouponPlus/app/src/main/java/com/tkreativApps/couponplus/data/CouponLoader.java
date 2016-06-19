package com.tkreativApps.couponplus.data;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

/**
 * Created by Kevin on 19.06.2016.
 */

public class CouponLoader extends CursorLoader {

    public CouponLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return super.loadInBackground();
    }
}
