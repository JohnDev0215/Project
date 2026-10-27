package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import kotlin.text.b;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import p.de2;
import p.fo;
import p.io;
import p.ko;
import p.ng2;
import p.pd2;
import p.qw0;
import p.tl2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Http1ExchangeCodec implements ExchangeCodec {
    public static final /* synthetic */ int h = 0;
    public final OkHttpClient a;
    public final RealConnection b;
    public final ko c;
    public final io d;
    public int e;
    public final HeadersReader f;
    public Headers g;

    public abstract class AbstractSource implements de2 {
        public final qw0 c;
        public boolean e;

        public AbstractSource() {
            this.c = new qw0(Http1ExchangeCodec.this.c.e());
        }

        public final void b() {
            Http1ExchangeCodec http1ExchangeCodec = Http1ExchangeCodec.this;
            int i = http1ExchangeCodec.e;
            if (i == 6) {
                return;
            }
            if (i != 5) {
                throw new IllegalStateException("state: " + http1ExchangeCodec.e);
            }
            qw0 qw0Var = this.c;
            tl2 tl2Var = qw0Var.e;
            qw0Var.e = tl2.d;
            tl2Var.a();
            tl2Var.b();
            http1ExchangeCodec.e = 6;
        }

        @Override // p.de2
        public final tl2 e() {
            return this.c;
        }

        @Override // p.de2
        public long t(fo foVar, long j) throws IOException {
            Http1ExchangeCodec http1ExchangeCodec = Http1ExchangeCodec.this;
            v03.j(foVar, "sink");
            try {
                return http1ExchangeCodec.c.t(foVar, j);
            } catch (IOException e) {
                http1ExchangeCodec.b.k();
                b();
                throw e;
            }
        }
    }

    public final class ChunkedSink implements pd2 {
        public final qw0 c;
        public boolean e;

        public ChunkedSink() {
            this.c = new qw0(Http1ExchangeCodec.this.d.e());
        }

        @Override // p.pd2
        public final void Y(fo foVar, long j) {
            v03.j(foVar, "source");
            if (!(!this.e)) {
                throw new IllegalStateException("closed".toString());
            }
            if (j == 0) {
                return;
            }
            Http1ExchangeCodec http1ExchangeCodec = Http1ExchangeCodec.this;
            http1ExchangeCodec.d.s(j);
            http1ExchangeCodec.d.s0("\r\n");
            http1ExchangeCodec.d.Y(foVar, j);
            http1ExchangeCodec.d.s0("\r\n");
        }

        @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
        public final synchronized void close() {
            if (this.e) {
                return;
            }
            this.e = true;
            Http1ExchangeCodec.this.d.s0("0\r\n\r\n");
            Http1ExchangeCodec http1ExchangeCodec = Http1ExchangeCodec.this;
            qw0 qw0Var = this.c;
            http1ExchangeCodec.getClass();
            tl2 tl2Var = qw0Var.e;
            qw0Var.e = tl2.d;
            tl2Var.a();
            tl2Var.b();
            Http1ExchangeCodec.this.e = 3;
        }

        @Override // p.pd2
        public final tl2 e() {
            return this.c;
        }

        @Override // p.pd2, java.io.Flushable
        public final synchronized void flush() {
            if (this.e) {
                return;
            }
            Http1ExchangeCodec.this.d.flush();
        }
    }

    public final class ChunkedSource extends AbstractSource {
        public final HttpUrl l;
        public long m;
        public boolean n;
        public final /* synthetic */ Http1ExchangeCodec o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChunkedSource(Http1ExchangeCodec http1ExchangeCodec, HttpUrl httpUrl) {
            super();
            v03.j(httpUrl, "url");
            this.o = http1ExchangeCodec;
            this.l = httpUrl;
            this.m = -1L;
            this.n = true;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.e) {
                return;
            }
            if (this.n && !Util.h(this, TimeUnit.MILLISECONDS)) {
                this.o.b.k();
                b();
            }
            this.e = true;
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, p.de2
        public final long t(fo foVar, long j) throws IOException {
            v03.j(foVar, "sink");
            if (j < 0) {
                throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
            }
            if (!(!this.e)) {
                throw new IllegalStateException("closed".toString());
            }
            if (!this.n) {
                return -1L;
            }
            long j2 = this.m;
            Http1ExchangeCodec http1ExchangeCodec = this.o;
            if (j2 == 0 || j2 == -1) {
                if (j2 != -1) {
                    http1ExchangeCodec.c.M();
                }
                try {
                    this.m = http1ExchangeCodec.c.y0();
                    String string = b.G0(http1ExchangeCodec.c.M()).toString();
                    if (this.m < 0 || (string.length() > 0 && !ng2.g0(string, ";", false))) {
                        throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.m + string + '\"');
                    }
                    if (this.m == 0) {
                        this.n = false;
                        HeadersReader headersReader = http1ExchangeCodec.f;
                        headersReader.getClass();
                        Headers.Builder builder = new Headers.Builder();
                        while (true) {
                            String strG0 = headersReader.a.g0(headersReader.b);
                            headersReader.b -= (long) strG0.length();
                            if (strG0.length() == 0) {
                                break;
                            }
                            builder.b(strG0);
                        }
                        http1ExchangeCodec.g = builder.e();
                        OkHttpClient okHttpClient = http1ExchangeCodec.a;
                        v03.h(okHttpClient);
                        Headers headers = http1ExchangeCodec.g;
                        v03.h(headers);
                        HttpHeaders.d(okHttpClient.r, this.l, headers);
                        b();
                    }
                    if (!this.n) {
                        return -1L;
                    }
                } catch (NumberFormatException e) {
                    throw new ProtocolException(e.getMessage());
                }
            }
            long jT = super.t(foVar, Math.min(j, this.m));
            if (jT != -1) {
                this.m -= jT;
                return jT;
            }
            http1ExchangeCodec.b.k();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            b();
            throw protocolException;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public final class FixedLengthSource extends AbstractSource {
        public long l;

        public FixedLengthSource(long j) {
            super();
            this.l = j;
            if (j == 0) {
                b();
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.e) {
                return;
            }
            if (this.l != 0 && !Util.h(this, TimeUnit.MILLISECONDS)) {
                Http1ExchangeCodec.this.b.k();
                b();
            }
            this.e = true;
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, p.de2
        public final long t(fo foVar, long j) throws IOException {
            v03.j(foVar, "sink");
            if (j < 0) {
                throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
            }
            if (!(!this.e)) {
                throw new IllegalStateException("closed".toString());
            }
            long j2 = this.l;
            if (j2 == 0) {
                return -1L;
            }
            long jT = super.t(foVar, Math.min(j2, j));
            if (jT == -1) {
                Http1ExchangeCodec.this.b.k();
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                b();
                throw protocolException;
            }
            long j3 = this.l - jT;
            this.l = j3;
            if (j3 == 0) {
                b();
            }
            return jT;
        }
    }

    public final class KnownLengthSink implements pd2 {
        public final qw0 c;
        public boolean e;

        public KnownLengthSink() {
            this.c = new qw0(Http1ExchangeCodec.this.d.e());
        }

        @Override // p.pd2
        public final void Y(fo foVar, long j) {
            v03.j(foVar, "source");
            if (!(!this.e)) {
                throw new IllegalStateException("closed".toString());
            }
            long j2 = foVar.e;
            byte[] bArr = Util.a;
            if (j < 0 || 0 > j2 || j2 < j) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Http1ExchangeCodec.this.d.Y(foVar, j);
        }

        @Override // p.pd2, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.e) {
                return;
            }
            this.e = true;
            int i = Http1ExchangeCodec.h;
            Http1ExchangeCodec http1ExchangeCodec = Http1ExchangeCodec.this;
            http1ExchangeCodec.getClass();
            qw0 qw0Var = this.c;
            tl2 tl2Var = qw0Var.e;
            qw0Var.e = tl2.d;
            tl2Var.a();
            tl2Var.b();
            http1ExchangeCodec.e = 3;
        }

        @Override // p.pd2
        public final tl2 e() {
            return this.c;
        }

        @Override // p.pd2, java.io.Flushable
        public final void flush() {
            if (this.e) {
                return;
            }
            Http1ExchangeCodec.this.d.flush();
        }
    }

    public final class UnknownLengthSource extends AbstractSource {
        public boolean l;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            if (this.e) {
                return;
            }
            if (!this.l) {
                b();
            }
            this.e = true;
        }

        @Override // okhttp3.internal.http1.Http1ExchangeCodec.AbstractSource, p.de2
        public final long t(fo foVar, long j) throws IOException {
            v03.j(foVar, "sink");
            if (j < 0) {
                throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
            }
            if (!(!this.e)) {
                throw new IllegalStateException("closed".toString());
            }
            if (this.l) {
                return -1L;
            }
            long jT = super.t(foVar, j);
            if (jT != -1) {
                return jT;
            }
            this.l = true;
            b();
            return -1L;
        }
    }

    static {
        new Companion(0);
    }

    public Http1ExchangeCodec(OkHttpClient okHttpClient, RealConnection realConnection, ko koVar, io ioVar) {
        v03.j(realConnection, "connection");
        this.a = okHttpClient;
        this.b = realConnection;
        this.c = koVar;
        this.d = ioVar;
        this.f = new HeadersReader(koVar);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void a() {
        this.d.flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void b(Request request) {
        RequestLine requestLine = RequestLine.a;
        Proxy.Type type = this.b.b.b.type();
        v03.i(type, "connection.route().proxy.type()");
        requestLine.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(request.b);
        sb.append(' ');
        HttpUrl httpUrl = request.a;
        if (httpUrl.j || type != Proxy.Type.HTTP) {
            sb.append(RequestLine.a(httpUrl));
        } else {
            sb.append(httpUrl);
        }
        sb.append(" HTTP/1.1");
        String string = sb.toString();
        v03.i(string, "StringBuilder().apply(builderAction).toString()");
        k(request.c, string);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void c() {
        this.d.flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void cancel() {
        Socket socket = this.b.c;
        if (socket != null) {
            Util.d(socket);
        }
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final long d(Response response) {
        if (!HttpHeaders.a(response)) {
            return 0L;
        }
        if (ng2.b0("chunked", Response.c(response, "Transfer-Encoding"), true)) {
            return -1L;
        }
        return Util.k(response);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final de2 e(Response response) {
        if (!HttpHeaders.a(response)) {
            return i(0L);
        }
        if (ng2.b0("chunked", Response.c(response, "Transfer-Encoding"), true)) {
            HttpUrl httpUrl = response.c.a;
            if (this.e == 4) {
                this.e = 5;
                return new ChunkedSource(this, httpUrl);
            }
            throw new IllegalStateException(("state: " + this.e).toString());
        }
        long jK = Util.k(response);
        if (jK != -1) {
            return i(jK);
        }
        if (this.e == 4) {
            this.e = 5;
            this.b.k();
            return new UnknownLengthSource();
        }
        throw new IllegalStateException(("state: " + this.e).toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final pd2 f(Request request, long j) {
        if (ng2.b0("chunked", request.c.a("Transfer-Encoding"), true)) {
            if (this.e == 1) {
                this.e = 2;
                return new ChunkedSink();
            }
            throw new IllegalStateException(("state: " + this.e).toString());
        }
        if (j == -1) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        if (this.e == 1) {
            this.e = 2;
            return new KnownLengthSink();
        }
        throw new IllegalStateException(("state: " + this.e).toString());
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final Response.Builder g(boolean z) {
        HeadersReader headersReader = this.f;
        int i = this.e;
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalStateException(("state: " + this.e).toString());
        }
        try {
            StatusLine.Companion companion = StatusLine.d;
            String strG0 = headersReader.a.g0(headersReader.b);
            headersReader.b -= (long) strG0.length();
            companion.getClass();
            StatusLine statusLineA = StatusLine.Companion.a(strG0);
            int i2 = statusLineA.b;
            Response.Builder builder = new Response.Builder();
            Protocol protocol = statusLineA.a;
            v03.j(protocol, "protocol");
            builder.b = protocol;
            builder.c = i2;
            String str = statusLineA.c;
            v03.j(str, "message");
            builder.d = str;
            Headers.Builder builder2 = new Headers.Builder();
            while (true) {
                String strG02 = headersReader.a.g0(headersReader.b);
                headersReader.b -= (long) strG02.length();
                if (strG02.length() == 0) {
                    break;
                }
                builder2.b(strG02);
            }
            builder.c(builder2.e());
            if (z && i2 == 100) {
                return null;
            }
            if (i2 == 100) {
                this.e = 3;
                return builder;
            }
            if (102 > i2 || i2 >= 200) {
                this.e = 4;
                return builder;
            }
            this.e = 3;
            return builder;
        } catch (EOFException e) {
            throw new IOException(u4.x("unexpected end of stream on ", this.b.b.a.i.g()), e);
        }
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final RealConnection h() {
        return this.b;
    }

    public final de2 i(long j) {
        if (this.e == 4) {
            this.e = 5;
            return new FixedLengthSource(j);
        }
        throw new IllegalStateException(("state: " + this.e).toString());
    }

    public final void j(Response response) {
        long jK = Util.k(response);
        if (jK == -1) {
            return;
        }
        de2 de2VarI = i(jK);
        Util.v(de2VarI, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        ((FixedLengthSource) de2VarI).close();
    }

    public final void k(Headers headers, String str) {
        v03.j(headers, "headers");
        v03.j(str, "requestLine");
        if (this.e != 0) {
            throw new IllegalStateException(("state: " + this.e).toString());
        }
        io ioVar = this.d;
        ioVar.s0(str).s0("\r\n");
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            ioVar.s0(headers.b(i)).s0(": ").s0(headers.d(i)).s0("\r\n");
        }
        ioVar.s0("\r\n");
        this.e = 1;
    }
}
