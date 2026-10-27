package com.amazon.avod.media.playback.pipeline;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.playback.util.ZoomCalculator;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.android.AndroidVideoSurface;
import com.amazon.avod.media.playback.avsync.MediaClock;
import com.amazon.avod.media.playback.render.SubmitBufferResult;
import com.amazon.avod.media.playback.source.MediaSource;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.media.playback.util.VideoConfig;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
@TargetApi(21)
public class SyncMediaPipeline extends MediaPipeline {
    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void checkPipelineError() throws PlaybackException {
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void configureCallbacks() {
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public boolean isAsynchronous() {
        return false;
    }

    public SyncMediaPipeline(@Nonnull MediaSource mediaSource, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaClock mediaClock, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull AndroidVideoSurface androidVideoSurface, boolean z, @Nonnull VideoConfig videoConfig, @Nullable Integer num, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        this(mediaSource, MediaCodecFactory.getInstance(), MediaRendererFactory.getInstance(), mediaSystemSharedContext, mediaClock, mediaPipelineContext, new ZoomCalculator(mediaSystemSharedContext.getDeviceConfiguration()), androidVideoSurface, num, z, videoConfig, MediaDefaultConfiguration.getInstance(), mediaCodecDeviceCapabilityDetector);
    }

    SyncMediaPipeline(@Nonnull MediaSource mediaSource, @Nonnull MediaCodecFactory mediaCodecFactory, @Nonnull MediaRendererFactory mediaRendererFactory, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaClock mediaClock, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull ZoomCalculator zoomCalculator, @Nonnull AndroidVideoSurface androidVideoSurface, @Nullable Integer num, boolean z, @Nonnull VideoConfig videoConfig, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        super(mediaSource, mediaCodecFactory, mediaRendererFactory, mediaSystemSharedContext, mediaClock, mediaPipelineContext, zoomCalculator, androidVideoSurface, num, z, videoConfig, mediaDefaultConfiguration, mediaCodecDeviceCapabilityDetector);
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        synchronized (this.mMutex) {
            if (!canStop()) {
                DLog.warnf("MediaPipeline %s ignoring stop() in %s state.", this.mMimeType, getState());
                return;
            }
            super.stop();
            if (this.mRenderer.isRunning()) {
                this.mRenderer.stop();
            }
            if (!this.mIsAudioPipeline && this.mVideoDecodeCadenceStopwatch.isRunning()) {
                this.mVideoDecodeCadenceStopwatch.stop().reset();
            }
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void executePipelineTask(@Nonnull PipelineTaskType pipelineTaskType) throws PlaybackException {
        Preconditions.checkNotNull(pipelineTaskType, "pipelineTaskType");
        if (isRunning()) {
            if (this.mIsAudioPipeline && this.mMediaPipelineContext.isAudioPipelineStalled()) {
                return;
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$playback$pipeline$PipelineTaskType[pipelineTaskType.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    feedInputBufferTask();
                    return;
                }
                throw new IllegalStateException(String.format("Unknown pipeline task type: %s", pipelineTaskType));
            }
            if (!this.mIsAudioPipeline && this.mShouldValidateVideoDecodingCadence) {
                validateVideoDecodingCadence();
            }
            drainOutputBufferTask();
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.pipeline.SyncMediaPipeline$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$pipeline$PipelineTaskType;

        static {
            int[] iArr = new int[PipelineTaskType.values().length];
            $SwitchMap$com$amazon$avod$media$playback$pipeline$PipelineTaskType = iArr;
            try {
                iArr[PipelineTaskType.DRAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$pipeline$PipelineTaskType[PipelineTaskType.FEED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if (r9.mHasInputStreamEnded != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
    
        if (r9.mSource.hasReadEndOfStream() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
    
        if (r9.mSource.hasNext() != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
    
        r9.mFeedTaskTimer.start();
        r1 = r9.mCodec.dequeueInputBuffer(0);
        r9.mFeedTaskTimer.stop("MediaCodec.dequeueInputBuffer", 20);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
    
        if (r1 == (-1)) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0058, code lost:
    
        feedEndOfStreamFlag(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void feedInputBufferTask() throws com.amazon.avod.playback.PlaybackException {
        /*
            r9 = this;
            r0 = 0
            r1 = r0
        L2:
            boolean r2 = r9.mIsAudioPipeline     // Catch: java.lang.IllegalStateException -> L5c
            r3 = -1
            r4 = 20
            java.lang.String r6 = "MediaCodec.dequeueInputBuffer"
            r7 = 0
            if (r2 != 0) goto L11
            int r2 = r9.mMaxConcurrentSampleCount     // Catch: java.lang.IllegalStateException -> L5c
            if (r1 >= r2) goto L32
        L11:
            com.amazon.avod.media.playback.source.MediaSource r2 = r9.mSource     // Catch: java.lang.IllegalStateException -> L5c
            boolean r2 = r2.hasNext()     // Catch: java.lang.IllegalStateException -> L5c
            if (r2 == 0) goto L32
            com.amazon.avod.media.playback.pipeline.ConditionalLoggingTimer r2 = r9.mFeedTaskTimer     // Catch: java.lang.IllegalStateException -> L5c
            r2.start()     // Catch: java.lang.IllegalStateException -> L5c
            android.media.MediaCodec r2 = r9.mCodec     // Catch: java.lang.IllegalStateException -> L5c
            int r2 = r2.dequeueInputBuffer(r7)     // Catch: java.lang.IllegalStateException -> L5c
            com.amazon.avod.media.playback.pipeline.ConditionalLoggingTimer r7 = r9.mFeedTaskTimer     // Catch: java.lang.IllegalStateException -> L5c
            r7.stop(r6, r4)     // Catch: java.lang.IllegalStateException -> L5c
            if (r2 != r3) goto L2c
            return
        L2c:
            r9.feedInputBuffer(r2)     // Catch: java.lang.IllegalStateException -> L5c
            int r1 = r1 + 1
            goto L2
        L32:
            boolean r1 = r9.mHasInputStreamEnded     // Catch: java.lang.IllegalStateException -> L5c
            if (r1 != 0) goto L77
            com.amazon.avod.media.playback.source.MediaSource r1 = r9.mSource     // Catch: java.lang.IllegalStateException -> L5c
            boolean r1 = r1.hasReadEndOfStream()     // Catch: java.lang.IllegalStateException -> L5c
            if (r1 == 0) goto L77
            com.amazon.avod.media.playback.source.MediaSource r1 = r9.mSource     // Catch: java.lang.IllegalStateException -> L5c
            boolean r1 = r1.hasNext()     // Catch: java.lang.IllegalStateException -> L5c
            if (r1 != 0) goto L77
            com.amazon.avod.media.playback.pipeline.ConditionalLoggingTimer r1 = r9.mFeedTaskTimer     // Catch: java.lang.IllegalStateException -> L5c
            r1.start()     // Catch: java.lang.IllegalStateException -> L5c
            android.media.MediaCodec r1 = r9.mCodec     // Catch: java.lang.IllegalStateException -> L5c
            int r1 = r1.dequeueInputBuffer(r7)     // Catch: java.lang.IllegalStateException -> L5c
            com.amazon.avod.media.playback.pipeline.ConditionalLoggingTimer r2 = r9.mFeedTaskTimer     // Catch: java.lang.IllegalStateException -> L5c
            r2.stop(r6, r4)     // Catch: java.lang.IllegalStateException -> L5c
            if (r1 == r3) goto L77
            r9.feedEndOfStreamFlag(r1)     // Catch: java.lang.IllegalStateException -> L5c
            goto L77
        L5c:
            r1 = move-exception
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            android.media.MediaFormat r3 = r9.mInputFormat
            r2[r0] = r3
            com.amazon.avod.media.playback.pipeline.AbstractMediaComponent$State r0 = r9.getState()
            r3 = 1
            r2[r3] = r0
            r0 = 2
            java.lang.String r1 = r1.getMessage()
            r2[r0] = r1
            java.lang.String r0 = "MediaCodec for %s (state = %s) threw unexpected IllegalStateException while reading from source: %s"
            com.amazon.avod.util.DLog.warnf(r0, r2)
        L77:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.playback.pipeline.SyncMediaPipeline.feedInputBufferTask():void");
    }

    private void drainOutputBufferTask() throws PlaybackException {
        try {
            long j = 0;
            long currentMediaTimeUs = this.mIsAudioPipeline ? 0L : this.mMediaClock.getCurrentMediaTimeUs();
            long micros = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
            while (!this.mHasOutputStreamEnded.get()) {
                if (this.mAvailableOutputBufferIndex < 0) {
                    this.mDrainTaskTimer.start();
                    this.mAvailableOutputBufferIndex = this.mCodec.dequeueOutputBuffer(this.mAvailableOutputBufferInfo, j);
                    this.mDrainTaskTimer.stop("MediaCodec.dequeueOutputBuffer", 20L);
                }
                int i = this.mAvailableOutputBufferIndex;
                if (i == -1) {
                    return;
                }
                if (i == -3) {
                    if (!shouldUseNewGetBufferApi()) {
                        this.mOutputBuffers = this.mCodec.getOutputBuffers();
                    }
                    if (!this.mIsAudioPipeline && this.mIsAudioStallingOptimizationEnabled) {
                        this.mMediaPipelineContext.stallAudioPipeline(true);
                    }
                } else if (i == -2) {
                    handleOutputFormatChanged(this.mCodec.getOutputFormat());
                } else {
                    this.mLastDecodedPresentationTimeUs = this.mAvailableOutputBufferInfo.presentationTimeUs;
                    if (!this.mIsAudioPipeline && this.mMediaPipelineContext.isAudioPipelineStalled()) {
                        this.mMediaPipelineContext.stallAudioPipeline(false);
                    }
                    if (!this.mIsAudioPipeline && this.mWaitForAudioStartupEnabled && !this.mMediaClock.hasActualTimeSourceStartedTicking()) {
                        return;
                    }
                    ByteBuffer outputBuffer = shouldUseNewGetBufferApi() ? this.mCodec.getOutputBuffer(this.mAvailableOutputBufferIndex) : this.mOutputBuffers[this.mAvailableOutputBufferIndex];
                    if (shouldUseNewGetBufferApi()) {
                        outputBuffer.position(outputBuffer.position() + this.mAvailableOutputBufferInfo.offset);
                    }
                    SubmitBufferResult submitBufferResultSubmitBuffer = this.mRenderer.submitBuffer(currentMediaTimeUs, this.mAvailableOutputBufferInfo, outputBuffer, micros);
                    int bytesRead = submitBufferResultSubmitBuffer.getBytesRead();
                    if (this.mIsAudioPipeline && bytesRead == -6) {
                        DLog.warnf("AudioTrack is dead. LastDecodedPresentationTimeMicros: %d", Long.valueOf(this.mLastDecodedPresentationTimeUs));
                        throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_AUDIOTRACK_IS_DEAD, "AudioTrack.ERROR_DEAD_OBJECT detected", this.mLastDecodedPresentationTimeUs);
                    }
                    if (bytesRead >= 0) {
                        MediaCodec.BufferInfo bufferInfo = this.mAvailableOutputBufferInfo;
                        bufferInfo.size -= bytesRead;
                        bufferInfo.offset += bytesRead;
                        return;
                    } else if (bytesRead == -1 || bytesRead == -2) {
                        long adjustedReleaseTimeNs = submitBufferResultSubmitBuffer.getAdjustedReleaseTimeNs();
                        this.mDrainTaskTimer.start();
                        if (bytesRead == -1) {
                            this.mCodec.releaseOutputBuffer(this.mAvailableOutputBufferIndex, false);
                        } else if (adjustedReleaseTimeNs == -3) {
                            this.mCodec.releaseOutputBuffer(this.mAvailableOutputBufferIndex, true);
                        } else {
                            this.mCodec.releaseOutputBuffer(this.mAvailableOutputBufferIndex, adjustedReleaseTimeNs);
                        }
                        this.mDrainTaskTimer.stop("MediaCodec.releaseOutputBuffer", 20L);
                        if ((this.mAvailableOutputBufferInfo.flags & 4) != 0) {
                            DLog.logf("Pipeline for %s decoded and rendered end of stream", this.mInputFormat);
                            this.mHasOutputStreamEnded.set(true);
                        }
                    }
                }
                clearAvailableOutputBufferInfo();
                j = 0;
            }
        } catch (IllegalStateException e) {
            DLog.warnf("MediaCodec for %s (state = %s) threw unexpected IllegalStateException while reading from decoder: %s", this.mInputFormat, getState(), e.getMessage());
        }
    }
}
