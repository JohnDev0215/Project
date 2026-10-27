package com.amazon.avod.media.playback.support;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class RendererPerformanceData {
    public static final RendererPerformanceData EMPTY_PERF_DATA = new RendererPerformanceData();
    private static final int UNDEFINED = -1;
    private final double mFramesPerSecond;
    private final int mLastDecodedSampleBitrate;
    private final MutableRendererPerformanceData mPerformanceData;
    private final List<Buffer> mRendererBuffers;
    private final int mTotalDroppedFrames;

    public RendererPerformanceData() {
        this(0.0d, 0, -1, new MutableRendererPerformanceData(), ImmutableList.of());
    }

    public RendererPerformanceData(double d, @Nonnull List<Buffer> list) {
        this(d, 0, -1, new MutableRendererPerformanceData(), list);
    }

    public RendererPerformanceData(double d, int i, int i2, @Nonnull List<Buffer> list) {
        this(d, i, i2, new MutableRendererPerformanceData(), list);
    }

    public RendererPerformanceData(MutableRendererPerformanceData mutableRendererPerformanceData, @Nonnull List<Buffer> list) {
        this(-1.0d, -1, -1, new MutableRendererPerformanceData(mutableRendererPerformanceData), list);
    }

    private RendererPerformanceData(double d, int i, int i2, @Nonnull MutableRendererPerformanceData mutableRendererPerformanceData, @Nonnull List<Buffer> list) {
        this.mFramesPerSecond = d;
        this.mTotalDroppedFrames = i;
        this.mLastDecodedSampleBitrate = i2;
        this.mPerformanceData = (MutableRendererPerformanceData) Preconditions.checkNotNull(mutableRendererPerformanceData, "performanceData");
        this.mRendererBuffers = (List) Preconditions.checkNotNull(list, "rendererBuffers");
    }

    public double getAverageFramesPerSecond() {
        double d = this.mFramesPerSecond;
        if (d >= 0.0d) {
            return d;
        }
        if (this.mPerformanceData.getTotalPlaybackDuration() == 0) {
            return 0.0d;
        }
        return ((double) this.mPerformanceData.getTotalRenderNum()) / (((double) this.mPerformanceData.getTotalPlaybackDuration()) / 1000.0d);
    }

    public int getTotalRenderDropNum() {
        int i = this.mTotalDroppedFrames;
        return i >= 0 ? i : this.mPerformanceData.getTotalRenderDropNum();
    }

    public int getLastDecodedSampleBitrate() {
        return this.mLastDecodedSampleBitrate;
    }

    public int getLastTime() {
        return this.mPerformanceData.getLastTime();
    }

    public int getSourceDropNum() {
        return this.mPerformanceData.getSourceDropNum();
    }

    public int getCodecDropNum() {
        return this.mPerformanceData.getCodecDropNum();
    }

    public int getRenderDropNum() {
        return this.mPerformanceData.getRenderDropNum();
    }

    public int getDecodedNum() {
        return this.mPerformanceData.getDecodedNum();
    }

    public int getRenderNum() {
        return this.mPerformanceData.getRenderNum();
    }

    public int getSourceTimeNum() {
        return this.mPerformanceData.getSourceTimeNum();
    }

    public int getCodecTimeNum() {
        return this.mPerformanceData.getCodecTimeNum();
    }

    public int getRenderTimeNum() {
        return this.mPerformanceData.getRenderTimeNum();
    }

    public int getJitterNum() {
        return this.mPerformanceData.getJitterNum();
    }

    public int getCodecErrorsNum() {
        return this.mPerformanceData.getCodecErrorsNum();
    }

    public int getCpuLoad() {
        return this.mPerformanceData.getCpuLoad();
    }

    public int getFrequency() {
        return this.mPerformanceData.getFrequency();
    }

    public int getMaxFrequency() {
        return this.mPerformanceData.getMaxFrequency();
    }

    public int getWorstDecodeTime() {
        return this.mPerformanceData.getWorstDecodeTime();
    }

    public int getWorstRenderTime() {
        return this.mPerformanceData.getWorstRenderTime();
    }

    public int getAverageDecodeTime() {
        return this.mPerformanceData.getAverageDecodeTime();
    }

    public int getAverageRenderTime() {
        return this.mPerformanceData.getAverageRenderTime();
    }

    public int getTotalCPULoad() {
        return this.mPerformanceData.getTotalCPULoad();
    }

    public int getTotalPlaybackDuration() {
        return this.mPerformanceData.getTotalPlaybackDuration();
    }

    public int getTotalSourceDropNum() {
        return this.mPerformanceData.getTotalSourceDropNum();
    }

    public int getTotalCodecDropNum() {
        return this.mPerformanceData.getTotalCodecDropNum();
    }

    public int getTotalDecodedNum() {
        return this.mPerformanceData.getTotalDecodedNum();
    }

    public int getTotalRenderNum() {
        return this.mPerformanceData.getTotalRenderNum();
    }

    public boolean isLowPerformanceDetected() {
        return this.mPerformanceData.isLowPerformanceDetected();
    }

    @Nonnull
    public List<Buffer> getRendererBuffers() {
        return this.mRendererBuffers;
    }
}
