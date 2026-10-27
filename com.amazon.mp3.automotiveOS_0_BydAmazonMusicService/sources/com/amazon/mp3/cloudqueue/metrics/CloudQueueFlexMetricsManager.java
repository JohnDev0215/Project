package com.amazon.mp3.cloudqueue.metrics;

import androidx.core.app.NotificationCompat;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.mp3.cloudqueue.CloudQueueDigitalXPUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CloudQueueFlexMetricsManager.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JH\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0007J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014JH\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0007R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/cloudqueue/metrics/CloudQueueFlexMetricsManager;", "", "()V", "serviceTier", "", "getServiceTier", "()Ljava/lang/String;", "serviceTier$delegate", "Lkotlin/Lazy;", "trackEvent", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/mp3/cloudqueue/metrics/CloudQueueFlexMetricsManager$Event;", ParserUtil.TAG_QUERY_PARAM_NAME, "message", "extraMessage", "isSuccess", "", "isOffline", "flexEvent", "Lcom/amazon/music/metrics/mts/event/definition/flex/FlexEvent;", "trackEventV2", "Event", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CloudQueueFlexMetricsManager {
    public static final CloudQueueFlexMetricsManager INSTANCE = new CloudQueueFlexMetricsManager();

    /* JADX INFO: renamed from: serviceTier$delegate, reason: from kotlin metadata */
    private static final Lazy serviceTier = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager$serviceTier$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            ServiceTierEnum serviceTier2 = CloudQueueDigitalXPUtil.INSTANCE.getServiceTier();
            if (serviceTier2 == null) {
                return null;
            }
            return serviceTier2.name();
        }
    });

    private CloudQueueFlexMetricsManager() {
    }

    private final String getServiceTier() {
        return (String) serviceTier.getValue();
    }

    public static /* synthetic */ void trackEvent$default(Event event, String str, String str2, String str3, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        if ((i & 16) != 0) {
            z = true;
        }
        if ((i & 32) != 0) {
            z2 = false;
        }
        trackEvent(event, str, str2, str3, z, z2);
    }

    @Deprecated(message = "Should use trackEventV2 for new metrics")
    @JvmStatic
    public static final void trackEvent(Event event, String tag, String message, String extraMessage, boolean isSuccess, boolean isOffline) {
        Intrinsics.checkNotNullParameter(event, "event");
        FlexEvent flexEventBuild = FlexEvent.builder(event.getValue()).withFlexStr1(tag).withFlexStr2(message).withFlexStr3(extraMessage).withFlexStr4(INSTANCE.getServiceTier()).withFlexNum1(Float.valueOf(isSuccess ? 1.0f : 0.0f)).withFlexNum2(Float.valueOf(isOffline ? 1.0f : 0.0f)).build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(event.value)\n   …\n                .build()");
        MetricsHolder.getManager().handleEvent(flexEventBuild);
    }

    public static /* synthetic */ void trackEventV2$default(Event event, String str, String str2, String str3, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        if ((i & 8) != 0) {
            str3 = null;
        }
        if ((i & 16) != 0) {
            z = true;
        }
        if ((i & 32) != 0) {
            z2 = false;
        }
        trackEventV2(event, str, str2, str3, z, z2);
    }

    @JvmStatic
    public static final void trackEventV2(Event event, String tag, String message, String extraMessage, boolean isSuccess, boolean isOffline) {
        Intrinsics.checkNotNullParameter(event, "event");
        FlexEvent.Builder builderWithHcFlexStr3 = FlexEvent.builder(event.getValue()).withHcFlexStr1(tag).withHcFlexStr2(message).withHcFlexStr3(extraMessage);
        CloudQueueFlexMetricsManager cloudQueueFlexMetricsManager = INSTANCE;
        FlexEvent flexEventBuild = builderWithHcFlexStr3.withHcFlexStr4(cloudQueueFlexMetricsManager.getServiceTier()).withFlexNum1(Float.valueOf(isSuccess ? 1.0f : 0.0f)).withFlexNum2(Float.valueOf(isOffline ? 1.0f : 0.0f)).build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(event.value)\n   … 0f)\n            .build()");
        cloudQueueFlexMetricsManager.trackEvent(flexEventBuild);
    }

    public final void trackEvent(FlexEvent flexEvent) {
        Intrinsics.checkNotNullParameter(flexEvent, "flexEvent");
        MetricsHolder.getManager().handleEvent(flexEvent);
    }

    /* JADX INFO: compiled from: CloudQueueFlexMetricsManager.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/cloudqueue/metrics/CloudQueueFlexMetricsManager$Event;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "FALLBACK", "INITIATE_QUEUE", "GET_NEXT_ENTITIES", "CREATE_QUEUE", "QUEUE_RESTORED", "QUEUE_CHANGED", "CASTING_INITIATE_ONLINE", "NO_OP_CQ_CLIENT_VOICE", "PLAYBACK_EXCEPTION", "DATASTORE_STARTED", "DATASTORE_STOPPED", "CQ_INTERACTION_PATTERN", "NULL_TRACK_LIST", "NO_MPQS_LEGACY_SUPPORT", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Event {
        FALLBACK("cloudQueueFallback"),
        INITIATE_QUEUE("cloudQueueInitiateQueue"),
        GET_NEXT_ENTITIES("cloudQueueGetNextEntities"),
        CREATE_QUEUE("cloudQueueCreateQueue"),
        QUEUE_RESTORED("cloudQueueRestored"),
        QUEUE_CHANGED("cloudQueueQueueChanged"),
        CASTING_INITIATE_ONLINE("castingInitiateOnline"),
        NO_OP_CQ_CLIENT_VOICE("noOpCloudQueueClientForVoice"),
        PLAYBACK_EXCEPTION("playbackException"),
        DATASTORE_STARTED("dataStoreStarted"),
        DATASTORE_STOPPED("dataStoreStopped"),
        CQ_INTERACTION_PATTERN("cloudQueueInteractionPattern"),
        NULL_TRACK_LIST("nullTrackList"),
        NO_MPQS_LEGACY_SUPPORT("noMPQSLegacyStationSupport");

        private final String value;

        Event(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
