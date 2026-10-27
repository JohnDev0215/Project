package com.amazon.music.find.metrics;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsApi;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.find.viewmodels.SearchOperation;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.find.common.EntityType;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchMetricsServiceDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 d2\u00020\u0001:\u0001dB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J,\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u000e\u0010\u0015\u001a\n\u0018\u00010\u0016j\u0004\u0018\u0001`\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001c\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001c\u0010\u001b\u001a\u00020\t2\n\u0010\u0015\u001a\u00060\u0016j\u0002`\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J?\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010#\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0002\u0010%J?\u0010&\u001a\u0004\u0018\u00010'2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\"\u001a\u0004\u0018\u00010\u00192\u0006\u0010(\u001a\u00020\u00192\b\u0010#\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0002\u0010)J\u0012\u0010*\u001a\u00020\u00192\b\u0010+\u001a\u0004\u0018\u00010\tH\u0002JE\u0010,\u001a\u00020-2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010#\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0002\u00100J\u0019\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u00104J!\u00105\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u00106J!\u00107\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u00106J!\u00108\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u00106J!\u00109\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u00106J!\u0010:\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u00106J\b\u0010;\u001a\u00020-H\u0016J)\u0010<\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\u0006\u0010=\u001a\u00020>H\u0016¢\u0006\u0002\u0010?J!\u0010@\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u00106J!\u0010A\u001a\u00020-2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010B\u001a\u0004\u0018\u00010CH\u0016¢\u0006\u0002\u0010DJ\u001c\u0010E\u001a\u00020-2\b\u0010F\u001a\u0004\u0018\u00010\t2\b\u0010+\u001a\u0004\u0018\u00010\tH\u0016J=\u0010G\u001a\u00020-2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016¢\u0006\u0002\u0010JJ+\u0010K\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010#\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0002\u0010LJ!\u0010M\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u00106J}\u0010N\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\u0006\u0010O\u001a\u00020P2\b\u0010+\u001a\u0004\u0018\u00010\t2\u000e\u0010\u0015\u001a\n\u0018\u00010\u0016j\u0004\u0018\u0001`\u00172\b\u0010Q\u001a\u0004\u0018\u00010R2\b\u0010S\u001a\u0004\u0018\u00010\t2\b\u0010T\u001a\u0004\u0018\u0001022\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010=\u001a\u00020>2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016¢\u0006\u0002\u0010UJ+\u0010V\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010+\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010WJ+\u0010X\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010+\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010WJ!\u0010Y\u001a\u00020-2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010B\u001a\u0004\u0018\u00010CH\u0016¢\u0006\u0002\u0010DJ?\u0010Z\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010T\u001a\u0004\u0018\u00010\t2\b\u0010H\u001a\u0004\u0018\u00010I2\b\u0010+\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0002\u0010[J!\u0010\\\u001a\u00020-2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010B\u001a\u0004\u0018\u00010CH\u0016¢\u0006\u0002\u0010DJ+\u0010]\u001a\u00020-2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016¢\u0006\u0002\u0010^J3\u0010_\u001a\u00020-2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010+\u001a\u0004\u0018\u00010\t2\u0006\u0010=\u001a\u00020>H\u0016¢\u0006\u0002\u0010`J+\u0010a\u001a\u00020-2\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016¢\u0006\u0002\u0010bJ\b\u0010c\u001a\u00020-H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Lcom/amazon/music/find/metrics/SearchMetricsServiceDefault;", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "searchMetricsApi", "Lcom/amazon/music/find/metrics/SearchMetricsApi;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/metrics/SearchMetricsApi;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "contentInfoForApplyFilter", "", "", "contentInfoForCancelFilter", "contentInfoForCancelSearch", "contentInfoForClearFilter", "contentInfoForClearSearchHistory", "contentInfoForExecuteSearch", "contentInfoForInitiateSearch", "contentInfoForOpenFilterMenu", "contentInfoForSearchHistoryEntity", "contentInfoForSearchHistoryQuery", "contentInfoForSearchSuggestion", "getContentInfoForLibraryContent", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "Lcom/amazon/music/find/metrics/MetricsEntityType;", "isOffline", "", "getContentInfoForLibrarySeeMore", "getContentNameForLibraryEntityType", "getPageLoadLatencyCodeForSearchOperation", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "searchOperation", "Lcom/amazon/music/find/viewmodels/SearchOperation;", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "isLibrary", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "(Lcom/amazon/music/find/viewmodels/SearchOperation;Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getUiPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "isCarMode", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;ZLcom/amazon/music/platform/find/common/EntityType;)Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "isBlockRefEmpty", "blockRef", "reportLatencies", "", "performanceData", "Lcom/amazon/music/find/metrics/PerformanceData;", "(Lcom/amazon/music/find/viewmodels/SearchOperation;Lcom/amazon/music/find/metrics/PerformanceData;Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)V", "toIntOrNull", "", "value", "(Ljava/lang/String;)Ljava/lang/Integer;", "trackApplyFilterButtonUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;)V", "trackCancelFilterButtonUiClick", "trackCancelSearchButtonUiClick", "trackClearFilterButtonUiClick", "trackClearSearchButtonUiClick", "trackClearSearchHistoryUiClick", "trackExecuteSearchUiClick", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "trackFilterButtonUiClick", "trackFindHomeUiPageView", "loadTimeMilliseconds", "", "(Ljava/lang/Boolean;Ljava/lang/Long;)V", "trackGenericUiClick", ImagesContract.URL, "trackGenericUiPageView", "uiEventMetadata", "Lcom/amazon/music/find/metrics/UiEventMetadata;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Long;Lcom/amazon/music/platform/find/common/EntityType;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "trackGoBackButtonUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)V", "trackInitiateSearchUiClick", "trackMetadataUiClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "entityPos", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/platform/find/common/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "trackSearchAutoCorrectOriginalUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/String;)V", "trackSearchDidYouMeanUiClick", "trackSearchHistoryUiPageView", "trackSearchQueryUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/String;Lcom/amazon/music/find/metrics/UiEventMetadata;Ljava/lang/String;)V", "trackSearchResultsUiPageView", "trackSearchSuggestionUiPageView", "(Ljava/lang/Boolean;Ljava/lang/Long;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "trackSeeMoreButtonUiClick", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;)V", "trackSeeMoreUiPageView", "(Ljava/lang/Boolean;Ljava/lang/Long;Lcom/amazon/music/platform/find/common/EntityType;)V", "trackTopHitsSearchUpsellNW", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchMetricsServiceDefault implements SearchMetricsService {
    public static final String CONTENT_NAME_BASE_OFFLINE_LIBRARY_SEARCH = "offlineSearchResults";
    public static final String CONTENT_NAME_BASE_ONLINE_LIBRARY_SEARCH = "cloudSearchResults";
    public static final String CONTENT_NAME_FILTER_DIALOG = "filterDialog";
    public static final String CONTENT_NAME_INLINE_SEARCH_SUGGESTIONS = "inlineSearchSuggestions";
    public static final String CONTENT_NAME_SEARCH_HISTORY = "searchHistory";
    public static final String CONTENT_NAME_SEARCH_HISTORY_ENTITY = "searchHistoryEntity";
    public static final String CONTENT_NAME_SEARCH_TOGGLE = "searchToggle";
    public static final String CONTENT_NAME_SUFFIX_ALBUMS = "-albums";
    public static final String CONTENT_NAME_SUFFIX_ARTISTS = "-artists";
    public static final String CONTENT_NAME_SUFFIX_PLAYLISTS = "-playlists";
    public static final String CONTENT_NAME_SUFFIX_TRACKS = "-tracks";
    public static final String CONTENT_SRC_CLIENT = "client";
    public static final boolean PAGE_LATENCY_REPORTING_ENABLED = true;
    private static final String TAG = "SPL";
    private static final String TOP_HIT_SEARCH_UPSELL_NW_FLEX_EVENT_NAME = "TOP_HIT_SEARCH_UPSELL_NW";
    private final Map<String, String> contentInfoForApplyFilter;
    private final Map<String, String> contentInfoForCancelFilter;
    private final Map<String, String> contentInfoForCancelSearch;
    private final Map<String, String> contentInfoForClearFilter;
    private final Map<String, String> contentInfoForClearSearchHistory;
    private final Map<String, String> contentInfoForExecuteSearch;
    private final Map<String, String> contentInfoForInitiateSearch;
    private final Map<String, String> contentInfoForOpenFilterMenu;
    private final Map<String, String> contentInfoForSearchHistoryEntity;
    private final Map<String, String> contentInfoForSearchHistoryQuery;
    private final Map<String, String> contentInfoForSearchSuggestion;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchMetricsApi searchMetricsApi;
    private static final Logger logger = LoggerFactory.getLogger(SearchMetricsServiceDefault.class.getName());

    /* JADX INFO: compiled from: SearchMetricsServiceDefault.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[SearchExperienceMode.values().length];
            iArr[SearchExperienceMode.SEARCH_HISTORY.ordinal()] = 1;
            iArr[SearchExperienceMode.SEARCH_SUGGESTION.ordinal()] = 2;
            iArr[SearchExperienceMode.SEARCH_RESULT.ordinal()] = 3;
            iArr[SearchExperienceMode.SEE_ALL.ordinal()] = 4;
            iArr[SearchExperienceMode.FIND_LANDING.ordinal()] = 5;
            iArr[SearchExperienceMode.SEARCH_FILTER.ordinal()] = 6;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[EntityType.values().length];
            iArr2[EntityType.ARTIST.ordinal()] = 1;
            iArr2[EntityType.ALBUM.ordinal()] = 2;
            iArr2[EntityType.TRACK.ordinal()] = 3;
            iArr2[EntityType.PLAYLIST.ordinal()] = 4;
            iArr2[EntityType.COMMUNITY_PLAYLIST.ordinal()] = 5;
            iArr2[EntityType.STATION.ordinal()] = 6;
            iArr2[EntityType.VIDEO.ordinal()] = 7;
            iArr2[EntityType.VIDEO_PLAYLIST.ordinal()] = 8;
            iArr2[EntityType.PODCAST_SHOW.ordinal()] = 9;
            iArr2[EntityType.PODCAST_EPISODE.ordinal()] = 10;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[SearchOperation.values().length];
            iArr3[SearchOperation.SHOW_SEARCH_SUGGESTIONS.ordinal()] = 1;
            iArr3[SearchOperation.SHOW_SEARCH_HISTORY.ordinal()] = 2;
            iArr3[SearchOperation.EXECUTE_SEARCH_FROM_METADATA.ordinal()] = 3;
            iArr3[SearchOperation.EXECUTE_SEARCH_FROM_SPELL_CORRECTION.ordinal()] = 4;
            iArr3[SearchOperation.EXECUTE_SEARCH_FROM_TERM.ordinal()] = 5;
            iArr3[SearchOperation.EXECUTE_SEARCH_FROM_APPLY_FILTER.ordinal()] = 6;
            iArr3[SearchOperation.REFRESH_SEARCH.ordinal()] = 7;
            iArr3[SearchOperation.EXECUTE_SEARCH_FROM_QUERY.ordinal()] = 8;
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType.values().length];
            iArr4[com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType.ALBUM.ordinal()] = 1;
            iArr4[com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType.ARTIST.ordinal()] = 2;
            iArr4[com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType.AUTO_PLAYLIST.ordinal()] = 3;
            iArr4[com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType.PRIME_PLAYLIST.ordinal()] = 4;
            iArr4[com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType.USER_PLAYLIST.ordinal()] = 5;
            iArr4[com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType.TRACK.ordinal()] = 6;
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    public SearchMetricsServiceDefault(SearchMetricsApi searchMetricsApi, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchMetricsApi, "searchMetricsApi");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.searchMetricsApi = searchMetricsApi;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.contentInfoForClearSearchHistory = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_SEARCH_HISTORY));
        this.contentInfoForSearchHistoryQuery = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_SEARCH_HISTORY));
        this.contentInfoForSearchHistoryEntity = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_SEARCH_HISTORY_ENTITY));
        this.contentInfoForSearchSuggestion = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_INLINE_SEARCH_SUGGESTIONS));
        this.contentInfoForInitiateSearch = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_SEARCH_TOGGLE));
        this.contentInfoForCancelSearch = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_SEARCH_TOGGLE));
        this.contentInfoForOpenFilterMenu = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_SEARCH_TOGGLE));
        this.contentInfoForCancelFilter = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_FILTER_DIALOG));
        this.contentInfoForApplyFilter = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_FILTER_DIALOG));
        this.contentInfoForClearFilter = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_FILTER_DIALOG));
        this.contentInfoForExecuteSearch = MapsKt__MapsKt.hashMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT), TuplesKt.to("contentName", CONTENT_NAME_SEARCH_TOGGLE));
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackCancelSearchButtonUiClick(SearchExperienceMode mode, Boolean isLibrary) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.CANCEL_SEARCH, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForCancelSearch), null, null, null, -1, null, null, 884, null);
        logger.debug("SPL::uiClick - cancel button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackClearSearchButtonUiClick(SearchExperienceMode mode, Boolean isLibrary) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.CANCEL_SEARCH, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForCancelSearch), null, null, null, -1, null, null, 884, null);
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.OPEN_FILTER_MENU, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForOpenFilterMenu), null, null, null, -1, null, null, 884, null);
        logger.debug("SPL::uiClick - filter button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackCancelFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.CANCEL_FILTER, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForCancelFilter), null, null, null, -1, null, null, 884, null);
        logger.debug("SPL::uiClick - filter cancel button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackApplyFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.APPLY_FILTER, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForApplyFilter), null, null, null, -1, null, null, 884, null);
        logger.debug("SPL::uiClick - filter apply button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackClearFilterButtonUiClick(SearchExperienceMode mode, Boolean isLibrary) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.CLEAR_FILTER, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForClearFilter), null, null, null, -1, null, null, 884, null);
        logger.debug("SPL::uiClick - filter clear button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackInitiateSearchUiClick(SearchExperienceMode mode, Boolean isLibrary) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.INITIATE_SEARCH, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForInitiateSearch), null, null, null, -1, null, null, 884, null);
        logger.debug("SPL::uiClick - search box button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackExecuteSearchUiClick(SearchExperienceMode mode, Boolean isLibrary, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.EXECUTE_SEARCH, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForExecuteSearch), null, null, null, -1, interactionType, null, 628, null);
        logger.debug("SPL::uiClick - search box button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackGenericUiClick(String url, String blockRef) {
        if (url == null) {
            return;
        }
        this.searchMetricsApi.trackUiClickEvent(url, blockRef);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    @Override // com.amazon.music.find.metrics.SearchMetricsService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void trackSearchQueryUiClick(com.amazon.music.find.viewmodels.SearchExperienceMode r23, java.lang.Boolean r24, java.lang.String r25, com.amazon.music.find.metrics.UiEventMetadata r26, java.lang.String r27) {
        /*
            r22 = this;
            r8 = r22
            com.amazon.music.find.providers.SearchFeaturesProvider r0 = r8.searchFeaturesProvider
            boolean r0 = r0.isOffline()
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
            com.amazon.music.find.providers.SearchFeaturesProvider r0 = r8.searchFeaturesProvider
            boolean r4 = r0.isInCarMode()
            r5 = 0
            r6 = 16
            r7 = 0
            r0 = r22
            r1 = r23
            r3 = r24
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r11 = com.amazon.music.find.metrics.SearchMetricsService.DefaultImpls.getUiPageType$default(r0, r1, r2, r3, r4, r5, r6, r7)
            if (r23 != 0) goto L24
            r0 = -1
            goto L2c
        L24:
            int[] r0 = com.amazon.music.find.metrics.SearchMetricsServiceDefault.WhenMappings.$EnumSwitchMapping$0
            int r1 = r23.ordinal()
            r0 = r0[r1]
        L2c:
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L3b
            r1 = 2
            if (r0 == r1) goto L36
            r1 = r2
            r10 = r1
            goto L40
        L36:
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_ISS
            java.util.Map<java.lang.String, java.lang.String> r1 = r8.contentInfoForSearchSuggestion
            goto L3f
        L3b:
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.SELECT_SEARCH_HISTORY
            java.util.Map<java.lang.String, java.lang.String> r1 = r8.contentInfoForSearchHistoryQuery
        L3f:
            r10 = r0
        L40:
            if (r10 == 0) goto L64
            if (r1 == 0) goto L4a
            java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r1)
            r13 = r0
            goto L4b
        L4a:
            r13 = r2
        L4b:
            com.amazon.music.find.metrics.SearchMetricsApi r9 = r8.searchMetricsApi
            r0 = r25
            java.lang.Integer r17 = r8.toIntOrNull(r0)
            r14 = 0
            r15 = 0
            r16 = 0
            r18 = 0
            r20 = 368(0x170, float:5.16E-43)
            r21 = 0
            r12 = r27
            r19 = r26
            com.amazon.music.find.metrics.SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.find.metrics.SearchMetricsServiceDefault.trackSearchQueryUiClick(com.amazon.music.find.viewmodels.SearchExperienceMode, java.lang.Boolean, java.lang.String, com.amazon.music.find.metrics.UiEventMetadata, java.lang.String):void");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackMetadataUiClickEvent(SearchExperienceMode mode, Boolean isLibrary, ActionType actionType, String blockRef, com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType entityType, EntityIdType entityIdType, String entityId, Integer entityPos, EntityType seeMoreEntityType, InteractionType interactionType, UiEventMetadata uiEventMetadata) {
        com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType entityType2;
        Map<String, String> contentInfoForLibraryContent;
        String str;
        List<? extends Map<String, String>> listListOf;
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        boolean zIsOffline = this.searchFeaturesProvider.isOffline();
        PageType uiPageType = getUiPageType(mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), seeMoreEntityType);
        int i = mode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 1) {
            entityType2 = entityType;
            contentInfoForLibraryContent = this.contentInfoForSearchHistoryQuery;
        } else if (i == 2) {
            entityType2 = entityType;
            contentInfoForLibraryContent = this.contentInfoForSearchSuggestion;
        } else if ((i == 3 || i == 4) && (Intrinsics.areEqual(isLibrary, Boolean.TRUE) || zIsOffline)) {
            entityType2 = entityType;
            contentInfoForLibraryContent = getContentInfoForLibraryContent(entityType2, zIsOffline);
        } else {
            entityType2 = entityType;
            contentInfoForLibraryContent = null;
        }
        if (contentInfoForLibraryContent != null) {
            listListOf = CollectionsKt__CollectionsJVMKt.listOf(contentInfoForLibraryContent);
            str = blockRef;
        } else {
            str = blockRef;
            listListOf = null;
        }
        boolean zIsBlockRefEmpty = isBlockRefEmpty(str);
        this.searchMetricsApi.trackUiClickEvent(actionType, uiPageType, blockRef, zIsBlockRefEmpty ? listListOf : null, zIsBlockRefEmpty ? entityType2 : null, zIsBlockRefEmpty ? entityIdType : null, zIsBlockRefEmpty ? entityId : null, zIsBlockRefEmpty ? entityPos : null, interactionType, uiEventMetadata);
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackClearSearchHistoryUiClick() {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.CLEAR_SEARCH_HISTORY, SearchMetricsService.DefaultImpls.getUiPageType$default(this, SearchExperienceMode.SEARCH_HISTORY, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), null, this.searchFeaturesProvider.isInCarMode(), null, 16, null), null, CollectionsKt__CollectionsJVMKt.listOf(this.contentInfoForClearSearchHistory), null, null, null, -1, null, null, 884, null);
        logger.debug("SPL::uiClick - clear search history");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackSeeMoreButtonUiClick(SearchExperienceMode mode, Boolean isLibrary, String blockRef, InteractionType interactionType) {
        String str;
        List listListOf;
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        PageType uiPageType$default = SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null);
        Map<String, String> contentInfoForLibrarySeeMore = getContentInfoForLibrarySeeMore(this.searchFeaturesProvider.isOffline());
        if (contentInfoForLibrarySeeMore != null) {
            listListOf = CollectionsKt__CollectionsJVMKt.listOf(contentInfoForLibrarySeeMore);
            str = blockRef;
        } else {
            str = blockRef;
            listListOf = null;
        }
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.SELECT_SEE_ALL, uiPageType$default, blockRef, isBlockRefEmpty(str) ? listListOf : null, null, null, null, -1, interactionType, null, 512, null);
        logger.debug("SPL::uiClick - see more button");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackSearchAutoCorrectOriginalUiClick(SearchExperienceMode mode, Boolean isLibrary, String blockRef) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.SEARCH_AUTO_CORRECT_ORIGINAL, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), blockRef, null, null, null, null, null, null, null, 768, null);
        logger.debug("SPL::uiClick - searchAutocorrectOriginal");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackSearchDidYouMeanUiClick(SearchExperienceMode mode, Boolean isLibrary, String blockRef) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.SEARCH_DID_YOU_MEAN, SearchMetricsService.DefaultImpls.getUiPageType$default(this, mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null), blockRef, null, null, null, null, null, null, null, 768, null);
        logger.debug("SPL::uiClick - searchDidYouMean");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackGoBackButtonUiClick(SearchExperienceMode mode, Boolean isLibrary, EntityType seeMoreEntityType) {
        SearchMetricsApi.DefaultImpls.trackUiClickEvent$default(this.searchMetricsApi, ActionType.GO_BACK, getUiPageType(mode, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), seeMoreEntityType), null, null, null, null, null, null, null, null, 768, null);
        logger.debug("SPL::uiClick - goBack");
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackGenericUiPageView(SearchExperienceMode mode, Boolean isLibrary, Long loadTimeMilliseconds, EntityType seeMoreEntityType, UiEventMetadata uiEventMetadata) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        int i = WhenMappings.$EnumSwitchMapping$0[mode.ordinal()];
        if (i == 1) {
            trackSearchHistoryUiPageView(isLibrary, loadTimeMilliseconds);
            return;
        }
        if (i == 2) {
            trackSearchSuggestionUiPageView(isLibrary, loadTimeMilliseconds, uiEventMetadata);
        } else if (i == 3) {
            trackSearchResultsUiPageView(isLibrary, loadTimeMilliseconds);
        } else {
            if (i != 4) {
                return;
            }
            trackSeeMoreUiPageView(isLibrary, loadTimeMilliseconds, seeMoreEntityType);
        }
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackFindHomeUiPageView(Boolean isLibrary, Long loadTimeMilliseconds) {
        PageType uiPageType$default = SearchMetricsService.DefaultImpls.getUiPageType$default(this, SearchExperienceMode.FIND_LANDING, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null);
        if (uiPageType$default != null) {
            SearchMetricsApi.DefaultImpls.trackUiPageViewEvent$default(this.searchMetricsApi, uiPageType$default, this.searchFeaturesProvider.getViewType(), loadTimeMilliseconds, null, 8, null);
        }
        logger.debug(Intrinsics.stringPlus("SPL::uiPageView - find home page. loadTimeMilliseconds: ", loadTimeMilliseconds));
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackSearchSuggestionUiPageView(Boolean isLibrary, Long loadTimeMilliseconds, UiEventMetadata uiEventMetadata) {
        PageType uiPageType$default = SearchMetricsService.DefaultImpls.getUiPageType$default(this, SearchExperienceMode.SEARCH_SUGGESTION, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null);
        if (uiPageType$default == null) {
            return;
        }
        this.searchMetricsApi.trackUiPageViewEvent(uiPageType$default, this.searchFeaturesProvider.getViewType(), loadTimeMilliseconds, uiEventMetadata);
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackSearchHistoryUiPageView(Boolean isLibrary, Long loadTimeMilliseconds) {
        PageType uiPageType$default = SearchMetricsService.DefaultImpls.getUiPageType$default(this, SearchExperienceMode.SEARCH_HISTORY, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null);
        if (uiPageType$default != null) {
            SearchMetricsApi.DefaultImpls.trackUiPageViewEvent$default(this.searchMetricsApi, uiPageType$default, this.searchFeaturesProvider.getViewType(), loadTimeMilliseconds, null, 8, null);
        }
        logger.debug(Intrinsics.stringPlus("SPL::uiPageView - search history page. loadTimeMilliseconds: ", loadTimeMilliseconds));
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackSearchResultsUiPageView(Boolean isLibrary, Long loadTimeMilliseconds) {
        PageType uiPageType$default = SearchMetricsService.DefaultImpls.getUiPageType$default(this, SearchExperienceMode.SEARCH_RESULT, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), null, 16, null);
        if (uiPageType$default != null) {
            SearchMetricsApi.DefaultImpls.trackUiPageViewEvent$default(this.searchMetricsApi, uiPageType$default, this.searchFeaturesProvider.getViewType(), loadTimeMilliseconds, null, 8, null);
        }
        logger.debug(Intrinsics.stringPlus("SPL::uiPageView - search results page. loadTimeMilliseconds: ", loadTimeMilliseconds));
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackSeeMoreUiPageView(Boolean isLibrary, Long loadTimeMilliseconds, EntityType seeMoreEntityType) {
        PageType uiPageType = getUiPageType(SearchExperienceMode.SEE_ALL, Boolean.valueOf(this.searchFeaturesProvider.isOffline()), isLibrary, this.searchFeaturesProvider.isInCarMode(), seeMoreEntityType);
        if (uiPageType != null) {
            SearchMetricsApi.DefaultImpls.trackUiPageViewEvent$default(this.searchMetricsApi, uiPageType, this.searchFeaturesProvider.getViewType(), loadTimeMilliseconds, null, 8, null);
        }
        logger.debug("SPL::uiPageView - see more page for entity " + seeMoreEntityType + ". loadTimeMilliseconds: " + loadTimeMilliseconds);
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void trackTopHitsSearchUpsellNW() {
        this.searchMetricsApi.trackFlexEvent(TOP_HIT_SEARCH_UPSELL_NW_FLEX_EVENT_NAME);
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public void reportLatencies(SearchOperation searchOperation, PerformanceData performanceData, SearchExperienceMode mode, Boolean isOffline, Boolean isLibrary, EntityType seeMoreEntityType) {
        Intrinsics.checkNotNullParameter(searchOperation, "searchOperation");
        Intrinsics.checkNotNullParameter(performanceData, "performanceData");
        PageLoadLatencyCode pageLoadLatencyCodeForSearchOperation = getPageLoadLatencyCodeForSearchOperation(searchOperation, mode, isOffline, isLibrary, seeMoreEntityType);
        if (pageLoadLatencyCodeForSearchOperation == null) {
            return;
        }
        Map<LatencyTrackingLeg, Long> mapMapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(LatencyTrackingLeg.REQUEST, Long.valueOf(performanceData.getRequestTimestamp())), TuplesKt.to(LatencyTrackingLeg.RESPONSE, Long.valueOf(performanceData.getResponseTimestamp())), TuplesKt.to(LatencyTrackingLeg.PROCESSING, Long.valueOf(performanceData.getProcessingTimestamp())), TuplesKt.to(LatencyTrackingLeg.RENDERING, Long.valueOf(performanceData.getRenderingTimestamp())), TuplesKt.to(LatencyTrackingLeg.LOAD_COMPLETE, Long.valueOf(performanceData.getLoadCompleteTimestamp())));
        logger.debug("SPL::reportPageLatencies. pageLoadLatencyCode=" + pageLoadLatencyCodeForSearchOperation + ". performanceData=" + performanceData);
        this.searchMetricsApi.reportPageLatencies(pageLoadLatencyCodeForSearchOperation, mapMapOf);
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsService
    public PageType getUiPageType(SearchExperienceMode mode, Boolean isOffline, Boolean isLibrary, boolean isCarMode, EntityType seeMoreEntityType) {
        if (isCarMode) {
            return PageType.CAR_MODE_BROWSE_SEARCH;
        }
        switch (mode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[mode.ordinal()]) {
            case 1:
                return PageType.BROWSE_SEARCH_HISTORY;
            case 2:
                return PageType.BROWSE_SEARCH_SUGGESTIONS;
            case 3:
                Boolean bool = Boolean.TRUE;
                return Intrinsics.areEqual(isOffline, bool) ? PageType.OFFLINE_LIBRARY_SEARCH : Intrinsics.areEqual(isLibrary, bool) ? PageType.SEARCH_TAB_LIBRARY : PageType.BROWSE_SEARCH;
            case 4:
                switch (seeMoreEntityType != null ? WhenMappings.$EnumSwitchMapping$1[seeMoreEntityType.ordinal()] : -1) {
                    case 1:
                        Boolean bool2 = Boolean.TRUE;
                        return Intrinsics.areEqual(isOffline, bool2) ? PageType.SEE_ALL_SEARCH_ARTISTS : Intrinsics.areEqual(isLibrary, bool2) ? PageType.SEE_ALL_SEARCH_LIBRARY_ARTISTS : PageType.SEE_ALL_SEARCH_BROWSE_ARTISTS;
                    case 2:
                        Boolean bool3 = Boolean.TRUE;
                        return Intrinsics.areEqual(isOffline, bool3) ? PageType.SEE_ALL_SEARCH_ALBUMS : Intrinsics.areEqual(isLibrary, bool3) ? PageType.SEE_ALL_SEARCH_LIBRARY_ALBUMS : PageType.SEE_ALL_SEARCH_BROWSE_ALBUMS;
                    case 3:
                        Boolean bool4 = Boolean.TRUE;
                        return Intrinsics.areEqual(isOffline, bool4) ? PageType.SEE_ALL_SEARCH_TRACKS : Intrinsics.areEqual(isLibrary, bool4) ? PageType.SEE_ALL_SEARCH_LIBRARY_TRACKS : PageType.SEE_ALL_SEARCH_BROWSE_TRACKS;
                    case 4:
                        Boolean bool5 = Boolean.TRUE;
                        return Intrinsics.areEqual(isOffline, bool5) ? PageType.SEE_ALL_SEARCH_PLAYLISTS : Intrinsics.areEqual(isLibrary, bool5) ? PageType.SEE_ALL_SEARCH_LIBRARY_PLAYLISTS : PageType.SEE_ALL_SEARCH_BROWSE_PLAYLISTS;
                    case 5:
                        Boolean bool6 = Boolean.TRUE;
                        return Intrinsics.areEqual(isOffline, bool6) ? PageType.SEE_ALL_SEARCH_PLAYLISTS : Intrinsics.areEqual(isLibrary, bool6) ? PageType.SEE_ALL_SEARCH_LIBRARY_PLAYLISTS : PageType.SEE_ALL_SEARCH_BROWSE_COMMUNITY_PLAYLISTS;
                    case 6:
                        return PageType.SEE_ALL_SEARCH_STATIONS;
                    case 7:
                        return PageType.SEE_ALL_SEARCH_VIDEOS;
                    case 8:
                        return PageType.SEE_ALL_SEARCH_VIDEO_PLAYLISTS;
                    case 9:
                        return PageType.SEE_ALL_SEARCH_PODCAST_SHOWS;
                    case 10:
                        return PageType.SEE_ALL_SEARCH_PODCAST_EPISODES;
                    default:
                        return PageType.BROWSE_FIND;
                }
            case 5:
                return PageType.BROWSE_FIND;
            case 6:
                return PageType.SEARCH_FILTER;
            default:
                logger.warn("current search experience " + mode + " not matched with any metrics page type");
                return PageType.BROWSE_FIND;
        }
    }

    private final Integer toIntOrNull(String value) {
        if (value == null) {
            return null;
        }
        return StringsKt__StringNumberConversionsKt.toIntOrNull(value);
    }

    private final PageLoadLatencyCode getPageLoadLatencyCodeForSearchOperation(SearchOperation searchOperation, SearchExperienceMode mode, Boolean isOffline, Boolean isLibrary, EntityType seeMoreEntityType) {
        switch (WhenMappings.$EnumSwitchMapping$2[searchOperation.ordinal()]) {
            case 1:
                return PageLoadLatencyCode.SEARCH_SUGGESTION;
            case 2:
                return PageLoadLatencyCode.SEARCH_HISTORY;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                Boolean bool = Boolean.TRUE;
                return Intrinsics.areEqual(isOffline, bool) ? PageLoadLatencyCode.OFFLINE_SEARCH_RESULTS : Intrinsics.areEqual(isLibrary, bool) ? PageLoadLatencyCode.MY_MUSIC_SEARCH_RESULTS : PageLoadLatencyCode.SEARCH_RESULTS;
            default:
                return null;
        }
    }

    private final Map<String, String> getContentInfoForLibraryContent(com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType entityType, boolean isOffline) {
        String contentNameForLibraryEntityType;
        Map mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT));
        if (entityType != null && (contentNameForLibraryEntityType = getContentNameForLibraryEntityType(entityType, isOffline)) != null) {
        }
        return MapsKt__MapsKt.toMap(mapMutableMapOf);
    }

    private final String getContentNameForLibraryEntityType(com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType entityType, boolean isOffline) {
        String str;
        String str2 = isOffline ? CONTENT_NAME_BASE_OFFLINE_LIBRARY_SEARCH : CONTENT_NAME_BASE_ONLINE_LIBRARY_SEARCH;
        switch (WhenMappings.$EnumSwitchMapping$3[entityType.ordinal()]) {
            case 1:
                str = CONTENT_NAME_SUFFIX_ALBUMS;
                break;
            case 2:
                str = CONTENT_NAME_SUFFIX_ARTISTS;
                break;
            case 3:
            case 4:
            case 5:
                str = CONTENT_NAME_SUFFIX_PLAYLISTS;
                break;
            case 6:
                str = CONTENT_NAME_SUFFIX_TRACKS;
                break;
            default:
                str = "";
                break;
        }
        return Intrinsics.stringPlus(str2, str);
    }

    private final Map<String, String> getContentInfoForLibrarySeeMore(boolean isOffline) {
        Pair[] pairArr = new Pair[2];
        pairArr[0] = TuplesKt.to(ContextMappingConstants.CONTENT_SRC, CONTENT_SRC_CLIENT);
        pairArr[1] = TuplesKt.to("contentName", isOffline ? CONTENT_NAME_BASE_OFFLINE_LIBRARY_SEARCH : CONTENT_NAME_BASE_ONLINE_LIBRARY_SEARCH);
        return MapsKt__MapsKt.hashMapOf(pairArr);
    }

    private final boolean isBlockRefEmpty(String blockRef) {
        return StringUtils.isEmpty(blockRef);
    }
}
