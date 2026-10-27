package k6;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import k6.d;
import p6.a0;
import p6.b0;

/* JADX INFO: compiled from: Http2Reader.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Closeable {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final Logger f7423j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final a f7424k = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b f7425e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final d.a f7426g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final p6.g f7427h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final boolean f7428i;

    /* JADX INFO: compiled from: Http2Reader.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final Logger a() {
            return h.f7423j;
        }

        public final int b(int i7, int i8, int i9) throws IOException {
            if ((i8 & 8) != 0) {
                i7--;
            }
            if (i9 <= i7) {
                return i7 - i9;
            }
            throw new IOException("PROTOCOL_ERROR padding " + i9 + " > remaining length " + i7);
        }
    }

    /* JADX INFO: compiled from: Http2Reader.kt */
    public static final class b implements a0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f7429e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f7430g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f7431h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private int f7432i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private int f7433j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private final p6.g f7434k;

        public b(p6.g source) {
            kotlin.jvm.internal.l.e(source, "source");
            this.f7434k = source;
        }

        private final void z() throws IOException {
            int i7 = this.f7431h;
            int iE = d6.b.E(this.f7434k);
            this.f7432i = iE;
            this.f7429e = iE;
            int iB = d6.b.b(this.f7434k.Y(), 255);
            this.f7430g = d6.b.b(this.f7434k.Y(), 255);
            a aVar = h.f7424k;
            if (aVar.a().isLoggable(Level.FINE)) {
                aVar.a().fine(e.f7314e.c(true, this.f7431h, this.f7429e, iB, this.f7430g));
            }
            int iX = this.f7434k.x() & Integer.MAX_VALUE;
            this.f7431h = iX;
            if (iB == 9) {
                if (iX != i7) {
                    throw new IOException("TYPE_CONTINUATION streamId changed");
                }
            } else {
                throw new IOException(iB + " != TYPE_CONTINUATION");
            }
        }

        public final void A(int i7) {
            this.f7430g = i7;
        }

        public final void G(int i7) {
            this.f7432i = i7;
        }

        public final void I(int i7) {
            this.f7429e = i7;
        }

        public final void K(int i7) {
            this.f7433j = i7;
        }

        public final void N(int i7) {
            this.f7431h = i7;
        }

        @Override // p6.a0
        public b0 c() {
            return this.f7434k.c();
        }

        @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // p6.a0
        public long i(p6.e sink, long j7) throws IOException {
            kotlin.jvm.internal.l.e(sink, "sink");
            while (true) {
                int i7 = this.f7432i;
                if (i7 != 0) {
                    long jI = this.f7434k.i(sink, Math.min(j7, i7));
                    if (jI == -1) {
                        return -1L;
                    }
                    this.f7432i -= (int) jI;
                    return jI;
                }
                this.f7434k.a(this.f7433j);
                this.f7433j = 0;
                if ((this.f7430g & 4) != 0) {
                    return -1L;
                }
                z();
            }
        }

        public final int m() {
            return this.f7432i;
        }
    }

    /* JADX INFO: compiled from: Http2Reader.kt */
    public interface c {
        void a(boolean z6, int i7, int i8, List<k6.c> list);

        void b(int i7, k6.b bVar);

        void d();

        void e(boolean z6, int i7, p6.g gVar, int i8);

        void f(int i7, long j7);

        void g(int i7, k6.b bVar, p6.h hVar);

        void h(int i7, int i8, List<k6.c> list);

        void i(boolean z6, int i7, int i8);

        void j(int i7, int i8, int i9, boolean z6);

        void k(boolean z6, m mVar);
    }

    static {
        Logger logger = Logger.getLogger(e.class.getName());
        kotlin.jvm.internal.l.d(logger, "Logger.getLogger(Http2::class.java.name)");
        f7423j = logger;
    }

    public h(p6.g source, boolean z6) {
        kotlin.jvm.internal.l.e(source, "source");
        this.f7427h = source;
        this.f7428i = z6;
        b bVar = new b(source);
        this.f7425e = bVar;
        this.f7426g = new d.a(bVar, 4096, 0, 4, null);
    }

    private final void G(c cVar, int i7, int i8, int i9) throws IOException {
        if (i9 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        boolean z6 = (i8 & 1) != 0;
        if ((i8 & 32) != 0) {
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        int iB = (i8 & 8) != 0 ? d6.b.b(this.f7427h.Y(), 255) : 0;
        cVar.e(z6, i9, this.f7427h, f7424k.b(i7, i8, iB));
        this.f7427h.a(iB);
    }

    private final void I(c cVar, int i7, int i8, int i9) throws IOException {
        if (i7 < 8) {
            throw new IOException("TYPE_GOAWAY length < 8: " + i7);
        }
        if (i9 != 0) {
            throw new IOException("TYPE_GOAWAY streamId != 0");
        }
        int iX = this.f7427h.x();
        int iX2 = this.f7427h.x();
        int i10 = i7 - 8;
        k6.b bVarA = k6.b.Companion.a(iX2);
        if (bVarA == null) {
            throw new IOException("TYPE_GOAWAY unexpected error code: " + iX2);
        }
        p6.h hVarN = p6.h.f8869i;
        if (i10 > 0) {
            hVarN = this.f7427h.n(i10);
        }
        cVar.g(iX, bVarA, hVarN);
    }

    private final List<k6.c> K(int i7, int i8, int i9, int i10) throws IOException {
        this.f7425e.G(i7);
        b bVar = this.f7425e;
        bVar.I(bVar.m());
        this.f7425e.K(i8);
        this.f7425e.A(i9);
        this.f7425e.N(i10);
        this.f7426g.k();
        return this.f7426g.e();
    }

    private final void N(c cVar, int i7, int i8, int i9) throws IOException {
        if (i9 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        boolean z6 = (i8 & 1) != 0;
        int iB = (i8 & 8) != 0 ? d6.b.b(this.f7427h.Y(), 255) : 0;
        if ((i8 & 32) != 0) {
            U(cVar, i9);
            i7 -= 5;
        }
        cVar.a(z6, i9, -1, K(f7424k.b(i7, i8, iB), iB, i8, i9));
    }

    private final void P(c cVar, int i7, int i8, int i9) throws IOException {
        if (i7 != 8) {
            throw new IOException("TYPE_PING length != 8: " + i7);
        }
        if (i9 != 0) {
            throw new IOException("TYPE_PING streamId != 0");
        }
        cVar.i((i8 & 1) != 0, this.f7427h.x(), this.f7427h.x());
    }

    private final void U(c cVar, int i7) {
        int iX = this.f7427h.x();
        cVar.j(i7, iX & Integer.MAX_VALUE, d6.b.b(this.f7427h.Y(), 255) + 1, (((int) 2147483648L) & iX) != 0);
    }

    private final void V(c cVar, int i7, int i8, int i9) throws IOException {
        if (i7 == 5) {
            if (i9 == 0) {
                throw new IOException("TYPE_PRIORITY streamId == 0");
            }
            U(cVar, i9);
        } else {
            throw new IOException("TYPE_PRIORITY length: " + i7 + " != 5");
        }
    }

    private final void Z(c cVar, int i7, int i8, int i9) throws IOException {
        if (i9 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        int iB = (i8 & 8) != 0 ? d6.b.b(this.f7427h.Y(), 255) : 0;
        cVar.h(i9, this.f7427h.x() & Integer.MAX_VALUE, K(f7424k.b(i7 - 4, i8, iB), iB, i8, i9));
    }

    private final void a0(c cVar, int i7, int i8, int i9) throws IOException {
        if (i7 != 4) {
            throw new IOException("TYPE_RST_STREAM length: " + i7 + " != 4");
        }
        if (i9 == 0) {
            throw new IOException("TYPE_RST_STREAM streamId == 0");
        }
        int iX = this.f7427h.x();
        k6.b bVarA = k6.b.Companion.a(iX);
        if (bVarA != null) {
            cVar.b(i9, bVarA);
            return;
        }
        throw new IOException("TYPE_RST_STREAM unexpected error code: " + iX);
    }

    private final void b0(c cVar, int i7, int i8, int i9) throws IOException {
        int iX;
        if (i9 != 0) {
            throw new IOException("TYPE_SETTINGS streamId != 0");
        }
        if ((i8 & 1) != 0) {
            if (i7 != 0) {
                throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
            }
            cVar.d();
            return;
        }
        if (i7 % 6 != 0) {
            throw new IOException("TYPE_SETTINGS length % 6 != 0: " + i7);
        }
        m mVar = new m();
        v5.a aVarH = v5.h.h(v5.h.i(0, i7), 6);
        int iC = aVarH.c();
        int iD = aVarH.d();
        int iE = aVarH.e();
        if (iE < 0 ? iC >= iD : iC <= iD) {
            while (true) {
                int iC2 = d6.b.c(this.f7427h.s(), 65535);
                iX = this.f7427h.x();
                if (iC2 != 2) {
                    if (iC2 == 3) {
                        iC2 = 4;
                    } else if (iC2 == 4) {
                        iC2 = 7;
                        if (iX < 0) {
                            throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                        }
                    } else if (iC2 == 5 && (iX < 16384 || iX > 16777215)) {
                        break;
                    }
                } else if (iX != 0 && iX != 1) {
                    throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                }
                mVar.h(iC2, iX);
                if (iC == iD) {
                    break;
                } else {
                    iC += iE;
                }
            }
            throw new IOException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: " + iX);
        }
        cVar.k(false, mVar);
    }

    private final void c0(c cVar, int i7, int i8, int i9) throws IOException {
        if (i7 != 4) {
            throw new IOException("TYPE_WINDOW_UPDATE length !=4: " + i7);
        }
        long jD = d6.b.d(this.f7427h.x(), 2147483647L);
        if (jD == 0) {
            throw new IOException("windowSizeIncrement was 0");
        }
        cVar.f(i9, jD);
    }

    public final void A(c handler) throws IOException {
        kotlin.jvm.internal.l.e(handler, "handler");
        if (this.f7428i) {
            if (!z(true, handler)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        p6.g gVar = this.f7427h;
        p6.h hVar = e.f7310a;
        p6.h hVarN = gVar.n(hVar.r());
        Logger logger = f7423j;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(d6.b.p("<< CONNECTION " + hVarN.i(), new Object[0]));
        }
        if (!kotlin.jvm.internal.l.a(hVar, hVarN)) {
            throw new IOException("Expected a connection header but was " + hVarN.u());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f7427h.close();
    }

    public final boolean z(boolean z6, c handler) throws IOException {
        kotlin.jvm.internal.l.e(handler, "handler");
        try {
            this.f7427h.F(9L);
            int iE = d6.b.E(this.f7427h);
            if (iE > 16384) {
                throw new IOException("FRAME_SIZE_ERROR: " + iE);
            }
            int iB = d6.b.b(this.f7427h.Y(), 255);
            int iB2 = d6.b.b(this.f7427h.Y(), 255);
            int iX = this.f7427h.x() & Integer.MAX_VALUE;
            Logger logger = f7423j;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(e.f7314e.c(true, iX, iE, iB, iB2));
            }
            if (z6 && iB != 4) {
                throw new IOException("Expected a SETTINGS frame but was " + e.f7314e.b(iB));
            }
            switch (iB) {
                case 0:
                    G(handler, iE, iB2, iX);
                    return true;
                case 1:
                    N(handler, iE, iB2, iX);
                    return true;
                case 2:
                    V(handler, iE, iB2, iX);
                    return true;
                case 3:
                    a0(handler, iE, iB2, iX);
                    return true;
                case 4:
                    b0(handler, iE, iB2, iX);
                    return true;
                case 5:
                    Z(handler, iE, iB2, iX);
                    return true;
                case 6:
                    P(handler, iE, iB2, iX);
                    return true;
                case 7:
                    I(handler, iE, iB2, iX);
                    return true;
                case 8:
                    c0(handler, iE, iB2, iX);
                    return true;
                default:
                    this.f7427h.a(iE);
                    return true;
            }
        } catch (EOFException unused) {
            return false;
        }
    }
}
