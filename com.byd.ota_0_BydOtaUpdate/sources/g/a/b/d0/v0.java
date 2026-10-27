package g.a.b.d0;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public abstract class v0 implements g.a.b.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f4620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f4621b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4623d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4624e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4625f;

    public static int p(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public static int q(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    @Override // g.a.b.e
    public final int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.f4621b == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.f4620a) {
            e(bArr, i2, bArr2, i3);
            return 16;
        }
        d(bArr, i2, bArr2, i3);
        return 16;
    }

    @Override // g.a.b.e
    public int b() {
        return 16;
    }

    public final void c() {
        int iP = p(this.f4622c, 13);
        int iP2 = p(this.f4624e, 3);
        int i2 = (this.f4623d ^ iP) ^ iP2;
        int i3 = (this.f4625f ^ iP2) ^ (iP << 3);
        this.f4623d = p(i2, 1);
        int iP3 = p(i3, 7);
        this.f4625f = iP3;
        this.f4622c = p((iP ^ this.f4623d) ^ iP3, 5);
        this.f4624e = p((this.f4625f ^ iP2) ^ (this.f4623d << 7), 22);
    }

    public abstract void d(byte[] bArr, int i2, byte[] bArr2, int i3);

    public abstract void e(byte[] bArr, int i2, byte[] bArr2, int i3);

    public final void f(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i3 ^ i2;
        int i8 = (i6 | i7) ^ i5;
        int i9 = i4 ^ i8;
        int i10 = i7 ^ i9;
        this.f4624e = i10;
        int i11 = (i7 & i5) ^ i6;
        int i12 = (i10 & i11) ^ i8;
        this.f4623d = i12;
        int i13 = (i2 & i8) ^ (i12 | i9);
        this.f4625f = i13;
        this.f4622c = i13 ^ (i11 ^ i9);
    }

    public final void g(int i2, int i3, int i4, int i5) {
        int i6 = i5 ^ i3;
        int i7 = i2 ^ (i3 & i6);
        int i8 = i6 ^ i7;
        int i9 = i4 ^ i8;
        this.f4625f = i9;
        int i10 = i3 ^ (i6 & i7);
        int i11 = i7 ^ (i9 | i10);
        this.f4623d = i11;
        int i12 = ~i11;
        int i13 = i10 ^ i9;
        this.f4622c = i12 ^ i13;
        this.f4624e = (i12 | i13) ^ i8;
    }

    @Override // g.a.b.e
    public String getAlgorithmName() {
        return "Serpent";
    }

    public final void h(int i2, int i3, int i4, int i5) {
        int i6 = i3 ^ i5;
        int i7 = ~i6;
        int i8 = i2 ^ i4;
        int i9 = i4 ^ i6;
        int i10 = (i3 & i9) ^ i8;
        this.f4622c = i10;
        int i11 = (((i2 | i7) ^ i5) | i8) ^ i6;
        this.f4625f = i11;
        int i12 = ~i9;
        int i13 = i11 | i10;
        this.f4623d = i12 ^ i13;
        this.f4624e = (i13 ^ i8) ^ (i5 & i12);
    }

    public final void i(int i2, int i3, int i4, int i5) {
        int i6 = i2 | i3;
        int i7 = i3 ^ i4;
        int i8 = i2 ^ (i3 & i7);
        int i9 = i4 ^ i8;
        int i10 = i5 | i8;
        int i11 = i7 ^ i10;
        this.f4622c = i11;
        int i12 = (i10 | i7) ^ i5;
        this.f4624e = i9 ^ i12;
        int i13 = i6 ^ i12;
        int i14 = i8 ^ (i11 & i13);
        this.f4625f = i14;
        this.f4623d = i14 ^ (i13 ^ i11);
    }

    @Override // g.a.b.e
    public void init(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.s0) {
            this.f4620a = z;
            this.f4621b = o(((g.a.b.j0.s0) iVar).a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + iVar.getClass().getName());
    }

    public final void j(int i2, int i3, int i4, int i5) {
        int i6 = i3 ^ ((i4 | i5) & i2);
        int i7 = i4 ^ (i2 & i6);
        int i8 = i5 ^ i7;
        this.f4623d = i8;
        int i9 = ~i2;
        int i10 = (i7 & i8) ^ i6;
        this.f4625f = i10;
        int i11 = i5 ^ (i8 | i9);
        this.f4622c = i10 ^ i11;
        this.f4624e = (i9 ^ i8) ^ (i6 & i11);
    }

    public final void k(int i2, int i3, int i4, int i5) {
        int i6 = ~i4;
        int i7 = (i3 & i6) ^ i5;
        int i8 = i2 & i7;
        int i9 = (i3 ^ i6) ^ i8;
        this.f4625f = i9;
        int i10 = i9 | i3;
        this.f4623d = i7 ^ (i2 & i10);
        int i11 = i5 | i2;
        this.f4622c = (i6 ^ i10) ^ i11;
        this.f4624e = ((i2 ^ i4) | i8) ^ (i3 & i11);
    }

    public final void l(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i2 ^ i3;
        int i8 = i4 ^ i7;
        int i9 = (i4 | i6) ^ i5;
        this.f4623d = i8 ^ i9;
        int i10 = i7 ^ (i8 & i9);
        int i11 = i9 ^ (i3 | i10);
        this.f4625f = i11;
        int i12 = i3 | i11;
        this.f4622c = i10 ^ i12;
        this.f4624e = (i5 & i6) ^ (i12 ^ i8);
    }

    public final void m(int i2, int i3, int i4, int i5) {
        int i6 = (i2 & i3) | i4;
        int i7 = (i2 | i3) & i5;
        int i8 = i6 ^ i7;
        this.f4625f = i8;
        int i9 = i3 ^ i7;
        int i10 = ((i8 ^ (~i5)) | i9) ^ i2;
        this.f4623d = i10;
        int i11 = (i9 ^ i4) ^ (i5 | i10);
        this.f4622c = i11;
        this.f4624e = ((i2 & i8) ^ i11) ^ (i6 ^ i10);
    }

    public final void n() {
        int iQ = (q(this.f4624e, 22) ^ this.f4625f) ^ (this.f4623d << 7);
        int iQ2 = q(this.f4622c, 5) ^ this.f4623d;
        int i2 = this.f4625f;
        int i3 = iQ2 ^ i2;
        int iQ3 = q(i2, 7);
        int iQ4 = q(this.f4623d, 1);
        this.f4625f = (iQ3 ^ iQ) ^ (i3 << 3);
        this.f4623d = (iQ4 ^ i3) ^ iQ;
        this.f4624e = q(iQ, 3);
        this.f4622c = q(i3, 13);
    }

    public abstract int[] o(byte[] bArr);

    public final void r(int i2, int i3, int i4, int i5) {
        int i6 = i2 ^ i5;
        int i7 = i4 ^ i6;
        int i8 = i3 ^ i7;
        int i9 = (i5 & i2) ^ i8;
        this.f4625f = i9;
        int i10 = i2 ^ (i3 & i6);
        this.f4624e = (i4 | i10) ^ i8;
        int i11 = (i7 ^ i10) & i9;
        this.f4623d = (~i7) ^ i11;
        this.f4622c = (~i10) ^ i11;
    }

    @Override // g.a.b.e
    public void reset() {
    }

    public final void s(int i2, int i3, int i4, int i5) {
        int i6 = (~i2) ^ i3;
        int i7 = (i2 | i6) ^ i4;
        int i8 = i5 ^ i7;
        this.f4624e = i8;
        int i9 = i3 ^ (i5 | i6);
        int i10 = i8 ^ i6;
        int i11 = (i7 & i9) ^ i10;
        this.f4625f = i11;
        int i12 = i9 ^ i7;
        this.f4623d = i11 ^ i12;
        this.f4622c = i7 ^ (i12 & i10);
    }

    public final void t(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i3 ^ i5;
        int i8 = (i4 & i6) ^ i7;
        this.f4622c = i8;
        int i9 = i4 ^ i6;
        int i10 = i3 & (i4 ^ i8);
        int i11 = i9 ^ i10;
        this.f4625f = i11;
        int i12 = i2 ^ ((i10 | i5) & (i8 | i9));
        this.f4624e = i12;
        this.f4623d = (i12 ^ (i5 | i6)) ^ (i7 ^ i11);
    }

    public final void u(int i2, int i3, int i4, int i5) {
        int i6 = i2 ^ i3;
        int i7 = i2 & i4;
        int i8 = i2 | i5;
        int i9 = i4 ^ i5;
        int i10 = i7 | (i6 & i8);
        int i11 = i9 ^ i10;
        this.f4624e = i11;
        int i12 = (i8 ^ i3) ^ i10;
        int i13 = i6 ^ (i9 & i12);
        this.f4622c = i13;
        int i14 = i13 & i11;
        this.f4623d = i12 ^ i14;
        this.f4625f = (i3 | i5) ^ (i9 ^ i14);
    }

    public final void v(int i2, int i3, int i4, int i5) {
        int i6 = i2 ^ i5;
        int i7 = i4 ^ (i5 & i6);
        int i8 = i3 | i7;
        this.f4625f = i6 ^ i8;
        int i9 = ~i3;
        int i10 = (i6 | i9) ^ i7;
        this.f4622c = i10;
        int i11 = i9 ^ i6;
        int i12 = (i8 & i11) ^ (i10 & i2);
        this.f4624e = i12;
        this.f4623d = (i2 ^ i7) ^ (i11 & i12);
    }

    public final void w(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i2 ^ i3;
        int i8 = i2 ^ i5;
        int i9 = (i4 ^ i6) ^ (i7 | i8);
        this.f4622c = i9;
        int i10 = i5 & i9;
        int i11 = (i7 ^ i9) ^ i10;
        this.f4623d = i11;
        int i12 = i8 ^ (i9 | i6);
        this.f4624e = (i7 | i10) ^ i12;
        this.f4625f = (i12 & i11) ^ (i3 ^ i10);
    }

    public final void x(int i2, int i3, int i4, int i5) {
        int i6 = ~i2;
        int i7 = i2 ^ i5;
        int i8 = i3 ^ i7;
        int i9 = i4 ^ (i6 | i7);
        int i10 = i3 ^ i9;
        this.f4623d = i10;
        int i11 = (i7 | i10) ^ i5;
        int i12 = (i9 & i11) ^ i8;
        this.f4624e = i12;
        int i13 = i11 ^ i9;
        this.f4622c = i12 ^ i13;
        this.f4625f = (i13 & i8) ^ (~i9);
    }

    public final void y(int i2, int i3, int i4, int i5) {
        int i6 = i3 ^ i4;
        int i7 = (i4 & i6) ^ i5;
        int i8 = i2 ^ i7;
        int i9 = i3 ^ ((i5 | i6) & i8);
        this.f4623d = i9;
        int i10 = (i2 & i8) ^ i6;
        this.f4625f = i10;
        int i11 = (i9 | i7) ^ i8;
        int i12 = i7 ^ (i10 & i11);
        this.f4624e = i12;
        this.f4622c = (i10 & i12) ^ (~i11);
    }
}
