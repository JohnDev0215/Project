package io.sentry.android.core;

import io.sentry.C1523x;
import io.sentry.InterfaceC1511t;

/* JADX INFO: loaded from: classes.dex */
public final class ScreenshotEventProcessor implements InterfaceC1511t {
    public final SentryAndroidOptions l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B f16094m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.d f16095n;

    public ScreenshotEventProcessor(SentryAndroidOptions sentryAndroidOptions, B b10) {
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.l = sentryAndroidOptions;
        this.f16094m = b10;
        this.f16095n = new io.sentry.android.core.internal.util.d(2000L, 3);
        if (sentryAndroidOptions.isAttachScreenshot()) {
            n7.z.c("Screenshot");
        }
    }

    @Override // io.sentry.InterfaceC1511t
    public final io.sentry.protocol.A g(io.sentry.protocol.A a10, C1523x c1523x) {
        return a10;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00ea  */
    @Override // io.sentry.InterfaceC1511t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final io.sentry.C1480k1 i(io.sentry.C1480k1 r14, io.sentry.C1523x r15) {
        /*
            Method dump skipped, instruction units count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.ScreenshotEventProcessor.i(io.sentry.k1, io.sentry.x):io.sentry.k1");
    }
}
