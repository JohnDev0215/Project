package com.amazon.avod.playback.smoothstream.diagnostics;

import android.os.Handler;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.event.CdnSwitchedEvent;
import com.amazon.avod.content.event.ContentEventFragmentConsumed;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentFpsEvent;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.content.event.ManifestRefreshEvent;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.urlvending.QoeDiagnosticInfo;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.event.EventDispatcher;
import com.amazon.avod.media.framework.platform.Handlers;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.playback.event.AudioQualityChangeEvent;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.playback.event.VideoQualityChangeEvent;
import com.amazon.avod.playback.event.playback.BufferStartEvent;
import com.amazon.avod.playback.event.playback.PlaybackStartEvent;
import com.amazon.avod.playback.event.playback.SeekStartEvent;
import com.amazon.avod.playback.event.playback.StatusEvent;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultDiagnosticDataCollector implements DiagnosticDataCollector {
    private static final long SEEK_MARGIN_IN_NANOSECONDS = TimeUnit.MILLISECONDS.toNanos(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    private boolean isStarted;
    private float mAccumulatedAudioBytes;
    private float mAccumulatedAudioDurationSeconds;
    private float mAccumulatedVideoBytes;
    private float mAccumulatedVideoDurationSeconds;
    private final TreeSet<AggregatedDataPoint> mAggregatedDataPoints;
    private int mAudioQualityCount;
    private final AtomicInteger mBufferingCount;
    private final TreeSet<BufferingDataPoint> mBufferingDataPoints;
    private final TreeSet<CdnSwitchedDataPoint> mCdnSwitchedDataPoints;
    private final MediaQualityConfig mConfig;
    private EventDispatcher mContentEventBus;
    private ContentSessionContext mContext;
    private final TreeSet<DiagnosticDataPoint> mDataPoints;
    private final AtomicInteger mDecoderFrameDropCount;
    private final DeviceResources mDeviceResources;
    private final Set<DiagnosticUpdateListener> mDiagnosticUpdateListeners;
    private String mDrmScheme;
    private final TreeSet<ErrorDataPoint> mErrorDataPoints;
    private double mFramesPerSecond;
    private final Handler mHandler;
    private volatile boolean mIsLiveRestartPending;
    private int mLastAudioBitrate;
    private long mLastDownloadedInNanoseconds;
    private long mLastSubmittedVideoToNativeInNanoseconds;
    private int mLastVideoBitrate;
    private final Object mListenersLock;
    private final TreeSet<ManifestRefreshDataPoint> mManifestRefreshDataPoints;
    private long mMaxTimeStampInNanoseconds;
    private long mMinTimeStampInNanoseconds;
    private String mPeriodId;
    private long mPlayHeadInNanoseconds;
    private final PlaybackEventTransport mPlaybackEventTransport;
    private final ConcurrentLinkedQueue<PlaybackEvent> mPlaybackEvents;
    private final TreeSet<QualityScoreDataPoint> mQualityScoreDataPoints;
    private final AtomicInteger mRendererFrameDropCount;
    private String mRendererScheme;
    private final List<Runnable> mUIListenersReferenceList;
    private final VideoFragmentQualityCounter mVideoFragmentQualityCounter;
    private int mVideoQualityCount;

    DefaultDiagnosticDataCollector(PlaybackEventTransport playbackEventTransport) {
        this(playbackEventTransport, Handlers.getUIHandler(), new CopyOnWriteArrayList(), MediaQualityConfig.INSTANCE, DeviceResources.getInstance());
    }

    DefaultDiagnosticDataCollector(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull Handler handler, @Nonnull List<Runnable> list, @Nonnull MediaQualityConfig mediaQualityConfig, @Nonnull DeviceResources deviceResources) {
        this.mDiagnosticUpdateListeners = new HashSet();
        this.mListenersLock = new Object();
        this.mDataPoints = new TreeSet<>();
        this.mErrorDataPoints = new TreeSet<>();
        this.mBufferingDataPoints = new TreeSet<>();
        this.mCdnSwitchedDataPoints = new TreeSet<>();
        this.mManifestRefreshDataPoints = new TreeSet<>();
        this.mAggregatedDataPoints = new TreeSet<>();
        this.mQualityScoreDataPoints = new TreeSet<>();
        this.mPlaybackEvents = new ConcurrentLinkedQueue<>();
        this.mBufferingCount = new AtomicInteger(0);
        this.mDecoderFrameDropCount = new AtomicInteger(0);
        this.mRendererFrameDropCount = new AtomicInteger(0);
        this.mIsLiveRestartPending = false;
        this.mVideoFragmentQualityCounter = new VideoFragmentQualityCounter();
        this.mContentEventBus = null;
        this.mPlayHeadInNanoseconds = 0L;
        this.mVideoQualityCount = 0;
        this.mAudioQualityCount = 0;
        this.mLastSubmittedVideoToNativeInNanoseconds = 0L;
        this.mLastDownloadedInNanoseconds = 0L;
        this.mMinTimeStampInNanoseconds = 0L;
        this.mMaxTimeStampInNanoseconds = Long.MAX_VALUE;
        this.mFramesPerSecond = 0.0d;
        this.isStarted = false;
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "uiHandler");
        PlaybackEventTransport playbackEventTransport2 = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "playbackEventTransport");
        this.mPlaybackEventTransport = playbackEventTransport2;
        playbackEventTransport2.registerEventBusHandler(this);
        this.mUIListenersReferenceList = (List) Preconditions.checkNotNull(list, "uiListenersReferenceList");
        this.mConfig = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "mediaQualityConfig");
        this.mDeviceResources = (DeviceResources) Preconditions.checkNotNull(deviceResources, "deviceResources");
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void initialize(EventDispatcher eventDispatcher, ContentSessionContext contentSessionContext) {
        this.mContentEventBus = eventDispatcher;
        this.mContext = contentSessionContext;
        eventDispatcher.registerEventBusHandler(this);
        this.mAudioQualityCount = contentSessionContext.getStreamSelections().getAudioStream().getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(contentSessionContext.getStreamSelections().getAudioStream().getIndex())).length;
        this.mVideoQualityCount = contentSessionContext.getStreamSelections().getVideoStream().getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(contentSessionContext.getStreamSelections().getVideoStream().getIndex())).length;
        clearDataPoints();
        this.isStarted = true;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void shutdown() {
        synchronized (this.mListenersLock) {
            this.isStarted = false;
            EventDispatcher eventDispatcher = this.mContentEventBus;
            if (eventDispatcher != null) {
                eventDispatcher.unregisterEventBusHandler(this);
            }
            this.mPlaybackEventTransport.unregisterEventBusHandler(this);
            clearDataPoints();
            Iterator<Runnable> it = this.mUIListenersReferenceList.iterator();
            while (it.hasNext()) {
                this.mHandler.removeCallbacks(it.next());
            }
            this.mUIListenersReferenceList.clear();
            this.mDiagnosticUpdateListeners.clear();
        }
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public ContentSessionContext getContext() {
        return this.mContext;
    }

    @Subscribe
    public void handleSeekEvents(SeekStartEvent seekStartEvent) {
        clearDataPoints();
        this.mPlayHeadInNanoseconds = 0L;
        this.mLastSubmittedVideoToNativeInNanoseconds = 0L;
        long totalNanoSeconds = seekStartEvent.getTargetTime().getTotalNanoSeconds();
        long j = this.mLastDownloadedInNanoseconds;
        this.mMinTimeStampInNanoseconds = Math.abs(totalNanoSeconds - SEEK_MARGIN_IN_NANOSECONDS);
        if (this.mLastDownloadedInNanoseconds < totalNanoSeconds) {
            j = Long.MAX_VALUE;
        }
        this.mMaxTimeStampInNanoseconds = j;
        notifyListener();
    }

    @Subscribe
    public void handleStatusEvent(StatusEvent statusEvent) {
        this.mPlayHeadInNanoseconds = statusEvent.getEventTimeStamp().getTotalNanoSeconds();
        this.mPeriodId = statusEvent.getLastPeriodId();
        this.mLastSubmittedVideoToNativeInNanoseconds = statusEvent.getLastSubmittedVideoToNativeInNanos();
        this.mDecoderFrameDropCount.set(statusEvent.getRendererPerformanceData().getTotalCodecDropNum());
        this.mRendererFrameDropCount.set(statusEvent.getRendererPerformanceData().getTotalRenderDropNum());
        notifyListener();
    }

    @Subscribe
    public void handleFpsStatusEvent(ContentFpsEvent contentFpsEvent) {
        this.mFramesPerSecond = contentFpsEvent.getFramesPerSecond();
        notifyListener();
    }

    @Subscribe
    public void handleFragmentDownloadedEvent(ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        long jAbs;
        long jAbs2;
        Optional optionalAbsent;
        Optional optionalAbsent2;
        synchronized (this.mListenersLock) {
            if (!this.isStarted) {
                DLog.logf("This event handler was/is being terminated. Skipping handleFragmentDownloadedEvent.");
                return;
            }
            FragmentInformationHolder fragmentInformationHolder = contentEventFragmentDownloaded.getFragmentInformationHolder();
            if (fragmentInformationHolder.isInitFragment()) {
                return;
            }
            long presentationTimeInNanos = fragmentInformationHolder.getPresentationTimeInNanos();
            this.mLastDownloadedInNanoseconds = presentationTimeInNanos;
            boolean z = presentationTimeInNanos < this.mMinTimeStampInNanoseconds || presentationTimeInNanos > this.mMaxTimeStampInNanoseconds;
            this.mMinTimeStampInNanoseconds = 0L;
            this.mMaxTimeStampInNanoseconds = Long.MAX_VALUE;
            if (z) {
                DLog.logf("Skipping uri: %s, time: %s nanoseconds", fragmentInformationHolder, Long.valueOf(presentationTimeInNanos));
                return;
            }
            long j = this.mPlayHeadInNanoseconds;
            long j2 = this.mLastSubmittedVideoToNativeInNanoseconds;
            if (j2 != 0) {
                jAbs = Math.abs(j - j2);
                jAbs2 = Math.abs(presentationTimeInNanos - this.mLastSubmittedVideoToNativeInNanoseconds);
            } else {
                jAbs = 0;
                jAbs2 = 0;
            }
            if (fragmentInformationHolder.isVideo()) {
                MediaQuality mediaQuality = this.mContext.getState().getMediaQuality();
                VideoResolution maxResolution = this.mContext.getMaxResolution();
                Optional optionalOf = Optional.of(Integer.valueOf(this.mConfig.getVideoStreamingBitrateCap(mediaQuality, maxResolution, this.mContext.getStreamSelections().getVideoStream().getFourCC())));
                Optional optionalOf2 = Optional.of(String.format(Locale.US, "%s-%s", maxResolution.getResolutionBand().toString(), mediaQuality));
                this.mAccumulatedVideoBytes += fragmentInformationHolder.getDownloadChunkSize();
                this.mAccumulatedVideoDurationSeconds += TimeUnit.NANOSECONDS.toSeconds(fragmentInformationHolder.getDurationInNanos());
                optionalAbsent2 = optionalOf2;
                optionalAbsent = optionalOf;
            } else if (fragmentInformationHolder.isAudio()) {
                StreamIndex audioStream = this.mContext.getStreamSelections().getAudioStream();
                AudioQualityLevel audioQualityLevel = (AudioQualityLevel) audioStream.getQualityLevelLessThanEqual(this.mContext.getState().getConsumptionHead(audioStream.getIndex()), fragmentInformationHolder.getBitrate());
                Optional optionalAbsent3 = Optional.absent();
                Optional optionalOf3 = Optional.of(audioQualityLevel.getAudioFormat().toString());
                this.mAccumulatedAudioBytes += fragmentInformationHolder.getDownloadChunkSize();
                this.mAccumulatedAudioDurationSeconds += TimeUnit.NANOSECONDS.toSeconds(fragmentInformationHolder.getDurationInNanos());
                optionalAbsent2 = optionalOf3;
                optionalAbsent = optionalAbsent3;
            } else {
                optionalAbsent = Optional.absent();
                optionalAbsent2 = Optional.absent();
            }
            addDataPoint(this.mDataPoints, new DiagnosticDataPoint(new TimeSpan(presentationTimeInNanos), fragmentInformationHolder.getBitrate(), optionalAbsent, optionalAbsent2, contentEventFragmentDownloaded.getBandwidthBitsPerSecond(), contentEventFragmentDownloaded.getAveragedBandwidthBitsPerSecond(), jAbs, jAbs2, fragmentInformationHolder.getFragmentType(), fragmentInformationHolder.getStreamType(), contentEventFragmentDownloaded.getTimeToTheFirstByteInNanos()));
            if (fragmentInformationHolder.isVideo()) {
                QoeDiagnosticInfo qoeDiagnosticInfo = this.mContext.getQoeEvaluator().getQoeDiagnosticInfo();
                if (qoeDiagnosticInfo.getIsStarted()) {
                    addDataPoint(this.mAggregatedDataPoints, new AggregatedDataPoint(new TimeSpan(presentationTimeInNanos), qoeDiagnosticInfo.getBandwidthBps(), qoeDiagnosticInfo.getLatencyMillis(), qoeDiagnosticInfo.getQoeValue()));
                }
            }
            if (fragmentInformationHolder.isVideo()) {
                addDataPoint(this.mQualityScoreDataPoints, new QualityScoreDataPoint(new TimeSpan(presentationTimeInNanos), fragmentInformationHolder.getQualityScore(), getAvgVideoBitrateMbps() + getAvgAudioBitrateMbps()));
            }
        }
    }

    private float getAvgVideoBitrateMbps() {
        return (this.mAccumulatedVideoBytes * 8.0f) / ((this.mAccumulatedVideoDurationSeconds * 1024.0f) * 1024.0f);
    }

    private float getAvgAudioBitrateMbps() {
        return (this.mAccumulatedAudioBytes * 8.0f) / ((this.mAccumulatedAudioDurationSeconds * 1024.0f) * 1024.0f);
    }

    @Subscribe
    public void handleFragmentDownloadFailed(RetriableContentEventError retriableContentEventError) {
        SmoothStreamingURI smoothStreamingUri = retriableContentEventError.getSmoothStreamingUri();
        if (smoothStreamingUri == null) {
            return;
        }
        addErrorDataPoint(smoothStreamingUri);
    }

    @Subscribe
    public void handleBufferStartEvent(BufferStartEvent bufferStartEvent) {
        if (this.mIsLiveRestartPending) {
            return;
        }
        addBufferingDataPoint(bufferStartEvent.getEventTimeStamp());
        this.mBufferingCount.addAndGet(1);
    }

    @Subscribe
    public void handleStartPlaybackEvent(PlaybackStartEvent playbackStartEvent) {
        this.mRendererScheme = playbackStartEvent.getRendererScheme();
        this.mDrmScheme = playbackStartEvent.getDrmScheme();
        this.mIsLiveRestartPending = false;
    }

    @Subscribe
    public void handleRestartPlaybackEvent(PlaybackRestartEvent playbackRestartEvent) {
        int type = playbackRestartEvent.getType();
        if (type == 3 || type == 1) {
            this.mIsLiveRestartPending = true;
        }
        this.mPlaybackEvents.add(playbackRestartEvent);
        notifyListener();
    }

    @Subscribe
    public void handleCdnSwitchedEvent(CdnSwitchedEvent cdnSwitchedEvent) {
        addCdnSwitchedDataPoint(cdnSwitchedEvent.getOldContentUrl().getCdnName(), cdnSwitchedEvent.getNewContentUrl().getCdnName(), cdnSwitchedEvent.getEventTimeStamp(), cdnSwitchedEvent.getFailoverRuleId());
        notifyListener();
    }

    @Subscribe
    public void handleVideoQualityChange(VideoQualityChangeEvent videoQualityChangeEvent) {
        this.mLastVideoBitrate = videoQualityChangeEvent.getBitrate();
        notifyListener();
    }

    @Subscribe
    public void handleAudioQualityChange(AudioQualityChangeEvent audioQualityChangeEvent) {
        this.mLastAudioBitrate = audioQualityChangeEvent.getBitrate();
        notifyListener();
    }

    @Subscribe
    public void handleFragmentConsumed(ContentEventFragmentConsumed contentEventFragmentConsumed) {
        SmoothStreamingURI uri = contentEventFragmentConsumed.getURI();
        if (uri.isVideo()) {
            this.mVideoFragmentQualityCounter.addSample(uri.getQualityLevel().getBitrate());
        }
    }

    @Subscribe
    public void handleManifestRefreshEvent(@Nonnull ManifestRefreshEvent manifestRefreshEvent) {
        Preconditions.checkNotNull(manifestRefreshEvent, "manifestRefreshEvent");
        addManifestRefreshDataPoint(manifestRefreshEvent.getEventTimeStamp());
    }

    private void clearDataPoints() {
        synchronized (this.mDataPoints) {
            this.mDataPoints.clear();
        }
        synchronized (this.mErrorDataPoints) {
            this.mErrorDataPoints.clear();
        }
        synchronized (this.mBufferingDataPoints) {
            this.mBufferingDataPoints.clear();
        }
        synchronized (this.mCdnSwitchedDataPoints) {
            this.mCdnSwitchedDataPoints.clear();
        }
        synchronized (this.mAggregatedDataPoints) {
            this.mAggregatedDataPoints.clear();
        }
        this.mPlaybackEvents.clear();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getVideoQualityCount() {
        return this.mVideoQualityCount;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getAudioQualityCount() {
        return this.mAudioQualityCount;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getLastVideoBitrate() {
        return this.mLastVideoBitrate;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getLastAudioBitrate() {
        return this.mLastAudioBitrate;
    }

    private void addErrorDataPoint(SmoothStreamingURI smoothStreamingURI) {
        QualityLevel qualityLevel;
        if (!smoothStreamingURI.isVideo() || smoothStreamingURI.isInitFragment() || (qualityLevel = smoothStreamingURI.getQualityLevel()) == null) {
            return;
        }
        addDataPoint(this.mErrorDataPoints, new ErrorDataPoint(new TimeSpan(smoothStreamingURI.getPresentationTimeInNanos()), qualityLevel.getBitrate()));
    }

    private void addBufferingDataPoint(TimeSpan timeSpan) {
        addDataPoint(this.mBufferingDataPoints, new BufferingDataPoint(timeSpan));
    }

    private void addCdnSwitchedDataPoint(String str, String str2, TimeSpan timeSpan, String str3) {
        addDataPoint(this.mCdnSwitchedDataPoints, new CdnSwitchedDataPoint(timeSpan, str, str2, str3));
    }

    private void addManifestRefreshDataPoint(@Nonnull TimeSpan timeSpan) {
        Preconditions.checkNotNull(timeSpan, "timeSpan");
        addDataPoint(this.mManifestRefreshDataPoints, new ManifestRefreshDataPoint(timeSpan));
    }

    private <T extends DataPoint> void addDataPoint(TreeSet<T> treeSet, T t) {
        synchronized (treeSet) {
            if (treeSet.contains(t)) {
                return;
            }
            treeSet.add(t);
            while (treeSet.size() > 500) {
                treeSet.pollFirst();
            }
            notifyListener();
        }
    }

    private void notifyListener() {
        Runnable runnable = new Runnable() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.DefaultDiagnosticDataCollector.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (DefaultDiagnosticDataCollector.this.mListenersLock) {
                    Iterator it = DefaultDiagnosticDataCollector.this.mDiagnosticUpdateListeners.iterator();
                    while (it.hasNext()) {
                        ((DiagnosticUpdateListener) it.next()).diagnosticDataUpdated(DefaultDiagnosticDataCollector.this);
                    }
                }
            }
        };
        this.mUIListenersReferenceList.add(runnable);
        this.mHandler.post(runnable);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public long getPlayHeadInNanos() {
        return this.mPlayHeadInNanoseconds;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public double getFramesPerSecond() {
        return this.mFramesPerSecond;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getBufferingCount() {
        return this.mBufferingCount.get();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public String getRendererScheme() {
        return this.mRendererScheme;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public String getDrmScheme() {
        return this.mDrmScheme;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public VideoFragmentQualityCounter getVideoFragmentQualityCounter() {
        return this.mVideoFragmentQualityCounter;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<DiagnosticDataPoint> getDiagnosticDataPoints() {
        return this.mDataPoints;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<ErrorDataPoint> getErrorDataPoints() {
        return this.mErrorDataPoints;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<BufferingDataPoint> getBufferingDataPoints() {
        return this.mBufferingDataPoints;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<CdnSwitchedDataPoint> getCdnSwitchedDataPoints() {
        return this.mCdnSwitchedDataPoints;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<AggregatedDataPoint> getAggregatedDataPoints() {
        return this.mAggregatedDataPoints;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public TreeSet<QualityScoreDataPoint> getQualityScoreDataPoints() {
        return this.mQualityScoreDataPoints;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nonnull
    public TreeSet<ManifestRefreshDataPoint> getManifestRefreshDataPoints() {
        Preconditions.checkNotNull(this.mManifestRefreshDataPoints, "mManifestRefreshDataPoints");
        return this.mManifestRefreshDataPoints;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void registerDiagnosticUpdateListener(@Nonnull DiagnosticUpdateListener diagnosticUpdateListener) {
        synchronized (this.mListenersLock) {
            this.mDiagnosticUpdateListeners.add((DiagnosticUpdateListener) Preconditions.checkNotNull(diagnosticUpdateListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        }
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public void unregisterDiagnosticUpdateListener(@Nonnull DiagnosticUpdateListener diagnosticUpdateListener) {
        synchronized (this.mListenersLock) {
            this.mDiagnosticUpdateListeners.remove(Preconditions.checkNotNull(diagnosticUpdateListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        }
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public PlaybackEvent getQueuedPlaybackEvent() {
        return this.mPlaybackEvents.poll();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getTotalDecoderDroppedCount() {
        return this.mDecoderFrameDropCount.get();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public int getTotalRendererDroppedCount() {
        return this.mRendererFrameDropCount.get();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    public double getMaxQualityScore() {
        return this.mDeviceResources.getDesiredQualityScore();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector
    @Nullable
    public String getPeriodId() {
        return this.mPeriodId;
    }
}
