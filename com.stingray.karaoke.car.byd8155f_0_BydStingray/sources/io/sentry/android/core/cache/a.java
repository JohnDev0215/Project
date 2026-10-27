package io.sentry.android.core.cache;

import android.os.SystemClock;
import h6.e;
import h8.AbstractC1387d;
import io.sentry.C1458d0;
import io.sentry.C1489n1;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.K;
import io.sentry.android.core.C1444u;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.internal.util.b;
import io.sentry.android.core.performance.f;
import io.sentry.cache.c;
import io.sentry.e2;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class a extends c {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ int f16156t = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final b f16157s;

    /* JADX WARN: Illegal instructions before constructor call */
    public a(SentryAndroidOptions sentryAndroidOptions) {
        b bVar = b.f16209a;
        String cacheDirPath = sentryAndroidOptions.getCacheDirPath();
        e.q(cacheDirPath, "cacheDirPath must not be null");
        super(sentryAndroidOptions, cacheDirPath, sentryAndroidOptions.getMaxCacheItems());
        this.f16157s = bVar;
    }

    @Override // io.sentry.cache.c, io.sentry.cache.d
    public final void g(C1489n1 c1489n1, C1523x c1523x) {
        super.g(c1489n1, c1523x);
        F1 f12 = this.l;
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) f12;
        f fVar = io.sentry.android.core.performance.e.b().o;
        if (e2.class.isInstance(AbstractC1387d.g(c1523x)) && fVar.b()) {
            this.f16157s.getClass();
            long jUptimeMillis = SystemClock.uptimeMillis() - fVar.f16288n;
            if (jUptimeMillis <= sentryAndroidOptions.getStartupCrashDurationThresholdMillis()) {
                K logger = sentryAndroidOptions.getLogger();
                EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
                logger.k(enumC1495p1, "Startup Crash detected %d milliseconds after SDK init. Writing a startup crash marker file to disk.", Long.valueOf(jUptimeMillis));
                String outboxPath = f12.getOutboxPath();
                if (outboxPath == null) {
                    f12.getLogger().k(enumC1495p1, "Outbox path is null, the startup crash marker file will not be written", new Object[0]);
                } else {
                    try {
                        new File(outboxPath, "startup_crash").createNewFile();
                    } catch (Throwable th) {
                        f12.getLogger().s(EnumC1495p1.ERROR, "Error writing the startup crash marker file to the disk", th);
                    }
                }
            }
        }
        C1458d0 c1458d0 = new C1458d0(this, 2, sentryAndroidOptions);
        Object objG = AbstractC1387d.g(c1523x);
        if (!C1444u.class.isInstance(AbstractC1387d.g(c1523x)) || objG == null) {
            return;
        }
        c1458d0.accept(objG);
    }
}
