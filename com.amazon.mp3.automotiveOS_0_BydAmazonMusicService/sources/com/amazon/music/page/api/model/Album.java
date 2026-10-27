package com.amazon.music.page.api.model;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Album implements Serializable {

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("isOwned")
    private Boolean isOwned = null;

    @SerializedName("parentalControls")
    private ParentalControls parentalControls = null;

    @SerializedName("contentTiers")
    private List<String> contentTiers = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("originalReleaseDateTimestamp")
    private BigDecimal originalReleaseDateTimestamp = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    @SerializedName("duration")
    private BigDecimal duration = null;

    @SerializedName("assetTypes")
    private List<AssetType> assetTypes = null;

    @SerializedName("tracksCount")
    private BigDecimal tracksCount = null;

    @SerializedName("artists")
    private List<Artist> artists = null;

    @SerializedName("playmodeEligibility")
    private PlaymodeEligibility playmodeEligibility = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName(ContextMappingConstants.CONTENT_ENCODING)
    private List<String> contentEncoding = null;

    @SerializedName("rank")
    private String rank = null;

    @SerializedName("asin")
    private String asin = null;

    @SerializedName("concatenatedArtistName")
    private String concatenatedArtistName = null;

    @SerializedName("isInLibrary")
    private Boolean isInLibrary = null;

    @SerializedName("spiritualCategory")
    private SpiritualCategory spiritualCategory = null;

    public String getPrimaryTitle() {
        return this.primaryTitle;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public Boolean getIsOwned() {
        return this.isOwned;
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

    public List<Artist> getArtists() {
        return this.artists;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public String getAsin() {
        return this.asin;
    }

    public Boolean getIsInLibrary() {
        return this.isInLibrary;
    }
}
