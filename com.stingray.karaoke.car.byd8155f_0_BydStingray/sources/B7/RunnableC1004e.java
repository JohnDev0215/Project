package b7;

import android.content.Context;
import io.sentry.android.core.AnrV2Integration;
import io.sentry.android.core.SentryAndroidOptions;

/* JADX INFO: renamed from: b7.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1004e implements Runnable {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f12283m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f12284n;
    public final Object o;

    public RunnableC1004e(Context context, SentryAndroidOptions sentryAndroidOptions, io.sentry.transport.d dVar) {
        this.f12284n = context;
        this.o = sentryAndroidOptions;
        dVar.getClass();
        this.f12283m = System.currentTimeMillis() - AnrV2Integration.o;
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00cf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.app.ApplicationExitInfo r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b7.RunnableC1004e.a(android.app.ApplicationExitInfo, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d6 A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b7.RunnableC1004e.run():void");
    }

    public RunnableC1004e(long j3, j jVar, h hVar) {
        this.f12283m = j3;
        this.f12284n = jVar;
        this.o = hVar;
    }
}
