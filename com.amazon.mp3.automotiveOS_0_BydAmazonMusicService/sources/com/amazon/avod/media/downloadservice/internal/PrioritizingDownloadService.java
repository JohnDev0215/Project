package com.amazon.avod.media.downloadservice.internal;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.BandwidthStatistics;
import com.amazon.avod.media.downloadservice.DownloadListener;
import com.amazon.avod.media.downloadservice.DownloadRequest;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.PriorityTier;
import com.amazon.avod.media.downloadservice.internal.DownloadCallableV1;
import com.amazon.avod.media.downloadservice.internal.DownloadCallableV2;
import com.amazon.avod.media.framework.executors.TimeoutScheduledExecutor;
import com.amazon.avod.media.framework.resources.SurgingBufferPool;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes2.dex */
public class PrioritizingDownloadService implements DownloadService {
    private final VoidFunction<DownloadRequest> andNotify;
    private final VoidFunction<DownloadRequest> andReenqueue;
    private final boolean mAllowImmediateDownloads;
    private final DownloadCallableFactory mCallableFactory;
    final DownloadCompletedListener mCallbackInvoker;
    private final Map<DownloadRequest, ScheduledFuture> mConcurrencyLimitedRunningRequests;
    private final int mDownloadServiceMaxConcurrentDownloads;
    private final DownloadServiceStatisticsTracker mDownloadServiceStatsTracker;
    private final DownloadThrottle mDownloadThrottle;
    private final Map<DownloadRequest, ScheduledFuture> mImmediatelyDispatchedRunningRequests;
    private final Set<DownloadService.GlobalEventListener> mListeners;
    private final TimeSpan mLogInterval;
    private final DownloadServiceQueue mQueuedRequests;
    private final Object mRunningRequestsLock;
    private final TimeoutScheduledExecutor mScheduler;
    private final Stopwatch mStopwatch;

    public static final class SingletonHolder {
        static final PrioritizingDownloadService INSTANCE = new PrioritizingDownloadService();
    }

    private interface VoidFunction<T> {
        void apply(T t);
    }

    @Nonnull
    public static PrioritizingDownloadService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public PrioritizingDownloadService() {
        this(DownloadServiceConfig.INSTANCE);
    }

    PrioritizingDownloadService(@Nonnull DownloadServiceConfig downloadServiceConfig) {
        this(downloadServiceConfig, new DownloadServiceStatisticsTracker(downloadServiceConfig.getBandwidthAverageCount()));
    }

    PrioritizingDownloadService(@Nonnull DownloadServiceConfig downloadServiceConfig, @Nonnull DownloadServiceStatisticsTracker downloadServiceStatisticsTracker) {
        this(new DownloadCallableFactory(downloadServiceConfig, new SurgingBufferPool(2)), new DownloadThrottle(downloadServiceConfig.getMaxBackgroundThrottleDelay(), downloadServiceConfig.getBackgroundThrottleFactor(), downloadServiceStatisticsTracker), downloadServiceStatisticsTracker, new DownloadServiceQueue(downloadServiceConfig.getPrimaryBufferBeforeSecondaryDownloads(), downloadServiceConfig.getLivePrimaryBufferFillRatioBeforeSecondaryDownloads(), downloadServiceStatisticsTracker), new TimeoutScheduledExecutor("DLService", downloadServiceConfig.getDownloadServiceTheadPoolSize(), downloadServiceConfig.getDownloadServiceThreadPoolCoreThreadKeepAliveTime().getTotalMilliseconds(), downloadServiceConfig.getDownloadServiceTimeoutSweepCadence().getTotalMilliseconds()), downloadServiceConfig.getBandwidthLogTimeIntervalSeconds(), Tickers.androidTicker(), downloadServiceConfig.allowImmediateDownloads(), downloadServiceConfig.getDownloadServiceMaxConcurrentDownloads());
    }

