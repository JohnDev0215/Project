package com.amazon.avod.playback.session;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.LiveWindowDuration;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.config.PlaybackZoomConfig;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.service.AVODContentUrlPolicyManager;
import com.amazon.avod.playback.AutoEvalPlayerPerformanceEvaluator;
import com.amazon.avod.playback.HighFrameRatePlayerPerformanceEvaluator;
import com.amazon.avod.playback.config.DeviceRebootErrorConfig;
import com.amazon.avod.playback.config.PlayerRestartConfig;
import com.amazon.avod.playback.smoothstream.SmoothStreamingVideoPlayer;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsControllerProvider;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonVideoPlayerFactory {
    private final DiagnosticsControllerProvider mDiagControllerFactory;
    private final PowerManagementLockFactory mLockFactory;
    private final MediaSystemSharedContext mSharedContext;

    AmazonVideoPlayerFactory(@Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull PowerManagementLockFactory powerManagementLockFactory) {
        this(new DiagnosticsControllerProvider(mediaSystemSharedContext.getDeviceConfiguration()), powerManagementLockFactory, mediaSystemSharedContext);
    }

    @VisibleForTesting
    AmazonVideoPlayerFactory(@Nonnull DiagnosticsControllerProvider diagnosticsControllerProvider, @Nonnull PowerManagementLockFactory powerManagementLockFactory, @Nonnull MediaSystemSharedContext mediaSystemSharedContext) {
        this.mDiagControllerFactory = (DiagnosticsControllerProvider) Preconditions.checkNotNull(diagnosticsControllerProvider, "diagnosticsControllerProvider");
        this.mLockFactory = (PowerManagementLockFactory) Preconditions.checkNotNull(powerManagementLockFactory, "powerManagementLockFactory");
        this.mSharedContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "sharedContext");
    }

    public AmazonVideoPlayer newAmazonVideoPlayer(@Nonnull PlaybackSession playbackSession, @Nonnull VideoSpecification videoSpecification) {
        PlaybackMediaEventReporters mediaEventReportersMain;
        Preconditions.checkNotNull(playbackSession, "playbackSession");
        PlaybackSessionResources resources = playbackSession.getResources();
        AVODContentUrlPolicyManager contentUrlPolicyManager = resources.getContentUrlPolicyManager();
        BaseDrmSystem drmSystem = resources.getDrmSystem();
        PlaybackEventTransport playbackEventTransport = resources.getPlaybackEventTransport();
        PlaybackListenerProxy playbackListenerProxy = new PlaybackListenerProxy();
        DiagnosticsController diagnosticsControllerNewController = this.mDiagControllerFactory.newController(resources.getDataCollector());
        PowerManagementLockFactory powerManagementLockFactory = this.mLockFactory;
        SmoothStreamingVideoPresentationEventReporter eventReporter = resources.getEventReporter();
        TimelineUtils timelineUtils = new TimelineUtils();
        DeviceRebootErrorConfig deviceRebootErrorConfig = DeviceRebootErrorConfig.getInstance();
        LiveWindowDuration liveWindowDuration = resources.getLiveWindowDuration();
        PlaybackZoomConfig playbackZoomConfig = PlaybackZoomConfig.getInstance();
        PlayerRestartConfig playerRestartConfig = PlayerRestartConfig.getInstance();
        DeviceConfiguration deviceConfiguration = this.mSharedContext.getDeviceConfiguration();
        if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
            mediaEventReportersMain = playbackSession.getResources().getMediaEventReporters();
        } else {
            mediaEventReportersMain = playbackSession.getResources().getMediaEventReportersMain();
        }
        return new AmazonVideoPlayer(videoSpecification, contentUrlPolicyManager, drmSystem, playbackEventTransport, playbackListenerProxy, diagnosticsControllerNewController, playbackSession, powerManagementLockFactory, eventReporter, timelineUtils, deviceRebootErrorConfig, liveWindowDuration, playbackZoomConfig, playerRestartConfig, deviceConfiguration, mediaEventReportersMain, new HighFrameRatePlayerPerformanceEvaluator(resources.getPlaybackEventTransport()), new AutoEvalPlayerPerformanceEvaluator(resources.getPlaybackEventTransport()), PlaybackPmetMetricReporter.getInstance(), playbackSession.getResources().getPlayerHealthReporter());
    }

    @Deprecated
    public SmoothStreamingVideoPlayer newSmoothStreamingVideoPlayer(@Nonnull PlaybackSession playbackSession, VideoSpecification videoSpecification) {
        PlaybackMediaEventReporters mediaEventReportersMain;
        Preconditions.checkNotNull(playbackSession, "playbackSession");
        PlaybackListenerProxy playbackListenerProxy = new PlaybackListenerProxy();
        com.amazon.avod.playback.smoothstream.TimelineUtils timelineUtils = new com.amazon.avod.playback.smoothstream.TimelineUtils();
        PlaybackSessionResources resources = playbackSession.getResources();
        DiagnosticsController diagnosticsControllerNewController = this.mDiagControllerFactory.newController(resources.getDataCollector());
        AVODContentUrlPolicyManager contentUrlPolicyManager = resources.getContentUrlPolicyManager();
        BaseDrmSystem drmSystem = resources.getDrmSystem();
        PlaybackEventTransport playbackEventTransport = resources.getPlaybackEventTransport();
        PowerManagementLockFactory powerManagementLockFactory = this.mLockFactory;
        SmoothStreamingVideoPresentationEventReporter eventReporter = resources.getEventReporter();
        DeviceRebootErrorConfig deviceRebootErrorConfig = DeviceRebootErrorConfig.getInstance();
        LiveWindowDuration liveWindowDuration = resources.getLiveWindowDuration();
        PlaybackZoomConfig playbackZoomConfig = PlaybackZoomConfig.getInstance();
        DeviceConfiguration deviceConfiguration = this.mSharedContext.getDeviceConfiguration();
        PlayerRestartConfig playerRestartConfig = PlayerRestartConfig.getInstance();
        if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
            mediaEventReportersMain = playbackSession.getResources().getMediaEventReporters();
        } else {
            mediaEventReportersMain = playbackSession.getResources().getMediaEventReportersMain();
        }
        return new SmoothStreamingVideoPlayer(videoSpecification, contentUrlPolicyManager, drmSystem, playbackEventTransport, playbackListenerProxy, diagnosticsControllerNewController, playbackSession, powerManagementLockFactory, eventReporter, timelineUtils, deviceRebootErrorConfig, liveWindowDuration, playbackZoomConfig, deviceConfiguration, playerRestartConfig, mediaEventReportersMain, new HighFrameRatePlayerPerformanceEvaluator(resources.getPlaybackEventTransport()), new AutoEvalPlayerPerformanceEvaluator(resources.getPlaybackEventTransport()), PlaybackPmetMetricReporter.getInstance(), playbackSession.getResources().getPlayerHealthReporter());
    }
}
