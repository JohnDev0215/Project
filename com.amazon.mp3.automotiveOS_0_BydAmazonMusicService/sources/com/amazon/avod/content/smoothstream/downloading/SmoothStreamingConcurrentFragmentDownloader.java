package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.downloading.DownloadEventsListener;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.FragmentDownloadParameters;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelector;
import com.amazon.avod.content.smoothstream.quality.StreamingState;
import com.amazon.avod.content.smoothstream.streamstate.StreamDownloaderState;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.content.urlvending.DownloadType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingConcurrentFragmentDownloader implements ConcurrentFragmentDownloader {
    private final int mBandwidthAverageWindowSize;
    private IntRollingStatisticsAggregator mBandwidthSampleAggregator;
    private StreamingBitrateSelector mBitrateSelector;
    private int mCompletedDownloadsInLogInterval;
    private final ContentUrlSelector mContentUrlSelector;
    private ContentSessionContext mContext;
    private DownloadEventsListener mEventsListener;
    private long mFragmentsDownloadCompleted;
    private long mFragmentsDownloadError;
    private DownloadTrackerFactory mHandlerFactory;
    private final AtomicBoolean mIsRunning;
    private final TimeSpan mLogInterval;
    private final Stopwatch mLogTimer;
    private final int mNumConcurrentDownloads;
    private ScheduledExecutorService mScheduledExecutor;
    private StreamIndex mStreamIndex;
    private StreamDownloaderState mStreamState;
    private final Map<Integer, DownloadServiceDownloadTracker> mUriRequestMap;

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void onDownloadProgress(SmoothStreamingURI smoothStreamingURI, long j, int i, DownloadStatistics downloadStatistics, long j2) {
    }

    public SmoothStreamingConcurrentFragmentDownloader(DownloadTrackerFactory downloadTrackerFactory, ContentUrlSelector contentUrlSelector, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this(downloadTrackerFactory, contentUrlSelector, smoothStreamingPlaybackConfig.getBandwidthAverageCount(), smoothStreamingPlaybackConfig.getConcurrentDownloadTaskNumberOfRequests(), smoothStreamingPlaybackConfig.getLogFragmentInfoAfterTime());
    }

    SmoothStreamingConcurrentFragmentDownloader(DownloadTrackerFactory downloadTrackerFactory, ContentUrlSelector contentUrlSelector, int i, int i2, TimeSpan timeSpan) {
        this.mLogTimer = Stopwatch.createUnstarted(Tickers.androidTicker());
        this.mUriRequestMap = Maps.newHashMap();
        this.mIsRunning = new AtomicBoolean(false);
        this.mCompletedDownloadsInLogInterval = 0;
        this.mFragmentsDownloadCompleted = 0L;
        this.mFragmentsDownloadError = 0L;
        this.mHandlerFactory = downloadTrackerFactory;
        this.mContentUrlSelector = contentUrlSelector;
        this.mBandwidthAverageWindowSize = i;
        this.mNumConcurrentDownloads = i2;
        this.mLogInterval = timeSpan;
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void initialize(ContentSessionContext contentSessionContext, StreamIndex streamIndex, StreamDownloaderState streamDownloaderState, StreamingBitrateSelector streamingBitrateSelector, DownloadEventsListener downloadEventsListener, Heuristics heuristics) {
        this.mContext = contentSessionContext;
        this.mStreamIndex = streamIndex;
        this.mStreamState = streamDownloaderState;
        streamDownloaderState.registerObserver(this);
        this.mBitrateSelector = streamingBitrateSelector;
        this.mEventsListener = downloadEventsListener;
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void start() {
        Preconditions.checkState(this.mIsRunning.compareAndSet(false, true), "Can't call start() twice in a row!");
        this.mLogTimer.reset().start();
        this.mScheduledExecutor = ScheduledExecutorBuilder.newBuilderFor(this, "RetryScheduler").withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
        this.mBandwidthSampleAggregator = new IntRollingStatisticsAggregator(this.mBandwidthAverageWindowSize);
        updateActiveRequests();
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void stop() {
        Preconditions.checkState(this.mIsRunning.compareAndSet(true, false), "Can't call stop() twice in a row!");
        cancelRequestsAfter(null);
        this.mScheduledExecutor.shutdownNow();
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.ConcurrentFragmentDownloader
    public void dispose() {
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
        return this.mBandwidthSampleAggregator.getAverage();
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
        synchronized (this.mUriRequestMap) {
            if (this.mUriRequestMap.isEmpty()) {
                updateActiveRequests();
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.StreamStateObserver
    public void onStreamPositionChanged() {
        updateBufferSize(0L, true);
        this.mBitrateSelector.updateStreamState(StreamingState.SEEKING);
        cancelRequestsAfter(null);
        updateActiveRequests();
    }

    private void updateActiveRequests() {
        if (this.mIsRunning.get() && hasAdditionalCapacity()) {
            SmoothStreamingURI nextRequest = this.mStreamState.getNextRequest();
            if (nextRequest == null) {
                this.mEventsListener.onBufferFilled();
            }
            synchronized (this.mUriRequestMap) {
                while (nextRequest != null) {
                    if (!hasAdditionalCapacity() || !this.mStreamState.shouldDownloadUri(nextRequest)) {
                        break;
                    }
                    if (!this.mStreamState.isUriDownloaded(nextRequest) && !isCurrentlyDownloading(nextRequest)) {
                        DownloadServiceDownloadTracker downloadServiceDownloadTrackerNewDownloadTracker = this.mHandlerFactory.newDownloadTracker(this.mContext, this, this.mScheduledExecutor);
                        this.mUriRequestMap.put(Integer.valueOf(nextRequest.getChunkIndex()), downloadServiceDownloadTrackerNewDownloadTracker);
                        downloadServiceDownloadTrackerNewDownloadTracker.download(nextRequest);
                    }
                    nextRequest = nextRequest.getNextURI();
                }
            }
        }
    }

    private void cancelRequestsAfter(SmoothStreamingURI smoothStreamingURI) {
        int chunkIndex = smoothStreamingURI == null ? Integer.MIN_VALUE : smoothStreamingURI.getChunkIndex();
        ArrayList arrayList = new ArrayList();
        synchronized (this.mUriRequestMap) {
            Iterator<Map.Entry<Integer, DownloadServiceDownloadTracker>> it = this.mUriRequestMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, DownloadServiceDownloadTracker> next = it.next();
                if (next.getKey().intValue() > chunkIndex) {
                    arrayList.add(next.getValue());
                    it.remove();
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((DownloadServiceDownloadTracker) it2.next()).cancel();
        }
    }

    private boolean hasAdditionalCapacity() {
        boolean z;
        synchronized (this.mUriRequestMap) {
            z = this.mUriRequestMap.size() < this.mNumConcurrentDownloads;
        }
        return z;
    }

    private boolean isCurrentlyDownloading(SmoothStreamingURI smoothStreamingURI) {
        boolean zContainsKey;
        synchronized (this.mUriRequestMap) {
            zContainsKey = this.mUriRequestMap.containsKey(Integer.valueOf(smoothStreamingURI.getChunkIndex()));
        }
        return zContainsKey;
    }

    private void updateBufferSize(long j, boolean z) {
        this.mBitrateSelector.updateBufferSizeInNanos(j);
        this.mEventsListener.onBufferSizeChange(j, this.mStreamIndex, z);
    }

    private int updateBandwidth(int i) {
        this.mBandwidthSampleAggregator.addSample(i);
        int average = this.mBandwidthSampleAggregator.getAverage();
        this.mBitrateSelector.updateBandwidth(i, average);
        return average;
    }

    private void logFragmentsDownloaded(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mLogTimer) {
            this.mCompletedDownloadsInLogInterval++;
            long jElapsed = this.mLogTimer.elapsed(TimeUnit.SECONDS);
            if (jElapsed > this.mLogInterval.getTotalSeconds()) {
                DLog.logf("Downloaded %s (%d fragments downloaded in last %d seconds).", smoothStreamingURI, Integer.valueOf(this.mCompletedDownloadsInLogInterval), Long.valueOf(jElapsed));
                DLog.logf("Average %s specific bandwidth bps: %d", smoothStreamingURI.getStream().getType(), Integer.valueOf(getBandwidthBps()));
                this.mLogTimer.reset().start();
                this.mCompletedDownloadsInLogInterval = 0;
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public FragmentDownloadParameters getDownloadParameters(SmoothStreamingURI smoothStreamingURI, int i, long j) {
        final FragmentDownloadParameters downloadParameters = this.mBitrateSelector.getDownloadParameters(smoothStreamingURI, i);
        try {
            this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.smoothstream.downloading.SmoothStreamingConcurrentFragmentDownloader.1
                @Override // java.lang.Runnable
                public void run() {
                    SmoothStreamingConcurrentFragmentDownloader.this.mEventsListener.onFragmentDownloadRequested(downloadParameters.getSmoothStreamingURI());
                }
            });
        } catch (RejectedExecutionException unused) {
            DLog.logf("Executor was terminated but we are still trying call onFragmentDownloadRequested. Callback will not be called.");
        }
        return downloadParameters;
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public ContentUrl getManifestUrl() {
        return this.mContentUrlSelector.getCurrentContentUrl();
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void onSuccess(final SmoothStreamingURI smoothStreamingURI, long j, final ContentUrl contentUrl, final int i, long j2, final DownloadStatistics downloadStatistics) {
        updateBandwidth((int) downloadStatistics.getBandwidth());
        notifyDownloadSuccess(smoothStreamingURI, j);
        try {
            this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.smoothstream.downloading.SmoothStreamingConcurrentFragmentDownloader.2
                @Override // java.lang.Runnable
                public void run() {
                    SmoothStreamingConcurrentFragmentDownloader.this.mEventsListener.onFragmentDownloadFinished(contentUrl, smoothStreamingURI, downloadStatistics, i);
                }
            });
        } catch (RejectedExecutionException unused) {
            DLog.logf("Executor was terminated but we are still trying call onFragmentDownloadFinished. Callback will not be called.");
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void notifyDownloadSuccess(final SmoothStreamingURI smoothStreamingURI, long j) {
        updateBufferSize(this.mStreamState.getDurationAfterPositionInNanos(this.mContext.getState().getPlayPositionInNanos()), false);
        this.mContentUrlSelector.notifyDownloadSuccess(null);
        logFragmentsDownloaded(smoothStreamingURI);
        try {
            this.mScheduledExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.smoothstream.downloading.SmoothStreamingConcurrentFragmentDownloader.3
                @Override // java.lang.Runnable
                public void run() {
                    SmoothStreamingConcurrentFragmentDownloader.this.mStreamState.onSuccessfulDownload(smoothStreamingURI);
                }
            });
        } catch (RejectedExecutionException unused) {
            DLog.logf("Executor was terminated but we are still trying call onSuccessfulDownload. Callback will not be called.");
        }
        this.mFragmentsDownloadCompleted++;
        synchronized (this.mUriRequestMap) {
            this.mUriRequestMap.remove(Integer.valueOf(smoothStreamingURI.getChunkIndex()));
            updateActiveRequests();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.downloading.FragmentDownloadController
    public void onFailure(SmoothStreamingURI smoothStreamingURI, long j, ContentUrl contentUrl, ContentException contentException, int i, @Nonnull DownloadStatistics downloadStatistics) {
        Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
        this.mFragmentsDownloadError++;
        updateBandwidth((int) downloadStatistics.getThroughput());
        updateBufferSize(this.mStreamState.getDurationAfterPositionInNanos(this.mContext.getState().getPlayPositionInNanos()), false);
        this.mContentUrlSelector.notifyDownloadFailure(contentException, contentUrl, i, smoothStreamingURI.getPresentationTimeInNanos(), false, null, downloadStatistics, DownloadType.FRAGMENT);
        DLog.warnf("Download for %s failed with error %s (try number %d)", smoothStreamingURI, contentException, Integer.valueOf(i));
        this.mEventsListener.onDownloadError(contentUrl, smoothStreamingURI, contentException, i, downloadStatistics.getUnformattedHeaders());
        cancelRequestsAfter(smoothStreamingURI);
    }
}
