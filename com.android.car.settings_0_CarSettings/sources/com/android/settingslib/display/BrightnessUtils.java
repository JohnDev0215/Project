package com.android.settingslib.display;

import android.util.MathUtils;

/* JADX INFO: loaded from: classes6.dex */
public class BrightnessUtils {
    private static final float A = 0.17883277f;
    private static final float B = 0.28466892f;
    private static final float C = 0.5599107f;
    public static final int GAMMA_SPACE_MAX = 65535;
    public static final int GAMMA_SPACE_MIN = 0;
    private static final float R = 0.5f;

    public static final int convertGammaToLinear(int val, int min, int max) {
        float ret;
        float normalizedVal = MathUtils.norm(0.0f, 65535.0f, val);
        if (normalizedVal <= R) {
            ret = MathUtils.sq(normalizedVal / R);
        } else {
            ret = MathUtils.exp((normalizedVal - C) / A) + B;
        }
        return Math.round(MathUtils.lerp(min, max, ret / 12.0f));
    }

    public static final float convertGammaToLinearFloat(int val, float min, float max) {
        float ret;
        float normalizedVal = MathUtils.norm(0.0f, 65535.0f, val);
        if (normalizedVal <= R) {
            ret = MathUtils.sq(normalizedVal / R);
        } else {
            ret = MathUtils.exp((normalizedVal - C) / A) + B;
        }
        float normalizedRet = MathUtils.constrain(ret, 0.0f, 12.0f);
        return MathUtils.lerp(min, max, normalizedRet / 12.0f);
    }

    public static final int convertLinearToGamma(int val, int min, int max) {
        return convertLinearToGammaFloat(val, min, max);
    }

    public static final int convertLinearToGammaFloat(float val, float min, float max) {
        float ret;
        float normalizedVal = MathUtils.norm(min, max, val) * 12.0f;
        if (normalizedVal <= 1.0f) {
            ret = MathUtils.sqrt(normalizedVal) * R;
        } else {
            ret = C + (MathUtils.log(normalizedVal - B) * A);
        }
        return Math.round(MathUtils.lerp(0.0f, 65535.0f, ret));
    }
}
