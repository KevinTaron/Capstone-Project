package com.tkreativApps.couponplus.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.tkreativApps.couponplus.R;
import com.tkreativApps.couponplus.adapter.CouponAdapter;
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.model.User;
import com.tkreativApps.couponplus.ui.viewholder.CouponHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class CouponFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Coupons, CouponHolder> mAdapter;
    private LinearLayoutManager mManager;
    private User mUser;


    @BindView(R.id.couponList)
    RecyclerView mRecyclerView;

    public CouponFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_coupon_list, container, false);
        ButterKnife.bind(this, rootView);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery((mDatabase)).orderByChild("company");
        mAdapter = new CouponAdapter(Coupons.class, R.layout.single_coupon, CouponHolder.class, postsQuery, getActivity());
        mManager = new LinearLayoutManager(getActivity());
        mManager.setStackFromEnd(true);
        mManager.setReverseLayout(true);
        getSortBy();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() { return FirebaseAuth.getInstance().getCurrentUser().getUid(); }

    public void getSortBy() {

//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        if(pref.getBoolean("ASC", false)) {
//            Log.i("SORT", "DESC");
//        } else {
//            Log.i("SORT", "ASC");
//        }
    }

    public abstract Query getQuery(DatabaseReference databaseReference);


}
