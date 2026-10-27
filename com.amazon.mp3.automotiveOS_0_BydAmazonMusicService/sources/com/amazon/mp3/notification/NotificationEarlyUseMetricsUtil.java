package com.amazon.mp3.notification;

import com.amazon.mp3.notification.NotificationEarlyUseHelper;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationEarlyUseMetricsUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/notification/NotificationEarlyUseMetricsUtil;", "", "Lcom/amazon/mp3/notification/NotificationEarlyUseMetricsUtil$EventType;", "eventType", "", "trackEvent", "Lcom/amazon/mp3/notification/NotificationEarlyUseHelper$CustomerDropFrom;", "customerDropFrom", "<init>", "()V", "EventType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NotificationEarlyUseMetricsUtil {
    public static final NotificationEarlyUseMetricsUtil INSTANCE = new NotificationEarlyUseMetricsUtil();

    /* JADX INFO: compiled from: NotificationEarlyUseMetricsUtil.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NotificationEarlyUseHelper.CustomerDropFrom.values().length];
            iArr[NotificationEarlyUseHelper.CustomerDropFrom.FTU.ordinal()] = 1;
            iArr[NotificationEarlyUseHelper.CustomerDropFrom.ATC.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private NotificationEarlyUseMetricsUtil() {
    }

    public final void trackEvent(EventType eventType) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        FlexEvent flexEventBuild = FlexEvent.builder("earlyUseExperience").withFlexStr1(eventType.getValue()).build();
        Intrinsics.checkNotNullExpressionValue(flexEventBuild, "builder(EARLY_USE_EXPERI…\n                .build()");
        MetricsHolder.getManager().handleEvent(flexEventBuild);
    }

    public final void trackEvent(NotificationEarlyUseHelper.CustomerDropFrom customerDropFrom) {
        Intrinsics.checkNotNullParameter(customerDropFrom, "customerDropFrom");
        int i = WhenMappings.$EnumSwitchMapping$0[customerDropFrom.ordinal()];
        if (i == 1) {
            trackEvent(EventType.FTU_ACTIVITY);
        } else {
            if (i != 2) {
                return;
            }
            trackEvent(EventType.ATC_ACTIVITY);
        }
    }

    /* JADX INFO: compiled from: NotificationEarlyUseMetricsUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/notification/NotificationEarlyUseMetricsUtil$EventType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ATC_ACTIVITY", "FTU_ACTIVITY", "NOTIFICATION_SENT", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum EventType {
        ATC_ACTIVITY("atcActivity"),
        FTU_ACTIVITY("ftuActivity"),
        NOTIFICATION_SENT("notificationSent");

        private final String value;

        EventType(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
