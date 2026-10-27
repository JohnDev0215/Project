package com.amazon.avod.dash;

import com.amazon.avod.content.HeuristicsProvider;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.shortbuffer.G2S2NativeShortBufferHeuristicsConfig;
import com.amazon.avod.content.urlvending.QoeConfig;
import com.amazon.avod.dash.config.bola.BolaConfig;
import com.amazon.avod.dash.config.downloadtogo.BackgroundDownloadConfig;
import com.amazon.avod.dash.config.g2s2.G2S2CVBRSupportConfig;
import com.amazon.avod.dash.config.g2s2.G2S2GraphConfigLive;
import com.amazon.avod.dash.config.g2s2.G2S2ThroughputBitrateSelectorConfig;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfig;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.playback.ContentType;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DashBackgroundDownloadHeuristicsProvider implements HeuristicsProvider {
    private final PlaybackNativeLibrariesLoader mPlaybackNativeLibrariesLoader;

    public DashBackgroundDownloadHeuristicsProvider(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this.mPlaybackNativeLibrariesLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader, "playbackNativeLibrariesLoader");
    }

    @Override // com.amazon.avod.content.HeuristicsProvider
    public Heuristics get(ContentType contentType, Map<String, String> map) {
        return new DashHeuristicsJni(this.mPlaybackNativeLibrariesLoader, new HeuristicsConfig(G2S2GraphConfigLive.getInstance(), BackgroundDownloadConfig.getInstanceForBackgroundDownload(), G2S2CVBRSupportConfig.getInstance(), G2S2ThroughputBitrateSelectorConfig.getInstance(), null, G2S2NativeShortBufferHeuristicsConfig.getInstance(), QoeConfig.INSTANCE, BolaConfig.INSTANCE, "HeuristicsSettingsIdG2S2"));
    }
}
