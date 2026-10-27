package com.amazon.avod.media.playback.pipeline;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.view.Surface;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
@TargetApi(21)
public class AsyncMediaPipeline extends MediaPipeline {
    private final Map<AsyncTaskType, BlockingQueue<AsyncPipelineTask>> mAsyncBlockingQueueMap;
    private final long mAsyncBlockingQueuePollTimeoutMillis;
    private final int mAsyncBlockingQueueSize;
    private final List<AsyncTaskLooper> mAsyncTaskLoopers;
    private final int mAsyncTaskRetryIntervalMillis;
    private final ScheduledExecutorService mExecutor;
    private final boolean mIsVideoSurfaceHotSwapSupported;
    private volatile PlaybackException mLastStashedAsyncTaskError;
    private final List<Future<Void>> mTaskFutures;
    private VideoDecodeCadenceWatchdog mWatchdog;
    private final int mWatchdogTickIntervalMillis;

    private interface AsyncPipelineTask {
        void executeAsyncPipelineTask() throws PlaybackException;
    }

    private enum AsyncTaskType {
        FEED,
        DRAIN,
        OUTPUT_FORMAT
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void executePipelineTask(@Nonnull PipelineTaskType pipelineTaskType) throws PlaybackException {
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public boolean isAsynchronous() {
        return true;
    }

    public AsyncMediaPipeline(@Nonnull MediaSource mediaSource, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaClock mediaClock, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull AndroidVideoSurface androidVideoSurface, boolean z, @Nonnull VideoConfig videoConfig, @Nullable Integer num, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        this(mediaSource, MediaCodecFactory.getInstance(), MediaRendererFactory.getInstance(), mediaSystemSharedContext, mediaClock, mediaPipelineContext, new ZoomCalculator(mediaSystemSharedContext.getDeviceConfiguration()), androidVideoSurface, num, z, videoConfig, scheduledExecutorService, MediaDefaultConfiguration.getInstance(), mediaCodecDeviceCapabilityDetector);
    }

    AsyncMediaPipeline(@Nonnull MediaSource mediaSource, @Nonnull MediaCodecFactory mediaCodecFactory, @Nonnull MediaRendererFactory mediaRendererFactory, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull MediaClock mediaClock, @Nonnull MediaPipelineContext mediaPipelineContext, @Nonnull ZoomCalculator zoomCalculator, @Nonnull AndroidVideoSurface androidVideoSurface, @Nullable Integer num, boolean z, @Nonnull VideoConfig videoConfig, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector) {
        super(mediaSource, mediaCodecFactory, mediaRendererFactory, mediaSystemSharedContext, mediaClock, mediaPipelineContext, zoomCalculator, androidVideoSurface, num, z, videoConfig, mediaDefaultConfiguration, mediaCodecDeviceCapabilityDetector);
        this.mAsyncBlockingQueueMap = new HashMap();
        this.mTaskFutures = new ArrayList();
        this.mAsyncTaskLoopers = new ArrayList();
        this.mLastStashedAsyncTaskError = null;
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
        Preconditions.checkNotNull(mediaDefaultConfiguration, "config");
        this.mAsyncTaskRetryIntervalMillis = mediaDefaultConfiguration.getAsyncTaskRetryIntervalMillis();
        this.mAsyncBlockingQueueSize = mediaDefaultConfiguration.getAsyncBlockingQueueSize();
        this.mAsyncBlockingQueuePollTimeoutMillis = mediaDefaultConfiguration.getAsyncBlockingQueuePollTimeout().getTotalMilliseconds();
        this.mWatchdogTickIntervalMillis = mediaDefaultConfiguration.getAsyncWatchdogTickIntervalMillis();
        this.mIsVideoSurfaceHotSwapSupported = mediaDefaultConfiguration.isVideoSurfaceHotSwapSupported();
        for (AsyncTaskType asyncTaskType : AsyncTaskType.values()) {
            this.mAsyncBlockingQueueMap.put(asyncTaskType, new ArrayBlockingQueue(this.mAsyncBlockingQueueSize));
            this.mAsyncTaskLoopers.add(new AsyncTaskLooper(asyncTaskType));
        }
        if (this.mIsAudioPipeline || !this.mShouldValidateVideoDecodingCadence) {
            return;
        }
        this.mWatchdog = new VideoDecodeCadenceWatchdog();
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline, com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void start() {
        synchronized (this.mMutex) {
            if (!canStart()) {
                DLog.warnf("MediaPipeline %s ignoring start() in %s state.", this.mInputFormat, getState());
                return;
            }
            super.start();
            Iterator<AsyncTaskLooper> it = this.mAsyncTaskLoopers.iterator();
            while (it.hasNext()) {
                this.mTaskFutures.add(this.mExecutor.submit(it.next()));
            }
            VideoDecodeCadenceWatchdog videoDecodeCadenceWatchdog = this.mWatchdog;
            if (videoDecodeCadenceWatchdog != null) {
                this.mTaskFutures.add(this.mExecutor.submit(videoDecodeCadenceWatchdog));
            }
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void stop() {
        synchronized (this.mMutex) {
            if (!canStop()) {
                DLog.warnf("MediaPipeline %s ignoring stop() in %s state.", this.mMimeType, getState());
                return;
            }
            super.stop();
            try {
                Iterator<Future<Void>> it = this.mTaskFutures.iterator();
                while (it.hasNext()) {
                    it.next().get();
                }
            } catch (InterruptedException | ExecutionException e) {
                DLog.warnf("Unexpected exception waiting for ongoing async looper task/s to complete: %s", e);
            }
            this.mTaskFutures.clear();
            if (this.mRenderer.isRunning()) {
                this.mRenderer.stop();
            }
            if (!this.mIsAudioPipeline && this.mVideoDecodeCadenceStopwatch.isRunning()) {
                this.mVideoDecodeCadenceStopwatch.stop().reset();
            }
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void flush() throws PlaybackException {
        synchronized (this.mMutex) {
            if (!canFlush()) {
                DLog.warnf("MediaPipeline %s ignoring flush() in %s state", this.mMimeType, getState());
                return;
            }
            super.flush();
            clearAsyncTasks();
            this.mPipelineTimer.start();
            this.mCodec.start();
            this.mPipelineTimer.stop("MediaCodec.start");
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline, com.amazon.avod.media.playback.pipeline.AbstractMediaComponent
    public void release() {
        synchronized (this.mMutex) {
            if (!canRelease()) {
                DLog.warnf("MediaPipeline %s ignoring release() in %s state.", this.mMimeType, getState());
            } else {
                super.release();
                clearAsyncTasks();
            }
        }
    }

    private void clearAsyncTasks() {
        Preconditions.checkState(Thread.holdsLock(this.mMutex));
        for (AsyncTaskType asyncTaskType : AsyncTaskType.values()) {
            this.mAsyncBlockingQueueMap.get(asyncTaskType).clear();
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void recreateRenderer() throws PlaybackException {
        super.recreateRenderer();
        this.mLastStashedAsyncTaskError = null;
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void configureCallbacks() {
        synchronized (this.mMutex) {
            this.mCodec.setCallback(new MediaCodec.Callback() { // from class: com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.1
                @Override // android.media.MediaCodec.Callback
                public void onInputBufferAvailable(@Nonnull MediaCodec mediaCodec, int i) {
                    try {
                        ((BlockingQueue) AsyncMediaPipeline.this.mAsyncBlockingQueueMap.get(AsyncTaskType.FEED)).put(AsyncMediaPipeline.this.new FeedInputBufferTask(i));
                    } catch (InterruptedException e) {
                        DLog.warnf("MediaCodec for %s interrupted queuing available input buffer: %s", AsyncMediaPipeline.this.mInputFormat, e);
                    }
                }

                @Override // android.media.MediaCodec.Callback
                public void onOutputBufferAvailable(@Nonnull MediaCodec mediaCodec, int i, @Nonnull MediaCodec.BufferInfo bufferInfo) {
                    try {
                        ((BlockingQueue) AsyncMediaPipeline.this.mAsyncBlockingQueueMap.get(AsyncTaskType.DRAIN)).put(AsyncMediaPipeline.this.new DrainOutputBufferTask(i, bufferInfo));
                    } catch (InterruptedException e) {
                        DLog.warnf("MediaCodec for %s interrupted queuing available output buffer: %s", AsyncMediaPipeline.this.mInputFormat, e);
                    }
                }

                @Override // android.media.MediaCodec.Callback
                public void onError(@Nonnull MediaCodec mediaCodec, @Nonnull MediaCodec.CodecException codecException) {
                    Surface surface;
                    String str = String.format("MediaCodec for %s threw unexpected codec exception: %s", AsyncMediaPipeline.this.mInputFormat, codecException);
                    AsyncMediaPipeline asyncMediaPipeline = AsyncMediaPipeline.this;
                    if (asyncMediaPipeline.mIsAudioPipeline || !asyncMediaPipeline.mIsVideoSurfaceHotSwapSupported || (surface = AsyncMediaPipeline.this.mSurface) == null || surface.isValid()) {
                        AsyncMediaPipeline.this.mLastStashedAsyncTaskError = new PlaybackException(PlaybackException.PlaybackError.INTERNAL_FATAL_ERROR, str);
                    } else {
                        DLog.warnf(str);
                    }
                }

                @Override // android.media.MediaCodec.Callback
                public void onOutputFormatChanged(@Nonnull MediaCodec mediaCodec, @Nonnull MediaFormat mediaFormat) {
                    try {
                        ((BlockingQueue) AsyncMediaPipeline.this.mAsyncBlockingQueueMap.get(AsyncTaskType.OUTPUT_FORMAT)).put(AsyncMediaPipeline.this.new OutputFormatChangedTask(mediaFormat));
                    } catch (InterruptedException e) {
                        DLog.warnf("MediaCodec for %s interrupted queuing oputput format change: %s", AsyncMediaPipeline.this.mInputFormat, e);
                    }
                }
            });
        }
    }

    @Override // com.amazon.avod.media.playback.pipeline.MediaPipeline
    public void checkPipelineError() throws PlaybackException {
        if (this.mLastStashedAsyncTaskError != null) {
            throw this.mLastStashedAsyncTaskError;
        }
    }

    private final class AsyncTaskLooper implements Callable<Void> {
        private final String mAsyncTaskLooperName;
        private final AsyncTaskType mAsyncTaskType;

        AsyncTaskLooper(@Nonnull AsyncTaskType asyncTaskType) {
            AsyncTaskType asyncTaskType2 = (AsyncTaskType) Preconditions.checkNotNull(asyncTaskType);
            this.mAsyncTaskType = asyncTaskType2;
            this.mAsyncTaskLooperName = AsyncMediaPipeline.this.getTaskName(asyncTaskType2.name());
        }

        @Override // java.util.concurrent.Callable
        @Nullable
        public Void call() {
            Thread.currentThread().setName(this.mAsyncTaskLooperName);
            while (AsyncMediaPipeline.this.isRunning()) {
                try {
                    AsyncPipelineTask asyncPipelineTask = (AsyncPipelineTask) ((BlockingQueue) AsyncMediaPipeline.this.mAsyncBlockingQueueMap.get(this.mAsyncTaskType)).poll(AsyncMediaPipeline.this.mAsyncBlockingQueuePollTimeoutMillis, TimeUnit.MILLISECONDS);
                    if (asyncPipelineTask != null) {
                        try {
                            asyncPipelineTask.executeAsyncPipelineTask();
                        } catch (PlaybackException e) {
                            DLog.warnf("Stashing encountered exception %s while executing %s", e, this.mAsyncTaskLooperName);
                            AsyncMediaPipeline.this.mLastStashedAsyncTaskError = e;
                            return null;
                        }
                    }
                } catch (InterruptedException unused) {
                    return null;
                }
            }
            return null;
        }
    }

    private final class FeedInputBufferTask implements AsyncPipelineTask {
        private final int mIndex;

        FeedInputBufferTask(int i) {
            Preconditions.checkState(i >= 0);
            this.mIndex = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0044 A[Catch: IllegalStateException -> 0x006b, TryCatch #1 {IllegalStateException -> 0x006b, blocks: (B:3:0x0003, B:5:0x000b, B:7:0x0015, B:9:0x001f, B:12:0x002b, B:13:0x003a, B:15:0x0044, B:16:0x004b, B:18:0x0051, B:20:0x0059, B:22:0x0063), top: B:29:0x0003, inners: #0 }] */
        @Override // com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.AsyncPipelineTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void executeAsyncPipelineTask() throws com.amazon.avod.playback.PlaybackException {
            /*
                r7 = this;
            L0:
                r0 = 2
                r1 = 1
                r2 = 0
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                boolean r3 = r3.isRunning()     // Catch: java.lang.IllegalStateException -> L6b
                if (r3 == 0) goto L3a
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                com.amazon.avod.media.playback.source.MediaSource r3 = r3.mSource     // Catch: java.lang.IllegalStateException -> L6b
                boolean r3 = r3.hasNext()     // Catch: java.lang.IllegalStateException -> L6b
                if (r3 != 0) goto L3a
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                com.amazon.avod.media.playback.source.MediaSource r3 = r3.mSource     // Catch: java.lang.IllegalStateException -> L6b
                boolean r3 = r3.hasReadEndOfStream()     // Catch: java.lang.IllegalStateException -> L6b
                if (r3 != 0) goto L3a
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.InterruptedException -> L2a java.lang.IllegalStateException -> L6b
                int r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.access$500(r3)     // Catch: java.lang.InterruptedException -> L2a java.lang.IllegalStateException -> L6b
                long r3 = (long) r3     // Catch: java.lang.InterruptedException -> L2a java.lang.IllegalStateException -> L6b
                java.lang.Thread.sleep(r3)     // Catch: java.lang.InterruptedException -> L2a java.lang.IllegalStateException -> L6b
                goto L0
            L2a:
                r3 = move-exception
                java.lang.String r4 = "MediaCodec for %s input buffer feed task was interrupted: %s"
                java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: java.lang.IllegalStateException -> L6b
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r6 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                android.media.MediaFormat r6 = r6.mInputFormat     // Catch: java.lang.IllegalStateException -> L6b
                r5[r2] = r6     // Catch: java.lang.IllegalStateException -> L6b
                r5[r1] = r3     // Catch: java.lang.IllegalStateException -> L6b
                com.amazon.avod.util.DLog.warnf(r4, r5)     // Catch: java.lang.IllegalStateException -> L6b
            L3a:
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                com.amazon.avod.media.playback.source.MediaSource r3 = r3.mSource     // Catch: java.lang.IllegalStateException -> L6b
                boolean r3 = r3.hasNext()     // Catch: java.lang.IllegalStateException -> L6b
                if (r3 == 0) goto L4b
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                int r4 = r7.mIndex     // Catch: java.lang.IllegalStateException -> L6b
                r3.feedInputBuffer(r4)     // Catch: java.lang.IllegalStateException -> L6b
            L4b:
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                boolean r4 = r3.mHasInputStreamEnded     // Catch: java.lang.IllegalStateException -> L6b
                if (r4 != 0) goto L86
                com.amazon.avod.media.playback.source.MediaSource r3 = r3.mSource     // Catch: java.lang.IllegalStateException -> L6b
                boolean r3 = r3.hasReadEndOfStream()     // Catch: java.lang.IllegalStateException -> L6b
                if (r3 == 0) goto L86
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                com.amazon.avod.media.playback.source.MediaSource r3 = r3.mSource     // Catch: java.lang.IllegalStateException -> L6b
                boolean r3 = r3.hasNext()     // Catch: java.lang.IllegalStateException -> L6b
                if (r3 != 0) goto L86
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r3 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this     // Catch: java.lang.IllegalStateException -> L6b
                int r4 = r7.mIndex     // Catch: java.lang.IllegalStateException -> L6b
                r3.feedEndOfStreamFlag(r4)     // Catch: java.lang.IllegalStateException -> L6b
                goto L86
            L6b:
                r3 = move-exception
                r4 = 3
                java.lang.Object[] r4 = new java.lang.Object[r4]
                com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline r5 = com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.this
                android.media.MediaFormat r6 = r5.mInputFormat
                r4[r2] = r6
                com.amazon.avod.media.playback.pipeline.AbstractMediaComponent$State r2 = r5.getState()
                r4[r1] = r2
                java.lang.String r1 = r3.getMessage()
                r4[r0] = r1
                java.lang.String r0 = "MediaCodec for %s (state = %s) threw unexpected IllegalStateException while reading from source: %s"
                com.amazon.avod.util.DLog.warnf(r0, r4)
            L86:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.FeedInputBufferTask.executeAsyncPipelineTask():void");
        }
    }

    private final class DrainOutputBufferTask implements AsyncPipelineTask {
        private final MediaCodec.BufferInfo mBufferInfo;
        private final int mIndex;

        DrainOutputBufferTask(int i, @Nonnull MediaCodec.BufferInfo bufferInfo) {
            Preconditions.checkState(i >= 0);
            this.mIndex = i;
            this.mBufferInfo = (MediaCodec.BufferInfo) Preconditions.checkNotNull(bufferInfo);
        }

        @Override // com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.AsyncPipelineTask
        public void executeAsyncPipelineTask() throws PlaybackException {
            int bytesRead;
            try {
                if (AsyncMediaPipeline.this.mHasOutputStreamEnded.get()) {
                    return;
                }
                long micros = TimeUnit.NANOSECONDS.toMicros(System.nanoTime());
                AsyncMediaPipeline asyncMediaPipeline = AsyncMediaPipeline.this;
                asyncMediaPipeline.mLastDecodedPresentationTimeUs = this.mBufferInfo.presentationTimeUs;
                ByteBuffer outputBuffer = asyncMediaPipeline.mCodec.getOutputBuffer(this.mIndex);
                do {
                    AsyncMediaPipeline asyncMediaPipeline2 = AsyncMediaPipeline.this;
                    SubmitBufferResult submitBufferResultSubmitBuffer = AsyncMediaPipeline.this.mRenderer.submitBuffer(asyncMediaPipeline2.mIsAudioPipeline ? 0L : asyncMediaPipeline2.mMediaClock.getCurrentMediaTimeUs(), this.mBufferInfo, outputBuffer, micros);
                    bytesRead = submitBufferResultSubmitBuffer.getBytesRead();
                    AsyncMediaPipeline asyncMediaPipeline3 = AsyncMediaPipeline.this;
                    if (asyncMediaPipeline3.mIsAudioPipeline && bytesRead == -6) {
                        DLog.warnf("AudioTrack is dead. LastDecodedPresentationTimeMicros: %d", Long.valueOf(asyncMediaPipeline3.mLastDecodedPresentationTimeUs));
                        throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_AUDIOTRACK_IS_DEAD, "AudioTrack.ERROR_DEAD_OBJECT detected", AsyncMediaPipeline.this.mLastDecodedPresentationTimeUs);
                    }
                    if (bytesRead >= 0) {
                        MediaCodec.BufferInfo bufferInfo = this.mBufferInfo;
                        bufferInfo.size -= bytesRead;
                        bufferInfo.offset += bytesRead;
                        try {
                            Thread.sleep(asyncMediaPipeline3.mAsyncTaskRetryIntervalMillis);
                        } catch (InterruptedException e) {
                            AsyncMediaPipeline asyncMediaPipeline4 = AsyncMediaPipeline.this;
                            if (!asyncMediaPipeline4.mIsAudioPipeline) {
                                DLog.warnf("MediaCodec for %s output buffer drain task was interrupted, releasing the buffer: %s", asyncMediaPipeline4.mInputFormat, e);
                                AsyncMediaPipeline.this.mCodec.releaseOutputBuffer(this.mIndex, true);
                                return;
                            }
                        }
                    } else if (bytesRead == -1 || bytesRead == -2) {
                        long adjustedReleaseTimeNs = submitBufferResultSubmitBuffer.getAdjustedReleaseTimeNs();
                        AsyncMediaPipeline.this.mDrainTaskTimer.start();
                        if (bytesRead == -1) {
                            AsyncMediaPipeline.this.mCodec.releaseOutputBuffer(this.mIndex, false);
                        } else if (adjustedReleaseTimeNs == -3) {
                            AsyncMediaPipeline.this.mCodec.releaseOutputBuffer(this.mIndex, true);
                        } else {
                            AsyncMediaPipeline.this.mCodec.releaseOutputBuffer(this.mIndex, adjustedReleaseTimeNs);
                        }
                        AsyncMediaPipeline.this.mDrainTaskTimer.stop("MediaCodec.releaseOutputBuffer", 20L);
                        if ((this.mBufferInfo.flags & 4) != 0) {
                            DLog.logf("Pipeline for %s decoded and rendered end of stream", AsyncMediaPipeline.this.mInputFormat);
                            AsyncMediaPipeline.this.mHasOutputStreamEnded.set(true);
                        }
                    }
                    if (!AsyncMediaPipeline.this.isRunning()) {
                        AsyncMediaPipeline asyncMediaPipeline5 = AsyncMediaPipeline.this;
                        if (!asyncMediaPipeline5.mIsAudioPipeline) {
                            DLog.warnf("MediaCodec for %s pipeline is not running, releasing the output buffer", asyncMediaPipeline5.mInputFormat);
                            AsyncMediaPipeline.this.mCodec.releaseOutputBuffer(this.mIndex, true);
                            return;
                        }
                    }
                } while (bytesRead >= 0);
            } catch (IllegalStateException e2) {
                AsyncMediaPipeline asyncMediaPipeline6 = AsyncMediaPipeline.this;
                DLog.warnf("MediaCodec for %s (state = %s) threw unexpected IllegalStateException while reading from decoder: %s", asyncMediaPipeline6.mInputFormat, asyncMediaPipeline6.getState(), e2.getMessage());
            }
        }
    }

    private final class OutputFormatChangedTask implements AsyncPipelineTask {
        private final MediaFormat mMediaFormat;

        OutputFormatChangedTask(@Nonnull MediaFormat mediaFormat) {
            this.mMediaFormat = (MediaFormat) Preconditions.checkNotNull(mediaFormat);
        }

        @Override // com.amazon.avod.media.playback.pipeline.AsyncMediaPipeline.AsyncPipelineTask
        public void executeAsyncPipelineTask() throws PlaybackException {
            AsyncMediaPipeline.this.handleOutputFormatChanged(this.mMediaFormat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nonnull
    public String getTaskName(@Nonnull String str) {
        Object[] objArr = new Object[2];
        objArr[0] = this.mIsAudioPipeline ? "A" : "V";
        objArr[1] = str;
        return String.format("AMP:%s:%s", objArr);
    }

    private final class VideoDecodeCadenceWatchdog implements Callable<Void> {
        private final String mTaskName;

        VideoDecodeCadenceWatchdog() {
            this.mTaskName = AsyncMediaPipeline.this.getTaskName("WATCHDOG");
        }

        @Override // java.util.concurrent.Callable
        public Void call() {
            Thread.currentThread().setName(this.mTaskName);
            while (AsyncMediaPipeline.this.isRunning()) {
                try {
                    AsyncMediaPipeline.this.validateVideoDecodingCadence();
                    Thread.sleep(AsyncMediaPipeline.this.mWatchdogTickIntervalMillis);
                } catch (PlaybackException e) {
                    DLog.warnf("Stashing encountered exception %s while executing %s", e, this.mTaskName);
                    AsyncMediaPipeline.this.mLastStashedAsyncTaskError = e;
                    return null;
                } catch (InterruptedException e2) {
                    DLog.warnf("VideoDecodeCadenceWatchdog: %s interrupted: %s", this.mTaskName, e2);
                }
            }
            return null;
        }
    }
}
