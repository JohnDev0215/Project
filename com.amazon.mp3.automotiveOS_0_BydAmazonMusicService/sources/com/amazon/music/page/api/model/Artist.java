package com.amazon.music.page.api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Artist implements Serializable {

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("roles")
    private List<Object> roles = null;

    @SerializedName("contributorAsin")
    private String contributorAsin = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("followerCountText")
    private String followerCountText = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName("asin")
    private String asin = null;

    @SerializedName("artistStationKey")
    private String artistStationKey = null;

    @SerializedName("isFollowed")
    private Boolean isFollowed = null;

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

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public String getName() {
        return this.name;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }
}
