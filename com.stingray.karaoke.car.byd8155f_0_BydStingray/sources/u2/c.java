package U2;

import B2.E;
import B2.G;
import android.util.Pair;
import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class c implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long[] f8968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long[] f8969b;
    public final long c;

    public c(long j3, long[] jArr, long[] jArr2) {
        this.f8968a = jArr;
        this.f8969b = jArr2;
        this.c = j3 == -9223372036854775807L ? AbstractC1343A.H(jArr2[jArr2.length - 1]) : j3;
    }

    public static Pair a(long j3, long[] jArr, long[] jArr2) {
        int iE = AbstractC1343A.e(jArr, j3, true);
        long j10 = jArr[iE];
        long j11 = jArr2[iE];
        int i7 = iE + 1;
        if (i7 == jArr.length) {
            return Pair.create(Long.valueOf(j10), Long.valueOf(j11));
        }
        return Pair.create(Long.valueOf(j3), Long.valueOf(((long) ((jArr[i7] == j10 ? 0.0d : (j3 - j10) / (r6 - j10)) * (jArr2[i7] - j11))) + j11));
    }

    @Override // U2.f
    public final long d() {
        return -1L;
    }

    @Override // B2.F
    public final boolean h() {
        return true;
    }

    @Override // U2.f
    public final long i(long j3) {
        return AbstractC1343A.H(((Long) a(j3, this.f8968a, this.f8969b).second).longValue());
    }

    @Override // B2.F
    public final E j(long j3) {
        Pair pairA = a(AbstractC1343A.T(AbstractC1343A.j(j3, 0L, this.c)), this.f8969b, this.f8968a);
        G g7 = new G(AbstractC1343A.H(((Long) pairA.first).longValue()), ((Long) pairA.second).longValue());
        return new E(g7, g7);
    }

    @Override // U2.f
    public final int k() {
        return -2147483647;
    }

    @Override // B2.F
    public final long l() {
        return this.c;
    }
}
