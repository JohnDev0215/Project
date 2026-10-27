package g.a.b.d0;

/* JADX INFO: loaded from: classes.dex */
public final class i extends h {
    public int[] n = new int[48];
    public int[] o = new int[48];
    public int[] p = new int[192];
    public int[] q = new int[192];
    public int[] r = new int[8];

    @Override // g.a.b.d0.h, g.a.b.e
    public int b() {
        return 16;
    }

    @Override // g.a.b.d0.h, g.a.b.e
    public String getAlgorithmName() {
        return "CAST6";
    }

    @Override // g.a.b.d0.h
    public int l(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = new int[4];
        o(e(bArr, i2), e(bArr, i2 + 4), e(bArr, i2 + 8), e(bArr, i2 + 12), iArr);
        c(iArr[0], bArr2, i3);
        c(iArr[1], bArr2, i3 + 4);
        c(iArr[2], bArr2, i3 + 8);
        c(iArr[3], bArr2, i3 + 12);
        return 16;
    }

    @Override // g.a.b.d0.h
    public int m(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[] iArr = new int[4];
        p(e(bArr, i2), e(bArr, i2 + 4), e(bArr, i2 + 8), e(bArr, i2 + 12), iArr);
        c(iArr[0], bArr2, i3);
        c(iArr[1], bArr2, i3 + 4);
        c(iArr[2], bArr2, i3 + 8);
        c(iArr[3], bArr2, i3 + 12);
        return 16;
    }

