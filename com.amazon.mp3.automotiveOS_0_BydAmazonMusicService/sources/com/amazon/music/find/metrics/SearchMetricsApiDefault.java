package com.amazon.music.find.metrics;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.ActivityIdType;
import com.amazon.music.metrics.mts.event.types.ResponseIdType;
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
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchMetricsApiDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\u007f\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\r2\u001a\u0010\u0014\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\b\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0002\u0010!J\u001a\u0010\u000e\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\rH\u0016J3\u0010#\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\n2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0002\u0010'¨\u0006("}, d2 = {"Lcom/amazon/music/find/metrics/SearchMetricsApiDefault;", "Lcom/amazon/music/find/metrics/SearchMetricsApi;", "()V", "reportPageLatencies", "", "pageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "latencies", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "", "trackFlexEvent", "name", "", "trackUiClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "blockRef", ContextMappingConstants.CONTENT_INFO, "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "entityPos", "", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "uiEventMetadata", "Lcom/amazon/music/find/metrics/UiEventMetadata;", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Ljava/lang/String;Ljava/lang/Integer;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", ImagesContract.URL, "trackUiPageViewEvent", "viewType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;", "loadTimeMilliseconds", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ViewType;Ljava/lang/Long;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchMetricsApiDefault implements SearchMetricsApi {
    @Override // com.amazon.music.find.metrics.SearchMetricsApi
    public void trackUiPageViewEvent(PageType pageType, ViewType viewType, Long loadTimeMilliseconds, UiEventMetadata uiEventMetadata) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        UiPageViewEvent.Builder builderWithSearchQuery = UiPageViewEvent.builder(pageType.getMetricsValue()).withViewType(viewType).withActivityId(uiEventMetadata == null ? null : uiEventMetadata.getActivityId()).withActivityIdType(uiEventMetadata == null ? null : uiEventMetadata.getActivityIdType()).withResponseId(uiEventMetadata == null ? null : uiEventMetadata.getResponseId()).withResponseIdType(uiEventMetadata == null ? null : uiEventMetadata.getResponseIdType()).withSearchQuery(uiEventMetadata != null ? uiEventMetadata.getSearchQuery() : null);
        if (loadTimeMilliseconds != null) {
            builderWithSearchQuery.withLoadTime(loadTimeMilliseconds.longValue());
        }
        MetricsHolder.getManager().handleEvent(builderWithSearchQuery.build());
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsApi
    public void trackUiClickEvent(String url, String blockRef) {
        Intrinsics.checkNotNullParameter(url, "url");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(url, blockRef).withInteractionType(InteractionType.TAP).build());
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsApi
    public void trackUiClickEvent(ActionType actionType, PageType pageType, String blockRef, List<? extends Map<String, String>> contentInfo, EntityType entityType, EntityIdType entityIdType, String entityId, Integer entityPos, InteractionType interactionType, UiEventMetadata uiEventMetadata) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        UiClickEvent.Builder builderWithPageType = UiClickEvent.builder(actionType).withInteractionType(interactionType).withPageType(pageType);
        if (contentInfo != null) {
            builderWithPageType.withContentInfo(contentInfo);
        }
        if (blockRef != null) {
            builderWithPageType.withBlockRef(blockRef);
        }
        if (entityIdType != null) {
            builderWithPageType.withEntityIdType(entityIdType);
            builderWithPageType.withEntityId(entityId);
        }
        if (entityType != null) {
            builderWithPageType.withEntityType(entityType);
        }
        if (entityPos != null) {
            builderWithPageType.withEntityPos(entityPos.intValue());
        }
        if (uiEventMetadata != null) {
            String searchQuery = uiEventMetadata.getSearchQuery();
            if (searchQuery != null) {
                builderWithPageType.withSearchQuery(searchQuery);
            }
            String activityId = uiEventMetadata.getActivityId();
            if (activityId != null) {
                builderWithPageType.withActivityId(activityId);
            }
            ActivityIdType activityIdType = uiEventMetadata.getActivityIdType();
            if (activityIdType != null) {
                builderWithPageType.withActivityIdType(activityIdType);
            }
            String responseId = uiEventMetadata.getResponseId();
            if (responseId != null) {
                builderWithPageType.withResponseId(responseId);
            }
            ResponseIdType responseIdType = uiEventMetadata.getResponseIdType();
            if (responseIdType != null) {
                builderWithPageType.withResponseIdType(responseIdType);
            }
        }
        MetricsHolder.getManager().handleEvent(builderWithPageType.build());
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsApi
    public void trackFlexEvent(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder(name).build());
    }

    @Override // com.amazon.music.find.metrics.SearchMetricsApi
    public void reportPageLatencies(PageLoadLatencyCode pageLoadLatencyCode, Map<LatencyTrackingLeg, Long> latencies) {
        Intrinsics.checkNotNullParameter(pageLoadLatencyCode, "pageLoadLatencyCode");
        Intrinsics.checkNotNullParameter(latencies, "latencies");
        LatencyTracker.INSTANCE.track(pageLoadLatencyCode, latencies);
    }
}
