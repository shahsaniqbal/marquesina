package com.bahria.fyp.marqeusina.ui.Loaders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bahria.fyp.marqeusina.R;

public class RegisterAsFinderFragment extends Fragment {


    public RegisterAsFinderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_as_finder, container, false);
    }
}