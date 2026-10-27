package com.amazon.music.find.metrics;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchMetricsApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u008f\u0001\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\f2\u001c\b\u0002\u0010\u0013\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0007\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001fH&¢\u0006\u0002\u0010 J\u001a\u0010\r\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\fH&J7\u0010\"\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001fH&¢\u0006\u0002\u0010&¨\u0006'"}, d2 = {"Lcom/amazon/music/find/metrics/SearchMetricsApi;", "", "reportPageLatencies", "", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "latencies", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "", "trackFlexEvent", "name", "", "trackUiClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "blockRef", ContextMappingConstants.CONTENT_INFO, "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "entityPos", "", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "uiEventMetadata", "Lcom/amazon/music/find/metrics/UiEventMetadata;", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", ImagesContract.URL, "trackUiPageViewEvent", "viewType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "loadTimeMilliseconds", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;Ljava/lang/Long;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchMetricsApi {
    void reportPageLatencies(PageLoadLatencyCode pageLoadLatencyCode, Map<LatencyTrackingLeg, Long> latencies);

    void trackFlexEvent(String name);

    void trackUiClickEvent(ActionType actionType, PageType pageType, String blockRef, List<? extends Map<String, String>> contentInfo, EntityType entityType, EntityIdType entityIdType, String entityId, Integer entityPos, InteractionType interactionType, UiEventMetadata uiEventMetadata);

    void trackUiClickEvent(String url, String blockRef);

    void trackUiPageViewEvent(PageType pageType, ViewType viewType, Long loadTimeMilliseconds, UiEventMetadata uiEventMetadata);

    /* JADX INFO: compiled from: SearchMetricsApi.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void trackUiPageViewEvent$default(SearchMetricsApi searchMetricsApi, PageType pageType, ViewType viewType, Long l, UiEventMetadata uiEventMetadata, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackUiPageViewEvent");
            }
            if ((i & 4) != 0) {
                l = null;
            }
            if ((i & 8) != 0) {
                uiEventMetadata = null;
            }
            searchMetricsApi.trackUiPageViewEvent(pageType, viewType, l, uiEventMetadata);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void trackUiClickEvent$default(SearchMetricsApi searchMetricsApi, ActionType actionType, PageType pageType, String str, List list, EntityType entityType, EntityIdType entityIdType, String str2, Integer num, InteractionType interactionType, UiEventMetadata uiEventMetadata, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackUiClickEvent");
            }
            searchMetricsApi.trackUiClickEvent(actionType, pageType, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : entityType, (i & 32) != 0 ? null : entityIdType, (i & 64) != 0 ? null : str2, (i & 128) != 0 ? null : num, (i & 256) != 0 ? InteractionType.TAP : interactionType, (i & 512) != 0 ? null : uiEventMetadata);
        }
    }
}
