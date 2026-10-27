package defpackage;

import J.N;
import android.content.SharedPreferences;
import defpackage.xz;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: DoubleCachedFieldTrialParameter.java */
/* JADX INFO: loaded from: classes.dex */
public final class rb0 extends rk {
    public final double c;

    public rb0(String str, String str2, double d) {
        super(str, str2);
        this.c = d;
    }

    @Override // defpackage.rk
    public final void a() {
        sk skVar = bq.a;
        double dMQdjXFDB = N.MQdjXFDB(this.a, this.b, this.c);
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        String strB = b();
        sharedPreferencesManager.a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putLong(strB, Double.doubleToRawLongBits(dMQdjXFDB));
        editorEdit.apply();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x007f A[Catch: all -> 0x00b9, TRY_LEAVE, TryCatch #1 {, blocks: (B:12:0x0033, B:14:0x003d, B:15:0x0041, B:17:0x0044, B:36:0x007f, B:41:0x00a5, B:45:0x00ad, B:46:0x00ae, B:47:0x00b3, B:20:0x004d, B:29:0x005f, B:30:0x0064, B:33:0x006f, B:37:0x008e, B:39:0x0094, B:40:0x0099), top: B:55:0x0033, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double c() {
        /*
            r9 = this;
            java.lang.String r0 = r9.b()
            double r1 = r9.c
            uk r9 = org.chromium.chrome.browser.flags.CachedFeatureFlags.d
            r9.d()
            um3 r3 = org.chromium.chrome.browser.flags.CachedFeatureFlags.c
            java.lang.Object r3 = r3.f
            r4 = r3
            java.util.Map r4 = (java.util.Map) r4
            r5 = 1
            if (r4 == 0) goto L17
            r4 = r5
            goto L18
        L17:
            r4 = 0
        L18:
            if (r4 == 0) goto L2e
            java.util.Map r3 = (java.util.Map) r3
            java.lang.Object r9 = r3.get(r0)
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto Lb8
            java.lang.Double r9 = java.lang.Double.valueOf(r9)
            double r1 = r9.doubleValue()
            goto Lb8
        L2e:
            wk3 r3 = org.chromium.chrome.browser.flags.CachedFeatureFlags.b
            java.util.HashMap r4 = r3.d
            monitor-enter(r4)
            java.util.HashMap r6 = r3.d     // Catch: java.lang.Throwable -> Lb9
            java.lang.Object r6 = r6.get(r0)     // Catch: java.lang.Throwable -> Lb9
            java.lang.Double r6 = (java.lang.Double) r6     // Catch: java.lang.Throwable -> Lb9
            if (r6 == 0) goto L44
            double r1 = r6.doubleValue()     // Catch: java.lang.Throwable -> Lb9
            monitor-exit(r4)     // Catch: java.lang.Throwable -> Lb9
            goto Lb8
        L44:
            boolean r6 = r9.c()     // Catch: java.lang.Throwable -> Lb9
            r7 = 0
            if (r6 != 0) goto L4d
            goto L7c
        L4d:
            java.util.concurrent.atomic.AtomicInteger r9 = r9.b     // Catch: java.lang.Throwable -> Lb9
            int r9 = r9.get()     // Catch: java.lang.Throwable -> Lb9
            if (r9 == r5) goto L7c
            r5 = 2
            if (r9 == r5) goto L64
            r5 = 3
            if (r9 == r5) goto L5f
            r5 = 4
            if (r9 == r5) goto L5f
            goto L7c
        L5f:
            java.lang.Double r9 = java.lang.Double.valueOf(r1)     // Catch: java.lang.Throwable -> Lb9
            goto L7d
        L64:
            android.content.SharedPreferences r9 = defpackage.uk.b()     // Catch: java.lang.Throwable -> Lb9
            boolean r5 = r9.contains(r0)     // Catch: java.lang.Throwable -> Lb9
            if (r5 != 0) goto L6f
            goto L7c
        L6f:
            long r5 = r9.getLong(r0, r7)     // Catch: java.lang.Throwable -> Lb9
            double r5 = java.lang.Double.longBitsToDouble(r5)     // Catch: java.lang.Throwable -> Lb9
            java.lang.Double r9 = java.lang.Double.valueOf(r5)     // Catch: java.lang.Throwable -> Lb9
            goto L7d
        L7c:
            r9 = 0
        L7d:
            if (r9 != 0) goto Lae
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r9 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()     // Catch: java.lang.Throwable -> Lb9
            a8 r9 = r9.a     // Catch: java.lang.Throwable -> Lb9
            r9.getClass()     // Catch: java.lang.Throwable -> Lb9
            android.content.SharedPreferences r9 = xz.a.a     // Catch: java.lang.Throwable -> Lb9
            ku2 r5 = defpackage.ku2.t()     // Catch: java.lang.Throwable -> Lb9
            boolean r6 = r9.contains(r0)     // Catch: java.lang.Throwable -> La9
            if (r6 != 0) goto L99
            java.lang.Double r9 = java.lang.Double.valueOf(r1)     // Catch: java.lang.Throwable -> La9
            goto La5
        L99:
            long r1 = r9.getLong(r0, r7)     // Catch: java.lang.Throwable -> La9
            double r1 = java.lang.Double.longBitsToDouble(r1)     // Catch: java.lang.Throwable -> La9
            java.lang.Double r9 = java.lang.Double.valueOf(r1)     // Catch: java.lang.Throwable -> La9
        La5:
            r5.close()     // Catch: java.lang.Throwable -> Lb9
            goto Lae
        La9:
            r9 = move-exception
            r5.close()     // Catch: java.lang.Throwable -> Lad
        Lad:
            throw r9     // Catch: java.lang.Throwable -> Lb9
        Lae:
            java.util.HashMap r1 = r3.d     // Catch: java.lang.Throwable -> Lb9
            r1.put(r0, r9)     // Catch: java.lang.Throwable -> Lb9
            monitor-exit(r4)     // Catch: java.lang.Throwable -> Lb9
            double r1 = r9.doubleValue()
        Lb8:
            return r1
        Lb9:
            r9 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> Lb9
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rb0.c():double");
    }
}
