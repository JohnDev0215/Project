package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.ByteString;
import p.de2;
import p.fo;
import p.hf2;
import p.ko;
import p.tl2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Http2Reader implements Closeable {
    public static final Companion m = new Companion(0);
    public static final Logger n;
    public final ko c;
    public final boolean e;
    public final ContinuationSource k;
    public final Hpack.Reader l;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static int a(int i, int i2, int i3) throws IOException {
            if ((i2 & 8) != 0) {
                i--;
            }
            if (i3 <= i) {
                return i - i3;
            }
            throw new IOException(hf2.c("PROTOCOL_ERROR padding ", i3, " > remaining length ", i));
        }
    }

    public static final class ContinuationSource implements de2 {
        public final ko c;
        public int e;
        public int k;
        public int l;
        public int m;
        public int n;

        public ContinuationSource(ko koVar) {
            this.c = koVar;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
        }

        @Override // p.de2
        public final tl2 e() {
            return this.c.e();
        }

        @Override // p.de2
        public final long t(fo foVar, long j) throws IOException {
            int i;
            int i2;
            v03.j(foVar, "sink");
            do {
                int i3 = this.m;
                ko koVar = this.c;
                if (i3 != 0) {
                    long jT = koVar.t(foVar, Math.min(j, i3));
                    if (jT == -1) {
                        return -1L;
                    }
                    this.m -= (int) jT;
                    return jT;
                }
                koVar.y(this.n);
                this.n = 0;
                if ((this.k & 4) != 0) {
                    return -1L;
                }
                i = this.l;
                int iT = Util.t(koVar);
                this.m = iT;
                this.e = iT;
                int i4 = koVar.readByte() & 255;
                this.k = koVar.readByte() & 255;
                Http2Reader.m.getClass();
                Logger logger = Http2Reader.n;
                if (logger.isLoggable(Level.FINE)) {
                    Http2 http2 = Http2.a;
                    int i5 = this.l;
                    int i6 = this.e;
                    int i7 = this.k;
                    http2.getClass();
                    logger.fine(Http2.a(i5, i6, i4, i7, true));
                }
                i2 = koVar.readInt() & Integer.MAX_VALUE;
                this.l = i2;
                if (i4 != 9) {
                    throw new IOException(i4 + " != TYPE_CONTINUATION");
                }
            } while (i2 == i);
            throw new IOException("TYPE_CONTINUATION streamId changed");
        }
    }

    public interface Handler {
        void a();

        void b(int i, int i2, ko koVar, boolean z);

        void c();

        void d(List list, int i);

        void f(Settings settings);

        void h(int i, ErrorCode errorCode);

        void i(int i, List list, boolean z);

        void j(int i, long j);

        void k(int i, int i2, boolean z);

        void l(int i, ErrorCode errorCode, ByteString byteString);
    }

    static {
        Logger logger = Logger.getLogger(Http2.class.getName());
        v03.i(logger, "getLogger(Http2::class.java.name)");
        n = logger;
    }

    public Http2Reader(ko koVar, boolean z) {
        this.c = koVar;
        this.e = z;
        ContinuationSource continuationSource = new ContinuationSource(koVar);
        this.k = continuationSource;
        this.l = new Hpack.Reader(continuationSource);
    }

    /* JADX WARN: Code restructure failed: missing block: B:92:0x01bd, code lost:
    
        throw new java.io.IOException(p.u4.e("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ", r9));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(boolean r18, okhttp3.internal.http2.Http2Reader.Handler r19) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 750
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Reader.b(boolean, okhttp3.internal.http2.Http2Reader$Handler):boolean");
    }

    public final void c(Handler handler) throws IOException {
        v03.j(handler, "handler");
        if (this.e) {
            if (!b(true, handler)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        ByteString byteString = Http2.b;
        ByteString byteStringV = this.c.v(byteString.e());
        Level level = Level.FINE;
        Logger logger = n;
        if (logger.isLoggable(level)) {
            logger.fine(Util.i("<< CONNECTION " + byteStringV.f(), new Object[0]));
        }
        if (!v03.b(byteString, byteStringV)) {
            throw new IOException("Expected a connection header but was ".concat(byteStringV.s()));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.c.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
    
        throw new java.io.IOException(p.u4.e("Header index too large ", r5));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List i(int r4, int r5, int r6, int r7) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Reader.i(int, int, int, int):java.util.List");
    }

    public final void q(Handler handler, int i) {
        ko koVar = this.c;
        koVar.readInt();
        koVar.readByte();
        byte[] bArr = Util.a;
        handler.c();
    }
}
