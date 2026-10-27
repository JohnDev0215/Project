package com.amazon.avod.smoothstream;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.ContentSessionFactoryBase;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.SmoothStreamingContentSession;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.downloading.ContentAccessorFactory;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.performance.NoOpPerformanceMonitor;
import com.amazon.avod.content.performance.SmoothStreamingPerformanceMonitor;
import com.amazon.avod.content.smoothstream.PlaybackSessionProtocolFactory;
import com.amazon.avod.content.smoothstream.StreamSelector;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerFactory;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.smoothstream.manifest.acquisition.PsshFragmentAcquirer;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamperFactory;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.LegacyDownloadCompatibleContentStore;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.WriterScopedContentStore;
import com.amazon.avod.content.smoothstream.storage.singlefile.SingleFileContentStore;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrlSwitchingPolicyFactory;
import com.amazon.avod.dash.DashHeuristicsProvider;
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
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.smoothstream.dash.CVBRSupportedFragmentParser;
import com.amazon.avod.smoothstream.dash.DashManifestParser;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.Collections;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DashContentSessionFactory extends ContentSessionFactoryBase {
    private final SingleFileContentStore mSingleFileContentStore;

    public DashContentSessionFactory(NetworkHistoryManager networkHistoryManager, MediaSystemSharedContext mediaSystemSharedContext, DownloadService downloadService, FileSystem fileSystem, MediaProfiler mediaProfiler, SmoothStreamingContentStore smoothStreamingContentStore, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, RendererSchemeController rendererSchemeController, FileBackedContentStore fileBackedContentStore, SingleFileContentStore singleFileContentStore) {
        super(networkHistoryManager, mediaSystemSharedContext, downloadService, fileSystem, mediaProfiler, smoothStreamingContentStore, playbackNativeLibrariesLoader, rendererSchemeController, new CVBRSupportedFragmentParser(playbackNativeLibrariesLoader, SmoothStreamingPlaybackConfig.INSTANCE), new DashManifestParser(playbackNativeLibrariesLoader), new DashHeuristicsProvider(playbackNativeLibrariesLoader), fileBackedContentStore);
        this.mSingleFileContentStore = singleFileContentStore;
    }

    @Override // com.amazon.avod.content.ContentSessionFactoryBase, com.amazon.avod.content.ContentSessionFactory
    public ContentSession newSingleFileContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, LifecycleProfiler lifecycleProfiler, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        DeviceCapabilityDetector deviceCapabilityDetector = getDeviceCapabilityDetector(rendererSchemeType, drmScheme);
        DeviceCapability deviceCapability = (DeviceCapability) Preconditions.checkNotNull(deviceCapabilityDetector.getDeviceCapability(), "Loading native libraries timed out. Device capabilities object is null.");
        DLog.logf("newContentSession called for:%s", videoSpecification);
        StreamSelector streamSelector = new StreamSelector(this.mFileBackedContentStore, this.mPlaybackConfig, deviceCapabilityDetector);
        Heuristics heuristics = this.mHeuristicsProvider.get(videoSpecification.getContentType(), Collections.emptyMap());
        ContentSessionConfiguration contentSessionConfigurationBuild = ContentSessionConfiguration.newBuilder().setFromConfig(this.mPlaybackConfig, this.mHeuristicsConfig, heuristics, this.mLiveStreamingPlaybackConfig).build();
        ContentManagementEventBus contentManagementEventBus = new ContentManagementEventBus();
        WriterScopedContentStore writerScopedContentStore = new WriterScopedContentStore(new LegacyDownloadCompatibleContentStore(this.mSingleFileContentStore));
        ManifestAcquirerInterface manifestAcquirer = ManifestAcquirerFactory.INSTANCE.getManifestAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore, this.mManifestParser, manifestCapturerInterface, videoSpecification.isLiveStream());
        PsshFragmentAcquirer psshFragmentAcquirer = new PsshFragmentAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore, this.mMp4FragmentParser, drmScheme);
        PsshFragmentAcquirer psshFragmentAcquirer2 = new PsshFragmentAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore, this.mMp4FragmentParser, drmScheme);
        PlaybackSessionProtocolFactory playbackSessionProtocolFactory = new PlaybackSessionProtocolFactory(this.mMp4FragmentParser, this.mPlaybackConfig, this.mLiveStreamingPlaybackConfig, this.mDefaultQualityConfig, contentManagementEventBus, drmScheme, playbackEventReporter, this.mSharedContext.getAppContext());
        SmoothStreamingContentSession smoothStreamingContentSession = new SmoothStreamingContentSession(this.mNetworkHistoryManager, contentManagementEventBus, new ContentAccessorFactory(contentManagementEventBus, this.mDefaultQualityConfig, this.mDownloadService, this.mSharedContext.getExecutorService(), this.mFileSystem, this.mMp4FragmentParser, writerScopedContentStore, this.mNetworkHistoryManager, deviceCapability, contentSessionConfigurationBuild, this.mHeuristicsConfig, new SurgingBufferPool(2), heuristics != null ? heuristics.getHeuristicsPlaybackConfig() : null), manifestAcquirer, psshFragmentAcquirer, psshFragmentAcquirer2, playbackSessionProtocolFactory, this.mRtwFactory, streamSelector, this.mMediaProfiler, writerScopedContentStore, new QualityLevelClamperFactory(contentSessionConfigurationBuild), this.mLibraryLoader, this.mContentUrlSelectorFactory, new ContentUrlSwitchingPolicyFactory(), this.mPlaybackConfig.shouldUseLegacyPerformanceMonitor(videoSpecification.isLiveStream()) ? new SmoothStreamingPerformanceMonitor(this.mSmoothStreamingPerfConfig, this.mDeviceCapsConfig, deviceCapability) : new NoOpPerformanceMonitor(), heuristics, this.mPlaybackConfig, this.mLiveStreamingPlaybackConfig, lifecycleProfiler, null, createManifestAcquirerExecutor(), playbackEventReporter, manifestCapturerInterface, getAppUid(), createLiveCacheExecutor());
        smoothStreamingContentSession.initialize(videoSpecification, ContentSessionType.PROGRESSIVE_PLAYBACK, audioVideoUrls, file, drmScheme, null);
        return smoothStreamingContentSession;
    }
}
