package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.omnibox.geo.f;

/* JADX INFO: compiled from: VisibleNetworksTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oo3 {
    public static f a = null;
    public static long b = Long.MAX_VALUE;
    public static boolean c;

    public static boolean a() {
        f fVar = a;
        return (fVar == null || b == Long.MAX_VALUE || fVar.a() || SystemClock.elapsedRealtime() - b >= 300000) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(android.content.Context r19) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.oo3.b(android.content.Context):void");
    }
}
