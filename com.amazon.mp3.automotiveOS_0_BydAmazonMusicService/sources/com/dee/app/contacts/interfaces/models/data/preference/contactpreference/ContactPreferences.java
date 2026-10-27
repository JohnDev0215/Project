package com.dee.app.contacts.interfaces.models.data.preference.contactpreference;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/* JADX INFO: loaded from: classes5.dex */
public class ContactPreferences {

    @JsonProperty("emergencyPreference")
    @JsonAlias({"EMERGENCY_CONTACT", "emergencyPreference"})
    private boolean emergencyPreference;

    @JsonProperty("favoritePreference")
    @JsonAlias({"FAVORITE_CONTACT", "favoritePreference"})
    private boolean favoritePreference;

    public void setFavoritePreference(boolean z) {
        this.favoritePreference = z;
    }

    public void setEmergencyPreference(boolean z) {
        this.emergencyPreference = z;
    }
}