    PrioritizingDownloadService(@Nonnull DownloadCallableFactory downloadCallableFactory, @Nonnull DownloadThrottle downloadThrottle, @Nonnull DownloadServiceStatisticsTracker downloadServiceStatisticsTracker, @Nonnull DownloadServiceQueue downloadServiceQueue, @Nonnull TimeoutScheduledExecutor timeoutScheduledExecutor, @Nonnull TimeSpan timeSpan, @Nonnull Ticker ticker, boolean z, int i) {
        this.mConcurrencyLimitedRunningRequests = Maps.newConcurrentMap();
        this.mImmediatelyDispatchedRunningRequests = Maps.newConcurrentMap();
        this.mListeners = new CopyOnWriteArraySet();
        this.mRunningRequestsLock = new Object();
        this.mCallbackInvoker = new DownloadCompletedListener() { // from class: com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService.1
            @Override // com.amazon.avod.media.downloadservice.internal.DownloadCompletedListener
            public void onDownloadComplete(@Nonnull DownloadRequest downloadRequest, @Nonnull DownloadStatistics downloadStatistics) {
                Future future;
                Future future2;
                String url = downloadRequest.getUrl();
                Float fValueOf = Float.valueOf(DataUnit.BYTES.toKiloBytes(downloadStatistics.getBytesProcessed()));
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                DLog.devf("DownloadRequest complete %s sizeKb: %.2f latencyMs: %s processedTimeMs: %s totalTimeMs: %s", url, fValueOf, Long.valueOf(timeUnit.toMillis(downloadStatistics.getLatencyInNanos())), Long.valueOf(timeUnit.toMillis(downloadStatistics.getProcessedTimeInNanos())), Long.valueOf(timeUnit.toMillis(downloadStatistics.getTotalTimeInNanos())));
                synchronized (PrioritizingDownloadService.this.mRunningRequestsLock) {
                    future = (Future) PrioritizingDownloadService.this.mConcurrencyLimitedRunningRequests.remove(downloadRequest);
                    future2 = (Future) PrioritizingDownloadService.this.mImmediatelyDispatchedRunningRequests.remove(downloadRequest);
                    PrioritizingDownloadService.this.mDownloadServiceStatsTracker.updateBandwidthStatistics(downloadStatistics);
                    PrioritizingDownloadService.this.updateRunningRequests();
                    PrioritizingDownloadService.this.tryLogBandwidth();
                }
                if (future == null) {
                    future = future2;
                }
                if (future != null) {
                    DownloadListener listener = downloadRequest.getListener();
                    try {
                        future.get();
                        listener.onSuccess(downloadRequest, downloadStatistics);
                    } catch (InterruptedException e) {
                        listener.onFailure(downloadRequest, e, downloadStatistics);
                    } catch (CancellationException unused) {
                    } catch (ExecutionException e2) {
                        listener.onFailure(downloadRequest, (Exception) MoreObjects.firstNonNull(CastUtils.castTo(e2.getCause(), Exception.class), e2), downloadStatistics);
                    }
                }
            }
        };
        this.andReenqueue = new VoidFunction<DownloadRequest>() { // from class: com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService.2
            @Override // com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService.VoidFunction
            public void apply(DownloadRequest downloadRequest) {
                PrioritizingDownloadService.this.submitDownloadRequest(downloadRequest);
            }
        };
        this.andNotify = new VoidFunction<DownloadRequest>() { // from class: com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService.3
            @Override // com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService.VoidFunction
            public void apply(DownloadRequest downloadRequest) {
                downloadRequest.getListener().onCancel(downloadRequest);
            }
        };
        this.mCallableFactory = (DownloadCallableFactory) Preconditions.checkNotNull(downloadCallableFactory, "callableFactory");
        this.mDownloadThrottle = (DownloadThrottle) Preconditions.checkNotNull(downloadThrottle, "downloadThrottle");
        this.mDownloadServiceStatsTracker = (DownloadServiceStatisticsTracker) Preconditions.checkNotNull(downloadServiceStatisticsTracker, "downloadStatisticsTracker");
        this.mQueuedRequests = (DownloadServiceQueue) Preconditions.checkNotNull(downloadServiceQueue, "downloadServiceQueue");
        this.mLogInterval = (TimeSpan) Preconditions.checkNotNull(timeSpan, "logInterval");
        this.mScheduler = (TimeoutScheduledExecutor) Preconditions.checkNotNull(timeoutScheduledExecutor, "scheduledExecutor");
        this.mStopwatch = Stopwatch.createUnstarted(ticker);
        this.mAllowImmediateDownloads = z;
        this.mDownloadServiceMaxConcurrentDownloads = i;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    public void submitDownloadRequest(DownloadRequest downloadRequest, boolean z) {
        if (this.mAllowImmediateDownloads && z) {
            synchronized (this.mRunningRequestsLock) {
                this.mImmediatelyDispatchedRunningRequests.put(downloadRequest, this.mScheduler.schedule(this.mCallableFactory.newDownloadCallable(this.mCallbackInvoker, downloadRequest), this.mDownloadThrottle.getDelayMillis(downloadRequest.getPriority().getApprxSizeBytes()), TimeUnit.MILLISECONDS));
            }
            return;
        }
        this.mQueuedRequests.enqueue(downloadRequest);
        updateRunningRequests();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitDownloadRequest(@Nonnull DownloadRequest downloadRequest) {
        submitDownloadRequest(downloadRequest, false);
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    public void cancelDownloadRequest(DownloadRequest downloadRequest) {
        boolean zRemove;
        synchronized (this.mRunningRequestsLock) {
            ScheduledFuture scheduledFutureRemove = this.mConcurrencyLimitedRunningRequests.remove(downloadRequest);
            ScheduledFuture scheduledFutureRemove2 = this.mImmediatelyDispatchedRunningRequests.remove(downloadRequest);
            if (scheduledFutureRemove == null) {
                scheduledFutureRemove = scheduledFutureRemove2;
            }
            if (scheduledFutureRemove != null) {
                zRemove = scheduledFutureRemove.cancel(true);
            } else {
                zRemove = this.mQueuedRequests.remove(downloadRequest);
            }
        }
        if (zRemove) {
            downloadRequest.getListener().onCancel(downloadRequest);
        }
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    public void updatePrimaryContentBufferInNanos(long j) {
        this.mDownloadServiceStatsTracker.updateBufferLengthInNanos(j);
        updateRunningRequests();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    public void setMaxPrimaryContentBufferInNanos(long j) {
        this.mDownloadServiceStatsTracker.setMaxBufferLengthInNanos(j);
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    public BandwidthStatistics getBandwidthStatistics() {
        return this.mDownloadServiceStatsTracker.getBandwidthStatistics();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    public void restrictBackgroundDownloads(boolean z) {
        this.mQueuedRequests.restrictBackgroundDownloads(z);
        updateRunningRequests();
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    @Nonnull
    public Collection<DownloadRequest> enableSecondaryDownloads(boolean z, boolean z2) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        this.mQueuedRequests.enableSecondaryDownloads(z);
        if (!z) {
            DLog.logf("Disabling secondary requests, checking for running requests to cancel...");
            arrayListNewArrayList.addAll(cancelRunningAuxiliaryRequests(this.andReenqueue));
            updateRunningRequests();
        }
        Iterator<DownloadService.GlobalEventListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onSecondaryDownloadsEnabledStateChange(z, z2);
        }
        return arrayListNewArrayList;
    }

    @Override // com.amazon.avod.media.downloadservice.DownloadService
    @Nonnull
    public Collection<DownloadRequest> cancelAuxiliaryDownloadRequests() {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        synchronized (this.mRunningRequestsLock) {
            for (DownloadRequest downloadRequest : this.mQueuedRequests.dequeue(PriorityTier.AUXILIARY)) {
                DLog.logf("Cancelling AUXILIARY request %s", downloadRequest.getUrl());
                downloadRequest.getListener().onCancel(downloadRequest);
            }
            arrayListNewArrayList.addAll(cancelRunningAuxiliaryRequests(this.andNotify));
        }
        return arrayListNewArrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRunningRequests() {
        DownloadRequest downloadRequestDequeue;
        synchronized (this.mRunningRequestsLock) {
            while (this.mConcurrencyLimitedRunningRequests.size() < this.mDownloadServiceMaxConcurrentDownloads && (downloadRequestDequeue = this.mQueuedRequests.dequeue()) != null) {
                this.mConcurrencyLimitedRunningRequests.put(downloadRequestDequeue, this.mScheduler.schedule(this.mCallableFactory.newDownloadCallable(this.mCallbackInvoker, downloadRequestDequeue), this.mDownloadThrottle.getDelayMillis(downloadRequestDequeue.getPriority().getApprxSizeBytes()), TimeUnit.MILLISECONDS));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryLogBandwidth() {
        synchronized (this.mStopwatch) {
            if (!this.mStopwatch.isRunning()) {
                this.mStopwatch.start();
            } else if (this.mStopwatch.elapsed(TimeUnit.MILLISECONDS) > this.mLogInterval.getTotalMilliseconds()) {
                DLog.logf("Application Bandwidth Observation: %s", this.mDownloadServiceStatsTracker.getBandwidthStatistics());
                this.mStopwatch.reset().start();
            }
        }
    }

    private Collection<DownloadRequest> cancelRunningAuxiliaryRequests(VoidFunction<DownloadRequest> voidFunction) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        synchronized (this.mRunningRequestsLock) {
            for (DownloadRequest downloadRequest : this.mConcurrencyLimitedRunningRequests.keySet()) {
                PriorityTier tier = downloadRequest.getPriority().getTier();
                if (tier == PriorityTier.AUXILIARY) {
                    ScheduledFuture scheduledFutureRemove = this.mConcurrencyLimitedRunningRequests.remove(downloadRequest);
                    DLog.logf("Cancelling %s download request for %s", tier, downloadRequest.getUrl());
                    if (scheduledFutureRemove != null && scheduledFutureRemove.cancel(true)) {
                        voidFunction.apply(downloadRequest);
                        arrayListNewArrayList.add(downloadRequest);
                    }
                }
            }
        }
        return arrayListNewArrayList;
    }

    static class DownloadCallableFactory {
        private boolean mAreConfigsCached;
        private boolean mAreRequestTimeoutsStrictlyEnforced;
        private final DownloadServiceConfig mConfig;
        private int mConnectTimeoutMillis;
        private boolean mFailOnContentLengthMismatch;
        private TimeSpan mHttpResponseCacheMaxAge;
        private boolean mIsEnabledDTIDInQueryParameter;
        private boolean mIsHttpResponseCacheEnabled;
        private String mPingUrl;
        private int mReadTimeoutMillis;
        private boolean mRequestGzipCompression;
        private boolean mShouldPingServerAfterDownloadFailure;
        private boolean mShouldUseThrottledMeteringInputStream;
        private boolean mSocketTaggingEnabled;
        private final SurgingBufferPool mSurgingBufferPool;
        private TimeSpan mThrottledMeteringInputStreamTimeThreshold;
        private String mUserAgent;

        public DownloadCallableFactory(@Nonnull DownloadServiceConfig downloadServiceConfig, @Nonnull SurgingBufferPool surgingBufferPool) {
            this.mConfig = (DownloadServiceConfig) Preconditions.checkNotNull(downloadServiceConfig, "config");
            this.mSurgingBufferPool = (SurgingBufferPool) Preconditions.checkNotNull(surgingBufferPool, "surgingBufferPool");
        }

        private void cacheConfigs() {
            if (this.mAreConfigsCached) {
                return;
            }
            this.mSocketTaggingEnabled = this.mConfig.isSocketTaggingEnabled();
            this.mShouldUseThrottledMeteringInputStream = this.mConfig.shouldUseThrottledMeteringInputStream();
            this.mThrottledMeteringInputStreamTimeThreshold = this.mConfig.getThrottledMeteringInputStreamTimeThreshold();
            this.mFailOnContentLengthMismatch = this.mConfig.failOnContentLengthMismatch();
            this.mShouldPingServerAfterDownloadFailure = this.mConfig.shouldPingServerAfterDownloadFailure();
            this.mPingUrl = this.mConfig.getPingUrl();
            this.mUserAgent = this.mConfig.getHttpUserAgent();
            this.mConnectTimeoutMillis = this.mConfig.getHttpConnectionTimeoutMilliseconds();
            this.mReadTimeoutMillis = this.mConfig.getHttpSocketTimeoutMilliseconds();
            this.mIsEnabledDTIDInQueryParameter = this.mConfig.shouldAddDTIDForManifestAndFragment();
            this.mRequestGzipCompression = this.mConfig.shouldRequestGzipCompression();
            this.mIsHttpResponseCacheEnabled = this.mConfig.isHttpResponseCacheEnabled();
            this.mHttpResponseCacheMaxAge = this.mConfig.getHttpResponseCacheMaxAge();
            this.mAreRequestTimeoutsStrictlyEnforced = this.mConfig.areRequestTimeoutsStrictlyEnforced();
            this.mAreConfigsCached = true;
        }

        @Nonnull
        public DownloadCallable newDownloadCallable(@Nonnull DownloadCompletedListener downloadCompletedListener, @Nonnull DownloadRequest downloadRequest) {
            cacheConfigs();
            OkHttpClient okHttpClient = OkHttpClientFactory.getInstance().getOkHttpClient(downloadRequest.shouldAllowHttpRedirects());
            if (okHttpClient != null) {
                return new DownloadCallableV2(new DownloadCallableV2.OkHttpRequester(okHttpClient, this.mUserAgent, this.mIsEnabledDTIDInQueryParameter, this.mRequestGzipCompression), NetworkConnectionManager.getInstance(), downloadCompletedListener, downloadRequest, DownloadStatistics.newBuilder(), Tickers.androidTicker(), this.mSurgingBufferPool, this.mSocketTaggingEnabled, this.mShouldUseThrottledMeteringInputStream, this.mThrottledMeteringInputStreamTimeThreshold, this.mFailOnContentLengthMismatch, this.mShouldPingServerAfterDownloadFailure, this.mPingUrl, this.mIsHttpResponseCacheEnabled, this.mHttpResponseCacheMaxAge, this.mAreRequestTimeoutsStrictlyEnforced);
            }
            return new DownloadCallableV1(new DownloadCallableV1.ConnectionOpener(this.mUserAgent, this.mConnectTimeoutMillis, this.mReadTimeoutMillis, this.mIsEnabledDTIDInQueryParameter, this.mRequestGzipCompression), NetworkConnectionManager.getInstance(), downloadCompletedListener, downloadRequest, DownloadStatistics.newBuilder(), Tickers.androidTicker(), this.mSurgingBufferPool, this.mSocketTaggingEnabled, this.mShouldUseThrottledMeteringInputStream, this.mThrottledMeteringInputStreamTimeThreshold, this.mFailOnContentLengthMismatch, this.mShouldPingServerAfterDownloadFailure, this.mPingUrl, this.mIsHttpResponseCacheEnabled, this.mHttpResponseCacheMaxAge, this.mAreRequestTimeoutsStrictlyEnforced);
        }
    }
}
