package com.amazon.avod.content.dash.quality.downloading;

import com.amazon.avod.connectivity.ConnectionChangeListener;
import com.amazon.avod.connectivity.DetailedNetworkInfo;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer;
import com.amazon.avod.content.dash.quality.heuristic.DownloadStatus;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsStreamType;
import com.amazon.avod.content.dash.quality.heuristic.MidstreamSwitchingController;
import com.amazon.avod.content.dash.quality.heuristic.StreamBehavior;
import com.amazon.avod.content.downloading.DownloadEventsListener;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader;
import com.amazon.avod.content.smoothstream.downloading.DownloadServiceDownloadTracker;
import com.amazon.avod.content.smoothstream.downloading.DownloadTrackerFactory;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.quality.FragmentDownloadParameters;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponentSet;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelector;
import com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.DownloadType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.internal.DownloadServiceConfig;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.pmet.ConnectivityChangeMetrics;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class ConcurrentFragmentDownloaderDash extends ConnectionChangeListener implements ConcurrentFragmentDownloader, HeuristicsCallbacks {
    private static final long DEFAULT_AVAILABLE_BUFFER_SIZE = DataUnit.MEGABYTES.toBytes(100.0f);
    private static long SECOND_IN_NANOSECOND = TimeUnit.SECONDS.toNanos(1);
    private final StreamingBitrateSelectionComponentSet mBitrateSelectionComponentSet;
    private int mCompletedDownloadsInLogInterval;
    private final SmoothStreamingPlaybackConfig mConfig;
    private final ContentManagementEventBus mContentEventDispatcher;
    private final ContentUrlSelector mContentUrlSelector;
    private ContentSessionContext mContext;
    private final DeviceResources mDeviceResources;
    private final DownloadServiceConfig mDownloadServiceConfig;
    private final DownloadSynchronizer mDownloadSynchronizer;
    private DownloadEventsListener mEventsListener;
    private final long mFixedBitrateDownloadTimeoutInNanos;
    private long mFragmentsDownloadCompleted;
    private long mFragmentsDownloadError;
    private DownloadTrackerFactory mHandlerFactory;
    private Heuristics mHeuristics;
    private boolean mHeuristicsPaused;
    private final Map<SmoothStreamingURI, DownloadServiceDownloadTracker> mInitFragmentRequestMap;
    private final AtomicBoolean mIsRunning;
    private final AtomicInteger mLastBandwidthSampleBitsPerSecond;
    private final TimeSpan mLogFragmentInfoAfterTime;
    private final Stopwatch mLogTimer;
    private final MidstreamSwitchingController mMidstreamSwitchingController;
    private ScheduledExecutorService mMidstreamSwitchingScheduledExecutor;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final long mReservedMemoryInBytes;
    private ScheduledExecutorService mScheduledExecutor;
    private final boolean mShouldNotifyHeuristicsOnDownloaderStateChange;
    private final long mShutDownTimeoutMillis;
    private final StreamBehavior mStreamBehavior;
    private StreamIndex mStreamIndex;
    private StreamDownloaderState mStreamState;
    private final StreamingBitrateSelectionState mStreamingBitrateSelectionState;
    private final Map<SmoothStreamingURI, DownloadServiceDownloadTracker> mUriRequestMap;

    public ConcurrentFragmentDownloaderDash(DownloadTrackerFactory downloadTrackerFactory, ContentUrlSelector contentUrlSelector, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, StreamingBitrateSelectionComponentSet streamingBitrateSelectionComponentSet, ContentManagementEventBus contentManagementEventBus, StreamBehavior streamBehavior, DownloadSynchronizer downloadSynchronizer) {
        this(downloadTrackerFactory, contentUrlSelector, smoothStreamingPlaybackConfig, streamingBitrateSelectionComponentSet, new StreamingBitrateSelectionState(), contentManagementEventBus, streamBehavior, downloadSynchronizer, DeviceResources.getInstance(), NetworkConnectionManager.getInstance(), DownloadServiceConfig.INSTANCE, null);
    }

    public ConcurrentFragmentDownloaderDash(DownloadTrackerFactory downloadTrackerFactory, ContentUrlSelector contentUrlSelector, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, StreamingBitrateSelectionComponentSet streamingBitrateSelectionComponentSet, ContentManagementEventBus contentManagementEventBus, StreamBehavior streamBehavior, DownloadSynchronizer downloadSynchronizer, @Nullable MidstreamSwitchingController midstreamSwitchingController) {
        this(downloadTrackerFactory, contentUrlSelector, smoothStreamingPlaybackConfig, streamingBitrateSelectionComponentSet, new StreamingBitrateSelectionState(), contentManagementEventBus, streamBehavior, downloadSynchronizer, DeviceResources.getInstance(), NetworkConnectionManager.getInstance(), DownloadServiceConfig.INSTANCE, midstreamSwitchingController);
    }

    ConcurrentFragmentDownloaderDash(DownloadTrackerFactory downloadTrackerFactory, ContentUrlSelector contentUrlSelector, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, StreamingBitrateSelectionComponentSet streamingBitrateSelectionComponentSet, StreamingBitrateSelectionState streamingBitrateSelectionState, ContentManagementEventBus contentManagementEventBus, StreamBehavior streamBehavior, DownloadSynchronizer downloadSynchronizer, DeviceResources deviceResources, @Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull DownloadServiceConfig downloadServiceConfig, @Nullable MidstreamSwitchingController midstreamSwitchingController) {
        this.mLogTimer = Stopwatch.createUnstarted(Tickers.androidTicker());
        this.mInitFragmentRequestMap = Maps.newHashMap();
        this.mUriRequestMap = Maps.newHashMap();
        this.mIsRunning = new AtomicBoolean(false);
        this.mLastBandwidthSampleBitsPerSecond = new AtomicInteger(0);
        this.mCompletedDownloadsInLogInterval = 0;
        this.mFragmentsDownloadCompleted = 0L;
        this.mFragmentsDownloadError = 0L;
        this.mHandlerFactory = downloadTrackerFactory;
        this.mContentUrlSelector = contentUrlSelector;
        this.mBitrateSelectionComponentSet = streamingBitrateSelectionComponentSet;
        this.mStreamingBitrateSelectionState = streamingBitrateSelectionState;
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mStreamBehavior = streamBehavior;
        this.mDownloadSynchronizer = downloadSynchronizer;
        this.mFixedBitrateDownloadTimeoutInNanos = smoothStreamingPlaybackConfig.getDashFixedBitrateDownloadTimeout().getTotalNanoSeconds();
        this.mShutDownTimeoutMillis = smoothStreamingPlaybackConfig.getStopDashDownloaderTimeout().getTotalMilliseconds();
        this.mShouldNotifyHeuristicsOnDownloaderStateChange = smoothStreamingPlaybackConfig.shouldNotifyHeuristicsOnDownloaderStateChange();
        this.mLogFragmentInfoAfterTime = smoothStreamingPlaybackConfig.getLogFragmentInfoAfterTime();
        this.mDeviceResources = deviceResources;
        this.mReservedMemoryInBytes = DataUnit.MEGABYTES.toBytes(smoothStreamingPlaybackConfig.getMemstoreReservedMemoryInMegaBytes());
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mDownloadServiceConfig = (DownloadServiceConfig) Preconditions.checkNotNull(downloadServiceConfig, "downloadServiceConfig");
        this.mContentEventDispatcher = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentEventDispatcher");
        this.mMidstreamSwitchingController = midstreamSwitchingController;
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void initialize(ContentSessionContext contentSessionContext, StreamIndex streamIndex, StreamDownloaderState streamDownloaderState, StreamingBitrateSelector streamingBitrateSelector, DownloadEventsListener downloadEventsListener, Heuristics heuristics) {
        this.mContext = contentSessionContext;
        this.mStreamIndex = streamIndex;
        this.mStreamState = streamDownloaderState;
        streamDownloaderState.registerObserver(this);
        this.mEventsListener = downloadEventsListener;
        this.mHeuristics = heuristics;
        this.mBitrateSelectionComponentSet.initialize(contentSessionContext);
        this.mHeuristicsPaused = false;
        this.mNetworkConnectionManager.registerListener((ConnectionChangeListener) this);
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void start() {
        this.mLogTimer.reset().start();
        ScheduledExecutorBuilder scheduledExecutorBuilderWithFixedThreadPoolSize = ScheduledExecutorBuilder.newBuilderFor(this, "CallbackScheduler").withFixedThreadPoolSize(4);
        Profiler.TraceLevel traceLevel = Profiler.TraceLevel.DEBUG;
        this.mScheduledExecutor = scheduledExecutorBuilderWithFixedThreadPoolSize.withProfilerTraceLevel(traceLevel).allowCoreThreadExpiry(this.mDownloadServiceConfig.getDownloadServiceThreadPoolCoreThreadKeepAliveTime().getTotalSeconds(), TimeUnit.SECONDS).build();
        this.mMidstreamSwitchingScheduledExecutor = ScheduledExecutorBuilder.newBuilderFor(this, "MidstreamSwitchingScheduler").withFixedThreadPoolSize(2).withProfilerTraceLevel(traceLevel).build();
        boolean zCompareAndSet = this.mIsRunning.compareAndSet(false, true);
        DLog.logf("Start fragment downloader, succeeded: %s", Boolean.valueOf(zCompareAndSet));
        this.mLastBandwidthSampleBitsPerSecond.set(0);
        try {
            if (this.mShouldNotifyHeuristicsOnDownloaderStateChange) {
                this.mHeuristics.resume(this.mStreamIndex.getIndex());
            }
            this.mHeuristics.bufferStateChanged();
        } catch (ContentException e) {
            DLog.exceptionf(e, "Failed to notify the engine about the state change", new Object[0]);
        }
        Preconditions.checkState(zCompareAndSet, "Can't call start() twice in a row!");
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void stop() {
        boolean zCompareAndSet = this.mIsRunning.compareAndSet(true, false);
        DLog.logf("Stop fragment downloader, succeeded: %s", Boolean.valueOf(zCompareAndSet));
        Preconditions.checkState(zCompareAndSet, "Can't call stop() twice in a row!");
        if (this.mShouldNotifyHeuristicsOnDownloaderStateChange) {
            try {
                this.mHeuristics.pause(this.mStreamIndex.getIndex());
            } catch (ContentException e) {
                DLog.warnf("Unable to pause Heuristics! %s", e.getMessage());
            }
        }
        cancelRequestsAfter(null);
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(this.mInitFragmentRequestMap.size());
        synchronized (this.mUriRequestMap) {
            arrayListNewArrayListWithCapacity.addAll(this.mInitFragmentRequestMap.values());
            this.mInitFragmentRequestMap.clear();
        }
        Iterator it = arrayListNewArrayListWithCapacity.iterator();
        while (it.hasNext()) {
            ((DownloadServiceDownloadTracker) it.next()).cancel();
        }
        this.mMidstreamSwitchingScheduledExecutor.shutdownNow();
        this.mScheduledExecutor.shutdownNow();
        try {
            if (this.mScheduledExecutor.awaitTermination(this.mShutDownTimeoutMillis, TimeUnit.MILLISECONDS)) {
                return;
            }
            DLog.warnf("Couldn't shutdown stream %s in %s milliseconds", this.mStreamIndex, Long.valueOf(this.mShutDownTimeoutMillis));
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            DLog.warnf("Downloader shutting down is interrupted! %s", e2);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void dispose() {
        this.mNetworkConnectionManager.unregisterListener(this);
        DownloadTrackerFactory downloadTrackerFactory = this.mHandlerFactory;
        if (downloadTrackerFactory != null) {
            downloadTrackerFactory.dispose();
            this.mHandlerFactory = null;
        } else {
            DLog.warnf("dispose() is called more than once!");
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public int getBandwidthBps() {
        return this.mLastBandwidthSampleBitsPerSecond.get();
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public boolean hasUpdatesForContentView() {
        return this.mFragmentsDownloadCompleted > 0;
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public boolean hasDownloadErrors() {
        return this.mFragmentsDownloadError > 0;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamStateObserver
    public void onStreamStateChanged() {
        Preconditions.checkState(!Thread.holdsLock(this.mUriRequestMap), "Don't call while holding locks - will cause deadlock!");
        try {
            this.mHeuristics.bufferStateChanged();
        } catch (ContentException e) {
            DLog.exceptionf(e, "Failed to notify the engine about the state change", new Object[0]);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamStateObserver
    public void onStreamPositionChanged() {
        updateBufferSize(0L, true);
        cancelRequestsAfter(null);
        this.mDownloadSynchronizer.onSeeked(this.mContext.getState().getPlayPositionInNanos());
    }

    private void cancelRequestsAfter(SmoothStreamingURI smoothStreamingURI) {
        int chunkIndex = (smoothStreamingURI == null || smoothStreamingURI.isInitFragment()) ? Integer.MIN_VALUE : smoothStreamingURI.getChunkIndex();
        ArrayList<Map.Entry> arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(this.mUriRequestMap.size());
        synchronized (this.mUriRequestMap) {
            Iterator<Map.Entry<SmoothStreamingURI, DownloadServiceDownloadTracker>> it = this.mUriRequestMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<SmoothStreamingURI, DownloadServiceDownloadTracker> next = it.next();
                if (next.getKey().getChunkIndex() > chunkIndex) {
                    arrayListNewArrayListWithCapacity.add(next);
                    it.remove();
                }
            }
        }
        for (Map.Entry entry : arrayListNewArrayListWithCapacity) {
            ((DownloadServiceDownloadTracker) entry.getValue()).cancel();
            notifyDownloadFailure(((SmoothStreamingURI) entry.getKey()).getChunkIndex(), ((DownloadServiceDownloadTracker) entry.getValue()).getDownloadHandle(), DownloadStatus.Cancelled);
        }
    }

    private boolean isCurrentlyDownloading(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mUriRequestMap) {
            if (smoothStreamingURI.isInitFragment()) {
                return this.mInitFragmentRequestMap.containsKey(smoothStreamingURI);
            }
            return this.mUriRequestMap.containsKey(smoothStreamingURI);
        }
    }

    private void updateBufferSize(long j, boolean z) {
        Preconditions.checkState(!Thread.holdsLock(this.mUriRequestMap), "Don't call while holding locks - will cause deadlock!");
        try {
            this.mHeuristics.bufferStateChanged();
        } catch (ContentException e) {
            DLog.exceptionf(e, "Failed to notify the engine about the state change", new Object[0]);
        }
        this.mEventsListener.onBufferSizeChange(j, this.mStreamIndex, z);
    }

    private void logFragmentsDownloaded(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mLogTimer) {
            this.mCompletedDownloadsInLogInterval++;
            long jElapsed = this.mLogTimer.elapsed(TimeUnit.SECONDS);
            if (jElapsed > this.mLogFragmentInfoAfterTime.getTotalSeconds()) {
                DLog.logf("Downloaded %s (%d fragments downloaded in last %d seconds)", smoothStreamingURI, Integer.valueOf(this.mCompletedDownloadsInLogInterval), Long.valueOf(jElapsed));
                this.mLogTimer.reset().start();
                this.mCompletedDownloadsInLogInterval = 0;
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public FragmentDownloadParameters getDownloadParameters(final SmoothStreamingURI smoothStreamingURI, int i, long j) {
        FragmentDownloadParameters fragmentDownloadParameters = new FragmentDownloadParameters(smoothStreamingURI, j);
        try {
            this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.dash.quality.downloading.ConcurrentFragmentDownloaderDash.1
                @Override // java.lang.Runnable
                public void run() {
                    ConcurrentFragmentDownloaderDash.this.mEventsListener.onFragmentDownloadRequested(smoothStreamingURI);
                }
            });
        } catch (RejectedExecutionException unused) {
            DLog.logf("Executor was terminated but we are still trying call onFragmentDownloadRequested. Callback will not be called.");
        }
        return fragmentDownloadParameters;
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public ContentUrl getManifestUrl() {
        return this.mContentUrlSelector.getCurrentContentUrl();
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void onSuccess(final SmoothStreamingURI smoothStreamingURI, long j, final ContentUrl contentUrl, final int i, long j2, final DownloadStatistics downloadStatistics) {
        if (!this.mIsRunning.get()) {
            DLog.logf("This downloader was already stopped. Not notifying a DownloadSuccess.");
            return;
        }
        notifyDownloadSuccess(smoothStreamingURI, j, downloadStatistics, DownloadStatus.Success);
        try {
            this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.dash.quality.downloading.ConcurrentFragmentDownloaderDash.2
                @Override // java.lang.Runnable
                public void run() {
                    ConcurrentFragmentDownloaderDash.this.mEventsListener.onFragmentDownloadFinished(contentUrl, smoothStreamingURI, downloadStatistics, i);
                }
            });
        } catch (RejectedExecutionException unused) {
            DLog.logf("Executor was terminated but we are still trying call onFragmentDownloadFinished. Callback will not be called.");
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void onDownloadProgress(SmoothStreamingURI smoothStreamingURI, long j, int i, DownloadStatistics downloadStatistics, long j2) {
        Preconditions.checkState(!Thread.holdsLock(this.mUriRequestMap), "Don't call while holding locks - will cause deadlock!");
        try {
            DLog.devf("%s: fragment# %d BytesProcessed %d Size %d ratioCompleted %f", smoothStreamingURI.isAudio() ? "Audio" : "Video", Integer.valueOf(smoothStreamingURI.getChunkIndex()), Long.valueOf(downloadStatistics.getBytesProcessed()), Long.valueOf(j2), Float.valueOf(downloadStatistics.getBytesProcessed() / j2));
            this.mHeuristics.reportDownloadProgress(this.mStreamIndex.getIndex(), smoothStreamingURI.getChunkIndex(), j, downloadStatistics.getLatencyInNanos() / 100, (int) downloadStatistics.getBytesProcessed(), DownloadStatus.PartSuccess, j2);
        } catch (ContentException e) {
            DLog.exceptionf(e, "Reporting download progress to the heuristics engine failed", new Object[0]);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void notifyDownloadSuccess(SmoothStreamingURI smoothStreamingURI, long j) {
        if (!this.mIsRunning.get()) {
            DLog.logf("This downloader was already stopped. Not notifying a DownloadSuccess.");
            return;
        }
        notifyDownloadSuccess(smoothStreamingURI, j, DownloadStatistics.newBuilder().build(), DownloadStatus.Existing);
        if (this.mStreamIndex.requiresInitFragments()) {
            startDownload(smoothStreamingURI.getInitFragmentUri(), Long.MAX_VALUE);
        }
    }

    void notifyDownloadSuccess(final SmoothStreamingURI smoothStreamingURI, final long j, final DownloadStatistics downloadStatistics, final DownloadStatus downloadStatus) {
        updateBufferSize(this.mStreamState.getDurationAfterPositionInNanos(this.mContext.getState().getPlayPositionInNanos()), false);
        if (smoothStreamingURI.isAudio() || smoothStreamingURI.isVideo()) {
            this.mContentUrlSelector.notifyDownloadSuccess(downloadStatistics);
        }
        logFragmentsDownloaded(smoothStreamingURI);
        this.mFragmentsDownloadCompleted++;
        if (this.mHeuristics.getHeuristicsPlaybackConfig() != null && this.mHeuristics.getHeuristicsPlaybackConfig().isMidstreamSwitchingEnabled() && this.mMidstreamSwitchingController != null) {
            try {
                this.mMidstreamSwitchingScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.dash.quality.downloading.ConcurrentFragmentDownloaderDash.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (ConcurrentFragmentDownloaderDash.this.mMidstreamSwitchingController != null) {
                            if (smoothStreamingURI.isVideo() || smoothStreamingURI.isAudio()) {
                                synchronized (ConcurrentFragmentDownloaderDash.this.mMidstreamSwitchingController) {
                                    ConcurrentFragmentDownloaderDash.this.mMidstreamSwitchingController.reportDownload(downloadStatistics.getThroughput(), downloadStatistics.getLatencyInNanos());
                                }
                            }
                        }
                    }
                });
            } catch (RejectedExecutionException e) {
                DLog.exceptionf(e, "Executor was terminated but we are still trying call onSuccessfulDownload. Midstream Switching Controller will not be called.", new Object[0]);
            }
        }
        try {
            this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.dash.quality.downloading.ConcurrentFragmentDownloaderDash.4
                @Override // java.lang.Runnable
                public void run() {
                    ConcurrentFragmentDownloaderDash.this.mStreamState.onSuccessfulDownload(smoothStreamingURI);
                    synchronized (ConcurrentFragmentDownloaderDash.this.mUriRequestMap) {
                        if (smoothStreamingURI.isInitFragment()) {
                            ConcurrentFragmentDownloaderDash.this.mInitFragmentRequestMap.remove(smoothStreamingURI);
                        }
                        ConcurrentFragmentDownloaderDash.this.mUriRequestMap.remove(smoothStreamingURI);
                    }
                    ConcurrentFragmentDownloaderDash.this.mDownloadSynchronizer.onFragmentDownloaded(smoothStreamingURI);
                    Preconditions.checkState(!Thread.holdsLock(ConcurrentFragmentDownloaderDash.this.mUriRequestMap), "Don't call while holding locks - will cause deadlock!");
                    try {
                        ConcurrentFragmentDownloaderDash.this.mHeuristics.reportDownloadProgress(ConcurrentFragmentDownloaderDash.this.mStreamIndex.getIndex(), smoothStreamingURI.getChunkIndex(), j, downloadStatistics.getLatencyInNanos() / 100, (int) downloadStatistics.getBytesProcessed(), downloadStatus, -1L);
                    } catch (ContentException e2) {
                        DLog.exceptionf(e2, "Reporting download progress to the heuristics engine failed", new Object[0]);
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            DLog.logf("Executor was terminated but we are still trying call onSuccessfulDownload. Callback will not be called.");
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void onFailure(SmoothStreamingURI smoothStreamingURI, long j, ContentUrl contentUrl, ContentException contentException, int i, @Nonnull DownloadStatistics downloadStatistics) {
        DownloadServiceDownloadTracker downloadServiceDownloadTrackerRemove;
        if (!this.mIsRunning.get()) {
            DLog.logf("This downloader was already stopped. Not notifying a DownloadFailure.");
            return;
        }
        Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
        this.mFragmentsDownloadError++;
        updateBufferSize(this.mStreamState.getDurationAfterPositionInNanos(this.mContext.getState().getPlayPositionInNanos()), false);
        if (smoothStreamingURI.isAudio() || smoothStreamingURI.isVideo()) {
            this.mContentUrlSelector.notifyDownloadFailure(contentException, contentUrl, i, smoothStreamingURI.getPresentationTimeInNanos(), false, null, downloadStatistics, DownloadType.FRAGMENT);
        }
        if (this.mContext.getSpecification().isLiveStream() && contentException.getErrorCode() == ContentException.ContentError.CDN_ERROR && contentException.getStatusCode() == 404) {
            this.mContext.getState().getLiveDynamicFrontMargin().onFragmentNotFound();
            DLog.warnf("Increase live front margin: %s ns", Long.valueOf(this.mContext.getState().getLiveDynamicFrontMargin().getCurrentFrontMarginNanos()));
        }
        DLog.warnf("Download for %s failed with error %s (try number %d)", smoothStreamingURI, contentException, Integer.valueOf(i));
        this.mEventsListener.onDownloadError(contentUrl, smoothStreamingURI, contentException, i, downloadStatistics.getUnformattedHeaders());
        synchronized (this.mUriRequestMap) {
            if (smoothStreamingURI.isInitFragment()) {
                downloadServiceDownloadTrackerRemove = this.mInitFragmentRequestMap.remove(smoothStreamingURI);
            } else {
                downloadServiceDownloadTrackerRemove = this.mUriRequestMap.remove(smoothStreamingURI);
            }
        }
        if (downloadServiceDownloadTrackerRemove != null) {
            downloadServiceDownloadTrackerRemove.cancel();
        }
        notifyDownloadFailure(smoothStreamingURI.getChunkIndex(), j, DownloadStatus.Failed);
    }

    private void notifyDownloadFailure(int i, long j, DownloadStatus downloadStatus) {
        Preconditions.checkState(!Thread.holdsLock(this.mUriRequestMap), "Don't call while holding locks - will cause deadlock!");
        try {
            this.mHeuristics.reportDownloadProgress(this.mStreamIndex.getIndex(), i, j, 0L, 0L, downloadStatus, -1L);
        } catch (ContentException e) {
            DLog.exceptionf(e, "Reporting download progress to the heuristics engine failed", new Object[0]);
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public boolean cancelDownload(final int i, int i2, long j) {
        final SmoothStreamingURI key;
        final DownloadServiceDownloadTracker value;
        synchronized (this.mUriRequestMap) {
            Iterator<Map.Entry<SmoothStreamingURI, DownloadServiceDownloadTracker>> it = this.mUriRequestMap.entrySet().iterator();
            while (true) {
                key = null;
                if (!it.hasNext()) {
                    value = null;
                    break;
                }
                Map.Entry<SmoothStreamingURI, DownloadServiceDownloadTracker> next = it.next();
                if (j == next.getValue().getDownloadHandle()) {
                    key = next.getKey();
                    value = next.getValue();
                    break;
                }
            }
            if (key != null) {
                this.mUriRequestMap.remove(key);
            }
        }
        if (value != null) {
            try {
                this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.dash.quality.downloading.ConcurrentFragmentDownloaderDash.5
                    @Override // java.lang.Runnable
                    public void run() {
                        URL url;
                        value.cancel();
                        ContentUrl currentContentUrl = ConcurrentFragmentDownloaderDash.this.mContentUrlSelector.getCurrentContentUrl();
                        SmoothStreamingURI smoothStreamingURI = key;
                        if (smoothStreamingURI != null) {
                            String absoluteUrl = smoothStreamingURI.getAbsoluteUrl(currentContentUrl.getUrl());
                            try {
                                url = new URL(absoluteUrl);
                            } catch (MalformedURLException unused) {
                                DLog.warnf("Failed to construct fragment URL from %s", absoluteUrl);
                                url = null;
                            }
                        } else {
                            url = null;
                        }
                        ContentException contentException = new ContentException(ContentException.ContentError.HEURISTICS_CANCELLATION, String.format(Locale.US, "Heuristics cancelled fragment download, buffered duration: %d millis, SSU: %s", Long.valueOf(TimeUnit.NANOSECONDS.toMillis(ConcurrentFragmentDownloaderDash.this.bufferedDuration(i) * 100)), key), null, url);
                        ContentUrlSelector contentUrlSelector = ConcurrentFragmentDownloaderDash.this.mContentUrlSelector;
                        SmoothStreamingURI smoothStreamingURI2 = key;
                        contentUrlSelector.notifyDownloadFailure(contentException, currentContentUrl, 0, smoothStreamingURI2 == null ? 0L : smoothStreamingURI2.getPresentationTimeInNanos(), false, null, null, DownloadType.FRAGMENT);
                        ConcurrentFragmentDownloaderDash.this.mEventsListener.onDownloadError(currentContentUrl, key, contentException, 0, null);
                    }
                });
            } catch (RejectedExecutionException unused) {
                DLog.warnf("Cannot cancel download request for uri %s because mScheduledExecutor rejected", key);
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long startDownload(int i, int i2, int i3, long j, long j2, FragmentType fragmentType) {
        QualityLevel qualityLevelSelectQuality;
        DLog.devf("Attempting to startDownload streamIndex %d fragmentIndex %d qualityIndex %d timeoutInHundredsOfNanos %d observedBandwidthBytesPerSecond %d FragmentType %s", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Long.valueOf(j), Long.valueOf(j2), fragmentType);
        if (!this.mIsRunning.get()) {
            DLog.devf("stopping startDownload due to DOWNLOAD_HANDLE_VALUE_CALLBACK_STOPPED");
            return -5L;
        }
        synchronized (this.mStreamingBitrateSelectionState) {
            this.mStreamingBitrateSelectionState.set(this.mStreamIndex, i2, fragmentType);
            qualityLevelSelectQuality = this.mBitrateSelectionComponentSet.selectQuality(this.mStreamingBitrateSelectionState, this.mStreamIndex.getQualityLevel(i2, i3));
        }
        SmoothStreamingURI smoothStreamingURI = new SmoothStreamingURI(this.mStreamIndex, qualityLevelSelectQuality, i2, false, fragmentType);
        this.mLastBandwidthSampleBitsPerSecond.set((int) DataUnit.BYTES.toBits(j2));
        if (!this.mDownloadSynchronizer.shouldDownload(smoothStreamingURI)) {
            DLog.devf("stopping startDownload due to DOWNLOAD_HANDLE_VALUE_INTERNAL_ERROR");
            return -2L;
        }
        if (this.mStreamIndex.requiresInitFragments()) {
            startDownload(smoothStreamingURI.getInitFragmentUri(), Long.MAX_VALUE);
        }
        return startDownload(smoothStreamingURI, 100 * j);
    }

    private long startDownload(SmoothStreamingURI smoothStreamingURI, long j) {
        DLog.devf("Attempting to start download of uri %s", smoothStreamingURI);
        if (!this.mStreamState.shouldDownloadUri(smoothStreamingURI)) {
            DLog.devf("stopping startDownload due to DOWNLOAD_HANDLE_VALUE_INTERNAL_ERROR");
            return -2L;
        }
        synchronized (this.mUriRequestMap) {
            if (!this.mIsRunning.get()) {
                DLog.devf("stopping startDownload due to DOWNLOAD_HANDLE_VALUE_CALLBACK_STOPPED");
                return -5L;
            }
            if (this.mStreamState.isUriDownloaded(smoothStreamingURI) && smoothStreamingURI.getFragmentType() != FragmentType.UPGRADE) {
                DLog.devf("stopping startDownload due to DOWNLOAD_HANDLE_VALUE_FRAGMENT_DOWNLOADED");
                return -3L;
            }
            if (isCurrentlyDownloading(smoothStreamingURI)) {
                DLog.devf("stopping startDownload due to DOWNLOAD_HANDLE_VALUE_FRAGMENT_DOWNLOADING");
                return -4L;
            }
            if (this.mContext.getSessionType() == ContentSessionType.CONTENT_CACHE || this.mContext.getSessionType() == ContentSessionType.DOWNLOAD || this.mContext.getSessionType() == ContentSessionType.PROGRESSIVE_PLAYBACK) {
                j = Math.max(this.mFixedBitrateDownloadTimeoutInNanos, j);
            }
            DownloadServiceDownloadTracker downloadServiceDownloadTrackerNewDownloadTracker = this.mHandlerFactory.newDownloadTracker(this.mContext, this, this.mScheduledExecutor, j);
            if (smoothStreamingURI.isInitFragment()) {
                this.mInitFragmentRequestMap.put(smoothStreamingURI, downloadServiceDownloadTrackerNewDownloadTracker);
            } else {
                this.mUriRequestMap.put(smoothStreamingURI, downloadServiceDownloadTrackerNewDownloadTracker);
            }
            DLog.devf("Requesting uri %s", smoothStreamingURI);
            downloadServiceDownloadTrackerNewDownloadTracker.download(smoothStreamingURI, ContentSessionType.needsImmediateDownloading(this.mContext.getEffectiveSessionType()));
            return downloadServiceDownloadTrackerNewDownloadTracker.getDownloadHandle();
        }
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long bufferedDuration(int i) {
        return this.mStreamState.getDurationAfterPositionInNanos(this.mContext.getState().getPlayPositionInNanos()) / 100;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long maxAvailableBufferDuration(int i) {
        Preconditions.checkArgument(i >= 0, "streamIndex");
        ContentSessionContext contentSessionContext = this.mContext;
        if (contentSessionContext == null) {
            return 0L;
        }
        if (contentSessionContext.getSpecification().isLiveStream()) {
            TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(this.mContext.getState().getLiveStreamingFutureBufferSeconds());
            DLog.devf("maxAvailableBufferDuration as %d", Long.valueOf(timeSpanFromSeconds.getTotalMilliseconds()));
            return timeSpanFromSeconds.getTotalMilliseconds();
        }
        TimeSpan timeSpanFromSeconds2 = TimeSpan.fromSeconds(this.mConfig.getStreamingFutureBufferSizeSeconds(this.mHeuristics));
        DLog.devf("maxAvailableBufferDuration as %d", Long.valueOf(timeSpanFromSeconds2.getTotalMilliseconds()));
        return timeSpanFromSeconds2.getTotalMilliseconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long availableBufferSize(int i) {
        if (this.mDeviceResources.getNativeHeapMaxSizeInBytes() > 0) {
            return Math.max(((long) this.mDeviceResources.getNativeHeapAvailableSizeInBytes()) - this.mReservedMemoryInBytes, 0L);
        }
        return DEFAULT_AVAILABLE_BUFFER_SIZE;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int missingFragment(int i, int i2) {
        Preconditions.checkArgument(i >= 0, "streamIndex");
        Preconditions.checkArgument(i2 >= 0, "missingIndex");
        SmoothStreamingURI latestExistingUri = this.mStreamState.getLatestExistingUri();
        if (latestExistingUri != null && this.mContext.getSpecification().isLiveStream() && this.mStreamIndex.isLastDownloadableChunk(latestExistingUri.getChunkIndex())) {
            return -1;
        }
        SmoothStreamingURI nextRequest = this.mStreamState.getNextRequest();
        if (nextRequest == null) {
            if (!this.mContext.getSpecification().isLiveStream()) {
                this.mEventsListener.onBufferFilled();
            }
            return -1;
        }
        while (i2 > 0) {
            if (this.mStreamIndex.isLastDownloadableChunk(nextRequest.getChunkIndex()) || (nextRequest = nextRequest.getNextURI()) == null || !this.mStreamState.shouldDownloadUri(nextRequest)) {
                return -1;
            }
            if (!this.mStreamState.isUriDownloaded(nextRequest)) {
                i2--;
            }
        }
        if (this.mContext.getSpecification().isLiveStream() && this.mStreamIndex.getType() == StreamType.SUBTITLES && this.mStreamIndex.isOutOfWindow(nextRequest.getChunkIndex())) {
            return -1;
        }
        return nextRequest.getChunkIndex();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int highestQualityObtained(int i, int i2) {
        return this.mStreamState.getHighestObtainedQualityBitrate(new SmoothStreamingURI(this.mStreamIndex, i2));
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int consumptionHead(int i) {
        return this.mContext.getState().getConsumptionHead(i);
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int streamBehavior(int i) {
        return this.mStreamBehavior.getValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long fragmentStartTime(int i, int i2) {
        return this.mStreamIndex.getChunkTimeInNanos(i2) / 100;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long fragmentDurationTime(int i, int i2) {
        return this.mStreamIndex.getChunkDurationInNanos(i2) / 100;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int fragmentQualityCount(int i, int i2) {
        return this.mStreamIndex.getSortedQualityLevels(i2).length;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int fragmentBitrate(int i, int i2, int i3) {
        return this.mStreamIndex.getQualityLevel(i2, i3).getBitrate();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public long fragmentSize(int i, int i2, int i3) {
        return (DataUnit.BITS.toBytes(this.mStreamIndex.getQualityLevel(i2, i3).getBitrate()) * this.mStreamIndex.getChunkDurationInNanos(i2)) / SECOND_IN_NANOSECOND;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int fragmentCount(int i) {
        return this.mStreamIndex.getNumChunks();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int averageBandwidthBps() {
        return this.mContext.getBandwidthStats().getAvgBandwidthBps();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public int streamType(int i) {
        return HeuristicsStreamType.fromStreamType(this.mStreamIndex.getType()).getValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsCallbacks
    public void overridePlaybackConfigsCallback(@Nonnull Map<String, String> map) {
        HeuristicsPlaybackConfig heuristicsPlaybackConfig = this.mHeuristics.getHeuristicsPlaybackConfig();
        if (heuristicsPlaybackConfig == null) {
            return;
        }
        heuristicsPlaybackConfig.applyHeuristicsEngineOverrides(map);
        this.mContext.recacheConfigs();
    }

    @Override // com.amazon.avod.connectivity.ConnectivityChangeListener
    public void onConnectionChange(DetailedNetworkInfo detailedNetworkInfo, DetailedNetworkInfo detailedNetworkInfo2) {
        try {
            DLog.logf("dataconn. onConnectionChange %s to %s", detailedNetworkInfo, detailedNetworkInfo2);
            if (detailedNetworkInfo2.hasFullNetworkAccess()) {
                if (this.mHeuristicsPaused) {
                    DLog.logf("dataconn. resume heuristics");
                    this.mHeuristicsPaused = false;
                    this.mHeuristics.resume(this.mStreamIndex.getIndex());
                }
                reportMetrics(ConnectivityChangeMetrics.CONNECTED_TO_NETWORK, "HasFullNetworkAccess");
                return;
            }
            if (!this.mHeuristicsPaused) {
                DLog.logf("dataconn. pause heuristics");
                this.mHeuristicsPaused = true;
                this.mHeuristics.pause(this.mStreamIndex.getIndex());
            }
            reportMetrics(ConnectivityChangeMetrics.DISCONNECTED_FROM_NETWORK, "HasNoNetworkAccess");
        } catch (ContentException e) {
            DLog.errorf("heuristics operations resulted in content exception %s", e.getMessage());
        }
    }

    private void reportMetrics(@Nonnull ConnectivityChangeMetrics connectivityChangeMetrics, @Nonnull String str) {
        Preconditions.checkNotNull(str, "eventSubType");
        this.mContext.getPlaybackEventReporter().reportMetric(QOSEventName.Information.name(), str, null, connectivityChangeMetrics.toReportableString(), null);
        this.mContentEventDispatcher.postEvent(new AloysiusDiagnosticEvent(str, connectivityChangeMetrics.toReportableString(), AloysiusDiagnosticsState.Discrete));
    }
}
