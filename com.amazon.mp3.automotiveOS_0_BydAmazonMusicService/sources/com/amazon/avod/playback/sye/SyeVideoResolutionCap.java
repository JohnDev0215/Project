package com.amazon.avod.playback.sye;

import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.util.SyeConfig;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyeVideoResolutionCap.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0006\u0010\u0006\u001a\u00020\u0004R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\fR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/avod/playback/sye/SyeVideoResolutionCap;", "", "Lcom/amazon/avod/media/MediaQuality;", "mediaQuality", "", "mapFrom", "getMaxResolutionHeight", "Lcom/amazon/avod/util/SyeConfig;", "config", "Lcom/amazon/avod/util/SyeConfig;", "Lcom/amazon/avod/media/VideoResolution$ResolutionBand;", "highFrameRateResolutionCap", "Lcom/amazon/avod/media/VideoResolution$ResolutionBand;", "mDisplayResolution", "mPreferredMediaQuality", "Lcom/amazon/avod/media/MediaQuality;", "<init>", "(Lcom/amazon/avod/util/SyeConfig;)V", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0})
public class SyeVideoResolutionCap {
    private final SyeConfig config;
    private final VideoResolution.ResolutionBand highFrameRateResolutionCap;
    private VideoResolution.ResolutionBand mDisplayResolution;
    private MediaQuality mPreferredMediaQuality;

    /* JADX INFO: compiled from: SyeVideoResolutionCap.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaQuality.values().length];
            iArr[MediaQuality.LOWEST.ordinal()] = 1;
            iArr[MediaQuality.LOW.ordinal()] = 2;
            iArr[MediaQuality.MEDIUM.ordinal()] = 3;
            iArr[MediaQuality.HIGH.ordinal()] = 4;
            iArr[MediaQuality.HIGHEST.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SyeVideoResolutionCap(SyeConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        VideoResolution.ResolutionBand resolutionBand = new VideoResolution(config.getMaxHFRDisplayWidth(), config.getMaxHFRDisplayHeight()).getResolutionBand();
        Intrinsics.checkNotNullExpressionValue(resolutionBand, "VideoResolution(config.m…layHeight).resolutionBand");
        this.highFrameRateResolutionCap = resolutionBand;
    }

    public final int getMaxResolutionHeight() {
        int minHeight = this.highFrameRateResolutionCap.getMinHeight();
        VideoResolution.ResolutionBand resolutionBand = this.mDisplayResolution;
        return Math.min(Math.min(minHeight, resolutionBand == null ? Integer.MAX_VALUE : resolutionBand.getMinHeight()), mapFrom(this.mPreferredMediaQuality));
    }

    private final int mapFrom(MediaQuality mediaQuality) {
        if (mediaQuality == null) {
            return Integer.MAX_VALUE;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[mediaQuality.ordinal()];
        if (i == 1) {
            return this.config.getMaxResolutionHeightMediaQualityLowest();
        }
        if (i == 2) {
            return this.config.getMaxResolutionHeightMediaQualityLow();
        }
        if (i == 3) {
            return this.config.getMaxResolutionHeightMediaQualityMedium();
        }
        if (i == 4 || i == 5) {
            return Integer.MAX_VALUE;
        }
        throw new NoWhenBranchMatchedException();
    }
}
