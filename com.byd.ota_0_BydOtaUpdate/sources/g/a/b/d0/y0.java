package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class y0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4662d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4663e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4664f;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (!this.f4663e) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 8 <= bArr2.length) {
            return this.f4664f ? e(bArr, i2, bArr2, i3) : d(bArr, i2, bArr2, i3);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // g.a.b.e
    public int b() {
        return 8;
    }

    public final int c(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        int i5 = (bArr[i2] << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        return (bArr[i6] & 255) | i5 | ((bArr[i4] & 255) << 8);
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iC = c(bArr, i2);
        int iC2 = c(bArr, i2 + 4);
        int i4 = -957401312;
        for (int i5 = 0; i5 != 32; i5++) {
            iC2 -= (((iC << 4) + this.f4661c) ^ (iC + i4)) ^ ((iC >>> 5) + this.f4662d);
            iC -= (((iC2 << 4) + this.f4659a) ^ (iC2 + i4)) ^ ((iC2 >>> 5) + this.f4660b);
            i4 += 1640531527;
        }
        g(iC, bArr2, i3);
        g(iC2, bArr2, i3 + 4);
        return 8;
    }

    public final int e(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iC = c(bArr, i2);
        int iC2 = c(bArr, i2 + 4);
        int i4 = iC;
        int i5 = 0;
        for (int i6 = 0; i6 != 32; i6++) {
            i5 -= 1640531527;
            i4 += (((iC2 << 4) + this.f4659a) ^ (iC2 + i5)) ^ ((iC2 >>> 5) + this.f4660b);
            iC2 += (((i4 << 4) + this.f4661c) ^ (i4 + i5)) ^ ((i4 >>> 5) + this.f4662d);
        }
        g(i4, bArr2, i3);
        g(iC2, bArr2, i3 + 4);
        return 8;
    }

    public final void f(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        this.f4659a = c(bArr, 0);
        this.f4660b = c(bArr, 4);
        this.f4661c = c(bArr, 8);
        this.f4662d = c(bArr, 12);
    }

    public final void g(int i2, byte[] bArr, int i3) {
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 24);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 16);
        bArr[i5] = (byte) (i2 >>> 8);
        bArr[i5 + 1] = (byte) i2;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "TEA";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4664f = z;
            this.f4663e = true;
            f(((g.a.b.j0.s0) iVar).a());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to TEA init - " + iVar.getClass().getName());
        }
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
