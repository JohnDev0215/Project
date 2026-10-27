package com.amazon.mp3.auto;

import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VoiceUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J2\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/auto/VoiceUtil;", "", "()V", "getControlSource", "Lcom/amazon/music/media/playback/ControlSource;", "isVoiceInitiated", "", "sendClickEvent", "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "experienceMode", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "blockRef", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VoiceUtil {
    public static final VoiceUtil INSTANCE = new VoiceUtil();

    private VoiceUtil() {
    }

    public final void sendClickEvent(ActionType actionType, PageType pageType, ExperienceMode experienceMode, String blockRef, boolean isVoiceInitiated) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        if (isVoiceInitiated) {
            return;
        }
        AutoUiEventUtil.sendUiClickEvent(actionType, pageType, experienceMode, blockRef);
    }

    public final ControlSource getControlSource(boolean isVoiceInitiated) {
        if (isVoiceInitiated) {
            ControlSource controlSource = ControlSource.ALEXA;
            Intrinsics.checkNotNullExpressionValue(controlSource, "{\n            ControlSource.ALEXA\n        }");
            return controlSource;
        }
        ControlSource controlSource2 = ControlSource.APP_UI;
        Intrinsics.checkNotNullExpressionValue(controlSource2, "{\n            ControlSource.APP_UI\n        }");
        return controlSource2;
    }
}
