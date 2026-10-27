package com.amazon.mp3.view.stage.module;

import androidx.core.app.FrameMetricsAggregator;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import com.amazon.mp3.view.stage.StageOverflowMenuSecondaryIconStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayXrayStateProvider;
import kotlin.Metadata;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: StageModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/view/stage/module/StageModule;", "", "()V", "getLyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "getOverlayLyricsStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "getOverlayTypeStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "getOverlayXrayStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayXrayStateProvider;", "getStageAutomatedSequencingHandler", "Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "getStageOverflowMenuSecondaryIconStateProvider", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageModule {
    public final OverlayTypeStateProvider getOverlayTypeStateProvider() {
        return new OverlayTypeStateProvider(null, null, null, false, 15, null);
    }

    public final OverlayXrayStateProvider getOverlayXrayStateProvider() {
        return new OverlayXrayStateProvider(null, null, 0, 0, null, null, null, WorkQueueKt.MASK, null);
    }

    public final OverlayLyricsStateProvider getOverlayLyricsStateProvider() {
        return new OverlayLyricsStateProvider(false, false, 3, null);
    }

    public final StageAutomatedSequencingHandler getStageAutomatedSequencingHandler() {
        return new StageAutomatedSequencingHandler(null, 1, 0 == true ? 1 : 0);
    }

    public final StageOverflowMenuSecondaryIconStateProvider getStageOverflowMenuSecondaryIconStateProvider() {
        return new StageOverflowMenuSecondaryIconStateProvider(null, 1, null);
    }

    public final LyricsSharingDataModel getLyricsSharingDataModel() {
        return new LyricsSharingDataModel(null, null, null, null, null, null, 0, 0, 0, FrameMetricsAggregator.EVERY_DURATION, null);
    }
}