    @Override // g.a.b.d0.h
    public void n(byte[] bArr) {
        int i2 = 1518500249;
        int i3 = 19;
        for (int i4 = 0; i4 < 24; i4++) {
            for (int i5 = 0; i5 < 8; i5++) {
                int i6 = (i4 * 8) + i5;
                this.q[i6] = i2;
                i2 += 1859775393;
                this.p[i6] = i3;
                i3 = (i3 + 17) & 31;
            }
        }
        byte[] bArr2 = new byte[64];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int i7 = 0; i7 < 8; i7++) {
            this.r[i7] = e(bArr2, i7 * 4);
        }
        for (int i8 = 0; i8 < 12; i8++) {
            int i9 = i8 * 2;
            int i10 = i9 * 8;
            int[] iArr = this.r;
            iArr[6] = iArr[6] ^ h(iArr[7], this.q[i10], this.p[i10]);
            int[] iArr2 = this.r;
            int i11 = i10 + 1;
            iArr2[5] = iArr2[5] ^ i(iArr2[6], this.q[i11], this.p[i11]);
            int[] iArr3 = this.r;
            int i12 = i10 + 2;
            iArr3[4] = iArr3[4] ^ j(iArr3[5], this.q[i12], this.p[i12]);
            int[] iArr4 = this.r;
            int i13 = i10 + 3;
            iArr4[3] = h(iArr4[4], this.q[i13], this.p[i13]) ^ iArr4[3];
            int[] iArr5 = this.r;
            int i14 = i10 + 4;
            iArr5[2] = i(iArr5[3], this.q[i14], this.p[i14]) ^ iArr5[2];
            int[] iArr6 = this.r;
            int i15 = i10 + 5;
            iArr6[1] = j(iArr6[2], this.q[i15], this.p[i15]) ^ iArr6[1];
            int[] iArr7 = this.r;
            int i16 = i10 + 6;
            iArr7[0] = iArr7[0] ^ h(iArr7[1], this.q[i16], this.p[i16]);
            int[] iArr8 = this.r;
            int i17 = i10 + 7;
            iArr8[7] = i(iArr8[0], this.q[i17], this.p[i17]) ^ iArr8[7];
            int i18 = (i9 + 1) * 8;
            int[] iArr9 = this.r;
            iArr9[6] = iArr9[6] ^ h(iArr9[7], this.q[i18], this.p[i18]);
            int[] iArr10 = this.r;
            int i19 = i18 + 1;
            iArr10[5] = iArr10[5] ^ i(iArr10[6], this.q[i19], this.p[i19]);
            int[] iArr11 = this.r;
            int i20 = i18 + 2;
            iArr11[4] = iArr11[4] ^ j(iArr11[5], this.q[i20], this.p[i20]);
            int[] iArr12 = this.r;
            int i21 = i18 + 3;
            iArr12[3] = h(iArr12[4], this.q[i21], this.p[i21]) ^ iArr12[3];
            int[] iArr13 = this.r;
            int i22 = i18 + 4;
            iArr13[2] = i(iArr13[3], this.q[i22], this.p[i22]) ^ iArr13[2];
            int[] iArr14 = this.r;
            int i23 = i18 + 5;
            iArr14[1] = j(iArr14[2], this.q[i23], this.p[i23]) ^ iArr14[1];
            int[] iArr15 = this.r;
            int i24 = i18 + 6;
            iArr15[0] = iArr15[0] ^ h(iArr15[1], this.q[i24], this.p[i24]);
            int[] iArr16 = this.r;
            int i25 = i18 + 7;
            iArr16[7] = i(iArr16[0], this.q[i25], this.p[i25]) ^ iArr16[7];
            int[] iArr17 = this.n;
            int i26 = i8 * 4;
            int[] iArr18 = this.r;
            iArr17[i26] = iArr18[0] & 31;
            int i27 = i26 + 1;
            iArr17[i27] = iArr18[2] & 31;
            int i28 = i26 + 2;
            iArr17[i28] = iArr18[4] & 31;
            int i29 = i26 + 3;
            iArr17[i29] = iArr18[6] & 31;
            int[] iArr19 = this.o;
            iArr19[i26] = iArr18[7];
            iArr19[i27] = iArr18[5];
            iArr19[i28] = iArr18[3];
            iArr19[i29] = iArr18[1];
        }
    }

    public final void o(int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        int i7 = 0;
        while (true) {
            if (i7 >= 6) {
                break;
            }
            int i8 = (11 - i7) * 4;
            i4 ^= h(i5, this.o[i8], this.n[i8]);
            int i9 = i8 + 1;
            i3 ^= i(i4, this.o[i9], this.n[i9]);
            int i10 = i8 + 2;
            i2 ^= j(i3, this.o[i10], this.n[i10]);
            int i11 = i8 + 3;
            i5 ^= h(i2, this.o[i11], this.n[i11]);
            i7++;
        }
        for (i6 = 6; i6 < 12; i6++) {
            int i12 = (11 - i6) * 4;
            int i13 = i12 + 3;
            i5 ^= h(i2, this.o[i13], this.n[i13]);
            int i14 = i12 + 2;
            i2 ^= j(i3, this.o[i14], this.n[i14]);
            int i15 = i12 + 1;
            i3 ^= i(i4, this.o[i15], this.n[i15]);
            i4 ^= h(i5, this.o[i12], this.n[i12]);
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = i4;
        iArr[3] = i5;
    }

    public final void p(int i2, int i3, int i4, int i5, int[] iArr) {
        int i6;
        int i7 = 0;
        while (true) {
            if (i7 >= 6) {
                break;
            }
            int i8 = i7 * 4;
            i4 ^= h(i5, this.o[i8], this.n[i8]);
            int i9 = i8 + 1;
            i3 ^= i(i4, this.o[i9], this.n[i9]);
            int i10 = i8 + 2;
            i2 ^= j(i3, this.o[i10], this.n[i10]);
            int i11 = i8 + 3;
            i5 ^= h(i2, this.o[i11], this.n[i11]);
            i7++;
        }
        for (i6 = 6; i6 < 12; i6++) {
            int i12 = i6 * 4;
            int i13 = i12 + 3;
            i5 ^= h(i2, this.o[i13], this.n[i13]);
            int i14 = i12 + 2;
            i2 ^= j(i3, this.o[i14], this.n[i14]);
            int i15 = i12 + 1;
            i3 ^= i(i4, this.o[i15], this.n[i15]);
            i4 ^= h(i5, this.o[i12], this.n[i12]);
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = i4;
        iArr[3] = i5;
    }

    @Override // g.a.b.d0.h, g.a.b.e
    public void reset() {
    }
}
