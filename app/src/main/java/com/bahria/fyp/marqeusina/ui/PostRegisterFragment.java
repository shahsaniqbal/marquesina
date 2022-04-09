package com.bahria.fyp.marqeusina.ui;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bahria.fyp.marqeusina.R;
import com.bahria.fyp.marqeusina.handlers.LoginIntentHandler;
import com.bahria.fyp.marqeusina.models.Users.UserModel;
import com.bahria.fyp.marqeusina.models.Users.UserOtherDetailsModel;
import com.bahria.fyp.marqeusina.temp.FirebaseDataKeys;
import com.bahria.fyp.marqeusina.temp.UserLive;
import com.bahria.fyp.marqeusina.temp.UserTypes;
import com.bahria.fyp.marqeusina.ui.Loaders.LoadingDialogue;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class PostRegisterFragment extends Fragment implements View.OnClickListener {

    private static final int IMAGE_SEL_REQ = 656;
    String imagePath = "";
    Uri imageReference;
    String selectedUserType = UserTypes.Customer;
    private UserModel model;
    //Views
    private ImageView userDP;
    private MaterialTextView edxEmail;
    private MaterialTextView edxName;
    private MaterialTextView edxMobile;
    private MaterialButton btnSignUp;
    private LoadingDialogue loadingDialogue;
    private String password;
    private StorageReference storageReference;
    private RadioGroup radioGroup;
    private RadioButton rdbFinder, rdbOwner;

    public PostRegisterFragment() {
    }

    public PostRegisterFragment(UserModel model, String password) {
        this.model = model;
        this.password = password;
    }

    public static PostRegisterFragment newInstance(UserModel model, String password) {
        return new PostRegisterFragment(model, password);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.generic_fragment_post_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializers(view);

        edxName.setText(model.getFullName());
        edxEmail.setText(model.getEmailAddress());
        edxMobile.setText(model.getMobileNumber());


        userDP.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(
                            intent,
                            "Select Image from here..."),
                    IMAGE_SEL_REQ);
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == rdbFinder.getId()) {
                    selectedUserType = UserTypes.Customer;
                } else if (i == rdbOwner.getId()) {
                    selectedUserType = UserTypes.Admin;
                }
            }
        });

        btnSignUp.setOnClickListener(view12 -> {
            if (analyzeInputsIfEmpty(true)) {
                loadingDialogue.show("Please Wait", "Saving User Details");
                model.setUserType(selectedUserType);
                FirebaseAuth
                        .getInstance()
                        .createUserWithEmailAndPassword(model.getEmailAddress(), password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                model.setUID(Objects.requireNonNull(task.getResult().getUser()).getUid());
                                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                                firebaseFirestore
                                        .collection(new FirebaseDataKeys().getUsersRef())
                                        .document(model.getUID())
                                        .set(model)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(requireActivity(), "User Registration Successful", Toast.LENGTH_SHORT).show();
                                                UserLive.currentLoggedInUser = model;

                                                Intent i = new LoginIntentHandler(PostRegisterFragment.this.requireActivity(), model.getUserType());

                                                loadingDialogue.dismiss();

                                                startActivity(i);
                                                requireActivity().finish();

                                            } else {
                                                Toast.makeText(requireActivity(), "" + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                loadingDialogue.dismiss();
                                            }
                                        });

                            } else {
                                Toast.makeText(requireActivity(), "Error Signing UP \n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                loadingDialogue.dismiss();

                            }
                        });

            } else {
                Toast.makeText(PostRegisterFragment.this.requireActivity(), "There's some problem with the inputs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openRegisterFragmentAgain() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }

    private boolean analyzeInputsIfEmpty(Boolean shouldAnalyze) {
        if (shouldAnalyze) {
            if (imagePath.isEmpty()) {
                Toast.makeText(this.requireActivity(), "Image is necessary. Please Select your 1:1 Image", Toast.LENGTH_SHORT).show();
                shouldAnalyze = false;
            }
        }
        return shouldAnalyze;
    }

    private void initializers(View v) {
        userDP = v.findViewById(R.id.card_add_users_dp_imgv);

        edxEmail = v.findViewById(R.id.edx_add_users_email);
        edxName = v.findViewById(R.id.edx_add_users_full_name);
        edxMobile = v.findViewById(R.id.edx_add_users_mobile);
        btnSignUp = v.findViewById(R.id.addUser_as_customer);
        loadingDialogue = new LoadingDialogue(PostRegisterFragment.this.requireActivity());
        radioGroup = v.findViewById(R.id.rdg);
        rdbFinder = v.findViewById(R.id.rdbFinder);
        rdbOwner = v.findViewById(R.id.rdbOwner);

        FirebaseStorage storage = FirebaseStorage.getInstance(FirebaseDataKeys.STORAGE_BUCKET_ADDRESS);
        storageReference = storage.getReference();

    }

    @Override
    public void onClick(View view) {
        if (analyzeInputsIfEmpty(true)) {

            model.setDetails(
                    new UserOtherDetailsModel(
                            imagePath
                    ));

            //Update User Object
            loadingDialogue.show("Please Wait", "Saving User Details");
            FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(model.getEmailAddress(), password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            model.setUID(Objects.requireNonNull(task.getResult().getUser()).getUid());
                            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                            firebaseFirestore
                                    .collection(new FirebaseDataKeys().getUsersRef())
                                    .document(model.getUID())
                                    .set(model)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(requireActivity(), "User Registration Successful", Toast.LENGTH_SHORT).show();
                                            UserLive.currentLoggedInUser = model;

                                            Intent i = new LoginIntentHandler(PostRegisterFragment.this.requireActivity(), model.getUserType());

                                            loadingDialogue.dismiss();

                                            startActivity(i);
                                            requireActivity().finish();

                                        } else {
                                            Toast.makeText(requireActivity(), "" + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            loadingDialogue.dismiss();
                                        }
                                    });

                        } else {
                            Toast.makeText(requireActivity(), "Error Signing UP \n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            loadingDialogue.dismiss();

                        }
                    });

        }
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
                userDP.setImageBitmap(bitmap);

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
            imagePath = "f/images/users/" + UUID.randomUUID().toString();

            StorageReference ref = storageReference.child(imagePath);

            ref.putBytes(fileInBytes)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                loadingDialogue.dismiss();
                                Toast.makeText(
                                        PostRegisterFragment.this.requireActivity(),
                                        "Image Uploaded!!",
                                        Toast.LENGTH_SHORT
                                ).show();
                            })

                    .addOnFailureListener(e -> {
                        loadingDialogue.dismiss();
                        Log.e(e.getCause() + "\n", e.getMessage());
                        Toast.makeText(PostRegisterFragment.this.requireActivity(),
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


}
