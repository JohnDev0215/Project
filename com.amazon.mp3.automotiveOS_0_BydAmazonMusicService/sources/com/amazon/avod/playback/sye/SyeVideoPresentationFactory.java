package com.amazon.avod.playback.sye;

import android.app.Activity;
import android.content.Context;
import com.amazon.avod.content.urlvending.TimeShiftPolicy;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.diagnostics.internal.DisabledDiagnosticsController;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.UserWatchSessionIdManager;
import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPlayerLifecyleEventHandler;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.reporting.aloysius.SyeAloysiusReporters;
import com.amazon.avod.media.playback.support.MediaCodecDeviceCapabilityDetector;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticsConfig;
import com.amazon.avod.playback.sye.diagnostics.SyeDiagnosticsController;
import com.amazon.avod.playback.sye.listeners.AudioTracksHolder;
import com.amazon.avod.playback.sye.listeners.NotificationHandler;
import com.amazon.avod.playback.sye.listeners.SubtitleTracksHolder;
import com.amazon.avod.playback.sye.listeners.SyeErrorRetryHandler;
import com.amazon.avod.playback.sye.listeners.SyeEventForwarder;
import com.amazon.avod.playback.sye.listeners.SyeTimelineHolder;
import com.amazon.avod.playback.sye.statistics.AloysiusMetricsListener;
import com.amazon.avod.playback.sye.statistics.MetricsNotifier;
import com.amazon.avod.playback.sye.trickplay.NoOpTrickplayManager;
import com.amazon.avod.playback.sye.trickplay.SyeTrickplayConfig;
import com.amazon.avod.playback.sye.trickplay.SyeTrickplayManager;
import com.amazon.avod.playback.sye.trickplay.TrickplayManager;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.CurrentActivityTracker;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SyeConfig;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.SyeAPI;
import com.netinsight.sye.syeClient.SyeAPIConfig;
import com.netinsight.sye.syeClient.SyePlayerConfig;
import com.netinsight.sye.syeClient.SyeSystem;
import com.netinsight.sye.syeClient.closedCaptions.CCType;
import com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener;
import com.netinsight.sye.syeClient.playerListeners.IEgressInfoListener;
import com.netinsight.sye.syeClient.playerListeners.IErrorListener;
import com.netinsight.sye.syeClient.playerListeners.IStateChangeListener;
import com.netinsight.sye.syeClient.playerListeners.IStatusListener;
import com.netinsight.sye.syeClient.playerListeners.ITeardownListener;
import com.netinsight.sye.syeClient.playerListeners.IVideoTrackListener;
import com.netinsight.sye.syeClient.video.SyeVideoLimitations;
import java.io.File;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SyeVideoPresentationFactory implements VideoPresentationFactory {
    private static final TimeShiftPolicy NO_TIME_SHIFT_POLICY = new TimeShiftPolicy(false, false, false, false);
    private static final TimeShiftPolicy SEEK_ENABLED_TIME_SHIFT_POLICY = new TimeShiftPolicy(true, true, true, true);
    private final AdvertisingIdCollector mAdvertisingIdCollector;
    private final SyeAloysiusReporters.Factory mAloysiusReporterFactory;
    private final Context mContext;
    private final DeviceIdentity mDeviceIdentity;
    private final DiagnosticsConfig mDiagnosticsConfig;
    private final EventReporterFactory mQosReporterFactory;
    private final MediaSystemSharedContext mSharedContext;
    private final SyeAPIConfig mSyeAPIConfig;
    private final SyeTrickplayConfig mSyeTrickplayConfig;
    private final CurrentActivityTracker mTracker;
    private final UserWatchSessionIdManager mUserWatchSessionIdManager;

    public SyeVideoPresentationFactory(@Nonnull SyeAloysiusReporters.Factory factory, @Nonnull Context context, @Nonnull EventReporterFactory eventReporterFactory, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull MediaSystemSharedContext mediaSystemSharedContext) {
        this(factory, context, CurrentActivityTracker.getInstance(), DiagnosticsConfig.getInstance(), eventReporterFactory, advertisingIdCollector, mediaSystemSharedContext, new SyeAPIConfig(), MediaSystemSharedDependencies.getInstance().getDeviceIdentity(), SyeTrickplayConfig.getInstance(), UserWatchSessionIdManager.getInstance());
    }

    SyeVideoPresentationFactory(@Nonnull SyeAloysiusReporters.Factory factory, @Nonnull Context context, @Nonnull CurrentActivityTracker currentActivityTracker, @Nonnull DiagnosticsConfig diagnosticsConfig, @Nonnull EventReporterFactory eventReporterFactory, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull MediaSystemSharedContext mediaSystemSharedContext, @Nonnull SyeAPIConfig syeAPIConfig, @Nonnull DeviceIdentity deviceIdentity, @Nonnull SyeTrickplayConfig syeTrickplayConfig, @Nonnull UserWatchSessionIdManager userWatchSessionIdManager) {
        this.mAloysiusReporterFactory = (SyeAloysiusReporters.Factory) Preconditions.checkNotNull(factory, "reporterFactory");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mTracker = (CurrentActivityTracker) Preconditions.checkNotNull(currentActivityTracker, "tracker");
        this.mDiagnosticsConfig = (DiagnosticsConfig) Preconditions.checkNotNull(diagnosticsConfig, "diagnosticsConfig");
        this.mQosReporterFactory = (EventReporterFactory) Preconditions.checkNotNull(eventReporterFactory, "qosReporterFactory");
        this.mAdvertisingIdCollector = (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        this.mSharedContext = (MediaSystemSharedContext) Preconditions.checkNotNull(mediaSystemSharedContext, "sharedContext");
        this.mSyeAPIConfig = (SyeAPIConfig) Preconditions.checkNotNull(syeAPIConfig, "syeAPIConfig");
        this.mDeviceIdentity = (DeviceIdentity) Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        this.mSyeTrickplayConfig = (SyeTrickplayConfig) Preconditions.checkNotNull(syeTrickplayConfig, "syeTrickplayConfig");
        this.mUserWatchSessionIdManager = (UserWatchSessionIdManager) Preconditions.checkNotNull(userWatchSessionIdManager, "userWatchSessionIdManager");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationFactory
    public VideoPresentation newVideoPresentation(@Nonnull VideoSpecification videoSpecification, @Nullable File file, @Nonnull VideoOptions videoOptions) {
        Activity activity;
        TrickplayManager noOpTrickplayManager;
        DiagnosticsController disabledDiagnosticsController;
        int iIntValue;
        if (!videoSpecification.isLiveStream()) {
            return null;
        }
        this.mDeviceIdentity.waitOnInitialized();
        this.mDeviceIdentity.isThirdParty();
        if (videoSpecification.getSyeUrls().isEmpty()) {
            return null;
        }
        Optional<Activity> currentActivity = this.mTracker.getCurrentActivity();
        if (!currentActivity.isPresent()) {
            DLog.warnf("No playback activity found! Cannot create sye player, fallback to normal player");
            return null;
        }
        SyeInitializer.setup(this.mContext.getApplicationContext(), this.mSyeAPIConfig);
        Activity activity2 = currentActivity.get();
        SyeDomainVendingMachine syeDomainVendingMachineFromSpec = SyeDomainVendingMachine.INSTANCE.fromSpec(videoSpecification);
        VideoPlayerLifecyleEventHandler videoPlayerLifecyleEventHandler = new VideoPlayerLifecyleEventHandler();
        PlaybackEventReporter playbackEventReporterNewStandaloneEventReporter = this.mQosReporterFactory.newStandaloneEventReporter(this.mUserWatchSessionIdManager.getOrCreate(videoSpecification.getTitleId()));
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorBuild = ScheduledExecutorBuilder.newBuilderFor(this, "SyeScheduledExecutor").withFixedThreadPoolSize(1).build();
        PlaybackEventTransport playbackEventTransport = new PlaybackEventTransport();
        SyePlayerConfig syePlayerConfig = new SyePlayerConfig();
        SyeConfig syeConfig = SyeConfig.getInstance();
        int preferredDelayMillis = syeConfig.getPreferredDelayMillis();
        if (preferredDelayMillis > 0) {
            syePlayerConfig.setPresentationDelayMillis(preferredDelayMillis);
            DLog.logf("Client enforces presentation delay to be %s millis", Integer.valueOf(preferredDelayMillis));
        } else {
            DLog.logf("Use server presentation delay");
        }
        syePlayerConfig.setContinuePlaybackInBackground(syeConfig.getContinuePlayInBackground());
        syePlayerConfig.setDynamicAudioLatencyCompensation(syeConfig.getDynamicAudioLatencyCompensation());
        syePlayerConfig.setSmoothFrameRendering(syeConfig.getSmoothFrameRendering());
        syePlayerConfig.setRenderLatencyCompensation(syeConfig.getRenderLatencyCompensation());
        syePlayerConfig.setPlayingStallThresholdMillis(syeConfig.getPlayingStallThresholdMillis());
        syePlayerConfig.setStallPlayingThresholdMillis(syeConfig.getStallPlayingThresholdMillis());
        SyeDomainHolder syeDomainHolderCurrentSyeDomain = syeDomainVendingMachineFromSpec.currentSyeDomain();
        SyeSystem syeSystem = new SyeSystem(syeDomainHolderCurrentSyeDomain.getSyeDomain(), syeDomainHolderCurrentSyeDomain.getToken());
        DLog.logf("Initial SyeDomain %s, %s", syeDomainHolderCurrentSyeDomain.getSyeDomain().getBaseUrl(), syeDomainHolderCurrentSyeDomain.getSyeDomain().getName());
        PlaybackListenerProxy playbackListenerProxy = new PlaybackListenerProxy();
        String consumptionId = syeDomainVendingMachineFromSpec.currentSyeDomain().getConsumptionId();
        PlaybackMediaEventReporters playbackMediaEventReportersCreate = this.mAloysiusReporterFactory.create(playbackEventTransport, consumptionId);
        SyeErrorRetryHandler syeErrorRetryHandler = new SyeErrorRetryHandler(scheduledThreadPoolExecutorBuild, playbackListenerProxy, playbackEventTransport, syeDomainVendingMachineFromSpec, playbackMediaEventReportersCreate, syeConfig);
        ISyePlayer iSyePlayerCreatePlayer = SyeAPI.createPlayer(syeSystem, syePlayerConfig, activity2, syeErrorRetryHandler);
        iSyePlayerCreatePlayer.setSelectedCCType(CCType.NTSCC);
        SyeVideoLimitations syeVideoLimitations = new SyeVideoLimitations();
        if (videoSpecification.getSyeResponse() != null && videoSpecification.getSyeResponse().maxBitrateBps.isPresent() && (iIntValue = videoSpecification.getSyeResponse().maxBitrateBps.get().intValue()) > 0) {
            DLog.logf("Setting SyePlayer max bitrate to %d based on vended maxBitrateBps", Integer.valueOf(iIntValue));
            syeVideoLimitations.setMaxBitrate(iIntValue);
        }
        int maxHFRDisplayHeight = syeConfig.getMaxHFRDisplayHeight();
        DLog.logf("Setting SyePlayer max height to %d based on configuration", Integer.valueOf(maxHFRDisplayHeight));
        syeVideoLimitations.setMaxHeight(maxHFRDisplayHeight);
        SyeAPI.setVideoLimitations(syeVideoLimitations);
        syeErrorRetryHandler.setSyePlayer(iSyePlayerCreatePlayer);
        AudioTracksHolder audioTracksHolder = new AudioTracksHolder();
        iSyePlayerCreatePlayer.addListener(audioTracksHolder);
        SubtitleTracksHolder subtitleTracksHolder = new SubtitleTracksHolder();
        iSyePlayerCreatePlayer.addListener(subtitleTracksHolder);
        SyeTimelineHolder syeTimelineHolder = new SyeTimelineHolder(playbackListenerProxy);
        iSyePlayerCreatePlayer.addListener(syeTimelineHolder);
        SyeEventForwarder syeEventForwarder = new SyeEventForwarder(iSyePlayerCreatePlayer, playbackListenerProxy, playbackEventTransport, syeTimelineHolder, syeDomainVendingMachineFromSpec, scheduledThreadPoolExecutorBuild, syeConfig, playbackMediaEventReportersCreate, videoSpecification);
        iSyePlayerCreatePlayer.addListener((IStateChangeListener) syeEventForwarder);
        iSyePlayerCreatePlayer.addListener((IVideoTrackListener) syeEventForwarder);
        iSyePlayerCreatePlayer.addListener((IAudioTrackListener) syeEventForwarder);
        iSyePlayerCreatePlayer.addListener((ITeardownListener) syeEventForwarder);
        iSyePlayerCreatePlayer.addListener((IErrorListener) syeEventForwarder);
        iSyePlayerCreatePlayer.addListener((IEgressInfoListener) syeEventForwarder);
        iSyePlayerCreatePlayer.addListener((IStatusListener) syeEventForwarder);
        NotificationHandler notificationHandler = new NotificationHandler(playbackListenerProxy, videoSpecification.getVCID());
        iSyePlayerCreatePlayer.addListener(notificationHandler);
        QATriggerSyePlayer.getInstance().prepare(syeEventForwarder, syeErrorRetryHandler, notificationHandler);
        AloysiusReporterHolder aloysiusReporterHolder = new AloysiusReporterHolder();
        MediaCodecDeviceCapabilityDetector mediaCodecDeviceCapabilityDetector = new MediaCodecDeviceCapabilityDetector(this.mSharedContext, new SyeNoOpDrmFramework());
        TimeShiftPolicy timeShiftPolicy = syeDomainHolderCurrentSyeDomain.getDvrDurationMillis() != 0 ? SEEK_ENABLED_TIME_SHIFT_POLICY : NO_TIME_SHIFT_POLICY;
        SyeViewHolder syeViewHolder = new SyeViewHolder();
        SyePlaybackExperienceController syePlaybackExperienceController = new SyePlaybackExperienceController(iSyePlayerCreatePlayer, syeDomainVendingMachineFromSpec, aloysiusReporterHolder, mediaCodecDeviceCapabilityDetector, audioTracksHolder, subtitleTracksHolder, syeViewHolder, syeTimelineHolder, timeShiftPolicy, syeConfig);
        SyeAuditPingTracker syeAuditPingTracker = new SyeAuditPingTracker(videoSpecification.getAuditPingUrls(), consumptionId, aloysiusReporterHolder, playbackEventTransport, this.mAdvertisingIdCollector);
        syeAuditPingTracker.start();
        if (this.mSyeTrickplayConfig.isSyeTrickplayEnabled()) {
            activity = activity2;
            noOpTrickplayManager = new SyeTrickplayManager(activity, iSyePlayerCreatePlayer, syeDomainVendingMachineFromSpec);
        } else {
            activity = activity2;
            noOpTrickplayManager = new NoOpTrickplayManager();
        }
        SyeVideoPlayer syeVideoPlayer = new SyeVideoPlayer(playbackListenerProxy, activity, iSyePlayerCreatePlayer, syeTimelineHolder, syeViewHolder, syePlaybackExperienceController, aloysiusReporterHolder, syeDomainVendingMachineFromSpec, syeConfig, syeAuditPingTracker, noOpTrickplayManager, timeShiftPolicy, syeEventForwarder);
        syeVideoPlayer.addListener(videoPlayerLifecyleEventHandler);
        aloysiusReporterHolder.setMediaEventReporters(playbackMediaEventReportersCreate);
        playbackMediaEventReportersCreate.initialize(syeVideoPlayer, syePlaybackExperienceController, ContentType.isLive(videoSpecification.getContentType()));
        playbackMediaEventReportersCreate.getAloysiusDeviceReporter().reportDeviceEventIfRendererChanged(RendererSchemeType.SYE, DrmScheme.NONE);
        MetricsNotifier metricsNotifier = new MetricsNotifier(iSyePlayerCreatePlayer, scheduledThreadPoolExecutorBuild, syeConfig);
        iSyePlayerCreatePlayer.addListener(metricsNotifier);
        metricsNotifier.addListener(new AloysiusMetricsListener(playbackEventTransport, syeDomainVendingMachineFromSpec));
        if (this.mDiagnosticsConfig.isDiagnosticsViewAvailable()) {
            SyeDiagnosticsController syeDiagnosticsController = new SyeDiagnosticsController(this.mContext, syeDomainVendingMachineFromSpec);
            metricsNotifier.addListener(syeDiagnosticsController);
            disabledDiagnosticsController = syeDiagnosticsController;
        } else {
            disabledDiagnosticsController = new DisabledDiagnosticsController();
        }
        SyeVideoPresentation syeVideoPresentation = new SyeVideoPresentation(videoSpecification, videoOptions, syeVideoPlayer, playbackEventReporterNewStandaloneEventReporter, videoPlayerLifecyleEventHandler, disabledDiagnosticsController, scheduledThreadPoolExecutorBuild);
        videoPlayerLifecyleEventHandler.setPresentation(syeVideoPresentation);
        return syeVideoPresentation;
    }
}
