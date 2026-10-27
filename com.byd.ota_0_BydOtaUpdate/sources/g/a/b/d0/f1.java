package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class f1 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f4473a = new int[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f4474b = new int[32];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int[] f4475c = new int[32];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4476d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4477e;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (!this.f4476d) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 8 <= bArr2.length) {
            return this.f4477e ? e(bArr, i2, bArr2, i3) : d(bArr, i2, bArr2, i3);
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
        for (int i4 = 31; i4 >= 0; i4--) {
            iC2 -= (((iC << 4) ^ (iC >>> 5)) + iC) ^ this.f4475c[i4];
            iC -= (((iC2 << 4) ^ (iC2 >>> 5)) + iC2) ^ this.f4474b[i4];
        }
        g(iC, bArr2, i3);
        g(iC2, bArr2, i3 + 4);
        return 8;
    }

    public final int e(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iC = c(bArr, i2);
        int iC2 = c(bArr, i2 + 4);
        for (int i4 = 0; i4 < 32; i4++) {
            iC += (((iC2 << 4) ^ (iC2 >>> 5)) + iC2) ^ this.f4474b[i4];
            iC2 += (((iC << 4) ^ (iC >>> 5)) + iC) ^ this.f4475c[i4];
        }
        g(iC, bArr2, i3);
        g(iC2, bArr2, i3 + 4);
        return 8;
    }

    public final void f(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 < 4) {
            this.f4473a[i2] = c(bArr, i3);
            i2++;
            i3 += 4;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 32; i5++) {
            int[] iArr = this.f4474b;
            int[] iArr2 = this.f4473a;
            iArr[i5] = iArr2[i4 & 3] + i4;
            i4 -= 1640531527;
            this.f4475c[i5] = iArr2[(i4 >>> 11) & 3] + i4;
        }
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
        return "XTEA";
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4477e = z;
            this.f4476d = true;
            f(((g.a.b.j0.s0) iVar).a());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to TEA init - " + iVar.getClass().getName());
        }
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
