package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
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
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.http2.Http2Stream;
import okio.ByteString;
import p.de2;
import p.pd2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Http2ExchangeCodec implements ExchangeCodec {
    public static final Companion g = new Companion(0);
    public static final List h = Util.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");
    public static final List i = Util.l("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");
    public final RealConnection a;
    public final RealInterceptorChain b;
    public final Http2Connection c;
    public volatile Http2Stream d;
    public final Protocol e;
    public volatile boolean f;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public Http2ExchangeCodec(OkHttpClient okHttpClient, RealConnection realConnection, RealInterceptorChain realInterceptorChain, Http2Connection http2Connection) {
        v03.j(realConnection, "connection");
        this.a = realConnection;
        this.b = realInterceptorChain;
        this.c = http2Connection;
        Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        this.e = okHttpClient.B.contains(protocol) ? protocol : Protocol.HTTP_2;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void a() {
        Http2Stream http2Stream = this.d;
        v03.h(http2Stream);
        http2Stream.f().close();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void b(Request request) throws IOException {
        int i2;
        Http2Stream http2Stream;
        boolean z;
        if (this.d != null) {
            return;
        }
        boolean z2 = request.d != null;
        g.getClass();
        Headers headers = request.c;
        ArrayList arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new Header(Header.f, request.b));
        ByteString byteString = Header.g;
        RequestLine requestLine = RequestLine.a;
        HttpUrl httpUrl = request.a;
        requestLine.getClass();
        arrayList.add(new Header(byteString, RequestLine.a(httpUrl)));
        String strA = request.c.a("Host");
        if (strA != null) {
            arrayList.add(new Header(Header.i, strA));
        }
        arrayList.add(new Header(Header.h, httpUrl.a));
        int size = headers.size();
        for (int i3 = 0; i3 < size; i3++) {
            String strB = headers.b(i3);
            Locale locale = Locale.US;
            v03.i(locale, "US");
            String lowerCase = strB.toLowerCase(locale);
            v03.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (!h.contains(lowerCase) || (v03.b(lowerCase, "te") && v03.b(headers.d(i3), "trailers"))) {
                arrayList.add(new Header(lowerCase, headers.d(i3)));
            }
        }
        Http2Connection http2Connection = this.c;
        http2Connection.getClass();
        boolean z3 = !z2;
        synchronized (http2Connection.G) {
            synchronized (http2Connection) {
                try {
                    if (http2Connection.n > 1073741823) {
                        http2Connection.E(ErrorCode.REFUSED_STREAM);
                    }
                    if (http2Connection.o) {
                        throw new ConnectionShutdownException();
                    }
                    i2 = http2Connection.n;
                    http2Connection.n = i2 + 2;
                    http2Stream = new Http2Stream(i2, http2Connection, z3, false, null);
                    z = !z2 || http2Connection.D >= http2Connection.E || http2Stream.e >= http2Stream.f;
                    if (http2Stream.h()) {
                        http2Connection.k.put(Integer.valueOf(i2), http2Stream);
                    }
                } finally {
                }
            }
            Http2Writer http2Writer = http2Connection.G;
            synchronized (http2Writer) {
                if (http2Writer.m) {
                    throw new IOException("closed");
                }
                http2Writer.n.d(arrayList);
                long j = http2Writer.k.e;
                long jMin = Math.min(http2Writer.l, j);
                int i4 = j == jMin ? 4 : 0;
                if (z3) {
                    i4 |= 1;
                }
                http2Writer.i(i2, (int) jMin, 1, i4);
                http2Writer.c.Y(http2Writer.k, jMin);
                if (j > jMin) {
                    http2Writer.o0(i2, j - jMin);
                }
            }
        }
        if (z) {
            http2Connection.G.flush();
        }
        this.d = http2Stream;
        if (this.f) {
            Http2Stream http2Stream2 = this.d;
            v03.h(http2Stream2);
            http2Stream2.e(ErrorCode.CANCEL);
            throw new IOException("Canceled");
        }
        Http2Stream http2Stream3 = this.d;
        v03.h(http2Stream3);
        Http2Stream.StreamTimeout streamTimeout = http2Stream3.k;
        long j2 = this.b.g;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        streamTimeout.g(j2, timeUnit);
        Http2Stream http2Stream4 = this.d;
        v03.h(http2Stream4);
        http2Stream4.l.g(this.b.h, timeUnit);
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void c() {
        this.c.flush();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final void cancel() {
        this.f = true;
        Http2Stream http2Stream = this.d;
        if (http2Stream != null) {
            http2Stream.e(ErrorCode.CANCEL);
        }
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final long d(Response response) {
        if (HttpHeaders.a(response)) {
            return Util.k(response);
        }
        return 0L;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final de2 e(Response response) {
        Http2Stream http2Stream = this.d;
        v03.h(http2Stream);
        return http2Stream.i;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final pd2 f(Request request, long j) {
        Http2Stream http2Stream = this.d;
        v03.h(http2Stream);
        return http2Stream.f();
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final Response.Builder g(boolean z) throws IOException {
        Headers headers;
        Http2Stream http2Stream = this.d;
        if (http2Stream == null) {
            throw new IOException("stream wasn't created");
        }
        synchronized (http2Stream) {
            http2Stream.k.i();
            while (http2Stream.g.isEmpty() && http2Stream.m == null) {
                try {
                    http2Stream.k();
                } catch (Throwable th) {
                    http2Stream.k.m();
                    throw th;
                }
            }
            http2Stream.k.m();
            if (!(!http2Stream.g.isEmpty())) {
                IOException iOException = http2Stream.n;
                if (iOException != null) {
                    throw iOException;
                }
                ErrorCode errorCode = http2Stream.m;
                v03.h(errorCode);
                throw new StreamResetException(errorCode);
            }
            Object objRemoveFirst = http2Stream.g.removeFirst();
            v03.i(objRemoveFirst, "headersQueue.removeFirst()");
            headers = (Headers) objRemoveFirst;
        }
        Companion companion = g;
        Protocol protocol = this.e;
        companion.getClass();
        v03.j(protocol, "protocol");
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        StatusLine statusLineA = null;
        for (int i2 = 0; i2 < size; i2++) {
            String strB = headers.b(i2);
            String strD = headers.d(i2);
            if (v03.b(strB, ":status")) {
                StatusLine.d.getClass();
                statusLineA = StatusLine.Companion.a("HTTP/1.1 " + strD);
            } else if (!i.contains(strB)) {
                builder.c(strB, strD);
            }
        }
        if (statusLineA == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        Response.Builder builder2 = new Response.Builder();
        builder2.b = protocol;
        builder2.c = statusLineA.b;
        String str = statusLineA.c;
        v03.j(str, "message");
        builder2.d = str;
        builder2.c(builder.e());
        if (z && builder2.c == 100) {
            return null;
        }
        return builder2;
    }

    @Override // okhttp3.internal.http.ExchangeCodec
    public final RealConnection h() {
        return this.a;
    }
}
