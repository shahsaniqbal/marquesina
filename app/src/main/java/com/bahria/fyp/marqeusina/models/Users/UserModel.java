package com.bahria.fyp.marqeusina.models.Users;

import com.bahria.fyp.marqeusina.models.MarqueeModel;

public class UserModel {

    private String UID;
    private String UserType;
    private String fullName;
    private String emailAddress;
    private String mobileNumber;
    private UserOtherDetailsModel details;
    private String marqueeDOCID;

    public UserModel() {


    }

    public UserModel(String UID, String userType, String fullName, String emailAddress, String mobileNumber, UserOtherDetailsModel details, String marqueeDOCID) {
        this.UID = UID;
        this.UserType = userType;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.details = details;
        this.marqueeDOCID = marqueeDOCID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public UserOtherDetailsModel getDetails() {
        return details;
    }

    public void setDetails(UserOtherDetailsModel details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "UID='" + UID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

}
