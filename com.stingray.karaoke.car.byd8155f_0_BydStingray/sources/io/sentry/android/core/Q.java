package io.sentry.android.core;

import android.os.Looper;
import io.sentry.C1480k1;
import io.sentry.C1523x;
import io.sentry.EnumC1494p0;
import io.sentry.InterfaceC1511t;
import io.sentry.T1;
import io.sentry.V1;
import io.sentry.W1;
import io.sentry.protocol.C1496a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class Q implements InterfaceC1511t {
    public boolean l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final N0.q f16091m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SentryAndroidOptions f16092n;

    public Q(SentryAndroidOptions sentryAndroidOptions, N0.q qVar) {
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16092n = sentryAndroidOptions;
        this.f16091m = qVar;
    }

    public static void a(io.sentry.android.core.performance.e eVar, io.sentry.protocol.A a10) {
        T1 t1B;
        V1 v12;
        if (eVar.l == io.sentry.android.core.performance.d.COLD && (t1B = a10.f15978m.b()) != null) {
            ArrayList arrayList = a10.f16649D;
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    v12 = null;
                    break;
                }
                io.sentry.protocol.w wVar = (io.sentry.protocol.w) it.next();
                if (wVar.f16782q.contentEquals("app.start.cold")) {
                    v12 = wVar.o;
                    break;
                }
            }
            long j3 = io.sentry.android.core.performance.e.f16275x;
            io.sentry.android.core.performance.f fVar = eVar.f16278n;
            boolean zB = fVar.b();
            io.sentry.protocol.t tVar = t1B.l;
            if (zB && Math.abs(j3 - fVar.f16288n) <= 10000) {
                io.sentry.android.core.performance.f fVar2 = new io.sentry.android.core.performance.f();
                fVar2.d(fVar.f16288n);
                fVar2.f16287m = fVar.f16287m;
                fVar2.o = j3;
                fVar2.l = "Process Initialization";
                arrayList.add(e(fVar2, v12, tVar, "process.load"));
            }
            ArrayList arrayList2 = new ArrayList(eVar.f16280q.values());
            Collections.sort(arrayList2);
            if (!arrayList2.isEmpty()) {
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    arrayList.add(e((io.sentry.android.core.performance.f) it2.next(), v12, tVar, "contentprovider.load"));
                }
            }
            io.sentry.android.core.performance.f fVar3 = eVar.f16279p;
            if (fVar3.c()) {
                arrayList.add(e(fVar3, v12, tVar, "application.load"));
            }
            ArrayList<io.sentry.android.core.performance.b> arrayList3 = new ArrayList(eVar.f16281r);
            Collections.sort(arrayList3);
            if (arrayList3.isEmpty()) {
                return;
            }
            for (io.sentry.android.core.performance.b bVar : arrayList3) {
                if (bVar.l.b()) {
                    io.sentry.android.core.performance.f fVar4 = bVar.l;
                    if (fVar4.c()) {
                        arrayList.add(e(fVar4, v12, tVar, "activity.load"));
                    }
                }
                io.sentry.android.core.performance.f fVar5 = bVar.f16272m;
                if (fVar5.b() && fVar5.c()) {
                    arrayList.add(e(fVar5, v12, tVar, "activity.load"));
                }
            }
        }
    }

    public static boolean c(io.sentry.protocol.A a10) {
        for (io.sentry.protocol.w wVar : a10.f16649D) {
            if (wVar.f16782q.contentEquals("app.start.cold") || wVar.f16782q.contentEquals("app.start.warm")) {
                return true;
            }
        }
        T1 t1B = a10.f15978m.b();
        if (t1B != null) {
            String str = t1B.f15959p;
            if (str.equals("app.start.cold") || str.equals("app.start.warm")) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void d(io.sentry.protocol.A r11) {
        /*
            java.util.ArrayList r11 = r11.f16649D
            java.util.Iterator r0 = r11.iterator()
            r1 = 0
            r2 = r1
        L8:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L2f
            java.lang.Object r3 = r0.next()
            io.sentry.protocol.w r3 = (io.sentry.protocol.w) r3
            java.lang.String r4 = r3.f16782q
            java.lang.String r5 = "ui.load.initial_display"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L20
            r1 = r3
            goto L2b
        L20:
            java.lang.String r4 = "ui.load.full_display"
            java.lang.String r5 = r3.f16782q
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L2b
            r2 = r3
        L2b:
            if (r1 == 0) goto L8
            if (r2 == 0) goto L8
        L2f:
            if (r1 != 0) goto L34
            if (r2 != 0) goto L34
            return
        L34:
            java.util.Iterator r11 = r11.iterator()
        L38:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto Lc6
            java.lang.Object r0 = r11.next()
            io.sentry.protocol.w r0 = (io.sentry.protocol.w) r0
            if (r0 == r1) goto L38
            if (r0 != r2) goto L49
            goto L38
        L49:
            java.util.Map r3 = r0.f16787v
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L62
            java.lang.String r6 = "thread.name"
            java.lang.Object r3 = r3.get(r6)
            if (r3 == 0) goto L62
            java.lang.String r6 = "main"
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L60
            goto L62
        L60:
            r3 = r4
            goto L63
        L62:
            r3 = r5
        L63:
            java.lang.Double r6 = r0.l
            if (r1 == 0) goto L85
            double r7 = r6.doubleValue()
            java.lang.Double r9 = r1.l
            double r9 = r9.doubleValue()
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 < 0) goto L85
            java.lang.Double r9 = r1.f16779m
            if (r9 == 0) goto L81
            double r9 = r9.doubleValue()
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 > 0) goto L85
        L81:
            if (r3 == 0) goto L85
            r3 = r5
            goto L86
        L85:
            r3 = r4
        L86:
            if (r2 == 0) goto La3
            double r6 = r6.doubleValue()
            java.lang.Double r8 = r2.l
            double r8 = r8.doubleValue()
            int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r8 < 0) goto La3
            java.lang.Double r8 = r2.f16779m
            if (r8 == 0) goto La2
            double r8 = r8.doubleValue()
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 > 0) goto La3
        La2:
            r4 = r5
        La3:
            if (r3 != 0) goto La7
            if (r4 == 0) goto L38
        La7:
            java.util.Map r5 = r0.f16787v
            if (r5 != 0) goto Lb2
            java.util.concurrent.ConcurrentHashMap r5 = new java.util.concurrent.ConcurrentHashMap
            r5.<init>()
            r0.f16787v = r5
        Lb2:
            if (r3 == 0) goto Lbb
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.String r3 = "ui.contributes_to_ttid"
            r5.put(r3, r0)
        Lbb:
            if (r4 == 0) goto L38
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.String r3 = "ui.contributes_to_ttfd"
            r5.put(r3, r0)
            goto L38
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.Q.d(io.sentry.protocol.A):void");
    }

    public static io.sentry.protocol.w e(io.sentry.android.core.performance.f fVar, V1 v12, io.sentry.protocol.t tVar, String str) {
        HashMap map = new HashMap(2);
        map.put("thread.id", Long.valueOf(Looper.getMainLooper().getThread().getId()));
        map.put("thread.name", "main");
        Boolean bool = Boolean.TRUE;
        map.put("ui.contributes_to_ttid", bool);
        map.put("ui.contributes_to_ttfd", bool);
        Double dValueOf = Double.valueOf(fVar.f16287m / 1000.0d);
        if (fVar.b()) {
            j = (fVar.c() ? fVar.o - fVar.f16288n : 0L) + fVar.f16287m;
        }
        return new io.sentry.protocol.w(dValueOf, Double.valueOf(j / 1000.0d), tVar, new V1(), v12, str, fVar.l, W1.OK, "auto.ui", new ConcurrentHashMap(), new ConcurrentHashMap(), null, map);
    }

    @Override // io.sentry.InterfaceC1511t
    public final synchronized io.sentry.protocol.A g(io.sentry.protocol.A a10, C1523x c1523x) {
        Map map;
        try {
            if (!this.f16092n.isTracingEnabled()) {
                return a10;
            }
            if (c(a10)) {
                if (!this.l) {
                    io.sentry.android.core.performance.f fVarA = io.sentry.android.core.performance.e.b().a(this.f16092n);
                    long j3 = fVarA.c() ? fVarA.o - fVarA.f16288n : 0L;
                    if (j3 != 0) {
                        a10.f16650E.put(io.sentry.android.core.performance.e.b().l == io.sentry.android.core.performance.d.COLD ? "app_start_cold" : "app_start_warm", new io.sentry.protocol.i(Float.valueOf(j3), EnumC1494p0.MILLISECOND.apiName()));
                        a(io.sentry.android.core.performance.e.b(), a10);
                        this.l = true;
                    }
                }
                C1496a c1496a = (C1496a) a10.f15978m.f(C1496a.class, "app");
                if (c1496a == null) {
                    c1496a = new C1496a();
                    a10.f15978m.c(c1496a);
                }
                c1496a.f16682u = io.sentry.android.core.performance.e.b().l == io.sentry.android.core.performance.d.COLD ? "cold" : "warm";
            }
            d(a10);
            io.sentry.protocol.t tVar = a10.l;
            T1 t1B = a10.f15978m.b();
            if (tVar != null && t1B != null && t1B.f15959p.contentEquals("ui.load")) {
                N0.q qVar = this.f16091m;
                synchronized (qVar) {
                    if (qVar.H()) {
                        Map map2 = (Map) ((ConcurrentHashMap) qVar.o).get(tVar);
                        ((ConcurrentHashMap) qVar.o).remove(tVar);
                        map = map2;
                    } else {
                        map = null;
                    }
                }
                if (map != null) {
                    a10.f16650E.putAll(map);
                }
            }
            return a10;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // io.sentry.InterfaceC1511t
    public final C1480k1 i(C1480k1 c1480k1, C1523x c1523x) {
        return c1480k1;
    }
}
