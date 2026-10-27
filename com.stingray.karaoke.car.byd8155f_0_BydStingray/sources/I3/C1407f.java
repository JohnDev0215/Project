package i3;

import B2.E;
import B2.F;
import B2.G;
import T0.j;
import h2.AbstractC1343A;
import java.math.RoundingMode;

/* JADX INFO: renamed from: i3.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1407f implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f15744a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f15745b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f15746e;

    public C1407f(j jVar, int i7, long j3, long j10) {
        this.f15744a = jVar;
        this.f15745b = i7;
        this.c = j3;
        long j11 = (j10 - j3) / ((long) jVar.o);
        this.d = j11;
        this.f15746e = a(j11);
    }

    public final long a(long j3) {
        long j10 = j3 * ((long) this.f15745b);
        long j11 = this.f15744a.f8455n;
        int i7 = AbstractC1343A.f15195a;
        return AbstractC1343A.N(j10, 1000000L, j11, RoundingMode.DOWN);
    }

    @Override // B2.F
    public final boolean h() {
        return true;
    }

    @Override // B2.F
    public final E j(long j3) {
        j jVar = this.f15744a;
        long j10 = (((long) jVar.f8455n) * j3) / (((long) this.f15745b) * 1000000);
        long j11 = this.d;
        long j12 = AbstractC1343A.j(j10, 0L, j11 - 1);
        long j13 = ((long) jVar.o) * j12;
        long j14 = this.c;
        long jA = a(j12);
        G g7 = new G(jA, j13 + j14);
        if (jA >= j3 || j12 == j11 - 1) {
            return new E(g7, g7);
        }
        long j15 = j12 + 1;
        return new E(g7, new G(a(j15), (((long) jVar.o) * j15) + j14));
    }

    @Override // B2.F
    public final long l() {
        return this.f15746e;
    }
}
