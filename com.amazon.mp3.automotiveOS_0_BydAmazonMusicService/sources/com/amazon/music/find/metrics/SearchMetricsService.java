package com.amazon.music.find.metrics;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.find.viewmodels.SearchOperation;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.find.common.EntityType;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchMetricsService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\bf\u0018\u00002\u00020\u0001JC\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010\fJM\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\u0015J!\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\u0015J!\u0010\u0018\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\u0015J!\u0010\u0019\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\u0015J\b\u0010\u001a\u001a\u00020\u000eH&J+\u0010\u001b\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u001dH&¢\u0006\u0002\u0010\u001eJ!\u0010\u001f\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\u0015J!\u0010 \u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\"H&¢\u0006\u0002\u0010#J\u001c\u0010$\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010&H&J?\u0010(\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*H&¢\u0006\u0002\u0010+J+\u0010,\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010-J!\u0010.\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\u0015J\u007f\u0010/\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u00100\u001a\u0002012\b\u0010'\u001a\u0004\u0018\u00010&2\u000e\u00102\u001a\n\u0018\u000103j\u0004\u0018\u0001`42\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u00010&2\b\u00108\u001a\u0004\u0018\u0001092\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*H&¢\u0006\u0002\u0010:J+\u0010;\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010'\u001a\u0004\u0018\u00010&H&¢\u0006\u0002\u0010<J+\u0010=\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010'\u001a\u0004\u0018\u00010&H&¢\u0006\u0002\u0010<J!\u0010>\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\"H&¢\u0006\u0002\u0010#JC\u0010?\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u00108\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&H&¢\u0006\u0002\u0010@J!\u0010A\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\"H&¢\u0006\u0002\u0010#J-\u0010B\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*H&¢\u0006\u0002\u0010CJ3\u0010D\u001a\u00020\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010E\u001a\u0004\u0018\u00010&2\u0006\u0010\u001c\u001a\u00020\u001dH&¢\u0006\u0002\u0010FJ+\u0010G\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&¢\u0006\u0002\u0010HJ\b\u0010I\u001a\u00020\u000eH&¨\u0006J"}, d2 = {"Lcom/amazon/music/find/metrics/SearchMetricsService;", "", "getUiPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "isOffline", "", "isLibrary", "isCarMode", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;ZLcom/amazon/music/platform/find/common/EntityType;)Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "reportLatencies", "", "searchOperation", "Lcom/amazon/music/find/viewmodels/SearchOperation;", "performanceData", "Lcom/amazon/music/find/metrics/PerformanceData;", "(Lcom/amazon/music/find/viewmodels/SearchOperation;Lcom/amazon/music/find/metrics/PerformanceData;Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)V", "trackApplyFilterButtonUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;)V", "trackCancelFilterButtonUiClick", "trackCancelSearchButtonUiClick", "trackClearFilterButtonUiClick", "trackClearSearchButtonUiClick", "trackClearSearchHistoryUiClick", "trackExecuteSearchUiClick", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "trackFilterButtonUiClick", "trackFindHomeUiPageView", "loadTimeMilliseconds", "", "(Ljava/lang/Boolean;Ljava/lang/Long;)V", "trackGenericUiClick", ImagesContract.URL, "", "blockRef", "trackGenericUiPageView", "uiEventMetadata", "Lcom/amazon/music/find/metrics/UiEventMetadata;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Long;Lcom/amazon/music/platform/find/common/EntityType;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "trackGoBackButtonUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)V", "trackInitiateSearchUiClick", "trackMetadataUiClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "Lcom/amazon/music/find/metrics/MetricsEntityType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "entityPos", "", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/platform/find/common/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "trackSearchAutoCorrectOriginalUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/String;)V", "trackSearchDidYouMeanUiClick", "trackSearchHistoryUiPageView", "trackSearchQueryUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/String;Lcom/amazon/music/find/metrics/UiEventMetadata;Ljava/lang/String;)V", "trackSearchResultsUiPageView", "trackSearchSuggestionUiPageView", "(Ljava/lang/Boolean;Ljava/lang/Long;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "trackSeeMoreButtonUiClick", "viewId", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "trackSeeMoreUiPageView", "(Ljava/lang/Boolean;Ljava/lang/Long;Lcom/amazon/music/platform/find/common/EntityType;)V", "trackTopHitsSearchUpsellNW", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchMetricsService {
    PageType getUiPageType(SearchExperienceMode mode, Boolean isOffline, Boolean isLibrary, boolean isCarMode, EntityType seeMoreEntityType);

    void reportLatencies(SearchOperation searchOperation, PerformanceData performanceData, SearchExperienceMode mode, Boolean isOffline, Boolean isLibrary, EntityType seeMoreEntityType);

    void trackApplyFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary);

    void trackCancelFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary);

    void trackCancelSearchButtonUiClick(SearchExperienceMode mode, Boolean isLibrary);

    void trackClearFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary);

    void trackClearSearchButtonUiClick(SearchExperienceMode mode, Boolean isLibrary);

    void trackClearSearchHistoryUiClick();

    void trackExecuteSearchUiClick(SearchExperienceMode mode, Boolean isLibrary, InteractionType interactionType);

    void trackFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary);

    void trackFindHomeUiPageView(Boolean isLibrary, Long loadTimeMilliseconds);

    void trackGenericUiClick(String url, String blockRef);

    void trackGenericUiPageView(SearchExperienceMode mode, Boolean isLibrary, Long loadTimeMilliseconds, EntityType seeMoreEntityType, UiEventMetadata uiEventMetadata);

    void trackGoBackButtonUiClick(SearchExperienceMode mode, Boolean isLibrary, EntityType seeMoreEntityType);

    void trackInitiateSearchUiClick(SearchExperienceMode mode, Boolean isLibrary);

    void trackMetadataUiClickEvent(SearchExperienceMode mode, Boolean isLibrary, ActionType actionType, String blockRef, com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType entityType, EntityIdType entityIdType, String entityId, Integer entityPos, EntityType seeMoreEntityType, InteractionType interactionType, UiEventMetadata uiEventMetadata);

    void trackSearchAutoCorrectOriginalUiClick(SearchExperienceMode mode, Boolean isLibrary, String blockRef);

    void trackSearchDidYouMeanUiClick(SearchExperienceMode mode, Boolean isLibrary, String blockRef);

    void trackSearchHistoryUiPageView(Boolean isLibrary, Long loadTimeMilliseconds);

    void trackSearchQueryUiClick(SearchExperienceMode mode, Boolean isLibrary, String entityPos, UiEventMetadata uiEventMetadata, String blockRef);

    void trackSearchResultsUiPageView(Boolean isLibrary, Long loadTimeMilliseconds);

    void trackSearchSuggestionUiPageView(Boolean isLibrary, Long loadTimeMilliseconds, UiEventMetadata uiEventMetadata);

    void trackSeeMoreButtonUiClick(SearchExperienceMode mode, Boolean isLibrary, String viewId, InteractionType interactionType);

    void trackSeeMoreUiPageView(Boolean isLibrary, Long loadTimeMilliseconds, EntityType seeMoreEntityType);

    void trackTopHitsSearchUpsellNW();

    /* JADX INFO: compiled from: SearchMetricsService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void trackExecuteSearchUiClick$default(SearchMetricsService searchMetricsService, SearchExperienceMode searchExperienceMode, Boolean bool, InteractionType interactionType, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackExecuteSearchUiClick");
            }
            if ((i & 4) != 0) {
                interactionType = InteractionType.TAP;
            }
            searchMetricsService.trackExecuteSearchUiClick(searchExperienceMode, bool, interactionType);
        }

        public static /* synthetic */ void trackSearchQueryUiClick$default(SearchMetricsService searchMetricsService, SearchExperienceMode searchExperienceMode, Boolean bool, String str, UiEventMetadata uiEventMetadata, String str2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackSearchQueryUiClick");
            }
            searchMetricsService.trackSearchQueryUiClick(searchExperienceMode, bool, str, (i & 8) != 0 ? null : uiEventMetadata, (i & 16) != 0 ? null : str2);
        }

        public static /* synthetic */ void trackMetadataUiClickEvent$default(SearchMetricsService searchMetricsService, SearchExperienceMode searchExperienceMode, Boolean bool, ActionType actionType, String str, com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType entityType, EntityIdType entityIdType, String str2, Integer num, EntityType entityType2, InteractionType interactionType, UiEventMetadata uiEventMetadata, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackMetadataUiClickEvent");
            }
            searchMetricsService.trackMetadataUiClickEvent(searchExperienceMode, bool, actionType, str, entityType, entityIdType, str2, num, entityType2, interactionType, (i & 1024) != 0 ? null : uiEventMetadata);
        }

        public static /* synthetic */ void trackGenericUiPageView$default(SearchMetricsService searchMetricsService, SearchExperienceMode searchExperienceMode, Boolean bool, Long l, EntityType entityType, UiEventMetadata uiEventMetadata, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackGenericUiPageView");
            }
            if ((i & 16) != 0) {
                uiEventMetadata = null;
            }
            searchMetricsService.trackGenericUiPageView(searchExperienceMode, bool, l, entityType, uiEventMetadata);
        }

        public static /* synthetic */ void trackSearchSuggestionUiPageView$default(SearchMetricsService searchMetricsService, Boolean bool, Long l, UiEventMetadata uiEventMetadata, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackSearchSuggestionUiPageView");
            }
            if ((i & 4) != 0) {
                uiEventMetadata = null;
            }
            searchMetricsService.trackSearchSuggestionUiPageView(bool, l, uiEventMetadata);
        }

        public static /* synthetic */ PageType getUiPageType$default(SearchMetricsService searchMetricsService, SearchExperienceMode searchExperienceMode, Boolean bool, Boolean bool2, boolean z, EntityType entityType, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUiPageType");
            }
            if ((i & 8) != 0) {
                z = false;
            }
            boolean z2 = z;
            if ((i & 16) != 0) {
                entityType = null;
            }
            return searchMetricsService.getUiPageType(searchExperienceMode, bool, bool2, z2, entityType);
        }

        public static /* synthetic */ void reportLatencies$default(SearchMetricsService searchMetricsService, SearchOperation searchOperation, PerformanceData performanceData, SearchExperienceMode searchExperienceMode, Boolean bool, Boolean bool2, EntityType entityType, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reportLatencies");
            }
            searchMetricsService.reportLatencies(searchOperation, performanceData, (i & 4) != 0 ? null : searchExperienceMode, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : bool2, (i & 32) != 0 ? null : entityType);
        }
    }
}
