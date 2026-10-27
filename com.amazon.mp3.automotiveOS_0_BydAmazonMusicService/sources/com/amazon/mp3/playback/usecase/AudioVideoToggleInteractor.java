package com.amazon.mp3.playback.usecase;

import android.content.Context;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.playback.view.AudioVideoToggleView;
import com.amazon.mp3.prime.requester.HybridQueueTrackRequester;
import com.amazon.mp3.prime.requester.HybridQueueType;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: AudioVideoToggleInteractor.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 52\u00020\u0001:\u00015B\u001f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b3\u00104J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\"\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0016J\u0016\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006J\b\u0010\u001c\u001a\u00020\u0006H\u0007R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R8\u0010&\u001a&\u0012\f\u0012\n %*\u0004\u0018\u00010\u000f0\u000f %*\u0012\u0012\f\u0012\n %*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010$0$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R \u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0(8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102¨\u00066"}, d2 = {"Lcom/amazon/mp3/playback/usecase/AudioVideoToggleInteractor;", "Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "", "asin", "", "updateToggleUi", "", "shouldHideToggle", "showToggle", "hideToggle", "Landroid/view/View;", "getToggleView", "Landroid/content/Context;", "context", "updateToggleState", "Lcom/amazon/mp3/prime/requester/HybridQueueType;", "hybridQueueType", "doToggle", "blockref", "sendToggleMetrics", "Landroid/view/View$OnClickListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setAudioVideoToggleListener", "removeAudioVideoToggleListener", "loadTrackAssociationsBatch", "isDefault", "isVideo", "updateToggleButtonState", "shouldHideToggleInOffineMode", "Landroid/content/Context;", "Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "hybridQueueStateProvider", "Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;", "Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequester;", "hybridQueueTrackRequester", "Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequester;", "Lrx/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "_toggleSuccessfulSubject", "Lrx/subjects/PublishSubject;", "Lrx/Observable;", "toggleSuccessfulObservable", "Lrx/Observable;", "getToggleSuccessfulObservable", "()Lrx/Observable;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/mp3/playback/view/AudioVideoToggleView;", "toggleView", "Lcom/amazon/mp3/playback/view/AudioVideoToggleView;", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/starlight/provider/HybridQueueStateProvider;Lcom/amazon/mp3/prime/requester/HybridQueueTrackRequester;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AudioVideoToggleInteractor implements AudioVideoToggleUseCase {
    private static final String TAG = AudioVideoToggleInteractor.class.getSimpleName();
    private final PublishSubject<HybridQueueType> _toggleSuccessfulSubject;
    private final Context context;
    private final HybridQueueStateProvider hybridQueueStateProvider;
    private final HybridQueueTrackRequester hybridQueueTrackRequester;
    private final ActionValidatedPlaybackController playbackController;
    private final Observable<HybridQueueType> toggleSuccessfulObservable;
    private final AudioVideoToggleView toggleView;

    public AudioVideoToggleInteractor(Context context, HybridQueueStateProvider hybridQueueStateProvider, HybridQueueTrackRequester hybridQueueTrackRequester) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hybridQueueStateProvider, "hybridQueueStateProvider");
        Intrinsics.checkNotNullParameter(hybridQueueTrackRequester, "hybridQueueTrackRequester");
        this.context = context;
        this.hybridQueueStateProvider = hybridQueueStateProvider;
        this.hybridQueueTrackRequester = hybridQueueTrackRequester;
        PublishSubject<HybridQueueType> publishSubjectCreate = PublishSubject.create();
        this._toggleSuccessfulSubject = publishSubjectCreate;
        Observable<HybridQueueType> observableAsObservable = publishSubjectCreate.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "_toggleSuccessfulSubject.asObservable()");
        this.toggleSuccessfulObservable = observableAsObservable;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
        this.toggleView = new AudioVideoToggleView(context);
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public Observable<HybridQueueType> getToggleSuccessfulObservable() {
        return this.toggleSuccessfulObservable;
    }

    public void showToggle() {
        this.toggleView.showToggle();
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public void hideToggle() {
        this.toggleView.hideToggle();
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public View getToggleView() {
        return this.toggleView;
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public synchronized void updateToggleState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (shouldHideToggle()) {
            hideToggle();
            return;
        }
        MediaItem currentMediaItem = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (currentMediaItem == null) {
            return;
        }
        updateToggleButtonState(this.hybridQueueStateProvider.isDefault(), NowPlayingUtil.isVideoPlaying() && (this.hybridQueueStateProvider.isVideo() || this.hybridQueueStateProvider.isDefault() || this.hybridQueueStateProvider.isBackgroundAudio()));
        MediaItemId mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
        final String id = mediaItemId == null ? null : mediaItemId.getId();
        if (id != null) {
            if (!this.hybridQueueStateProvider.getAsinsAlreadyRequested().contains(id)) {
                this.hybridQueueTrackRequester.requestHybridQueueAndUpdateDb(context, false, "", new Function0<Unit>() { // from class: com.amazon.mp3.playback.usecase.AudioVideoToggleInteractor$updateToggleState$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        this.this$0.updateToggleUi(id);
                    }
                });
            } else {
                updateToggleUi(id);
            }
        }
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public void doToggle(Context context, final HybridQueueType hybridQueueType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hybridQueueType, "hybridQueueType");
        this.hybridQueueStateProvider.setHybridQueueType(hybridQueueType);
        this.hybridQueueTrackRequester.requestHybridQueueAndUpdateDb(context, true, "", new Function0<Unit>() { // from class: com.amazon.mp3.playback.usecase.AudioVideoToggleInteractor.doToggle.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AudioVideoToggleInteractor.this._toggleSuccessfulSubject.onNext(hybridQueueType);
                if (AudioVideoToggleInteractor.this.playbackController.getPlayStatus() == PlayStatus.USER_PAUSED || AudioVideoToggleInteractor.this.playbackController.getPlayStatus() == PlayStatus.SYSTEM_PAUSED) {
                    AudioVideoToggleInteractor.this.playbackController.play();
                }
            }
        });
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public void sendToggleMetrics(String asin, HybridQueueType hybridQueueType, String blockref) {
        PageType pageType;
        Intrinsics.checkNotNullParameter(asin, "asin");
        Intrinsics.checkNotNullParameter(hybridQueueType, "hybridQueueType");
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            pageType = PageType.NOW_PLAYING_STAGE;
        } else {
            pageType = PageType.NOW_PLAYING;
        }
        PageType pageType2 = pageType;
        if (Intrinsics.areEqual(hybridQueueType, HybridQueueType.VIDEO_QUEUE.INSTANCE)) {
            VideoUtil.sendUiClickMetric$default(VideoUtil.INSTANCE, asin, ActionType.VIDEO_TOGGLE_MUSIC_VIDEO, null, pageType2, null, null, 48, null);
        } else if (Intrinsics.areEqual(hybridQueueType, HybridQueueType.AUDIO_QUEUE.INSTANCE)) {
            VideoUtil.sendUiClickMetric$default(VideoUtil.INSTANCE, asin, ActionType.VIDEO_TOGGLE_AUDIO, null, pageType2, null, null, 48, null);
        }
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public void setAudioVideoToggleListener(View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.toggleView.setAudioVideoToggleListener(listener);
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public void removeAudioVideoToggleListener() {
        this.toggleView.removeAudioVideoToggleListener();
    }

    @Override // com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase
    public void loadTrackAssociationsBatch(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.hybridQueueTrackRequester.requestHybridQueueAndUpdateDb(context, false, "", new Function0<Unit>() { // from class: com.amazon.mp3.playback.usecase.AudioVideoToggleInteractor.loadTrackAssociationsBatch.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateToggleUi(String asin) {
        if (shouldHideToggle()) {
            hideToggle();
            return;
        }
        if ((this.hybridQueueStateProvider.getAudioToVideoMap().get(asin) == null && this.hybridQueueStateProvider.getVideoToAudioMap().get(asin) == null) ? false : true) {
            showToggle();
        } else {
            hideToggle();
        }
    }

    public final void updateToggleButtonState(boolean isDefault, boolean isVideo) {
        if (isVideo) {
            AudioVideoToggleView.setAudioVideoToggleToVideo$default(this.toggleView, isDefault, false, 2, null);
        } else {
            AudioVideoToggleView.setAudioVideoToggleToAudio$default(this.toggleView, isDefault, false, 2, null);
        }
    }

    private final boolean shouldHideToggle() {
        return !AmazonApplication.getCapabilities().isVideoToggleEnabled() || NowPlayingUtil.isAlexaPlaying() || NowPlayingUtil.isStationPlaying() || NowPlayingUtil.isSideLoadedTrackPlaying() || CastingUtil.isCasting() || shouldHideToggleInOffineMode();
    }

    @VisibleForTesting
    public final boolean shouldHideToggleInOffineMode() {
        return (LastViewedScreenUtil.getLastViewedSource(this.context) == LastViewedScreenUtil.LastViewedSource.DEVICE) && Intrinsics.areEqual(KatanaNPSBadgeProvider.isCurrentMediaItemDownloaded(PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI).getCurrentMediaItem()), Boolean.TRUE);
    }
}
