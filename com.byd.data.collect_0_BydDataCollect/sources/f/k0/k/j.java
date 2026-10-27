package f.k0.k;

import f.k0.k.d;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSink;

/* JADX INFO: compiled from: Http2Writer.java */
/* JADX INFO: loaded from: classes.dex */
final class j implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Logger f5405b = Logger.getLogger(e.class.getName());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final BufferedSink f5406c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f5407d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Buffer f5408e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f5409f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f5410g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final d.b f5411h;

    j(BufferedSink bufferedSink, boolean z) {
        this.f5406c = bufferedSink;
        this.f5407d = z;
        Buffer buffer = new Buffer();
        this.f5408e = buffer;
        this.f5411h = new d.b(buffer);
        this.f5409f = 16384;
    }

    private void Z(int i2, long j) {
        while (j > 0) {
            int iMin = (int) Math.min(this.f5409f, j);
            long j2 = iMin;
            j -= j2;
            C(i2, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f5406c.h(this.f5408e, j2);
        }
    }

    private static void a0(BufferedSink bufferedSink, int i2) {
        bufferedSink.J((i2 >>> 16) & 255);
        bufferedSink.J((i2 >>> 8) & 255);
        bufferedSink.J(i2 & 255);
    }

    public void C(int i2, int i3, byte b2, byte b3) {
        Logger logger = f5405b;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(e.b(false, i2, i3, b2, b3));
        }
        int i4 = this.f5409f;
        if (i3 > i4) {
            throw e.c("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i4), Integer.valueOf(i3));
        }
        if ((Integer.MIN_VALUE & i2) != 0) {
            throw e.c("reserved bit set: %s", Integer.valueOf(i2));
        }
        a0(this.f5406c, i3);
        this.f5406c.J(b2 & 255);
        this.f5406c.J(b3 & 255);
        this.f5406c.x(i2 & Integer.MAX_VALUE);
    }

    public synchronized void R(int i2, b bVar, byte[] bArr) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        if (bVar.httpCode == -1) {
            throw e.c("errorCode.httpCode == -1", new Object[0]);
        }
        C(0, bArr.length + 8, (byte) 7, (byte) 0);
        this.f5406c.x(i2);
        this.f5406c.x(bVar.httpCode);
        if (bArr.length > 0) {
            this.f5406c.f(bArr);
        }
        this.f5406c.flush();
    }

    public synchronized void S(boolean z, int i2, List<c> list) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        this.f5411h.g(list);
        long jG0 = this.f5408e.g0();
        int iMin = (int) Math.min(this.f5409f, jG0);
        long j = iMin;
        byte b2 = jG0 == j ? (byte) 4 : (byte) 0;
        if (z) {
            b2 = (byte) (b2 | 1);
        }
        C(i2, iMin, (byte) 1, b2);
        this.f5406c.h(this.f5408e, j);
        if (jG0 > j) {
            Z(i2, jG0 - j);
        }
    }

    public int T() {
        return this.f5409f;
    }

    public synchronized void U(boolean z, int i2, int i3) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        C(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
        this.f5406c.x(i2);
        this.f5406c.x(i3);
        this.f5406c.flush();
    }

    public synchronized void V(int i2, int i3, List<c> list) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        this.f5411h.g(list);
        long jG0 = this.f5408e.g0();
        int iMin = (int) Math.min(this.f5409f - 4, jG0);
        long j = iMin;
        C(i2, iMin + 4, (byte) 5, jG0 == j ? (byte) 4 : (byte) 0);
        this.f5406c.x(i3 & Integer.MAX_VALUE);
        this.f5406c.h(this.f5408e, j);
        if (jG0 > j) {
            Z(i2, jG0 - j);
        }
    }

    public synchronized void W(int i2, b bVar) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        if (bVar.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        C(i2, 4, (byte) 3, (byte) 0);
        this.f5406c.x(bVar.httpCode);
        this.f5406c.flush();
    }

    public synchronized void X(m mVar) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        int i2 = 0;
        C(0, mVar.j() * 6, (byte) 4, (byte) 0);
        while (i2 < 10) {
            if (mVar.g(i2)) {
                this.f5406c.v(i2 == 4 ? 3 : i2 == 7 ? 4 : i2);
                this.f5406c.x(mVar.b(i2));
            }
            i2++;
        }
        this.f5406c.flush();
    }

    public synchronized void Y(int i2, long j) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            throw e.c("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
        }
        C(i2, 4, (byte) 8, (byte) 0);
        this.f5406c.x((int) j);
        this.f5406c.flush();
    }

    public synchronized void a(m mVar) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        this.f5409f = mVar.f(this.f5409f);
        if (mVar.c() != -1) {
            this.f5411h.e(mVar.c());
        }
        C(0, 0, (byte) 4, (byte) 1);
        this.f5406c.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f5410g = true;
        this.f5406c.close();
    }

    public synchronized void d() {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        if (this.f5407d) {
            Logger logger = f5405b;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(f.k0.e.p(">> CONNECTION %s", e.f5309a.k()));
            }
            this.f5406c.f(e.f5309a.w());
            this.f5406c.flush();
        }
    }

    public synchronized void flush() {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        this.f5406c.flush();
    }

    public synchronized void j(boolean z, int i2, Buffer buffer, int i3) {
        if (this.f5410g) {
            throw new IOException("closed");
        }
        y(i2, z ? (byte) 1 : (byte) 0, buffer, i3);
    }

    void y(int i2, byte b2, Buffer buffer, int i3) {
        C(i2, i3, (byte) 0, b2);
        if (i3 > 0) {
            this.f5406c.h(buffer, i3);
        }
    }
}
