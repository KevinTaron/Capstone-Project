package com.tkreativApps.couponplus.ui.fragments;

import android.content.Intent;
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
import com.tkreativApps.couponplus.model.Coupons;
import com.tkreativApps.couponplus.ui.coupons.CouponActivity;
import com.tkreativApps.couponplus.ui.viewholder.CouponHolder;
import com.tkreativApps.couponplus.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class CouponFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Coupons, CouponHolder> mAdapter;
    private LinearLayoutManager mManager;


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
        Query postsQuery = getQuery((mDatabase));
        mAdapter = new FirebaseRecyclerAdapter<Coupons, CouponHolder>(Coupons.class, R.layout.single_coupon,
                CouponHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final CouponHolder viewHolder, final Coupons model, final int position) {
                final DatabaseReference postRef = getRef(position);

                viewHolder.couponName.setText(model.getCompany());
                viewHolder.couponAmount.setText(model.getAmount());

                if(model.getCode() != "") viewHolder.couponCode.setText(model.getCode()); else viewHolder.couponCodeRow.setVisibility(View.GONE);
                if(false) viewHolder.couponDate.setText(""); else viewHolder.couponDateRow.setVisibility(View.GONE);
                if(false) viewHolder.couponInfo.setText(""); else viewHolder.couponInfoRow.setVisibility(View.GONE);

                // Set click listener for the whole post view
                final String couponKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CouponActivity.class);
                        intent.putExtra(CouponActivity.EXTRA_COUPON_KEY, couponKey);
                        startActivityForResult(intent, Constants.EDIT_COUPON);
                    }
                });



            }
        };

        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
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

    public abstract Query getQuery(DatabaseReference databaseReference);
}