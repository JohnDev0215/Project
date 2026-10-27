package com.amazon.avod.content;

import android.content.pm.PackageManager;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingDeviceCapabilityConfig;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackPerformanceConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.downloading.ContentAccessorFactory;
import com.amazon.avod.content.downloading.DefaultContentQualityConfiguration;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.performance.NoOpPerformanceMonitor;
import com.amazon.avod.content.performance.SmoothStreamingPerformanceMonitor;
import com.amazon.avod.content.readytowatch.ReadyToWatchFactory;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.content.smoothstream.PlaybackSessionProtocolFactory;
import com.amazon.avod.content.smoothstream.StreamSelector;
import com.amazon.avod.content.smoothstream.manifest.ManifestParser;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerFactory;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.PsshFragmentAcquirer;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.SingleFragmentContentStore;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.WriterScopedContentStore;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrlSelectorFactory;
import com.amazon.avod.content.urlvending.ContentUrlSwitchingPolicyFactory;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.framework.resources.SurgingBufferPool;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ContentSessionFactoryBase implements ContentSessionFactory {
    protected final SmoothStreamingContentStore mContentStore;
    protected final ContentUrlSelectorFactory mContentUrlSelectorFactory;
    protected final DefaultQualityConfiguration mDefaultQualityConfig;
    protected final SmoothStreamingDeviceCapabilityConfig mDeviceCapsConfig;
    protected final DownloadService mDownloadService;
    protected final FileBackedContentStore mFileBackedContentStore;
    protected final FileSystem mFileSystem;
    protected final SmoothStreamingHeuristicConfig mHeuristicsConfig;
    protected final HeuristicsProvider mHeuristicsProvider;
    protected final PlaybackNativeLibrariesLoader mLibraryLoader;
    protected final LiveStreamingPlaybackConfig mLiveStreamingPlaybackConfig;
    protected final ManifestParser mManifestParser;
    protected final MediaProfiler mMediaProfiler;
    protected final Mp4FragmentParser mMp4FragmentParser;
    protected final NetworkHistoryManager mNetworkHistoryManager;
    protected final SmoothStreamingPlaybackConfig mPlaybackConfig;
    protected final RendererSchemeController mRendererSchemeController;
    protected final ReadyToWatchFactory mRtwFactory;
    protected final MediaSystemSharedContext mSharedContext;
    protected final SmoothStreamingPlaybackPerformanceConfig mSmoothStreamingPerfConfig;

    public ContentSessionFactoryBase(NetworkHistoryManager networkHistoryManager, MediaSystemSharedContext mediaSystemSharedContext, DownloadService downloadService, FileSystem fileSystem, MediaProfiler mediaProfiler, SmoothStreamingContentStore smoothStreamingContentStore, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, RendererSchemeController rendererSchemeController, Mp4FragmentParser mp4FragmentParser, ManifestParser manifestParser, HeuristicsProvider heuristicsProvider, FileBackedContentStore fileBackedContentStore) {
        this(networkHistoryManager, mediaSystemSharedContext, downloadService, fileSystem, mediaProfiler, smoothStreamingContentStore, playbackNativeLibrariesLoader, rendererSchemeController, SmoothStreamingPlaybackConfig.INSTANCE, SmoothStreamingPlaybackPerformanceConfig.INSTANCE, SmoothStreamingDeviceCapabilityConfig.INSTANCE, new DefaultContentQualityConfiguration(), new ContentUrlSelectorFactory(), mp4FragmentParser, manifestParser, heuristicsProvider, LiveStreamingPlaybackConfig.getInstance(), fileBackedContentStore);
    }

    ContentSessionFactoryBase(NetworkHistoryManager networkHistoryManager, MediaSystemSharedContext mediaSystemSharedContext, DownloadService downloadService, FileSystem fileSystem, MediaProfiler mediaProfiler, SmoothStreamingContentStore smoothStreamingContentStore, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, RendererSchemeController rendererSchemeController, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, SmoothStreamingPlaybackPerformanceConfig smoothStreamingPlaybackPerformanceConfig, SmoothStreamingDeviceCapabilityConfig smoothStreamingDeviceCapabilityConfig, DefaultContentQualityConfiguration defaultContentQualityConfiguration, ContentUrlSelectorFactory contentUrlSelectorFactory, Mp4FragmentParser mp4FragmentParser, ManifestParser manifestParser, HeuristicsProvider heuristicsProvider, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, FileBackedContentStore fileBackedContentStore) {
        this.mSharedContext = mediaSystemSharedContext;
        this.mDownloadService = downloadService;
        this.mFileSystem = fileSystem;
        this.mMediaProfiler = mediaProfiler;
        this.mContentStore = smoothStreamingContentStore;
        this.mLibraryLoader = playbackNativeLibrariesLoader;
        this.mRendererSchemeController = rendererSchemeController;
        this.mNetworkHistoryManager = networkHistoryManager;
        this.mMp4FragmentParser = mp4FragmentParser;
        this.mManifestParser = manifestParser;
        this.mHeuristicsProvider = heuristicsProvider;
        this.mPlaybackConfig = smoothStreamingPlaybackConfig;
        this.mSmoothStreamingPerfConfig = smoothStreamingPlaybackPerformanceConfig;
        this.mDeviceCapsConfig = smoothStreamingDeviceCapabilityConfig;
        this.mDefaultQualityConfig = defaultContentQualityConfiguration;
        this.mContentUrlSelectorFactory = contentUrlSelectorFactory;
        this.mRtwFactory = new ReadyToWatchFactory(defaultContentQualityConfiguration, smoothStreamingPlaybackConfig);
        this.mHeuristicsConfig = SmoothStreamingHeuristicConfig.getInstance(mediaSystemSharedContext.getDeviceConfiguration());
        this.mFileBackedContentStore = fileBackedContentStore;
        this.mLiveStreamingPlaybackConfig = liveStreamingPlaybackConfig;
    }

    @Override // com.amazon.avod.content.ContentSessionFactory
    public ContentSession newContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull LifecycleProfiler lifecycleProfiler, @Nullable LiveWindowDuration liveWindowDuration, @Nullable String str, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        Heuristics heuristics = this.mHeuristicsProvider.get(videoSpecification.getContentType(), audioVideoUrls.getPlaybackSettingsOverride());
        return newContentSession(videoSpecification, file, contentSessionType, audioVideoUrls, ContentSessionConfiguration.newBuilder().setFromConfig(this.mPlaybackConfig, this.mHeuristicsConfig, heuristics, this.mLiveStreamingPlaybackConfig).build(), drmScheme, rendererSchemeType, lifecycleProfiler, heuristics, liveWindowDuration, str, contentManagementEventBus, playbackEventReporter, manifestCapturerInterface);
    }

    private ContentSession newContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, ContentSessionConfiguration contentSessionConfiguration, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull LifecycleProfiler lifecycleProfiler, @Nullable Heuristics heuristics, @Nullable LiveWindowDuration liveWindowDuration, @Nullable String str, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        WriterScopedContentStore writerScopedContentStore;
        DLog.logf("newContentSession called for:%s", videoSpecification);
        if (videoSpecification.isLiveStream() && this.mPlaybackConfig.deleteBaselineAfterDownloadingUpgradeInLiveStreaming()) {
            writerScopedContentStore = new WriterScopedContentStore(new SingleFragmentContentStore(this.mContentStore));
        } else {
            writerScopedContentStore = new WriterScopedContentStore(this.mContentStore);
        }
        DeviceCapabilityDetector deviceCapabilityDetector = getDeviceCapabilityDetector(rendererSchemeType, drmScheme);
        DeviceCapability deviceCapability = (DeviceCapability) Preconditions.checkNotNull(deviceCapabilityDetector.getDeviceCapability(), "Loading native libraries timed out. Device capabilities object is null.");
        StreamSelector streamSelector = new StreamSelector(this.mFileBackedContentStore, this.mPlaybackConfig, deviceCapabilityDetector);
        WriterScopedContentStore writerScopedContentStore2 = writerScopedContentStore;
        ManifestAcquirerInterface manifestAcquirer = ManifestAcquirerFactory.INSTANCE.getManifestAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore2, this.mManifestParser, manifestCapturerInterface, videoSpecification.isLiveStream());
        PsshFragmentAcquirer psshFragmentAcquirer = new PsshFragmentAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore2, this.mMp4FragmentParser, drmScheme);
        PsshFragmentAcquirer psshFragmentAcquirer2 = new PsshFragmentAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore2, this.mMp4FragmentParser, drmScheme);
        PlaybackSessionProtocolFactory playbackSessionProtocolFactory = new PlaybackSessionProtocolFactory(this.mMp4FragmentParser, this.mPlaybackConfig, this.mLiveStreamingPlaybackConfig, this.mDefaultQualityConfig, contentManagementEventBus, drmScheme, playbackEventReporter, this.mSharedContext.getAppContext());
        SmoothStreamingContentSession smoothStreamingContentSession = new SmoothStreamingContentSession(this.mNetworkHistoryManager, contentManagementEventBus, new ContentAccessorFactory(contentManagementEventBus, this.mDefaultQualityConfig, this.mDownloadService, this.mSharedContext.getExecutorService(), this.mFileSystem, this.mMp4FragmentParser, writerScopedContentStore, this.mNetworkHistoryManager, deviceCapability, contentSessionConfiguration, this.mHeuristicsConfig, new SurgingBufferPool(2), heuristics != null ? heuristics.getHeuristicsPlaybackConfig() : null), manifestAcquirer, psshFragmentAcquirer, psshFragmentAcquirer2, playbackSessionProtocolFactory, this.mRtwFactory, streamSelector, this.mMediaProfiler, writerScopedContentStore, new QualityLevelClamperFactory(contentSessionConfiguration), this.mLibraryLoader, this.mContentUrlSelectorFactory, new ContentUrlSwitchingPolicyFactory(), this.mPlaybackConfig.shouldUseLegacyPerformanceMonitor(videoSpecification.isLiveStream()) ? new SmoothStreamingPerformanceMonitor(this.mSmoothStreamingPerfConfig, this.mDeviceCapsConfig, deviceCapability) : new NoOpPerformanceMonitor(), heuristics, this.mPlaybackConfig, this.mLiveStreamingPlaybackConfig, lifecycleProfiler, liveWindowDuration, createManifestAcquirerExecutor(), playbackEventReporter, manifestCapturerInterface, getAppUid(), createLiveCacheExecutor());
        smoothStreamingContentSession.initialize(videoSpecification, contentSessionType, audioVideoUrls, file, drmScheme, str);
        return smoothStreamingContentSession;
    }

    @Nonnull
    protected ScheduledExecutorService createManifestAcquirerExecutor() {
        return ScheduledExecutorBuilder.newBuilderFor("ManifestAcquirer", new String[0]).withFixedThreadPoolSize(this.mPlaybackConfig.getManifestRefreshThreads()).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
    }

    @Nonnull
    protected ScheduledExecutorService createLiveCacheExecutor() {
        return ScheduledExecutorBuilder.newBuilder("LiveCacheTicker", new String[0]).withProfilerTraceLevel(Profiler.TraceLevel.VERBOSE).withFixedThreadPoolSize(1).build();
    }

    @Override // com.amazon.avod.content.ContentSessionFactory
    public ContentSession newSingleFileContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, LifecycleProfiler lifecycleProfiler, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        throw new UnsupportedOperationException();
    }

    protected int getAppUid() {
        try {
            return this.mSharedContext.getAppContext().getPackageManager().getApplicationInfo(this.mSharedContext.getAppContext().getPackageName(), 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    @Nonnull
    protected DeviceCapabilityDetector getDeviceCapabilityDetector(@Nullable RendererSchemeType rendererSchemeType, @Nullable DrmScheme drmScheme) {
        return this.mRendererSchemeController.getRendererScheme(rendererSchemeType, drmScheme).getCapabilityDetector();
    }
}
