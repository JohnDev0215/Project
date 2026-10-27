package u2;

import B2.C0076p;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import e2.C1207o;
import e2.C1208p;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import i3.C1406e;
import io.sentry.C1458d0;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import n2.RunnableC1918s;
import y2.ExecutorC2739a;

/* JADX INFO: loaded from: classes.dex */
public final class T implements InterfaceC2490A, B2.t, x2.j {

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public static final Map f22706b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public static final C1208p f22707c0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final N f22708A;
    public final N B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final Handler f22709C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public InterfaceC2522z f22710D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public O2.b f22711E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Z[] f22712F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public S[] f22713G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f22714H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f22715I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f22716J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f22717K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public B8.t f22718L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public B2.F f22719M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public long f22720N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f22721O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f22722P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f22723Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f22724R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f22725S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int f22726T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f22727U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public long f22728V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public long f22729W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public boolean f22730X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public int f22731Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public boolean f22732Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f22733a0;
    public final Uri l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k2.h f22734m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final q2.o f22735n;
    public final x2.i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final B5.u f22736p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final q2.k f22737q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final W f22738r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final x2.e f22739s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final String f22740t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f22741u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f22742v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final long f22743w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final x2.n f22744x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final h3.y f22745y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final B2.P f22746z;

    static {
        HashMap map = new HashMap();
        map.put("Icy-MetaData", "1");
        f22706b0 = Collections.unmodifiableMap(map);
        C1207o c1207o = new C1207o();
        c1207o.f14237a = "icy";
        c1207o.f14246m = e2.L.l("application/x-icy");
        f22707c0 = new C1208p(c1207o);
    }

    public T(Uri uri, k2.h hVar, h3.y yVar, q2.o oVar, q2.k kVar, x2.i iVar, B5.u uVar, W w10, x2.e eVar, String str, int i7, boolean z6, long j3, ExecutorC2739a executorC2739a) {
        this.l = uri;
        this.f22734m = hVar;
        this.f22735n = oVar;
        this.f22737q = kVar;
        this.o = iVar;
        this.f22736p = uVar;
        this.f22738r = w10;
        this.f22739s = eVar;
        this.f22740t = str;
        this.f22741u = i7;
        this.f22742v = z6;
        this.f22744x = executorC2739a != null ? new x2.n(executorC2739a) : new x2.n("ProgressiveMediaPeriod");
        this.f22745y = yVar;
        this.f22743w = j3;
        this.f22746z = new B2.P();
        this.f22708A = new N(this, 0);
        this.B = new N(this, 1);
        this.f22709C = AbstractC1343A.l(null);
        this.f22713G = new S[0];
        this.f22712F = new Z[0];
        this.f22729W = -9223372036854775807L;
        this.f22722P = 1;
    }

