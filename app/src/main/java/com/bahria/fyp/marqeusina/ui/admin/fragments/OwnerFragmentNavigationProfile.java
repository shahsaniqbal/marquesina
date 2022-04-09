package com.bahria.fyp.marqeusina.ui.admin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bahria.fyp.marqeusina.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class OwnerFragmentNavigationProfile extends Fragment {
    MaterialTextView marqueeName,marqueeDetails,marqueeAddress,marqueeNumberOfGuests,marqueePerHeadPrice,marqueeOnlyHallPrice;
    ImageView marqueeDP;


    public OwnerFragmentNavigationProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_owner___nevigation__profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialisation(view);

    }


    private void initialisation(View view) {
        marqueeName=view.findViewById(R.id.TexViewProfileMarqueeName);
        marqueeDetails=view.findViewById(R.id.TexViewProfileMarqueeDetails);
        marqueeAddress=view.findViewById(R.id.TexViewProfileMarqueeAddress);
        marqueeNumberOfGuests=view.findViewById(R.id.TexViewProfileMarqueeNumberOfGuests);
        marqueePerHeadPrice=view.findViewById(R.id.TexViewProfileMarqueePerHeadPrice);
        marqueeOnlyHallPrice=view.findViewById(R.id.TexViewProfileMarqueeHallBookingPrice);
        marqueeDP=view.findViewById(R.id.imageButtonMarqueeImageProfile);
    }

}