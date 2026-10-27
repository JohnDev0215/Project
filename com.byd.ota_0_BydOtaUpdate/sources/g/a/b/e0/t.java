package g.a.b.e0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;

/* JADX INFO: loaded from: classes.dex */
public class t extends g.a.b.u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.n f4725d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4726e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4727f;

    public t(g.a.b.n nVar) {
        this.f4725d = nVar;
        if (nVar instanceof g.a.b.p) {
            this.f4726e = nVar.getDigestSize();
            this.f4727f = ((g.a.b.p) nVar).getByteLength();
        } else {
            throw new IllegalArgumentException("Digest " + nVar.getAlgorithmName() + " unsupported");
        }
    }

    @Override // g.a.b.u
    public g.a.b.i d(int i2) {
        int i3 = i2 / 8;
        return new s0(i(3, i3), 0, i3);
    }

    @Override // g.a.b.u
    public g.a.b.i e(int i2) {
        int i3 = i2 / 8;
        return new s0(i(1, i3), 0, i3);
    }

    @Override // g.a.b.u
    public g.a.b.i f(int i2, int i3) {
        int i4 = i2 / 8;
        int i5 = i3 / 8;
        byte[] bArrI = i(1, i4);
        return new w0(new s0(bArrI, 0, i4), i(2, i5), 0, i5);
    }

    public final void h(byte[] bArr, int i2, byte[] bArr2) {
        int i3 = (bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + i2) - 1] & 255) + 1;
        bArr[(bArr2.length + i2) - 1] = (byte) i3;
        int i4 = i3 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int i5 = i2 + length;
            int i6 = i4 + (bArr2[length] & 255) + (bArr[i5] & 255);
            bArr[i5] = (byte) i6;
            i4 = i6 >>> 8;
        }
    }

    public final byte[] i(int i2, int i3) {
        byte[] bArr;
        byte[] bArr2;
        int i4;
        int i5 = this.f4727f;
        byte[] bArr3 = new byte[i5];
        byte[] bArr4 = new byte[i3];
        int i6 = 0;
        for (int i7 = 0; i7 != i5; i7++) {
            bArr3[i7] = (byte) i2;
        }
        byte[] bArr5 = this.f5196b;
        int i8 = 1;
        if (bArr5 == null || bArr5.length == 0) {
            bArr = new byte[0];
        } else {
            int i9 = this.f4727f;
            int length = i9 * (((bArr5.length + i9) - 1) / i9);
            bArr = new byte[length];
            for (int i10 = 0; i10 != length; i10++) {
                byte[] bArr6 = this.f5196b;
                bArr[i10] = bArr6[i10 % bArr6.length];
            }
        }
        byte[] bArr7 = this.f5195a;
        if (bArr7 == null || bArr7.length == 0) {
            bArr2 = new byte[0];
        } else {
            int i11 = this.f4727f;
            int length2 = i11 * (((bArr7.length + i11) - 1) / i11);
            bArr2 = new byte[length2];
            for (int i12 = 0; i12 != length2; i12++) {
                byte[] bArr8 = this.f5195a;
                bArr2[i12] = bArr8[i12 % bArr8.length];
            }
        }
        int length3 = bArr.length + bArr2.length;
        byte[] bArr9 = new byte[length3];
        System.arraycopy(bArr, 0, bArr9, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr9, bArr.length, bArr2.length);
        int i13 = this.f4727f;
        byte[] bArr10 = new byte[i13];
        int i14 = this.f4726e;
        int i15 = ((i3 + i14) - 1) / i14;
        byte[] bArr11 = new byte[i14];
        int i16 = 1;
        while (i16 <= i15) {
            this.f4725d.update(bArr3, i6, i5);
            this.f4725d.update(bArr9, i6, length3);
            this.f4725d.doFinal(bArr11, i6);
            for (int i17 = i8; i17 < this.f5197c; i17++) {
                this.f4725d.update(bArr11, i6, i14);
                this.f4725d.doFinal(bArr11, i6);
            }
            for (int i18 = i6; i18 != i13; i18++) {
                bArr10[i18] = bArr11[i18 % i14];
            }
            int i19 = i6;
            while (true) {
                int i20 = this.f4727f;
                if (i19 == length3 / i20) {
                    break;
                }
                h(bArr9, i20 * i19, bArr10);
                i19++;
            }
            if (i16 == i15) {
                int i21 = i16 - 1;
                int i22 = this.f4726e;
                int i23 = i21 * i22;
                int i24 = i3 - (i21 * i22);
                i4 = 0;
                System.arraycopy(bArr11, 0, bArr4, i23, i24);
            } else {
                i4 = 0;
                System.arraycopy(bArr11, 0, bArr4, (i16 - 1) * this.f4726e, i14);
            }
            i16++;
            i6 = i4;
            i8 = 1;
        }
        return bArr4;
    }
}
