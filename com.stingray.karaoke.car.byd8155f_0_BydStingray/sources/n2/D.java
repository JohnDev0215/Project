package n2;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import d5.C1077F;
import d5.C1078G;
import d5.d0;
import e2.C1208p;
import e2.C1217z;
import e2.c0;
import e2.e0;
import e2.f0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.android.core.C1438n;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import u2.C2492C;
import u2.InterfaceC2490A;
import u2.InterfaceC2522z;
import u2.n0;
import v2.C2546d;

/* JADX INFO: loaded from: classes.dex */
public final class D implements Handler.Callback, InterfaceC2522z, V {

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final long f19486h0 = AbstractC1343A.T(10000);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final ArrayList f19487A;
    public final h2.v B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final r f19488C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final K f19489D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final Y4.m f19490E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final C1908h f19491F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final long f19492G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final o2.l f19493H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final o2.e f19494I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final h2.x f19495J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public b0 f19496K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public U f19497L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public h3.x f19498M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f19499N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f19500O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f19501P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f19502Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public long f19503R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f19504S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int f19505T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f19506U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f19507V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public boolean f19508W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public boolean f19509X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public int f19510Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public C f19511Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public long f19512a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public long f19513b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f19514c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f19515d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public C1913m f19516e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public long f19517f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public C1916p f19518g0;
    public final AbstractC1905e[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Set f19519m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AbstractC1905e[] f19520n;
    public final boolean[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final w2.r f19521p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final w2.s f19522q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C1910j f19523r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final x2.d f19524s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final h2.x f19525t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final h2.s f19526u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Looper f19527v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final e0 f19528w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final c0 f19529x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final long f19530y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C1911k f19531z;

    public D(AbstractC1905e[] abstractC1905eArr, w2.r rVar, w2.s sVar, C1910j c1910j, x2.d dVar, int i7, boolean z6, o2.e eVar, b0 b0Var, C1908h c1908h, long j3, boolean z10, Looper looper, h2.v vVar, r rVar2, o2.l lVar) {
        Looper looper2;
        C1916p c1916p = C1916p.f19717a;
        this.f19488C = rVar2;
        this.l = abstractC1905eArr;
        this.f19521p = rVar;
        this.f19522q = sVar;
        this.f19523r = c1910j;
        this.f19524s = dVar;
        this.f19505T = i7;
        this.f19506U = z6;
        this.f19496K = b0Var;
        this.f19491F = c1908h;
        this.f19492G = j3;
        this.f19500O = z10;
        this.B = vVar;
        this.f19493H = lVar;
        this.f19518g0 = c1916p;
        this.f19494I = eVar;
        this.f19517f0 = -9223372036854775807L;
        this.f19503R = -9223372036854775807L;
        this.f19530y = c1910j.f19678g;
        e2.b0 b0Var2 = f0.f14097a;
        U uI = U.i(sVar);
        this.f19497L = uI;
        this.f19498M = new h3.x(uI);
        this.f19520n = new AbstractC1905e[abstractC1905eArr.length];
        this.o = new boolean[abstractC1905eArr.length];
        w2.o oVar = (w2.o) rVar;
        oVar.getClass();
        boolean z11 = false;
        for (int i10 = 0; i10 < abstractC1905eArr.length; i10++) {
            AbstractC1905e abstractC1905e = abstractC1905eArr[i10];
            abstractC1905e.f19638p = i10;
            abstractC1905e.f19639q = lVar;
            abstractC1905e.f19640r = vVar;
            this.f19520n[i10] = abstractC1905e;
            AbstractC1905e abstractC1905e2 = this.f19520n[i10];
            synchronized (abstractC1905e2.l) {
                abstractC1905e2.B = oVar;
            }
        }
        this.f19531z = new C1911k(this, vVar);
        this.f19487A = new ArrayList();
        this.f19519m = Collections.newSetFromMap(new IdentityHashMap());
        this.f19528w = new e0();
        this.f19529x = new c0();
        rVar.f23269a = this;
        rVar.f23270b = dVar;
        this.f19515d0 = true;
        h2.x xVarA = vVar.a(looper, null);
        this.f19495J = xVarA;
        this.f19489D = new K(eVar, xVarA, new G2.a(this, 29));
        this.f19490E = new Y4.m(this, eVar, xVarA, lVar);
        h2.s sVar2 = new h2.s();
        sVar2.d = new Object();
        sVar2.f15238b = null;
        sVar2.c = null;
        sVar2.f15237a = 0;
        this.f19526u = sVar2;
        synchronized (sVar2.d) {
            try {
                if (((Looper) sVar2.f15238b) == null) {
                    if (sVar2.f15237a == 0 && ((HandlerThread) sVar2.c) == null) {
                        z11 = true;
                    }
                    AbstractC1346c.j(z11);
                    HandlerThread handlerThread = new HandlerThread("ExoPlayer:Playback", -16);
                    sVar2.c = handlerThread;
                    handlerThread.start();
                    sVar2.f15238b = ((HandlerThread) sVar2.c).getLooper();
                }
                sVar2.f15237a++;
                looper2 = (Looper) sVar2.f15238b;
            } finally {
            }
        }
        this.f19527v = looper2;
        this.f19525t = vVar.a(looper2, this);
    }

    public static Pair J(f0 f0Var, C c, boolean z6, int i7, boolean z10, e0 e0Var, c0 c0Var) {
        Pair pairI;
        int iK;
        f0 f0Var2 = c.f19484a;
        if (f0Var.p()) {
            return null;
        }
        f0 f0Var3 = f0Var2.p() ? f0Var : f0Var2;
        try {
            pairI = f0Var3.i(e0Var, c0Var, c.f19485b, c.c);
        } catch (IndexOutOfBoundsException unused) {
        }
        if (f0Var.equals(f0Var3)) {
            return pairI;
        }
        if (f0Var.b(pairI.first) != -1) {
            return (f0Var3.g(pairI.first, c0Var).f14062f && f0Var3.m(c0Var.c, e0Var, 0L).f14095n == f0Var3.b(pairI.first)) ? f0Var.i(e0Var, c0Var, f0Var.g(pairI.first, c0Var).c, c.c) : pairI;
        }
        if (z6 && (iK = K(e0Var, c0Var, i7, z10, pairI.first, f0Var3, f0Var)) != -1) {
            return f0Var.i(e0Var, c0Var, iK, -9223372036854775807L);
        }
        return null;
    }

    public static int K(e0 e0Var, c0 c0Var, int i7, boolean z6, Object obj, f0 f0Var, f0 f0Var2) {
        Object obj2 = f0Var.m(f0Var.g(obj, c0Var).c, e0Var, 0L).f14085a;
        for (int i10 = 0; i10 < f0Var2.o(); i10++) {
            if (f0Var2.m(i10, e0Var, 0L).f14085a.equals(obj2)) {
                return i10;
            }
        }
        int iB = f0Var.b(obj);
        int iH = f0Var.h();
        int iD = iB;
        int iB2 = -1;
        for (int i11 = 0; i11 < iH && iB2 == -1; i11++) {
            iD = f0Var.d(iD, c0Var, e0Var, i7, z6);
            if (iD == -1) {
                break;
            }
            iB2 = f0Var2.b(f0Var.l(iD));
        }
        if (iB2 == -1) {
            return -1;
        }
        return f0Var2.f(iB2, c0Var, false).c;
    }

    public static void R(AbstractC1905e abstractC1905e, long j3) {
        abstractC1905e.f19647y = true;
        if (abstractC1905e instanceof C2546d) {
            C2546d c2546d = (C2546d) abstractC1905e;
            AbstractC1346c.j(c2546d.f19647y);
            c2546d.f22999U = j3;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, u2.A, u2.b0] */
    public static boolean q(I i7) {
        if (i7 == null) {
            return false;
        }
        try {
            ?? r12 = i7.f19538a;
            if (i7.f19540e) {
                for (u2.a0 a0Var : i7.c) {
                    if (a0Var != null) {
                        a0Var.r();
                    }
                }
            } else {
                r12.m();
            }
            return (!i7.f19540e ? 0L : r12.g()) != Long.MIN_VALUE;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean r(AbstractC1905e abstractC1905e) {
        return abstractC1905e.f19641s != 0;
    }

    public final void A() {
        this.f19498M.f(1);
        int i7 = 0;
        F(false, false, false, true);
        C1910j c1910j = this.f19523r;
        c1910j.getClass();
        long id = Thread.currentThread().getId();
        long j3 = c1910j.f19680i;
        AbstractC1346c.i("Players that share the same LoadControl must share the same playback thread. See ExoPlayer.Builder.setPlaybackLooper(Looper).", j3 == -1 || j3 == id);
        c1910j.f19680i = id;
        HashMap map = c1910j.f19679h;
        o2.l lVar = this.f19493H;
        if (!map.containsKey(lVar)) {
            map.put(lVar, new C1909i());
        }
        C1909i c1909i = (C1909i) map.get(lVar);
        c1909i.getClass();
        int i10 = c1910j.f19677f;
        if (i10 == -1) {
            i10 = 13107200;
        }
        c1909i.f19673b = i10;
        c1909i.f19672a = false;
        b0(this.f19497L.f19586a.p() ? 4 : 2);
        x2.h hVar = (x2.h) this.f19524s;
        hVar.getClass();
        Y4.m mVar = this.f19490E;
        AbstractC1346c.j(!mVar.f10758a);
        mVar.l = hVar;
        while (true) {
            ArrayList arrayList = (ArrayList) mVar.c;
            if (i7 >= arrayList.size()) {
                mVar.f10758a = true;
                this.f19525t.e(2);
                return;
            } else {
                T t10 = (T) arrayList.get(i7);
                mVar.g(t10);
                ((HashSet) mVar.f10763h).add(t10);
                i7++;
            }
        }
    }

    public final void B() {
        try {
            F(true, false, true, false);
            C();
            C1910j c1910j = this.f19523r;
            if (c1910j.f19679h.remove(this.f19493H) != null) {
                c1910j.d();
            }
            if (c1910j.f19679h.isEmpty()) {
                c1910j.f19680i = -1L;
            }
            b0(1);
            this.f19526u.e();
            synchronized (this) {
                this.f19499N = true;
                notifyAll();
            }
        } catch (Throwable th) {
            this.f19526u.e();
            synchronized (this) {
                this.f19499N = true;
                notifyAll();
                throw th;
            }
        }
    }

    public final void C() {
        for (int i7 = 0; i7 < this.l.length; i7++) {
            AbstractC1905e abstractC1905e = this.f19520n[i7];
            synchronized (abstractC1905e.l) {
                abstractC1905e.B = null;
            }
            AbstractC1905e abstractC1905e2 = this.l[i7];
            AbstractC1346c.j(abstractC1905e2.f19641s == 0);
            abstractC1905e2.t();
        }
    }

    public final void D(int i7, int i10, u2.c0 c0Var) throws Throwable {
        this.f19498M.f(1);
        Y4.m mVar = this.f19490E;
        mVar.getClass();
        AbstractC1346c.c(i7 >= 0 && i7 <= i10 && i10 <= ((ArrayList) mVar.c).size());
        mVar.f10766k = c0Var;
        mVar.i(i7, i10);
        m(mVar.c(), false);
    }

    public final void E() throws C1913m {
        float f10 = this.f19531z.j().f13995a;
        K k10 = this.f19489D;
        I i7 = k10.f19563i;
        I i10 = k10.f19564j;
        w2.s sVar = null;
        I i11 = i7;
        boolean z6 = true;
        while (i11 != null && i11.f19540e) {
            U u4 = this.f19497L;
            w2.s sVarJ = i11.j(f10, u4.f19586a, u4.l);
            w2.s sVar2 = i11 == this.f19489D.f19563i ? sVarJ : sVar;
            w2.s sVar3 = i11.o;
            if (sVar3 != null) {
                int length = sVar3.c.length;
                w2.q[] qVarArr = sVarJ.c;
                if (length == qVarArr.length) {
                    for (int i12 = 0; i12 < qVarArr.length; i12++) {
                        if (sVarJ.a(sVar3, i12)) {
                        }
                    }
                    if (i11 == i10) {
                        z6 = false;
                    }
                    i11 = i11.f19547m;
                    sVar = sVar2;
                }
            }
            if (z6) {
                K k11 = this.f19489D;
                I i13 = k11.f19563i;
                boolean zM = k11.m(i13);
                boolean[] zArr = new boolean[this.l.length];
                sVar2.getClass();
                long jA = i13.a(sVar2, this.f19497L.f19600s, zM, zArr);
                U u10 = this.f19497L;
                boolean z10 = (u10.f19588e == 4 || jA == u10.f19600s) ? false : true;
                U u11 = this.f19497L;
                this.f19497L = p(u11.f19587b, jA, u11.c, u11.d, z10, 5);
                if (z10) {
                    H(jA);
                }
                boolean[] zArr2 = new boolean[this.l.length];
                int i14 = 0;
                while (true) {
                    AbstractC1905e[] abstractC1905eArr = this.l;
                    if (i14 >= abstractC1905eArr.length) {
                        break;
                    }
                    AbstractC1905e abstractC1905e = abstractC1905eArr[i14];
                    boolean zR = r(abstractC1905e);
                    zArr2[i14] = zR;
                    u2.a0 a0Var = i13.c[i14];
                    if (zR) {
                        if (a0Var != abstractC1905e.f19642t) {
                            b(i14);
                        } else if (zArr[i14]) {
                            long j3 = this.f19512a0;
                            abstractC1905e.f19647y = false;
                            abstractC1905e.f19645w = j3;
                            abstractC1905e.f19646x = j3;
                            abstractC1905e.s(j3, false);
                        }
                    }
                    i14++;
                }
                f(zArr2, this.f19512a0);
            } else {
                this.f19489D.m(i11);
                if (i11.f19540e) {
                    i11.a(sVarJ, Math.max(i11.f19542g.f19551b, this.f19512a0 - i11.f19549p), false, new boolean[i11.f19545j.length]);
                }
            }
            l(true);
            if (this.f19497L.f19588e != 4) {
                t();
                k0();
                this.f19525t.e(2);
                return;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00bb A[PHI: r5 r6 r8
  0x00bb: PHI (r5v5 u2.C) = (r5v4 u2.C), (r5v14 u2.C) binds: [B:32:0x008f, B:34:0x00b4] A[DONT_GENERATE, DONT_INLINE]
  0x00bb: PHI (r6v2 long) = (r6v1 long), (r6v23 long) binds: [B:32:0x008f, B:34:0x00b4] A[DONT_GENERATE, DONT_INLINE]
  0x00bb: PHI (r8v2 long) = (r8v1 long), (r8v14 long) binds: [B:32:0x008f, B:34:0x00b4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x011b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(boolean r34, boolean r35, boolean r36, boolean r37) {
        /*
            Method dump skipped, instruction units count: 485
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.D.F(boolean, boolean, boolean, boolean):void");
    }

    public final void G() {
        I i7 = this.f19489D.f19563i;
        this.f19501P = i7 != null && i7.f19542g.f19555h && this.f19500O;
    }

    public final void H(long j3) {
        I i7 = this.f19489D.f19563i;
        long j10 = j3 + (i7 == null ? 1000000000000L : i7.f19549p);
        this.f19512a0 = j10;
        this.f19531z.l.b(j10);
        for (AbstractC1905e abstractC1905e : this.l) {
            if (r(abstractC1905e)) {
                long j11 = this.f19512a0;
                abstractC1905e.f19647y = false;
                abstractC1905e.f19645w = j11;
                abstractC1905e.f19646x = j11;
                abstractC1905e.s(j11, false);
            }
        }
        for (I i10 = r0.f19563i; i10 != null; i10 = i10.f19547m) {
            for (w2.q qVar : i10.o.c) {
                if (qVar != null) {
                    qVar.j();
                }
            }
        }
    }

    public final void I(f0 f0Var, f0 f0Var2) {
        if (f0Var.p() && f0Var2.p()) {
            return;
        }
        ArrayList arrayList = this.f19487A;
        int size = arrayList.size() - 1;
        if (size < 0) {
            Collections.sort(arrayList);
        } else {
            A7.k.q(arrayList.get(size));
            throw null;
        }
    }

    public final void L(long j3) {
        this.f19525t.f15248a.sendEmptyMessageAtTime(2, j3 + ((this.f19497L.f19588e != 3 || c0()) ? f19486h0 : 1000L));
    }

    public final void M(boolean z6) throws C1913m {
        C2492C c2492c = this.f19489D.f19563i.f19542g.f19550a;
        long jO = O(c2492c, this.f19497L.f19600s, true, false);
        if (jO != this.f19497L.f19600s) {
            U u4 = this.f19497L;
            this.f19497L = p(c2492c, jO, u4.c, u4.d, z6, 5);
        }
    }

    /* JADX WARN: Type inference failed for: r1v19, types: [java.lang.Object, u2.A] */
    public final void N(C c) throws Throwable {
        long j3;
        long j10;
        boolean z6;
        C2492C c2492c;
        long j11;
        long j12;
        long j13;
        U u4;
        int i7;
        this.f19498M.f(1);
        Pair pairJ = J(this.f19497L.f19586a, c, true, this.f19505T, this.f19506U, this.f19528w, this.f19529x);
        if (pairJ == null) {
            Pair pairH = h(this.f19497L.f19586a);
            c2492c = (C2492C) pairH.first;
            long jLongValue = ((Long) pairH.second).longValue();
            z6 = !this.f19497L.f19586a.p();
            j3 = jLongValue;
            j10 = -9223372036854775807L;
        } else {
            Object obj = pairJ.first;
            long jLongValue2 = ((Long) pairJ.second).longValue();
            long j14 = c.c == -9223372036854775807L ? -9223372036854775807L : jLongValue2;
            C2492C c2492cO = this.f19489D.o(this.f19497L.f19586a, obj, jLongValue2);
            if (c2492cO.c()) {
                this.f19497L.f19586a.g(c2492cO.f22663a, this.f19529x);
                j3 = this.f19529x.f(c2492cO.f22664b) == c2492cO.c ? this.f19529x.f14063g.f14044b : 0L;
                j10 = j14;
                c2492c = c2492cO;
                z6 = true;
            } else {
                j3 = jLongValue2;
                j10 = j14;
                z6 = c.c == -9223372036854775807L;
                c2492c = c2492cO;
            }
        }
        try {
            if (this.f19497L.f19586a.p()) {
                this.f19511Z = c;
            } else {
                if (pairJ != null) {
                    if (c2492c.equals(this.f19497L.f19587b)) {
                        I i10 = this.f19489D.f19563i;
                        long jA = (i10 == null || !i10.f19540e || j3 == 0) ? j3 : i10.f19538a.a(j3, this.f19496K);
                        if (AbstractC1343A.T(jA) == AbstractC1343A.T(this.f19497L.f19600s) && ((i7 = (u4 = this.f19497L).f19588e) == 2 || i7 == 3)) {
                            long j15 = u4.f19600s;
                            this.f19497L = p(c2492c, j15, j10, j15, z6, 2);
                            return;
                        }
                        j12 = jA;
                    } else {
                        j12 = j3;
                    }
                    boolean z10 = this.f19497L.f19588e == 4;
                    K k10 = this.f19489D;
                    long jO = O(c2492c, j12, k10.f19563i != k10.f19564j, z10);
                    z6 |= j3 != jO;
                    try {
                        U u10 = this.f19497L;
                        f0 f0Var = u10.f19586a;
                        l0(f0Var, c2492c, f0Var, u10.f19587b, j10, true);
                        j13 = jO;
                        this.f19497L = p(c2492c, j13, j10, j13, z6, 2);
                    } catch (Throwable th) {
                        th = th;
                        j11 = jO;
                        this.f19497L = p(c2492c, j11, j10, j11, z6, 2);
                        throw th;
                    }
                }
                if (this.f19497L.f19588e != 1) {
                    b0(4);
                }
                F(false, true, false, true);
            }
            j13 = j3;
            this.f19497L = p(c2492c, j13, j10, j13, z6, 2);
        } catch (Throwable th2) {
            th = th2;
            j11 = j3;
        }
    }

    /* JADX WARN: Type inference failed for: r9v9, types: [java.lang.Object, u2.A] */
    public final long O(C2492C c2492c, long j3, boolean z6, boolean z10) throws C1913m {
        AbstractC1905e[] abstractC1905eArr;
        g0();
        m0(false, true);
        if (z10 || this.f19497L.f19588e == 3) {
            b0(2);
        }
        K k10 = this.f19489D;
        I i7 = k10.f19563i;
        I i10 = i7;
        while (i10 != null && !c2492c.equals(i10.f19542g.f19550a)) {
            i10 = i10.f19547m;
        }
        if (z6 || i7 != i10 || (i10 != null && i10.f19549p + j3 < 0)) {
            int i11 = 0;
            while (true) {
                abstractC1905eArr = this.l;
                if (i11 >= abstractC1905eArr.length) {
                    break;
                }
                b(i11);
                i11++;
            }
            if (i10 != null) {
                while (k10.f19563i != i10) {
                    k10.a();
                }
                k10.m(i10);
                i10.f19549p = 1000000000000L;
                f(new boolean[abstractC1905eArr.length], k10.f19564j.e());
            }
        }
        if (i10 != null) {
            k10.m(i10);
            if (!i10.f19540e) {
                i10.f19542g = i10.f19542g.b(j3);
            } else if (i10.f19541f) {
                ?? r92 = i10.f19538a;
                j3 = r92.o(j3);
                r92.p(j3 - this.f19530y);
            }
            H(j3);
            t();
        } else {
            k10.b();
            H(j3);
        }
        l(false);
        this.f19525t.e(2);
        return j3;
    }

    public final void P(X x8) {
        Looper looper = x8.f19605f;
        Looper looper2 = this.f19527v;
        h2.x xVar = this.f19525t;
        if (looper != looper2) {
            xVar.a(15, x8).b();
            return;
        }
        synchronized (x8) {
        }
        try {
            x8.f19602a.b(x8.d, x8.f19604e);
            x8.b(true);
            int i7 = this.f19497L.f19588e;
            if (i7 == 3 || i7 == 2) {
                xVar.e(2);
            }
        } catch (Throwable th) {
            x8.b(true);
            throw th;
        }
    }

    public final void Q(X x8) {
        Looper looper = x8.f19605f;
        if (looper.getThread().isAlive()) {
            this.B.a(looper, null).c(new RunnableC1918s(this, 1, x8));
        } else {
            AbstractC1346c.z("TAG", "Trying to send message on a dead thread.");
            x8.b(false);
        }
    }

    public final void S(boolean z6, AtomicBoolean atomicBoolean) {
        if (this.f19507V != z6) {
            this.f19507V = z6;
            if (!z6) {
                for (AbstractC1905e abstractC1905e : this.l) {
                    if (!r(abstractC1905e) && this.f19519m.remove(abstractC1905e)) {
                        abstractC1905e.B();
                    }
                }
            }
        }
        if (atomicBoolean != null) {
            synchronized (this) {
                atomicBoolean.set(true);
                notifyAll();
            }
        }
    }

    public final void T(C1925z c1925z) throws Throwable {
        this.f19498M.f(1);
        int i7 = c1925z.c;
        ArrayList arrayList = c1925z.f19785a;
        u2.c0 c0Var = c1925z.f19786b;
        if (i7 != -1) {
            this.f19511Z = new C(new Z(arrayList, c0Var), c1925z.c, c1925z.d);
        }
        Y4.m mVar = this.f19490E;
        ArrayList arrayList2 = (ArrayList) mVar.c;
        mVar.i(0, arrayList2.size());
        m(mVar.a(arrayList2.size(), arrayList, c0Var), false);
    }

    public final void U(boolean z6) throws C1913m {
        this.f19500O = z6;
        G();
        if (this.f19501P) {
            K k10 = this.f19489D;
            if (k10.f19564j != k10.f19563i) {
                M(true);
                l(false);
            }
        }
    }

    public final void V(int i7, int i10, boolean z6, boolean z10) {
        this.f19498M.f(z10 ? 1 : 0);
        this.f19497L = this.f19497L.d(i10, i7, z6);
        m0(false, false);
        for (I i11 = this.f19489D.f19563i; i11 != null; i11 = i11.f19547m) {
            for (w2.q qVar : i11.o.c) {
                if (qVar != null) {
                    qVar.a(z6);
                }
            }
        }
        if (!c0()) {
            g0();
            k0();
            return;
        }
        int i12 = this.f19497L.f19588e;
        h2.x xVar = this.f19525t;
        if (i12 != 3) {
            if (i12 == 2) {
                xVar.e(2);
            }
        } else {
            C1911k c1911k = this.f19531z;
            c1911k.f19684q = true;
            c1911k.l.d();
            e0();
            xVar.e(2);
        }
    }

    public final void W(e2.P p10) {
        this.f19525t.d(16);
        C1911k c1911k = this.f19531z;
        c1911k.f(p10);
        e2.P pJ = c1911k.j();
        o(pJ, pJ.f13995a, true, true);
    }

    public final void X(C1916p c1916p) {
        this.f19518g0 = c1916p;
        f0 f0Var = this.f19497L.f19586a;
        K k10 = this.f19489D;
        k10.getClass();
        c1916p.getClass();
        if (k10.f19568p.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < k10.f19568p.size(); i7++) {
            ((I) k10.f19568p.get(i7)).i();
        }
        k10.f19568p = arrayList;
        k10.l = null;
        k10.k();
    }

    public final void Y(int i7) throws C1913m {
        this.f19505T = i7;
        f0 f0Var = this.f19497L.f19586a;
        K k10 = this.f19489D;
        k10.f19561g = i7;
        if (!k10.q(f0Var)) {
            M(true);
        }
        l(false);
    }

    public final void Z(boolean z6) throws C1913m {
        this.f19506U = z6;
        f0 f0Var = this.f19497L.f19586a;
        K k10 = this.f19489D;
        k10.f19562h = z6;
        if (!k10.q(f0Var)) {
            M(true);
        }
        l(false);
    }

    public final void a(C1925z c1925z, int i7) throws Throwable {
        this.f19498M.f(1);
        Y4.m mVar = this.f19490E;
        if (i7 == -1) {
            i7 = ((ArrayList) mVar.c).size();
        }
        m(mVar.a(i7, c1925z.f19785a, c1925z.f19786b), false);
    }

    public final void a0(u2.c0 c0Var) throws Throwable {
        this.f19498M.f(1);
        Y4.m mVar = this.f19490E;
        int size = ((ArrayList) mVar.c).size();
        if (c0Var.f22804b.length != size) {
            c0Var = new u2.c0(new Random(c0Var.f22803a.nextLong())).a(0, size);
        }
        mVar.f10766k = c0Var;
        m(mVar.c(), false);
    }

    public final void b(int i7) {
        AbstractC1905e abstractC1905e = this.l[i7];
        if (r(abstractC1905e)) {
            x(i7, false);
            C1911k c1911k = this.f19531z;
            if (abstractC1905e == c1911k.f19682n) {
                c1911k.o = null;
                c1911k.f19682n = null;
                c1911k.f19683p = true;
            }
            int i10 = abstractC1905e.f19641s;
            if (i10 == 2) {
                AbstractC1346c.j(i10 == 2);
                abstractC1905e.f19641s = 1;
                abstractC1905e.w();
            }
            AbstractC1346c.j(abstractC1905e.f19641s == 1);
            abstractC1905e.f19637n.c();
            abstractC1905e.f19641s = 0;
            abstractC1905e.f19642t = null;
            abstractC1905e.f19643u = null;
            abstractC1905e.f19647y = false;
            abstractC1905e.q();
            this.f19510Y--;
        }
    }

    public final void b0(int i7) {
        U u4 = this.f19497L;
        if (u4.f19588e != i7) {
            if (i7 != 2) {
                this.f19517f0 = -9223372036854775807L;
            }
            this.f19497L = u4.g(i7);
        }
    }

    @Override // u2.InterfaceC2522z
    public final void c(InterfaceC2490A interfaceC2490A) {
        this.f19525t.a(8, interfaceC2490A).b();
    }

    public final boolean c0() {
        U u4 = this.f19497L;
        return u4.l && u4.f19596n == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x06b6  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x06b9  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x06c1  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x06f8  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x06fd  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x0704  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0197  */
    /* JADX WARN: Type inference failed for: r1v48, types: [java.lang.Object, u2.A] */
    /* JADX WARN: Type inference failed for: r1v68, types: [w2.s] */
    /* JADX WARN: Type inference failed for: r4v16, types: [java.lang.Object, u2.A] */
    /* JADX WARN: Type inference failed for: r4v52, types: [java.lang.Object, u2.b0] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object, u2.A] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.lang.Object, u2.A] */
    /* JADX WARN: Type inference failed for: r9v43 */
    /* JADX WARN: Type inference failed for: r9v44, types: [int] */
    /* JADX WARN: Type inference failed for: r9v51 */
    /* JADX WARN: Type inference failed for: r9v52 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() throws n2.C1913m {
        /*
            Method dump skipped, instruction units count: 1931
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.D.d():void");
    }

    public final boolean d0(f0 f0Var, C2492C c2492c) {
        if (c2492c.c() || f0Var.p()) {
            return false;
        }
        int i7 = f0Var.g(c2492c.f22663a, this.f19529x).c;
        e0 e0Var = this.f19528w;
        f0Var.n(i7, e0Var);
        return e0Var.a() && e0Var.f14091i && e0Var.f14088f != -9223372036854775807L;
    }

    @Override // u2.InterfaceC2522z
    public final void e(u2.b0 b0Var) {
        this.f19525t.a(9, (InterfaceC2490A) b0Var).b();
    }

    public final void e0() {
        I i7 = this.f19489D.f19563i;
        if (i7 == null) {
            return;
        }
        w2.s sVar = i7.o;
        int i10 = 0;
        while (true) {
            AbstractC1905e[] abstractC1905eArr = this.l;
            if (i10 >= abstractC1905eArr.length) {
                return;
            }
            if (sVar.b(i10)) {
                AbstractC1905e abstractC1905e = abstractC1905eArr[i10];
                int i11 = abstractC1905e.f19641s;
                if (i11 == 1) {
                    AbstractC1346c.j(i11 == 1);
                    abstractC1905e.f19641s = 2;
                    abstractC1905e.v();
                }
            }
            i10++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(boolean[] r24, long r25) throws n2.C1913m {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.D.f(boolean[], long):void");
    }

    public final void f0(boolean z6, boolean z10) {
        F(z6 || !this.f19507V, false, true, false);
        this.f19498M.f(z10 ? 1 : 0);
        C1910j c1910j = this.f19523r;
        if (c1910j.f19679h.remove(this.f19493H) != null) {
            c1910j.d();
        }
        b0(1);
    }

    public final long g(f0 f0Var, Object obj, long j3) {
        c0 c0Var = this.f19529x;
        int i7 = f0Var.g(obj, c0Var).c;
        e0 e0Var = this.f19528w;
        f0Var.n(i7, e0Var);
        if (e0Var.f14088f == -9223372036854775807L || !e0Var.a() || !e0Var.f14091i) {
            return -9223372036854775807L;
        }
        long j10 = e0Var.f14089g;
        return AbstractC1343A.H((j10 == -9223372036854775807L ? System.currentTimeMillis() : j10 + SystemClock.elapsedRealtime()) - e0Var.f14088f) - (j3 + c0Var.f14061e);
    }

    public final void g0() {
        int i7;
        C1911k c1911k = this.f19531z;
        c1911k.f19684q = false;
        C1438n c1438n = c1911k.l;
        if (c1438n.l) {
            c1438n.b(c1438n.c());
            c1438n.l = false;
        }
        for (AbstractC1905e abstractC1905e : this.l) {
            if (r(abstractC1905e) && (i7 = abstractC1905e.f19641s) == 2) {
                AbstractC1346c.j(i7 == 2);
                abstractC1905e.f19641s = 1;
                abstractC1905e.w();
            }
        }
    }

    public final Pair h(f0 f0Var) {
        if (f0Var.p()) {
            return Pair.create(U.f19585u, 0L);
        }
        Pair pairI = f0Var.i(this.f19528w, this.f19529x, f0Var.a(this.f19506U), -9223372036854775807L);
        C2492C c2492cO = this.f19489D.o(f0Var, pairI.first, 0L);
        long jLongValue = ((Long) pairI.second).longValue();
        if (c2492cO.c()) {
            Object obj = c2492cO.f22663a;
            c0 c0Var = this.f19529x;
            f0Var.g(obj, c0Var);
            jLongValue = c2492cO.c == c0Var.f(c2492cO.f22664b) ? c0Var.f14063g.f14044b : 0L;
        }
        return Pair.create(c2492cO, Long.valueOf(jLongValue));
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Object, u2.b0] */
    public final void h0() {
        I i7 = this.f19489D.f19565k;
        boolean z6 = this.f19504S || (i7 != null && i7.f19538a.d());
        U u4 = this.f19497L;
        if (z6 != u4.f19590g) {
            this.f19497L = new U(u4.f19586a, u4.f19587b, u4.c, u4.d, u4.f19588e, u4.f19589f, z6, u4.f19591h, u4.f19592i, u4.f19593j, u4.f19594k, u4.l, u4.f19595m, u4.f19596n, u4.o, u4.f19598q, u4.f19599r, u4.f19600s, u4.f19601t, u4.f19597p);
        }
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) throws Throwable {
        I i7;
        int i10;
        I i11;
        int i12;
        try {
            switch (message.what) {
                case 1:
                    boolean z6 = message.arg1 != 0;
                    int i13 = message.arg2;
                    V(i13 >> 4, i13 & 15, z6, true);
                    break;
                case 2:
                    d();
                    break;
                case 3:
                    N((C) message.obj);
                    break;
                case 4:
                    W((e2.P) message.obj);
                    break;
                case 5:
                    this.f19496K = (b0) message.obj;
                    break;
                case 6:
                    f0(false, true);
                    break;
                case 7:
                    B();
                    return true;
                case 8:
                    n((InterfaceC2490A) message.obj);
                    break;
                case 9:
                    j((InterfaceC2490A) message.obj);
                    break;
                case 10:
                    E();
                    break;
                case 11:
                    Y(message.arg1);
                    break;
                case 12:
                    Z(message.arg1 != 0);
                    break;
                case 13:
                    S(message.arg1 != 0, (AtomicBoolean) message.obj);
                    break;
                case 14:
                    X x8 = (X) message.obj;
                    x8.getClass();
                    P(x8);
                    break;
                case 15:
                    Q((X) message.obj);
                    break;
                case 16:
                    e2.P p10 = (e2.P) message.obj;
                    o(p10, p10.f13995a, true, false);
                    break;
                case 17:
                    T((C1925z) message.obj);
                    break;
                case 18:
                    a((C1925z) message.obj, message.arg1);
                    break;
                case 19:
                    z((C1899A) message.obj);
                    break;
                case 20:
                    D(message.arg1, message.arg2, (u2.c0) message.obj);
                    break;
                case 21:
                    a0((u2.c0) message.obj);
                    break;
                case 22:
                    y();
                    break;
                case 23:
                    U(message.arg1 != 0);
                    break;
                case 24:
                default:
                    return false;
                case 25:
                    E();
                    M(true);
                    break;
                case 26:
                    E();
                    M(true);
                    break;
                case 27:
                    j0(message.arg1, message.arg2, (List) message.obj);
                    break;
                case 28:
                    X((C1916p) message.obj);
                    break;
                case 29:
                    A();
                    break;
            }
        } catch (e2.M e10) {
            boolean z10 = e10.l;
            int i14 = e10.f13984m;
            if (i14 == 1) {
                i12 = z10 ? 3001 : 3003;
            } else {
                if (i14 == 4) {
                    i12 = z10 ? 3002 : 3004;
                }
                k(i, e10);
            }
            i = i12;
            k(i, e10);
        } catch (RuntimeException e11) {
            C1913m c1913m = new C1913m(2, e11, ((e11 instanceof IllegalStateException) || (e11 instanceof IllegalArgumentException)) ? 1004 : 1000);
            AbstractC1346c.q("ExoPlayerImplInternal", "Playback error", c1913m);
            f0(true, false);
            this.f19497L = this.f19497L.e(c1913m);
        } catch (k2.j e12) {
            k(e12.l, e12);
        } catch (C1913m e13) {
            e = e13;
            int i15 = e.f19693u;
            K k10 = this.f19489D;
            if (i15 == 1 && (i11 = k10.f19564j) != null) {
                e = e.c(i11.f19542g.f19550a);
            }
            if (e.f19692A && (this.f19516e0 == null || (i10 = e.l) == 5004 || i10 == 5003)) {
                AbstractC1346c.A("ExoPlayerImplInternal", "Recoverable renderer error", e);
                C1913m c1913m2 = this.f19516e0;
                if (c1913m2 != null) {
                    c1913m2.addSuppressed(e);
                    e = this.f19516e0;
                } else {
                    this.f19516e0 = e;
                }
                h2.x xVar = this.f19525t;
                h2.w wVarA = xVar.a(25, e);
                xVar.getClass();
                Message message2 = wVarA.f15246a;
                message2.getClass();
                xVar.f15248a.sendMessageAtFrontOfQueue(message2);
                wVarA.a();
            } else {
                C1913m c1913m3 = this.f19516e0;
                if (c1913m3 != null) {
                    c1913m3.addSuppressed(e);
                    e = this.f19516e0;
                }
                AbstractC1346c.q("ExoPlayerImplInternal", "Playback error", e);
                if (e.f19693u == 1 && k10.f19563i != k10.f19564j) {
                    while (true) {
                        i7 = k10.f19563i;
                        if (i7 == k10.f19564j) {
                            break;
                        }
                        k10.a();
                    }
                    i7.getClass();
                    v();
                    J j3 = i7.f19542g;
                    C2492C c2492c = j3.f19550a;
                    long j10 = j3.f19551b;
                    this.f19497L = p(c2492c, j10, j3.c, j10, true, 0);
                }
                f0(true, false);
                this.f19497L = this.f19497L.e(e);
            }
        } catch (q2.g e14) {
            k(e14.l, e14);
        } catch (IOException e15) {
            k(2000, e15);
        }
        v();
        return true;
    }

    public final long i(long j3) {
        I i7 = this.f19489D.f19565k;
        if (i7 == null) {
            return 0L;
        }
        return Math.max(0L, j3 - (this.f19512a0 - i7.f19549p));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void i0(w2.s sVar) {
        I i7 = this.f19489D.f19565k;
        i7.getClass();
        i(i7.d());
        if (d0(this.f19497L.f19586a, i7.f19542g.f19550a)) {
            long j3 = this.f19491F.f19666h;
        }
        f0 f0Var = this.f19497L.f19586a;
        float f10 = this.f19531z.j().f13995a;
        boolean z6 = this.f19497L.l;
        w2.q[] qVarArr = sVar.c;
        C1910j c1910j = this.f19523r;
        C1909i c1909i = (C1909i) c1910j.f19679h.get(this.f19493H);
        c1909i.getClass();
        int iMax = c1910j.f19677f;
        if (iMax == -1) {
            int length = qVarArr.length;
            int i10 = 0;
            int i11 = 0;
            while (true) {
                int i12 = 13107200;
                if (i10 < length) {
                    w2.q qVar = qVarArr[i10];
                    if (qVar != null) {
                        switch (qVar.g().c) {
                            case -2:
                                i12 = 0;
                                i11 += i12;
                                break;
                            case -1:
                            case 1:
                                i11 += i12;
                                break;
                            case 0:
                                i12 = 144310272;
                                i11 += i12;
                                break;
                            case 2:
                                i12 = 131072000;
                                i11 += i12;
                                break;
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                i12 = 131072;
                                i11 += i12;
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                    }
                    i10++;
                } else {
                    iMax = Math.max(13107200, i11);
                }
            }
        }
        c1909i.f19673b = iMax;
        c1910j.d();
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [java.lang.Object, u2.b0] */
    public final void j(InterfaceC2490A interfaceC2490A) {
        K k10 = this.f19489D;
        I i7 = k10.f19565k;
        if (i7 == null || i7.f19538a != interfaceC2490A) {
            I i10 = k10.l;
            if (i10 == null || i10.f19538a != interfaceC2490A) {
                return;
            }
            u();
            return;
        }
        long j3 = this.f19512a0;
        if (i7 != null) {
            AbstractC1346c.j(i7.f19547m == null);
            if (i7.f19540e) {
                i7.f19538a.q(j3 - i7.f19549p);
            }
        }
        t();
    }

    public final void j0(int i7, int i10, List list) throws Throwable {
        this.f19498M.f(1);
        Y4.m mVar = this.f19490E;
        mVar.getClass();
        ArrayList arrayList = (ArrayList) mVar.c;
        AbstractC1346c.c(i7 >= 0 && i7 <= i10 && i10 <= arrayList.size());
        AbstractC1346c.c(list.size() == i10 - i7);
        for (int i11 = i7; i11 < i10; i11++) {
            ((T) arrayList.get(i11)).f19582a.s((e2.F) list.get(i11 - i7));
        }
        m(mVar.c(), false);
    }

    public final void k(int i7, IOException iOException) {
        C1913m c1913m = new C1913m(0, iOException, i7);
        I i10 = this.f19489D.f19563i;
        if (i10 != null) {
            c1913m = c1913m.c(i10.f19542g.f19550a);
        }
        AbstractC1346c.q("ExoPlayerImplInternal", "Playback error", c1913m);
        f0(false, false);
        this.f19497L = this.f19497L.e(c1913m);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00cb  */
    /* JADX WARN: Type inference failed for: r1v32, types: [java.lang.Object, u2.A] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k0() {
        /*
            Method dump skipped, instruction units count: 730
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.D.k0():void");
    }

    public final void l(boolean z6) {
        I i7 = this.f19489D.f19565k;
        C2492C c2492c = i7 == null ? this.f19497L.f19587b : i7.f19542g.f19550a;
        boolean zEquals = this.f19497L.f19594k.equals(c2492c);
        if (!zEquals) {
            this.f19497L = this.f19497L.b(c2492c);
        }
        U u4 = this.f19497L;
        u4.f19598q = i7 == null ? u4.f19600s : i7.d();
        U u10 = this.f19497L;
        u10.f19599r = i(u10.f19598q);
        if ((!zEquals || z6) && i7 != null && i7.f19540e) {
            i0(i7.o);
        }
    }

    public final void l0(f0 f0Var, C2492C c2492c, f0 f0Var2, C2492C c2492c2, long j3, boolean z6) {
        if (!d0(f0Var, c2492c)) {
            e2.P p10 = c2492c.c() ? e2.P.d : this.f19497L.o;
            C1911k c1911k = this.f19531z;
            if (c1911k.j().equals(p10)) {
                return;
            }
            this.f19525t.d(16);
            c1911k.f(p10);
            o(this.f19497L.o, p10.f13995a, false, false);
            return;
        }
        Object obj = c2492c.f22663a;
        c0 c0Var = this.f19529x;
        int i7 = f0Var.g(obj, c0Var).c;
        e0 e0Var = this.f19528w;
        f0Var.n(i7, e0Var);
        C1217z c1217z = e0Var.f14092j;
        C1908h c1908h = this.f19491F;
        c1908h.getClass();
        c1908h.c = AbstractC1343A.H(c1217z.f14376a);
        c1908h.f19664f = AbstractC1343A.H(c1217z.f14377b);
        c1908h.f19665g = AbstractC1343A.H(c1217z.c);
        float f10 = c1217z.d;
        if (f10 == -3.4028235E38f) {
            f10 = 0.97f;
        }
        c1908h.f19668j = f10;
        float f11 = c1217z.f14378e;
        if (f11 == -3.4028235E38f) {
            f11 = 1.03f;
        }
        c1908h.f19667i = f11;
        if (f10 == 1.0f && f11 == 1.0f) {
            c1908h.c = -9223372036854775807L;
        }
        c1908h.a();
        if (j3 != -9223372036854775807L) {
            c1908h.d = g(f0Var, obj, j3);
            c1908h.a();
            return;
        }
        if (!Objects.equals(!f0Var2.p() ? f0Var2.m(f0Var2.g(c2492c2.f22663a, c0Var).c, e0Var, 0L).f14085a : null, e0Var.f14085a) || z6) {
            c1908h.d = -9223372036854775807L;
            c1908h.a();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01e8  */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v14 */
    /* JADX WARN: Type inference failed for: r15v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r26v1 */
    /* JADX WARN: Type inference failed for: r26v2 */
    /* JADX WARN: Type inference failed for: r26v22 */
    /* JADX WARN: Type inference failed for: r26v29 */
    /* JADX WARN: Type inference failed for: r26v3 */
    /* JADX WARN: Type inference failed for: r26v30 */
    /* JADX WARN: Type inference failed for: r26v4 */
    /* JADX WARN: Type inference failed for: r26v5 */
    /* JADX WARN: Type inference failed for: r26v6 */
    /* JADX WARN: Type inference failed for: r26v7 */
    /* JADX WARN: Type inference failed for: r3v16, types: [long] */
    /* JADX WARN: Type inference failed for: r40v0, types: [n2.D] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11, types: [long] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r9v11, types: [boolean] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(e2.f0 r41, boolean r42) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1149
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.D.m(e2.f0, boolean):void");
    }

    public final void m0(boolean z6, boolean z10) {
        long jElapsedRealtime;
        this.f19502Q = z6;
        if (!z6 || z10) {
            jElapsedRealtime = -9223372036854775807L;
        } else {
            this.B.getClass();
            jElapsedRealtime = SystemClock.elapsedRealtime();
        }
        this.f19503R = jElapsedRealtime;
    }

    public final void n(InterfaceC2490A interfaceC2490A) throws C1913m {
        I i7;
        K k10 = this.f19489D;
        I i10 = k10.f19565k;
        int i11 = 0;
        boolean z6 = i10 != null && i10.f19538a == interfaceC2490A;
        C1911k c1911k = this.f19531z;
        if (z6) {
            i10.getClass();
            if (!i10.f19540e) {
                float f10 = c1911k.j().f13995a;
                U u4 = this.f19497L;
                i10.f(f10, u4.f19586a, u4.l);
            }
            i0(i10.o);
            if (i10 == k10.f19563i) {
                H(i10.f19542g.f19551b);
                f(new boolean[this.l.length], k10.f19564j.e());
                U u10 = this.f19497L;
                C2492C c2492c = u10.f19587b;
                J j3 = i10.f19542g;
                long j10 = u10.c;
                long j11 = j3.f19551b;
                this.f19497L = p(c2492c, j11, j10, j11, false, 5);
            }
            t();
            return;
        }
        while (true) {
            if (i11 >= k10.f19568p.size()) {
                i7 = null;
                break;
            }
            i7 = (I) k10.f19568p.get(i11);
            if (i7.f19538a == interfaceC2490A) {
                break;
            } else {
                i11++;
            }
        }
        if (i7 != null) {
            AbstractC1346c.j(!i7.f19540e);
            float f11 = c1911k.j().f13995a;
            U u11 = this.f19497L;
            i7.f(f11, u11.f19586a, u11.l);
            I i12 = k10.l;
            if (i12 == null || i12.f19538a != interfaceC2490A) {
                return;
            }
            u();
        }
    }

    public final synchronized void n0(C1914n c1914n, long j3) {
        this.B.getClass();
        long jElapsedRealtime = SystemClock.elapsedRealtime() + j3;
        boolean z6 = false;
        while (!((Boolean) c1914n.get()).booleanValue() && j3 > 0) {
            try {
                this.B.getClass();
                wait(j3);
            } catch (InterruptedException unused) {
                z6 = true;
            }
            this.B.getClass();
            j3 = jElapsedRealtime - SystemClock.elapsedRealtime();
        }
        if (z6) {
            Thread.currentThread().interrupt();
        }
    }

    public final void o(e2.P p10, float f10, boolean z6, boolean z10) {
        int i7;
        if (z6) {
            if (z10) {
                this.f19498M.f(1);
            }
            this.f19497L = this.f19497L.f(p10);
        }
        float f11 = p10.f13995a;
        I i10 = this.f19489D.f19563i;
        while (true) {
            i7 = 0;
            if (i10 == null) {
                break;
            }
            w2.q[] qVarArr = i10.o.c;
            int length = qVarArr.length;
            while (i7 < length) {
                w2.q qVar = qVarArr[i7];
                if (qVar != null) {
                    qVar.i(f11);
                }
                i7++;
            }
            i10 = i10.f19547m;
        }
        AbstractC1905e[] abstractC1905eArr = this.l;
        int length2 = abstractC1905eArr.length;
        while (i7 < length2) {
            AbstractC1905e abstractC1905e = abstractC1905eArr[i7];
            if (abstractC1905e != null) {
                abstractC1905e.C(f10, p10.f13995a);
            }
            i7++;
        }
    }

    public final U p(C2492C c2492c, long j3, long j10, long j11, boolean z6, int i7) {
        w2.s sVar;
        List list;
        n0 n0Var;
        d0 d0VarH;
        boolean z10;
        int i10;
        int i11;
        this.f19515d0 = (!this.f19515d0 && j3 == this.f19497L.f19600s && c2492c.equals(this.f19497L.f19587b)) ? false : true;
        G();
        U u4 = this.f19497L;
        n0 n0Var2 = u4.f19591h;
        w2.s sVar2 = u4.f19592i;
        List list2 = u4.f19593j;
        if (this.f19490E.f10758a) {
            I i12 = this.f19489D.f19563i;
            n0 n0Var3 = i12 == null ? n0.d : i12.f19548n;
            w2.s sVar3 = i12 == null ? this.f19522q : i12.o;
            w2.q[] qVarArr = sVar3.c;
            C1077F c1077f = new C1077F(4);
            int length = qVarArr.length;
            int i13 = 0;
            boolean z11 = false;
            while (i13 < length) {
                w2.q qVar = qVarArr[i13];
                if (qVar == null) {
                    i11 = 1;
                } else {
                    e2.K k10 = qVar.b(0).l;
                    if (k10 == null) {
                        c1077f.a(new e2.K(new e2.J[0]));
                        i11 = 1;
                    } else {
                        c1077f.a(k10);
                        i11 = 1;
                        z11 = true;
                    }
                }
                i13 += i11;
            }
            if (z11) {
                d0VarH = c1077f.h();
            } else {
                C1078G c1078g = d5.I.f13384m;
                d0VarH = d0.f13415p;
            }
            if (i12 != null) {
                J j12 = i12.f19542g;
                if (j12.c != j10) {
                    i12.f19542g = j12.a(j10);
                }
            }
            I i14 = this.f19489D.f19563i;
            if (i14 != null) {
                w2.s sVar4 = i14.o;
                boolean z12 = false;
                int i15 = 0;
                while (true) {
                    AbstractC1905e[] abstractC1905eArr = this.l;
                    if (i15 >= abstractC1905eArr.length) {
                        z10 = true;
                        break;
                    }
                    if (sVar4.b(i15)) {
                        i10 = 1;
                        if (abstractC1905eArr[i15].f19636m != 1) {
                            z10 = false;
                            break;
                        }
                        if (sVar4.f23272b[i15].f19622a != 0) {
                            z12 = true;
                        }
                    } else {
                        i10 = 1;
                    }
                    i15 += i10;
                }
                boolean z13 = z12 && z10;
                if (z13 != this.f19509X) {
                    this.f19509X = z13;
                    if (!z13 && this.f19497L.f19597p) {
                        this.f19525t.e(2);
                    }
                }
            }
            list = d0VarH;
            n0Var = n0Var3;
            sVar = sVar3;
        } else if (c2492c.equals(u4.f19587b)) {
            sVar = sVar2;
            list = list2;
            n0Var = n0Var2;
        } else {
            n0Var = n0.d;
            sVar = this.f19522q;
            list = d0.f13415p;
        }
        if (z6) {
            h3.x xVar = this.f19498M;
            if (!xVar.d || xVar.f15497e == 5) {
                xVar.f15496b = true;
                xVar.d = true;
                xVar.f15497e = i7;
            } else {
                AbstractC1346c.c(i7 == 5);
            }
        }
        U u10 = this.f19497L;
        return u10.c(c2492c, j3, j10, j11, i(u10.f19598q), n0Var, sVar, list);
    }

    public final boolean s() {
        I i7 = this.f19489D.f19563i;
        long j3 = i7.f19542g.f19552e;
        return i7.f19540e && (j3 == -9223372036854775807L || this.f19497L.f19600s < j3 || !c0());
    }

    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.Object, u2.b0] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Object, u2.b0] */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.lang.Object, u2.A] */
    public final void t() {
        boolean zC;
        if (q(this.f19489D.f19565k)) {
            I i7 = this.f19489D.f19565k;
            long jI = i(!i7.f19540e ? 0L : i7.f19538a.g());
            I i10 = this.f19489D.f19563i;
            long j3 = d0(this.f19497L.f19586a, i7.f19542g.f19550a) ? this.f19491F.f19666h : -9223372036854775807L;
            o2.l lVar = this.f19493H;
            f0 f0Var = this.f19497L.f19586a;
            float f10 = this.f19531z.j().f13995a;
            boolean z6 = this.f19497L.l;
            E e10 = new E(lVar, jI, f10, this.f19502Q, j3);
            zC = this.f19523r.c(e10);
            I i11 = this.f19489D.f19563i;
            if (!zC && i11.f19540e && jI < 500000 && this.f19530y > 0) {
                i11.f19538a.p(this.f19497L.f19600s);
                zC = this.f19523r.c(e10);
            }
        } else {
            zC = false;
        }
        this.f19504S = zC;
        if (zC) {
            I i12 = this.f19489D.f19565k;
            i12.getClass();
            F f11 = new F();
            f11.f19534a = this.f19512a0 - i12.f19549p;
            float f12 = this.f19531z.j().f13995a;
            AbstractC1346c.c(f12 > 0.0f || f12 == -3.4028235E38f);
            f11.f19535b = f12;
            long j10 = this.f19503R;
            AbstractC1346c.c(j10 >= 0 || j10 == -9223372036854775807L);
            f11.c = j10;
            G g7 = new G(f11);
            AbstractC1346c.j(i12.f19547m == null);
            i12.f19538a.i(g7);
        }
        h0();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, u2.A, u2.b0] */
    public final void u() {
        K k10 = this.f19489D;
        k10.k();
        I i7 = k10.l;
        if (i7 != null) {
            if (!i7.d || i7.f19540e) {
                ?? r12 = i7.f19538a;
                if (r12.d()) {
                    return;
                }
                f0 f0Var = this.f19497L.f19586a;
                if (i7.f19540e) {
                    r12.l();
                }
                Iterator it = this.f19523r.f19679h.values().iterator();
                while (it.hasNext()) {
                    if (((C1909i) it.next()).f19672a) {
                        return;
                    }
                }
                if (!i7.d) {
                    J j3 = i7.f19542g;
                    i7.d = true;
                    r12.b(this, j3.f19551b);
                    return;
                }
                F f10 = new F();
                f10.f19534a = this.f19512a0 - i7.f19549p;
                float f11 = this.f19531z.j().f13995a;
                AbstractC1346c.c(f11 > 0.0f || f11 == -3.4028235E38f);
                f10.f19535b = f11;
                long j10 = this.f19503R;
                AbstractC1346c.c(j10 >= 0 || j10 == -9223372036854775807L);
                f10.c = j10;
                G g7 = new G(f10);
                AbstractC1346c.j(i7.f19547m == null);
                r12.i(g7);
            }
        }
    }

    public final void v() {
        h3.x xVar = this.f19498M;
        U u4 = this.f19497L;
        boolean z6 = xVar.f15496b | (((U) xVar.f15498f) != u4);
        xVar.f15496b = z6;
        xVar.f15498f = u4;
        if (z6) {
            C1923x c1923x = this.f19488C.f19719m;
            c1923x.f19766j.c(new RunnableC1918s(c1923x, 0, xVar));
            this.f19498M = new h3.x(this.f19497L);
        }
    }

    public final void w(int i7) {
        AbstractC1905e abstractC1905e = this.l[i7];
        try {
            u2.a0 a0Var = abstractC1905e.f19642t;
            a0Var.getClass();
            a0Var.r();
        } catch (IOException | RuntimeException e10) {
            int i10 = abstractC1905e.f19636m;
            if (i10 != 3 && i10 != 5) {
                throw e10;
            }
            w2.s sVar = this.f19489D.f19563i.o;
            AbstractC1346c.q("ExoPlayerImplInternal", "Disabling track due to error: " + C1208p.e(sVar.c[i7].h()), e10);
            w2.s sVar2 = new w2.s((a0[]) sVar.f23272b.clone(), (w2.q[]) sVar.c.clone(), sVar.d, sVar.f23273e);
            sVar2.f23272b[i7] = null;
            sVar2.c[i7] = null;
            b(i7);
            I i11 = this.f19489D.f19563i;
            i11.a(sVar2, this.f19497L.f19600s, false, new boolean[i11.f19545j.length]);
        }
    }

    public final void x(int i7, boolean z6) {
        boolean[] zArr = this.o;
        if (zArr[i7] != z6) {
            zArr[i7] = z6;
            this.f19495J.c(new M.g(this, i7, z6));
        }
    }

    public final void y() throws Throwable {
        m(this.f19490E.c(), true);
    }

    public final void z(C1899A c1899a) throws Throwable {
        f0 f0VarC;
        this.f19498M.f(1);
        int i7 = c1899a.f19478a;
        Y4.m mVar = this.f19490E;
        mVar.getClass();
        ArrayList arrayList = (ArrayList) mVar.c;
        int i10 = c1899a.f19479b;
        int i11 = c1899a.c;
        AbstractC1346c.c(i7 >= 0 && i7 <= i10 && i10 <= arrayList.size() && i11 >= 0);
        mVar.f10766k = c1899a.d;
        if (i7 == i10 || i7 == i11) {
            f0VarC = mVar.c();
        } else {
            int iMin = Math.min(i7, i11);
            int iMax = Math.max(((i10 - i7) + i11) - 1, i10 - 1);
            int iO = ((T) arrayList.get(iMin)).d;
            AbstractC1343A.G(arrayList, i7, i10, i11);
            while (iMin <= iMax) {
                T t10 = (T) arrayList.get(iMin);
                t10.d = iO;
                iO += t10.f19582a.o.f22896e.o();
                iMin++;
            }
            f0VarC = mVar.c();
        }
        m(f0VarC, false);
    }
}
