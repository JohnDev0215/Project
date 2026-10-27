package org.chromium.components.location;

import J.N;
import android.os.Process;
import defpackage.ba1;
import defpackage.c6;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class LocationSettings {
    @CalledByNative
    public static boolean canPromptForAndroidLocationPermission(WindowAndroid windowAndroid) {
        return windowAndroid.canRequestPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    @CalledByNative
    public static boolean canPromptToEnableSystemLocationSetting() {
        ba1.a().getClass();
        return false;
    }

    @CalledByNative
    public static boolean hasAndroidLocationPermission() {
        ba1.a().getClass();
        if (c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            return true;
        }
        return c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    @CalledByNative
    public static boolean isSystemLocationSettingEnabled() {
        ba1.a().getClass();
        return ba1.b();
    }

    @CalledByNative
    public static void promptToEnableSystemLocationSetting(int i, WindowAndroid windowAndroid, long j) {
        ba1.a().getClass();
        Integer num = 3;
        N.MeFOXp9F(j, num.intValue());
    }
}
