package com.bahria.fyp.marqeusina.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.bahria.fyp.marqeusina.R;
import com.bahria.fyp.marqeusina.handlers.LoginIntentHandler;
import com.bahria.fyp.marqeusina.models.Users.UserModel;
import com.bahria.fyp.marqeusina.temp.FirebaseDataKeys;
import com.bahria.fyp.marqeusina.temp.UserLive;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Objects.requireNonNull(getSupportActionBar()).hide();

        String uid = FirebaseAuth.getInstance().getUid();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        if (uid == null || uid.isEmpty()) {
            callToActionOnUserDataNotFound();
        } else {
            performSplashOnUserdataFound(uid);
        }
    }


    private void performSplashOnUserdataFound(String uid) {

        FirebaseFirestore
                .getInstance()
                .collection(new FirebaseDataKeys().USERS)
                .document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            UserLive.currentLoggedInUser = task.getResult().toObject(UserModel.class);
                            startActivity(new LoginIntentHandler(SplashActivity.this, UserLive.currentLoggedInUser.getUserType()));
                            finish();
                        }
                    }
                });


    }

    private void callToActionOnUserDataNotFound() {
        startActivity(new Intent(SplashActivity.this, LoginNavigatorActivity.class));
        finish();
    }
}