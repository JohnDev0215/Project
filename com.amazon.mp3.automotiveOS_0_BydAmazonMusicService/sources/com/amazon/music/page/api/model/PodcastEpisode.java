package com.amazon.music.page.api.model;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastEpisode implements Serializable {

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName(ContextMappingConstants.IMAGE)
    private Image image = null;

    @SerializedName("podcastShowImage")
    private Image podcastShowImage = null;

    @SerializedName("publishTime")
    private Date publishTime = null;

    @SerializedName("podcastShowTitle")
    private String podcastShowTitle = null;

    @SerializedName("contentTraits")
    private PodcastContentTraits contentTraits = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName("durationSeconds")
    private BigDecimal durationSeconds = null;

    @SerializedName("publishDate")
    private String publishDate = null;

    @SerializedName("availabilityDate")
    private String availabilityDate = null;

    @SerializedName(MediaTrack.ROLE_DESCRIPTION)
    private String description = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("podcastShowVariantId")
    private String podcastShowVariantId = null;

    @SerializedName("podcastShowCatalogId")
    private String podcastShowCatalogId = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    @SerializedName("durationMilliseconds")
    private BigDecimal durationMilliseconds = null;

    @SerializedName("availableUpsells")
    private PodcastAvailableUpsells availableUpsells = null;

    @SerializedName("podcastEpisodeVariantId")
    private String podcastEpisodeVariantId = null;

    @SerializedName("catalogId")
    private String catalogId = null;

    @SerializedName(MediaTrack.ROLE_SUBTITLE)
    private String subtitle = null;

    @SerializedName("playbackMode")
    private PodcastPlaybackMode playbackMode = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName("audioUri")
    private String audioUri = null;

    public Image getImage() {
        return this.image;
    }

    public Image getPodcastShowImage() {
        return this.podcastShowImage;
    }

    public Date getPublishTime() {
        return this.publishTime;
    }

    public String getPodcastShowTitle() {
        return this.podcastShowTitle;
    }

    public PodcastContentTraits getContentTraits() {
        return this.contentTraits;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public BigDecimal getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public String getAvailabilityDate() {
        return this.availabilityDate;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public String getPodcastShowCatalogId() {
        return this.podcastShowCatalogId;
    }

    public BigDecimal getDurationMilliseconds() {
        return this.durationMilliseconds;
    }

    public PodcastAvailableUpsells getAvailableUpsells() {
        return this.availableUpsells;
    }

    public String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public PodcastPlaybackMode getPlaybackMode() {
        return this.playbackMode;
    }
}
