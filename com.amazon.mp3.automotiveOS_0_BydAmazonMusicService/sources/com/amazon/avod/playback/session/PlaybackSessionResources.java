package com.amazon.avod.playback.session;

import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.LiveWindowDuration;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.AdEventTransport;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.drm.DrmLicenseAcquirer;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.monitoring.TimelineMonitor;
import com.amazon.avod.media.playback.pmet.PmetAcquisitionEventListener;
import com.amazon.avod.media.playback.reporting.PlayerHealthReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.service.AVODContentUrlPolicyManager;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.smoothstream.SmoothStreamingBitrateChangeAdapter;
import com.amazon.avod.playback.smoothstream.SmoothStreamingContentEventAdapter;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticDataCollector;
import com.amazon.avod.playback.smoothstream.fsm.VideoPlaybackEngineFactory;
import com.amazon.avod.playback.subtitles.SubtitlesEngine;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.userdownload.PVDownloadManagerPlayerShim;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackSessionResources {
    @Nonnull
    AdEventTransport getAdEventBus();

    @Nonnull
    AdPlanFactory getAdPlanFactory();

    @Nonnull
    AdUriProxy getAdUriProxy();

    @Nonnull
    SmoothStreamingBitrateChangeAdapter getBitrateChangeAdapter();

    @Nonnull
    VideoCacheManager getCacheManager();

    @Nonnull
    SmoothStreamingContentEventAdapter getContentEventAdapter();

    @Nonnull
    ContentManagementEventBus getContentManagementEventBus();

    @Nonnull
    ContentSessionFactory getContentSessionFactory();

    @Nonnull
    AVODContentUrlPolicyManager getContentUrlPolicyManager();

    @Nonnull
    DiagnosticDataCollector getDataCollector();

    @Nonnull
    PVDownloadManagerPlayerShim getDownloadManager();

    @Nonnull
    DownloadService getDownloadService();

    @Nonnull
    BaseDrmSystem getDrmSystem();

    @Nonnull
    SmoothStreamingVideoPresentationEventReporter getEventReporter();

    @Nonnull
    ExecutorService getExecutor();

    @Nonnull
    IdentityShim getIdentity();

    @Nonnull
    DrmLicenseAcquirer getLicenseAcquirer();

    @Nonnull
    LifecycleProfiler getLifecycleProfiler();

    @Nonnull
    LiveWindowDuration getLiveWindowDuration();

    @Nonnull
    PlaybackMediaEventReporters getMediaEventReporters();

    @Nonnull
    @Deprecated
    PlaybackMediaEventReporters getMediaEventReportersAd();

    @Nonnull
    @Deprecated
    PlaybackMediaEventReporters getMediaEventReportersMain();

    @Nonnull
    PlaybackConfig getPlaybackConfig();

    @Nonnull
    PlaybackEventTransport getPlaybackEventTransport();

    @Nonnull
    PlaybackSessionFailureManager getPlaybackSessionFailureManager();

    @Nonnull
    PlaybackSupportEvaluator getPlaybackSupportEvaluator();

    @Nonnull
    PlayerHealthReporter getPlayerHealthReporter();

    @Nonnull
    PmetAcquisitionEventListener getPmetAcquisitionEventListener();

    @Nonnull
    MediaProfiler getProfiler();

    @Nonnull
    RendererSchemeController getRendererSchemeController();

    @Nonnull
    SubtitlesEngine getSubtitlesEngine();

    @Nonnull
    TimelineMonitor getTimelineMonitor();

    @Nonnull
    VideoPlaybackEngineFactory getVideoPlaybackEngineFactory();

    @Nonnull
    VideoPlayerLifecyleEventHandler getVideoPlayerLifecyleEventHandler();
}
