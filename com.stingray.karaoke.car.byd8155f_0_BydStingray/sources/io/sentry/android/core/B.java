package io.sentry.android.core;

import android.os.Handler;
import android.os.Looper;
import io.sentry.F1;

/* JADX INFO: loaded from: classes.dex */
public final class B implements io.sentry.transport.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final B f16041b = new B();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f16042a;

    public B() {
        this.f16042a = new Handler(Looper.getMainLooper());
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Boolean a() {
        /*
            r3 = this;
            java.lang.String r0 = "google_sdk"
            java.lang.String r1 = "generic"
            java.lang.String r2 = android.os.Build.BRAND     // Catch: java.lang.Throwable -> L15
            boolean r2 = r2.startsWith(r1)     // Catch: java.lang.Throwable -> L15
            if (r2 == 0) goto L18
            java.lang.String r2 = android.os.Build.DEVICE     // Catch: java.lang.Throwable -> L15
            boolean r2 = r2.startsWith(r1)     // Catch: java.lang.Throwable -> L15
            if (r2 != 0) goto L97
            goto L18
        L15:
            r0 = move-exception
            goto L9d
        L18:
            java.lang.String r2 = android.os.Build.FINGERPRINT     // Catch: java.lang.Throwable -> L15
            boolean r1 = r2.startsWith(r1)     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L97
            java.lang.String r1 = "unknown"
            boolean r1 = r2.startsWith(r1)     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L97
            java.lang.String r1 = android.os.Build.HARDWARE     // Catch: java.lang.Throwable -> L15
            java.lang.String r2 = "goldfish"
            boolean r2 = r1.contains(r2)     // Catch: java.lang.Throwable -> L15
            if (r2 != 0) goto L97
            java.lang.String r2 = "ranchu"
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L97
            java.lang.String r1 = android.os.Build.MODEL     // Catch: java.lang.Throwable -> L15
            boolean r2 = r1.contains(r0)     // Catch: java.lang.Throwable -> L15
            if (r2 != 0) goto L97
            java.lang.String r2 = "Emulator"
            boolean r2 = r1.contains(r2)     // Catch: java.lang.Throwable -> L15
            if (r2 != 0) goto L97
            java.lang.String r2 = "Android SDK built for x86"
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L97
            java.lang.String r1 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> L15
            java.lang.String r2 = "Genymotion"
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Throwable -> L15
            if (r1 != 0) goto L97
            java.lang.String r1 = android.os.Build.PRODUCT     // Catch: java.lang.Throwable -> L15
            java.lang.String r2 = "sdk_google"
            boolean r2 = r1.contains(r2)     // Catch: java.lang.Throwable -> L15
            if (r2 != 0) goto L97
            boolean r0 = r1.contains(r0)     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L97
            java.lang.String r0 = "sdk"
            boolean r0 = r1.contains(r0)     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L97
            java.lang.String r0 = "sdk_x86"
            boolean r0 = r1.contains(r0)     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L97
            java.lang.String r0 = "vbox86p"
            boolean r0 = r1.contains(r0)     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L97
            java.lang.String r0 = "emulator"
            boolean r0 = r1.contains(r0)     // Catch: java.lang.Throwable -> L15
            if (r0 != 0) goto L97
            java.lang.String r0 = "simulator"
            boolean r0 = r1.contains(r0)     // Catch: java.lang.Throwable -> L15
            if (r0 == 0) goto L95
            goto L97
        L95:
            r0 = 0
            goto L98
        L97:
            r0 = 1
        L98:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)     // Catch: java.lang.Throwable -> L15
            return r3
        L9d:
            io.sentry.p1 r1 = io.sentry.EnumC1495p1.ERROR
            java.lang.String r2 = "Error checking whether application is running in an emulator."
            java.lang.Object r3 = r3.f16042a
            io.sentry.K r3 = (io.sentry.K) r3
            r3.s(r1, r2, r0)
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.B.a():java.lang.Boolean");
    }

    @Override // io.sentry.transport.g
    public boolean v() {
        int i7 = AbstractC1441q.f16290a[((F1) this.f16042a).getConnectionStatusProvider().e().ordinal()];
        return i7 == 1 || i7 == 2 || i7 == 3;
    }

    public B(F1 f12) {
        this.f16042a = f12;
    }

    public B(io.sentry.K k10) {
        h6.e.q(k10, "The ILogger object is required.");
        this.f16042a = k10;
    }
}
