package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class k implements g.a.b.p, g.a.f.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f4310a = {41, 46, 67, -55, -94, -40, 124, 1, 61, 54, 84, -95, -20, -16, 6, 19, 98, -89, 5, -13, -64, -57, 115, -116, -104, -109, 43, -39, -68, 76, -126, -54, 30, -101, 87, 60, -3, -44, -32, 22, 103, 66, 111, 24, -118, 23, -27, 18, -66, 78, -60, -42, -38, -98, -34, 73, -96, -5, -11, -114, -69, 47, -18, 122, -87, 104, 121, -111, 21, -78, 7, 63, -108, -62, 16, -119, 11, 34, 95, 33, -128, 127, 93, -102, 90, -112, 50, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, 25, 48, -77, 72, -91, -75, -47, -41, 94, -110, 42, -84, 86, -86, -58, 79, -72, 56, -46, -106, -92, 125, -74, 118, -4, 107, -30, -100, 116, 4, -15, 69, -99, 112, 89, 100, 113, -121, 32, -122, 91, -49, 101, -26, 45, -88, 2, 27, 96, 37, -83, -82, -80, -71, -10, 28, 70, 97, 105, 52, 64, 126, 15, 85, 71, -93, 35, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, 44, 83, 13, 110, -123, 40, -124, 9, -45, -33, -51, -12, 65, -127, 77, 82, 106, -36, 55, -56, 108, -63, -85, -6, 36, -31, 123, 8, 12, -67, -79, 74, 120, -120, -107, -117, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, 29, 57, -14, -17, -73, 14, 102, 88, -48, -28, -90, 119, 114, -8, -21, 117, 75, 10, 49, 68, 80, -76, -113, -19, 31, 26, -37, -103, -115, 51, -97, 17, -125, 20};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4312c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4313d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4314e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4315f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4316g;

    public k() {
        this.f4311b = new byte[48];
        this.f4313d = new byte[16];
        this.f4315f = new byte[16];
        reset();
    }

    public k(k kVar) {
        this.f4311b = new byte[48];
        this.f4313d = new byte[16];
        this.f4315f = new byte[16];
        d(kVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        d((k) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new k(this);
    }

    public final void d(k kVar) {
        byte[] bArr = kVar.f4311b;
        System.arraycopy(bArr, 0, this.f4311b, 0, bArr.length);
        this.f4312c = kVar.f4312c;
        byte[] bArr2 = kVar.f4313d;
        System.arraycopy(bArr2, 0, this.f4313d, 0, bArr2.length);
        this.f4314e = kVar.f4314e;
        byte[] bArr3 = kVar.f4315f;
        System.arraycopy(bArr3, 0, this.f4315f, 0, bArr3.length);
        this.f4316g = kVar.f4316g;
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        int length = this.f4313d.length;
        int i3 = this.f4314e;
        byte b2 = (byte) (length - i3);
        while (true) {
            byte[] bArr2 = this.f4313d;
            if (i3 >= bArr2.length) {
                f(bArr2);
                e(this.f4313d);
                e(this.f4315f);
                System.arraycopy(this.f4311b, this.f4312c, bArr, i2, 16);
                reset();
                return 16;
            }
            bArr2[i3] = b2;
            i3++;
        }
    }

    public void e(byte[] bArr) {
        for (int i2 = 0; i2 < 16; i2++) {
            byte[] bArr2 = this.f4311b;
            bArr2[i2 + 16] = bArr[i2];
            bArr2[i2 + 32] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 18; i4++) {
            for (int i5 = 0; i5 < 48; i5++) {
                byte[] bArr3 = this.f4311b;
                byte b2 = (byte) (f4310a[i3] ^ bArr3[i5]);
                bArr3[i5] = b2;
                i3 = b2 & 255;
            }
            i3 = (i3 + i4) % 256;
        }
    }

    public void f(byte[] bArr) {
        byte b2 = this.f4315f[15];
        for (int i2 = 0; i2 < 16; i2++) {
            byte[] bArr2 = this.f4315f;
            bArr2[i2] = (byte) (f4310a[(b2 ^ bArr[i2]) & 255] ^ bArr2[i2]);
            b2 = bArr2[i2];
        }
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "MD2";
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return 16;
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 16;
    }

    @Override // g.a.b.n
    public void reset() {
        this.f4312c = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4311b;
            if (i2 == bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        this.f4314e = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr2 = this.f4313d;
            if (i3 == bArr2.length) {
                break;
            }
            bArr2[i3] = 0;
            i3++;
        }
        this.f4316g = 0;
        int i4 = 0;
        while (true) {
            byte[] bArr3 = this.f4315f;
            if (i4 == bArr3.length) {
                return;
            }
            bArr3[i4] = 0;
            i4++;
        }
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        byte[] bArr = this.f4313d;
        int i2 = this.f4314e;
        int i3 = i2 + 1;
        this.f4314e = i3;
        bArr[i2] = b2;
        if (i3 == 16) {
            f(bArr);
            e(this.f4313d);
            this.f4314e = 0;
        }
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        while (this.f4314e != 0 && i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
        while (i3 > 16) {
            System.arraycopy(bArr, i2, this.f4313d, 0, 16);
            f(this.f4313d);
            e(this.f4313d);
            i3 -= 16;
            i2 += 16;
        }
        while (i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
    }
}
