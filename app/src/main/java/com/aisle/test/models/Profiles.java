package com.aisle.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profiles {

    @SerializedName("general_information")
    @Expose
    private GeneralInformation generalInformation;

    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;

    @SerializedName("has_active_subscription")
    @Expose
    private Boolean hasActiveSubscription;

    public GeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation = generalInformation;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Boolean getHasActiveSubscription() {
        return hasActiveSubscription;
    }

    public void setHasActiveSubscription(Boolean hasActiveSubscription) {
        this.hasActiveSubscription = hasActiveSubscription;
    }
}
