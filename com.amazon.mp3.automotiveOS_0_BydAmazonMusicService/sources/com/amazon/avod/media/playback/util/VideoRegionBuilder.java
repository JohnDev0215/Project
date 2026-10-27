package com.amazon.avod.media.playback.util;

/* JADX INFO: loaded from: classes2.dex */
public class VideoRegionBuilder {
    private HorizontalAlign mHorizontalAlign = HorizontalAlign.CENTER;
    private VerticalAlign mVerticalAlign = VerticalAlign.CENTER;
    private Integer mWidth = null;
    private Float mWidthPercent = null;
    private Integer mHeight = null;
    private Float mHeightPercent = null;
    private Integer mTop = null;
    private Float mTopPercent = null;
    private Integer mBottom = null;
    private Float mBottomPercent = null;
    private Integer mLeft = null;
    private Float mLeftPercent = null;
    private Integer mRight = null;
    private Float mRightPercent = null;

    public enum HorizontalAlign {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum VerticalAlign {
        TOP,
        CENTER,
        BOTTOM
    }

    public static VideoRegionBuilder fromVideoRegionRules(VideoRegionRules videoRegionRules) {
        VideoRegionBuilder videoRegionBuilder = new VideoRegionBuilder();
        videoRegionBuilder.mHorizontalAlign = videoRegionRules.mHorizontalAlign;
        videoRegionBuilder.mVerticalAlign = videoRegionRules.mVerticalAlign;
        videoRegionBuilder.mWidth = videoRegionRules.mWidth;
        videoRegionBuilder.mWidthPercent = videoRegionRules.mWidthPercent;
        videoRegionBuilder.mHeight = videoRegionRules.mHeight;
        videoRegionBuilder.mHeightPercent = videoRegionRules.mHeightPercent;
        videoRegionBuilder.mTop = videoRegionRules.mTop;
        videoRegionBuilder.mTopPercent = videoRegionRules.mTopPercent;
        videoRegionBuilder.mBottom = videoRegionRules.mBottom;
        videoRegionBuilder.mBottomPercent = videoRegionRules.mBottomPercent;
        videoRegionBuilder.mLeft = videoRegionRules.mLeft;
        videoRegionBuilder.mLeftPercent = videoRegionRules.mLeftPercent;
        videoRegionBuilder.mRight = videoRegionRules.mRight;
        videoRegionBuilder.mRightPercent = videoRegionRules.mRightPercent;
        return videoRegionBuilder;
    }

    public VideoRegion build(float f, int i, int i2) {
        int iFloatValue;
        int iFloatValue2;
        int iFloatValue3;
        int iFloatValue4;
        int iFloatValue5;
        int iFloatValue6;
        Integer num = this.mTop;
        if (num != null) {
            iFloatValue = num.intValue();
        } else {
            Float f2 = this.mTopPercent;
            iFloatValue = f2 != null ? (int) ((i2 * f2.floatValue()) / 100.0f) : 0;
        }
        Integer num2 = this.mBottom;
        if (num2 != null) {
            iFloatValue2 = num2.intValue();
        } else {
            Float f3 = this.mBottomPercent;
            iFloatValue2 = f3 != null ? (int) ((i2 * f3.floatValue()) / 100.0f) : 0;
        }
        Integer num3 = this.mLeft;
        if (num3 != null) {
            iFloatValue3 = num3.intValue();
        } else {
            Float f4 = this.mLeftPercent;
            iFloatValue3 = f4 != null ? (int) ((i * f4.floatValue()) / 100.0f) : 0;
        }
        Integer num4 = this.mRight;
        if (num4 != null) {
            iFloatValue4 = num4.intValue();
        } else {
            Float f5 = this.mRightPercent;
            iFloatValue4 = f5 != null ? (int) ((i * f5.floatValue()) / 100.0f) : 0;
        }
        int i3 = (i - iFloatValue3) - iFloatValue4;
        int i4 = (i2 - iFloatValue) - iFloatValue2;
        Integer num5 = this.mWidth;
        if (num5 != null) {
            iFloatValue5 = num5.intValue();
        } else {
            Float f6 = this.mWidthPercent;
            iFloatValue5 = f6 != null ? (int) ((i * f6.floatValue()) / 100.0f) : i3;
        }
        int iMax = Math.max(Math.min(iFloatValue5, i3), 0);
        Integer num6 = this.mHeight;
        if (num6 != null) {
            iFloatValue6 = num6.intValue();
        } else {
            Float f7 = this.mHeightPercent;
            iFloatValue6 = f7 != null ? (int) ((i2 * f7.floatValue()) / 100.0f) : i4;
        }
        int iMax2 = Math.max(Math.min(iFloatValue6, i4), 0);
        float f8 = iMax;
        float f9 = iMax2;
        if (f8 / f9 <= f) {
            iMax2 = (int) (f8 / f);
        } else {
            iMax = (int) (f9 * f);
        }
        int i5 = AnonymousClass1.$SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$HorizontalAlign[this.mHorizontalAlign.ordinal()];
        if (i5 == 1) {
            iFloatValue3 += (((i - iMax) - iFloatValue4) - iFloatValue3) / 2;
        } else if (i5 == 2) {
            iFloatValue3 = (i - iMax) - iFloatValue4;
        }
        int i6 = AnonymousClass1.$SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$VerticalAlign[this.mVerticalAlign.ordinal()];
        if (i6 == 1) {
            iFloatValue += (((i2 - iMax2) - iFloatValue2) - iFloatValue) / 2;
        } else if (i6 == 2) {
            iFloatValue = (i2 - iMax2) - iFloatValue2;
        }
        return new VideoRegion(iFloatValue, iFloatValue3, iMax, iMax2);
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.util.VideoRegionBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$HorizontalAlign;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$VerticalAlign;

        static {
            int[] iArr = new int[VerticalAlign.values().length];
            $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$VerticalAlign = iArr;
            try {
                iArr[VerticalAlign.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$VerticalAlign[VerticalAlign.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$VerticalAlign[VerticalAlign.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[HorizontalAlign.values().length];
            $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$HorizontalAlign = iArr2;
            try {
                iArr2[HorizontalAlign.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$HorizontalAlign[HorizontalAlign.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$playback$util$VideoRegionBuilder$HorizontalAlign[HorizontalAlign.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
