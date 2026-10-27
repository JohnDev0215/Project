package n2;

import android.os.SystemClock;
import d5.d0;
import e2.f0;
import h2.AbstractC1343A;
import java.util.List;
import u2.C2492C;
import u2.n0;

/* JADX INFO: loaded from: classes.dex */
public final class U {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C2492C f19585u = new C2492C(new Object());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f0 f19586a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2492C f19587b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f19588e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1913m f19589f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f19590g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final n0 f19591h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final w2.s f19592i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List f19593j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C2492C f19594k;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f19595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f19596n;
    public final e2.P o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f19597p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile long f19598q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile long f19599r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile long f19600s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public volatile long f19601t;

    public U(f0 f0Var, C2492C c2492c, long j3, long j10, int i7, C1913m c1913m, boolean z6, n0 n0Var, w2.s sVar, List list, C2492C c2492c2, boolean z10, int i10, int i11, e2.P p10, long j11, long j12, long j13, long j14, boolean z11) {
        this.f19586a = f0Var;
        this.f19587b = c2492c;
        this.c = j3;
        this.d = j10;
        this.f19588e = i7;
        this.f19589f = c1913m;
        this.f19590g = z6;
        this.f19591h = n0Var;
        this.f19592i = sVar;
        this.f19593j = list;
        this.f19594k = c2492c2;
        this.l = z10;
        this.f19595m = i10;
        this.f19596n = i11;
        this.o = p10;
        this.f19598q = j11;
        this.f19599r = j12;
        this.f19600s = j13;
        this.f19601t = j14;
        this.f19597p = z11;
    }

    public static U i(w2.s sVar) {
        e2.b0 b0Var = f0.f14097a;
        C2492C c2492c = f19585u;
        return new U(b0Var, c2492c, -9223372036854775807L, 0L, 1, null, false, n0.d, sVar, d0.f13415p, c2492c, false, 1, 0, e2.P.d, 0L, 0L, 0L, 0L, false);
    }

    public final U a() {
        return new U(this.f19586a, this.f19587b, this.c, this.d, this.f19588e, this.f19589f, this.f19590g, this.f19591h, this.f19592i, this.f19593j, this.f19594k, this.l, this.f19595m, this.f19596n, this.o, this.f19598q, this.f19599r, j(), SystemClock.elapsedRealtime(), this.f19597p);
    }

    public final U b(C2492C c2492c) {
        return new U(this.f19586a, this.f19587b, this.c, this.d, this.f19588e, this.f19589f, this.f19590g, this.f19591h, this.f19592i, this.f19593j, c2492c, this.l, this.f19595m, this.f19596n, this.o, this.f19598q, this.f19599r, this.f19600s, this.f19601t, this.f19597p);
    }

    public final U c(C2492C c2492c, long j3, long j10, long j11, long j12, n0 n0Var, w2.s sVar, List list) {
        return new U(this.f19586a, c2492c, j10, j11, this.f19588e, this.f19589f, this.f19590g, n0Var, sVar, list, this.f19594k, this.l, this.f19595m, this.f19596n, this.o, this.f19598q, j12, j3, SystemClock.elapsedRealtime(), this.f19597p);
    }

    public final U d(int i7, int i10, boolean z6) {
        return new U(this.f19586a, this.f19587b, this.c, this.d, this.f19588e, this.f19589f, this.f19590g, this.f19591h, this.f19592i, this.f19593j, this.f19594k, z6, i7, i10, this.o, this.f19598q, this.f19599r, this.f19600s, this.f19601t, this.f19597p);
    }

    public final U e(C1913m c1913m) {
        return new U(this.f19586a, this.f19587b, this.c, this.d, this.f19588e, c1913m, this.f19590g, this.f19591h, this.f19592i, this.f19593j, this.f19594k, this.l, this.f19595m, this.f19596n, this.o, this.f19598q, this.f19599r, this.f19600s, this.f19601t, this.f19597p);
    }

    public final U f(e2.P p10) {
        return new U(this.f19586a, this.f19587b, this.c, this.d, this.f19588e, this.f19589f, this.f19590g, this.f19591h, this.f19592i, this.f19593j, this.f19594k, this.l, this.f19595m, this.f19596n, p10, this.f19598q, this.f19599r, this.f19600s, this.f19601t, this.f19597p);
    }

    public final U g(int i7) {
        return new U(this.f19586a, this.f19587b, this.c, this.d, i7, this.f19589f, this.f19590g, this.f19591h, this.f19592i, this.f19593j, this.f19594k, this.l, this.f19595m, this.f19596n, this.o, this.f19598q, this.f19599r, this.f19600s, this.f19601t, this.f19597p);
    }

    public final U h(f0 f0Var) {
        return new U(f0Var, this.f19587b, this.c, this.d, this.f19588e, this.f19589f, this.f19590g, this.f19591h, this.f19592i, this.f19593j, this.f19594k, this.l, this.f19595m, this.f19596n, this.o, this.f19598q, this.f19599r, this.f19600s, this.f19601t, this.f19597p);
    }

    public final long j() {
        long j3;
        long j10;
        if (!k()) {
            return this.f19600s;
        }
        do {
            j3 = this.f19601t;
            j10 = this.f19600s;
        } while (j3 != this.f19601t);
        return AbstractC1343A.H(AbstractC1343A.T(j10) + ((long) ((SystemClock.elapsedRealtime() - j3) * this.o.f13995a)));
    }

    public final boolean k() {
        return this.f19588e == 3 && this.l && this.f19596n == 0;
    }
}
