package com.aisle.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Likes {
    @SerializedName("profiles")
    @Expose
    private List<LikesProfile> profiles = null;
    @SerializedName("can_see_profile")
    @Expose
    private Boolean canSeeProfile;
    @SerializedName("likes_received_count")
    @Expose
    private Integer likesReceivedCount;

    public List<LikesProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<LikesProfile> profiles) {
        this.profiles = profiles;
    }

    public Boolean getCanSeeProfile() {
        return canSeeProfile;
    }

    public void setCanSeeProfile(Boolean canSeeProfile) {
        this.canSeeProfile = canSeeProfile;
    }

    public Integer getLikesReceivedCount() {
        return likesReceivedCount;
    }

    public void setLikesReceivedCount(Integer likesReceivedCount) {
        this.likesReceivedCount = likesReceivedCount;
    }
}
