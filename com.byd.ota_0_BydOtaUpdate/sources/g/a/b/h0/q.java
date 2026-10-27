package g.a.b.h0;

import g.a.b.j0.s0;
import g.a.b.j0.w0;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class q implements g.a.b.h0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.e f4940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.f f4941b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4943d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f4944e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4945f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4946g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g.a.b.h0.x.a f4947h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long[] f4948i;
    public final int j;
    public a k = new a();
    public a l = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4942c = -1;

    public class a extends ByteArrayOutputStream {
        public a() {
        }

        public byte[] c() {
            return ((ByteArrayOutputStream) this).buf;
        }
    }

    public q(g.a.b.e eVar) {
        this.f4940a = eVar;
        this.f4941b = new g.a.b.f(new p(eVar));
        int iB = this.f4940a.b();
        this.j = iB;
        this.f4944e = new byte[iB];
        this.f4946g = new byte[iB];
        this.f4947h = d(iB);
        this.f4948i = new long[iB >>> 3];
        this.f4945f = null;
    }

    public static g.a.b.h0.x.a d(int i2) {
        if (i2 == 16) {
            return new g.a.b.h0.x.f();
        }
        if (i2 == 32) {
            return new g.a.b.h0.x.g();
        }
        if (i2 == 64) {
            return new g.a.b.h0.x.e();
        }
        throw new IllegalArgumentException("Only 128, 256, and 512 -bit block sizes supported");
    }

    public static void h(long[] jArr, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < jArr.length; i3++) {
            jArr[i3] = jArr[i3] ^ g.a.f.f.n(bArr, i2);
            i2 += 8;
        }
    }

    @Override // g.a.b.h0.a
    public void a(byte[] bArr, int i2, int i3) {
        this.k.write(bArr, i2, i3);
    }

    @Override // g.a.b.h0.a
    public byte[] b() {
        int i2 = this.f4942c;
        byte[] bArr = new byte[i2];
        System.arraycopy(this.f4945f, 0, bArr, 0, i2);
        return bArr;
    }

    public final void c(byte[] bArr, int i2, int i3, int i4) {
        int i5 = i2 + i3;
        while (i2 < i5) {
            h(this.f4948i, bArr, i2);
            this.f4947h.a(this.f4948i);
            i2 += this.j;
        }
        long[] jArr = this.f4948i;
        jArr[0] = ((((long) i4) & 4294967295L) << 3) ^ jArr[0];
        int i6 = this.j >>> 4;
        jArr[i6] = jArr[i6] ^ ((4294967295L & ((long) i3)) << 3);
        byte[] bArrX = g.a.f.f.x(jArr);
        this.f4945f = bArrX;
        this.f4940a.a(bArrX, 0, bArrX, 0);
    }

    @Override // g.a.b.h0.a
    public int doFinal(byte[] bArr, int i2) throws InvalidCipherTextException, IllegalStateException {
        int iA;
        int size = this.l.size();
        if (!this.f4943d && size < this.f4942c) {
            throw new InvalidCipherTextException("data too short");
        }
        byte[] bArr2 = new byte[this.j];
        this.f4940a.a(bArr2, 0, bArr2, 0);
        long[] jArr = new long[this.j >>> 3];
        g.a.f.f.o(bArr2, 0, jArr);
        this.f4947h.b(jArr);
        g.a.f.a.u(bArr2, (byte) 0);
        g.a.f.a.w(jArr, 0L);
        int size2 = this.k.size();
        if (size2 > 0) {
            e(this.k.c(), 0, size2);
        }
        if (!this.f4943d) {
            int i3 = size - this.f4942c;
            if (bArr.length - i2 < i3) {
                throw new OutputLengthException("Output buffer too short");
            }
            c(this.l.c(), 0, i3, size2);
            int iH = this.f4941b.h(this.l.c(), 0, i3, bArr, i2);
            iA = iH + this.f4941b.a(bArr, i2 + iH);
        } else {
            if ((bArr.length - i2) - this.f4942c < size) {
                throw new OutputLengthException("Output buffer too short");
            }
            int iH2 = this.f4941b.h(this.l.c(), 0, size, bArr, i2);
            iA = iH2 + this.f4941b.a(bArr, i2 + iH2);
            c(bArr, i2, size, size2);
        }
        byte[] bArr3 = this.f4945f;
        if (bArr3 == null) {
            throw new IllegalStateException("mac is not calculated");
        }
        if (this.f4943d) {
            System.arraycopy(bArr3, 0, bArr, i2 + iA, this.f4942c);
            g();
            return iA + this.f4942c;
        }
        byte[] bArr4 = new byte[this.f4942c];
        byte[] bArrC = this.l.c();
        int i4 = this.f4942c;
        System.arraycopy(bArrC, size - i4, bArr4, 0, i4);
        int i5 = this.f4942c;
        byte[] bArr5 = new byte[i5];
        System.arraycopy(this.f4945f, 0, bArr5, 0, i5);
        if (!g.a.f.a.q(bArr4, bArr5)) {
            throw new InvalidCipherTextException("mac verification failed");
        }
        g();
        return iA;
    }

    public final void e(byte[] bArr, int i2, int i3) {
        int i4 = i3 + i2;
        while (i2 < i4) {
            h(this.f4948i, bArr, i2);
            this.f4947h.a(this.f4948i);
            i2 += this.j;
        }
    }

    public void f(byte b2) {
        this.k.write(b2);
    }

    public void g() {
        g.a.f.a.w(this.f4948i, 0L);
        this.f4940a.reset();
        this.l.reset();
        this.k.reset();
        byte[] bArr = this.f4944e;
        if (bArr != null) {
            a(bArr, 0, bArr.length);
        }
    }

    @Override // g.a.b.h0.a
    public int getOutputSize(int i2) {
        int size = i2 + this.l.size();
        if (this.f4943d) {
            return size + this.f4942c;
        }
        int i3 = this.f4942c;
        if (size < i3) {
            return 0;
        }
        return size - i3;
    }

    @Override // g.a.b.h0.a
    public g.a.b.e getUnderlyingCipher() {
        return this.f4940a;
    }

    @Override // g.a.b.h0.a
    public int getUpdateOutputSize(int i2) {
        return 0;
    }

    @Override // g.a.b.h0.a
    public void init(boolean z, g.a.b.i iVar) throws IllegalArgumentException {
        s0 s0VarB;
        this.f4943d = z;
        if (iVar instanceof g.a.b.j0.a) {
            g.a.b.j0.a aVar = (g.a.b.j0.a) iVar;
            byte[] bArrD = aVar.d();
            byte[] bArr = this.f4946g;
            int length = bArr.length - bArrD.length;
            g.a.f.a.u(bArr, (byte) 0);
            System.arraycopy(bArrD, 0, this.f4946g, length, bArrD.length);
            this.f4944e = aVar.a();
            int iC = aVar.c();
            if (iC < 64 || iC > (this.j << 3) || (iC & 7) != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + iC);
            }
            this.f4942c = iC >>> 3;
            s0VarB = aVar.b();
            byte[] bArr2 = this.f4944e;
            if (bArr2 != null) {
                a(bArr2, 0, bArr2.length);
            }
        } else {
            if (!(iVar instanceof w0)) {
                throw new IllegalArgumentException("Invalid parameter passed");
            }
            w0 w0Var = (w0) iVar;
            byte[] bArrA = w0Var.a();
            byte[] bArr3 = this.f4946g;
            int length2 = bArr3.length - bArrA.length;
            g.a.f.a.u(bArr3, (byte) 0);
            System.arraycopy(bArrA, 0, this.f4946g, length2, bArrA.length);
            this.f4944e = null;
            this.f4942c = this.j;
            s0VarB = (s0) w0Var.b();
        }
        this.f4945f = new byte[this.j];
        this.f4941b.f(true, new w0(s0VarB, this.f4946g));
        this.f4940a.init(true, s0VarB);
    }

    @Override // g.a.b.h0.a
    public int processByte(byte b2, byte[] bArr, int i2) throws IllegalStateException, DataLengthException {
        this.l.write(b2);
        return 0;
    }

    @Override // g.a.b.h0.a
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) throws IllegalStateException, DataLengthException {
        if (bArr.length < i2 + i3) {
            throw new DataLengthException("input buffer too short");
        }
        this.l.write(bArr, i2, i3);
        return 0;
    }
}
