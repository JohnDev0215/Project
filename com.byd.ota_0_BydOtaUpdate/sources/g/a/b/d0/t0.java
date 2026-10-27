package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.MaxBytesExceededException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class t0 implements g.a.b.w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4600a = g.a.f.f.m(g.a.f.i.e("expand 16-byte kexpand 32-byte k"), 0, 8);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f4601b = g.a.f.i.e("expand 32-byte k");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f4602c = g.a.f.i.e("expand 16-byte k");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4603d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4604e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f4605f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f4606g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4607h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4608i;
    public int j;
    public int k;
    public int l;

    public t0() {
        this(20);
    }

    public t0(int i2) {
        this.f4604e = 0;
        this.f4605f = new int[16];
        this.f4606g = new int[16];
        this.f4607h = new byte[64];
        this.f4608i = false;
        if (i2 <= 0 || (i2 & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.f4603d = i2;
    }

    public static int h(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public static void i(int i2, int[] iArr, int[] iArr2) {
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        boolean z = false;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = iArr[4];
        int i8 = iArr[5];
        int i9 = iArr[6];
        int i10 = 7;
        int i11 = iArr[7];
        int i12 = iArr[8];
        int i13 = 9;
        int i14 = iArr[9];
        int i15 = iArr[10];
        int i16 = iArr[11];
        int i17 = iArr[12];
        int i18 = 13;
        int i19 = iArr[13];
        int i20 = iArr[14];
        int iH = iArr[15];
        int iH2 = i20;
        int iH3 = i19;
        int iH4 = i17;
        int iH5 = i16;
        int iH6 = i15;
        int iH7 = i14;
        int i21 = i12;
        int iH8 = i11;
        int iH9 = i9;
        int iH10 = i8;
        int i22 = i7;
        int i23 = i6;
        int iH11 = i5;
        int iH12 = i4;
        int i24 = i3;
        int i25 = i2;
        while (i25 > 0) {
            int iH13 = h(i24 + iH4, i10) ^ i22;
            int iH14 = i21 ^ h(iH13 + i24, i13);
            int iH15 = iH4 ^ h(iH14 + iH13, i18);
            int iH16 = h(iH15 + iH14, 18) ^ i24;
            int iH17 = iH7 ^ h(iH10 + iH12, i10);
            int iH18 = iH3 ^ h(iH17 + iH10, i13);
            int iH19 = iH12 ^ h(iH18 + iH17, i18);
            int iH20 = h(iH19 + iH18, 18) ^ iH10;
            int iH21 = iH2 ^ h(iH6 + iH9, 7);
            int iH22 = iH11 ^ h(iH21 + iH6, 9);
            int iH23 = iH9 ^ h(iH22 + iH21, 13);
            int iH24 = iH6 ^ h(iH23 + iH22, 18);
            int iH25 = i23 ^ h(iH + iH5, 7);
            int iH26 = iH8 ^ h(iH25 + iH, 9);
            int i26 = i25;
            int iH27 = iH5 ^ h(iH26 + iH25, 13);
            int iH28 = iH ^ h(iH27 + iH26, 18);
            iH12 = iH19 ^ h(iH16 + iH25, 7);
            iH11 = iH22 ^ h(iH12 + iH16, 9);
            int iH29 = iH25 ^ h(iH11 + iH12, 13);
            int iH30 = iH16 ^ h(iH29 + iH11, 18);
            iH9 = iH23 ^ h(iH20 + iH13, 7);
            iH8 = iH26 ^ h(iH9 + iH20, 9);
            int iH31 = h(iH8 + iH9, 13) ^ iH13;
            iH10 = iH20 ^ h(iH31 + iH8, 18);
            iH5 = iH27 ^ h(iH24 + iH17, 7);
            int iH32 = h(iH5 + iH24, 9) ^ iH14;
            iH7 = iH17 ^ h(iH32 + iH5, 13);
            iH6 = iH24 ^ h(iH7 + iH32, 18);
            iH4 = iH15 ^ h(iH28 + iH21, 7);
            iH3 = iH18 ^ h(iH4 + iH28, 9);
            iH2 = iH21 ^ h(iH3 + iH4, 13);
            iH = iH28 ^ h(iH2 + iH3, 18);
            i23 = iH29;
            i21 = iH32;
            i24 = iH30;
            i22 = iH31;
            z = false;
            i18 = 13;
            i13 = 9;
            i10 = 7;
            i25 = i26 - 2;
        }
        boolean z2 = z;
        iArr2[z2 ? 1 : 0] = i24 + iArr[z2 ? 1 : 0];
        iArr2[1] = iH12 + iArr[1];
        iArr2[2] = iH11 + iArr[2];
        iArr2[3] = i23 + iArr[3];
        iArr2[4] = i22 + iArr[4];
        iArr2[5] = iH10 + iArr[5];
        iArr2[6] = iH9 + iArr[6];
        iArr2[7] = iH8 + iArr[7];
        iArr2[8] = i21 + iArr[8];
        iArr2[9] = iH7 + iArr[9];
        iArr2[10] = iH6 + iArr[10];
        iArr2[11] = iH5 + iArr[11];
        iArr2[12] = iH4 + iArr[12];
        iArr2[13] = iH3 + iArr[13];
        iArr2[14] = iH2 + iArr[14];
        iArr2[15] = iH + iArr[15];
    }

    public void a() {
        int[] iArr = this.f4605f;
        int i2 = iArr[8] + 1;
        iArr[8] = i2;
        if (i2 == 0) {
            iArr[9] = iArr[9] + 1;
        }
    }

    public void b(byte[] bArr) {
        i(this.f4603d, this.f4605f, this.f4606g);
        g.a.f.f.h(this.f4606g, bArr, 0);
    }

    public int c() {
        return 8;
    }

    public final boolean d(int i2) {
        int i3 = this.j + i2;
        this.j = i3;
        if (i3 >= i2 || i3 < 0) {
            return false;
        }
        int i4 = this.k + 1;
        this.k = i4;
        if (i4 != 0) {
            return false;
        }
        int i5 = this.l + 1;
        this.l = i5;
        return (i5 & 32) != 0;
    }

    public void e(int i2, int[] iArr, int i3) {
        int i4 = (i2 - 16) / 4;
        int[] iArr2 = f4600a;
        iArr[i3] = iArr2[i4];
        iArr[i3 + 1] = iArr2[i4 + 1];
        iArr[i3 + 2] = iArr2[i4 + 2];
        iArr[i3 + 3] = iArr2[i4 + 3];
    }

    public void f() {
        int[] iArr = this.f4605f;
        iArr[9] = 0;
        iArr[8] = 0;
    }

    public final void g() {
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }

    @Override // g.a.b.w
    public String getAlgorithmName() {
        if (this.f4603d == 20) {
            return "Salsa20";
        }
        return "Salsa20/" + this.f4603d;
    }

    @Override // g.a.b.w
    public void init(boolean z, g.a.b.i iVar) {
        if (!(iVar instanceof g.a.b.j0.w0)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must include an IV");
        }
        g.a.b.j0.w0 w0Var = (g.a.b.j0.w0) iVar;
        byte[] bArrA = w0Var.a();
        if (bArrA == null || bArrA.length != c()) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires exactly " + c() + " bytes of IV");
        }
        g.a.b.i iVarB = w0Var.b();
        if (iVarB == null) {
            if (!this.f4608i) {
                throw new IllegalStateException(getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            j(null, bArrA);
        } else {
            if (!(iVarB instanceof g.a.b.j0.s0)) {
                throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
            }
            j(((g.a.b.j0.s0) iVarB).a(), bArrA);
        }
        reset();
        this.f4608i = true;
    }

    public void j(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            int length = (bArr.length - 16) / 4;
            int[] iArr = this.f4605f;
            int[] iArr2 = f4600a;
            iArr[0] = iArr2[length];
            iArr[5] = iArr2[length + 1];
            iArr[10] = iArr2[length + 2];
            iArr[15] = iArr2[length + 3];
            g.a.f.f.l(bArr, 0, iArr, 1, 4);
            g.a.f.f.l(bArr, bArr.length - 16, this.f4605f, 11, 4);
        }
        g.a.f.f.l(bArr2, 0, this.f4605f, 6, 2);
    }

    @Override // g.a.b.w
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (!this.f4608i) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i4 + i3 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (d(i3)) {
            throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
        }
        for (int i5 = 0; i5 < i3; i5++) {
            byte[] bArr3 = this.f4607h;
            int i6 = this.f4604e;
            bArr2[i5 + i4] = (byte) (bArr3[i6] ^ bArr[i5 + i2]);
            int i7 = (i6 + 1) & 63;
            this.f4604e = i7;
            if (i7 == 0) {
                a();
                b(this.f4607h);
            }
        }
        return i3;
    }

    @Override // g.a.b.w
    public void reset() {
        this.f4604e = 0;
        g();
        f();
        b(this.f4607h);
    }
}
