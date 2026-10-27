package k6;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import k6.d;

/* JADX INFO: compiled from: Http2Writer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final p6.e f7465e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f7466g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f7467h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final d.b f7468i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final p6.f f7469j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final boolean f7470k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f7464m = new a(null);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final Logger f7463l = Logger.getLogger(e.class.getName());

    /* JADX INFO: compiled from: Http2Writer.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public j(p6.f sink, boolean z6) {
        kotlin.jvm.internal.l.e(sink, "sink");
        this.f7469j = sink;
        this.f7470k = z6;
        p6.e eVar = new p6.e();
        this.f7465e = eVar;
        this.f7466g = 16384;
        this.f7468i = new d.b(0, false, eVar, 3, null);
    }

    private final void c0(int i7, long j7) {
        while (j7 > 0) {
            long jMin = Math.min(this.f7466g, j7);
            j7 -= jMin;
            I(i7, (int) jMin, 9, j7 == 0 ? 4 : 0);
            this.f7469j.l(this.f7465e, jMin);
        }
    }

    public final synchronized void A(boolean z6, int i7, p6.e eVar, int i8) {
        if (this.f7467h) {
            throw new IOException("closed");
        }
        G(i7, z6 ? 1 : 0, eVar, i8);
    }

    public final void G(int i7, int i8, p6.e eVar, int i9) {
        I(i7, i9, 0, i8);
        if (i9 > 0) {
            p6.f fVar = this.f7469j;
            kotlin.jvm.internal.l.c(eVar);
            fVar.l(eVar, i9);
        }
    }

    public final void I(int i7, int i8, int i9, int i10) {
        Logger logger = f7463l;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(e.f7314e.c(false, i7, i8, i9, i10));
        }
        if (!(i8 <= this.f7466g)) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.f7466g + ": " + i8).toString());
        }
        if (!((((int) 2147483648L) & i7) == 0)) {
            throw new IllegalArgumentException(("reserved bit set: " + i7).toString());
        }
        d6.b.T(this.f7469j, i8);
        this.f7469j.M(i9 & 255);
        this.f7469j.M(i10 & 255);
        this.f7469j.y(i7 & Integer.MAX_VALUE);
    }

    public final synchronized void K(int i7, b errorCode, byte[] debugData) {
        kotlin.jvm.internal.l.e(errorCode, "errorCode");
        kotlin.jvm.internal.l.e(debugData, "debugData");
        if (this.f7467h) {
            throw new IOException("closed");
        }
        if (!(errorCode.getHttpCode() != -1)) {
            throw new IllegalArgumentException("errorCode.httpCode == -1".toString());
        }
        I(0, debugData.length + 8, 7, 0);
        this.f7469j.y(i7);
        this.f7469j.y(errorCode.getHttpCode());
        if (!(debugData.length == 0)) {
            this.f7469j.d(debugData);
        }
        this.f7469j.flush();
    }

    public final synchronized void N(boolean z6, int i7, List<c> headerBlock) {
        kotlin.jvm.internal.l.e(headerBlock, "headerBlock");
        if (this.f7467h) {
            throw new IOException("closed");
        }
        this.f7468i.g(headerBlock);
        long jJ0 = this.f7465e.j0();
        long jMin = Math.min(this.f7466g, jJ0);
        int i8 = jJ0 == jMin ? 4 : 0;
        if (z6) {
            i8 |= 1;
        }
        I(i7, (int) jMin, 1, i8);
        this.f7469j.l(this.f7465e, jMin);
        if (jJ0 > jMin) {
            c0(i7, jJ0 - jMin);
        }
    }

    public final int P() {
        return this.f7466g;
    }

    public final synchronized void U(boolean z6, int i7, int i8) {
        if (this.f7467h) {
            throw new IOException("closed");
        }
        I(0, 8, 6, z6 ? 1 : 0);
        this.f7469j.y(i7);
        this.f7469j.y(i8);
        this.f7469j.flush();
    }

    public final synchronized void V(int i7, int i8, List<c> requestHeaders) {
        kotlin.jvm.internal.l.e(requestHeaders, "requestHeaders");
        if (this.f7467h) {
            throw new IOException("closed");
        }
        this.f7468i.g(requestHeaders);
        long jJ0 = this.f7465e.j0();
        int iMin = (int) Math.min(((long) this.f7466g) - 4, jJ0);
        long j7 = iMin;
        I(i7, iMin + 4, 5, jJ0 == j7 ? 4 : 0);
        this.f7469j.y(i8 & Integer.MAX_VALUE);
        this.f7469j.l(this.f7465e, j7);
        if (jJ0 > j7) {
            c0(i7, jJ0 - j7);
        }
    }

    public final synchronized void Z(int i7, b errorCode) {
        kotlin.jvm.internal.l.e(errorCode, "errorCode");
        if (this.f7467h) {
            throw new IOException("closed");
        }
        if (!(errorCode.getHttpCode() != -1)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        I(i7, 4, 3, 0);
        this.f7469j.y(errorCode.getHttpCode());
        this.f7469j.flush();
    }

    public final synchronized void a0(m settings) {
        kotlin.jvm.internal.l.e(settings, "settings");
        if (this.f7467h) {
            throw new IOException("closed");
        }
        int i7 = 0;
        I(0, settings.i() * 6, 4, 0);
        while (i7 < 10) {
            if (settings.f(i7)) {
                this.f7469j.t(i7 != 4 ? i7 != 7 ? i7 : 4 : 3);
                this.f7469j.y(settings.a(i7));
            }
            i7++;
        }
        this.f7469j.flush();
    }

    public final synchronized void b0(int i7, long j7) {
        if (this.f7467h) {
            throw new IOException("closed");
        }
        if (!(j7 != 0 && j7 <= 2147483647L)) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j7).toString());
        }
        I(i7, 4, 8, 0);
        this.f7469j.y((int) j7);
        this.f7469j.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f7467h = true;
        this.f7469j.close();
    }

    public final synchronized void flush() {
        if (this.f7467h) {
            throw new IOException("closed");
        }
        this.f7469j.flush();
    }

    public final synchronized void m(m peerSettings) {
        kotlin.jvm.internal.l.e(peerSettings, "peerSettings");
        if (this.f7467h) {
            throw new IOException("closed");
        }
        this.f7466g = peerSettings.e(this.f7466g);
        if (peerSettings.b() != -1) {
            this.f7468i.e(peerSettings.b());
        }
        I(0, 0, 4, 1);
        this.f7469j.flush();
    }

    public final synchronized void z() {
        if (this.f7467h) {
            throw new IOException("closed");
        }
        if (this.f7470k) {
            Logger logger = f7463l;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(d6.b.p(">> CONNECTION " + e.f7310a.i(), new Object[0]));
            }
            this.f7469j.Q(e.f7310a);
            this.f7469j.flush();
        }
    }
}
