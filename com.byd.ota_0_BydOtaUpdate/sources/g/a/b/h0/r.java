package g.a.b.h0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import java.util.Vector;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class r implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.e f4950a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.e f4951b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4952c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4953d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4954e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Vector f4955f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4956g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4957h;
    public byte[] l;
    public byte[] m;
    public int n;
    public int o;
    public long p;
    public long q;
    public byte[] r;
    public byte[] s;
    public byte[] u;
    public byte[] v;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4958i = null;
    public byte[] j = new byte[24];
    public byte[] k = new byte[16];
    public byte[] t = new byte[16];

    public r(g.a.b.e eVar, g.a.b.e eVar2) {
        if (eVar == null) {
            throw new IllegalArgumentException("'hashCipher' cannot be null");
        }
        if (eVar.b() != 16) {
            throw new IllegalArgumentException("'hashCipher' must have a block size of 16");
        }
        if (eVar2 == null) {
            throw new IllegalArgumentException("'mainCipher' cannot be null");
        }
        if (eVar2.b() != 16) {
            throw new IllegalArgumentException("'mainCipher' must have a block size of 16");
        }
        if (!eVar.getAlgorithmName().equals(eVar2.getAlgorithmName())) {
            throw new IllegalArgumentException("'hashCipher' and 'mainCipher' must be the same algorithm");
        }
        this.f4950a = eVar;
        this.f4951b = eVar2;
    }

    public static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) ((135 >>> ((1 - l(bArr, bArr2)) << 3)) ^ bArr2[15]);
        return bArr2;
    }

    public static void d(byte[] bArr, int i2) {
        bArr[i2] = -128;
        while (true) {
            i2++;
            if (i2 >= 16) {
                return;
            } else {
                bArr[i2] = 0;
            }
        }
    }

    public static int e(long j) {
        if (j == 0) {
            return 64;
        }
        int i2 = 0;
        while ((1 & j) == 0) {
            i2++;
            j >>>= 1;
        }
        return i2;
    }

    public static int l(byte[] bArr, byte[] bArr2) {
        int i2 = 16;
        int i3 = 0;
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3;
            }
            int i4 = bArr[i2] & 255;
            bArr2[i2] = (byte) (i3 | (i4 << 1));
            i3 = (i4 >>> 7) & 1;
        }
    }

    public static void n(byte[] bArr, byte[] bArr2) {
        for (int i2 = 15; i2 >= 0; i2--) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
    }

    @Override // g.a.b.h0.a
    public void a(byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            byte[] bArr2 = this.l;
            int i5 = this.n;
            bArr2[i5] = bArr[i2 + i4];
            int i6 = i5 + 1;
            this.n = i6;
            if (i6 == bArr2.length) {
                h();
            }
        }
    }

    @Override // g.a.b.h0.a
    public byte[] b() {
        byte[] bArr = this.v;
        return bArr == null ? new byte[this.f4953d] : g.a.f.a.f(bArr);
    }

    @Override // g.a.b.h0.a
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException {
        byte[] bArr2;
        if (this.f4952c) {
            bArr2 = null;
        } else {
            int i3 = this.o;
            int i4 = this.f4953d;
            if (i3 < i4) {
                throw new InvalidCipherTextException("data too short");
            }
            int i5 = i3 - i4;
            this.o = i5;
            bArr2 = new byte[i4];
            System.arraycopy(this.m, i5, bArr2, 0, i4);
        }
        int i6 = this.n;
        if (i6 > 0) {
            d(this.l, i6);
            m(this.f4956g);
        }
        int i7 = this.o;
        if (i7 > 0) {
            if (this.f4952c) {
                d(this.m, i7);
                n(this.u, this.m);
            }
            n(this.t, this.f4956g);
            byte[] bArr3 = new byte[16];
            this.f4950a.a(this.t, 0, bArr3, 0);
            n(this.m, bArr3);
            int length = bArr.length;
            int i8 = this.o;
            if (length < i2 + i8) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.m, 0, bArr, i2, i8);
            if (!this.f4952c) {
                d(this.m, this.o);
                n(this.u, this.m);
            }
        }
        n(this.u, this.t);
        n(this.u, this.f4957h);
        g.a.b.e eVar = this.f4950a;
        byte[] bArr4 = this.u;
        eVar.a(bArr4, 0, bArr4, 0);
        n(this.u, this.s);
        int i9 = this.f4953d;
        byte[] bArr5 = new byte[i9];
        this.v = bArr5;
        System.arraycopy(this.u, 0, bArr5, 0, i9);
        int i10 = this.o;
        if (this.f4952c) {
            int length2 = bArr.length;
            int i11 = i2 + i10;
            int i12 = this.f4953d;
            if (length2 < i11 + i12) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.v, 0, bArr, i11, i12);
            i10 += this.f4953d;
        } else if (!g.a.f.a.q(this.v, bArr2)) {
            throw new InvalidCipherTextException("mac check in OCB failed");
        }
        k(false);
        return i10;
    }

    public void f(byte[] bArr) {
        if (bArr != null) {
            g.a.f.a.u(bArr, (byte) 0);
        }
    }

    public byte[] g(int i2) {
        while (i2 >= this.f4955f.size()) {
            Vector vector = this.f4955f;
            vector.addElement(c((byte[]) vector.lastElement()));
        }
        return (byte[]) this.f4955f.elementAt(i2);
    }

    @Override // g.a.b.h0.a
    public int getOutputSize(int i2) {
        int i3 = i2 + this.o;
        if (this.f4952c) {
            return i3 + this.f4953d;
        }
        int i4 = this.f4953d;
        if (i3 < i4) {
            return 0;
        }
        return i3 - i4;
    }

    @Override // g.a.b.h0.a
    public g.a.b.e getUnderlyingCipher() {
        return this.f4951b;
    }

    @Override // g.a.b.h0.a
    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.o;
        if (!this.f4952c) {
            int i4 = this.f4953d;
            if (i3 < i4) {
                return 0;
            }
            i3 -= i4;
        }
        return i3 - (i3 % 16);
    }

    public void h() {
        long j = this.p + 1;
        this.p = j;
        m(g(e(j)));
        this.n = 0;
    }

    public void i(byte[] bArr, int i2) {
        if (bArr.length < i2 + 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.f4952c) {
            n(this.u, this.m);
            this.o = 0;
        }
        byte[] bArr2 = this.t;
        long j = this.q + 1;
        this.q = j;
        n(bArr2, g(e(j)));
        n(this.m, this.t);
        g.a.b.e eVar = this.f4951b;
        byte[] bArr3 = this.m;
        eVar.a(bArr3, 0, bArr3, 0);
        n(this.m, this.t);
        System.arraycopy(this.m, 0, bArr, i2, 16);
        if (this.f4952c) {
            return;
        }
        n(this.u, this.m);
        byte[] bArr4 = this.m;
        System.arraycopy(bArr4, 16, bArr4, 0, this.f4953d);
        this.o = this.f4953d;
    }

    @Override // g.a.b.h0.a
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        byte[] bArrA;
        s0 s0VarB;
        boolean z2 = this.f4952c;
        this.f4952c = z;
        this.v = null;
        if (iVar instanceof g.a.b.j0.a) {
            g.a.b.j0.a aVar = (g.a.b.j0.a) iVar;
            bArrA = aVar.d();
            this.f4954e = aVar.a();
            int iC = aVar.c();
            if (iC < 64 || iC > 128 || iC % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + iC);
            }
            this.f4953d = iC / 8;
            s0VarB = aVar.b();
        } else {
            if (!(iVar instanceof w0)) {
                throw new IllegalArgumentException("invalid parameters passed to OCB");
            }
            w0 w0Var = (w0) iVar;
            bArrA = w0Var.a();
            this.f4954e = null;
            this.f4953d = 16;
            s0VarB = (s0) w0Var.b();
        }
        this.l = new byte[16];
        this.m = new byte[z ? 16 : this.f4953d + 16];
        if (bArrA == null) {
            bArrA = new byte[0];
        }
        if (bArrA.length > 15) {
            throw new IllegalArgumentException("IV must be no more than 15 bytes");
        }
        if (s0VarB != null) {
            this.f4950a.init(true, s0VarB);
            this.f4951b.init(z, s0VarB);
            this.f4958i = null;
        } else if (z2 != z) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
        byte[] bArr = new byte[16];
        this.f4956g = bArr;
        this.f4950a.a(bArr, 0, bArr, 0);
        this.f4957h = c(this.f4956g);
        Vector vector = new Vector();
        this.f4955f = vector;
        vector.addElement(c(this.f4957h));
        int iJ = j(bArrA);
        int i2 = iJ % 8;
        int i3 = iJ / 8;
        if (i2 == 0) {
            System.arraycopy(this.j, i3, this.k, 0, 16);
        } else {
            for (int i4 = 0; i4 < 16; i4++) {
                byte[] bArr2 = this.j;
                int i5 = bArr2[i3] & 255;
                i3++;
                this.k[i4] = (byte) (((bArr2[i3] & 255) >>> (8 - i2)) | (i5 << i2));
            }
        }
        this.n = 0;
        this.o = 0;
        this.p = 0L;
        this.q = 0L;
        this.r = new byte[16];
        this.s = new byte[16];
        System.arraycopy(this.k, 0, this.t, 0, 16);
        this.u = new byte[16];
        byte[] bArr3 = this.f4954e;
        if (bArr3 != null) {
            a(bArr3, 0, bArr3.length);
        }
    }

    public int j(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i2 = 0;
        System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
        bArr2[0] = (byte) (this.f4953d << 4);
        int length = 15 - bArr.length;
        bArr2[length] = (byte) (bArr2[length] | 1);
        int i3 = bArr2[15] & 63;
        bArr2[15] = (byte) (bArr2[15] & 192);
        byte[] bArr3 = this.f4958i;
        if (bArr3 == null || !g.a.f.a.a(bArr2, bArr3)) {
            byte[] bArr4 = new byte[16];
            this.f4958i = bArr2;
            this.f4950a.a(bArr2, 0, bArr4, 0);
            System.arraycopy(bArr4, 0, this.j, 0, 16);
            while (i2 < 8) {
                byte[] bArr5 = this.j;
                int i4 = i2 + 16;
                byte b2 = bArr4[i2];
                i2++;
                bArr5[i4] = (byte) (b2 ^ bArr4[i2]);
            }
        }
        return i3;
    }

    public void k(boolean z) {
        this.f4950a.reset();
        this.f4951b.reset();
        f(this.l);
        f(this.m);
        this.n = 0;
        this.o = 0;
        this.p = 0L;
        this.q = 0L;
        f(this.r);
        f(this.s);
        System.arraycopy(this.k, 0, this.t, 0, 16);
        f(this.u);
        if (z) {
            this.v = null;
        }
        byte[] bArr = this.f4954e;
        if (bArr != null) {
            a(bArr, 0, bArr.length);
        }
    }

    public void m(byte[] bArr) {
        n(this.r, bArr);
        n(this.l, this.r);
        g.a.b.e eVar = this.f4950a;
        byte[] bArr2 = this.l;
        eVar.a(bArr2, 0, bArr2, 0);
        n(this.s, this.l);
    }

    @Override // g.a.b.h0.a
    public int processByte(byte b2, byte[] bArr, int i2) throws DataLengthException {
        byte[] bArr2 = this.m;
        int i3 = this.o;
        bArr2[i3] = b2;
        int i4 = i3 + 1;
        this.o = i4;
        if (i4 != bArr2.length) {
            return 0;
        }
        i(bArr, i2);
        return 16;
    }

    @Override // g.a.b.h0.a
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws DataLengthException {
        if (bArr.length < i2 + i3) {
            throw new DataLengthException("Input buffer too short");
        }
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            byte[] bArr3 = this.m;
            int i7 = this.o;
            bArr3[i7] = bArr[i2 + i6];
            int i8 = i7 + 1;
            this.o = i8;
            if (i8 == bArr3.length) {
                i(bArr2, i4 + i5);
                i5 += 16;
            }
        }
        return i5;
    }
}
