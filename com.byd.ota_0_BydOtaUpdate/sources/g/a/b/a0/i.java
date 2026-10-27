package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class i implements g.a.b.p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long[] f4294a = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f4295b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f4296c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4297d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4298e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4299f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4300g;

    public i() {
        this(288);
    }

    public i(int i2) {
        this.f4295b = new long[25];
        this.f4296c = new byte[192];
        g(i2);
    }

    public i(i iVar) {
        long[] jArr = new long[25];
        this.f4295b = jArr;
        this.f4296c = new byte[192];
        long[] jArr2 = iVar.f4295b;
        System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
        byte[] bArr = iVar.f4296c;
        System.arraycopy(bArr, 0, this.f4296c, 0, bArr.length);
        this.f4297d = iVar.f4297d;
        this.f4298e = iVar.f4298e;
        this.f4299f = iVar.f4299f;
        this.f4300g = iVar.f4300g;
    }

    public final void a(byte[] bArr, int i2) {
        int i3 = this.f4297d >> 6;
        for (int i4 = 0; i4 < i3; i4++) {
            long[] jArr = this.f4295b;
            jArr[i4] = jArr[i4] ^ g.a.f.f.n(bArr, i2);
            i2 += 8;
        }
        d();
    }

    public final void b() {
        g.a.f.f.u(this.f4295b, 0, this.f4297d >> 6, this.f4296c, 0);
    }

    public final void d() {
        long[] jArr = this.f4295b;
        int i2 = 0;
        long j = jArr[0];
        char c2 = 1;
        long j2 = jArr[1];
        long j3 = jArr[2];
        char c3 = 3;
        long j4 = jArr[3];
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[8];
        long j10 = jArr[9];
        long j11 = jArr[10];
        long j12 = jArr[11];
        long j13 = jArr[12];
        long j14 = jArr[13];
        long j15 = jArr[14];
        long j16 = jArr[15];
        long j17 = jArr[16];
        long j18 = jArr[17];
        long j19 = jArr[18];
        long j20 = jArr[19];
        long j21 = jArr[20];
        long j22 = jArr[21];
        long j23 = jArr[22];
        long j24 = jArr[23];
        int i3 = 24;
        long j25 = jArr[24];
        while (i2 < i3) {
            long j26 = (((j ^ j6) ^ j11) ^ j16) ^ j21;
            long j27 = (((j2 ^ j7) ^ j12) ^ j17) ^ j22;
            long j28 = (((j3 ^ j8) ^ j13) ^ j18) ^ j23;
            long j29 = (((j4 ^ j9) ^ j14) ^ j19) ^ j24;
            long j30 = (((j5 ^ j10) ^ j15) ^ j20) ^ j25;
            long j31 = ((j27 << c2) | (j27 >>> (-1))) ^ j30;
            long j32 = ((j28 << c2) | (j28 >>> (-1))) ^ j26;
            long j33 = ((j29 << c2) | (j29 >>> (-1))) ^ j27;
            long j34 = ((j30 << c2) | (j30 >>> (-1))) ^ j28;
            long j35 = ((j26 << c2) | (j26 >>> (-1))) ^ j29;
            long j36 = j ^ j31;
            long j37 = j6 ^ j31;
            long j38 = j11 ^ j31;
            long j39 = j16 ^ j31;
            long j40 = j21 ^ j31;
            long j41 = j2 ^ j32;
            long j42 = j7 ^ j32;
            long j43 = j12 ^ j32;
            long j44 = j17 ^ j32;
            long j45 = j22 ^ j32;
            long j46 = j3 ^ j33;
            long j47 = j8 ^ j33;
            long j48 = j13 ^ j33;
            long j49 = j18 ^ j33;
            long j50 = j23 ^ j33;
            long j51 = j4 ^ j34;
            long j52 = j9 ^ j34;
            long j53 = j14 ^ j34;
            long j54 = j19 ^ j34;
            long j55 = j24 ^ j34;
            long j56 = j5 ^ j35;
            long j57 = j10 ^ j35;
            long j58 = j15 ^ j35;
            long j59 = j20 ^ j35;
            long j60 = j25 ^ j35;
            long j61 = (j41 << c2) | (j41 >>> 63);
            long j62 = (j42 << 44) | (j42 >>> 20);
            long j63 = (j57 << 20) | (j57 >>> 44);
            long j64 = (j50 << 61) | (j50 >>> c3);
            long j65 = (j58 << 39) | (j58 >>> 25);
            long j66 = (j40 << 18) | (j40 >>> 46);
            long j67 = (j46 << 62) | (j46 >>> 2);
            long j68 = (j48 << 43) | (j48 >>> 21);
            long j69 = (j53 << 25) | (j53 >>> 39);
            long j70 = (j59 << 8) | (j59 >>> 56);
            long j71 = (j55 << 56) | (j55 >>> 8);
            long j72 = (j39 << 41) | (j39 >>> 23);
            long j73 = (j56 << 27) | (j56 >>> 37);
            long j74 = (j60 << 14) | (j60 >>> 50);
            long j75 = (j45 << 2) | (j45 >>> 62);
            long j76 = (j52 << 55) | (j52 >>> 9);
            long j77 = (j44 << 45) | (j44 >>> 19);
            long j78 = (j37 << 36) | (j37 >>> 28);
            long j79 = (j51 << 28) | (j51 >>> 36);
            long j80 = (j54 << 21) | (j54 >>> 43);
            long j81 = (j49 << 15) | (j49 >>> 49);
            long j82 = (j43 << 10) | (j43 >>> 54);
            long j83 = (j47 << 6) | (j47 >>> 58);
            long j84 = (j38 << 3) | (j38 >>> 61);
            long j85 = ((~j62) & j68) ^ j36;
            long j86 = ((~j68) & j80) ^ j62;
            j3 = j68 ^ ((~j80) & j74);
            j4 = j80 ^ ((~j74) & j36);
            long j87 = j74 ^ ((~j36) & j62);
            long j88 = j79 ^ ((~j63) & j84);
            long j89 = ((~j84) & j77) ^ j63;
            long j90 = ((~j77) & j64) ^ j84;
            long j91 = j77 ^ ((~j64) & j79);
            long j92 = ((~j79) & j63) ^ j64;
            j11 = j61 ^ ((~j83) & j69);
            long j93 = ((~j69) & j70) ^ j83;
            long j94 = ((~j70) & j66) ^ j69;
            long j95 = j70 ^ ((~j66) & j61);
            long j96 = ((~j61) & j83) ^ j66;
            long j97 = j73 ^ ((~j78) & j82);
            long j98 = ((~j82) & j81) ^ j78;
            long j99 = j82 ^ ((~j81) & j71);
            long j100 = ((~j71) & j73) ^ j81;
            long j101 = ((~j73) & j78) ^ j71;
            long j102 = j67 ^ ((~j76) & j65);
            long j103 = ((~j65) & j72) ^ j76;
            j21 = j102;
            long j104 = j65 ^ ((~j72) & j75);
            long j105 = ((~j75) & j67) ^ j72;
            long j106 = ((~j67) & j76) ^ j75;
            long j107 = j85 ^ f4294a[i2];
            i2++;
            j7 = j89;
            j13 = j94;
            j12 = j93;
            j14 = j95;
            j22 = j103;
            c3 = 3;
            j24 = j105;
            j23 = j104;
            j10 = j92;
            jArr = jArr;
            j20 = j101;
            j15 = j96;
            j8 = j90;
            j9 = j91;
            j18 = j99;
            j16 = j97;
            j5 = j87;
            j6 = j88;
            i3 = 24;
            j19 = j100;
            j17 = j98;
            c2 = 1;
            j2 = j86;
            j25 = j106;
            j = j107;
        }
        long[] jArr2 = jArr;
        jArr2[0] = j;
        jArr2[1] = j2;
        jArr2[2] = j3;
        jArr2[3] = j4;
        jArr2[4] = j5;
        jArr2[5] = j6;
        jArr2[6] = j7;
        jArr2[7] = j8;
        jArr2[8] = j9;
        jArr2[9] = j10;
        jArr2[10] = j11;
        jArr2[11] = j12;
        jArr2[12] = j13;
        jArr2[13] = j14;
        jArr2[14] = j15;
        jArr2[15] = j16;
        jArr2[16] = j17;
        jArr2[17] = j18;
        jArr2[18] = j19;
        jArr2[19] = j20;
        jArr2[20] = j21;
        jArr2[21] = j22;
        jArr2[22] = j23;
        jArr2[23] = j24;
        jArr2[24] = j25;
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        j(bArr, i2, this.f4299f);
        reset();
        return getDigestSize();
    }

    public void e(byte[] bArr, int i2, int i3) {
        int i4;
        int i5 = this.f4298e;
        if (i5 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.f4300g) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        int i6 = i5 >> 3;
        int i7 = this.f4297d >> 3;
        int i8 = 0;
        while (i8 < i3) {
            if (i6 != 0 || i8 > (i4 = i3 - i7)) {
                int iMin = Math.min(i7 - i6, i3 - i8);
                System.arraycopy(bArr, i2 + i8, this.f4296c, i6, iMin);
                i6 += iMin;
                i8 += iMin;
                if (i6 == i7) {
                    a(this.f4296c, 0);
                    i6 = 0;
                }
            } else {
                do {
                    a(bArr, i2 + i8);
                    i8 += i7;
                } while (i8 <= i4);
            }
        }
        this.f4298e = i6 << 3;
    }

    public void f(int i2, int i3) {
        if (i3 < 1 || i3 > 7) {
            throw new IllegalArgumentException("'bits' must be in the range 1 to 7");
        }
        int i4 = this.f4298e;
        if (i4 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.f4300g) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        this.f4296c[i4 >> 3] = (byte) (i2 & ((1 << i3) - 1));
        this.f4298e = i4 + i3;
    }

    public final void g(int i2) {
        if (i2 != 128 && i2 != 224 && i2 != 256 && i2 != 288 && i2 != 384 && i2 != 512) {
            throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
        }
        h(1600 - (i2 << 1));
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "Keccak-" + this.f4299f;
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return this.f4297d / 8;
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return this.f4299f / 8;
    }

    public final void h(int i2) {
        if (i2 <= 0 || i2 >= 1600 || i2 % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.f4297d = i2;
        int i3 = 0;
        while (true) {
            long[] jArr = this.f4295b;
            if (i3 >= jArr.length) {
                g.a.f.a.u(this.f4296c, (byte) 0);
                this.f4298e = 0;
                this.f4300g = false;
                this.f4299f = (1600 - i2) / 2;
                return;
            }
            jArr[i3] = 0;
            i3++;
        }
    }

    public final void i() {
        byte[] bArr = this.f4296c;
        int i2 = this.f4298e;
        int i3 = i2 >> 3;
        bArr[i3] = (byte) (bArr[i3] | ((byte) (1 << (i2 & 7))));
        int i4 = i2 + 1;
        this.f4298e = i4;
        if (i4 == this.f4297d) {
            a(bArr, 0);
            this.f4298e = 0;
        }
        int i5 = this.f4298e;
        int i6 = i5 >> 6;
        int i7 = i5 & 63;
        int i8 = 0;
        for (int i9 = 0; i9 < i6; i9++) {
            long[] jArr = this.f4295b;
            jArr[i9] = jArr[i9] ^ g.a.f.f.n(this.f4296c, i8);
            i8 += 8;
        }
        if (i7 > 0) {
            long j = (1 << i7) - 1;
            long[] jArr2 = this.f4295b;
            jArr2[i6] = jArr2[i6] ^ (g.a.f.f.n(this.f4296c, i8) & j);
        }
        long[] jArr3 = this.f4295b;
        int i10 = (this.f4297d - 1) >> 6;
        jArr3[i10] = jArr3[i10] ^ Long.MIN_VALUE;
        d();
        b();
        this.f4298e = this.f4297d;
        this.f4300g = true;
    }

    public void j(byte[] bArr, int i2, long j) {
        if (!this.f4300g) {
            i();
        }
        long j2 = 0;
        if (j % 8 != 0) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        while (j2 < j) {
            if (this.f4298e == 0) {
                d();
                b();
                this.f4298e = this.f4297d;
            }
            int iMin = (int) Math.min(this.f4298e, j - j2);
            System.arraycopy(this.f4296c, (this.f4297d - this.f4298e) / 8, bArr, ((int) (j2 / 8)) + i2, iMin / 8);
            this.f4298e -= iMin;
            j2 += (long) iMin;
        }
    }

    @Override // g.a.b.n
    public void reset() {
        g(this.f4299f);
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        e(new byte[]{b2}, 0, 1);
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        e(bArr, i2, i3);
    }
}
