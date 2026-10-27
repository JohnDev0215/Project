package j9;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import n7.AbstractC1996k;
import r9.C;
import r9.C2319i;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class v implements Closeable {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Logger f17591q = Logger.getLogger(e.class.getName());
    public final C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2319i f17592m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17593n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final c f17594p;

    public v(C c) {
        A7.m.f(c, "sink");
        this.l = c;
        C2319i c2319i = new C2319i();
        this.f17592m = c2319i;
        this.f17593n = 16384;
        this.f17594p = new c(c2319i);
    }

    public final synchronized void b(y yVar) {
        try {
            A7.m.f(yVar, "peerSettings");
            if (this.o) {
                throw new IOException("closed");
            }
            int i7 = this.f17593n;
            int i10 = yVar.f17598a;
            if ((i10 & 32) != 0) {
                i7 = yVar.f17599b[5];
            }
            this.f17593n = i7;
            if (((i10 & 2) != 0 ? yVar.f17599b[1] : -1) != -1) {
                c cVar = this.f17594p;
                int i11 = (i10 & 2) != 0 ? yVar.f17599b[1] : -1;
                cVar.getClass();
                int iMin = Math.min(i11, 16384);
                int i12 = cVar.f17511e;
                if (i12 != iMin) {
                    if (iMin < i12) {
                        cVar.c = Math.min(cVar.c, iMin);
                    }
                    cVar.d = true;
                    cVar.f17511e = iMin;
                    int i13 = cVar.f17515i;
                    if (iMin < i13) {
                        if (iMin == 0) {
                            b[] bVarArr = cVar.f17512f;
                            AbstractC1996k.J(bVarArr, null, 0, bVarArr.length);
                            cVar.f17513g = cVar.f17512f.length - 1;
                            cVar.f17514h = 0;
                            cVar.f17515i = 0;
                        } else {
                            cVar.a(i13 - iMin);
                        }
                    }
                }
            }
            i(0, 0, 4, 1);
            this.l.flush();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.o = true;
        this.l.close();
    }

    public final synchronized void flush() {
        if (this.o) {
            throw new IOException("closed");
        }
        this.l.flush();
    }

    public final synchronized void g(boolean z6, int i7, C2319i c2319i, int i10) {
        if (this.o) {
            throw new IOException("closed");
        }
        i(i7, i10, 0, z6 ? 1 : 0);
        if (i10 > 0) {
            A7.m.c(c2319i);
            this.l.t(c2319i, i10);
        }
    }

    public final void i(int i7, int i10, int i11, int i12) {
        Level level = Level.FINE;
        Logger logger = f17591q;
        if (logger.isLoggable(level)) {
            logger.fine(e.a(false, i7, i10, i11, i12));
        }
        if (i10 > this.f17593n) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.f17593n + ": " + i10).toString());
        }
        if ((Integer.MIN_VALUE & i7) != 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "reserved bit set: ").toString());
        }
        byte[] bArr = d9.b.f13645a;
        C c = this.l;
        A7.m.f(c, "<this>");
        c.s((i10 >>> 16) & 255);
        c.s((i10 >>> 8) & 255);
        c.s(i10 & 255);
        c.s(i11 & 255);
        c.s(i12 & 255);
        c.g(i7 & Integer.MAX_VALUE);
    }

    public final synchronized void j(int i7, int i10, byte[] bArr) {
        A7.k.p(i10, "errorCode");
        if (this.o) {
            throw new IOException("closed");
        }
        if (AbstractC2641j.c(i10) == -1) {
            throw new IllegalArgumentException("errorCode.httpCode == -1");
        }
        i(0, bArr.length + 8, 7, 0);
        this.l.g(i7);
        this.l.g(AbstractC2641j.c(i10));
        if (bArr.length != 0) {
            this.l.u(bArr);
        }
        this.l.flush();
    }

    public final synchronized void k(boolean z6, int i7, ArrayList arrayList) {
        if (this.o) {
            throw new IOException("closed");
        }
        this.f17594p.d(arrayList);
        long j3 = this.f17592m.f21932m;
        long jMin = Math.min(this.f17593n, j3);
        int i10 = j3 == jMin ? 4 : 0;
        if (z6) {
            i10 |= 1;
        }
        i(i7, (int) jMin, 1, i10);
        this.l.t(this.f17592m, jMin);
        if (j3 > jMin) {
            long j10 = j3 - jMin;
            while (j10 > 0) {
                long jMin2 = Math.min(this.f17593n, j10);
                j10 -= jMin2;
                i(i7, (int) jMin2, 9, j10 == 0 ? 4 : 0);
                this.l.t(this.f17592m, jMin2);
            }
        }
    }

    public final synchronized void m(int i7, int i10, boolean z6) {
        if (this.o) {
            throw new IOException("closed");
        }
        i(0, 8, 6, z6 ? 1 : 0);
        this.l.g(i7);
        this.l.g(i10);
        this.l.flush();
    }

    public final synchronized void o(int i7, int i10) {
        A7.k.p(i10, "errorCode");
        if (this.o) {
            throw new IOException("closed");
        }
        if (AbstractC2641j.c(i10) == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        i(i7, 4, 3, 0);
        this.l.g(AbstractC2641j.c(i10));
        this.l.flush();
    }

    public final synchronized void q(long j3, int i7) {
        if (this.o) {
            throw new IOException("closed");
        }
        if (j3 == 0 || j3 > 2147483647L) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j3).toString());
        }
        i(i7, 4, 8, 0);
        this.l.g((int) j3);
        this.l.flush();
    }
}
