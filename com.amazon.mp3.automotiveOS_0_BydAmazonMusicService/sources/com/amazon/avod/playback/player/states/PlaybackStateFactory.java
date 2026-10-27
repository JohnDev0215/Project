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
import com.amazon.avod.playback.AVSyncDebugReporter;
import com.amazon.avod.playback.player.PlaybackActionQueue;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.player.RendererSampleReceiver;
import com.amazon.avod.playback.player.VideoPlaybackTimeline;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackStateFactory {
    private final PlaybackStateContext mStateContext;
    private final ImmutableMap<PlaybackState, PlaybackEngineState> mStates;

    public PlaybackStateFactory(PlaybackActionQueue playbackActionQueue, VideoRenderer videoRenderer, PlaybackEventTransport playbackEventTransport, VideoPlaybackTimeline videoPlaybackTimeline, MediaProfiler mediaProfiler, NetworkConnectionManager networkConnectionManager, DecryptionContextFactory decryptionContextFactory, PlaybackConfig playbackConfig, RendererScheme rendererScheme, AloysiusPlaybackReporterInterface aloysiusPlaybackReporterInterface, @Nonnull AloysiusInteractionReporterInterface aloysiusInteractionReporterInterface, @Nonnull AloysiusPlayerHeuristicReporterInterface aloysiusPlayerHeuristicReporterInterface, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull AVSyncDebugReporter aVSyncDebugReporter) {
        this(new PlaybackStateDependencies(playbackActionQueue, videoRenderer, playbackEventTransport, new RendererSampleReceiver(videoRenderer, playbackConfig, aVSyncDebugReporter), videoPlaybackTimeline, mediaProfiler, networkConnectionManager, playbackConfig, decryptionContextFactory, rendererScheme, aloysiusPlaybackReporterInterface, aloysiusInteractionReporterInterface, aloysiusPlayerHeuristicReporterInterface, rendererSchemeController), new PlaybackStateContext());
    }

    PlaybackStateFactory(@Nonnull PlaybackStateDependencies playbackStateDependencies, @Nonnull PlaybackStateContext playbackStateContext) {
        this.mStateContext = (PlaybackStateContext) Preconditions.checkNotNull(playbackStateContext, "context");
        this.mStates = ImmutableMap.builder().put(PlaybackState.Uninitialized, new UninitializedState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Initializing, new InitializingState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Loading, new LoadingState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Launching, new LaunchingState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Paused, new PauseState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Playing, new PlayingState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Buffering, new BufferingState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Seeking, new SeekingState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Shutdown, new ShutdownState(playbackStateDependencies, playbackStateContext)).put(PlaybackState.Error, new ErrorState(playbackStateDependencies, playbackStateContext)).build();
    }

    public PlaybackEngineState getState(PlaybackState playbackState) {
        PlaybackEngineState playbackEngineState = this.mStates.get(playbackState);
        Preconditions.checkArgument(playbackEngineState != null, "Playback state: ", playbackState.name(), " missing from the PlaybackStateFactory.");
        return playbackEngineState;
    }

    @Nonnull
    public PlaybackStateContext getStateContext() {
        return this.mStateContext;
    }
}
