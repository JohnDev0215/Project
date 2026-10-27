package U2;

import B2.E;
import B2.G;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class h implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f8990a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8991b;
    public final long c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f8992e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f8993f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long[] f8994g;

    public h(long j3, int i7, long j10, int i10, long j11, long[] jArr) {
        this.f8990a = j3;
        this.f8991b = i7;
        this.c = j10;
        this.d = i10;
        this.f8992e = j11;
        this.f8994g = jArr;
        this.f8993f = j11 != -1 ? j3 + j11 : -1L;
    }

    @Override // U2.f
    public final long d() {
        return this.f8993f;
    }

    @Override // B2.F
    public final boolean h() {
        return this.f8994g != null;
    }

    @Override // U2.f
    public final long i(long j3) {
        long j10 = j3 - this.f8990a;
        if (!h() || j10 <= this.f8991b) {
            return 0L;
        }
        long[] jArr = this.f8994g;
        AbstractC1346c.k(jArr);
        double d = (j10 * 256.0d) / this.f8992e;
        int iE = AbstractC1343A.e(jArr, (long) d, true);
        long j11 = this.c;
        long j12 = (((long) iE) * j11) / 100;
        long j13 = jArr[iE];
        int i7 = iE + 1;
        long j14 = (j11 * ((long) i7)) / 100;
        return Math.round((j13 == (iE == 99 ? 256L : jArr[i7]) ? 0.0d : (d - j13) / (r0 - j13)) * (j14 - j12)) + j12;
    }

    @Override // B2.F
    public final E j(long j3) {
        double d;
        boolean zH = h();
        int i7 = this.f8991b;
        long j10 = this.f8990a;
        if (!zH) {
            G g7 = new G(0L, j10 + ((long) i7));
            return new E(g7, g7);
        }
        long j11 = AbstractC1343A.j(j3, 0L, this.c);
        double d10 = (j11 * 100.0d) / this.c;
        double d11 = 0.0d;
        if (d10 <= 0.0d) {
            d = 256.0d;
        } else if (d10 >= 100.0d) {
            d = 256.0d;
            d11 = 256.0d;
        } else {
            int i10 = (int) d10;
            long[] jArr = this.f8994g;
            AbstractC1346c.k(jArr);
            double d12 = jArr[i10];
            d11 = (((i10 == 99 ? 256.0d : jArr[i10 + 1]) - d12) * (d10 - ((double) i10))) + d12;
            d = 256.0d;
        }
        long j12 = this.f8992e;
        G g10 = new G(j11, j10 + AbstractC1343A.j(Math.round((d11 / d) * j12), i7, j12 - 1));
        return new E(g10, g10);
    }

    @Override // U2.f
    public final int k() {
        return this.d;
    }

    @Override // B2.F
    public final long l() {
        return this.c;
    }
}
