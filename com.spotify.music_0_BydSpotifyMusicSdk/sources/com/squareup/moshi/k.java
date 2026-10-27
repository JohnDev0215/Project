package com.squareup.moshi;

import okio.ByteString;
import p.de2;
import p.fo;
import p.ko;
import p.qo;
import p.tl2;

/* JADX INFO: loaded from: classes.dex */
final class k implements de2 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    static final ByteString f69p;
    static final ByteString q;
    static final ByteString r;
    static final ByteString s;
    static final ByteString t;
    static final ByteString u;
    private final ko c;
    private final fo e;
    private final fo k;
    private ByteString l;
    private int m;
    private long n = 0;
    private boolean o = false;

    static {
        ByteString byteString = ByteString.k;
        f69p = qo.p("[]{}\"'/#");
        q = qo.p("'\\");
        r = qo.p("\"\\");
        s = qo.p("\r\n");
        t = qo.p("*");
        u = ByteString.k;
    }

    public k(ko koVar, fo foVar, ByteString byteString, int i) {
        this.c = koVar;
        this.e = koVar.d();
        this.k = foVar;
        this.l = byteString;
        this.m = i;
    }

    private void b(long j) {
        while (true) {
            long j2 = this.n;
            if (j2 >= j) {
                return;
            }
            ByteString byteString = this.l;
            ByteString byteString2 = u;
            if (byteString == byteString2) {
                return;
            }
            if (j2 == this.e.e) {
                if (j2 > 0) {
                    return;
                } else {
                    this.c.q0(1L);
                }
            }
            long jJ0 = this.e.J0(this.n, this.l);
            if (jJ0 == -1) {
                this.n = this.e.e;
            } else {
                byte bG0 = this.e.G0(jJ0);
                ByteString byteString3 = this.l;
                ByteString byteString4 = f69p;
                if (byteString3 == byteString4) {
                    if (bG0 == 34) {
                        this.l = r;
                        this.n = jJ0 + 1;
                    } else if (bG0 == 35) {
                        this.l = s;
                        this.n = jJ0 + 1;
                    } else if (bG0 == 39) {
                        this.l = q;
                        this.n = jJ0 + 1;
                    } else if (bG0 != 47) {
                        if (bG0 != 91) {
                            if (bG0 != 93) {
                                if (bG0 != 123) {
                                    if (bG0 != 125) {
                                    }
                                }
                            }
                            int i = this.m - 1;
                            this.m = i;
                            if (i == 0) {
                                this.l = byteString2;
                            }
                            this.n = jJ0 + 1;
                        }
                        this.m++;
                        this.n = jJ0 + 1;
                    } else {
                        long j3 = 2 + jJ0;
                        this.c.q0(j3);
                        long j4 = jJ0 + 1;
                        byte bG02 = this.e.G0(j4);
                        if (bG02 == 47) {
                            this.l = s;
                            this.n = j3;
                        } else if (bG02 == 42) {
                            this.l = t;
                            this.n = j3;
                        } else {
                            this.n = j4;
                        }
                    }
                } else if (byteString3 == q || byteString3 == r) {
                    if (bG0 == 92) {
                        long j5 = jJ0 + 2;
                        this.c.q0(j5);
                        this.n = j5;
                    } else {
                        if (this.m > 0) {
                            byteString2 = byteString4;
                        }
                        this.l = byteString2;
                        this.n = jJ0 + 1;
                    }
                } else if (byteString3 == t) {
                    long j6 = 2 + jJ0;
                    this.c.q0(j6);
                    long j7 = jJ0 + 1;
                    if (this.e.G0(j7) == 47) {
                        this.n = j6;
                        this.l = byteString4;
                    } else {
                        this.n = j7;
                    }
                } else {
                    if (byteString3 != s) {
                        throw new AssertionError();
                    }
                    this.n = jJ0 + 1;
                    this.l = byteString4;
                }
            }
        }
    }

    public void c() {
        this.o = true;
        while (this.l != u) {
            b(8192L);
            this.c.y(this.n);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.o = true;
    }

    @Override // p.de2
    public tl2 e() {
        return this.c.e();
    }

    @Override // p.de2
    public long t(fo foVar, long j) {
        if (this.o) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        if (!this.k.Q()) {
            long jT = this.k.t(foVar, j);
            long j2 = j - jT;
            if (this.e.Q()) {
                return jT;
            }
            long jT2 = t(foVar, j2);
            return jT2 != -1 ? jT + jT2 : jT;
        }
        b(j);
        long j3 = this.n;
        if (j3 == 0) {
            if (this.l == u) {
                return -1L;
            }
            throw new AssertionError();
        }
        long jMin = Math.min(j, j3);
        foVar.Y(this.e, jMin);
        this.n -= jMin;
        return jMin;
    }
}
