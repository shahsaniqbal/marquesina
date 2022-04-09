package com.bahria.fyp.marqeusina.models.Users;


public class UserOtherDetailsModel {
    private String imageReference;

    public UserOtherDetailsModel() {
    }

    public UserOtherDetailsModel(String imageReference) {
        this.imageReference = imageReference;
    }

    public String getImageReference() {
        return imageReference;
    }

    public void setImageReference(String imageReference) {
        this.imageReference = imageReference;
    }

    @Override
    public String toString() {
        return imageReference.toString();
    }
}
