package org.chromium.android_webview.metrics;

import android.content.Context;
import android.content.pm.PackageManager;
import defpackage.Is;
import defpackage.Ts;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwMetricsServiceClient {
    public static long getAppInstallTime() {
        try {
            Context context = Is.a;
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime / 1000;
        } catch (PackageManager.NameNotFoundException unused) {
            Ts.a("AwMetricsServiceCli-", "App could not find itself by package name!", new Object[0]);
            return -1L;
        }
    }
}
