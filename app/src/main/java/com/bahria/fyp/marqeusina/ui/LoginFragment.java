package com.bahria.fyp.marqeusina.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bahria.fyp.marqeusina.R;
import com.bahria.fyp.marqeusina.handlers.LoginIntentHandler;
import com.bahria.fyp.marqeusina.models.Users.UserModel;
import com.bahria.fyp.marqeusina.temp.FirebaseDataKeys;
import com.bahria.fyp.marqeusina.temp.UserLive;
import com.bahria.fyp.marqeusina.ui.Loaders.LoadingDialogue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    MaterialCardView openSignUpFragment;
    EditText edxEmail;
    EditText edxPassword;
    MaterialButton btnSignin;
    LoadingDialogue loadingDialogue;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeViews(view);


        openSignUpFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_manager_at_login, RegisterFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (analyzeInputsIfEmpty(true)) {
                    loadingDialogue.show("Loading", "Signing you in");
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                            edxEmail.getText().toString(),
                            edxPassword.getText().toString()
                    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseFirestore
                                        .getInstance()
                                        .collection(new FirebaseDataKeys().USERS)
                                        .document(task.getResult().getUser().getUid())
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    UserLive.currentLoggedInUser = (UserModel) task.getResult().toObject(UserModel.class);
                                                    //TODO 0002 Remove Toast
                                                    //Toast.makeText(requireActivity(), "" + UserLive.currentLoggedInUser, Toast.LENGTH_SHORT).show();
                                                    loadingDialogue.dismiss();
                                                    navigateToMainScreen(UserLive.currentLoggedInUser.getUserType());
                                                }
                                            }
                                        });
                            } else{
                                Toast.makeText(requireActivity(), "Error Solving Task \n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                loadingDialogue.dismiss();
                            }
                        }
                    });
                } else
                    Toast.makeText(requireActivity(), "Error analyzing Inputs", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initializeViews(View view) {
        edxEmail = view.findViewById(R.id.edx_login_email);
        edxPassword = view.findViewById(R.id.edx_login_pass);
        openSignUpFragment = view.findViewById(R.id.btn_login_signUp);
        btnSignin = view.findViewById(R.id.btn_login_login);

        loadingDialogue = new LoadingDialogue(requireActivity());

    }

    private void navigateToMainScreen(String userType) {
        // DONE TO//DO 0003 Navigate According to Conditions
        // TODO 0004 Register & Login Fragments Connectivity
        // Code By AHSAN 08-02-2022 15:40:00 TUESDAY

        Intent i = new LoginIntentHandler(requireActivity(), userType);

        startActivity(i);
        requireActivity().finish();
    }

    private boolean analyzeInputsIfEmpty(Boolean shouldAnalyze) {
        if (shouldAnalyze) {
            if (edxEmail.getText().toString().isEmpty()) {
                edxEmail.setError("Email Field is mandatory");
                shouldAnalyze = false;
            }
            if (edxPassword.getText().toString().isEmpty()) {
                edxPassword.setError("Password can't be Empty");
                shouldAnalyze = false;
            }
        }
        return shouldAnalyze;
    }
}