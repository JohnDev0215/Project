package com.amazon.avod.media;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class VideoResolution {
    private static final Double DELTA = Double.valueOf(0.01d);
    private final double mAspectRatio;
    private final int mHeight;
    private final int mWidth;

    public enum ResolutionBand implements MetricParameter {
        UNKNOWN(Integer.MIN_VALUE, Integer.MIN_VALUE),
        SUB_SD(0, 0),
        SD(640, 480),
        HD(1280, 720),
        HD_1080P(1920, 1080),
        ULTRA_HD(3840, 2160);

        private final int mMinHeight;
        private final int mMinWidth;

        ResolutionBand(int i, int i2) {
            this.mMinWidth = i;
            this.mMinHeight = i2;
        }

        public int getMinWidth() {
            return this.mMinWidth;
        }

        public int getMinHeight() {
            return this.mMinHeight;
        }

        public boolean satisfiesOrExceeds(int i, int i2) {
            return i >= this.mMinWidth || i2 >= this.mMinHeight;
        }

        public boolean doesNotExceed(int i, int i2) {
            int i3 = AnonymousClass1.$SwitchMap$com$amazon$avod$media$VideoResolution$ResolutionBand[ordinal()];
            if (i3 == 1) {
                return !SD.satisfiesOrExceeds(i, i2);
            }
            if (i3 == 2) {
                return !HD.satisfiesOrExceeds(i, i2);
            }
            if (i3 == 3) {
                return !HD_1080P.satisfiesOrExceeds(i, i2);
            }
            if (i3 != 4) {
                return i3 == 5;
            }
            return !ULTRA_HD.satisfiesOrExceeds(i, i2);
        }

        @Override // com.amazon.avod.metrics.pmet.MetricParameter
        @Nonnull
        public String toReportableString() {
            return name();
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.VideoResolution$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$VideoResolution$ResolutionBand;

        static {
            int[] iArr = new int[ResolutionBand.values().length];
            $SwitchMap$com$amazon$avod$media$VideoResolution$ResolutionBand = iArr;
            try {
                iArr[ResolutionBand.SUB_SD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$VideoResolution$ResolutionBand[ResolutionBand.SD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$VideoResolution$ResolutionBand[ResolutionBand.HD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$VideoResolution$ResolutionBand[ResolutionBand.HD_1080P.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$VideoResolution$ResolutionBand[ResolutionBand.ULTRA_HD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public VideoResolution(@Nonnegative int i, @Nonnegative int i2) {
        this(i, i2, -1.0d);
    }

    public VideoResolution(@Nonnegative int i, @Nonnegative int i2, double d) {
        Preconditions.checkArgument(i >= 0, "width must be non-negative!");
        Preconditions.checkArgument(i2 >= 0, "height must be non-negative!");
        this.mWidth = i;
        this.mHeight = i2;
        this.mAspectRatio = d;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getStorageAspectRatioAdjustedHeight() {
        double d = this.mAspectRatio;
        if (d > 0.0d && Math.abs(d - (-1.0d)) > DELTA.doubleValue()) {
            return (int) (((double) this.mWidth) / this.mAspectRatio);
        }
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isSDOrHigher() {
        return isSDOrHigher(this.mWidth, this.mHeight);
    }

    private static boolean isSDOrHigher(int i, int i2) {
        return ResolutionBand.SD.satisfiesOrExceeds(i, i2);
    }

    public boolean isHDOrHigher() {
        return isHDOrHigher(this.mWidth, this.mHeight);
    }

    private static boolean isHDOrHigher(int i, int i2) {
        return ResolutionBand.HD.satisfiesOrExceeds(i, i2);
    }

    public boolean isHD1080pOrHigher() {
        return isHD1080pOrHigher(this.mWidth, this.mHeight);
    }

    private static boolean isHD1080pOrHigher(int i, int i2) {
        return ResolutionBand.HD_1080P.satisfiesOrExceeds(i, i2);
    }

    public boolean isUltraHDOrHigher() {
        return isUltraHDOrHigher(this.mWidth, this.mHeight);
    }

    private static boolean isUltraHDOrHigher(int i, int i2) {
        return ResolutionBand.ULTRA_HD.satisfiesOrExceeds(i, i2);
    }

    public ResolutionBand getResolutionBand() {
        if (!isSDOrHigher()) {
            return ResolutionBand.SUB_SD;
        }
        if (!isHDOrHigher()) {
            return ResolutionBand.SD;
        }
        if (!isHD1080pOrHigher()) {
            return ResolutionBand.HD;
        }
        if (!isUltraHDOrHigher()) {
            return ResolutionBand.HD_1080P;
        }
        return ResolutionBand.ULTRA_HD;
    }

    @Nonnull
    public static ResolutionBand getResolutionBand(int i, int i2, double d) {
        Preconditions.checkArgument(i != 0, "width should not be 0");
        if (d == -1.0d) {
            d = 1.7777777777777777d;
        }
        double d2 = i2;
        double d3 = i;
        double d4 = (d2 / d3) * d;
        if (d4 >= 1.0d) {
            i = (int) (d3 * d4);
        } else {
            i2 = (int) (d2 * d4);
        }
        if (!isSDOrHigher(i, i2)) {
            return ResolutionBand.SUB_SD;
        }
        if (!isHDOrHigher(i, i2)) {
            return ResolutionBand.SD;
        }
        if (!isHD1080pOrHigher(i, i2)) {
            return ResolutionBand.HD;
        }
        if (!isUltraHDOrHigher(i, i2)) {
            return ResolutionBand.HD_1080P;
        }
        if (isUltraHDOrHigher(i, i2)) {
            return ResolutionBand.ULTRA_HD;
        }
        return ResolutionBand.UNKNOWN;
    }

    public double getAspectRatio() {
        return this.mAspectRatio;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("Height", getHeight()).add("Width", getWidth()).add("AspectRatio", getAspectRatio()).add("StorageAspectRatioAdjustedHeight", getStorageAspectRatioAdjustedHeight()).toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof VideoResolution)) {
            return false;
        }
        VideoResolution videoResolution = (VideoResolution) obj;
        return Objects.equal(Integer.valueOf(getWidth()), Integer.valueOf(videoResolution.getWidth())) && Objects.equal(Integer.valueOf(getHeight()), Integer.valueOf(videoResolution.getHeight()));
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
    }
}