    public final B2.L A(S s6) {
        int length = this.f22712F.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (s6.equals(this.f22713G[i7])) {
                return this.f22712F[i7];
            }
        }
        if (this.f22714H) {
            AbstractC1346c.z("ProgressiveMediaPeriod", "Extractor added new track (id=" + s6.f22704a + ") after finishing tracks.");
            return new C0076p();
        }
        q2.o oVar = this.f22735n;
        oVar.getClass();
        Z z6 = new Z(this.f22739s, oVar, this.f22737q);
        z6.f22772f = this;
        int i10 = length + 1;
        S[] sArr = (S[]) Arrays.copyOf(this.f22713G, i10);
        sArr[length] = s6;
        this.f22713G = sArr;
        Z[] zArr = (Z[]) Arrays.copyOf(this.f22712F, i10);
        zArr[length] = z6;
        this.f22712F = zArr;
        return z6;
    }

    public final void B(B2.F f10) {
        this.f22719M = this.f22711E == null ? f10 : new B2.w(-9223372036854775807L);
        this.f22720N = f10.l();
        boolean z6 = !this.f22727U && f10.l() == -9223372036854775807L;
        this.f22721O = z6;
        this.f22722P = z6 ? 7 : 1;
        if (this.f22715I) {
            this.f22738r.u(this.f22720N, f10.h(), this.f22721O);
        } else {
            x();
        }
    }

    public final void C() {
        P p10 = new P(this, this.l, this.f22734m, this.f22745y, this, this.f22746z);
        if (this.f22715I) {
            AbstractC1346c.j(v());
            long j3 = this.f22720N;
            if (j3 != -9223372036854775807L && this.f22729W > j3) {
                this.f22732Z = true;
                this.f22729W = -9223372036854775807L;
                return;
            }
            B2.F f10 = this.f22719M;
            f10.getClass();
            long j10 = f10.j(this.f22729W).f508a.f511b;
            long j11 = this.f22729W;
            p10.f22696f.f600a = j10;
            p10.f22699i = j11;
            p10.f22698h = true;
            p10.l = false;
            for (Z z6 : this.f22712F) {
                z6.f22784t = this.f22729W;
            }
            this.f22729W = -9223372036854775807L;
        }
        this.f22731Y = s();
        int iY = this.o.y(this.f22722P);
        x2.n nVar = this.f22744x;
        nVar.getClass();
        Looper looperMyLooper = Looper.myLooper();
        AbstractC1346c.k(looperMyLooper);
        nVar.c = null;
        x2.k kVar = new x2.k(nVar, looperMyLooper, p10, this, iY, SystemClock.elapsedRealtime());
        AbstractC1346c.j(nVar.f23758b == null);
        nVar.f23758b = kVar;
        SystemClock.elapsedRealtime();
        kVar.f23750n.getClass();
        kVar.o = null;
        x2.k kVar2 = nVar.f23758b;
        kVar2.getClass();
        nVar.f23757a.execute(kVar2);
        C2516t c2516t = new C2516t(p10.f22700j);
        long j12 = p10.f22699i;
        long j13 = this.f22720N;
        B5.u uVar = this.f22736p;
        uVar.k(new C2494E(uVar, c2516t, new C2521y(1, -1, null, AbstractC1343A.T(j12), AbstractC1343A.T(j13)), 0));
    }

    public final boolean D() {
        return this.f22724R || v();
    }

    @Override // u2.InterfaceC2490A
    public final long a(long j3, n2.b0 b0Var) {
        r();
        if (!this.f22719M.h()) {
            return 0L;
        }
        B2.E eJ = this.f22719M.j(j3);
        long j10 = eJ.f508a.f510a;
        long j11 = eJ.f509b.f510a;
        long j12 = b0Var.f19625a;
        long j13 = b0Var.f19626b;
        if (j12 == 0 && j13 == 0) {
            return j3;
        }
        int i7 = AbstractC1343A.f15195a;
        long j14 = j3 - j12;
        if (((j12 ^ j3) & (j3 ^ j14)) < 0) {
            j14 = Long.MIN_VALUE;
        }
        long j15 = j3 + j13;
        if (((j13 ^ j15) & (j3 ^ j15)) < 0) {
            j15 = Long.MAX_VALUE;
        }
        boolean z6 = false;
        boolean z10 = j14 <= j10 && j10 <= j15;
        if (j14 <= j11 && j11 <= j15) {
            z6 = true;
        }
        if (z10 && z6) {
            if (Math.abs(j10 - j3) <= Math.abs(j11 - j3)) {
                return j10;
            }
        } else {
            if (z10) {
                return j10;
            }
            if (!z6) {
                return j14;
            }
        }
        return j11;
    }

    @Override // u2.InterfaceC2490A
    public final void b(InterfaceC2522z interfaceC2522z, long j3) {
        this.f22710D = interfaceC2522z;
        this.f22746z.c();
        C();
    }

    @Override // x2.j
    public final void c(x2.l lVar) {
        B2.F f10;
        P p10 = (P) lVar;
        if (this.f22720N == -9223372036854775807L && (f10 = this.f22719M) != null) {
            boolean zH = f10.h();
            long jT = t(true);
            long j3 = jT == Long.MIN_VALUE ? 0L : jT + 10000;
            this.f22720N = j3;
            this.f22738r.u(j3, zH, this.f22721O);
        }
        Uri uri = p10.f22694b.f17632n;
        C2516t c2516t = new C2516t();
        this.o.getClass();
        long j10 = p10.f22699i;
        long j11 = this.f22720N;
        B5.u uVar = this.f22736p;
        uVar.k(new C2494E(uVar, c2516t, new C2521y(1, -1, null, AbstractC1343A.T(j10), AbstractC1343A.T(j11)), 1));
        this.f22732Z = true;
        InterfaceC2522z interfaceC2522z = this.f22710D;
        interfaceC2522z.getClass();
        interfaceC2522z.e(this);
    }

    @Override // u2.b0
    public final boolean d() {
        boolean z6;
        if (this.f22744x.a()) {
            B2.P p10 = this.f22746z;
            synchronized (p10) {
                z6 = p10.l;
            }
            if (z6) {
                return true;
            }
        }
        return false;
    }

    @Override // B2.t
    public final void e() {
        this.f22714H = true;
        this.f22709C.post(this.f22708A);
    }

    @Override // x2.j
    public final C1406e f(x2.l lVar, IOException iOException, int i7) {
        C1406e c1406e;
        B2.F f10;
        P p10 = (P) lVar;
        Uri uri = p10.f22694b.f17632n;
        C2516t c2516t = new C2516t();
        AbstractC1343A.T(p10.f22699i);
        AbstractC1343A.T(this.f22720N);
        long jD = this.o.d(new C1.j(iOException, i7));
        if (jD == -9223372036854775807L) {
            c1406e = x2.n.f23756e;
        } else {
            int iS = s();
            int i10 = iS > this.f22731Y ? 1 : 0;
            if (this.f22727U || !((f10 = this.f22719M) == null || f10.l() == -9223372036854775807L)) {
                this.f22731Y = iS;
            } else if (!this.f22715I || D()) {
                this.f22724R = this.f22715I;
                this.f22728V = 0L;
                this.f22731Y = 0;
                for (Z z6 : this.f22712F) {
                    z6.l(false);
                }
                p10.f22696f.f600a = 0L;
                p10.f22699i = 0L;
                p10.f22698h = true;
                p10.l = false;
            } else {
                this.f22730X = true;
                c1406e = x2.n.d;
            }
            c1406e = new C1406e(jD, i10);
        }
        int i11 = c1406e.f15742a;
        boolean z10 = i11 == 0 || i11 == 1;
        long j3 = p10.f22699i;
        long j10 = this.f22720N;
        B5.u uVar = this.f22736p;
        uVar.k(new C2495F(uVar, c2516t, new C2521y(1, -1, null, AbstractC1343A.T(j3), AbstractC1343A.T(j10)), iOException, !z10));
        return c1406e;
    }

    @Override // u2.b0
    public final long g() {
        return l();
    }

    @Override // u2.InterfaceC2490A
    public final long h() {
        if (this.f22725S) {
            this.f22725S = false;
            return this.f22728V;
        }
        if (!this.f22724R) {
            return -9223372036854775807L;
        }
        if (!this.f22732Z && s() <= this.f22731Y) {
            return -9223372036854775807L;
        }
        this.f22724R = false;
        return this.f22728V;
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        if (this.f22732Z) {
            return false;
        }
        x2.n nVar = this.f22744x;
        if (nVar.c != null || this.f22730X) {
            return false;
        }
        if (this.f22715I && this.f22726T == 0) {
            return false;
        }
        boolean zC = this.f22746z.c();
        if (nVar.a()) {
            return zC;
        }
        C();
        return true;
    }

    @Override // u2.InterfaceC2490A
    public final n0 j() {
        r();
        return (n0) this.f22718L.f808n;
    }

    @Override // u2.InterfaceC2490A
    public final long k(w2.q[] qVarArr, boolean[] zArr, a0[] a0VarArr, boolean[] zArr2, long j3) {
        w2.q qVar;
        r();
        B8.t tVar = this.f22718L;
        n0 n0Var = (n0) tVar.f808n;
        boolean[] zArr3 = (boolean[]) tVar.f809p;
        int i7 = this.f22726T;
        for (int i10 = 0; i10 < qVarArr.length; i10++) {
            a0 a0Var = a0VarArr[i10];
            if (a0Var != null && (qVarArr[i10] == null || !zArr[i10])) {
                int i11 = ((Q) a0Var).l;
                AbstractC1346c.j(zArr3[i11]);
                this.f22726T--;
                zArr3[i11] = false;
                a0VarArr[i10] = null;
            }
        }
        boolean z6 = !this.f22723Q ? j3 == 0 || this.f22717K : i7 != 0;
        for (int i12 = 0; i12 < qVarArr.length; i12++) {
            if (a0VarArr[i12] == null && (qVar = qVarArr[i12]) != null) {
                AbstractC1346c.j(qVar.length() == 1);
                AbstractC1346c.j(qVar.d(0) == 0);
                int iIndexOf = n0Var.f22882b.indexOf(qVar.g());
                if (iIndexOf < 0) {
                    iIndexOf = -1;
                }
                AbstractC1346c.j(!zArr3[iIndexOf]);
                this.f22726T++;
                zArr3[iIndexOf] = true;
                this.f22725S = qVar.h().f14321t | this.f22725S;
                a0VarArr[i12] = new Q(this, iIndexOf);
                zArr2[i12] = true;
                if (!z6) {
                    Z z10 = this.f22712F[iIndexOf];
                    z6 = (z10.f22781q + z10.f22783s == 0 || z10.m(j3, true)) ? false : true;
                }
            }
        }
        if (this.f22726T == 0) {
            this.f22730X = false;
            this.f22724R = false;
            this.f22725S = false;
            x2.n nVar = this.f22744x;
            if (nVar.a()) {
                for (Z z11 : this.f22712F) {
                    z11.f();
                }
                x2.k kVar = nVar.f23758b;
                AbstractC1346c.k(kVar);
                kVar.a(false);
            } else {
                this.f22732Z = false;
                for (Z z12 : this.f22712F) {
                    z12.l(false);
                }
            }
        } else if (z6) {
            j3 = o(j3);
            for (int i13 = 0; i13 < a0VarArr.length; i13++) {
                if (a0VarArr[i13] != null) {
                    zArr2[i13] = true;
                }
            }
        }
        this.f22723Q = true;
        return j3;
    }

    @Override // u2.b0
    public final long l() {
        long jT;
        boolean z6;
        long j3;
        r();
        if (this.f22732Z || this.f22726T == 0) {
            return Long.MIN_VALUE;
        }
        if (v()) {
            return this.f22729W;
        }
        if (this.f22716J) {
            int length = this.f22712F.length;
            jT = Long.MAX_VALUE;
            for (int i7 = 0; i7 < length; i7++) {
                B8.t tVar = this.f22718L;
                if (((boolean[]) tVar.o)[i7] && ((boolean[]) tVar.f809p)[i7]) {
                    Z z10 = this.f22712F[i7];
                    synchronized (z10) {
                        z6 = z10.f22787w;
                    }
                    if (z6) {
                        continue;
                    } else {
                        Z z11 = this.f22712F[i7];
                        synchronized (z11) {
                            j3 = z11.f22786v;
                        }
                        jT = Math.min(jT, j3);
                    }
                }
            }
        } else {
            jT = Long.MAX_VALUE;
        }
        if (jT == Long.MAX_VALUE) {
            jT = t(false);
        }
        return jT == Long.MIN_VALUE ? this.f22728V : jT;
    }

    @Override // u2.InterfaceC2490A
    public final void m() throws IOException {
        int iY;
        x2.n nVar;
        IOException iOException;
        try {
            iY = this.o.y(this.f22722P);
            nVar = this.f22744x;
            iOException = nVar.c;
        } catch (IOException e10) {
            if (!this.f22742v) {
                throw e10;
            }
            AbstractC1346c.q("ProgressiveMediaPeriod", "Suppressing preparation error because suppressPrepareError=true", e10);
            this.f22714H = true;
            B(new B2.w(-9223372036854775807L));
        }
        if (iOException != null) {
            throw iOException;
        }
        x2.k kVar = nVar.f23758b;
        if (kVar != null) {
            if (iY == Integer.MIN_VALUE) {
                iY = kVar.l;
            }
            IOException iOException2 = kVar.o;
            if (iOException2 != null && kVar.f23751p > iY) {
                throw iOException2;
            }
        }
        if (this.f22732Z && !this.f22715I) {
            throw e2.M.a(null, "Loading finished before preparation is complete.");
        }
    }

    @Override // x2.j
    public final void n(x2.l lVar, boolean z6) {
        P p10 = (P) lVar;
        Uri uri = p10.f22694b.f17632n;
        C2516t c2516t = new C2516t();
        this.o.getClass();
        long j3 = p10.f22699i;
        long j10 = this.f22720N;
        B5.u uVar = this.f22736p;
        uVar.k(new C2494E(uVar, c2516t, new C2521y(1, -1, null, AbstractC1343A.T(j3), AbstractC1343A.T(j10)), 2));
        if (z6) {
            return;
        }
        for (Z z10 : this.f22712F) {
            z10.l(false);
        }
        if (this.f22726T > 0) {
            InterfaceC2522z interfaceC2522z = this.f22710D;
            interfaceC2522z.getClass();
            interfaceC2522z.e(this);
        }
    }

    @Override // u2.InterfaceC2490A
    public final long o(long j3) {
        boolean zM;
        r();
        boolean[] zArr = (boolean[]) this.f22718L.o;
        if (!this.f22719M.h()) {
            j3 = 0;
        }
        this.f22724R = false;
        boolean z6 = true;
        boolean z10 = this.f22728V == j3;
        this.f22728V = j3;
        if (v()) {
            this.f22729W = j3;
            return j3;
        }
        if (this.f22722P != 7 && (this.f22732Z || this.f22744x.a())) {
            int length = this.f22712F.length;
            for (int i7 = 0; i7 < length; i7++) {
                Z z11 = this.f22712F[i7];
                int i10 = z11.f22781q;
                if (z11.f22783s + i10 != 0 || !z10) {
                    if (this.f22717K) {
                        synchronized (z11) {
                            synchronized (z11) {
                                z11.f22783s = 0;
                                X x8 = z11.f22769a;
                                x8.f22763e = x8.d;
                            }
                        }
                        int i11 = z11.f22781q;
                        if (i10 < i11 || i10 > z11.f22780p + i11) {
                            zM = false;
                        } else {
                            z11.f22784t = Long.MIN_VALUE;
                            z11.f22783s = i10 - i11;
                            zM = true;
                        }
                    } else {
                        zM = z11.m(j3, false);
                    }
                    if (!zM && (zArr[i7] || !this.f22716J)) {
                        z6 = false;
                        break;
                    }
                }
            }
            if (z6) {
                return j3;
            }
        }
        this.f22730X = false;
        this.f22729W = j3;
        this.f22732Z = false;
        this.f22725S = false;
        if (this.f22744x.a()) {
            for (Z z12 : this.f22712F) {
                z12.f();
            }
            x2.k kVar = this.f22744x.f23758b;
            AbstractC1346c.k(kVar);
            kVar.a(false);
        } else {
            this.f22744x.c = null;
            for (Z z13 : this.f22712F) {
                z13.l(false);
            }
        }
        return j3;
    }

    @Override // u2.InterfaceC2490A
    public final void p(long j3) {
        long jE;
        int i7;
        if (this.f22717K) {
            return;
        }
        r();
        if (v()) {
            return;
        }
        boolean[] zArr = (boolean[]) this.f22718L.f809p;
        int length = this.f22712F.length;
        for (int i10 = 0; i10 < length; i10++) {
            Z z6 = this.f22712F[i10];
            boolean z10 = zArr[i10];
            X x8 = z6.f22769a;
            synchronized (z6) {
                try {
                    int i11 = z6.f22780p;
                    jE = -1;
                    if (i11 != 0) {
                        long[] jArr = z6.f22779n;
                        int i12 = z6.f22782r;
                        if (j3 >= jArr[i12]) {
                            int iG = z6.g(i12, (!z10 || (i7 = z6.f22783s) == i11) ? i11 : i7 + 1, j3, false);
                            if (iG != -1) {
                                jE = z6.e(iG);
                            }
                        }
                    }
                } finally {
                }
            }
            x8.a(jE);
        }
    }

    @Override // u2.b0
    public final void q(long j3) {
    }

    public final void r() {
        AbstractC1346c.j(this.f22715I);
        this.f22718L.getClass();
        this.f22719M.getClass();
    }

    public final int s() {
        int i7 = 0;
        for (Z z6 : this.f22712F) {
            i7 += z6.f22781q + z6.f22780p;
        }
        return i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long t(boolean r7) {
        /*
            r6 = this;
            r0 = -9223372036854775808
            r2 = 0
        L3:
            u2.Z[] r3 = r6.f22712F
            int r3 = r3.length
            if (r2 >= r3) goto L29
            if (r7 != 0) goto L17
            B8.t r3 = r6.f22718L
            r3.getClass()
            java.lang.Object r3 = r3.f809p
            boolean[] r3 = (boolean[]) r3
            boolean r3 = r3[r2]
            if (r3 == 0) goto L23
        L17:
            u2.Z[] r3 = r6.f22712F
            r3 = r3[r2]
            monitor-enter(r3)
            long r4 = r3.f22786v     // Catch: java.lang.Throwable -> L26
            monitor-exit(r3)
            long r0 = java.lang.Math.max(r0, r4)
        L23:
            int r2 = r2 + 1
            goto L3
        L26:
            r6 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L26
            throw r6
        L29:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.T.t(boolean):long");
    }

    @Override // B2.t
    public final B2.L u(int i7, int i10) {
        return A(new S(i7, false));
    }

    public final boolean v() {
        return this.f22729W != -9223372036854775807L;
    }

    @Override // B2.t
    public final void w(B2.F f10) {
        this.f22709C.post(new RunnableC1918s(this, 6, f10));
    }

    public final void x() {
        long j3;
        C1208p c1208p;
        int i7;
        C1208p c1208p2;
        if (this.f22733a0 || this.f22715I || !this.f22714H || this.f22719M == null) {
            return;
        }
        for (Z z6 : this.f22712F) {
            synchronized (z6) {
                c1208p2 = z6.f22789y ? null : z6.f22790z;
            }
            if (c1208p2 == null) {
                return;
            }
        }
        B2.P p10 = this.f22746z;
        synchronized (p10) {
            p10.l = false;
        }
        int length = this.f22712F.length;
        e2.g0[] g0VarArr = new e2.g0[length];
        boolean[] zArr = new boolean[length];
        int i10 = 0;
        while (true) {
            j3 = this.f22743w;
            if (i10 >= length) {
                break;
            }
            Z z10 = this.f22712F[i10];
            synchronized (z10) {
                c1208p = z10.f22789y ? null : z10.f22790z;
            }
            c1208p.getClass();
            String str = c1208p.f14316n;
            boolean zH = e2.L.h(str);
            boolean z11 = zH || e2.L.k(str);
            zArr[i10] = z11;
            this.f22716J |= z11;
            this.f22717K = j3 != -9223372036854775807L && length == 1 && e2.L.i(str);
            O2.b bVar = this.f22711E;
            if (bVar != null) {
                if (zH || this.f22713G[i10].f22705b) {
                    e2.K k10 = c1208p.l;
                    e2.K k11 = k10 == null ? new e2.K(bVar) : k10.a(bVar);
                    C1207o c1207oA = c1208p.a();
                    c1207oA.f14245k = k11;
                    c1208p = new C1208p(c1207oA);
                }
                if (zH && c1208p.f14311h == -1 && c1208p.f14312i == -1 && (i7 = bVar.l) != -1) {
                    C1207o c1207oA2 = c1208p.a();
                    c1207oA2.f14242h = i7;
                    c1208p = new C1208p(c1207oA2);
                }
            }
            int iE = this.f22735n.e(c1208p);
            C1207o c1207oA3 = c1208p.a();
            c1207oA3.f14236K = iE;
            C1208p c1208p3 = new C1208p(c1207oA3);
            g0VarArr[i10] = new e2.g0(Integer.toString(i10), c1208p3);
            this.f22725S = c1208p3.f14321t | this.f22725S;
            i10++;
        }
        this.f22718L = new B8.t(new n0(g0VarArr), zArr);
        if (this.f22717K && this.f22720N == -9223372036854775807L) {
            this.f22720N = j3;
            this.f22719M = new O(this, this.f22719M);
        }
        this.f22738r.u(this.f22720N, this.f22719M.h(), this.f22721O);
        this.f22715I = true;
        InterfaceC2522z interfaceC2522z = this.f22710D;
        interfaceC2522z.getClass();
        interfaceC2522z.c(this);
    }

    public final void y(int i7) {
        r();
        B8.t tVar = this.f22718L;
        boolean[] zArr = (boolean[]) tVar.f807m;
        if (zArr[i7]) {
            return;
        }
        C1208p c1208p = ((n0) tVar.f808n).a(i7).d[0];
        int iG = e2.L.g(c1208p.f14316n);
        long j3 = this.f22728V;
        B5.u uVar = this.f22736p;
        uVar.k(new C1458d0(uVar, 26, new C2521y(1, iG, c1208p, AbstractC1343A.T(j3), -9223372036854775807L)));
        zArr[i7] = true;
    }

    public final void z(int i7) {
        r();
        boolean[] zArr = (boolean[]) this.f22718L.o;
        if (this.f22730X && zArr[i7] && !this.f22712F[i7].i(false)) {
            this.f22729W = 0L;
            this.f22730X = false;
            this.f22724R = true;
            this.f22728V = 0L;
            this.f22731Y = 0;
            for (Z z6 : this.f22712F) {
                z6.l(false);
            }
            InterfaceC2522z interfaceC2522z = this.f22710D;
            interfaceC2522z.getClass();
            interfaceC2522z.e(this);
        }
    }
}
