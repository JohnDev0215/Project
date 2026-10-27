package com.amazon.mp3.auto;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoUiEventUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J0\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u001c\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/auto/AutoUiEventUtil;", "", "()V", "sendUiClickEvent", "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "experienceMode", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "blockRef", "", "sendUiPageViewEvent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutoUiEventUtil {
    public static final AutoUiEventUtil INSTANCE = new AutoUiEventUtil();

    private AutoUiEventUtil() {
    }

    public static /* synthetic */ void sendUiClickEvent$default(ActionType actionType, PageType pageType, ExperienceMode experienceMode, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            experienceMode = null;
        }
        if ((i & 8) != 0) {
            str = null;
        }
        sendUiClickEvent(actionType, pageType, experienceMode, str);
    }

    @JvmStatic
    public static final void sendUiClickEvent(ActionType actionType, PageType pageType, ExperienceMode experienceMode, String blockRef) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        UiClickEvent.Builder builderWithExperienceMode = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(pageType).withExperienceMode(experienceMode == null ? null : experienceMode.name());
        if (blockRef != null) {
            builderWithExperienceMode.withBlockRef(blockRef);
        }
        MetricsHolder.getManager().handleEvent(builderWithExperienceMode.build());
    }

    @JvmStatic
    public static final void sendUiClickEvent(ActionType actionType, PageType pageType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        sendUiClickEvent(actionType, pageType, null, null);
    }

    @JvmStatic
    public static final void sendUiPageViewEvent(PageType pageType, ExperienceMode experienceMode) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).withExperienceMode(experienceMode == null ? null : experienceMode.name()).build());
    }
}
