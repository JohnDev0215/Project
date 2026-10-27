package u2;

import android.util.Pair;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Collections;

/* JADX INFO: renamed from: u2.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2510m extends e2.f0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e2.F f22867e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d5.d0 f22868f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d5.d0 f22869g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d5.d0 f22870h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f22871i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f22872j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f22873k;
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22874m;

    public C2510m(e2.F f10, d5.d0 d0Var, d5.d0 d0Var2, d5.d0 d0Var3, boolean z6, boolean z10, long j3, long j10, Object obj) {
        this.f22867e = f10;
        this.f22868f = d0Var;
        this.f22869g = d0Var2;
        this.f22870h = d0Var3;
        this.f22871i = z6;
        this.f22872j = z10;
        this.f22873k = j3;
        this.l = j10;
        this.f22874m = obj;
    }

    @Override // e2.f0
    public final int b(Object obj) {
        if (!(obj instanceof Pair) || !(((Pair) obj).first instanceof Integer)) {
            return -1;
        }
        int iY = C2512o.y(obj);
        int iB = ((e2.f0) this.f22868f.get(iY)).b(C2512o.z(obj));
        if (iB == -1) {
            return -1;
        }
        return ((Integer) this.f22869g.get(iY)).intValue() + iB;
    }

    @Override // e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        int iQ = q(i7);
        ((e2.f0) this.f22868f.get(iQ)).f(i7 - ((Integer) this.f22869g.get(iQ)).intValue(), c0Var, z6);
        c0Var.c = 0;
        c0Var.f14061e = ((Long) this.f22870h.get(i7)).longValue();
        c0Var.d = r(c0Var, i7);
        if (z6) {
            Object obj = c0Var.f14060b;
            AbstractC1346c.g(obj);
            c0Var.f14060b = C2512o.A(iQ, obj);
        }
        return c0Var;
    }

    @Override // e2.f0
    public final e2.c0 g(Object obj, e2.c0 c0Var) {
        int iY = C2512o.y(obj);
        Object objZ = C2512o.z(obj);
        e2.f0 f0Var = (e2.f0) this.f22868f.get(iY);
        int iB = f0Var.b(objZ) + ((Integer) this.f22869g.get(iY)).intValue();
        f0Var.g(objZ, c0Var);
        c0Var.c = 0;
        c0Var.f14061e = ((Long) this.f22870h.get(iB)).longValue();
        c0Var.d = r(c0Var, iB);
        c0Var.f14060b = obj;
        return c0Var;
    }

    @Override // e2.f0
    public final int h() {
        return this.f22870h.o;
    }

    @Override // e2.f0
    public final Object l(int i7) {
        int iQ = q(i7);
        return C2512o.A(iQ, ((e2.f0) this.f22868f.get(iQ)).l(i7 - ((Integer) this.f22869g.get(iQ)).intValue()));
    }

    @Override // e2.f0
    public final e2.e0 m(int i7, e2.e0 e0Var, long j3) {
        Object obj = e2.e0.f14075q;
        d5.d0 d0Var = this.f22870h;
        e0Var.b(obj, this.f22867e, this.f22874m, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, this.f22871i, this.f22872j, null, this.l, this.f22873k, 0, d0Var.size() - 1, -((Long) d0Var.get(0)).longValue());
        return e0Var;
    }

    @Override // e2.f0
    public final int o() {
        return 1;
    }

    public final int q(int i7) {
        Integer numValueOf = Integer.valueOf(i7 + 1);
        int i10 = AbstractC1343A.f15195a;
        d5.d0 d0Var = this.f22869g;
        int iBinarySearch = Collections.binarySearch(d0Var, numValueOf);
        if (iBinarySearch < 0) {
            return -(iBinarySearch + 2);
        }
        do {
            iBinarySearch--;
            if (iBinarySearch < 0) {
                break;
            }
        } while (((Comparable) d0Var.get(iBinarySearch)).compareTo(numValueOf) == 0);
        return iBinarySearch;
    }

    public final long r(e2.c0 c0Var, int i7) {
        if (c0Var.d == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        d5.d0 d0Var = this.f22870h;
        return (i7 == d0Var.size() + (-1) ? this.f22873k : ((Long) d0Var.get(i7 + 1)).longValue()) - ((Long) d0Var.get(i7)).longValue();
    }
}
