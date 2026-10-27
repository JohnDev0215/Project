package com.amazon.avod.playback.subtitles;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.event.playback.SubtitleEngineShutdownEvent;
import com.amazon.avod.playback.player.VideoPlaybackTimeline;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleReadResult;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitlesEngine {
    private static final long NANOSECONDS_PER_MILLISECOND = TimeUnit.MILLISECONDS.toNanos(1);
    private PlaybackEventTransport mPlaybackEventTransport;
    PlaybackSessionProtocol mSessionProtocol;
    StreamSelections mStreams;
    long mSubtitleLeadTimeNanos;
    private VideoPlaybackTimeline mVideoPlaybackTimeline;
    private final SampleHolder mSampleHolder = new SampleHolder();
    private final SampleHolder mDroppedSampleHolder = new SampleHolder();
    final Set<SubtitlesListener> mSubtitlesListenerSet = Sets.newHashSet();
    private final Object mMutex = new Object();
    volatile boolean mCallbackComplete = true;
    volatile boolean mShouldStartSubtitleStreamAfterFlushOrRestart = false;
    volatile boolean mIsRunning = false;
    volatile long mLastRenderedFragmentEndTimestampNanos = -1;
    volatile boolean mIsSubtitlesEngineInitialized = false;
    int mWaitIOCount = 0;
    private final Stopwatch mRestartStopWatch = Stopwatch.createStarted(Tickers.androidTicker());
    private final SubtitlesAggregator mSubtitlesAggregator = new SubtitlesAggregator();

    public void flush(boolean z) {
        this.mLastRenderedFragmentEndTimestampNanos = -1L;
        this.mWaitIOCount = 0;
        if (z) {
            this.mShouldStartSubtitleStreamAfterFlushOrRestart = true;
        }
    }

    public void initialize(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull VideoPlaybackTimeline videoPlaybackTimeline, @Nonnull PlaybackSessionProtocol playbackSessionProtocol, @Nonnull StreamSelections streamSelections) {
        initialize(playbackEventTransport, videoPlaybackTimeline, playbackSessionProtocol, streamSelections, SmoothStreamingPlaybackConfig.INSTANCE.getSubtitleLeadTime().getTotalNanoSeconds());
    }

    void initialize(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull VideoPlaybackTimeline videoPlaybackTimeline, @Nonnull PlaybackSessionProtocol playbackSessionProtocol, @Nonnull StreamSelections streamSelections, long j) {
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mVideoPlaybackTimeline = (VideoPlaybackTimeline) Preconditions.checkNotNull(videoPlaybackTimeline, "videoPlaybackTimeline");
        this.mSessionProtocol = (PlaybackSessionProtocol) Preconditions.checkNotNull(playbackSessionProtocol, "protocol");
        this.mStreams = (StreamSelections) Preconditions.checkNotNull(streamSelections, "streams");
        this.mLastRenderedFragmentEndTimestampNanos = -1L;
        if (j <= 0) {
            j = 0;
        }
        this.mSubtitleLeadTimeNanos = j;
        this.mSubtitlesAggregator.reset();
        this.mSubtitlesAggregator.initialize(videoPlaybackTimeline);
        this.mCallbackComplete = true;
        this.mIsSubtitlesEngineInitialized = true;
    }

    public void trySubmitSample(long j) throws MediaException {
        if (this.mIsSubtitlesEngineInitialized && this.mIsRunning) {
            Preconditions.checkState(this.mSessionProtocol != null, "cannot call trySubmitSample before initialization");
            if (this.mShouldStartSubtitleStreamAfterFlushOrRestart) {
                this.mSessionProtocol.startStream(StreamType.SUBTITLES, this.mVideoPlaybackTimeline.getCurrentPlayTimeInNanos());
                this.mShouldStartSubtitleStreamAfterFlushOrRestart = false;
            } else if (j >= this.mLastRenderedFragmentEndTimestampNanos - this.mSubtitleLeadTimeNanos) {
                if (this.mCallbackComplete) {
                    processResultAndRestartIfNeeded(readSampleToSampleHolder(this.mSampleHolder, true));
                } else {
                    readSampleToSampleHolder(this.mDroppedSampleHolder, false);
                    this.mSubtitlesAggregator.incrementDroppedSubtitleSampleCount();
                }
            }
        }
    }

    void processResultAndRestartIfNeeded(SampleReadResult sampleReadResult) {
        if (sampleReadResult != SampleReadResult.WAITING_FOR_IO) {
            this.mWaitIOCount = 0;
            this.mRestartStopWatch.reset().start();
            return;
        }
        int i = this.mWaitIOCount + 1;
        this.mWaitIOCount = i;
        if (i < 10 || this.mRestartStopWatch.elapsed(TimeUnit.MILLISECONDS) <= 2000) {
            return;
        }
        restartSubtitlesSessionStream();
    }

    SampleReadResult readSampleToSampleHolder(@Nonnull SampleHolder sampleHolder, boolean z) throws MediaException {
        Preconditions.checkNotNull(sampleHolder, "sampleHolder");
        PlaybackSessionProtocol playbackSessionProtocol = this.mSessionProtocol;
        SampleType sampleType = SampleType.SUBTITLE_SAMPLE;
        SampleReadResult nextSample = playbackSessionProtocol.getNextSample(sampleType, sampleHolder);
        if (nextSample != SampleReadResult.SAMPLE_READY) {
            return nextSample;
        }
        long presentationTime = sampleHolder.getPresentationTime();
        long j = NANOSECONDS_PER_MILLISECOND;
        long j2 = presentationTime / j;
        long duration = j2 + (sampleHolder.getDuration() / j);
        this.mLastRenderedFragmentEndTimestampNanos = j * duration;
        if (z) {
            StreamIndex subtitleStream = this.mStreams.getSubtitleStream();
            Preconditions.checkNotNull(subtitleStream, "must have a subtitle stream to render subtitles");
            sendSubtitleFragment(this.mSampleHolder.getData(), j2, duration, TimeUnit.NANOSECONDS.toMillis(sampleHolder.getAvSyncOffsetInNanoseconds()), subtitleStream.getLanguage());
        }
        if (sampleHolder.getIsLastinFragment()) {
            this.mSessionProtocol.releaseFragment(sampleType, sampleHolder);
        }
        sampleHolder.releaseSample();
        return nextSample;
    }

    public void startDownload() {
        if (this.mIsRunning) {
            return;
        }
        this.mSubtitlesAggregator.recordStartTimeInNanos();
        Preconditions.checkState(this.mSessionProtocol != null, "cannot call startDownload before initialization");
        this.mSessionProtocol.startStream(StreamType.SUBTITLES, this.mVideoPlaybackTimeline.getCurrentPlayTimeInNanos());
        this.mIsRunning = true;
        this.mShouldStartSubtitleStreamAfterFlushOrRestart = false;
    }

    public void stopDownload() {
        if (this.mIsRunning) {
            this.mSubtitlesAggregator.recordEndTimeInNanos();
            Preconditions.checkState(this.mSessionProtocol != null, "cannot call stopDownload before initialization");
            this.mSessionProtocol.stopStream(StreamType.SUBTITLES);
            this.mIsRunning = false;
        }
    }

    public void registerListener(SubtitlesListener subtitlesListener) {
        synchronized (this.mMutex) {
            this.mSubtitlesListenerSet.add(subtitlesListener);
        }
    }

    public void deregisterListener(SubtitlesListener subtitlesListener) {
        synchronized (this.mMutex) {
            this.mSubtitlesListenerSet.remove(subtitlesListener);
        }
    }

    public boolean isStreamingSubtitlesSupported() {
        Preconditions.checkState(this.mSessionProtocol != null, "cannot call isDashLiveSubtitlesSupported before initialization");
        try {
            return this.mSessionProtocol.isStreamingSubtitlesSupported();
        } catch (IllegalStateException unused) {
            DLog.warnf("Returning false for streaming subtitles support as called after shutdown");
            return false;
        }
    }

    @Nonnull
    public Set<String> getAvailableSubtitleLanguageCodes() {
        Preconditions.checkState(this.mSessionProtocol != null, "cannot call getAvailableSubtitleLanguageCodes before initialization");
        StreamIndex subtitleStream = this.mStreams.getSubtitleStream();
        HashSet hashSetNewHashSet = Sets.newHashSet();
        if (subtitleStream != null) {
            hashSetNewHashSet.add(subtitleStream.getLanguage());
        }
        return hashSetNewHashSet;
    }

    public void onCallbackComplete() {
        this.mCallbackComplete = true;
        this.mSubtitlesAggregator.incrementSuccessfulSubmittedSubtitleCount();
    }

    public void shutdown() {
        this.mIsSubtitlesEngineInitialized = false;
        this.mShouldStartSubtitleStreamAfterFlushOrRestart = true;
        PlaybackEventTransport playbackEventTransport = this.mPlaybackEventTransport;
        if (playbackEventTransport != null) {
            playbackEventTransport.postEvent(new SubtitleEngineShutdownEvent(TimeSpan.ZERO, this.mSubtitlesAggregator));
        }
    }

    private void sendSubtitleFragment(ByteBuffer byteBuffer, long j, long j2, long j3, String str) {
        this.mCallbackComplete = false;
        synchronized (this.mMutex) {
            Iterator<SubtitlesListener> it = this.mSubtitlesListenerSet.iterator();
            while (it.hasNext()) {
                it.next().onRenderSubtitles(byteBuffer, j, j2, j3, str);
            }
        }
    }

    private void restartSubtitlesSessionStream() {
        Preconditions.checkState(this.mSessionProtocol != null, "cannot call restartSubtitlesSessionStream before initialization");
        PlaybackSessionProtocol playbackSessionProtocol = this.mSessionProtocol;
        StreamType streamType = StreamType.SUBTITLES;
        playbackSessionProtocol.stopStream(streamType);
        this.mSessionProtocol.startStream(streamType, this.mVideoPlaybackTimeline.getCurrentPlayTimeInNanos());
        this.mWaitIOCount = 0;
        this.mRestartStopWatch.reset().start();
    }
}
