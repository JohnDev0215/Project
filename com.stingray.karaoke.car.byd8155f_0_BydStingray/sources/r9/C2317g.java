package r9;

import java.io.Closeable;
import java.util.Arrays;

/* JADX INFO: renamed from: r9.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2317g implements Closeable {
    public C2319i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21926m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public E f21927n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public byte[] f21928p;
    public long o = -1;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21929q = -1;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f21930r = -1;

    public final void b(long j3) {
        C2319i c2319i = this.l;
        if (c2319i == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        if (!this.f21926m) {
            throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
        }
        long j10 = c2319i.f21932m;
        if (j3 <= j10) {
            if (j3 < 0) {
                throw new IllegalArgumentException(M5.b.f("newSize < 0: ", j3).toString());
            }
            long j11 = j10 - j3;
            while (true) {
                if (j11 <= 0) {
                    break;
                }
                E e10 = c2319i.l;
                A7.m.c(e10);
                E e11 = e10.f21903g;
                A7.m.c(e11);
                int i7 = e11.c;
                long j12 = i7 - e11.f21900b;
                if (j12 > j11) {
                    e11.c = i7 - ((int) j11);
                    break;
                } else {
                    c2319i.l = e11.a();
                    F.a(e11);
                    j11 -= j12;
                }
            }
            this.f21927n = null;
            this.o = j3;
            this.f21928p = null;
            this.f21929q = -1;
            this.f21930r = -1;
        } else if (j3 > j10) {
            long j13 = j3 - j10;
            int i10 = 1;
            boolean z6 = true;
            for (long j14 = 0; j13 > j14; j14 = 0) {
                E eT = c2319i.T(i10);
                int iMin = (int) Math.min(j13, 8192 - eT.c);
                int i11 = eT.c + iMin;
                eT.c = i11;
                j13 -= (long) iMin;
                if (z6) {
                    this.f21927n = eT;
                    this.o = j10;
                    this.f21928p = eT.f21899a;
                    this.f21929q = i11 - iMin;
                    this.f21930r = i11;
                    z6 = false;
                }
                i10 = 1;
            }
        }
        c2319i.f21932m = j3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.l == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        this.l = null;
        this.f21927n = null;
        this.o = -1L;
        this.f21928p = null;
        this.f21929q = -1;
        this.f21930r = -1;
    }

    public final int g(long j3) {
        C2319i c2319i = this.l;
        if (c2319i == null) {
            throw new IllegalStateException("not attached to a buffer");
        }
        if (j3 >= -1) {
            long j10 = c2319i.f21932m;
            if (j3 <= j10) {
                if (j3 == -1 || j3 == j10) {
                    this.f21927n = null;
                    this.o = j3;
                    this.f21928p = null;
                    this.f21929q = -1;
                    this.f21930r = -1;
                    return -1;
                }
                E e10 = c2319i.l;
                E e11 = this.f21927n;
                long j11 = 0;
                if (e11 != null) {
                    long j12 = this.o - ((long) (this.f21929q - e11.f21900b));
                    if (j12 > j3) {
                        j10 = j12;
                        e11 = e10;
                        e10 = e11;
                    } else {
                        j11 = j12;
                    }
                } else {
                    e11 = e10;
                }
                if (j10 - j3 > j3 - j11) {
                    while (true) {
                        A7.m.c(e11);
                        long j13 = ((long) (e11.c - e11.f21900b)) + j11;
                        if (j3 < j13) {
                            break;
                        }
                        e11 = e11.f21902f;
                        j11 = j13;
                    }
                } else {
                    while (j10 > j3) {
                        A7.m.c(e10);
                        e10 = e10.f21903g;
                        A7.m.c(e10);
                        j10 -= (long) (e10.c - e10.f21900b);
                    }
                    e11 = e10;
                    j11 = j10;
                }
                if (this.f21926m) {
                    A7.m.c(e11);
                    if (e11.d) {
                        byte[] bArr = e11.f21899a;
                        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                        A7.m.e(bArrCopyOf, "copyOf(...)");
                        E e12 = new E(bArrCopyOf, e11.f21900b, e11.c, false, true);
                        if (c2319i.l == e11) {
                            c2319i.l = e12;
                        }
                        e11.b(e12);
                        E e13 = e12.f21903g;
                        A7.m.c(e13);
                        e13.a();
                        e11 = e12;
                    }
                }
                this.f21927n = e11;
                this.o = j3;
                A7.m.c(e11);
                this.f21928p = e11.f21899a;
                int i7 = e11.f21900b + ((int) (j3 - j11));
                this.f21929q = i7;
                int i10 = e11.c;
                this.f21930r = i10;
                return i10 - i7;
            }
        }
        throw new ArrayIndexOutOfBoundsException("offset=" + j3 + " > size=" + c2319i.f21932m);
    }
}
