package org.chromium.android_webview.safe_browsing;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Trace;
import defpackage.AbstractC0178Jq;
import defpackage.AbstractC0279Pk;
import defpackage.Es;
import defpackage.It;
import defpackage.Ts;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwSafeBrowsingConfigHelper {
    public static volatile boolean a;
    public static volatile boolean b;

    public static Boolean a(Context context) {
        try {
            Trace.beginSection("AwSafeBrowsingConfigHelper.getAppOptInPreference");
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo.metaData == null) {
                    Trace.endSection();
                    return null;
                }
                Boolean boolValueOf = applicationInfo.metaData.containsKey("android.webkit.WebView.EnableSafeBrowsing") ? Boolean.valueOf(applicationInfo.metaData.getBoolean("android.webkit.WebView.EnableSafeBrowsing")) : null;
                Trace.endSection();
                return boolValueOf;
            } finally {
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Ts.a("AwSafeBrowsingConfi-", "App could not find itself by package name!", new Object[0]);
            return Boolean.FALSE;
        }
        Ts.a("AwSafeBrowsingConfi-", "App could not find itself by package name!", new Object[0]);
        return Boolean.FALSE;
    }

    public static void b(Context context) {
        boolean zBooleanValue;
        Trace.beginSection("AwSafeBrowsingConfigHelper.maybeEnableSafeBrowsingFromManifest");
        try {
            Boolean boolA = a(context);
            if (boolA == null) {
                c(0);
            } else if (boolA.booleanValue()) {
                c(1);
            } else {
                c(2);
            }
            if (boolA == null) {
                Trace.beginSection("AwSafeBrowsingConfigHelper.isDisabledByCommandLine");
                try {
                    boolean zH = Es.e().h("webview-disable-safebrowsing-support");
                    Trace.endSection();
                    zBooleanValue = !zH;
                } finally {
                }
            } else {
                zBooleanValue = boolA.booleanValue();
            }
            b = zBooleanValue;
            Objects.requireNonNull(AbstractC0178Jq.a());
            Boolean bool = Boolean.FALSE;
            a = bool == null ? false : bool.booleanValue();
            if (bool == null) {
                d(2);
            } else if (bool.booleanValue()) {
                d(1);
            } else {
                d(0);
            }
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public static void c(int i) {
        It.d("SafeBrowsing.WebView.AppOptIn", i, 3);
    }

    public static void d(int i) {
        It.d("SafeBrowsing.WebView.UserOptIn", i, 3);
    }

    public static boolean getSafeBrowsingUserOptIn() {
        return a;
    }
}
