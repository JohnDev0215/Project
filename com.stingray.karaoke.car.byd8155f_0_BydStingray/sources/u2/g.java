package U2;

import B2.E;
import B2.G;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long[] f8987a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f8988b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8989e;

    public g(long[] jArr, long[] jArr2, long j3, long j10, int i7) {
        this.f8987a = jArr;
        this.f8988b = jArr2;
        this.c = j3;
        this.d = j10;
        this.f8989e = i7;
    }

    @Override // U2.f
    public final long d() {
        return this.d;
    }

    @Override // B2.F
    public final boolean h() {
        return true;
    }

    @Override // U2.f
    public final long i(long j3) {
        return this.f8987a[AbstractC1343A.e(this.f8988b, j3, true)];
    }

    @Override // B2.F
    public final E j(long j3) {
        long[] jArr = this.f8987a;
        int iE = AbstractC1343A.e(jArr, j3, true);
        long j10 = jArr[iE];
        long[] jArr2 = this.f8988b;
        G g7 = new G(j10, jArr2[iE]);
        if (j10 >= j3 || iE == jArr.length - 1) {
            return new E(g7, g7);
        }
        int i7 = iE + 1;
        return new E(g7, new G(jArr[i7], jArr2[i7]));
    }

    @Override // U2.f
    public final int k() {
        return this.f8989e;
    }

    @Override // B2.F
    public final long l() {
        return this.c;
    }
}
