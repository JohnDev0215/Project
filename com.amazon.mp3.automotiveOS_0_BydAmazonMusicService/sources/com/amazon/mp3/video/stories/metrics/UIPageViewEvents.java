package com.amazon.mp3.video.stories.metrics;

import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ViewType;
import com.amazon.music.platform.metrics.event.UIPageViewWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UIMetricsEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/video/stories/metrics/UIPageViewEvents;", "", "()V", "sendPageViewMetrics", "", "blockRef", "", "pageType", "detailPageItemId", "detailPageItemIdType", "associateTag", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UIPageViewEvents {
    public static final UIPageViewEvents INSTANCE = new UIPageViewEvents();

    private UIPageViewEvents() {
    }

    public final void sendPageViewMetrics(String blockRef, String pageType, String detailPageItemId, String detailPageItemIdType, String associateTag) {
        Intrinsics.checkNotNullParameter(blockRef, "blockRef");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        UIPageViewWrapper.Builder builderWithPageType = new UIPageViewWrapper.Builder().withPageType(pageType);
        builderWithPageType.withBlockRef(blockRef);
        if (detailPageItemId != null) {
            builderWithPageType.withDetailPageItemId(detailPageItemId);
            if (detailPageItemIdType == null) {
                detailPageItemIdType = "";
            }
            builderWithPageType.withDetailPageItemIdType(detailPageItemIdType);
        }
        builderWithPageType.withPageSubType(PageSubType.UNAVAILABLE_UNAVAILABLE.name());
        builderWithPageType.withViewType(ViewType.PORTRAIT.name());
        if (associateTag != null) {
            builderWithPageType.withAssociateTag(associateTag);
        }
        UIMetricsEvent.INSTANCE.sendMetricsEvent(builderWithPageType.build());
    }
}
