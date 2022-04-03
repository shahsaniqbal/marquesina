package com.bahria.fyp.marqeusina.handlers;

import android.content.Context;
import android.content.Intent;

import com.bahria.fyp.marqeusina.temp.UserLive;
import com.bahria.fyp.marqeusina.temp.UserTypes;
import com.bahria.fyp.marqeusina.ui.admin.activities.MainActivityOwner;

/**
 * This Class is being coded by AHSAN IQBAL on 09-02-2022 - 11:50 pm
 * The Purpose of this class is to Extend through Intent and Navigation of User based on Login User Type
 *
 * Modification
 * Constructor(Context, String UserType) -> Super(Context, Activity)
 * */

public class LoginIntentHandler extends Intent {

    //TODO 0006 To Change the Classes After Making User based Main Activities

    private final Class<?> CLASS_IF_ADMIN = MainActivityOwner.class;
    //private final Class<?> CLASS_IF_CUSTOMER = MainActivityFinder.class;


    public LoginIntentHandler(Context context, String UserType) {
        Class<?> toNavigateTo = null;

       /* if (UserType.equalsIgnoreCase(UserTypes.Customer)) {
            toNavigateTo = CLASS_IF_CUSTOMER;*//*
            UserLive.currentLoggedInUser.getCart().eliminateCartByLatestStock();*//*
        }*/
        if (UserType.equalsIgnoreCase(UserTypes.Admin)) toNavigateTo = CLASS_IF_ADMIN;

        setClass(context, toNavigateTo);
    }
}