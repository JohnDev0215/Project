package com.amazon.avod.playback.smoothstream.fsm;

import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.drm.DrmDecryptionContextFactory;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDeviceReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporterInterface;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlayerHeuristicReporterInterface;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.AVSyncDebugReporter;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VideoPlaybackEngineFactory {

    @Nonnull
    private final DrmDecryptionContextFactory mDecryptContextFactory;

    @Nonnull
    private final BaseDrmSystem mDrmSystem;

    @Nonnull
    private final PlaybackEventTransport mEventTransport;

    @Nonnull
    private final PlaybackConfig mPlaybackConfig;

    @Nonnull
    private final MediaProfiler mProfiler;

    @Nonnull
    private final RendererSchemeController mRendererSchemeController;

    public VideoPlaybackEngineFactory(@Nonnull MediaProfiler mediaProfiler, @Nonnull BaseDrmSystem baseDrmSystem, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull RendererSchemeController rendererSchemeController) {
        this.mProfiler = (MediaProfiler) Preconditions.checkNotNull(mediaProfiler, "profiler");
        BaseDrmSystem baseDrmSystem2 = (BaseDrmSystem) Preconditions.checkNotNull(baseDrmSystem, "drmSystem");
        this.mDrmSystem = baseDrmSystem2;
        this.mPlaybackConfig = PlaybackConfig.INSTANCE;
        this.mEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventTransport");
        this.mDecryptContextFactory = new DrmDecryptionContextFactory(baseDrmSystem2);
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
    }

    public VideoPlaybackEngine createVideoPlaybackEngine(@Nonnull RendererSchemeType rendererSchemeType, @Nonnull DrmScheme drmScheme, @Nonnull AloysiusPlaybackReporterInterface aloysiusPlaybackReporterInterface, @Nonnull AloysiusInteractionReporterInterface aloysiusInteractionReporterInterface, @Nonnull AloysiusPlayerHeuristicReporterInterface aloysiusPlayerHeuristicReporterInterface, @Nonnull ManifestCapturerInterface manifestCapturerInterface, @Nonnull AloysiusDeviceReporter aloysiusDeviceReporter, @Nonnull PlaybackEventReporter playbackEventReporter) {
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        Preconditions.checkNotNull(drmScheme, "drmScheme");
        Preconditions.checkNotNull(aloysiusDeviceReporter, "aloysiusDeviceReporter");
        RendererScheme rendererScheme = this.mRendererSchemeController.getRendererScheme(rendererSchemeType, drmScheme);
        aloysiusDeviceReporter.reportDeviceEventIfRendererChanged(rendererScheme.getSchemeType(), drmScheme);
        return new VideoPlaybackEngine(this.mEventTransport, this.mDecryptContextFactory, this.mPlaybackConfig, rendererScheme.createRenderer(), this.mProfiler, rendererScheme, aloysiusPlaybackReporterInterface, aloysiusInteractionReporterInterface, aloysiusPlayerHeuristicReporterInterface, manifestCapturerInterface, this.mRendererSchemeController, new AVSyncDebugReporter(playbackEventReporter));
    }
}
