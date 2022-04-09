package com.bahria.fyp.marqeusina.ui.admin.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bahria.fyp.marqeusina.R;
import com.bahria.fyp.marqeusina.handlers.LoginIntentHandler;
import com.bahria.fyp.marqeusina.models.MarqueeModel;
import com.bahria.fyp.marqeusina.temp.FirebaseDataKeys;
import com.bahria.fyp.marqeusina.ui.Loaders.LoadingDialogue;
import com.bahria.fyp.marqeusina.ui.PostRegisterFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class OwnerFragmentProfileRegistrationForm extends Fragment {

    private static final int IMAGE_SEL_REQ = 901;
    MaterialButton registerMarquee;
    TextInputEditText marqueeName;
    TextInputEditText marqueeDetails;
    TextInputEditText marqueeAddress;
    TextInputEditText marqueeNumberOfGuests;
    TextInputEditText marqueePerHeadPrice;
    TextInputEditText marqueeOnlyHallPrice;
    ImageView imageButton;
    String imagePath = "";
    Uri imageReference;
    private LoadingDialogue loadingDialogue;
    private StorageReference storageReference;

    private MarqueeModel model;
    private String uid;
    private String userType;


    public OwnerFragmentProfileRegistrationForm() {
        // Required empty public constructor
    }

    public OwnerFragmentProfileRegistrationForm(String uid, String userType) {
        this.uid = uid;
        this.userType = userType;
    }

    public static OwnerFragmentProfileRegistrationForm newInstance(String uid, String userType) {
        return new OwnerFragmentProfileRegistrationForm(uid, userType);
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
        model = new MarqueeModel();


        registerMarquee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // imagePath has imageText
                if (imagePath.isEmpty()) {
                    Toast.makeText(OwnerFragmentProfileRegistrationForm.this.requireActivity(), "Please Select the 1:1 Image", Toast.LENGTH_SHORT).show();

                } else {

                    storeDataInModel();


                }

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromGallery();
            }
        });


    }

    private void storeDataInModel() {

        if (analyzeInputs(true)) {

            model.setImagePath(imagePath);
            model.setMarqueeAddress(marqueeAddress.getText().toString());
            model.setMarqueeDescription(marqueeDetails.getText().toString());
            model.setMarqueeName(marqueeName.getText().toString());
            model.setNumberOfGuestsCanHandle(marqueeNumberOfGuests.getText().toString());
            model.setOnlyHallPrice(marqueeOnlyHallPrice.getText().toString());
            model.setPerHeadPrice(marqueePerHeadPrice.getText().toString());
            model.setOwnerID(uid);
            model.setDocID("");

            // Set Everything in Model through model's setter methods


            uploadDetails();
        }
    }


    private void uploadDetails() {
        FirebaseFirestore
                .getInstance()
                .collection(FirebaseDataKeys.MARQUEES)
                .add(model)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            model.setDocID(task.getResult().getId());
                            FirebaseFirestore
                                    .getInstance()
                                    .collection(FirebaseDataKeys.MARQUEES)
                                    .document(model.getDocID())
                                    .update(
                                            "docID", model.getDocID()
                                    )
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){

                                                Intent i = new LoginIntentHandler(OwnerFragmentProfileRegistrationForm.this.requireActivity(), userType);
                                                loadingDialogue.dismiss();

                                                startActivity(i);
                                                requireActivity().finish();
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    private void selectImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                IMAGE_SEL_REQ);
    }


    //Initialisation of views
    private void initializeViews(View view) {
        registerMarquee = view.findViewById(R.id.ButtonMarqueeRegisterMarquee);
        marqueeDetails = view.findViewById(R.id.editTextMarqueeDiscription);
        marqueeNumberOfGuests = view.findViewById(R.id.editTextMarqueeNumberOfGuests);
        marqueePerHeadPrice = view.findViewById(R.id.editTextMarqueePerHeadPrice);
        marqueeOnlyHallPrice = view.findViewById(R.id.editTextMarqueeHallBookingPrice);
        imageButton = view.findViewById(R.id.imageButtonMarqueeProfileImage);
        marqueeName = view.findViewById(R.id.editTextMarqueeName);
        marqueeAddress = view.findViewById(R.id.editTextMarqueeAddress);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_SEL_REQ
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            imageReference = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                this.requireActivity().getContentResolver(),
                                imageReference);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);

                //Setting Image Bitmap to my desired ImageView
                imageButton.setImageBitmap(bitmap);

                uploadImage(baos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadImage(byte[] fileInBytes) {
        if (imageReference != null) {

            // Code for showing progressDialog while uploading
            loadingDialogue.show("Uploading", "Image");

            // Defining the child of storageReference
            imagePath = "f/images/marquee/" + UUID.randomUUID().toString();

            StorageReference ref = storageReference.child(imagePath);

            ref.putBytes(fileInBytes)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                loadingDialogue.dismiss();
                                Toast.makeText(
                                        OwnerFragmentProfileRegistrationForm.this.requireActivity(),
                                        "Image Uploaded!!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            })

                    .addOnFailureListener(e -> {
                        loadingDialogue.dismiss();
                        Log.e(e.getCause() + "\n", e.getMessage());
                        Toast.makeText(OwnerFragmentProfileRegistrationForm.this.requireActivity(),
                                "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    })
                    .addOnProgressListener(
                            taskSnapshot -> {
                                double progress
                                        = (100.0
                                        * taskSnapshot.getBytesTransferred()
                                        / taskSnapshot.getTotalByteCount());
                                loadingDialogue.show(
                                        "Uploaded ", ""
                                                + (int) progress + "%");
                            });
        }
    }


    private boolean analyzeInputs(boolean b) {
        //TODO The purpose of this method is to validate Text Inputs
        return b;
    }
}