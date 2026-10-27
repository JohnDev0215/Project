package com.amazon.mp3.stability;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.event.EventHandler;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.platform.providers.StabilityManagerProvider;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StabilityEventHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003j\u0002`\u00040\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/stability/StabilityEventHandler;", "Lcom/amazon/music/metrics/event/EventHandler;", "Lcom/amazon/music/metrics/mts/MTSEvent;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "stabilityManager", "Lcom/amazon/music/platform/providers/StabilityManagerProvider;", "(Lcom/amazon/music/platform/providers/StabilityManagerProvider;)V", "handleEvent", "", NotificationCompat.CATEGORY_EVENT, "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StabilityEventHandler extends EventHandler<MTSEvent, Exception> {
    private static final String TAG = StabilityEventHandler.class.getSimpleName();
    private final StabilityManagerProvider stabilityManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StabilityEventHandler(StabilityManagerProvider stabilityManager) {
        super(MTSEvent.class);
        Intrinsics.checkNotNullParameter(stabilityManager, "stabilityManager");
        this.stabilityManager = stabilityManager;
    }

    @Override // com.amazon.music.metrics.event.EventHandler
    public void handleEvent(MTSEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (AmazonApplication.getCapabilities().isBugsnagBreadcrumbsEnabled() && StabilityBreadcrumbFilters.INSTANCE.getMETRIC_ALLOW_LIST().contains(event.getEventName())) {
            Log.verbose(TAG, "handled emitting metric event=%s as breadcrumb", event);
            StabilityManagerProvider stabilityManagerProvider = this.stabilityManager;
            String eventName = event.getEventName();
            Intrinsics.checkNotNullExpressionValue(eventName, "event.eventName");
            Map<String, String> eventAttributes = event.getEventAttributes();
            Intrinsics.checkNotNullExpressionValue(eventAttributes, "event.eventAttributes");
            stabilityManagerProvider.leaveMetricBreadcrumbs(eventName, eventAttributes);
        }
    }
}
