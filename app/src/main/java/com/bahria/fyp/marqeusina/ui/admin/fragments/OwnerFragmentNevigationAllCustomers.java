package com.bahria.fyp.marqeusina.ui.admin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bahria.fyp.marqeusina.R;

public class OwnerFragmentNevigationAllCustomers extends Fragment {

    public OwnerFragmentNevigationAllCustomers() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_nevigation_all_customers, container, false);
    }
}