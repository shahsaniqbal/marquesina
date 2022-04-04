package com.bahria.fyp.marqeusina.temp;

public class FirebaseDataKeys {
    final String CATEGORIES = "Menu";
    final String USERS = "Users";
    final String ITEMS = "Items";
    final String LOCATIONS = "CityAreasForDelivery";
    final static public String STORAGE_BUCKET_ADDRESS = "gs://marqeusina.appspot.com";

    public String getMenuRef(){
        return CATEGORIES;
    }
    public String getUsersRef(){
        return USERS;
    }
    public String getItemsRef() { return ITEMS; }

    public String getLocationsRef() {
        return LOCATIONS;
    }
}
