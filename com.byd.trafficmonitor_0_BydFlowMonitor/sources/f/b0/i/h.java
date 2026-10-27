package f.b0.i;

import f.b0.i.b;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: compiled from: Http2Writer.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f5240b = Logger.getLogger(c.class.getName());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.d f5241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f5242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g.c f5243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5244f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5245g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final b.C0102b f5246h;

    public h(g.d dVar, boolean z) {
        this.f5241c = dVar;
        this.f5242d = z;
        g.c cVar = new g.c();
        this.f5243e = cVar;
        this.f5246h = new b.C0102b(cVar);
        this.f5244f = 16384;
    }

    public static void b0(g.d dVar, int i2) throws IOException {
        dVar.E((i2 >>> 16) & 255);
        dVar.E((i2 >>> 8) & 255);
        dVar.E(i2 & 255);
    }

    public synchronized void O(boolean z, int i2, g.c cVar, int i3) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        P(i2, z ? (byte) 1 : (byte) 0, cVar, i3);
    }

    public void P(int i2, byte b2, g.c cVar, int i3) throws IOException {
        Q(i2, i3, (byte) 0, b2);
        if (i3 > 0) {
            this.f5241c.g(cVar, i3);
        }
    }

    public void Q(int i2, int i3, byte b2, byte b3) throws IOException {
        Logger logger = f5240b;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(c.b(false, i2, i3, b2, b3));
        }
        int i4 = this.f5244f;
        if (i3 > i4) {
            throw c.c("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i4), Integer.valueOf(i3));
        }
        if ((Integer.MIN_VALUE & i2) != 0) {
            throw c.c("reserved bit set: %s", Integer.valueOf(i2));
        }
        b0(this.f5241c, i3);
        this.f5241c.E(b2 & 255);
        this.f5241c.E(b3 & 255);
        this.f5241c.u(i2 & Integer.MAX_VALUE);
    }

    public synchronized void R(int i2, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw c.c("errorCode.httpCode == -1", new Object[0]);
        }
        Q(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.f5241c.u(i2);
        this.f5241c.u(errorCode.httpCode);
        if (bArr.length > 0) {
            this.f5241c.c(bArr);
        }
        this.f5241c.flush();
    }

    public void S(boolean z, int i2, List<a> list) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        this.f5246h.g(list);
        long jF0 = this.f5243e.f0();
        int iMin = (int) Math.min(this.f5244f, jF0);
        long j = iMin;
        byte b2 = jF0 == j ? (byte) 4 : (byte) 0;
        if (z) {
            b2 = (byte) (b2 | 1);
        }
        Q(i2, iMin, (byte) 1, b2);
        this.f5241c.g(this.f5243e, j);
        if (jF0 > j) {
            a0(i2, jF0 - j);
        }
    }

    public int T() {
        return this.f5244f;
    }

    public synchronized void U(boolean z, int i2, int i3) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        Q(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.f5241c.u(i2);
        this.f5241c.u(i3);
        this.f5241c.flush();
    }

    public synchronized void V(int i2, int i3, List<a> list) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        this.f5246h.g(list);
        long jF0 = this.f5243e.f0();
        int iMin = (int) Math.min(this.f5244f - 4, jF0);
        long j = iMin;
        Q(i2, iMin + 4, (byte) 5, jF0 == j ? (byte) 4 : (byte) 0);
        this.f5241c.u(i3 & Integer.MAX_VALUE);
        this.f5241c.g(this.f5243e, j);
        if (jF0 > j) {
            a0(i2, jF0 - j);
        }
    }

    public synchronized void W(int i2, ErrorCode errorCode) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        Q(i2, 4, (byte) 3, (byte) 0);
        this.f5241c.u(errorCode.httpCode);
        this.f5241c.flush();
    }

    public synchronized void X(l lVar) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        int i2 = 0;
        Q(0, lVar.j() * 6, (byte) 4, (byte) 0);
        while (i2 < 10) {
            if (lVar.g(i2)) {
                this.f5241c.q(i2 == 4 ? 3 : i2 == 7 ? 4 : i2);
                this.f5241c.u(lVar.b(i2));
            }
            i2++;
        }
        this.f5241c.flush();
    }

    public synchronized void Y(boolean z, int i2, int i3, List<a> list) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        S(z, i2, list);
    }

    public synchronized void Z(int i2, long j) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            throw c.c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        }
        Q(i2, 4, (byte) 8, (byte) 0);
        this.f5241c.u((int) j);
        this.f5241c.flush();
    }

    public final void a0(int i2, long j) throws IOException {
        while (j > 0) {
            int iMin = (int) Math.min(this.f5244f, j);
            long j2 = iMin;
            j -= j2;
            Q(i2, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f5241c.g(this.f5243e, j2);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.f5245g = true;
        this.f5241c.close();
    }

    public synchronized void flush() throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        this.f5241c.flush();
    }

    public synchronized void w(l lVar) throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        this.f5244f = lVar.f(this.f5244f);
        if (lVar.c() != -1) {
            this.f5246h.e(lVar.c());
        }
        Q(0, 0, (byte) 4, (byte) 1);
        this.f5241c.flush();
    }

    public synchronized void z() throws IOException {
        if (this.f5245g) {
            throw new IOException("closed");
        }
        if (this.f5242d) {
            Logger logger = f5240b;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(f.b0.c.l(">> CONNECTION %s", c.f5147a.hex()));
            }
            this.f5241c.c(c.f5147a.toByteArray());
            this.f5241c.flush();
        }
    }
}
