package io.sentry.android.core;

import A.C0050y0;
import android.content.Context;
import android.content.pm.PackageInfo;
import h8.AbstractC1387d;
import io.sentry.C1507r1;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.H1;
import io.sentry.InterfaceC1511t;
import io.sentry.X0;
import io.sentry.protocol.C1496a;
import io.sentry.protocol.C1498c;
import io.sentry.protocol.C1501f;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class D implements InterfaceC1511t {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B f16045m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SentryAndroidOptions f16046n;
    public final Future o;

    public D(Context context, B b10, final SentryAndroidOptions sentryAndroidOptions) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
        this.f16045m = b10;
        h6.e.q(sentryAndroidOptions, "The options object is required.");
        this.f16046n = sentryAndroidOptions;
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        this.o = executorServiceNewSingleThreadExecutor.submit(new Callable() { // from class: io.sentry.android.core.C
            @Override // java.util.concurrent.Callable
            public final Object call() {
                D d = this.f16043a;
                SentryAndroidOptions sentryAndroidOptions2 = sentryAndroidOptions;
                Context context2 = d.l;
                if (F.f16050h == null) {
                    synchronized (F.class) {
                        try {
                            if (F.f16050h == null) {
                                Context applicationContext2 = context2.getApplicationContext();
                                if (applicationContext2 != null) {
                                    context2 = applicationContext2;
                                }
                                F.f16050h = new F(context2, sentryAndroidOptions2);
                            }
                        } finally {
                        }
                    }
                }
                return F.f16050h;
            }
        });
        executorServiceNewSingleThreadExecutor.shutdown();
    }

    public final void a(X0 x02, C1523x c1523x) {
        Boolean bool;
        C1496a c1496a = (C1496a) x02.f15978m.f(C1496a.class, "app");
        if (c1496a == null) {
            c1496a = new C1496a();
        }
        SentryAndroidOptions sentryAndroidOptions = this.f16046n;
        io.sentry.K logger = sentryAndroidOptions.getLogger();
        Context context = this.l;
        c1496a.f16677p = h6.i.j(context, logger);
        io.sentry.android.core.performance.f fVarA = io.sentry.android.core.performance.e.b().a(sentryAndroidOptions);
        if (fVarA.b()) {
            c1496a.f16675m = (fVarA.b() ? new C1507r1(fVarA.f16287m * 1000000) : null) != null ? AbstractC1985B.g(Double.valueOf(r4.l / 1000000.0d).longValue()) : null;
        }
        if (!AbstractC1387d.i(c1523x) && c1496a.f16683v == null && (bool = A.f16013b.f16014a) != null) {
            c1496a.f16683v = Boolean.valueOf(!bool.booleanValue());
        }
        io.sentry.K logger2 = sentryAndroidOptions.getLogger();
        B b10 = this.f16045m;
        PackageInfo packageInfoO = h6.i.o(context, 4096, logger2, b10);
        if (packageInfoO != null) {
            b10.getClass();
            String string = Long.toString(packageInfoO.getLongVersionCode());
            if (x02.f15987w == null) {
                x02.f15987w = string;
            }
            c1496a.l = packageInfoO.packageName;
            c1496a.f16678q = packageInfoO.versionName;
            c1496a.f16679r = Long.toString(packageInfoO.getLongVersionCode());
            HashMap map = new HashMap();
            String[] strArr = packageInfoO.requestedPermissions;
            int[] iArr = packageInfoO.requestedPermissionsFlags;
            if (strArr != null && strArr.length > 0 && iArr != null && iArr.length > 0) {
                for (int i7 = 0; i7 < strArr.length; i7++) {
                    String str = strArr[i7];
                    map.put(str.substring(str.lastIndexOf(46) + 1), (iArr[i7] & 2) == 2 ? "granted" : "not_granted");
                }
            }
            c1496a.f16680s = map;
        }
        x02.f15978m.c(c1496a);
    }

    @Override // io.sentry.InterfaceC1511t
    public final H1 b(H1 h12, C1523x c1523x) {
        boolean zD = d(h12, c1523x);
        if (zD) {
            a(h12, c1523x);
        }
        c(h12, false, zD);
        return h12;
    }

    public final void c(X0 x02, boolean z6, boolean z10) {
        io.sentry.protocol.E e10 = x02.f15984t;
        if (e10 == null) {
            e10 = new io.sentry.protocol.E();
            x02.f15984t = e10;
        }
        if (e10.f16656m == null) {
            e10.f16656m = K.a(this.l);
        }
        if (e10.f16658p == null) {
            e10.f16658p = "{{auto}}";
        }
        C1498c c1498c = x02.f15978m;
        C1501f c1501f = (C1501f) c1498c.f(C1501f.class, "device");
        Future future = this.o;
        SentryAndroidOptions sentryAndroidOptions = this.f16046n;
        if (c1501f == null) {
            try {
                c1498c.put("device", ((F) future.get()).a(z6, z10));
            } catch (Throwable th) {
                sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Failed to retrieve device info", th);
            }
            io.sentry.protocol.m mVar = (io.sentry.protocol.m) c1498c.f(io.sentry.protocol.m.class, "os");
            try {
                c1498c.put("os", ((F) future.get()).f16054f);
            } catch (Throwable th2) {
                sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Failed to retrieve os system", th2);
            }
            if (mVar != null) {
                String str = mVar.l;
                c1498c.put((str == null || str.isEmpty()) ? "os_1" : "os_" + str.trim().toLowerCase(Locale.ROOT), mVar);
            }
        }
        try {
            C0050y0 c0050y0 = ((F) future.get()).f16053e;
            if (c0050y0 != null) {
                HashMap map = new HashMap();
                map.put("isSideLoaded", String.valueOf(c0050y0.f300m));
                String str2 = (String) c0050y0.f301n;
                if (str2 != null) {
                    map.put("installerStore", str2);
                }
                for (Map.Entry entry : map.entrySet()) {
                    x02.a((String) entry.getKey(), (String) entry.getValue());
                }
            }
        } catch (Throwable th3) {
            sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Error getting side loaded info.", th3);
        }
    }

    public final boolean d(X0 x02, C1523x c1523x) {
        if (AbstractC1387d.s(c1523x)) {
            return true;
        }
        this.f16046n.getLogger().k(EnumC1495p1.DEBUG, "Event was cached so not applying data relevant to the current app execution/version: %s", x02.l);
        return false;
    }

    @Override // io.sentry.InterfaceC1511t
    public final io.sentry.protocol.A g(io.sentry.protocol.A a10, C1523x c1523x) {
        boolean zD = d(a10, c1523x);
        if (zD) {
            a(a10, c1523x);
        }
        c(a10, false, zD);
        return a10;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004b  */
    @Override // io.sentry.InterfaceC1511t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final io.sentry.C1480k1 i(io.sentry.C1480k1 r10, io.sentry.C1523x r11) {
        /*
            r9 = this;
            boolean r0 = r9.d(r10, r11)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L63
            r9.a(r10, r11)
            A7.D r3 = r10.f16599D
            if (r3 == 0) goto L12
            java.util.ArrayList r3 = r3.l
            goto L13
        L12:
            r3 = r1
        L13:
            if (r3 == 0) goto L63
            boolean r11 = h8.AbstractC1387d.i(r11)
            A7.D r3 = r10.f16599D
            if (r3 == 0) goto L20
            java.util.ArrayList r3 = r3.l
            goto L21
        L20:
            r3 = r1
        L21:
            java.util.Iterator r3 = r3.iterator()
        L25:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L63
            java.lang.Object r4 = r3.next()
            io.sentry.protocol.z r4 = (io.sentry.protocol.z) r4
            java.lang.Long r5 = r4.l
            if (r5 == 0) goto L4b
            long r5 = r5.longValue()
            android.os.Looper r7 = android.os.Looper.getMainLooper()
            java.lang.Thread r7 = r7.getThread()
            long r7 = r7.getId()
            int r5 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r5 != 0) goto L4b
            r5 = r2
            goto L4c
        L4b:
            r5 = 0
        L4c:
            java.lang.Boolean r6 = r4.f16811q
            if (r6 != 0) goto L56
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r5)
            r4.f16811q = r6
        L56:
            if (r11 != 0) goto L25
            java.lang.Boolean r6 = r4.f16813s
            if (r6 != 0) goto L25
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r4.f16813s = r5
            goto L25
        L63:
            r9.c(r10, r2, r0)
            A7.D r9 = r10.f16600E
            if (r9 != 0) goto L6b
            goto L6d
        L6b:
            java.util.ArrayList r1 = r9.l
        L6d:
            if (r1 == 0) goto Laf
            int r9 = r1.size()
            if (r9 <= r2) goto Laf
            int r9 = r1.size()
            int r9 = r9 - r2
            java.lang.Object r9 = r1.get(r9)
            io.sentry.protocol.s r9 = (io.sentry.protocol.s) r9
            java.lang.String r11 = r9.f16770n
            java.lang.String r0 = "java.lang"
            boolean r11 = r0.equals(r11)
            if (r11 == 0) goto Laf
            io.sentry.protocol.y r9 = r9.f16771p
            if (r9 == 0) goto Laf
            java.util.List r9 = r9.l
            if (r9 == 0) goto Laf
            java.util.Iterator r9 = r9.iterator()
        L96:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto Laf
            java.lang.Object r11 = r9.next()
            io.sentry.protocol.x r11 = (io.sentry.protocol.x) r11
            java.lang.String r11 = r11.f16794n
            java.lang.String r0 = "com.android.internal.os.RuntimeInit$MethodAndArgsCaller"
            boolean r11 = r0.equals(r11)
            if (r11 == 0) goto L96
            java.util.Collections.reverse(r1)
        Laf:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.D.i(io.sentry.k1, io.sentry.x):io.sentry.k1");
    }
}
