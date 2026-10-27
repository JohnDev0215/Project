package com.amazon.mp3.view.stage;

import android.content.Context;
import android.view.ViewGroup;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.playback.OnLyricsAvailableListener;
import com.amazon.mp3.playback.OnNoLyricsDisplayedListener;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.stage.StageLyrics;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageLyrics.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0002!;\u0018\u0000 B2\u00020\u0001:\u0002BCB%\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00040>¢\u0006\u0004\b@\u0010AJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\u0004J\b\u0010\u000e\u001a\u0004\u0018\u00010\rR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001c8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0018\u00104\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u001d\u00107\u001a\b\u0012\u0004\u0012\u00020.068\u0006¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=¨\u0006D"}, d2 = {"Lcom/amazon/mp3/view/stage/StageLyrics;", "", "Lcom/amazon/music/media/playback/MediaItem;", "item", "", "showLyrics", "", "isLyricsDisabled", "showCompleteLyrics", "setShowFullLyricsOnOverlays", "startListeningToPlaybackChanges", "stopListeningToPlaybackChanges", "unsubscribeFrugalLyricsCompletedSubscription", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "getLyrics", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/view/OnSwipeEventListener;", "onSwipeEventListener", "Lcom/amazon/mp3/view/OnSwipeEventListener;", "getOnSwipeEventListener", "()Lcom/amazon/mp3/view/OnSwipeEventListener;", "lyrics", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "Lio/reactivex/rxjava3/disposables/Disposable;", "frugalLyricsCompletedSubscription", "Lio/reactivex/rxjava3/disposables/Disposable;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsTextViewContainer;", "lyricsViewContainer", "Lcom/amazon/mp3/playback/view/lyrics/LyricsTextViewContainer;", "getLyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease", "()Lcom/amazon/mp3/playback/view/lyrics/LyricsTextViewContainer;", "com/amazon/mp3/view/stage/StageLyrics$lyricsWindowInteractionListener$1", "lyricsWindowInteractionListener", "Lcom/amazon/mp3/view/stage/StageLyrics$lyricsWindowInteractionListener$1;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "playStateListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/mp3/playback/playbackcontrol/OnLyricsLineUpdateListener;", "lyricsLineUpdateListener", "Lcom/amazon/mp3/playback/playbackcontrol/OnLyricsLineUpdateListener;", "Lcom/amazon/music/media/playback/OnMetadataChangedListener;", "metadataListener", "Lcom/amazon/music/media/playback/OnMetadataChangedListener;", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/view/stage/StageLyrics$LyricsState;", "_lyricsSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "Lio/reactivex/rxjava3/core/Observable;", "lyricsObservable", "Lio/reactivex/rxjava3/core/Observable;", "getLyricsObservable", "()Lio/reactivex/rxjava3/core/Observable;", "com/amazon/mp3/view/stage/StageLyrics$frugalLyricsListener$1", "frugalLyricsListener", "Lcom/amazon/mp3/view/stage/StageLyrics$frugalLyricsListener$1;", "Lkotlin/Function0;", "onClick", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/view/OnSwipeEventListener;Lkotlin/jvm/functions/Function0;)V", "Companion", "LyricsState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageLyrics {
    private static final String TAG;
    private final PublishSubject<LyricsState> _lyricsSubject;
    private final Context context;
    private Disposable frugalLyricsCompletedSubscription;
    private final StageLyrics$frugalLyricsListener$1 frugalLyricsListener;
    private Lyrics lyrics;
    private final OnLyricsLineUpdateListener lyricsLineUpdateListener;
    private final Observable<LyricsState> lyricsObservable;
    private final LyricsTextViewContainer lyricsViewContainer;
    private final StageLyrics$lyricsWindowInteractionListener$1 lyricsWindowInteractionListener;
    private MediaItem mediaItem;
    private final OnMetadataChangedListener metadataListener;
    private final OnSwipeEventListener onSwipeEventListener;
    private final OnPlayStateChangedListener playStateListener;
    private final ActionValidatedPlaybackController playbackController;

    /* JADX INFO: compiled from: StageLyrics.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/view/stage/StageLyrics$LyricsState;", "", "(Ljava/lang/String;I)V", "FRUGAL_DISPLAYED", "FRUGAL_HIDDEN", "NO_LYRICS", "LYRICS_AVAILABLE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum LyricsState {
        FRUGAL_DISPLAYED,
        FRUGAL_HIDDEN,
        NO_LYRICS,
        LYRICS_AVAILABLE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showLyrics$lambda-5, reason: not valid java name */
    public static final void m1744showLyrics$lambda5(Void r0) {
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [com.amazon.mp3.view.stage.StageLyrics$lyricsWindowInteractionListener$1] */
    public StageLyrics(Context context, OnSwipeEventListener onSwipeEventListener, final Function0<Unit> onClick) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onSwipeEventListener, "onSwipeEventListener");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        this.context = context;
        this.onSwipeEventListener = onSwipeEventListener;
        final LyricsTextViewContainer lyricsTextViewContainer = new LyricsTextViewContainer(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        lyricsTextViewContainer.setOnNoLyricsDisplayedListener(new OnNoLyricsDisplayedListener() { // from class: com.amazon.mp3.view.stage.StageLyrics$lyricsViewContainer$1$1
            @Override // com.amazon.mp3.playback.OnNoLyricsDisplayedListener
            public void onNoLyricsDisplayed() {
                this.this$0._lyricsSubject.onNext(StageLyrics.LyricsState.NO_LYRICS);
                lyricsTextViewContainer.setVisibility(8);
                this.this$0.lyrics = null;
            }
        });
        lyricsTextViewContainer.setOnLyricsAvailableListener(new OnLyricsAvailableListener() { // from class: com.amazon.mp3.view.stage.StageLyrics$lyricsViewContainer$1$2
            @Override // com.amazon.mp3.playback.OnLyricsAvailableListener
            public void onLyricsAvailable(Lyrics lyrics) {
                Intrinsics.checkNotNullParameter(lyrics, "lyrics");
                this.this$0._lyricsSubject.onNext(StageLyrics.LyricsState.LYRICS_AVAILABLE);
                this.this$0.lyrics = lyrics;
            }
        });
        lyricsTextViewContainer.setLayoutParams(layoutParams);
        lyricsTextViewContainer.setGravity(17);
        Context context2 = lyricsTextViewContainer.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        Integer compatColor = ContextKt.getCompatColor(context2, R.color.transparent);
        if (compatColor != null) {
            lyricsTextViewContainer.setBackgroundColor(compatColor.intValue());
        }
        this.lyricsViewContainer = lyricsTextViewContainer;
        this.lyricsWindowInteractionListener = new LyricsTextViewContainer.LyricsWindowInteractionListener() { // from class: com.amazon.mp3.view.stage.StageLyrics$lyricsWindowInteractionListener$1
            @Override // com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer.LyricsWindowInteractionListener
            public void onLyricsWindowSwipedUp() {
            }

            @Override // com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer.LyricsWindowInteractionListener
            public void onLyricsWindowClicked() {
                onClick.invoke();
            }
        };
        this.playStateListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.view.stage.StageLyrics$$ExternalSyntheticLambda3
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                StageLyrics.m1742playStateListener$lambda3(this.f$0);
            }
        };
        this.lyricsLineUpdateListener = new OnLyricsLineUpdateListener() { // from class: com.amazon.mp3.view.stage.StageLyrics$lyricsLineUpdateListener$1
            @Override // com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener
            public void onLyricsLineUpdated(long positionMillis) {
                this.this$0.getLyricsViewContainer().setPlaybackTime(positionMillis);
            }
        };
        this.metadataListener = new OnMetadataChangedListener() { // from class: com.amazon.mp3.view.stage.StageLyrics$metadataListener$1
            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMediaCollectionInfoChanged(MediaCollectionInfo p0) {
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMetadataChanged(MediaItem item) {
                this.this$0.showLyrics(item);
            }
        };
        PublishSubject<LyricsState> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this._lyricsSubject = publishSubjectCreate;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        Observable<LyricsState> observableHide = publishSubjectCreate.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "_lyricsSubject.hide()");
        this.lyricsObservable = observableHide;
        this.frugalLyricsListener = new StageLyrics$frugalLyricsListener$1(this);
    }

    static {
        String simpleName = StageLyrics.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "StageLyrics::class.java.simpleName");
        TAG = simpleName;
    }

    /* JADX INFO: renamed from: getLyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease, reason: from getter */
    public final LyricsTextViewContainer getLyricsViewContainer() {
        return this.lyricsViewContainer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playStateListener$lambda-3, reason: not valid java name */
    public static final void m1742playStateListener$lambda3(StageLyrics this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem currentMediaItem = this$0.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            Log.warning(TAG, "current media item is null, skip OnPlayStateChanged");
            return;
        }
        if (!Intrinsics.areEqual(currentMediaItem, this$0.mediaItem)) {
            this$0.showLyrics(currentMediaItem);
        }
        this$0.lyricsViewContainer.setPlayStatus(this$0.playbackController.getPlayStatus());
        this$0.lyricsViewContainer.setPlaybackTime(this$0.playbackController.getPositionMillis());
        this$0.mediaItem = currentMediaItem;
    }

    public final Observable<LyricsState> getLyricsObservable() {
        return this.lyricsObservable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLyrics(final MediaItem item) {
        if (item == null || (item instanceof AdMediaItem) || (item instanceof InterludeMediaItem) || isLyricsDisabled()) {
            this.lyricsViewContainer.show(false);
        } else {
            this.lyricsViewContainer.show(false);
            Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.view.stage.StageLyrics$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) {
                    StageLyrics.m1743showLyrics$lambda4(this.f$0, item, observableEmitter);
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.view.stage.StageLyrics$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    StageLyrics.m1744showLyrics$lambda5((Void) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.view.stage.StageLyrics$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    StageLyrics.m1745showLyrics$lambda6((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showLyrics$lambda-4, reason: not valid java name */
    public static final void m1743showLyrics$lambda4(StageLyrics this$0, MediaItem mediaItem, ObservableEmitter observableEmitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.lyricsViewContainer.showLyricsForMediaItem(mediaItem);
        this$0.lyricsViewContainer.setPlayStatus(this$0.playbackController.getPlayStatus());
        observableEmitter.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showLyrics$lambda-6, reason: not valid java name */
    public static final void m1745showLyrics$lambda6(Throwable th) {
        Log.error(TAG, "Error setting up lyricsViewContainer", th);
    }

    public final void setShowFullLyricsOnOverlays(boolean showCompleteLyrics) {
        this.lyricsViewContainer.setShowFullLyricsOnOverlays(showCompleteLyrics);
    }

    private final boolean isLyricsDisabled() {
        return AmazonApplication.getCapabilities().shouldDisableLyrics(this.context);
    }

    public final void startListeningToPlaybackChanges() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem != null) {
            showLyrics(currentMediaItem);
            this.mediaItem = currentMediaItem;
        }
        this.playbackController.addOnLyricsLineUpdateListener(this.lyricsLineUpdateListener);
        this.playbackController.addOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.addOnMetadataChangedListener(this.metadataListener);
        this.lyricsViewContainer.setFrugalLyricsListener(this.frugalLyricsListener);
        this.lyricsViewContainer.setLyricsWindowInteractionListener(this.lyricsWindowInteractionListener);
        this.lyricsViewContainer.setSwipeEventListener(this.onSwipeEventListener);
    }

    public final void stopListeningToPlaybackChanges() {
        this.playbackController.removeOnLyricsLineUpdateListener(this.lyricsLineUpdateListener);
        this.playbackController.removeOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.removeOnMetadataChangedListener(this.metadataListener);
        this.lyricsViewContainer.setFrugalLyricsListener(null);
        this.lyricsViewContainer.setLyricsWindowInteractionListener(null);
        this.lyricsViewContainer.setSwipeEventListener(null);
    }

    public final void unsubscribeFrugalLyricsCompletedSubscription() {
        Disposable disposable = this.frugalLyricsCompletedSubscription;
        if (disposable != null) {
            boolean z = false;
            if (disposable != null && !disposable.isDisposed()) {
                z = true;
            }
            if (z) {
                Disposable disposable2 = this.frugalLyricsCompletedSubscription;
                if (disposable2 != null) {
                    disposable2.dispose();
                }
                this.frugalLyricsCompletedSubscription = null;
            }
        }
    }

    public final Lyrics getLyrics() {
        return this.lyrics;
    }
}
