package org.chromium.ui.gfx;

import android.provider.Settings;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Animation {
    public static boolean prefersReducedMotion() {
        return ((double) Settings.Global.getFloat(Is.a.getContentResolver(), "animator_duration_scale", 1.0f)) == 0.0d;
    }
}
