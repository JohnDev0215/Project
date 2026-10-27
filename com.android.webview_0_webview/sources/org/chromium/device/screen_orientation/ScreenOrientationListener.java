package org.chromium.device.screen_orientation;

import android.provider.Settings;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ScreenOrientationListener {
    public static boolean isAutoRotateEnabledByUser() {
        return Settings.System.getInt(Is.a.getContentResolver(), "accelerometer_rotation", 0) == 1;
    }
}
