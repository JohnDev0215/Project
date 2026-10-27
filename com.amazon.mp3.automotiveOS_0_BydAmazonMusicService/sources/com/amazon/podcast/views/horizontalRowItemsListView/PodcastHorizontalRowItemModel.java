package com.amazon.podcast.views.horizontalRowItemsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastHorizontalRowItemModel extends BaseViewContentModel {
    private List<String> availableUpsells;
    private String catalogId;
    private List<String> contentTiers;
    private List<String> contentTraits;
    private String imageUrl;
    private Integer index;
    private boolean isSearchResult;
    private String playbackMode;
    private String podcastShowVariantId;
    private final boolean shouldEnableOrdinal;
    private final boolean shouldShowOrdinal;
    private String subTitle;
    private Map<String, List<String>> tierBenefits;
    private String title;

    public PodcastHorizontalRowItemModel(@Nullable final String viewId, @NonNull final ContentMetadata metadata, final int contentType, final String catalogId, final String title, final String subTitle, final String imageUrl, final List<String> contentTraits, final List<String> availableUpsells, final String playbackMode, final String podcastShowVariantId, final List<String> contentTiers, final Map<String, List<String>> tierBenefits, final boolean isSearchResult, final boolean shouldEnableOrdinal, final boolean shouldShowOrdinal) {
        super(viewId, metadata, contentType, null, null);
        this.catalogId = catalogId;
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.contentTraits = contentTraits;
        this.availableUpsells = availableUpsells;
        this.playbackMode = playbackMode;
        this.podcastShowVariantId = podcastShowVariantId;
        this.contentTiers = contentTiers;
        this.tierBenefits = tierBenefits;
        this.isSearchResult = isSearchResult;
        this.shouldEnableOrdinal = shouldEnableOrdinal;
        this.shouldShowOrdinal = shouldShowOrdinal;
        this.index = -1;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
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

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public Map<String, List<String>> getTierBenefits() {
        return this.tierBenefits;
    }

    public boolean isSearchResult() {
        return this.isSearchResult;
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
