package com.amazon.avod.playback.player.states;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.drm.DecryptionContextFactory;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlayerHeuristicReporterInterface;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.player.PlaybackActionQueue;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.player.RendererSampleReceiver;
import com.amazon.avod.playback.player.VideoPlaybackTimeline;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
class PlaybackStateDependencies {
    private final PlaybackActionQueue mActionQueue;
    private final DecryptionContextFactory mContextFactory;
    private final RendererSampleReceiver mDataForwarder;
    private final PlaybackEventTransport mEventTransport;

    @Nonnull
    private final AloysiusPlayerHeuristicReporterInterface mHeuristicsReporter;

    @Nonnull
    private final AloysiusInteractionReporterInterface mInteractionReporter;
    private final MediaProfiler mMediaProfiler;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final PlaybackConfig mPlaybackConfig;

    @Nonnull
    private final AloysiusPlaybackReporterInterface mPlaybackReporter;
    private final RendererScheme mRendererScheme;
    private final RendererSchemeController mRendererSchemeController;
    private final VideoPlaybackTimeline mTimeline;
    private final VideoRenderer mVideoRenderer;

    public PlaybackStateDependencies(@Nonnull PlaybackActionQueue playbackActionQueue, VideoRenderer videoRenderer, PlaybackEventTransport playbackEventTransport, RendererSampleReceiver rendererSampleReceiver, VideoPlaybackTimeline videoPlaybackTimeline, MediaProfiler mediaProfiler, NetworkConnectionManager networkConnectionManager, PlaybackConfig playbackConfig, DecryptionContextFactory decryptionContextFactory, RendererScheme rendererScheme, @Nonnull AloysiusPlaybackReporterInterface aloysiusPlaybackReporterInterface, @Nonnull AloysiusInteractionReporterInterface aloysiusInteractionReporterInterface, @Nonnull AloysiusPlayerHeuristicReporterInterface aloysiusPlayerHeuristicReporterInterface, @Nonnull RendererSchemeController rendererSchemeController) {
        this.mActionQueue = (PlaybackActionQueue) Preconditions.checkNotNull(playbackActionQueue, "actionQueue");
        this.mVideoRenderer = videoRenderer;
        this.mEventTransport = playbackEventTransport;
        this.mDataForwarder = rendererSampleReceiver;
        this.mTimeline = videoPlaybackTimeline;
        this.mMediaProfiler = mediaProfiler;
        this.mNetworkConnectionManager = networkConnectionManager;
        this.mPlaybackConfig = playbackConfig;
        this.mContextFactory = decryptionContextFactory;
        this.mRendererScheme = rendererScheme;
        this.mPlaybackReporter = (AloysiusPlaybackReporterInterface) Preconditions.checkNotNull(aloysiusPlaybackReporterInterface, "playbackReporter");
        this.mInteractionReporter = (AloysiusInteractionReporterInterface) Preconditions.checkNotNull(aloysiusInteractionReporterInterface, "interactionReporter");
        this.mHeuristicsReporter = (AloysiusPlayerHeuristicReporterInterface) Preconditions.checkNotNull(aloysiusPlayerHeuristicReporterInterface, "heuristicsReporter");
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
    }

    public PlaybackEventTransport getEventTransport() {
        return this.mEventTransport;
    }

    @Nonnull
    public AloysiusPlaybackReporterInterface getPlaybackReporter() {
        return this.mPlaybackReporter;
    }

    public RendererSampleReceiver getDataForwarder() {
        return this.mDataForwarder;
    }

    @Nonnull
    public PlaybackActionQueue getActionQueue() {
        return this.mActionQueue;
    }

    public VideoRenderer getRenderer() {
        return this.mVideoRenderer;
    }

    public MediaProfiler getProfiler() {
        return this.mMediaProfiler;
    }

    public VideoPlaybackTimeline getTimeline() {
        return this.mTimeline;
    }

    public NetworkConnectionManager getNetworkConnectionManager() {
        return this.mNetworkConnectionManager;
    }

    @Nonnull
    public PlaybackConfig getConfig() {
        return this.mPlaybackConfig;
    }

    public DecryptionContextFactory getDecryptionContextFactory() {
        return this.mContextFactory;
    }

    public RendererScheme getRendererScheme() {
        return this.mRendererScheme;
    }

    @Nonnull
    public AloysiusInteractionReporterInterface getInteractionReporter() {
        return this.mInteractionReporter;
    }

    @Nonnull
    public AloysiusPlayerHeuristicReporterInterface getHeuristicsReporter() {
        return this.mHeuristicsReporter;
    }

    @Nonnull
    public RendererSchemeController getRendererSchemeController() {
        return this.mRendererSchemeController;
    }
}
