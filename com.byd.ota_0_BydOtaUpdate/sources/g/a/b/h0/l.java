package g.a.b.h0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class l implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.e f4907a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.h0.w.c f4908b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.h0.w.b f4909c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4910d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4911e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4912f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4913g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4914h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f4915i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public byte[] m;
    public byte[] n;
    public byte[] o;
    public byte[] p;
    public byte[] q;
    public int r;
    public int s;
    public long t;
    public byte[] u;
    public int v;
    public long w;
    public long x;

    public l(g.a.b.e eVar) {
        this(eVar, null);
    }

    public l(g.a.b.e eVar, g.a.b.h0.w.c cVar) {
        if (eVar.b() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        cVar = cVar == null ? new g.a.b.h0.w.e() : cVar;
        this.f4907a = eVar;
        this.f4908b = cVar;
    }

    @Override // g.a.b.h0.a
    public void a(byte[] bArr, int i2, int i3) {
        c();
        for (int i4 = 0; i4 < i3; i4++) {
            byte[] bArr2 = this.u;
            int i5 = this.v;
            bArr2[i5] = bArr[i2 + i4];
            int i6 = i5 + 1;
            this.v = i6;
            if (i6 == 16) {
                e(this.o, bArr2);
                this.v = 0;
                this.w += 16;
            }
        }
    }

    @Override // g.a.b.h0.a
    public byte[] b() {
        byte[] bArr = this.m;
        return bArr == null ? new byte[this.f4912f] : g.a.f.a.f(bArr);
    }

    public final void c() {
        if (this.f4911e) {
            return;
        }
        if (!this.f4910d) {
            throw new IllegalStateException("GCM cipher needs to be initialised");
        }
        throw new IllegalStateException("GCM cipher cannot be reused for encryption");
    }

    public final void d(byte[] bArr, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 16) {
            g(bArr, bArr2, i3, Math.min(i2 - i3, 16));
        }
    }

    @Override // g.a.b.h0.a
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException {
        c();
        if (this.t == 0) {
            i();
        }
        int i3 = this.s;
        if (!this.f4910d) {
            int i4 = this.f4912f;
            if (i3 < i4) {
                throw new InvalidCipherTextException("data too short");
            }
            i3 -= i4;
            if (bArr.length - i2 < i3) {
                throw new OutputLengthException("Output buffer too short");
            }
        } else if (bArr.length - i2 < this.f4912f + i3) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (i3 > 0) {
            l(this.l, 0, i3, bArr, i2);
        }
        long j = this.w;
        int i5 = this.v;
        long j2 = j + ((long) i5);
        this.w = j2;
        if (j2 > this.x) {
            if (i5 > 0) {
                g(this.o, this.u, 0, i5);
            }
            if (this.x > 0) {
                g.a.b.h0.w.d.l(this.o, this.p);
            }
            long j3 = ((this.t * 8) + 127) >>> 7;
            byte[] bArr2 = new byte[16];
            if (this.f4909c == null) {
                g.a.b.h0.w.a aVar = new g.a.b.h0.w.a();
                this.f4909c = aVar;
                aVar.a(this.j);
            }
            this.f4909c.b(j3, bArr2);
            g.a.b.h0.w.d.e(this.o, bArr2);
            g.a.b.h0.w.d.l(this.n, this.o);
        }
        byte[] bArr3 = new byte[16];
        g.a.f.f.q(this.w * 8, bArr3, 0);
        g.a.f.f.q(this.t * 8, bArr3, 8);
        e(this.n, bArr3);
        byte[] bArr4 = new byte[16];
        this.f4907a.a(this.k, 0, bArr4, 0);
        g.a.b.h0.w.d.l(bArr4, this.n);
        int i6 = this.f4912f;
        byte[] bArr5 = new byte[i6];
        this.m = bArr5;
        System.arraycopy(bArr4, 0, bArr5, 0, i6);
        if (this.f4910d) {
            System.arraycopy(this.m, 0, bArr, i2 + this.s, this.f4912f);
            i3 += this.f4912f;
        } else {
            int i7 = this.f4912f;
            byte[] bArr6 = new byte[i7];
            System.arraycopy(this.l, i3, bArr6, 0, i7);
            if (!g.a.f.a.q(this.m, bArr6)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        n(false);
        return i3;
    }

    public final void e(byte[] bArr, byte[] bArr2) {
        g.a.b.h0.w.d.l(bArr, bArr2);
        this.f4908b.b(bArr);
    }

    public final void f(byte[] bArr, byte[] bArr2, int i2) {
        g.a.b.h0.w.d.m(bArr, bArr2, i2);
        this.f4908b.b(bArr);
    }

    public final void g(byte[] bArr, byte[] bArr2, int i2, int i3) {
        g.a.b.h0.w.d.n(bArr, bArr2, i2, i3);
        this.f4908b.b(bArr);
    }

    @Override // g.a.b.h0.a
    public int getOutputSize(int i2) {
        int i3 = i2 + this.s;
        if (this.f4910d) {
            return i3 + this.f4912f;
        }
        int i4 = this.f4912f;
        if (i3 < i4) {
            return 0;
        }
        return i3 - i4;
    }

    @Override // g.a.b.h0.a
    public g.a.b.e getUnderlyingCipher() {
        return this.f4907a;
    }

    @Override // g.a.b.h0.a
    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.s;
        if (!this.f4910d) {
            int i4 = this.f4912f;
            if (i3 < i4) {
                return 0;
            }
            i3 -= i4;
        }
        return i3 - (i3 % 16);
    }

    public final void h(byte[] bArr) {
        int i2 = this.r;
        if (i2 == 0) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.r = i2 - 1;
        byte[] bArr2 = this.q;
        int i3 = (bArr2[15] & 255) + 1;
        bArr2[15] = (byte) i3;
        int i4 = (i3 >>> 8) + (bArr2[14] & 255);
        bArr2[14] = (byte) i4;
        int i5 = (i4 >>> 8) + (bArr2[13] & 255);
        bArr2[13] = (byte) i5;
        bArr2[12] = (byte) ((i5 >>> 8) + (bArr2[12] & 255));
        this.f4907a.a(bArr2, 0, bArr, 0);
    }

    public final void i() {
        if (this.w > 0) {
            System.arraycopy(this.o, 0, this.p, 0, 16);
            this.x = this.w;
        }
        int i2 = this.v;
        if (i2 > 0) {
            g(this.p, this.u, 0, i2);
            this.x += (long) this.v;
        }
        if (this.x > 0) {
            System.arraycopy(this.p, 0, this.n, 0, 16);
        }
    }

    @Override // g.a.b.h0.a
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        byte[] bArrA;
        s0 s0VarB;
        byte[] bArr;
        this.f4910d = z;
        this.m = null;
        this.f4911e = true;
        if (iVar instanceof g.a.b.j0.a) {
            g.a.b.j0.a aVar = (g.a.b.j0.a) iVar;
            bArrA = aVar.d();
            this.f4915i = aVar.a();
            int iC = aVar.c();
            if (iC < 32 || iC > 128 || iC % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + iC);
            }
            this.f4912f = iC / 8;
            s0VarB = aVar.b();
        } else {
            if (!(iVar instanceof w0)) {
                throw new IllegalArgumentException("invalid parameters passed to GCM");
            }
            w0 w0Var = (w0) iVar;
            bArrA = w0Var.a();
            this.f4915i = null;
            this.f4912f = 16;
            s0VarB = (s0) w0Var.b();
        }
        this.l = new byte[z ? 16 : this.f4912f + 16];
        if (bArrA == null || bArrA.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (z && (bArr = this.f4914h) != null && g.a.f.a.a(bArr, bArrA)) {
            if (s0VarB == null) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
            byte[] bArr2 = this.f4913g;
            if (bArr2 != null && g.a.f.a.a(bArr2, s0VarB.a())) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
        }
        this.f4914h = bArrA;
        if (s0VarB != null) {
            this.f4913g = s0VarB.a();
        }
        if (s0VarB != null) {
            this.f4907a.init(true, s0VarB);
            byte[] bArr3 = new byte[16];
            this.j = bArr3;
            this.f4907a.a(bArr3, 0, bArr3, 0);
            this.f4908b.a(this.j);
            this.f4909c = null;
        } else if (this.j == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        byte[] bArr4 = new byte[16];
        this.k = bArr4;
        byte[] bArr5 = this.f4914h;
        if (bArr5.length == 12) {
            System.arraycopy(bArr5, 0, bArr4, 0, bArr5.length);
            this.k[15] = 1;
        } else {
            d(bArr4, bArr5, bArr5.length);
            byte[] bArr6 = new byte[16];
            g.a.f.f.q(((long) this.f4914h.length) * 8, bArr6, 8);
            e(this.k, bArr6);
        }
        this.n = new byte[16];
        this.o = new byte[16];
        this.p = new byte[16];
        this.u = new byte[16];
        this.v = 0;
        this.w = 0L;
        this.x = 0L;
        this.q = g.a.f.a.f(this.k);
        this.r = -2;
        this.s = 0;
        this.t = 0L;
        byte[] bArr7 = this.f4915i;
        if (bArr7 != null) {
            a(bArr7, 0, bArr7.length);
        }
    }

    public void j(byte b2) {
        c();
        byte[] bArr = this.u;
        int i2 = this.v;
        bArr[i2] = b2;
        int i3 = i2 + 1;
        this.v = i3;
        if (i3 == 16) {
            e(this.o, bArr);
            this.v = 0;
            this.w += 16;
        }
    }

    public final void k(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (bArr2.length - i3 < 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.t == 0) {
            i();
        }
        byte[] bArr3 = new byte[16];
        h(bArr3);
        if (this.f4910d) {
            g.a.b.h0.w.d.m(bArr3, bArr, i2);
            e(this.n, bArr3);
            System.arraycopy(bArr3, 0, bArr2, i3, 16);
        } else {
            f(this.n, bArr, i2);
            g.a.b.h0.w.d.k(bArr3, 0, bArr, i2, bArr2, i3);
        }
        this.t += 16;
    }

    public final void l(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        byte[] bArr3 = new byte[16];
        h(bArr3);
        if (this.f4910d) {
            g.a.b.h0.w.d.j(bArr, i2, bArr3, 0, i3);
            g(this.n, bArr, i2, i3);
        } else {
            g(this.n, bArr, i2, i3);
            g.a.b.h0.w.d.j(bArr, i2, bArr3, 0, i3);
        }
        System.arraycopy(bArr, i2, bArr2, i4, i3);
        this.t += (long) i3;
    }

    public void m() {
        n(true);
    }

    public final void n(boolean z) {
        this.f4907a.reset();
        this.n = new byte[16];
        this.o = new byte[16];
        this.p = new byte[16];
        this.u = new byte[16];
        this.v = 0;
        this.w = 0L;
        this.x = 0L;
        this.q = g.a.f.a.f(this.k);
        this.r = -2;
        this.s = 0;
        this.t = 0L;
        byte[] bArr = this.l;
        if (bArr != null) {
            g.a.f.a.u(bArr, (byte) 0);
        }
        if (z) {
            this.m = null;
        }
        if (this.f4910d) {
            this.f4911e = false;
            return;
        }
        byte[] bArr2 = this.f4915i;
        if (bArr2 != null) {
            a(bArr2, 0, bArr2.length);
        }
    }

    @Override // g.a.b.h0.a
    public int processByte(byte b2, byte[] bArr, int i2) throws DataLengthException {
        c();
        byte[] bArr2 = this.l;
        int i3 = this.s;
        bArr2[i3] = b2;
        int i4 = i3 + 1;
        this.s = i4;
        if (i4 != bArr2.length) {
            return 0;
        }
        k(bArr2, 0, bArr, i2);
        if (this.f4910d) {
            this.s = 0;
        } else {
            byte[] bArr3 = this.l;
            System.arraycopy(bArr3, 16, bArr3, 0, this.f4912f);
            this.s = this.f4912f;
        }
        return 16;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033 A[LOOP:1: B:14:0x0031->B:15:0x0033, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    @Override // g.a.b.h0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int processBytes(byte[] r8, int r9, int r10, byte[] r11, int r12) throws org.bouncycastle.crypto.DataLengthException {
        /*
            r7 = this;
            r7.c()
            int r0 = r8.length
            int r0 = r0 - r9
            if (r0 < r10) goto L74
            boolean r0 = r7.f4910d
            r1 = 16
            r2 = 0
            if (r0 == 0) goto L49
            int r0 = r7.s
            if (r0 == 0) goto L30
        L12:
            if (r10 <= 0) goto L30
            int r10 = r10 + (-1)
            byte[] r0 = r7.l
            int r3 = r7.s
            int r4 = r9 + 1
            r9 = r8[r9]
            r0[r3] = r9
            int r3 = r3 + 1
            r7.s = r3
            if (r3 != r1) goto L2e
            r7.k(r0, r2, r11, r12)
            r7.s = r2
            r0 = r1
            r9 = r4
            goto L31
        L2e:
            r9 = r4
            goto L12
        L30:
            r0 = r2
        L31:
            if (r10 < r1) goto L3f
            int r3 = r12 + r0
            r7.k(r8, r9, r11, r3)
            int r9 = r9 + 16
            int r10 = r10 + (-16)
            int r0 = r0 + 16
            goto L31
        L3f:
            if (r10 <= 0) goto L73
            byte[] r11 = r7.l
            java.lang.System.arraycopy(r8, r9, r11, r2, r10)
            r7.s = r10
            goto L73
        L49:
            r0 = r2
            r3 = r0
        L4b:
            if (r3 >= r10) goto L73
            byte[] r4 = r7.l
            int r5 = r7.s
            int r6 = r9 + r3
            r6 = r8[r6]
            r4[r5] = r6
            int r5 = r5 + 1
            r7.s = r5
            int r6 = r4.length
            if (r5 != r6) goto L70
            int r5 = r12 + r0
            r7.k(r4, r2, r11, r5)
            byte[] r4 = r7.l
            int r5 = r7.f4912f
            java.lang.System.arraycopy(r4, r1, r4, r2, r5)
            int r4 = r7.f4912f
            r7.s = r4
            int r0 = r0 + 16
        L70:
            int r3 = r3 + 1
            goto L4b
        L73:
            return r0
        L74:
            org.bouncycastle.crypto.DataLengthException r8 = new org.bouncycastle.crypto.DataLengthException
            java.lang.String r9 = "Input buffer too short"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.b.h0.l.processBytes(byte[], int, int, byte[], int):int");
    }
}
