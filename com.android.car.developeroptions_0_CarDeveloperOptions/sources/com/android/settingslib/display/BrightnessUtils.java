package com.android.settingslib.display;

import android.util.MathUtils;

/* JADX INFO: loaded from: classes.dex */
public class BrightnessUtils {
    public static final int convertLinearToGamma(int i, int i2, int i3) {
        return convertLinearToGammaFloat(i, i2, i3);
    }

    public static final int convertLinearToGammaFloat(float f, float f2, float f3) {
        float fLog;
        float fNorm = MathUtils.norm(f2, f3, f) * 12.0f;
        if (fNorm <= 1.0f) {
            fLog = MathUtils.sqrt(fNorm) * 0.5f;
        } else {
            fLog = (MathUtils.log(fNorm - 0.28466892f) * 0.17883277f) + 0.5599107f;
        }
        return Math.round(MathUtils.lerp(0.0f, 65535.0f, fLog));
    }
}
