package com.amazon.avod.content;

import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.media.service.playbackoptimizationservice.DefaultHeuristicsConfigurationSynchronizer;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.smoothstream.DashContentSessionFactory;
import com.amazon.avod.smoothstream.DashDownloadContentSessionFactory;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeContentSessionFactory implements ContentSessionFactory {
    private final AVODServiceConfig mAVODServiceConfig;
    private final ContentSessionFactory mDashDownloadContentSessionFactory;
    private final ContentSessionFactory mDashMiyagiContentSessionFactory;
    private final ContentSessionFactory mDashSessionFactory;
    private final DefaultHeuristicsConfigurationSynchronizer mDefaultHeuristicsConfigurationSynchronizer;
    private final ContentSessionFactory mSmoothStreamingDashHeuristicsContentSessionFactory;

    public CompositeContentSessionFactory(DashContentSessionFactory dashContentSessionFactory, DashMiyagiContentSessionFactory dashMiyagiContentSessionFactory, SmoothStreamingDashHeuristicsContentSesionFactory smoothStreamingDashHeuristicsContentSesionFactory, DashDownloadContentSessionFactory dashDownloadContentSessionFactory, DefaultHeuristicsConfigurationSynchronizer defaultHeuristicsConfigurationSynchronizer) {
        this(dashContentSessionFactory, dashMiyagiContentSessionFactory, smoothStreamingDashHeuristicsContentSesionFactory, dashDownloadContentSessionFactory, defaultHeuristicsConfigurationSynchronizer, AVODServiceConfig.getInstance());
    }

    CompositeContentSessionFactory(DashContentSessionFactory dashContentSessionFactory, DashMiyagiContentSessionFactory dashMiyagiContentSessionFactory, SmoothStreamingDashHeuristicsContentSesionFactory smoothStreamingDashHeuristicsContentSesionFactory, DashDownloadContentSessionFactory dashDownloadContentSessionFactory, DefaultHeuristicsConfigurationSynchronizer defaultHeuristicsConfigurationSynchronizer, AVODServiceConfig aVODServiceConfig) {
        this.mDashSessionFactory = dashContentSessionFactory;
        this.mDashMiyagiContentSessionFactory = dashMiyagiContentSessionFactory;
        this.mDashDownloadContentSessionFactory = dashDownloadContentSessionFactory;
        this.mDefaultHeuristicsConfigurationSynchronizer = defaultHeuristicsConfigurationSynchronizer;
        this.mSmoothStreamingDashHeuristicsContentSessionFactory = smoothStreamingDashHeuristicsContentSesionFactory;
        this.mAVODServiceConfig = aVODServiceConfig;
    }

    @Override // com.amazon.avod.content.ContentSessionFactory
    public ContentSession newContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull LifecycleProfiler lifecycleProfiler, @Nullable LiveWindowDuration liveWindowDuration, @Nullable String str, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        return getContentSessionFactory(audioVideoUrls, contentSessionType).newContentSession(videoSpecification, file, contentSessionType, audioVideoUrls, drmScheme, rendererSchemeType, lifecycleProfiler, liveWindowDuration, str, contentManagementEventBus, playbackEventReporter, manifestCapturerInterface);
    }

    @Override // com.amazon.avod.content.ContentSessionFactory
    public ContentSession newSingleFileContentSession(VideoSpecification videoSpecification, File file, ContentSessionType contentSessionType, AudioVideoUrls audioVideoUrls, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull LifecycleProfiler lifecycleProfiler, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ManifestCapturerInterface manifestCapturerInterface) {
        return getContentSessionFactory(audioVideoUrls, contentSessionType).newSingleFileContentSession(videoSpecification, file, contentSessionType, audioVideoUrls, drmScheme, rendererSchemeType, lifecycleProfiler, playbackEventReporter, manifestCapturerInterface);
    }

    private ContentSessionFactory getContentSessionFactory(@Nonnull AudioVideoUrls audioVideoUrls, @Nonnull ContentSessionType contentSessionType) {
        if (ContentUrl.isDashUrl(audioVideoUrls.getContentUrls().get(0))) {
            if (contentSessionType == ContentSessionType.DOWNLOAD) {
                return this.mDashDownloadContentSessionFactory;
            }
            if (contentSessionType == ContentSessionType.PROGRESSIVE_PLAYBACK) {
                return this.mDashSessionFactory;
            }
            if (this.mDefaultHeuristicsConfigurationSynchronizer.isInitialized() && this.mAVODServiceConfig.isMiyagiEnabled()) {
                return this.mDashMiyagiContentSessionFactory;
            }
            return this.mDashSessionFactory;
        }
        return this.mSmoothStreamingDashHeuristicsContentSessionFactory;
    }
}
