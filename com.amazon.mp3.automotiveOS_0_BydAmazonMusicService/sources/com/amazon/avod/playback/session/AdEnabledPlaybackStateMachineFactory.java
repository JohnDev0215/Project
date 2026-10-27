package com.amazon.avod.playback.session;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdBreakSelector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.ads.internal.adplaybackstatemachine.ClientInsertedAdEnabledPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.adplaybackstatemachine.ServerInsertedAdEnabledPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.diagnostics.DiagnosticsOverlayToggler;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playback.smoothstream.ServerInsertedManifestTimelineManager;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AdEnabledPlaybackStateMachineFactory {
    @Nonnull
    public AdPlaybackStateMachine newDAGBasedAdEnabledStateMachine(@Nonnull AdPlaybackStateMachineContext adPlaybackStateMachineContext, @Nonnull VolumeManager volumeManager, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull PlaybackSession playbackSession, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager, @Nonnull PlaybackExperienceController playbackExperienceController) {
        if (AdsConfig.getInstance().isSSAIEnabled()) {
            playbackExperienceController.setTimelineManager(serverInsertedManifestTimelineManager);
            return new ServerInsertedAdEnabledPlaybackStateMachine((AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector"), (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext, "context"), new AdBreakSelector(), (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager"), AdsConfig.getInstance(), (DiagnosticsOverlayToggler) Preconditions.checkNotNull(diagnosticsOverlayToggler, "diagnosticsToggler"), (PlaybackSession) Preconditions.checkNotNull(playbackSession, "playbackSession"), (AdEnabledVideoPlayer) Preconditions.checkNotNull(adEnabledVideoPlayer, "adEnabledVideoPlayer"), (ServerInsertedManifestTimelineManager) Preconditions.checkNotNull(serverInsertedManifestTimelineManager, "serverInsertedManifestTimelineManager"));
        }
        return new ClientInsertedAdEnabledPlaybackStateMachine((AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector"), (AdPlaybackStateMachineContext) Preconditions.checkNotNull(adPlaybackStateMachineContext, "context"), new AdBreakSelector(), (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager"), AdsConfig.getInstance(), (DiagnosticsOverlayToggler) Preconditions.checkNotNull(diagnosticsOverlayToggler, "diagnosticsToggler"), (PlaybackSession) Preconditions.checkNotNull(playbackSession, "playbackSession"), (AdEnabledVideoPlayer) Preconditions.checkNotNull(adEnabledVideoPlayer, "adEnabledVideoPlayer"));
    }

    @Deprecated
    public AdPlaybackStateMachine newPLSMBasedAdEnabledStateMachine(@Nonnull ExecutorService executorService, @Nonnull AdPlanFactory adPlanFactory, @Nonnull AdUriProxy adUriProxy, @Nonnull VolumeManager volumeManager, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull DiagnosticsOverlayToggler diagnosticsOverlayToggler, @Nonnull VideoPresentation videoPresentation, @Nonnull String str, @Nonnull AdEnabledVideoPlayer adEnabledVideoPlayer, @Nonnull PlaybackSession playbackSession, @Nonnull ServerInsertedManifestTimelineManager serverInsertedManifestTimelineManager, @Nonnull PlaybackExperienceController playbackExperienceController) {
        if (AdsConfig.getInstance().isSSAIEnabled()) {
            playbackExperienceController.setTimelineManager(serverInsertedManifestTimelineManager);
            return new com.amazon.avod.media.ads.internal.state.ServerInsertedAdEnabledPlaybackStateMachine((ExecutorService) Preconditions.checkNotNull(executorService, "executor"), (AdPlanFactory) Preconditions.checkNotNull(adPlanFactory, "planFactory"), (AdUriProxy) Preconditions.checkNotNull(adUriProxy, "uriProxy"), (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager"), (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector"), (DiagnosticsOverlayToggler) Preconditions.checkNotNull(diagnosticsOverlayToggler, "diagnosticsToggler"), (VideoPresentation) Preconditions.checkNotNull(videoPresentation, "primaryVideoPresentation"), (String) Preconditions.checkNotNull(str, "offerType"), (AdEnabledVideoPlayer) Preconditions.checkNotNull(adEnabledVideoPlayer, "adEnabledVideoPlayer"), (PlaybackSession) Preconditions.checkNotNull(playbackSession, "playbackSession"), (ServerInsertedManifestTimelineManager) Preconditions.checkNotNull(serverInsertedManifestTimelineManager, "serverInsertedManifestTimelineManager"));
        }
        return new com.amazon.avod.media.ads.internal.state.ClientInsertedAdEnabledPlaybackStateMachine((ExecutorService) Preconditions.checkNotNull(executorService, "executor"), (AdPlanFactory) Preconditions.checkNotNull(adPlanFactory, "planFactory"), (AdUriProxy) Preconditions.checkNotNull(adUriProxy, "uriProxy"), (VolumeManager) Preconditions.checkNotNull(volumeManager, "volumeManager"), (AdvertisingIdCollector) Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector"), (DiagnosticsOverlayToggler) Preconditions.checkNotNull(diagnosticsOverlayToggler, "diagnosticsToggler"), (VideoPresentation) Preconditions.checkNotNull(videoPresentation, "primaryVideoPresentation"), (String) Preconditions.checkNotNull(str, "offerType"), (AdEnabledVideoPlayer) Preconditions.checkNotNull(adEnabledVideoPlayer, "adEnabledVideoPlayer"), (PlaybackSession) Preconditions.checkNotNull(playbackSession, "playbackSession"));
    }
}
