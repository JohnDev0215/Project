package com.amazon.music.media.auto.util;

import com.amazon.music.media.auto.googleAssistant.GoogleAssistantError;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: GoogleAssistantMetricsUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJ \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/auto/util/GoogleAssistantMetricsUtil;", "", "Lcom/amazon/music/media/auto/googleAssistant/GoogleAssistantError;", "error", "", "errorMessage", "voiceHandler", "", "emitGoogleAssistantVoiceFailedMetric", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class GoogleAssistantMetricsUtil {
    public static final GoogleAssistantMetricsUtil INSTANCE = new GoogleAssistantMetricsUtil();

    private GoogleAssistantMetricsUtil() {
    }

    public final void emitGoogleAssistantVoiceFailedMetric(GoogleAssistantError error, String errorMessage, String voiceHandler) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(voiceHandler, "voiceHandler");
        FlexEvent.Builder builderWithHcFlexStr1 = FlexEvent.builder("thirdPartyVoiceError").withHcFlexStr1(error.toString());
        if (errorMessage != null) {
            builderWithHcFlexStr1.withHcFlexStr2(StringsKt___StringsKt.take(errorMessage, 128));
        }
        builderWithHcFlexStr1.withHcFlexStr3(voiceHandler);
        MetricsHolder.getManager().handleEvent(builderWithHcFlexStr1.build());
    }
}
