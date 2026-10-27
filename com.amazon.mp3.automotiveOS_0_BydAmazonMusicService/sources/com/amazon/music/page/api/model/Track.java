package com.amazon.music.page.api.model;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Track implements Serializable {

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName("images")
    private List<Image> images = null;

    @SerializedName("trackNumber")
    private BigDecimal trackNumber = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("album")
    private Album album = null;

    @SerializedName("isOwned")
    private Boolean isOwned = null;

    @SerializedName("assetQualities")
    private List<AssetQuality> assetQualities = null;

    @SerializedName("diskNumber")
    private BigDecimal diskNumber = null;

    @SerializedName("genreName")
    private String genreName = null;

    @SerializedName("parentalControls")
    private ParentalControls parentalControls = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("contentTiers")
    private List<String> contentTiers = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    @SerializedName("assetType")
    private AssetType assetType = null;

    @SerializedName("labels")
    private List<String> labels = null;

    @SerializedName("duration")
    private BigDecimal duration = null;

    @SerializedName("hasLyrics")
    private Boolean hasLyrics = null;

    @SerializedName("artists")
    private List<Artist> artists = null;

    @SerializedName("popularity")
    private BigDecimal popularity = null;

    @SerializedName(ContextMappingConstants.CONTENT_ENCODING)
    private List<String> contentEncoding = null;

    @SerializedName("playmodeEligibility")
    private PlaymodeEligibility playmodeEligibility = null;

    @SerializedName("rank")
    private String rank = null;

    @SerializedName("rankMovement")
    private RankMovement rankMovement = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

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

    public RankMovement getRankMovement() {
        return this.rankMovement;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public Album getAlbum() {
        return this.album;
    }

    public Boolean getIsOwned() {
        return this.isOwned;
    }

    public List<AssetQuality> getAssetQualities() {
        return this.assetQualities;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getTitle() {
        return this.title;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public String getTertiaryTitle() {
        return this.tertiaryTitle;
    }

    public AssetType getAssetType() {
        return this.assetType;
    }

    public List<String> getLabels() {
        return this.labels;
    }

    public BigDecimal getDuration() {
        return this.duration;
    }

    public List<Artist> getArtists() {
        return this.artists;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public String getRank() {
        return this.rank;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getSecondaryTitle() {
        return this.secondaryTitle;
    }

    public String getAsin() {
        return this.asin;
    }

    public Boolean getIsInLibrary() {
        return this.isInLibrary;
    }
}
