package org.chromium.ui.gfx;

import android.provider.Settings;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class Animation {
    @CalledByNative
    public static boolean prefersReducedMotion() {
        return ((double) Settings.Global.getFloat(xz.a.getContentResolver(), "animator_duration_scale", 1.0f)) == 0.0d;
    }
}
