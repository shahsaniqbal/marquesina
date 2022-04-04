package com.bahria.fyp.marqeusina.ui.admin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bahria.fyp.marqeusina.R;
import com.bahria.fyp.marqeusina.ui.admin.fragments.OwnerFragmentNavigationNotificationa;
import com.bahria.fyp.marqeusina.ui.admin.fragments.OwnerFragmentNavigationProfile;
import com.bahria.fyp.marqeusina.ui.admin.fragments.OwnerFragmentNevigationAllCustomers;
import com.bahria.fyp.marqeusina.ui.admin.fragments.OwnerFragmentNevigationPackeges;
import com.bahria.fyp.marqeusina.ui.admin.fragments.OwnerFragmentsNavigationBookings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityOwner extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_owner);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FrameLayout fl = findViewById(R.id.flFragment);
                fl.removeAllViews();

                switch (item.getItemId()) {
                    case R.id.owner_nevigation_profile:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.flFragment, OwnerFragmentNavigationProfile.class, null)
                                .commit();
                        return true;

                    case R.id.owner_nevigation_packeges:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.flFragment, OwnerFragmentNevigationPackeges.class, null)
                                .commit();
                        return true;
                    case R.id.owner_nevigation_all_customers:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.flFragment, OwnerFragmentNevigationAllCustomers.class, null)
                                .commit();
                        return true;
                    case R.id.owner_nevigation_bookings:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.flFragment, OwnerFragmentsNavigationBookings.class, null)
                                .commit();
                        return true;
                    case R.id.owner_nevigation_notification:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.flFragment, OwnerFragmentNavigationNotificationa.class, null)
                                .commit();
                        return true;

                }


                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.owner_nevigation_profile);
    }
}