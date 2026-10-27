package com.amazon.music.page.api.model;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class VideoStory implements Serializable {

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("asin")
    private String asin = null;

    @SerializedName("assetQualities")
    private List<AssetQuality> assetQualities = null;

    @SerializedName("contentTiers")
    private List<String> contentTiers = null;

    @SerializedName("duration")
    private BigDecimal duration = null;

    @SerializedName("parentalControls")
    private ParentalControls parentalControls = null;

    @SerializedName(ContextMappingConstants.IMAGE)
    private Image image = null;

    @SerializedName("genreName")
    private String genreName = null;

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    @SerializedName("title")
    private String title = null;

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<AssetQuality> getAssetQualities() {
        return this.assetQualities;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public BigDecimal getDuration() {
        return this.duration;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public Image getImages() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }
}
