package com.amazon.mp3.playback.casting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.casting.GetTrackFromMetadataThingShadowTask;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.session.CastingPlaybackCallback;
import com.amazon.music.casting.session.CastingQueueCallback;
import com.amazon.music.casting.session.CastingQueueResult;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.casting.session.things.MetadataThingShadow;
import com.amazon.music.casting.session.things.NowPlayingThingShadow;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.player.PositionTracker;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.RateLimiter;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class CastingStateManager implements CastingPlaybackCallback, CastingQueueCallback, CastingLatencyCallback {
    private static final long CHANGE_FAIL_MILLIS = TimeUnit.SECONDS.toMillis(5);
    private static final Callback NULL_CALLBACK = new Callback() { // from class: com.amazon.mp3.playback.casting.CastingStateManager.1
        @Override // com.amazon.mp3.playback.casting.CastingStateManager.Callback
        public void onMediaItemChanged(final MediaItem mediaItem) {
        }

        @Override // com.amazon.mp3.playback.casting.CastingStateManager.Callback
        public void onPlayStateChanged() {
        }

        @Override // com.amazon.mp3.playback.casting.CastingStateManager.Callback
        public void onPositionChanged(final long positionMillis) {
        }
    };
    private static final String TAG = "CastingStateManager";
    private static CastingStateManager sCastingStateManager;
    private Callback mCallback;
    private final CastingSessionManager mCastingManager;
    private GetTrackFromMetadataThingShadowTask.GetTrackFromMetadataThingShadowHandler mGetTrackHandler;
    private State<CastingMediaItem> mIncomingState;
    private State<MediaItem> mLocalState;
    private final OffsetFilter mOffsetFilter;
    private State<MediaItem> mOutgoingState;
    private State<MediaItem> mPreviousOutgoingState;
    private final RateLimiter mRateLimiter;
    private State<CastingMediaItem> mRemoteState;
    private final Runnable mScheduledUpdate;
    private MetadataThingShadow.Track mTrack;
    private boolean mWaitingToCast;
    private String queueId;
    private final Map<LatencyTrackingLeg, Long> mLatencyCastingTransferQueue = new HashMap();
    private final Object mLock = new Object();

    public interface Callback {
        void onMediaItemChanged(MediaItem mediaItem);

        void onPlayStateChanged();

        void onPositionChanged(final long positionMillis);
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onVolumeUpdated(final NowPlayingThingShadow.State.ReportedNode.Volume volume) {
    }

    public static CastingStateManager getInstance() {
        if (sCastingStateManager == null) {
            sCastingStateManager = new CastingStateManager();
        }
        return sCastingStateManager;
    }

    private static class Update<T> {
        public long updateTime;
        public T value;

        public Update(final T value) {
            this.value = value;
        }

        public void setValue(final T value) {
            this.value = value;
        }

        public void set(final T value) {
            setValue(value);
            this.updateTime = Clock.now();
        }

        public void updated() {
            this.updateTime = Clock.now();
        }

        public void copyFrom(Update<? extends T> source) {
            set(source.value);
        }

        public void copyValueFrom(Update<? extends T> source) {
            setValue(source.value);
        }

        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return Objects.equals(this.value, ((Update) o).value);
        }

        public int hashCode() {
            return Objects.hash(this.value);
        }

        public boolean isOlderThan(final long ageMillis) {
            return Clock.now() - this.updateTime > ageMillis;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("Update{value=");
            sb.append(this.value);
            if (this.value == null) {
                str = "";
            } else {
                str = "@@" + System.identityHashCode(this.value);
            }
            sb.append(str);
            sb.append(", updateTime=");
            sb.append(this.updateTime);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class EnumSetUpdate<T extends Enum<T>> extends Update<EnumSet<T>> {
        public EnumSetUpdate(final EnumSet<T> value) {
            super(value);
        }

        @Override // com.amazon.mp3.playback.casting.CastingStateManager.Update
        public void setValue(EnumSet<T> enumSet) {
            ((EnumSet) this.value).clear();
            if (enumSet != null) {
                ((EnumSet) this.value).addAll(enumSet);
            }
        }
    }

    private static class PositionTrackerUpdate extends Update<PositionTracker> {
        public PositionTrackerUpdate(final PositionTracker value) {
            super(value);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazon.mp3.playback.casting.CastingStateManager.Update
        public void setValue(final PositionTracker value) {
            ((PositionTracker) this.value).copyFrom(value);
        }
    }

    private static class State<T extends MediaItem> {
        public Update<T> mediaItem;
        public Update<PlayStatus> playStatus;
        public Update<PositionTracker> positionTracker;
        public Update<Integer> rating;
        public Update<RepeatMode> repeatMode;
        public EnumSetUpdate<RepeatMode> repeatModes;
        public Update<Boolean> shuffled;
        public EnumSetUpdate<NowPlayingThingShadow.State.Transport> transports;

        private State() {
            this.mediaItem = new Update<>(null);
            RepeatMode repeatMode = RepeatMode.REPEAT_NONE;
            this.repeatModes = new EnumSetUpdate<>(EnumSet.of(repeatMode));
            this.repeatMode = new Update<>(repeatMode);
            this.transports = new EnumSetUpdate<>(EnumSet.noneOf(NowPlayingThingShadow.State.Transport.class));
            this.playStatus = new Update<>(PlayStatus.USER_PAUSED);
            this.shuffled = new Update<>(Boolean.FALSE);
            this.rating = new Update<>(-1);
            this.positionTracker = new PositionTrackerUpdate(new PositionTracker());
        }

        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return Objects.equals(this.mediaItem, state.mediaItem) && Objects.equals(this.repeatModes, state.repeatModes) && Objects.equals(this.repeatMode, state.repeatMode) && Objects.equals(this.transports, state.transports) && Objects.equals(this.positionTracker, state.positionTracker) && Objects.equals(this.playStatus, state.playStatus) && Objects.equals(this.shuffled, state.shuffled) && Objects.equals(this.rating, state.rating);
        }

        public int hashCode() {
            return Objects.hash(this.mediaItem, this.repeatModes, this.repeatMode, this.transports, this.positionTracker, this.playStatus, this.shuffled, this.rating);
        }

        public String toString() {
            return "State{\n      mediaItem=" + this.mediaItem + "\n      repeatModes=" + this.repeatModes + "\n      repeatMode=" + this.repeatMode + "\n      transports=" + this.transports + "\n      playStatus=" + this.playStatus + "\n      shuffled=" + this.shuffled + "\n      rating=" + this.rating + "\n      positionTracker=" + this.positionTracker + "\n  }";
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public void copyFrom(State<? extends T> state) {
            this.mediaItem.copyFrom((Update<? extends T>) state.mediaItem);
            this.repeatModes.copyFrom(state.repeatModes);
            this.repeatMode.copyFrom(state.repeatMode);
            this.transports.copyFrom(state.transports);
            this.playStatus.copyFrom(state.playStatus);
            this.shuffled.copyFrom(state.shuffled);
            this.rating.copyFrom(state.rating);
            this.positionTracker.copyFrom(state.positionTracker);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public void copyValuesFrom(State<? extends T> state) {
            this.mediaItem.copyValueFrom((Update<? extends T>) state.mediaItem);
            this.repeatModes.copyValueFrom(state.repeatModes);
            this.repeatMode.copyValueFrom(state.repeatMode);
            this.transports.copyValueFrom(state.transports);
            this.playStatus.copyValueFrom(state.playStatus);
            this.shuffled.copyValueFrom(state.shuffled);
            this.rating.copyValueFrom(state.rating);
            this.positionTracker.copyValueFrom(state.positionTracker);
        }
    }

    private CastingStateManager() {
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        this.mCastingManager = castingSessionManager;
        this.mOffsetFilter = new OffsetFilter(Clock.getClock());
        this.mRateLimiter = new RateLimiter(new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingStateManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0();
            }
        }, 5L, 50L);
        this.mIncomingState = new State<>();
        this.mRemoteState = new State<>();
        this.mLocalState = new State<>();
        this.mOutgoingState = new State<>();
        this.mPreviousOutgoingState = new State<>();
        this.mCallback = NULL_CALLBACK;
        this.mWaitingToCast = true;
        this.mScheduledUpdate = new Runnable() { // from class: com.amazon.mp3.playback.casting.CastingStateManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$1();
            }
        };
        this.mGetTrackHandler = new GetTrackFromMetadataThingShadowTask.GetTrackFromMetadataThingShadowHandler() { // from class: com.amazon.mp3.playback.casting.CastingStateManager$$ExternalSyntheticLambda2
            @Override // com.amazon.mp3.casting.GetTrackFromMetadataThingShadowTask.GetTrackFromMetadataThingShadowHandler
            public final void onCompleted(MetadataThingShadow.Track track, MusicTrack musicTrack) {
                this.f$0.lambda$new$2(track, musicTrack);
            }
        };
        onSetAvailableFeatures(castingSessionManager.getAvailableFeatures());
    }

    public void setCallback(Callback callback) {
        if (callback == null) {
            callback = NULL_CALLBACK;
        }
        this.mCallback = callback;
    }

    public String toString() {
        return "CastingStateManager{\n  mWaitingToCast=" + this.mWaitingToCast + "\n  mIncomingState: " + this.mIncomingState + "\n  mRemoteState=" + this.mRemoteState + "\n  mLocalState=" + this.mLocalState + "\n  mOutgoingState=" + this.mOutgoingState + "\n  mPreviousOutgoingState=" + this.mPreviousOutgoingState + "\n}";
    }

    public PlayStatus getPlayStatus() {
        return this.mOutgoingState.playStatus.value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MediaItem getCurrentMediaItem() {
        return (MediaItem) this.mOutgoingState.mediaItem.value;
    }

    public EnumSet<RepeatMode> getAvailableRepeatModes() {
        return (EnumSet) this.mOutgoingState.repeatModes.value;
    }

    public RepeatMode getRepeatMode() {
        return this.mOutgoingState.repeatMode.value;
    }

    public boolean isShuffled() {
        return this.mOutgoingState.shuffled.value.booleanValue();
    }

    public boolean canShuffle() {
        return ((EnumSet) this.mOutgoingState.transports.value).contains(NowPlayingThingShadow.State.Transport.SHUFFLE);
    }

    public long getPositionMillis() {
        State<MediaItem> state = this.mOutgoingState;
        if (state.mediaItem.value == 0) {
            return 0L;
        }
        return Math.max(0L, state.positionTracker.value.getCurrentPosition());
    }

    public boolean canSkipNext() {
        return ((EnumSet) this.mOutgoingState.transports.value).contains(NowPlayingThingShadow.State.Transport.NEXT);
    }

    public boolean canSkipPrevious() {
        return ((EnumSet) this.mOutgoingState.transports.value).contains(NowPlayingThingShadow.State.Transport.PREVIOUS);
    }

    public boolean canRateMediaItems() {
        return ((EnumSet) this.mOutgoingState.transports.value).contains(NowPlayingThingShadow.State.Transport.RATINGS);
    }

    public boolean canSeek() {
        return ((EnumSet) this.mOutgoingState.transports.value).contains(NowPlayingThingShadow.State.Transport.SCRUB);
    }

    public void onLocalPlay() {
        Log.verbose(TAG, "onLocalPlay()");
        this.mLocalState.playStatus.set(PlayStatus.RENDERING);
        this.mLocalState.positionTracker.updated();
        lambda$new$1();
    }

    public void onLocalPause() {
        Log.verbose(TAG, "onLocalPause()");
        this.mLocalState.playStatus.set(PlayStatus.USER_PAUSED);
        this.mLocalState.positionTracker.updated();
        lambda$new$1();
    }

    public void onLocalSetShuffled(final boolean shuffled) {
        Log.verbose(TAG, "onLocalSetShuffled(" + shuffled + ")");
        this.mLocalState.shuffled.set(Boolean.valueOf(shuffled));
        lambda$new$1();
    }

    public void onLocalSetRepeatMode(final RepeatMode repeatMode) {
        Log.verbose(TAG, "onLocalSetRepeatMode(" + repeatMode + ")");
        this.mLocalState.repeatMode.set(repeatMode);
        lambda$new$1();
    }

    public void onLocalSetPosition(final long positionMillis) {
        Log.verbose(TAG, "onLocalSetPosition(" + positionMillis + ")");
        this.mLocalState.positionTracker.value.set(positionMillis, Clock.now(), this.mLocalState.positionTracker.value.getRunSpeed());
        this.mLocalState.positionTracker.updated();
        lambda$new$1();
    }

    public void onLocalSkip() {
        Log.verbose(TAG, "onLocalSkip()");
        this.mLocalState.mediaItem.set(null);
        this.mLocalState.playStatus.set(PlayStatus.LOADING);
        this.mLocalState.positionTracker.value.set(0L, Clock.now(), 0.0f);
        this.mLocalState.positionTracker.updated();
        this.mOutgoingState.mediaItem.set(null);
        lambda$new$1();
    }

    public void onWillNotCast() {
        Log.debug(TAG, "onWillNotCast()");
        this.mWaitingToCast = false;
        Update<T> update = this.mLocalState.mediaItem;
        update.value = null;
        update.updateTime = 0L;
        lambda$new$1();
    }

    public void onRemoteCast() {
        Log.debug(TAG, "onRemoteCast()");
        this.mWaitingToCast = false;
        this.mLocalState.mediaItem.set(null);
        lambda$new$1();
    }

    public void onWillCast(final Sequencer sequencer, final long positionMillis) {
        Log.debug(TAG, "onWillCast()");
        this.mWaitingToCast = true;
        updateFromSequencer(sequencer, positionMillis);
        lambda$new$1();
    }

    public void onCast() {
        Log.debug(TAG, "onCast()");
        this.mWaitingToCast = false;
        lambda$new$1();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private void updateFromSequencer(Sequencer sequencer, long j) {
        ((EnumSet) this.mLocalState.transports.value).clear();
        ((EnumSet) this.mLocalState.transports.value).add(NowPlayingThingShadow.State.Transport.PAUSE);
        if (sequencer.getRatingsProvider().canRateMediaItems()) {
            ((EnumSet) this.mLocalState.transports.value).add(NowPlayingThingShadow.State.Transport.RATINGS);
        }
        this.mLocalState.transports.updated();
        this.mLocalState.repeatModes.set(sequencer.getAvailableRepeatModes());
        this.mLocalState.shuffled.set(Boolean.valueOf(sequencer.isShuffled()));
        this.mLocalState.repeatMode.set(sequencer.getRepeatMode());
        this.mLocalState.repeatMode.set(sequencer.getRepeatMode());
        MediaItem currentMediaItem = sequencer.getCurrentMediaItem();
        this.mLocalState.mediaItem.set((T) currentMediaItem);
        if (currentMediaItem != null) {
            this.mLocalState.playStatus.set(PlayStatus.PREPARING);
            this.mLocalState.positionTracker.value.set(j, Clock.now(), 0.0f);
            this.mLocalState.rating.set(Integer.valueOf(currentMediaItem.getRating()));
        } else {
            this.mLocalState.playStatus.set(PlayStatus.LOADING);
            this.mLocalState.positionTracker.value.set(j, 0L, 0.0f);
            this.mLocalState.rating.set(-1);
        }
        this.mLocalState.positionTracker.updated();
    }

    public void onDisconnected(final Sequencer sequencer) {
        String str = TAG;
        Log.debug(str, "onDisconnected()");
        if (sequencer == null) {
            Log.warning(str, "onDisconnected() sequencer is null");
            return;
        }
        MediaItem currentMediaItem = sequencer.getCurrentMediaItem();
        if (currentMediaItem == null) {
            this.mLocalState.positionTracker.value.set(0L, 0L, 0.0f);
        } else if (matches(currentMediaItem, getCurrentMediaItem())) {
            this.mLocalState.positionTracker.value.set(getPositionMillis(), 0L, 0.0f);
        }
        this.mLocalState.positionTracker.updated();
        sequencer.setShuffled(isShuffled(), true);
        sequencer.setRepeatMode(getRepeatMode());
        updateFromSequencer(sequencer, getPositionMillis());
        this.mLocalState.playStatus.set(PlayStatus.USER_PAUSED);
        this.mWaitingToCast = true;
        lambda$new$1();
    }

    @Override // com.amazon.music.casting.session.CastingQueueCallback
    public void onQueueUpdated(final CastingQueueResult result) {
        Log.verbose(TAG, "onQueueUpdated(" + result + ")");
        synchronized (this.mLock) {
            this.queueId = result.getQueueId();
            updateIncomingPosition();
        }
        this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
        lambda$new$1();
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onPlay() {
        Log.verbose(TAG, "onPlay()");
        synchronized (this.mLock) {
            updateIncomingPosition();
            this.mIncomingState.playStatus.set(PlayStatus.RENDERING);
            updateIncomingPosition();
        }
        this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
        this.mRateLimiter.onDataChanged(false);
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onPause() {
        Log.verbose(TAG, "onPause()");
        synchronized (this.mLock) {
            updateIncomingPosition();
            this.mIncomingState.playStatus.set(PlayStatus.USER_PAUSED);
            updateIncomingPosition();
        }
        this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
        this.mRateLimiter.onDataChanged(false);
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onStop() {
        Log.verbose(TAG, "onStop()");
        synchronized (this.mLock) {
            updateIncomingPosition();
            this.mIncomingState.playStatus.set(PlayStatus.USER_PAUSED);
            updateIncomingPosition();
        }
        this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
        this.mRateLimiter.onDataChanged(false);
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onSetShuffled(final boolean shuffled) {
        Log.verbose(TAG, "onSetShuffled(" + shuffled + ")");
        synchronized (this.mLock) {
            this.mIncomingState.shuffled.set(Boolean.valueOf(shuffled));
        }
        this.mRateLimiter.onDataChanged(false);
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onSetLoopMode(final NowPlayingThingShadow.State.LoopMode loopMode) {
        RepeatMode repeatMode;
        Log.verbose(TAG, "onSetLoopMode(" + loopMode + ")");
        int i = AnonymousClass2.$SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$LoopMode[loopMode.ordinal()];
        if (i == 1) {
            repeatMode = RepeatMode.REPEAT_ALL;
        } else if (i == 2) {
            repeatMode = RepeatMode.REPEAT_ONE;
        } else {
            repeatMode = RepeatMode.REPEAT_NONE;
        }
        synchronized (this.mLock) {
            this.mIncomingState.repeatMode.set(repeatMode);
            updateIncomingPosition();
        }
        this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
        this.mRateLimiter.onDataChanged(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onSetRating(final NowPlayingThingShadow.State.Rating shadowRating) {
        Log.verbose(TAG, "onSetRating(" + shadowRating + ")");
        int i = AnonymousClass2.$SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$Rating[shadowRating.ordinal()];
        int i2 = 2;
        if (i == 1) {
            i2 = 1;
        } else if (i != 2) {
            i2 = 0;
        }
        synchronized (this.mLock) {
            T t = this.mIncomingState.mediaItem.value;
            if (t == 0) {
                return;
            }
            ((CastingMediaItem) t).onRatingSet(i2);
            updateIncomingPosition();
            this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
            this.mRateLimiter.onDataChanged(false);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.casting.CastingStateManager$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$LoopMode;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$Rating;

        static {
            int[] iArr = new int[NowPlayingThingShadow.State.Rating.values().length];
            $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$Rating = iArr;
            try {
                iArr[NowPlayingThingShadow.State.Rating.THUMBS_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$Rating[NowPlayingThingShadow.State.Rating.THUMBS_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[NowPlayingThingShadow.State.LoopMode.values().length];
            $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$LoopMode = iArr2;
            try {
                iArr2[NowPlayingThingShadow.State.LoopMode.LOOP_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$LoopMode[NowPlayingThingShadow.State.LoopMode.LOOP_ONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onSetAvailableFeatures(final NowPlayingThingShadow.State.Transport[] transports) {
        Log.verbose(TAG, "onSetAvailableFeatures(" + StringUtils.toString(transports) + ")");
        synchronized (this.mLock) {
            ((EnumSet) this.mIncomingState.transports.value).clear();
            if (transports != null) {
                for (NowPlayingThingShadow.State.Transport transport : transports) {
                    ((EnumSet) this.mIncomingState.transports.value).add(transport);
                }
            }
        }
        this.mRateLimiter.onDataChanged(false);
    }

    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onSetAvailableLoopModes(final NowPlayingThingShadow.State.LoopMode[] loopModes) {
        Log.verbose(TAG, "onSetAvailableLoopModes(" + StringUtils.toString(loopModes) + ")");
        synchronized (this.mLock) {
            ((EnumSet) this.mIncomingState.repeatModes.value).clear();
            ((EnumSet) this.mIncomingState.repeatModes.value).add(RepeatMode.REPEAT_NONE);
            for (NowPlayingThingShadow.State.LoopMode loopMode : loopModes) {
                int i = AnonymousClass2.$SwitchMap$com$amazon$music$casting$session$things$NowPlayingThingShadow$State$LoopMode[loopMode.ordinal()];
                if (i == 1) {
                    ((EnumSet) this.mIncomingState.repeatModes.value).add(RepeatMode.REPEAT_ALL);
                } else if (i == 2) {
                    ((EnumSet) this.mIncomingState.repeatModes.value).add(RepeatMode.REPEAT_ONE);
                }
            }
        }
        this.mRateLimiter.onDataChanged(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.casting.session.CastingPlaybackCallback
    public void onTracksMetadataUpdated(MetadataThingShadow.Track[] trackArr) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onTracksMetadataUpdated(");
        sb.append((trackArr == null || trackArr.length == 0) ? "null" : StringUtils.shouldRedact() ? "non-null" : trackArr[0]);
        sb.append(")");
        Log.verbose(str, sb.toString());
        synchronized (this.mLock) {
            if (trackArr != null) {
                if (trackArr.length != 0 && trackArr[0] != null) {
                    updateIncomingPosition();
                    boolean zIsSame = isSame(trackArr[0], this.mTrack);
                    MetadataThingShadow.Track track = trackArr[0];
                    this.mTrack = track;
                    this.queueId = track.queueMetadata.queueId;
                    Log.debug(str, "mTrack is set to: " + this.mTrack.name + " from track list of size: " + trackArr.length);
                    if (isSame(this.mTrack, (MediaItem) this.mIncomingState.mediaItem.value)) {
                        ((CastingMediaItem) this.mIncomingState.mediaItem.value).update(this.mTrack);
                        updateIncomingPosition();
                        this.mRateLimiter.onDataChanged(false);
                    } else if (!zIsSame) {
                        new GetTrackFromMetadataThingShadowTask(Playback.getInstance().getPlaybackConfig().getContext(), this.mTrack, this.mGetTrackHandler).execute(new Void[0]);
                    }
                    this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
                    return;
                }
            }
            Update<T> update = this.mIncomingState.mediaItem;
            boolean z = update.value != 0;
            update.value = null;
            this.mTrack = null;
            if (z) {
                this.mRateLimiter.onDataChanged(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /* JADX INFO: renamed from: onTrackLoaded, reason: merged with bridge method [inline-methods] */
    public void lambda$new$2(@Nullable MetadataThingShadow.Track track, @Nullable MusicTrack musicTrack) {
        synchronized (this.mLock) {
            if (isSame(track, this.mTrack) && !isSame(track, (MediaItem) this.mIncomingState.mediaItem.value) && musicTrack != null) {
                this.mIncomingState.mediaItem.set(new CastingMediaItem(this.mTrack, musicTrack));
                updateIncomingPosition();
                this.mCallback.onPositionChanged(this.mIncomingState.positionTracker.value.getCurrentPosition());
                this.mRateLimiter.onDataChanged(false);
            }
        }
    }

    private boolean isSame(final MetadataThingShadow.Track track1, final MetadataThingShadow.Track track2) {
        if (track1 == null || track2 == null) {
            return track1 == track2;
        }
        return track1.trackId.equals(track2.trackId);
    }

    private boolean isSame(@NonNull final MetadataThingShadow.Track track, @Nullable final MediaItem mediaItem) {
        String mediaItemIdString;
        return (mediaItem == null || (mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.MPQS, null)) == null || !mediaItemIdString.equals(track.trackId)) ? false : true;
    }

    private void updateIncomingPosition() {
        Long l;
        NowPlayingThingShadow.State.ReportedNode.TrackInfo trackInfo = this.mCastingManager.getTrackInfo();
        State<CastingMediaItem> state = this.mIncomingState;
        float f = state.playStatus.value == PlayStatus.RENDERING ? 1.0f : 0.0f;
        PositionTracker positionTracker = state.positionTracker.value;
        if (trackInfo == null || trackInfo.positionInMs == null || (l = trackInfo.positionInMsTimeStamp) == null || trackInfo.maximumPositionInMs == null || state.mediaItem.value == 0) {
            positionTracker.setRunSpeed(f);
            return;
        }
        long jLongValue = l.longValue() + Clock.getClock().getSystemTimeOffsetMillis();
        this.mOffsetFilter.addTimeSample(jLongValue);
        long time = this.mOffsetFilter.getTime(jLongValue);
        if (time == this.mIncomingState.positionTracker.updateTime) {
            positionTracker.setRunSpeed(f);
            return;
        }
        if (trackInfo.positionInMs.longValue() == positionTracker.getCheckpointPosition()) {
            positionTracker.setRunSpeed(f);
        } else {
            positionTracker.set(trackInfo.positionInMs.longValue(), time, f);
        }
        this.mIncomingState.positionTracker.updateTime = time;
        this.mRateLimiter.onDataChanged(false);
    }

    private void updateOutgoingPosition() {
        if (this.mWaitingToCast) {
            this.mOutgoingState.positionTracker.copyFrom(this.mLocalState.positionTracker);
            return;
        }
        if (this.mLocalState.positionTracker.isOlderThan(CHANGE_FAIL_MILLIS)) {
            this.mOutgoingState.positionTracker.copyFrom(this.mIncomingState.positionTracker);
            return;
        }
        State<MediaItem> state = this.mOutgoingState;
        T t = state.mediaItem.value;
        State<CastingMediaItem> state2 = this.mIncomingState;
        if (t != state2.mediaItem.value) {
            state.positionTracker.copyFrom(this.mLocalState.positionTracker);
            this.mLocalState.positionTracker.updateTime = 0L;
            return;
        }
        if (t != this.mLocalState.mediaItem.value) {
            state.positionTracker.copyFrom(state2.positionTracker);
            return;
        }
        long currentPosition = state2.positionTracker.value.getCurrentPosition();
        long currentPosition2 = this.mRemoteState.positionTracker.value.getCurrentPosition();
        long currentPosition3 = this.mLocalState.positionTracker.value.getCurrentPosition();
        if (currentPosition >= this.mLocalState.positionTracker.value.getCheckpointPosition() && (currentPosition3 > currentPosition2 || currentPosition < (currentPosition3 + currentPosition2) / 2)) {
            State<CastingMediaItem> state3 = this.mIncomingState;
            if (state3.playStatus.value != PlayStatus.USER_PAUSED || currentPosition >= currentPosition2) {
                this.mOutgoingState.positionTracker.copyFrom(state3.positionTracker);
                this.mLocalState.positionTracker.updateTime = 0L;
                return;
            }
        }
        this.mOutgoingState.positionTracker.copyFrom(this.mLocalState.positionTracker);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long getUpdateTime(final Update<?> local, final Update<?> incoming, final Update<?> outgoing, final long now, final long otherwiseTime) {
        T t = incoming.value;
        if (t != 0 && !t.equals(outgoing.value)) {
            long j = local.updateTime + CHANGE_FAIL_MILLIS;
            if (j >= now && j <= otherwiseTime) {
                return j;
            }
        }
        return otherwiseTime;
    }

    private void scheduleUpdate(final long now) {
        long updateTime = getUpdateTime(this.mLocalState.positionTracker, this.mIncomingState.positionTracker, this.mOutgoingState.positionTracker, now, getUpdateTime(this.mLocalState.rating, this.mIncomingState.rating, this.mOutgoingState.rating, now, getUpdateTime(this.mLocalState.shuffled, this.mIncomingState.shuffled, this.mOutgoingState.shuffled, now, getUpdateTime(this.mLocalState.playStatus, this.mIncomingState.playStatus, this.mOutgoingState.playStatus, now, getUpdateTime(this.mLocalState.transports, this.mIncomingState.transports, this.mOutgoingState.transports, now, getUpdateTime(this.mLocalState.repeatMode, this.mIncomingState.repeatMode, this.mOutgoingState.repeatMode, now, getUpdateTime(this.mLocalState.repeatModes, this.mIncomingState.repeatModes, this.mOutgoingState.repeatModes, now, getUpdateTime(this.mLocalState.mediaItem, this.mIncomingState.mediaItem, this.mOutgoingState.mediaItem, now, Long.MAX_VALUE))))))));
        ThreadUtils.cancelOnMainThread(this.mScheduledUpdate);
        if (updateTime != Long.MAX_VALUE) {
            ThreadUtils.postOnMainThreadDelayed(this.mScheduledUpdate, (updateTime + 10) - now);
        }
    }

    private boolean matches(final MediaItem item1, final MediaItem item2) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }
        return item1.hasSameId(item2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void updateMediaItem(final long now) {
        if (this.mWaitingToCast) {
            this.mOutgoingState.mediaItem.copyFrom(this.mLocalState.mediaItem);
        } else if (this.mLocalState.mediaItem.isOlderThan(CHANGE_FAIL_MILLIS)) {
            this.mOutgoingState.mediaItem.copyFrom(this.mIncomingState.mediaItem);
        } else {
            T t = this.mIncomingState.mediaItem.value;
            if (t == 0) {
                this.mOutgoingState.mediaItem.copyFrom(this.mLocalState.mediaItem);
            } else if (matches((MediaItem) this.mLocalState.mediaItem.value, (MediaItem) t)) {
                this.mOutgoingState.mediaItem.copyFrom(this.mIncomingState.mediaItem);
            } else {
                Update<T> update = this.mIncomingState.mediaItem;
                T t2 = update.value;
                T t3 = this.mRemoteState.mediaItem.value;
                if (t2 == t3 || t3 == 0 || update.updateTime < this.mLocalState.mediaItem.updateTime) {
                    this.mOutgoingState.mediaItem.copyFrom(this.mLocalState.mediaItem);
                } else {
                    this.mOutgoingState.mediaItem.copyFrom(update);
                }
            }
        }
        T t4 = this.mOutgoingState.mediaItem.value;
        if (t4 != this.mIncomingState.mediaItem.value || t4 == 0) {
            return;
        }
        this.mLocalState.mediaItem.updateTime = 0L;
    }

    private <T> void update(final Update<? extends T> local, final Update<? extends T> remote, final Update<? extends T> incoming, final Update<T> outgoing, final boolean favorLocal) {
        if (this.mWaitingToCast) {
            outgoing.copyFrom(local);
            return;
        }
        if (local.isOlderThan(CHANGE_FAIL_MILLIS)) {
            outgoing.copyFrom(incoming);
        } else if (favorLocal || incoming.value.equals(remote.value) || this.mOutgoingState.mediaItem.value != this.mIncomingState.mediaItem.value) {
            outgoing.copyFrom(local);
        } else {
            outgoing.copyFrom(incoming);
        }
    }

    private void udpatePlayStatus() {
        Update<PlayStatus> update = this.mLocalState.playStatus;
        PlayStatus playStatus = update.value;
        PlayStatus playStatus2 = this.mRemoteState.playStatus.value;
        State<CastingMediaItem> state = this.mIncomingState;
        PlayStatus playStatus3 = state.playStatus.value;
        if (this.mOutgoingState.mediaItem.value != state.mediaItem.value || this.mWaitingToCast) {
            playStatus3 = playStatus.willPlay() ? PlayStatus.LOADING : PlayStatus.USER_PAUSED;
        } else if (!update.isOlderThan(CHANGE_FAIL_MILLIS)) {
            if (playStatus.willPlay() && !playStatus3.willPlay()) {
                playStatus3 = PlayStatus.BUFFERING;
            } else if (!playStatus.willPlay()) {
                playStatus3 = PlayStatus.USER_PAUSED;
            }
        }
        this.mOutgoingState.playStatus.setValue(playStatus3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: update, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$new$1() {
        this.mPreviousOutgoingState.copyFrom(this.mOutgoingState);
        synchronized (this.mLock) {
            long jNow = Clock.now();
            updateMediaItem(jNow);
            State<MediaItem> state = this.mOutgoingState;
            T t = state.mediaItem.value;
            State<CastingMediaItem> state2 = this.mIncomingState;
            if (t == state2.mediaItem.value && !this.mWaitingToCast) {
                state.repeatModes.copyValueFrom(state2.repeatModes);
                this.mOutgoingState.transports.copyValueFrom(this.mIncomingState.transports);
                this.mLocalState.repeatModes.copyValueFrom(this.mIncomingState.repeatModes);
                this.mLocalState.transports.copyValueFrom(this.mIncomingState.transports);
            } else {
                state.repeatModes.copyValueFrom(this.mLocalState.repeatModes);
                this.mOutgoingState.transports.copyValueFrom(this.mLocalState.transports);
            }
            update(this.mLocalState.repeatMode, this.mRemoteState.repeatMode, this.mIncomingState.repeatMode, this.mOutgoingState.repeatMode, true);
            update(this.mLocalState.shuffled, this.mRemoteState.shuffled, this.mIncomingState.shuffled, this.mOutgoingState.shuffled, true);
            update(this.mLocalState.rating, this.mRemoteState.rating, this.mIncomingState.rating, this.mOutgoingState.rating, true);
            updateOutgoingPosition();
            udpatePlayStatus();
            this.mRemoteState.copyFrom(this.mIncomingState);
            if (!this.mOutgoingState.equals(this.mPreviousOutgoingState)) {
                this.mCallback.onPlayStateChanged();
            }
            T t2 = this.mOutgoingState.mediaItem.value;
            if (t2 != 0 && t2 != this.mPreviousOutgoingState.mediaItem.value && t2 != this.mLocalState.mediaItem.value) {
                this.mCallback.onMediaItemChanged((MediaItem) t2);
            }
            this.mPreviousOutgoingState.copyFrom(this.mOutgoingState);
            this.mLocalState.copyValuesFrom(this.mOutgoingState);
            scheduleUpdate(jNow);
        }
    }

    @Override // com.amazon.mp3.playback.casting.CastingLatencyCallback
    public void trackCastingTransferQueue(final LatencyTrackingLeg latencyLeg) {
        if (Feature.casting_v2.isEnabled()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            LatencyTrackingLeg latencyTrackingLeg = LatencyTrackingLeg.REQUEST;
            if (latencyLeg == latencyTrackingLeg) {
                this.mLatencyCastingTransferQueue.clear();
                this.mLatencyCastingTransferQueue.put(latencyLeg, Long.valueOf(jCurrentTimeMillis));
            } else if (this.mLatencyCastingTransferQueue.containsKey(latencyTrackingLeg)) {
                this.mLatencyCastingTransferQueue.put(latencyLeg, Long.valueOf(jCurrentTimeMillis));
                if (latencyLeg == LatencyTrackingLeg.LOAD_COMPLETE) {
                    LatencyTracker.INSTANCE.track(PageLoadLatencyCode.CASTING_TRANSFER_QUEUE, this.mLatencyCastingTransferQueue);
                    this.mLatencyCastingTransferQueue.clear();
                }
            }
        }
    }

    public String getQueueId() {
        return this.queueId;
    }
}
