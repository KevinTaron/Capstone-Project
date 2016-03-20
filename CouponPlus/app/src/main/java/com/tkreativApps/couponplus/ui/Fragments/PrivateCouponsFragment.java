package com.tkreativApps.couponplus.ui.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;
import com.tkreativApps.couponplus.Adapters.CouponListAdapter;
import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PrivateCouponsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private CouponListAdapter mCouponListAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.couponRecViewPrivate) RecyclerView mRecyclerView;



    public PrivateCouponsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrivateCouponsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrivateCouponsFragment newInstance(String param1, String param2) {
        PrivateCouponsFragment fragment = new PrivateCouponsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_private_coupons, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String uid = sharedPref.getString("uid", "");

        Firebase activeCouponListRef = new Firebase(Constants.FIREBASE_URL).child(Constants.FIREBASE_LOCATION_COUPONS_PRIVATE).child(uid);

        mCouponListAdapter = new CouponListAdapter(Coupons.class, R.layout.single_coupon, CouponListAdapter.CouponViewHolder.class, activeCouponListRef);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mCouponListAdapter);

    }
}
