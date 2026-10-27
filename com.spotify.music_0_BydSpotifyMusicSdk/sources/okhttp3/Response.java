package okhttp3;

import java.io.Closeable;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.internal.connection.Exchange;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Response implements Closeable {
    public final Request c;
    public final Protocol e;
    public final String k;
    public final int l;
    public final Handshake m;
    public final Headers n;
    public final ResponseBody o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Response f96p;
    public final Response q;
    public final Response r;
    public final long s;
    public final long t;
    public final Exchange u;
    public CacheControl v;

    public static class Builder {
        public Request a;
        public Protocol b;
        public String d;
        public Handshake e;
        public ResponseBody g;
        public Response h;
        public Response i;
        public Response j;
        public long k;
        public long l;
        public Exchange m;
        public int c = -1;
        public Headers.Builder f = new Headers.Builder();

        public static void b(String str, Response response) {
            if (response != null) {
                if (response.o != null) {
                    throw new IllegalArgumentException(str.concat(".body != null").toString());
                }
                if (response.f96p != null) {
                    throw new IllegalArgumentException(str.concat(".networkResponse != null").toString());
                }
                if (response.q != null) {
                    throw new IllegalArgumentException(str.concat(".cacheResponse != null").toString());
                }
                if (response.r != null) {
                    throw new IllegalArgumentException(str.concat(".priorResponse != null").toString());
                }
            }
        }

        public final Response a() {
            int i = this.c;
            if (i < 0) {
                throw new IllegalStateException(("code < 0: " + this.c).toString());
            }
            Request request = this.a;
            if (request == null) {
                throw new IllegalStateException("request == null".toString());
            }
            Protocol protocol = this.b;
            if (protocol == null) {
                throw new IllegalStateException("protocol == null".toString());
            }
            String str = this.d;
            if (str != null) {
                return new Response(request, protocol, str, i, this.e, this.f.e(), this.g, this.h, this.i, this.j, this.k, this.l, this.m);
            }
            throw new IllegalStateException("message == null".toString());
        }

        public final void c(Headers headers) {
            v03.j(headers, "headers");
            this.f = headers.c();
        }
    }

    public Response(Request request, Protocol protocol, String str, int i, Handshake handshake, Headers headers, ResponseBody responseBody, Response response, Response response2, Response response3, long j, long j2, Exchange exchange) {
        this.c = request;
        this.e = protocol;
        this.k = str;
        this.l = i;
        this.m = handshake;
        this.n = headers;
        this.o = responseBody;
        this.f96p = response;
        this.q = response2;
        this.r = response3;
        this.s = j;
        this.t = j2;
        this.u = exchange;
    }

    public static String c(Response response, String str) {
        response.getClass();
        String strA = response.n.a(str);
        if (strA == null) {
            return null;
        }
        return strA;
    }

    public final CacheControl b() {
        CacheControl cacheControl = this.v;
        if (cacheControl != null) {
            return cacheControl;
        }
        CacheControl.n.getClass();
        CacheControl cacheControlA = CacheControl.Companion.a(this.n);
        this.v = cacheControlA;
        return cacheControlA;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ResponseBody responseBody = this.o;
        if (responseBody == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed".toString());
        }
        responseBody.close();
    }

    public final boolean i() {
        int i = this.l;
        return 200 <= i && i < 300;
    }

    public final Builder q() {
        Builder builder = new Builder();
        builder.a = this.c;
        builder.b = this.e;
        builder.c = this.l;
        builder.d = this.k;
        builder.e = this.m;
        builder.f = this.n.c();
        builder.g = this.o;
        builder.h = this.f96p;
        builder.i = this.q;
        builder.j = this.r;
        builder.k = this.s;
        builder.l = this.t;
        builder.m = this.u;
        return builder;
    }

    public final String toString() {
        return "Response{protocol=" + this.e + ", code=" + this.l + ", message=" + this.k + ", url=" + this.c.a + '}';
    }
}
