package io.sentry.android.core;

import io.sentry.A0;
import io.sentry.AbstractC1453b1;
import io.sentry.C1510s1;
import io.sentry.C1530z0;
import java.util.Date;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class a0 implements io.sentry.O, io.sentry.android.core.internal.util.k {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f16140h = TimeUnit.SECONDS.toNanos(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C1510s1 f16141i = new C1510s1(new Date(0), 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f16142a;
    public final io.sentry.android.core.internal.util.l c;
    public volatile String d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f16143b = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TreeSet f16144e = new TreeSet(new C1.b(8));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ConcurrentSkipListSet f16145f = new ConcurrentSkipListSet();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f16146g = 16666666;

    public a0(SentryAndroidOptions sentryAndroidOptions, io.sentry.android.core.internal.util.l lVar) {
        this.c = lVar;
        this.f16142a = sentryAndroidOptions.isEnablePerformanceV2() && sentryAndroidOptions.isEnableFramesTracking();
    }

    public static long g(AbstractC1453b1 abstractC1453b1) {
        if (abstractC1453b1 instanceof C1510s1) {
            return abstractC1453b1.b(f16141i);
        }
        return System.nanoTime() - ((System.currentTimeMillis() * 1000000) - abstractC1453b1.d());
    }

    @Override // io.sentry.android.core.internal.util.k
    public final void c(long j3, long j10, long j11, long j12, boolean z6, boolean z10, float f10) {
        ConcurrentSkipListSet concurrentSkipListSet = this.f16145f;
        if (concurrentSkipListSet.size() > 3600) {
            return;
        }
        long j13 = (long) (f16140h / ((double) f10));
        this.f16146g = j13;
        if (z6 || z10) {
            concurrentSkipListSet.add(new Z(j3, j10, j11, j12, z6, z10, j13));
        }
    }

    public final void d() {
        synchronized (this.f16143b) {
            try {
                if (this.d != null) {
                    this.c.a(this.d);
                    this.d = null;
                }
                this.f16145f.clear();
                this.f16144e.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01c9 A[Catch: all -> 0x0032, TryCatch #2 {all -> 0x0032, blocks: (B:22:0x0027, B:24:0x002f, B:28:0x0035, B:30:0x003b, B:32:0x003e, B:34:0x0052, B:36:0x0055, B:38:0x005f, B:39:0x0077, B:41:0x007d, B:46:0x0093, B:48:0x0099, B:50:0x00a5, B:77:0x0113, B:53:0x00ae, B:57:0x00b8, B:63:0x00c8, B:67:0x00fa, B:71:0x0103, B:73:0x0107, B:76:0x0110, B:61:0x00c2, B:79:0x0128, B:81:0x0132, B:83:0x0136, B:85:0x013e, B:91:0x014b, B:96:0x015a, B:100:0x0165, B:102:0x0171, B:106:0x017e, B:108:0x0187, B:110:0x0194, B:103:0x0177, B:111:0x0196, B:113:0x01c9, B:114:0x01ed), top: B:135:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(io.sentry.U r29) {
        /*
            Method dump skipped, instruction units count: 549
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.a0.e(io.sentry.U):void");
    }

    public final void f(io.sentry.U u4) {
        String str;
        if (!this.f16142a || (u4 instanceof C1530z0) || (u4 instanceof A0)) {
            return;
        }
        synchronized (this.f16143b) {
            try {
                this.f16144e.add(u4);
                if (this.d == null) {
                    io.sentry.android.core.internal.util.l lVar = this.c;
                    if (lVar.f16235r) {
                        String string = UUID.randomUUID().toString();
                        lVar.f16234q.put(string, this);
                        lVar.c();
                        str = string;
                    } else {
                        str = null;
                    }
                    this.d = str;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
