package com.amazon.podcast.views.episodeRowItemsListView;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeRowItemModel extends BaseViewContentModel {
    private String availabilityDate;
    private List<String> availableUpsells;
    private String catalogId;
    private List<String> contentTraits;
    private int durationSeconds;
    private String imageUrl;
    private String playbackMode;
    private String podcastEpisodeVariantId;
    private String podcastShowCatalogId;
    private String podcastShowVariantId;
    private String publishDate;
    private String subTitle;
    private String title;

    public EpisodeRowItemModel(final String viewId, final ContentMetadata metadata, final int contentType, final String catalogId, final String podcastShowCatalogId, final String title, final String subTitle, final String publishDate, final int durationSeconds, final String imageUrl, final List<String> contentTraits, final List<String> availableUpsells, final String playbackMode, final String podcastEpisodeVariantId, final String podcastShowVariantId, final String availabilityDate) {
        super(viewId, metadata, contentType, null, null);
        this.catalogId = catalogId;
        this.podcastShowCatalogId = podcastShowCatalogId;
        this.title = title;
        this.subTitle = subTitle;
        this.publishDate = publishDate;
        this.durationSeconds = durationSeconds;
        this.imageUrl = imageUrl;
        this.contentTraits = contentTraits;
        this.availableUpsells = availableUpsells;
        this.playbackMode = playbackMode;
        this.podcastEpisodeVariantId = podcastEpisodeVariantId;
        this.podcastShowVariantId = podcastShowVariantId;
        this.availabilityDate = availabilityDate;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public String getPodcastShowCatalogId() {
        return this.podcastShowCatalogId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public int getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public List<String> getContentTraits() {
        return this.contentTraits;
    }

    public List<String> getAvailableUpsells() {
        return this.availableUpsells;
    }

    public String getPlaybackMode() {
        return this.playbackMode;
    }

    public String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public String getAvailabilityDate() {
        return this.availabilityDate;
    }
}
