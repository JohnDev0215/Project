package p;

import java.io.Closeable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class eo implements Closeable {
    public fo c;
    public boolean e;
    public bb2 k;
    public byte[] m;
    public long l = -1;
    public int n = -1;
    public int o = -1;

    public final void b(long j) {
        fo foVar = this.c;
        if (foVar == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        if (!this.e) {
            throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
        }
        long j2 = foVar.e;
        if (j <= j2) {
            if (j < 0) {
                throw new IllegalArgumentException(u4.g("newSize < 0: ", j).toString());
            }
            long j3 = j2 - j;
            while (true) {
                if (j3 <= 0) {
                    break;
                }
                bb2 bb2Var = foVar.c;
                v03.h(bb2Var);
                bb2 bb2Var2 = bb2Var.g;
                v03.h(bb2Var2);
                int i = bb2Var2.c;
                long j4 = i - bb2Var2.b;
                if (j4 > j3) {
                    bb2Var2.c = i - ((int) j3);
                    break;
                } else {
                    foVar.c = bb2Var2.a();
                    db2.a(bb2Var2);
                    j3 -= j4;
                }
            }
            this.k = null;
            this.l = j;
            this.m = null;
            this.n = -1;
            this.o = -1;
        } else if (j > j2) {
            long j5 = j - j2;
            int i2 = 1;
            boolean z = true;
            for (long j6 = 0; j5 > j6; j6 = 0) {
                bb2 bb2VarS0 = foVar.S0(i2);
                int iMin = (int) Math.min(j5, 8192 - bb2VarS0.c);
                int i3 = bb2VarS0.c + iMin;
                bb2VarS0.c = i3;
                j5 -= (long) iMin;
                if (z) {
                    this.k = bb2VarS0;
                    this.l = j2;
                    this.m = bb2VarS0.a;
                    this.n = i3 - iMin;
                    this.o = i3;
                    z = false;
                }
                i2 = 1;
            }
        }
        foVar.e = j;
    }

    public final int c(long j) {
        fo foVar = this.c;
        if (foVar == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        if (j >= -1) {
            long j2 = foVar.e;
            if (j <= j2) {
                if (j == -1 || j == j2) {
                    this.k = null;
                    this.l = j;
                    this.m = null;
                    this.n = -1;
                    this.o = -1;
                    return -1;
                }
                bb2 bb2Var = foVar.c;
                bb2 bb2Var2 = this.k;
                long j3 = 0;
                if (bb2Var2 != null) {
                    long j4 = this.l - ((long) (this.n - bb2Var2.b));
                    if (j4 > j) {
                        j2 = j4;
                        bb2Var2 = bb2Var;
                        bb2Var = bb2Var2;
                    } else {
                        j3 = j4;
                    }
                } else {
                    bb2Var2 = bb2Var;
                }
                if (j2 - j > j - j3) {
                    while (true) {
                        v03.h(bb2Var2);
                        long j5 = ((long) (bb2Var2.c - bb2Var2.b)) + j3;
                        if (j < j5) {
                            break;
                        }
                        bb2Var2 = bb2Var2.f;
                        j3 = j5;
                    }
                } else {
                    while (j2 > j) {
                        v03.h(bb2Var);
                        bb2Var = bb2Var.g;
                        v03.h(bb2Var);
                        j2 -= (long) (bb2Var.c - bb2Var.b);
                    }
                    bb2Var2 = bb2Var;
                    j3 = j2;
                }
                if (this.e) {
                    v03.h(bb2Var2);
                    if (bb2Var2.d) {
                        byte[] bArr = bb2Var2.a;
                        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                        v03.i(bArrCopyOf, "copyOf(...)");
                        bb2 bb2Var3 = new bb2(bArrCopyOf, bb2Var2.b, bb2Var2.c, false, true);
                        if (foVar.c == bb2Var2) {
                            foVar.c = bb2Var3;
                        }
                        bb2Var2.b(bb2Var3);
                        bb2 bb2Var4 = bb2Var3.g;
                        v03.h(bb2Var4);
                        bb2Var4.a();
                        bb2Var2 = bb2Var3;
                    }
                }
                this.k = bb2Var2;
                this.l = j;
                v03.h(bb2Var2);
                this.m = bb2Var2.a;
                int i = bb2Var2.b + ((int) (j - j3));
                this.n = i;
                int i2 = bb2Var2.c;
                this.o = i2;
                return i2 - i;
            }
        }
        throw new ArrayIndexOutOfBoundsException("offset=" + j + " > size=" + foVar.e);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c == null) {
            throw new IllegalStateException("not attached to a buffer".toString());
        }
        this.c = null;
        this.k = null;
        this.l = -1L;
        this.m = null;
        this.n = -1;
        this.o = -1;
    }
}
