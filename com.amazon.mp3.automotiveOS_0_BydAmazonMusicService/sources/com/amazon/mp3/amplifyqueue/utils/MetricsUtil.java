package com.amazon.mp3.amplifyqueue.utils;

import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.MetricsProvider;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MetricsUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001 B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\n\u0010\u0006\u001a\u00060\u0004j\u0002`\u0005JF\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000eR$\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/MetricsUtil;", "", "Lcom/amazon/mp3/amplifyqueue/utils/MetricsUtil$EventType;", "eventType", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "", "trackEvent", "", "eventName", ParserUtil.TAG_QUERY_PARAM_NAME, "message", "extraMessage", "", "isSuccess", "isOffline", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "getMetricsProvider", "()Lcom/amazon/music/platform/providers/MetricsProvider;", "setMetricsProvider", "(Lcom/amazon/music/platform/providers/MetricsProvider;)V", "serviceTier", "Ljava/lang/String;", "getServiceTier", "()Ljava/lang/String;", "setServiceTier", "(Ljava/lang/String;)V", "<init>", "()V", "EventType", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0})
public final class MetricsUtil {
    public static final MetricsUtil INSTANCE = new MetricsUtil();
    private static MetricsProvider metricsProvider;
    private static String serviceTier;

    private MetricsUtil() {
    }

    public final void setMetricsProvider(MetricsProvider metricsProvider2) {
        metricsProvider = metricsProvider2;
    }

    public final String getServiceTier() {
        return serviceTier;
    }

    public final void setServiceTier(String str) {
        serviceTier = str;
    }

    public final void trackEvent(EventType eventType, Exception e) {
        Unit unit;
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        Intrinsics.checkNotNullParameter(e, "e");
        if (!(e instanceof NoSuchElementException)) {
            MetricsProvider metricsProvider2 = metricsProvider;
            if (metricsProvider2 == null) {
                unit = null;
            } else {
                FlexWrapper.Builder builderWithStr2 = new FlexWrapper.Builder().withName("cloudQueueFailure").withStr1(eventType.getValue()).withStr2(e.getMessage());
                Throwable cause = e.getCause();
                metricsProvider2.sendMetricsEvent(builderWithStr2.withStr3(cause == null ? null : cause.getMessage()).withStr4(INSTANCE.getServiceTier()).build());
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                FlexEvent.Builder builderWithFlexStr2 = FlexEvent.builder("cloudQueueFailure").withFlexStr1(eventType.getValue()).withFlexStr2(e.getMessage());
                Throwable cause2 = e.getCause();
                FlexEvent flexEventBuild = builderWithFlexStr2.withFlexStr3(cause2 != null ? cause2.getMessage() : null).withFlexStr4(getServiceTier()).build();
                Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(CQ_EVENT_FAILURE…                 .build()");
                MetricsHolder.getManager().handleEvent(flexEventBuild);
            }
            Log.e("AmplifyClient", Intrinsics.stringPlus(eventType.getValue(), " failed"), e);
            return;
        }
        Log.w("AmplifyClient", Intrinsics.stringPlus(eventType.getValue(), " failed with NoSuchElementException"));
    }

    public final void trackEvent(String eventName, String tag, String message, String extraMessage, boolean isSuccess, boolean isOffline) {
        Unit unit;
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        MetricsProvider metricsProvider2 = metricsProvider;
        if (metricsProvider2 == null) {
            unit = null;
        } else {
            metricsProvider2.sendMetricsEvent(new FlexWrapper.Builder().withName(eventName).withStr1(tag).withStr2(message).withStr3(extraMessage).withStr4(INSTANCE.getServiceTier()).withNum1(Float.valueOf(isSuccess ? 1.0f : 0.0f)).withNum2(Float.valueOf(isOffline ? 1.0f : 0.0f)).build());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            FlexEvent flexEventBuild = FlexEvent.builder(eventName).withFlexStr1(tag).withFlexStr2(message).withFlexStr3(extraMessage).withFlexStr4(getServiceTier()).withFlexNum1(Float.valueOf(isSuccess ? 1.0f : 0.0f)).withFlexNum2(Float.valueOf(isOffline ? 1.0f : 0.0f)).build();
            Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(eventName)\n     …                 .build()");
            MetricsHolder.getManager().handleEvent(flexEventBuild);
        }
    }

    /* JADX INFO: compiled from: MetricsUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001c¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/MetricsUtil$EventType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "EVENT_DEFAULT", "EVENT_GET_DEVICE_PLAYBACK_STATE", "EVENT_GET_QUEUE_BY_ID", "EVENT_GET_QUEUE_ENTITY_BY_ID", "EVENT_GET_QUEUE_SEQUENCE_SLICE_BY_ID", "EVENT_GET_QUEUE_VIEW", "EVENT_INITIATE_QUEUE", "EVENT_GET_NEXT_TRACKS", "EVENT_CREATE_NEW_QUEUE", "EVENT_CREATE_NEW_QUEUE_ENTITY", "EVENT_CREATE_NEW_QUEUE_SEQUENCE_SLICE", "EVENT_DELETE_QUEUE_BY_ID", "EVENT_DELETE_QUEUE_SEQUENCE_SLICE_BY_ID", "EVENT_CHANGE_DEVICE_STATE", "EVENT_CHANGE_QUEUE_SEQUENCE_SLICE", "EVENT_CONNECT", "EVENT_REGISTER_DEVICE_PLAYBACK_STATE_LISTENER", "EVENT_GET_ACTIVE_DEVICES_PLAYBACK_VIEW", "EVENT_START_CLOUD_QUEUE", "EVENT_AUTH", "EVENT_HANDLER", "EVENT_GET_METRICS_CONTEXT_BY_ID", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum EventType {
        EVENT_DEFAULT("default"),
        EVENT_GET_DEVICE_PLAYBACK_STATE("getDevicePlaybackState"),
        EVENT_GET_QUEUE_BY_ID("getQueueById"),
        EVENT_GET_QUEUE_ENTITY_BY_ID("getQueueEntityById"),
        EVENT_GET_QUEUE_SEQUENCE_SLICE_BY_ID("getQueueSequenceSliceById"),
        EVENT_GET_QUEUE_VIEW("getQueueView"),
        EVENT_INITIATE_QUEUE("initiateQueue"),
        EVENT_GET_NEXT_TRACKS("getNextTracks"),
        EVENT_CREATE_NEW_QUEUE("createNewQueue"),
        EVENT_CREATE_NEW_QUEUE_ENTITY("createNewQueueEntity"),
        EVENT_CREATE_NEW_QUEUE_SEQUENCE_SLICE("createNewQueueSequenceSlice"),
        EVENT_DELETE_QUEUE_BY_ID("deleteQueueById"),
        EVENT_DELETE_QUEUE_SEQUENCE_SLICE_BY_ID("deleteQueueSequenceSliceById"),
        EVENT_CHANGE_DEVICE_STATE("changeDeviceState"),
        EVENT_CHANGE_QUEUE_SEQUENCE_SLICE("changeQueueSequenceSlice"),
        EVENT_CONNECT("connect"),
        EVENT_REGISTER_DEVICE_PLAYBACK_STATE_LISTENER("registerDevicePlaybackStateListener"),
        EVENT_GET_ACTIVE_DEVICES_PLAYBACK_VIEW("getActiveDevicesPlaybackView"),
        EVENT_START_CLOUD_QUEUE("startCloudQueue"),
        EVENT_AUTH("auth"),
        EVENT_HANDLER("handler"),
        EVENT_GET_METRICS_CONTEXT_BY_ID("getMetricsContextById");

        private final String value;

        EventType(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
