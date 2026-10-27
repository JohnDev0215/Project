package U2;

import B2.C;
import B2.E;
import h2.AbstractC1343A;
import h2.p;
import java.math.RoundingMode;

/* JADX INFO: loaded from: classes.dex */
public final class b implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f8966a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8967b;
    public final C c;

    public b(long j3, long j10, long j11) {
        this.c = new C(j3, new long[]{j10}, new long[]{0});
        this.f8966a = j11;
        int i7 = -2147483647;
        if (j3 == -9223372036854775807L) {
            this.f8967b = -2147483647;
            return;
        }
        long jN = AbstractC1343A.N(j10 - j11, 8L, j3, RoundingMode.HALF_UP);
        if (jN > 0 && jN <= 2147483647L) {
            i7 = (int) jN;
        }
        this.f8967b = i7;
    }

    @Override // U2.f
    public final long d() {
        return this.f8966a;
    }

    @Override // B2.F
    public final boolean h() {
        return this.c.h();
    }

    @Override // U2.f
    public final long i(long j3) {
        C c = this.c;
        p pVar = c.f502b;
        if (pVar.f15229a == 0) {
            return -9223372036854775807L;
        }
        return pVar.e(AbstractC1343A.c(c.f501a, j3));
    }

    @Override // B2.F
    public final E j(long j3) {
        return this.c.j(j3);
    }

    @Override // U2.f
    public final int k() {
        return this.f8967b;
    }

    @Override // B2.F
    public final long l() {
        return this.c.c;
    }
}
