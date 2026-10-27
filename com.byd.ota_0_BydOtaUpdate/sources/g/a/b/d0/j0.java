package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class j0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f4515a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4516b;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iB = b();
        if (this.f4515a == null) {
            throw new IllegalStateException("RC6 engine not initialised");
        }
        if (i2 + iB > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (iB + i3 <= bArr2.length) {
            return this.f4516b ? e(bArr, i2, bArr2, i3) : d(bArr, i2, bArr2, i3);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // g.a.b.e
    public int b() {
        return 16;
    }

    public final int c(byte[] bArr, int i2) {
        int i3 = 0;
        for (int i4 = 3; i4 >= 0; i4--) {
            i3 = (i3 << 8) + (bArr[i4 + i2] & 255);
        }
        return i3;
    }

    public final int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iC = c(bArr, i2);
        int iC2 = c(bArr, i2 + 4);
        int iC3 = c(bArr, i2 + 8);
        int iC4 = c(bArr, i2 + 12);
        int[] iArr = this.f4515a;
        int i4 = iC3 - iArr[43];
        int iG = iC - iArr[42];
        int i5 = 20;
        while (i5 >= 1) {
            int iF = f(((iG * 2) + 1) * iG, 5);
            int iF2 = f(((i4 * 2) + 1) * i4, 5);
            int i6 = i5 * 2;
            int iG2 = g(iC2 - this.f4515a[i6 + 1], iF) ^ iF2;
            i5--;
            int i7 = iG;
            iG = g(iC4 - this.f4515a[i6], iF2) ^ iF;
            iC4 = i4;
            i4 = iG2;
            iC2 = i7;
        }
        int[] iArr2 = this.f4515a;
        int i8 = iC4 - iArr2[1];
        int i9 = iC2 - iArr2[0];
        i(iG, bArr2, i3);
        i(i9, bArr2, i3 + 4);
        i(i4, bArr2, i3 + 8);
        i(i8, bArr2, i3 + 12);
        return 16;
    }

    public final int e(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iC = c(bArr, i2);
        int iC2 = c(bArr, i2 + 4);
        int iC3 = c(bArr, i2 + 8);
        int iC4 = c(bArr, i2 + 12);
        int[] iArr = this.f4515a;
        int i4 = iC2 + iArr[0];
        int i5 = iC4 + iArr[1];
        int i6 = 1;
        while (i6 <= 20) {
            int iF = f(((i4 * 2) + 1) * i4, 5);
            int iF2 = f(((i5 * 2) + 1) * i5, 5);
            int i7 = i6 * 2;
            int iF3 = f(iC ^ iF, iF2) + this.f4515a[i7];
            int iF4 = f(iC3 ^ iF2, iF) + this.f4515a[i7 + 1];
            i6++;
            iC3 = i5;
            i5 = iF3;
            iC = i4;
            i4 = iF4;
        }
        int[] iArr2 = this.f4515a;
        int i8 = iC + iArr2[42];
        int i9 = iC3 + iArr2[43];
        i(i8, bArr2, i3);
        i(i4, bArr2, i3 + 4);
        i(i9, bArr2, i3 + 8);
        i(i5, bArr2, i3 + 12);
        return 16;
    }

    public final int f(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public final int g(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "RC6";
    }

    public final void h(byte[] bArr) {
        int[] iArr;
        int length = (bArr.length + 3) / 4;
        int length2 = ((bArr.length + 4) - 1) / 4;
        int[] iArr2 = new int[length2];
        for (int length3 = bArr.length - 1; length3 >= 0; length3--) {
            int i2 = length3 / 4;
            iArr2[i2] = (iArr2[i2] << 8) + (bArr[length3] & 255);
        }
        int[] iArr3 = new int[44];
        this.f4515a = iArr3;
        iArr3[0] = -1209970333;
        int i3 = 1;
        while (true) {
            iArr = this.f4515a;
            if (i3 >= iArr.length) {
                break;
            }
            iArr[i3] = iArr[i3 - 1] - 1640531527;
            i3++;
        }
        int length4 = length2 > iArr.length ? length2 * 3 : iArr.length * 3;
        int length5 = 0;
        int iF = 0;
        int iF2 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length4; i5++) {
            int[] iArr4 = this.f4515a;
            iF = f(iArr4[length5] + iF + iF2, 3);
            iArr4[length5] = iF;
            iF2 = f(iArr2[i4] + iF + iF2, iF2 + iF);
            iArr2[i4] = iF2;
            length5 = (length5 + 1) % this.f4515a.length;
            i4 = (i4 + 1) % length2;
        }
    }

    public final void i(int i2, byte[] bArr, int i3) {
        for (int i4 = 0; i4 < 4; i4++) {
            bArr[i4 + i3] = (byte) i2;
            i2 >>>= 8;
        }
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4516b = z;
            h(((g.a.b.j0.s0) iVar).a());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to RC6 init - " + iVar.getClass().getName());
        }
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
