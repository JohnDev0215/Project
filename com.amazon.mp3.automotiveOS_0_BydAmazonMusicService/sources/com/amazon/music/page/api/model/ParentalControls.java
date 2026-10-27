package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class ParentalControls implements Serializable {

    @SerializedName("hasExplicitLanguage")
    private Boolean hasExplicitLanguage = null;

    public Boolean getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }
}
