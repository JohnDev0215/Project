package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.dash.quality.heuristic.CVBRSupportConfig;
import com.amazon.avod.content.dash.quality.heuristic.DownloadConfig;
import com.amazon.avod.content.dash.quality.heuristic.GraphConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.ThroughputBitrateSelectorConfig;
import com.amazon.avod.content.dash.quality.heuristic.shortbuffer.NativeShortBufferHeuristicsConfig;
import com.amazon.avod.content.urlvending.QoeConfigInterface;
import com.amazon.avod.dash.config.bola.BolaConfigInterface;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class HeuristicsConfig {
    private final BolaConfigInterface mBolaConfig;
    private final CVBRSupportConfig mCVBRSupportConfig;
    private final DownloadConfig mDownloadConfig;
    private final GraphConfig mGraphConfig;
    private final HeuristicsPlaybackConfig mHeuristicsPlaybackConfig;
    private final String mHeuristicsSettingsId;
    private final QoeConfigInterface mQoeConfig;
    private final NativeShortBufferHeuristicsConfig mShortBufferHeuristicsConfig;
    private final ThroughputBitrateSelectorConfig mThroughputBitrateSelectorConfig;

    public HeuristicsConfig(@Nonnull GraphConfig graphConfig, @Nonnull DownloadConfig downloadConfig, @Nonnull CVBRSupportConfig cVBRSupportConfig, @Nonnull ThroughputBitrateSelectorConfig throughputBitrateSelectorConfig, @Nullable HeuristicsPlaybackConfig heuristicsPlaybackConfig, @Nonnull NativeShortBufferHeuristicsConfig nativeShortBufferHeuristicsConfig, @Nonnull QoeConfigInterface qoeConfigInterface, @Nonnull BolaConfigInterface bolaConfigInterface, @Nonnull String str) {
        this.mGraphConfig = (GraphConfig) Preconditions.checkNotNull(graphConfig, "graphConfig");
        this.mDownloadConfig = (DownloadConfig) Preconditions.checkNotNull(downloadConfig, "downloadConfig");
        this.mCVBRSupportConfig = (CVBRSupportConfig) Preconditions.checkNotNull(cVBRSupportConfig, "CVBRSupportConfig");
        this.mThroughputBitrateSelectorConfig = (ThroughputBitrateSelectorConfig) Preconditions.checkNotNull(throughputBitrateSelectorConfig, "throughputBitrateSelectorConfig");
        this.mHeuristicsPlaybackConfig = heuristicsPlaybackConfig;
        this.mShortBufferHeuristicsConfig = (NativeShortBufferHeuristicsConfig) Preconditions.checkNotNull(nativeShortBufferHeuristicsConfig, "nativeShortBufferHeuristicsConfig");
        this.mQoeConfig = (QoeConfigInterface) Preconditions.checkNotNull(qoeConfigInterface, "qoeConfig");
        this.mBolaConfig = (BolaConfigInterface) Preconditions.checkNotNull(bolaConfigInterface, "bolaConfig");
        this.mHeuristicsSettingsId = (String) Preconditions.checkNotNull(str, "heuristicsSettingsId");
    }

    @Nonnull
    public GraphConfig getGraphConfig() {
        return this.mGraphConfig;
    }

    @Nonnull
    public DownloadConfig getDownloadConfig() {
        return this.mDownloadConfig;
    }

    @Nonnull
    public CVBRSupportConfig getCVBRSupportConfig() {
        return this.mCVBRSupportConfig;
    }

    @Nonnull
    public ThroughputBitrateSelectorConfig getThroughputBitrateSelectorConfig() {
        return this.mThroughputBitrateSelectorConfig;
    }

    @Nullable
    public HeuristicsPlaybackConfig getHeuristicsPlaybackConfig() {
        return this.mHeuristicsPlaybackConfig;
    }

    @Nonnull
    public NativeShortBufferHeuristicsConfig getShortBufferHeuristicsConfig() {
        return this.mShortBufferHeuristicsConfig;
    }

    @Nonnull
    public QoeConfigInterface getQoeConfig() {
        return this.mQoeConfig;
    }

    @Nonnull
    public BolaConfigInterface getBolaConfig() {
        return this.mBolaConfig;
    }

    @Nonnull
    public String getHeuristicsSettingsId() {
        return this.mHeuristicsSettingsId;
    }
}
