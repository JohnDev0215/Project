package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.collect.Range;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QualityLevelClamperFactory {
    private static final int BITS_IN_A_KILO_BIT;
    private static final int KILO_BIT_CEILING;
    private final ContentSessionConfiguration mHeuristicConfig;

    static {
        int bits = (int) DataUnit.KILOBITS.toBits(1.0f);
        BITS_IN_A_KILO_BIT = bits;
        KILO_BIT_CEILING = Integer.MAX_VALUE / bits;
    }

    public QualityLevelClamperFactory(ContentSessionConfiguration contentSessionConfiguration) {
        this.mHeuristicConfig = contentSessionConfiguration;
    }

    public QualityLevelClamper newServerConfigurableQualityLevelClamper(Manifest manifest) {
        return new QualityLevelClamper(manifest, this.mHeuristicConfig.getBitrateRange(), this.mHeuristicConfig.getHorizontalResolutionRange(), this.mHeuristicConfig.getVerticalResolutionRange());
    }

    @Nonnull
    public QualityLevelClamper newServerConfigurableQualityLevelClamper(Manifest manifest, HeuristicsPlaybackConfig heuristicsPlaybackConfig) {
        Range<Integer> bitrateRange;
        Range<Integer> bitrateRange2 = this.mHeuristicConfig.getBitrateRange();
        int iMax = Math.max(heuristicsPlaybackConfig.getMinBitrateKbps(), 0);
        int i = BITS_IN_A_KILO_BIT;
        try {
            bitrateRange = Range.closed(Integer.valueOf(Math.max(((Integer) bitrateRange2.lowerEndpoint()).intValue(), iMax * i)), Integer.valueOf(Math.min(((Integer) bitrateRange2.upperEndpoint()).intValue(), Math.min(heuristicsPlaybackConfig.getMaxBitrateKbps(), KILO_BIT_CEILING) * i)));
        } catch (IllegalArgumentException e) {
            DLog.errorf("%s. Using G2S2 bitrate range values.", e);
            bitrateRange = this.mHeuristicConfig.getBitrateRange();
        }
        return new QualityLevelClamper(manifest, bitrateRange, this.mHeuristicConfig.getHorizontalResolutionRange(), this.mHeuristicConfig.getVerticalResolutionRange());
    }

    @Nonnull
    public QualityLevelClamper newServerConfigurableQualityLevelClamper(Manifest manifest, int i, int i2) {
        Range<Integer> bitrateRange;
        Range<Integer> bitrateRange2 = this.mHeuristicConfig.getBitrateRange();
        int iMax = Math.max(i2, 0);
        int i3 = BITS_IN_A_KILO_BIT;
        try {
            bitrateRange = Range.closed(Integer.valueOf(Math.max(((Integer) bitrateRange2.lowerEndpoint()).intValue(), iMax * i3)), Integer.valueOf(Math.min(((Integer) bitrateRange2.upperEndpoint()).intValue(), Math.min(i, KILO_BIT_CEILING) * i3)));
        } catch (IllegalArgumentException e) {
            DLog.errorf("%s. Using G2S2 bitrate range values.", e);
            bitrateRange = this.mHeuristicConfig.getBitrateRange();
        }
        return new QualityLevelClamper(manifest, bitrateRange, this.mHeuristicConfig.getHorizontalResolutionRange(), this.mHeuristicConfig.getVerticalResolutionRange());
    }

    public QualityLevelClamper newDeviceCapabilityBasedQualityLevelClamper(Manifest manifest, int i) {
        return new QualityLevelClamper(manifest, Range.closed(0, Integer.valueOf(i)), Range.closed(0, Integer.MAX_VALUE), Range.closed(0, Integer.MAX_VALUE));
    }
}
