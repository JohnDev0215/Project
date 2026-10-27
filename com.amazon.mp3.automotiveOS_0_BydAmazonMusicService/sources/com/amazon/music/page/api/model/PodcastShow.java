package com.amazon.music.page.api.model;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastShow implements Serializable {

    @SerializedName("primaryTitle")
    private String primaryTitle = null;

    @SerializedName(ContextMappingConstants.IMAGE)
    private Image image = null;

    @SerializedName("contentTraits")
    private PodcastContentTraits contentTraits = null;

    @SerializedName("blockRef")
    private String blockRef = null;

    @SerializedName(MediaTrack.ROLE_DESCRIPTION)
    private String description = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("podcastShowVariantId")
    private String podcastShowVariantId = null;

    @SerializedName("tertiaryTitle")
    private String tertiaryTitle = null;

    @SerializedName("availableUpsells")
    private PodcastAvailableUpsells availableUpsells = null;

    @SerializedName("catalogId")
    private String catalogId = null;

    @SerializedName(MediaTrack.ROLE_SUBTITLE)
    private String subtitle = null;

    @SerializedName("secondaryTitle")
    private String secondaryTitle = null;

    @SerializedName("playbackMode")
    private PodcastPlaybackMode playbackMode = null;

    public Image getImage() {
        return this.image;
    }

    public PodcastContentTraits getContentTraits() {
        return this.contentTraits;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public PodcastAvailableUpsells getAvailableUpsells() {
        return this.availableUpsells;
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
