package n2;

import A.C0050y0;
import F1.C0250i;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.Spatializer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.util.SparseBooleanArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.image.ImageOutput;
import d5.C1078G;
import d5.d0;
import e2.AbstractC1197e;
import e2.C1195c;
import e2.C1202j;
import e2.C1206n;
import e2.e0;
import e2.f0;
import e2.l0;
import e2.n0;
import e2.q0;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;
import k3.C1613G;
import k3.C1615I;
import k3.C1616J;
import k3.C1618L;
import k3.C1620N;
import k3.C1680z;
import k3.X0;
import n0.C1886o;
import n7.AbstractC1985B;
import o2.C2024a;
import o2.C2025b;
import u2.AbstractC2498a;
import u2.C2492C;
import u2.InterfaceC2491B;
import u2.c0;
import w2.C2594h;
import w2.C2595i;
import w2.C2596j;

/* JADX INFO: renamed from: n2.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1923x extends AbstractC1197e implements ExoPlayer {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C1921v f19727A;
    public final t4.e B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C1904d f19728C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final m6.b f19729D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final C1886o f19730E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final long f19731F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f19732G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f19733H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f19734I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f19735J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f19736K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public c0 f19737L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f19738M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public e2.S f19739N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public e2.I f19740O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public e2.I f19741P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public Object f19742Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public Surface f19743R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public SurfaceHolder f19744S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public A2.o f19745T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f19746U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public TextureView f19747V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final int f19748W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public h2.u f19749X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public C1195c f19750Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public float f19751Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f19752a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public C1290c f19753b0;
    public final w2.s c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final boolean f19754c0;
    public final e2.S d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f19755d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final B2.P f19756e = new B2.P();

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final int f19757e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f19758f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f19759f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e2.W f19760g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final C1202j f19761g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AbstractC1905e[] f19762h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public q0 f19763h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w2.r f19764i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public e2.I f19765i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final h2.x f19766j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public U f19767j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final r f19768k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public int f19769k0;
    public final D l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public long f19770l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final h2.o f19771m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CopyOnWriteArraySet f19772n;
    public final e2.c0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f19773p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f19774q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final InterfaceC2491B f19775r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final o2.e f19776s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Looper f19777t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final x2.d f19778u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final long f19779v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final long f19780w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final long f19781x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final h2.v f19782y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final SurfaceHolderCallbackC1920u f19783z;

    static {
        e2.G.a("media3.exoplayer");
    }

    public C1923x(C1915o c1915o) {
        int i7 = 0;
        try {
            AbstractC1346c.t("ExoPlayerImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.0] [" + AbstractC1343A.f15197e + "]");
            Context context = c1915o.f19700a;
            Context applicationContext = context.getApplicationContext();
            this.f19758f = applicationContext;
            h2.v vVar = c1915o.f19701b;
            o2.e eVar = new o2.e(vVar);
            this.f19776s = eVar;
            this.f19757e0 = c1915o.f19705h;
            this.f19750Y = c1915o.f19706i;
            this.f19748W = c1915o.f19707j;
            this.f19752a0 = false;
            this.f19731F = c1915o.f19713r;
            SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = new SurfaceHolderCallbackC1920u(this);
            this.f19783z = surfaceHolderCallbackC1920u;
            this.f19727A = new C1921v();
            Handler handler = new Handler(c1915o.f19704g);
            AbstractC1905e[] abstractC1905eArrA = ((C1912l) c1915o.c.get()).a(handler, surfaceHolderCallbackC1920u, surfaceHolderCallbackC1920u, surfaceHolderCallbackC1920u, surfaceHolderCallbackC1920u);
            this.f19762h = abstractC1905eArrA;
            AbstractC1346c.j(abstractC1905eArrA.length > 0);
            w2.r rVar = (w2.r) c1915o.f19702e.get();
            this.f19764i = rVar;
            this.f19775r = (InterfaceC2491B) c1915o.d.get();
            x2.d dVar = (x2.d) c1915o.f19703f.get();
            this.f19778u = dVar;
            this.f19774q = c1915o.f19708k;
            b0 b0Var = c1915o.l;
            this.f19779v = c1915o.f19709m;
            this.f19780w = c1915o.f19710n;
            this.f19781x = c1915o.o;
            this.f19738M = false;
            Looper looper = c1915o.f19704g;
            this.f19777t = looper;
            this.f19782y = vVar;
            this.f19760g = this;
            this.f19771m = new h2.o(looper, vVar, new r(this, i7));
            CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
            this.f19772n = copyOnWriteArraySet;
            this.f19773p = new ArrayList();
            this.f19737L = new c0();
            w2.s sVar = new w2.s(new a0[abstractC1905eArrA.length], new w2.q[abstractC1905eArrA.length], n0.f14225b, null);
            this.c = sVar;
            this.o = new e2.c0();
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
            int[] iArr = {1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 31, 20, 30, 21, 35, 22, 24, 27, 28, 32};
            int length = iArr.length;
            int i10 = 0;
            while (i10 < length) {
                int i11 = length;
                int i12 = iArr[i10];
                AbstractC1346c.j(!false);
                sparseBooleanArray.append(i12, true);
                i10++;
                length = i11;
                iArr = iArr;
            }
            rVar.getClass();
            AbstractC1346c.j(!false);
            sparseBooleanArray.append(29, true);
            AbstractC1346c.j(!false);
            C1206n c1206n = new C1206n(sparseBooleanArray);
            this.d = new e2.S(c1206n);
            SparseBooleanArray sparseBooleanArray2 = new SparseBooleanArray();
            int i13 = 0;
            while (i13 < c1206n.f14224a.size()) {
                int iB = c1206n.b(i13);
                AbstractC1346c.j(!false);
                sparseBooleanArray2.append(iB, true);
                i13++;
                c1206n = c1206n;
            }
            AbstractC1346c.j(!false);
            sparseBooleanArray2.append(4, true);
            AbstractC1346c.j(!false);
            sparseBooleanArray2.append(10, true);
            AbstractC1346c.j(!false);
            this.f19739N = new e2.S(new C1206n(sparseBooleanArray2));
            this.f19766j = vVar.a(looper, null);
            r rVar2 = new r(this, 1);
            this.f19768k = rVar2;
            this.f19767j0 = U.i(sVar);
            eVar.a0(this, looper);
            int i14 = AbstractC1343A.f15195a;
            String str = c1915o.f19716u;
            this.l = new D(abstractC1905eArrA, rVar, sVar, new C1910j(), dVar, this.f19732G, this.f19733H, eVar, b0Var, c1915o.f19711p, c1915o.f19712q, this.f19738M, looper, vVar, rVar2, i14 < 31 ? new o2.l(str) : AbstractC1985B.s(applicationContext, this, c1915o.f19714s, str));
            this.f19751Z = 1.0f;
            this.f19732G = 0;
            e2.I i15 = e2.I.f13914K;
            this.f19740O = i15;
            this.f19741P = i15;
            this.f19765i0 = i15;
            this.f19769k0 = -1;
            AudioManager audioManager = (AudioManager) this.f19758f.getSystemService("audio");
            int iGenerateAudioSessionId = audioManager == null ? -1 : audioManager.generateAudioSessionId();
            this.f19753b0 = C1290c.c;
            this.f19754c0 = true;
            D0(this.f19776s);
            Handler handler2 = new Handler(looper);
            o2.e eVar2 = this.f19776s;
            x2.h hVar = (x2.h) dVar;
            hVar.getClass();
            eVar2.getClass();
            C0250i c0250i = hVar.f23740b;
            c0250i.getClass();
            CopyOnWriteArrayList<x2.c> copyOnWriteArrayList = c0250i.f2338a;
            for (x2.c cVar : copyOnWriteArrayList) {
                if (cVar.f23724b == eVar2) {
                    cVar.c = true;
                    copyOnWriteArrayList.remove(cVar);
                }
            }
            copyOnWriteArrayList.add(new x2.c(handler2, eVar2));
            copyOnWriteArraySet.add(this.f19783z);
            t4.e eVar3 = new t4.e(context, handler, this.f19783z);
            this.B = eVar3;
            eVar3.n();
            C1904d c1904d = new C1904d(context, handler, this.f19783z);
            this.f19728C = c1904d;
            c1904d.b(null);
            m6.b bVar = new m6.b();
            context.getApplicationContext();
            this.f19729D = bVar;
            this.f19730E = new C1886o(context);
            E5.z zVar = new E5.z(0);
            zVar.c = 0;
            zVar.d = 0;
            this.f19761g0 = zVar.c();
            this.f19763h0 = q0.d;
            this.f19749X = h2.u.c;
            this.f19764i.a(this.f19750Y);
            r1(1, 10, Integer.valueOf(iGenerateAudioSessionId));
            r1(2, 10, Integer.valueOf(iGenerateAudioSessionId));
            r1(1, 3, this.f19750Y);
            r1(2, 4, Integer.valueOf(this.f19748W));
            r1(2, 5, 0);
            r1(1, 9, Boolean.valueOf(this.f19752a0));
            r1(2, 7, this.f19727A);
            r1(6, 8, this.f19727A);
            r1(-1, 16, Integer.valueOf(this.f19757e0));
            this.f19756e.c();
        } catch (Throwable th) {
            this.f19756e.c();
            throw th;
        }
    }

    public static long k1(U u4) {
        e0 e0Var = new e0();
        e2.c0 c0Var = new e2.c0();
        u4.f19586a.g(u4.f19587b.f22663a, c0Var);
        long j3 = u4.c;
        if (j3 != -9223372036854775807L) {
            return c0Var.f14061e + j3;
        }
        return u4.f19586a.m(c0Var.c, e0Var, 0L).l;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final e2.S A() {
        A1();
        return this.f19739N;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void A0(int i7, int i10) {
        A1();
        AbstractC1346c.c(i7 >= 0 && i10 >= i7);
        int size = this.f19773p.size();
        int iMin = Math.min(i10, size);
        if (i7 >= size || i7 == iMin) {
            return;
        }
        U uO1 = o1(this.f19767j0, i7, iMin);
        y1(uO1, 0, !uO1.f19587b.f22663a.equals(this.f19767j0.f19587b.f22663a), 4, h1(uO1), -1, false);
    }

    public final void A1() {
        B2.P p10 = this.f19756e;
        synchronized (p10) {
            boolean z6 = false;
            while (!p10.l) {
                try {
                    p10.wait();
                } catch (InterruptedException unused) {
                    z6 = true;
                }
            }
            if (z6) {
                Thread.currentThread().interrupt();
            }
        }
        if (Thread.currentThread() != this.f19777t.getThread()) {
            String name = Thread.currentThread().getName();
            String name2 = this.f19777t.getThread().getName();
            int i7 = AbstractC1343A.f15195a;
            Locale locale = Locale.US;
            String str = "Player is accessed on the wrong thread.\nCurrent thread: '" + name + "'\nExpected thread: '" + name2 + "'\nSee https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread";
            if (this.f19754c0) {
                throw new IllegalStateException(str);
            }
            AbstractC1346c.A("ExoPlayerImpl", str, this.f19755d0 ? null : new IllegalStateException());
            this.f19755d0 = true;
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long B() {
        A1();
        if (!s()) {
            return z0();
        }
        U u4 = this.f19767j0;
        return u4.f19594k.equals(u4.f19587b) ? AbstractC1343A.T(this.f19767j0.f19598q) : s0();
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void B0(int i7) {
        A1();
    }

    @Override // e2.AbstractC1197e, e2.W
    public final boolean C() {
        A1();
        return this.f19767j0.l;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void D0(e2.U u4) {
        u4.getClass();
        this.f19771m.a(u4);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void F(boolean z6) {
        A1();
        if (this.f19733H != z6) {
            this.f19733H = z6;
            h2.x xVar = this.l.f19525t;
            xVar.getClass();
            h2.w wVarB = h2.x.b();
            wVarB.f15246a = xVar.f15248a.obtainMessage(12, z6 ? 1 : 0, 0);
            wVarB.b();
            C1613G c1613g = new C1613G(4, z6);
            h2.o oVar = this.f19771m;
            oVar.c(9, c1613g);
            w1();
            oVar.b();
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void F0(TextureView textureView) {
        A1();
        if (textureView == null) {
            d1();
            return;
        }
        q1();
        this.f19747V = textureView;
        if (textureView.getSurfaceTextureListener() != null) {
            AbstractC1346c.z("ExoPlayerImpl", "Replacing existing SurfaceTextureListener.");
        }
        textureView.setSurfaceTextureListener(this.f19783z);
        SurfaceTexture surfaceTexture = textureView.isAvailable() ? textureView.getSurfaceTexture() : null;
        if (surfaceTexture == null) {
            u1(null);
            n1(0, 0);
        } else {
            Surface surface = new Surface(surfaceTexture);
            u1(surface);
            this.f19743R = surface;
            n1(textureView.getWidth(), textureView.getHeight());
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void G0(e2.I i7) {
        A1();
        if (i7.equals(this.f19741P)) {
            return;
        }
        this.f19741P = i7;
        this.f19771m.f(15, new r(this, 3));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void H(e2.U u4) {
        A1();
        u4.getClass();
        this.f19771m.e(u4);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void I(int i7) {
        A1();
    }

    @Override // e2.AbstractC1197e, e2.W
    public final n0 J() {
        A1();
        return this.f19767j0.f19592i.d;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void J0(float f10) {
        A1();
        float fH = AbstractC1343A.h(f10, 0.0f, 1.0f);
        if (this.f19751Z == fH) {
            return;
        }
        this.f19751Z = fH;
        r1(1, 2, Float.valueOf(this.f19728C.f19633g * fH));
        this.f19771m.f(22, new C1615I(fH, 3));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final e2.I K0() {
        A1();
        return this.f19740O;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long L() {
        A1();
        return this.f19781x;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void L0(List list) {
        A1();
        ArrayList arrayListE1 = e1(list);
        A1();
        s1(arrayListE1, -1, -9223372036854775807L, true);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final e2.I N() {
        A1();
        return this.f19741P;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long N0() {
        A1();
        return AbstractC1343A.T(h1(this.f19767j0));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long O0() {
        A1();
        return this.f19779v;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int Q() {
        A1();
        if (this.f19767j0.f19586a.p()) {
            return 0;
        }
        U u4 = this.f19767j0;
        return u4.f19586a.b(u4.f19587b.f22663a);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final C1290c R() {
        A1();
        return this.f19753b0;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void S(TextureView textureView) {
        A1();
        if (textureView == null || textureView != this.f19747V) {
            return;
        }
        d1();
    }

    @Override // e2.AbstractC1197e, e2.W
    public final q0 T() {
        A1();
        return this.f19763h0;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final Looper T0() {
        return this.f19777t;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void U(C1195c c1195c, boolean z6) {
        A1();
        if (this.f19759f0) {
            return;
        }
        C1195c c1195c2 = this.f19750Y;
        int i7 = AbstractC1343A.f15195a;
        boolean zEquals = Objects.equals(c1195c2, c1195c);
        h2.o oVar = this.f19771m;
        if (!zEquals) {
            this.f19750Y = c1195c;
            r1(1, 3, c1195c);
            oVar.c(20, new C1620N(c1195c, 1));
        }
        C1195c c1195c3 = z6 ? c1195c : null;
        C1904d c1904d = this.f19728C;
        c1904d.b(c1195c3);
        this.f19764i.a(c1195c);
        boolean zC = C();
        int iD = c1904d.d(e(), zC);
        x1(iD, iD == -1 ? 2 : 1, zC);
        oVar.b();
    }

    @Override // e2.AbstractC1197e
    public final void W0(int i7, long j3, boolean z6) {
        A1();
        if (i7 == -1) {
            return;
        }
        AbstractC1346c.c(i7 >= 0);
        f0 f0Var = this.f19767j0.f19586a;
        if (f0Var.p() || i7 < f0Var.o()) {
            o2.e eVar = this.f19776s;
            if (!eVar.f20411t) {
                C2024a c2024aU = eVar.U();
                eVar.f20411t = true;
                eVar.Z(c2024aU, -1, new C2025b(20));
            }
            this.f19734I++;
            if (s()) {
                AbstractC1346c.z("ExoPlayerImpl", "seekTo ignored because an ad is playing");
                h3.x xVar = new h3.x(this.f19767j0);
                xVar.f(1);
                C1923x c1923x = this.f19768k.f19719m;
                c1923x.f19766j.c(new RunnableC1918s(c1923x, 0, xVar));
                return;
            }
            U uG = this.f19767j0;
            int i10 = uG.f19588e;
            if (i10 == 3 || (i10 == 4 && !f0Var.p())) {
                uG = this.f19767j0.g(2);
            }
            int iB0 = b0();
            U uL1 = l1(uG, f0Var, m1(f0Var, i7, j3));
            this.l.f19525t.a(3, new C(f0Var, i7, AbstractC1343A.H(j3))).b();
            y1(uL1, 0, true, 1, h1(uL1), iB0, z6);
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final float X() {
        A1();
        return this.f19751Z;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final C1195c Z() {
        A1();
        return this.f19750Y;
    }

    public final ArrayList Z0(int i7, List list) {
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < list.size(); i10++) {
            T t10 = new T((AbstractC2498a) list.get(i10), this.f19774q);
            arrayList.add(t10);
            this.f19773p.add(i10 + i7, new C1922w(t10.f19583b, t10.f19582a));
        }
        this.f19737L = this.f19737L.a(i7, arrayList.size());
        return arrayList;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void a() {
        String str;
        boolean z6;
        A2.c cVar;
        C2596j c2596j;
        StringBuilder sb = new StringBuilder("Release ");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" [AndroidXMedia3/1.5.0] [");
        sb.append(AbstractC1343A.f15197e);
        sb.append("] [");
        HashSet hashSet = e2.G.f13882a;
        synchronized (e2.G.class) {
            str = e2.G.f13883b;
        }
        sb.append(str);
        sb.append("]");
        AbstractC1346c.t("ExoPlayerImpl", sb.toString());
        A1();
        this.B.n();
        this.f19729D.getClass();
        this.f19730E.getClass();
        C1904d c1904d = this.f19728C;
        c1904d.c = null;
        c1904d.a();
        c1904d.c(0);
        D d = this.l;
        synchronized (d) {
            if (d.f19499N || !d.f19527v.getThread().isAlive()) {
                z6 = true;
            } else {
                d.f19525t.e(7);
                d.n0(new C1914n(d, 2), d.f19492G);
                z6 = d.f19499N;
            }
        }
        if (!z6) {
            this.f19771m.f(10, new X0(22));
        }
        this.f19771m.d();
        this.f19766j.f15248a.removeCallbacksAndMessages(null);
        x2.d dVar = this.f19778u;
        o2.e eVar = this.f19776s;
        CopyOnWriteArrayList<x2.c> copyOnWriteArrayList = ((x2.h) dVar).f23740b.f2338a;
        for (x2.c cVar2 : copyOnWriteArrayList) {
            if (cVar2.f23724b == eVar) {
                cVar2.c = true;
                copyOnWriteArrayList.remove(cVar2);
            }
        }
        U u4 = this.f19767j0;
        if (u4.f19597p) {
            this.f19767j0 = u4.a();
        }
        U uG = this.f19767j0.g(1);
        this.f19767j0 = uG;
        U uB = uG.b(uG.f19587b);
        this.f19767j0 = uB;
        uB.f19598q = uB.f19600s;
        this.f19767j0.f19599r = 0L;
        o2.e eVar2 = this.f19776s;
        h2.x xVar = eVar2.f20410s;
        AbstractC1346c.k(xVar);
        xVar.c(new A2.l(eVar2, 29));
        w2.o oVar = (w2.o) this.f19764i;
        synchronized (oVar.c) {
            try {
                if (AbstractC1343A.f15195a >= 32 && (cVar = oVar.f23265h) != null && (c2596j = (C2596j) cVar.f312e) != null && ((Handler) cVar.d) != null) {
                    ((Spatializer) cVar.c).removeOnSpatializerStateChangedListener(c2596j);
                    ((Handler) cVar.d).removeCallbacksAndMessages(null);
                    cVar.d = null;
                    cVar.f312e = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        oVar.f23269a = null;
        oVar.f23270b = null;
        q1();
        Surface surface = this.f19743R;
        if (surface != null) {
            surface.release();
            this.f19743R = null;
        }
        this.f19753b0 = C1290c.c;
        this.f19759f0 = true;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int a0() {
        A1();
        if (s()) {
            return this.f19767j0.f19587b.f22664b;
        }
        return -1;
    }

    public final void a1(int i7, List list) {
        A1();
        AbstractC1346c.c(i7 >= 0);
        ArrayList arrayList = this.f19773p;
        int iMin = Math.min(i7, arrayList.size());
        if (!arrayList.isEmpty()) {
            y1(b1(this.f19767j0, iMin, list), 0, false, 5, -9223372036854775807L, -1, false);
            return;
        }
        boolean z6 = this.f19769k0 == -1;
        A1();
        s1(list, -1, -9223372036854775807L, z6);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int b0() {
        A1();
        int iI1 = i1(this.f19767j0);
        if (iI1 == -1) {
            return 0;
        }
        return iI1;
    }

    public final U b1(U u4, int i7, List list) {
        f0 f0Var = u4.f19586a;
        this.f19734I++;
        ArrayList arrayListZ0 = Z0(i7, list);
        Z z6 = new Z(this.f19773p, this.f19737L);
        U uL1 = l1(u4, z6, j1(f0Var, z6, i1(u4), g1(u4)));
        c0 c0Var = this.f19737L;
        h2.x xVar = this.l.f19525t;
        C1925z c1925z = new C1925z(arrayListZ0, c0Var, -1, -9223372036854775807L);
        xVar.getClass();
        h2.w wVarB = h2.x.b();
        wVarB.f15246a = xVar.f15248a.obtainMessage(18, i7, 0, c1925z);
        wVarB.b();
        return uL1;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void c() {
        A1();
        boolean zC = C();
        int iD = this.f19728C.d(2, zC);
        x1(iD, iD == -1 ? 2 : 1, zC);
        U u4 = this.f19767j0;
        if (u4.f19588e != 1) {
            return;
        }
        U uE = u4.e(null);
        U uG = uE.g(uE.f19586a.p() ? 4 : 2);
        this.f19734I++;
        h2.x xVar = this.l.f19525t;
        xVar.getClass();
        h2.w wVarB = h2.x.b();
        wVarB.f15246a = xVar.f15248a.obtainMessage(29);
        wVarB.b();
        y1(uG, 1, false, 5, -9223372036854775807L, -1, false);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void c0(int i7, boolean z6) {
        A1();
    }

    public final e2.I c1() {
        f0 f0VarT0 = t0();
        if (f0VarT0.p()) {
            return this.f19765i0;
        }
        e2.F f10 = f0VarT0.m(b0(), (e0) this.f14069b, 0L).c;
        e2.H hA = this.f19765i0.a();
        e2.I i7 = f10.d;
        if (i7 != null) {
            CharSequence charSequence = i7.f13959a;
            if (charSequence != null) {
                hA.f13892a = charSequence;
            }
            CharSequence charSequence2 = i7.f13960b;
            if (charSequence2 != null) {
                hA.f13893b = charSequence2;
            }
            CharSequence charSequence3 = i7.c;
            if (charSequence3 != null) {
                hA.c = charSequence3;
            }
            CharSequence charSequence4 = i7.d;
            if (charSequence4 != null) {
                hA.d = charSequence4;
            }
            CharSequence charSequence5 = i7.f13961e;
            if (charSequence5 != null) {
                hA.f13894e = charSequence5;
            }
            CharSequence charSequence6 = i7.f13962f;
            if (charSequence6 != null) {
                hA.f13895f = charSequence6;
            }
            CharSequence charSequence7 = i7.f13963g;
            if (charSequence7 != null) {
                hA.f13896g = charSequence7;
            }
            Long l = i7.f13964h;
            if (l != null) {
                hA.c(l);
            }
            e2.X x8 = i7.f13965i;
            if (x8 != null) {
                hA.f13898i = x8;
            }
            e2.X x9 = i7.f13966j;
            if (x9 != null) {
                hA.f13899j = x9;
            }
            byte[] bArr = i7.f13967k;
            Uri uri = i7.f13968m;
            if (uri != null || bArr != null) {
                hA.f13901m = uri;
                hA.b(bArr, i7.l);
            }
            Integer num = i7.f13969n;
            if (num != null) {
                hA.f13902n = num;
            }
            Integer num2 = i7.o;
            if (num2 != null) {
                hA.o = num2;
            }
            Integer num3 = i7.f13970p;
            if (num3 != null) {
                hA.f13903p = num3;
            }
            Boolean bool = i7.f13971q;
            if (bool != null) {
                hA.f13904q = bool;
            }
            Boolean bool2 = i7.f13972r;
            if (bool2 != null) {
                hA.f13905r = bool2;
            }
            Integer num4 = i7.f13973s;
            if (num4 != null) {
                hA.f13906s = num4;
            }
            Integer num5 = i7.f13974t;
            if (num5 != null) {
                hA.f13906s = num5;
            }
            Integer num6 = i7.f13975u;
            if (num6 != null) {
                hA.f13907t = num6;
            }
            Integer num7 = i7.f13976v;
            if (num7 != null) {
                hA.f13908u = num7;
            }
            Integer num8 = i7.f13977w;
            if (num8 != null) {
                hA.f13909v = num8;
            }
            Integer num9 = i7.f13978x;
            if (num9 != null) {
                hA.f13910w = num9;
            }
            Integer num10 = i7.f13979y;
            if (num10 != null) {
                hA.f13911x = num10;
            }
            CharSequence charSequence8 = i7.f13980z;
            if (charSequence8 != null) {
                hA.f13912y = charSequence8;
            }
            CharSequence charSequence9 = i7.f13950A;
            if (charSequence9 != null) {
                hA.f13913z = charSequence9;
            }
            CharSequence charSequence10 = i7.B;
            if (charSequence10 != null) {
                hA.f13884A = charSequence10;
            }
            Integer num11 = i7.f13951C;
            if (num11 != null) {
                hA.B = num11;
            }
            Integer num12 = i7.f13952D;
            if (num12 != null) {
                hA.f13885C = num12;
            }
            CharSequence charSequence11 = i7.f13953E;
            if (charSequence11 != null) {
                hA.f13886D = charSequence11;
            }
            CharSequence charSequence12 = i7.f13954F;
            if (charSequence12 != null) {
                hA.f13887E = charSequence12;
            }
            CharSequence charSequence13 = i7.f13955G;
            if (charSequence13 != null) {
                hA.f13888F = charSequence13;
            }
            Integer num13 = i7.f13956H;
            if (num13 != null) {
                hA.f13889G = num13;
            }
            Bundle bundle = i7.f13957I;
            if (bundle != null) {
                hA.f13890H = bundle;
            }
            d5.I i10 = i7.f13958J;
            if (!i10.isEmpty()) {
                hA.f13891I = d5.I.w(i10);
            }
        }
        return new e2.I(hA);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final boolean d() {
        A1();
        return this.f19767j0.f19590g;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final C1202j d0() {
        A1();
        return this.f19761g0;
    }

    public final void d1() {
        A1();
        q1();
        u1(null);
        n1(0, 0);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int e() {
        A1();
        return this.f19767j0.f19588e;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void e0() {
        A1();
    }

    public final ArrayList e1(List list) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < list.size(); i7++) {
            arrayList.add(this.f19775r.c((e2.F) list.get(i7)));
        }
        return arrayList;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void f(e2.P p10) {
        A1();
        if (this.f19767j0.o.equals(p10)) {
            return;
        }
        U uF = this.f19767j0.f(p10);
        this.f19734I++;
        this.l.f19525t.a(4, p10).b();
        y1(uF, 0, false, 5, -9223372036854775807L, -1, false);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void f0(l0 l0Var) {
        A1();
        w2.r rVar = this.f19764i;
        rVar.getClass();
        w2.o oVar = (w2.o) rVar;
        if (l0Var.equals(oVar.e())) {
            return;
        }
        if (l0Var instanceof C2595i) {
            oVar.j((C2595i) l0Var);
        }
        C2594h c2594h = new C2594h(oVar.e());
        c2594h.e(l0Var);
        oVar.j(new C2595i(c2594h));
        this.f19771m.f(19, new C1618L(l0Var, 1));
    }

    public final X f1(W w10) {
        int iI1 = i1(this.f19767j0);
        f0 f0Var = this.f19767j0.f19586a;
        if (iI1 == -1) {
            iI1 = 0;
        }
        D d = this.l;
        return new X(d, w10, f0Var, iI1, this.f19782y, d.f19527v);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void g0(int i7, int i10) {
        A1();
    }

    public final long g1(U u4) {
        if (!u4.f19587b.c()) {
            return AbstractC1343A.T(h1(u4));
        }
        Object obj = u4.f19587b.f22663a;
        f0 f0Var = u4.f19586a;
        e2.c0 c0Var = this.o;
        f0Var.g(obj, c0Var);
        long j3 = u4.c;
        return j3 == -9223372036854775807L ? AbstractC1343A.T(f0Var.m(i1(u4), (e0) this.f14069b, 0L).l) : AbstractC1343A.T(c0Var.f14061e) + AbstractC1343A.T(j3);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void h(int i7) {
        A1();
        if (this.f19732G != i7) {
            this.f19732G = i7;
            h2.x xVar = this.l.f19525t;
            xVar.getClass();
            h2.w wVarB = h2.x.b();
            wVarB.f15246a = xVar.f15248a.obtainMessage(11, i7, 0);
            wVarB.b();
            C1616J c1616j = new C1616J(i7, 6);
            h2.o oVar = this.f19771m;
            oVar.c(8, c1616j);
            w1();
            oVar.b();
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void h0(boolean z6) {
        A1();
    }

    public final long h1(U u4) {
        if (u4.f19586a.p()) {
            return AbstractC1343A.H(this.f19770l0);
        }
        long j3 = u4.f19597p ? u4.j() : u4.f19600s;
        if (u4.f19587b.c()) {
            return j3;
        }
        f0 f0Var = u4.f19586a;
        Object obj = u4.f19587b.f22663a;
        e2.c0 c0Var = this.o;
        f0Var.g(obj, c0Var);
        return j3 + c0Var.f14061e;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int i() {
        A1();
        return this.f19732G;
    }

    public final int i1(U u4) {
        if (u4.f19586a.p()) {
            return this.f19769k0;
        }
        return u4.f19586a.g(u4.f19587b.f22663a, this.o).c;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final e2.P j() {
        A1();
        return this.f19767j0.o;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void j0(int i7) {
        A1();
    }

    public final Pair j1(f0 f0Var, Z z6, int i7, long j3) {
        if (f0Var.p() || z6.p()) {
            boolean z10 = !f0Var.p() && z6.p();
            return m1(z6, z10 ? -1 : i7, z10 ? -9223372036854775807L : j3);
        }
        Pair pairI = f0Var.i((e0) this.f14069b, this.o, i7, AbstractC1343A.H(j3));
        Object obj = pairI.first;
        if (z6.b(obj) != -1) {
            return pairI;
        }
        int iK = D.K((e0) this.f14069b, this.o, this.f19732G, this.f19733H, obj, f0Var, z6);
        if (iK == -1) {
            return m1(z6, -1, -9223372036854775807L);
        }
        e0 e0Var = (e0) this.f14069b;
        z6.m(iK, e0Var, 0L);
        return m1(z6, iK, AbstractC1343A.T(e0Var.l));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int k0() {
        A1();
        if (s()) {
            return this.f19767j0.f19587b.c;
        }
        return -1;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void l0(SurfaceView surfaceView) {
        A1();
        if (surfaceView instanceof z2.m) {
            q1();
            u1(surfaceView);
            t1(surfaceView.getHolder());
            return;
        }
        boolean z6 = surfaceView instanceof A2.o;
        SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = this.f19783z;
        if (z6) {
            q1();
            this.f19745T = (A2.o) surfaceView;
            X xF1 = f1(this.f19727A);
            AbstractC1346c.j(!xF1.f19606g);
            xF1.d = 10000;
            A2.o oVar = this.f19745T;
            AbstractC1346c.j(true ^ xF1.f19606g);
            xF1.f19604e = oVar;
            xF1.c();
            this.f19745T.l.add(surfaceHolderCallbackC1920u);
            u1(this.f19745T.getVideoSurface());
            t1(surfaceView.getHolder());
            return;
        }
        SurfaceHolder holder = surfaceView == null ? null : surfaceView.getHolder();
        A1();
        if (holder == null) {
            d1();
            return;
        }
        q1();
        this.f19746U = true;
        this.f19744S = holder;
        holder.addCallback(surfaceHolderCallbackC1920u);
        Surface surface = holder.getSurface();
        if (surface == null || !surface.isValid()) {
            u1(null);
            n1(0, 0);
        } else {
            u1(surface);
            Rect surfaceFrame = holder.getSurfaceFrame();
            n1(surfaceFrame.width(), surfaceFrame.height());
        }
    }

    public final U l1(U u4, f0 f0Var, Pair pair) {
        List list;
        AbstractC1346c.c(f0Var.p() || pair != null);
        f0 f0Var2 = u4.f19586a;
        long jG1 = g1(u4);
        U uH = u4.h(f0Var);
        if (f0Var.p()) {
            C2492C c2492c = U.f19585u;
            long jH = AbstractC1343A.H(this.f19770l0);
            U uB = uH.c(c2492c, jH, jH, jH, 0L, u2.n0.d, this.c, d0.f13415p).b(c2492c);
            uB.f19598q = uB.f19600s;
            return uB;
        }
        Object obj = uH.f19587b.f22663a;
        int i7 = AbstractC1343A.f15195a;
        boolean zEquals = obj.equals(pair.first);
        C2492C c2492c2 = !zEquals ? new C2492C(pair.first) : uH.f19587b;
        long jLongValue = ((Long) pair.second).longValue();
        long jH2 = AbstractC1343A.H(jG1);
        if (!f0Var2.p()) {
            jH2 -= f0Var2.g(obj, this.o).f14061e;
        }
        if (!zEquals || jLongValue < jH2) {
            AbstractC1346c.j(!c2492c2.c());
            u2.n0 n0Var = !zEquals ? u2.n0.d : uH.f19591h;
            w2.s sVar = !zEquals ? this.c : uH.f19592i;
            if (zEquals) {
                list = uH.f19593j;
            } else {
                C1078G c1078g = d5.I.f13384m;
                list = d0.f13415p;
            }
            U uB2 = uH.c(c2492c2, jLongValue, jLongValue, jLongValue, 0L, n0Var, sVar, list).b(c2492c2);
            uB2.f19598q = jLongValue;
            return uB2;
        }
        if (jLongValue != jH2) {
            AbstractC1346c.j(!c2492c2.c());
            long jMax = Math.max(0L, uH.f19599r - (jLongValue - jH2));
            long j3 = uH.f19598q;
            if (uH.f19594k.equals(uH.f19587b)) {
                j3 = jLongValue + jMax;
            }
            U uC = uH.c(c2492c2, jLongValue, jLongValue, jLongValue, jMax, uH.f19591h, uH.f19592i, uH.f19593j);
            uC.f19598q = j3;
            return uC;
        }
        int iB = f0Var.b(uH.f19594k.f22663a);
        if (iB != -1 && f0Var.f(iB, this.o, false).c == f0Var.g(c2492c2.f22663a, this.o).c) {
            return uH;
        }
        f0Var.g(c2492c2.f22663a, this.o);
        long jA = c2492c2.c() ? this.o.a(c2492c2.f22664b, c2492c2.c) : this.o.d;
        U uB3 = uH.c(c2492c2, uH.f19600s, uH.f19600s, uH.d, jA - uH.f19600s, uH.f19591h, uH.f19592i, uH.f19593j).b(c2492c2);
        uB3.f19598q = jA;
        return uB3;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void m(List list, int i7, long j3) {
        A1();
        ArrayList arrayListE1 = e1(list);
        A1();
        s1(arrayListE1, i7, j3, false);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void m0(SurfaceView surfaceView) {
        A1();
        SurfaceHolder holder = surfaceView == null ? null : surfaceView.getHolder();
        A1();
        if (holder == null || holder != this.f19744S) {
            return;
        }
        d1();
    }

    public final Pair m1(f0 f0Var, int i7, long j3) {
        if (f0Var.p()) {
            this.f19769k0 = i7;
            if (j3 == -9223372036854775807L) {
                j3 = 0;
            }
            this.f19770l0 = j3;
            return null;
        }
        if (i7 == -1 || i7 >= f0Var.o()) {
            i7 = f0Var.a(this.f19733H);
            j3 = AbstractC1343A.T(f0Var.m(i7, (e0) this.f14069b, 0L).l);
        }
        return f0Var.i((e0) this.f14069b, this.o, i7, AbstractC1343A.H(j3));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final e2.O n() {
        A1();
        return this.f19767j0.f19589f;
    }

    public final void n1(int i7, int i10) {
        h2.u uVar = this.f19749X;
        if (i7 == uVar.f15243a && i10 == uVar.f15244b) {
            return;
        }
        this.f19749X = new h2.u(i7, i10);
        this.f19771m.f(24, new k3.W(i7, i10, 3));
        r1(2, 14, new h2.u(i7, i10));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int o() {
        A1();
        return 0;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void o0(int i7, int i10, int i11) {
        A1();
        AbstractC1346c.c(i7 >= 0 && i7 <= i10 && i11 >= 0);
        ArrayList arrayList = this.f19773p;
        int size = arrayList.size();
        int iMin = Math.min(i10, size);
        int iMin2 = Math.min(i11, size - (iMin - i7));
        if (i7 >= size || i7 == iMin || i7 == iMin2) {
            return;
        }
        f0 f0VarT0 = t0();
        this.f19734I++;
        AbstractC1343A.G(arrayList, i7, iMin, iMin2);
        Z z6 = new Z(arrayList, this.f19737L);
        U u4 = this.f19767j0;
        U uL1 = l1(u4, z6, j1(f0VarT0, z6, i1(u4), g1(this.f19767j0)));
        c0 c0Var = this.f19737L;
        D d = this.l;
        d.getClass();
        d.f19525t.a(19, new C1899A(i7, iMin, iMin2, c0Var)).b();
        y1(uL1, 0, false, 5, -9223372036854775807L, -1, false);
    }

    public final U o1(U u4, int i7, int i10) {
        int iI1 = i1(u4);
        long jG1 = g1(u4);
        ArrayList arrayList = this.f19773p;
        int size = arrayList.size();
        this.f19734I++;
        p1(i7, i10);
        Z z6 = new Z(arrayList, this.f19737L);
        U uL1 = l1(u4, z6, j1(u4.f19586a, z6, iI1, jG1));
        int i11 = uL1.f19588e;
        if (i11 != 1 && i11 != 4 && i7 < i10 && i10 == size && iI1 >= uL1.f19586a.o()) {
            uL1 = uL1.g(4);
        }
        c0 c0Var = this.f19737L;
        h2.x xVar = this.l.f19525t;
        xVar.getClass();
        h2.w wVarB = h2.x.b();
        wVarB.f15246a = xVar.f15248a.obtainMessage(20, i7, i10, c0Var);
        wVarB.b();
        return uL1;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final int p0() {
        A1();
        return this.f19767j0.f19596n;
    }

    public final void p1(int i7, int i10) {
        for (int i11 = i10 - 1; i11 >= i7; i11--) {
            this.f19773p.remove(i11);
        }
        c0 c0Var = this.f19737L;
        int i12 = i10 - i7;
        int[] iArr = c0Var.f22804b;
        int[] iArr2 = new int[iArr.length - i12];
        int i13 = 0;
        for (int i14 = 0; i14 < iArr.length; i14++) {
            int i15 = iArr[i14];
            if (i15 < i7 || i15 >= i10) {
                int i16 = i14 - i13;
                if (i15 >= i7) {
                    i15 -= i12;
                }
                iArr2[i16] = i15;
            } else {
                i13++;
            }
        }
        this.f19737L = new c0(iArr2, new Random(c0Var.f22803a.nextLong()));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void q(boolean z6) {
        A1();
        int iD = this.f19728C.d(e(), z6);
        x1(iD, iD == -1 ? 2 : 1, z6);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void q0(int i7, int i10, List list) {
        A1();
        AbstractC1346c.c(i7 >= 0 && i10 >= i7);
        ArrayList arrayList = this.f19773p;
        int size = arrayList.size();
        if (i7 > size) {
            return;
        }
        int iMin = Math.min(i10, size);
        if (iMin - i7 == list.size()) {
            for (int i11 = i7; i11 < iMin; i11++) {
                if (((C1922w) arrayList.get(i11)).f19726b.f22886k.a((e2.F) list.get(i11 - i7))) {
                }
            }
            this.f19734I++;
            h2.x xVar = this.l.f19525t;
            xVar.getClass();
            h2.w wVarB = h2.x.b();
            wVarB.f15246a = xVar.f15248a.obtainMessage(27, i7, iMin, list);
            wVarB.b();
            for (int i12 = i7; i12 < iMin; i12++) {
                C1922w c1922w = (C1922w) arrayList.get(i12);
                c1922w.c = new Y(c1922w.c, (e2.F) list.get(i12 - i7));
            }
            y1(this.f19767j0.h(new Z(arrayList, this.f19737L)), 0, false, 4, -9223372036854775807L, -1, false);
            return;
        }
        ArrayList arrayListE1 = e1(list);
        if (!arrayList.isEmpty()) {
            U uO1 = o1(b1(this.f19767j0, iMin, arrayListE1), i7, iMin);
            y1(uO1, 0, !uO1.f19587b.f22663a.equals(this.f19767j0.f19587b.f22663a), 4, h1(uO1), -1, false);
        } else {
            boolean z6 = this.f19769k0 == -1;
            A1();
            s1(arrayListE1, -1, -9223372036854775807L, z6);
        }
    }

    public final void q1() {
        A2.o oVar = this.f19745T;
        SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = this.f19783z;
        if (oVar != null) {
            X xF1 = f1(this.f19727A);
            AbstractC1346c.j(!xF1.f19606g);
            xF1.d = 10000;
            AbstractC1346c.j(!xF1.f19606g);
            xF1.f19604e = null;
            xF1.c();
            this.f19745T.l.remove(surfaceHolderCallbackC1920u);
            this.f19745T = null;
        }
        TextureView textureView = this.f19747V;
        if (textureView != null) {
            if (textureView.getSurfaceTextureListener() != surfaceHolderCallbackC1920u) {
                AbstractC1346c.z("ExoPlayerImpl", "SurfaceTextureListener already unset or replaced.");
            } else {
                this.f19747V.setSurfaceTextureListener(null);
            }
            this.f19747V = null;
        }
        SurfaceHolder surfaceHolder = this.f19744S;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(surfaceHolderCallbackC1920u);
            this.f19744S = null;
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void r(Surface surface) {
        A1();
        q1();
        u1(surface);
        int i7 = surface == null ? 0 : -1;
        n1(i7, i7);
    }

    public final void r1(int i7, int i10, Object obj) {
        for (AbstractC1905e abstractC1905e : this.f19762h) {
            if (i7 == -1 || abstractC1905e.f19636m == i7) {
                X xF1 = f1(abstractC1905e);
                AbstractC1346c.j(!xF1.f19606g);
                xF1.d = i10;
                AbstractC1346c.j(!xF1.f19606g);
                xF1.f19604e = obj;
                xF1.c();
            }
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final boolean s() {
        A1();
        return this.f19767j0.f19587b.c();
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long s0() {
        A1();
        if (!s()) {
            return P();
        }
        U u4 = this.f19767j0;
        C2492C c2492c = u4.f19587b;
        f0 f0Var = u4.f19586a;
        Object obj = c2492c.f22663a;
        e2.c0 c0Var = this.o;
        f0Var.g(obj, c0Var);
        return AbstractC1343A.T(c0Var.a(c2492c.f22664b, c2492c.c));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s1(java.util.List r16, int r17, long r18, boolean r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r17
            n2.U r2 = r0.f19767j0
            int r2 = r15.i1(r2)
            long r3 = r15.N0()
            int r5 = r0.f19734I
            r6 = 1
            int r5 = r5 + r6
            r0.f19734I = r5
            java.util.ArrayList r5 = r0.f19773p
            boolean r7 = r5.isEmpty()
            r8 = 0
            if (r7 != 0) goto L23
            int r7 = r5.size()
            r15.p1(r8, r7)
        L23:
            r7 = r16
            java.util.ArrayList r10 = r15.Z0(r8, r7)
            n2.Z r7 = new n2.Z
            u2.c0 r9 = r0.f19737L
            r7.<init>(r5, r9)
            boolean r5 = r7.p()
            int r9 = r7.f19614g
            if (r5 != 0) goto L41
            if (r1 >= r9) goto L3b
            goto L41
        L3b:
            Q7.v r0 = new Q7.v
            r0.<init>()
            throw r0
        L41:
            r5 = -1
            if (r20 == 0) goto L51
            boolean r1 = r0.f19733H
            int r1 = r7.a(r1)
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L4f:
            r12 = r1
            goto L59
        L51:
            if (r1 != r5) goto L56
            r12 = r2
            r2 = r3
            goto L59
        L56:
            r2 = r18
            goto L4f
        L59:
            n2.U r1 = r0.f19767j0
            android.util.Pair r4 = r15.m1(r7, r12, r2)
            n2.U r1 = r15.l1(r1, r7, r4)
            int r4 = r1.f19588e
            if (r12 == r5) goto L75
            if (r4 == r6) goto L75
            boolean r4 = r7.p()
            if (r4 != 0) goto L74
            if (r12 < r9) goto L72
            goto L74
        L72:
            r4 = 2
            goto L75
        L74:
            r4 = 4
        L75:
            n2.U r1 = r1.g(r4)
            long r13 = h2.AbstractC1343A.H(r2)
            u2.c0 r11 = r0.f19737L
            n2.D r2 = r0.l
            h2.x r2 = r2.f19525t
            n2.z r3 = new n2.z
            r9 = r3
            r9.<init>(r10, r11, r12, r13)
            r4 = 17
            h2.w r2 = r2.a(r4, r3)
            r2.b()
            n2.U r2 = r0.f19767j0
            u2.C r2 = r2.f19587b
            java.lang.Object r2 = r2.f22663a
            u2.C r3 = r1.f19587b
            java.lang.Object r3 = r3.f22663a
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto Lae
            n2.U r2 = r0.f19767j0
            e2.f0 r2 = r2.f19586a
            boolean r2 = r2.p()
            if (r2 != 0) goto Lae
            r3 = r6
            goto Laf
        Lae:
            r3 = r8
        Laf:
            long r5 = r15.h1(r1)
            r2 = 0
            r4 = 4
            r7 = -1
            r8 = 0
            r0 = r15
            r0.y1(r1, r2, r3, r4, r5, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.C1923x.s1(java.util.List, int, long, boolean):void");
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public final void setImageOutput(ImageOutput imageOutput) {
        A1();
        r1(4, 15, imageOutput);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void stop() {
        A1();
        this.f19728C.d(1, C());
        v1(null);
        this.f19753b0 = new C1290c(d0.f13415p, this.f19767j0.f19600s);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final f0 t0() {
        A1();
        return this.f19767j0.f19586a;
    }

    public final void t1(SurfaceHolder surfaceHolder) {
        this.f19746U = false;
        this.f19744S = surfaceHolder;
        surfaceHolder.addCallback(this.f19783z);
        Surface surface = this.f19744S.getSurface();
        if (surface == null || !surface.isValid()) {
            n1(0, 0);
        } else {
            Rect surfaceFrame = this.f19744S.getSurfaceFrame();
            n1(surfaceFrame.width(), surfaceFrame.height());
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long u() {
        A1();
        return this.f19780w;
    }

    @Override // e2.AbstractC1197e, e2.W
    public final boolean u0() {
        A1();
        return false;
    }

    public final void u1(Object obj) {
        ArrayList arrayList = new ArrayList();
        boolean z6 = false;
        for (AbstractC1905e abstractC1905e : this.f19762h) {
            if (abstractC1905e.f19636m == 2) {
                X xF1 = f1(abstractC1905e);
                AbstractC1346c.j(!xF1.f19606g);
                xF1.d = 1;
                AbstractC1346c.j(true ^ xF1.f19606g);
                xF1.f19604e = obj;
                xF1.c();
                arrayList.add(xF1);
            }
        }
        Object obj2 = this.f19742Q;
        if (obj2 != null && obj2 != obj) {
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((X) it.next()).a(this.f19731F);
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (TimeoutException unused2) {
                z6 = true;
            }
            Object obj3 = this.f19742Q;
            Surface surface = this.f19743R;
            if (obj3 == surface) {
                surface.release();
                this.f19743R = null;
            }
        }
        this.f19742Q = obj;
        if (z6) {
            v1(new C1913m(2, new A9.k("Detaching surface timed out."), 1003));
        }
    }

    public final void v1(C1913m c1913m) {
        U u4 = this.f19767j0;
        U uB = u4.b(u4.f19587b);
        uB.f19598q = uB.f19600s;
        uB.f19599r = 0L;
        U uG = uB.g(1);
        if (c1913m != null) {
            uG = uG.e(c1913m);
        }
        U u10 = uG;
        this.f19734I++;
        h2.x xVar = this.l.f19525t;
        xVar.getClass();
        h2.w wVarB = h2.x.b();
        wVarB.f15246a = xVar.f15248a.obtainMessage(6);
        wVarB.b();
        y1(u10, 0, false, 5, -9223372036854775807L, -1, false);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void w0() {
        A1();
    }

    public final void w1() {
        e2.S s6 = this.f19739N;
        int i7 = AbstractC1343A.f15195a;
        e2.W w10 = this.f19760g;
        boolean zS = w10.s();
        boolean zP0 = w10.P0();
        boolean zI0 = w10.i0();
        boolean zM = w10.M();
        boolean zV0 = w10.V0();
        boolean zS0 = w10.S0();
        boolean zP = w10.t0().p();
        e2.Q q4 = new e2.Q();
        q4.f13998a = new C0050y0(5);
        C1206n c1206n = this.d.f14000a;
        C0050y0 c0050y0 = (C0050y0) q4.f13998a;
        c0050y0.j(c1206n);
        boolean z6 = !zS;
        q4.a(4, z6);
        q4.a(5, zP0 && !zS);
        q4.a(6, zI0 && !zS);
        q4.a(7, !zP && (zI0 || !zV0 || zP0) && !zS);
        q4.a(8, zM && !zS);
        q4.a(9, !zP && (zM || (zV0 && zS0)) && !zS);
        q4.a(10, z6);
        q4.a(11, zP0 && !zS);
        q4.a(12, zP0 && !zS);
        e2.S s10 = new e2.S(c0050y0.l());
        this.f19739N = s10;
        if (s10.equals(s6)) {
            return;
        }
        this.f19771m.c(13, new r(this, 2));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long x() {
        A1();
        return g1(this.f19767j0);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final boolean x0() {
        A1();
        return this.f19733H;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v4 */
    public final void x1(int i7, int i10, boolean z6) {
        ?? r14 = (!z6 || i7 == -1) ? 0 : 1;
        int i11 = i7 == 0 ? 1 : 0;
        U u4 = this.f19767j0;
        if (u4.l == r14 && u4.f19596n == i11 && u4.f19595m == i10) {
            return;
        }
        this.f19734I++;
        boolean z10 = u4.f19597p;
        U uA = u4;
        if (z10) {
            uA = u4.a();
        }
        U uD = uA.d(i10, i11, r14);
        int i12 = (i11 << 4) | i10;
        h2.x xVar = this.l.f19525t;
        xVar.getClass();
        h2.w wVarB = h2.x.b();
        wVarB.f15246a = xVar.f15248a.obtainMessage(1, r14, i12);
        wVarB.b();
        y1(uD, 0, false, 5, -9223372036854775807L, -1, false);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long y() {
        A1();
        return AbstractC1343A.T(this.f19767j0.f19599r);
    }

    @Override // e2.AbstractC1197e, e2.W
    public final l0 y0() {
        A1();
        return ((w2.o) this.f19764i).e();
    }

    public final void y1(final U u4, int i7, boolean z6, int i10, long j3, int i11, boolean z10) {
        Pair pair;
        int i12;
        e2.F f10;
        boolean z11;
        boolean z12;
        int i13;
        Object obj;
        e2.F f11;
        Object obj2;
        int i14;
        long j10;
        long j11;
        long jK1;
        long jK12;
        Object obj3;
        e2.F f12;
        Object obj4;
        int i15;
        U u10 = this.f19767j0;
        this.f19767j0 = u4;
        boolean zEquals = u10.f19586a.equals(u4.f19586a);
        f0 f0Var = u10.f19586a;
        f0 f0Var2 = u4.f19586a;
        if (f0Var2.p() && f0Var.p()) {
            pair = new Pair(Boolean.FALSE, -1);
        } else if (f0Var2.p() != f0Var.p()) {
            pair = new Pair(Boolean.TRUE, 3);
        } else {
            C2492C c2492c = u10.f19587b;
            Object obj5 = c2492c.f22663a;
            e2.c0 c0Var = this.o;
            int i16 = f0Var.g(obj5, c0Var).c;
            e0 e0Var = (e0) this.f14069b;
            Object obj6 = f0Var.m(i16, e0Var, 0L).f14085a;
            C2492C c2492c2 = u4.f19587b;
            if (obj6.equals(f0Var2.m(f0Var2.g(c2492c2.f22663a, c0Var).c, e0Var, 0L).f14085a)) {
                pair = (z6 && i10 == 0 && c2492c.d < c2492c2.d) ? new Pair(Boolean.TRUE, 0) : (z6 && i10 == 1 && z10) ? new Pair(Boolean.TRUE, 2) : new Pair(Boolean.FALSE, -1);
            } else {
                if (z6 && i10 == 0) {
                    i12 = 1;
                } else if (z6 && i10 == 1) {
                    i12 = 2;
                } else {
                    if (zEquals) {
                        throw new IllegalStateException();
                    }
                    i12 = 3;
                }
                pair = new Pair(Boolean.TRUE, Integer.valueOf(i12));
            }
        }
        boolean zBooleanValue = ((Boolean) pair.first).booleanValue();
        int iIntValue = ((Integer) pair.second).intValue();
        if (zBooleanValue) {
            f10 = !u4.f19586a.p() ? u4.f19586a.m(u4.f19586a.g(u4.f19587b.f22663a, this.o).c, (e0) this.f14069b, 0L).c : null;
            this.f19765i0 = e2.I.f13914K;
        } else {
            f10 = null;
        }
        if (zBooleanValue || !u10.f19593j.equals(u4.f19593j)) {
            e2.H hA = this.f19765i0.a();
            List list = u4.f19593j;
            for (int i17 = 0; i17 < list.size(); i17++) {
                e2.K k10 = (e2.K) list.get(i17);
                int i18 = 0;
                while (true) {
                    e2.J[] jArr = k10.l;
                    if (i18 < jArr.length) {
                        jArr[i18].e(hA);
                        i18++;
                    }
                }
            }
            this.f19765i0 = new e2.I(hA);
        }
        e2.I iC1 = c1();
        boolean zEquals2 = iC1.equals(this.f19740O);
        this.f19740O = iC1;
        boolean z13 = u10.l != u4.l;
        boolean z14 = u10.f19588e != u4.f19588e;
        if (z14 || z13) {
            z1();
        }
        boolean z15 = u10.f19590g != u4.f19590g;
        if (!zEquals) {
            this.f19771m.c(0, new io.sentry.instrumentation.file.f(i7, 1, u4));
        }
        if (z6) {
            e2.c0 c0Var2 = new e2.c0();
            if (u10.f19586a.p()) {
                z11 = z14;
                z12 = z15;
                i13 = i11;
                obj = null;
                f11 = null;
                obj2 = null;
                i14 = -1;
            } else {
                Object obj7 = u10.f19587b.f22663a;
                u10.f19586a.g(obj7, c0Var2);
                int i19 = c0Var2.c;
                int iB = u10.f19586a.b(obj7);
                z11 = z14;
                z12 = z15;
                obj = u10.f19586a.m(i19, (e0) this.f14069b, 0L).f14085a;
                f11 = ((e0) this.f14069b).c;
                i13 = i19;
                i14 = iB;
                obj2 = obj7;
            }
            if (i10 == 0) {
                if (u10.f19587b.c()) {
                    C2492C c2492c3 = u10.f19587b;
                    jK1 = c0Var2.a(c2492c3.f22664b, c2492c3.c);
                    jK12 = k1(u10);
                } else if (u10.f19587b.f22665e != -1) {
                    jK1 = k1(this.f19767j0);
                    jK12 = jK1;
                } else {
                    j10 = c0Var2.f14061e;
                    j11 = c0Var2.d;
                    jK1 = j10 + j11;
                    jK12 = jK1;
                }
            } else if (u10.f19587b.c()) {
                jK1 = u10.f19600s;
                jK12 = k1(u10);
            } else {
                j10 = c0Var2.f14061e;
                j11 = u10.f19600s;
                jK1 = j10 + j11;
                jK12 = jK1;
            }
            long jT = AbstractC1343A.T(jK1);
            long jT2 = AbstractC1343A.T(jK12);
            C2492C c2492c4 = u10.f19587b;
            e2.V v10 = new e2.V(obj, i13, f11, obj2, i14, jT, jT2, c2492c4.f22664b, c2492c4.c);
            int iB0 = b0();
            if (this.f19767j0.f19586a.p()) {
                obj3 = null;
                f12 = null;
                obj4 = null;
                i15 = -1;
            } else {
                U u11 = this.f19767j0;
                Object obj8 = u11.f19587b.f22663a;
                u11.f19586a.g(obj8, this.o);
                int iB2 = this.f19767j0.f19586a.b(obj8);
                f0 f0Var3 = this.f19767j0.f19586a;
                e0 e0Var2 = (e0) this.f14069b;
                i15 = iB2;
                obj3 = f0Var3.m(iB0, e0Var2, 0L).f14085a;
                f12 = e0Var2.c;
                obj4 = obj8;
            }
            long jT3 = AbstractC1343A.T(j3);
            long jT4 = this.f19767j0.f19587b.c() ? AbstractC1343A.T(k1(this.f19767j0)) : jT3;
            C2492C c2492c5 = this.f19767j0.f19587b;
            this.f19771m.c(11, new k3.V(i10, v10, new e2.V(obj3, iB0, f12, obj4, i15, jT3, jT4, c2492c5.f22664b, c2492c5.c)));
        } else {
            z11 = z14;
            z12 = z15;
        }
        if (zBooleanValue) {
            this.f19771m.c(1, new io.sentry.instrumentation.file.f(iIntValue, 2, f10));
        }
        if (u10.f19589f != u4.f19589f) {
            final int i20 = 7;
            this.f19771m.c(10, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i20) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
            if (u4.f19589f != null) {
                final int i21 = 8;
                this.f19771m.c(10, new h2.l() { // from class: n2.q
                    @Override // h2.l
                    public final void b(Object obj9) {
                        e2.U u12 = (e2.U) obj9;
                        switch (i21) {
                            case 0:
                                U u13 = u4;
                                u12.h(u13.f19590g);
                                u12.k(u13.f19590g);
                                break;
                            case 1:
                                U u14 = u4;
                                u12.p(u14.f19588e, u14.l);
                                break;
                            case 2:
                                u12.x(u4.f19588e);
                                break;
                            case 3:
                                U u15 = u4;
                                u12.q(u15.f19595m, u15.l);
                                break;
                            case 4:
                                u12.e(u4.f19596n);
                                break;
                            case 5:
                                u12.T(u4.k());
                                break;
                            case 6:
                                u12.G(u4.o);
                                break;
                            case 7:
                                u12.H(u4.f19589f);
                                break;
                            case 8:
                                u12.f(u4.f19589f);
                                break;
                            default:
                                u12.J(u4.f19592i.d);
                                break;
                        }
                    }
                });
            }
        }
        w2.s sVar = u10.f19592i;
        w2.s sVar2 = u4.f19592i;
        if (sVar != sVar2) {
            w2.r rVar = this.f19764i;
            i2.q qVar = sVar2.f23273e;
            rVar.getClass();
            final int i22 = 9;
            this.f19771m.c(2, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i22) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        if (!zEquals2) {
            this.f19771m.c(14, new C1680z(this.f19740O, 2));
        }
        if (z12) {
            final int i23 = 0;
            this.f19771m.c(3, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i23) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        if (z11 || z13) {
            final int i24 = 1;
            this.f19771m.c(-1, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i24) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        if (z11) {
            final int i25 = 2;
            this.f19771m.c(4, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i25) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        if (z13 || u10.f19595m != u4.f19595m) {
            final int i26 = 3;
            this.f19771m.c(5, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i26) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        if (u10.f19596n != u4.f19596n) {
            final int i27 = 4;
            this.f19771m.c(6, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i27) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        if (u10.k() != u4.k()) {
            final int i28 = 5;
            this.f19771m.c(7, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i28) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        if (!u10.o.equals(u4.o)) {
            final int i29 = 6;
            this.f19771m.c(12, new h2.l() { // from class: n2.q
                @Override // h2.l
                public final void b(Object obj9) {
                    e2.U u12 = (e2.U) obj9;
                    switch (i29) {
                        case 0:
                            U u13 = u4;
                            u12.h(u13.f19590g);
                            u12.k(u13.f19590g);
                            break;
                        case 1:
                            U u14 = u4;
                            u12.p(u14.f19588e, u14.l);
                            break;
                        case 2:
                            u12.x(u4.f19588e);
                            break;
                        case 3:
                            U u15 = u4;
                            u12.q(u15.f19595m, u15.l);
                            break;
                        case 4:
                            u12.e(u4.f19596n);
                            break;
                        case 5:
                            u12.T(u4.k());
                            break;
                        case 6:
                            u12.G(u4.o);
                            break;
                        case 7:
                            u12.H(u4.f19589f);
                            break;
                        case 8:
                            u12.f(u4.f19589f);
                            break;
                        default:
                            u12.J(u4.f19592i.d);
                            break;
                    }
                }
            });
        }
        w1();
        this.f19771m.b();
        if (u10.f19597p != u4.f19597p) {
            Iterator it = this.f19772n.iterator();
            while (it.hasNext()) {
                ((SurfaceHolderCallbackC1920u) it.next()).l.z1();
            }
        }
    }

    @Override // e2.AbstractC1197e, e2.W
    public final void z(int i7, List list) {
        A1();
        a1(i7, e1(list));
    }

    @Override // e2.AbstractC1197e, e2.W
    public final long z0() {
        A1();
        if (this.f19767j0.f19586a.p()) {
            return this.f19770l0;
        }
        U u4 = this.f19767j0;
        if (u4.f19594k.d != u4.f19587b.d) {
            return AbstractC1343A.T(u4.f19586a.m(b0(), (e0) this.f14069b, 0L).f14094m);
        }
        long j3 = u4.f19598q;
        if (this.f19767j0.f19594k.c()) {
            U u10 = this.f19767j0;
            e2.c0 c0VarG = u10.f19586a.g(u10.f19594k.f22663a, this.o);
            long jD = c0VarG.d(this.f19767j0.f19594k.f22664b);
            j3 = jD == Long.MIN_VALUE ? c0VarG.d : jD;
        }
        U u11 = this.f19767j0;
        f0 f0Var = u11.f19586a;
        Object obj = u11.f19594k.f22663a;
        e2.c0 c0Var = this.o;
        f0Var.g(obj, c0Var);
        return AbstractC1343A.T(j3 + c0Var.f14061e);
    }

    public final void z1() {
        int iE = e();
        C1886o c1886o = this.f19730E;
        m6.b bVar = this.f19729D;
        if (iE != 1) {
            if (iE == 2 || iE == 3) {
                A1();
                boolean z6 = this.f19767j0.f19597p;
                C();
                bVar.getClass();
                C();
                c1886o.getClass();
                return;
            }
            if (iE != 4) {
                throw new IllegalStateException();
            }
        }
        bVar.getClass();
        c1886o.getClass();
    }
}
