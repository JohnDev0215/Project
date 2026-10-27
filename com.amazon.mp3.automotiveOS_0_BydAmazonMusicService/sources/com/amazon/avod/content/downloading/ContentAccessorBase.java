package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicQosMetrics;
import com.amazon.avod.content.event.ContentEventBase;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentEventFragmentRequested;
import com.amazon.avod.content.event.ContentEventStartDownloading;
import com.amazon.avod.content.event.ContentEventStopDownloading;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.smoothstream.FragmentStreamRequestResult;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.downloading.CompositeDownloadTask;
import com.amazon.avod.content.smoothstream.downloading.DownloadTaskFactory;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.streamstate.CachedContentView;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.util.UrlUtils;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContentAccessorBase implements ContentAccessor, DownloadEventsListener, ContentViewPersistence {
    private static final long DEFAULT_MAX_BYTES = DataUnit.MEGABYTES.toBytes(100000.0f);
    private final ContentUrlSelector mContentUrlSelector;
    protected Map<StreamType, CachedContentView> mContentViewMap;
    protected final DownloadAvailabilityController mDownloadAvailabilityController;
    protected final DownloadService mDownloadService;
    protected CompositeDownloadTask mDownloadTask;
    private final DownloadTaskFactory mDownloadTaskFactory;
    private final ContentManagementEventBus mEventDispatcher;
    protected Map<StreamType, Set<SmoothStreamingURI>> mInitFragmentMap;
    private final AtomicBoolean mIsStarted;
    private final int mReportingFrequencyForDiagnostics;
    protected ContentSessionContext mSessionContext;
    private final Object mStartStopMutex;

    @Override // com.amazon.avod.content.downloading.DownloadEventsListener
    public void onBufferFilled() {
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void restrictToQuality(@Nonnull QualityLevel qualityLevel, @Nonnull long j) {
    }

    protected ContentAccessorBase(@Nonnull DownloadAvailabilityController downloadAvailabilityController, ContentManagementEventBus contentManagementEventBus, DownloadTaskFactory downloadTaskFactory, DownloadService downloadService, @Nonnull ContentUrlSelector contentUrlSelector) {
        this(downloadAvailabilityController, contentManagementEventBus, downloadTaskFactory, downloadService, contentUrlSelector, SmoothStreamingPlaybackConfig.INSTANCE.getReportingCadenceForFragmentDiagnostics());
    }

    protected ContentAccessorBase(@Nonnull DownloadAvailabilityController downloadAvailabilityController, ContentManagementEventBus contentManagementEventBus, DownloadTaskFactory downloadTaskFactory, DownloadService downloadService, @Nonnull ContentUrlSelector contentUrlSelector, int i) {
        this.mIsStarted = new AtomicBoolean(false);
        this.mStartStopMutex = new Object();
        this.mDownloadAvailabilityController = (DownloadAvailabilityController) Preconditions.checkNotNull(downloadAvailabilityController, "downloadAvailabilityController");
        this.mEventDispatcher = contentManagementEventBus;
        this.mDownloadTaskFactory = downloadTaskFactory;
        this.mDownloadService = downloadService;
        this.mContentUrlSelector = (ContentUrlSelector) Preconditions.checkNotNull(contentUrlSelector, "contentUrlSelector");
        this.mContentViewMap = Maps.newConcurrentMap();
        this.mInitFragmentMap = Maps.newConcurrentMap();
        this.mReportingFrequencyForDiagnostics = i;
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public final void stop(boolean z) {
        synchronized (this.mStartStopMutex) {
            if (this.mIsStarted.compareAndSet(true, false)) {
                this.mDownloadTask.cancel();
                if (this.mSessionContext.getEffectiveSessionType() == ContentSessionType.STREAMING) {
                    if (z) {
                        this.mDownloadService.cancelAuxiliaryDownloadRequests();
                    }
                    this.mDownloadAvailabilityController.onPrimaryDownloadStateChange(false);
                    this.mDownloadService.enableSecondaryDownloads(true, false);
                }
                postEvent(new ContentEventStopDownloading(this.mSessionContext.getContent(), this.mSessionContext.getSessionType()));
            }
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void seek(long j) {
        synchronized (this.mStartStopMutex) {
            CompositeDownloadTask compositeDownloadTask = this.mDownloadTask;
            if (compositeDownloadTask != null) {
                compositeDownloadTask.seek(j);
            }
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void dispose() {
        synchronized (this.mStartStopMutex) {
            CompositeDownloadTask compositeDownloadTask = this.mDownloadTask;
            if (compositeDownloadTask != null) {
                compositeDownloadTask.dispose();
            }
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void enableStream(StreamType streamType) {
        this.mDownloadTask.enableTask(streamType);
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void disableStream(StreamType streamType) {
        this.mDownloadTask.disableTask(streamType);
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void start(ContentSessionContext contentSessionContext) throws ContentException {
        this.mSessionContext = contentSessionContext;
        if (contentSessionContext.getEffectiveSessionType() == ContentSessionType.STREAMING) {
            this.mDownloadAvailabilityController.onPrimaryDownloadStateChange(true);
            this.mDownloadService.enableSecondaryDownloads(false, false);
        }
        if (contentSessionContext.getSpecification().isLiveStream()) {
            this.mDownloadService.setMaxPrimaryContentBufferInNanos(TimeUnit.SECONDS.toNanos(contentSessionContext.getState().getLiveStreamingFutureBufferSeconds()));
        }
        CompositeDownloadTask compositeDownloadTaskNewDownloadTask = this.mDownloadTaskFactory.newDownloadTask(this.mSessionContext, this.mContentUrlSelector, this, this);
        this.mDownloadTask = compositeDownloadTaskNewDownloadTask;
        compositeDownloadTaskNewDownloadTask.start();
        this.mIsStarted.set(true);
        postEvent(new ContentEventStartDownloading(this.mSessionContext.getContent(), this.mSessionContext.getSessionType(), getTotalNeededSizeInBytes()));
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void onContentContextChanged() {
        if (this.mIsStarted.get()) {
            this.mDownloadTask.onContentContextChanged();
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    @Nullable
    public final FragmentStreamRequestResult getFragmentStream(SmoothStreamingURI smoothStreamingURI) throws ContentException {
        if (this.mIsStarted.get()) {
            return this.mDownloadTask.requestFragment(smoothStreamingURI);
        }
        return null;
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void notifyFragmentStreamCorrupt(SmoothStreamingURI smoothStreamingURI) {
        if (this.mIsStarted.get()) {
            this.mDownloadTask.notifyFragmentCorrupt(smoothStreamingURI);
        }
    }

    @Override // com.amazon.avod.content.downloading.DownloadEventsListener
    public void onDownloadError(@Nullable ContentUrl contentUrl, @Nullable SmoothStreamingURI smoothStreamingURI, ContentException contentException, int i, @Nullable Map<String, List<String>> map) {
        String url = contentUrl == null ? null : contentUrl.getUrl();
        if ((contentException.getErrorCode() == ContentException.ContentError.INVALID_CONTENT_LENGTH && (!this.mSessionContext.getConfig().isHttpsFallbackAllowed() || UrlUtils.isHttpsUrl(url))) || contentException.getErrorCode() == ContentException.ContentError.DISK_FULL) {
            postEvent(new FatalContentEventError(this.mSessionContext.getContent(), this.mSessionContext.getSessionType(), contentException, contentUrl, smoothStreamingURI, map, getConsumptionId(), this.mSessionContext.getEffectiveSessionType()));
        } else {
            postEvent(new RetriableContentEventError(this.mSessionContext.getContent(), this.mSessionContext.getSessionType(), contentException, contentUrl, smoothStreamingURI, i, map));
        }
    }

    @Override // com.amazon.avod.content.downloading.DownloadEventsListener
    public void onFragmentDownloadRequested(SmoothStreamingURI smoothStreamingURI) {
        synchronized (this.mStartStopMutex) {
            if (!this.mIsStarted.get()) {
                DLog.logf("Not propagating events since we are not started anymore.");
            } else {
                postEvent(new ContentEventFragmentRequested(this.mSessionContext.getContent(), this.mSessionContext.getSessionType(), FragmentInformationHolder.extractFragmentData(smoothStreamingURI, null, null)));
            }
        }
    }

    @Override // com.amazon.avod.content.downloading.DownloadEventsListener
    public void onFragmentDownloadFinished(ContentUrl contentUrl, SmoothStreamingURI smoothStreamingURI, DownloadStatistics downloadStatistics, int i) {
        synchronized (this.mStartStopMutex) {
            if (!this.mIsStarted.get()) {
                DLog.logf("Not propagating events since we are not started anymore.");
                return;
            }
            FragmentInformationHolder fragmentInformationHolderExtractFragmentData = FragmentInformationHolder.extractFragmentData(smoothStreamingURI, contentUrl, downloadStatistics);
            postEvent(new ContentEventFragmentDownloaded(this.mSessionContext.getContent(), this.mSessionContext.getSessionType(), fragmentInformationHolderExtractFragmentData, downloadStatistics, this.mSessionContext.getHeuristics(), i, this.mSessionContext.getSpecification().getContentType()));
            if (fragmentInformationHolderExtractFragmentData.isVideo() && fragmentInformationHolderExtractFragmentData.getChunkIndex() % this.mReportingFrequencyForDiagnostics == 0) {
                postDiagEventToAloysius();
            }
        }
    }

    @Override // com.amazon.avod.content.downloading.DownloadEventsListener
    public void onBufferSizeChange(long j, StreamIndex streamIndex, boolean z) {
        ContentSessionType sessionType = this.mSessionContext.getSessionType();
        if (ContentSessionType.isStreamingSession(sessionType) || sessionType == ContentSessionType.PROGRESSIVE_PLAYBACK) {
            long downloadedTimeAfterPositionInNanos = getDownloadedTimeAfterPositionInNanos(this.mSessionContext.getState().getPlayPositionInNanos());
            this.mDownloadService.updatePrimaryContentBufferInNanos(downloadedTimeAfterPositionInNanos);
            this.mDownloadAvailabilityController.onBufferSizeChange(this.mDownloadService, downloadedTimeAfterPositionInNanos, z);
        }
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getDownloadedTimeAfterPositionInNanos(long j) {
        if (this.mIsStarted.get()) {
            return this.mDownloadTask.getDownloadedTimeAfterPositionInNanos(j);
        }
        return 0L;
    }

    @Override // com.amazon.avod.content.LocalContentState
    public boolean hasUpdatesForContentView() {
        return this.mIsStarted.get() && this.mDownloadTask.hasUpdatesForContentView();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public boolean hasDownloadErrors() {
        return this.mIsStarted.get() && this.mDownloadTask.hasDownloadErrors();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public boolean isFullyDownloadedFromNanos(long j) {
        return this.mIsStarted.get() && this.mDownloadTask.isFullyDownloadedFromNanos(j);
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getRemainingNeededSizeInBytes() {
        return this.mIsStarted.get() ? this.mDownloadTask.getRemainingNeededSizeInBytes() : DEFAULT_MAX_BYTES;
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getTotalNeededSizeInBytes() {
        return this.mIsStarted.get() ? this.mDownloadTask.getTotalNeededSizeInBytes() : DEFAULT_MAX_BYTES;
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getLastAvailableTimeInNanos() {
        return getLastAvailableTimeInNanos(null);
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public long getFirstAvailableTimeInNanos(@Nullable StreamType streamType) {
        if (this.mIsStarted.get()) {
            return this.mDownloadTask.getFirstAvailableTimeInNanos(streamType);
        }
        return 0L;
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public long getLastAvailableTimeInNanos(@Nullable StreamType streamType) {
        if (this.mIsStarted.get()) {
            return this.mDownloadTask.getLastAvailableTimeInNanos(streamType);
        }
        return 0L;
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public int getAverageVideoBandwidthBps() {
        if (this.mIsStarted.get()) {
            return this.mDownloadTask.getAverageVideoBandwidthBps();
        }
        return 0;
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void releaseFragment(@Nonnull FragmentStreamRequestResult fragmentStreamRequestResult) {
        if (this.mIsStarted.get()) {
            this.mDownloadTask.releaseFragment(fragmentStreamRequestResult);
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentViewPersistence
    public void setContentView(@Nonnull StreamType streamType, @Nonnull CachedContentView cachedContentView) {
        Preconditions.checkNotNull(streamType, "streamType");
        Preconditions.checkNotNull(cachedContentView, "cachedContentView");
        this.mContentViewMap.put(streamType, cachedContentView);
    }

    @Override // com.amazon.avod.content.downloading.ContentViewPersistence
    @Nullable
    public CachedContentView getContentView(@Nonnull StreamType streamType) {
        Preconditions.checkNotNull(streamType, "streamType");
        return this.mContentViewMap.get(streamType);
    }

    @Override // com.amazon.avod.content.downloading.ContentViewPersistence
    public void updateInitFragmentMap(@Nonnull StreamType streamType, @Nonnull Set<SmoothStreamingURI> set) {
        Preconditions.checkNotNull(streamType, "streamType");
        Preconditions.checkNotNull(set, "initFragments");
        this.mInitFragmentMap.put(streamType, set);
    }

    @Override // com.amazon.avod.content.downloading.ContentViewPersistence
    @Nullable
    public Set<SmoothStreamingURI> getInitFragments(@Nonnull StreamType streamType) {
        Preconditions.checkNotNull(streamType, "streamType");
        return this.mInitFragmentMap.get(streamType);
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    @Nonnull
    public ContentUrlSelector getContentUrlSelector() {
        return this.mContentUrlSelector;
    }

    protected void postEvent(ContentEventBase contentEventBase) {
        this.mEventDispatcher.postEvent(contentEventBase);
    }

    private void postDiagEventToAloysius() {
        HeuristicQosMetrics heuristicQosMetrics = new HeuristicQosMetrics();
        heuristicQosMetrics.initialize(this.mSessionContext.getHeuristics());
        String jsonString = heuristicQosMetrics.toJsonString();
        if (jsonString != null) {
            this.mEventDispatcher.postEvent(new AloysiusDiagnosticEvent("HeuristicsState", jsonString, AloysiusDiagnosticsState.Discrete));
        }
    }

    @Override // com.amazon.avod.content.downloading.ContentAccessor
    public void notifyLivePointUpdated(long j) {
        synchronized (this.mStartStopMutex) {
            CompositeDownloadTask compositeDownloadTask = this.mDownloadTask;
            if (compositeDownloadTask != null) {
                compositeDownloadTask.notifyLivePointUpdated(j);
            }
        }
    }

    @Nullable
    protected String getConsumptionId() {
        ContentUrlSelector contentUrlSelector = this.mContentUrlSelector;
        if (contentUrlSelector != null) {
            return contentUrlSelector.getCurrentContentUrl().getSessionId();
        }
        return null;
    }
}
