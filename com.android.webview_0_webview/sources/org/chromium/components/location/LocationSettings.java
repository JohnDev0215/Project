package org.chromium.components.location;

import J.N;
import defpackage.Mx;
import java.util.Objects;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LocationSettings {
    public static boolean canPromptForAndroidLocationPermission(WindowAndroid windowAndroid) {
        return windowAndroid.canRequestPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    public static boolean canPromptToEnableSystemLocationSetting() {
        Objects.requireNonNull(Mx.a());
        return false;
    }

    public static boolean hasAndroidLocationPermission() {
        return Mx.a().b();
    }

    public static boolean isSystemLocationSettingEnabled() {
        return Mx.a().d();
    }

    public static void promptToEnableSystemLocationSetting(int i, WindowAndroid windowAndroid, long j) {
        Objects.requireNonNull(Mx.a());
        Integer num = 3;
        N.MeFOXp9F(j, num.intValue());
    }
}
