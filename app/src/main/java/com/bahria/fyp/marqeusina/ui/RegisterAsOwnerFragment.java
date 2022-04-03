package com.bahria.fyp.marqeusina.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bahria.fyp.marqeusina.R;

public class RegisterAsOwnerFragment extends Fragment {

    //Declare All your Views here
    //MaterialButton registerButton;

    public RegisterAsOwnerFragment() {
    }

    public static RegisterAsOwnerFragment newInstance(String param1, String param2) {

        return new RegisterAsOwnerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_as_owner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize them here one by one as registerButton=view.findViewById(R.id...)


    }
}