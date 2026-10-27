package org.chromium.components.permissions;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import defpackage.ba1;
import defpackage.pd;
import defpackage.ro1;
import defpackage.vd1;
import defpackage.xz;
import defpackage.yj;
import java.util.Arrays;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionUtil {
    public static final String[] a = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public static final String[] b = {"android.permission.ACCESS_COARSE_LOCATION"};
    public static final String[] c = {"android.permission.ACCESS_FINE_LOCATION"};
    public static final String[] d = {"android.permission.CAMERA"};
    public static final String[] e = {"android.permission.RECORD_AUDIO"};
    public static final String[] f = {"android.permission.POST_NOTIFICATIONS"};
    public static final String[] g = new String[0];

    @CalledByNative
    public static boolean areAppLevelNotificationsEnabled() {
        return new ro1(xz.a).a();
    }

    @CalledByNative
    public static boolean canRequestSystemPermissionsForBluetooth(WindowAndroid windowAndroid) {
        return Build.VERSION.SDK_INT >= 31 ? windowAndroid.canRequestPermission("android.permission.BLUETOOTH_SCAN") && windowAndroid.canRequestPermission("android.permission.BLUETOOTH_CONNECT") : windowAndroid.canRequestPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    @CalledByNative
    public static boolean doesAppLevelSettingsAllowSiteNotifications() {
        vd1 vd1Var = vd1.j;
        return vd1Var != null && vd1Var.e();
    }

    @CalledByNative
    public static String[] getOptionalAndroidPermissionsForContentSetting(int i) {
        String[] strArr = g;
        if (i != 4) {
            return strArr;
        }
        return Build.VERSION.SDK_INT >= 31 && N.Mvpau744("AndroidApproximateLocationPermissionSupport") ? (String[]) Arrays.copyOf(c, 1) : strArr;
    }

    @CalledByNative
    public static String[] getRequiredAndroidPermissionsForContentSetting(int i) {
        if (i == 4) {
            return Build.VERSION.SDK_INT >= 31 && N.Mvpau744("AndroidApproximateLocationPermissionSupport") ? (String[]) Arrays.copyOf(b, 1) : (String[]) Arrays.copyOf(a, 2);
        }
        String[] strArr = g;
        return i != 5 ? i != 8 ? (i == 9 || i == 56) ? (String[]) Arrays.copyOf(d, 1) : strArr : (String[]) Arrays.copyOf(e, 1) : yj.a() ? (String[]) Arrays.copyOf(f, 1) : strArr;
    }

    @CalledByNative
    public static boolean needsLocationPermissionForBluetooth(WindowAndroid windowAndroid) {
        return Build.VERSION.SDK_INT < 31 && !windowAndroid.hasPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    @CalledByNative
    public static boolean needsLocationServicesForBluetooth() {
        if (Build.VERSION.SDK_INT < 31) {
            ba1.a().getClass();
            if (!ba1.b()) {
                return true;
            }
        }
        return false;
    }

    @CalledByNative
    public static boolean needsNearbyDevicesPermissionForBluetooth(WindowAndroid windowAndroid) {
        return Build.VERSION.SDK_INT >= 31 && !(windowAndroid.hasPermission("android.permission.BLUETOOTH_SCAN") && windowAndroid.hasPermission("android.permission.BLUETOOTH_CONNECT"));
    }

    @CalledByNative
    public static void requestLocationServices(WindowAndroid windowAndroid) {
        Activity activity = (Activity) windowAndroid.k().get();
        ba1.a().getClass();
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        intent.setFlags(268435456);
        activity.startActivity(intent);
    }

    @CalledByNative
    public static void requestSystemPermissionsForBluetooth(WindowAndroid windowAndroid, PermissionCallback permissionCallback) {
        String[] strArr = Build.VERSION.SDK_INT >= 31 ? new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"} : new String[]{"android.permission.ACCESS_FINE_LOCATION"};
        if (permissionCallback == null) {
            permissionCallback = new pd(20);
        }
        windowAndroid.b(strArr, permissionCallback);
    }
}
