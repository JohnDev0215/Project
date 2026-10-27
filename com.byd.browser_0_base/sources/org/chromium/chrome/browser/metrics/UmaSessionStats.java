package org.chromium.chrome.browser.metrics;

import J.N;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import defpackage.ai3;
import defpackage.bi3;
import defpackage.qj;
import defpackage.v42;
import defpackage.y13;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class UmaSessionStats {
    public static long f;
    public y13 a;
    public bi3 b;
    public final Context c;
    public ai3 d;
    public boolean e;

    public UmaSessionStats(Context context) {
        this.c = context;
    }

    public static boolean a() {
        return qj.a().g();
    }

    public static void b() {
        v42 v42VarG = v42.g();
        N.MmqfIJ4g(v42VarG.a());
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) v42VarG.a.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = true;
        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected()) || (!v42VarG.a() && !v42VarG.e())) {
            z = false;
        }
        N.Mq3Hvtdc(z);
    }

    @CalledByNative
    public static boolean hasVisibleActivity() {
        return ApplicationStatus.hasVisibleActivities();
    }
}
