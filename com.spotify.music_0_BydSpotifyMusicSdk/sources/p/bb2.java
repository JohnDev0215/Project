package p;

/* JADX INFO: loaded from: classes.dex */
public final class bb2 {
    public final byte[] a;
    public int b;
    public int c;
    public boolean d;
    public final boolean e;
    public bb2 f;
    public bb2 g;

    public bb2() {
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    public final bb2 a() {
        bb2 bb2Var = this.f;
        if (bb2Var == this) {
            bb2Var = null;
        }
        bb2 bb2Var2 = this.g;
        v03.h(bb2Var2);
        bb2Var2.f = this.f;
        bb2 bb2Var3 = this.f;
        v03.h(bb2Var3);
        bb2Var3.g = this.g;
        this.f = null;
        this.g = null;
        return bb2Var;
    }

    public final void b(bb2 bb2Var) {
        bb2Var.g = this;
        bb2Var.f = this.f;
        bb2 bb2Var2 = this.f;
        v03.h(bb2Var2);
        bb2Var2.g = bb2Var;
        this.f = bb2Var;
    }

    public final bb2 c() {
        this.d = true;
        return new bb2(this.a, this.b, this.c, true, false);
    }

    public final void d(bb2 bb2Var, int i) {
        if (!bb2Var.e) {
            throw new IllegalStateException("only owner can write".toString());
        }
        int i2 = bb2Var.c;
        int i3 = i2 + i;
        byte[] bArr = bb2Var.a;
        if (i3 > 8192) {
            if (bb2Var.d) {
                throw new IllegalArgumentException();
            }
            int i4 = bb2Var.b;
            if (i3 - i4 > 8192) {
                throw new IllegalArgumentException();
            }
            rb.i0(0, i4, i2, bArr, bArr);
            bb2Var.c -= bb2Var.b;
            bb2Var.b = 0;
        }
        int i5 = bb2Var.c;
        int i6 = this.b;
        rb.i0(i5, i6, i6 + i, this.a, bArr);
        bb2Var.c += i;
        this.b += i;
    }

    public bb2(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        v03.j(bArr, "data");
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = z2;
    }
}
