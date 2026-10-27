package com.amazon.music.connect.feed;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;

/* JADX INFO: loaded from: classes4.dex */
public class FeedMetrics {
    private static final PageType ACTIVITY_FEED_PAGE_TYPE = PageType.ACTIVITY_FEED;

    public static void emitIconDisplayMetric(boolean z) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("activityFeed").withFlexStr1("iconDisplayed").withFlexStr2(z ? "indicatorDisplayed" : "indicatorNotDisplayed").build());
    }

    public static void emitIconClickMetric(boolean z) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_ACTIVITY_FEED).withInteractionType(InteractionType.TAP).withEntityId(z ? "indicatorDisplayed" : "indicatorNotDisplayed").build());
    }

    public static void emitItemViewMetric(String str, String str2, float f) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("activityFeedItem").withFlexStr1(str).withFlexStr2(str2).withFlexNum1(Float.valueOf(f)).build());
    }

    public static void emitItemClickMetric(String str, String str2, String str3) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_FEED_ITEM).withRefMarker(str2).withPageSubType(str3).withInteractionType(InteractionType.TAP).withEntityId(str).withEntityIdType(EntityIdType.FEED_BASE_ITEM_ID).build());
    }

    public static void emitPageViewMetric() {
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(ACTIVITY_FEED_PAGE_TYPE.getMetricsValue()).build());
    }

    public static void emitErrorMetric(String str, String str2) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("activityFeed").withFlexStr1(str).withFlexStr2(str2).build());
    }
}
