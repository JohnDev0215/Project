package g.a.b.d0;

/* JADX INFO: loaded from: classes.dex */
public class m extends t0 {
    public static void k(int i2, int[] iArr, int[] iArr2) {
        int i3 = 16;
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c2 = 0;
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = iArr[4];
        int i9 = iArr[5];
        int i10 = iArr[6];
        int i11 = 7;
        int i12 = iArr[7];
        int i13 = 8;
        int i14 = iArr[8];
        int i15 = iArr[9];
        int i16 = iArr[10];
        int i17 = iArr[11];
        int i18 = 12;
        int i19 = iArr[12];
        int i20 = iArr[13];
        int i21 = iArr[14];
        int iH = iArr[15];
        int iH2 = i21;
        int iH3 = i20;
        int iH4 = i19;
        int i22 = i17;
        int i23 = i16;
        int i24 = i15;
        int i25 = i14;
        int iH5 = i12;
        int iH6 = i10;
        int iH7 = i9;
        int iH8 = i8;
        int i26 = i7;
        int i27 = i6;
        int i28 = i5;
        int i29 = i4;
        int i30 = i2;
        while (i30 > 0) {
            int i31 = i29 + iH8;
            int iH9 = t0.h(iH4 ^ i31, i3);
            int i32 = i25 + iH9;
            int iH10 = t0.h(iH8 ^ i32, i18);
            int i33 = i31 + iH10;
            int iH11 = t0.h(iH9 ^ i33, i13);
            int i34 = i32 + iH11;
            int iH12 = t0.h(iH10 ^ i34, i11);
            int i35 = i28 + iH7;
            int iH13 = t0.h(iH3 ^ i35, i3);
            int i36 = i24 + iH13;
            int iH14 = t0.h(iH7 ^ i36, i18);
            int i37 = i35 + iH14;
            int iH15 = t0.h(iH13 ^ i37, i13);
            int i38 = i36 + iH15;
            int iH16 = t0.h(iH14 ^ i38, i11);
            int i39 = i27 + iH6;
            int iH17 = t0.h(iH2 ^ i39, i3);
            int i40 = i23 + iH17;
            int iH18 = t0.h(iH6 ^ i40, i18);
            int i41 = i39 + iH18;
            int iH19 = t0.h(iH17 ^ i41, i13);
            int i42 = i40 + iH19;
            int iH20 = t0.h(iH18 ^ i42, i11);
            int i43 = i26 + iH5;
            int iH21 = t0.h(iH ^ i43, 16);
            int i44 = i22 + iH21;
            int iH22 = t0.h(iH5 ^ i44, i18);
            int i45 = i43 + iH22;
            int iH23 = t0.h(iH21 ^ i45, 8);
            int i46 = i44 + iH23;
            int iH24 = t0.h(iH22 ^ i46, 7);
            int i47 = i33 + iH16;
            int iH25 = t0.h(iH23 ^ i47, 16);
            int i48 = i42 + iH25;
            int iH26 = t0.h(iH16 ^ i48, 12);
            i29 = i47 + iH26;
            iH = t0.h(iH25 ^ i29, 8);
            i23 = i48 + iH;
            iH7 = t0.h(iH26 ^ i23, 7);
            int i49 = i37 + iH20;
            int iH27 = t0.h(iH11 ^ i49, 16);
            int i50 = i46 + iH27;
            int iH28 = t0.h(iH20 ^ i50, 12);
            i28 = i49 + iH28;
            iH4 = t0.h(iH27 ^ i28, 8);
            i22 = i50 + iH4;
            iH6 = t0.h(iH28 ^ i22, 7);
            int i51 = i41 + iH24;
            int iH29 = t0.h(iH15 ^ i51, 16);
            int i52 = i34 + iH29;
            int iH30 = t0.h(iH24 ^ i52, 12);
            i27 = i51 + iH30;
            iH3 = t0.h(iH29 ^ i27, 8);
            i25 = i52 + iH3;
            iH5 = t0.h(iH30 ^ i25, 7);
            int i53 = i45 + iH12;
            i3 = 16;
            int iH31 = t0.h(iH19 ^ i53, 16);
            int i54 = i38 + iH31;
            int iH32 = t0.h(iH12 ^ i54, 12);
            i26 = i53 + iH32;
            iH2 = t0.h(iH31 ^ i26, 8);
            i24 = i54 + iH2;
            iH8 = t0.h(iH32 ^ i24, 7);
            i30 -= 2;
            c2 = 0;
            i18 = 12;
            i13 = 8;
            i11 = 7;
        }
        iArr2[c2] = i29 + iArr[c2];
        iArr2[1] = i28 + iArr[1];
        iArr2[2] = i27 + iArr[2];
        iArr2[3] = i26 + iArr[3];
        iArr2[4] = iH8 + iArr[4];
        iArr2[5] = iH7 + iArr[5];
        iArr2[6] = iH6 + iArr[6];
        iArr2[7] = iH5 + iArr[7];
        iArr2[8] = i25 + iArr[8];
        iArr2[9] = i24 + iArr[9];
        iArr2[10] = i23 + iArr[10];
        iArr2[11] = i22 + iArr[11];
        iArr2[12] = iH4 + iArr[12];
        iArr2[13] = iH3 + iArr[13];
        iArr2[14] = iH2 + iArr[14];
        iArr2[15] = iH + iArr[15];
    }

    @Override // g.a.b.d0.t0
    public void a() {
        int[] iArr = this.f4605f;
        int i2 = iArr[12] + 1;
        iArr[12] = i2;
        if (i2 == 0) {
            iArr[13] = iArr[13] + 1;
        }
    }

    @Override // g.a.b.d0.t0
    public void b(byte[] bArr) {
        k(this.f4603d, this.f4605f, this.f4606g);
        g.a.f.f.h(this.f4606g, bArr, 0);
    }

    @Override // g.a.b.d0.t0
    public void f() {
        int[] iArr = this.f4605f;
        iArr[13] = 0;
        iArr[12] = 0;
    }

    @Override // g.a.b.d0.t0, g.a.b.w
    public String getAlgorithmName() {
        return "ChaCha" + this.f4603d;
    }

    @Override // g.a.b.d0.t0
    public void j(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            e(bArr.length, this.f4605f, 0);
            g.a.f.f.l(bArr, 0, this.f4605f, 4, 4);
            g.a.f.f.l(bArr, bArr.length - 16, this.f4605f, 8, 4);
        }
        g.a.f.f.l(bArr2, 0, this.f4605f, 14, 2);
    }
}
