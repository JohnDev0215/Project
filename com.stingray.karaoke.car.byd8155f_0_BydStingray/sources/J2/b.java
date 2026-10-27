package J2;

import A.s1;
import B2.C0073m;
import B2.r;
import B2.s;
import B2.w;
import V2.l;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class b implements r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public T f3052b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3053e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Q2.a f3055g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C0073m f3056h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public s1 f3057i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public l f3058j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t f3051a = new t(6);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f3054f = -1;

    @Override // B2.r
    public final void a() {
        l lVar = this.f3058j;
        if (lVar != null) {
            lVar.getClass();
        }
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        this.f3052b = (T) tVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0187  */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r27, B2.v r28) throws e2.M, java.io.EOFException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 484
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: J2.b.c(B2.s, B2.v):int");
    }

    public final void d() {
        T t10 = this.f3052b;
        t10.getClass();
        t10.e();
        this.f3052b.w(new w(-9223372036854775807L));
        this.c = 6;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        if (j3 == 0) {
            this.c = 0;
            this.f3058j = null;
        } else if (this.c == 5) {
            l lVar = this.f3058j;
            lVar.getClass();
            lVar.e(j3, j10);
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) throws EOFException, InterruptedIOException {
        C0073m c0073m = (C0073m) sVar;
        t tVar = this.f3051a;
        tVar.E(2);
        c0073m.k(tVar.f15241a, 0, 2, false);
        if (tVar.B() != 65496) {
            return false;
        }
        tVar.E(2);
        c0073m.k(tVar.f15241a, 0, 2, false);
        int iB = tVar.B();
        this.d = iB;
        if (iB == 65504) {
            tVar.E(2);
            c0073m.k(tVar.f15241a, 0, 2, false);
            c0073m.e(tVar.B() - 2, false);
            tVar.E(2);
            c0073m.k(tVar.f15241a, 0, 2, false);
            this.d = tVar.B();
        }
        if (this.d != 65505) {
            return false;
        }
        c0073m.e(2, false);
        tVar.E(6);
        c0073m.k(tVar.f15241a, 0, 6, false);
        return tVar.x() == 1165519206 && tVar.B() == 0;
    }
}
