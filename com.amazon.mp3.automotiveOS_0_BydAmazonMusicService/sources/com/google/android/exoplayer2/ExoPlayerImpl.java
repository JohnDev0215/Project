package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerImplInternal;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes6.dex */
final class ExoPlayerImpl implements ExoPlayer {
    private final TrackSelectionArray emptyTrackSelections;
    private final Handler eventHandler;
    private final ExoPlayerImplInternal internalPlayer;
    private boolean isLoading;
    private final CopyOnWriteArraySet<Player.EventListener> listeners;
    private Object manifest;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private int pendingPrepareAcks;
    private int pendingSeekAcks;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private ExoPlayerImplInternal.PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private int playbackState;
    private final Renderer[] renderers;
    private int repeatMode;
    private Timeline timeline;
    private TrackGroupArray trackGroups;
    private TrackSelectionArray trackSelections;
    private final TrackSelector trackSelector;
    private boolean tracksSelected;
    private final Timeline.Window window;

    @SuppressLint({"HandlerLeak"})
    public ExoPlayerImpl(Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl) {
        Log.i("ExoPlayerImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [ExoPlayerLib/2.5.4] [" + Util.DEVICE_DEBUG_INFO + "]");
        Assertions.checkState(rendererArr.length > 0);
        this.renderers = (Renderer[]) Assertions.checkNotNull(rendererArr);
        this.trackSelector = (TrackSelector) Assertions.checkNotNull(trackSelector);
        this.playWhenReady = false;
        this.repeatMode = 0;
        this.playbackState = 1;
        this.listeners = new CopyOnWriteArraySet<>();
        TrackSelectionArray trackSelectionArray = new TrackSelectionArray(new TrackSelection[rendererArr.length]);
        this.emptyTrackSelections = trackSelectionArray;
        this.timeline = Timeline.EMPTY;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.trackGroups = TrackGroupArray.EMPTY;
        this.trackSelections = trackSelectionArray;
        this.playbackParameters = PlaybackParameters.DEFAULT;
        Handler handler = new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()) { // from class: com.google.android.exoplayer2.ExoPlayerImpl.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                ExoPlayerImpl.this.handleEvent(message);
            }
        };
        this.eventHandler = handler;
        ExoPlayerImplInternal.PlaybackInfo playbackInfo = new ExoPlayerImplInternal.PlaybackInfo(0, 0L);
        this.playbackInfo = playbackInfo;
        this.internalPlayer = new ExoPlayerImplInternal(rendererArr, trackSelector, loadControl, this.playWhenReady, this.repeatMode, handler, playbackInfo, this);
    }

    @Override // com.google.android.exoplayer2.Player
    public void addListener(Player.EventListener eventListener) {
        this.listeners.add(eventListener);
    }

    @Override // com.google.android.exoplayer2.Player
    public void removeListener(Player.EventListener eventListener) {
        this.listeners.remove(eventListener);
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getPlaybackState() {
        return this.playbackState;
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void prepare(MediaSource mediaSource) {
        prepare(mediaSource, true, true);
    }

    public void prepare(MediaSource mediaSource, boolean z, boolean z2) {
        if (z2) {
            if (!this.timeline.isEmpty() || this.manifest != null) {
                this.timeline = Timeline.EMPTY;
                this.manifest = null;
                Iterator<Player.EventListener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onTimelineChanged(this.timeline, this.manifest);
                }
            }
            if (this.tracksSelected) {
                this.tracksSelected = false;
                this.trackGroups = TrackGroupArray.EMPTY;
                this.trackSelections = this.emptyTrackSelections;
                this.trackSelector.onSelectionActivated(null);
                Iterator<Player.EventListener> it2 = this.listeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onTracksChanged(this.trackGroups, this.trackSelections);
                }
            }
        }
        this.pendingPrepareAcks++;
        this.internalPlayer.prepare(mediaSource, z);
    }

    @Override // com.google.android.exoplayer2.Player
    public void setPlayWhenReady(boolean z) {
        if (this.playWhenReady != z) {
            this.playWhenReady = z;
            this.internalPlayer.setPlayWhenReady(z);
            Iterator<Player.EventListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onPlayerStateChanged(z, this.playbackState);
            }
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public void setRepeatMode(int i) {
        if (this.repeatMode != i) {
            this.repeatMode = i;
            this.internalPlayer.setRepeatMode(i);
            Iterator<Player.EventListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onRepeatModeChanged(i);
            }
        }
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getRepeatMode() {
        return this.repeatMode;
    }

    @Override // com.google.android.exoplayer2.Player
    public void seekTo(long j) {
        seekTo(getCurrentWindowIndex(), j);
    }

    public void seekTo(int i, long j) {
        if (i < 0 || (!this.timeline.isEmpty() && i >= this.timeline.getWindowCount())) {
            throw new IllegalSeekPositionException(this.timeline, i, j);
        }
        this.pendingSeekAcks++;
        this.maskingWindowIndex = i;
        if (this.timeline.isEmpty()) {
            this.maskingPeriodIndex = 0;
        } else {
            this.timeline.getWindow(i, this.window);
            long defaultPositionUs = j == -9223372036854775807L ? this.window.getDefaultPositionUs() : C.msToUs(j);
            Timeline.Window window = this.window;
            int i2 = window.firstPeriodIndex;
            long positionInFirstPeriodUs = window.getPositionInFirstPeriodUs() + defaultPositionUs;
            long durationUs = this.timeline.getPeriod(i2, this.period).getDurationUs();
            while (durationUs != -9223372036854775807L && positionInFirstPeriodUs >= durationUs && i2 < this.window.lastPeriodIndex) {
                positionInFirstPeriodUs -= durationUs;
                i2++;
                durationUs = this.timeline.getPeriod(i2, this.period).getDurationUs();
            }
            this.maskingPeriodIndex = i2;
        }
        if (j == -9223372036854775807L) {
            this.maskingWindowPositionMs = 0L;
            this.internalPlayer.seekTo(this.timeline, i, -9223372036854775807L);
            return;
        }
        this.maskingWindowPositionMs = j;
        this.internalPlayer.seekTo(this.timeline, i, C.msToUs(j));
        Iterator<Player.EventListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onPositionDiscontinuity();
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (playbackParameters == null) {
            playbackParameters = PlaybackParameters.DEFAULT;
        }
        this.internalPlayer.setPlaybackParameters(playbackParameters);
    }

    @Override // com.google.android.exoplayer2.Player
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // com.google.android.exoplayer2.Player
    public void stop() {
        this.internalPlayer.stop();
    }

    @Override // com.google.android.exoplayer2.Player
    public void release() {
        Log.i("ExoPlayerImpl", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [ExoPlayerLib/2.5.4] [" + Util.DEVICE_DEBUG_INFO + "] [" + ExoPlayerLibraryInfo.registeredModules() + "]");
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void sendMessages(ExoPlayer.ExoPlayerMessage... exoPlayerMessageArr) {
        this.internalPlayer.sendMessages(exoPlayerMessageArr);
    }

    @Override // com.google.android.exoplayer2.ExoPlayer
    public void blockingSendMessages(ExoPlayer.ExoPlayerMessage... exoPlayerMessageArr) {
        this.internalPlayer.blockingSendMessages(exoPlayerMessageArr);
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getCurrentPeriodIndex() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingPeriodIndex;
        }
        return this.playbackInfo.periodId.periodIndex;
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getCurrentWindowIndex() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingWindowIndex;
        }
        return this.timeline.getPeriod(this.playbackInfo.periodId.periodIndex, this.period).windowIndex;
    }

    @Override // com.google.android.exoplayer2.Player
    public long getDuration() {
        if (this.timeline.isEmpty()) {
            return -9223372036854775807L;
        }
        if (isPlayingAd()) {
            MediaSource.MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
            this.timeline.getPeriod(mediaPeriodId.periodIndex, this.period);
            return C.usToMs(this.period.getAdDurationUs(mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup));
        }
        return this.timeline.getWindow(getCurrentWindowIndex(), this.window).getDurationMs();
    }

    @Override // com.google.android.exoplayer2.Player
    public long getCurrentPosition() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingWindowPositionMs;
        }
        return playbackInfoPositionUsToWindowPositionMs(this.playbackInfo.positionUs);
    }

    @Override // com.google.android.exoplayer2.Player
    public long getBufferedPosition() {
        if (this.timeline.isEmpty() || this.pendingSeekAcks > 0) {
            return this.maskingWindowPositionMs;
        }
        return playbackInfoPositionUsToWindowPositionMs(this.playbackInfo.bufferedPositionUs);
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getBufferedPercentage() {
        if (this.timeline.isEmpty()) {
            return 0;
        }
        long bufferedPosition = getBufferedPosition();
        long duration = getDuration();
        if (bufferedPosition == -9223372036854775807L || duration == -9223372036854775807L) {
            return 0;
        }
        if (duration == 0) {
            return 100;
        }
        return Util.constrainValue((int) ((bufferedPosition * 100) / duration), 0, 100);
    }

    public boolean isPlayingAd() {
        return !this.timeline.isEmpty() && this.pendingSeekAcks == 0 && this.playbackInfo.periodId.isAd();
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getCurrentAdGroupIndex() {
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adGroupIndex;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getCurrentAdIndexInAdGroup() {
        if (isPlayingAd()) {
            return this.playbackInfo.periodId.adIndexInAdGroup;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.ExoPlayer, com.google.android.exoplayer2.Player
    public int getRendererCount() {
        return this.renderers.length;
    }

    void handleEvent(Message message) {
        switch (message.what) {
            case 0:
                this.pendingPrepareAcks--;
                return;
            case 1:
                this.playbackState = message.arg1;
                Iterator<Player.EventListener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onPlayerStateChanged(this.playWhenReady, this.playbackState);
                }
                return;
            case 2:
                this.isLoading = message.arg1 != 0;
                Iterator<Player.EventListener> it2 = this.listeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onLoadingChanged(this.isLoading);
                }
                return;
            case 3:
                if (this.pendingPrepareAcks == 0) {
                    TrackSelectorResult trackSelectorResult = (TrackSelectorResult) message.obj;
                    this.tracksSelected = true;
                    this.trackGroups = trackSelectorResult.groups;
                    this.trackSelections = trackSelectorResult.selections;
                    this.trackSelector.onSelectionActivated(trackSelectorResult.info);
                    Iterator<Player.EventListener> it3 = this.listeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onTracksChanged(this.trackGroups, this.trackSelections);
                    }
                    return;
                }
                return;
            case 4:
                int i = this.pendingSeekAcks - 1;
                this.pendingSeekAcks = i;
                if (i == 0) {
                    this.playbackInfo = (ExoPlayerImplInternal.PlaybackInfo) message.obj;
                    if (this.timeline.isEmpty()) {
                        this.maskingPeriodIndex = 0;
                        this.maskingWindowIndex = 0;
                        this.maskingWindowPositionMs = 0L;
                    }
                    if (message.arg1 != 0) {
                        Iterator<Player.EventListener> it4 = this.listeners.iterator();
                        while (it4.hasNext()) {
                            it4.next().onPositionDiscontinuity();
                        }
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.pendingSeekAcks == 0) {
                    this.playbackInfo = (ExoPlayerImplInternal.PlaybackInfo) message.obj;
                    Iterator<Player.EventListener> it5 = this.listeners.iterator();
                    while (it5.hasNext()) {
                        it5.next().onPositionDiscontinuity();
                    }
                    return;
                }
                return;
            case 6:
                ExoPlayerImplInternal.SourceInfo sourceInfo = (ExoPlayerImplInternal.SourceInfo) message.obj;
                int i2 = this.pendingSeekAcks - sourceInfo.seekAcks;
                this.pendingSeekAcks = i2;
                if (this.pendingPrepareAcks == 0) {
                    Timeline timeline = sourceInfo.timeline;
                    this.timeline = timeline;
                    this.manifest = sourceInfo.manifest;
                    this.playbackInfo = sourceInfo.playbackInfo;
                    if (i2 == 0 && timeline.isEmpty()) {
                        this.maskingPeriodIndex = 0;
                        this.maskingWindowIndex = 0;
                        this.maskingWindowPositionMs = 0L;
                    }
                    Iterator<Player.EventListener> it6 = this.listeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onTimelineChanged(this.timeline, this.manifest);
                    }
                    return;
                }
                return;
            case 7:
                PlaybackParameters playbackParameters = (PlaybackParameters) message.obj;
                if (this.playbackParameters.equals(playbackParameters)) {
                    return;
                }
                this.playbackParameters = playbackParameters;
                Iterator<Player.EventListener> it7 = this.listeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onPlaybackParametersChanged(playbackParameters);
                }
                return;
            case 8:
                ExoPlaybackException exoPlaybackException = (ExoPlaybackException) message.obj;
                Iterator<Player.EventListener> it8 = this.listeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onPlayerError(exoPlaybackException);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }

    private long playbackInfoPositionUsToWindowPositionMs(long j) {
        long jUsToMs = C.usToMs(j);
        if (this.playbackInfo.periodId.isAd()) {
            return jUsToMs;
        }
        this.timeline.getPeriod(this.playbackInfo.periodId.periodIndex, this.period);
        return jUsToMs + this.period.getPositionInWindowMs();
    }
}
