package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Station implements Serializable {

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("seed")
    private Seed seed = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName("parentalControls")
    private ParentalControls parentalControls = null;

    @SerializedName("stationKey")
    private String stationKey = null;

    @SerializedName("contentTiers")
    private List<String> contentTiers = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    @SerializedName("callToActionTitle")
    private String callToActionTitle = null;

    @SerializedName("pausedStateTitle")
    private String pausedStateTitle = null;

    @SerializedName("playingStateTitle")
    private String playingStateTitle = null;

    public String getCallToActionTitle() {
        return this.callToActionTitle;
    }

    public String getPausedStateTitle() {
        return this.pausedStateTitle;
    }

    public String getPlayingStateTitle() {
        return this.playingStateTitle;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getStationKey() {
        return this.stationKey;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }
}
