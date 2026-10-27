package j9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import r9.C2319i;
import r9.C2322l;
import r9.D;
import r9.J;
import r9.L;

/* JADX INFO: loaded from: classes.dex */
public final class p implements J {
    public final D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17567m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17568n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17569p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17570q;

    public p(D d) {
        A7.m.f(d, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.l = d;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // r9.J
    public final L e() {
        return this.l.l.e();
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        int i7;
        int iQ;
        A7.m.f(c2319i, "sink");
        do {
            int i10 = this.f17569p;
            D d = this.l;
            if (i10 != 0) {
                long jP = d.p(c2319i, Math.min(j3, i10));
                if (jP == -1) {
                    return -1L;
                }
                this.f17569p -= (int) jP;
                return jP;
            }
            d.K(this.f17570q);
            this.f17570q = 0;
            if ((this.f17568n & 4) != 0) {
                return -1L;
            }
            i7 = this.o;
            int iT = d9.b.t(d);
            this.f17569p = iT;
            this.f17567m = iT;
            int iK = d.k() & 255;
            this.f17568n = d.k() & 255;
            Logger logger = q.o;
            if (logger.isLoggable(Level.FINE)) {
                C2322l c2322l = e.f17518a;
                logger.fine(e.a(true, this.o, this.f17567m, iK, this.f17568n));
            }
            iQ = d.q() & Integer.MAX_VALUE;
            this.o = iQ;
            if (iK != 9) {
                throw new IOException(iK + " != TYPE_CONTINUATION");
            }
        } while (iQ == i7);
        throw new IOException("TYPE_CONTINUATION streamId changed");
    }
}
