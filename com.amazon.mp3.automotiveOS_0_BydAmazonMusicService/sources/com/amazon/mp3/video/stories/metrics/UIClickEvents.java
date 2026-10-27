package com.amazon.mp3.video.stories.metrics;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.platform.metrics.event.UIClickWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIMetricsEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/UIClickEvents;", "", "()V", "sendUIClickMetrics", "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "pageType", "", "pageSubType", ContextMappingConstants.ENTITY_ID, PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ContextMappingConstants.ENTITY_ID_TYPE, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UIClickEvents {
    public static final UIClickEvents INSTANCE = new UIClickEvents();

    private UIClickEvents() {
    }

    public final void sendUIClickMetrics(ActionType actionType, InteractionType interactionType, String pageType, String pageSubType, String entityId, String entityType, String entityIdType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(pageSubType, "pageSubType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        UIClickWrapper.Builder builderWithActionType = new UIClickWrapper.Builder().withActionType(actionType.toString());
        builderWithActionType.withInteractionType(interactionType.name());
        builderWithActionType.withPageType(pageType);
        builderWithActionType.withPageSubType(pageSubType);
        builderWithActionType.withEntityId(entityId);
        builderWithActionType.withEntityIdType(entityIdType);
        builderWithActionType.withEntityType(entityType);
        UIMetricsEvent.INSTANCE.sendMetricsEvent(builderWithActionType.build());
    }
}
