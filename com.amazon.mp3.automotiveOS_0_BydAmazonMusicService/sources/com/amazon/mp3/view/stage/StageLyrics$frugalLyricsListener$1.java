package com.amazon.mp3.view.stage;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.mp3.playback.FrugalLyricsListener;
import com.amazon.mp3.playback.view.lyrics.ViewState;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.stage.StageLyrics;
import com.amazon.music.media.playback.MediaItem;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageLyrics.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"com/amazon/mp3/view/stage/StageLyrics$frugalLyricsListener$1", "Lcom/amazon/mp3/playback/FrugalLyricsListener;", "onFrugalLyricsDisplayed", "", "onFrugalLyricsHidden", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageLyrics$frugalLyricsListener$1 implements FrugalLyricsListener {
    final /* synthetic */ StageLyrics this$0;

    StageLyrics$frugalLyricsListener$1(StageLyrics stageLyrics) {
        this.this$0 = stageLyrics;
    }

    @Override // com.amazon.mp3.playback.FrugalLyricsListener
    public void onFrugalLyricsDisplayed() {
        long j = ScreenUtil.isPortrait() ? CoroutineLiveDataKt.DEFAULT_TIMEOUT : 0L;
        final MediaItem currentMediaItem = this.this$0.playbackController.getCurrentMediaItem();
        StageLyrics stageLyrics = this.this$0;
        Observable<Long> observableTimer = Observable.timer(j, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread());
        final StageLyrics stageLyrics2 = this.this$0;
        stageLyrics.frugalLyricsCompletedSubscription = observableTimer.subscribe(new Consumer() { // from class: com.amazon.mp3.view.stage.StageLyrics$frugalLyricsListener$1$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StageLyrics$frugalLyricsListener$1.m1747onFrugalLyricsDisplayed$lambda0(currentMediaItem, stageLyrics2, (Long) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onFrugalLyricsDisplayed$lambda-0, reason: not valid java name */
    public static final void m1747onFrugalLyricsDisplayed$lambda0(MediaItem mediaItem, StageLyrics this$0, Long l) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(mediaItem, this$0.playbackController.getCurrentMediaItem()) && this$0.getLyricsViewContainer().getCachedViewState() == ViewState.FRUGAL_LYRICS) {
            this$0._lyricsSubject.onNext(StageLyrics.LyricsState.FRUGAL_DISPLAYED);
        }
    }

    @Override // com.amazon.mp3.playback.FrugalLyricsListener
    public void onFrugalLyricsHidden() {
        this.this$0.playbackController.addOnLyricsLineUpdateListener(this.this$0.lyricsLineUpdateListener);
        this.this$0._lyricsSubject.onNext(StageLyrics.LyricsState.FRUGAL_HIDDEN);
    }
}
