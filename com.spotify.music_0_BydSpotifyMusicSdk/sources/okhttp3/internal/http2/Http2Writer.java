package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import p.fo;
import p.io;
import p.rb;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Http2Writer implements Closeable {
    public static final Logger o;
    public final io c;
    public final boolean e;
    public final fo k;
    public int l;
    public boolean m;
    public final Hpack.Writer n;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
        o = Logger.getLogger(Http2.class.getName());
    }

    public Http2Writer(io ioVar, boolean z) {
        this.c = ioVar;
        this.e = z;
        fo foVar = new fo();
        this.k = foVar;
        this.l = 16384;
        this.n = new Hpack.Writer(foVar);
    }

    public final synchronized void E(int i, int i2, boolean z) {
        if (this.m) {
            throw new IOException("closed");
        }
        i(0, 8, 6, z ? 1 : 0);
        this.c.I(i);
        this.c.I(i2);
        this.c.flush();
    }

    public final synchronized void J(int i, ErrorCode errorCode) {
        v03.j(errorCode, "errorCode");
        if (this.m) {
            throw new IOException("closed");
        }
        if (errorCode.a() == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        i(i, 4, 3, 0);
        this.c.I(errorCode.a());
        this.c.flush();
    }

    public final synchronized void X(int i, long j) {
        if (this.m) {
            throw new IOException("closed");
        }
        if (j == 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j).toString());
        }
        i(i, 4, 8, 0);
        this.c.I((int) j);
        this.c.flush();
    }

    public final synchronized void b(Settings settings) {
        try {
            v03.j(settings, "peerSettings");
            if (this.m) {
                throw new IOException("closed");
            }
            int i = this.l;
            int i2 = settings.a;
            if ((i2 & 32) != 0) {
                i = settings.b[5];
            }
            this.l = i;
            if (((i2 & 2) != 0 ? settings.b[1] : -1) != -1) {
                Hpack.Writer writer = this.n;
                int i3 = (i2 & 2) != 0 ? settings.b[1] : -1;
                writer.getClass();
                int iMin = Math.min(i3, 16384);
                int i4 = writer.e;
                if (i4 != iMin) {
                    if (iMin < i4) {
                        writer.c = Math.min(writer.c, iMin);
                    }
                    writer.d = true;
                    writer.e = iMin;
                    int i5 = writer.i;
                    if (iMin < i5) {
                        if (iMin == 0) {
                            rb.o0(writer.f, null);
                            writer.g = writer.f.length - 1;
                            writer.h = 0;
                            writer.i = 0;
                        } else {
                            writer.a(i5 - iMin);
                        }
                    }
                }
            }
            i(0, 0, 4, 1);
            this.c.flush();
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void c(boolean z, int i, fo foVar, int i2) {
        if (this.m) {
            throw new IOException("closed");
        }
        i(i, i2, 0, z ? 1 : 0);
        if (i2 > 0) {
            v03.h(foVar);
            this.c.Y(foVar, i2);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.m = true;
        this.c.close();
    }

    public final synchronized void flush() {
        if (this.m) {
            throw new IOException("closed");
        }
        this.c.flush();
    }

    public final void i(int i, int i2, int i3, int i4) {
        Level level = Level.FINE;
        Logger logger = o;
        if (logger.isLoggable(level)) {
            Http2.a.getClass();
            logger.fine(Http2.a(i, i2, i3, i4, false));
        }
        if (i2 > this.l) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.l + ": " + i2).toString());
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            throw new IllegalArgumentException(u4.e("reserved bit set: ", i).toString());
        }
        byte[] bArr = Util.a;
        io ioVar = this.c;
        v03.j(ioVar, "<this>");
        ioVar.R((i2 >>> 16) & 255);
        ioVar.R((i2 >>> 8) & 255);
        ioVar.R(i2 & 255);
        ioVar.R(i3 & 255);
        ioVar.R(i4 & 255);
        ioVar.I(i & Integer.MAX_VALUE);
    }

    public final void o0(int i, long j) {
        while (j > 0) {
            long jMin = Math.min(this.l, j);
            j -= jMin;
            i(i, (int) jMin, 9, j == 0 ? 4 : 0);
            this.c.Y(this.k, jMin);
        }
    }

    public final synchronized void q(int i, ErrorCode errorCode, byte[] bArr) {
        try {
            if (this.m) {
                throw new IOException("closed");
            }
            if (errorCode.a() == -1) {
                throw new IllegalArgumentException("errorCode.httpCode == -1".toString());
            }
            i(0, bArr.length + 8, 7, 0);
            this.c.I(i);
            this.c.I(errorCode.a());
            if (!(bArr.length == 0)) {
                this.c.V(bArr);
            }
            this.c.flush();
        } catch (Throwable th) {
            throw th;
        }
    }
}
