package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/* JADX INFO: renamed from: Lf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0203Lf {
    public static Boolean a;
    public static Boolean b;
    public static Boolean c;

    public static boolean a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (a == null) {
            a = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        return a.booleanValue();
    }

    public static boolean b(Context context) {
        if (a(context)) {
            if (Build.VERSION.SDK_INT >= 24) {
                if (b == null) {
                    b = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
                }
                if (!b.booleanValue() || AbstractC0220Mf.a()) {
                }
            }
            return true;
        }
        return false;
    }
}
