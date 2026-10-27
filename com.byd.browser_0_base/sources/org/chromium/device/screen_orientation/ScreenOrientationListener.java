package org.chromium.device.screen_orientation;

import android.provider.Settings;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class ScreenOrientationListener {
    @CalledByNative
    public static boolean isAutoRotateEnabledByUser() {
        return Settings.System.getInt(xz.a.getContentResolver(), "accelerometer_rotation", 0) == 1;
    }
}
