package org.chromium.chrome.browser.metrics;

import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import defpackage.gx;
import defpackage.oj2;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class UmaUtils {
    public static a a;
    public static long b;
    public static long c;
    public static long d;

    public interface a {
    }

    public static int a(Context context) {
        int appStandbyBucket = ((UsageStatsManager) context.getSystemService("usagestats")).getAppStandbyBucket();
        if (appStandbyBucket == 5) {
            return 6;
        }
        if (appStandbyBucket == 10) {
            return 0;
        }
        if (appStandbyBucket == 20) {
            return 1;
        }
        if (appStandbyBucket == 30) {
            return 2;
        }
        if (appStandbyBucket == 40) {
            return 3;
        }
        if (appStandbyBucket != 45) {
            return appStandbyBucket != 50 ? 8 : 7;
        }
        return 4;
    }

    public static boolean b() {
        return d != 0;
    }

    public static boolean c() {
        return c != 0;
    }

    public static void d() {
        long j = c;
        if (j == 0 || j < d) {
            a aVar = a;
            if (aVar != null && j == 0) {
                org.chromium.chrome.browser.metrics.a aVar2 = (org.chromium.chrome.browser.metrics.a) ((gx) aVar).f;
                if (aVar2.i) {
                    oj2.O("Startup.Android.Cold.FirstNavigationCommitOccurredPreForeground", true);
                }
                if (aVar2.j) {
                    oj2.O("Startup.Android.Cold.FirstPaintOccurredPreForeground", true);
                }
                Object obj = ThreadUtils.a;
                a = null;
            }
            c = SystemClock.uptimeMillis();
        }
    }

    @CalledByNative
    public static long getApplicationStartTime() {
        return b;
    }

    @CalledByNative
    public static long getProcessStartTime() {
        return Process.getStartUptimeMillis();
    }
}
