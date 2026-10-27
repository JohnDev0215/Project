package com.amazon.music.find.providers;

import com.amazon.music.find.model.EntityMetaData;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SearchContentTier;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchFeaturesProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u000208H&J\u0012\u00109\u001a\u0004\u0018\u0001062\u0006\u0010:\u001a\u00020;H&J\u0010\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020>H&J\u0018\u0010?\u001a\u00020\t2\u0006\u0010@\u001a\u00020\t2\u0006\u0010A\u001a\u00020\tH&J\u0012\u0010B\u001a\u00020\u00032\b\u0010C\u001a\u0004\u0018\u00010\tH&J\u0012\u0010D\u001a\u00020\u00032\b\u0010E\u001a\u0004\u0018\u00010FH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0014\u0010\u000e\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0012\u0010\u0010\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0005R\u0012\u0010\u0011\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0012\u0010\u0012\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0005R\u0012\u0010\u0013\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0005R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0012\u0010\u0018\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0005R\u0012\u0010\u0019\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0012\u0010\u001a\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0005R\u0012\u0010\u001b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u0012\u0010\u001c\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0005R\u0012\u0010\u001d\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0005R\u0012\u0010\u001e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0005R\u0012\u0010\u001f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005R\u0012\u0010 \u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0005R\u0012\u0010!\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0005R\u0012\u0010\"\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0005R\u0012\u0010#\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0005R\u0012\u0010$\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0005R\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0012\u0010-\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0005R\u0012\u0010/\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0005R\u0014\u00101\u001a\u0004\u0018\u000102X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u0006G"}, d2 = {"Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "", "areIvyRefinementPillsEnabled", "", "getAreIvyRefinementPillsEnabled", "()Z", "areQualityFiltersEnabled", "getAreQualityFiltersEnabled", "currentProfileMusicDirectedId", "", "getCurrentProfileMusicDirectedId", "()Ljava/lang/String;", "filterByChildFriendlyContent", "getFilterByChildFriendlyContent", "filterByExplicitLanguage", "getFilterByExplicitLanguage", "isAlexaSearchFindBrowseEnabled", "isAlexaSearchOrdinalPlaybackEnabled", "isBundesligaAccessible", "isChildDirected", "isCommunityPlaylistEnabled", "isDefaultProfile", "()Ljava/lang/Boolean;", "isExplicitLanguageFilterEnabled", "isFireOS", "isFreeTierCustomer", "isInCarMode", "isKatana", "isLanguageOfPerformanceEnabled", "isLiveEventEnabled", "isOffline", "isPodcastEnabled", "isSearchResultsCachingEnabled", "isUpsellToUnlimitedOnTopHitsTurnedOn", "isUserInHawkfireMarketplace", "isVideoPlaylistEnabled", "isVideosEnabled", "librarySource", "Lcom/amazon/music/find/model/search/SearchLibrarySource;", "getLibrarySource", "()Lcom/amazon/music/find/model/search/SearchLibrarySource;", "searchContentTier", "Lcom/amazon/music/search/SearchContentTier;", "getSearchContentTier", "()Lcom/amazon/music/search/SearchContentTier;", "shouldRequestRelatedEntities", "getShouldRequestRelatedEntities", "shouldSuppressIvyCatalogNonTrackAlbumFiltering", "getShouldSuppressIvyCatalogNonTrackAlbumFiltering", "viewType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "getViewType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "getEntityInfoFromContentMetaData", "Lcom/amazon/music/find/model/EntityMetaData;", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getEntityInfoFromNavigationMetaData", "navigationMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "getLocalizedString", "id", "", "getPodcastShowContentUrl", "podcastId", "title", "isLibrarySource", PageUriUtils.SOURCE_QUERY_PARAM, "shouldRequestPlaymodeEligibility", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchFeaturesProvider {
    boolean getAreIvyRefinementPillsEnabled();

    boolean getAreQualityFiltersEnabled();

    String getCurrentProfileMusicDirectedId();

    EntityMetaData getEntityInfoFromContentMetaData(ContentMetadata contentMetadata);

    EntityMetaData getEntityInfoFromNavigationMetaData(NavigationMetadata navigationMetadata);

    boolean getFilterByChildFriendlyContent();

    boolean getFilterByExplicitLanguage();

    SearchLibrarySource getLibrarySource();

    String getLocalizedString(int id);

    String getPodcastShowContentUrl(String podcastId, String title);

    SearchContentTier getSearchContentTier();

    boolean getShouldRequestRelatedEntities();

    boolean getShouldSuppressIvyCatalogNonTrackAlbumFiltering();

    ViewType getViewType();

    boolean isAlexaSearchFindBrowseEnabled();

    boolean isAlexaSearchOrdinalPlaybackEnabled();

    boolean isBundesligaAccessible();

    boolean isChildDirected();

    boolean isCommunityPlaylistEnabled();

    Boolean isDefaultProfile();

    boolean isExplicitLanguageFilterEnabled();

    boolean isFireOS();

    boolean isFreeTierCustomer();

    boolean isInCarMode();

    boolean isKatana();

    boolean isLanguageOfPerformanceEnabled();

    boolean isLibrarySource(String source);

    boolean isLiveEventEnabled();

    boolean isOffline();

    boolean isPodcastEnabled();

    boolean isSearchResultsCachingEnabled();

    boolean isUpsellToUnlimitedOnTopHitsTurnedOn();

    boolean isUserInHawkfireMarketplace();

    boolean isVideoPlaylistEnabled();

    boolean isVideosEnabled();

    boolean shouldRequestPlaymodeEligibility(EntityType entityType);

    /* JADX INFO: compiled from: SearchFeaturesProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean getFilterByExplicitLanguage(SearchFeaturesProvider searchFeaturesProvider) {
            Intrinsics.checkNotNullParameter(searchFeaturesProvider, "this");
            return (searchFeaturesProvider.isChildDirected() || searchFeaturesProvider.isInCarMode()) && searchFeaturesProvider.isExplicitLanguageFilterEnabled();
        }

        public static boolean getFilterByChildFriendlyContent(SearchFeaturesProvider searchFeaturesProvider) {
            Intrinsics.checkNotNullParameter(searchFeaturesProvider, "this");
            return searchFeaturesProvider.isChildDirected() && searchFeaturesProvider.isExplicitLanguageFilterEnabled();
        }
    }
}
