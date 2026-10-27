package e.d0.i;

import e.d0.i.b;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.ErrorCode;

/* JADX INFO: compiled from: Http2Writer.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Closeable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Logger f3507c = Logger.getLogger(c.class.getName());
    public boolean E3;
    public final b.C0082b F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f.d f3508d;
    public final boolean s;
    public final f.c x;
    public int y;

    public h(f.d dVar, boolean z) {
        this.f3508d = dVar;
        this.s = z;
        f.c cVar = new f.c();
        this.x = cVar;
        this.F3 = new b.C0082b(cVar);
        this.y = 16384;
    }

    public static void S(f.d dVar, int i2) throws IOException {
        dVar.A((i2 >>> 16) & 255);
        dVar.A((i2 >>> 8) & 255);
        dVar.A(i2 & 255);
    }

    public void H(int i2, int i3, byte b2, byte b3) throws IOException {
        Logger logger = f3507c;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(c.b(false, i2, i3, b2, b3));
        }
        int i4 = this.y;
        if (i3 > i4) {
            throw c.c("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i4), Integer.valueOf(i3));
        }
        if ((Integer.MIN_VALUE & i2) != 0) {
            throw c.c("reserved bit set: %s", Integer.valueOf(i2));
        }
        S(this.f3508d, i3);
        this.f3508d.A(b2 & 255);
        this.f3508d.A(b3 & 255);
        this.f3508d.r(i2 & Integer.MAX_VALUE);
    }

    public synchronized void I(int i2, ErrorCode errorCode, byte[] bArr) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw c.c("errorCode.httpCode == -1", new Object[0]);
        }
        H(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.f3508d.r(i2);
        this.f3508d.r(errorCode.httpCode);
        if (bArr.length > 0) {
            this.f3508d.f(bArr);
        }
        this.f3508d.flush();
    }

    public void J(boolean z, int i2, List<a> list) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        this.F3.g(list);
        long jY = this.x.Y();
        int iMin = (int) Math.min(this.y, jY);
        long j = iMin;
        byte b2 = jY == j ? (byte) 4 : (byte) 0;
        if (z) {
            b2 = (byte) (b2 | 1);
        }
        H(i2, iMin, (byte) 1, b2);
        this.f3508d.h(this.x, j);
        if (jY > j) {
            R(i2, jY - j);
        }
    }

    public int K() {
        return this.y;
    }

    public synchronized void L(boolean z, int i2, int i3) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        H(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.f3508d.r(i2);
        this.f3508d.r(i3);
        this.f3508d.flush();
    }

    public synchronized void M(int i2, int i3, List<a> list) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        this.F3.g(list);
        long jY = this.x.Y();
        int iMin = (int) Math.min(this.y - 4, jY);
        long j = iMin;
        H(i2, iMin + 4, (byte) 5, jY == j ? (byte) 4 : (byte) 0);
        this.f3508d.r(i3 & Integer.MAX_VALUE);
        this.f3508d.h(this.x, j);
        if (jY > j) {
            R(i2, jY - j);
        }
    }

    public synchronized void N(int i2, ErrorCode errorCode) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        H(i2, 4, (byte) 3, (byte) 0);
        this.f3508d.r(errorCode.httpCode);
        this.f3508d.flush();
    }

    public synchronized void O(l lVar) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        int i2 = 0;
        H(0, lVar.j() * 6, (byte) 4, (byte) 0);
        while (i2 < 10) {
            if (lVar.g(i2)) {
                this.f3508d.q(i2 == 4 ? 3 : i2 == 7 ? 4 : i2);
                this.f3508d.r(lVar.b(i2));
            }
            i2++;
        }
        this.f3508d.flush();
    }

    public synchronized void P(boolean z, int i2, int i3, List<a> list) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        J(z, i2, list);
    }

    public synchronized void Q(int i2, long j) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            throw c.c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        }
        H(i2, 4, (byte) 8, (byte) 0);
        this.f3508d.r((int) j);
        this.f3508d.flush();
    }

    public final void R(int i2, long j) throws IOException {
        while (j > 0) {
            int iMin = (int) Math.min(this.y, j);
            long j2 = iMin;
            j -= j2;
            H(i2, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f3508d.h(this.x, j2);
        }
    }

    public synchronized void c(l lVar) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        this.y = lVar.f(this.y);
        if (lVar.c() != -1) {
            this.F3.e(lVar.c());
        }
        H(0, 0, (byte) 4, (byte) 1);
        this.f3508d.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.E3 = true;
        this.f3508d.close();
    }

    public synchronized void d() throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        if (this.s) {
            Logger logger = f3507c;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(e.d0.c.l(">> CONNECTION %s", c.f3455a.hex()));
            }
            this.f3508d.f(c.f3455a.toByteArray());
            this.f3508d.flush();
        }
    }

    public synchronized void flush() throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        this.f3508d.flush();
    }

    public synchronized void s(boolean z, int i2, f.c cVar, int i3) throws IOException {
        if (this.E3) {
            throw new IOException("closed");
        }
        v(i2, z ? (byte) 1 : (byte) 0, cVar, i3);
    }

    public void v(int i2, byte b2, f.c cVar, int i3) throws IOException {
        H(i2, i3, (byte) 0, b2);
        if (i3 > 0) {
            this.f3508d.h(cVar, i3);
        }
    }
}
