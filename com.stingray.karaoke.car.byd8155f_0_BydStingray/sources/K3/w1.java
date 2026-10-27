package k3;

import a.AbstractC0904a;
import d5.C1077F;
import d5.C1078G;
import e2.C1194b;
import h2.AbstractC1343A;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class w1 extends e2.f0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final w1 f18151g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f18152h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d5.I f18153e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final v1 f18154f;

    static {
        C1078G c1078g = d5.I.f13384m;
        f18151g = new w1(d5.d0.f13415p, null);
        f18152h = new Object();
    }

    public w1(d5.I i7, v1 v1Var) {
        this.f18153e = i7;
        this.f18154f = v1Var;
    }

    @Override // e2.f0
    public final int b(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // e2.f0
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w1)) {
            return false;
        }
        w1 w1Var = (w1) obj;
        return AbstractC0904a.A(this.f18153e, w1Var.f18153e) && AbstractC0904a.A(this.f18154f, w1Var.f18154f);
    }

    @Override // e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        v1 v1VarS = s(i7);
        Long lValueOf = Long.valueOf(v1VarS.f18143b);
        long jH = AbstractC1343A.H(v1VarS.c);
        c0Var.getClass();
        c0Var.i(lValueOf, null, i7, jH, 0L, C1194b.f14037f, false);
        return c0Var;
    }

    @Override // e2.f0
    public final int h() {
        return o();
    }

    @Override // e2.f0
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f18153e, this.f18154f});
    }

    @Override // e2.f0
    public final Object l(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // e2.f0
    public final e2.e0 m(int i7, e2.e0 e0Var, long j3) {
        v1 v1VarS = s(i7);
        e0Var.b(f18152h, v1VarS.f18142a, null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, true, false, null, 0L, AbstractC1343A.H(v1VarS.c), i7, i7, 0L);
        return e0Var;
    }

    @Override // e2.f0
    public final int o() {
        return this.f18153e.size() + (this.f18154f == null ? 0 : 1);
    }

    public final w1 q(int i7, List list) {
        C1077F c1077f = new C1077F(4);
        d5.I i10 = this.f18153e;
        c1077f.e(i10.subList(0, i7));
        for (int i11 = 0; i11 < list.size(); i11++) {
            c1077f.a(new v1((e2.F) list.get(i11), -1L, -9223372036854775807L));
        }
        c1077f.e(i10.subList(i7, i10.size()));
        return new w1(c1077f.h(), this.f18154f);
    }

    public final long r(int i7) {
        if (i7 >= 0) {
            d5.I i10 = this.f18153e;
            if (i7 < i10.size()) {
                return ((v1) i10.get(i7)).f18143b;
            }
        }
        return -1L;
    }

    public final v1 s(int i7) {
        v1 v1Var;
        d5.I i10 = this.f18153e;
        return (i7 != i10.size() || (v1Var = this.f18154f) == null) ? (v1) i10.get(i7) : v1Var;
    }
}
