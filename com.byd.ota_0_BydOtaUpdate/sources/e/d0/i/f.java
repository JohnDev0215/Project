package e.d0.i;

import e.d0.i.b;
import f.q;
import f.r;
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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Logger f3490c = Logger.getLogger(c.class.getName());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f.e f3491d;
    public final a s;
    public final boolean x;
    public final b.a y;

    /* JADX INFO: compiled from: Http2Reader.java */
    public static final class a implements q {
        public short E3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final f.e f3492c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3493d;
        public byte s;
        public int x;
        public int y;

        public a(f.e eVar) {
            this.f3492c = eVar;
        }

        public final void c() throws IOException {
            int i2 = this.x;
            int iK = f.K(this.f3492c);
            this.y = iK;
            this.f3493d = iK;
            byte b2 = (byte) (this.f3492c.readByte() & 255);
            this.s = (byte) (this.f3492c.readByte() & 255);
            Logger logger = f.f3490c;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(c.b(true, this.x, this.f3493d, b2, this.s));
            }
            int i3 = this.f3492c.readInt() & Integer.MAX_VALUE;
            this.x = i3;
            if (b2 != 9) {
                throw c.d("%s != TYPE_CONTINUATION", Byte.valueOf(b2));
            }
            if (i3 != i2) {
                throw c.d("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }

        @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // f.q
        public r e() {
            return this.f3492c.e();
        }

        @Override // f.q
        public long n(f.c cVar, long j) throws IOException {
            while (true) {
                int i2 = this.y;
                if (i2 != 0) {
                    long jN = this.f3492c.n(cVar, Math.min(j, i2));
                    if (jN == -1) {
                        return -1L;
                    }
                    this.y = (int) (((long) this.y) - jN);
                    return jN;
                }
                this.f3492c.skip(this.E3);
                this.E3 = (short) 0;
                if ((this.s & 4) != 0) {
                    return -1L;
                }
                c();
            }
        }
    }

    /* JADX INFO: compiled from: Http2Reader.java */
    public interface b {
        void a(boolean z, int i2, int i3, List<e.d0.i.a> list);

        void b();

        void c(int i2, long j);

        void d(boolean z, l lVar);

        void e(int i2, int i3, List<e.d0.i.a> list) throws IOException;

        void f(boolean z, int i2, f.e eVar, int i3) throws IOException;

        void g(boolean z, int i2, int i3);

        void h(int i2, ErrorCode errorCode, ByteString byteString);

        void i(int i2, int i3, int i4, boolean z);

        void j(int i2, ErrorCode errorCode);
    }

    public f(f.e eVar, boolean z) {
        this.f3491d = eVar;
        this.x = z;
        a aVar = new a(eVar);
        this.s = aVar;
        this.y = new b.a(4096, aVar);
    }

    public static int K(f.e eVar) throws IOException {
        return (eVar.readByte() & 255) | ((eVar.readByte() & 255) << 16) | ((eVar.readByte() & 255) << 8);
    }

    public static int c(int i2, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i2--;
        }
        if (s <= i2) {
            return (short) (i2 - s);
        }
        throw c.d("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i2));
    }

    public final void H(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 < 8) {
            throw c.d("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i2));
        }
        if (i3 != 0) {
            throw c.d("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        int i4 = this.f3491d.readInt();
        int i5 = this.f3491d.readInt();
        int i6 = i2 - 8;
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i5);
        if (errorCodeFromHttp2 == null) {
            throw c.d("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i5));
        }
        ByteString byteStringO = ByteString.EMPTY;
        if (i6 > 0) {
            byteStringO = this.f3491d.o(i6);
        }
        bVar.h(i4, errorCodeFromHttp2, byteStringO);
    }

    public final List<e.d0.i.a> I(int i2, short s, byte b2, int i3) throws IOException {
        a aVar = this.s;
        aVar.y = i2;
        aVar.f3493d = i2;
        aVar.E3 = s;
        aVar.s = b2;
        aVar.x = i3;
        this.y.k();
        return this.y.e();
    }

    public final void J(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw c.d("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        short s = (b2 & 8) != 0 ? (short) (this.f3491d.readByte() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            M(bVar, i3);
            i2 -= 5;
        }
        bVar.a(z, i3, -1, I(c(i2, b2, s), s, b2, i3));
    }

    public final void L(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 8) {
            throw c.d("TYPE_PING length != 8: %s", Integer.valueOf(i2));
        }
        if (i3 != 0) {
            throw c.d("TYPE_PING streamId != 0", new Object[0]);
        }
        bVar.g((b2 & 1) != 0, this.f3491d.readInt(), this.f3491d.readInt());
    }

    public final void M(b bVar, int i2) throws IOException {
        int i3 = this.f3491d.readInt();
        bVar.i(i2, i3 & Integer.MAX_VALUE, (this.f3491d.readByte() & 255) + 1, (Integer.MIN_VALUE & i3) != 0);
    }

    public final void N(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 5) {
            throw c.d("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i2));
        }
        if (i3 == 0) {
            throw c.d("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        M(bVar, i3);
    }

    public final void O(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw c.d("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        short s = (b2 & 8) != 0 ? (short) (this.f3491d.readByte() & 255) : (short) 0;
        bVar.e(i3, this.f3491d.readInt() & Integer.MAX_VALUE, I(c(i2 - 4, b2, s), s, b2, i3));
    }

    public final void P(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 4) {
            throw c.d("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i2));
        }
        if (i3 == 0) {
            throw c.d("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        int i4 = this.f3491d.readInt();
        ErrorCode errorCodeFromHttp2 = ErrorCode.fromHttp2(i4);
        if (errorCodeFromHttp2 == null) {
            throw c.d("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i4));
        }
        bVar.j(i3, errorCodeFromHttp2);
    }

    public final void Q(b bVar, int i2, byte b2, int i3) throws IOException {
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
            short s = this.f3491d.readShort();
            int i5 = this.f3491d.readInt();
            if (s == 2) {
                if (i5 != 0 && i5 != 1) {
                    throw c.d("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                }
            } else if (s == 3) {
                s = 4;
            } else if (s == 4) {
                s = 7;
                if (i5 < 0) {
                    throw c.d("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                }
            } else if (s == 5 && (i5 < 16384 || i5 > 16777215)) {
                throw c.d("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(i5));
            }
            lVar.i(s, i5);
        }
        bVar.d(false, lVar);
    }

    public final void R(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i2 != 4) {
            throw c.d("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i2));
        }
        long j = ((long) this.f3491d.readInt()) & 2147483647L;
        if (j == 0) {
            throw c.d("windowSizeIncrement was 0", Long.valueOf(j));
        }
        bVar.c(i3, j);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f3491d.close();
    }

    public boolean d(boolean z, b bVar) throws IOException {
        try {
            this.f3491d.u(9L);
            int iK = K(this.f3491d);
            if (iK < 0 || iK > 16384) {
                throw c.d("FRAME_SIZE_ERROR: %s", Integer.valueOf(iK));
            }
            byte b2 = (byte) (this.f3491d.readByte() & 255);
            if (z && b2 != 4) {
                throw c.d("Expected a SETTINGS frame but was %s", Byte.valueOf(b2));
            }
            byte b3 = (byte) (this.f3491d.readByte() & 255);
            int i2 = this.f3491d.readInt() & Integer.MAX_VALUE;
            Logger logger = f3490c;
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(c.b(true, i2, iK, b2, b3));
            }
            switch (b2) {
                case 0:
                    v(bVar, iK, b3, i2);
                    return true;
                case 1:
                    J(bVar, iK, b3, i2);
                    return true;
                case 2:
                    N(bVar, iK, b3, i2);
                    return true;
                case 3:
                    P(bVar, iK, b3, i2);
                    return true;
                case 4:
                    Q(bVar, iK, b3, i2);
                    return true;
                case 5:
                    O(bVar, iK, b3, i2);
                    return true;
                case 6:
                    L(bVar, iK, b3, i2);
                    return true;
                case 7:
                    H(bVar, iK, b3, i2);
                    return true;
                case 8:
                    R(bVar, iK, b3, i2);
                    return true;
                default:
                    this.f3491d.skip(iK);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public void s(b bVar) throws IOException {
        if (this.x) {
            if (!d(true, bVar)) {
                throw c.d("Required SETTINGS preface not received", new Object[0]);
            }
            return;
        }
        f.e eVar = this.f3491d;
        ByteString byteString = c.f3455a;
        ByteString byteStringO = eVar.o(byteString.size());
        Logger logger = f3490c;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(e.d0.c.l("<< CONNECTION %s", byteStringO.hex()));
        }
        if (!byteString.equals(byteStringO)) {
            throw c.d("Expected a connection header but was %s", byteStringO.utf8());
        }
    }

    public final void v(b bVar, int i2, byte b2, int i3) throws IOException {
        if (i3 == 0) {
            throw c.d("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean z = (b2 & 1) != 0;
        if ((b2 & 32) != 0) {
            throw c.d("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        short s = (b2 & 8) != 0 ? (short) (this.f3491d.readByte() & 255) : (short) 0;
        bVar.f(z, i3, this.f3491d, c(i2, b2, s));
        this.f3491d.skip(s);
    }
}
