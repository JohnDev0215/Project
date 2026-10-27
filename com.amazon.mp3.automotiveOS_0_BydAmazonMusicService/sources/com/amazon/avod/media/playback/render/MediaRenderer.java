package com.amazon.avod.media.playback.render;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.amazon.avod.media.playback.avsync.TimeSource;
import com.amazon.avod.media.playback.pipeline.AbstractMediaComponent;
import com.amazon.avod.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.playback.PlaybackException;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public abstract class MediaRenderer extends AbstractMediaComponent {
    private long mFpsSampleStartUs;
    private boolean mIsResuming;
    private volatile float mLastFpsSample;
    MediaPipelineContext mMediaPipelineContext;
    private int mRenderedFramesInCurrentSample;
    private final TimeSource mTimeSource;
    private volatile int mTotalDroppedFrames;

    public abstract void flush();

    @Nullable
    public abstract String getPassthroughMimeType();

    @Nullable
    public abstract TimeSource getTimeSource();

    public abstract boolean isPassthroughSupported(@Nonnull String str);

    public abstract void recreate() throws PlaybackException;

    public abstract boolean rendersToSurface();

    public abstract void setPlaybackSpeed(float f);

    public abstract void setVolume(float f);

    @Nonnull
    protected abstract SubmitBufferResult submitBuffer(long j, long j2, boolean z, int i, int i2, @Nonnull ByteBuffer byteBuffer, long j3);

    protected MediaRenderer() {
        this(TimeSource.DEFAULT_INSTANCE);
    }

    MediaRenderer(@Nonnull TimeSource timeSource) {
        this.mTotalDroppedFrames = 0;
        this.mRenderedFramesInCurrentSample = 0;
        this.mFpsSampleStartUs = 0L;
        this.mLastFpsSample = -1.0f;
        this.mIsResuming = false;
        this.mTimeSource = (TimeSource) Preconditions.checkNotNull(timeSource, "timeSource");
    }

    public float getLastFpsSample() {
        return this.mLastFpsSample;
    }

    public int getTotalDroppedFrames() {
        return this.mTotalDroppedFrames;
    }

    public void configure(@Nonnull MediaFormat mediaFormat, @Nullable Integer num) throws PlaybackException {
        super.configure();
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void start() {
        super.start();
        this.mFpsSampleStartUs = 0L;
        this.mRenderedFramesInCurrentSample = 0;
        this.mLastFpsSample = 0.0f;
        this.mIsResuming = true;
    }

    @SuppressFBWarnings(justification = "OK for mTotalDroppedFrames to be off by one increment", value = {"VO_VOLATILE_INCREMENT"})
    public SubmitBufferResult submitBuffer(long j, @Nonnull MediaCodec.BufferInfo bufferInfo, @Nonnull ByteBuffer byteBuffer, long j2) {
        SubmitBufferResult submitBufferResultSubmitBuffer = submitBuffer(j, bufferInfo.presentationTimeUs, this.mIsResuming, bufferInfo.size, bufferInfo.offset, byteBuffer, j2);
        int bytesRead = submitBufferResultSubmitBuffer.getBytesRead();
        if (bytesRead == -1 && !this.mIsResuming) {
            this.mTotalDroppedFrames++;
        } else if (bytesRead == -2) {
            this.mRenderedFramesInCurrentSample++;
            this.mIsResuming = false;
        }
        long currentRealTimeUs = this.mTimeSource.getCurrentRealTimeUs();
        long millis = TimeUnit.MICROSECONDS.toMillis(currentRealTimeUs - this.mFpsSampleStartUs);
        if (millis >= 1000) {
            this.mLastFpsSample = (this.mRenderedFramesInCurrentSample * TimeUnit.SECONDS.toMillis(1L)) / millis;
            this.mRenderedFramesInCurrentSample = 0;
            this.mFpsSampleStartUs = currentRealTimeUs;
        }
        return submitBufferResultSubmitBuffer;
    }
}
