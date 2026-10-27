package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class b0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f4424a = null;

    @Override // g.a.b.e
    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = this.f4424a;
        if (iArr == null) {
            throw new IllegalStateException("IDEA engine not initialised");
        }
        if (i2 + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 8 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        g(iArr, bArr, i2, bArr2, i3);
        return 8;
    }

    @Override // g.a.b.e
    public int b() {
        return 8;
    }

    public int c(int i2) {
        return (0 - i2) & 65535;
    }

    public final int d(byte[] bArr, int i2) {
        return ((bArr[i2] << 8) & 65280) + (bArr[i2 + 1] & 255);
    }

    public final int[] e(byte[] bArr) {
        int i2;
        int[] iArr = new int[52];
        int i3 = 0;
        if (bArr.length < 16) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        while (true) {
            if (i3 >= 8) {
                break;
            }
            iArr[i3] = d(bArr, i3 * 2);
            i3++;
        }
        for (i2 = 8; i2 < 52; i2++) {
            int i4 = i2 & 7;
            if (i4 < 6) {
                iArr[i2] = (((iArr[i2 - 7] & 127) << 9) | (iArr[i2 - 6] >> 7)) & 65535;
            } else if (i4 == 6) {
                iArr[i2] = (((iArr[i2 - 7] & 127) << 9) | (iArr[i2 - 14] >> 7)) & 65535;
            } else {
                iArr[i2] = (((iArr[i2 - 15] & 127) << 9) | (iArr[i2 - 14] >> 7)) & 65535;
            }
        }
        return iArr;
    }

    public final int[] f(boolean z, byte[] bArr) {
        return z ? e(bArr) : h(e(bArr));
    }

    public final void g(int[] iArr, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iD = d(bArr, i2);
        int iD2 = d(bArr, i2 + 2);
        int iD3 = d(bArr, i2 + 4);
        int iD4 = d(bArr, i2 + 6);
        int i4 = 0;
        int i5 = iD3;
        int i6 = iD2;
        int i7 = iD;
        int i8 = 0;
        while (i4 < 8) {
            int i9 = i8 + 1;
            int i10 = i(i7, iArr[i8]);
            int i11 = i9 + 1;
            int i12 = (i6 + iArr[i9]) & 65535;
            int i13 = i11 + 1;
            int i14 = (i5 + iArr[i11]) & 65535;
            int i15 = i13 + 1;
            int i16 = i(iD4, iArr[i13]);
            int i17 = i15 + 1;
            int i18 = i(i14 ^ i10, iArr[i15]);
            int i19 = i(((i12 ^ i16) + i18) & 65535, iArr[i17]);
            int i20 = (i18 + i19) & 65535;
            iD4 = i16 ^ i20;
            i5 = i20 ^ i12;
            i4++;
            i6 = i14 ^ i19;
            i7 = i10 ^ i19;
            i8 = i17 + 1;
        }
        int i21 = i8 + 1;
        k(i(i7, iArr[i8]), bArr2, i3);
        int i22 = i21 + 1;
        k(i5 + iArr[i21], bArr2, i3 + 2);
        k(i6 + iArr[i22], bArr2, i3 + 4);
        k(i(iD4, iArr[i22 + 1]), bArr2, i3 + 6);
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "IDEA";
    }

    public final int[] h(int[] iArr) {
        int[] iArr2 = new int[52];
        int iJ = j(iArr[0]);
        int i2 = 1;
        int iC = c(iArr[1]);
        int iC2 = c(iArr[2]);
        iArr2[51] = j(iArr[3]);
        iArr2[50] = iC2;
        iArr2[49] = iC;
        int i3 = 48;
        iArr2[48] = iJ;
        int i4 = 4;
        while (i2 < 8) {
            int i5 = i4 + 1;
            int i6 = iArr[i4];
            int i7 = i5 + 1;
            int i8 = i3 - 1;
            iArr2[i8] = iArr[i5];
            int i9 = i8 - 1;
            iArr2[i9] = i6;
            int i10 = i7 + 1;
            int iJ2 = j(iArr[i7]);
            int i11 = i10 + 1;
            int iC3 = c(iArr[i10]);
            int i12 = i11 + 1;
            int iC4 = c(iArr[i11]);
            int i13 = i9 - 1;
            iArr2[i13] = j(iArr[i12]);
            int i14 = i13 - 1;
            iArr2[i14] = iC3;
            int i15 = i14 - 1;
            iArr2[i15] = iC4;
            i3 = i15 - 1;
            iArr2[i3] = iJ2;
            i2++;
            i4 = i12 + 1;
        }
        int i16 = i4 + 1;
        int i17 = iArr[i4];
        int i18 = i16 + 1;
        int i19 = i3 - 1;
        iArr2[i19] = iArr[i16];
        int i20 = i19 - 1;
        iArr2[i20] = i17;
        int i21 = i18 + 1;
        int iJ3 = j(iArr[i18]);
        int i22 = i21 + 1;
        int iC5 = c(iArr[i21]);
        int i23 = i22 + 1;
        int iC6 = c(iArr[i22]);
        int i24 = i20 - 1;
        iArr2[i24] = j(iArr[i23]);
        int i25 = i24 - 1;
        iArr2[i25] = iC6;
        int i26 = i25 - 1;
        iArr2[i26] = iC5;
        iArr2[i26 - 1] = iJ3;
        return iArr2;
    }

    public final int i(int i2, int i3) {
        int i4;
        if (i2 == 0) {
            i4 = 65537 - i3;
        } else if (i3 == 0) {
            i4 = 65537 - i2;
        } else {
            int i5 = i2 * i3;
            int i6 = i5 & 65535;
            int i7 = i5 >>> 16;
            i4 = (i6 - i7) + (i6 < i7 ? 1 : 0);
        }
        return i4 & 65535;
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4424a = f(z, ((g.a.b.j0.s0) iVar).a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + iVar.getClass().getName());
    }

    public final int j(int i2) {
        if (i2 < 2) {
            return i2;
        }
        int i3 = 65537 / i2;
        int i4 = 65537 % i2;
        int i5 = 1;
        while (i4 != 1) {
            int i6 = i2 / i4;
            i2 %= i4;
            i5 = (i5 + (i6 * i3)) & 65535;
            if (i2 == 1) {
                return i5;
            }
            int i7 = i4 / i2;
            i4 %= i2;
            i3 = (i3 + (i7 * i5)) & 65535;
        }
        return (1 - i3) & 65535;
    }

    public final void k(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >>> 8);
        bArr[i3 + 1] = (byte) i2;
    }

    @Override // g.a.b.e
    public void reset() {
    }
}
