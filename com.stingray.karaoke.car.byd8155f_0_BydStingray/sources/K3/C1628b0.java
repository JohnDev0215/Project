package k3;

import A.C0050y0;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.session.MediaController;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import d5.C1077F;
import e2.BinderC1198f;
import e2.C1194b;
import e2.C1195c;
import e2.C1202j;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h3.C1350a;
import i3.C1406e;
import io.sentry.C1458d0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import u.C2465e;
import u.C2466f;

/* JADX INFO: renamed from: k3.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1628b0 implements InterfaceC1670u {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC1655m f17899A;
    public MediaController B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public long f17900C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public long f17901D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public q1 f17902E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public o1 f17903F;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1672v f17904a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E0.X f17905b;
    public final BinderC1658n0 c;
    public final Context d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final E1 f17906e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Bundle f17907f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C1614H f17908g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final SurfaceHolderCallbackC1625a0 f17909h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h2.o f17910i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final M.t f17911j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C2466f f17912k;
    public E1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Y f17913m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f17914n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public d5.I f17915p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public d5.I f17916q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public d5.d0 f17917r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public e2.S f17919t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public e2.S f17920u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public e2.S f17921v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Surface f17922w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public SurfaceHolder f17923x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextureView f17924y;
    public q1 o = q1.f18034F;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public h2.u f17925z = h2.u.c;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public z1 f17918s = z1.f18172b;

    /* JADX WARN: Type inference failed for: r5v4, types: [k3.H] */
    public C1628b0(Context context, C1672v c1672v, E1 e12, Bundle bundle, Looper looper) {
        d5.d0 d0Var = d5.d0.f13415p;
        this.f17915p = d0Var;
        this.f17916q = d0Var;
        this.f17917r = d0Var;
        e2.S s6 = e2.S.f13999b;
        this.f17919t = s6;
        this.f17920u = s6;
        this.f17921v = X0(s6, s6);
        this.f17910i = new h2.o(looper, h2.v.f15245a, new C1678y(this, 8));
        this.f17904a = c1672v;
        AbstractC1346c.h(context, "context must not be null");
        AbstractC1346c.h(e12, "token must not be null");
        this.d = context;
        this.f17905b = new E0.X(1);
        this.c = new BinderC1658n0(this);
        this.f17912k = new C2466f(null);
        this.f17906e = e12;
        this.f17907f = bundle;
        this.f17908g = new IBinder.DeathRecipient() { // from class: k3.H
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                C1672v c1672v2 = this.f17790a.f17904a;
                Objects.requireNonNull(c1672v2);
                c1672v2.X0(new A2.l(c1672v2, 21));
            }
        };
        this.f17909h = new SurfaceHolderCallbackC1625a0(this);
        Bundle bundle2 = Bundle.EMPTY;
        this.f17913m = e12.f17758a.b() != 0 ? new Y(this, bundle) : null;
        M.t tVar = new M.t();
        tVar.f4151m = this;
        tVar.l = new Handler(looper, new h2.k(tVar, 1));
        this.f17911j = tVar;
        this.f17900C = -9223372036854775807L;
        this.f17901D = -9223372036854775807L;
    }

    public static e2.S X0(e2.S s6, e2.S s10) {
        e2.S sG = n1.g(s6, s10);
        if (sG.a(32)) {
            return sG;
        }
        C0050y0 c0050y0 = new C0050y0(5);
        c0050y0.j(sG.f14000a);
        c0050y0.f(32);
        return new e2.S(c0050y0.l());
    }

    public static e2.d0 Y0(ArrayList arrayList, ArrayList arrayList2) {
        C1077F c1077f = new C1077F(4);
        c1077f.e(arrayList);
        d5.d0 d0VarH = c1077f.h();
        C1077F c1077f2 = new C1077F(4);
        c1077f2.e(arrayList2);
        d5.d0 d0VarH2 = c1077f2.h();
        int size = arrayList.size();
        int[] iArr = new int[size];
        for (int i7 = 0; i7 < size; i7++) {
            iArr[i7] = i7;
        }
        return new e2.d0(d0VarH, d0VarH2, iArr);
    }

    public static int c1(q1 q1Var) {
        int i7 = q1Var.c.f17732a.f14008b;
        if (i7 == -1) {
            return 0;
        }
        return i7;
    }

    public static q1 h1(q1 q1Var, int i7, List list, long j3, long j10) {
        int size;
        int size2;
        e2.f0 f0Var = q1Var.f18078j;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i10 = 0; i10 < f0Var.o(); i10++) {
            arrayList.add(f0Var.m(i10, new e2.e0(), 0L));
        }
        for (int i11 = 0; i11 < list.size(); i11++) {
            e2.F f10 = (e2.F) list.get(i11);
            e2.e0 e0Var = new e2.e0();
            e0Var.b(0, f10, null, 0L, 0L, 0L, true, false, null, 0L, -9223372036854775807L, -1, -1, 0L);
            arrayList.add(i11 + i7, e0Var);
        }
        o1(f0Var, arrayList, arrayList2);
        e2.d0 d0VarY0 = Y0(arrayList, arrayList2);
        if (q1Var.f18078j.p()) {
            size2 = 0;
            size = 0;
        } else {
            B1 b12 = q1Var.c;
            size = b12.f17732a.f14008b;
            if (size >= i7) {
                size += list.size();
            }
            size2 = b12.f17732a.f14009e;
            if (size2 >= i7) {
                size2 += list.size();
            }
        }
        return j1(q1Var, d0VarY0, size, size2, j3, j10, 5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0074, code lost:
    
        r8 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static k3.q1 i1(k3.q1 r46, int r47, int r48, boolean r49, long r50, long r52) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1628b0.i1(k3.q1, int, int, boolean, long, long):k3.q1");
    }

    public static q1 j1(q1 q1Var, e2.d0 d0Var, int i7, int i10, long j3, long j10, int i11) {
        e2.e0 e0Var = new e2.e0();
        d0Var.m(i7, e0Var, 0L);
        e2.F f10 = e0Var.c;
        e2.V v10 = q1Var.c.f17732a;
        e2.V v11 = new e2.V(null, i7, f10, null, i10, j3, j10, v10.f14012h, v10.f14013i);
        B1 b12 = q1Var.c;
        return k1(q1Var, d0Var, v11, new B1(v11, b12.f17733b, SystemClock.elapsedRealtime(), b12.d, b12.f17734e, b12.f17735f, b12.f17736g, b12.f17737h, b12.f17738i, b12.f17739j), i11);
    }

    public static q1 k1(q1 q1Var, e2.f0 f0Var, e2.V v10, B1 b12, int i7) {
        e2.O o = q1Var.f18071a;
        e2.V v11 = q1Var.c.f17732a;
        AbstractC1346c.j(f0Var.p() || b12.f17732a.f14008b < f0Var.o());
        return new q1(o, q1Var.f18072b, b12, v11, v10, i7, q1Var.f18075g, q1Var.f18076h, q1Var.f18077i, q1Var.l, f0Var, q1Var.f18079k, q1Var.f18080m, q1Var.f18081n, q1Var.o, q1Var.f18082p, q1Var.f18083q, q1Var.f18084r, q1Var.f18085s, q1Var.f18086t, q1Var.f18087u, q1Var.f18090x, q1Var.f18091y, q1Var.f18088v, q1Var.f18089w, q1Var.f18092z, q1Var.f18067A, q1Var.B, q1Var.f18068C, q1Var.f18069D, q1Var.f18070E);
    }

    public static void o1(e2.f0 f0Var, ArrayList arrayList, ArrayList arrayList2) {
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            e2.e0 e0Var = (e2.e0) arrayList.get(i7);
            int i10 = e0Var.f14095n;
            int i11 = e0Var.o;
            if (i10 == -1 || i11 == -1) {
                e0Var.f14095n = arrayList2.size();
                e0Var.o = arrayList2.size();
                e2.c0 c0Var = new e2.c0();
                c0Var.i(null, null, i7, -9223372036854775807L, 0L, C1194b.f14037f, true);
                arrayList2.add(c0Var);
            } else {
                e0Var.f14095n = arrayList2.size();
                e0Var.o = (i11 - i10) + arrayList2.size();
                while (i10 <= i11) {
                    e2.c0 c0Var2 = new e2.c0();
                    f0Var.f(i10, c0Var2, false);
                    c0Var2.c = i7;
                    arrayList2.add(c0Var2);
                    i10++;
                }
            }
        }
    }

    public static d5.d0 r1(List list, List list2, z1 z1Var, e2.S s6) {
        List list3 = list.isEmpty() ? list2 : list;
        String str = C1627b.f17885j;
        C1077F c1077f = new C1077F(4);
        for (int i7 = 0; i7 < list3.size(); i7++) {
            C1627b c1627b = (C1627b) list3.get(i7);
            y1 y1Var = c1627b.f17892a;
            if (y1Var != null) {
                z1Var.getClass();
                if (!z1Var.f18173a.contains(y1Var)) {
                }
                c1077f.a(c1627b);
            }
            int i10 = c1627b.f17893b;
            if (i10 == -1 || !s6.a(i10)) {
                if (c1627b.f17898i) {
                    c1627b = new C1627b(c1627b.f17892a, c1627b.f17893b, c1627b.c, c1627b.d, c1627b.f17894e, c1627b.f17895f, new Bundle(c1627b.f17896g), false, c1627b.f17897h);
                }
                c1077f.a(c1627b);
            } else {
                c1077f.a(c1627b);
            }
        }
        return c1077f.h();
    }

    @Override // k3.InterfaceC1670u
    public final e2.S A() {
        return this.f17921v;
    }

    @Override // k3.InterfaceC1670u
    public final void A0(int i7, int i10) {
        if (g1(20)) {
            AbstractC1346c.c(i7 >= 0 && i10 >= i7);
            a1(new C1607A(this, i7, i10, 0));
            p1(i7, i10);
        }
    }

    @Override // k3.InterfaceC1670u
    public final long B() {
        return this.o.c.f17734e;
    }

    @Override // k3.InterfaceC1670u
    public final void B0(int i7) {
        if (g1(25)) {
            a1(new C1676x(this, i7, 7));
            q1 q1Var = this.o;
            C1202j c1202j = q1Var.f18083q;
            if (q1Var.f18084r == i7 || c1202j.f14128b > i7) {
                return;
            }
            int i10 = c1202j.c;
            if (i10 == 0 || i7 <= i10) {
                this.o = q1Var.c(i7, q1Var.f18085s);
                C1676x c1676x = new C1676x(this, i7, 8);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1676x);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final boolean C() {
        return this.o.f18086t;
    }

    @Override // k3.InterfaceC1670u
    public final void C0() {
        if (g1(9)) {
            a1(new C1678y(this, 9));
            e2.f0 f0Var = this.o.f18078j;
            if (f0Var.p() || s()) {
                return;
            }
            if (M()) {
                s1(-9223372036854775807L, d1());
                return;
            }
            e2.e0 e0VarM = f0Var.m(c1(this.o), new e2.e0(), 0L);
            if (e0VarM.f14091i && e0VarM.a()) {
                s1(-9223372036854775807L, c1(this.o));
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void D() {
        if (g1(20)) {
            a1(new C1678y(this, 7));
            p1(0, Integer.MAX_VALUE);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void D0(e2.U u4) {
        this.f17910i.a(u4);
    }

    @Override // k3.InterfaceC1670u
    public final void E(final e2.F f10, final long j3) {
        if (g1(31)) {
            a1(new X() { // from class: k3.O
                @Override // k3.X
                public final void h(InterfaceC1655m interfaceC1655m, int i7) {
                    C1628b0 c1628b0 = this.l;
                    c1628b0.getClass();
                    interfaceC1655m.S(c1628b0.c, i7, f10.d(true), j3);
                }
            });
            v1(Collections.singletonList(f10), -1, j3, false);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void E0() {
        if (g1(12)) {
            a1(new C1678y(this, 3));
            t1(this.o.B);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void F(boolean z6) {
        if (g1(14)) {
            a1(new C1611E(this, z6, 1));
            q1 q1Var = this.o;
            if (q1Var.f18077i != z6) {
                this.o = q1Var.k(z6);
                C1613G c1613g = new C1613G(0, z6);
                h2.o oVar = this.f17910i;
                oVar.c(9, c1613g);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void F0(TextureView textureView) {
        if (g1(27)) {
            if (textureView == null) {
                W0();
                return;
            }
            if (this.f17924y == textureView) {
                return;
            }
            V0();
            this.f17924y = textureView;
            textureView.setSurfaceTextureListener(this.f17909h);
            SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
            if (surfaceTexture == null) {
                b1(new C1678y(this, 1));
                l1(0, 0);
            } else {
                this.f17922w = new Surface(surfaceTexture);
                b1(new C1678y(this, 2));
                l1(textureView.getWidth(), textureView.getHeight());
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void G() {
        if (g1(8)) {
            a1(new C1678y(this, 16));
            if (d1() != -1) {
                s1(-9223372036854775807L, d1());
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void G0(e2.I i7) {
        if (g1(19)) {
            a1(new C1458d0(this, 9, i7));
            if (this.o.f18080m.equals(i7)) {
                return;
            }
            this.o = this.o.g(i7);
            C1680z c1680z = new C1680z(i7, 0);
            h2.o oVar = this.f17910i;
            oVar.c(15, c1680z);
            oVar.b();
        }
    }

    @Override // k3.InterfaceC1670u
    public final void H(e2.U u4) {
        this.f17910i.e(u4);
    }

    @Override // k3.InterfaceC1670u
    public final void H0() {
        if (g1(11)) {
            a1(new C1678y(this, 10));
            t1(-this.o.f18067A);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void I(int i7) {
        if (g1(34)) {
            a1(new C1676x(this, i7, 10));
            q1 q1Var = this.o;
            int i10 = q1Var.f18084r - 1;
            if (i10 >= q1Var.f18083q.f14128b) {
                this.o = q1Var.c(i10, q1Var.f18085s);
                C1676x c1676x = new C1676x(this, i10, 11);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1676x);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void I0(long j3, int i7) {
        if (g1(10)) {
            AbstractC1346c.c(i7 >= 0);
            a1(new Y2.m(i7, j3, this));
            s1(j3, i7);
        }
    }

    @Override // k3.InterfaceC1670u
    public final e2.n0 J() {
        return this.o.f18069D;
    }

    @Override // k3.InterfaceC1670u
    public final void J0(float f10) {
        if (g1(24)) {
            a1(new C1608B(f10, 1, this));
            q1 q1Var = this.o;
            if (q1Var.f18081n != f10) {
                this.o = q1Var.p(f10);
                C1615I c1615i = new C1615I(f10, 0);
                h2.o oVar = this.f17910i;
                oVar.c(22, c1615i);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final int K() {
        return this.o.c.f17735f;
    }

    @Override // k3.InterfaceC1670u
    public final e2.I K0() {
        return this.o.f18092z;
    }

    @Override // k3.InterfaceC1670u
    public final long L() {
        return this.o.f18068C;
    }

    @Override // k3.InterfaceC1670u
    public final void L0(List list) {
        if (g1(20)) {
            a1(new U(this, list, 0));
            v1(list, -1, -9223372036854775807L, true);
        }
    }

    @Override // k3.InterfaceC1670u
    public final boolean M() {
        return d1() != -1;
    }

    @Override // k3.InterfaceC1670u
    public final void M0() {
        if (g1(7)) {
            a1(new C1678y(this, 6));
            e2.f0 f0Var = this.o.f18078j;
            if (f0Var.p() || s()) {
                return;
            }
            boolean zI0 = i0();
            e2.e0 e0VarM = f0Var.m(c1(this.o), new e2.e0(), 0L);
            if (e0VarM.f14091i && e0VarM.a()) {
                if (zI0) {
                    s1(-9223372036854775807L, f1());
                }
            } else if (!zI0 || N0() > this.o.f18068C) {
                s1(0L, c1(this.o));
            } else {
                s1(-9223372036854775807L, f1());
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final e2.I N() {
        return this.o.f18080m;
    }

    @Override // k3.InterfaceC1670u
    public final long N0() {
        long jF = n1.f(this.o, this.f17900C, this.f17901D, this.f17904a.f18138f);
        this.f17900C = jF;
        return jF;
    }

    @Override // k3.InterfaceC1670u
    public final boolean O() {
        return this.o.f18088v;
    }

    @Override // k3.InterfaceC1670u
    public final long O0() {
        return this.o.f18067A;
    }

    @Override // k3.InterfaceC1670u
    public final long P() {
        return this.o.c.f17738i;
    }

    @Override // k3.InterfaceC1670u
    public final z1 P0() {
        return this.f17918s;
    }

    @Override // k3.InterfaceC1670u
    public final int Q() {
        return this.o.c.f17732a.f14009e;
    }

    @Override // k3.InterfaceC1670u
    public final Bundle Q0() {
        return this.f17907f;
    }

    @Override // k3.InterfaceC1670u
    public final C1290c R() {
        return this.o.f18082p;
    }

    @Override // k3.InterfaceC1670u
    public final d5.I R0() {
        return this.f17917r;
    }

    @Override // k3.InterfaceC1670u
    public final void S(TextureView textureView) {
        if (g1(27) && textureView != null && this.f17924y == textureView) {
            W0();
        }
    }

    @Override // k3.InterfaceC1670u
    public final void S0() {
        InterfaceC1655m interfaceC1655m;
        E1 e12 = this.f17906e;
        int iB = e12.f17758a.b();
        D1 d12 = e12.f17758a;
        C1672v c1672v = this.f17904a;
        Context context = this.d;
        Bundle bundle = this.f17907f;
        if (iB == 0) {
            this.f17913m = null;
            Object objP = d12.p();
            AbstractC1346c.k(objP);
            IBinder iBinder = (IBinder) objP;
            int i7 = m1.f18013i;
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("androidx.media3.session.IMediaSession");
            if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1655m)) {
                C1653l c1653l = new C1653l();
                c1653l.c = iBinder;
                interfaceC1655m = c1653l;
            } else {
                interfaceC1655m = (InterfaceC1655m) iInterfaceQueryLocalInterface;
            }
            int iU = this.f17905b.u();
            String packageName = context.getPackageName();
            int iMyPid = Process.myPid();
            c1672v.getClass();
            try {
                interfaceC1655m.D(this.c, iU, new C1642g(packageName, iMyPid, bundle).b());
                return;
            } catch (RemoteException e10) {
                AbstractC1346c.A("MCImplBase", "Failed to call connection request.", e10);
            }
        } else {
            this.f17913m = new Y(this, bundle);
            int i10 = AbstractC1343A.f15195a >= 29 ? 4097 : 1;
            Intent intent = new Intent("androidx.media3.session.MediaSessionService");
            intent.setClassName(d12.getPackageName(), d12.q());
            if (context.bindService(intent, this.f17913m, i10)) {
                return;
            }
            AbstractC1346c.z("MCImplBase", "bind to " + e12 + " failed");
        }
        Objects.requireNonNull(c1672v);
        c1672v.X0(new A2.l(c1672v, 21));
    }

    @Override // k3.InterfaceC1670u
    public final e2.q0 T() {
        return this.o.l;
    }

    @Override // k3.InterfaceC1670u
    public final h5.y T0(y1 y1Var, Bundle bundle) {
        InterfaceC1655m interfaceC1655m;
        io.sentry.Y0 y02 = new io.sentry.Y0(this, y1Var, bundle, 2);
        AbstractC1346c.c(y1Var.f18167a == 0);
        z1 z1Var = this.f17918s;
        z1Var.getClass();
        if (z1Var.f18173a.contains(y1Var)) {
            interfaceC1655m = this.f17899A;
        } else {
            AbstractC1346c.z("MCImplBase", "Controller isn't allowed to call custom session command:" + y1Var.f18168b);
            interfaceC1655m = null;
        }
        return Z0(interfaceC1655m, y02, false);
    }

    @Override // k3.InterfaceC1670u
    public final void U(C1195c c1195c, boolean z6) {
        if (g1(35)) {
            a1(new C1619M(this, c1195c, z6));
            if (this.o.o.equals(c1195c)) {
                return;
            }
            this.o = this.o.a(c1195c);
            C1620N c1620n = new C1620N(c1195c, 0);
            h2.o oVar = this.f17910i;
            oVar.c(20, c1620n);
            oVar.b();
        }
    }

    public final void U0(int i7, List list) {
        if (list.isEmpty()) {
            return;
        }
        if (this.o.f18078j.p()) {
            v1(list, -1, -9223372036854775807L, false);
        } else {
            x1(h1(this.o, Math.min(i7, this.o.f18078j.o()), list, N0(), x()), 0, null, null, this.o.f18078j.p() ? 3 : null);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void V(e2.F f10) {
        if (g1(31)) {
            a1(new C1458d0(this, 13, f10));
            v1(Collections.singletonList(f10), -1, -9223372036854775807L, true);
        }
    }

    public final void V0() {
        TextureView textureView = this.f17924y;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(null);
            this.f17924y = null;
        }
        SurfaceHolder surfaceHolder = this.f17923x;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.f17909h);
            this.f17923x = null;
        }
        if (this.f17922w != null) {
            this.f17922w = null;
        }
    }

    @Override // k3.InterfaceC1670u
    public final void W() {
        if (g1(6)) {
            a1(new C1678y(this, 5));
            if (f1() != -1) {
                s1(-9223372036854775807L, f1());
            }
        }
    }

    public final void W0() {
        if (g1(27)) {
            V0();
            b1(new C1678y(this, 20));
            l1(0, 0);
        }
    }

    @Override // k3.InterfaceC1670u
    public final float X() {
        return this.o.f18081n;
    }

    @Override // k3.InterfaceC1670u
    public final void Y() {
        if (g1(4)) {
            a1(new C1678y(this, 4));
            s1(-9223372036854775807L, c1(this.o));
        }
    }

    @Override // k3.InterfaceC1670u
    public final C1195c Z() {
        return this.o.o;
    }

    public final h5.y Z0(InterfaceC1655m interfaceC1655m, X x8, boolean z6) {
        int iU;
        x1 x1Var;
        if (interfaceC1655m == null) {
            return l9.l.T(new C1(-4));
        }
        E0.X x9 = this.f17905b;
        C1 c1 = new C1(1);
        synchronized (x9.o) {
            try {
                iU = x9.u();
                x1Var = new x1(iU, c1);
                if (x9.f1596n) {
                    x1Var.n();
                } else {
                    ((C2465e) x9.f1597p).put(Integer.valueOf(iU), x1Var);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z6) {
            this.f17912k.add(Integer.valueOf(iU));
        }
        try {
            x8.h(interfaceC1655m, iU);
        } catch (RemoteException e10) {
            AbstractC1346c.A("MCImplBase", "Cannot connect to the service or the session is gone", e10);
            this.f17912k.remove(Integer.valueOf(iU));
            this.f17905b.v(iU, new C1(-100));
        }
        return x1Var;
    }

    @Override // k3.InterfaceC1670u
    public final void a() {
        InterfaceC1655m interfaceC1655m = this.f17899A;
        if (this.f17914n) {
            return;
        }
        this.f17914n = true;
        this.l = null;
        M.t tVar = this.f17911j;
        Handler handler = (Handler) tVar.l;
        if (handler.hasMessages(1)) {
            try {
                C1628b0 c1628b0 = (C1628b0) tVar.f4151m;
                c1628b0.f17899A.p(c1628b0.c);
            } catch (RemoteException unused) {
                AbstractC1346c.z("MCImplBase", "Error in sending flushCommandQueue");
            }
        }
        handler.removeCallbacksAndMessages(null);
        this.f17899A = null;
        if (interfaceC1655m != null) {
            int iU = this.f17905b.u();
            try {
                interfaceC1655m.asBinder().unlinkToDeath(this.f17908g, 0);
                interfaceC1655m.B(this.c, iU);
            } catch (RemoteException unused2) {
            }
        }
        this.f17910i.d();
        E0.X x8 = this.f17905b;
        A2.l lVar = new A2.l(this, 22);
        synchronized (x8.o) {
            try {
                Handler handlerL = AbstractC1343A.l(null);
                x8.f1599r = handlerL;
                x8.f1598q = lVar;
                if (((C2465e) x8.f1597p).isEmpty()) {
                    x8.a();
                } else {
                    handlerL.postDelayed(new A2.l(x8, 24), 30000L);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final int a0() {
        return this.o.c.f17732a.f14012h;
    }

    public final void a1(X x8) {
        M.t tVar = this.f17911j;
        if (((C1628b0) tVar.f4151m).f17899A != null) {
            Handler handler = (Handler) tVar.l;
            if (!handler.hasMessages(1)) {
                handler.sendEmptyMessage(1);
            }
        }
        Z0(this.f17899A, x8, true);
    }

    @Override // k3.InterfaceC1670u
    public final void b() {
        if (g1(1)) {
            a1(new C1678y(this, 11));
            w1(false);
        }
    }

    @Override // k3.InterfaceC1670u
    public final int b0() {
        return c1(this.o);
    }

    public final void b1(X x8) {
        M.t tVar = this.f17911j;
        if (((C1628b0) tVar.f4151m).f17899A != null) {
            Handler handler = (Handler) tVar.l;
            if (!handler.hasMessages(1)) {
                handler.sendEmptyMessage(1);
            }
        }
        h5.y yVarZ0 = Z0(this.f17899A, x8, true);
        try {
            AbstractC1663q.t(yVarZ0);
        } catch (ExecutionException e10) {
            throw new IllegalStateException(e10);
        } catch (TimeoutException e11) {
            if (yVarZ0 instanceof x1) {
                int i7 = ((x1) yVarZ0).f18157s;
                this.f17912k.remove(Integer.valueOf(i7));
                this.f17905b.v(i7, new C1(-1));
            }
            AbstractC1346c.A("MCImplBase", "Synchronous command takes too long on the session side.", e11);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void c() {
        if (g1(2)) {
            a1(new C1678y(this, 15));
            q1 q1Var = this.o;
            if (q1Var.f18091y == 1) {
                x1(q1Var.f(q1Var.f18078j.p() ? 4 : 2, null), null, null, null, null);
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void c0(final int i7, final boolean z6) {
        if (g1(34)) {
            a1(new X() { // from class: k3.D
                @Override // k3.X
                public final void h(InterfaceC1655m interfaceC1655m, int i10) {
                    interfaceC1655m.b1(this.l.c, i10, z6, i7);
                }
            });
            q1 q1Var = this.o;
            if (q1Var.f18085s != z6) {
                this.o = q1Var.c(q1Var.f18084r, z6);
                C1611E c1611e = new C1611E(this, z6, 0);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1611e);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final boolean d() {
        return this.o.f18089w;
    }

    @Override // k3.InterfaceC1670u
    public final C1202j d0() {
        return this.o.f18083q;
    }

    public final int d1() {
        if (this.o.f18078j.p()) {
            return -1;
        }
        q1 q1Var = this.o;
        e2.f0 f0Var = q1Var.f18078j;
        int iC1 = c1(q1Var);
        q1 q1Var2 = this.o;
        int i7 = q1Var2.f18076h;
        if (i7 == 1) {
            i7 = 0;
        }
        return f0Var.e(iC1, i7, q1Var2.f18077i);
    }

    @Override // k3.InterfaceC1670u
    public final int e() {
        return this.o.f18091y;
    }

    @Override // k3.InterfaceC1670u
    public final void e0() {
        if (g1(26)) {
            a1(new C1678y(this, 17));
            q1 q1Var = this.o;
            int i7 = q1Var.f18084r - 1;
            if (i7 >= q1Var.f18083q.f14128b) {
                this.o = q1Var.c(i7, q1Var.f18085s);
                C1676x c1676x = new C1676x(this, i7, 9);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1676x);
                oVar.b();
            }
        }
    }

    public final C1406e e1(e2.f0 f0Var, int i7, long j3) {
        if (f0Var.p()) {
            return null;
        }
        e2.e0 e0Var = new e2.e0();
        e2.c0 c0Var = new e2.c0();
        if (i7 == -1 || i7 >= f0Var.o()) {
            i7 = f0Var.a(this.o.f18077i);
            j3 = AbstractC1343A.T(f0Var.m(i7, e0Var, 0L).l);
        }
        long jH = AbstractC1343A.H(j3);
        AbstractC1346c.f(i7, f0Var.o());
        f0Var.n(i7, e0Var);
        if (jH == -9223372036854775807L) {
            jH = e0Var.l;
            if (jH == -9223372036854775807L) {
                return null;
            }
        }
        int i10 = e0Var.f14095n;
        f0Var.f(i10, c0Var, false);
        while (i10 < e0Var.o && c0Var.f14061e != jH) {
            int i11 = i10 + 1;
            if (f0Var.f(i11, c0Var, false).f14061e > jH) {
                break;
            }
            i10 = i11;
        }
        f0Var.f(i10, c0Var, false);
        return new C1406e(jH - c0Var.f14061e, i10);
    }

    @Override // k3.InterfaceC1670u
    public final void f(e2.P p10) {
        if (g1(13)) {
            a1(new C1458d0(this, 12, p10));
            if (this.o.f18075g.equals(p10)) {
                return;
            }
            this.o = this.o.e(p10);
            C1609C c1609c = new C1609C(p10, 1);
            h2.o oVar = this.f17910i;
            oVar.c(12, c1609c);
            oVar.b();
        }
    }

    @Override // k3.InterfaceC1670u
    public final void f0(e2.l0 l0Var) {
        if (g1(29)) {
            a1(new C1458d0(this, 10, l0Var));
            q1 q1Var = this.o;
            if (l0Var != q1Var.f18070E) {
                this.o = q1Var.o(l0Var);
                C1618L c1618l = new C1618L(l0Var, 0);
                h2.o oVar = this.f17910i;
                oVar.c(19, c1618l);
                oVar.b();
            }
        }
    }

    public final int f1() {
        if (this.o.f18078j.p()) {
            return -1;
        }
        q1 q1Var = this.o;
        e2.f0 f0Var = q1Var.f18078j;
        int iC1 = c1(q1Var);
        q1 q1Var2 = this.o;
        int i7 = q1Var2.f18076h;
        if (i7 == 1) {
            i7 = 0;
        }
        return f0Var.k(iC1, i7, q1Var2.f18077i);
    }

    @Override // k3.InterfaceC1670u
    public final void g() {
        MediaController mediaController;
        if (!g1(1)) {
            AbstractC1346c.z("MCImplBase", "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
            return;
        }
        if (AbstractC1343A.f15195a >= 31 && (mediaController = this.B) != null) {
            mediaController.sendCommand("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST", null, null);
        }
        a1(new C1678y(this, 13));
        w1(true);
    }

    @Override // k3.InterfaceC1670u
    public final void g0(int i7, int i10) {
        if (g1(33)) {
            a1(new C1607A(this, i7, i10, 1));
            q1 q1Var = this.o;
            C1202j c1202j = q1Var.f18083q;
            if (q1Var.f18084r == i7 || c1202j.f14128b > i7) {
                return;
            }
            int i11 = c1202j.c;
            if (i11 == 0 || i7 <= i11) {
                this.o = q1Var.c(i7, q1Var.f18085s);
                C1676x c1676x = new C1676x(this, i7, 1);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1676x);
                oVar.b();
            }
        }
    }

    public final boolean g1(int i7) {
        if (this.f17921v.a(i7)) {
            return true;
        }
        AbstractC1347d.p("Controller isn't allowed to call command= ", i7, "MCImplBase");
        return false;
    }

    @Override // k3.InterfaceC1670u
    public final void h(int i7) {
        if (g1(15)) {
            a1(new C1676x(this, i7, 6));
            q1 q1Var = this.o;
            if (q1Var.f18076h != i7) {
                this.o = q1Var.i(i7);
                C1616J c1616j = new C1616J(i7, 0);
                h2.o oVar = this.f17910i;
                oVar.c(8, c1616j);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void h0(boolean z6) {
        if (g1(26)) {
            a1(new C1611E(this, z6, 2));
            q1 q1Var = this.o;
            if (q1Var.f18085s != z6) {
                this.o = q1Var.c(q1Var.f18084r, z6);
                C1611E c1611e = new C1611E(this, z6, 3);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1611e);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final int i() {
        return this.o.f18076h;
    }

    @Override // k3.InterfaceC1670u
    public final boolean i0() {
        return f1() != -1;
    }

    @Override // k3.InterfaceC1670u
    public final e2.P j() {
        return this.o.f18075g;
    }

    @Override // k3.InterfaceC1670u
    public final void j0(int i7) {
        if (g1(34)) {
            a1(new C1676x(this, i7, 4));
            q1 q1Var = this.o;
            int i10 = q1Var.f18084r + 1;
            int i11 = q1Var.f18083q.c;
            if (i11 == 0 || i10 <= i11) {
                this.o = q1Var.c(i10, q1Var.f18085s);
                C1676x c1676x = new C1676x(this, i10, 5);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1676x);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void k(long j3) {
        if (g1(5)) {
            a1(new io.sentry.instrumentation.file.c(j3, this));
            s1(j3, c1(this.o));
        }
    }

    @Override // k3.InterfaceC1670u
    public final int k0() {
        return this.o.c.f17732a.f14013i;
    }

    @Override // k3.InterfaceC1670u
    public final void l(float f10) {
        if (g1(13)) {
            a1(new C1608B(f10, 0, this));
            e2.P p10 = this.o.f18075g;
            if (p10.f13995a != f10) {
                e2.P p11 = new e2.P(f10, p10.f13996b);
                this.o = this.o.e(p11);
                C1609C c1609c = new C1609C(p11, 0);
                h2.o oVar = this.f17910i;
                oVar.c(12, c1609c);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void l0(SurfaceView surfaceView) {
        if (g1(27)) {
            SurfaceHolder holder = surfaceView == null ? null : surfaceView.getHolder();
            if (g1(27)) {
                if (holder == null) {
                    W0();
                    return;
                }
                if (this.f17923x == holder) {
                    return;
                }
                V0();
                this.f17923x = holder;
                holder.addCallback(this.f17909h);
                Surface surface = holder.getSurface();
                if (surface == null || !surface.isValid()) {
                    this.f17922w = null;
                    b1(new C1678y(this, 0));
                    l1(0, 0);
                } else {
                    this.f17922w = surface;
                    b1(new C1617K(this, surface, 1));
                    Rect surfaceFrame = holder.getSurfaceFrame();
                    l1(surfaceFrame.width(), surfaceFrame.height());
                }
            }
        }
    }

    public final void l1(int i7, int i10) {
        h2.u uVar = this.f17925z;
        if (uVar.f15243a == i7 && uVar.f15244b == i10) {
            return;
        }
        this.f17925z = new h2.u(i7, i10);
        this.f17910i.f(24, new W(i7, i10, 0));
    }

    @Override // k3.InterfaceC1670u
    public final void m(final List list, final int i7, final long j3) {
        if (g1(20)) {
            a1(new X() { // from class: k3.P
                @Override // k3.X
                public final void h(InterfaceC1655m interfaceC1655m, int i10) {
                    C1628b0 c1628b0 = this.l;
                    c1628b0.getClass();
                    C1077F c1077fU = d5.I.u();
                    int i11 = 0;
                    while (true) {
                        List list2 = list;
                        if (i11 >= list2.size()) {
                            interfaceC1655m.V0(c1628b0.c, i10, new BinderC1198f(c1077fU.h()), i7, j3);
                            return;
                        }
                        c1077fU.a(((e2.F) list2.get(i11)).d(true));
                        i11++;
                    }
                }
            });
            v1(list, i7, j3, false);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void m0(SurfaceView surfaceView) {
        if (g1(27)) {
            SurfaceHolder holder = surfaceView == null ? null : surfaceView.getHolder();
            if (g1(27) && holder != null && this.f17923x == holder) {
                W0();
            }
        }
    }

    public final void m1(int i7, int i10, int i11) {
        int i12;
        int i13;
        e2.f0 f0Var = this.o.f18078j;
        int iO = f0Var.o();
        int iMin = Math.min(i10, iO);
        int i14 = iMin - i7;
        int iMin2 = Math.min(i11, iO - i14);
        if (i7 >= iO || i7 == iMin || i7 == iMin2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i15 = 0; i15 < iO; i15++) {
            arrayList.add(f0Var.m(i15, new e2.e0(), 0L));
        }
        AbstractC1343A.G(arrayList, i7, iMin, iMin2);
        o1(f0Var, arrayList, arrayList2);
        e2.d0 d0VarY0 = Y0(arrayList, arrayList2);
        if (d0VarY0.p()) {
            return;
        }
        int iC1 = c1(this.o);
        if (iC1 >= i7 && iC1 < iMin) {
            i13 = (iC1 - i7) + iMin2;
        } else {
            if (iMin > iC1 || iMin2 <= iC1) {
                i12 = (iMin <= iC1 || iMin2 > iC1) ? iC1 : i14 + iC1;
                e2.e0 e0Var = new e2.e0();
                int i16 = this.o.c.f17732a.f14009e - f0Var.m(iC1, e0Var, 0L).f14095n;
                d0VarY0.m(i12, e0Var, 0L);
                x1(j1(this.o, d0VarY0, i12, e0Var.f14095n + i16, N0(), x(), 5), 0, null, null, null);
            }
            i13 = iC1 - i14;
        }
        i12 = i13;
        e2.e0 e0Var2 = new e2.e0();
        int i162 = this.o.c.f17732a.f14009e - f0Var.m(iC1, e0Var2, 0L).f14095n;
        d0VarY0.m(i12, e0Var2, 0L);
        x1(j1(this.o, d0VarY0, i12, e0Var2.f14095n + i162, N0(), x(), 5), 0, null, null, null);
    }

    @Override // k3.InterfaceC1670u
    public final e2.O n() {
        return this.o.f18071a;
    }

    @Override // k3.InterfaceC1670u
    public final void n0(int i7, int i10) {
        if (g1(20)) {
            AbstractC1346c.c(i7 >= 0 && i10 >= 0);
            a1(new C1607A(this, i7, i10, 2));
            m1(i7, i7 + 1, i10);
        }
    }

    public final void n1(q1 q1Var, final q1 q1Var2, final Integer num, final Integer num2, final Integer num3, Integer num4) {
        h2.o oVar = this.f17910i;
        if (num != null) {
            final int i7 = 0;
            oVar.c(0, new h2.l() { // from class: k3.Q
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i7) {
                        case 0:
                            q1 q1Var3 = q1Var2;
                            u4.N(q1Var3.f18078j, num.intValue());
                            break;
                        case 1:
                            q1 q1Var4 = q1Var2;
                            u4.t(num.intValue(), q1Var4.d, q1Var4.f18073e);
                            break;
                        default:
                            u4.q(num.intValue(), q1Var2.f18086t);
                            break;
                    }
                }
            });
        }
        if (num3 != null) {
            final int i10 = 1;
            oVar.c(11, new h2.l() { // from class: k3.Q
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i10) {
                        case 0:
                            q1 q1Var3 = q1Var2;
                            u4.N(q1Var3.f18078j, num3.intValue());
                            break;
                        case 1:
                            q1 q1Var4 = q1Var2;
                            u4.t(num3.intValue(), q1Var4.d, q1Var4.f18073e);
                            break;
                        default:
                            u4.q(num3.intValue(), q1Var2.f18086t);
                            break;
                    }
                }
            });
        }
        e2.F fS = q1Var2.s();
        if (num4 != null) {
            oVar.c(1, new C1458d0(fS, 11, num4));
        }
        e2.O o = q1Var.f18071a;
        e2.O o4 = q1Var2.f18071a;
        if (o != o4 && (o == null || !o.a(o4))) {
            oVar.c(10, new T(0, o4));
            if (o4 != null) {
                oVar.c(10, new T(1, o4));
            }
        }
        if (!q1Var.f18069D.equals(q1Var2.f18069D)) {
            final int i11 = 17;
            oVar.c(2, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i11) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.f18092z.equals(q1Var2.f18092z)) {
            final int i12 = 18;
            oVar.c(14, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i12) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18089w != q1Var2.f18089w) {
            final int i13 = 19;
            oVar.c(3, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i13) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18091y != q1Var2.f18091y) {
            final int i14 = 20;
            oVar.c(4, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i14) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (num2 != null) {
            final int i15 = 2;
            oVar.c(5, new h2.l() { // from class: k3.Q
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i15) {
                        case 0:
                            q1 q1Var3 = q1Var2;
                            u4.N(q1Var3.f18078j, num2.intValue());
                            break;
                        case 1:
                            q1 q1Var4 = q1Var2;
                            u4.t(num2.intValue(), q1Var4.d, q1Var4.f18073e);
                            break;
                        default:
                            u4.q(num2.intValue(), q1Var2.f18086t);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18090x != q1Var2.f18090x) {
            final int i16 = 0;
            oVar.c(6, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i16) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18088v != q1Var2.f18088v) {
            final int i17 = 1;
            oVar.c(7, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i17) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.f18075g.equals(q1Var2.f18075g)) {
            final int i18 = 2;
            oVar.c(12, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i18) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18076h != q1Var2.f18076h) {
            final int i19 = 3;
            oVar.c(8, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i19) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18077i != q1Var2.f18077i) {
            final int i20 = 4;
            oVar.c(9, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i20) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.f18080m.equals(q1Var2.f18080m)) {
            final int i21 = 5;
            oVar.c(15, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i21) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18081n != q1Var2.f18081n) {
            final int i22 = 6;
            oVar.c(22, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i22) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.o.equals(q1Var2.o)) {
            final int i23 = 7;
            oVar.c(20, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i23) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.f18082p.f14801a.equals(q1Var2.f18082p.f14801a)) {
            final int i24 = 8;
            oVar.c(27, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i24) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
            final int i25 = 9;
            oVar.c(27, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i25) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.f18083q.equals(q1Var2.f18083q)) {
            final int i26 = 10;
            oVar.c(29, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i26) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18084r != q1Var2.f18084r || q1Var.f18085s != q1Var2.f18085s) {
            final int i27 = 11;
            oVar.c(30, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i27) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.l.equals(q1Var2.l)) {
            final int i28 = 12;
            oVar.c(25, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i28) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18067A != q1Var2.f18067A) {
            final int i29 = 13;
            oVar.c(16, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i29) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.B != q1Var2.B) {
            final int i30 = 14;
            oVar.c(17, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i30) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (q1Var.f18068C != q1Var2.f18068C) {
            final int i31 = 15;
            oVar.c(18, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i31) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        if (!q1Var.f18070E.equals(q1Var2.f18070E)) {
            final int i32 = 16;
            oVar.c(19, new h2.l() { // from class: k3.S
                @Override // h2.l
                public final void b(Object obj) {
                    e2.U u4 = (e2.U) obj;
                    switch (i32) {
                        case 0:
                            u4.e(q1Var2.f18090x);
                            break;
                        case 1:
                            u4.T(q1Var2.f18088v);
                            break;
                        case 2:
                            u4.G(q1Var2.f18075g);
                            break;
                        case 3:
                            u4.d(q1Var2.f18076h);
                            break;
                        case 4:
                            u4.I(q1Var2.f18077i);
                            break;
                        case 5:
                            u4.z(q1Var2.f18080m);
                            break;
                        case 6:
                            u4.r(q1Var2.f18081n);
                            break;
                        case 7:
                            u4.D(q1Var2.o);
                            break;
                        case 8:
                            u4.o(q1Var2.f18082p.f14801a);
                            break;
                        case 9:
                            u4.R(q1Var2.f18082p);
                            break;
                        case 10:
                            u4.A(q1Var2.f18083q);
                            break;
                        case 11:
                            q1 q1Var3 = q1Var2;
                            u4.S(q1Var3.f18084r, q1Var3.f18085s);
                            break;
                        case 12:
                            u4.M(q1Var2.l);
                            break;
                        case 13:
                            u4.g(q1Var2.f18067A);
                            break;
                        case 14:
                            u4.j(q1Var2.B);
                            break;
                        case 15:
                            u4.y(q1Var2.f18068C);
                            break;
                        case 16:
                            u4.w(q1Var2.f18070E);
                            break;
                        case 17:
                            u4.J(q1Var2.f18069D);
                            break;
                        case 18:
                            u4.F(q1Var2.f18092z);
                            break;
                        case 19:
                            u4.k(q1Var2.f18089w);
                            break;
                        default:
                            u4.x(q1Var2.f18091y);
                            break;
                    }
                }
            });
        }
        oVar.b();
    }

    @Override // k3.InterfaceC1670u
    public final int o() {
        return this.o.f18084r;
    }

    @Override // k3.InterfaceC1670u
    public final void o0(final int i7, final int i10, final int i11) {
        if (g1(20)) {
            AbstractC1346c.c(i7 >= 0 && i7 <= i10 && i11 >= 0);
            a1(new X() { // from class: k3.F
                @Override // k3.X
                public final void h(InterfaceC1655m interfaceC1655m, int i12) {
                    interfaceC1655m.P0(this.l.c, i12, i7, i10, i11);
                }
            });
            m1(i7, i10, i11);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void p(int i7, e2.F f10) {
        if (g1(20)) {
            AbstractC1346c.c(i7 >= 0);
            a1(new V(this, i7, f10, 1));
            q1(i7, i7 + 1, d5.I.B(f10));
        }
    }

    @Override // k3.InterfaceC1670u
    public final int p0() {
        return this.o.f18090x;
    }

    public final void p1(int i7, int i10) {
        int iO = this.o.f18078j.o();
        int iMin = Math.min(i10, iO);
        if (i7 >= iO || i7 == iMin || iO == 0) {
            return;
        }
        boolean z6 = c1(this.o) >= i7 && c1(this.o) < iMin;
        q1 q1VarI1 = i1(this.o, i7, iMin, false, N0(), x());
        int i11 = this.o.c.f17732a.f14008b;
        x1(q1VarI1, 0, null, z6 ? 4 : null, i11 >= i7 && i11 < iMin ? 3 : null);
    }

    @Override // k3.InterfaceC1670u
    public final void q(boolean z6) {
        if (g1(1)) {
            a1(new C1611E(this, z6, 4));
            w1(z6);
        } else if (z6) {
            AbstractC1346c.z("MCImplBase", "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
        }
    }

    @Override // k3.InterfaceC1670u
    public final void q0(int i7, int i10, List list) {
        if (g1(20)) {
            AbstractC1346c.c(i7 >= 0 && i7 <= i10);
            a1(new io.sentry.instrumentation.file.d(this, list, i7, i10, 2));
            q1(i7, i10, list);
        }
    }

    public final void q1(int i7, int i10, List list) {
        int iO = this.o.f18078j.o();
        if (i7 > iO) {
            return;
        }
        if (this.o.f18078j.p()) {
            v1(list, -1, -9223372036854775807L, false);
            return;
        }
        int iMin = Math.min(i10, iO);
        q1 q1VarI1 = i1(h1(this.o, iMin, list, N0(), x()), i7, iMin, true, N0(), x());
        int i11 = this.o.c.f17732a.f14008b;
        boolean z6 = i11 >= i7 && i11 < iMin;
        x1(q1VarI1, 0, null, z6 ? 4 : null, z6 ? 3 : null);
    }

    @Override // k3.InterfaceC1670u
    public final void r(Surface surface) {
        if (g1(27)) {
            V0();
            this.f17922w = surface;
            b1(new C1617K(this, surface, 0));
            int i7 = surface == null ? 0 : -1;
            l1(i7, i7);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void r0(List list) {
        if (g1(20)) {
            a1(new U(this, list, 1));
            U0(this.o.f18078j.o(), list);
        }
    }

    @Override // k3.InterfaceC1670u
    public final boolean s() {
        return this.o.c.f17733b;
    }

    @Override // k3.InterfaceC1670u
    public final long s0() {
        return this.o.c.d;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x022c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s1(long r53, int r55) {
        /*
            Method dump skipped, instruction units count: 569
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1628b0.s1(long, int):void");
    }

    @Override // k3.InterfaceC1670u
    public final void stop() {
        if (g1(3)) {
            a1(new C1678y(this, 14));
            q1 q1Var = this.o;
            B1 b12 = this.o.c;
            e2.V v10 = b12.f17732a;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            B1 b13 = this.o.c;
            long j3 = b13.d;
            long j10 = b13.f17732a.f14010f;
            int iE = n1.e(j10, j3);
            B1 b14 = this.o.c;
            q1 q1VarJ = q1Var.j(new B1(v10, b12.f17733b, jElapsedRealtime, j3, j10, iE, 0L, b14.f17737h, b14.f17738i, b14.f17732a.f14010f));
            this.o = q1VarJ;
            if (q1VarJ.f18091y != 1) {
                this.o = q1VarJ.f(1, q1VarJ.f18071a);
                C1350a c1350a = new C1350a(16);
                h2.o oVar = this.f17910i;
                oVar.c(4, c1350a);
                oVar.b();
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void t(int i7) {
        if (g1(10)) {
            AbstractC1346c.c(i7 >= 0);
            a1(new C1676x(this, i7, 0));
            s1(-9223372036854775807L, i7);
        }
    }

    @Override // k3.InterfaceC1670u
    public final e2.f0 t0() {
        return this.o.f18078j;
    }

    public final void t1(long j3) {
        long jN0 = N0() + j3;
        long jS0 = s0();
        if (jS0 != -9223372036854775807L) {
            jN0 = Math.min(jN0, jS0);
        }
        s1(Math.max(jN0, 0L), c1(this.o));
    }

    @Override // k3.InterfaceC1670u
    public final long u() {
        return this.o.B;
    }

    @Override // k3.InterfaceC1670u
    public final boolean u0() {
        return this.o.f18085s;
    }

    public final void u1(int i7, h5.v vVar) {
        vVar.b(new h.k(this, vVar, i7, 3), h5.r.l);
    }

    @Override // k3.InterfaceC1670u
    public final boolean v() {
        return this.f17899A != null;
    }

    @Override // k3.InterfaceC1670u
    public final void v0(int i7) {
        if (g1(20)) {
            AbstractC1346c.c(i7 >= 0);
            a1(new C1676x(this, i7, 2));
            p1(i7, i7 + 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v1(java.util.List r66, int r67, long r68, boolean r70) {
        /*
            Method dump skipped, instruction units count: 451
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1628b0.v1(java.util.List, int, long, boolean):void");
    }

    @Override // k3.InterfaceC1670u
    public final long w() {
        return this.o.c.f17737h;
    }

    @Override // k3.InterfaceC1670u
    public final void w0() {
        if (g1(26)) {
            a1(new C1678y(this, 12));
            q1 q1Var = this.o;
            int i7 = q1Var.f18084r + 1;
            int i10 = q1Var.f18083q.c;
            if (i10 == 0 || i7 <= i10) {
                this.o = q1Var.c(i7, q1Var.f18085s);
                C1676x c1676x = new C1676x(this, i7, 3);
                h2.o oVar = this.f17910i;
                oVar.c(30, c1676x);
                oVar.b();
            }
        }
    }

    public final void w1(boolean z6) {
        q1 q1Var = this.o;
        int i7 = q1Var.f18090x;
        int i10 = i7 == 1 ? 0 : i7;
        if (q1Var.f18086t == z6 && i7 == i10) {
            return;
        }
        this.f17900C = n1.f(q1Var, this.f17900C, this.f17901D, this.f17904a.f18138f);
        this.f17901D = SystemClock.elapsedRealtime();
        x1(this.o.d(1, i10, z6), null, 1, null, null);
    }

    @Override // k3.InterfaceC1670u
    public final long x() {
        B1 b12 = this.o.c;
        return !b12.f17733b ? N0() : b12.f17732a.f14011g;
    }

    @Override // k3.InterfaceC1670u
    public final boolean x0() {
        return this.o.f18077i;
    }

    public final void x1(q1 q1Var, Integer num, Integer num2, Integer num3, Integer num4) {
        q1 q1Var2 = this.o;
        this.o = q1Var;
        n1(q1Var2, q1Var, num, num2, num3, num4);
    }

    @Override // k3.InterfaceC1670u
    public final long y() {
        return this.o.c.f17736g;
    }

    @Override // k3.InterfaceC1670u
    public final e2.l0 y0() {
        return this.o.f18070E;
    }

    @Override // k3.InterfaceC1670u
    public final void z(int i7, List list) {
        if (g1(20)) {
            AbstractC1346c.c(i7 >= 0);
            a1(new V(this, i7, list, 0));
            U0(i7, list);
        }
    }

    @Override // k3.InterfaceC1670u
    public final long z0() {
        return this.o.c.f17739j;
    }
}
