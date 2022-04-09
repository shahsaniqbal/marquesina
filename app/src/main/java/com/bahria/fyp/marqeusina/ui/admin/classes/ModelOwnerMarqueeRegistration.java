package com.bahria.fyp.marqeusina.ui.admin.classes;

public class ModelOwnerMarqueeRegistration {

    String img, marqueeName, marqueeDescription,marqueeAddress, numberOfGuestsCanHandle, perHeadPrice, onlyHallPrice;


    public ModelOwnerMarqueeRegistration() {
    }

    public ModelOwnerMarqueeRegistration(String img, String marqueeName, String marqueeDescription, String marqueeAddress, String numberOfGuestsCanHandle, String perHeadPrice, String onlyHallPrice) {
        this.img = img;
        this.marqueeName = marqueeName;
        this.marqueeDescription = marqueeDescription;
        this.marqueeAddress = marqueeAddress;
        this.numberOfGuestsCanHandle = numberOfGuestsCanHandle;
        this.perHeadPrice = perHeadPrice;
        this.onlyHallPrice = onlyHallPrice;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
