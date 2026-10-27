package B2;

import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: renamed from: B2.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0071k implements F {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f579b;
    public final long[] c;
    public final long[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long[] f580e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f581f;

    public C0071k(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.f579b = iArr;
        this.c = jArr;
        this.d = jArr2;
        this.f580e = jArr3;
        int length = iArr.length;
        this.f578a = length;
        if (length > 0) {
            this.f581f = jArr2[length - 1] + jArr3[length - 1];
        } else {
            this.f581f = 0L;
        }
    }

    @Override // B2.F
    public final boolean h() {
        return true;
    }

    @Override // B2.F
    public final E j(long j3) {
        long[] jArr = this.f580e;
        int iE = AbstractC1343A.e(jArr, j3, true);
        long j10 = jArr[iE];
        long[] jArr2 = this.c;
        G g7 = new G(j10, jArr2[iE]);
        if (j10 >= j3 || iE == this.f578a - 1) {
            return new E(g7, g7);
        }
        int i7 = iE + 1;
        return new E(g7, new G(jArr[i7], jArr2[i7]));
    }

    @Override // B2.F
    public final long l() {
        return this.f581f;
    }

    public final String toString() {
        return "ChunkIndex(length=" + this.f578a + ", sizes=" + Arrays.toString(this.f579b) + ", offsets=" + Arrays.toString(this.c) + ", timeUs=" + Arrays.toString(this.f580e) + ", durationsUs=" + Arrays.toString(this.d) + ")";
    }
}
