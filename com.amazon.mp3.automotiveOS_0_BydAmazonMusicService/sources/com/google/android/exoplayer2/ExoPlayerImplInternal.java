package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaPeriodInfoSequence;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ClippingMediaPeriod;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.StandaloneMediaClock;
import com.google.android.exoplayer2.util.TraceUtil;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSource.Listener {
    private int customMessagesProcessed;
    private int customMessagesSent;
    private long elapsedRealtimeUs;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final Handler handler;
    private final HandlerThread internalPlaybackThread;
    private boolean isLoading;
    private final LoadControl loadControl;
    private MediaPeriodHolder loadingPeriodHolder;
    private final MediaPeriodInfoSequence mediaPeriodInfoSequence;
    private MediaSource mediaSource;
    private int pendingInitialSeekCount;
    private SeekPosition pendingSeekPosition;
    private final Timeline.Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private final ExoPlayer player;
    private MediaPeriodHolder playingPeriodHolder;
    private MediaPeriodHolder readingPeriodHolder;
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private MediaClock rendererMediaClock;
    private Renderer rendererMediaClockSource;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final StandaloneMediaClock standaloneMediaClock;
    private int state = 1;
    private Timeline timeline;
    private final TrackSelector trackSelector;
    private final Timeline.Window window;

    public static final class PlaybackInfo {
        public volatile long bufferedPositionUs;
        public final long contentPositionUs;
        public final MediaSource.MediaPeriodId periodId;
        public volatile long positionUs;
        public final long startPositionUs;

        public PlaybackInfo(int i, long j) {
            this(new MediaSource.MediaPeriodId(i), j);
        }

        public PlaybackInfo(MediaSource.MediaPeriodId mediaPeriodId, long j) {
            this(mediaPeriodId, j, -9223372036854775807L);
        }

        public PlaybackInfo(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2) {
            this.periodId = mediaPeriodId;
            this.startPositionUs = j;
            this.contentPositionUs = j2;
            this.positionUs = j;
            this.bufferedPositionUs = j;
        }

        public PlaybackInfo copyWithPeriodIndex(int i) {
            PlaybackInfo playbackInfo = new PlaybackInfo(this.periodId.copyWithPeriodIndex(i), this.startPositionUs, this.contentPositionUs);
            playbackInfo.positionUs = this.positionUs;
            playbackInfo.bufferedPositionUs = this.bufferedPositionUs;
            return playbackInfo;
        }
    }

    public static final class SourceInfo {
        public final Object manifest;
        public final PlaybackInfo playbackInfo;
        public final int seekAcks;
        public final Timeline timeline;

        public SourceInfo(Timeline timeline, Object obj, PlaybackInfo playbackInfo, int i) {
            this.timeline = timeline;
            this.manifest = obj;
            this.playbackInfo = playbackInfo;
            this.seekAcks = i;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector, LoadControl loadControl, boolean z, int i, Handler handler, PlaybackInfo playbackInfo, ExoPlayer exoPlayer) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector;
        this.loadControl = loadControl;
        this.playWhenReady = z;
        this.repeatMode = i;
        this.eventHandler = handler;
        this.playbackInfo = playbackInfo;
        this.player = exoPlayer;
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            rendererArr[i2].setIndex(i2);
            this.rendererCapabilities[i2] = rendererArr[i2].getCapabilities();
        }
        this.standaloneMediaClock = new StandaloneMediaClock();
        this.enabledRenderers = new Renderer[0];
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.mediaPeriodInfoSequence = new MediaPeriodInfoSequence();
        trackSelector.init(this);
        this.playbackParameters = PlaybackParameters.DEFAULT;
        HandlerThread handlerThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper(), this);
    }

    public void prepare(MediaSource mediaSource, boolean z) {
        this.handler.obtainMessage(0, z ? 1 : 0, 0, mediaSource).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void setRepeatMode(int i) {
        this.handler.obtainMessage(12, i, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i, j)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void stop() {
        this.handler.sendEmptyMessage(5);
    }

    public void sendMessages(ExoPlayer.ExoPlayerMessage... exoPlayerMessageArr) {
        if (this.released) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
        } else {
            this.customMessagesSent++;
            this.handler.obtainMessage(11, exoPlayerMessageArr).sendToTarget();
        }
    }

    public synchronized void blockingSendMessages(ExoPlayer.ExoPlayerMessage... exoPlayerMessageArr) {
        if (this.released) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            return;
        }
        int i = this.customMessagesSent;
        this.customMessagesSent = i + 1;
        this.handler.obtainMessage(11, exoPlayerMessageArr).sendToTarget();
        boolean z = false;
        while (this.customMessagesProcessed <= i) {
            try {
                wait();
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void release() {
        if (this.released) {
            return;
        }
        this.handler.sendEmptyMessage(6);
        boolean z = false;
        while (!this.released) {
            try {
                wait();
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        this.internalPlaybackThread.quit();
    }

    @Override // com.google.android.exoplayer2.source.MediaSource.Listener
    public void onSourceInfoRefreshed(Timeline timeline, Object obj) {
        this.handler.obtainMessage(7, Pair.create(timeline, obj)).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(8, mediaPeriod).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    prepareInternal((MediaSource) message.obj, message.arg1 != 0);
                    return true;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    return true;
                case 2:
                    doSomeWork();
                    return true;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    return true;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    return true;
                case 5:
                    stopInternal();
                    return true;
                case 6:
                    releaseInternal();
                    return true;
                case 7:
                    handleSourceInfoRefreshed((Pair) message.obj);
                    return true;
                case 8:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    return true;
                case 9:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    return true;
                case 10:
                    reselectTracksInternal();
                    return true;
                case 11:
                    sendMessagesInternal((ExoPlayer.ExoPlayerMessage[]) message.obj);
                    return true;
                case 12:
                    setRepeatModeInternal(message.arg1);
                    return true;
                default:
                    return false;
            }
        } catch (ExoPlaybackException e) {
            Log.e("ExoPlayerImplInternal", "Renderer error.", e);
            this.eventHandler.obtainMessage(8, e).sendToTarget();
            stopInternal();
            return true;
        } catch (IOException e2) {
            Log.e("ExoPlayerImplInternal", "Source error.", e2);
            this.eventHandler.obtainMessage(8, ExoPlaybackException.createForSource(e2)).sendToTarget();
            stopInternal();
            return true;
        } catch (RuntimeException e3) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", e3);
            this.eventHandler.obtainMessage(8, ExoPlaybackException.createForUnexpected(e3)).sendToTarget();
            stopInternal();
            return true;
        }
    }

    private void setState(int i) {
        if (this.state != i) {
            this.state = i;
            this.eventHandler.obtainMessage(1, i, 0).sendToTarget();
        }
    }

    private void setIsLoading(boolean z) {
        if (this.isLoading != z) {
            this.isLoading = z;
            this.eventHandler.obtainMessage(2, z ? 1 : 0, 0).sendToTarget();
        }
    }

    private void prepareInternal(MediaSource mediaSource, boolean z) {
        this.eventHandler.sendEmptyMessage(0);
        resetInternal(true);
        this.loadControl.onPrepared();
        if (z) {
            this.playbackInfo = new PlaybackInfo(0, -9223372036854775807L);
        } else {
            PlaybackInfo playbackInfo = this.playbackInfo;
            this.playbackInfo = new PlaybackInfo(playbackInfo.periodId, playbackInfo.positionUs, this.playbackInfo.contentPositionUs);
        }
        this.mediaSource = mediaSource;
        mediaSource.prepareSource(this.player, true, this);
        setState(2);
        this.handler.sendEmptyMessage(2);
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        this.rebuffering = false;
        this.playWhenReady = z;
        if (!z) {
            stopRenderers();
            updatePlaybackPositions();
            return;
        }
        int i = this.state;
        if (i == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (i == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int i) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder;
        MediaPeriodHolder mediaPeriodHolder2;
        this.repeatMode = i;
        this.mediaPeriodInfoSequence.setRepeatMode(i);
        MediaPeriodHolder mediaPeriodHolder3 = this.playingPeriodHolder;
        if (mediaPeriodHolder3 == null) {
            mediaPeriodHolder3 = this.loadingPeriodHolder;
        }
        if (mediaPeriodHolder3 == null) {
            return;
        }
        while (true) {
            int nextPeriodIndex = this.timeline.getNextPeriodIndex(mediaPeriodHolder3.info.id.periodIndex, this.period, this.window, i);
            while (true) {
                mediaPeriodHolder = mediaPeriodHolder3.next;
                if (mediaPeriodHolder == null || mediaPeriodHolder3.info.isLastInTimelinePeriod) {
                    break;
                } else {
                    mediaPeriodHolder3 = mediaPeriodHolder;
                }
            }
            if (nextPeriodIndex == -1 || mediaPeriodHolder == null || mediaPeriodHolder.info.id.periodIndex != nextPeriodIndex) {
                break;
            } else {
                mediaPeriodHolder3 = mediaPeriodHolder;
            }
        }
        int i2 = this.loadingPeriodHolder.index;
        MediaPeriodHolder mediaPeriodHolder4 = this.readingPeriodHolder;
        int i3 = mediaPeriodHolder4 != null ? mediaPeriodHolder4.index : -1;
        if (mediaPeriodHolder != null) {
            releasePeriodHoldersFrom(mediaPeriodHolder);
            mediaPeriodHolder3.next = null;
        }
        mediaPeriodHolder3.info = this.mediaPeriodInfoSequence.getUpdatedMediaPeriodInfo(mediaPeriodHolder3.info);
        int i4 = mediaPeriodHolder3.index;
        if (!(i2 <= i4)) {
            this.loadingPeriodHolder = mediaPeriodHolder3;
        }
        if ((i3 != -1 && i3 <= i4) || (mediaPeriodHolder2 = this.playingPeriodHolder) == null) {
            return;
        }
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodHolder2.info.id;
        this.playbackInfo = new PlaybackInfo(mediaPeriodId, seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs), this.playbackInfo.contentPositionUs);
    }

    private void startRenderers() throws ExoPlaybackException {
        this.rebuffering = false;
        this.standaloneMediaClock.start();
        for (Renderer renderer : this.enabledRenderers) {
            renderer.start();
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.standaloneMediaClock.stop();
        for (Renderer renderer : this.enabledRenderers) {
            ensureStopped(renderer);
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.playingPeriodHolder;
        if (mediaPeriodHolder == null) {
            return;
        }
        long discontinuity = mediaPeriodHolder.mediaPeriod.readDiscontinuity();
        if (discontinuity != -9223372036854775807L) {
            resetRendererPosition(discontinuity);
        } else {
            Renderer renderer = this.rendererMediaClockSource;
            if (renderer == null || renderer.isEnded() || (!this.rendererMediaClockSource.isReady() && rendererWaitingForNextStream(this.rendererMediaClockSource))) {
                this.rendererPositionUs = this.standaloneMediaClock.getPositionUs();
            } else {
                long positionUs = this.rendererMediaClock.getPositionUs();
                this.rendererPositionUs = positionUs;
                this.standaloneMediaClock.setPositionUs(positionUs);
            }
            discontinuity = this.playingPeriodHolder.toPeriodTime(this.rendererPositionUs);
        }
        this.playbackInfo.positionUs = discontinuity;
        this.elapsedRealtimeUs = SystemClock.elapsedRealtime() * 1000;
        long bufferedPositionUs = this.enabledRenderers.length == 0 ? Long.MIN_VALUE : this.playingPeriodHolder.mediaPeriod.getBufferedPositionUs();
        PlaybackInfo playbackInfo = this.playbackInfo;
        if (bufferedPositionUs == Long.MIN_VALUE) {
            bufferedPositionUs = this.playingPeriodHolder.info.durationUs;
        }
        playbackInfo.bufferedPositionUs = bufferedPositionUs;
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        boolean zIsTimelineReady;
        int i;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        updatePeriods();
        if (this.playingPeriodHolder == null) {
            maybeThrowPeriodPrepareError();
            scheduleNextWork(jElapsedRealtime, 10L);
            return;
        }
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        this.playingPeriodHolder.mediaPeriod.discardBuffer(this.playbackInfo.positionUs);
        boolean zIsTimelineReady2 = true;
        boolean z = true;
        for (Renderer renderer : this.enabledRenderers) {
            renderer.render(this.rendererPositionUs, this.elapsedRealtimeUs);
            z = z && renderer.isEnded();
            boolean z2 = renderer.isReady() || renderer.isEnded() || rendererWaitingForNextStream(renderer);
            if (!z2) {
                renderer.maybeThrowStreamError();
            }
            zIsTimelineReady2 = zIsTimelineReady2 && z2;
        }
        if (!zIsTimelineReady2) {
            maybeThrowPeriodPrepareError();
        }
        MediaClock mediaClock = this.rendererMediaClock;
        if (mediaClock != null) {
            PlaybackParameters playbackParameters = mediaClock.getPlaybackParameters();
            if (!playbackParameters.equals(this.playbackParameters)) {
                this.playbackParameters = playbackParameters;
                this.standaloneMediaClock.setPlaybackParameters(playbackParameters);
                this.eventHandler.obtainMessage(7, playbackParameters).sendToTarget();
            }
        }
        long j = this.playingPeriodHolder.info.durationUs;
        if (z && ((j == -9223372036854775807L || j <= this.playbackInfo.positionUs) && this.playingPeriodHolder.info.isFinal)) {
            setState(4);
            stopRenderers();
        } else {
            int i2 = this.state;
            if (i2 == 2) {
                if (this.enabledRenderers.length > 0) {
                    zIsTimelineReady = zIsTimelineReady2 && this.loadingPeriodHolder.haveSufficientBuffer(this.rebuffering, this.rendererPositionUs);
                } else {
                    zIsTimelineReady = isTimelineReady(j);
                }
                if (zIsTimelineReady) {
                    setState(3);
                    if (this.playWhenReady) {
                        startRenderers();
                    }
                }
            } else if (i2 == 3) {
                if (this.enabledRenderers.length <= 0) {
                    zIsTimelineReady2 = isTimelineReady(j);
                }
                if (!zIsTimelineReady2) {
                    this.rebuffering = this.playWhenReady;
                    setState(2);
                    stopRenderers();
                }
            }
        }
        if (this.state == 2) {
            for (Renderer renderer2 : this.enabledRenderers) {
                renderer2.maybeThrowStreamError();
            }
        }
        if ((this.playWhenReady && this.state == 3) || (i = this.state) == 2) {
            scheduleNextWork(jElapsedRealtime, 10L);
        } else if (this.enabledRenderers.length != 0 && i != 4) {
            scheduleNextWork(jElapsedRealtime, 1000L);
        } else {
            this.handler.removeMessages(2);
        }
        TraceUtil.endSection();
    }

    private void scheduleNextWork(long j, long j2) {
        this.handler.removeMessages(2);
        long jElapsedRealtime = (j + j2) - SystemClock.elapsedRealtime();
        if (jElapsedRealtime <= 0) {
            this.handler.sendEmptyMessage(2);
        } else {
            this.handler.sendEmptyMessageDelayed(2, jElapsedRealtime);
        }
    }

    private void seekToInternal(SeekPosition seekPosition) throws ExoPlaybackException {
        int i;
        long j;
        if (this.timeline == null) {
            this.pendingInitialSeekCount++;
            this.pendingSeekPosition = seekPosition;
            return;
        }
        Pair<Integer, Long> pairResolveSeekPosition = resolveSeekPosition(seekPosition);
        if (pairResolveSeekPosition == null) {
            PlaybackInfo playbackInfo = new PlaybackInfo(0, 0L);
            this.playbackInfo = playbackInfo;
            this.eventHandler.obtainMessage(4, 1, 0, playbackInfo).sendToTarget();
            this.playbackInfo = new PlaybackInfo(0, -9223372036854775807L);
            setState(4);
            resetInternal(false);
            return;
        }
        int i2 = seekPosition.windowPositionUs == -9223372036854775807L ? 1 : 0;
        int iIntValue = ((Integer) pairResolveSeekPosition.first).intValue();
        long jLongValue = ((Long) pairResolveSeekPosition.second).longValue();
        MediaSource.MediaPeriodId mediaPeriodIdResolvePeriodPositionForAds = this.mediaPeriodInfoSequence.resolvePeriodPositionForAds(iIntValue, jLongValue);
        if (mediaPeriodIdResolvePeriodPositionForAds.isAd()) {
            i = 1;
            j = 0;
        } else {
            i = i2;
            j = jLongValue;
        }
        try {
            if (mediaPeriodIdResolvePeriodPositionForAds.equals(this.playbackInfo.periodId) && j / 1000 == this.playbackInfo.positionUs / 1000) {
                return;
            }
            long jSeekToPeriodPosition = seekToPeriodPosition(mediaPeriodIdResolvePeriodPositionForAds, j);
            int i3 = i | (j == jSeekToPeriodPosition ? 0 : 1);
            PlaybackInfo playbackInfo2 = new PlaybackInfo(mediaPeriodIdResolvePeriodPositionForAds, jSeekToPeriodPosition, jLongValue);
            this.playbackInfo = playbackInfo2;
            this.eventHandler.obtainMessage(4, i3, 0, playbackInfo2).sendToTarget();
        } finally {
            PlaybackInfo playbackInfo3 = new PlaybackInfo(mediaPeriodIdResolvePeriodPositionForAds, j, jLongValue);
            this.playbackInfo = playbackInfo3;
            this.eventHandler.obtainMessage(4, i, 0, playbackInfo3).sendToTarget();
        }
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId mediaPeriodId, long j) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder;
        stopRenderers();
        this.rebuffering = false;
        setState(2);
        MediaPeriodHolder mediaPeriodHolder2 = this.playingPeriodHolder;
        if (mediaPeriodHolder2 == null) {
            MediaPeriodHolder mediaPeriodHolder3 = this.loadingPeriodHolder;
            if (mediaPeriodHolder3 != null) {
                mediaPeriodHolder3.release();
            }
            mediaPeriodHolder = null;
        } else {
            mediaPeriodHolder = null;
            while (mediaPeriodHolder2 != null) {
                if (mediaPeriodHolder == null && shouldKeepPeriodHolder(mediaPeriodId, j, mediaPeriodHolder2)) {
                    mediaPeriodHolder = mediaPeriodHolder2;
                } else {
                    mediaPeriodHolder2.release();
                }
                mediaPeriodHolder2 = mediaPeriodHolder2.next;
            }
        }
        MediaPeriodHolder mediaPeriodHolder4 = this.playingPeriodHolder;
        if (mediaPeriodHolder4 != mediaPeriodHolder || mediaPeriodHolder4 != this.readingPeriodHolder) {
            for (Renderer renderer : this.enabledRenderers) {
                renderer.disable();
            }
            this.enabledRenderers = new Renderer[0];
            this.rendererMediaClock = null;
            this.rendererMediaClockSource = null;
            this.playingPeriodHolder = null;
        }
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.next = null;
            this.loadingPeriodHolder = mediaPeriodHolder;
            this.readingPeriodHolder = mediaPeriodHolder;
            setPlayingPeriodHolder(mediaPeriodHolder);
            MediaPeriodHolder mediaPeriodHolder5 = this.playingPeriodHolder;
            if (mediaPeriodHolder5.hasEnabledTracks) {
                j = mediaPeriodHolder5.mediaPeriod.seekToUs(j);
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.loadingPeriodHolder = null;
            this.readingPeriodHolder = null;
            this.playingPeriodHolder = null;
            resetRendererPosition(j);
        }
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private boolean shouldKeepPeriodHolder(MediaSource.MediaPeriodId mediaPeriodId, long j, MediaPeriodHolder mediaPeriodHolder) {
        if (!mediaPeriodId.equals(mediaPeriodHolder.info.id) || !mediaPeriodHolder.prepared) {
            return false;
        }
        this.timeline.getPeriod(mediaPeriodHolder.info.id.periodIndex, this.period);
        int adGroupIndexAfterPositionUs = this.period.getAdGroupIndexAfterPositionUs(j);
        return adGroupIndexAfterPositionUs == -1 || this.period.getAdGroupTimeUs(adGroupIndexAfterPositionUs) == mediaPeriodHolder.info.endPositionUs;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.playingPeriodHolder;
        long rendererTime = mediaPeriodHolder == null ? j + 60000000 : mediaPeriodHolder.toRendererTime(j);
        this.rendererPositionUs = rendererTime;
        this.standaloneMediaClock.setPositionUs(rendererTime);
        for (Renderer renderer : this.enabledRenderers) {
            renderer.resetPosition(this.rendererPositionUs);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        MediaClock mediaClock = this.rendererMediaClock;
        if (mediaClock != null) {
            playbackParameters = mediaClock.setPlaybackParameters(playbackParameters);
        }
        this.standaloneMediaClock.setPlaybackParameters(playbackParameters);
        this.playbackParameters = playbackParameters;
        this.eventHandler.obtainMessage(7, playbackParameters).sendToTarget();
    }

    private void stopInternal() {
        resetInternal(true);
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true);
        this.loadControl.onReleased();
        setState(1);
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void resetInternal(boolean z) {
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.standaloneMediaClock.stop();
        this.rendererMediaClock = null;
        this.rendererMediaClockSource = null;
        this.rendererPositionUs = 60000000L;
        for (Renderer renderer : this.enabledRenderers) {
            try {
                ensureStopped(renderer);
                renderer.disable();
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e("ExoPlayerImplInternal", "Stop failed.", e);
            }
        }
        this.enabledRenderers = new Renderer[0];
        MediaPeriodHolder mediaPeriodHolder = this.playingPeriodHolder;
        if (mediaPeriodHolder == null) {
            mediaPeriodHolder = this.loadingPeriodHolder;
        }
        releasePeriodHoldersFrom(mediaPeriodHolder);
        this.loadingPeriodHolder = null;
        this.readingPeriodHolder = null;
        this.playingPeriodHolder = null;
        setIsLoading(false);
        if (z) {
            MediaSource mediaSource = this.mediaSource;
            if (mediaSource != null) {
                mediaSource.releaseSource();
                this.mediaSource = null;
            }
            this.mediaPeriodInfoSequence.setTimeline(null);
            this.timeline = null;
        }
    }

    private void sendMessagesInternal(ExoPlayer.ExoPlayerMessage[] exoPlayerMessageArr) throws ExoPlaybackException {
        try {
            for (ExoPlayer.ExoPlayerMessage exoPlayerMessage : exoPlayerMessageArr) {
                exoPlayerMessage.target.handleMessage(exoPlayerMessage.messageType, exoPlayerMessage.message);
            }
            int i = this.state;
            if (i == 3 || i == 2) {
                this.handler.sendEmptyMessage(2);
            }
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
            }
        } catch (Throwable th) {
            synchronized (this) {
                this.customMessagesProcessed++;
                notifyAll();
                throw th;
            }
        }
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.playingPeriodHolder;
        if (mediaPeriodHolder == null) {
            return;
        }
        boolean z = true;
        while (mediaPeriodHolder != null && mediaPeriodHolder.prepared) {
            if (!mediaPeriodHolder.selectTracks()) {
                if (mediaPeriodHolder == this.readingPeriodHolder) {
                    z = false;
                }
                mediaPeriodHolder = mediaPeriodHolder.next;
            } else {
                if (z) {
                    MediaPeriodHolder mediaPeriodHolder2 = this.readingPeriodHolder;
                    MediaPeriodHolder mediaPeriodHolder3 = this.playingPeriodHolder;
                    boolean z2 = mediaPeriodHolder2 != mediaPeriodHolder3;
                    releasePeriodHoldersFrom(mediaPeriodHolder3.next);
                    MediaPeriodHolder mediaPeriodHolder4 = this.playingPeriodHolder;
                    mediaPeriodHolder4.next = null;
                    this.loadingPeriodHolder = mediaPeriodHolder4;
                    this.readingPeriodHolder = mediaPeriodHolder4;
                    boolean[] zArr = new boolean[this.renderers.length];
                    long jUpdatePeriodTrackSelection = mediaPeriodHolder4.updatePeriodTrackSelection(this.playbackInfo.positionUs, z2, zArr);
                    if (jUpdatePeriodTrackSelection != this.playbackInfo.positionUs) {
                        this.playbackInfo.positionUs = jUpdatePeriodTrackSelection;
                        resetRendererPosition(jUpdatePeriodTrackSelection);
                    }
                    boolean[] zArr2 = new boolean[this.renderers.length];
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i >= rendererArr.length) {
                            break;
                        }
                        Renderer renderer = rendererArr[i];
                        boolean z3 = renderer.getState() != 0;
                        zArr2[i] = z3;
                        SampleStream sampleStream = this.playingPeriodHolder.sampleStreams[i];
                        if (sampleStream != null) {
                            i2++;
                        }
                        if (z3) {
                            if (sampleStream != renderer.getStream()) {
                                if (renderer == this.rendererMediaClockSource) {
                                    this.rendererMediaClock = null;
                                    this.rendererMediaClockSource = null;
                                }
                                ensureStopped(renderer);
                                renderer.disable();
                            } else if (zArr[i]) {
                                renderer.resetPosition(this.rendererPositionUs);
                            }
                        }
                        i++;
                    }
                    this.eventHandler.obtainMessage(3, mediaPeriodHolder.trackSelectorResult).sendToTarget();
                    enableRenderers(zArr2, i2);
                } else {
                    this.loadingPeriodHolder = mediaPeriodHolder;
                    for (MediaPeriodHolder mediaPeriodHolder5 = mediaPeriodHolder.next; mediaPeriodHolder5 != null; mediaPeriodHolder5 = mediaPeriodHolder5.next) {
                        mediaPeriodHolder5.release();
                    }
                    MediaPeriodHolder mediaPeriodHolder6 = this.loadingPeriodHolder;
                    mediaPeriodHolder6.next = null;
                    if (mediaPeriodHolder6.prepared) {
                        this.loadingPeriodHolder.updatePeriodTrackSelection(Math.max(mediaPeriodHolder6.info.startPositionUs, mediaPeriodHolder6.toPeriodTime(this.rendererPositionUs)), false);
                    }
                }
                maybeContinueLoading();
                updatePlaybackPositions();
                this.handler.sendEmptyMessage(2);
                return;
            }
        }
    }

    private boolean isTimelineReady(long j) {
        MediaPeriodHolder mediaPeriodHolder;
        return j == -9223372036854775807L || this.playbackInfo.positionUs < j || ((mediaPeriodHolder = this.playingPeriodHolder.next) != null && (mediaPeriodHolder.prepared || mediaPeriodHolder.info.id.isAd()));
    }

    private void maybeThrowPeriodPrepareError() throws IOException {
        MediaPeriodHolder mediaPeriodHolder = this.loadingPeriodHolder;
        if (mediaPeriodHolder == null || mediaPeriodHolder.prepared) {
            return;
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.readingPeriodHolder;
        if (mediaPeriodHolder2 == null || mediaPeriodHolder2.next == mediaPeriodHolder) {
            for (Renderer renderer : this.enabledRenderers) {
                if (!renderer.hasReadStreamToEnd()) {
                    return;
                }
            }
            this.loadingPeriodHolder.mediaPeriod.maybeThrowPrepareError();
        }
    }

    private void handleSourceInfoRefreshed(Pair<Timeline, Object> pair) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder;
        Timeline timeline = this.timeline;
        Timeline timeline2 = (Timeline) pair.first;
        this.timeline = timeline2;
        this.mediaPeriodInfoSequence.setTimeline(timeline2);
        Object obj = pair.second;
        boolean z = false;
        if (timeline == null) {
            if (this.pendingInitialSeekCount > 0) {
                Pair<Integer, Long> pairResolveSeekPosition = resolveSeekPosition(this.pendingSeekPosition);
                int i = this.pendingInitialSeekCount;
                this.pendingInitialSeekCount = 0;
                this.pendingSeekPosition = null;
                if (pairResolveSeekPosition == null) {
                    handleSourceInfoRefreshEndedPlayback(obj, i);
                    return;
                }
                int iIntValue = ((Integer) pairResolveSeekPosition.first).intValue();
                long jLongValue = ((Long) pairResolveSeekPosition.second).longValue();
                MediaSource.MediaPeriodId mediaPeriodIdResolvePeriodPositionForAds = this.mediaPeriodInfoSequence.resolvePeriodPositionForAds(iIntValue, jLongValue);
                this.playbackInfo = new PlaybackInfo(mediaPeriodIdResolvePeriodPositionForAds, mediaPeriodIdResolvePeriodPositionForAds.isAd() ? 0L : jLongValue, jLongValue);
                notifySourceInfoRefresh(obj, i);
                return;
            }
            if (this.playbackInfo.startPositionUs == -9223372036854775807L) {
                if (this.timeline.isEmpty()) {
                    handleSourceInfoRefreshEndedPlayback(obj);
                    return;
                }
                Pair<Integer, Long> periodPosition = getPeriodPosition(0, -9223372036854775807L);
                int iIntValue2 = ((Integer) periodPosition.first).intValue();
                long jLongValue2 = ((Long) periodPosition.second).longValue();
                MediaSource.MediaPeriodId mediaPeriodIdResolvePeriodPositionForAds2 = this.mediaPeriodInfoSequence.resolvePeriodPositionForAds(iIntValue2, jLongValue2);
                this.playbackInfo = new PlaybackInfo(mediaPeriodIdResolvePeriodPositionForAds2, mediaPeriodIdResolvePeriodPositionForAds2.isAd() ? 0L : jLongValue2, jLongValue2);
                notifySourceInfoRefresh(obj);
                return;
            }
            notifySourceInfoRefresh(obj);
            return;
        }
        int i2 = this.playbackInfo.periodId.periodIndex;
        MediaPeriodHolder mediaPeriodHolder2 = this.playingPeriodHolder;
        if (mediaPeriodHolder2 == null) {
            mediaPeriodHolder2 = this.loadingPeriodHolder;
        }
        if (mediaPeriodHolder2 == null && i2 >= timeline.getPeriodCount()) {
            notifySourceInfoRefresh(obj);
            return;
        }
        int indexOfPeriod = this.timeline.getIndexOfPeriod(mediaPeriodHolder2 == null ? timeline.getPeriod(i2, this.period, true).uid : mediaPeriodHolder2.uid);
        if (indexOfPeriod == -1) {
            int iResolveSubsequentPeriod = resolveSubsequentPeriod(i2, timeline, this.timeline);
            if (iResolveSubsequentPeriod == -1) {
                handleSourceInfoRefreshEndedPlayback(obj);
                return;
            }
            Pair<Integer, Long> periodPosition2 = getPeriodPosition(this.timeline.getPeriod(iResolveSubsequentPeriod, this.period).windowIndex, -9223372036854775807L);
            int iIntValue3 = ((Integer) periodPosition2.first).intValue();
            long jLongValue3 = ((Long) periodPosition2.second).longValue();
            this.timeline.getPeriod(iIntValue3, this.period, true);
            if (mediaPeriodHolder2 != null) {
                Object obj2 = this.period.uid;
                mediaPeriodHolder2.info = mediaPeriodHolder2.info.copyWithPeriodIndex(-1);
                while (true) {
                    mediaPeriodHolder2 = mediaPeriodHolder2.next;
                    if (mediaPeriodHolder2 == null) {
                        break;
                    } else if (mediaPeriodHolder2.uid.equals(obj2)) {
                        mediaPeriodHolder2.info = this.mediaPeriodInfoSequence.getUpdatedMediaPeriodInfo(mediaPeriodHolder2.info, iIntValue3);
                    } else {
                        mediaPeriodHolder2.info = mediaPeriodHolder2.info.copyWithPeriodIndex(-1);
                    }
                }
            }
            MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(iIntValue3);
            this.playbackInfo = new PlaybackInfo(mediaPeriodId, seekToPeriodPosition(mediaPeriodId, jLongValue3));
            notifySourceInfoRefresh(obj);
            return;
        }
        if (indexOfPeriod != i2) {
            this.playbackInfo = this.playbackInfo.copyWithPeriodIndex(indexOfPeriod);
        }
        if (this.playbackInfo.periodId.isAd()) {
            MediaSource.MediaPeriodId mediaPeriodIdResolvePeriodPositionForAds3 = this.mediaPeriodInfoSequence.resolvePeriodPositionForAds(indexOfPeriod, this.playbackInfo.contentPositionUs);
            if (!mediaPeriodIdResolvePeriodPositionForAds3.isAd() || mediaPeriodIdResolvePeriodPositionForAds3.adIndexInAdGroup != this.playbackInfo.periodId.adIndexInAdGroup) {
                this.playbackInfo = new PlaybackInfo(mediaPeriodIdResolvePeriodPositionForAds3, seekToPeriodPosition(mediaPeriodIdResolvePeriodPositionForAds3, this.playbackInfo.contentPositionUs), mediaPeriodIdResolvePeriodPositionForAds3.isAd() ? this.playbackInfo.contentPositionUs : -9223372036854775807L);
                notifySourceInfoRefresh(obj);
                return;
            }
        }
        if (mediaPeriodHolder2 == null) {
            notifySourceInfoRefresh(obj);
            return;
        }
        MediaPeriodHolder mediaPeriodHolderUpdatePeriodInfo = updatePeriodInfo(mediaPeriodHolder2, indexOfPeriod);
        while (true) {
            mediaPeriodHolder = mediaPeriodHolderUpdatePeriodInfo.next;
            if (mediaPeriodHolder == null) {
                break;
            }
            indexOfPeriod = this.timeline.getNextPeriodIndex(indexOfPeriod, this.period, this.window, this.repeatMode);
            if (indexOfPeriod == -1 || !mediaPeriodHolder.uid.equals(this.timeline.getPeriod(indexOfPeriod, this.period, true).uid)) {
                break;
            } else {
                mediaPeriodHolderUpdatePeriodInfo = updatePeriodInfo(mediaPeriodHolder, indexOfPeriod);
            }
        }
        MediaPeriodHolder mediaPeriodHolder3 = this.readingPeriodHolder;
        if (mediaPeriodHolder3 != null && mediaPeriodHolder3.index < mediaPeriodHolder.index) {
            z = true;
        }
        if (!z) {
            this.playbackInfo = new PlaybackInfo(this.playingPeriodHolder.info.id, seekToPeriodPosition(this.playingPeriodHolder.info.id, this.playbackInfo.positionUs), this.playbackInfo.contentPositionUs);
        } else {
            this.loadingPeriodHolder = mediaPeriodHolderUpdatePeriodInfo;
            mediaPeriodHolderUpdatePeriodInfo.next = null;
            releasePeriodHoldersFrom(mediaPeriodHolder);
        }
        notifySourceInfoRefresh(obj);
    }

    private MediaPeriodHolder updatePeriodInfo(MediaPeriodHolder mediaPeriodHolder, int i) {
        MediaPeriodHolder mediaPeriodHolder2;
        while (true) {
            MediaPeriodInfoSequence.MediaPeriodInfo updatedMediaPeriodInfo = this.mediaPeriodInfoSequence.getUpdatedMediaPeriodInfo(mediaPeriodHolder.info, i);
            mediaPeriodHolder.info = updatedMediaPeriodInfo;
            if (updatedMediaPeriodInfo.isLastInTimelinePeriod || (mediaPeriodHolder2 = mediaPeriodHolder.next) == null) {
                break;
            }
            mediaPeriodHolder = mediaPeriodHolder2;
        }
        return mediaPeriodHolder;
    }

    private void handleSourceInfoRefreshEndedPlayback(Object obj) {
        handleSourceInfoRefreshEndedPlayback(obj, 0);
    }

    private void handleSourceInfoRefreshEndedPlayback(Object obj, int i) {
        this.playbackInfo = new PlaybackInfo(0, 0L);
        notifySourceInfoRefresh(obj, i);
        this.playbackInfo = new PlaybackInfo(0, -9223372036854775807L);
        setState(4);
        resetInternal(false);
    }

    private void notifySourceInfoRefresh(Object obj) {
        notifySourceInfoRefresh(obj, 0);
    }

    private void notifySourceInfoRefresh(Object obj, int i) {
        this.eventHandler.obtainMessage(6, new SourceInfo(this.timeline, obj, this.playbackInfo, i)).sendToTarget();
    }

    private int resolveSubsequentPeriod(int i, Timeline timeline, Timeline timeline2) {
        int periodCount = timeline.getPeriodCount();
        int indexOfPeriod = -1;
        for (int i2 = 0; i2 < periodCount && indexOfPeriod == -1; i2++) {
            i = timeline.getNextPeriodIndex(i, this.period, this.window, this.repeatMode);
            if (i == -1) {
                break;
            }
            indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getPeriod(i, this.period, true).uid);
        }
        return indexOfPeriod;
    }

    private Pair<Integer, Long> resolveSeekPosition(SeekPosition seekPosition) {
        Timeline timeline = seekPosition.timeline;
        if (timeline.isEmpty()) {
            timeline = this.timeline;
        }
        try {
            Pair<Integer, Long> periodPosition = timeline.getPeriodPosition(this.window, this.period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            Timeline timeline2 = this.timeline;
            if (timeline2 == timeline) {
                return periodPosition;
            }
            int indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getPeriod(((Integer) periodPosition.first).intValue(), this.period, true).uid);
            if (indexOfPeriod != -1) {
                return Pair.create(Integer.valueOf(indexOfPeriod), periodPosition.second);
            }
            int iResolveSubsequentPeriod = resolveSubsequentPeriod(((Integer) periodPosition.first).intValue(), timeline, this.timeline);
            if (iResolveSubsequentPeriod != -1) {
                return getPeriodPosition(this.timeline.getPeriod(iResolveSubsequentPeriod, this.period).windowIndex, -9223372036854775807L);
            }
            return null;
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(this.timeline, seekPosition.windowIndex, seekPosition.windowPositionUs);
        }
    }

    private Pair<Integer, Long> getPeriodPosition(int i, long j) {
        return this.timeline.getPeriodPosition(this.window, this.period, i, j);
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        MediaPeriodHolder mediaPeriodHolder;
        if (this.timeline == null) {
            this.mediaSource.maybeThrowSourceInfoRefreshError();
            return;
        }
        maybeUpdateLoadingPeriod();
        MediaPeriodHolder mediaPeriodHolder2 = this.loadingPeriodHolder;
        int i = 0;
        if (mediaPeriodHolder2 == null || mediaPeriodHolder2.isFullyBuffered()) {
            setIsLoading(false);
        } else if (this.loadingPeriodHolder != null && !this.isLoading) {
            maybeContinueLoading();
        }
        if (this.playingPeriodHolder == null) {
            return;
        }
        while (this.playWhenReady && (mediaPeriodHolder = this.playingPeriodHolder) != this.readingPeriodHolder && this.rendererPositionUs >= mediaPeriodHolder.next.rendererPositionOffsetUs) {
            mediaPeriodHolder.release();
            setPlayingPeriodHolder(this.playingPeriodHolder.next);
            MediaPeriodInfoSequence.MediaPeriodInfo mediaPeriodInfo = this.playingPeriodHolder.info;
            this.playbackInfo = new PlaybackInfo(mediaPeriodInfo.id, mediaPeriodInfo.startPositionUs, mediaPeriodInfo.contentPositionUs);
            updatePlaybackPositions();
            this.eventHandler.obtainMessage(5, this.playbackInfo).sendToTarget();
        }
        MediaPeriodHolder mediaPeriodHolder3 = this.readingPeriodHolder;
        if (mediaPeriodHolder3.info.isFinal) {
            while (true) {
                Renderer[] rendererArr = this.renderers;
                if (i >= rendererArr.length) {
                    return;
                }
                Renderer renderer = rendererArr[i];
                SampleStream sampleStream = this.readingPeriodHolder.sampleStreams[i];
                if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                    renderer.setCurrentStreamFinal();
                }
                i++;
            }
        } else {
            MediaPeriodHolder mediaPeriodHolder4 = mediaPeriodHolder3.next;
            if (mediaPeriodHolder4 == null || !mediaPeriodHolder4.prepared) {
                return;
            }
            int i2 = 0;
            while (true) {
                Renderer[] rendererArr2 = this.renderers;
                if (i2 < rendererArr2.length) {
                    Renderer renderer2 = rendererArr2[i2];
                    SampleStream sampleStream2 = this.readingPeriodHolder.sampleStreams[i2];
                    if (renderer2.getStream() != sampleStream2) {
                        return;
                    }
                    if (sampleStream2 != null && !renderer2.hasReadStreamToEnd()) {
                        return;
                    } else {
                        i2++;
                    }
                } else {
                    MediaPeriodHolder mediaPeriodHolder5 = this.readingPeriodHolder;
                    TrackSelectorResult trackSelectorResult = mediaPeriodHolder5.trackSelectorResult;
                    MediaPeriodHolder mediaPeriodHolder6 = mediaPeriodHolder5.next;
                    this.readingPeriodHolder = mediaPeriodHolder6;
                    TrackSelectorResult trackSelectorResult2 = mediaPeriodHolder6.trackSelectorResult;
                    boolean z = mediaPeriodHolder6.mediaPeriod.readDiscontinuity() != -9223372036854775807L;
                    while (true) {
                        Renderer[] rendererArr3 = this.renderers;
                        if (i >= rendererArr3.length) {
                            return;
                        }
                        Renderer renderer3 = rendererArr3[i];
                        if (trackSelectorResult.selections.get(i) != null) {
                            if (z) {
                                renderer3.setCurrentStreamFinal();
                            } else if (!renderer3.isCurrentStreamFinal()) {
                                TrackSelection trackSelection = trackSelectorResult2.selections.get(i);
                                RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i];
                                RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i];
                                if (trackSelection != null && rendererConfiguration2.equals(rendererConfiguration)) {
                                    Format[] formats = getFormats(trackSelection);
                                    MediaPeriodHolder mediaPeriodHolder7 = this.readingPeriodHolder;
                                    renderer3.replaceStream(formats, mediaPeriodHolder7.sampleStreams[i], mediaPeriodHolder7.getRendererOffset());
                                } else {
                                    renderer3.setCurrentStreamFinal();
                                }
                            }
                        }
                        i++;
                    }
                }
            }
        }
    }

    private void maybeUpdateLoadingPeriod() throws IOException {
        MediaPeriodInfoSequence.MediaPeriodInfo nextMediaPeriodInfo;
        MediaPeriodHolder mediaPeriodHolder = this.loadingPeriodHolder;
        if (mediaPeriodHolder == null) {
            nextMediaPeriodInfo = this.mediaPeriodInfoSequence.getFirstMediaPeriodInfo(this.playbackInfo);
        } else {
            if (mediaPeriodHolder.info.isFinal || !mediaPeriodHolder.isFullyBuffered()) {
                return;
            }
            MediaPeriodHolder mediaPeriodHolder2 = this.loadingPeriodHolder;
            MediaPeriodInfoSequence.MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder2.info;
            if (mediaPeriodInfo.durationUs == -9223372036854775807L) {
                return;
            }
            MediaPeriodHolder mediaPeriodHolder3 = this.playingPeriodHolder;
            if (mediaPeriodHolder3 != null && mediaPeriodHolder2.index - mediaPeriodHolder3.index == 100) {
                return;
            } else {
                nextMediaPeriodInfo = this.mediaPeriodInfoSequence.getNextMediaPeriodInfo(mediaPeriodInfo, mediaPeriodHolder2.getRendererOffset(), this.rendererPositionUs);
            }
        }
        if (nextMediaPeriodInfo == null) {
            this.mediaSource.maybeThrowSourceInfoRefreshError();
            return;
        }
        MediaPeriodHolder mediaPeriodHolder4 = this.loadingPeriodHolder;
        long rendererOffset = mediaPeriodHolder4 == null ? 60000000L : mediaPeriodHolder4.getRendererOffset() + this.loadingPeriodHolder.info.durationUs;
        MediaPeriodHolder mediaPeriodHolder5 = this.loadingPeriodHolder;
        MediaPeriodHolder mediaPeriodHolder6 = new MediaPeriodHolder(this.renderers, this.rendererCapabilities, rendererOffset, this.trackSelector, this.loadControl, this.mediaSource, this.timeline.getPeriod(nextMediaPeriodInfo.id.periodIndex, this.period, true).uid, mediaPeriodHolder5 == null ? 0 : mediaPeriodHolder5.index + 1, nextMediaPeriodInfo);
        MediaPeriodHolder mediaPeriodHolder7 = this.loadingPeriodHolder;
        if (mediaPeriodHolder7 != null) {
            mediaPeriodHolder7.next = mediaPeriodHolder6;
        }
        this.loadingPeriodHolder = mediaPeriodHolder6;
        mediaPeriodHolder6.mediaPeriod.prepare(this, nextMediaPeriodInfo.startPositionUs);
        setIsLoading(true);
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        MediaPeriodHolder mediaPeriodHolder = this.loadingPeriodHolder;
        if (mediaPeriodHolder == null || mediaPeriodHolder.mediaPeriod != mediaPeriod) {
            return;
        }
        mediaPeriodHolder.handlePrepared();
        if (this.playingPeriodHolder == null) {
            MediaPeriodHolder mediaPeriodHolder2 = this.loadingPeriodHolder;
            this.readingPeriodHolder = mediaPeriodHolder2;
            resetRendererPosition(mediaPeriodHolder2.info.startPositionUs);
            setPlayingPeriodHolder(this.readingPeriodHolder);
        }
        maybeContinueLoading();
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.loadingPeriodHolder;
        if (mediaPeriodHolder == null || mediaPeriodHolder.mediaPeriod != mediaPeriod) {
            return;
        }
        maybeContinueLoading();
    }

    private void maybeContinueLoading() {
        boolean zShouldContinueLoading = this.loadingPeriodHolder.shouldContinueLoading(this.rendererPositionUs);
        setIsLoading(zShouldContinueLoading);
        if (zShouldContinueLoading) {
            this.loadingPeriodHolder.continueLoading(this.rendererPositionUs);
        }
    }

    private void releasePeriodHoldersFrom(MediaPeriodHolder mediaPeriodHolder) {
        while (mediaPeriodHolder != null) {
            mediaPeriodHolder.release();
            mediaPeriodHolder = mediaPeriodHolder.next;
        }
    }

    private void setPlayingPeriodHolder(MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        if (this.playingPeriodHolder == mediaPeriodHolder) {
            return;
        }
        boolean[] zArr = new boolean[this.renderers.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i < rendererArr.length) {
                Renderer renderer = rendererArr[i];
                zArr[i] = renderer.getState() != 0;
                TrackSelection trackSelection = mediaPeriodHolder.trackSelectorResult.selections.get(i);
                if (trackSelection != null) {
                    i2++;
                }
                if (zArr[i] && (trackSelection == null || (renderer.isCurrentStreamFinal() && renderer.getStream() == this.playingPeriodHolder.sampleStreams[i]))) {
                    if (renderer == this.rendererMediaClockSource) {
                        this.rendererMediaClock = null;
                        this.rendererMediaClockSource = null;
                    }
                    ensureStopped(renderer);
                    renderer.disable();
                }
                i++;
            } else {
                this.playingPeriodHolder = mediaPeriodHolder;
                this.eventHandler.obtainMessage(3, mediaPeriodHolder.trackSelectorResult).sendToTarget();
                enableRenderers(zArr, i2);
                return;
            }
        }
    }

    private void enableRenderers(boolean[] zArr, int i) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            Renderer[] rendererArr = this.renderers;
            if (i2 >= rendererArr.length) {
                return;
            }
            Renderer renderer = rendererArr[i2];
            TrackSelection trackSelection = this.playingPeriodHolder.trackSelectorResult.selections.get(i2);
            if (trackSelection != null) {
                int i4 = i3 + 1;
                this.enabledRenderers[i3] = renderer;
                if (renderer.getState() == 0) {
                    RendererConfiguration rendererConfiguration = this.playingPeriodHolder.trackSelectorResult.rendererConfigurations[i2];
                    boolean z = this.playWhenReady && this.state == 3;
                    boolean z2 = !zArr[i2] && z;
                    int length = trackSelection.length();
                    Format[] formatArr = new Format[length];
                    for (int i5 = 0; i5 < length; i5++) {
                        formatArr[i5] = trackSelection.getFormat(i5);
                    }
                    MediaPeriodHolder mediaPeriodHolder = this.playingPeriodHolder;
                    renderer.enable(rendererConfiguration, formatArr, mediaPeriodHolder.sampleStreams[i2], this.rendererPositionUs, z2, mediaPeriodHolder.getRendererOffset());
                    MediaClock mediaClock = renderer.getMediaClock();
                    if (mediaClock != null) {
                        if (this.rendererMediaClock != null) {
                            throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."));
                        }
                        this.rendererMediaClock = mediaClock;
                        this.rendererMediaClockSource = renderer;
                        mediaClock.setPlaybackParameters(this.playbackParameters);
                    }
                    if (z) {
                        renderer.start();
                    }
                }
                i3 = i4;
            }
            i2++;
        }
    }

    private boolean rendererWaitingForNextStream(Renderer renderer) {
        MediaPeriodHolder mediaPeriodHolder = this.readingPeriodHolder.next;
        return mediaPeriodHolder != null && mediaPeriodHolder.prepared && renderer.hasReadStreamToEnd();
    }

    private static Format[] getFormats(TrackSelection trackSelection) {
        int length = trackSelection != null ? trackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i = 0; i < length; i++) {
            formatArr[i] = trackSelection.getFormat(i);
        }
        return formatArr;
    }

    private static final class MediaPeriodHolder {
        public boolean hasEnabledTracks;
        public final int index;
        public MediaPeriodInfoSequence.MediaPeriodInfo info;
        private final LoadControl loadControl;
        public final boolean[] mayRetainStreamFlags;
        public final MediaPeriod mediaPeriod;
        private final MediaSource mediaSource;
        public MediaPeriodHolder next;
        private TrackSelectorResult periodTrackSelectorResult;
        public boolean prepared;
        private final RendererCapabilities[] rendererCapabilities;
        public final long rendererPositionOffsetUs;
        private final Renderer[] renderers;
        public final SampleStream[] sampleStreams;
        private final TrackSelector trackSelector;
        public TrackSelectorResult trackSelectorResult;
        public final Object uid;

        public MediaPeriodHolder(Renderer[] rendererArr, RendererCapabilities[] rendererCapabilitiesArr, long j, TrackSelector trackSelector, LoadControl loadControl, MediaSource mediaSource, Object obj, int i, MediaPeriodInfoSequence.MediaPeriodInfo mediaPeriodInfo) {
            this.renderers = rendererArr;
            this.rendererCapabilities = rendererCapabilitiesArr;
            this.rendererPositionOffsetUs = j;
            this.trackSelector = trackSelector;
            this.loadControl = loadControl;
            this.mediaSource = mediaSource;
            this.uid = Assertions.checkNotNull(obj);
            this.index = i;
            this.info = mediaPeriodInfo;
            this.sampleStreams = new SampleStream[rendererArr.length];
            this.mayRetainStreamFlags = new boolean[rendererArr.length];
            MediaPeriod mediaPeriodCreatePeriod = mediaSource.createPeriod(mediaPeriodInfo.id, loadControl.getAllocator());
            if (mediaPeriodInfo.endPositionUs != Long.MIN_VALUE) {
                ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(mediaPeriodCreatePeriod, true);
                clippingMediaPeriod.setClipping(0L, mediaPeriodInfo.endPositionUs);
                mediaPeriodCreatePeriod = clippingMediaPeriod;
            }
            this.mediaPeriod = mediaPeriodCreatePeriod;
        }

        public long toRendererTime(long j) {
            return j + getRendererOffset();
        }

        public long toPeriodTime(long j) {
            return j - getRendererOffset();
        }

        public long getRendererOffset() {
            return this.index == 0 ? this.rendererPositionOffsetUs : this.rendererPositionOffsetUs - this.info.startPositionUs;
        }

        public boolean isFullyBuffered() {
            return this.prepared && (!this.hasEnabledTracks || this.mediaPeriod.getBufferedPositionUs() == Long.MIN_VALUE);
        }

        public boolean haveSufficientBuffer(boolean z, long j) {
            long bufferedPositionUs = !this.prepared ? this.info.startPositionUs : this.mediaPeriod.getBufferedPositionUs();
            if (bufferedPositionUs == Long.MIN_VALUE) {
                MediaPeriodInfoSequence.MediaPeriodInfo mediaPeriodInfo = this.info;
                if (mediaPeriodInfo.isFinal) {
                    return true;
                }
                bufferedPositionUs = mediaPeriodInfo.durationUs;
            }
            return this.loadControl.shouldStartPlayback(bufferedPositionUs - toPeriodTime(j), z);
        }

        public void handlePrepared() throws ExoPlaybackException {
            this.prepared = true;
            selectTracks();
            this.info = this.info.copyWithStartPositionUs(updatePeriodTrackSelection(this.info.startPositionUs, false));
        }

        public boolean shouldContinueLoading(long j) {
            long nextLoadPositionUs = !this.prepared ? 0L : this.mediaPeriod.getNextLoadPositionUs();
            if (nextLoadPositionUs == Long.MIN_VALUE) {
                return false;
            }
            return this.loadControl.shouldContinueLoading(nextLoadPositionUs - toPeriodTime(j));
        }

        public void continueLoading(long j) {
            this.mediaPeriod.continueLoading(toPeriodTime(j));
        }

        public boolean selectTracks() throws ExoPlaybackException {
            TrackSelectorResult trackSelectorResultSelectTracks = this.trackSelector.selectTracks(this.rendererCapabilities, this.mediaPeriod.getTrackGroups());
            if (trackSelectorResultSelectTracks.isEquivalent(this.periodTrackSelectorResult)) {
                return false;
            }
            this.trackSelectorResult = trackSelectorResultSelectTracks;
            return true;
        }

        public long updatePeriodTrackSelection(long j, boolean z) {
            return updatePeriodTrackSelection(j, z, new boolean[this.renderers.length]);
        }

        public long updatePeriodTrackSelection(long j, boolean z, boolean[] zArr) {
            TrackSelectionArray trackSelectionArray = this.trackSelectorResult.selections;
            int i = 0;
            while (true) {
                boolean z2 = true;
                if (i >= trackSelectionArray.length) {
                    break;
                }
                boolean[] zArr2 = this.mayRetainStreamFlags;
                if (z || !this.trackSelectorResult.isEquivalent(this.periodTrackSelectorResult, i)) {
                    z2 = false;
                }
                zArr2[i] = z2;
                i++;
            }
            long jSelectTracks = this.mediaPeriod.selectTracks(trackSelectionArray.getAll(), this.mayRetainStreamFlags, this.sampleStreams, zArr, j);
            this.periodTrackSelectorResult = this.trackSelectorResult;
            this.hasEnabledTracks = false;
            int i2 = 0;
            while (true) {
                SampleStream[] sampleStreamArr = this.sampleStreams;
                if (i2 < sampleStreamArr.length) {
                    if (sampleStreamArr[i2] != null) {
                        Assertions.checkState(trackSelectionArray.get(i2) != null);
                        this.hasEnabledTracks = true;
                    } else {
                        Assertions.checkState(trackSelectionArray.get(i2) == null);
                    }
                    i2++;
                } else {
                    this.loadControl.onTracksSelected(this.renderers, this.trackSelectorResult.groups, trackSelectionArray);
                    return jSelectTracks;
                }
            }
        }

        public void release() {
            try {
                if (this.info.endPositionUs != Long.MIN_VALUE) {
                    this.mediaSource.releasePeriod(((ClippingMediaPeriod) this.mediaPeriod).mediaPeriod);
                } else {
                    this.mediaSource.releasePeriod(this.mediaPeriod);
                }
            } catch (RuntimeException e) {
                Log.e("ExoPlayerImplInternal", "Period release failed.", e);
            }
        }
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline, int i, long j) {
            this.timeline = timeline;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }
}
