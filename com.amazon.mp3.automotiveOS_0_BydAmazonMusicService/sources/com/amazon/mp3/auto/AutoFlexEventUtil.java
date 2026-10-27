package com.amazon.mp3.auto;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoFlexEventUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\t\b\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J&\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002J \u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\rJ\u001e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0002J\u0016\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nJ&\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/auto/AutoFlexEventUtil;", "", "", "error", "", "emitCarModePageErrorFlexEvent", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, ContextMappingConstants.ENTITY_ID, "widgetId", "emitCarModePlaybackFailureFlexEvent", "", "match", "playbackInstanceId", "Lcom/amazon/mp3/auto/AutoFlexEventUtil$CarModeQueueMatchContext;", "context", "emitCarModePresetMatchFlexEvent", "isAppForeground", "autoLaunchSetting", "showCarModeButtonSetting", "emitCarModeAutoLaunchFlexEvent", "isAutoLaunchEnabled", "emitCarModeAutoLaunchSettingFlexEvent", "emitAutomotivePlaybackFailureFlexEvent", "<init>", "()V", "CarModeQueueMatchContext", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutoFlexEventUtil {
    public static final AutoFlexEventUtil INSTANCE = new AutoFlexEventUtil();

    private AutoFlexEventUtil() {
    }

    /* JADX INFO: compiled from: AutoFlexEventUtil.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/auto/AutoFlexEventUtil$CarModeQueueMatchContext;", "", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "Ingress", "Other", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum CarModeQueueMatchContext {
        Ingress("ingress"),
        Other("other");

        private final String type;

        CarModeQueueMatchContext(String str) {
            this.type = str;
        }

        public final String getType() {
            return this.type;
        }
    }

    public final void emitCarModePageErrorFlexEvent(String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("carModePageError").withHcFlexStr1(error).build());
    }

    public final void emitCarModePlaybackFailureFlexEvent(String entityType, String entityId, String widgetId, String error) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(widgetId, "widgetId");
        Intrinsics.checkNotNullParameter(error, "error");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("carModePlaybackFailure").withHcFlexStr1(entityType).withHcFlexStr2(entityId).withHcFlexStr3(widgetId).withHcFlexStr4(error).build());
    }

    public static /* synthetic */ void emitCarModePresetMatchFlexEvent$default(AutoFlexEventUtil autoFlexEventUtil, boolean z, String str, CarModeQueueMatchContext carModeQueueMatchContext, int i, Object obj) {
        if ((i & 4) != 0) {
            carModeQueueMatchContext = CarModeQueueMatchContext.Other;
        }
        autoFlexEventUtil.emitCarModePresetMatchFlexEvent(z, str, carModeQueueMatchContext);
    }

    public final void emitCarModePresetMatchFlexEvent(boolean match, String playbackInstanceId, CarModeQueueMatchContext context) {
        Intrinsics.checkNotNullParameter(playbackInstanceId, "playbackInstanceId");
        Intrinsics.checkNotNullParameter(context, "context");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("carModeQueuePresetMatch").withHcFlexStr1(String.valueOf(match)).withHcFlexStr2(playbackInstanceId).withHcFlexStr3(context.getType()).build());
    }

    public final void emitCarModeAutoLaunchFlexEvent(boolean isAppForeground, boolean autoLaunchSetting, String showCarModeButtonSetting) {
        Intrinsics.checkNotNullParameter(showCarModeButtonSetting, "showCarModeButtonSetting");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("carModeAutoLaunch").withHcFlexStr1(String.valueOf(isAppForeground)).withHcFlexStr2(String.valueOf(autoLaunchSetting)).withHcFlexStr3(showCarModeButtonSetting).build());
    }

    public final void emitCarModeAutoLaunchSettingFlexEvent(boolean isAutoLaunchEnabled, boolean isAppForeground) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("carModeAutoLaunchSetting").withHcFlexStr1(String.valueOf(isAutoLaunchEnabled)).withHcFlexStr2(String.valueOf(isAppForeground)).build());
    }

    public final void emitAutomotivePlaybackFailureFlexEvent(String entityType, String entityId, String widgetId, String error) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(widgetId, "widgetId");
        Intrinsics.checkNotNullParameter(error, "error");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("AutomotivePlaybackFailure").withHcFlexStr1(entityType).withHcFlexStr2(entityId).withHcFlexStr3(widgetId).withHcFlexStr4(error).build());
    }
}
