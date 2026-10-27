package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.classification.ClassifierType;
import com.amazon.avod.content.classification.ExponentialSmoothingClassifier;
import com.amazon.avod.content.config.ClusterModelConfig;
import com.amazon.avod.content.config.ClusterModelParserUtils;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualityPair;
import com.amazon.avod.content.dash.quality.downloading.ConcurrentFragmentDownloaderDash;
import com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.BidirectionalAudioVideoSynchronizer;
import com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DashAudioVideoDownloadSynchronizer;
import com.amazon.avod.content.dash.quality.downloading.downloadsynchronizer.DownloadSynchronizer;
import com.amazon.avod.content.dash.quality.heuristic.CompositeHeuristicsCallbacks;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.MidstreamSwitchingController;
import com.amazon.avod.content.dash.quality.heuristic.StreamBehavior;
import com.amazon.avod.content.downloading.ContentViewPersistence;
import com.amazon.avod.content.downloading.DownloadEventsListener;
import com.amazon.avod.content.downloading.DownloadTask;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.BitrateSelectorFactory;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponentSet;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.streamstate.ContentSizeEstimator;
import com.amazon.avod.content.smoothstream.streamstate.StreamStateFactory;
import com.amazon.avod.content.smoothstream.streamstate.StreamStateImpl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadTaskFactory {
    private final BitrateSelectorFactory mBitrateSelectorFactory;
    private final SmoothStreamingPlaybackConfig mConfig;
    private final int mDashAudioVideoDownloadSynchronizationThresholdSeconds;
    private final DownloadTrackerFactory mDownloadTrackerFactory;
    private final ContentManagementEventBus mEventBus;
    private final boolean mIsBidirectionalAudioVideoSynchronizationEnabled;
    private final LiveStreamingPlaybackConfig mLiveConfig;
    private final StreamStateFactory mStreamStateFactory;

    public DownloadTaskFactory(BitrateSelectorFactory bitrateSelectorFactory, DefaultQualityConfiguration defaultQualityConfiguration, DownloadService downloadService, ExecutorService executorService, Mp4FragmentParser mp4FragmentParser, SmoothStreamingContentStore smoothStreamingContentStore, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, SurgingResourcePool<GrowableBuffer> surgingResourcePool, ContentSessionConfiguration contentSessionConfiguration, ContentManagementEventBus contentManagementEventBus) {
        this.mStreamStateFactory = new StreamStateFactory(defaultQualityConfiguration, executorService, smoothStreamingContentStore, smoothStreamingPlaybackConfig, contentSessionConfiguration, downloadService);
        this.mDownloadTrackerFactory = new DownloadTrackerFactory(downloadService, mp4FragmentParser, smoothStreamingPlaybackConfig, smoothStreamingContentStore, surgingResourcePool);
        this.mBitrateSelectorFactory = bitrateSelectorFactory;
        this.mConfig = smoothStreamingPlaybackConfig;
        this.mLiveConfig = liveStreamingPlaybackConfig;
        this.mEventBus = contentManagementEventBus;
        this.mDashAudioVideoDownloadSynchronizationThresholdSeconds = smoothStreamingPlaybackConfig.getDashAudioVideoDownloadSynchronizationThreshold().getTotalSeconds();
        this.mIsBidirectionalAudioVideoSynchronizationEnabled = smoothStreamingPlaybackConfig.isBidirectionalAudioVideoSynchronizationEnabled();
    }

    public CompositeDownloadTask newDownloadTask(@Nonnull ContentSessionContext contentSessionContext, @Nonnull ContentUrlSelector contentUrlSelector, @Nonnull DownloadEventsListener downloadEventsListener, @Nonnull ContentViewPersistence contentViewPersistence) throws ContentException {
        ImmutableList.Builder builder;
        ImmutableMap.Builder builder2;
        CompositeHeuristicsCallbacks compositeHeuristicsCallbacks;
        Preconditions.checkNotNull(contentSessionContext, "context");
        Preconditions.checkNotNull(contentUrlSelector, "contentUrlSelector");
        Preconditions.checkNotNull(downloadEventsListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(contentViewPersistence, "contentViewPersistence");
        StreamIndex videoStream = contentSessionContext.getStreamSelections().getVideoStream();
        if (contentSessionContext.getHeuristics() != null) {
            Heuristics heuristics = contentSessionContext.getHeuristics();
            CompositeHeuristicsCallbacks compositeHeuristicsCallbacks2 = new CompositeHeuristicsCallbacks(heuristics);
            heuristics.initialize(compositeHeuristicsCallbacks2, contentSessionContext);
            DLog.logf("HeuristicAlgorithm %s Version %f", heuristics.getAlgorithm(), Float.valueOf(heuristics.getVersionInfo()));
            DownloadSynchronizer downloadSynchronizer = getDownloadSynchronizer(heuristics);
            StreamingBitrateSelectionComponentSet streamingBitrateSelectionComponentSetNewBitrateSelectionComponentSet = this.mBitrateSelectorFactory.newBitrateSelectionComponentSet(contentSessionContext, videoStream, true);
            MidstreamSwitchingController midstreamSwitchingController = getMidstreamSwitchingController(contentSessionContext, streamingBitrateSelectionComponentSetNewBitrateSelectionComponentSet, heuristics);
            ConcurrentFragmentDownloaderDash concurrentFragmentDownloaderDash = new ConcurrentFragmentDownloaderDash(this.mDownloadTrackerFactory, contentUrlSelector, this.mConfig, streamingBitrateSelectionComponentSetNewBitrateSelectionComponentSet, this.mEventBus, heuristics.getStreamBehavior(), downloadSynchronizer, midstreamSwitchingController);
            CompositeHeuristicsCallbacks compositeHeuristicsCallbacks3 = compositeHeuristicsCallbacks2;
            ImmutableList.Builder builderAdd = new ImmutableList.Builder().add(newDashStreamSpecificDownloadTask(contentSessionContext, videoStream, downloadEventsListener, concurrentFragmentDownloaderDash, heuristics, contentViewPersistence));
            ImmutableMap.Builder builderPut = ImmutableMap.builder().put(Integer.valueOf(videoStream.getIndex()), concurrentFragmentDownloaderDash);
            Iterator<AudioStreamAndQualityPair> it = contentSessionContext.getStreamSelections().getAudioStreamAndQualityPairs().iterator();
            while (it.hasNext()) {
                StreamIndex audioStream = it.next().getAudioStream();
                ConcurrentFragmentDownloaderDash concurrentFragmentDownloaderDash2 = new ConcurrentFragmentDownloaderDash(this.mDownloadTrackerFactory, contentUrlSelector, this.mConfig, this.mBitrateSelectorFactory.newBitrateSelectionComponentSet(contentSessionContext, audioStream, true), this.mEventBus, StreamBehavior.DEFAULT, downloadSynchronizer, midstreamSwitchingController);
                compositeHeuristicsCallbacks3 = compositeHeuristicsCallbacks3;
                ImmutableMap.Builder builder3 = builderPut;
                ImmutableList.Builder builder4 = builderAdd;
                builder4.add(newDashStreamSpecificDownloadTask(contentSessionContext, audioStream, downloadEventsListener, concurrentFragmentDownloaderDash2, heuristics, contentViewPersistence));
                builder3.put(Integer.valueOf(audioStream.getIndex()), concurrentFragmentDownloaderDash2);
                builderAdd = builder4;
                builderPut = builder3;
            }
            ImmutableMap.Builder builder5 = builderPut;
            ImmutableList.Builder builder6 = builderAdd;
            StreamIndex subtitleStream = contentSessionContext.getStreamSelections().getSubtitleStream();
            if (subtitleStream != null) {
                ConcurrentFragmentDownloaderDash concurrentFragmentDownloaderDash3 = new ConcurrentFragmentDownloaderDash(this.mDownloadTrackerFactory, contentUrlSelector, this.mConfig, this.mBitrateSelectorFactory.newBitrateSelectionComponentSet(contentSessionContext, subtitleStream, true), this.mEventBus, StreamBehavior.DEFAULT, downloadSynchronizer);
                compositeHeuristicsCallbacks = compositeHeuristicsCallbacks3;
                builder2 = builder5;
                builder = builder6;
                builder.add(newDashStreamSpecificDownloadTask(contentSessionContext, subtitleStream, downloadEventsListener, concurrentFragmentDownloaderDash3, heuristics, contentViewPersistence));
                builder2.put(Integer.valueOf(subtitleStream.getIndex()), concurrentFragmentDownloaderDash3);
            } else {
                builder = builder6;
                builder2 = builder5;
                compositeHeuristicsCallbacks = compositeHeuristicsCallbacks3;
            }
            compositeHeuristicsCallbacks.setDownloadTaskAdapters(builder.build(), builder2.build());
            return compositeHeuristicsCallbacks;
        }
        ImmutableList<? extends DownloadTask> immutableListOf = ImmutableList.of(newStreamSpecificDownloadTask(contentSessionContext, contentSessionContext.getStreamSelections().getAudioStream(), downloadEventsListener, new SmoothStreamingConcurrentFragmentDownloader(this.mDownloadTrackerFactory, contentUrlSelector, this.mConfig), contentViewPersistence), newStreamSpecificDownloadTask(contentSessionContext, videoStream, downloadEventsListener, new SmoothStreamingConcurrentFragmentDownloader(this.mDownloadTrackerFactory, contentUrlSelector, this.mConfig), contentViewPersistence));
        CompositeDownloadTask compositeDownloadTask = new CompositeDownloadTask();
        compositeDownloadTask.setDownloadTasks(immutableListOf);
        return compositeDownloadTask;
    }

    @Nonnull
    private DownloadSynchronizer getDownloadSynchronizer(@Nonnull Heuristics heuristics) {
        Preconditions.checkNotNull(heuristics, "heuristics");
        HeuristicsPlaybackConfig heuristicsPlaybackConfig = heuristics.getHeuristicsPlaybackConfig();
        int audioVideoDownloadSynchronizationThresholdSeconds = heuristicsPlaybackConfig != null ? heuristicsPlaybackConfig.getAudioVideoDownloadSynchronizationThresholdSeconds() : this.mDashAudioVideoDownloadSynchronizationThresholdSeconds;
        if (heuristicsPlaybackConfig != null ? heuristicsPlaybackConfig.isBidirectionalAudioVideoSynchronizationEnabled() : this.mIsBidirectionalAudioVideoSynchronizationEnabled) {
            return new BidirectionalAudioVideoSynchronizer(audioVideoDownloadSynchronizationThresholdSeconds);
        }
        return new DashAudioVideoDownloadSynchronizer(audioVideoDownloadSynchronizationThresholdSeconds);
    }

    private DownloadTask newStreamSpecificDownloadTask(ContentSessionContext contentSessionContext, StreamIndex streamIndex, DownloadEventsListener downloadEventsListener, ConcurrentFragmentDownloader concurrentFragmentDownloader, ContentViewPersistence contentViewPersistence) {
        StreamStateImpl streamStateImplNewStreamState = this.mStreamStateFactory.newStreamState(contentSessionContext, streamIndex, contentViewPersistence);
        ContentSizeEstimator contentSizeEstimatorNewSizeEstimator = this.mStreamStateFactory.newSizeEstimator(contentSessionContext, streamIndex, streamStateImplNewStreamState);
        concurrentFragmentDownloader.initialize(contentSessionContext, streamIndex, streamStateImplNewStreamState, this.mBitrateSelectorFactory.newBitrateSelector(contentSessionContext, streamIndex), downloadEventsListener, null);
        return new DownloadTaskAdapter(contentSessionContext, streamIndex, contentSizeEstimatorNewSizeEstimator, streamStateImplNewStreamState, concurrentFragmentDownloader);
    }

    private DownloadTaskAdapter newDashStreamSpecificDownloadTask(ContentSessionContext contentSessionContext, StreamIndex streamIndex, DownloadEventsListener downloadEventsListener, ConcurrentFragmentDownloader concurrentFragmentDownloader, Heuristics heuristics, ContentViewPersistence contentViewPersistence) throws ContentException {
        heuristics.setStreamHandle(streamIndex.getIndex(), streamIndex.getStreamHandle());
        StreamStateImpl streamStateImplNewStreamState = this.mStreamStateFactory.newStreamState(contentSessionContext, streamIndex, contentViewPersistence);
        ContentSizeEstimator contentSizeEstimatorNewSizeEstimator = this.mStreamStateFactory.newSizeEstimator(contentSessionContext, streamIndex, streamStateImplNewStreamState);
        concurrentFragmentDownloader.initialize(contentSessionContext, streamIndex, streamStateImplNewStreamState, null, downloadEventsListener, heuristics);
        return new DownloadTaskAdapter(contentSessionContext, streamIndex, contentSizeEstimatorNewSizeEstimator, streamStateImplNewStreamState, concurrentFragmentDownloader);
    }

    private MidstreamSwitchingController getMidstreamSwitchingController(ContentSessionContext contentSessionContext, StreamingBitrateSelectionComponentSet streamingBitrateSelectionComponentSet, Heuristics heuristics) {
        if (heuristics.getHeuristicsPlaybackConfig() != null && heuristics.getHeuristicsPlaybackConfig().isMidstreamSwitchingEnabled()) {
            try {
                ClusterModelParserUtils clusterModelParserUtils = ClusterModelParserUtils.INSTANCE;
                String clusterLabel = clusterModelParserUtils.parseClusterLabel(heuristics.getSettingsId());
                String clusterModelVersion = clusterModelParserUtils.parseClusterModelVersion(clusterLabel);
                ImmutableMap<String, ImmutableList<Double>> immutableMap = ClusterModelConfig.getInstance().getClusterModel().get(clusterModelVersion);
                if (ClassifierType.EXPONENTIAL_SMOOTHING.equals(heuristics.getHeuristicsPlaybackConfig().getClassifierType())) {
                    return new MidstreamSwitchingController(contentSessionContext, streamingBitrateSelectionComponentSet, contentSessionContext.getClamperFactory(), new ExponentialSmoothingClassifier(immutableMap, heuristics.getHeuristicsPlaybackConfig().getWindowSize(), heuristics.getHeuristicsPlaybackConfig().getAlpha(), clusterLabel, clusterModelVersion), clusterLabel);
                }
                DLog.warnf(String.format(Locale.US, "Classifier not supported %s", heuristics.getHeuristicsPlaybackConfig().getClassifierType()));
            } catch (ArrayIndexOutOfBoundsException e) {
                DLog.errorf("Invalid settings string %s Exception %s", heuristics.getSettingsId(), e.getMessage());
            }
        }
        return null;
    }
}
