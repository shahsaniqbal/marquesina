package com.bahria.fyp.marqeusina.ui.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bahria.fyp.marqeusina.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

public class OwnerFragmentProfileRegistrationForm extends Fragment {

    MaterialButton registerMarquee;
    TextInputEditText marqueeName,marqueeDetails,marqueeAddress,marqueeNumberOfGuests,marqueePerHeadPrice,marqueeOnlyHallPrice;
    ImageButton imageButton;


    public OwnerFragmentProfileRegistrationForm() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner_profile_registration_form, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);


        registerMarquee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });




    }

    private void selectImageFromGallery() {

    }


    //Initialisation of views
    private void initializeViews(View view) {
        registerMarquee=view.findViewById(R.id.ButtonMarqueeRegisterMarquee);
        marqueeDetails=view.findViewById(R.id.editTextMarqueeDiscription);
        marqueeNumberOfGuests=view.findViewById(R.id.editTextMarqueeNumberOfGuests);
        marqueePerHeadPrice=view.findViewById(R.id.editTextMarqueePerHeadPrice);
        marqueeOnlyHallPrice=view.findViewById(R.id.editTextMarqueeHallBookingPrice);
        imageButton=view.findViewById(R.id.imageButtonMarqueeProfileImage);
        marqueeName=view.findViewById(R.id.editTextMarqueeName);
        marqueeAddress=view.findViewById(R.id.editTextMarqueeAddress);

    }
}