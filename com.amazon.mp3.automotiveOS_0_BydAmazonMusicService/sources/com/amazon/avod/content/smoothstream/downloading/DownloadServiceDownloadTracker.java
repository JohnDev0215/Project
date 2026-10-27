package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadServiceDownloadTracker implements DownloadListener {
    private final SurgingResourcePool<GrowableBuffer> mBufferPool;
    private final SmoothStreamingContentStore mContentStore;
    protected ContentSessionContext mContext;
    protected FragmentDownloadController mDownloadController;
    private final long mDownloadHandle;
    private final DownloadService mDownloadService;
    private final SmoothStreamingDownloadExceptionUtils mExceptionUtils;
    private final TimeSpan mMaximumRetryDelay;
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private final SmoothStreamingRequestPrioritizationUtils mPrioritizationUtils;
    protected SmoothStreamingLazyFragmentRequest mRequest;
    private TimeSpan mRetryDelayBackoff;
    private final ScheduledExecutorService mScheduledRetryExecutor;
    private final boolean mShouldAllowCdnFragmentRedirects;
    private boolean mShouldConsumeExactAudioFragment;
    private long mTimeOutInNanos;
    private final FragmentValidator mValidator;
    private final Object mStateMutex = new Object();
    protected boolean mIsCancelled = false;
    private int mTryCount = 0;
    private long mAggregateTimeInNanoseconds = 0;

    DownloadServiceDownloadTracker(DownloadService downloadService, FragmentValidator fragmentValidator, SmoothStreamingContentStore smoothStreamingContentStore, SmoothStreamingDownloadExceptionUtils smoothStreamingDownloadExceptionUtils, SmoothStreamingRequestPrioritizationUtils smoothStreamingRequestPrioritizationUtils, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, ScheduledExecutorService scheduledExecutorService, SurgingResourcePool<GrowableBuffer> surgingResourcePool, long j) {
        this.mContentStore = smoothStreamingContentStore;
        this.mDownloadService = downloadService;
        this.mValidator = fragmentValidator;
        this.mExceptionUtils = smoothStreamingDownloadExceptionUtils;
        this.mPrioritizationUtils = smoothStreamingRequestPrioritizationUtils;
        this.mPlaybackConfig = smoothStreamingPlaybackConfig;
        this.mMaximumRetryDelay = smoothStreamingPlaybackConfig.getMaximumRetryDelay();
        this.mScheduledRetryExecutor = scheduledExecutorService;
        this.mBufferPool = surgingResourcePool;
        this.mDownloadHandle = j;
        this.mShouldAllowCdnFragmentRedirects = smoothStreamingPlaybackConfig.shouldAllowCdnFragmentRedirects();
    }

    public void initialize(ContentSessionContext contentSessionContext, FragmentDownloadController fragmentDownloadController, long j) {
        this.mContext = contentSessionContext;
        this.mDownloadController = fragmentDownloadController;
        this.mShouldConsumeExactAudioFragment = this.mPlaybackConfig.shouldConsumeExactAudioFragment(contentSessionContext.getSpecification().isLiveStream());
        this.mRetryDelayBackoff = this.mPlaybackConfig.getRetryDelayBackoff(contentSessionContext.getSessionType());
        this.mTimeOutInNanos = j;
    }

    public void download(SmoothStreamingURI smoothStreamingURI) {
        download(smoothStreamingURI, false);
    }

    public void download(SmoothStreamingURI smoothStreamingURI, boolean z) {
        final SmoothStreamingURI smoothStreamingURICheckCache = checkCache(smoothStreamingURI, smoothStreamingURI.getFragmentType() == FragmentType.UPGRADE);
        if (smoothStreamingURICheckCache != null) {
            try {
                this.mScheduledRetryExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.smoothstream.downloading.DownloadServiceDownloadTracker.1
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (DownloadServiceDownloadTracker.this.mStateMutex) {
                            DownloadServiceDownloadTracker downloadServiceDownloadTracker = DownloadServiceDownloadTracker.this;
                            if (!downloadServiceDownloadTracker.mIsCancelled) {
                                downloadServiceDownloadTracker.mDownloadController.notifyDownloadSuccess(smoothStreamingURICheckCache, downloadServiceDownloadTracker.mDownloadHandle);
                            } else {
                                DLog.logf("Skipping notifyDownloadSuccess as this session was canceled already.");
                            }
                        }
                    }
                });
                return;
            } catch (RejectedExecutionException unused) {
                DLog.logf("Executor was terminated but we are still trying to notifyDownloadSuccess. Callback will not be called.");
                return;
            }
        }
        queueDownload(smoothStreamingURI, 0, z);
    }

    public void cancel() {
        synchronized (this.mStateMutex) {
            this.mIsCancelled = true;
            SmoothStreamingLazyFragmentRequest smoothStreamingLazyFragmentRequest = this.mRequest;
            if (smoothStreamingLazyFragmentRequest != null) {
                this.mDownloadService.cancelDownloadRequest(smoothStreamingLazyFragmentRequest);
            }
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onSuccess(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics) {
        Preconditions.checkArgument(downloadRequest.equals(this.mRequest), "We didn't submit this request!");
        synchronized (this.mStateMutex) {
            if (this.mIsCancelled) {
                DLog.logf("onSuccess was called but the session was canceled. Not propagating the event.");
            } else {
                this.mAggregateTimeInNanoseconds += downloadStatistics.getTotalTimeInNanos();
                this.mTryCount++;
                this.mDownloadController.onSuccess(this.mRequest.getAdjustedUri(), this.mDownloadHandle, this.mRequest.getManifestUrl(), this.mTryCount, this.mAggregateTimeInNanoseconds, downloadStatistics);
            }
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onFailure(DownloadRequest downloadRequest, Exception exc, @Nonnull DownloadStatistics downloadStatistics) {
        Preconditions.checkArgument(downloadRequest.equals(this.mRequest), "We didn't submit this request!");
        Preconditions.checkNotNull(downloadStatistics, "statistics");
        synchronized (this.mStateMutex) {
            if (this.mIsCancelled) {
                DLog.logf("onFailure was called but the session was canceled. Not propagating the event.");
            } else {
                this.mAggregateTimeInNanoseconds += downloadStatistics.getTotalTimeInNanos();
                this.mTryCount++;
                this.mDownloadController.onFailure(this.mRequest.getAdjustedUri(), this.mDownloadHandle, this.mRequest.getManifestUrl(), translateException(exc, downloadStatistics), this.mTryCount, downloadStatistics);
                try {
                    this.mScheduledRetryExecutor.schedule(new Runnable() { // from class: com.amazon.avod.content.smoothstream.downloading.DownloadServiceDownloadTracker.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ContentSessionType effectiveSessionType = DownloadServiceDownloadTracker.this.mContext.getEffectiveSessionType();
                            DownloadServiceDownloadTracker downloadServiceDownloadTracker = DownloadServiceDownloadTracker.this;
                            downloadServiceDownloadTracker.queueDownload(downloadServiceDownloadTracker.mRequest.getAdjustedUri(), DownloadServiceDownloadTracker.this.mTryCount, (effectiveSessionType == ContentSessionType.CONTENT_CACHE || effectiveSessionType == ContentSessionType.LIVE_CACHE) ? false : true);
                        }
                    }, Math.min(this.mRetryDelayBackoff.getTotalMilliseconds() * ((long) this.mTryCount), this.mMaximumRetryDelay.getTotalMilliseconds()), TimeUnit.MILLISECONDS);
                } catch (RejectedExecutionException unused) {
                    DLog.logf("Executor was terminated but we are still trying to enqueue download. Not enqueuing");
                }
            }
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onCancel(DownloadRequest downloadRequest) {
        Preconditions.checkArgument(downloadRequest.equals(this.mRequest), "We didn't submit this request!");
        Preconditions.checkState(this.mIsCancelled, "Shouldn't get cancelled unless we requested it!");
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadListener
    public void onDownloadProgress(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics, long j) {
        Preconditions.checkArgument(downloadRequest.equals(this.mRequest), "We didn't submit this request!");
        synchronized (this.mStateMutex) {
            if (this.mIsCancelled) {
                DLog.logf("onDownloadProgress was called but the session was canceled. Not propagating the event.");
            } else {
                this.mDownloadController.onDownloadProgress(this.mRequest.getAdjustedUri(), this.mDownloadHandle, this.mTryCount, downloadStatistics, j);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0048, code lost:
    
        return r2.mContentStore.getBestQualityAvailable(r2.mContext, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0049, code lost:
    
        com.amazon.avod.util.DLog.warnf("Failed to get existing fragment quality...");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.amazon.avod.content.smoothstream.SmoothStreamingURI checkCache(com.amazon.avod.content.smoothstream.SmoothStreamingURI r3, boolean r4) {
        /*
            r2 = this;
            boolean r0 = r3.isInitFragment()
            r1 = 0
            if (r0 == 0) goto L14
            com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore r4 = r2.mContentStore
            com.amazon.avod.content.ContentSessionContext r0 = r2.mContext
            boolean r4 = r4.isFragmentAvailable(r0, r3)
            if (r4 == 0) goto L12
            goto L13
        L12:
            r3 = r1
        L13:
            return r3
        L14:
            boolean r0 = r3.isAudio()
            if (r0 == 0) goto L29
            boolean r0 = r2.mShouldConsumeExactAudioFragment
            if (r0 == 0) goto L29
            com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore r4 = r2.mContentStore
            com.amazon.avod.content.ContentSessionContext r0 = r2.mContext
            boolean r4 = r4.isFragmentAvailable(r0, r3)
            if (r4 == 0) goto L4e
            return r3
        L29:
            if (r4 == 0) goto L36
            com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore r4 = r2.mContentStore
            com.amazon.avod.content.ContentSessionContext r0 = r2.mContext
            boolean r4 = r4.isFragmentAvailable(r0, r3)
            if (r4 == 0) goto L4e
            goto L40
        L36:
            com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore r4 = r2.mContentStore
            com.amazon.avod.content.ContentSessionContext r0 = r2.mContext
            boolean r4 = r4.isAnyFragmentAvailable(r0, r3)
            if (r4 == 0) goto L4e
        L40:
            com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore r4 = r2.mContentStore     // Catch: com.amazon.avod.content.ContentException -> L49
            com.amazon.avod.content.ContentSessionContext r0 = r2.mContext     // Catch: com.amazon.avod.content.ContentException -> L49
            com.amazon.avod.content.smoothstream.SmoothStreamingURI r3 = r4.getBestQualityAvailable(r0, r3)     // Catch: com.amazon.avod.content.ContentException -> L49
            return r3
        L49:
            java.lang.String r3 = "Failed to get existing fragment quality..."
            com.amazon.avod.util.DLog.warnf(r3)
        L4e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.downloading.DownloadServiceDownloadTracker.checkCache(com.amazon.avod.content.smoothstream.SmoothStreamingURI, boolean):com.amazon.avod.content.smoothstream.SmoothStreamingURI");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queueDownload(SmoothStreamingURI smoothStreamingURI, int i, boolean z) {
        synchronized (this.mStateMutex) {
            if (!this.mIsCancelled) {
                SmoothStreamingLazyFragmentRequest smoothStreamingLazyFragmentRequest = new SmoothStreamingLazyFragmentRequest(this.mValidator, this.mContentStore, this.mPrioritizationUtils, this.mTimeOutInNanos, this.mBufferPool, !this.mContext.getSpecification().isLiveStream() || this.mShouldAllowCdnFragmentRedirects);
                this.mRequest = smoothStreamingLazyFragmentRequest;
                smoothStreamingLazyFragmentRequest.initialize(this.mContext, smoothStreamingURI, i, this.mDownloadController, this);
                this.mDownloadService.submitDownloadRequest(this.mRequest, z);
            }
        }
    }

    private ContentException translateException(Exception exc, DownloadStatistics downloadStatistics) {
        return this.mExceptionUtils.translateExceptionForFragment(exc, exc instanceof TimeoutException ? String.format(Locale.US, "Fragment %s timed out (got %s bytes in %s ms). ", this.mRequest.getAdjustedUri(), Long.valueOf(downloadStatistics.getBytesProcessed()), Long.valueOf(TimeUnit.NANOSECONDS.toMillis(downloadStatistics.getProcessedTimeInNanos()))) : null, downloadStatistics);
    }

    public long getDownloadHandle() {
        return this.mDownloadHandle;
    }
}
