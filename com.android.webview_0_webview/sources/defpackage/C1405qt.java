package defpackage;

import android.content.pm.PackageInfo;
import android.location.LocationManager;
import android.net.LinkProperties;

/* JADX INFO: renamed from: qt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1405qt {
    public static long a(PackageInfo packageInfo) {
        return packageInfo.getLongVersionCode();
    }

    public static String b(LinkProperties linkProperties) {
        return linkProperties.getPrivateDnsServerName();
    }

    public static boolean c(LocationManager locationManager) {
        return locationManager.isLocationEnabled();
    }

    public static boolean d(LinkProperties linkProperties) {
        return linkProperties.isPrivateDnsActive();
    }
}
