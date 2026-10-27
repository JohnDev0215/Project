package org.chromium.components.webapps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import defpackage.fq3;
import defpackage.ku2;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
public class WebappsUtils {
    public static boolean a;
    public static boolean b;

    @SuppressLint({"WrongConstant"})
    public static boolean a() {
        if (b()) {
            return true;
        }
        return !xz.a.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 32).isEmpty();
    }

    public static boolean b() {
        if (!b) {
            ShortcutManager shortcutManager = (ShortcutManager) xz.a.getSystemService(ShortcutManager.class);
            ku2 ku2VarT = ku2.t();
            try {
                a = shortcutManager.isRequestPinShortcutSupported();
                ku2VarT.close();
                b = true;
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        return a;
    }

    @CalledByNative
    public static String queryFirstWebApkPackage(String str) {
        return fq3.d(xz.a, str);
    }

    @CalledByNative
    public static void showWebApkInstallResultToast(int i) {
        Context context = xz.a;
        if (i == 11) {
            Toast.b(0, xz.a, context.getString(R$string.webapk_install_in_progress)).e();
        } else if (i != 0) {
            Toast.b(0, xz.a, context.getString(R$string.webapk_install_failed)).e();
        }
    }
}
