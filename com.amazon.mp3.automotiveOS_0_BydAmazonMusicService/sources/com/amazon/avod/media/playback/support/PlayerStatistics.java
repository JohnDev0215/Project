package com.amazon.avod.media.playback.support;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayerStatistics {
    public static final PlayerStatistics EMPTY_PLAYER_STATS = new PlayerStatistics(RendererPerformanceData.EMPTY_PERF_DATA, ImmutableList.of(), ImmutableList.of());
    private final List<Buffer> mBackBuffer;
    private final List<Buffer> mFrontBuffers;
    private final RendererPerformanceData mRendererPerformanceData;

    public PlayerStatistics(@Nonnull RendererPerformanceData rendererPerformanceData, @Nonnull List<Buffer> list, @Nonnull List<Buffer> list2) {
        this.mRendererPerformanceData = (RendererPerformanceData) Preconditions.checkNotNull(rendererPerformanceData, "rendererPerformanceData");
        this.mFrontBuffers = (List) Preconditions.checkNotNull(list, "frontBuffers");
        this.mBackBuffer = (List) Preconditions.checkNotNull(list2, "backBuffers");
    }

    @Nonnull
    public RendererPerformanceData getRendererPerformanceData() {
        return this.mRendererPerformanceData;
    }

    @Nonnull
    public List<Buffer> getFrontBuffers() {
        return this.mFrontBuffers;
    }

    @Nonnull
    public List<Buffer> getBackBuffer() {
        return this.mBackBuffer;
    }
}
