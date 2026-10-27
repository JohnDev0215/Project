package g.a.b.g0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.j0.y0;
import g.a.b.t;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class f implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4771a = 8;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4772b = 4;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4776f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f4777g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4778h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4779i = {9, 6, 3, 2, 8, 11, 1, 7, 10, 4, 14, 15, 12, 0, 13, 5, 3, 7, 14, 9, 8, 10, 15, 0, 5, 2, 6, 12, 11, 4, 13, 1, 14, 4, 6, 2, 11, 3, 13, 8, 12, 15, 5, 10, 0, 7, 1, 9, 14, 7, 10, 12, 13, 1, 3, 9, 0, 2, 11, 4, 15, 8, 5, 6, 11, 5, 1, 9, 8, 13, 15, 0, 14, 4, 2, 3, 12, 7, 10, 6, 3, 10, 13, 12, 1, 2, 0, 11, 7, 5, 9, 4, 8, 15, 14, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, 12, 4, 5, 15, 3, 11, 14, 11, 10, 15, 5, 0, 12, 14, 8, 6, 2, 3, 9, 1, 7, 13, 4};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4775e = new byte[8];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4774d = new byte[8];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4773c = 0;

    public final byte[] a(byte[] bArr, int i2, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length - i2];
        System.arraycopy(bArr, i2, bArr3, 0, bArr2.length);
        for (int i3 = 0; i3 != bArr2.length; i3++) {
            bArr3[i3] = (byte) (bArr3[i3] ^ bArr2[i3]);
        }
        return bArr3;
    }

    public final int b(byte[] bArr, int i2) {
        return ((bArr[i2 + 3] << 24) & (-16777216)) + ((bArr[i2 + 2] << 16) & 16711680) + ((bArr[i2 + 1] << 8) & 65280) + (bArr[i2] & 255);
    }

    public final int[] c(byte[] bArr) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        int[] iArr = new int[8];
        for (int i2 = 0; i2 != 8; i2++) {
            iArr[i2] = b(bArr, i2 * 4);
        }
        return iArr;
    }

    public final void d(int[] iArr, byte[] bArr, int i2, byte[] bArr2, int i3) {
        int iB = b(bArr, i2);
        int iB2 = b(bArr, i2 + 4);
        for (int i4 = 0; i4 < 2; i4++) {
            int i5 = 0;
            while (i5 < 8) {
                int iE = iB2 ^ e(iB, iArr[i5]);
                i5++;
                int i6 = iB;
                iB = iE;
                iB2 = i6;
            }
        }
        f(iB, bArr2, i3);
        f(iB2, bArr2, i3 + 4);
    }

    @Override // g.a.b.t
    public int doFinal(byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        while (true) {
            int i3 = this.f4773c;
            if (i3 >= this.f4771a) {
                break;
            }
            this.f4774d[i3] = 0;
            this.f4773c = i3 + 1;
        }
        byte[] bArr2 = this.f4774d;
        byte[] bArrA = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArrA, 0, this.f4775e.length);
        if (this.f4776f) {
            this.f4776f = false;
        } else {
            bArrA = a(this.f4774d, 0, this.f4775e);
        }
        d(this.f4777g, bArrA, 0, this.f4775e, 0);
        byte[] bArr3 = this.f4775e;
        int length = bArr3.length / 2;
        int i4 = this.f4772b;
        System.arraycopy(bArr3, length - i4, bArr, i2, i4);
        reset();
        return this.f4772b;
    }

    public final int e(int i2, int i3) {
        int i4 = i3 + i2;
        byte[] bArr = this.f4779i;
        int i5 = (bArr[((i4 >> 0) & 15) + 0] << 0) + (bArr[((i4 >> 4) & 15) + 16] << 4) + (bArr[((i4 >> 8) & 15) + 32] << 8) + (bArr[((i4 >> 12) & 15) + 48] << 12) + (bArr[((i4 >> 16) & 15) + 64] << 16) + (bArr[((i4 >> 20) & 15) + 80] << 20) + (bArr[((i4 >> 24) & 15) + 96] << 24) + (bArr[((i4 >> 28) & 15) + 112] << 28);
        return (i5 << 11) | (i5 >>> 21);
    }

    public final void f(int i2, byte[] bArr, int i3) {
        bArr[i3 + 3] = (byte) (i2 >>> 24);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3] = (byte) i2;
    }

    @Override // g.a.b.t
    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    @Override // g.a.b.t
    public int getMacSize() {
        return this.f4772b;
    }

    @Override // g.a.b.t
    public void init(g.a.b.i iVar) throws IllegalArgumentException {
        reset();
        this.f4774d = new byte[this.f4771a];
        this.f4778h = null;
        if (iVar instanceof y0) {
            y0 y0Var = (y0) iVar;
            System.arraycopy(y0Var.b(), 0, this.f4779i, 0, y0Var.b().length);
            if (y0Var.a() != null) {
                this.f4777g = c(((s0) y0Var.a()).a());
                return;
            }
            return;
        }
        if (iVar instanceof s0) {
            this.f4777g = c(((s0) iVar).a());
            return;
        }
        if (!(iVar instanceof w0)) {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + iVar.getClass().getName());
        }
        w0 w0Var = (w0) iVar;
        this.f4777g = c(((s0) w0Var.b()).a());
        byte[] bArrA = w0Var.a();
        byte[] bArr = this.f4775e;
        System.arraycopy(bArrA, 0, bArr, 0, bArr.length);
        this.f4778h = w0Var.a();
    }

    @Override // g.a.b.t
    public void reset() {
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4774d;
            if (i2 >= bArr.length) {
                this.f4773c = 0;
                this.f4776f = true;
                return;
            } else {
                bArr[i2] = 0;
                i2++;
            }
        }
    }

    @Override // g.a.b.t
    public void update(byte b2) throws IllegalStateException {
        int i2 = this.f4773c;
        byte[] bArr = this.f4774d;
        if (i2 == bArr.length) {
            byte[] bArrA = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArrA, 0, this.f4775e.length);
            if (this.f4776f) {
                this.f4776f = false;
                byte[] bArr2 = this.f4778h;
                if (bArr2 != null) {
                    bArrA = a(this.f4774d, 0, bArr2);
                }
            } else {
                bArrA = a(this.f4774d, 0, this.f4775e);
            }
            d(this.f4777g, bArrA, 0, this.f4775e, 0);
            this.f4773c = 0;
        }
        byte[] bArr3 = this.f4774d;
        int i3 = this.f4773c;
        this.f4773c = i3 + 1;
        bArr3[i3] = b2;
    }

    @Override // g.a.b.t
    public void update(byte[] bArr, int i2, int i3) throws IllegalStateException, DataLengthException {
        if (i3 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int i4 = this.f4771a;
        int i5 = this.f4773c;
        int i6 = i4 - i5;
        if (i3 > i6) {
            System.arraycopy(bArr, i2, this.f4774d, i5, i6);
            byte[] bArr2 = this.f4774d;
            byte[] bArrA = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArrA, 0, this.f4775e.length);
            if (this.f4776f) {
                this.f4776f = false;
                byte[] bArr3 = this.f4778h;
                if (bArr3 != null) {
                    bArrA = a(this.f4774d, 0, bArr3);
                }
            } else {
                bArrA = a(this.f4774d, 0, this.f4775e);
            }
            d(this.f4777g, bArrA, 0, this.f4775e, 0);
            this.f4773c = 0;
            while (true) {
                i3 -= i6;
                i2 += i6;
                if (i3 <= this.f4771a) {
                    break;
                }
                d(this.f4777g, a(bArr, i2, this.f4775e), 0, this.f4775e, 0);
                i6 = this.f4771a;
            }
        }
        System.arraycopy(bArr, i2, this.f4774d, this.f4773c, i3);
        this.f4773c += i3;
    }
}
