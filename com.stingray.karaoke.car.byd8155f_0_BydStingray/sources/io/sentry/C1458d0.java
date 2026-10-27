package io.sentry;

import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import e2.C1208p;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.android.core.C1444u;
import io.sentry.android.core.SentryAndroidOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import k3.C1628b0;
import k3.C1672v;
import k3.C1678y;
import k3.C1679y0;
import k3.InterfaceC1655m;
import k3.InterfaceC1656m0;
import k3.InterfaceC1668t;
import l3.C1740E;
import l5.C1778f;
import o2.C2024a;
import u2.C2492C;
import u2.C2514q;
import u2.C2521y;
import u2.InterfaceC2497H;

/* JADX INFO: renamed from: io.sentry.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1458d0 implements InterfaceC1464f0, O0, io.sentry.instrumentation.file.a, M0, k3.X, h2.l, InterfaceC1656m0, k3.P0, h2.g, k3.l1, h2.m, B2.u, w2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16519m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16520n;

    public /* synthetic */ C1458d0(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f16519m = obj;
        this.f16520n = obj2;
    }

    @Override // k3.l1
    public Object a(k3.I0 i02, C1679y0 c1679y0, int i7) {
        switch (this.l) {
            case 19:
                return i02.l(c1679y0, (k3.y1) this.f16519m, (Bundle) this.f16520n);
            case 20:
            default:
                return i02.h() ? l9.l.T(new k3.C1(-100)) : AbstractC1343A.S((h5.y) ((k3.l1) this.f16519m).a(i02, c1679y0, i7), new Y0(i02, c1679y0, (k3.k1) this.f16520n, 6));
            case 21:
                return i02.h() ? l9.l.T(new k3.C1(-100)) : AbstractC1343A.S((h5.y) ((k3.l1) this.f16519m).a(i02, c1679y0, i7), new Y0(i02, c1679y0, (k3.X0) this.f16520n, 5));
        }
    }

    @Override // h2.g
    public void accept(Object obj) {
        switch (this.l) {
            case 2:
                io.sentry.android.core.cache.a aVar = (io.sentry.android.core.cache.a) this.f16519m;
                aVar.getClass();
                Long lValueOf = Long.valueOf(((C1444u) obj).o);
                K logger = ((SentryAndroidOptions) this.f16520n).getLogger();
                EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
                logger.k(enumC1495p1, "Writing last reported ANR marker with timestamp %d", lValueOf);
                F1 f12 = aVar.l;
                String cacheDirPath = f12.getCacheDirPath();
                if (cacheDirPath == null) {
                    f12.getLogger().k(enumC1495p1, "Cache dir path is null, the ANR marker will not be written", new Object[0]);
                    return;
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(cacheDirPath, "last_anr_report"));
                    try {
                        fileOutputStream.write(String.valueOf(lValueOf).getBytes(io.sentry.cache.c.f16489r));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        return;
                    } finally {
                        try {
                            break;
                        } catch (Throwable th) {
                        }
                    }
                } catch (Throwable th2) {
                    f12.getLogger().s(EnumC1495p1.ERROR, "Error writing the ANR marker to the disk", th2);
                    return;
                }
            case 18:
                k3.u1 u1Var = (k3.u1) obj;
                k3.m1 m1Var = (k3.m1) this.f16519m;
                m1Var.getClass();
                e2.l0 l0VarB = (e2.l0) this.f16520n;
                d5.K k10 = l0VarB.f14194A;
                if (!k10.isEmpty()) {
                    e2.k0 k0VarC = l0VarB.a().c();
                    d5.s0 it = k10.values().iterator();
                    while (it.hasNext()) {
                        e2.h0 h0Var = (e2.h0) it.next();
                        e2.g0 g0Var = (e2.g0) m1Var.f18016g.f13412s.get(h0Var.f14118a.f14105b);
                        if (g0Var == null || h0Var.f14118a.f14104a != g0Var.f14104a) {
                            k0VarC.a(h0Var);
                        } else {
                            k0VarC.a(new e2.h0(g0Var, h0Var.f14119b));
                        }
                    }
                    l0VarB = k0VarC.b();
                }
                u1Var.f0(l0VarB);
                return;
            case 20:
                k3.I0 i02 = (k3.I0) ((k3.m1) this.f16519m).c.get();
                if (i02 == null || i02.h()) {
                    return;
                }
                i02.f((C1679y0) this.f16520n, false);
                return;
            default:
                B5.u uVar = (B5.u) this.f16519m;
                ((InterfaceC2497H) obj).s(uVar.f735m, (C2492C) uVar.f736n, (C2521y) this.f16520n);
                return;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        switch (this.l) {
            case 11:
                ((e2.U) obj).L(((Integer) this.f16520n).intValue(), (e2.F) this.f16519m);
                break;
            case 14:
                ((e2.U) obj).L(((Integer) this.f16520n).intValue(), ((k3.q1) ((H0.b) this.f16519m).f2765m).s());
                break;
            default:
                o2.j jVar = (o2.j) obj;
                jVar.getClass();
                C2024a c2024a = (C2024a) this.f16519m;
                C2492C c2492c = c2024a.d;
                if (c2492c != null) {
                    C2521y c2521y = (C2521y) this.f16520n;
                    C1208p c1208p = c2521y.c;
                    c1208p.getClass();
                    c2492c.getClass();
                    C1778f c1778f = new C1778f(c1208p, 2, jVar.f20426b.c(c2024a.f20397b, c2492c));
                    int i7 = c2521y.f22917b;
                    if (i7 != 0) {
                        if (i7 == 1) {
                            jVar.f20436p = c1778f;
                            break;
                        } else if (i7 != 2) {
                            if (i7 == 3) {
                                jVar.f20437q = c1778f;
                                break;
                            }
                        }
                    }
                    jVar.o = c1778f;
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x04ce  */
    /* JADX WARN: Type inference failed for: r2v41 */
    /* JADX WARN: Type inference failed for: r2v66, types: [e2.p, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v67 */
    /* JADX WARN: Type inference failed for: r2v68 */
    @Override // h2.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.lang.Object r26, e2.C1206n r27) {
        /*
            Method dump skipped, instruction units count: 1748
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.C1458d0.c(java.lang.Object, e2.n):void");
    }

    @Override // io.sentry.instrumentation.file.a
    public Object call() throws IOException {
        switch (this.l) {
            case 4:
                return Integer.valueOf(((io.sentry.instrumentation.file.e) this.f16519m).l.read((byte[]) this.f16520n));
            case 5:
                int i7 = ((io.sentry.instrumentation.file.e) this.f16519m).l.read();
                ((AtomicInteger) this.f16520n).set(i7);
                return Integer.valueOf(i7 != -1 ? 1 : 0);
            default:
                FileOutputStream fileOutputStream = ((io.sentry.instrumentation.file.g) this.f16519m).l;
                byte[] bArr = (byte[]) this.f16520n;
                fileOutputStream.write(bArr);
                return Integer.valueOf(bArr.length);
        }
    }

    @Override // k3.P0
    public void d(C1679y0 c1679y0) {
        k3.Q0 q02 = (k3.Q0) this.f16519m;
        q02.getClass();
        String str = ((C1740E) this.f16520n).l;
        if (TextUtils.isEmpty(str)) {
            AbstractC1346c.z("MediaSessionLegacyStub", "onRemoveQueueItem(): Media ID shouldn't be null");
            return;
        }
        k3.u1 u1Var = q02.f17843f.f17811t;
        if (!u1Var.R0(17)) {
            AbstractC1346c.z("MediaSessionLegacyStub", "Can't remove item by ID without COMMAND_GET_TIMELINE being available");
            return;
        }
        e2.f0 f0VarT0 = u1Var.t0();
        e2.e0 e0Var = new e2.e0();
        for (int i7 = 0; i7 < f0VarT0.o(); i7++) {
            if (TextUtils.equals(f0VarT0.m(i7, e0Var, 0L).c.f13878a, str)) {
                u1Var.v0(i7);
                return;
            }
        }
    }

    @Override // k3.InterfaceC1656m0
    public void e(C1628b0 c1628b0) {
        k3.o1 o1Var;
        boolean z6;
        boolean z10;
        Object obj = this.f16519m;
        Object obj2 = this.f16520n;
        switch (this.l) {
            case 15:
                if (c1628b0.v()) {
                    k3.q1 q1Var = c1628b0.f17902E;
                    k3.o1 o1Var2 = (k3.o1) obj2;
                    k3.q1 q1Var2 = (k3.q1) obj;
                    if (q1Var != null && (o1Var = c1628b0.f17903F) != null) {
                        Pair pairH = k3.n1.h(q1Var, o1Var, q1Var2, o1Var2, c1628b0.f17921v);
                        q1Var2 = (k3.q1) pairH.first;
                        o1Var2 = (k3.o1) pairH.second;
                    }
                    c1628b0.f17902E = null;
                    c1628b0.f17903F = null;
                    if (!c1628b0.f17912k.isEmpty()) {
                        c1628b0.f17902E = q1Var2;
                        c1628b0.f17903F = o1Var2;
                    } else {
                        k3.q1 q1Var3 = c1628b0.o;
                        k3.q1 q1Var4 = (k3.q1) k3.n1.h(q1Var3, k3.o1.c, q1Var2, o1Var2, c1628b0.f17921v).first;
                        c1628b0.o = q1Var4;
                        Integer numValueOf = (q1Var3.d.equals(q1Var2.d) && q1Var3.f18073e.equals(q1Var2.f18073e)) ? null : Integer.valueOf(q1Var4.f18074f);
                        e2.F fS = q1Var3.s();
                        e2.F fS2 = q1Var4.s();
                        int i7 = AbstractC1343A.f15195a;
                        Integer numValueOf2 = !Objects.equals(fS, fS2) ? Integer.valueOf(q1Var4.f18072b) : null;
                        Integer numValueOf3 = !q1Var3.f18078j.equals(q1Var4.f18078j) ? Integer.valueOf(q1Var4.f18079k) : null;
                        int i10 = q1Var3.f18087u;
                        int i11 = q1Var4.f18087u;
                        c1628b0.n1(q1Var3, q1Var4, numValueOf3, (i10 == i11 && q1Var3.f18086t == q1Var4.f18086t) ? null : Integer.valueOf(i11), numValueOf, numValueOf2);
                    }
                    break;
                }
                break;
            default:
                if (c1628b0.v()) {
                    e2.S s6 = c1628b0.f17919t;
                    int i12 = AbstractC1343A.f15195a;
                    e2.S s10 = (e2.S) obj2;
                    boolean zEquals = Objects.equals(s6, s10);
                    k3.z1 z1Var = (k3.z1) obj;
                    boolean zEquals2 = Objects.equals(c1628b0.f17918s, z1Var);
                    if (!zEquals || !zEquals2) {
                        c1628b0.f17918s = z1Var;
                        if (zEquals) {
                            z6 = false;
                        } else {
                            c1628b0.f17919t = s10;
                            e2.S s11 = c1628b0.f17921v;
                            e2.S sX0 = C1628b0.X0(s10, c1628b0.f17920u);
                            c1628b0.f17921v = sX0;
                            z6 = !sX0.equals(s11);
                        }
                        if (!zEquals2 || z6) {
                            d5.d0 d0Var = c1628b0.f17917r;
                            d5.d0 d0VarR1 = C1628b0.r1(c1628b0.f17916q, c1628b0.f17915p, z1Var, c1628b0.f17921v);
                            c1628b0.f17917r = d0VarR1;
                            z10 = !d0VarR1.equals(d0Var);
                        } else {
                            z10 = false;
                        }
                        if (z6) {
                            c1628b0.f17910i.f(13, new C1678y(c1628b0, 18));
                        }
                        C1672v c1672v = c1628b0.f17904a;
                        if (!zEquals2) {
                            c1672v.getClass();
                            AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
                            c1672v.d.s();
                        }
                        if (z10) {
                            c1672v.getClass();
                            AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
                            InterfaceC1668t interfaceC1668t = c1672v.d;
                            interfaceC1668t.getClass();
                            interfaceC1668t.n();
                        }
                    }
                    break;
                }
                break;
        }
    }

    @Override // B2.u
    public B2.r[] f() {
        C2514q c2514q = (C2514q) this.f16519m;
        io.sentry.hints.i iVar = c2514q.c;
        C1208p c1208p = (C1208p) this.f16520n;
        return new B2.r[]{iVar.j(c1208p) ? new Y2.h(c2514q.c.c(c1208p), c1208p) : new J2.a(c1208p)};
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    @Override // w2.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public d5.d0 g(int r17, e2.g0 r18, int[] r19) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.C1458d0.g(int, e2.g0, int[]):d5.d0");
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        switch (this.l) {
            case 9:
                C1628b0 c1628b0 = (C1628b0) this.f16519m;
                c1628b0.getClass();
                interfaceC1655m.V(c1628b0.c, i7, ((e2.I) this.f16520n).c());
                break;
            case 10:
                interfaceC1655m.O(((C1628b0) this.f16519m).c, i7, ((e2.l0) this.f16520n).c());
                break;
            case 11:
            default:
                C1628b0 c1628b02 = (C1628b0) this.f16519m;
                c1628b02.getClass();
                interfaceC1655m.g0(c1628b02.c, i7, ((e2.F) this.f16520n).d(true), true);
                break;
            case 12:
                C1628b0 c1628b03 = (C1628b0) this.f16519m;
                c1628b03.getClass();
                e2.P p10 = (e2.P) this.f16520n;
                Bundle bundle = new Bundle();
                bundle.putFloat(e2.P.f13993e, p10.f13995a);
                bundle.putFloat(e2.P.f13994f, p10.f13996b);
                interfaceC1655m.K(c1628b03.c, i7, bundle);
                break;
        }
    }

    @Override // io.sentry.O0
    public void i(N0 n02) {
        switch (this.l) {
            case 1:
                ((AtomicReference) this.f16519m).set(n02.f15894b);
                ((AtomicReference) this.f16520n).set(n02.f15906r);
                return;
            case 2:
            default:
                ((e7.f) this.f16519m).f14480m = n02.f(new C1458d0((F1) this.f16520n, 8, n02));
                return;
            case 3:
                io.sentry.android.core.internal.gestures.f fVar = (io.sentry.android.core.internal.gestures.f) this.f16519m;
                V v10 = (V) this.f16520n;
                fVar.getClass();
                synchronized (n02.f15902m) {
                    if (n02.f15893a == null) {
                        n02.e(v10);
                    } else {
                        fVar.f16184n.getLogger().k(EnumC1495p1.DEBUG, "Transaction '%s' won't be bound to the Scope since there's one already in there.", v10.getName());
                    }
                    break;
                }
                return;
        }
    }

    @Override // io.sentry.M0
    public void j(h3.y yVar) {
        A2.c cVar = (A2.c) yVar.o;
        F1 f12 = (F1) this.f16519m;
        if (cVar == null) {
            cVar = new A2.c(f12.getLogger());
            yVar.o = cVar;
        }
        if (cVar.f311b) {
            N0 n02 = (N0) this.f16520n;
            h3.y yVar2 = n02.f15905q;
            io.sentry.protocol.E e10 = n02.f15894b;
            io.sentry.protocol.t tVar = n02.f15906r;
            cVar.o("sentry-trace_id", ((io.sentry.protocol.t) yVar2.f15499m).toString());
            cVar.o("sentry-public_key", f12.retrieveParsedDsn().f16818b);
            cVar.o("sentry-release", f12.getRelease());
            cVar.o("sentry-environment", f12.getEnvironment());
            if (!io.sentry.protocol.t.f16774m.equals(tVar)) {
                cVar.o("sentry-replay_id", tVar.toString());
            }
            cVar.o("sentry-user_segment", e10 != null ? A2.c.m(e10) : null);
            cVar.o("sentry-transaction", null);
            cVar.o("sentry-sample_rate", null);
            cVar.o("sentry-sampled", null);
            cVar.f311b = false;
        }
    }

    @Override // io.sentry.InterfaceC1464f0
    public Object k() {
        C1482l0 c1482l0 = (C1482l0) this.f16520n;
        ((A7.D) this.f16519m).getClass();
        try {
            try {
                return Integer.valueOf(((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).F());
            } catch (Exception unused) {
                return Double.valueOf(((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).C());
            }
        } catch (Exception unused2) {
            return Long.valueOf(((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).G());
        }
    }
}
