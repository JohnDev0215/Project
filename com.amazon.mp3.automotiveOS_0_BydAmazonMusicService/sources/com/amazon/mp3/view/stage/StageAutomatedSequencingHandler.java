package com.amazon.mp3.view.stage;

import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.view.stage.StageViewPresenter;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlaySelectionType;
import com.amazon.mp3.view.stage.overlay.OverlayType;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: StageAutomatedSequencingHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b7\u0010\u0018J%\u0010\b\u001a\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\n\u001a\u00020\u0007J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\t\u0010\u000e\u001a\u00020\rHÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\"\u0010#\u001a\u00020\"8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010*\u001a\u00020)8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010!R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106¨\u00068"}, d2 = {"Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "", "Lrx/subjects/PublishSubject;", "Lcom/amazon/mp3/view/stage/StageViewPresenter$OverlaySequence;", "overlaySequenceSubject", "", "timeToTransitionFromAlbumArtToLyrics", "", "startAlbumArtTimer", "(Lrx/subjects/PublishSubject;Ljava/lang/Long;)V", "resetAutomatedSequenceCurrentOverlay", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "automatedSequenceCurrentOverlay", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "getAutomatedSequenceCurrentOverlay", "()Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "setAutomatedSequenceCurrentOverlay", "(Lcom/amazon/mp3/view/stage/overlay/OverlayType;)V", "J", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "overlaySequenceObservable", "Lrx/subjects/PublishSubject;", "Lcom/amazon/music/media/playback/MediaItem;", "lastRequestedMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "overlayTypeStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "getOverlayTypeStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "setOverlayTypeStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;)V", "Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "overlayLyricsStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "getOverlayLyricsStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "setOverlayLyricsStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;)V", "mediaItem", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "onPlayStateChangedListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/mp3/playback/playbackcontrol/OnLyricsLineUpdateListener;", "lyricsLineUpdateListener", "Lcom/amazon/mp3/playback/playbackcontrol/OnLyricsLineUpdateListener;", "<init>", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class StageAutomatedSequencingHandler {
    private OverlayType automatedSequenceCurrentOverlay;
    private MediaItem lastRequestedMediaItem;
    private final OnLyricsLineUpdateListener lyricsLineUpdateListener;
    private MediaItem mediaItem;
    private final OnPlayStateChangedListener onPlayStateChangedListener;
    public OverlayLyricsStateProvider overlayLyricsStateProvider;
    private PublishSubject<StageViewPresenter.OverlaySequence> overlaySequenceObservable;
    public OverlayTypeStateProvider overlayTypeStateProvider;
    private final ActionValidatedPlaybackController playbackController;
    private long timeToTransitionFromAlbumArtToLyrics;

    public StageAutomatedSequencingHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof StageAutomatedSequencingHandler) && this.automatedSequenceCurrentOverlay == ((StageAutomatedSequencingHandler) other).automatedSequenceCurrentOverlay;
    }

    public int hashCode() {
        return this.automatedSequenceCurrentOverlay.hashCode();
    }

    public String toString() {
        return "StageAutomatedSequencingHandler(automatedSequenceCurrentOverlay=" + this.automatedSequenceCurrentOverlay + ')';
    }

    public StageAutomatedSequencingHandler(OverlayType automatedSequenceCurrentOverlay) {
        Intrinsics.checkNotNullParameter(automatedSequenceCurrentOverlay, "automatedSequenceCurrentOverlay");
        this.automatedSequenceCurrentOverlay = automatedSequenceCurrentOverlay;
        this.timeToTransitionFromAlbumArtToLyrics = 3000L;
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        OnPlayStateChangedListener onPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.view.stage.StageAutomatedSequencingHandler$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                StageAutomatedSequencingHandler.m1734onPlayStateChangedListener$lambda0(this.f$0);
            }
        };
        this.onPlayStateChangedListener = onPlayStateChangedListener;
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
        controller.addOnPlayStateChangedListener(onPlayStateChangedListener);
        this.lyricsLineUpdateListener = new OnLyricsLineUpdateListener() { // from class: com.amazon.mp3.view.stage.StageAutomatedSequencingHandler$lyricsLineUpdateListener$1
            @Override // com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener
            public void onLyricsLineUpdated(long positionMillis) {
                StageAutomatedSequencingHandler stageAutomatedSequencingHandler = this.this$0;
                if (stageAutomatedSequencingHandler.overlayLyricsStateProvider != null) {
                    OverlayTypeStateProvider overlayTypeStateProvider = stageAutomatedSequencingHandler.getOverlayTypeStateProvider();
                    if (Intrinsics.areEqual(overlayTypeStateProvider == null ? null : overlayTypeStateProvider.getOverlaySelectionType(), OverlaySelectionType.Automatic.INSTANCE) && this.this$0.getAutomatedSequenceCurrentOverlay() == OverlayType.ALBUM_ART && positionMillis >= this.this$0.timeToTransitionFromAlbumArtToLyrics && Intrinsics.areEqual(this.this$0.lastRequestedMediaItem, this.this$0.playbackController.getCurrentMediaItem())) {
                        PublishSubject publishSubject = this.this$0.overlaySequenceObservable;
                        if (publishSubject != null) {
                            publishSubject.onNext(StageViewPresenter.OverlaySequence.ALBUM_ART_PREVIEW_FINISHED);
                        }
                        this.this$0.setAutomatedSequenceCurrentOverlay(OverlayType.LYRICS_PARTIAL);
                        this.this$0.playbackController.removeOnLyricsLineUpdateListener(this);
                    }
                }
            }
        };
    }

    public /* synthetic */ StageAutomatedSequencingHandler(OverlayType overlayType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? OverlayType.ALBUM_ART : overlayType);
    }

    public final OverlayType getAutomatedSequenceCurrentOverlay() {
        return this.automatedSequenceCurrentOverlay;
    }

    public final void setAutomatedSequenceCurrentOverlay(OverlayType overlayType) {
        Intrinsics.checkNotNullParameter(overlayType, "<set-?>");
        this.automatedSequenceCurrentOverlay = overlayType;
    }

    public final OverlayTypeStateProvider getOverlayTypeStateProvider() {
        OverlayTypeStateProvider overlayTypeStateProvider = this.overlayTypeStateProvider;
        if (overlayTypeStateProvider != null) {
            return overlayTypeStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayTypeStateProvider");
        return null;
    }

    public final OverlayLyricsStateProvider getOverlayLyricsStateProvider() {
        OverlayLyricsStateProvider overlayLyricsStateProvider = this.overlayLyricsStateProvider;
        if (overlayLyricsStateProvider != null) {
            return overlayLyricsStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayLyricsStateProvider");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayStateChangedListener$lambda-0, reason: not valid java name */
    public static final void m1734onPlayStateChangedListener$lambda0(StageAutomatedSequencingHandler this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem currentMediaItem = this$0.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || Intrinsics.areEqual(currentMediaItem, this$0.mediaItem)) {
            return;
        }
        this$0.automatedSequenceCurrentOverlay = OverlayType.ALBUM_ART;
        if (this$0.overlayLyricsStateProvider != null) {
            this$0.getOverlayLyricsStateProvider().setFrugalLyricsCompleted(false);
        }
        this$0.mediaItem = currentMediaItem;
    }

    public final void startAlbumArtTimer(PublishSubject<StageViewPresenter.OverlaySequence> overlaySequenceSubject, Long timeToTransitionFromAlbumArtToLyrics) {
        Intrinsics.checkNotNullParameter(overlaySequenceSubject, "overlaySequenceSubject");
        this.overlaySequenceObservable = overlaySequenceSubject;
        if (Intrinsics.areEqual(this.lastRequestedMediaItem, this.playbackController.getCurrentMediaItem())) {
            return;
        }
        if (timeToTransitionFromAlbumArtToLyrics != null) {
            this.timeToTransitionFromAlbumArtToLyrics = timeToTransitionFromAlbumArtToLyrics.longValue();
        }
        this.lastRequestedMediaItem = this.playbackController.getCurrentMediaItem();
        this.playbackController.addOnLyricsLineUpdateListener(this.lyricsLineUpdateListener);
    }

    public final void resetAutomatedSequenceCurrentOverlay() {
        this.automatedSequenceCurrentOverlay = OverlayType.ALBUM_ART;
    }
}
