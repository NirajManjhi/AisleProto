package com.aisle.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Invites {
    @SerializedName("profiles")
    @Expose
    private List<Profiles> profiles = null;
    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("pending_invitations_count")
    @Expose
    private Integer pendingInvitationsCount;

    public List<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profiles> profiles) {
        this.profiles = profiles;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPendingInvitationsCount() {
        return pendingInvitationsCount;
    }

    public void setPendingInvitationsCount(Integer pendingInvitationsCount) {
        this.pendingInvitationsCount = pendingInvitationsCount;
    }
}
