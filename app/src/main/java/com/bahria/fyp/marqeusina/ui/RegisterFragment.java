package com.bahria.fyp.marqeusina.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bahria.fyp.marqeusina.R;
import com.bahria.fyp.marqeusina.ui.Loaders.RegisterAsFinderFragment;

public class RegisterFragment extends Fragment {
    //Declare 2 MaterialButtons here
    Button reginsterAsOwner,registerAsFinder;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }









    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Do here Sohaib
        //Initialize here
        reginsterAsOwner=view.findViewById(R.id.register_as_owner);
        registerAsFinder=view.findViewById(R.id.register_as_finder);



        //registerAsOwner Sider
        reginsterAsOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.register_fragment,new LoginFragment()).commit();
            }
        });



        //registerAsFinder side
        registerAsFinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.register_fragment,new RegisterAsFinderFragment()).commit();

            }
        });




    }





}