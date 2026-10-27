package com.amazon.mp3.playback.service.audioeffects;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EqualizerMetrics.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/playback/service/audioeffects/EqualizerMetrics;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EqualizerMetrics {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: EqualizerMetrics.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/playback/service/audioeffects/EqualizerMetrics$Companion;", "", "()V", "emitEqualizerUIPageView", "", "emitUiClickEQAdjust", "bandIndex", "", "emitUiClickEQPageClose", "emitUiClickEQToggleOff", "emitUiClickEQToggleOn", "emitUiClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "emitUiClickPreset", "name", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void emitUiClickEQAdjust(short bandIndex) {
        }

        public final void emitUiClickPreset(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
        }

        private Companion() {
        }

        public final void emitEqualizerUIPageView() {
            MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(PageType.EQUALIZER.getMetricsValue()).build());
        }

        public final void emitUiClickEQToggleOn() {
            emitUiClickEvent(ActionType.EQ_ON);
        }

        public final void emitUiClickEQToggleOff() {
            emitUiClickEvent(ActionType.EQ_OFF);
        }

        public final void emitUiClickEQPageClose() {
            emitUiClickEvent(ActionType.CLOSE_EQ_PAGE);
        }

        private final void emitUiClickEvent(ActionType actionType) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).build());
        }
    }
}
