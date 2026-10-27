package f.k0.k;

import f.k0.k.d;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: Http2Reader.java */
/* JADX INFO: loaded from: classes.dex */
final class h implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Logger f5373b = Logger.getLogger(e.class.getName());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final BufferedSource f5374c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f5375d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f5376e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final d.a f5377f;

    /* JADX INFO: compiled from: Http2Reader.java */
    static final class a implements Source {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final BufferedSource f5378b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f5379c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        byte f5380d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f5381e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f5382f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        short f5383g;

        a(BufferedSource bufferedSource) {
            this.f5378b = bufferedSource;
        }

        private void a() throws IOException {
            int i2 = this.f5381e;
            int iT = h.T(this.f5378b);
            this.f5382f = iT;
            this.f5379c = iT;
            byte bP = (byte) (this.f5378b.P() & 255);
            this.f5380d = (byte) (this.f5378b.P() & 255);
            Logger logger = h.f5373b;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(e.b(true, this.f5381e, this.f5379c, bP, this.f5380d));
            }
            int iW = this.f5378b.w() & Integer.MAX_VALUE;
            this.f5381e = iW;
            if (bP != 9) {
                throw e.d("%s != TYPE_CONTINUATION", Byte.valueOf(bP));
            }
            if (iW != i2) {
                throw e.d("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // okio.Source
        public Timeout e() {
            return this.f5378b.e();
        }

        @Override // okio.Source
        public long p(Buffer buffer, long j) throws IOException {
            while (true) {
                int i2 = this.f5382f;
                if (i2 != 0) {
                    long jP = this.f5378b.p(buffer, Math.min(j, i2));
                    if (jP == -1) {
                        return -1L;
                    }
                    this.f5382f = (int) (((long) this.f5382f) - jP);
                    return jP;
                }
                this.f5378b.s(this.f5383g);
                this.f5383g = (short) 0;
                if ((this.f5380d & 4) != 0) {
                    return -1L;
                }
                a();
            }
        }
    }

    /* JADX INFO: compiled from: Http2Reader.java */
    interface b {
        void a(boolean z, int i2, int i3, List<c> list);

        void b();

        void c(int i2, long j);

        void d(boolean z, m mVar);

        void e(int i2, int i3, List<c> list);

        void f(boolean z, int i2, BufferedSource bufferedSource, int i3);

        void g(boolean z, int i2, int i3);

        void h(int i2, f.k0.k.b bVar, ByteString byteString);

        void i(int i2, int i3, int i4, boolean z);

        void j(int i2, f.k0.k.b bVar);
    }

    h(BufferedSource bufferedSource, boolean z) {
        this.f5374c = bufferedSource;
        this.f5376e = z;
        a aVar = new a(bufferedSource);
        this.f5375d = aVar;
        this.f5377f = new d.a(4096, aVar);
    }

    private void C(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 < 8) {
            throw e.d("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i2));
        }
        if (i3 != 0) {
            throw e.d("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        int iW = this.f5374c.w();
        int iW2 = this.f5374c.w();
        int i4 = i2 - 8;
        f.k0.k.b bVarA = f.k0.k.b.a(iW2);
        if (bVarA == null) {
            throw e.d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(iW2));
        }
        ByteString byteStringQ = ByteString.f5781c;
        if (i4 > 0) {
            byteStringQ = this.f5374c.q(i4);
        }
        bVar.h(iW, bVarA, byteStringQ);
    }

    private List<c> R(int i2, short s, byte b2, int i3) throws IOException {
        a aVar = this.f5375d;
        aVar.f5382f = i2;
        aVar.f5379c = i2;
        aVar.f5383g = s;
        aVar.f5380d = b2;
        aVar.f5381e = i3;
        this.f5377f.k();
        return this.f5377f.e();
    }

    private void S(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw e.d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        short sP = (b2 & 8) != 0 ? (short) (this.f5374c.P() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            V(bVar, i3);
            i2 -= 5;
        }
        bVar.a(z, i3, -1, R(a(i2, b2, sP), sP, b2, i3));
    }

    static int T(BufferedSource bufferedSource) {
        return (bufferedSource.P() & 255) | ((bufferedSource.P() & 255) << 16) | ((bufferedSource.P() & 255) << 8);
    }

    private void U(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 8) {
            throw e.d("TYPE_PING length != 8: %s", Integer.valueOf(i2));
        }
        if (i3 != 0) {
            throw e.d("TYPE_PING streamId != 0", new Object[0]);
        }
        bVar.g((b2 & 1) != 0, this.f5374c.w(), this.f5374c.w());
    }

    private void V(b bVar, int i2) {
        int iW = this.f5374c.w();
        bVar.i(i2, iW & Integer.MAX_VALUE, (this.f5374c.P() & 255) + 1, (Integer.MIN_VALUE & iW) != 0);
    }

    private void W(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 5) {
            throw e.d("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i2));
        }
        if (i3 == 0) {
            throw e.d("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        V(bVar, i3);
    }

    private void X(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw e.d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        short sP = (b2 & 8) != 0 ? (short) (this.f5374c.P() & 255) : (short) 0;
        bVar.e(i3, this.f5374c.w() & Integer.MAX_VALUE, R(a(i2 - 4, b2, sP), sP, b2, i3));
    }

    private void Y(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 4) {
            throw e.d("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i2));
        }
        if (i3 == 0) {
            throw e.d("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        int iW = this.f5374c.w();
        f.k0.k.b bVarA = f.k0.k.b.a(iW);
        if (bVarA == null) {
            throw e.d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(iW));
        }
        bVar.j(i3, bVarA);
    }

    private void Z(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 != 0) {
            throw e.d("TYPE_SETTINGS streamId != 0", new Object[0]);
        }
        if ((b2 & 1) != 0) {
            if (i2 != 0) {
                throw e.d("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            bVar.b();
            return;
        }
        if (i2 % 6 != 0) {
            throw e.d("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i2));
        }
        m mVar = new m();
        for (int i4 = 0; i4 < i2; i4 += 6) {
            int iU = this.f5374c.u() & 65535;
            int iW = this.f5374c.w();
            if (iU == 2) {
                if (iW != 0 && iW != 1) {
                    throw e.d("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                }
            } else if (iU == 3) {
                iU = 4;
            } else if (iU == 4) {
                iU = 7;
                if (iW < 0) {
                    throw e.d("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                }
            } else if (iU == 5 && (iW < 16384 || iW > 16777215)) {
                throw e.d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(iW));
            }
            mVar.i(iU, iW);
        }
        bVar.d(false, mVar);
    }

    static int a(int i2, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i2--;
        }
        if (s <= i2) {
            return (short) (i2 - s);
        }
        throw e.d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i2));
    }

    private void a0(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 4) {
            throw e.d("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i2));
        }
        long jW = ((long) this.f5374c.w()) & 2147483647L;
        if (jW == 0) {
            throw e.d("windowSizeIncrement was 0", Long.valueOf(jW));
        }
        bVar.c(i3, jW);
    }

    private void y(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw e.d("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        if ((b2 & 32) != 0) {
            throw e.d("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        short sP = (b2 & 8) != 0 ? (short) (this.f5374c.P() & 255) : (short) 0;
        bVar.f(z, i3, this.f5374c, a(i2, b2, sP));
        this.f5374c.s(sP);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5374c.close();
    }

    public boolean d(boolean z, b bVar) throws IOException {
        try {
            this.f5374c.B(9L);
            int iT = T(this.f5374c);
            if (iT < 0 || iT > 16384) {
                throw e.d("FRAME_SIZE_ERROR: %s", Integer.valueOf(iT));
            }
            byte bP = (byte) (this.f5374c.P() & 255);
            if (z && bP != 4) {
                throw e.d("Expected a SETTINGS frame but was %s", Byte.valueOf(bP));
            }
            byte bP2 = (byte) (this.f5374c.P() & 255);
            int iW = this.f5374c.w() & Integer.MAX_VALUE;
            Logger logger = f5373b;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(e.b(true, iW, iT, bP, bP2));
            }
            switch (bP) {
                case 0:
                    y(bVar, iT, bP2, iW);
                    return true;
                case 1:
                    S(bVar, iT, bP2, iW);
                    return true;
                case 2:
                    W(bVar, iT, bP2, iW);
                    return true;
                case 3:
                    Y(bVar, iT, bP2, iW);
                    return true;
                case 4:
                    Z(bVar, iT, bP2, iW);
                    return true;
                case 5:
                    X(bVar, iT, bP2, iW);
                    return true;
                case 6:
                    U(bVar, iT, bP2, iW);
                    return true;
                case 7:
                    C(bVar, iT, bP2, iW);
                    return true;
                case 8:
                    a0(bVar, iT, bP2, iW);
                    return true;
                default:
                    this.f5374c.s(iT);
                    return true;
            }
        } catch (EOFException unused) {
            return false;
        }
    }

    public void j(b bVar) throws IOException {
        if (this.f5376e) {
            if (!d(true, bVar)) {
                throw e.d("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        BufferedSource bufferedSource = this.f5374c;
        ByteString byteString = e.f5309a;
        ByteString byteStringQ = bufferedSource.q(byteString.s());
        Logger logger = f5373b;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(f.k0.e.p("<< CONNECTION %s", byteStringQ.k()));
        }
        if (!byteString.equals(byteStringQ)) {
            throw e.d("Expected a connection header but was %s", byteStringQ.x());
        }
    }
}
