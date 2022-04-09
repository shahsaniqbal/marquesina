package com.bahria.fyp.marqeusina.models;

public class MarqueeModel {

    String docID;
    String imagePath;
    String marqueeName;
    String marqueeDescription;
    String marqueeAddress;
    String numberOfGuestsCanHandle;
    String perHeadPrice;
    String onlyHallPrice;
    String ownerID;

    public MarqueeModel() {
    }

    public MarqueeModel(String docID, String imagePath, String marqueeName, String marqueeDescription, String marqueeAddress, String numberOfGuestsCanHandle, String perHeadPrice, String onlyHallPrice) {
        this.docID = docID;
        this.imagePath = imagePath;
        this.marqueeName = marqueeName;
        this.marqueeDescription = marqueeDescription;
        this.marqueeAddress = marqueeAddress;
        this.numberOfGuestsCanHandle = numberOfGuestsCanHandle;
        this.perHeadPrice = perHeadPrice;
        this.onlyHallPrice = onlyHallPrice;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMarqueeName() {
        return marqueeName;
    }

    public void setMarqueeName(String marqueeName) {
        this.marqueeName = marqueeName;
    }

    public String getMarqueeDescription() {
        return marqueeDescription;
    }

    public void setMarqueeDescription(String marqueeDescription) {
        this.marqueeDescription = marqueeDescription;
    }

    public String getMarqueeAddress() {
        return marqueeAddress;
    }

    public void setMarqueeAddress(String marqueeAddress) {
        this.marqueeAddress = marqueeAddress;
    }

    public String getNumberOfGuestsCanHandle() {
        return numberOfGuestsCanHandle;
    }

    public void setNumberOfGuestsCanHandle(String numberOfGuestsCanHandle) {
        this.numberOfGuestsCanHandle = numberOfGuestsCanHandle;
    }

    public String getPerHeadPrice() {
        return perHeadPrice;
    }

    public void setPerHeadPrice(String perHeadPrice) {
        this.perHeadPrice = perHeadPrice;
    }

    public String getOnlyHallPrice() {
        return onlyHallPrice;
    }

    public void setOnlyHallPrice(String onlyHallPrice) {
        this.onlyHallPrice = onlyHallPrice;
    }
}
