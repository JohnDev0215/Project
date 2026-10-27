package io.sentry;

import io.sentry.android.core.SentryAndroidOptions;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
public abstract class U0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f15965a = new ThreadLocal();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile J f15966b = C1518v0.f16955b;
    public static volatile boolean c = false;
    public static final Charset d = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f15967e = System.currentTimeMillis();

    public static synchronized void a() {
        J jB = b();
        f15966b = C1518v0.f16955b;
        f15965a.remove();
        jB.a(false);
    }

    public static J b() {
        if (c) {
            return f15966b;
        }
        ThreadLocal threadLocal = f15965a;
        J j3 = (J) threadLocal.get();
        if (j3 != null && !(j3 instanceof C1518v0)) {
            return j3;
        }
        J jClone = f15966b.m3clone();
        threadLocal.set(jClone);
        return jClone;
    }

    public static void c(C1524x0 c1524x0, io.sentry.android.core.U u4) {
        F1 f12 = (F1) SentryAndroidOptions.class.getDeclaredConstructor(null).newInstance(null);
        try {
            u4.a(f12);
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Error in the 'OptionsConfiguration.configure' callback.", th);
        }
        synchronized (U0.class) {
            try {
                if (b().isEnabled()) {
                    f12.getLogger().k(EnumC1495p1.WARNING, "Sentry has been already initialized. Previous configuration will be overwritten.", new Object[0]);
                }
                if (d(f12)) {
                    try {
                        f12.getExecutorService().submit(new S0(f12, 0));
                    } catch (RejectedExecutionException e10) {
                        f12.getLogger().s(EnumC1495p1.DEBUG, "Failed to call the executor. Lazy fields will not be loaded. Did you call Sentry.close()?", e10);
                    }
                    f12.getLogger().k(EnumC1495p1.INFO, "GlobalHubMode: '%s'", String.valueOf(true));
                    c = true;
                    J jB = b();
                    if (f12.getDsn() == null || f12.getDsn().isEmpty()) {
                        throw new IllegalArgumentException("Hub requires a DSN to be instantiated. Considering using the NoOpHub if no DSN is available.");
                    }
                    f15966b = new D(f12, new C1489n1(f12.getLogger(), new X1(f12, new B8.t(f12), new N0(f12))));
                    f15965a.set(f15966b);
                    jB.a(true);
                    if (f12.getExecutorService().f()) {
                        f12.setExecutorService(new e7.f(13, (byte) 0));
                    }
                    Iterator<Z> it = f12.getIntegrations().iterator();
                    while (it.hasNext()) {
                        it.next().k(f12);
                    }
                    try {
                        f12.getExecutorService().submit(new S0(f12, 2));
                    } catch (Throwable th2) {
                        f12.getLogger().s(EnumC1495p1.DEBUG, "Failed to notify options observers.", th2);
                    }
                    try {
                        f12.getExecutorService().submit(new F0(f12));
                    } catch (Throwable th3) {
                        f12.getLogger().s(EnumC1495p1.DEBUG, "Failed to finalize previous session.", th3);
                    }
                    try {
                        f12.getExecutorService().submit(new S0(f12, 1));
                    } catch (Throwable th4) {
                        f12.getLogger().s(EnumC1495p1.ERROR, "Failed to call the executor. App start profiling config will not be changed. Did you call Sentry.close()?", th4);
                    }
                }
            } catch (Throwable th5) {
                throw th5;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x024d A[LOOP:4: B:98:0x0247->B:100:0x024d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0271 A[LOOP:5: B:102:0x026b->B:104:0x0271, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0137 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0127 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x017c A[LOOP:0: B:63:0x0176->B:65:0x017c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01d2 A[LOOP:1: B:73:0x01cc->B:75:0x01d2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01ee A[LOOP:2: B:77:0x01e8->B:79:0x01ee, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0216  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean d(io.sentry.F1 r12) {
        /*
            Method dump skipped, instruction units count: 1276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.U0.d(io.sentry.F1):boolean");
    }
}
