package com.amazon.avod.dash;

import com.amazon.avod.content.HeuristicsProvider;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicAlgorithm;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.shortbuffer.ShortBufferHeuristics;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfig;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfigStore;
import com.amazon.avod.dash.config.miyagi.SharedPreferencesHeuristicsConfigStore;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.throughput.BandwidthStats;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DashMiyagiHeuristicsProvider implements HeuristicsProvider {
    private final PlaybackNativeLibrariesLoader mPlaybackNativeLibrariesLoader;
    private final HeuristicsConfigStore mHeuristicsConfigStore = SharedPreferencesHeuristicsConfigStore.getInstance();
    private final DeviceResources mDeviceResources = DeviceResources.getInstance();

    public DashMiyagiHeuristicsProvider(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this.mPlaybackNativeLibrariesLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader, "playbackNativeLibrariesLoader");
    }

    @Override // com.amazon.avod.content.HeuristicsProvider
    public Heuristics get(@Nonnull ContentType contentType, @Nullable Map<String, String> map) {
        HeuristicsConfig defaultHeuristicsConfig;
        this.mDeviceResources.setContentType(contentType);
        if (map != null) {
            defaultHeuristicsConfig = this.mHeuristicsConfigStore.createMergedHeuristicsConfig(map);
        } else {
            defaultHeuristicsConfig = this.mHeuristicsConfigStore.getDefaultHeuristicsConfig();
        }
        HeuristicsPlaybackConfig heuristicsPlaybackConfig = defaultHeuristicsConfig.getHeuristicsPlaybackConfig();
        this.mDeviceResources.setDesiredQualityScore(heuristicsPlaybackConfig != null ? heuristicsPlaybackConfig.getDesiredMediaQualityScore() : 100.0d);
        this.mDeviceResources.setDelayBehindLiveHeadInSeconds(heuristicsPlaybackConfig != null ? heuristicsPlaybackConfig.getDelayBehindLiveHeadSeconds() : 0);
        String heuristicAlgorithm = heuristicsPlaybackConfig != null ? heuristicsPlaybackConfig.getHeuristicAlgorithm(ContentType.isLive(contentType)) : null;
        if (BandwidthStats.isSupported()) {
            HeuristicAlgorithm heuristicAlgorithmLookupHeuristicAlgorithm = HeuristicAlgorithm.INSTANCE.lookupHeuristicAlgorithm(heuristicAlgorithm, ContentType.isLive(contentType));
            HeuristicAlgorithm heuristicAlgorithm2 = HeuristicAlgorithm.LEGACY_SBH;
            if (heuristicAlgorithmLookupHeuristicAlgorithm == heuristicAlgorithm2) {
                DLog.logf("HeuristicAlgorithm - selecting Java SBH");
                this.mDeviceResources.setHeuristicAlgorithm(heuristicAlgorithm2);
                this.mDeviceResources.setTimeoutStrategy(defaultHeuristicsConfig.getShortBufferHeuristicsConfig().getTimeoutCalculationStrategy());
                this.mDeviceResources.setReviewProgressStrategy(defaultHeuristicsConfig.getShortBufferHeuristicsConfig().getReviewDownloadProgressStrategy());
                return new ShortBufferHeuristics(defaultHeuristicsConfig.getDownloadConfig(), defaultHeuristicsConfig.getHeuristicsPlaybackConfig(), defaultHeuristicsConfig.getQoeConfig(), defaultHeuristicsConfig.getHeuristicsSettingsId());
            }
        }
        return new DashHeuristicsJni(this.mPlaybackNativeLibrariesLoader, defaultHeuristicsConfig);
    }
}
