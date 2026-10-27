package V2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f9445a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f9446b;
    public final long[] c;
    public final int[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9447e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long[] f9448f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f9449g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f9450h;

    public s(p pVar, long[] jArr, int[] iArr, int i7, long[] jArr2, int[] iArr2, long j3) {
        AbstractC1346c.c(iArr.length == jArr2.length);
        AbstractC1346c.c(jArr.length == jArr2.length);
        AbstractC1346c.c(iArr2.length == jArr2.length);
        this.f9445a = pVar;
        this.c = jArr;
        this.d = iArr;
        this.f9447e = i7;
        this.f9448f = jArr2;
        this.f9449g = iArr2;
        this.f9450h = j3;
        this.f9446b = jArr.length;
        if (iArr2.length > 0) {
            int length = iArr2.length - 1;
            iArr2[length] = iArr2[length] | 536870912;
        }
    }

    public final int a(long j3) {
        long[] jArr = this.f9448f;
        for (int iB = AbstractC1343A.b(jArr, j3, true); iB < jArr.length; iB++) {
            if ((this.f9449g[iB] & 1) != 0) {
                return iB;
            }
        }
        return -1;
    }
}
