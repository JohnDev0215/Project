package f.b0.i;

import f.b0.i.b;
import g.p;
import g.q;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.http2.ErrorCode;
import okio.ByteString;

/* JADX INFO: compiled from: Http2Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f5210b = Logger.getLogger(c.class.getName());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.e f5211c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f5212d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5213e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b.a f5214f;

    /* JADX INFO: compiled from: Http2Reader.java */
    public static final class a implements p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g.e f5215b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5216c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public byte f5217d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5218e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5219f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public short f5220g;

        public a(g.e eVar) {
            this.f5215b = eVar;
        }

        @Override // g.p
        public q b() {
            return this.f5215b.b();
        }

        @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // g.p
        public long m(g.c cVar, long j) throws IOException {
            while (true) {
                int i2 = this.f5219f;
                if (i2 != 0) {
                    long jM = this.f5215b.m(cVar, Math.min(j, i2));
                    if (jM == -1) {
                        return -1L;
                    }
                    this.f5219f = (int) (((long) this.f5219f) - jM);
                    return jM;
                }
                this.f5215b.o(this.f5220g);
                this.f5220g = (short) 0;
                if ((this.f5217d & 4) != 0) {
                    return -1L;
                }
                w();
            }
        }

        public final void w() throws IOException {
            int i2 = this.f5218e;
            int iT = f.T(this.f5215b);
            this.f5219f = iT;
            this.f5216c = iT;
            byte bN = (byte) (this.f5215b.N() & 255);
            this.f5217d = (byte) (this.f5215b.N() & 255);
            Logger logger = f.f5210b;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(c.b(true, this.f5218e, this.f5216c, bN, this.f5217d));
            }
            int iT2 = this.f5215b.t() & Integer.MAX_VALUE;
            this.f5218e = iT2;
            if (bN != 9) {
                throw c.d("%s != TYPE_CONTINUATION", Byte.valueOf(bN));
            }
            if (iT2 != i2) {
                throw c.d("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    /* JADX INFO: compiled from: Http2Reader.java */
    public interface b {
        void a(boolean z, int i2, int i3, List<f.b0.i.a> list);

        void b();

        void c(int i2, long j);

        void d(boolean z, l lVar);

        void e(int i2, int i3, List<f.b0.i.a> list) throws IOException;

        void f(boolean z, int i2, g.e eVar, int i3) throws IOException;

        void g(boolean z, int i2, int i3);

        void h(int i2, ErrorCode errorCode, ByteString byteString);

        void i(int i2, int i3, int i4, boolean z);

        void j(int i2, ErrorCode errorCode);
    }

    public f(g.e eVar, boolean z) {
        this.f5211c = eVar;
        this.f5213e = z;
        a aVar = new a(eVar);
        this.f5212d = aVar;
        this.f5214f = new b.a(4096, aVar);
    }

    public static int T(g.e eVar) throws IOException {
        return (eVar.N() & 255) | ((eVar.N() & 255) << 16) | ((eVar.N() & 255) << 8);
    }

    public static int w(int i2, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i2--;
        }
        if (s <= i2) {
            return (short) (i2 - s);
        }
        throw c.d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i2));
    }

    public void O(b bVar) throws IOException {
        if (this.f5213e) {
            if (!z(true, bVar)) {
                throw c.d("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        g.e eVar = this.f5211c;
        ByteString byteString = c.f5147a;
        ByteString byteStringN = eVar.n(byteString.size());
        Logger logger = f5210b;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(f.b0.c.l("<< CONNECTION %s", byteStringN.hex()));
        }
        if (!byteString.equals(byteStringN)) {
            throw c.d("Expected a connection header but was %s", byteStringN.utf8());
        }
    }

    public final void P(b bVar, int i2, byte b2, int i3) throws IOException {
        boolean z = (b2 & 1) != 0;
        if ((b2 & 32) != 0) {
            throw c.d("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        short sN = (b2 & 8) != 0 ? (short) (this.f5211c.N() & 255) : (short) 0;
        bVar.f(z, i3, this.f5211c, w(i2, b2, sN));
        this.f5211c.o(sN);
    }

    public final void Q(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 < 8) {
            throw c.d("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i2));
        }
        if (i3 != 0) {
            throw c.d("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        int iT = this.f5211c.t();
        int iT2 = this.f5211c.t();
        int i4 = i2 - 8;
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(iT2);
        if (errorCodeFromHttp2 == null) {
            throw c.d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(iT2));
        }
        ByteString byteStringN = ByteString.EMPTY;
        if (i4 > 0) {
            byteStringN = this.f5211c.n(i4);
        }
        bVar.h(iT, errorCodeFromHttp2, byteStringN);
    }

    public final List<f.b0.i.a> R(int i2, short s, byte b2, int i3) throws IOException {
        a aVar = this.f5212d;
        aVar.f5219f = i2;
        aVar.f5216c = i2;
        aVar.f5220g = s;
        aVar.f5217d = b2;
        aVar.f5218e = i3;
        this.f5214f.k();
        return this.f5214f.e();
    }

    public final void S(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw c.d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        short sN = (b2 & 8) != 0 ? (short) (this.f5211c.N() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            V(bVar, i3);
            i2 -= 5;
        }
        bVar.a(z, i3, -1, R(w(i2, b2, sN), sN, b2, i3));
    }

    public final void U(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 8) {
            throw c.d("TYPE_PING length != 8: %s", Integer.valueOf(i2));
        }
        if (i3 != 0) {
            throw c.d("TYPE_PING streamId != 0", new Object[0]);
        }
        bVar.g((b2 & 1) != 0, this.f5211c.t(), this.f5211c.t());
    }

    public final void V(b bVar, int i2) throws IOException {
        int iT = this.f5211c.t();
        bVar.i(i2, iT & Integer.MAX_VALUE, (this.f5211c.N() & 255) + 1, (Integer.MIN_VALUE & iT) != 0);
    }

    public final void W(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 5) {
            throw c.d("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i2));
        }
        if (i3 == 0) {
            throw c.d("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        V(bVar, i3);
    }

    public final void X(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw c.d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        short sN = (b2 & 8) != 0 ? (short) (this.f5211c.N() & 255) : (short) 0;
        bVar.e(i3, this.f5211c.t() & Integer.MAX_VALUE, R(w(i2 - 4, b2, sN), sN, b2, i3));
    }

    public final void Y(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 4) {
            throw c.d("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i2));
        }
        if (i3 == 0) {
            throw c.d("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        int iT = this.f5211c.t();
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(iT);
        if (errorCodeFromHttp2 == null) {
            throw c.d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(iT));
        }
        bVar.j(i3, errorCodeFromHttp2);
    }

    public final void Z(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 != 0) {
            throw c.d("TYPE_SETTINGS streamId != 0", new Object[0]);
        }
        if ((b2 & 1) != 0) {
            if (i2 != 0) {
                throw c.d("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            bVar.b();
            return;
        }
        if (i2 % 6 != 0) {
            throw c.d("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i2));
        }
        l lVar = new l();
        for (int i4 = 0; i4 < i2; i4 += 6) {
            short sP = this.f5211c.p();
            int iT = this.f5211c.t();
            if (sP == 2) {
                if (iT != 0 && iT != 1) {
                    throw c.d("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                }
            } else if (sP == 3) {
                sP = 4;
            } else if (sP == 4) {
                sP = 7;
                if (iT < 0) {
                    throw c.d("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                }
            } else if (sP == 5 && (iT < 16384 || iT > 16777215)) {
                throw c.d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(iT));
            }
            lVar.i(sP, iT);
        }
        bVar.d(false, lVar);
    }

    public final void a0(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 4) {
            throw c.d("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i2));
        }
        long jT = ((long) this.f5211c.t()) & 2147483647L;
        if (jT == 0) {
            throw c.d("windowSizeIncrement was 0", Long.valueOf(jT));
        }
        bVar.c(i3, jT);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5211c.close();
    }

    public boolean z(boolean z, b bVar) throws IOException {
        try {
            this.f5211c.y(9L);
            int iT = T(this.f5211c);
            if (iT < 0 || iT > 16384) {
                throw c.d("FRAME_SIZE_ERROR: %s", Integer.valueOf(iT));
            }
            byte bN = (byte) (this.f5211c.N() & 255);
            if (z && bN != 4) {
                throw c.d("Expected a SETTINGS frame but was %s", Byte.valueOf(bN));
            }
            byte bN2 = (byte) (this.f5211c.N() & 255);
            int iT2 = this.f5211c.t() & Integer.MAX_VALUE;
            Logger logger = f5210b;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(c.b(true, iT2, iT, bN, bN2));
            }
            switch (bN) {
                case 0:
                    P(bVar, iT, bN2, iT2);
                    return true;
                case 1:
                    S(bVar, iT, bN2, iT2);
                    return true;
                case 2:
                    W(bVar, iT, bN2, iT2);
                    return true;
                case 3:
                    Y(bVar, iT, bN2, iT2);
                    return true;
                case 4:
                    Z(bVar, iT, bN2, iT2);
                    return true;
                case 5:
                    X(bVar, iT, bN2, iT2);
                    return true;
                case 6:
                    U(bVar, iT, bN2, iT2);
                    return true;
                case 7:
                    Q(bVar, iT, bN2, iT2);
                    return true;
                case 8:
                    a0(bVar, iT, bN2, iT2);
                    return true;
                default:
                    this.f5211c.o(iT);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }
}
