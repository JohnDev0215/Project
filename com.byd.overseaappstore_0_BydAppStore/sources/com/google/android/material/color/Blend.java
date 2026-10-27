package com.google.android.material.color;

import spi.d;

/* JADX INFO: loaded from: classes2.dex */
final class Blend {
    private static final float HARMONIZE_MAX_DEGREES = 15.0f;
    private static final float HARMONIZE_PERCENTAGE = 0.5f;

    private Blend() {
    }

    public static int blendCam16Ucs(int i, int i2, float f) {
        Cam16 cam16FromInt = Cam16.fromInt(i);
        Cam16 cam16FromInt2 = Cam16.fromInt(i2);
        float jStar = cam16FromInt.getJStar();
        float aStar = cam16FromInt.getAStar();
        float bStar = cam16FromInt.getBStar();
        return Cam16.fromUcs(d.a(cam16FromInt2.getJStar(), jStar, f, jStar), d.a(cam16FromInt2.getAStar(), aStar, f, aStar), d.a(cam16FromInt2.getBStar(), bStar, f, bStar)).getInt();
    }

    public static int blendHctHue(int i, int i2, float f) {
        return Hct.from(Cam16.fromInt(blendCam16Ucs(i, i2, f)).getHue(), Cam16.fromInt(i).getChroma(), ColorUtils.lstarFromInt(i)).toInt();
    }

    public static int harmonize(int i, int i2) {
        Hct hctFromInt = Hct.fromInt(i);
        Hct hctFromInt2 = Hct.fromInt(i2);
        float fMin = Math.min(MathUtils.differenceDegrees(hctFromInt.getHue(), hctFromInt2.getHue()) * 0.5f, HARMONIZE_MAX_DEGREES);
        return Hct.from(MathUtils.sanitizeDegrees((fMin * rotationDirection(hctFromInt.getHue(), hctFromInt2.getHue())) + hctFromInt.getHue()), hctFromInt.getChroma(), hctFromInt.getTone()).toInt();
    }

    private static float rotationDirection(float f, float f2) {
        float f3 = f2 - f;
        float f4 = f3 + 360.0f;
        float f5 = f3 - 360.0f;
        float fAbs = Math.abs(f3);
        float fAbs2 = Math.abs(f4);
        float fAbs3 = Math.abs(f5);
        return (fAbs > fAbs2 || fAbs > fAbs3) ? (fAbs2 > fAbs || fAbs2 > fAbs3) ? ((double) f5) >= 0.0d ? 1.0f : -1.0f : ((double) f4) >= 0.0d ? 1.0f : -1.0f : ((double) f3) >= 0.0d ? 1.0f : -1.0f;
    }
}
