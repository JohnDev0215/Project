package com.amazon.avod.media.playback.render;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.avsync.TimeSource;
import com.amazon.avod.media.playback.pipeline.MediaPipelineContext;
import com.amazon.avod.media.playback.pipeline.VideoFrameReleaseTimeHelper;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
@TargetApi(21)
public class VideoRenderer extends MediaRenderer {
    static final TimeSpan AV_SYNC_DROP_THRESHOLD = TimeSpan.fromMilliseconds(30);
    static final TimeSpan AV_SYNC_RENDER_THRESHOLD = TimeSpan.fromMilliseconds(30);
    private final TimeSpan mAvSyncRenderThresholdV21;
    private final VideoFrameReleaseTimeHelper mFrameReleaseTimeHelper;
    private final boolean mIsAdjustedTimeFrameReleaseEnabled;
    private final SubmitBufferResult mSubmitVideoBufferResult;

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void flush() {
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    @Nullable
    public String getPassthroughMimeType() {
        return null;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public TimeSource getTimeSource() {
        return null;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public boolean isPassthroughSupported(@Nonnull String str) {
        return false;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public boolean rendersToSurface() {
        return true;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void setPlaybackSpeed(float f) {
    }

    public VideoRenderer(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaPipelineContext mediaPipelineContext) {
        this(new VideoFrameReleaseTimeHelper(mediaSystemSharedContext.getAppContext()), MediaDefaultConfiguration.getInstance(), mediaPipelineContext);
    }

    VideoRenderer(@Nonnull VideoFrameReleaseTimeHelper videoFrameReleaseTimeHelper, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaPipelineContext mediaPipelineContext) {
        VideoFrameReleaseTimeHelper videoFrameReleaseTimeHelper2 = (VideoFrameReleaseTimeHelper) Preconditions.checkNotNull(videoFrameReleaseTimeHelper, "videoFrameReleaseTimeHelper");
        this.mFrameReleaseTimeHelper = videoFrameReleaseTimeHelper2;
        this.mMediaPipelineContext = (MediaPipelineContext) Preconditions.checkNotNull(mediaPipelineContext, "mediaPipelineContext");
        this.mSubmitVideoBufferResult = new SubmitBufferResult();
        this.mAvSyncRenderThresholdV21 = mediaDefaultConfiguration.getAvSyncRenderThresholdV21();
        boolean zIsAdjustedTimeFrameReleaseEnabled = mediaDefaultConfiguration.isAdjustedTimeFrameReleaseEnabled();
        this.mIsAdjustedTimeFrameReleaseEnabled = zIsAdjustedTimeFrameReleaseEnabled;
        if (zIsAdjustedTimeFrameReleaseEnabled) {
            videoFrameReleaseTimeHelper2.enable();
        }
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer, com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void start() {
        super.start();
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        super.stop();
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void release() {
        super.release();
        if (this.mIsAdjustedTimeFrameReleaseEnabled) {
            this.mFrameReleaseTimeHelper.disable();
        }
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void configure(@Nonnull MediaFormat mediaFormat, @Nullable Integer num) throws PlaybackException {
        super.configure(mediaFormat, num);
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void recreate() throws PlaybackException {
        throw new UnsupportedOperationException("Recreation of video renderer is not supported.");
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    @Nonnull
    protected SubmitBufferResult submitBuffer(long j, long j2, boolean z, int i, int i2, @Nonnull ByteBuffer byteBuffer, long j3) {
        if (this.mIsAdjustedTimeFrameReleaseEnabled) {
            return processOutputBufferV21(j, j2, z, j3);
        }
        return processOutputBuffer(j, j2, z);
    }

    @Nonnull
    private SubmitBufferResult processOutputBufferV21(long j, long j2, boolean z, long j3) {
        int i;
        long jNanoTime = System.nanoTime();
        long adjustedFrameReleaseTimeUs = getAdjustedFrameReleaseTimeUs(j2, j, jNanoTime, j3);
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        long micros = timeUnit.toMicros(jNanoTime - adjustedFrameReleaseTimeUs);
        if (micros >= AV_SYNC_DROP_THRESHOLD.getTotalMicroseconds()) {
            String str = z ? "Skipping (for initial A/V sync after resuming playback)" : "Dropping";
            Long lValueOf = Long.valueOf(timeUnit.toMillis(adjustedFrameReleaseTimeUs));
            Long lValueOf2 = Long.valueOf(timeUnit.toMillis(jNanoTime));
            TimeUnit timeUnit2 = TimeUnit.MICROSECONDS;
            DLog.logf("%s frame (releaseMs = %s, systemMs = %s, presMs = %s, audioMs = %s, lateByMs = %s)", str, lValueOf, lValueOf2, Long.valueOf(timeUnit2.toMillis(j2)), Long.valueOf(timeUnit2.toMillis(j)), Long.valueOf(timeUnit2.toMillis(micros)));
            i = -1;
        } else {
            i = Math.abs(micros) <= this.mAvSyncRenderThresholdV21.getTotalMicroseconds() ? -2 : 0;
        }
        this.mSubmitVideoBufferResult.setBytesRead(i);
        this.mSubmitVideoBufferResult.setAdjustedReleaseTimeNs(adjustedFrameReleaseTimeUs);
        return this.mSubmitVideoBufferResult;
    }

    private long getAdjustedFrameReleaseTimeUs(long j, long j2, long j3, long j4) {
        return this.mFrameReleaseTimeHelper.adjustReleaseTime(j, j3 + TimeUnit.MICROSECONDS.toNanos((j - j2) - (TimeUnit.NANOSECONDS.toMicros(System.nanoTime()) - j4)));
    }

    @Nonnull
    private SubmitBufferResult processOutputBuffer(long j, long j2, boolean z) {
        int i;
        long j3 = j - j2;
        if (j3 >= AV_SYNC_DROP_THRESHOLD.getTotalMicroseconds()) {
            String str = z ? "Skipping (for initial A/V sync after resuming playback)" : "Dropping";
            TimeUnit timeUnit = TimeUnit.MICROSECONDS;
            DLog.logf("%s frame (presMs = %s, audioMs = %s, lateByMs = %s)", str, Long.valueOf(timeUnit.toMillis(j2)), Long.valueOf(timeUnit.toMillis(j)), Long.valueOf(timeUnit.toMillis(j3)));
            i = -1;
        } else {
            i = Math.abs(j3) <= AV_SYNC_RENDER_THRESHOLD.getTotalMicroseconds() ? -2 : 0;
        }
        this.mSubmitVideoBufferResult.setBytesRead(i);
        this.mSubmitVideoBufferResult.setAdjustedReleaseTimeNs(-3L);
        return this.mSubmitVideoBufferResult;
    }

    @Override // com.amazon.avod.media.playback.render.MediaRenderer
    public void setVolume(float f) {
        throw new UnsupportedOperationException("VideoRenderer does not support volume change.");
    }
}
