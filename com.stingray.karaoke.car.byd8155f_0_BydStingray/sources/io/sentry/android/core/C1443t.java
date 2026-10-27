package io.sentry.android.core;

import android.content.Context;
import io.sentry.C1523x;
import io.sentry.InterfaceC1511t;
import io.sentry.L1;

/* JADX INFO: renamed from: io.sentry.android.core.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1443t implements InterfaceC1511t {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final SentryAndroidOptions f16293m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final B f16294n;
    public final e7.f o;

    public C1443t(Context context, B b10, SentryAndroidOptions sentryAndroidOptions) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
        this.f16293m = sentryAndroidOptions;
        this.f16294n = b10;
        this.o = new e7.f(new L1(sentryAndroidOptions, 0), 12);
    }

    public static boolean a(io.sentry.hints.b bVar) {
        if (bVar instanceof io.sentry.hints.a) {
            return "anr_background".equals(((io.sentry.hints.a) bVar).e());
        }
        return false;
    }

    @Override // io.sentry.InterfaceC1511t
    public final io.sentry.protocol.A g(io.sentry.protocol.A a10, C1523x c1523x) {
        return a10;
    }

    /* JADX WARN: Removed duplicated region for block: B:196:0x0488  */
    @Override // io.sentry.InterfaceC1511t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final io.sentry.C1480k1 i(io.sentry.C1480k1 r26, io.sentry.C1523x r27) {
        /*
            Method dump skipped, instruction units count: 1671
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.C1443t.i(io.sentry.k1, io.sentry.x):io.sentry.k1");
    }
}
