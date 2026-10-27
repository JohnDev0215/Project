package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Genre implements Serializable {

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName("stationKey")
    private String stationKey = null;

    @SerializedName("id")
    private String id = null;

    @SerializedName("contentTiers")
    private List<String> contentTiers = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("target")
    private String target = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    public String getPrimaryTitle() {
        return this.primaryTitle;
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

    public String getStationKey() {
        return this.stationKey;
    }

    public String getId() {
        return this.id;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTarget() {
        return this.target;
    }

    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }
}
