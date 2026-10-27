package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class PlaymodeEligibility implements Serializable {

    @SerializedName("onDemand")
    private Boolean onDemand = null;

    @SerializedName("shuffleType")
    private ShuffleType shuffleType = null;

    @SerializedName("sfa")
    private Boolean sfa = null;

    public Boolean getOnDemand() {
        return this.onDemand;
    }

    public ShuffleType getShuffleType() {
        return this.shuffleType;
    }

    public Boolean getSfa() {
        return this.sfa;
    }
}
