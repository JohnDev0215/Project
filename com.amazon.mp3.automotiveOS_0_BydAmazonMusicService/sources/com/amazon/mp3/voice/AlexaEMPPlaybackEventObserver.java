package com.amazon.mp3.voice;

import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackErrorListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.voice.emp.EMPPlayStatus;
import com.amazon.music.voice.emp.EMPPlaybackState;
import com.amazon.music.voice.emp.events.EMPError;
import com.amazon.music.voice.emp.events.EMPEvent;
import io.reactivex.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AlexaEMPPlaybackEventObserver.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tJ\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u000bJ\u0006\u0010\u0016\u001a\u00020\rR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/voice/AlexaEMPPlaybackEventObserver;", "", "empEventSubject", "Lio/reactivex/subjects/PublishSubject;", "Lcom/amazon/music/voice/emp/events/EMPEvent;", "empErrorSubject", "Lcom/amazon/music/voice/emp/events/EMPError;", "empPlaybackStateSubject", "Lcom/amazon/music/voice/emp/EMPPlaybackState;", "(Lio/reactivex/subjects/PublishSubject;Lio/reactivex/subjects/PublishSubject;Lio/reactivex/subjects/PublishSubject;)V", "mOnPlaybackErrorListener", "Lcom/amazon/music/media/playback/OnPlaybackErrorListener;", "mOnPlaybackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "onPlayStateChangedListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "previousPlayState", "Lcom/amazon/music/media/playback/PlayStatus;", "getPlayStateChangedListener", "getPlaybackErrorListener", "getPlaybackEventListener", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaEMPPlaybackEventObserver {
    private static final Logger LOG;
    private static final String TAG;
    private final PublishSubject<EMPError> empErrorSubject;
    private final PublishSubject<EMPEvent> empEventSubject;
    private final PublishSubject<EMPPlaybackState> empPlaybackStateSubject;
    private final OnPlaybackErrorListener mOnPlaybackErrorListener;
    private final OnPlaybackEventListener mOnPlaybackEventListener;
    private MediaItem mediaItem;
    private final OnPlayStateChangedListener onPlayStateChangedListener;
    private PlayStatus previousPlayState;

    /* JADX INFO: compiled from: AlexaEMPPlaybackEventObserver.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlaybackException.PlaybackError.values().length];
            iArr[PlaybackException.PlaybackError.NetworkFailure.ordinal()] = 1;
            iArr[PlaybackException.PlaybackError.StreamingPlayback.ordinal()] = 2;
            iArr[PlaybackException.PlaybackError.StreamingPlaybackLocal.ordinal()] = 3;
            iArr[PlaybackException.PlaybackError.StreamingServiceError.ordinal()] = 4;
            iArr[PlaybackException.PlaybackError.StreamingPlaybackRemote.ordinal()] = 5;
            iArr[PlaybackException.PlaybackError.StreamingRestriction.ordinal()] = 6;
            iArr[PlaybackException.PlaybackError.InvalidParameter.ordinal()] = 7;
            iArr[PlaybackException.PlaybackError.InsufficientStorage.ordinal()] = 8;
            iArr[PlaybackException.PlaybackError.ContentNotEligible.ordinal()] = 9;
            iArr[PlaybackException.PlaybackError.RegistrationError.ordinal()] = 10;
            iArr[PlaybackException.PlaybackError.SignedOut.ordinal()] = 11;
            iArr[PlaybackException.PlaybackError.CustomerNotEligible.ordinal()] = 12;
            iArr[PlaybackException.PlaybackError.ConcurrentStreamLimit.ordinal()] = 13;
            iArr[PlaybackException.PlaybackError.PrimeStreamingConcurrency.ordinal()] = 14;
            iArr[PlaybackException.PlaybackError.QueueTerminated.ordinal()] = 15;
            iArr[PlaybackException.PlaybackError.ReachEndOfQueue.ordinal()] = 16;
            iArr[PlaybackException.PlaybackError.StationOutOfTracks.ordinal()] = 17;
            iArr[PlaybackException.PlaybackError.ExpiredUrl.ordinal()] = 18;
            iArr[PlaybackException.PlaybackError.DeviceRooted.ordinal()] = 19;
            iArr[PlaybackException.PlaybackError.LocalFileNotFound.ordinal()] = 20;
            iArr[PlaybackException.PlaybackError.DrmFileNotFound.ordinal()] = 21;
            iArr[PlaybackException.PlaybackError.DrmLicenseExpired.ordinal()] = 22;
            iArr[PlaybackException.PlaybackError.InvalidFile.ordinal()] = 23;
            iArr[PlaybackException.PlaybackError.FileNotFound.ordinal()] = 24;
            iArr[PlaybackException.PlaybackError.StationNotFound.ordinal()] = 25;
            iArr[PlaybackException.PlaybackError.StationCannotContinue.ordinal()] = 26;
            iArr[PlaybackException.PlaybackError.StationCannotStart.ordinal()] = 27;
            iArr[PlaybackException.PlaybackError.InvalidStationSeed.ordinal()] = 28;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AlexaEMPPlaybackEventObserver(PublishSubject<EMPEvent> empEventSubject, PublishSubject<EMPError> empErrorSubject, PublishSubject<EMPPlaybackState> empPlaybackStateSubject) {
        Intrinsics.checkNotNullParameter(empEventSubject, "empEventSubject");
        Intrinsics.checkNotNullParameter(empErrorSubject, "empErrorSubject");
        Intrinsics.checkNotNullParameter(empPlaybackStateSubject, "empPlaybackStateSubject");
        this.empEventSubject = empEventSubject;
        this.empErrorSubject = empErrorSubject;
        this.empPlaybackStateSubject = empPlaybackStateSubject;
        this.onPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.voice.AlexaEMPPlaybackEventObserver$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                AlexaEMPPlaybackEventObserver.m1810onPlayStateChangedListener$lambda0(this.f$0);
            }
        };
        this.mOnPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.voice.AlexaEMPPlaybackEventObserver$$ExternalSyntheticLambda1
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                AlexaEMPPlaybackEventObserver.m1809mOnPlaybackEventListener$lambda1(this.f$0, i, controlSource);
            }
        };
        this.mOnPlaybackErrorListener = new OnPlaybackErrorListener() { // from class: com.amazon.mp3.voice.AlexaEMPPlaybackEventObserver$$ExternalSyntheticLambda2
            @Override // com.amazon.music.media.playback.OnPlaybackErrorListener
            public final void onPlaybackError(PlaybackException playbackException) {
                AlexaEMPPlaybackEventObserver.m1808mOnPlaybackErrorListener$lambda2(this.f$0, playbackException);
            }
        };
    }

    static {
        String simpleName = AlexaEMPPlaybackEventObserver.class.getSimpleName();
        TAG = simpleName;
        Logger logger = LoggerFactory.getLogger(simpleName);
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(TAG)");
        LOG = logger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayStateChangedListener$lambda-0, reason: not valid java name */
    public static final void m1810onPlayStateChangedListener$lambda0(AlexaEMPPlaybackEventObserver this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.ALEXA);
        MediaItem currentMediaItem = controller.getCurrentMediaItem();
        boolean zIsVoiceInitiated = currentMediaItem == null ? false : currentMediaItem.isVoiceInitiated();
        PlayStatus playStatus = controller.getPlayStatus();
        PlayStatus playStatus2 = this$0.previousPlayState;
        if (playStatus2 == null) {
            playStatus2 = PlayStatus.USER_PAUSED;
        }
        if (playStatus2 == playStatus || !zIsVoiceInitiated || currentMediaItem == null || (currentMediaItem instanceof AlexaMediaItem)) {
            return;
        }
        if (PlayStatus.PREPARING == playStatus) {
            LOG.debug("Playback Controller PREPARING");
            this$0.previousPlayState = playStatus;
            PublishSubject<EMPPlaybackState> publishSubject = this$0.empPlaybackStateSubject;
            EMPPlayStatus eMPPlayStatus = EMPPlayStatus.PREPARING;
            String id = currentMediaItem.getMediaItemId().getId();
            Intrinsics.checkNotNullExpressionValue(id, "currentMediaItem.mediaItemId.id");
            publishSubject.onNext(new EMPPlaybackState(eMPPlayStatus, id));
        }
        if (PlayStatus.RENDERING != playStatus || Intrinsics.areEqual(currentMediaItem, this$0.mediaItem)) {
            return;
        }
        LOG.debug("Playback state changed to PLAYBACK_STARTED for mediaItem " + currentMediaItem.getName() + ' ');
        this$0.mediaItem = currentMediaItem;
        PublishSubject<EMPPlaybackState> publishSubject2 = this$0.empPlaybackStateSubject;
        EMPPlayStatus eMPPlayStatus2 = EMPPlayStatus.PLAYBACK_STARTED;
        String id2 = currentMediaItem.getMediaItemId().getId();
        Intrinsics.checkNotNullExpressionValue(id2, "currentMediaItem.mediaItemId.id");
        publishSubject2.onNext(new EMPPlaybackState(eMPPlayStatus2, id2));
        this$0.empEventSubject.onNext(EMPEvent.PLAYBACK_STARTED);
        this$0.previousPlayState = playStatus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mOnPlaybackEventListener$lambda-1, reason: not valid java name */
    public static final void m1809mOnPlaybackEventListener$lambda1(AlexaEMPPlaybackEventObserver this$0, int i, ControlSource controlSource) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LOG.debug(Intrinsics.stringPlus("Received playback event  for EMP -> ", Integer.valueOf(i)));
        switch (i) {
            case 1:
                this$0.empEventSubject.onNext(EMPEvent.PLAYBACK_NEXT);
                this$0.empEventSubject.onNext(EMPEvent.TRACK_CHANGED);
                break;
            case 2:
                this$0.empEventSubject.onNext(EMPEvent.PLAYBACK_PREVIOUS);
                this$0.empEventSubject.onNext(EMPEvent.TRACK_CHANGED);
                break;
            case 3:
                this$0.empEventSubject.onNext(EMPEvent.TRACK_CHANGED);
                break;
            case 8:
            case 9:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
                this$0.empEventSubject.onNext(EMPEvent.PLAY_MODE_CHANGED);
                break;
            case 11:
            case 12:
            case 15:
                this$0.empEventSubject.onNext(EMPEvent.PLAYBACK_STOPPED);
                break;
            case 14:
            case 16:
                this$0.empEventSubject.onNext(EMPEvent.PLAYBACK_STARTED);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mOnPlaybackErrorListener$lambda-2, reason: not valid java name */
    public static final void m1808mOnPlaybackErrorListener$lambda2(AlexaEMPPlaybackEventObserver this$0, PlaybackException error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(error, "error");
        PlaybackException.PlaybackError playbackError = error.getPlaybackError();
        switch (playbackError == null ? -1 : WhenMappings.$EnumSwitchMapping$0[playbackError.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                this$0.empErrorSubject.onNext(EMPError.INTERNAL_ERROR);
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                this$0.empErrorSubject.onNext(EMPError.UNPLAYABLE_BY_ACCOUNT);
                break;
            case 13:
            case 14:
                this$0.empErrorSubject.onNext(EMPError.UNPLAYABLE_BY_STREAM_CONCURRENCY);
                break;
            case 15:
            case 16:
            case 17:
                this$0.empErrorSubject.onNext(EMPError.OPERATION_REJECTED_END_OF_QUEUE);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
                this$0.empErrorSubject.onNext(EMPError.OPERATION_UNSUPPORTED);
                break;
            default:
                this$0.empErrorSubject.onNext(EMPError.UNKNOWN_ERROR);
                break;
        }
    }

    /* JADX INFO: renamed from: getPlaybackEventListener, reason: from getter */
    public final OnPlaybackEventListener getMOnPlaybackEventListener() {
        return this.mOnPlaybackEventListener;
    }

    /* JADX INFO: renamed from: getPlaybackErrorListener, reason: from getter */
    public final OnPlaybackErrorListener getMOnPlaybackErrorListener() {
        return this.mOnPlaybackErrorListener;
    }

    /* JADX INFO: renamed from: getPlayStateChangedListener, reason: from getter */
    public final OnPlayStateChangedListener getOnPlayStateChangedListener() {
        return this.onPlayStateChangedListener;
    }
}
