package com.amazon.avod.media.playback.render;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.media.MediaFormat;
import android.media.PlaybackParams;
import android.os.Build;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.avsync.RendererDebugInformationType;
import com.amazon.avod.media.playback.avsync.TimeSource;
import com.amazon.avod.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.visualon.OSMPUtils.voMimeTypes;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
@TargetApi(21)
public class AudioRenderer extends MediaRenderer {
    private static final long AUDIO_LATENCY_THRESHOLD_US;
    private static final long MAX_AUDIO_TIMESTAMP_OFFSET_US;
    private static final long MIN_AUDIO_TIMESTAMP_PROGRESS_US;
    private volatile Integer mAudioSessionId;
    private AudioTrack mAudioTrack;
    private long mAudioTrackBufferSizeUs;
    private long mAudioTrackLatencyUs;
    final TimeSource mAudioTrackTimeSource;
    private final AvSyncHeaderFormatter mAvSyncHeaderFormatter;
    private final long mBackoffTimeForRetriesOnAudioTrackCreationFailureMillis;
    private final MediaCodecDeviceCapabilityDetector mCapabilityDetector;
    private float mCurrentAudioTrackSpeed;
    private float mDesiredPlaybackSpeed;
    private Method mGetAudioTrackLatencyMethod;
    private final boolean mIsAudioTimestampApiEnabled;
    private final boolean mIsAudioTrackLatencyComputationEnabled;
    private final boolean mIsAudioTrackLatencyComputationInPassthroughEnabled;
    private final boolean mIsNonBlockingAudioRenderingEnabled;
    private boolean mIsPassThrough;
    private final boolean mIsTunnelModeNewWriteAPIForAudioBufferEnabled;
    private final boolean mIsTunnelModeNonBlockingAudioRenderingEnabled;
    private long mLastAudioTrackLatencySampleTimeNanos;
    private final ReadWriteLock mLock;
    private volatile MediaFormat mMediaFormat;
    private final TimeSpan mMinAudioTrackLatencySampleInterval;
    private final int mNumTriesForAudioTrackCreationFailure;
    private long mResumeSystemTimeUs;
    private final boolean mShouldFlushBeforeAudioTrackStop;
    private final boolean mShouldReportAVSyncStats;
    private final boolean mShouldRetryOnAudioTrackCreationFailure;
    private final SubmitBufferResult mSubmitAudioBufferResult;
    private final AudioTrackFactory mTrackFactory;
    private float mVolumeGain;

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    @Nullable
    public String getPassthroughMimeType() {
        return voMimeTypes.VOAUDIO_RAW;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public boolean rendersToSurface() {
        return false;
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        AUDIO_LATENCY_THRESHOLD_US = timeUnit.toMicros(5L);
        MAX_AUDIO_TIMESTAMP_OFFSET_US = timeUnit.toMicros(5L);
        MIN_AUDIO_TIMESTAMP_PROGRESS_US = TimeUnit.MILLISECONDS.toMicros(500L);
    }

    public AudioRenderer(@Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        this(AudioTrackFactory.getInstance(), MediaDefaultConfiguration.getInstance(), mediaPipelineContext, new AvSyncHeaderFormatter(Build.VERSION.SDK_INT), mediaCodecDeviceCapabilityDetector);
    }

    AudioRenderer(@Nonnull AudioTrackFactory audioTrackFactory, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull AvSyncHeaderFormatter avSyncHeaderFormatter, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        this.mLock = new ReentrantReadWriteLock();
        this.mGetAudioTrackLatencyMethod = null;
        this.mAudioTrackLatencyUs = 0L;
        this.mLastAudioTrackLatencySampleTimeNanos = 0L;
        this.mDesiredPlaybackSpeed = 1.0f;
        this.mCurrentAudioTrackSpeed = 1.0f;
        this.mAudioTrackTimeSource = new TimeSource() { // from class: com.amazon.avod.media.playback.render.AudioRenderer.1
            private volatile boolean mHasStartedTicking = false;
            private final AudioTimestamp mAudioTimestamp = new AudioTimestamp();

            @Override // com.amazon.avod.media.playback.avsync.TimeSource
            public long getCurrentRealTimeUs() {
                long jFramesToDurationUs;
                if (AudioRenderer.this.mAudioTrack != null && AudioRenderer.this.mAudioTrack.getPlayState() == 3) {
                    long playbackHeadPosition = (((long) AudioRenderer.this.mAudioTrack.getPlaybackHeadPosition()) * TimeUnit.SECONDS.toMicros(1L)) / ((long) AudioRenderer.this.mAudioTrack.getSampleRate());
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    long micros = timeUnit.toMicros(System.nanoTime());
                    if (AudioRenderer.this.mIsAudioTimestampApiEnabled && AudioRenderer.this.mAudioTrack.getTimestamp(this.mAudioTimestamp)) {
                        long micros2 = timeUnit.toMicros(this.mAudioTimestamp.nanoTime);
                        long j = this.mAudioTimestamp.framePosition;
                        if (Math.abs(micros2 - micros) > AudioRenderer.MAX_AUDIO_TIMESTAMP_OFFSET_US || Math.abs(framesToDurationUs(j) - playbackHeadPosition) > AudioRenderer.MAX_AUDIO_TIMESTAMP_OFFSET_US) {
                            DLog.warnf("Spurious audio timestamp (system clock mismatch): audioTimestampFramePosition %s audioTimestampUs %s systemClockUs %s currentAudioPlayheadPositionUs %s", Long.valueOf(j), Long.valueOf(micros2), Long.valueOf(micros), Long.valueOf(playbackHeadPosition));
                        } else if (micros2 >= AudioRenderer.this.mResumeSystemTimeUs) {
                            jFramesToDurationUs = framesToDurationUs(this.mAudioTimestamp.framePosition + durationUsToFrames(micros - timeUnit.toMicros(this.mAudioTimestamp.nanoTime)));
                        }
                        jFramesToDurationUs = 0;
                    } else {
                        jFramesToDurationUs = 0;
                    }
                    if (jFramesToDurationUs < AudioRenderer.MIN_AUDIO_TIMESTAMP_PROGRESS_US) {
                        jFramesToDurationUs = playbackHeadPosition - AudioRenderer.this.mAudioTrackLatencyUs;
                    }
                    this.mHasStartedTicking = jFramesToDurationUs > 0;
                    return Math.max(jFramesToDurationUs, 0L);
                }
                this.mHasStartedTicking = false;
                return 0L;
            }

            private long framesToDurationUs(long j) {
                return (j * TimeUnit.SECONDS.toMicros(1L)) / ((long) AudioRenderer.this.mAudioTrack.getSampleRate());
            }

            private long durationUsToFrames(long j) {
                return (j * ((long) AudioRenderer.this.mAudioTrack.getSampleRate())) / TimeUnit.SECONDS.toMicros(1L);
            }

            @Override // com.amazon.avod.media.playback.avsync.TimeSource
            public boolean hasStartedTicking() {
                return this.mHasStartedTicking;
            }
        };
        this.mVolumeGain = 1.0f;
        Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaDefaultConfiguration");
        this.mIsNonBlockingAudioRenderingEnabled = mediaDefaultConfiguration.isNonBlockingAudioRenderingEnabled();
        this.mIsAudioTrackLatencyComputationEnabled = mediaDefaultConfiguration.isAudioTrackLatencyComputationEnabled();
        this.mIsAudioTrackLatencyComputationInPassthroughEnabled = mediaDefaultConfiguration.isAudioTrackLatencyComputationInPassthroughEnabled();
        this.mMinAudioTrackLatencySampleInterval = mediaDefaultConfiguration.getMinAudioTrackLatencySampleInterval();
        this.mIsAudioTimestampApiEnabled = mediaDefaultConfiguration.isAudioTimestampApiEnabled();
        this.mIsTunnelModeNonBlockingAudioRenderingEnabled = mediaDefaultConfiguration.isTunnelModeNonBlockingAudioRenderingEnabled();
        this.mShouldFlushBeforeAudioTrackStop = mediaDefaultConfiguration.shouldFlushBeforeAudioTrackStop();
        this.mIsTunnelModeNewWriteAPIForAudioBufferEnabled = mediaDefaultConfiguration.isTunnelModeNewWriteAPIForAudioBufferEnabled();
        this.mShouldReportAVSyncStats = mediaDefaultConfiguration.shouldReportAVSyncStats();
        this.mBackoffTimeForRetriesOnAudioTrackCreationFailureMillis = mediaDefaultConfiguration.getBackoffTimeForRetriesOnAudioTrackCreationFailure().getTotalMilliseconds();
        this.mNumTriesForAudioTrackCreationFailure = mediaDefaultConfiguration.getNumTriesForAudioTrackCreationFailure();
        this.mShouldRetryOnAudioTrackCreationFailure = mediaDefaultConfiguration.shouldRetryOnAudioTrackCreationFailure();
        this.mTrackFactory = (AudioTrackFactory) Preconditions.checkNotNull(audioTrackFactory, "audioTrackFactory");
        this.mSubmitAudioBufferResult = new SubmitBufferResult();
        this.mMediaPipelineContext = (MediaPipelineContext) Preconditions.checkNotNull(mediaPipelineContext, "mediaPipelineContext");
        this.mAvSyncHeaderFormatter = (AvSyncHeaderFormatter) Preconditions.checkNotNull(avSyncHeaderFormatter, "avSyncHeaderFormatter");
        this.mCapabilityDetector = (MediaCodecDeviceCapabilityDetector) Preconditions.checkNotNull(mediaCodecDeviceCapabilityDetector, "capabilityDetector");
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public TimeSource getTimeSource() {
        return this.mAudioTrackTimeSource;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void configure(@Nonnull MediaFormat mediaFormat, @Nullable Integer num) throws PlaybackException {
        this.mLock.writeLock().lock();
        try {
            if (configureAudioRendererWithRetry(mediaFormat, num)) {
            } else {
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, "Failed to create a valid AudioTrack");
            }
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    private boolean configureAudioRendererWithRetry(@Nonnull MediaFormat mediaFormat, @Nullable Integer num) {
        boolean z;
        Throwable e;
        int i = 0;
        boolean z2 = false;
        while (true) {
            try {
                releaseAudioRenderer();
                configureAudioRenderer(mediaFormat, num);
                this.mAudioTrack.getPlaybackHeadPosition();
                try {
                    DLog.logf("Created AudioTrack successfully");
                    return true;
                } catch (PlaybackException | IllegalStateException e2) {
                    e = e2;
                    z = true;
                    DLog.exceptionf(e, "AudioTrack creation attempt #%d failed", Integer.valueOf(i));
                    try {
                        Thread.sleep(this.mBackoffTimeForRetriesOnAudioTrackCreationFailureMillis);
                    } catch (InterruptedException e3) {
                        DLog.exceptionf(e3, "interrupted during backoff for audio track creation failure", new Object[0]);
                    }
                    i++;
                    if (i >= this.mNumTriesForAudioTrackCreationFailure || !this.mShouldRetryOnAudioTrackCreationFailure) {
                        return z;
                    }
                    z2 = z;
                }
            } catch (PlaybackException | IllegalStateException e4) {
                z = z2;
                e = e4;
            }
            z2 = z;
        }
        return z;
    }

    private void configureAudioRenderer(@Nonnull MediaFormat mediaFormat, @Nullable Integer num) throws PlaybackException {
        super.configure(mediaFormat, num);
        this.mMediaFormat = mediaFormat;
        this.mAudioSessionId = num;
        boolean zIsPassthroughSupported = isPassthroughSupported(mediaFormat.getString("mime"));
        this.mIsPassThrough = zIsPassthroughSupported;
        AudioTrackWrapper audioTrackWrapperNewAudioTrackWrapper = this.mTrackFactory.newAudioTrackWrapper(mediaFormat, zIsPassthroughSupported, this.mAudioSessionId);
        AudioTrack audioTrack = audioTrackWrapperNewAudioTrackWrapper.getAudioTrack();
        this.mAudioTrack = audioTrack;
        if (audioTrack.getState() != 1) {
            DLog.warnf("AudioTrack not in STATE_INITIALIZED after configuring, state %d", Integer.valueOf(this.mAudioTrack.getState()));
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, "AudioTrack not in STATE_INITIALIZED after configuring");
        }
        this.mAudioTrackBufferSizeUs = audioTrackWrapperNewAudioTrackWrapper.getAudioTrackBufferSizeUs();
        try {
            this.mGetAudioTrackLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
        } catch (NoSuchMethodException e) {
            DLog.warnf("Error retrieving the audio track latency method: %s", e);
        }
        adjustAudioTrackSpeed();
        float f = this.mVolumeGain;
        if (f != 1.0f) {
            setVolume(f);
        }
    }

    @SuppressFBWarnings(justification = "Latency method obtained through reflection can throw.", value = {"REC_CATCH_EXCEPTION"})
    private void updateAudioTrackLatency() {
        long jIntValue;
        long jNanoTime = System.nanoTime();
        if (this.mGetAudioTrackLatencyMethod == null || !this.mIsAudioTrackLatencyComputationEnabled) {
            return;
        }
        if ((!this.mIsPassThrough || this.mIsAudioTrackLatencyComputationInPassthroughEnabled) && jNanoTime - this.mLastAudioTrackLatencySampleTimeNanos >= this.mMinAudioTrackLatencySampleInterval.getTotalNanoSeconds()) {
            long j = 0;
            try {
                jIntValue = (((long) ((Integer) this.mGetAudioTrackLatencyMethod.invoke(this.mAudioTrack, null)).intValue()) * 1000) - this.mAudioTrackBufferSizeUs;
            } catch (Exception unused) {
                this.mGetAudioTrackLatencyMethod = null;
                jIntValue = 0;
            }
            if (jIntValue >= 0 && jIntValue <= AUDIO_LATENCY_THRESHOLD_US) {
                j = jIntValue;
            }
            if (j != this.mAudioTrackLatencyUs) {
                DLog.logf("AudioTrack updated latency: %d Us", Long.valueOf(j));
            }
            this.mAudioTrackLatencyUs = j;
            this.mLastAudioTrackLatencySampleTimeNanos = jNanoTime;
        }
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer, com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void start() {
        this.mLock.writeLock().lock();
        try {
            super.start();
            this.mAudioTrack.play();
            this.mResumeSystemTimeUs = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        this.mLock.writeLock().lock();
        try {
            super.stop();
            this.mAudioTrack.pause();
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void flush() {
        Preconditions.checkState(isIdle(), "Can only call flush() when in the IDLE state!");
        this.mLock.writeLock().lock();
        try {
            this.mAudioTrack.flush();
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void recreate() throws PlaybackException {
        Preconditions.checkState(isIdle(), "Can only call recreate() when in the IDLE state!");
        this.mLock.writeLock().lock();
        try {
            if (this.mMediaFormat.getString("mime").equalsIgnoreCase(voMimeTypes.VOAUDIO_E_AC3) && !this.mCapabilityDetector.isDolbyOverBluetoothSupported()) {
                String str = String.format("Restarting player AudioTrack does not support %s over bluetooth.", this.mMediaFormat);
                DLog.warnf(str);
                throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_AUDIOTRACK_DIED_RESTART_PLAYER, str);
            }
            if (configureAudioRendererWithRetry(this.mMediaFormat, this.mAudioSessionId)) {
                return;
            }
            String str2 = String.format("Failed to recreate AudioTrack after %s attempts, restarting player", Integer.valueOf(this.mNumTriesForAudioTrackCreationFailure));
            DLog.errorf(str2);
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_AUDIOTRACK_DIED_RESTART_PLAYER, str2);
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void release() {
        this.mLock.writeLock().lock();
        try {
            releaseAudioRenderer();
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    private void releaseAudioRenderer() {
        super.release();
        if (this.mAudioTrack != null) {
            try {
                if (this.mShouldFlushBeforeAudioTrackStop) {
                    DLog.logf("AudioTrack.flush() on AudioRenderer#Release()");
                    this.mAudioTrack.flush();
                }
                DLog.logf("AudioTrack.stop() on AudioRenderer#Release()");
                this.mAudioTrack.stop();
            } catch (IllegalStateException e) {
                DLog.exceptionf(e, "failed to flush/stop AudioTrack", new Object[0]);
            }
            DLog.logf("AudioTrack.release() on AudioRenderer#Release()");
            this.mAudioTrack.release();
        }
        this.mAudioTrack = null;
        this.mAudioTrackBufferSizeUs = 0L;
        this.mAudioTrackLatencyUs = 0L;
        this.mCurrentAudioTrackSpeed = 1.0f;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    @Nonnull
    protected SubmitBufferResult submitBuffer(long j, long j2, boolean z, int i, int i2, @Nonnull ByteBuffer byteBuffer, long j3) {
        this.mLock.readLock().lock();
        try {
            updateAudioTrackLatency();
            adjustAudioTrackSpeed();
            int iWriteToAudioTrackBuffer = isUnconfigured() ? 0 : writeToAudioTrackBuffer(i, i2, j2, byteBuffer);
            SubmitBufferResult submitBufferResult = this.mSubmitAudioBufferResult;
            if (iWriteToAudioTrackBuffer >= i) {
                iWriteToAudioTrackBuffer = -2;
            }
            submitBufferResult.setBytesRead(iWriteToAudioTrackBuffer);
            return this.mSubmitAudioBufferResult;
        } finally {
            this.mLock.readLock().unlock();
        }
    }

    private int writeToAudioTrackBuffer(int i, int i2, long j, @Nonnull ByteBuffer byteBuffer) {
        int i3 = ((this.mAudioSessionId == null || this.mIsTunnelModeNonBlockingAudioRenderingEnabled) && this.mIsNonBlockingAudioRenderingEnabled) ? 1 : 0;
        if (this.mAudioSessionId != null && i2 == 0) {
            if (this.mIsTunnelModeNewWriteAPIForAudioBufferEnabled) {
                return this.mAudioTrack.write(byteBuffer, i, i3, TimeUnit.MICROSECONDS.toNanos(j));
            }
            this.mAvSyncHeaderFormatter.setAvSyncHeader(i, j);
            int iWrite = this.mAudioTrack.write(this.mAvSyncHeaderFormatter.getAvSyncHeader(), this.mAvSyncHeaderFormatter.getAvSyncHeaderSize(), i3);
            if (this.mShouldReportAVSyncStats && iWrite < this.mAvSyncHeaderFormatter.getAvSyncHeaderSize()) {
                this.mMediaPipelineContext.getRendererDebugTracker().updateDebugInfo(RendererDebugInformationType.IncompleteHeader.name(), String.format(Locale.US, "PTSUs:%d, Bytes:%d;", Long.valueOf(j), Integer.valueOf(iWrite)));
            }
        }
        return this.mAudioTrack.write(byteBuffer, i, i3);
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public boolean isPassthroughSupported(@Nonnull String str) {
        Preconditions.checkNotNull(str, "mimeType");
        return str.equalsIgnoreCase(voMimeTypes.VOAUDIO_E_AC3) && this.mCapabilityDetector.isDolbyPassthroughSupported();
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void setVolume(float f) {
        Preconditions.checkArgument(f >= 0.0f, "gain cannot be negative.");
        this.mLock.writeLock().lock();
        try {
            this.mVolumeGain = f;
            if (this.mAudioTrack != null) {
                DLog.logf("Setting audio track volume gain to: %s", Float.valueOf(f));
                this.mAudioTrack.setVolume(f);
            }
        } finally {
            this.mLock.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void setPlaybackSpeed(float f) {
        Preconditions.checkArgument(f > 0.0f, "Playback speed must be positive.");
        DLog.logf("Setting desired playback speed to: %s", Float.valueOf(f));
        this.mDesiredPlaybackSpeed = f;
    }

    private void adjustAudioTrackSpeed() {
        if (this.mAudioTrack != null) {
            float f = this.mCurrentAudioTrackSpeed;
            float f2 = this.mDesiredPlaybackSpeed;
            if (f != f2) {
                DLog.logf("Setting AudioTrack's playback speed to: %s", Float.valueOf(f2));
                try {
                    this.mAudioTrack.setPlaybackParams(new PlaybackParams().setSpeed(this.mDesiredPlaybackSpeed));
                    this.mCurrentAudioTrackSpeed = this.mDesiredPlaybackSpeed;
                } catch (IllegalArgumentException | IllegalStateException e) {
                    DLog.errorf("Failed to adjust AudioTrack's playback speed to %s due to: %s", Float.valueOf(this.mDesiredPlaybackSpeed), e);
                }
            }
        }
    }
}
