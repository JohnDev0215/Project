package u2;

import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import e2.C1208p;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import i3.C1406e;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j0 implements InterfaceC2490A, x2.j {
    public final k2.m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final M.t f22845m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final k2.E f22846n;
    public final x2.i o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final B5.u f22847p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final n0 f22848q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final long f22850s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C1208p f22852u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f22853v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f22854w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public byte[] f22855x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f22856y;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f22849r = new ArrayList();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final x2.n f22851t = new x2.n("SingleSampleMediaPeriod");

    public j0(k2.m mVar, M.t tVar, k2.E e10, C1208p c1208p, long j3, x2.i iVar, B5.u uVar, boolean z6) {
        this.l = mVar;
        this.f22845m = tVar;
        this.f22846n = e10;
        this.f22852u = c1208p;
        this.f22850s = j3;
        this.o = iVar;
        this.f22847p = uVar;
        this.f22853v = z6;
        this.f22848q = new n0(new e2.g0("", c1208p));
    }

    @Override // u2.InterfaceC2490A
    public final long a(long j3, n2.b0 b0Var) {
        return j3;
    }

    @Override // u2.InterfaceC2490A
    public final void b(InterfaceC2522z interfaceC2522z, long j3) {
        interfaceC2522z.c(this);
    }

    @Override // x2.j
    public final void c(x2.l lVar) {
        i0 i0Var = (i0) lVar;
        this.f22856y = (int) i0Var.f22841b.f17631m;
        byte[] bArr = i0Var.c;
        bArr.getClass();
        this.f22855x = bArr;
        this.f22854w = true;
        Uri uri = i0Var.f22841b.f17632n;
        C2516t c2516t = new C2516t();
        this.o.getClass();
        B5.u uVar = this.f22847p;
        uVar.k(new C2494E(uVar, c2516t, new C2521y(1, -1, this.f22852u, AbstractC1343A.T(0L), AbstractC1343A.T(this.f22850s)), 1));
    }

    @Override // u2.b0
    public final boolean d() {
        return this.f22851t.a();
    }

    @Override // x2.j
    public final C1406e f(x2.l lVar, IOException iOException, int i7) {
        C1406e c1406e;
        Uri uri = ((i0) lVar).f22841b.f17632n;
        C2516t c2516t = new C2516t();
        long j3 = this.f22850s;
        AbstractC1343A.T(j3);
        C1.j jVar = new C1.j(iOException, i7);
        x2.i iVar = this.o;
        long jD = iVar.d(jVar);
        boolean z6 = jD == -9223372036854775807L || i7 >= iVar.y(1);
        if (this.f22853v && z6) {
            AbstractC1346c.A("SingleSampleMediaPeriod", "Loading failed, treating as end-of-stream.", iOException);
            this.f22854w = true;
            c1406e = x2.n.d;
        } else {
            c1406e = jD != -9223372036854775807L ? new C1406e(jD, 0) : x2.n.f23756e;
        }
        C1406e c1406e2 = c1406e;
        int i10 = c1406e2.f15742a;
        boolean z10 = i10 == 0 || i10 == 1;
        B5.u uVar = this.f22847p;
        uVar.k(new C2495F(uVar, c2516t, new C2521y(1, -1, this.f22852u, AbstractC1343A.T(0L), AbstractC1343A.T(j3)), iOException, !z10));
        return c1406e2;
    }

    @Override // u2.b0
    public final long g() {
        return (this.f22854w || this.f22851t.a()) ? Long.MIN_VALUE : 0L;
    }

    @Override // u2.InterfaceC2490A
    public final long h() {
        return -9223372036854775807L;
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        if (!this.f22854w) {
            x2.n nVar = this.f22851t;
            if (!nVar.a() && nVar.c == null) {
                k2.h hVarQ = this.f22845m.q();
                k2.E e10 = this.f22846n;
                if (e10 != null) {
                    ((k2.n) hVarQ).b(e10);
                }
                k2.m mVar = this.l;
                i0 i0Var = new i0(mVar, hVarQ);
                int iY = this.o.y(1);
                Looper looperMyLooper = Looper.myLooper();
                AbstractC1346c.k(looperMyLooper);
                nVar.c = null;
                x2.k kVar = new x2.k(nVar, looperMyLooper, i0Var, this, iY, SystemClock.elapsedRealtime());
                AbstractC1346c.j(nVar.f23758b == null);
                nVar.f23758b = kVar;
                SystemClock.elapsedRealtime();
                kVar.f23750n.getClass();
                kVar.o = null;
                x2.k kVar2 = nVar.f23758b;
                kVar2.getClass();
                nVar.f23757a.execute(kVar2);
                C2516t c2516t = new C2516t(mVar);
                B5.u uVar = this.f22847p;
                uVar.k(new C2494E(uVar, c2516t, new C2521y(1, -1, this.f22852u, AbstractC1343A.T(0L), AbstractC1343A.T(this.f22850s)), 0));
                return true;
            }
        }
        return false;
    }

    @Override // u2.InterfaceC2490A
    public final n0 j() {
        return this.f22848q;
    }

    @Override // u2.InterfaceC2490A
    public final long k(w2.q[] qVarArr, boolean[] zArr, a0[] a0VarArr, boolean[] zArr2, long j3) {
        for (int i7 = 0; i7 < qVarArr.length; i7++) {
            a0 a0Var = a0VarArr[i7];
            ArrayList arrayList = this.f22849r;
            if (a0Var != null && (qVarArr[i7] == null || !zArr[i7])) {
                arrayList.remove(a0Var);
                a0VarArr[i7] = null;
            }
            if (a0VarArr[i7] == null && qVarArr[i7] != null) {
                h0 h0Var = new h0(this);
                arrayList.add(h0Var);
                a0VarArr[i7] = h0Var;
                zArr2[i7] = true;
            }
        }
        return j3;
    }

    @Override // u2.b0
    public final long l() {
        return this.f22854w ? Long.MIN_VALUE : 0L;
    }

    @Override // u2.InterfaceC2490A
    public final void m() {
    }

    @Override // x2.j
    public final void n(x2.l lVar, boolean z6) {
        Uri uri = ((i0) lVar).f22841b.f17632n;
        C2516t c2516t = new C2516t();
        this.o.getClass();
        B5.u uVar = this.f22847p;
        uVar.k(new C2494E(uVar, c2516t, new C2521y(1, -1, null, AbstractC1343A.T(0L), AbstractC1343A.T(this.f22850s)), 2));
    }

    @Override // u2.InterfaceC2490A
    public final long o(long j3) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f22849r;
            if (i7 >= arrayList.size()) {
                return j3;
            }
            h0 h0Var = (h0) arrayList.get(i7);
            if (h0Var.l == 2) {
                h0Var.l = 1;
            }
            i7++;
        }
    }

    @Override // u2.InterfaceC2490A
    public final void p(long j3) {
    }

    @Override // u2.b0
    public final void q(long j3) {
    }
}
