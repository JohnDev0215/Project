package com.amazon.podcast.views.verticalItemView;

import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class VerticalItemModel extends BaseViewContentModel {
    private String availabilityDate;
    private List<String> availableUpsells;
    private List<String> contentTraits;
    private long durationSeconds;
    private String imageUrl;
    private boolean isDisabled;
    private boolean isProgressEnabled;
    private String playbackMode;
    private String podcastEpisodeCatalogId;
    private String podcastEpisodeVariantId;
    private String podcastShowCatalogId;
    private String podcastShowVariantId;
    private String subTitle;
    private String title;

    public VerticalItemModel(final String viewId, final ContentMetadata metadata, final int contentType, final String podcastEpisodeCatalogId, final String podcastShowCatalogId, final String title, final String subTitle, final long durationSeconds, final String imageUrl, final boolean isDisabled, final String podcastEpisodeVariantId, final String podcastShowVariantId, final List<String> availableUpsells, final String playbackMode, final List<String> contentTraits, final String availabilityDate, final boolean isProgressEnabled) {
        super(viewId, metadata, contentType, null, null);
        this.podcastEpisodeCatalogId = podcastEpisodeCatalogId;
        this.podcastShowCatalogId = podcastShowCatalogId;
        this.title = title;
        this.subTitle = subTitle;
        this.durationSeconds = durationSeconds;
        this.imageUrl = imageUrl;
        this.podcastEpisodeVariantId = podcastEpisodeVariantId;
        this.podcastShowVariantId = podcastShowVariantId;
        this.isDisabled = isDisabled;
        this.availableUpsells = availableUpsells;
        this.playbackMode = playbackMode;
        this.contentTraits = contentTraits;
        this.availabilityDate = availabilityDate;
        this.isProgressEnabled = isProgressEnabled;
    }

    public String getPodcastEpisodeCatalogId() {
        return this.podcastEpisodeCatalogId;
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

    public long getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getPlaybackMode() {
        return this.playbackMode;
    }

    public List<String> getContentTraits() {
        return this.contentTraits;
    }

    public String getAvailabilityDate() {
        return this.availabilityDate;
    }

    public boolean isProgressEnabled() {
        return this.isProgressEnabled;
    }
}
