package org.chromium.chrome.browser.webapps;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import defpackage.C0175b;
import defpackage.kp1;
import defpackage.lp1;
import defpackage.n3;
import defpackage.o8;
import defpackage.qq;
import defpackage.xz;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.webapps.WebappsIconUtils;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes2.dex */
public class WebApkInstallService {
    public static void a(String str, int i, String str2, String str3, Bitmap bitmap, String str4, o8 o8Var) {
        String str5;
        int i2;
        Context context = xz.a;
        if (i == 30) {
            str5 = "browser";
            i2 = 0;
        } else {
            str5 = "twa_disclosure_initial";
            i2 = 1;
        }
        qq qqVarD = n3.D(str5, new o8(i, -1, C0175b.j("webapk_install_notification_tag_prefix.", str)));
        qqVarD.k(str2);
        qqVarD.j(str4);
        qqVarD.m(bitmap);
        qqVarD.p(R$drawable.ic_chrome);
        qqVarD.c(o8Var);
        qq qqVar = qqVarD;
        qqVar.a.i = i2;
        qqVar.a.A.when = System.currentTimeMillis();
        qqVar.r(N.MR6Af3ZS(str3, 1));
        qqVar.h(true);
        if (i == 38) {
            qqVarD.b(0, context.getResources().getString(R$string.webapk_install_failed_action_open), o8Var, 28);
        }
        lp1 lp1VarF = qqVarD.f();
        new NotificationManagerProxyImpl(context).c(lp1VarF);
        kp1.a.a.b(i, lp1VarF.a);
    }

    @CalledByNative
    public static void cancelNotification(String str) {
        new NotificationManagerProxyImpl(xz.a).d(-1, "webapk_install_notification_tag_prefix." + str);
    }

    @CalledByNative
    public static void showInstallFailedNotification(String str, String str2, String str3, Bitmap bitmap, boolean z, int i) {
        Context context = xz.a;
        String string = context.getResources().getString(R$string.notification_webapk_install_failed, str2);
        String string2 = xz.a.getResources().getString(R$string.notification_webapk_install_failed_contents_general);
        int i2 = WebApkInstallBroadcastReceiver.a;
        Intent intent = new Intent("WebApkInstallNotification.open");
        intent.setClass(context, WebApkInstallBroadcastReceiver.class);
        intent.putExtra("WebApkInstallNotification.notification_id", str);
        intent.putExtra("WebApkInstallNotification.start_url", str3);
        a(str, 38, string, str3, z ? WebappsIconUtils.generateAdaptiveIconBitmap(bitmap) : bitmap, string2, o8.b(context, 0, intent, 134217728, false));
    }

    @CalledByNative
    public static void showInstallInProgressNotification(String str, String str2, String str3, Bitmap bitmap, boolean z) {
        String string = xz.a.getResources().getString(R$string.notification_webapk_install_in_progress, str2);
        if (z) {
            bitmap = WebappsIconUtils.generateAdaptiveIconBitmap(bitmap);
        }
        a(str, 30, str2, str3, bitmap, string, null);
        Toast.b(0, xz.a, string).e();
    }

    @CalledByNative
    public static void showInstalledNotification(String str, String str2, String str3, String str4, Bitmap bitmap, boolean z) {
        Context context = xz.a;
        o8 o8VarA = o8.a(context, 0, z7.d(str, str4, false), 134217728);
        if (z) {
            bitmap = WebappsIconUtils.generateAdaptiveIconBitmap(bitmap);
        }
        a(str2, 31, str3, str4, bitmap, context.getResources().getString(R$string.notification_webapk_installed), o8VarA);
    }
}
