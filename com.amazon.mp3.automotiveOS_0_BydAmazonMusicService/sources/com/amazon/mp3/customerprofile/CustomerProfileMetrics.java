package com.amazon.mp3.customerprofile;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;

/* JADX INFO: loaded from: classes3.dex */
public class CustomerProfileMetrics {
    private static final String PAGE_ITEM_ID_TYPE = EntityIdType.PROFILE_ID.toString();

    public static void emitUiClickMetric(final ActionType actionType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEntityType(EntityType.CUSTOMER_PROFILE).withEntityIdType(EntityIdType.PROFILE_ID).build());
    }

    public static void emitProfileStatsViewMetric(final PageType pageType) {
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).withDetailPageItemIdType(PAGE_ITEM_ID_TYPE).build());
    }
}
