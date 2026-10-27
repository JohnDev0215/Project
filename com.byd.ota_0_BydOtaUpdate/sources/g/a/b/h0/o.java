package g.a.b.h0;

import g.a.b.j0.w0;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class o implements g.a.b.h0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.e f4924a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4925b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4926c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f4927d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4928e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4929f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4930g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4931h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4932i;
    public byte[] j;
    public byte[] k;
    public a l;
    public a m;
    public int n;

    public class a extends ByteArrayOutputStream {
        public a() {
        }

        public byte[] c() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public o(g.a.b.e eVar) {
        this(eVar, 4);
    }

    public o(g.a.b.e eVar, int i2) {
        this.l = new a();
        this.m = new a();
        this.n = 4;
        this.f4924a = eVar;
        this.f4925b = eVar.b();
        this.f4930g = new byte[eVar.b()];
        this.f4927d = new byte[eVar.b()];
        this.f4928e = new byte[eVar.b()];
        this.f4929f = new byte[eVar.b()];
        this.f4931h = new byte[eVar.b()];
        this.f4932i = new byte[eVar.b()];
        this.j = new byte[eVar.b()];
        this.k = new byte[eVar.b()];
        j(i2);
    }

    @Override // g.a.b.h0.a
    public void a(byte[] bArr, int i2, int i3) {
        this.l.write(bArr, i2, i3);
    }

    @Override // g.a.b.h0.a
    public byte[] b() {
        return g.a.f.a.f(this.f4928e);
    }

    public final void c(byte[] bArr, int i2, int i3) {
        while (i3 > 0) {
            for (int i4 = 0; i4 < this.f4924a.b(); i4++) {
                byte[] bArr2 = this.f4929f;
                bArr2[i4] = (byte) (bArr2[i4] ^ bArr[i2 + i4]);
            }
            g.a.b.e eVar = this.f4924a;
            byte[] bArr3 = this.f4929f;
            eVar.a(bArr3, 0, bArr3, 0);
            i3 -= this.f4924a.b();
            i2 += this.f4924a.b();
        }
    }

    public final void d(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = 0;
        while (true) {
            byte[] bArr3 = this.k;
            if (i5 >= bArr3.length) {
                break;
            }
            byte[] bArr4 = this.j;
            bArr4[i5] = (byte) (bArr4[i5] + bArr3[i5]);
            i5++;
        }
        this.f4924a.a(this.j, 0, this.f4932i, 0);
        for (int i6 = 0; i6 < this.f4924a.b(); i6++) {
            bArr2[i4 + i6] = (byte) (this.f4932i[i6] ^ bArr[i2 + i6]);
        }
    }

    @Override // g.a.b.h0.a
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException {
        int iH = h(this.m.c(), 0, this.m.size(), bArr, i2);
        i();
        return iH;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0047 A[LOOP:0: B:24:0x0040->B:26:0x0047, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte e(boolean r4, int r5) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "0"
            if (r4 == 0) goto Lf
            java.lang.String r4 = "1"
            r0.append(r4)
            goto L12
        Lf:
            r0.append(r1)
        L12:
            r4 = 8
            if (r5 == r4) goto L33
            r4 = 16
            if (r5 == r4) goto L30
            r4 = 32
            if (r5 == r4) goto L2d
            r4 = 48
            if (r5 == r4) goto L2a
            r4 = 64
            if (r5 == r4) goto L27
            goto L38
        L27:
            java.lang.String r4 = "110"
            goto L35
        L2a:
            java.lang.String r4 = "101"
            goto L35
        L2d:
            java.lang.String r4 = "100"
            goto L35
        L30:
            java.lang.String r4 = "011"
            goto L35
        L33:
            java.lang.String r4 = "010"
        L35:
            r0.append(r4)
        L38:
            int r4 = r3.n
            int r4 = r4 + (-1)
            java.lang.String r4 = java.lang.Integer.toBinaryString(r4)
        L40:
            int r5 = r4.length()
            r2 = 4
            if (r5 >= r2) goto L56
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>(r4)
            r4 = 0
            java.lang.StringBuffer r4 = r5.insert(r4, r1)
            java.lang.String r4 = r4.toString()
            goto L40
        L56:
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5 = 2
            int r4 = java.lang.Integer.parseInt(r4, r5)
            byte r4 = (byte) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.b.h0.o.e(boolean, int):byte");
    }

    public final void f(int i2, byte[] bArr, int i3) {
        bArr[i3 + 3] = (byte) (i2 >> 24);
        bArr[i3 + 2] = (byte) (i2 >> 16);
        bArr[i3 + 1] = (byte) (i2 >> 8);
        bArr[i3] = (byte) i2;
    }

    public final void g(byte[] bArr, int i2, int i3, int i4) {
        if (i3 - i2 < this.f4924a.b()) {
            throw new IllegalArgumentException("authText buffer too short");
        }
        if (i3 % this.f4924a.b() != 0) {
            throw new IllegalArgumentException("padding not supported");
        }
        byte[] bArr2 = this.f4930g;
        System.arraycopy(bArr2, 0, this.f4931h, 0, (bArr2.length - this.n) - 1);
        f(i4, this.f4932i, 0);
        System.arraycopy(this.f4932i, 0, this.f4931h, (this.f4930g.length - this.n) - 1, 4);
        byte[] bArr3 = this.f4931h;
        bArr3[bArr3.length - 1] = e(true, this.f4925b);
        this.f4924a.a(this.f4931h, 0, this.f4929f, 0);
        f(i3, this.f4932i, 0);
        if (i3 <= this.f4924a.b() - this.n) {
            for (int i5 = 0; i5 < i3; i5++) {
                byte[] bArr4 = this.f4932i;
                int i6 = this.n + i5;
                bArr4[i6] = (byte) (bArr4[i6] ^ bArr[i2 + i5]);
            }
            for (int i7 = 0; i7 < this.f4924a.b(); i7++) {
                byte[] bArr5 = this.f4929f;
                bArr5[i7] = (byte) (bArr5[i7] ^ this.f4932i[i7]);
            }
            g.a.b.e eVar = this.f4924a;
            byte[] bArr6 = this.f4929f;
            eVar.a(bArr6, 0, bArr6, 0);
            return;
        }
        for (int i8 = 0; i8 < this.f4924a.b(); i8++) {
            byte[] bArr7 = this.f4929f;
            bArr7[i8] = (byte) (bArr7[i8] ^ this.f4932i[i8]);
        }
        g.a.b.e eVar2 = this.f4924a;
        byte[] bArr8 = this.f4929f;
        eVar2.a(bArr8, 0, bArr8, 0);
        while (i3 != 0) {
            for (int i9 = 0; i9 < this.f4924a.b(); i9++) {
                byte[] bArr9 = this.f4929f;
                bArr9[i9] = (byte) (bArr9[i9] ^ bArr[i9 + i2]);
            }
            g.a.b.e eVar3 = this.f4924a;
            byte[] bArr10 = this.f4929f;
            eVar3.a(bArr10, 0, bArr10, 0);
            i2 += this.f4924a.b();
            i3 -= this.f4924a.b();
        }
    }

    @Override // g.a.b.h0.a
    public int getOutputSize(int i2) {
        return i2 + this.f4925b;
    }

    @Override // g.a.b.h0.a
    public g.a.b.e getUnderlyingCipher() {
        return this.f4924a;
    }

    @Override // g.a.b.h0.a
    public int getUpdateOutputSize(int i2) {
        return i2;
    }

    public int h(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws InvalidCipherTextException, IllegalStateException {
        int i5;
        if (bArr.length - i2 < i3) {
            throw new DataLengthException("input buffer too short");
        }
        if (bArr2.length - i4 < i3) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.l.size() > 0) {
            if (this.f4926c) {
                g(this.l.c(), 0, this.l.size(), this.m.size());
            } else {
                g(this.l.c(), 0, this.l.size(), this.m.size() - this.f4925b);
            }
        }
        if (!this.f4926c) {
            if ((i3 - this.f4925b) % this.f4924a.b() != 0) {
                throw new DataLengthException("partial blocks not supported");
            }
            this.f4924a.a(this.f4930g, 0, this.j, 0);
            int iB = i3 / this.f4924a.b();
            for (int i6 = 0; i6 < iB; i6++) {
                d(bArr, i2, i3, bArr2, i4);
                i2 += this.f4924a.b();
                i4 += this.f4924a.b();
            }
            if (i3 > i2) {
                int i7 = 0;
                while (true) {
                    byte[] bArr3 = this.k;
                    if (i7 >= bArr3.length) {
                        break;
                    }
                    byte[] bArr4 = this.j;
                    bArr4[i7] = (byte) (bArr4[i7] + bArr3[i7]);
                    i7++;
                }
                this.f4924a.a(this.j, 0, this.f4932i, 0);
                int i8 = 0;
                while (true) {
                    i5 = this.f4925b;
                    if (i8 >= i5) {
                        break;
                    }
                    bArr2[i4 + i8] = (byte) (this.f4932i[i8] ^ bArr[i2 + i8]);
                    i8++;
                }
                i4 += i5;
            }
            int i9 = 0;
            while (true) {
                byte[] bArr5 = this.k;
                if (i9 >= bArr5.length) {
                    break;
                }
                byte[] bArr6 = this.j;
                bArr6[i9] = (byte) (bArr6[i9] + bArr5[i9]);
                i9++;
            }
            this.f4924a.a(this.j, 0, this.f4932i, 0);
            int i10 = this.f4925b;
            System.arraycopy(bArr2, i4 - i10, this.f4932i, 0, i10);
            c(bArr2, 0, i4 - this.f4925b);
            System.arraycopy(this.f4929f, 0, this.f4928e, 0, this.f4925b);
            int i11 = this.f4925b;
            byte[] bArr7 = new byte[i11];
            System.arraycopy(this.f4932i, 0, bArr7, 0, i11);
            if (!g.a.f.a.q(this.f4928e, bArr7)) {
                throw new InvalidCipherTextException("mac check failed");
            }
            i();
            return i3 - this.f4925b;
        }
        if (i3 % this.f4924a.b() != 0) {
            throw new DataLengthException("partial blocks not supported");
        }
        c(bArr, i2, i3);
        this.f4924a.a(this.f4930g, 0, this.j, 0);
        int iB2 = i3;
        while (iB2 > 0) {
            d(bArr, i2, i3, bArr2, i4);
            iB2 -= this.f4924a.b();
            i2 += this.f4924a.b();
            i4 += this.f4924a.b();
        }
        int i12 = 0;
        while (true) {
            byte[] bArr8 = this.k;
            if (i12 >= bArr8.length) {
                break;
            }
            byte[] bArr9 = this.j;
            bArr9[i12] = (byte) (bArr9[i12] + bArr8[i12]);
            i12++;
        }
        this.f4924a.a(this.j, 0, this.f4932i, 0);
        int i13 = 0;
        while (true) {
            int i14 = this.f4925b;
            if (i13 >= i14) {
                System.arraycopy(this.f4929f, 0, this.f4928e, 0, i14);
                i();
                return i3 + this.f4925b;
            }
            bArr2[i4 + i13] = (byte) (this.f4932i[i13] ^ this.f4929f[i13]);
            i13++;
        }
    }

    public void i() {
        g.a.f.a.u(this.f4931h, (byte) 0);
        g.a.f.a.u(this.f4932i, (byte) 0);
        g.a.f.a.u(this.k, (byte) 0);
        g.a.f.a.u(this.f4929f, (byte) 0);
        this.k[0] = 1;
        this.m.reset();
        this.l.reset();
        byte[] bArr = this.f4927d;
        if (bArr != null) {
            a(bArr, 0, bArr.length);
        }
    }

    @Override // g.a.b.h0.a
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        g.a.b.i iVarB;
        if (iVar instanceof g.a.b.j0.a) {
            g.a.b.j0.a aVar = (g.a.b.j0.a) iVar;
            if (aVar.c() > 512 || aVar.c() < 64 || aVar.c() % 8 != 0) {
                throw new IllegalArgumentException("Invalid mac size specified");
            }
            this.f4930g = aVar.d();
            this.f4925b = aVar.c() / 8;
            this.f4927d = aVar.a();
            iVarB = aVar.b();
        } else {
            if (!(iVar instanceof w0)) {
                throw new IllegalArgumentException("Invalid parameters specified");
            }
            w0 w0Var = (w0) iVar;
            this.f4930g = w0Var.a();
            this.f4925b = this.f4924a.b();
            this.f4927d = null;
            iVarB = w0Var.b();
        }
        this.f4928e = new byte[this.f4925b];
        this.f4926c = z;
        this.f4924a.init(true, iVarB);
        this.k[0] = 1;
        byte[] bArr = this.f4927d;
        if (bArr != null) {
            a(bArr, 0, bArr.length);
        }
    }

    public final void j(int i2) {
        if (i2 != 4 && i2 != 6 && i2 != 8) {
            throw new IllegalArgumentException("Nb = 4 is recommended by DSTU7624 but can be changed to only 6 or 8 in this implementation");
        }
        this.n = i2;
    }

    @Override // g.a.b.h0.a
    public int processByte(byte b2, byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        this.m.write(b2);
        return 0;
    }

    @Override // g.a.b.h0.a
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalStateException, DataLengthException {
        if (bArr.length < i2 + i3) {
            throw new DataLengthException("input buffer too short");
        }
        this.m.write(bArr, i2, i3);
        return 0;
    }
}
