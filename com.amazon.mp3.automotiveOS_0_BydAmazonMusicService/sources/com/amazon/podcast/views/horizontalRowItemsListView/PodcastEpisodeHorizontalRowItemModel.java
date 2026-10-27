package com.amazon.podcast.views.horizontalRowItemsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastEpisodeHorizontalRowItemModel extends BaseViewContentModel {
    private final Map<String, Date> availabilityDateMap;
    private final String catalogId;
    private final List<String> contentTiers;
    private final String imageUrl;
    private Integer index;
    private final Map<String, List<String>> podcastEpisodeTierBenefits;
    private final String podcastShowCatalogId;
    private final Map<String, List<String>> podcastShowTierBenefits;
    private final String podcastShowVariantId;
    private final String publishDate;
    private final boolean shouldEnableOrdinal;
    private final boolean shouldShowOrdinal;
    private final String subtitle;
    private final String title;
    private final String variantId;

    public PodcastEpisodeHorizontalRowItemModel(@Nullable final String viewId, @NonNull final ContentMetadata metadata, final int contentType, final String catalogId, final String podcastShowCatalogId, final String title, final String subtitle, final String imageUrl, final String publishDate, final String variantId, final String podcastShowVariantId, final List<String> contentTiers, final Map<String, List<String>> podcastEpisodeTierBenefits, final Map<String, List<String>> podcastShowTierBenefits, final Map<String, Date> availabilityDateMap, final boolean shouldEnableOrdinal, final boolean shouldShowOrdinal) {
        super(viewId, metadata, contentType, null, null);
        this.catalogId = catalogId;
        this.podcastShowCatalogId = podcastShowCatalogId;
        this.title = title;
        this.subtitle = subtitle;
        this.imageUrl = imageUrl;
        this.contentTiers = contentTiers;
        this.podcastEpisodeTierBenefits = podcastEpisodeTierBenefits;
        this.podcastShowTierBenefits = podcastShowTierBenefits;
        this.availabilityDateMap = availabilityDateMap;
        this.variantId = variantId;
        this.podcastShowVariantId = podcastShowVariantId;
        this.publishDate = publishDate;
        this.shouldEnableOrdinal = shouldEnableOrdinal;
        this.shouldShowOrdinal = shouldShowOrdinal;
        this.index = -1;
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

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public Map<String, List<String>> getPodcastEpisodeTierBenefits() {
        return this.podcastEpisodeTierBenefits;
    }

    public Map<String, Date> getAvailabilityDateMap() {
        return this.availabilityDateMap;
    }

    public boolean isShouldEnableOrdinal() {
        return this.shouldEnableOrdinal;
    }

    public boolean isShouldShowOrdinal() {
        return this.shouldShowOrdinal;
    }

    public Integer getIndex() {
        return this.index;
    }

    public void setIndex(final Integer index) {
        this.index = index;
    }
}
