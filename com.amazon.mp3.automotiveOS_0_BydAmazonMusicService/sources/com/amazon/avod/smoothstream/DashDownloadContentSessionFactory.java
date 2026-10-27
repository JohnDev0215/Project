package com.amazon.avod.smoothstream;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.ContentSessionFactoryBase;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.SmoothStreamingContentSessionEventTranslator;
import com.amazon.avod.content.backgrounddownload.BackgroundDownloadContentSession;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.StreamSelector;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestAcquirerFactory;
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
import com.amazon.avod.dash.DashBackgroundDownloadHeuristicsProvider;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.smoothstream.dash.CVBRSupportedFragmentParser;
import com.amazon.avod.smoothstream.dash.DashManifestParser;
import com.amazon.avod.util.DLog;
import java.io.File;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DashDownloadContentSessionFactory extends ContentSessionFactoryBase {
    private final SingleFileContentStore mSingleFileContentStore;

    public DashDownloadContentSessionFactory(NetworkHistoryManager networkHistoryManager, MediaSystemSharedContext mediaSystemSharedContext, DownloadService downloadService, FileSystem fileSystem, MediaProfiler mediaProfiler, SmoothStreamingContentStore smoothStreamingContentStore, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, RendererSchemeController rendererSchemeController, FileBackedContentStore fileBackedContentStore, SingleFileContentStore singleFileContentStore) {
        super(networkHistoryManager, mediaSystemSharedContext, downloadService, fileSystem, mediaProfiler, smoothStreamingContentStore, playbackNativeLibrariesLoader, rendererSchemeController, new CVBRSupportedFragmentParser(playbackNativeLibrariesLoader, SmoothStreamingPlaybackConfig.INSTANCE), new DashManifestParser(playbackNativeLibrariesLoader), new DashBackgroundDownloadHeuristicsProvider(playbackNativeLibrariesLoader), fileBackedContentStore);
        this.mSingleFileContentStore = singleFileContentStore;
    }

    @Override // com.amazon.avod.content.ContentSessionFactoryBase, com.amazon.avod.content.ContentSessionFactory
    public ContentSession newSingleFileContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull LifecycleProfiler lifecycleProfiler, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        DeviceCapabilityDetector deviceCapabilityDetector = getDeviceCapabilityDetector(rendererSchemeType, drmScheme);
        ContentSessionConfiguration contentSessionConfigurationBuild = ContentSessionConfiguration.newBuilder().setFromConfig(this.mPlaybackConfig, this.mHeuristicsConfig, null, this.mLiveStreamingPlaybackConfig).build();
        DLog.logf("newContentSession called for:%s", videoSpecification);
        StreamSelector streamSelector = new StreamSelector(this.mFileBackedContentStore, this.mPlaybackConfig, deviceCapabilityDetector);
        ContentManagementEventBus contentManagementEventBus = new ContentManagementEventBus();
        WriterScopedContentStore writerScopedContentStore = new WriterScopedContentStore(new LegacyDownloadCompatibleContentStore(this.mSingleFileContentStore));
        BackgroundDownloadContentSession backgroundDownloadContentSession = new BackgroundDownloadContentSession(this.mNetworkHistoryManager, contentManagementEventBus, ManifestAcquirerFactory.INSTANCE.getManifestAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore, this.mManifestParser, manifestCapturerInterface, videoSpecification.isLiveStream()), new PsshFragmentAcquirer(this.mDownloadService, contentManagementEventBus, this.mMediaProfiler, writerScopedContentStore, this.mMp4FragmentParser, drmScheme), streamSelector, writerScopedContentStore, new QualityLevelClamperFactory(contentSessionConfigurationBuild), this.mLibraryLoader, this.mContentUrlSelectorFactory, new ContentUrlSwitchingPolicyFactory(), this.mPlaybackConfig, lifecycleProfiler, this.mDownloadService, MediaQualityConfig.INSTANCE, new SmoothStreamingContentSessionEventTranslator(contentManagementEventBus), getAppUid(), playbackEventReporter, manifestCapturerInterface);
        backgroundDownloadContentSession.initialize(videoSpecification, audioVideoUrls, file, drmScheme);
        return backgroundDownloadContentSession;
    }
}
