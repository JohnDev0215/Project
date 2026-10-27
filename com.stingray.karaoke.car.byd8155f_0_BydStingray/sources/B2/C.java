package B2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class C implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h2.p f501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.p f502b;
    public long c;

    public C(long j3, long[] jArr, long[] jArr2) {
        AbstractC1346c.c(jArr.length == jArr2.length);
        int length = jArr2.length;
        if (length <= 0 || jArr2[0] <= 0) {
            this.f501a = new h2.p(length);
            this.f502b = new h2.p(length);
        } else {
            int i7 = length + 1;
            h2.p pVar = new h2.p(i7);
            this.f501a = pVar;
            h2.p pVar2 = new h2.p(i7);
            this.f502b = pVar2;
            pVar.a(0L);
            pVar2.a(0L);
        }
        this.f501a.c(jArr);
        this.f502b.c(jArr2);
        this.c = j3;
    }

    public final void a(long j3, long j10) {
        h2.p pVar = this.f502b;
        int i7 = pVar.f15229a;
        h2.p pVar2 = this.f501a;
        if (i7 == 0 && j3 > 0) {
            pVar2.a(0L);
            pVar.a(0L);
        }
        pVar2.a(j10);
        pVar.a(j3);
    }

    @Override // B2.F
    public final boolean h() {
        return this.f502b.f15229a > 0;
    }

    @Override // B2.F
    public final E j(long j3) {
        h2.p pVar = this.f502b;
        if (pVar.f15229a == 0) {
            G g7 = G.c;
            return new E(g7, g7);
        }
        int iC = AbstractC1343A.c(pVar, j3);
        long jE = pVar.e(iC);
        h2.p pVar2 = this.f501a;
        G g10 = new G(jE, pVar2.e(iC));
        if (jE == j3 || iC == pVar.f15229a - 1) {
            return new E(g10, g10);
        }
        int i7 = iC + 1;
        return new E(g10, new G(pVar.e(i7), pVar2.e(i7)));
    }

    @Override // B2.F
    public final long l() {
        return this.c;
    }
}
