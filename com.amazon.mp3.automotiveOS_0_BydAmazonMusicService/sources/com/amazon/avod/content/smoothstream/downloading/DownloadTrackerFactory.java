package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.google.common.base.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadTrackerFactory {
    private SurgingResourcePool<GrowableBuffer> mBufferPool;
    private final SmoothStreamingContentStore mContentStore;
    private final DownloadService mDownloadService;
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private final SmoothStreamingRequestPrioritizationUtils mPrioritizationUtils;
    private FragmentValidator mValidator;
    private final AtomicLong mDownloadHandle = new AtomicLong(0);
    private final SmoothStreamingDownloadExceptionUtils mExceptionUtils = new SmoothStreamingDownloadExceptionUtils();
    private boolean mIsTerminated = false;

    public DownloadTrackerFactory(DownloadService downloadService, Mp4FragmentParser mp4FragmentParser, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, SmoothStreamingContentStore smoothStreamingContentStore, SurgingResourcePool<GrowableBuffer> surgingResourcePool) {
        this.mContentStore = smoothStreamingContentStore;
        this.mDownloadService = downloadService;
        this.mPlaybackConfig = smoothStreamingPlaybackConfig;
        this.mValidator = new FragmentValidator(mp4FragmentParser, smoothStreamingPlaybackConfig);
        this.mPrioritizationUtils = new SmoothStreamingRequestPrioritizationUtils(smoothStreamingPlaybackConfig);
        this.mBufferPool = surgingResourcePool;
    }

    public DownloadServiceDownloadTracker newDownloadTracker(ContentSessionContext contentSessionContext, FragmentDownloadController fragmentDownloadController, ScheduledExecutorService scheduledExecutorService) {
        return newDownloadTracker(contentSessionContext, fragmentDownloadController, scheduledExecutorService, 705032704L);
    }

    public DownloadServiceDownloadTracker newDownloadTracker(ContentSessionContext contentSessionContext, FragmentDownloadController fragmentDownloadController, ScheduledExecutorService scheduledExecutorService, long j) {
        Preconditions.checkState(!this.mIsTerminated, "Call after object is terminated");
        DownloadServiceDownloadTracker downloadServiceDownloadTracker = new DownloadServiceDownloadTracker(this.mDownloadService, this.mValidator, this.mContentStore, this.mExceptionUtils, this.mPrioritizationUtils, this.mPlaybackConfig, scheduledExecutorService, this.mBufferPool, this.mDownloadHandle.getAndIncrement());
        downloadServiceDownloadTracker.initialize(contentSessionContext, fragmentDownloadController, j);
        return downloadServiceDownloadTracker;
    }

    public void dispose() {
        this.mIsTerminated = true;
        this.mBufferPool = null;
        this.mValidator = null;
    }
}
