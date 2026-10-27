package g.a.b.d0;

/* JADX INFO: loaded from: classes.dex */
public class i0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4503a = 12;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f4504b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4505c;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        return this.f4505c ? e(bArr, i2, bArr2, i3) : d(bArr, i2, bArr2, i3);
    }

    @Override // g.a.b.e
    public int b() {
        return 16;
    }

    public final long c(byte[] bArr, int i2) {
        long j = 0;
        for (int i3 = 7; i3 >= 0; i3--) {
            j = (j << 8) + ((long) (bArr[i3 + i2] & 255));
        }
        return j;
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        long jC = c(bArr, i2);
        long jC2 = c(bArr, i2 + 8);
        for (int i4 = this.f4503a; i4 >= 1; i4--) {
            int i5 = i4 * 2;
            jC2 = g(jC2 - this.f4504b[i5 + 1], jC) ^ jC;
            jC = g(jC - this.f4504b[i5], jC2) ^ jC2;
        }
        i(jC - this.f4504b[0], bArr2, i3);
        i(jC2 - this.f4504b[1], bArr2, i3 + 8);
        return 16;
    }

    public final int e(byte[] bArr, int i2, byte[] bArr2, int i3) {
        long jC = c(bArr, i2) + this.f4504b[0];
        long jC2 = c(bArr, i2 + 8) + this.f4504b[1];
        for (int i4 = 1; i4 <= this.f4503a; i4++) {
            int i5 = i4 * 2;
            jC = f(jC ^ jC2, jC2) + this.f4504b[i5];
            jC2 = f(jC2 ^ jC, jC) + this.f4504b[i5 + 1];
        }
        i(jC, bArr2, i3);
        i(jC2, bArr2, i3 + 8);
        return 16;
    }

    public final long f(long j, long j2) {
        long j3 = j2 & 63;
        return (j >>> ((int) (64 - j3))) | (j << ((int) j3));
    }

    public final long g(long j, long j2) {
        long j3 = j2 & 63;
        return (j << ((int) (64 - j3))) | (j >>> ((int) j3));
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "RC5-64";
    }

    public final void h(byte[] bArr) {
        long[] jArr;
        int length = (bArr.length + 7) / 8;
        long[] jArr2 = new long[length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = i2 / 8;
            jArr2[i3] = jArr2[i3] + (((long) (bArr[i2] & 255)) << ((i2 % 8) * 8));
        }
        long[] jArr3 = new long[(this.f4503a + 1) * 2];
        this.f4504b = jArr3;
        jArr3[0] = -5196783011329398165L;
        int i4 = 1;
        while (true) {
            jArr = this.f4504b;
            if (i4 >= jArr.length) {
                break;
            }
            jArr[i4] = jArr[i4 - 1] - 7046029254386353131L;
            i4++;
        }
        int length2 = length > jArr.length ? length * 3 : jArr.length * 3;
        long jF = 0;
        long jF2 = 0;
        int length3 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length2; i6++) {
            long[] jArr4 = this.f4504b;
            jF = f(jArr4[length3] + jF + jF2, 3L);
            jArr4[length3] = jF;
            jF2 = f(jArr2[i5] + jF + jF2, jF2 + jF);
            jArr2[i5] = jF2;
            length3 = (length3 + 1) % this.f4504b.length;
            i5 = (i5 + 1) % length;
        }
    }

    public final void i(long j, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 8; i3++) {
            bArr[i3 + i2] = (byte) j;
            j >>>= 8;
        }
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (!(iVar instanceof g.a.b.j0.b1)) {
            throw new IllegalArgumentException("invalid parameter passed to RC564 init - " + iVar.getClass().getName());
        }
        g.a.b.j0.b1 b1Var = (g.a.b.j0.b1) iVar;
        this.f4505c = z;
        this.f4503a = b1Var.b();
        h(b1Var.a());
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
