package r9;

import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f21899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21900b;
    public int c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f21901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public E f21902f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public E f21903g;

    public E() {
        this.f21899a = new byte[8192];
        this.f21901e = true;
        this.d = false;
    }

    public final E a() {
        E e10 = this.f21902f;
        if (e10 == this) {
            e10 = null;
        }
        E e11 = this.f21903g;
        A7.m.c(e11);
        e11.f21902f = this.f21902f;
        E e12 = this.f21902f;
        A7.m.c(e12);
        e12.f21903g = this.f21903g;
        this.f21902f = null;
        this.f21903g = null;
        return e10;
    }

    public final void b(E e10) {
        A7.m.f(e10, "segment");
        e10.f21903g = this;
        e10.f21902f = this.f21902f;
        E e11 = this.f21902f;
        A7.m.c(e11);
        e11.f21903g = e10;
        this.f21902f = e10;
    }

    public final E c() {
        this.d = true;
        return new E(this.f21899a, this.f21900b, this.c, true, false);
    }

    public final void d(E e10, int i7) {
        A7.m.f(e10, "sink");
        if (!e10.f21901e) {
            throw new IllegalStateException("only owner can write");
        }
        int i10 = e10.c;
        int i11 = i10 + i7;
        byte[] bArr = e10.f21899a;
        if (i11 > 8192) {
            if (e10.d) {
                throw new IllegalArgumentException();
            }
            int i12 = e10.f21900b;
            if (i11 - i12 > 8192) {
                throw new IllegalArgumentException();
            }
            AbstractC1996k.B(0, i12, i10, bArr, bArr);
            e10.c -= e10.f21900b;
            e10.f21900b = 0;
        }
        int i13 = e10.c;
        int i14 = this.f21900b;
        AbstractC1996k.B(i13, i14, i14 + i7, this.f21899a, bArr);
        e10.c += i7;
        this.f21900b += i7;
    }

    public E(byte[] bArr, int i7, int i10, boolean z6, boolean z10) {
        A7.m.f(bArr, "data");
        this.f21899a = bArr;
        this.f21900b = i7;
        this.c = i10;
        this.d = z6;
        this.f21901e = z10;
    }
}
