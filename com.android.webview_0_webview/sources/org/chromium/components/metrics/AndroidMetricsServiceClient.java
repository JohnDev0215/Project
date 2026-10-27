package org.chromium.components.metrics;

import android.content.Context;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidMetricsServiceClient {
    public static boolean canRecordPackageNameForAppType() {
        Context context = Is.a;
        return (context.getApplicationInfo().flags & 1) != 0 || "com.android.vending".equals(context.getPackageManager().getInstallerPackageName(context.getPackageName()));
    }

    public static String getAppPackageName() {
        return Is.a.getPackageName();
    }
}
